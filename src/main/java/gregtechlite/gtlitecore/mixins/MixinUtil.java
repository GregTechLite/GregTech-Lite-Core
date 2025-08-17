package gregtechlite.gtlitecore.mixins;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.List;

import static gregtechlite.gtlitecore.api.GTLiteValues.MOD_ID;

public final class MixinUtil
{

    @SuppressWarnings("UnstableApiUsage")
    @Unmodifiable
    public static List<String> getMixinConfigs(@NotNull String... names)
    {
        return Arrays.stream(names)
                .map(s -> String.format("mixins.%s.%s.json", MOD_ID, s))
                .collect(ImmutableList.toImmutableList());
    }

}
