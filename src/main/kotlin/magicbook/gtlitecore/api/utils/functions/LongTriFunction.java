package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface LongTriFunction<R>
{

    R apply(long a, long b, long c);

    @NotNull
    default <S> LongTriFunction<S> andThen(@NotNull Function<? super R,
                                                             ? extends S> after)
    {
        Objects.requireNonNull(after);
        return (long a, long b, long c) -> after.apply(this.apply(a, b, c));
    }

}
