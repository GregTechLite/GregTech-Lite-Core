package magicbook.gtlitecore.integration.justenoughitems;

import magicbook.gtlitecore.api.module.Module;
import magicbook.gtlitecore.api.recipe.frontend.SpacePumpRecipeFrontend;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.api.utils.tuples.Pair;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import magicbook.gtlitecore.core.module.GTLiteModules;
import magicbook.gtlitecore.integration.IntegrationSubModule;
import magicbook.gtlitecore.integration.justenoughitems.category.SpacePumpRecipeCategory;
import magicbook.gtlitecore.integration.justenoughitems.info.SpacePumpRecipeWrapper;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraftforge.fluids.FluidStack;
import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JEIPlugin
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
    private IGuiHelper guiHelper;
    private IIngredientRegistry ingredientRegistry;

    public JustEnoughItemsModule() {}

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registry)
    {
        this.guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new SpacePumpRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(@NotNull IModRegistry registry)
    {
        this.blacklist = registry.getJeiHelpers().getIngredientBlacklist();
        this.ingredientRegistry = registry.getIngredientRegistry();

        // SpacePumpRecipeCategory / SpacePumpRecipeWrapper
        String spacePumpId = GTLiteValues.MODID + ":" + "space_pump_module";
        List<SpacePumpRecipeWrapper> spacePumpInfo = StreamEx.of(SpacePumpRecipeFrontend.RECIPES.entrySet())
                .filter(info -> {
                    Pair<Integer, Integer> infoKey = info.getKey();
                    FluidStack infoValue = info.getValue();
                    return infoKey != null && infoKey.getKey() != null && infoKey.getValue() != null
                            && infoValue != null;
                })
                // k1 > k2: (k1, v1), (k2, v1); v1 > v2: (k1, v1), (k1, v2)
                .sorted(Comparator.comparingInt((Map.Entry<Pair<Integer, Integer>, FluidStack> info) -> info.getKey().getKey()) // planetId
                        .thenComparingInt(info -> info.getKey().getValue())) // fluidId
                .map(info -> {
                    Pair<Integer, Integer> infoKey = info.getKey();
                    FluidStack infoValue = info.getValue();
                    return new SpacePumpRecipeWrapper(infoKey.getKey(), infoKey.getValue(), infoValue);
                })
                .distinct()
                .toList();

        registry.addRecipes(spacePumpInfo, spacePumpId);
        registry.addRecipeCatalyst(GTLiteMetaTileEntities.SPACE_PUMP_MK1.getStackForm(), spacePumpId);
        registry.addRecipeCatalyst(GTLiteMetaTileEntities.SPACE_PUMP_MK2.getStackForm(), spacePumpId);
        registry.addRecipeCatalyst(GTLiteMetaTileEntities.SPACE_PUMP_MK3.getStackForm(), spacePumpId);
    }

    @Override
    public void onRuntimeAvailable(@NotNull IJeiRuntime runtime)
    {
        JustEnoughItemsModule.runtime = runtime;
    }

    @NotNull
    @Override
    public Logger getLogger()
    {
        return logger;
    }

}
