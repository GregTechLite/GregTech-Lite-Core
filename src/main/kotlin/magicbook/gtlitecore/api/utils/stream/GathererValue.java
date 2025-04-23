package magicbook.gtlitecore.api.utils.stream;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

@SuppressWarnings({"rawtypes", "unchecked"})
/* package */ enum GathererValue implements Supplier, BinaryOperator, BiConsumer
{

    DEFAULT;

    /* package */ final BinaryOperator<Void> statelessCombiner = (left, right) -> null;

    GathererValue() {}

    /**
     * {@inheritDoc}
     *
     * @param state      The first input argument.
     * @param downstream The second input argument.
     */
    @Override
    public void accept(Object state, Object downstream) {}

    /**
     * {@inheritDoc}
     *
     * @param left  The first function argument.
     * @param right The second function argument.
     */
    @Override
    public Object apply(Object left, Object right)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get()
    {
        return null;
    }

    /* package */ <A> Supplier<A> initializer()
    {
        return this;
    }

    /* package */ <T> BinaryOperator<T> combiner()
    {
        return this;
    }

    /* package */ <T, R> BiConsumer<T, Gatherer.Downstream<? super R>> finisher()
    {
        return this;
    }

}
