package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface IntTriPredicate
{

    boolean test(int a, int b, int c);

    @NotNull
    default IntTriPredicate and(@NotNull IntTriPredicate other)
    {
        Objects.requireNonNull(other);
        return (int a, int b, int c) -> this.test(a, b, c) && other.test(a, b, c);
    }

    @NotNull
    default IntTriPredicate negate()
    {
        return (int a, int b, int c) -> !this.test(a, b, c);
    }

    @NotNull
    default IntTriPredicate or(@NotNull IntTriPredicate other)
    {
        Objects.requireNonNull(other);
        return (int a, int b, int c) -> this.test(a, b, c) || other.test(a, b, c);
    }

}
