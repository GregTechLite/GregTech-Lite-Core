package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface DoubleTriFunction<R>
{

    R apply(double a, double b, double c);

    @NotNull
    default <S> DoubleTriFunction<S> andThen(@NotNull Function<? super R,
                                                               ? extends S> after)
    {
        Objects.requireNonNull(after);
        return (double a, double b, double c) -> after.apply(this.apply(a, b, c));
    }

}
