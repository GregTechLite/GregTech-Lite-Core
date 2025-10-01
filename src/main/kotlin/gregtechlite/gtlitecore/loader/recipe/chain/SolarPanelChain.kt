package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.BlackSteel
import gregtech.api.unification.material.Materials.BlueSteel
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.EnrichedNaquadahTriniumEuropiumDuranide
import gregtech.api.unification.material.Materials.GalliumArsenide
import gregtech.api.unification.material.Materials.Glass
import gregtech.api.unification.material.Materials.Glue
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.IndiumGalliumPhosphide
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Polytetrafluoroethylene
import gregtech.api.unification.material.Materials.PolyvinylAcetate
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SamariumIronArsenicOxide
import gregtech.api.unification.material.Materials.UraniumRhodiumDinaquadide
import gregtech.api.unification.material.Materials.VinylAcetate
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.battery
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.MetaItems.BATTERY_HULL_LV
import gregtech.common.items.MetaItems.COVER_SOLAR_PANEL
import gregtech.common.items.MetaItems.COVER_SOLAR_PANEL_EV
import gregtech.common.items.MetaItems.COVER_SOLAR_PANEL_HV
import gregtech.common.items.MetaItems.COVER_SOLAR_PANEL_IV
import gregtech.common.items.MetaItems.COVER_SOLAR_PANEL_LUV
import gregtech.common.items.MetaItems.COVER_SOLAR_PANEL_LV
import gregtech.common.items.MetaItems.COVER_SOLAR_PANEL_MV
import gregtech.common.items.MetaItems.COVER_SOLAR_PANEL_ULV
import gregtech.common.items.MetaItems.COVER_SOLAR_PANEL_UV
import gregtech.common.items.MetaItems.COVER_SOLAR_PANEL_ZPM
import gregtech.common.items.MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.LOW_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.ROBOT_ARM_HV
import gregtech.common.items.MetaItems.ROBOT_ARM_IV
import gregtech.common.items.MetaItems.SENSOR_UEV
import gregtech.common.items.MetaItems.SENSOR_UHV
import gregtech.common.items.MetaItems.SENSOR_UV
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_PLATE
import gregtech.common.items.MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.getFluid
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ArceusAlloy2B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronFranciumCarbideSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CadmiumSulfide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicBoronNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicHeterodiamond
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicSiliconNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicZirconia
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DielectricFormationMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneVinylAcetatePolymer
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullereneSuperconductor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GalliumNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GalliumTrioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyK243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyX78
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HexagonalSiliconNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumHexaboride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methyltrichlorosilane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SeaborgiumDopedCarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Silane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SilicaGel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumPeroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SolarGradeSilicon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitanSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TransitionAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.glass
import gregtechlite.gtlitecore.common.block.variant.component.SensorCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICA_INSULATOR_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PICO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.POLYMER_INSULATOR_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_UHV

object SolarPanelChain
{

    // @formatter:off

    fun init()
    {
        // Si(CH3)Cl3 + 4H -> SiH4 + 3HCl + C
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(Methyltrichlorosilane.getFluid(1000))
            .fluidInputs(Hydrogen.getFluid(4000))
            .output(dust, Carbon)
            .fluidOutputs(Silane.getFluid(1000))
            .fluidOutputs(HydrochloricAcid.getFluid(3000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // SiH4 + Na2O2 + 2Cl -> Si + 2NaCl + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, SodiumPeroxide, 4)
            .fluidInputs(Silane.getFluid(1000))
            .fluidInputs(Chlorine.getFluid(2000))
            .output(dust, SolarGradeSilicon)
            .output(dust, Salt, 4)
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[HV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // 3C2H4 + 2C4H6O2 -> (C2H4)3(C4H6O2)2
        AUTOCLAVE_RECIPES.recipeBuilder()
            .input(round, Polyethylene, 27)
            .fluidInputs(PolyvinylAcetate.getFluid(2000))
            .fluidOutputs(EthyleneVinylAcetatePolymer.getFluid(1000))
            .EUt(VA[MV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Solar Panel
        ModHandler.removeRecipeByName("gregtech:solar_panel_basic")
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(plate, SolarGradeSilicon, 4)
            .input(circuit, Tier.ULV, 2)
            .input(foil, Aluminium, 24)
            .input(MICA_INSULATOR_FOIL, 32)
            .input(dust, SilicaGel, 6)
            .fluidInputs(Glue.getFluid(1000))
            .fluidInputs(AnnealedCopper.getFluid(L * 4))
            .fluidInputs(Polytetrafluoroethylene.getFluid(1000))
            .fluidInputs(EthyleneVinylAcetatePolymer.getFluid(500))
            .output(COVER_SOLAR_PANEL, 2)
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(plate, SolarGradeSilicon, 4)
            .input(circuit, Tier.ULV, 2)
            .input(foil, Aluminium, 24)
            .input(POLYMER_INSULATOR_FOIL, 8)
            .input(dust, SilicaGel, 6)
            .fluidInputs(Glue.getFluid(1000))
            .fluidInputs(AnnealedCopper.getFluid(L * 4))
            .fluidInputs(Polytetrafluoroethylene.getFluid(1000))
            .fluidInputs(EthyleneVinylAcetatePolymer.getFluid(500))
            .output(COVER_SOLAR_PANEL, 2)
            .EUt(VA[IV])
            .duration(2 * SECOND + 10 * TICK)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }



    // @formatter:on

}