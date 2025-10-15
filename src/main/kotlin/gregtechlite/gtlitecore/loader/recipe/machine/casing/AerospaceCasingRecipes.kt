package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Concrete
import gregtech.api.unification.material.Materials.Dysprosium
import gregtech.api.unification.material.Materials.Erbium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Gadolinium
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Holmium
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Lubricant
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Roentgenium
import gregtech.api.unification.material.Materials.RutheniumTriniumAmericiumNeutronate
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Terbium
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.material.Materials.Ytterbium
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.common.items.MetaItems.CONVEYOR_MODULE_UHV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UEV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UHV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UIV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UV
import gregtech.common.items.MetaItems.ELECTRIC_MOTOR_UXV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UHV
import gregtech.common.items.MetaItems.ELECTRIC_PISTON_UV
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UHV
import gregtech.common.items.MetaItems.ROBOT_ARM_UHV
import gregtech.common.metatileentities.MetaTileEntities.HIGH_PERFORMANCE_COMPUTING_ARRAY
import gregtech.common.metatileentities.MetaTileEntities.HULL
import gregtech.common.metatileentities.MetaTileEntities.POLARIZER
import gregtech.common.metatileentities.MetaTileEntities.POWER_TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_CHEST
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_STORAGE_CONTROLLER
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_STORAGE_EXTENDER
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_STORAGE_PROXY
import gregtech.common.metatileentities.MetaTileEntities.QUANTUM_TANK
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimensionallyShiftedSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GelidCryotheum
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyX78
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarkDegenerateMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kevlar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableFlerovium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PreciousMetalAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrimordialMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumchromodynamicallyConfinedMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Rhugnor
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SamariumCobalt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tairitsium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumHafniumSeaborgiumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitanSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VibraniumTritaniumActiniumIronSuperhydride
import gregtechlite.gtlitecore.common.block.adapter.GTCleanroomCasing
import gregtechlite.gtlitecore.common.block.adapter.GTComputerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.aerospace.AccelerationTrack
import gregtechlite.gtlitecore.common.block.variant.aerospace.AerospaceCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FEMTO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICA_INSULATOR_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.POLYMER_INSULATOR_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UHV

