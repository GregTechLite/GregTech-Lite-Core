package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.recipes.RecipeMaps.RESEARCH_STATION_RECIPES
import gregtech.api.recipes.RecipeMaps.SCANNER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.DrillingFluid
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.SodiumPotassium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.toolHeadDrill
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.MetaItems.EMITTER_LuV
import gregtech.common.items.MetaItems.EMITTER_OpV
import gregtech.common.items.MetaItems.EMITTER_UEV
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.EMITTER_UIV
import gregtech.common.items.MetaItems.EMITTER_UV
import gregtech.common.items.MetaItems.EMITTER_UXV
import gregtech.common.items.MetaItems.EMITTER_ZPM
import gregtech.common.items.MetaItems.HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.ROBOT_ARM_LuV
import gregtech.common.items.MetaItems.ROBOT_ARM_OpV
import gregtech.common.items.MetaItems.ROBOT_ARM_UEV
import gregtech.common.items.MetaItems.ROBOT_ARM_UHV
import gregtech.common.items.MetaItems.ROBOT_ARM_UIV
import gregtech.common.items.MetaItems.ROBOT_ARM_UV
import gregtech.common.items.MetaItems.ROBOT_ARM_UXV
import gregtech.common.items.MetaItems.ROBOT_ARM_ZPM
import gregtech.common.items.MetaItems.SENSOR_LuV
import gregtech.common.items.MetaItems.SENSOR_OpV
import gregtech.common.items.MetaItems.SENSOR_UEV
import gregtech.common.items.MetaItems.SENSOR_UHV
import gregtech.common.items.MetaItems.SENSOR_UIV
import gregtech.common.items.MetaItems.SENSOR_UV
import gregtech.common.items.MetaItems.SENSOR_UXV
import gregtech.common.items.MetaItems.SENSOR_ZPM
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import gregtech.common.items.MetaItems.TOOL_DATA_ORB
import gregtech.common.items.MetaItems.TOOL_DATA_STICK
import gregtech.common.items.MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.VOLTAGE_COIL_LuV
import gregtech.common.items.MetaItems.VOLTAGE_COIL_UV
import gregtech.common.items.MetaItems.VOLTAGE_COIL_ZPM
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_OUTPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.HULL
import gregtech.loaders.recipe.CraftingComponent
import gregtechlite.gtlitecore.api.HOUR
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AxinoFusedRedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EnrichedNaquadahAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Legendarium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RealizedQuantumFoamShard
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Rhugnor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.EMITTER_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FEMTO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_IV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_LuV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_ZPM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PICO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ROBOT_ARM_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SENSOR_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UXV
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_INPUT_HATCH_1048576
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_INPUT_HATCH_16777216
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_INPUT_HATCH_4194304
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_1048576
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_16777216
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.LASER_OUTPUT_HATCH_4194304
import net.minecraft.item.ItemStack

internal object AssemblyLineRecipes
{

    // @formatter:off

    fun init()
    {
        miningDroneRecipes()
        energyHatchesRecipes()
        laserHatchesRecipes()
    }

