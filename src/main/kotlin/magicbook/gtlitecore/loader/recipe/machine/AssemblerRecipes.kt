package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Cupronickel
import gregtech.api.unification.material.Materials.Glue
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Nichrome
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.RTMAlloy
import gregtech.api.unification.material.Materials.RedAlloy
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.pipeNonupleFluid
import gregtech.api.unification.ore.OrePrefix.pipeQuadrupleFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.common.blocks.BlockWireCoil
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH_4A
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_OUTPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_OUTPUT_HATCH_4A
import gregtech.common.metatileentities.MetaTileEntities.FLUID_EXPORT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.FLUID_IMPORT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.POWER_TRANSFORMER
import gregtech.common.metatileentities.MetaTileEntities.TRANSFORMER
import magicbook.gtlitecore.api.utils.GTLiteUtility
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockConveyorCasing
import magicbook.gtlitecore.common.block.blocks.BlockEmitterCasing
import magicbook.gtlitecore.common.block.blocks.BlockFieldGenCasing
import magicbook.gtlitecore.common.block.blocks.BlockMotorCasing
import magicbook.gtlitecore.common.block.blocks.BlockPistonCasing
import magicbook.gtlitecore.common.block.blocks.BlockProcessorCasing
import magicbook.gtlitecore.common.block.blocks.BlockPumpCasing
import magicbook.gtlitecore.common.block.blocks.BlockRobotArmCasing
import magicbook.gtlitecore.common.block.blocks.BlockSensorCasing
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MICA_INSULATOR_FOIL
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.CHROME_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.COPPER_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.DYNAMO_HATCH_16A
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.DYNAMO_HATCH_4A
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ENERGY_HATCH_16A
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.ENERGY_HATCH_4A
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.IRIDIUM_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.IRON_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.LEAD_DRUM
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.NONUPLE_FLUID_EXPORT_HATCH
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.NONUPLE_FLUID_IMPORT_HATCH
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.QUADRUPLE_FLUID_EXPORT_HATCH
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.QUADRUPLE_FLUID_IMPORT_HATCH
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SUBSTATION_DYNAMO_HATCH_64A
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.SUBSTATION_ENERGY_HATCH_64A
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.TUNGSTEN_DRUM

@Suppress("MISSING_DEPENDENCY_CLASS")
class AssemblerRecipes
{

    companion object
    {

        fun init()
        {
            // Iron Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Iron, 2)
                .input(plate, Iron, 4)
                .output(IRON_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Copper Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Copper, 2)
                .input(plate, Copper, 4)
                .output(COPPER_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Lead Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Lead, 2)
                .input(plate, Lead, 4)
                .output(LEAD_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Chrome Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Chrome, 2)
                .input(plate, Chrome, 4)
                .output(CHROME_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Tungsten Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Tungsten, 2)
                .input(plate, Tungsten, 4)
                .output(TUNGSTEN_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Iridium Drum
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stickLong, Iridium, 2)
                .input(plate, Iridium, 4)
                .output(IRIDIUM_DRUM)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Component Casings.
            val motorCasings = arrayOf(
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.LV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.MV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.HV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.EV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.IV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.LuV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.ZPM),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.UV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.UHV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.UEV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.UIV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.UXV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.OpV),
                GTLiteMetaBlocks.MOTOR_CASING.getItemVariant(BlockMotorCasing.MotorCasingTier.MAX))

