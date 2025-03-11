package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface BooleanTriPredicate
{

    boolean test(boolean left, boolean middle, boolean right);

    @NotNull
    default BooleanTriPredicate and(@NotNull BooleanTriPredicate other)
    {
        Objects.requireNonNull(other);
        return (boolean left, boolean middle, boolean right)
                -> this.test(left, middle, right) && other.test(left, middle, right);
    }

    @NotNull
    default BooleanTriPredicate negate()
    {
        return (boolean left, boolean middle, boolean right) -> !this.test(left, middle, right);
    }

    @NotNull
    default BooleanTriPredicate or(@NotNull BooleanTriPredicate other)
    {
        Objects.requireNonNull(other);
        return (boolean left, boolean middle, boolean right)
                -> this.test(left, middle, right) || other.test(left, middle, right);
    }

}
