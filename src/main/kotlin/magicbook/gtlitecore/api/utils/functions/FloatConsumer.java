package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface FloatConsumer
{

    void accept(float value);

    @NotNull
    default FloatConsumer andThen(@NotNull FloatConsumer after)
    {
        Objects.requireNonNull(after);
        return (float t) -> {
            this.accept(t);
            after.accept(t);
        };
    }

}
