package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface TriConsumer<T, U, S>
{

    void accept(T t, U u, S s);

    @NotNull
    default TriConsumer<T, U, S> andThen(@NotNull TriConsumer<? super T,
                                                              ? super U,
                                                              ? super S> after)
    {
        Objects.requireNonNull(after);
        return (l, m, r) -> {
            this.accept(l, m, r);
            after.accept(l, m, r);
        };
    }

}
