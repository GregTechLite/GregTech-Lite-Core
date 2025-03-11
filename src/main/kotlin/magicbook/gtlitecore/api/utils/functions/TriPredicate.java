package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface TriPredicate<T, U, S>
{

    boolean test(T t, U u, S s);

    @NotNull
    default TriPredicate<T, U, S> and(@NotNull TriPredicate<? super T,
                                                            ? super U,
                                                            ? super S> other)
    {
        Objects.requireNonNull(other);
        return (T t, U u, S s) -> this.test(t, u, s) && other.test(t, u, s);
    }

    @NotNull
    default TriPredicate<T, U, S> negate()
    {
        return (T t, U u, S s) -> !this.test(t, u, s);
    }

    @NotNull
    default TriPredicate<T, U, S> or(@NotNull TriPredicate<? super T,
                                                           ? super U,
                                                           ? super S> other)
    {
        Objects.requireNonNull(other);
        return (T t, U u, S s) -> this.test(t, u, s) || other.test(t, u, s);
    }

}
