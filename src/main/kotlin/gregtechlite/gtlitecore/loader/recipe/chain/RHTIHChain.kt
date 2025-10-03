package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Ethenone
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Phenol
import gregtech.api.unification.material.Materials.PhosphoricAcid
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Diethylthiourea
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HassiumTetrachloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HexafluorophosphoricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenPeroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Isophthaloylbisdiethylthiourea
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhenylenedioxydiaceticAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RheniumPentachloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumCyanide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumThiocyanate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThalliumChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThionylChloride

object RHTIHChain
{

    // @formatter:off

    fun init()
    {
        // Tl + Cl -> TlCl
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Thallium)
            .fluidInputs(Chlorine.getFluid(1000))
            .output(dust, ThalliumChloride, 2)
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // Hs + 4Cl -> HsCl4
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(dust, MetastableHassium)
            .fluidInputs(Chlorine.getFluid(4000))
            .output(dust, HassiumTetrachloride, 5)
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Re + 5Cl -> ReCl5
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(dust, Rhenium)
            .fluidInputs(Chlorine.getFluid(5000))
            .output(dust, RheniumPentachloride, 6)
            .EUt(VA[MV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // H3PO4 + 6HF -> HPF6 + 4H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(PhosphoricAcid.getFluid(1000))
            .fluidInputs(HydrofluoricAcid.getFluid(6000))
            .fluidOutputs(HexafluorophosphoricAcid.getFluid(1000))
            .fluidOutputs(Water.getFluid(4000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // NaCN + S -> NaSCN
        BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, SodiumCyanide, 3)
                .input(dust, Sulfur)
                .output(dust, SodiumThiocyanate, 4)
                .EUt(VA[EV])
                .duration(6 * SECOND)
                .buildAndRegister()

        // C2H4 + NH3 -> C2H5NH2
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(Ethylene.getFluid(1000))
            .fluidInputs(Ammonia.getFluid(1000))
            .fluidOutputs(Ethylamine.getFluid(1000))
            .EUt(VA[HV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // NaSCN + HCl + 2C2H5NH2 -> NaCl + (C2H5NH)2CS + NH3 (cycle)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumThiocyanate, 4)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Ethylamine.getFluid(2000))
                .output(dust, Salt, 2)
                .fluidOutputs(Diethylthiourea.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .EUt(VA[LuV])
                .duration(7 * SECOND)
                .buildAndRegister()

        // C6H6O + H2O2 + 2C2H2O + 2O -> C10H10O6 + H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .fluidInputs(Phenol.getFluid(1000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidInputs(Ethenone.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(PhenylenedioxydiaceticAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[UV])
                .duration(17 * SECOND + 10)
                .buildAndRegister()

        // 2(C2H5NH)2CS + 2SOCl2 + C10H10O6 -> C18H26N4O2S2 + 4HCl + 2SO2
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(Diethylthiourea.getFluid(2000))
                .fluidInputs(ThionylChloride.getFluid(2000))
                .fluidInputs(PhenylenedioxydiaceticAcid.getFluid(1000))
                .fluidOutputs(Isophthaloylbisdiethylthiourea.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(4000))
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .EUt(VA[UEV])
                .duration(4 * SECOND)
                .buildAndRegister()

        // TlCl + HsCl4 + ReCl5 + 3C18H26N4O2S2 + HPF6 -> C60H84O12N12S6F6PReHsTl + 7HCl + 3Cl
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .input(dust, ThalliumChloride, 2)
            .input(dust, HassiumTetrachloride, 5)
            .input(dust, RheniumPentachloride, 6)
            .fluidInputs(Isophthaloylbisdiethylthiourea.getFluid(3000))
            .fluidInputs(HexafluorophosphoricAcid.getFluid(1000))
            .output(dust, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 125)
            .fluidOutputs(HydrochloricAcid.getFluid(7000))
            .fluidOutputs(Chlorine.getFluid(3000))
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}