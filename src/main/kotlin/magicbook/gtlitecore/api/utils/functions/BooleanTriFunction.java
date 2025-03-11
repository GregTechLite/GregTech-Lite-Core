package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface BooleanTriFunction<R>
{

    R apply(boolean left, boolean middle, boolean right);

    @NotNull
    default <S> BooleanTriFunction<S> andThen(@NotNull Function<? super R,
                                                                ? extends S> after)
    {
        Objects.requireNonNull(after);
        return (boolean left, boolean middle, boolean right)
                -> after.apply(this.apply(left, middle, right));
    }

}