    private fun miningDroneRecipes()
    {
        // LuV Mining Drone
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, RhodiumPlatedPalladium)
            .input(plateDouble, RhodiumPlatedPalladium, 8)
            .input(round, RhodiumPlatedPalladium, 16)
            .input(EMITTER_LuV, 2)
            .input(SENSOR_LuV, 2)
            .input(ROBOT_ARM_LuV, 2)
            .input(rotor, RhodiumPlatedPalladium, 4)
            .input(toolHeadDrill, HSSE, 8)
            .input(cableGtQuadruple, NiobiumTitanium, 2)
            .input(circuit, Tier.LuV, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(DrillingFluid.getFluid(2000))
            .output(MINING_DRONE_LuV)
            .EUt(VA[LuV])
            .duration(30 * SECOND)
            .scannerResearch { r ->
                r.researchStack(MINING_DRONE_IV.stackForm)
                    .EUt(VA[IV])
                    .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // ZPM Mining Drone
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, NaquadahAlloy)
            .input(plateDouble, NaquadahAlloy, 8)
            .input(round, NaquadahAlloy, 16)
            .input(EMITTER_ZPM, 2)
            .input(SENSOR_ZPM, 2)
            .input(ROBOT_ARM_ZPM, 2)
            .input(rotor, NaquadahAlloy, 4)
            .input(toolHeadDrill, NaquadahAlloy, 8)
            .input(cableGtQuadruple, VanadiumGallium, 2)
            .input(circuit, Tier.ZPM, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(DrillingFluid.getFluid(4000))
            .output(MINING_DRONE_ZPM)
            .EUt(VA[ZPM])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(MINING_DRONE_LuV.stackForm)
                    .EUt(VA[LuV])
                    .CWUt(4)
            }
            .buildAndRegister()

        // UV Mining Drone
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Darmstadtium)
            .input(plateDouble, Darmstadtium, 12)
            .input(round, Darmstadtium, 16)
            .input(EMITTER_UV, 2)
            .input(SENSOR_UV, 2)
            .input(ROBOT_ARM_UV, 2)
            .input(rotor, Darmstadtium, 4)
            .input(toolHeadDrill, Duranium, 16)
            .input(cableGtQuadruple, YttriumBariumCuprate, 2)
            .input(circuit, Tier.UV, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(DrillingFluid.getFluid(8000))
            .output(MINING_DRONE_UV)
            .EUt(VA[UV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(MINING_DRONE_ZPM.stackForm)
                    .EUt(VA[ZPM])
                    .CWUt(16)
            }
            .buildAndRegister()

        // UHV Mining Drone
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Neutronium)
            .input(plateDouble, Neutronium, 12)
            .input(round, Neutronium, 16)
            .input(EMITTER_UHV, 2)
            .input(SENSOR_UHV, 2)
            .input(ROBOT_ARM_UHV, 2)
            .input(rotor, Neutronium, 4)
            .input(toolHeadDrill, EnrichedNaquadahAlloy, 16)
            .input(cableGtQuadruple, Europium, 2)
            .input(circuit, Tier.UHV, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(DrillingFluid.getFluid(16000))
            .output(MINING_DRONE_UHV)
            .EUt(VA[UHV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(MINING_DRONE_UV.stackForm)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // UEV Mining Drone
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Vibranium)
            .input(plateDouble, Vibranium, 16)
            .input(round, Vibranium, 32)
            .input(EMITTER_UEV, 2)
            .input(SENSOR_UEV, 2)
            .input(ROBOT_ARM_UEV, 2)
            .input(rotor, Vibranium, 4)
            .input(toolHeadDrill, Adamantium, 16)
            .input(cableGtQuadruple, Seaborgium, 2)
            .input(circuit, Tier.UEV, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .fluidInputs(DrillingFluid.getFluid(32000))
            .output(MINING_DRONE_UEV)
            .EUt(VA[UEV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(MINING_DRONE_UHV.stackForm)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // UIV Mining Drone
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Shirabon)
            .input(plateDouble, Shirabon, 16)
            .input(round, Shirabon, 32)
            .input(EMITTER_UIV, 2)
            .input(SENSOR_UIV, 2)
            .input(ROBOT_ARM_UIV, 2)
            .input(rotor, Shirabon, 4)
            .input(toolHeadDrill, Rhugnor, 16)
            .input(cableGtQuadruple, SuperheavyAlloyA, 2)
            .input(circuit, Tier.UIV, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .fluidInputs(DrillingFluid.getFluid(64000))
            .output(MINING_DRONE_UIV)
            .EUt(VA[UIV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(MINING_DRONE_UEV.stackForm)
                    .EUt(VA[UEV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // UXV Mining Drone
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Creon)
            .input(plateDouble, Creon, 32)
            .input(round, Creon, 64)
            .input(EMITTER_UXV, 2)
            .input(SENSOR_UXV, 2)
            .input(ROBOT_ARM_UXV, 2)
            .input(rotor, Creon, 8)
            .input(toolHeadDrill, Legendarium, 16)
            .input(cableGtQuadruple, SuperheavyAlloyB, 2)
            .input(circuit, Tier.UXV, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .fluidInputs(DrillingFluid.getFluid(128000))
            .output(MINING_DRONE_UXV)
            .EUt(VA[UXV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(MINING_DRONE_UIV.stackForm)
                    .EUt(VA[UIV])
                    .CWUt(96)
            }
            .buildAndRegister()

        // OpV Mining Drone
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, BlackDwarfMatter)
            .input(plateDouble, BlackDwarfMatter, 32)
            .input(round, BlackDwarfMatter, 64)
            .input(EMITTER_OpV, 2)
            .input(SENSOR_OpV, 2)
            .input(ROBOT_ARM_OpV, 2)
            .input(rotor, BlackDwarfMatter, 8)
            .input(toolHeadDrill, Creon, 16)
            .input(cableGtQuadruple, Periodicium, 2)
            .input(circuit, Tier.OpV, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .fluidInputs(DrillingFluid.getFluid(256000))
            .output(MINING_DRONE_OpV)
            .EUt(VA[OpV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(MINING_DRONE_UXV.stackForm)
                    .EUt(VA[UXV])
                    .CWUt(128)
            }
            .buildAndRegister()

        // MAX Mining Drones
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, AxinoFusedRedMatter)
            .input(plateDouble, AxinoFusedRedMatter, 32)
            .input(round, AxinoFusedRedMatter, 64)
            .input(EMITTER_MAX, 2)
            .input(SENSOR_MAX, 2)
            .input(ROBOT_ARM_MAX, 2)
            .input(rotor, AxinoFusedRedMatter, 8)
            .input(toolHeadDrill, AxinoFusedRedMatter, 16)
            .input(cableGtQuadruple, RealizedQuantumFoamShard, 2)
            .input(circuit, Tier.MAX, 4)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
            .fluidInputs(DrillingFluid.getFluid(512000))
            .output(MINING_DRONE_MAX)
            .EUt(VA[MAX])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(MINING_DRONE_OpV)
                    .EUt(VA[OpV])
                    .CWUt(160)
            }
            .buildAndRegister()
    }

    private fun energyHatchesRecipes()
    {
        // LuV Energy Hatch
        GTRecipeHandler.removeRecipesByInputs(SCANNER_RECIPES,
            TOOL_DATA_STICK.stackForm, ENERGY_INPUT_HATCH[IV].stackForm)
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(HULL[LuV].stackForm,
                OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 4),
                HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.LuV),
                VOLTAGE_COIL_LuV.getStackForm(2)),
            arrayOf(SodiumPotassium.getFluid(6000), SolderingAlloy.getFluid(L * 5)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[LuV])
            .input(cableGtSingle, NiobiumTitanium, 2)
            .input(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
            .input(circuit, Tier.LuV)
            .input(VOLTAGE_COIL_LuV, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 5))
            .fluidInputs(SodiumPotassium.getFluid(4000))
            .output(ENERGY_INPUT_HATCH[LuV])
            .EUt(VA[LuV])
            .duration(10 * SECOND)
            .scannerResearch { r -> r
                .researchStack(ENERGY_INPUT_HATCH[IV])
                .EUt(VA[HV])
                .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // LuV Dynamo Hatch
        GTRecipeHandler.removeRecipesByInputs(SCANNER_RECIPES,
            TOOL_DATA_STICK.stackForm, ENERGY_OUTPUT_HATCH[IV].stackForm)
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(HULL[LuV].stackForm,
                OreDictUnifier.get(spring, NiobiumTitanium, 4),
                HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.LuV),
                VOLTAGE_COIL_LuV.getStackForm(2)),
            arrayOf(SodiumPotassium.getFluid(6000), SolderingAlloy.getFluid(L * 5)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[LuV])
            .input(spring, NiobiumTitanium, 2)
            .input(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
            .input(circuit, Tier.LuV)
            .input(VOLTAGE_COIL_LuV, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 5))
            .fluidInputs(SodiumPotassium.getFluid(4000))
            .output(ENERGY_OUTPUT_HATCH[LuV])
            .EUt(VA[LuV])
            .duration(10 * SECOND)
            .scannerResearch { r -> r
                .researchStack(ENERGY_OUTPUT_HATCH[IV])
                .EUt(VA[HV])
                .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // ZPM Energy Hatch
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            TOOL_DATA_ORB.stackForm, ENERGY_INPUT_HATCH[LuV].stackForm)
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(HULL[ZPM].stackForm,
                OreDictUnifier.get(cableGtSingle, VanadiumGallium, 4),
                ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.ZPM),
                VOLTAGE_COIL_ZPM.getStackForm(2)),
            arrayOf(SodiumPotassium.getFluid(8000), SolderingAlloy.getFluid(L * 10)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[ZPM])
            .input(cableGtSingle, VanadiumGallium, 2)
            .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
            .input(circuit, Tier.ZPM)
            .input(VOLTAGE_COIL_ZPM, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .fluidInputs(SodiumPotassium.getFluid(8000))
            .output(ENERGY_INPUT_HATCH[ZPM])
            .EUt(VA[ZPM])
            .duration(10 * SECOND)
            .scannerResearch { r -> r
                .researchStack(ENERGY_INPUT_HATCH[LuV])
                .EUt(VA[IV])
                .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // ZPM Dynamo Hatch
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            TOOL_DATA_ORB.stackForm, ENERGY_OUTPUT_HATCH[LuV].stackForm)
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(HULL[ZPM].stackForm,
                OreDictUnifier.get(spring, VanadiumGallium, 4),
                ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.ZPM),
                VOLTAGE_COIL_ZPM.getStackForm(2)),
            arrayOf(SodiumPotassium.getFluid(8000), SolderingAlloy.getFluid(L * 10)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[ZPM])
            .input(spring, VanadiumGallium, 2)
            .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
            .input(circuit, Tier.ZPM)
            .input(VOLTAGE_COIL_ZPM, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 10))
            .fluidInputs(SodiumPotassium.getFluid(8000))
            .output(ENERGY_OUTPUT_HATCH[ZPM])
            .EUt(VA[ZPM])
            .duration(10 * SECOND)
            .scannerResearch { r -> r
                .researchStack(ENERGY_OUTPUT_HATCH[LuV])
                .EUt(VA[IV])
                .duration(1 * MINUTE)
            }
            .buildAndRegister()

        // UV Energy Hatch
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            TOOL_DATA_MODULE.stackForm, ENERGY_INPUT_HATCH[ZPM].stackForm)
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(HULL[UV].stackForm,
                OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 4),
                ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.UV),
                VOLTAGE_COIL_UV.getStackForm(2)),
            arrayOf(SodiumPotassium.getFluid(10000), SolderingAlloy.getFluid(L * 20)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UV])
            .input(cableGtSingle, YttriumBariumCuprate, 4)
            .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
            .input(circuit, Tier.UV)
            .input(VOLTAGE_COIL_UV, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 20))
            .fluidInputs(SodiumPotassium.getFluid(12000))
            .output(ENERGY_INPUT_HATCH[UV])
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_INPUT_HATCH[ZPM])
                .EUt(VA[ZPM])
                .CWUt(16)
            }
            .buildAndRegister()

        // UV Dynamo Hatch
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            TOOL_DATA_MODULE.stackForm, ENERGY_OUTPUT_HATCH[ZPM].stackForm)
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(HULL[UV].stackForm,
                OreDictUnifier.get(spring, YttriumBariumCuprate, 4),
                ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.UV),
                VOLTAGE_COIL_UV.getStackForm(2)),
            arrayOf(SodiumPotassium.getFluid(10000), SolderingAlloy.getFluid(L * 20)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UV])
            .input(spring, YttriumBariumCuprate, 4)
            .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
            .input(circuit, Tier.UV)
            .input(VOLTAGE_COIL_UV, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 20))
            .fluidInputs(SodiumPotassium.getFluid(12000))
            .output(ENERGY_OUTPUT_HATCH[UV])
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_OUTPUT_HATCH[ZPM])
                .EUt(VA[ZPM])
                .CWUt(16)
            }
            .buildAndRegister()

        // UHV Energy Hatch
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            TOOL_DATA_MODULE.stackForm, ENERGY_INPUT_HATCH[UV].stackForm)
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(HULL[UHV].stackForm,
                OreDictUnifier.get(cableGtSingle, Europium, 4),
                ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.UHV),
                OreDictUnifier.get(wireGtDouble, RutheniumTriniumAmericiumNeutronate, 2)),
            arrayOf(SodiumPotassium.getFluid(12000), SolderingAlloy.getFluid(L * 40)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UHV])
            .input(cableGtSingle, Europium, 4)
            .input(NANO_PIC_CHIP, 2)
            .input(circuit, Tier.UHV)
            .input(VOLTAGE_COIL_UHV, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(SodiumPotassiumEutatic.getFluid(16000))
            .output(ENERGY_INPUT_HATCH[UHV])
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_INPUT_HATCH[UV])
                .EUt(VA[UV])
                .CWUt(24)
            }
            .buildAndRegister()

        // UHV Dynamo Hatch
        GTRecipeHandler.removeRecipesByInputs(RESEARCH_STATION_RECIPES,
            TOOL_DATA_MODULE.stackForm, ENERGY_OUTPUT_HATCH[UV].stackForm)
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLY_LINE_RECIPES,
            arrayOf(HULL[UHV].stackForm,
                OreDictUnifier.get(spring, Europium, 4),
                ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2),
                OreDictUnifier.get(circuit, Tier.UHV),
                OreDictUnifier.get(wireGtDouble, RutheniumTriniumAmericiumNeutronate, 2)),
            arrayOf(SodiumPotassium.getFluid(12000), SolderingAlloy.getFluid(L * 40)))

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UHV])
            .input(spring, Europium, 4)
            .input(NANO_PIC_CHIP, 2)
            .input(circuit, Tier.UHV)
            .input(VOLTAGE_COIL_UHV, 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(SodiumPotassiumEutatic.getFluid(16000))
            .output(ENERGY_OUTPUT_HATCH[UHV])
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_OUTPUT_HATCH[UV])
                .EUt(VA[UV])
                .CWUt(24)
            }
            .buildAndRegister()

        // UEV Energy Hatch
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UEV])
            .input(cableGtSingle, Seaborgium, 4)
            .input(NANO_PIC_CHIP, 4)
            .input(circuit, Tier.UEV)
            .input(VOLTAGE_COIL_UEV, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
            .fluidInputs(SodiumPotassiumEutatic.getFluid(18000))
            .output(ENERGY_INPUT_HATCH[UEV])
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_INPUT_HATCH[UHV])
                .EUt(VA[UHV])
                .CWUt(32)
            }
            .buildAndRegister()

        // UEV Dynamo Hatch
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UEV])
            .input(spring, Seaborgium, 4)
            .input(NANO_PIC_CHIP, 4)
            .input(circuit, Tier.UEV)
            .input(VOLTAGE_COIL_UEV, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 80))
            .fluidInputs(SodiumPotassiumEutatic.getFluid(18000))
            .output(ENERGY_OUTPUT_HATCH[UEV])
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_OUTPUT_HATCH[UHV])
                .EUt(VA[UHV])
                .CWUt(32)
            }
            .buildAndRegister()

        // UIV Energy Hatch
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UIV])
            .input(cableGtSingle, SuperheavyAlloyA, 4)
            .input(PICO_PIC_CHIP, 4)
            .input(circuit, Tier.UIV)
            .input(VOLTAGE_COIL_UIV, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 160))
            .fluidInputs(SodiumPotassiumEutatic.getFluid(20000))
            .output(ENERGY_INPUT_HATCH[UIV])
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_INPUT_HATCH[UEV])
                .EUt(VA[UEV])
                .CWUt(48)
            }
            .buildAndRegister()

        // UIV Dynamo Hatch
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UIV])
            .input(spring, SuperheavyAlloyA, 4)
            .input(PICO_PIC_CHIP, 4)
            .input(circuit, Tier.UIV)
            .input(VOLTAGE_COIL_UIV, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 160))
            .fluidInputs(SodiumPotassiumEutatic.getFluid(20000))
            .output(ENERGY_OUTPUT_HATCH[UIV])
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_OUTPUT_HATCH[UEV])
                .EUt(VA[UEV])
                .CWUt(48)
            }
            .buildAndRegister()

        // UXV Energy Hatch
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UXV])
            .input(cableGtSingle, SuperheavyAlloyB, 4)
            .input(PICO_PIC_CHIP, 4)
            .input(circuit, Tier.UXV)
            .input(VOLTAGE_COIL_UXV, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 320))
            .fluidInputs(SodiumPotassiumEutatic.getFluid(40000))
            .output(ENERGY_INPUT_HATCH[UXV])
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_INPUT_HATCH[UIV])
                .EUt(VA[UIV])
                .CWUt(64)
            }
            .buildAndRegister()

        // UXV Dynamo Hatch
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UXV])
            .input(spring, SuperheavyAlloyB, 4)
            .input(PICO_PIC_CHIP, 4)
            .input(circuit, Tier.UXV)
            .input(VOLTAGE_COIL_UXV, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 320))
            .fluidInputs(SodiumPotassiumEutatic.getFluid(40000))
            .output(ENERGY_OUTPUT_HATCH[UXV])
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_OUTPUT_HATCH[UIV])
                .EUt(VA[UIV])
                .CWUt(64)
            }
            .buildAndRegister()

        // OpV Energy Hatch
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[OpV])
            .input(cableGtSingle, Periodicium, 4)
            .input(FEMTO_PIC_CHIP, 4)
            .input(circuit, Tier.OpV)
            .input(VOLTAGE_COIL_OpV, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 640))
            .fluidInputs(SodiumPotassiumEutatic.getFluid(80000))
            .output(ENERGY_INPUT_HATCH[OpV])
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_INPUT_HATCH[UXV])
                .EUt(VA[UXV])
                .CWUt(96)
            }
            .buildAndRegister()

        // OpV Dynamo Hatch
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[OpV])
            .input(spring, Periodicium, 4)
            .input(FEMTO_PIC_CHIP, 4)
            .input(circuit, Tier.OpV)
            .input(VOLTAGE_COIL_OpV, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 640))
            .fluidInputs(SodiumPotassiumEutatic.getFluid(80000))
            .output(ENERGY_OUTPUT_HATCH[OpV])
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_OUTPUT_HATCH[UXV])
                .EUt(VA[UXV])
                .CWUt(96)
            }
            .buildAndRegister()

        // MAX Energy Hatch
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[MAX])
            .input(cableGtSingle, RealizedQuantumFoamShard, 4)
            .input(FEMTO_PIC_CHIP, 4)
            .input(circuit, Tier.MAX)
            .input(VOLTAGE_COIL_MAX, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 1280))
            .fluidInputs(SodiumPotassiumEutatic.getFluid(160000))
            .output(ENERGY_INPUT_HATCH[MAX])
            .EUt(VA[OpV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_INPUT_HATCH[OpV])
                .EUt(VA[OpV])
                .CWUt(128)
            }
            .buildAndRegister()

        // MAX Dynamo Hatch
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[MAX])
            .input(spring, RealizedQuantumFoamShard, 4)
            .input(FEMTO_PIC_CHIP, 4)
            .input(circuit, Tier.MAX)
            .input(VOLTAGE_COIL_MAX, 2)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 1280))
            .fluidInputs(SodiumPotassiumEutatic.getFluid(160000))
            .output(ENERGY_OUTPUT_HATCH[MAX])
            .EUt(VA[OpV])
            .duration(10 * SECOND)
            .stationResearch { r -> r
                .researchStack(ENERGY_OUTPUT_HATCH[OpV])
                .EUt(VA[OpV])
                .CWUt(128)
            }
            .buildAndRegister()

    }

    private fun laserHatchesRecipes()
    {
        // Ultimate laser hatches consists 4194304A-16777216A.
        for (tier in IV..OpV)
        {
            val actualTier = tier - IV // Because laser hatch start at IV stage.
            // 4194304A Laser Target Hatch
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[tier])
                .input(lens, Diamond, 64)
                .input(lens, Diamond, 64)
                .inputs((CraftingComponent.SENSOR.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.SENSOR.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                .input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 32)
                .fluidInputs(Neutronium.getFluid(L * (actualTier + 1)))
                .output(LASER_INPUT_HATCH_4194304[actualTier])
                .EUt(VA[tier])
                .duration(32 * MINUTE)
                .scannerResearch { r ->
                    r.researchStack(LASER_INPUT_HATCH_1048576[actualTier].stackForm)
                        .EUt(VA[IV])
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // 4194304A Laser Source Hatch
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[tier])
                .input(lens, Diamond, 64)
                .input(lens, Diamond, 64)
                .inputs((CraftingComponent.EMITTER.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.EMITTER.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                .input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 32)
                .fluidInputs(Neutronium.getFluid(L * (actualTier + 1)))
                .output(LASER_OUTPUT_HATCH_4194304[actualTier])
                .EUt(VA[tier])
                .duration(32 * MINUTE)
                .scannerResearch { r ->
                    r.researchStack(LASER_OUTPUT_HATCH_1048576[actualTier].stackForm)
                        .EUt(VA[IV])
                        .duration(1 * MINUTE)
                }
                .buildAndRegister()

            // 16777216A Laser Target Hatch
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[tier])
                .input(lens, Diamond, 64)
                .input(lens, Diamond, 64)
                .input(lens, Diamond, 64)
                .input(lens, Diamond, 64)
                .inputs((CraftingComponent.SENSOR.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.SENSOR.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.SENSOR.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.SENSOR.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                .input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 64)
                .fluidInputs(SpaceTime.getFluid(L * (actualTier + 1)))
                .output(LASER_INPUT_HATCH_16777216[actualTier])
                .EUt(VA[tier])
                .duration(1 * HOUR + 4 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(LASER_INPUT_HATCH_4194304[actualTier].stackForm)
                        .EUt(VA[UV])
                        .CWUt(32)
                }
                .buildAndRegister()

            // 16777216A Laser Source Hatch
            ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[tier])
                .input(lens, Diamond, 64)
                .input(lens, Diamond, 64)
                .input(lens, Diamond, 64)
                .input(lens, Diamond, 64)
                .inputs((CraftingComponent.EMITTER.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.EMITTER.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.EMITTER.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.EMITTER.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                .inputs((CraftingComponent.PUMP.getIngredient(tier) as ItemStack).copy(64))
                .input(cableGtHex, (CraftingComponent.CABLE.getIngredient(tier) as UnificationEntry).material, 64)
                .fluidInputs(SpaceTime.getFluid(L * (actualTier + 1)))
                .output(LASER_OUTPUT_HATCH_16777216[actualTier])
                .EUt(VA[tier])
                .duration(1 * HOUR + 4 * MINUTE)
                .stationResearch { r ->
                    r.researchStack(LASER_OUTPUT_HATCH_4194304[actualTier].stackForm)
                        .EUt(VA[UV])
                        .CWUt(32)
                }
                .buildAndRegister()

        }

    }

    // @formatter:on

}