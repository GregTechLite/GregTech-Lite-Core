package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface BooleanUnaryOperator
{

    boolean applyAsBoolean(boolean operand);

    @NotNull
    default BooleanUnaryOperator compose(@NotNull BooleanUnaryOperator before)
    {
        Objects.requireNonNull(before);
        return (boolean v) -> this.applyAsBoolean(before.applyAsBoolean(v));
    }

    @NotNull
    default BooleanUnaryOperator andThen(@NotNull BooleanUnaryOperator after)
    {
        Objects.requireNonNull(after);
        return (boolean t) -> after.applyAsBoolean(this.applyAsBoolean(t));
    }

    static BooleanUnaryOperator identity()
    {
        return t -> t;
    }

}
