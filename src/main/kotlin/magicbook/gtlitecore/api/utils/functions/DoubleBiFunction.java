package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface DoubleBiFunction<R>
{

    R apply(double a, double b);

    @NotNull
    default <S> DoubleBiFunction<S> andThen(@NotNull Function<? super R,
                                                              ? extends S> after)
    {
        Objects.requireNonNull(after);
        return (double a, double b) -> after.apply(this.apply(a, b));
    }

}
