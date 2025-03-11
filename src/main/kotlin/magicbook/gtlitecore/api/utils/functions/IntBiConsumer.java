package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface IntBiConsumer
{

    void accept(int a, int b);

    @NotNull
    default IntBiConsumer andThen(@NotNull IntBiConsumer after)
    {
        Objects.requireNonNull(after);
        return (a, b) -> {
            this.accept(a, b);
            after.accept(a, b);
        };
    }

}
