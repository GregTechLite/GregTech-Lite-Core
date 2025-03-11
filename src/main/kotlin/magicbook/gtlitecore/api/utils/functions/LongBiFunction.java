package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface LongBiFunction<R>
{

    R apply(long a, long b);

    @NotNull
    default <S> LongBiFunction<S> andThen(@NotNull Function<? super R,
                                                            ? extends S> after)
    {
        Objects.requireNonNull(after);
        return (long a, long b) -> after.apply(this.apply(a, b));
    }

}
