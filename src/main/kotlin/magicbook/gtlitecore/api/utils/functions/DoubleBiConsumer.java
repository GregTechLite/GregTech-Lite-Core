package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface DoubleBiConsumer
{

    void accept(double a, double b);

    @NotNull
    default DoubleBiConsumer andThen(@NotNull DoubleBiConsumer after)
    {
        Objects.requireNonNull(after);
        return (a, b) -> {
            this.accept(a, b);
            after.accept(a, b);
        };
    }

}
