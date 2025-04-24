package magicbook.gtlitecore.api.utils.stream;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * An addition spliterator for {@link Gatherer} to provide functions which the source stream
 * unsupported with {@link Gatherer} API contents. This is a pseudo spliterator to provide a
 * start point of stream with unsupported source contents.
 * <p>
 * This is a part of the port contents of Java 24 feature "Gatherer API" and we implement it
 * with a spliterator and make it be the spliterator in pipeline of {@code StreamSupport}.
 *
 * @param <T> The type of input elements for the new gatherer.
 * @param <A> The type of the state of the returned initializer.
 * @param <R> The type of results for the new gatherer.
 *
 * @author Magic_Sweepy
 */
public class GathererSpliterator<T, A, R> implements Spliterator<R>
{

    private final Supplier<A> initializer;
    private final Gatherer.Integrator<A, ? super T, R> integrator;
    private final BinaryOperator<A> combiner;
    private final BiConsumer<A, Gatherer.Downstream<? super R>> finisher;

    private final Spliterator<T> source;
    private final int characteristics;
    private final boolean parallel;

    private A state;
    private Queue<R> buffer = new ArrayDeque<>();
    private boolean finished;

    /* package */ GathererSpliterator(Supplier<A> initializer,
                                      Gatherer.Integrator<A, ? super T, R> integrator,
                                      BinaryOperator<A> combiner,
                                      BiConsumer<A, Gatherer.Downstream<? super R>> finisher,
                                      Spliterator<T> source,
                                      int characteristics,
                                      boolean parallel)
    {
        this.initializer = initializer;
        this.integrator = integrator;
        this.combiner = combiner;
        this.finisher = finisher;
        this.source = source;
        this.characteristics = characteristics;
        this.parallel = parallel;
        this.state = initializer.get();
    }

    @Override
    public boolean tryAdvance(Consumer<? super R> action)
    {
        if (buffer != null && !buffer.isEmpty())
        {
            action.accept(buffer.poll());
            return true;
        }

        if (finished) return false;

        DownstreamImpl downstream = new DownstreamImpl();
        while (!finished)
        {
            boolean hasMore = source.tryAdvance(t -> {
                if (!integrator.integrate(state, t, downstream))
                    finished = true;
            });

            if (!hasMore)
            {
                finisher.accept(state, downstream);
                finished = true;
            }

            if (!downstream.buffer.isEmpty())
            {
                buffer = downstream.buffer;
                action.accept(buffer.poll());
                return true;
            }

            if (finished) break;
        }
        return false;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Spliterator<R> trySplit()
    {
        if (!parallel) return null;

        Spliterator<T> prefix = source.trySplit();
        if (prefix == null) return null;

        A splitState = combiner.apply(state, initializer.get());
        return new GathererSpliterator(initializer, integrator, combiner, finisher,
                prefix, characteristics, parallel) // TODO should we contained final?
        {
            @Override
            public boolean tryAdvance(Consumer action)
            {
                state = combiner.apply(state, splitState);
                return super.tryAdvance(action);
            }
        };
    }

    @Override
    public long estimateSize()
    {
        return source.estimateSize();
    }

    @Override
    public int characteristics()
    {
        return characteristics;
    }

    /* package */ class DownstreamImpl implements Gatherer.Downstream<R>
    {

        Queue<R> buffer = new ArrayDeque<>();

        @Override
        public boolean push(R element)
        {
            if (!isRejecting())
            {
                buffer.add(element);
                return true;
            }
            return false;
        }

        @Override
        public boolean isRejecting()
        {
            return finished;
        }

    }

}
