package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface LongBiPredicate
{

    boolean test(long a, long b);

    @NotNull
    default LongBiPredicate and(@NotNull LongBiPredicate other)
    {
        Objects.requireNonNull(other);
        return (long a, long b) -> this.test(a, b) && other.test(a, b);
    }

    @NotNull
    default LongBiPredicate negate()
    {
        return (long a, long b) -> !this.test(a, b);
    }

    @NotNull
    default LongBiPredicate or(@NotNull LongBiPredicate other)
    {
        Objects.requireNonNull(other);
        return (long a, long b) -> this.test(a, b) || other.test(a, b);
    }

}
