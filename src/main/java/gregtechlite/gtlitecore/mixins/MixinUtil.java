package gregtechlite.gtlitecore.mixins;

import gregtechlite.magicbook.collection.ExtraCollectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.List;

import static gregtechlite.gtlitecore.api.GTLiteValues.MOD_ID;

public final class MixinUtil
{

    @Unmodifiable
    public static List<String> getMixinConfigs(@NotNull String... names)
    {
        return Arrays.stream(names)
                .map(s -> String.format("mixins.%s.%s.json", MOD_ID, s))
                .collect(ExtraCollectors.toImmutableList());
    }

}
