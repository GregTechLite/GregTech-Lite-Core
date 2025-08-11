package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Lubricant
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SAP_COLLECTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RainbowSap
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resin
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import net.minecraft.init.Blocks

@Suppress("Deprecation")
internal object SapCollectorRecipes
{

    // @formatter:off

    fun init()
    {
        // Vanilla logs.
        SAP_COLLECTOR_RECIPES.recipeBuilder()
            .notConsumable(DistilledWater.getFluid(10))
            .fluidOutputs(Resin.getFluid(100))
            .EUt(VA[ULV].toLong())
            .duration(1 * SECOND)
            .blockStates("common", arrayListOf(
                Blocks.LOG.getStateFromMeta(0),
                Blocks.LOG.getStateFromMeta(1),
                Blocks.LOG.getStateFromMeta(2),
                Blocks.LOG.getStateFromMeta(3),
                Blocks.LOG2.getStateFromMeta(0),
                Blocks.LOG2.getStateFromMeta(1)))
            .buildAndRegister()

        // Rainbow logs.
        SAP_COLLECTOR_RECIPES.recipeBuilder()
            .notConsumable(Lubricant.getFluid(10))
            .fluidOutputs(RainbowSap.getFluid(100))
            .EUt(VA[ULV])
            .duration(1 * SECOND)
            .blockStates("rainbow", arrayListOf(
                GTLiteMetaBlocks.LOGS[2].getStateFromMeta(5)))
            .buildAndRegister()
    }

    // @formatter:on

}