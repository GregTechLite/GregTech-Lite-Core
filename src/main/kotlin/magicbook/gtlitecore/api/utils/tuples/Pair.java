package magicbook.gtlitecore.api.utils.tuples;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import magicbook.gtlitecore.api.utils.functions.BiFunctor;
import magicbook.gtlitecore.api.utils.functions.TriConsumer;
import magicbook.gtlitecore.api.utils.functions.TriFunction;
import magicbook.gtlitecore.api.utils.functions.TriPredicate;
import one.util.streamex.IntStreamEx;
import one.util.streamex.MoreCollectors;
import one.util.streamex.StreamEx;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A functional enhancement of {@link org.apache.commons.lang3.tuple.Pair}.
 * <p>
 * This class is a "pair" structure like {@link Map.Entry}, it is suitable for
 * serialization and functional programming operations. For example:
 * <pre>{@code
 *     // Bob is a 24 year old woman.
 *     Pair<String, Integer> bobAge = Pair.of("Bob", 12)
 *             .mapKey(String::toLowerCase) // "Bob" -> "bob".
 *             .mapValue(v -> v * 2); // 12 -> 24.
 *     // List of all (key, value) => (name, age) ages.
 *     List<Pair<String, Integer>> ages = Arrays.asList(bobAge);
 *     // Another convenient operation is transform a pair list to map.
 *     Map<String, Integer> map = Pair.toMap(ages);
 * }</pre>
 * This structure is an implementation of <tt>2-Functor</tt> ({@link BiFunctor}).
 *
 * @param <K> The type of key.
 * @param <V> The type of value.
 *
 * @author Magic_Sweepy
 */
@Getter
@ToString
@EqualsAndHashCode
public final class Pair<K, V> implements BiFunctor<K, V, Pair<?, ?>>, Serializable
{

    private final K key;
    private final V value;

    public Pair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    // ---------------------------- Builder Methods ----------------------------

    // Builder: k, v -> (k, v).
    public static <K, V> Pair<K, V> of(K k, V v)
    {
        return new Pair<>(k, v);
    }

    // Builder: k, v -> (k, v); both not-null.
    public static <K, V> Pair<K, V> ofNotNull(K k, V v)
    {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        return new Pair<>(k, v);
    }

    // Builder: k, v -> (v, k).
    public static <K, V> Pair<V, K> ofReverse(K k, V v)
    {
        return new Pair<>(v, k);
    }

    // --------------------------- Functional Mappers --------------------------
    // (k, v) => (f(k), f(v))
    @Override
    public <R1, R2> Pair<R1, R2> map(Function<K, R1> keyMapper,
                                     Function<V, R2> valueMapper)
    {
        return Pair.of(keyMapper.apply(key), valueMapper.apply(value));
    }

    // (k, i) => (f(k), i); f(i) = i
    public <R> Pair<R, V> mapKey(Function<K, R> keyMapper)
    {
        return this.map(keyMapper, Function.identity());
    }

    // (i, v) => (i, f(v)); f(i) = i
    public <R> Pair<K, R> mapValue(Function<V, R> valueMapper)
    {
        return this.map(Function.identity(), valueMapper);
    }

    // -------------------------- Interaction Utility --------------------------
    // Multi version of mapValue(Function); (k, v1) -> (k, v2).
    public static <K, V1, V2> List<Pair<K, V2>> mapValues(List<Pair<K, V1>> pairs,
                                                          Function<V1, V2> mapper)
    {
        return StreamEx.of(pairs)
                .map(p -> p.mapValue(mapper))
                .toList();
    }

    // Key provided mapValues(List, Function); (k, v1) => (k, v2).
    public static <K, V1, V2> List<Pair<K, V2>> mapValuesWithKey(List<Pair<K, V1>> pairs,
                                                                 BiFunction<K, V1, V2> mapper)
    {
        return StreamEx.of(pairs)
                .map(p -> p.mapValue(v -> mapper.apply(p.getKey(), v)))
                .toList();
    }

