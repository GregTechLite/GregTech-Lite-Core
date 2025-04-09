package magicbook.gtlitecore.core.module;

import magicbook.gtlitecore.api.module.IModuleContainer;
import magicbook.gtlitecore.api.module.ModuleContainer;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@ModuleContainer
public class GTLiteModules implements IModuleContainer
{

    public static final String MODULE_CORE = "core";
    public static final String MODULE_INTEGRATION = "integration";
    public static final String MODULE_WORLDGEN = "worldgen";

    public static final String MODULE_JEI = "jei";
    public static final String MODULE_TOP = "top";
    public static final String MODULE_GRS = "grs";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId()
    {
        return GTLiteValues.MODID;
    }

}
