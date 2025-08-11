package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.BREWING_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Acetone
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.CalciumChloride
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Dichlorobenzene
import gregtech.api.unification.material.Materials.DilutedHydrochloricAcid
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bistrichloromethylbenzene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Butanediol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GammaButyrolactone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kevlar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methylamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NMethylPyrrolidone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ParaPhenylenediamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ParaXylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TerephthalicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TerephthaloylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetrabromoethane
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object KevlarChain
{

    // @formatter:off

    fun init()
    {
        nMethylPyrrolidoneProcess()
        terephthalicAcidProcess()
        kevlarProcess()
    }

    private fun nMethylPyrrolidoneProcess()
    {
        // C4H10O2 -> C4H6O2 + 4H (drop)
        BREWING_RECIPES.recipeBuilder()
            .input(dust, Copper)
            .fluidInputs(Butanediol.getFluid(1000))
            .fluidOutputs(GammaButyrolactone.getFluid(1000))
            .EUt(VA[EV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // CH3NH2 + C4H6O2 -> C5H9NO + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Methylamine.getFluid(1000))
            .fluidInputs(GammaButyrolactone.getFluid(1000))
            .fluidOutputs(NMethylPyrrolidone.getFluid(1000))
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[IV])
            .duration(12 * SECOND)
            .buildAndRegister()
    }

    private fun terephthalicAcidProcess()
    {
        // Amoco Process for Terephthalic Acid.

        // 4Br + C2H2 -> C2H2Br4
        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .fluidInputs(Bromine.getFluid(4000))
            .fluidInputs(Acetylene.getFluid(1000))
            .fluidOutputs(Tetrabromoethane.getFluid(1000))
            .EUt(VA[HV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // C6H4(CH3)2 + 6O -> C6H4(CO2H)2 + 2H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, Manganese)
            .notConsumable(dust, Cobalt)
            .input(foil, Titanium, 10)
            .notConsumable(Acetone.getFluid(1))
            .fluidInputs(ParaXylene.getFluid(1000))
            .fluidInputs(Tetrabromoethane.getFluid(50))
            .fluidInputs(Air.getFluid(12000))
            .output(dust, TerephthalicAcid, 3)
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[ZPM])
            .duration(12 * SECOND)
            .buildAndRegister()

        // C6H4(CH3)2 + 6O -> C6H4(CO2H)2 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(ParaXylene.getFluid(1000))
            .fluidInputs(Chlorine.getFluid(6000))
            .fluidOutputs(Bistrichloromethylbenzene.getFluid(1000))
            .fluidOutputs(Hydrogen.getFluid(6000))
            .EUt(VA[EV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // C6H4(CCl3)2 + C6H4(CO2H)2 -> 2C6H4(COCl)2 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, TerephthalicAcid, 3)
            .fluidInputs(Bistrichloromethylbenzene.getFluid(1000))
            .output(dust, TerephthaloylChloride, 6)
            .fluidOutputs(HydrochloricAcid.getFluid(2000))
            .EUt(VH[HV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    private fun kevlarProcess()
    {
        // C6H4(NH2)2 + C6H4(COCl)2 -> (C6H4)2(CO)2(NH)2 + 2HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, CalciumChloride) // as catalyst
            .input(dust, ParaPhenylenediamine, 8)
            .input(dust, TerephthaloylChloride, 3)
            .fluidInputs(NMethylPyrrolidone.getFluid(100))
            .fluidInputs(SulfuricAcid.getFluid(500))
            .fluidOutputs(Kevlar.getFluid(L * 4))
            .fluidOutputs(HydrochloricAcid.getFluid(1000))
            .EUt(VA[UV])
            .duration(20 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // C6H4Cl2 + C6H4(CO2H)2 + 2NH3 -> (C6H4)2(CO)2(NH)2 + 2(HCl)(H2O) => 2HCl + 2H2O
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .notConsumable(dust, CalciumChloride)
            .input(dust, TerephthalicAcid, 3)
            .fluidInputs(Dichlorobenzene.getFluid(1000))
            .fluidInputs(Ammonia.getFluid(2000))
            .notConsumable(SulfuricAcid.getFluid(1))
            .fluidOutputs(Kevlar.getFluid(L * 8))
            .fluidOutputs(DilutedHydrochloricAcid.getFluid(4000))
            .EUt(VA[UEV])
            .duration(40 * SECOND)
            .buildAndRegister()

        // Addition recipe of CaCl2, because this compound used for this chain.

        // Ca + 2Cl -> CaCl2
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Calcium)
            .fluidInputs(Chlorine.getFluid(2000))
            .output(dust, CalciumChloride, 3)
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}