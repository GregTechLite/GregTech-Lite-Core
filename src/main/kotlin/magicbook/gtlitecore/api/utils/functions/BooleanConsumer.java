package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface BooleanConsumer
{

    void accept(boolean value);

    @NotNull
    default BooleanConsumer andThen(@NotNull BooleanConsumer after)
    {
        Objects.requireNonNull(after);
        return (boolean t) -> {
            this.accept(t);
            after.accept(t);
        };
    }

}
