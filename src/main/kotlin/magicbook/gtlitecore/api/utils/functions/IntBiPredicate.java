package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface IntBiPredicate
{

    boolean test(int a, int b);

    @NotNull
    default IntBiPredicate and(@NotNull IntBiPredicate other)
    {
        Objects.requireNonNull(other);
        return (int a, int b) -> this.test(a, b) && other.test(a, b);
    }

    @NotNull
    default IntBiPredicate negate()
    {
        return (int a, int b) -> !this.test(a, b);
    }

    @NotNull
    default IntBiPredicate or(@NotNull IntBiPredicate other)
    {
        Objects.requireNonNull(other);
        return (int a, int b) -> this.test(a, b) || other.test(a, b);
    }

}
