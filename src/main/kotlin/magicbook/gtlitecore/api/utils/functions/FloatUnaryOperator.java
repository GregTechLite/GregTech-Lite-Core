package magicbook.gtlitecore.api.utils.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@FunctionalInterface
public interface FloatUnaryOperator
{

    float applyAsFloat(float operand);

    @NotNull
    default FloatUnaryOperator compose(@NotNull FloatUnaryOperator before)
    {
        Objects.requireNonNull(before);
        return (float v) -> this.applyAsFloat(before.applyAsFloat(v));
    }

    @NotNull
    default FloatUnaryOperator andThen(@NotNull FloatUnaryOperator after)
    {
        Objects.requireNonNull(after);
        return (float t) -> after.applyAsFloat(this.applyAsFloat(t));
    }

    static FloatUnaryOperator identity()
    {
        return t -> t;
    }

}
