package magicbook.gtlitecore.api.utils.functions;

import java.util.Comparator;
import java.util.Objects;

@FunctionalInterface
public interface IntTernaryOperator
{

    int applyAsInt(int a, int b, int c);

    static IntTernaryOperator minBy(Comparator<Integer> comparator)
    {
        Objects.requireNonNull(comparator);
        return (a, b, c) -> {
            int t = comparator.compare(a, b) <= 0 ? a : b;
            return comparator.compare(t, c) <= 0 ? t : c;
        };
    }

    static IntTernaryOperator maxBy(Comparator<Integer> comparator)
    {
        return (a, b, c) -> {
            int t = comparator.compare(a, b) >= 0 ? a : b;
            return comparator.compare(t, c) >= 0 ? t : c;
        };
    }

}
