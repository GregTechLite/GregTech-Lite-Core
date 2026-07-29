package gregtechlite.gtlitecore.mixins;

import gregtechlite.gtlitecore.api.GTLiteValues;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface CustomMixinLoader
{
    /**
     * Distributes the configuration files for mixins by single name.
     * <p>
     * All mixin configurations has unique mod id, and will use single name
     * as secondary name, e.g. <tt>"mixins.gtlitecore.gregtech"</tt>.
     *
     * @param names All distributed names for mixin configurations.
     * @return      Returns all distributed names corresponding mixin configuration files.
     */
    default @Unmodifiable List<String> createMixinConfigs(String... names)
    {
        return Arrays.stream(names)
                .map(s -> String.format("mixins.%s.%s.json", GTLiteValues.MOD_ID, s))
                .collect(Collectors.toList());
    }
}
