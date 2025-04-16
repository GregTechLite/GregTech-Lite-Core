package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Ethenone
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrogenSulfide
import gregtech.api.unification.material.Materials.MethylAcetate
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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYOGENIC_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AceticAnhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dibromomethylbenzene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dinitrodipropanyloxybenzene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenPeroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Isochloropropane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ParaXylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PretreatedZylon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resorcinol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumAcetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Terephthalaldehyde
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Zylon
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class ZylonChain
{

    companion object
    {

        fun init()
        {
            // C3H6 + HCl -> CH3CHClCH3
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(Propene.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Isochloropropane.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(24 * SECOND)
                .buildAndRegister()

            // 2C2H4O2 -> C4H6O3 + H2O
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(AceticAcid.getFluid(2000))
                .fluidOutputs(AceticAnhydride.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[MV].toLong())
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
                .EUt(VA[UV].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // C2H3NaO2 -> NaOH + C2H2O
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(SodiumAcetate.getFluid(1000))
                .output(dust, SodiumHydroxide, 3)
                .fluidOutputs(Ethenone.getFluid(1000))
                .EUt(VA[LV].toLong())
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
                .EUt(VA[IV].toLong())
                .duration(21 * SECOND + 10 * TICK)
                .buildAndRegister()

            // C7H8 + 2Br -> C7H6Br2
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(Bromine.getFluid(2000))
                .fluidOutputs(Dibromomethylbenzene.getFluid(1000))
                .EUt(VA[ZPM].toLong())
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
                .EUt(VA[LuV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // C8H6O2 + C12H16O2(NO2)2 -> C20H22N2O2 + 6O
            CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Palladium)
                .input(dust, Terephthalaldehyde, 16)
                .fluidInputs(Dinitrodipropanyloxybenzene.getFluid(1000))
                .output(dust, PretreatedZylon, 46)
                .fluidOutputs(Oxygen.getFluid(6000))
                .EUt(VA[UHV].toLong())
                .duration(25 * SECOND + 10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // C20H22N2O2 -> C14H6N2O2 + 2C3H6
            ROASTER_RECIPES.recipeBuilder()
                .input(dust, PretreatedZylon, 46)
                .output(dust, Zylon, 24)
                .fluidOutputs(Propane.getFluid(2000))
                .EUt(VA[IV].toLong())
                .duration(35 * SECOND)
                .buildAndRegister() // Smelting to ingot or extracting to fluid.
        }

    }

}