    // Filter with (k, v).
    public static <K, V> List<Pair<K, V>> filter(List<Pair<K, V>> pairs,
                                                 BiPredicate<K, V> predicate)
    {
        return StreamEx.of(pairs)
                .filter(p -> predicate.test(p.getKey(), p.getValue()))
                .toList();
    }

    // Indexed (index: int) filter with (k, v).
    public static <K, V> List<Pair<K, V>> filterIndexed(List<Pair<K, V>> pairs,
                                                        TriPredicate<K, V, Integer> predicate)
    {
        return IntStreamEx.range(0, pairs.size())
                .filter(i -> predicate.test(pairs.get(i).getKey(),
                        pairs.get(i).getValue(), i))
                .mapToObj(pairs::get)
                .toList();
    }

    // Filter (k, v) by k.
    public static <K, V> List<Pair<K, V>> filterByKey(List<Pair<K, V>> pairs,
                                                      Predicate<K> keyPredicate)
    {
        return StreamEx.of(pairs)
                .filter(p -> keyPredicate.test(p.getKey()))
                .toList();
    }

    // Filter (k, v) by v.
    public static <K, V> List<Pair<K, V>> filterByValue(List<Pair<K, V>> pairs,
                                                        Predicate<V> valuePredicate)
    {
        return StreamEx.of(pairs)
                .filter(p -> valuePredicate.test(p.getValue()))
                .toList();
    }

    // Pair list to key-value map.
    public static <K, V> Map<K, V> toMap(List<Pair<K, V>> pairs)
    {
        return StreamEx.of(pairs)
                .toMap(Pair::getKey, Pair::getValue);
    }

    // Indexed (index: int) toMap() method.
    public static <K, V, R, S> Map<R, S> toMapIndexed(List<Pair<K, V>> pairs,
                                                      TriFunction<K, V, Integer, R> keyMapper,
                                                      TriFunction<K, V, Integer, S> valueMapper)
    {
        return IntStreamEx.range(0, pairs.size())
                .boxed()
                .toMap(i -> keyMapper.apply(pairs.get(i).getKey(),
                        pairs.get(i).getValue(), i), i -> valueMapper.apply(
                        pairs.get(i).getKey(), pairs.get(i).getValue(), i));
    }

    // (k, v) => (k).
    public static <K, V> List<K> keys(List<Pair<K, V>> pairs)
    {
        return StreamEx.of(pairs)
                .map(Pair::getKey)
                .toList();
    }

    // (k, v) => (k)Set.
    public static <K, V> Set<K> keySet(List<Pair<K, V>> pairs)
    {
        return StreamEx.of(pairs)
                .map(Pair::getKey)
                .toSet();
    }

    // (k, v) => (v).
    public static <K, V> List<V> values(List<Pair<K, V>> pairs)
    {
        return StreamEx.of(pairs)
                .map(Pair::getValue)
                .toList();
    }

    // (k, v) => (v)Set.
    public static <K, V> Set<V> valueSet(List<Pair<K, V>> pairs)
    {
        return StreamEx.of(pairs)
                .map(Pair::getValue)
                .toSet();
    }

    // Tree (A, (A, B)) -fication
    public static <K, V, R> Map<R, Map<R, V>> toTree(List<Pair<K, V>> pairs,
                                                     TriFunction<K, V, Integer, R> keyMapper)
    {
        return IntStreamEx.range(0, pairs.size())
                .boxed()
                .groupingBy(i -> keyMapper.apply(pairs.get(i).getKey(),
                                pairs.get(i).getValue(), i),
                        Collectors.toMap(i -> keyMapper.apply(pairs.get(i).getKey(),
                                        pairs.get(i).getValue(), i),
                                i -> pairs.get(i).getValue()));
    }

