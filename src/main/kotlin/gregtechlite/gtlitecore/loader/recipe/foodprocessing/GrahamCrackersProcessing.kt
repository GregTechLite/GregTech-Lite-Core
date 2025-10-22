package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.unification.material.Materials.Milk
import gregtech.api.unification.material.Materials.SodiumBicarbonate
import gregtech.api.unification.material.Materials.Sugar
import gregtech.api.unification.material.Materials.Wheat
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.getStack
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MULTICOOKER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SLICER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butter
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAHAM_CRACKER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SLICER_BLADE_FLAT
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.GRAHAM_CRACKER_CHUNK
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.GRAHAM_CRACKER_DOUGH
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.GRAHAM_CRACKER_SLICE

internal object GrahamCrackersProcessing
{

    // @formatter:off

    fun init()
    {
        // Graham Cracker Dough
        MULTICOOKER_RECIPES.recipeBuilder()
            .input(dust, Sugar)
            .input(dust, Wheat, 3)
            .input(dustSmall, SodiumBicarbonate)
            .fluidInputs(Butter.getFluid(2000))
            .fluidInputs(Milk.getFluid(500))
            .outputs(GRAHAM_CRACKER_DOUGH.getStack(6))
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Graham Cracker Dough -> Graham Cracker Chunk
        ModHandler.addSmeltingRecipe(GRAHAM_CRACKER_DOUGH.stack(),
            GRAHAM_CRACKER_CHUNK.stack())

        // Graham Cracker Chunk -> Graham Cracker Slice
        SLICER_RECIPES.recipeBuilder()
            .notConsumable(SLICER_BLADE_FLAT)
            .inputs(GRAHAM_CRACKER_CHUNK.stack())
            .outputs(GRAHAM_CRACKER_SLICE.getStack(9))
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Graham Cracker Slice -> Graham Cracker
        ModHandler.addSmeltingRecipe(GRAHAM_CRACKER_SLICE.stack(), GRAHAM_CRACKER.stackForm)

    }

    // @formatter:on

}