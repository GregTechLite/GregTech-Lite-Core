package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface FloatTriConsumer
{

    void accept(float a, float b, float c);

    @NotNull
    default FloatTriConsumer andThen(@NotNull FloatTriConsumer after)
    {
        Objects.requireNonNull(after);
        return (a, b, c) -> {
            this.accept(a, b, c);
            after.accept(a, b, c);
        };
    }

}
