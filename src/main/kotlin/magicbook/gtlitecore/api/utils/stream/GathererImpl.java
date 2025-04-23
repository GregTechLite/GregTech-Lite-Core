package magicbook.gtlitecore.api.utils.stream;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.MODULE)
public final class GathererImpl<T, A, R> implements Gatherer<T, A, R>
{

    private final Supplier<A> initializer;
    private final Gatherer.Integrator<A, T, R> integrator;
    private final BinaryOperator<A> combiner;
    private final BiConsumer<A, Gatherer.Downstream<? super R>> finisher;

    /* package */ GathererImpl<T, A, R> of(Supplier<A> initializer,
                                           Gatherer.Integrator<A, T, R> integrator,
                                           BinaryOperator<A> combiner,
                                           BiConsumer<A, Gatherer.Downstream<? super R>> finisher)
    {
        return new GathererImpl<>(Objects.requireNonNull(initializer),
                Objects.requireNonNull(integrator), Objects.requireNonNull(combiner),
                Objects.requireNonNull(finisher));
    }

    @Override
    public Supplier<A> initializer()
    {
        return initializer;
    }

    @Override
    public Gatherer.Integrator<A, T, R> integrator()
    {
        return integrator;
    }

    @Override
    public BinaryOperator<A> combiner()
    {
        return combiner;
    }

    @Override
    public BiConsumer<A, Gatherer.Downstream<? super R>> finisher()
    {
        return finisher;
    }

}