    // (k1, v1) + (k2, v2) => ({k1, k2}, {v1, v2}).
    public static <K, V> List<Pair<K, V>> merge(List<Pair<K, V>> pairs1,
                                                List<Pair<K, V>> pairs2,
                                                BinaryOperator<V> mergeFunction)
    {
        // Original kvSet (k1, v1).
        Map<K, V> pairs = StreamEx.of(pairs1)
                .toMap(Pair::getKey, Pair::getValue);
        // Merged (k2, v2) to (k1, v1) with mergeFunction.
        StreamEx.of(pairs2)
                .forEach(p -> pairs.merge(p.getKey(), p.getValue(), mergeFunction));
        // Final mapping of original kvSet, i.e. (k1, v1) => ({k1, k2}, {v1, v2}).
        return StreamEx.of(pairs.entrySet())
                .map(e -> Pair.of(e.getKey(), e.getValue()))
                .toList();
    }

    // Combine kSet (k1, k2,..., kn), vSet (v1, v2,..., vn) => ({k1, k2,..., kn}, {v1, v2,..., vn}).
    public static <K, V> List<Pair<K, V>> combine(List<K> keys,
                                                  List<V> values)
    {
        return Optional.of(keys)
                .filter(k -> k.size() == values.size()) // |kSet| = |vSet|.
                .map(k -> IntStreamEx.range(0, k.size())
                        .mapToObj(i -> Pair.of(k.get(i), values.get(i)))
                        .toList())
                .orElseThrow(() -> new IllegalArgumentException("Two pairs which will be combined"
                        + "must be same size!")); // just a sanity check... ^^
    }

    // Split (k, v) -> ({k1, k2,..., kn}, {v1, v2,..., vn}),
    // where k: {k1, k2,..., kn}, v: {v1, v2,...,vn}.
    public static <K, V> Pair<List<K>, List<V>> split(List<Pair<K, V>> pairs)
    {
        return Pair.of(keys(pairs), values(pairs));
    }

    // Grouping (k, v) by group classifier.
    public static <K, V, G> Map<G, List<Pair<K, V>>> groupingBy(List<Pair<K, V>> pairs,
                                                                BiFunction<K, V, G> classifier)
    {
        return StreamEx.of(pairs)
                .groupingBy(p -> classifier.apply(p.getKey(), p.getValue()));
    }

    // Indexed (index: int) groupingBy() method.
    public static <K, V, G> Map<G, List<Pair<K, V>>> groupingBy(List<Pair<K, V>> pairs,
                                                                TriFunction<K, V, Integer, G> classifier)
    {
        return IntStreamEx.range(0, pairs.size())
                .boxed()
                .groupingBy(i -> classifier.apply(pairs.get(i).getKey(),
                        pairs.get(i).getValue(), i), MoreCollectors.mapping(pairs::get,
                        Collectors.toList()));
    }

    // Indexed (index: int) groupingBy() + (action).
    public static <K, V, G> void groupingByAndThen(List<Pair<K, V>> pairs,
                                                   TriFunction<K, V, Integer, G> classifier,
                                                   TriConsumer<G, List<Pair<K, V>>, Integer> consumer)
    {
        IntStreamEx.range(0, pairs.size())
                .boxed()
                .groupingBy(i -> classifier.apply(pairs.get(i).getKey(),
                                pairs.get(i).getValue(), i),
                        MoreCollectors.mapping(pairs::get, Collectors.toList()))
                .forEach((g, gp) -> consumer.accept(g, gp, gp.size())); // g: G, gp: Grouped Pairs.
    }

    // Indexed (index: int) groupingBy() + (condition + action).
    public static <K, V> void groupingByAndThen(List<Pair<K, V>> pairs,
                                                TriPredicate<K, V, Integer> predicate,
                                                TriConsumer<Boolean, List<Pair<K, V>>, Integer> consumer)
    {
        IntStreamEx.range(0, pairs.size())
                .boxed()
                .partitioningBy(i -> predicate.test(pairs.get(i).getKey(),
                                pairs.get(i).getValue(), i),
                        MoreCollectors.mapping(pairs::get, Collectors.toList()))
                .forEach((c, gp) -> consumer.accept(c, gp, gp.size())); // c: Condition, gp: Grouped Pairs.
    }

