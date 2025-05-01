package magicbook.gtlitecore.loader.recipe.chain.food

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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MULTICOOKER_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SLICER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Butter
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GRAHAM_CRACKER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SLICER_BLADE_FLAT
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.GRAHAM_CRACKER_CHUNK
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.GRAHAM_CRACKER_DOUGH
import magicbook.gtlitecore.common.item.GTLiteMetaOreDictItems.Companion.GRAHAM_CRACKER_SLICE

@Suppress("MISSING_DEPENDENCY_CLASS")
class GrahamCrackersChain
{

    companion object
    {

        fun init()
        {
            // Graham Cracker Dough
            MULTICOOKER_RECIPES.recipeBuilder()
                .input(dust, Sugar)
                .input(dust, Wheat, 3)
                .input(dustSmall, SodiumBicarbonate)
                .fluidInputs(Butter.getFluid(2000))
                .fluidInputs(Milk.getFluid(500))
                .outputs(GRAHAM_CRACKER_DOUGH.getItemStack(6))
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Graham Cracker Dough -> Graham Cracker Chunk
            ModHandler.addSmeltingRecipe(GRAHAM_CRACKER_DOUGH.itemStack,
                GRAHAM_CRACKER_CHUNK.itemStack)

            // Graham Cracker Chunk -> Graham Cracker Slice
            SLICER_RECIPES.recipeBuilder()
                .notConsumable(SLICER_BLADE_FLAT)
                .inputs(GRAHAM_CRACKER_CHUNK.itemStack)
                .outputs(GRAHAM_CRACKER_SLICE.getItemStack(9))
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Graham Cracker Slice -> Graham Cracker
            ROASTER_RECIPES.recipeBuilder()
                .inputs(GRAHAM_CRACKER_SLICE.itemStack)
                .output(GRAHAM_CRACKER)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

        }

    }

}