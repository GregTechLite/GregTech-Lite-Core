package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface BooleanBiPredicate
{

    boolean test(boolean left, boolean right);

    @NotNull
    default BooleanBiPredicate and(@NotNull BooleanBiPredicate other)
    {
        Objects.requireNonNull(other);
        return (boolean left, boolean right) -> this.test(left, right) && other.test(left, right);
    }

    @NotNull
    default BooleanBiPredicate negate()
    {
        return (boolean left, boolean right) -> !this.test(left, right);
    }

    @NotNull
    default BooleanBiPredicate or(@NotNull BooleanBiPredicate other)
    {
        Objects.requireNonNull(other);
        return (boolean left, boolean right) -> this.test(left, right) || other.test(left, right);
    }

}
