package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface FloatBiConsumer
{

    void accept(float a, float b);

    @NotNull
    default FloatBiConsumer andThen(@NotNull FloatBiConsumer after)
    {
        Objects.requireNonNull(after);
        return (a, b) -> {
            this.accept(a, b);
            after.accept(a, b);
        };
    }

}
