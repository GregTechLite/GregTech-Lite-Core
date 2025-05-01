package magicbook.gtlitecore.loader.recipe.chain.food

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.COMPRESSOR_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.SeedOil
import gregtech.common.items.MetaItems.PLANT_BALL
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MULTICOOKER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polenta
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CERAMIC_BOWL
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CORN
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.POLENTA
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.BARE_CORN_KERNEL
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.CORN_COB
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.CORN_KERNEL

@Suppress("MISSING_DEPENDENCY_CLASS")
class CornChain
{

    companion object
    {

        fun init()
        {
            // Corn -> Corn Cob
            ModHandler.addShapedRecipe(true, "corn_cob", CORN_COB.itemStack,
                "A  ",
                'A', CORN)

            // Corn -> Corn Kernel
            ModHandler.addShapedRecipe(true, "corn_kernel", CORN_KERNEL.getItemStack(8),
                " A ",
                'A', CORN)

            // Corn -> Corn Cob + Corn Kernel
            CENTRIFUGE_RECIPES.recipeBuilder()
                .input(CORN)
                .outputs(CORN_KERNEL.getItemStack(16))
                .outputs(CORN_COB.itemStack)
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Corn Kernel -> Bare Corn Kernel
            ModHandler.addShapelessRecipe("bare_corn_kernel", BARE_CORN_KERNEL.itemStack,
                CORN_KERNEL.itemStack)

            EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(CORN_KERNEL.itemStack)
                .outputs(BARE_CORN_KERNEL.itemStack)
                .EUt(7) // ULV
                .duration(3 * SECOND)
                .buildAndRegister()

            // Bare Corn Kernel decomposition.
            EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(BARE_CORN_KERNEL.itemStack)
                .fluidOutputs(SeedOil.getFluid(8))
                .EUt(2) // ULV
                .duration(1 * SECOND + 12 * TICK)
                .buildAndRegister()

            // Corn cob decomposition.
            COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(CORN_COB.getItemStack(8))
                .output(PLANT_BALL)
                .EUt(2) // ULV
                .duration(15 * SECOND)
                .buildAndRegister()

            // Bare Corn Kernel + H2O -> Polenta
            MULTICOOKER_RECIPES.recipeBuilder()
                .inputs(BARE_CORN_KERNEL.getItemStack(8))
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(Polenta.getFluid(250))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            CANNER_RECIPES.recipeBuilder()
                .input(CERAMIC_BOWL)
                .fluidInputs(Polenta.getFluid(250))
                .output(POLENTA)
                .EUt(VH[LV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

        }

    }

}