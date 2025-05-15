package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.BENDER_RECIPES
import gregtech.api.recipes.RecipeMaps.BREWING_RECIPES
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRUDER_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.recipes.RecipeMaps.PYROLYSE_RECIPES
import gregtech.api.unification.material.Materials.Acetone
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Benzene
import gregtech.api.unification.material.Materials.Biphenyl
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Butadiene
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.DilutedHydrochloricAcid
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iodine
import gregtech.api.unification.material.Materials.Isoprene
import gregtech.api.unification.material.Materials.Lanthanum
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.PalladiumRaw
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.RockSalt
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_FOIL
import gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYOGENIC_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.PLASMA_CVD_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Acetaldehyde
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Acetylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumPersulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumSulfate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Benzaldehyde
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bipyridine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BoronTrifluoride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BoronTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Butyllithium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CarbonNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cyclooctadiene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cycloparaphenylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dibenzylideneacetone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dichlorocyclooctadieneplatinium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dicyclopentadiene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Diiodobiphenyl
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Formaldehyde
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GeodesicPolyarene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HRAMagnesium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HexachloroplatinicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumEmbeddedFullerene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumFullereneMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LanthanumFullereneNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnetoResonatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MalonicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Octene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumBisdibenzylidieneacetone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumTetrachloroplatinate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pyridine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Resorcinol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SeaborgiumDopedCarbonNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SilverChloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SilverOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SilverTetrafluoroborate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TinDichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TrimethyltinChloride
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CARBON_ALLOTROPE_MIXTURE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GRAPHENE_ALIGNED_CNT

@Suppress("MISSING_DEPENDENCY_CLASS")
class NanotubesChain
{

