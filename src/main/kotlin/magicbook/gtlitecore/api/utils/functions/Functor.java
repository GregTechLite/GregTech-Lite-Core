package magicbook.gtlitecore.api.utils.functions;

import java.util.function.Function;

/**
 * Functor
 *
 * @author Magic_Sweepy
 *
 * <p>
 *     The functional interface {@link Function} is {@code f: T -> R}, which {@code T} is
 *     type of the param input, and {@code R} is type of return value. For example, if we
 *     want to transform a {@code String} to {@code Integer}, then we can define below:
 *     <pre>{@code
 *         Function<String, Integer> function = Integer::parseInt;
 *     }</pre>
 *     Then we should input a param {@code "testString"} in "domain" {@code T}, and then,
 *     the function {@code Function<String, Integer>} will map it to {@code R}, i.e.
 *     <pre>{@code
 *         Integer result = function.apply("testString");
 *     }</pre>
 *     In this code, {@code Integer} is {@code R}, and {@code String} is {@code T}.
 *     Functor is a higher version of {@code Function}.
 * </p>
 * <p>
 *     Functor is a higher version of {@code Function}, {@code T} means the param type,
 *     such as: if {@code T} is {@code Integer}, then {@code Functor<T, F>} will consists
 *     of a integer value maybe; {@code F} is the type of {@code Functor<T, F>}, and
 *     another constraint is {@code F} must be the subtype of {@code Functor<T, F>}.
 *     The method {@link #map(Function)} is not like {@link Function#apply(Object)},
 *     it will contain a function {@code Function<T, R>}, i.e. {@code f: T -> R},
 *     and then map it to {@code F}, this method will transform value of {@code Functor},
 *     return a new {@code Functor} which consists of the transformed value. For example,
 *     if we create a class which implements this class:
 *     <pre>{@code
 *         public class FunctorImpl implements Functor<String, Functor> {
 *             private String value;
 *
 *             public FunctorImpl(String value) {
 *                 this.value = value;
 *             }
 *
 *             @Override
 *             public <R> FunctorImpl map(Function<String, R> function) {
 *                 R result = function.apply(value);
 *                 return new Functor(result.toString());
 *             }
 *         }
 *     }</pre>
 *     The {@code FunctorImpl} has same function with the example about {@code Function},
 *     in this example, {@code FunctorImpl} is a {@code Functor} which consists {@code String}
 *     value, and its {@link #map(Function)} method return a new {@code FunctorImpl}, i.e.
 *     <pre>{@code
 *         FunctorImpl functor = new FunctorImpl("testString");
 *         FunctorImpl mappedFunctor = functor.map(Integer::parseInt);
 *     }</pre>
 *     You can see that {@code "testString"} in {@code String}, this example returns a
 *     new {@code FunctorImpl} which consists of {@code "testString"}.
 * </p>
 *
 * @since 1.0.0
 */
@FunctionalInterface
public interface Functor<T, F extends Functor<?, ?>>
{

    <R> F map(Function<T, R> f);

}
