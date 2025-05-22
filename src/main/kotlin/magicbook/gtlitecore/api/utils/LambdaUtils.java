package magicbook.gtlitecore.api.utils;

import one.util.streamex.StreamEx;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class LambdaUtils
{

    /**
     * Merged two {@link Consumer} and returns new {@link Consumer} which executed in sequence.
     *
     * @param first   The first {@link Consumer} (it is nullable).
     * @param andThen The second {@link Consumer} which will be late executed (cannot be null).
     * @return        Merged {@link Consumer}.
     *
     * @apiNote       For example:
     *                <pre>{@code
     *                    Consumer<String> print = System.out::println;
     *                    Consumer<String> validate = s -> {
     *                        if (s.isEmpty()) throw new IllegalArgumentException();
     *                    };
     *                    Consumer<String> merged = mergeConsumers(validate, log);
     *                    merged.accept("Hello");
     *                }</pre>
     *
     * @param <T>     The type of the input {@link Consumer}.
     */
    @NotNull
    public static <T> Consumer<T> mergeConsumers(@Nullable Consumer<T> first,
                                                 @NotNull Consumer<T> andThen)
    {
        return first == null ? andThen : first.andThen(andThen);
    }

    /**
     * Merged several {@link Consumer}s and returns new {@link Consumer} which executed in sequence.
     *
     * @param consumers Several nullable {@link Consumer}.
     * @return          Merged {@link Consumer}.
     *
     * @apiNote         For example:
     *                  <pre>{@code
     *                      List<Consumer<Integer>> actions = Arrays.asList(null, // Will be filtered.
     *                          i -> System.out.println("Received: " + i),
     *                          i -> database.save(i)); // Any actions, this is an example.
     *                      Consumer<Integer> pipeline = mergeConsumers(actions);
     *                      pipeline.accept(42);
     *                  }</pre>
     *
     * @param <T>       The type of the input {@link Consumer}.
     */
    public static <T> Consumer<T> mergeConsumers(@Nullable List<Consumer<T>> consumers)
    {
        return StreamEx.of(Optional.ofNullable(consumers).orElse(Collections.emptyList()))
                .nonNull()
                .reduce(Consumer::andThen).orElse(t -> {});
    }

}