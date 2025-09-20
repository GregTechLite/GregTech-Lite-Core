package gregtechlite.gtlitecore.integration.justenoughitems

import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials
import gregtech.api.unification.ore.OrePrefix
import com.morphismmc.morphismlib.util.SidedLogger
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.module.Module
import gregtechlite.gtlitecore.api.recipe.frontend.SpacePumpRecipeFrontend
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import gregtechlite.gtlitecore.core.module.GTLiteModules.Companion.MODULE_JEI
import gregtechlite.gtlitecore.integration.IntegrationSubModule
import gregtechlite.gtlitecore.integration.justenoughitems.category.SpacePumpRecipeCategory
import gregtechlite.gtlitecore.integration.justenoughitems.info.SpacePumpRecipeWrapper
import mezz.jei.api.IGuiHelper
import mezz.jei.api.IJeiRuntime
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.ingredients.IIngredientBlacklist
import mezz.jei.api.ingredients.IIngredientRegistry
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraftforge.fluids.FluidStack
import org.apache.logging.log4j.Logger

@JEIPlugin
@Module(moduleId = MODULE_JEI,
        containerId = MOD_ID,
        modDependencies = [ "jei" ],
        name = "GregTech Lite JEI Module",
        descriptions = "Just Enough Items (JEI) Module of GregTech Lite Core Mod.")
class JustEnoughItemsModule : IntegrationSubModule(), IModPlugin
{

    companion object
    {

        @JvmField
        val logger: Logger = SidedLogger("$MOD_ID-jei-module")

    }

    private lateinit var runtime: IJeiRuntime
    private lateinit var blacklist: IIngredientBlacklist
    private lateinit var guiHelper: IGuiHelper
    private lateinit var ingredientRegistry: IIngredientRegistry

    @Suppress("Deprecation", "UnstableApiUsage")
    override fun onRuntimeAvailable(availableRuntime: IJeiRuntime)
    {
        this.runtime = availableRuntime
        logger.info("Registering JEI Blacklist...")

        // Zirconia => Baddeleyite
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dust, Materials.Zirconia))
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Zirconia))
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Zirconia))

        // Phosphate is deprecated TODO Remove this blacklist settings in 2.10.
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dust, Materials.Phosphate))
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Phosphate))
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Phosphate))
    }
    
    override fun registerCategories(registry: IRecipeCategoryRegistration)
    {
        this.guiHelper = registry.jeiHelpers.guiHelper
        logger.info("Registering JEI Recipe Categories...")

        registry.addRecipeCategories(SpacePumpRecipeCategory(registry.jeiHelpers.guiHelper))
    }
    
    override fun register(registry: IModRegistry)
    {
        this.blacklist = registry.jeiHelpers.ingredientBlacklist
        this.ingredientRegistry = registry.ingredientRegistry
        logger.info("Registering JEI Recipe Wrappers and Catalysts...")

        // SpacePumpRecipeCategory / SpacePumpRecipeWrapper
        val spacePumpId = SpacePumpRecipeCategory.UID
        val spacePumpInfo = SpacePumpRecipeFrontend.RECIPES.entries
            .sortedWith(compareBy<Map.Entry<Pair<Int, Int>, FluidStack>> { it.key.first }.thenBy { it.key.second })
            .map { info ->
                val infoKey = info.key
                val infoValue = info.value
                SpacePumpRecipeWrapper(infoKey.first, infoKey.second, infoValue)
            }
            .distinct()
            .toList()

        registry.addRecipes(spacePumpInfo, spacePumpId)
        registry.addRecipeCatalyst(GTLiteMetaTileEntities.SPACE_PUMP_MK1.stackForm, spacePumpId)
        registry.addRecipeCatalyst(GTLiteMetaTileEntities.SPACE_PUMP_MK2.stackForm, spacePumpId)
        registry.addRecipeCatalyst(GTLiteMetaTileEntities.SPACE_PUMP_MK3.stackForm, spacePumpId)
    }

    override fun getLogger(): Logger = Companion.logger

}
