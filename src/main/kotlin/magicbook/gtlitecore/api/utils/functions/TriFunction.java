package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<T, U, R, S>
{

    S apply(T t, U u, R r);

    @NotNull
    default <V> TriFunction<T, U, R, V> andThen(@NotNull Function<? super S,
                                                                  ? extends V> after)
    {
        Objects.requireNonNull(after);
        return (T t, U u, R r) -> after.apply(this.apply(t, u, r));
    }

}
