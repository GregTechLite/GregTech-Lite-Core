package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface FloatTriFunction<R>
{

    R apply(float a, float b, float c);

    @NotNull
    default <S> FloatTriFunction<S> andThen(@NotNull Function<? super R,
                                                              ? extends S> after)
    {
        Objects.requireNonNull(after);
        return (float a, float b, float c) -> after.apply(this.apply(a, b, c));
    }

}
