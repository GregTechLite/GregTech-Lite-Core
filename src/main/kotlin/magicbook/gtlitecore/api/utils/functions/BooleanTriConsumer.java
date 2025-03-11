package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface BooleanTriConsumer
{

    void accept(boolean left, boolean middle, boolean right);

    @NotNull
    default BooleanTriConsumer andThen(@NotNull BooleanTriConsumer after)
    {
        Objects.requireNonNull(after);
        return (left, middle, right) -> {
            this.accept(left, middle, right);
            after.accept(left, middle, right);
        };
    }

}
