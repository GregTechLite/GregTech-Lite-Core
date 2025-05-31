package magicbook.gtlitecore.api.utils.stream;

import gregtech.api.GregTechAPI;
import gregtech.api.block.IHeatingCoilBlockStats;
import it.unimi.dsi.fastutil.objects.AbstractObject2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import kotlin.Pair;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import one.util.streamex.StreamEx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A lazy and short-circuit stream related utilities class, this class is based on common
 * stream and {@link StreamEx}, provided some methods to reduce same code usage.
 *
 * @author Magic_Sweepy
 */
public final class LazyStreams
{
    /**
     * Generated a sorted list for a given {@code map}.
     * <p>
     * The action of values is from {@code tierExtractor}, it is used to send value to
     * the sorter. As default, tier value used integer type.
     *
     * @param map           A common map.
     * @param tierExtractor A to-integer function to get the value which will be sent
     *                      to sorter of the list.
     * @return              Sorted new list of all key values in {@code map} with the
     *                      action which contained in sorter by value values in entry
     *                      map of {@code map}.
     *
     * @param <K> The type of key of {@code map}.
     * @param <V> The type of value of {@code map}.
     *
     * @author Magic_Sweepy
     */
    public static <K, V> List<K> fastSortedByKey(Map<K, V> map,
                                                 ToIntFunction<? super V> tierExtractor)
    {
        return StreamEx.of(map.entrySet())
                .sortedBy(entry -> tierExtractor.applyAsInt(entry.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * Generated a sorted list for a given {@code map}.
     *
     * @param map A <it>it.unimi.dsi.fastutil.objects</it>.{@link Object2ObjectMap},
     *            for example, we used {@link Object2ObjectOpenHashMap} as the default
     *            choice of storage usage in {@link GTLiteAPI}.
     * @return    Sorted new list of all key values in {@code map} with the
     *            action which contained in sorter by value values in entry
     *            map of {@code map}.
     *
     * @param <K> The type of key of {@code map}.
     * @param <V> The type of value of {@code map}.
     *
     * @throws ClassCastException When the correspondenced types of {@code MetaBlock}
     *         at {@code <K>} is not the implementation of {@link WrappedIntTier}.
     *
     * @see it.unimi.dsi.fastutil.objects.AbstractObject2ObjectMap
     *
     * @author Magic_Sweepy
     */
    public static <K, V> List<K> fastSortedByKey(AbstractObject2ObjectMap<K, V> map)
    {
        return fastSortedByKey(map, v -> ((WrappedIntTier) v).getIntTier());
    }

    /**
     * Generated a sorted list for a given {@code map}.
     *
     * @param map A <it>it.unimi.dsi.fastutil.objects</it>.{@link Object2ObjectMap},
     *            for example, the gregtech mod used {@link Object2ObjectMap} as the
     *            default choice of storage usage in {@link GregTechAPI}.
     * @return    Sorted new list of all key values in {@code map} with the
     *            action which contained in sorter by value values in entry
     *            map of {@code map}.
     *
     * @param <K> The type of key of {@code map}.
     * @param <V> The type of value of {@code map}.
     *
     * @throws ClassCastException When the correspondenced types of {@code MetaBlock}
     *         at {@code <K>} is not the implementation of {@code IHeatingCoilBlockStats}.
     *
     * @see it.unimi.dsi.fastutil.objects.Object2ObjectMap
     *
     * @author Magic_Sweepy
     */
    public static <K, V> List<K> fastSortedByKey(Object2ObjectMap<K, V> map)
    {
        return fastSortedByKey(map, v -> ((IHeatingCoilBlockStats) v).getTier());
    }

    /**
     * Returns a stream consisting of the results of applying the given {@link Gatherer}
     * of the upstream {@code source} to the elements of this stream.
     * <p>
     * This is a stateful intermediate operation that is an extension point.
     * <p>
     * Gatherers are highly flexible and can describe a vast array of possibly stateful
     * operations, with support for short-circuiting, and parallelization.
     * <p>
     * When executed in parallel, multiple intermediate results may be instantiated,
     * populated, and merged to maintain isolation of mutable data structures. Therefore,
     * even when executed in parallel with non-thread-safe data structures (such as
     * a {@code ArrayList}), no additional synchronization is needed for a parallel
     * reduction.
     *
     * @param source   Upstream for gatherer pipeline.
     * @param gatherer A gatherer.
     * @return         The new stream.
     *
     * @param <T> The type of input elements for the gatherer.
     * @param <A> The type of the state of the returned initializer.
     * @param <R> The type of results for the gatherer.
     */
    public static <T, A, R> Stream<R> gatherer(Stream<T> source,
                                               Gatherer<? super T, A, R> gatherer)
    {
        Objects.requireNonNull(source);
        Objects.requireNonNull(gatherer);

        Supplier<A> initializer = gatherer.initializer();
        Gatherer.Integrator<A, ? super T, R> integrator = gatherer.integrator();
        BinaryOperator<A> combiner = gatherer.combiner();
        BiConsumer<A, Gatherer.Downstream<? super R>> finisher = gatherer.finisher();

        Spliterator<T> srcSpliterator = source.spliterator();
        int characteristics = srcSpliterator.characteristics() &
                (Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED);

        return StreamSupport.stream(new GathererSpliterator<>(initializer, integrator,
                combiner, finisher, srcSpliterator, characteristics, source.isParallel()),
                source.isParallel()).onClose(source::close);
    }

    /**
     * Zip elements with sorted indexes of an upstream.
     *
     * @param stream The Upstream.
     * @return       New stream which elements is the zip-ed elements.
     *
     * @param <T>    The type of upstream.
     */
    public static <T> Stream<Pair<T, Integer>> zipWithIndex(final Stream<T> stream)
    {
        return gatherer(stream, Gatherer.ofSequential(() -> new Object() { int index; },
                (state, element, downstream) -> downstream.push(new Pair<>(element, state.index++))));
    }

    /**
     * Zip elements with the next index of an upstream.
     *
     * @param stream The Upstream.
     * @return       New stream which elements is the zip-ed elements.
     *
     * @param <T>    The type of upstream.
     */
    public static <T> Stream<Pair<T, T>> zipWithNext(final Stream<T> stream)
    {
        return gatherer(stream, Gatherer.ofSequential(() -> new Object() { T prev; boolean first = true; },
                (state, element, downstream) -> {
                    if (!state.first)
                        downstream.push(new Pair<>(state.prev, element));
                    state.prev = element;
                    state.first = false;
                    return true;
                }));
    }

    /**
     *
     * @param stream
     * @param mapper
     * @return
     *
     * @param <T>
     * @param <R>
     */
    public static <T, R> Stream<R> mapConcat(Stream<T> stream, Function<T, ? extends Collection<R>> mapper)
    {
        return gatherer(stream, Gatherer.ofSequential(() -> new Object() { Iterator<R> currentIterator; },
                (state, element, downstream) -> {
                    Collection<R> collection = mapper.apply(element);
                    if (collection != null)
                    {
                        state.currentIterator = collection.iterator();
                        while (state.currentIterator.hasNext())
                            downstream.push(state.currentIterator.next());
                    }
                    return true;
                }));
    }

    /**
     *
     * @param stream
     * @param intervalMillis
     * @return
     *
     * @param <T>
     */
    public static <T> Stream<T> throttle(Stream<T> stream, long intervalMillis)
    {
        return gatherer(stream, Gatherer.ofSequential(() -> new Object() { long lastTime = Long.MIN_VALUE; },
                (state, element, downstream) -> {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - state.lastTime >= intervalMillis)
                    {
                        state.lastTime = currentTime;
                        downstream.push(element);
                    }
                    return true;
                }));
    }

    /**
     *
     * @param stream
     * @param batchSize
     * @return
     *
     * @param <T>
     */
    public static <T> Stream<List<T>> batch(Stream<T> stream, int batchSize)
    {
        return gatherer(stream, Gatherer.ofSequential(
                () -> new ArrayList<T>(batchSize),
                (state, element, downstream) -> {
                    state.add(element);
                    if (state.size() >= batchSize)
                    {
                        downstream.push(new ArrayList<>(state));
                        state.clear();
                    }
                    return true;
                },
                (state, downstream) -> {
                    if (!state.isEmpty())
                    {
                        downstream.push(new ArrayList<>(state));
                    }
                }
        ));
    }

}
