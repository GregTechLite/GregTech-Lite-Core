package gregtechlite.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.Pollucite
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Alumina
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CaesiumHexachlorotinate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyAlkaliChloridesSolution
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RubidiumHexachlorotinate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TinDichloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TinTetrachloride
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

/**
 * Produces Caesium and Rubidium from Pollucite.
 * - Main Products: Caesium, Rubidium.
 * - Side Products: Alumina, Silicon Dioxide.
 *
 * A raw product estimations for these Ore Processing:
 * - 10 Pollucite -> 2 Caesium + 1 Rubidium
 */
internal object CaesiumRubidiumProcessing
{

    // @formatter:off

    fun init()
    {
        // Another processing of Pollucite (Cs2Al2Si4(H2O)2O12) is original electrolyzer,
        // player can extract caesium and other part of components by this processing.

        // Cs2Al2Si4(H2O)2O12 + 3HCl -> (RbCl)(CsCl)2(H2O)2 + 0.25Al2O3 + 1.6SiO2 + O (lost)
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, Pollucite, 11)
            .fluidInputs(HydrochloricAcid.getFluid(3000))
            .output(dust, Alumina)
            .output(dust, SiliconDioxide, 4)
            .fluidOutputs(HeavyAlkaliChloridesSolution.getFluid(1000))
            .EUt(VA[EV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // 2(RbCl)(CsCl)2(H2O)2 + 3SnCl4 -> Rb2SnCl6 + 2Cs2SnCl6 + 4H2O
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(dust, TinTetrachloride, 15)
            .fluidInputs(HeavyAlkaliChloridesSolution.getFluid(2000))
            .output(dust, RubidiumHexachlorotinate, 9)
            .output(dust, CaesiumHexachlorotinate, 18)
            .fluidOutputs(Ice.getFluid(4000))
            .EUt(VA[EV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // SnCl2 and SnCl4 recipes and convert recipes.

        // Sn + 2Cl -> SnCl2
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Tin)
            .fluidInputs(Chlorine.getFluid(2000))
            .output(dust, TinDichloride, 3)
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Sn + 4Cl -> SnCl4
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(dust, Tin)
            .fluidInputs(Chlorine.getFluid(4000))
            .output(dust, TinTetrachloride, 5)
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // SnCl2 + 2Cl -> SnCl4
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, TinDichloride, 3)
            .fluidInputs(Chlorine.getFluid(2000))
            .output(dust, TinTetrachloride, 5)
            .EUt(VA[HV])
            .duration(15 * TICK)
            .buildAndRegister()

        // SnCl4 + 2H -> SnCl2 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, TinTetrachloride, 5)
            .fluidInputs(Hydrogen.getFluid(2000))
            .output(dust, TinDichloride, 3)
            .fluidOutputs(HydrochloricAcid.getFluid(2000))
            .EUt(VA[HV])
            .duration(15 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}