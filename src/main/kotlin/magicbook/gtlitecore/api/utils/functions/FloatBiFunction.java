package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface FloatBiFunction<R>
{

    R apply(float a, float b);

    @NotNull
    default <S> FloatBiFunction<S> andThen(@NotNull Function<? super R,
                                                             ? extends S> after)
    {
        Objects.requireNonNull(after);
        return (float a, float b) -> after.apply(this.apply(a, b));
    }

}