    companion object
    {

        fun init()
        {
            bipyridineProcess()
            palladiumBisdibenzylidieneacetoneProcess()
            dichlorocyclooctadieneplatiniumProcess()
            cppProcess()
            cntProcess()
            seaborgiumCNTProcess()
            lanthanumFullereneNTProcess()
        }

        private fun bipyridineProcess()
        {
            // CH2O + 2C2H4O + NH3 -> C5H5N + 3H2O + 2H
            CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                .notConsumable(dust, Thallium)
                .fluidInputs(Formaldehyde.getFluid(1000))
                .fluidInputs(Acetaldehyde.getFluid(2000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Pyridine.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(VA[EV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            // 2C5H5N -> C10H8N2 + 2H
            BREWING_RECIPES.recipeBuilder()
                .notConsumable(dust, Aluminium)
                .fluidInputs(Pyridine.getFluid(2000))
                .output(dust, Bipyridine, 20)
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(VA[EV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }

        private fun palladiumBisdibenzylidieneacetoneProcess()
        {
            // Pd + 2Cl -> PdCl2
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Palladium)
                .fluidInputs(Chlorine.getFluid(2000))
                .output(dust, PalladiumRaw, 3)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // 2C7H6O + C3H6O -> C17H14O + 2H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Benzaldehyde.getFluid(2000))
                .fluidInputs(Acetone.getFluid(1000))
                .fluidOutputs(Dibenzylideneacetone.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // 2PdCl2 + 3C17H14O -> C51H42O3Pd2 + 4Cl
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PalladiumRaw, 6)
                .fluidInputs(Dibenzylideneacetone.getFluid(3000))
                .output(dust, PalladiumBisdibenzylidieneacetone, 64)
                .output(dust, PalladiumBisdibenzylidieneacetone, 34)
                .fluidOutputs(Chlorine.getFluid(4000))
                .EUt(VA[IV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()
        }

        private fun dichlorocyclooctadieneplatiniumProcess()
        {
            // 2C4H6 -> C8H12
            PYROLYSE_RECIPES.recipeBuilder()
                .notConsumable(dust, Nickel)
                .fluidInputs(Butadiene.getFluid(2000))
                .fluidOutputs(Cyclooctadiene.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // C10H12 -> C8H12 + 2C (drop)
            FLUID_HEATER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Dicyclopentadiene.getFluid(100))
                .fluidOutputs(Cyclooctadiene.getFluid(100))
                .EUt(VA[HV].toLong())
                .duration(12 * TICK)
                .buildAndRegister()

            // C5H8 + C3H4O4 -> C8H12 + 4O
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, MalonicAcid, 11)
                .fluidInputs(Isoprene.getFluid(1000))
                .fluidOutputs(Cyclooctadiene.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(4000))
                .EUt(VA[EV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // Pt + 2HCl + 4Cl -> H2PtCl6
            CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Platinum)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidOutputs(HexachloroplatinicAcid.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

            // K2 + H2PtCl6 -> K2PtCl4 + 2HCl
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Potassium, 2)
                .fluidInputs(HexachloroplatinicAcid.getFluid(1000))
                .output(dust, PotassiumTetrachloroplatinate, 7)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // K2PtCl4 + C8H12 -> C8H12Cl2Pt + 2KCl
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PotassiumTetrachloroplatinate, 7)
                .fluidInputs(Cyclooctadiene.getFluid(1000))
                .output(dust, Dichlorocyclooctadieneplatinium, 23)
                .output(dust, RockSalt, 4)
                .EUt(VA[HV].toLong())
                .duration(18 * SECOND)
                .buildAndRegister()
        }

        private fun cppProcess()
        {
            // (C6H5)2 + 2I + (NH4)2SO4 + H2SO4 -> C12H8I2 + (NH4)2S2O8 + 4H
            CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Biphenyl, 22)
                .input(dust, Iodine, 2)
                .input(dust, AmmoniumSulfate, 17)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, Diiodobiphenyl, 22)
                .output(dust, AmmoniumPersulfate, 20)
                .fluidOutputs(Hydrogen.getFluid(4000))
                .EUt(VA[HV].toLong())
                .duration(13 * SECOND)
                .buildAndRegister()

            // (NH4)2S2O8 + 2H -> (NH2)4SO4 + H2SO4 (cycle)
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, AmmoniumPersulfate, 20)
                .fluidInputs(Hydrogen.getFluid(2000))
                .output(dust, AmmoniumSulfate, 17)
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(6 * SECOND + 10 * TICK)
                .buildAndRegister()

            // SnCl2 + 3CH4 + O -> (CH3)3SnCl + (HCl)(H2O)
            CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, HRAMagnesium)
                .input(dust, TinDichloride, 3)
                .fluidInputs(Methane.getFluid(3000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(TrimethyltinChloride.getFluid(1000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(2000))
                .EUt(VA[EV].toLong())
                .duration(6 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Ag + Cl -> AgCl
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Silver)
                .fluidInputs(Chlorine.getFluid(1000))
                .output(dust, SilverChloride, 2)
                .EUt(VA[MV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // 3Ag2O + 2BF3 -> B2O3 + AgBF4
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SilverOxide, 9)
                .fluidInputs(BoronTrifluoride.getFluid(8000))
                .notConsumable(Benzene.getFluid(1))
                .output(dust, SilverTetrafluoroborate, 6)
                .output(dust, BoronTrioxide, 5)
                .EUt(VA[IV].toLong())
                .duration(32 * SECOND + 10 * TICK)
                .buildAndRegister()

            // 2AgCl + H2O -> Ag2O + 2HCl
            CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, SodiumHydroxide)
                .input(dust, SilverChloride, 4)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, SilverOxide, 3)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(VA[HV].toLong())
                .duration(9 * SECOND)
                .buildAndRegister()

            // 2C8H12Cl2Pt + 2C12H8I2 + 8C + 4AgBF4 + 4(CH3)3SnCl
            //         -> 2PtCl2 + 8I + 4AgCl + 4Sn + 10C6H4 + 4BF3 + 3C8H16 + 4HF
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .notConsumable(dust, Bipyridine) // C10H8N2 catalyst
                .notConsumable(dust, PalladiumBisdibenzylidieneacetone) // C51H42O3Pd2 catalyst
                .input(dust, Dichlorocyclooctadieneplatinium, 46)
                .input(dust, Diiodobiphenyl, 44)
                .input(dust, SilverTetrafluoroborate, 24)
                .input(dust, Carbon, 8)
                .notConsumable(Resorcinol.getFluid(1))
                .notConsumable(Butyllithium.getFluid(1))
                .fluidInputs(TrimethyltinChloride.getFluid(4000))
                .output(dust, PalladiumRaw, 6)
                .output(dust, Iodine, 8)
                .output(dust, SilverChloride, 8)
                .output(dust, Tin, 4)
                .fluidOutputs(Cycloparaphenylene.getFluid(10000))
                .fluidOutputs(BoronTrifluoride.getFluid(4000))
                .fluidOutputs(Octene.getFluid(3000))
                .fluidOutputs(HydrofluoricAcid.getFluid(4000))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // C8H16 -> 2C4H6 + 4C (drop)
            CENTRIFUGE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Octene.getFluid(1000))
                .fluidOutputs(Butadiene.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // C8H16 -> C8H12 + 4C (drop)
            CENTRIFUGE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(Octene.getFluid(1000))
                .fluidOutputs(Cyclooctadiene.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

        private fun cntProcess()
        {
            // 3C2H2 + 7C6H4 + 10N -> C48 + 10NH3 (993K)
            PLASMA_CVD_RECIPES.recipeBuilder()
                .notConsumable(plate, Rhenium)
                .notConsumable(SHAPE_MOLD_INGOT)
                .fluidInputs(Acetylene.getFluid(3000))
                .fluidInputs(Cycloparaphenylene.getFluid(7000))
                .fluidInputs(Nitrogen.getPlasma(10000))
                .output(ingot, CarbonNanotube)
                .EUt(VA[UV].toLong())
                .duration(5 * SECOND)
                .temperature(993)
                .buildAndRegister()

            // 12C2H2 + 28C6H4 + 4N -> 4C48 + 40NH3 (3972K)
            PLASMA_CVD_RECIPES.recipeBuilder()
                .notConsumable(plateDouble, Rhenium)
                .notConsumable(SHAPE_MOLD_INGOT)
                .fluidInputs(Acetylene.getFluid(12000))
                .fluidInputs(Cycloparaphenylene.getFluid(28000))
                .fluidInputs(Nitrogen.getPlasma(40000))
                .output(ingot, CarbonNanotube, 4)
                .fluidOutputs(Ammonia.getFluid(40000))
                .EUt(VA[UEV].toLong())
                .duration(1 * SECOND + 5 * TICK)
                .temperature(993)
                .buildAndRegister()

            // C48 decomposition.
            ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, CarbonNanotube)
                .output(dust, Carbon, 48)
                .EUt(VH[MV].toLong())
                .duration((Carbon.mass * 48).toInt())
                .buildAndRegister()

            // In CNT (C48) chain, the main compound CPP/Cycloparaphenylene (C6H4) is not
            // consumable actually, player should complete the following cycling when they
            // completed CNT chain.

            // Completed recipes of graphene foil, required it in CNT cycling.
            BENDER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(plate, Graphene)
                .output(foil, Graphene, 4)
                .EUt(24)
                .duration(Graphene.mass.toInt())
                .buildAndRegister()

            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_FOIL)
                .input(dust, Graphene)
                .output(foil, Graphene, 4)
                .EUt(24)
                .duration(Graphene.mass.toInt())
                .buildAndRegister()

            // 6C8 + C48 -> C96
            FORMING_PRESS_RECIPES.recipeBuilder()
                .input(foil, Graphene, 24)
                .input(dust, CarbonNanotube)
                .output(CARBON_ALLOTROPE_MIXTURE)
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // C96 -> C48 + (C6H4)7C12
            ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder()
                .input(CARBON_ALLOTROPE_MIXTURE)
                .output(ingot, CarbonNanotube)
                .output(GRAPHENE_ALIGNED_CNT, 4)
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // (C6H4)7(C12) -> 7C6H4 + 12C
            EXTRACTOR_RECIPES.recipeBuilder()
                .input(GRAPHENE_ALIGNED_CNT)
                .output(dust, Carbon, 3)
                .fluidOutputs(Cycloparaphenylene.getFluid(1750))
                .EUt(VA[HV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

        private fun seaborgiumCNTProcess()
        {
            // 3C2H2 + 7C6H4 + 10N -> C48 + 10NH3 (2876K)
            PLASMA_CVD_RECIPES.recipeBuilder()
                .notConsumable(plate, Technetium)
                .notConsumable(SHAPE_MOLD_INGOT)
                .input(dust, Seaborgium)
                .fluidInputs(Acetylene.getFluid(3000))
                .fluidInputs(Cycloparaphenylene.getFluid(7000))
                .fluidInputs(Nitrogen.getPlasma(10000))
                .output(ingot, SeaborgiumDopedCarbonNanotube)
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .temperature(2876)
                .buildAndRegister()

            // 12C2H2 + 28C6H4 + 4N -> 4C48 + 40NH3 (3972K)
            PLASMA_CVD_RECIPES.recipeBuilder()
                .notConsumable(plateDouble, Technetium)
                .notConsumable(SHAPE_MOLD_INGOT)
                .input(dust, Seaborgium)
                .fluidInputs(Acetylene.getFluid(12000))
                .fluidInputs(Cycloparaphenylene.getFluid(28000))
                .fluidInputs(Nitrogen.getPlasma(40000))
                .output(ingot, SeaborgiumDopedCarbonNanotube, 4)
                .fluidOutputs(Ammonia.getFluid(40000))
                .EUt(VA[UEV].toLong())
                .duration(1 * SECOND + 5 * TICK)
                .temperature(6364)
                .buildAndRegister()
        }

        private fun lanthanumFullereneNTProcess()
        {
            // 2C60H30 + 2La -> (C60H30)2La2
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, GeodesicPolyarene, 2)
                .input(dust, Lanthanum, 2)
                .output(dust, LanthanumFullereneMixture, 4)
                .EUt(VA[LuV].toLong())
                .duration(2 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // (C60H30)2La2 + 20N -> (C60)2La2 + 20NH3
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, MagnetoResonatic)
                .input(dust, LanthanumFullereneMixture, 4)
                .fluidInputs(Nitrogen.getFluid(20000))
                .output(dust, LanthanumEmbeddedFullerene, 4)
                .fluidOutputs(Ammonia.getFluid(20000))
                .EUt(VA[UHV].toLong())
                .duration(16 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // 0.5(C60)2La2 + 3C2H2 + 7C6H4 + 10N -> C48C60La + 10NH3
            PLASMA_CVD_RECIPES.recipeBuilder()
                .notConsumable(plate, Bohrium)
                .notConsumable(SHAPE_MOLD_INGOT)
                .input(dust, LanthanumEmbeddedFullerene, 2)
                .fluidInputs(Acetylene.getFluid(3000))
                .fluidInputs(Cycloparaphenylene.getFluid(7000))
                .fluidInputs(Nitrogen.getPlasma(10000))
                .output(ingot, LanthanumFullereneNanotube)
                .EUt(VA[UEV].toLong())
                .duration(5 * SECOND)
                .temperature(1986)
                .buildAndRegister()

            // 2(C60)2La2 + 12C2H2 + 28C6H4 + 4N -> 4C48C60La + 40NH3 (3972K)
            PLASMA_CVD_RECIPES.recipeBuilder()
                .notConsumable(plateDouble, Bohrium)
                .notConsumable(SHAPE_MOLD_INGOT)
                .input(dust, LanthanumEmbeddedFullerene, 8)
                .fluidInputs(Acetylene.getFluid(12000))
                .fluidInputs(Cycloparaphenylene.getFluid(28000))
                .fluidInputs(Nitrogen.getPlasma(40000))
                .output(ingot, LanthanumFullereneNanotube, 4)
                .fluidOutputs(Ammonia.getFluid(40000))
                .EUt(VA[UIV].toLong())
                .duration(1 * SECOND + 5 * TICK)
                .temperature(7944)
                .buildAndRegister()
        }

    }

}