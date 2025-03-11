package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface FloatPredicate
{

    boolean test(float value);

    @NotNull
    default FloatPredicate and(@NotNull FloatPredicate other)
    {
        Objects.requireNonNull(other);
        return (t) -> this.test(t) && other.test(t);
    }

    @NotNull
    default FloatPredicate negate()
    {
        return (t) -> !this.test(t);
    }

    @NotNull
    default FloatPredicate or(@NotNull FloatPredicate other)
    {
        Objects.requireNonNull(other);
        return (t) -> this.test(t) || other.test(t);
    }

}
