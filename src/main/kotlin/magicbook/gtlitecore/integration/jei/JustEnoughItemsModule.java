package magicbook.gtlitecore.integration.jei;

import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.core.module.GTLiteModules;
import magicbook.gtlitecore.integration.IntegrationSubModule;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@Module(moduleId = GTLiteModules.MODULE_JEI,
        containerId = GTLiteValues.MODID,
        modDependencies = { "jei" },
        name = "GregTech Lite JEI Module",
        descriptions = "Just Enough Items (JEI) Module of GregTech Lite Core Mod.")
public class JustEnoughItemsModule extends IntegrationSubModule implements IModPlugin
{

    public static final Logger logger = LogManager.getLogger(GTLiteValues.NAME + " JEI Module");

    private static IJeiRuntime runtime;
    private IIngredientBlacklist blacklist;

    public JustEnoughItemsModule() {}

    @Override
    public void register(@NotNull IModRegistry registry)
    {
        this.blacklist = registry.getJeiHelpers().getIngredientBlacklist();
    }

    @Override
    public void onRuntimeAvailable(@NotNull IJeiRuntime runtime)
    {
        JustEnoughItemsModule.runtime = runtime;
        // ...
    }

    @NotNull
    @Override
    public Logger getLogger()
    {
        return logger;
    }

}
