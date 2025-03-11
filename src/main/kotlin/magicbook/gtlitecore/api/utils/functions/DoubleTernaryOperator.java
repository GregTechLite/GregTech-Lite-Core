package magicbook.gtlitecore.api.utils.functions;

import java.util.Comparator;
import java.util.Objects;

@FunctionalInterface
public interface DoubleTernaryOperator
{

    double applyAsDouble(double a, double b, double c);

    static DoubleTernaryOperator minBy(Comparator<Double> comparator)
    {
        Objects.requireNonNull(comparator);
        return (a, b, c) -> {
            double t = comparator.compare(a, b) <= 0 ? a : b;
            return comparator.compare(t, c) <= 0 ? t : c;
        };
    }

    static DoubleTernaryOperator maxBy(Comparator<Double> comparator)
    {
        Objects.requireNonNull(comparator);
        return (a, b, c) -> {
            double t = comparator.compare(a, b) >= 0 ? a : b;
            return comparator.compare(t, c) >= 0 ? t : c;
        };
    }

}
