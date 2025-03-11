package magicbook.gtlitecore.api.utils.functions;

@FunctionalInterface
public interface BooleanTernaryOperator
{

    boolean applyAsBoolean(boolean left, boolean middle, boolean right);

}
