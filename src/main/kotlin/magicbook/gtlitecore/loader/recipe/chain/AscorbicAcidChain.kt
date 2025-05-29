package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.spring
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.VACUUM_CHAMBER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AscorbicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DehydroascorbicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Sorbose
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class AscorbicAcidChain
{

    companion object
    {

        fun init()
        {
            // C6H12O6 -> C6H8O6
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .notConsumable(spring, Platinum)
                .input(dust, Sorbose, 24)
                .fluidOutputs(AscorbicAcid.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(14 * SECOND)
                .buildAndRegister()

            // C6H6O6 + 2H -> C6H8O6
            VACUUM_CHAMBER_RECIPES.recipeBuilder()
                .notConsumable(spring, Nickel)
                .fluidInputs(DehydroascorbicAcid.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(AscorbicAcid.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(7 * SECOND)
                .buildAndRegister()

        }

    }

}