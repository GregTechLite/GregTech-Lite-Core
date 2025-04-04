package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.PalladiumRaw
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumAcetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumNitrate
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class PalladiumAcetateChain
{

    companion object
    {

        fun init()
        {
            // Pd + 2HNO3 -> Pd(NO3)2 + 2H
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Palladium)
                .fluidInputs(NitricAcid.getFluid(2000))
                .output(dust, PalladiumNitrate, 9)
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // PdCl2 + 2HNO3 -> Pd(NO3)2 + 2HCl
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, PalladiumRaw, 3)
                .fluidInputs(NitricAcid.getFluid(2000))
                .output(dust, PalladiumNitrate, 9)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Pd(NO3)2 + 2CH3COOH -> Pd(CH3COOH)2 + 2HNO3
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PalladiumNitrate, 9)
                .fluidInputs(AceticAcid.getFluid(2000))
                .output(dust, PalladiumAcetate, 15)
                .fluidOutputs(NitricAcid.getFluid(2000))
                .EUt(VA[EV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

    }

}