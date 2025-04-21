package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DimethylTerephthalate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthyleneGlycol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Methylparatoluate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ParaToluicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ParaXylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolyethyleneTerephthalate
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class PETChain
{

    companion object
    {

        fun init()
        {
            // C6H4(CH3)2 + 2O -> C8H8O2 + 2H
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(ParaXylene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(ParaToluicAcid.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // C8H8O2 + CH4O -> C9H10O2 + H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(ParaToluicAcid.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(Methylparatoluate.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // C9H10O2 + CO2 -> C10H10O4
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Methylparatoluate.getFluid(1000))
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(DimethylTerephthalate.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

            // C10H10O4 + C2H6O2 -> 2C10H6O4 + 2H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(DimethylTerephthalate.getFluid(2592))
                .fluidInputs(EthyleneGlycol.getFluid(1000))
                .fluidOutputs(PolyethyleneTerephthalate.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(VA[UV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()
        }

    }

}