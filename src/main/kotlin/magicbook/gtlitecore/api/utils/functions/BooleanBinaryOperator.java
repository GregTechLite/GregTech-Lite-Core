package magicbook.gtlitecore.api.utils.functions;

@FunctionalInterface
public interface BooleanBinaryOperator
{

    boolean applyAsBoolean(boolean left, boolean right);

}
