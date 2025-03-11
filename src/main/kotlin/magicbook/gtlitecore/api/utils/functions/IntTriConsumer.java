package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface IntTriConsumer
{

    void accept(int a, int b, int c);

    @NotNull
    default IntTriConsumer andThen(@NotNull IntTriConsumer after)
    {
        Objects.requireNonNull(after);
        return (a, b, c) -> {
            this.accept(a, b, c);
            after.accept(a, b, c);
        };
    }

}
