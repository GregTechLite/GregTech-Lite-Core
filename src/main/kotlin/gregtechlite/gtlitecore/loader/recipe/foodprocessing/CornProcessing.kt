package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.COMPRESSOR_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.SeedOil
import gregtech.common.items.MetaItems.PLANT_BALL
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.getStack
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MULTICOOKER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polenta
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CERAMIC_BOWL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CORN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.POLENTA
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.BARE_CORN_KERNEL
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.CORN_COB
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.CORN_KERNEL

internal object CornProcessing
{

    // @formatter:off

    fun init()
    {
        // Corn -> Corn Cob
        ModHandler.addShapedRecipe(false, "corn_cob", CORN_COB.stack(),
            "A  ",
            'A', CORN)

        // Corn -> Corn Kernel
        ModHandler.addShapedRecipe(false, "corn_kernel", CORN_KERNEL.getStack(8),
            " A ",
            'A', CORN)

        // Corn -> Corn Cob + Corn Kernel
        CENTRIFUGE_RECIPES.addRecipe {
            input(CORN)
            outputs(CORN_KERNEL.getStack(16))
            outputs(CORN_COB.stack())
            EUt(VA[LV])
            duration(2 * SECOND)
        }

        // Corn Kernel -> Bare Corn Kernel
        ModHandler.addShapelessRecipe("bare_corn_kernel",
            BARE_CORN_KERNEL.stack(),
            CORN_KERNEL.stack())

        EXTRACTOR_RECIPES.addRecipe {
            inputs(CORN_KERNEL.stack())
            outputs(BARE_CORN_KERNEL.stack())
            EUt(7) // ULV
            duration(3 * SECOND)
        }

        // Bare Corn Kernel decomposition.
        EXTRACTOR_RECIPES.addRecipe {
            inputs(BARE_CORN_KERNEL.stack())
            fluidOutputs(SeedOil.getFluid(8))
            EUt(2) // ULV
            duration(1 * SECOND + 12 * TICK)
        }

        // Corn cob decomposition.
        COMPRESSOR_RECIPES.addRecipe {
            inputs(CORN_COB.getStack(8))
            output(PLANT_BALL)
            EUt(2) // ULV
            duration(15 * SECOND)
        }

        // Bare Corn Kernel + H2O -> Polenta
        MULTICOOKER_RECIPES.addRecipe {
            inputs(BARE_CORN_KERNEL.getStack(8))
            fluidInputs(DistilledWater.getFluid(1000))
            fluidOutputs(Polenta.getFluid(250))
            EUt(VA[LV])
            duration(2 * SECOND)
        }

        CANNER_RECIPES.addRecipe {
            input(CERAMIC_BOWL)
            fluidInputs(Polenta.getFluid(250))
            output(POLENTA)
            EUt(VH[LV])
            duration(4 * SECOND)
        }
    }

    // @formatter:on

}