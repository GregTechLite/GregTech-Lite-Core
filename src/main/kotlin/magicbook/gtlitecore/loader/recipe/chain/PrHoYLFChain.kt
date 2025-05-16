package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Beryllium
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Praseodymium
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumBifluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumFluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BerylliumDifluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EDTA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HolmiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumFluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PrHoYLF
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PrHoYNitratesSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PraseodymiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YttriumOxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class PrHoYLFChain
{

    companion object
    {

        fun init()
        {
            // NH3 + HF -> NH4F
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(HydrofluoricAcid.getFluid(1000))
                .output(dust, AmmoniumFluoride, 6)
                .EUt(VA[HV].toLong())
                .duration(8 * SECOND + 10 * TICK)
                .buildAndRegister()

            // 2NH4F -> NH4HF2 + NH3
            CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, AmmoniumFluoride, 12)
                .output(dust, AmmoniumBifluoride, 8)
                .fluidOutputs(Ammonia.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(17 * SECOND)
                .buildAndRegister()

            // Pr2O3 + Ho2O3 + 3Y2O3 + 30HNO3 -> (Pr(NO3)3)2(Ho(NO3)3)2(Y(NO3)3)6(H2O)15
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, PraseodymiumOxide, 5)
                .input(dust, HolmiumOxide, 5)
                .input(dust, YttriumOxide, 15)
                .fluidInputs(NitricAcid.getFluid(30000))
                .fluidOutputs(PrHoYNitratesSolution.getFluid(30000))
                .EUt(VA[ZPM].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Be + LiF + 2NH4HF2 + 1/15(Pr(NO3)3)2(Ho(NO3)3)2(Y(NO3)3)6(H2O)15 + CO -> 2Pr/Ho:YLF + BeF2 + 2NH4NO3 + 2HF + CO2
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .input(dust, Beryllium)
                .input(dust, LithiumFluoride, 2)
                .input(dust, AmmoniumBifluoride, 16)
                .notConsumable(EDTA.getFluid(1))
                .fluidInputs(PrHoYNitratesSolution.getFluid(2000))
                .fluidInputs(CarbonMonoxide.getFluid(1000))
                .output(dust, PrHoYLF, 2)
                .output(dust, BerylliumDifluoride, 3)
                .output(dust, AmmoniumNitrate, 18)
                .fluidOutputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(VA[UV].toLong())
                .duration(20 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }

    }

}