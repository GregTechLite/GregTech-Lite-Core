package magicbook.gtlitecore.api.utils.functions;

import java.util.Comparator;
import java.util.Objects;

@FunctionalInterface
public interface LongTernaryOperator
{

    long applyAsLong(long a, long b, long c);

    static LongTernaryOperator minBy(Comparator<Long> comparator)
    {
        Objects.requireNonNull(comparator);
        return (a, b, c) -> {
            long t = comparator.compare(a, b) <= 0L ? a : b;
            return comparator.compare(t, c) <= 0L ? t : c;
        };
    }

    static LongTernaryOperator maxBy(Comparator<Long> comparator)
    {
        return (a, b, c) -> {
            long t = comparator.compare(a, b) >= 0L ? a : b;
            return comparator.compare(t, c) >= 0L ? t : c;
        };
    }

}
