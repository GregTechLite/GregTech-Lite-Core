package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Glycerol
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.PotassiumDichromate
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CitricAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FormicAcid
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.LEMON

@Suppress("MISSING_DEPENDENCY_CLASS")
class CitricAcidChain
{

    companion object
    {

        fun init()
        {
            // C3H8O3 + HCOOH + 2CO -> C6H8O7 + 2H
            CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, PotassiumDichromate)
                .fluidInputs(Glycerol.getFluid(1000))
                .fluidInputs(FormicAcid.getFluid(1000))
                .fluidInputs(CarbonMonoxide.getFluid(2000))
                .fluidOutputs(CitricAcid.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(VA[EV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            // Raw processing of lemon. // TODO rebalanced it.
            EXTRACTOR_RECIPES.recipeBuilder()
                .input(LEMON)
                .fluidOutputs(CitricAcid.getFluid(10))
                .EUt(VA[HV].toLong())
                .duration(24 * SECOND)
                .buildAndRegister()
        }

    }

}