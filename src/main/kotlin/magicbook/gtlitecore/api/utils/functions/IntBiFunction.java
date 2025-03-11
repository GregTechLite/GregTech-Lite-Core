package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface IntBiFunction<R>
{

    R apply(int a, int b);

    @NotNull
    default <S> IntBiFunction<S> andThen(@NotNull Function<? super R,
                                                           ? extends S> after)
    {
        Objects.requireNonNull(after);
        return (int a, int b) -> after.apply(this.apply(a, b));
    }

}
