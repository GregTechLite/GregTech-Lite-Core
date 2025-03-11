package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface IntTriFunction<R>
{

    R apply(int a, int b, int c);

    @NotNull
    default <S> IntTriFunction<S> andThen(@NotNull Function<? super R,
                                                            ? extends S> after)
    {
        Objects.requireNonNull(after);
        return (int a, int b, int c) -> after.apply(this.apply(a, b, c));
    }

}
