package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface BooleanBiConsumer
{

    void accept(boolean left, boolean right);

    @NotNull
    default BooleanBiConsumer andThen(@NotNull BooleanBiConsumer after)
    {
        Objects.requireNonNull(after);
        return (left, right) -> {
            this.accept(left, right);
            after.accept(left, right);
        };
    }

}