internal object AerospaceCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // High Strength Concrete
        ASSEMBLER_RECIPES.recipeBuilder()
            .inputs(GTCleanroomCasing.PLASCRETE.getStack(4))
            .input(plate, HDCS, 8)
            .fluidInputs(Adamantium.getFluid(L))
            .outputs(AerospaceCasing.HIGH_STRENGTH_CONCRETE.getStack(4))
            .EUt(VA[LuV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Space Elevator Base Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Neutronium, 8)
            .input(screw, Palladium, 32)
            .input(plate, Pikyonium64B, 64)
            .input(circuit, Tier.UHV, 4)
            .input(ELECTRIC_PISTON_UV, 2)
            .input(ring, Adamantium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(2000))
            .fluidInputs(Iridium.getFluid(L * 8))
            .outputs(AerospaceCasing.ELEVATOR_BASE_CASING.getStack(16))
            .EUt(VA[UV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(MetalCasing.OSMIRIDIUM.stack)
                    .EUt(VA[UV])
                    .CWUt(16)
            }
            .buildAndRegister()

        // Support Structure Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, Neutronium, 8)
            .input(bolt, Naquadria, 16)
            .input(stickLong, Neutronium, 8)
            .input(plateDouble, Osmiridium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(1000))
            .fluidInputs(Iridium.getFluid(L * 10))
            .outputs(AerospaceCasing.SUPPORT_STRUCTURE_CASING.getStack(16))
            .EUt(VA[UV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(OreDictUnifier.get(frameGt, Neutronium))
                    .EUt(VA[UV])
                    .CWUt(8)
            }
            .buildAndRegister()

        // Internal Support Structure Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(GTComputerCasing.HIGH_POWER_CASING.getStack(8))
            .input(bolt, Palladium, 16)
            .input(plateDouble, Neutronium, 8)
            .input(ring, HSLASteel, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(8000))
            .fluidInputs(Concrete.getFluid(L * 10))
            .outputs(AerospaceCasing.INTERNAL_STRUCTURE_CASING.getStack(16))
            .EUt(VA[UV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(GTComputerCasing.HIGH_POWER_CASING.stack)
                    .EUt(VA[UV])
                    .CWUt(12)
            }
            .buildAndRegister()

        // Acceleration Track MK1
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(AerospaceCasing.ELEVATOR_BASE_CASING.stack)
            .input(ELECTRIC_MOTOR_UV, 4)
            .input(ring, Neutronium, 8)
            .input(stick, Neutronium, 4)
            .input(circuit, Tier.UV)
            .input(screw, Neutronium, 16)
            .input(plate, Osmiridium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(8000))
            .fluidInputs(Naquadria.getFluid(L * 10))
            .fluidInputs(Lubricant.getFluid(16000))
            .outputs(AccelerationTrack.MK1.getStack(8))
            .EUt(VA[UV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(AerospaceCasing.ELEVATOR_BASE_CASING.stack)
                    .EUt(VA[UV])
                    .CWUt(16)
            }
            .buildAndRegister()

        // Acceleration Track MK2
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(AerospaceCasing.ELEVATOR_BASE_CASING.stack)
            .input(ELECTRIC_MOTOR_UHV, 4)
            .input(ring, Adamantium, 8)
            .input(stick, Adamantium, 4)
            .input(circuit, Tier.UHV)
            .input(screw, CosmicNeutronium, 16)
            .input(plate, Osmiridium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(8000))
            .fluidInputs(Taranium.getFluid(L * 10))
            .fluidInputs(Lubricant.getFluid(16000))
            .outputs(AccelerationTrack.MK2.getStack(8))
            .EUt(VA[UHV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(AccelerationTrack.MK1.stack)
                    .EUt(VA[UV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // Acceleration Track MK3
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(AerospaceCasing.ELEVATOR_BASE_CASING.stack)
            .input(ELECTRIC_MOTOR_UEV, 4)
            .input(ring, CosmicNeutronium, 8)
            .input(stick, CosmicNeutronium, 4)
            .input(circuit, Tier.UEV)
            .input(screw, Infinity, 16)
            .input(plate, Osmiridium, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(8000))
            .fluidInputs(MetastableHassium.getFluid(L * 10))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
            .outputs(AccelerationTrack.MK3.getStack(8))
            .EUt(VA[UEV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(AccelerationTrack.MK2.stack)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Acceleration Track MK4
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(AerospaceCasing.ELEVATOR_BASE_CASING.stack)
            .input(ELECTRIC_MOTOR_UIV, 4)
            .input(ring, HeavyQuarkDegenerateMatter, 8)
            .input(stick, HeavyQuarkDegenerateMatter, 4)
            .input(circuit, Tier.UIV)
            .input(screw, TranscendentMetal, 16)
            .input(plate, Pikyonium64B, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(8000))
            .fluidInputs(QuantumchromodynamicallyConfinedMatter.getFluid(L * 10))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
            .outputs(AccelerationTrack.MK4.getStack(8))
            .EUt(VA[UIV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(AccelerationTrack.MK3.stack)
                    .EUt(VA[UEV])
                    .CWUt(48)
            }
            .buildAndRegister()

        // Acceleration Orbit MK5
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(AerospaceCasing.ELEVATOR_BASE_CASING.stack)
            .input(ELECTRIC_MOTOR_UXV, 4)
            .input(ring, TranscendentMetal, 8)
            .input(stick, TranscendentMetal, 4)
            .input(circuit, Tier.UXV)
            .input(screw, SpaceTime, 16)
            .input(plate, Pikyonium64B, 16)
            .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(8000))
            .fluidInputs(PrimordialMatter.getFluid(L * 10))
            .fluidInputs(DimensionallyShiftedSuperfluid.getFluid(16000))
            .outputs(AccelerationTrack.MK5.getStack(8))
            .EUt(VA[UXV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(AccelerationTrack.MK4.stack)
                    .EUt(VA[UIV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // Dyson Swarm Energy Receiver Base Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(POWER_TRANSFORMER[UHV])
            .input(FEMTO_PIC_CHIP, 64)
            .input(wireGtHex, VibraniumTritaniumActiniumIronSuperhydride, 4)
            .input(VOLTAGE_COIL_UHV, 64)
            .input(VOLTAGE_COIL_UHV, 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 50000))
            .fluidInputs(GelidCryotheum.getFluid(16000))
            .fluidInputs(UUMatter.getFluid(8000))
            .outputs(AerospaceCasing.DYSON_SWARM_ENERGY_RECEIVER_BASE_CASING.getStack(16))
            .EUt(VA[UHV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(POWER_TRANSFORMER[UHV].stackForm)
                    .EUt(VA[UHV])
                    .CWUt(24)
            }
            .buildAndRegister()

        // Dyson Swarm Module Deployment Unit Base Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UHV])
            .input(circuit, Tier.UEV, 4)
            .input(ELECTRIC_PUMP_UHV, 16)
            .input(QUANTUM_TANK[UHV], 2)
            .input(CONVEYOR_MODULE_UHV, 16)
            .input(QUANTUM_CHEST[UHV], 2)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(Lubricant.getFluid(50000))
            .fluidInputs(GelidCryotheum.getFluid(FluidStorageKeys.LIQUID, 16000))
            .fluidInputs(UUMatter.getFluid(8000))
            .outputs(AerospaceCasing.DYSON_SWARM_MODULE_DEPLOYMENT_UNIT_BASE_CASING.getStack(16))
            .EUt(VA[UHV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(QUANTUM_CHEST[UHV].stackForm)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Dyson Swarm Module Deployment Unit Core
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UHV], 4)
            .input(QUANTUM_STORAGE_CONTROLLER, 8)
            .input(QUANTUM_STORAGE_PROXY, 16)
            .input(QUANTUM_STORAGE_EXTENDER, 16)
            .input(CONVEYOR_MODULE_UHV, 8)
            .input(ROBOT_ARM_UHV, 8)
            .input(ELECTRIC_PISTON_UHV, 8)
            .input(plateDouble, Rhugnor, 4)
            .input(foil, QuantumAlloy, 32)
            .input(screw, Tairitsium, 24)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(Lubricant.getFluid(50000))
            .fluidInputs(Bedrockium.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(8000))
            .outputs(AerospaceCasing.DYSON_SWARM_MODULE_DEPLOYMENT_UNIT_CORE.stack)
            .EUt(VA[UHV])
            .duration(1 * MINUTE)
            .stationResearch {
                it.researchStack(QUANTUM_STORAGE_CONTROLLER.stackForm)
                    .EUt(VA[UHV])
                    .CWUt(48)
            }
            .buildAndRegister()

        // Dyson Swarm Module Deployment Unit Superconducting Magnet
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(block, SamariumCobalt, 8)
            .input(wireGtHex, RutheniumTriniumAmericiumNeutronate, 16)
            .input(FEMTO_PIC_CHIP, 64)
            .input(circuit, Tier.UEV, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(Lubricant.getFluid(50000))
            .fluidInputs(TitanSteel.getFluid(L * 20))
            .fluidInputs(UUMatter.getFluid(8000))
            .outputs(AerospaceCasing.DYSON_SWARM_MODULE_DEPLOYMENT_UNIT_SUPERCONDUCTING_MAGNET.getStack(16))
            .EUt(VA[UHV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(POLARIZER[UHV].stackForm)
                    .EUt(VA[UHV])
                    .CWUt(16)
            }
            .buildAndRegister()

        // Dyson Swarm Control Center Base Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(HULL[UHV], 4)
            .input(HIGH_PERFORMANCE_COMPUTING_ARRAY, 8)
            .input(circuit, Tier.UHV, 4)
            .input(screw, Roentgenium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(Holmium.getFluid(L * 40))
            .fluidInputs(UUMatter.getFluid(8000))
            .outputs(AerospaceCasing.DYSON_SWARM_CONTROL_CENTER_BASE_CASING.getStack(16))
            .EUt(VA[UHV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(HIGH_PERFORMANCE_COMPUTING_ARRAY.stackForm)
                    .EUt(VA[UHV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // Dyson Swarm Control Center Primary Windings
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.MOLECULAR_COIL.getStack(4))
            .input(FEMTO_PIC_CHIP, 8)
            .input(foil, Dysprosium, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(Kevlar.getFluid(L * 24))
            .fluidInputs(UUMatter.getFluid(8000))
            .outputs(AerospaceCasing.DYSON_SWARM_CONTROL_CENTER_PRIMARY_WINDINGS.getStack(4))
            .EUt(VA[UHV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(VOLTAGE_COIL_UHV.stackForm)
                    .EUt(VA[UHV])
                    .CWUt(8)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.MOLECULAR_COIL.getStack(4))
            .input(FEMTO_PIC_CHIP, 8)
            .input(foil, Dysprosium, 64)
            .input(POLYMER_INSULATOR_FOIL, 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(Kevlar.getFluid(L * 24))
            .fluidInputs(UUMatter.getFluid(8000))
            .outputs(AerospaceCasing.DYSON_SWARM_CONTROL_CENTER_PRIMARY_WINDINGS.getStack(4))
            .EUt(VA[UHV])
            .duration(7 * SECOND + 10 * SECOND)
            .stationResearch {
                it.researchStack(VOLTAGE_COIL_UHV.stackForm)
                    .EUt(VA[UHV])
                    .CWUt(8)
            }
            .buildAndRegister()

        // Dyson Swarm Control Center Secondary Windings
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(GTFusionCasing.SUPERCONDUCTOR_COIL.getStack(4))
            .input(spring, TantalumHafniumSeaborgiumCarbide, 8)
            .input(foil, Erbium, 64)
            .input(foil, HastelloyX78, 64)
            .input(foil, Fermium, 64)
            .input(foil, PreciousMetalAlloy, 64)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(Gadolinium.getFluid(L * 16))
            .fluidInputs(UUMatter.getFluid(8000))
            .outputs(AerospaceCasing.DYSON_SWARM_CONTROL_CENTER_SECONDARY_WINDINGS.getStack(4))
            .EUt(VA[UHV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(GTFusionCasing.SUPERCONDUCTOR_COIL.stack)
                    .EUt(VA[UHV])
                    .CWUt(12)
            }
            .buildAndRegister()

        // Dyson Swarm Control Center Toroid Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, MetastableOganesson, 4)
            .input(foil, Terbium, 64)
            .input(foil, Ytterbium, 64)
            .input(foil, Lutetium, 64)
            .input(foil, CosmicNeutronium, 64)
            .input(screw, MetastableFlerovium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L * 80))
            .fluidInputs(UUMatter.getFluid(8000))
            .outputs(AerospaceCasing.DYSON_SWARM_CONTROL_CENTER_TOROID_CASING.getStack(64))
            .EUt(VA[UHV])
            .duration(30 * SECOND)
            .stationResearch {
                it.researchStack(OreDictUnifier.get(frameGt, MetastableOganesson))
                    .EUt(VA[UHV])
                    .CWUt(16)
            }
            .buildAndRegister()

    }

    // @formatter:on

}