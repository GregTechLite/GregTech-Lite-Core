package magicbook.gtlitecore.core.module;

import magicbook.gtlitecore.api.module.IModuleContainer;
import magicbook.gtlitecore.api.module.ModuleContainer;
import magicbook.gtlitecore.api.utils.GTLiteValues;

@ModuleContainer
public class GTLiteModules implements IModuleContainer
{

    public static final String MODULE_CORE = "core";
    public static final String MODULE_INTEGRATION = "integration";
    public static final String MODULE_WORLDGEN = "worldgen";

    public static final String MODULE_JEI = "jei";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId()
    {
        return GTLiteValues.MODID;
    }

}
