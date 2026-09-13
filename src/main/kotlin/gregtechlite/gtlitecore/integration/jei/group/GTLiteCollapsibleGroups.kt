package gregtechlite.gtlitecore.integration.jei.group

import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.extension.unzipSubBlocks
import gregtechlite.gtlitecore.api.extension.unzipSubVariants
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import gregtechlite.gtlitecore.common.block.variant.ComponentAssemblyCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.Manipulator
import gregtechlite.gtlitecore.common.block.variant.NuclearReactorCore
import gregtechlite.gtlitecore.common.block.variant.ShieldingCore
import gregtechlite.gtlitecore.common.block.variant.aerospace.AccelerationTrack
import gregtechlite.gtlitecore.common.block.variant.component.ConveyorCasing
import gregtechlite.gtlitecore.common.block.variant.component.EmitterCasing
import gregtechlite.gtlitecore.common.block.variant.component.FieldGenCasing
import gregtechlite.gtlitecore.common.block.variant.component.MotorCasing
import gregtechlite.gtlitecore.common.block.variant.component.PistonCasing
import gregtechlite.gtlitecore.common.block.variant.component.ProcessorCasing
import gregtechlite.gtlitecore.common.block.variant.component.PumpCasing
import gregtechlite.gtlitecore.common.block.variant.component.RobotArmCasing
import gregtechlite.gtlitecore.common.block.variant.component.SensorCasing
import gregtechlite.gtlitecore.common.block.variant.science.SpacetimeCompressionFieldGenerator
import gregtechlite.gtlitecore.common.block.variant.science.StabilizationFieldGenerator
import gregtechlite.gtlitecore.common.block.variant.science.TimeAccelerationFieldGenerator
import gregtechlite.gtlitecore.common.item.GTLiteMetaItem1
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import mezz.jei.api.ICollapsibleGroupRegistry
import net.minecraft.item.ItemStack

object GTLiteCollapsibleGroups
{
    internal fun registerGroup(registry: ICollapsibleGroupRegistry)
    {
        buildStorageGroups(registry)
        registry.addGroup("sheeted_frame", GTLiteBlocks.SHEETED_FRAME_BLOCKS.unzipSubBlocks())
        registry.addGroup("metal_wall", GTLiteBlocks.METAL_WALL_BLOCKS.unzipSubVariants())

        registry.addCasingGroup<MotorCasing>("motor_casing")
        registry.addCasingGroup<PistonCasing>("piston_casing")
        registry.addCasingGroup<PumpCasing>("pump_casing")
        registry.addCasingGroup<ConveyorCasing>("conveyor_casing")
        registry.addCasingGroup<RobotArmCasing>("robot_arm_casing")
        registry.addCasingGroup<EmitterCasing>("emitter_casing")
        registry.addCasingGroup<SensorCasing>("sensor_casing")
        registry.addCasingGroup<FieldGenCasing>("field_gen_casing")
        registry.addCasingGroup<ProcessorCasing>("processor_casing")
        registry.addCasingGroup<ComponentAssemblyCasing>("component_assembly_casing")
        registry.addCasingGroup<SpacetimeCompressionFieldGenerator>("spacetime_compression_field_generator")
        registry.addCasingGroup<TimeAccelerationFieldGenerator>("time_acceleration_field_generator")
        registry.addCasingGroup<StabilizationFieldGenerator>("stabilization_field_generator")
        registry.addCasingGroup<AccelerationTrack>("acceleration_track")
        registry.addCasingGroup("nuclear_reactor_core", NuclearReactorCore.Enum01.entries, NuclearReactorCore.Enum02.entries)
        registry.addCasingGroup<GlassCasing.Enum01>("borosilicate_glass")
        registry.addCasingGroup<Manipulator>("manipulator")
        registry.addCasingGroup<ShieldingCore>("shielding_core")

        registry.addGroupBy("additional_mold", "shape.mold")
        registry.addGroupBy("additional_extruder", "shape.extruder")
        registry.addGroupBy("slicer_blade", "shape.slicer_blade")
        registry.addGroupBy("force_field", "shape.field")
        registry.addGroupBy("petri_dish", "tool.petri_dish")
        registry.addGroupBy("memory_card", "tool.memory_card")
        registry.addGroupBy("catalyst", "tool.catalyst")
        registry.addGroupBy("wrap")
        registry.addGroupBy("additional_voltage_coil", "additional_voltage_coil")
        registry.addGroupBy("suprachronal_circuit", "circuit.suprachronal")
        registry.addGroupBy("generic_circuit", "circuit.generic")
        registry.addGroupBy("mining_drone")
    }

    private fun buildStorageGroups(registry: ICollapsibleGroupRegistry)
    {
        registry.addGroup("additional_drum", arrayOf(GTLiteMetaTileEntities.IRON_DRUM, GTLiteMetaTileEntities.COPPER_DRUM,
            GTLiteMetaTileEntities.LEAD_DRUM, GTLiteMetaTileEntities.CHROME_DRUM, GTLiteMetaTileEntities.TUNGSTEN_DRUM,
            GTLiteMetaTileEntities.IRIDIUM_DRUM, GTLiteMetaTileEntities.PE_CAN, GTLiteMetaTileEntities.PTFE_CAN,
            GTLiteMetaTileEntities.PBI_CAN, GTLiteMetaTileEntities.KEVLAR_CAN).map { it.stack() })
        registry.addGroup("additional_crate", arrayOf(GTLiteMetaTileEntities.IRON_CRATE, GTLiteMetaTileEntities.COPPER_CRATE,
            GTLiteMetaTileEntities.SILVER_CRATE, GTLiteMetaTileEntities.GOLD_CRATE, GTLiteMetaTileEntities.DIAMOND_CRATE)
            .map { it.stack() })
    }

    private fun ICollapsibleGroupRegistry.addGroupBy(id: String, prefix: String = id): Unit = addGroup(id, filterBy(prefix))

    private fun filterBy(prefix: String): List<ItemStack>
        = GTLiteMetaItem1.META_ITEMS_1.allItems.filter { it.unlocalizedName.startsWith(prefix) }.map { it.stack() }
}