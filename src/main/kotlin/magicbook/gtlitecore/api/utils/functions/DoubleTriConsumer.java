package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface DoubleTriConsumer
{

    void accept(double a, double b, double c);

    @NotNull
    default DoubleTriConsumer andThen(@NotNull DoubleTriConsumer after)
    {
        Objects.requireNonNull(after);
        return (a, b, c) -> {
            this.accept(a, b, c);
            after.accept(a, b, c);
        };
    }

}
