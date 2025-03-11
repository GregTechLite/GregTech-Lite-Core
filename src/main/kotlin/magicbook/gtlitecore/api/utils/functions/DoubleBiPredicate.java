package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface DoubleBiPredicate
{

    boolean test(double a, double b);

    @NotNull
    default DoubleBiPredicate and(@NotNull DoubleBiPredicate other)
    {
        Objects.requireNonNull(other);
        return (double a, double b) -> this.test(a, b) && other.test(a, b);
    }

    @NotNull
    default DoubleBiPredicate negate()
    {
        return (double a, double b) -> !this.test(a, b);
    }

    @NotNull
    default DoubleBiPredicate or(@NotNull DoubleBiPredicate other)
    {
        Objects.requireNonNull(other);
        return (double a, double b) -> this.test(a, b) || other.test(a, b);
    }

}
