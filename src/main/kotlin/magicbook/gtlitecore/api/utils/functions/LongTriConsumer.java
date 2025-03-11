package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface LongTriConsumer
{

    void accept(long a, long b, long c);

    @NotNull
    default LongTriConsumer andThen(@NotNull LongTriConsumer after)
    {
        Objects.requireNonNull(after);
        return (a, b, c) -> {
            this.accept(a, b, c);
            after.accept(a, b, c);
        };
    }

}