    // Sorted (k, v) by k.
    public static <K extends Comparable<? super K>, V> List<Pair<K, V>> sortByKey(List<Pair<K, V>> pairs)
    {
        return StreamEx.of(pairs)
                .sorted(Comparator.comparing(Pair::getKey))
                .toList();
    }

    // Sorted (k, v) by v.
    public static <K, V extends Comparable<? super V>> List<Pair<K, V>> sortByValue(List<Pair<K, V>> pairs)
    {
        return StreamEx.of(pairs)
                .sorted(Comparator.comparing(Pair::getValue))
                .toList();
    }

    // Distinct (k, v) by k.
    public static <K, V> List<Pair<K, V>> distinctByKey(List<Pair<K, V>> pairs)
    {
        return StreamEx.of(pairs)
                .distinct(Pair::getKey)
                .toList();
    }

    // Distinct (k, v) by v.
    public static <K, V> List<Pair<K, V>> distinctByValue(List<Pair<K, V>> pairs)
    {
        return StreamEx.of(pairs)
                .distinct(Pair::getValue)
                .toList();
    }

    // Count of pairs.
    public static <K, V> long count(List<Pair<K, V>> pairs,
                                    TriPredicate<K, V, Integer> predicate)
    {
        return IntStreamEx.range(0, pairs.size())
                .filter(i -> predicate.test(pairs.get(i).getKey(), pairs.get(i).getValue(), i))
                .count();
    }

    // Count (k, v) by k.
    public static <K, V> Map<K, Long> countByKey(List<Pair<K, V>> pairs)
    {
        return StreamEx.of(pairs)
                .groupingBy(Pair::getKey, Collectors.counting());
    }

    // Count (k, v) by v.
    public static <K, V> Map<V, Long> countByValue(List<Pair<K, V>> pairs)
    {
        return StreamEx.of(pairs)
                .groupingBy(Pair::getValue, Collectors.counting());
    }

    // Count (k, v) + (predicate + action)
    public static <K, V, R> R countAndThen(List<Pair<K, V>> pairs,
                                           TriPredicate<K, V, Integer> predicate,
                                           TriFunction<Integer, List<Pair<K, V>>, Integer, R> mapper)
    {
        List<Pair<K, V>> filteredPairs = IntStreamEx.range(0, pairs.size())
                .filter(i -> predicate.test(pairs.get(i).getKey(), pairs.get(i).getValue(), i))
                .mapToObj(pairs::get)
                .toList();
        return mapper.apply(filteredPairs.size(), filteredPairs, pairs.size());
    }
    // flatMap ((k, v)) => (k, v).
    public static <K, V> List<Pair<K, V>> flatten(List<List<Pair<K, V>>> nestedPairs)
    {
        return StreamEx.of(nestedPairs)
                .flatMap(StreamEx::of)
                .toList();
    }

    // Try to find (k, v) in optional[(k, v)].
    public static <K, V> Optional<Pair<K, V>> find(List<Pair<K, V>> pairs,
                                                   TriPredicate<K, V, Integer> predicate)
    {
        return IntStreamEx.range(0, pairs.size())
                .filter(i -> predicate.test(pairs.get(i).getKey(), pairs.get(i).getValue(), i))
                .mapToObj(pairs::get)
                .findFirst();
    }

    // Try to find (k, v) in optional[(k, v)] with fixed k.
    public static <K, V> Optional<Pair<K, V>> findByKey(List<Pair<K, V>> pairs,
                                                        K key)
    {
        return StreamEx.of(pairs)
                .filter(p -> Objects.equal(p.getKey(), key))
                .findFirst();
    }

    // Try to find (k, v) in optional[(k, v)] with fixed v.
    public static <K, V> Optional<Pair<K, V>> findByValue(List<Pair<K, V>> pairs,
                                                          V value)
    {
        return StreamEx.of(pairs)
                .filter(p -> Objects.equal(p.getValue(), value))
                .findFirst();
    }

    // Indexed (index: int) for-each loop.
    public static <K, V> void forEach(List<Pair<K, V>> pairs,
                                      TriConsumer<K, V, Integer> consumer)
    {
        IntStreamEx.range(0, pairs.size())
                .forEach(i -> consumer.accept(pairs.get(i).getKey(), pairs.get(i).getValue(), i));
    }

