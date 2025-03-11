package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface DoubleTriPredicate
{

    boolean test(double a, double b, double c);

    @NotNull
    default DoubleTriPredicate and(@NotNull DoubleTriPredicate other)
    {
        Objects.requireNonNull(other);
        return (double a, double b, double c) -> this.test(a, b, c) && other.test(a, b, c);
    }

    @NotNull
    default DoubleTriPredicate negate()
    {
        return (double a, double b, double c) -> !this.test(a, b, c);
    }

    @NotNull
    default DoubleTriPredicate or(@NotNull DoubleTriPredicate other)
    {
        Objects.requireNonNull(other);
        return (double a, double b, double c) -> this.test(a, b, c) || other.test(a, b, c);
    }

}
