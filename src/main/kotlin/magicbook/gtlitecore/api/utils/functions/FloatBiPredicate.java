package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface FloatBiPredicate
{

    boolean test(float a, float b);

    @NotNull
    default FloatBiPredicate and(@NotNull FloatBiPredicate other)
    {
        Objects.requireNonNull(other);
        return (float a, float b) -> this.test(a, b) && other.test(a, b);
    }

    @NotNull
    default FloatBiPredicate negate()
    {
        return (float a, float b) -> !this.test(a, b);
    }

    @NotNull
    default FloatBiPredicate or(@NotNull FloatBiPredicate other)
    {
        Objects.requireNonNull(other);
        return (float a, float b) -> this.test(a, b) || other.test(a, b);
    }

}
