package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface FloatTriPredicate
{

    boolean test(float a, float b, float c);

    @NotNull
    default FloatTriPredicate and(@NotNull FloatTriPredicate other)
    {
        Objects.requireNonNull(other);
        return (float a, float b, float c) -> this.test(a, b, c) && other.test(a, b, c);
    }

    @NotNull
    default FloatTriPredicate negate()
    {
        return (float a, float b, float c) -> !this.test(a, b, c);
    }

    @NotNull
    default FloatTriPredicate or(@NotNull FloatTriPredicate other)
    {
        Objects.requireNonNull(other);
        return (float a, float b, float c) -> this.test(a, b, c) || other.test(a, b, c);
    }

}
