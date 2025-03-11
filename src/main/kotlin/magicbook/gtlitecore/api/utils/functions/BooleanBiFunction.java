package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface BooleanBiFunction<R>
{

    R apply(boolean left, boolean right);

    @NotNull
    default <S> BooleanBiFunction<S> andThen(@NotNull Function<? super R,
                                                               ? extends S> after)
    {
        Objects.requireNonNull(after);
        return (boolean left, boolean right) -> after.apply(this.apply(left, right));
    }

}
