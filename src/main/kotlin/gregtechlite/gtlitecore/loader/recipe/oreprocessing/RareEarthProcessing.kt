package gregtechlite.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.VHA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Butane
import gregtech.api.unification.material.Materials.Butyraldehyde
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.PhosphorusPentoxide
import gregtech.api.unification.material.Materials.RareEarth
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CeriumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DiethylhexylPhosphoricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ErTmYbLuOxidesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylhexanol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LaPrNdCeOxidesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NeodymiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PromethiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RareEarthChloridesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RareEarthHydroxidesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SamariumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ScEuGdSmOxidesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.YTbDyHoOxidesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.YttriumOxide

/**
 * Produces Rare Earth Elements from Rare Earth dust.
 * - Main Products: All Rare Earth Elements (La, Ce, Pr, Nd, ...).
 * - Side Products: None.
 *
 * A raw product estimations for these ore processing:
 * - 4 Rare Earth -> 1 Every Rare Earth Element Oxides.
 */
internal object RareEarthProcessing
{

    // @formatter:off

    fun init()
    {
        // Remove original centrifuge recipe.
        GTRecipeHandler.removeRecipesByInputs(CENTRIFUGE_RECIPES,
            OreDictUnifier.get(dust, RareEarth))

        diethylhexylPhosphoricAcidProcess()
        rareEarthProcess()
    }

    private fun diethylhexylPhosphoricAcidProcess()
    {
        // 2C4H8O + 4H -> C8H18O + H2O (lost)
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Butyraldehyde.getFluid(2000))
            .fluidInputs(Hydrogen.getFluid(4000))
            .fluidOutputs(Ethylhexanol.getFluid(1000))
            .EUt(VA[MV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // 0.5P4O10 + 5C8H18O -> 2C16H35O4P + 2C4H10 + 2O (lost)
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, PhosphorusPentoxide, 7)
            .fluidInputs(Ethylhexanol.getFluid(5000))
            .fluidOutputs(DiethylhexylPhosphoricAcid.getFluid(2000))
            .fluidOutputs(Butane.getFluid(2000))
            .EUt(VH[LV])
            .duration(30 * SECOND)
            .buildAndRegister()
    }

    private fun rareEarthProcess()
    {
        // (RE) -> (REHS)
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, RareEarth)
            .input(dust, SodiumHydroxide, 3)
            .fluidInputs(DiethylhexylPhosphoricAcid.getFluid(100))
            .fluidInputs(Water.getFluid(900))
            .fluidOutputs(RareEarthHydroxidesSolution.getFluid(1000))
            .EUt(VA[HV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // (REHS) + HCl -> (RECS) + NaOH (cycle)
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(RareEarthHydroxidesSolution.getFluid(1000))
            .fluidInputs(HydrochloricAcid.getFluid(1000))
            .output(dust, SodiumHydroxide, 3)
            .fluidOutputs(RareEarthChloridesSolution.getFluid(1000))
            .EUt(VA[LV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Neodymium Processing for optional choice of EV stage, this reaction can
        // still be obtained as ore byproducts.
        DISTILLERY_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(RareEarthChloridesSolution.getFluid(1000))
            .output(dust, NeodymiumOxide)
            .fluidOutputs(HydrochloricAcid.getFluid(900))
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Cerium Processing for option choice of ZPM stage (Plutonium-241 Fusion).
        DISTILLERY_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .fluidInputs(RareEarthChloridesSolution.getFluid(1000))
            .output(dust, CeriumOxide)
            .fluidOutputs(HydrochloricAcid.getFluid(850))
            .EUt(VHA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Samarium Processing for option choice of IV-LuV stage (consists of
        // Samarium Magnetic and Alloy Component requirements).
        DISTILLERY_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .fluidInputs(RareEarthChloridesSolution.getFluid(1000))
            .output(dust, SamariumOxide)
            .fluidOutputs(HydrochloricAcid.getFluid(800))
            .EUt(VHA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Yttrium Processing for IV stage (Alloy Component of Incoloy-MA956).
        DISTILLERY_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .fluidInputs(RareEarthChloridesSolution.getFluid(1000))
            .output(dust, YttriumOxide)
            .fluidOutputs(HydrochloricAcid.getFluid(750))
            .EUt(VHA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Lanthanum Processing for optional choice of LuV and UV stage (consists of
        // americium fusion reaction, ZBLAN glass material components).
        DISTILLERY_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .fluidInputs(RareEarthChloridesSolution.getFluid(1000))
            .output(dust, LanthanumOxide)
            .fluidOutputs(HydrochloricAcid.getFluid(500))
            .EUt(VHA[LuV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // (RECS) -> (REEs)
        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(RareEarthChloridesSolution.getFluid(1000))
            .output(dust, PromethiumOxide)
            .fluidOutputs(LaPrNdCeOxidesSolution.getFluid(250))
            .fluidOutputs(ScEuGdSmOxidesSolution.getFluid(250))
            .fluidOutputs(YTbDyHoOxidesSolution.getFluid(250))
            .fluidOutputs(ErTmYbLuOxidesSolution.getFluid(250))
            .fluidOutputs(HydrochloricAcid.getFluid(1000))
            .EUt(VA[ZPM]) // Required double LuV Energy Hatches.
            .duration(10 * SECOND)
            .disableDistilleryRecipes()
            .buildAndRegister()

    }

    // @formatter:on

}