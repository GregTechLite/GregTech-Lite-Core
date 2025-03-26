package magicbook.gtlitecore.api.utils.stream;

import gregtech.api.GregTechAPI;
import gregtech.api.block.IHeatingCoilBlockStats;
import it.unimi.dsi.fastutil.objects.AbstractObject2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import one.util.streamex.StreamEx;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A lazy and short-circuit stream related utilities class, this class is based on common
 * stream and {@link StreamEx}, provided some methods to reduce same code usage.
 * <p>
 * This class also consists of some functional interface which used for stream contexts,
 * and its implementation (it is completed at this class yet).
 * <ul>
 *     <li>{@code Gatherer<T, R>}: A fast intermediate operator for stream, can gather
 *         input elements and do consume to a downstream.</li>
 * </ul>
 *
 * @author Magic_Sweepy
 */
public final class LazyStreams
{
    /**
     * Generated a sorted list for a given {@code object2ObjectMap.entrySet()}.
     * <p>
     * Will sorted all elements in entry set by {@link StreamEx#sortedByInt(ToIntFunction)},
     * this method is a lazy importing of {@link WrappedIntTier}.
     *
     * @throws ClassCastException When the correspondenced types of {@code MetaBlock}
     *         at {@code <K>} is not the implementation of {@code WrappedIntTier}.
     *
     * @param object2ObjectMap Any (object, object) map, will use its entry set.
     * @return                 The sorted key list.
     *
     * @param <K> The type of key in {@code object2ObjectMap}.
     * @param <V> The type of value in {@code object2ObjectMap}.
     */
    public static <K, V> List<K> fastSortedByKey(AbstractObject2ObjectMap<K, V> object2ObjectMap)
    {
        return StreamEx.of(object2ObjectMap.entrySet())
                .sortedBy(entry -> ((WrappedIntTier) entry.getValue()).getIntTier())
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * Generated a sorted list for a given {@code object2ObjectMap.entrySet()}.
     * <p>
     * Will sorted all elements in entry set by {@link StreamEx#sortedByInt(ToIntFunction)},
     * this method is a lazy importing of {@link IHeatingCoilBlockStats}.
     * <p>
     * This method is a special version for all tiered stats stored at {@link GregTechAPI},
     * because these stats are initialize with {@link Object2ObjectMap}, but not the
     * default choice {@link Object2ObjectOpenHashMap} of {@link GTLiteAPI}.
     *
     * @throws ClassCastException When the correspondenced types of {@code MetaBlock}
     *         at {@code <K>} is not the implementation of {@code IHeatingCoilBlockStats}.
     *
     * @param object2ObjectMap Any (object, object) map, will use its entry set.
     * @return                 The sorted key list.
     */
    public static <K, V> List<K> fastSortedByKey(Object2ObjectMap<K, V> object2ObjectMap)
    {
        return StreamEx.of(object2ObjectMap.entrySet())
                .sortedBy(entry -> ((IHeatingCoilBlockStats) entry.getValue()).getTier())
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * @param source   The upstream stream which consists of input elements.
     * @param gatherer The gatherer.
     * @return         Transformed stream which consists of output elements of gatherer.
     *
     * @param <T> The type of input elements.
     * @param <R> The type of output elements.
     */
    public <T, R> Stream<R> gather(Stream<T> source, Gatherer<T, R> gatherer)
    {
        return StreamSupport.stream(new GathererSpliterator<>(source.spliterator(), gatherer),
                source.isParallel()).onClose(source::close);
    }

    /**
     * <pre>{@code
     *     LazyStreams.gather(Stream.of(1, 2, 3, 4, 5),  windowSliding(3))
     *                .forEach(System.out::println)
     *     // Will output:
     *     // [1, 2, 3]
     *     // [2, 3, 4]
     *     // [3, 4, 5]
     * }</pre>
     */
    public static <T> Gatherer<T, List<T>> windowSliding(int windowSize)
    {
        return new Gatherer<T, List<T>>()
        {
            private final Queue<T> buffer = new ArrayDeque<>();

            @Override
            public boolean gather(T element, Consumer<List<T>> downstream)
            {
                buffer.add(element);
                if (buffer.size() == windowSize)
                {
                    downstream.accept(new ArrayList<>(buffer));
                    buffer.poll();
                    return true;
                }
                return false;
            }
        };
    }

    /**
     * A stateful intermediate transformation for input elements in a stream that can emit zero to more
     * transformed elements to downstream consumers.
     * <p>
     * This is a simplified version of Java 24 feature "Gatherer".
     *
     * @param <T> The type of input elements.
     * @param <R> The type of output elements.
     */
    public interface Gatherer<T, R>
    {
        /**
         * Gathers input elements and optionally emits transformed elements to downstream consumer.
         *
         * @param element    The input elements to gathered.
         * @param downstream The consumer for emitting result elements.
         * @return           Returns {@code true} to continue processing, {@code false} to stop it.
         */
        boolean gather(T element, Consumer<R> downstream);

        /**
         * Callback invoked before element processing begins, used for initializing a transformation
         * state.
         */
        default void begin() {}

        /**
         * Callback invoked after all elements have been processed, used for finalizing transformation
         * state and emitting any remaining elements.
         *
         * @param downstream The consumer for emitting final result elements.
         */
        default void finish(Consumer<R> downstream) {}
    }

    /**
     * @implSpec  This class is only for common stream, do not used it and related contents like
     *            {@link #gatherer} method in a parallel stream.
     *
     * @param <T> The type of input elements.
     * @param <R> The type of output elements.
     */
    protected static class GathererSpliterator<T, R> extends Spliterators.AbstractSpliterator<R>
    {

        private final Spliterator<T> source;
        private final Gatherer<T, R> gatherer;
        private boolean finished = false;

        public GathererSpliterator(Spliterator<T> source,
                                   Gatherer<T, R> gatherer)
        {
            super(Long.MAX_VALUE, source.characteristics());
            this.source = source;
            this.gatherer = gatherer;
            this.gatherer.begin();
        }

        @Override
        public boolean tryAdvance(Consumer<? super R> action)
        {
            boolean[] elementProcessed = { false };
            while (!finished)
            {
                boolean hasElement = source.tryAdvance(element -> {
                    if (gatherer.gather(element, action::accept))
                        elementProcessed[0] = true;
                });

                if (!hasElement)
                {
                    finished = true;
                    gatherer.finish(action::accept);
                }

                if (elementProcessed[0])
                    return true;
                if (!hasElement)
                    break;
            }
            return false;
        }

    }

}
