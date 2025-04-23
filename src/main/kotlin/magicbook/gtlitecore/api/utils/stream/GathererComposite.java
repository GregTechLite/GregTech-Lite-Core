package magicbook.gtlitecore.api.utils.stream;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public final class GathererComposite<T, A, R, AA, RR> implements Gatherer<T, Object, RR>
{

    private final Gatherer<T, A, ? extends R> left;
    private final Gatherer<? super R, AA, ? extends RR> right;
    private volatile GathererImpl<T, Object, RR> impl;

    public static <T, A, R, AA, RR> GathererComposite<T, A, R, AA, RR> of(Gatherer<T, A, ? extends R> left,
                                                                          Gatherer<? super R, AA, ? extends RR> right)
    {
        return new GathererComposite<>(Objects.requireNonNull(left),
                Objects.requireNonNull(right));
    }

    /* package */ GathererComposite(Gatherer<T, A, ? extends R> left,
                                    Gatherer<? super R, AA, ? extends RR> right)
    {
        this.left = left;
        this.right = right;
    }

    @SuppressWarnings("unchecked")
    private GathererImpl<T, Object, RR> impl()
    {
        GathererImpl<T, Object, RR> result = impl;
        if (result == null)
        {
            synchronized (this)
            {
                result = impl;
                if (result == null)
                    impl = result = (GathererImpl<T, Object, RR>) impl(left, right);
            }
        }
        return result;
    }

    @Override
    public Supplier<Object> initializer()
    {
        return impl().initializer();
    }

    @Override
    public Gatherer.Integrator<Object, T, RR> integrator()
    {
        return impl().integrator();
    }

    @Override
    public BinaryOperator<Object> combiner()
    {
        return impl().combiner();
    }

    @Override
    public BiConsumer<Object, Downstream<? super RR>> finisher()
    {
        return impl().finisher();
    }

    @Override
    public <RRR> Gatherer<T, ?, RRR> andThen(Gatherer<? super RR, ?, ? extends RRR> that)
    {
        if (that instanceof GathererComposite)
        {
            @SuppressWarnings("unchecked")
            GathererComposite<? super RR, ?, Object, ?, ? extends RRR> c = (GathererComposite<? super RR, ?, Object, ?, ? extends RRR>) that;
            return left.andThen(right.andThen(c.left).andThen(c.right));
        }
        else
        {
            return left.andThen(right.andThen(that));
        }
    }

    public static <T, A, R, AA, RR> GathererImpl<T, ?, RR> impl(Gatherer<T, A, R> left,
                                                                Gatherer<? super R, AA, RR> right)
    {
        final Supplier<A> leftInitializer = left.initializer();
        final Gatherer.Integrator<A, T, R> leftIntegrator = left.integrator();
        final BinaryOperator<A> leftCombiner = left.combiner();
        final BiConsumer<A, Gatherer.Downstream<? super R>> leftFinisher = left.finisher();

        final Supplier<AA> rightInitializer = right.initializer();
        final Gatherer.Integrator<AA, ? super R, RR> rightIntegrator = right.integrator();
        final BinaryOperator<AA> rightCombiner = right.combiner();
        final BiConsumer<AA, Gatherer.Downstream<? super RR>> rightFinisher = right.finisher();

        final boolean leftStateless = leftInitializer == Gatherer.<A>defaultInitializer();
        final boolean rightStateless = rightInitializer == Gatherer.<AA>defaultInitializer();

        final boolean leftGreedy = leftIntegrator instanceof Gatherer.Integrator.Greedy;
        final boolean rightGreedy = rightIntegrator instanceof Gatherer.Integrator.Greedy;

        if (leftStateless && rightStateless && leftGreedy && rightGreedy)
        {
            return new GathererImpl<>(
                    Gatherer.<Void>defaultInitializer(),
                    Gatherer.Integrator.ofGreedy(new Gatherer.Integrator.Greedy<Void, T, RR>()
                    {
                        @Override
                        public boolean integrate(Void unused, T element, Gatherer.Downstream<? super RR> downstream)
                        {
                            return leftIntegrator.integrate(null, element,
                                    new Gatherer.Downstream<R>()
                                    {
                                        @Override
                                        public boolean push(R r)
                                        {
                                            return rightIntegrator.integrate(null, r, downstream);
                                        }

                                    });
                        }
                    }),
                    (leftCombiner == Gatherer.<A>defaultCombiner() || rightCombiner == Gatherer.<AA>defaultCombiner())
                            ? Gatherer.<Void>defaultCombiner()
                            : GathererValue.DEFAULT.statelessCombiner,
                    (leftFinisher == Gatherer.<A, R>defaultFinisher() && rightFinisher == Gatherer.<AA, RR>defaultFinisher())
                            ? Gatherer.<Void, RR>defaultFinisher()
                            : new BiConsumer<Void, Gatherer.Downstream<? super RR>>()
                    {
                        @Override
                        public void accept(Void unused, Gatherer.Downstream<? super RR> downstream)
                        {
                            if (leftFinisher != Gatherer.<A, R>defaultFinisher())
                            {
                                leftFinisher.accept(null,
                                        new Gatherer.Downstream<R>()
                                        {
                                            @Override
                                            public boolean push(R r)
                                            {
                                                return rightIntegrator.integrate(null, r, downstream);
                                            }
                                        });
                            }
                            if (rightFinisher != Gatherer.<AA, RR>defaultFinisher())
                            {
                                rightFinisher.accept(null, downstream);
                            }
                        }
                    }
            );
        }
        else
        {
            class State
            {
                final A leftState;
                final AA rightState;
                boolean leftProceed;
                boolean rightProceed;

                private State(A leftState, AA rightState, boolean leftProceed, boolean rightProceed)
                {
                    this.leftState = leftState;
                    this.rightState = rightState;
                    this.leftProceed = leftProceed;
                    this.rightProceed = rightProceed;
                }

                State()
                {
                    this(leftStateless ? null : leftInitializer.get(),
                            rightStateless ? null : rightInitializer.get(),
                            true, true);
                }

                State joinLeft(State right)
                {
                    return new State(
                            leftStateless ? null : leftCombiner.apply(this.leftState, right.leftState),
                            rightStateless ? null : rightCombiner.apply(this.rightState, right.rightState),
                            this.leftProceed && right.leftProceed,
                            this.rightProceed && right.rightProceed);
                }

                boolean integrate(T t, Gatherer.Downstream<? super RR> c)
                {
                    return (leftIntegrator.integrate(leftState, t, new Gatherer.Downstream<R>()
                    {
                        @Override
                        public boolean push(R r) {
                            return rightIntegrate(r, c);
                        }
                    }) || leftGreedy || (leftProceed = false)) && (rightGreedy || rightProceed);
                }

                void finish(Gatherer.Downstream<? super RR> c)
                {
                    if (leftFinisher != Gatherer.<A, R>defaultFinisher()) {
                        leftFinisher.accept(leftState, new Gatherer.Downstream<R>()
                        {
                            @Override
                            public boolean push(R r)
                            {
                                return rightIntegrate(r, c);
                            }
                        });
                    }
                    if (rightFinisher != Gatherer.<AA, RR>defaultFinisher())
                    {
                        rightFinisher.accept(rightState, c);
                    }
                }

                public boolean rightIntegrate(R r, Gatherer.Downstream<? super RR> downstream)
                {
                    return (rightGreedy || rightProceed)
                            && (rightIntegrator.integrate(rightState, r, downstream)
                            || rightGreedy
                            || (rightProceed = false));
                }
            }

            return new GathererImpl<T, State, RR>(
                    new Supplier<State>() {
                        @Override
                        public State get() {
                            return new State();
                        }
                    },
                    (leftGreedy && rightGreedy)
                            ? Gatherer.Integrator.<State, T, RR>ofGreedy(new Gatherer.Integrator.Greedy<State, T, RR>() {
                        @Override
                        public boolean integrate(State state, T element, Gatherer.Downstream<? super RR> downstream) {
                            return state.integrate(element, downstream);
                        }
                    })
                            : Gatherer.Integrator.<State, T, RR>of(new Gatherer.Integrator<State, T, RR>() {
                        @Override
                        public boolean integrate(State state, T element, Gatherer.Downstream<? super RR> downstream) {
                            return state.integrate(element, downstream);
                        }
                    }),
                    (leftCombiner == Gatherer.<A>defaultCombiner() || rightCombiner == Gatherer.<AA>defaultCombiner())
                            ? Gatherer.<State>defaultCombiner()
                            : new BinaryOperator<State>() {
                        @Override
                        public State apply(State s1, State s2) {
                            return s1.joinLeft(s2);
                        }
                    },
                    (leftFinisher == Gatherer.<A, R>defaultFinisher() && rightFinisher == Gatherer.<AA, RR>defaultFinisher())
                            ? Gatherer.<State, RR>defaultFinisher()
                            : new BiConsumer<State, Gatherer.Downstream<? super RR>>() {
                        @Override
                        public void accept(State state, Gatherer.Downstream<? super RR> downstream) {
                            state.finish(downstream);
                        }
                    }
            );
        }
    }

}