            val pistonCasings = arrayOf(
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.LV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.MV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.HV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.EV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.IV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.LuV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.ZPM),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.UV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.UHV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.UEV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.UIV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.UXV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.OpV),
                GTLiteMetaBlocks.PISTON_CASING.getItemVariant(BlockPistonCasing.PistonCasingTier.MAX))

            val pumpCasings = arrayOf(
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.LV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.MV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.HV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.EV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.IV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.LuV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.ZPM),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.UV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.UHV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.UEV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.UIV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.UXV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.OpV),
                GTLiteMetaBlocks.PUMP_CASING.getItemVariant(BlockPumpCasing.PumpCasingTier.MAX))

            val conveyorCasings = arrayOf(
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.LV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.MV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.HV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.EV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.IV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.LuV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.ZPM),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.UV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.UHV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.UEV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.UIV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.UXV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.OpV),
                GTLiteMetaBlocks.CONVEYOR_CASING.getItemVariant(BlockConveyorCasing.ConveyorCasingTier.MAX))

            val robotArmCasings = arrayOf(
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.LV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.MV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.HV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.EV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.IV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.LuV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.ZPM),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.UV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.UHV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.UEV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.UIV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.UXV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.OpV),
                GTLiteMetaBlocks.ROBOT_ARM_CASING.getItemVariant(BlockRobotArmCasing.RobotArmCasingTier.MAX))

            val emitterCasings = arrayOf(
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.LV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.MV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.HV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.EV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.IV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.LuV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.ZPM),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.UV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.UHV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.UEV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.UIV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.UXV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.OpV),
                GTLiteMetaBlocks.EMITTER_CASING.getItemVariant(BlockEmitterCasing.EmitterCasingTier.MAX))

            val sensorCasings = arrayOf(
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.LV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.MV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.HV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.EV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.IV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.LuV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.ZPM),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.UV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.UHV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.UEV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.UIV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.UXV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.OpV),
                GTLiteMetaBlocks.SENSOR_CASING.getItemVariant(BlockSensorCasing.SensorCasingTier.MAX))

            val fieldGenCasings = arrayOf(
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.LV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.MV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.HV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.EV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.IV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.LuV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.ZPM),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.UV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.UHV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.UEV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.UIV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.UXV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.OpV),
                GTLiteMetaBlocks.FIELD_GEN_CASING.getItemVariant(BlockFieldGenCasing.FieldGenCasingTier.MAX))

            val processorCasings = arrayOf(
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.LV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.MV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.HV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.EV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.IV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.LuV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.ZPM),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.UV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.UHV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.UEV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.UIV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.UXV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.OpV),
                GTLiteMetaBlocks.PROCESSOR_CASING.getItemVariant(BlockProcessorCasing.ProcessorCasingTier.MAX))

            for (i in motorCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(ring, Steel, 2)
                    .input(GTLiteUtility.getMotorByTier(i + 1))
                    .outputs(motorCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }

            for (i in pistonCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(gearSmall, Steel, 2)
                    .input(GTLiteUtility.getPistonByTier(i + 1))
                    .outputs(pistonCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }

            for (i in pumpCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(rotor, Steel, 2)
                    .input(GTLiteUtility.getPumpByTier(i + 1))
                    .outputs(pumpCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }

            for (i in conveyorCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(round, Steel, 2)
                    .input(GTLiteUtility.getConveyorByTier(i + 1))
                    .outputs(conveyorCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }

            for (i in robotArmCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(gear, Steel, 2)
                    .input(GTLiteUtility.getRobotArmByTier(i + 1))
                    .outputs(robotArmCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }

            for (i in emitterCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(foil, Steel, 2)
                    .input(GTLiteUtility.getEmitterByTier(i + 1))
                    .outputs(emitterCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }

            for (i in sensorCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(wireFine, Steel, 2)
                    .input(GTLiteUtility.getSensorByTier(i + 1))
                    .outputs(sensorCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }

            for (i in fieldGenCasings.indices)
            {
                ASSEMBLER_RECIPES.recipeBuilder()
                    .circuitMeta(9)
                    .input(frameGt, Steel)
                    .input(plate, Steel, 4)
                    .input(wireGtSingle, Steel, 2)
                    .input(GTLiteUtility.getFieldGenByTier(i + 1))
                    .outputs(fieldGenCasings[i])
                    .EUt(VA[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }

            // For my testing, used GTLiteUtility#getCircuitByTier() will caused
            // circuit cannot be circuitX, so...

            // LV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.LV)
                .outputs(processorCasings[0])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // MV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.MV)
                .outputs(processorCasings[1])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // HV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.HV)
                .outputs(processorCasings[2])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // EV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.EV)
                .outputs(processorCasings[3])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // IV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.IV)
                .outputs(processorCasings[4])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // LuV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.LuV)
                .outputs(processorCasings[5])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // ZPM Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .outputs(processorCasings[6])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // UV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.UV)
                .outputs(processorCasings[7])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // UHV Processor Casing
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(spring, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .outputs(processorCasings[8])
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // TODO UEV-MAX

            // ULV 4A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_INPUT_HATCH[ULV])
                .input(wireGtQuadruple, RedAlloy, 2)
                .input(plate, WroughtIron, 2)
                .output(ENERGY_HATCH_4A[0])
                .EUt(1L)
                .duration(5 * SECOND)
                .buildAndRegister()

            // LV 4A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_INPUT_HATCH[LV])
                .input(wireGtQuadruple, Tin, 2)
                .input(plate, Steel, 2)
                .output(ENERGY_HATCH_4A[1])
                .EUt(VA[ULV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // MV 4A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_INPUT_HATCH[MV])
                .input(wireGtQuadruple, Copper, 2)
                .input(plate, Aluminium, 2)
                .output(ENERGY_HATCH_4A[2])
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // HV 4A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_INPUT_HATCH[HV])
                .input(wireGtQuadruple, Gold, 2)
                .input(plate, StainlessSteel, 2)
                .output(ENERGY_HATCH_4A[3])
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // ULV 4A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_OUTPUT_HATCH[ULV])
                .input(wireGtQuadruple, RedAlloy, 2)
                .input(plate, WroughtIron, 2)
                .output(DYNAMO_HATCH_4A[0])
                .EUt(1L)
                .duration(5 * SECOND)
                .buildAndRegister()

            // LV 4A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_OUTPUT_HATCH[LV])
                .input(wireGtQuadruple, Tin, 2)
                .input(plate, Steel, 2)
                .output(DYNAMO_HATCH_4A[1])
                .EUt(VA[ULV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // MV 4A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_OUTPUT_HATCH[MV])
                .input(wireGtQuadruple, Copper, 2)
                .input(plate, Aluminium, 2)
                .output(DYNAMO_HATCH_4A[2])
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // HV 4A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(ENERGY_OUTPUT_HATCH[HV])
                .input(wireGtQuadruple, Gold, 2)
                .input(plate, StainlessSteel, 2)
                .output(DYNAMO_HATCH_4A[3])
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // ULV 16A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[ULV])
                .input(ENERGY_HATCH_4A[0])
                .input(wireGtOctal, RedAlloy, 2)
                .input(plate, WroughtIron, 4)
                .output(ENERGY_HATCH_16A[0])
                .EUt(1L)
                .duration(10 * SECOND)
                .buildAndRegister()

            // LV 16A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[LV])
                .input(ENERGY_HATCH_4A[1])
                .input(wireGtOctal, Tin, 2)
                .input(plate, Steel, 4)
                .output(ENERGY_HATCH_16A[1])
                .EUt(VA[ULV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // MV 16A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[MV])
                .input(ENERGY_HATCH_4A[2])
                .input(wireGtOctal, Copper, 2)
                .input(plate, Aluminium, 4)
                .output(ENERGY_HATCH_16A[2])
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // HV 16A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[HV])
                .input(ENERGY_HATCH_4A[3])
                .input(wireGtOctal, Gold, 2)
                .input(plate, StainlessSteel, 4)
                .output(ENERGY_HATCH_16A[3])
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // EV 16A Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[EV])
                .input(ENERGY_INPUT_HATCH_4A[0])
                .input(wireGtOctal, Aluminium, 2)
                .input(plate, Titanium, 4)
                .output(ENERGY_HATCH_16A[4])
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // ULV 16A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[ULV])
                .input(DYNAMO_HATCH_4A[0])
                .input(wireGtOctal, RedAlloy, 2)
                .input(plate, WroughtIron, 4)
                .output(DYNAMO_HATCH_16A[0])
                .EUt(1L)
                .duration(10 * SECOND)
                .buildAndRegister()

            // LV 16A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[LV])
                .input(DYNAMO_HATCH_4A[1])
                .input(wireGtOctal, Tin, 2)
                .input(plate, Steel, 4)
                .output(DYNAMO_HATCH_16A[1])
                .EUt(VA[ULV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // MV 16A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[MV])
                .input(DYNAMO_HATCH_4A[2])
                .input(wireGtOctal, Copper, 2)
                .input(plate, Aluminium, 4)
                .output(DYNAMO_HATCH_16A[2])
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // HV 16A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[HV])
                .input(DYNAMO_HATCH_4A[3])
                .input(wireGtOctal, Gold, 2)
                .input(plate, StainlessSteel, 4)
                .output(DYNAMO_HATCH_16A[3])
                .EUt(VA[MV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // EV 16A Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(TRANSFORMER[EV])
                .input(ENERGY_OUTPUT_HATCH_4A[0])
                .input(wireGtOctal, Aluminium, 2)
                .input(plate, Titanium, 4)
                .output(DYNAMO_HATCH_16A[4])
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // ULV 64A Substation Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[ULV])
                .input(ENERGY_HATCH_16A[0])
                .input(wireGtHex, RedAlloy, 2)
                .input(plate, WroughtIron, 6)
                .output(SUBSTATION_ENERGY_HATCH_64A[0])
                .EUt(1L)
                .duration(20 * SECOND)
                .buildAndRegister()

            // LV 64A Substation Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[LV])
                .input(ENERGY_HATCH_16A[1])
                .input(wireGtHex, Tin, 2)
                .input(plate, Steel, 6)
                .output(SUBSTATION_ENERGY_HATCH_64A[1])
                .EUt(VA[ULV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // MV 64A Substation Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[MV])
                .input(ENERGY_HATCH_16A[2])
                .input(wireGtHex, Copper, 2)
                .input(plate, Aluminium, 6)
                .output(SUBSTATION_ENERGY_HATCH_64A[2])
                .EUt(VA[LV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // HV 64A Substation Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[HV])
                .input(ENERGY_HATCH_16A[3])
                .input(wireGtHex, Gold, 2)
                .input(plate, StainlessSteel, 6)
                .output(SUBSTATION_ENERGY_HATCH_64A[3])
                .EUt(VA[MV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // EV 64A Substation Energy Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[EV])
                .input(ENERGY_HATCH_16A[4])
                .input(wireGtHex, Aluminium, 2)
                .input(plate, Titanium, 6)
                .output(SUBSTATION_ENERGY_HATCH_64A[4])
                .EUt(VA[MV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // ULV 64A Substation Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[ULV])
                .input(DYNAMO_HATCH_16A[0])
                .input(wireGtHex, RedAlloy, 2)
                .input(plate, WroughtIron, 6)
                .output(SUBSTATION_DYNAMO_HATCH_64A[0])
                .EUt(1L)
                .duration(20 * SECOND)
                .buildAndRegister()

            // LV 64A Substation Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[LV])
                .input(DYNAMO_HATCH_16A[1])
                .input(wireGtHex, Tin, 2)
                .input(plate, Steel, 6)
                .output(SUBSTATION_DYNAMO_HATCH_64A[1])
                .EUt(VA[ULV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // MV 64A Substation Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[MV])
                .input(DYNAMO_HATCH_16A[2])
                .input(wireGtHex, Copper, 2)
                .input(plate, Aluminium, 6)
                .output(SUBSTATION_DYNAMO_HATCH_64A[2])
                .EUt(VA[LV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // HV 64A Substation Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[HV])
                .input(DYNAMO_HATCH_16A[3])
                .input(wireGtHex, Gold, 2)
                .input(plate, StainlessSteel, 6)
                .output(SUBSTATION_DYNAMO_HATCH_64A[3])
                .EUt(VA[MV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // EV 64A Substation Dynamo Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[EV])
                .input(DYNAMO_HATCH_16A[4])
                .input(wireGtHex, Aluminium, 2)
                .input(plate, Titanium, 6)
                .output(SUBSTATION_DYNAMO_HATCH_64A[4])
                .EUt(VA[MV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // ULV Quadruple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_IMPORT_HATCH[ULV])
                .input(pipeQuadrupleFluid, TinAlloy)
                .fluidInputs(Glue.getFluid(1000))
                .output(QUADRUPLE_FLUID_IMPORT_HATCH[0])
                .EUt(VA[ULV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // LV Quadruple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_IMPORT_HATCH[LV])
                .input(pipeQuadrupleFluid, Copper)
                .fluidInputs(Glue.getFluid(2000))
                .output(QUADRUPLE_FLUID_IMPORT_HATCH[1])
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // MV Quadruple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_IMPORT_HATCH[MV])
                .input(pipeQuadrupleFluid, Steel)
                .fluidInputs(Polyethylene.getFluid(L * 4))
                .output(QUADRUPLE_FLUID_IMPORT_HATCH[2])
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // HV Quadruple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_IMPORT_HATCH[HV])
                .input(pipeQuadrupleFluid, StainlessSteel)
                .fluidInputs(Polyethylene.getFluid(L * 4))
                .output(QUADRUPLE_FLUID_IMPORT_HATCH[3])
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // ULV Quadruple Output Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_EXPORT_HATCH[ULV])
                .input(pipeQuadrupleFluid, TinAlloy)
                .fluidInputs(Glue.getFluid(1000))
                .output(QUADRUPLE_FLUID_EXPORT_HATCH[0])
                .EUt(VA[ULV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // LV Quadruple Output Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_EXPORT_HATCH[LV])
                .input(pipeQuadrupleFluid, Copper)
                .fluidInputs(Glue.getFluid(2000))
                .output(QUADRUPLE_FLUID_EXPORT_HATCH[1])
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // MV Quadruple Output Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_EXPORT_HATCH[MV])
                .input(pipeQuadrupleFluid, Steel)
                .fluidInputs(Polyethylene.getFluid(L * 4))
                .output(QUADRUPLE_FLUID_EXPORT_HATCH[2])
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // HV Quadruple Output Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(FLUID_EXPORT_HATCH[HV])
                .input(pipeQuadrupleFluid, StainlessSteel)
                .fluidInputs(Polyethylene.getFluid(L * 4))
                .output(QUADRUPLE_FLUID_EXPORT_HATCH[3])
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // ULV Nonuple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_IMPORT_HATCH[ULV])
                .input(pipeNonupleFluid, TinAlloy)
                .fluidInputs(Glue.getFluid(2250))
                .output(NONUPLE_FLUID_IMPORT_HATCH[0])
                .EUt(VA[ULV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // LV Nonuple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_IMPORT_HATCH[LV])
                .input(pipeNonupleFluid, Copper)
                .fluidInputs(Glue.getFluid(4500))
                .output(NONUPLE_FLUID_IMPORT_HATCH[1])
                .EUt(VA[LV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // MV Nonuple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_IMPORT_HATCH[MV])
                .input(pipeNonupleFluid, Steel)
                .fluidInputs(Polyethylene.getFluid(L * 9))
                .output(NONUPLE_FLUID_IMPORT_HATCH[2])
                .EUt(VA[MV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // HV Nonuple Input Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_IMPORT_HATCH[HV])
                .input(pipeNonupleFluid, StainlessSteel)
                .fluidInputs(Polyethylene.getFluid(L * 9))
                .output(NONUPLE_FLUID_IMPORT_HATCH[3])
                .EUt(VA[HV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // ULV Nonuple Export Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_EXPORT_HATCH[ULV])
                .input(pipeNonupleFluid, TinAlloy)
                .fluidInputs(Glue.getFluid(2250))
                .output(NONUPLE_FLUID_EXPORT_HATCH[0])
                .EUt(VA[ULV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // LV Nonuple Export Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_EXPORT_HATCH[LV])
                .input(pipeNonupleFluid, Copper)
                .fluidInputs(Glue.getFluid(4500))
                .output(NONUPLE_FLUID_EXPORT_HATCH[1])
                .EUt(VA[LV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // MV Nonuple Export Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_EXPORT_HATCH[MV])
                .input(pipeNonupleFluid, Steel)
                .fluidInputs(Polyethylene.getFluid(L * 9))
                .output(NONUPLE_FLUID_EXPORT_HATCH[2])
                .EUt(VA[MV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // HV Nonuple Export Hatch
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(FLUID_EXPORT_HATCH[HV])
                .input(pipeNonupleFluid, StainlessSteel)
                .fluidInputs(Polyethylene.getFluid(L * 9))
                .output(NONUPLE_FLUID_EXPORT_HATCH[3])
                .EUt(VA[HV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Make wire coils easier than original recipes, these new recipes do not
            // need metal foils and instead of mica insulator foils.

            // Cupronickel Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, Cupronickel, 8),
                        OreDictUnifier.get(foil, Bronze, 8)),
                arrayOf(TinAlloy.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Cupronickel, 8)
                .input(MICA_INSULATOR_FOIL, 8)
                .fluidInputs(TinAlloy.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL, 1))
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Kanthal Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, Kanthal, 8),
                    OreDictUnifier.get(foil, Aluminium, 8)),
                arrayOf(Copper.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Kanthal, 8)
                .input(MICA_INSULATOR_FOIL, 12)
                .fluidInputs(Copper.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.KANTHAL, 1))
                .EUt(VA[MV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Nichrome Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, Nichrome, 8),
                    OreDictUnifier.get(foil, StainlessSteel, 8)),
                arrayOf(Aluminium.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Nichrome, 8)
                .input(MICA_INSULATOR_FOIL, 16)
                .fluidInputs(Aluminium.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NICHROME, 1))
                .EUt(VA[HV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // RTM Alloy Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, RTMAlloy, 8),
                    OreDictUnifier.get(foil, VanadiumSteel, 8)),
                arrayOf(Nichrome.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, RTMAlloy, 8)
                .input(MICA_INSULATOR_FOIL, 20)
                .fluidInputs(Titanium.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.RTM_ALLOY, 1))
                .EUt(VA[EV].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // HSS-G Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, HSSG, 8),
                    OreDictUnifier.get(foil, TungstenCarbide, 8)),
                arrayOf(Tungsten.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, HSSG, 8)
                .input(MICA_INSULATOR_FOIL, 24)
                .fluidInputs(Tungsten.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.HSS_G, 1))
                .EUt(VA[IV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Naquadah Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, Naquadah, 8),
                    OreDictUnifier.get(foil, Osmium, 8)),
                arrayOf(TungstenSteel.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Naquadah, 8)
                .input(MICA_INSULATOR_FOIL, 28)
                .fluidInputs(TungstenSteel.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NAQUADAH, 1))
                .EUt(VA[LuV].toLong())
                .duration(35 * SECOND)
                .buildAndRegister()

            // Trinium Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, Trinium, 8),
                    OreDictUnifier.get(foil, NaquadahEnriched, 8)),
                arrayOf(Naquadah.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Trinium, 8)
                .input(MICA_INSULATOR_FOIL, 32)
                .fluidInputs(Naquadah.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TRINIUM, 1))
                .EUt(VA[ZPM].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Tritanium Wire Coil
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(wireGtDouble, Tritanium, 8),
                    OreDictUnifier.get(foil, Naquadria, 8)),
                arrayOf(Trinium.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtDouble, Tritanium, 8)
                .input(MICA_INSULATOR_FOIL, 36)
                .fluidInputs(NaquadahEnriched.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TRITANIUM, 1))
                .EUt(VA[UV].toLong())
                .duration(45 * SECOND)
                .buildAndRegister()

            // TODO UHV-MAX Wire Coils.

        }

    }

}