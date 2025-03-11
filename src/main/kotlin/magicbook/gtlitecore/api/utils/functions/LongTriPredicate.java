package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface LongTriPredicate
{

    boolean test(long a, long b, long c);

    @NotNull
    default LongTriPredicate and(@NotNull LongTriPredicate other)
    {
        Objects.requireNonNull(other);
        return (long a, long b, long c) -> this.test(a, b, c) && other.test(a, b, c);
    }

    @NotNull
    default LongTriPredicate negate()
    {
        return (long a, long b, long c) -> !this.test(a, b, c);
    }

    @NotNull
    default LongTriPredicate or(@NotNull LongTriPredicate other)
    {
        Objects.requireNonNull(other);
        return (long a, long b, long c) -> this.test(a, b, c) || other.test(a, b, c);
    }

}
