package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Ethenone
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrogenSulfide
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Propane
import gregtech.api.unification.material.Materials.Propene
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Toluene
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AceticAnhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dibromomethylbenzene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dinitrodipropanyloxybenzene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenPeroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Isochloropropane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PalladiumLoadedRutileNanoparticles
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ParaXylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PretreatedZylon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Resorcinol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumAcetate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Terephthalaldehyde
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Zylon
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt

internal object ZylonChain
{

    // @formatter:off

    fun init()
    {
        // C3H6 + HCl -> CH3CHClCH3
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .fluidInputs(Propene.getFluid(1000))
            .fluidInputs(HydrochloricAcid.getFluid(1000))
            .fluidOutputs(Isochloropropane.getFluid(1000))
            .EUt(VA[HV])
            .duration(24 * SECOND)
            .buildAndRegister()

        // 2C2H4O2 -> C4H6O3 + H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .fluidInputs(AceticAcid.getFluid(2000))
            .fluidOutputs(AceticAnhydride.getFluid(1000))
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[MV])
            .duration(12 * SECOND)
            .buildAndRegister()

        // Na2O + C6H6O2 + CH3CHClCH3 + C4H6O3 + 2HNO3 + C3H6 -> C12H16O2(NO2)2 + NaCl + 2H2O + C2H4O2 + C2H3NaO2
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .input(dust, SodiumOxide, 3)
            .fluidInputs(Resorcinol.getFluid(1000))
            .fluidInputs(Isochloropropane.getFluid(1000))
            .fluidInputs(AceticAnhydride.getFluid(1000))
            .fluidInputs(NitricAcid.getFluid(2000))
            .fluidInputs(Propene.getFluid(1000))
            .output(dust, Salt, 2)
            .fluidOutputs(Dinitrodipropanyloxybenzene.getFluid(1000))
            .fluidOutputs(Water.getFluid(2000))
            .fluidOutputs(AceticAcid.getFluid(1000))
            .fluidOutputs(SodiumAcetate.getFluid(1000))
            .EUt(VA[UV])
            .duration(25 * SECOND)
            .buildAndRegister()

        // C2H3NaO2 -> NaOH + C2H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .fluidInputs(SodiumAcetate.getFluid(1000))
            .output(dust, SodiumHydroxide, 3)
            .fluidOutputs(Ethenone.getFluid(1000))
            .EUt(VA[LV])
            .duration(4 * SECOND + 5 * TICK)
            .buildAndRegister()

        // C6H4(CH3)2 + 2Br + 2O -> C7H6Br2 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(ParaXylene.getFluid(1000))
            .fluidInputs(Bromine.getFluid(2000))
            .fluidInputs(Oxygen.getFluid(2000))
            .fluidOutputs(Dibromomethylbenzene.getFluid(1000))
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[IV])
            .duration(21 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C7H8 + 2Br -> C7H6Br2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .fluidInputs(Toluene.getFluid(1000))
            .fluidInputs(Bromine.getFluid(2000))
            .fluidOutputs(Dibromomethylbenzene.getFluid(1000))
            .EUt(VA[ZPM])
            .duration(10 * SECOND + 15 * TICK)
            .buildAndRegister()

        // C7H6Br2 + H2SO4 -> C8H6O2 + 2Br + HS + H2O2
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(Dibromomethylbenzene.getFluid(1000))
            .fluidInputs(SulfuricAcid.getFluid(1000))
            .output(dust, Terephthalaldehyde, 16)
            .fluidOutputs(Bromine.getFluid(2000))
            .fluidOutputs(HydrogenSulfide.getFluid(1000))
            .fluidOutputs(HydrogenPeroxide.getFluid(1000))
            .EUt(VA[LuV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // C8H6O2 + C12H16O2(NO2)2 -> C20H22N2O2 + 6O
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, Palladium)
            .input(dust, Terephthalaldehyde, 16)
            .fluidInputs(Dinitrodipropanyloxybenzene.getFluid(1000))
            .output(dust, PretreatedZylon, 46)
            .fluidOutputs(Oxygen.getFluid(6000))
            .EUt(VA[UHV])
            .duration(25 * SECOND + 10 * TICK)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // C20H22N2O2 -> C14H6N2O2 + 2C3H6
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, PretreatedZylon, 46)
            .output(dust, Zylon, 24)
            .fluidOutputs(Propane.getFluid(2000))
            .EUt(VA[IV])
            .duration(35 * SECOND)
            .buildAndRegister() // Smelting to ingot or extracting to fluid.

        // Advanced recipes for C14H6N2O2
        // C6H6O2 + C6H4(CH3)2 + 2HNO3 -> C14H6N2O2 + 6H2O
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .notConsumable(dust, PalladiumLoadedRutileNanoparticles)
            .fluidInputs(Resorcinol.getFluid(1000))
            .fluidInputs(ParaXylene.getFluid(1000))
            .fluidInputs(NitricAcid.getFluid(2000))
            .output(dust, Zylon, 24)
            .fluidOutputs(Water.getFluid(6000))
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    // @formatter:on

}