    // Parallel Indexed (index: int) for-each loop.
    public static <K, V> void forEachParallel(List<Pair<K, V>> pairs,
                                              TriConsumer<K, V, Integer> consumer)
    {
        IntStreamEx.range(0, pairs.size())
                .parallel()
                .forEach(i -> consumer.accept(pairs.get(i).getKey(), pairs.get(i).getValue(), i));
    }

    // Indexed (index: int) for-each loop + (condition + consumer).
    public static <K, V> void forEachIf(List<Pair<K, V>> pairs,
                                        TriPredicate<K, V, Integer> predicate,
                                        TriConsumer<K, V, Integer> consumer)
    {
        IntStreamEx.range(0, pairs.size())
                .filter(i -> predicate.test(pairs.get(i).getKey(), pairs.get(i).getValue(), i))
                .forEach(i -> consumer.accept(pairs.get(i).getKey(), pairs.get(i).getValue(), i));
    }

    // Factorial style generator for (k, v) list.
    public static <K, V> List<Pair<K, V>> generate(Class<K> keyClazz,
                                                   Class<V> valueClazz,
                                                   Object... elements)
    {
        return Optional.ofNullable(elements)
                .filter(e -> e.length % 2 == 0)
                .map(e -> IntStreamEx.range(0, e.length / 2)
                        .mapToObj(i -> Pair.of(e[2 * i], e[2 * i + 1]))
                        .filter(p -> keyClazz.isInstance(p.getKey())
                                && valueClazz.isInstance(p.getValue()))
                        .map(p -> Pair.of(keyClazz.cast(p.getKey()), valueClazz.cast(p.getValue())))
                        .toList())
                .orElseThrow(() -> new IllegalArgumentException("The number of elements must be"
                        + " even an non-null!"));
    }

    // (k, v) => (v, k)
    public static <K, V> List<Pair<V, K>> reverse(List<Pair<K, V>> pairs)
    {
        return StreamEx.of(pairs)
                .map(p -> Pair.of(p.getValue(), p.getKey()))
                .toList();
    }

    // ---------------------- File IO Interaction Methods ----------------------
    // ((k1, v1), (k2, v2),..., (kn, vn)) => "k1 -> v1\nk2 -> v2\n...\nkn -> vn".
    public static <K, V> void writeToFile(List<Pair<K, V>> pairs,
                                          String filePath,
                                          TriConsumer<K, V, Integer> consumer)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            IntStreamEx.range(0, pairs.size())
                    .forEach(i -> {
                        Pair<K, V> pair = pairs.get(i);
                        consumer.accept(pair.getKey(), pair.getValue(), i);
                        try
                        {
                            writer.write(pair.getKey() + " -> " + pair.getValue() + "\n");
                        } catch (IOException exception)
                        {
                            throw new RuntimeException("Failed to write contents to file", exception);
                        }
                    });
        }
        catch (IOException exception)
        {
            throw new RuntimeException("Failed to open file", exception);
        }
    }

    // ((k1, v1), (k2, v2),..., (kn, vn)) => "{ k1: v1, k2: v2,..., kn: vn}".
    public static <K, V> String toJson(List<Pair<K, V>> pairs,
                                       TriFunction<K, V, Integer, String> keyMapper,
                                       TriFunction<K, V, Integer, String> valueMapper)
    {
        StringBuilder stringBuilder = new StringBuilder("{");
        IntStreamEx.range(0, pairs.size())
                .forEach(i -> {
                    Pair<K, V> pair = pairs.get(i);
                    stringBuilder.append("\"")
                            .append(keyMapper.apply(pair.getKey(),
                                    pair.getValue(), i))
                            .append("\": \"")
                            .append(valueMapper.apply(pair.getKey(),
                                    pair.getValue(), i))
                            .append("\"");
                    if (i < pairs.size() - 1) {
                        stringBuilder.append(", ");
                    }
                });
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

}
