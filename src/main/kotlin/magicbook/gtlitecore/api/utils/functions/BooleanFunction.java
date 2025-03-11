package magicbook.gtlitecore.api.utils.functions;

@FunctionalInterface
public interface BooleanFunction<T>
{

    T apply(boolean value);

}
