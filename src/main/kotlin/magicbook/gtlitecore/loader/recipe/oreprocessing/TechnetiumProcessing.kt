package magicbook.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumPertechnetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pertechnetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TechnetiumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TechnetiumHeptaoxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class TechnetiumProcessing
{

    companion object
    {

        fun init()
        {
            // Tc2O7 + H2O -> HTcO4
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, TechnetiumHeptaoxide, 9)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Pertechnetate.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // NH3 + HNO3 -> NH4NO3
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(1000))
                .output(dust, AmmoniumNitrate, 2) // Special count like NH4Cl.
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // HTcO4 + NH4NO3 -> NH4TcO4 + HNO3
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, AmmoniumNitrate, 2)
                .fluidInputs(Pertechnetate.getFluid(1000))
                .output(dust, AmmoniumPertechnetate, 10)
                .fluidOutputs(NitricAcid.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // 2NH4TcO4 -> 2TcO2 + 4H2O + 2N (drop)
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .input(dust, AmmoniumPertechnetate, 20)
                .output(dust, TechnetiumDioxide, 6)
                .fluidOutputs(Water.getFluid(4000))
                .EUt(VA[IV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // TcO2 -> Tc + 2O by electrolysis decomposition.

        }

    }

}