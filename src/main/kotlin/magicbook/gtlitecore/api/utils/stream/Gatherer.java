package magicbook.gtlitecore.api.utils.stream;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public interface Gatherer<T, A, R>
{

    /**
     * A function that produces an instance of the intermediate state used for
     * this gathering operation.
     *
     * @return A function that produces an instance of the intermediate state used for this
     *         gathering operation.
     *
     * @implSpec The implementation in this interface returns {@link #defaultInitializer()}.
     */
    default Supplier<A> initializer()
    {
        return defaultInitializer();
    }

    /**
     * Returns an initializer which is the default initializer of a {@code Gatherer}.
     * <p>
     * The returned initializer identifies that the owner {@code Gatherer} is stateless.
     *
     * @return The instance of the default initializer.
     *
     * @param <A> The type of the state of the returned initializer.
     *
     * @implSpec This method always returns the same instance.
     *
     * @see Gatherer#initializer()
     */
    static <A> Supplier<A> defaultInitializer()
    {
        return GathererValue.DEFAULT.initializer();
    }

    /**
     * A function which integrates provided elements, potentially using the provided
     * intermediate state, optionally producing output to the provided {@link Downstream}.
     *
     * @return A function which integrates provided elements, potentially using the provided
     *         state, optionally producing output to the provided downstream.
     */
    Integrator<A, T, R> integrator();

    /**
     * A function which accepts two intermediate states and combines them into one.
     *
     * @return A function which accepts two intermediate states and combines them into one.
     *
     * @implSpec The implementation in this interface returns {@link #defaultCombiner()}.
     */
    default BinaryOperator<A> combiner()
    {
        return defaultCombiner();
    }

    /**
     * Returns a combiner which is the default combiner of a {@code Gatherer}. The returned
     * combiner identifies that the owning {@code Gatherer} must only be evaluated sequentially.
     *
     * @return The instance of the default combiner.
     *
     * @param <A> The type of the state of the returned combiner.
     *
     * @implSpec This method always returns the same instance.
     *
     * @see Gatherer#combiner()
     */
    static <A> BinaryOperator<A> defaultCombiner()
    {
        return GathererValue.DEFAULT.combiner();
    }

    /**
     * A function which accepts the final intermediate state and a {@link Downstream} object,
     * allowing to perform a final action at the end of input elements.
     *
     * @return a function which transforms the intermediate result to the final result(s)
     *         which are then passed on to the provided downstream.
     *
     * @implSpec The implementation in this interface returns {@link #defaultFinisher()}.
     */
    default BiConsumer<A, Downstream<? super R>> finisher()
    {
        return defaultFinisher();
    }

    /**
     * Returns a {@code finisher} which is the default finisher of a {@code Gatherer}. The
     * returned finisher identifies that the owning {@code Gatherer} performs no additional
     * actions at the end of input.
     *
     * @return The instance of the default finisher.
     *
     * @param <A> The type of the state of the returned finisher
     * @param <R> The type of the Downstream of the returned finisher
     *
     * @implSpec This method always returns the same instance.
     *
     * @see Gatherer#finisher()
     */
    static <A, R> BiConsumer<A, Downstream<? super R>> defaultFinisher()
    {
        return GathererValue.DEFAULT.finisher();
    }

    /**
     * Returns a composed {@code Gatherer} which connects the output of this {@code Gatherer}
     * to the input of that {@code Gatherer}.
     *
     * @param that The other gatherer.
     * @return     Returns a composed {@code Gatherer} which connects the output of this
     *             {@code Gatherer} as input that {@code Gatherer}.
     *
     * @param <RR> The type of output of that Gatherer
     *
     * @throws NullPointerException If the argument is {@code null}.
     *
     * @implSpec The implementation in this interface returns a new {@code Gatherer} which
     *           is semantically equivalent to the combination of this and that gatherer.
     */
    default <RR> Gatherer<T, ?, RR> andThen(Gatherer<? super R, ?, ? extends RR> that)
    {
        Objects.requireNonNull(that);
        return GathererComposite.of(this, that);
    }

    /**
     * Returns a new, sequential, and stateless {@code Gatherer} described by the given
     * {@code integrator}.
     *
     * @param integrator The integrator function for the new gatherer.
     * @return           The new {@code Gatherer}.
     *
     * @param <T> The type of input elements for the new gatherer.
     * @param <R> The type of results for the new gatherer.
     *
     * @throws NullPointerException If the argument is {@code null}.
     */
    static <T, R> Gatherer<T, Void, R> ofSequential(Integrator<Void, T, R> integrator)
    {
        return of(defaultInitializer(), integrator, defaultCombiner(), defaultFinisher());
    }

    /**
     * Returns a new, sequential, and stateless {@code Gatherer} described by the given
     * {@code integrator} and {@code finisher}.
     *
     * @param integrator The integrator function for the new gatherer.
     * @param finisher   The finisher function for the new gatherer.
     * @return           The new {@code Gatherer}.
     *
     * @param <T> The type of input elements for the new gatherer.
     * @param <R> The type of results for the new gatherer.
     *
     * @throws NullPointerException If any argument is {@code null}.
     */
    static <T, R> Gatherer<T, Void, R> ofSequential(Integrator<Void, T, R> integrator,
                                                    BiConsumer<Void, Downstream<? super R>> finisher)
    {
        return of(defaultInitializer(), integrator, defaultCombiner(), finisher);
    }

    /**
     * Returns a new, sequential, {@code Gatherer} described by the given {@code initializer}
     * and {@code integrator}.
     *
     * @param initializer The initializer function for the new gatherer.
     * @param integrator  The integrator function for the new gatherer.
     * @return            The new {@code Gatherer}.
     *
     * @param <T> The type of input elements for the new gatherer.
     * @param <A> The type of state for the new gatherer.
     * @param <R> The type of results for the new gatherer.
     *
     * @throws NullPointerException If any argument is {@code null}.
     */
    static <T, A, R> Gatherer<T, A, R> ofSequential(Supplier<A> initializer,
                                                    Integrator<A, T, R> integrator)
    {
        return of(initializer, integrator, defaultCombiner(), defaultFinisher());
    }

    /**
     * Returns a new, sequential, {@code Gatherer} described by the given {@code initializer},
     * {@code integrator}, and {@code finisher}.
     *
     * @param initializer The initializer function for the new gatherer.
     * @param integrator  The integrator function for the new gatherer.
     * @param finisher    The finisher function for the new gatherer.
     * @return            The new {@code Gatherer}.
     *
     * @param <T> The type of input elements for the new gatherer.
     * @param <A> The type of state for the new gatherer.
     * @param <R> The type of results for the new gatherer.
     *
     * @throws NullPointerException If any argument is {@code null}.
     */
    static <T, A, R> Gatherer<T, A, R> ofSequential(Supplier<A> initializer,
                                                    Integrator<A, T, R> integrator,
                                                    BiConsumer<A, Downstream<? super R>> finisher)
    {
        return of(initializer, integrator, defaultCombiner(), finisher);
    }

    /**
     * Returns a new, parallelizable, and stateless {@code Gatherer} described by the given
     * {@code integrator}.
     *
     * @param integrator The integrator function for the new gatherer.
     * @return           The new {@code Gatherer}.
     *
     * @param <T> The type of input elements for the new gatherer.
     * @param <R> The type of results for the new gatherer.
     *
     * @throws NullPointerException If any argument is {@code null}.
     */
    static <T, R> Gatherer<T, Void, R> of(Integrator<Void, T, R> integrator)
    {
        return of(defaultInitializer(), integrator, GathererValue.DEFAULT.statelessCombiner,
                defaultFinisher());
    }

    /**
     * Returns a new, parallelizable, and stateless {@code Gatherer} described by the given
     * {@code integrator} and {@code finisher}.
     *
     * @param integrator The integrator function for the new gatherer.
     * @param finisher   The finisher function for the new gatherer.
     * @return           The new {@code Gatherer}.
     *
     * @param <T> The type of input elements for the new gatherer.
     * @param <R> The type of results for the new gatherer.
     *
     * @throws NullPointerException If any argument is {@code null}.
     */
    static <T, R> Gatherer<T, Void, R> of(Integrator<Void, T, R> integrator,
                                          BiConsumer<Void, Downstream<? super R>> finisher)
    {
        return of(defaultInitializer(), integrator, GathererValue.DEFAULT.statelessCombiner,
                finisher);
    }

    /**
     * Returns a new, parallelizable, {@code Gatherer} described by the given
     * {@code initializer}, {@code integrator}, {@code combiner} and {@code finisher}.
     *
     * @param initializer The initializer function for the new gatherer.
     * @param integrator  The integrator function for the new gatherer.
     * @param combiner    The combiner function for the new gatherer.
     * @param finisher    The finisher function for the new gatherer.
     * @return            The new {@code Gatherer}.
     *
     * @param <T> The type of input elements for the new gatherer.
     * @param <A> The type of state for the new gatherer.
     * @param <R> The type of results for the new gatherer.
     *
     * @throws NullPointerException If any argument is {@code null}.
     */
    static <T, A, R> Gatherer<T, A, R> of(Supplier<A> initializer,
                                          Integrator<A, T, R> integrator,
                                          BinaryOperator<A> combiner,
                                          BiConsumer<A, Downstream<? super R>> finisher)
    {
        return new GathererImpl<>(Objects.requireNonNull(initializer),
                Objects.requireNonNull(integrator), Objects.requireNonNull(combiner),
                Objects.requireNonNull(finisher));
    }

    /**
     * A downstream object is the next stage in a pipeline of operations, to which elements
     * can be sent.
     *
     * @param <T> The type of elements this downstream accepts.
     */
    @FunctionalInterface
    interface Downstream<T>
    {
        /**
         * Pushes, if possible, the provided element downstream -- to the next stage in the
         * pipeline.
         *
         * @param element The element to push downstream
         * @return        Returns {@code true} if more elements can be sent, and {@code false}
         *                if not.
         *
         * @implSpec If this method returns {@code false} then no further elements will be
         *           accepted and subsequent invocations of this method will return {@code false}.
         */
        boolean push(T element);

        /**
         * Checks whether the next stage is known to not want any more elements sent to it.
         *
         * @return Returns {@code true} if this downstream is known not to want anymore
         *         elements sent to it, {@code false} if otherwise.
         *
         * @apiNote This is best-effort only, once this returns {@code true} it should
         *          never return {@code false} again for the same instance.
         *
         * @implSpec The implementation in this interface returns {@code false}.
         */
        default boolean isRejecting()
        {
            return false;
        }

    }

    /**
     * An integrator receives elements and processes them, optionally using the supplied
     * state, and optionally sends incremental results downstream.
     *
     * @param <A> The type of state used by this integrator.
     * @param <T> The type of elements this integrator consumes.
     * @param <R> The type of results this integrator can produce.
     */
    @FunctionalInterface
    interface Integrator<A, T, R>
    {
        /**
         * Performs an action given: the current state, the next element, and a downstream
         * object; potentially inspecting and/or updating the state, optionally sending any
         * number of elements downstream -- and then returns whether more elements are to
         * be consumed or not.
         *
         * @param state      The state to integrate into.
         * @param element    The element to integrate.
         * @param downstream The downstream object of this integration.
         * @return           Returns {@code true} if subsequent integration is desired,
         *                   {@code false} if not
         */
        boolean integrate(A state, T element, Downstream<? super R> downstream);

        /**
         * Factory method for turning integrator-shaped lambdas into integrators.
         *
         * @param integrator A lambda to create as integrator.
         * @return           The given lambda as an integrator.
         *
         * @param <A> The type of state used by this integrator.
         * @param <T> The type of elements this integrator receives.
         * @param <R> The type of results this integrator can produce.
         */
        static <A, T, R> Integrator<A, T, R> of(Integrator<A, T, R> integrator)
        {
            return integrator;
        }

        /**
         * Factory method for turning Integrator-shaped lambdas into
         * {@link Greedy} Integrators.
         *
         * @param greedy a lambda to create as Integrator.Greedy
         * @return the given lambda as a Greedy Integrator
         * @param <A> the type of state used by this integrator
         * @param <T> the type of elements this integrator receives
         * @param <R> the type of results this integrator can produce
         */
        static <A, T, R> Greedy<A, T, R> ofGreedy(Greedy<A, T, R> greedy)
        {
            return greedy;
        }

        /**
         * Greedy Integrators consume all their input, and may only relay that
         * the downstream does not want more elements.
         *
         * @param <A> The type of state used by this integrator.
         * @param <T> The type of elements this greedy integrator receives.
         * @param <R> The type of results this greedy integrator can produce.
         * @implSpec This interface is used to communicate that no short-circuiting will
         *           be <i>initiated</i> by this Integrator, and that information can then
         *           be used to optimize evaluation.
         */
        @FunctionalInterface
        interface Greedy<A, T, R> extends Integrator<A, T, R> {}

    }

}
