package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Benzene
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chlorobenzene
import gregtech.api.unification.material.Materials.Chloroform
import gregtech.api.unification.material.Materials.DilutedHydrochloricAcid
import gregtech.api.unification.material.Materials.Dimethylamine
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.HydrogenSulfide
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Propene
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Styrene
import gregtech.api.unification.material.Materials.TitaniumTetrachloride
import gregtech.api.unification.material.Materials.Toluene
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYOGENIC_REACTOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Alumina
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CalciumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dichloromethane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dicyclopentadiene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Diisopropylcarbodiimide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DimethylSulfide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dimethylaminopyridine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ferrocene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ferrocenylfulleropyrddolidine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fullerene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FullerenePolymerMatrix
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenPeroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Iron2Chloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IsopropylAlcohol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Methylamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ozone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumFullereneMatrix
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhenylC61ButyricAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhenylC61ButyricStyrene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhenylpentanoicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phosgene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhthalicAnhydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pyridine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Sarcosine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumEthoxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Triphenylphosphine
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class FullerenePolymerMatrixChain
{

    companion object
    {

        fun init()
        {
            palladiumFullereneMatrixProcess()
            pcbsProcess()
            fpmProcess()
        }

        private fun palladiumFullereneMatrixProcess()
        {
            // CH3NH2 + 2Cl + C2H4O2 -> C3H7NO2 + 2HCl
            CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                .notConsumable(dust, PhthalicAnhydride)
                .fluidInputs(Methylamine.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidInputs(AceticAcid.getFluid(1000))
                .output(dust, Sarcosine, 13)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(VA[EV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // C2H6O + NaOH -> C2H5ONa + H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(Ethanol.getFluid(1000))
                .output(dust, SodiumEthoxide, 9)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Fe + 2HCl -> FeCl2 + 2H
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Iron)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(Iron2Chloride.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // FeCl2 + C10H12 -> C10H10Fe + 2HCl
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Iron2Chloride.getFluid(1000))
                .fluidInputs(Dicyclopentadiene.getFluid(1000))
                .fluidOutputs(Ferrocene.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(VA[IV].toLong())
                .duration(4 * SECOND + 12 * TICK)
                .buildAndRegister()

            // C60 + C3H7NO2 + 3C2H5ONa + C10H10Fe + CHCl3 + CH4 -> C74H19NFe + 3NaCl + CO2 + 3C2H6O
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Fullerene) // C60
                .input(dust, Sarcosine, 13) // C3H7NO2
                .input(dust, SodiumEthoxide, 27) // C2H5ONa
                .fluidInputs(Ferrocene.getFluid(1000)) // C10H10Fe
                .fluidInputs(Chloroform.getFluid(1000)) // CHCl3
                .fluidInputs(Methane.getFluid(1000)) // CH4
                .notConsumable(TitaniumTetrachloride.getFluid(1))
                .notConsumable(Toluene.getFluid(1))
                .output(dust, Salt, 6) // NaCl
                .fluidOutputs(Ferrocenylfulleropyrddolidine.getFluid(1000)) // C74H19NFe
                .fluidOutputs(CarbonDioxide.getFluid(1000)) // CO2
                .fluidOutputs(Ethanol.getFluid(3000)) // C2H6O
                .EUt(VA[UEV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Pd + C74H19NFe -> (C73H15NFe)Pd + CH4
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Palladium)
                .fluidInputs(Ferrocenylfulleropyrddolidine.getFluid(1000))
                .notConsumable(AceticAcid.getFluid(1))
                .notConsumable(NitricAcid.getFluid(1))
                .output(dust, PalladiumFullereneMatrix)
                .EUt(VA[UV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Advanced recipes of (C73H15NFe)Pd.
            // C60 + Pd + C3H7NO2 + C10H10Fe -> (C73H15NFe)Pd + H2O2
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .notConsumable(dust, Alumina)
                .notConsumable(dust, SodiumEthoxide)
                .input(dust, Fullerene)
                .input(dust, Palladium)
                .input(dust, SodiumEthoxide, 13)
                .fluidInputs(Ferrocene.getFluid(1000))
                .notConsumable(AceticAcid.getFluid(1))
                .notConsumable(Toluene.getFluid(1))
                .notConsumable(TitaniumTetrachloride.getFluid(1))
                .output(dust, PalladiumFullereneMatrix)
                .fluidOutputs(HydrogenPeroxide.getFluid(1000))
                .EUt(VA[UXV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()
        }

        private fun pcbsProcess()
        {
            // C6H6 + C8H8 + CaCO3 -> C11H14O2 + CaC2 + CO + C
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Calcite, 5)
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Styrene.getFluid(1000))
                .output(dust, CalciumCarbide, 3)
                .chancedOutput(dust, Carbon, 1000, 0)
                .fluidOutputs(PhenylpentanoicAcid.getFluid(1000))
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // 2CH4O + H2S -> (CH3)2S + 2H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Methanol.getFluid(2000))
                .fluidInputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(DimethylSulfide.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // 2C60 + 2C11H14O2 + (CH3)2S + 2C6H5Cl + C2H4 -> 2C72H14O2 + 2C7H8 + 2HCl + H2S
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Fullerene, 2)
                .fluidInputs(PhenylpentanoicAcid.getFluid(2000))
                .fluidInputs(DimethylSulfide.getFluid(1000))
                .fluidInputs(Chlorobenzene.getFluid(2000))
                .fluidInputs(Ethylene.getFluid(1000))
                .notConsumable(Ozone.getFluid(1))
                .fluidOutputs(PhenylC61ButyricAcid.getFluid(2000))
                .fluidOutputs(Toluene.getFluid(2000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .EUt(VA[ZPM].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()

            // C2H7N + C5H5N -> (CH3)2NC5H4N
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Pyridine.getFluid(1000))
                .fluidInputs(Dimethylamine.getFluid(1000))
                .output(dust, Dimethylaminopyridine, 19)
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // C3H6 + H2O -> C3H8O
            MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Propene.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(IsopropylAlcohol.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // 2NH3 + COCl2 + 2C3H8O -> C7H14N2 + 2(HCl)(H2O)
            CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Triphenylphosphine)
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(Phosgene.getFluid(1000))
                .fluidInputs(IsopropylAlcohol.getFluid(2000))
                .fluidOutputs(Diisopropylcarbodiimide.getFluid(1000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(4000))
                .EUt(VA[IV].toLong())
                .duration(12 * SECOND + 10 * TICK)
                .buildAndRegister()

            // C72H14O2 + C8H8 + CH2Cl2 + NaOH -> C80H21O2 + NaCl + HCl + CO + 3H
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Dimethylaminopyridine)
                .input(dust, SodiumHydroxide, 3)
                .notConsumable(Diisopropylcarbodiimide.getFluid(1))
                .fluidInputs(PhenylC61ButyricAcid.getFluid(1000))
                .fluidInputs(Styrene.getFluid(1000))
                .fluidInputs(Dichloromethane.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(PhenylC61ButyricStyrene.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(3000))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // 8C60 + 7C8H8 + 8CH2Cl2 + 8C11H14O2 + 8(CH3)2S + 8C6H5Cl -> 8C80H21O2 + 8C7H8 + 8H2S + 24HCl
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .notConsumable(dust, Dimethylaminopyridine)
                .input(dust, Fullerene, 8)
                .fluidInputs(Styrene.getFluid(7000))
                .fluidInputs(Dichloromethane.getFluid(8000))
                .fluidInputs(PhenylpentanoicAcid.getFluid(8000))
                .fluidInputs(DimethylSulfide.getFluid(8000))
                .fluidInputs(Chlorobenzene.getFluid(8000))
                .fluidOutputs(PhenylC61ButyricStyrene.getFluid(8000))
                .fluidOutputs(Toluene.getFluid(8000))
                .fluidOutputs(HydrogenSulfide.getFluid(8000))
                .fluidOutputs(HydrochloricAcid.getFluid(24000))
                .EUt(VA[UXV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()
        }

        private fun fpmProcess()
        {
            // (C73H15NFe)Pd + C80H21O2 -> (C153H36NO2)PdFe
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, PalladiumFullereneMatrix)
                .fluidInputs(PhenylC61ButyricStyrene.getFluid(1000))
                .output(dust, FullerenePolymerMatrix, 2)
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }

    }

}