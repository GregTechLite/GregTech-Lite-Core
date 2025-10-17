package gregtechlite.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Sphalerite
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Zinc
import gregtech.api.unification.material.Materials.Zincite
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GermaniumDioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RoastedSphalerite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WaelzOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WaelzSlag
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZincRichSphalerite

/**
 * Produces Germanium from Sphalerite.
 * - Main Products: Germanium.
 * - Side Products: Zinc, Gallium, Manganese.
 *
 * A raw product estimations for these Ore Processing:
 * - 2 Sphalerite -> 1 Germanium
 */
internal object GermaniumZincProcessing
{

    // @formatter:off

    fun init()
    {
        // Waelz processing of sphalerite.

        // ZnS + 5O -> (GeO2)? + ZnO + SO2
        ROASTER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Sphalerite, 2)
            .fluidInputs(Oxygen.getFluid(5000))
            .output(dust, RoastedSphalerite, 4)
            .output(dust, Zincite, 2)
            .fluidOutputs(SulfurDioxide.getFluid(1000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // (GeO2)? + 2Zn -> Zn2(GaGeO2)
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, RoastedSphalerite, 4)
            .input(dust, Zinc, 2)
            .output(dust, ZincRichSphalerite, 6)
            .EUt(VA[LV])
            .duration(16 * SECOND)
            .buildAndRegister()

        // Zn2(GaGeO2) + H2SO4 -> (GeO2)Zn + (ZnSO4)Ga + 2H (lost)
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, ZincRichSphalerite, 6)
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .output(dust, WaelzOxide, 4)
            .output(dust, WaelzSlag, 7)
            .EUt(VA[EV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // (GeO2)Zn + H2SO4 -> GeO2 + (ZnSO4)Ga + 2H (lost) + (Cd)
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, WaelzOxide, 4)
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .output(dust, GermaniumDioxide, 3)
            .output(dust, WaelzSlag, 7)
            .chancedOutput(dust, Cadmium, 500, 1000)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // GeO2 + 4H -> Ge + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, GermaniumDioxide, 3)
            .fluidInputs(Hydrogen.getFluid(4000))
            .output(dust, Germanium)
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[EV])
            .duration(12 * SECOND)
            .buildAndRegister()

        // GeO2 + C -> Ge + CO2
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, GermaniumDioxide, 3)
            .input(dust, Carbon)
            .output(dust, Germanium)
            .fluidOutputs(CarbonDioxide.getFluid(1000))
            .EUt(VA[EV])
            .duration(36 * SECOND)
            .buildAndRegister()

        // (ZnSO4)Ga + H2O -> ZnO + H2SO4 (cycle) + (Ga)
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, WaelzSlag, 7)
            .fluidInputs(Water.getFluid(1000))
            .output(dust, Zincite, 2)
            .chancedOutput(dust, Gallium, 2000, 1000)
            .fluidOutputs(SulfuricAcid.getFluid(1000))
            .EUt(VA[HV])
            .duration(8 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}