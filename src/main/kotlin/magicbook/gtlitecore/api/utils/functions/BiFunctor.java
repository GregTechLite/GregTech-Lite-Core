package magicbook.gtlitecore.api.utils.functions;

import java.util.function.Function;

@FunctionalInterface
public interface BiFunctor<T1, T2, F extends BiFunctor<?, ?, ?>>
{

    <R1, R2> F map(Function<T1, R1> f1, Function<T2, R2> f2);

}
