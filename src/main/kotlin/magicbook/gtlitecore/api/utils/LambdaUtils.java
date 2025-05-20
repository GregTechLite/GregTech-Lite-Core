package magicbook.gtlitecore.api.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class LambdaUtils
{

    @NotNull
    public static <T> Consumer<T> mergeConsumers(@Nullable Consumer<T> first,
                                                 @NotNull Consumer<T> andThen)
    {
        return first == null ? andThen : first.andThen(andThen);
    }

}