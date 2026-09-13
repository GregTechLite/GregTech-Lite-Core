package gregtechlite.gtlitecore.mixins.hooks

import gregtech.api.GTValues
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.recipes.RecipeMap
import gregtech.api.recipes.RecipeMaps
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.item.ItemStack

object JEIRecipeGroup
{
    @JvmField
    val recipeGroups = openHashMapOf<ItemStack, String>()

    init
    {
        addRecipeGroup(GTLiteMetaTileEntities.PLASMA_ARC_TRANSMITTER, RecipeMaps.ARC_FURNACE_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.PLASMA_ARC_TRANSMITTER, RecipeMaps.ALLOY_SMELTER_RECIPES)

        addRecipeGroup(GTLiteMetaTileEntities.NANO_ASSEMBLY_COMPLEX, GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES)

        addRecipeGroup(GTLiteMetaTileEntities.MATTER_RESHAPING_FRAMEWORK, GTLiteRecipeMaps.MATTER_RESHAPING_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.MATTER_RESHAPING_FRAMEWORK, RecipeMaps.FORGE_HAMMER_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.MATTER_RESHAPING_FRAMEWORK, RecipeMaps.FLUID_SOLIDFICATION_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.MATTER_RESHAPING_FRAMEWORK, RecipeMaps.COMPRESSOR_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.MATTER_RESHAPING_FRAMEWORK, GTLiteRecipeMaps.TOOL_CASTER_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.MATTER_RESHAPING_FRAMEWORK, RecipeMaps.EXTRACTOR_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.MATTER_RESHAPING_FRAMEWORK, RecipeMaps.CANNER_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.MATTER_RESHAPING_FRAMEWORK, GTLiteRecipeMaps.LAMINATOR_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.MATTER_RESHAPING_FRAMEWORK, GTLiteRecipeMaps.VULCANIZATION_RECIPES)

        addRecipeGroup(GTLiteMetaTileEntities.NANOLITHOGRAPHY_ARRAY, GTLiteRecipeMaps.PCB_FACTORY_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.MICROSCALE_CIRCUIT_DETECTOR, GTLiteRecipeMaps.PCB_FACTORY_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.BIO_CULTIVATION_CHAMBER, GTLiteRecipeMaps.PCB_FACTORY_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.WATER_COOLING_TOWER, GTLiteRecipeMaps.PCB_FACTORY_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.THERMOSINK_COOLING_TOWER, GTLiteRecipeMaps.PCB_FACTORY_RECIPES)

        addRecipeGroup(GTLiteMetaTileEntities.CONSCIOUSNESS_STORAGE_CENTER, GTLiteRecipeMaps.NANO_FORGE_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.NANITE_REPLICATION_UNRESTRICOR, GTLiteRecipeMaps.NANO_FORGE_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.VIRTUAL_GESTALT_COMPUTING_UPLINK, GTLiteRecipeMaps.NANO_FORGE_RECIPES)

        addRecipeGroup(GTLiteMetaTileEntities.BLACKHOLE_FORMER, RecipeMaps.BENDER_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.BLACKHOLE_FORMER, RecipeMaps.FORMING_PRESS_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.BLACKHOLE_FORMER, RecipeMaps.EXTRUDER_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.BLACKHOLE_FORMER, RecipeMaps.WIREMILL_RECIPES)
        addRecipeGroup(GTLiteMetaTileEntities.BLACKHOLE_FORMER, GTLiteRecipeMaps.LOOM_RECIPES)
    }

    private fun <T : MetaTileEntity> addRecipeGroup(mte: T, recipeMap: RecipeMap<*>)
    {
        recipeGroups[mte.stack()] = name(recipeMap)
    }

    private fun name(recipeMap: RecipeMap<*>): String = "${GTValues.MODID}.${recipeMap.unlocalizedName}"
}