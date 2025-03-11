package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface LongBiConsumer
{

    void accept(long a, long b);

    @NotNull
    default LongBiConsumer andThen(@NotNull LongBiConsumer after)
    {
        Objects.requireNonNull(after);
        return (a, b) -> {
            this.accept(a, b);
            after.accept(a, b);
        };
    }

}
