package magicbook.gtlitecore.api.utils.functions;

import java.util.Comparator;
import java.util.Objects;

@FunctionalInterface
public interface TernaryOperator<T> extends TriFunction<T, T, T, T>
{

    static <T> TernaryOperator<T> minBy(Comparator<? super T> comparator)
    {
        Objects.requireNonNull(comparator);
        return (a, b, c) -> {
            T t = comparator.compare(a, b) <= 0 ? a : b;
            return comparator.compare(t, c) <= 0 ? t : c;
        };
    }

    static <T> TernaryOperator<T> maxBy(Comparator<? super T> comparator)
    {
        return (a, b, c) -> {
            T t = comparator.compare(a, b) >= 0 ? a : b;
            return comparator.compare(t, c) >= 0 ? t : c;
        };
    }

}
