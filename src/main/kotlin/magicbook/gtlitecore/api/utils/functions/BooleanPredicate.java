package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface BooleanPredicate
{

    boolean test(boolean value);

    @NotNull
    default BooleanPredicate and(@NotNull BooleanPredicate other)
    {
        Objects.requireNonNull(other);
        return (t) -> this.test(t) && other.test(t);
    }

    @NotNull
    default BooleanPredicate negate()
    {
        return (t) -> !this.test(t);
    }

    @NotNull
    default BooleanPredicate or(@NotNull BooleanPredicate other)
    {
        Objects.requireNonNull(other);
        return (t) -> this.test(t) || other.test(t);
    }

}
