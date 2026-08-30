package gregtechlite.gtlitecore.common

import gregtech.api.block.VariantItemBlock
import gregtech.common.blocks.MaterialItemBlock
import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.TranslatableVariantItemBlock
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeBackends
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import gregtechlite.gtlitecore.common.item.DimensionDisplayItemBlock
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems
import gregtechlite.gtlitecore.common.item.SheetedFrameItemBlock
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemSlab
import net.minecraftforge.common.config.Config
import net.minecraftforge.common.config.ConfigManager
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.client.event.ConfigChangedEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@EventBusSubscriber(modid = MOD_ID)
open class CommonProxy
{
    open fun onPreInit()
    {
        GTLiteRecipeMaps.preInit()
        GTLiteMetaOreDictItems.register()
        GTLiteRecipeBackends.init()
    }

    open fun onInit() {}

    open fun onPostInit()
    {
        GTLiteRecipeBackends.postInit()
    }

    companion object
    {
        @SubscribeEvent
        fun syncConfigValues(event: ConfigChangedEvent.OnConfigChangedEvent)
        {
            if (event.modID == MOD_ID)
            {
                ConfigManager.sync(MOD_ID, Config.Type.INSTANCE)
            }
        }

        @SubscribeEvent
        fun registerBlocks(event: RegistryEvent.Register<Block>)
        {
            val registry = event.registry
            LOGGER.info("Registering Blocks...")

            GTLiteBlocks.STONES.values.forEach { registry.register(it) }
            GTLiteBlocks.LEAVES.forEach { registry.register(it) }
            GTLiteBlocks.LOGS.forEach { registry.register(it) }
            GTLiteBlocks.PLANKS.forEach { registry.register(it) }
            GTLiteBlocks.SAPLINGS.forEach { registry.register(it) }
            GTLiteBlocks.CROPS.forEach { registry.register(it) }

            registry.register(GTLiteBlocks.WOOD_SLABS)
            registry.register(GTLiteBlocks.DOUBLE_WOOD_SLABS)

            registry.register(GTLiteBlocks.BANANA_WOOD_STAIR)
            registry.register(GTLiteBlocks.ORANGE_WOOD_STAIR)
            registry.register(GTLiteBlocks.MANGO_WOOD_STAIR)
            registry.register(GTLiteBlocks.APRICOT_WOOD_STAIR)
            registry.register(GTLiteBlocks.LEMON_WOOD_STAIR)
            registry.register(GTLiteBlocks.LIME_WOOD_STAIR)
            registry.register(GTLiteBlocks.OLIVE_WOOD_STAIR)
            registry.register(GTLiteBlocks.NUTMEG_WOOD_STAIR)
            registry.register(GTLiteBlocks.COCONUT_WOOD_STAIR)
            registry.register(GTLiteBlocks.RAINBOW_WOOD_STAIR)

            registry.register(GTLiteBlocks.BANANA_WOOD_FENCE)
            registry.register(GTLiteBlocks.ORANGE_WOOD_FENCE)
            registry.register(GTLiteBlocks.MANGO_WOOD_FENCE)
            registry.register(GTLiteBlocks.APRICOT_WOOD_FENCE)
            registry.register(GTLiteBlocks.LEMON_WOOD_FENCE)
            registry.register(GTLiteBlocks.LIME_WOOD_FENCE)
            registry.register(GTLiteBlocks.OLIVE_WOOD_FENCE)
            registry.register(GTLiteBlocks.NUTMEG_WOOD_FENCE)
            registry.register(GTLiteBlocks.COCONUT_WOOD_FENCE)
            registry.register(GTLiteBlocks.RAINBOW_WOOD_FENCE)

            registry.register(GTLiteBlocks.BANANA_WOOD_FENCE_GATE)
            registry.register(GTLiteBlocks.ORANGE_WOOD_FENCE_GATE)
            registry.register(GTLiteBlocks.MANGO_WOOD_FENCE_GATE)
            registry.register(GTLiteBlocks.APRICOT_WOOD_FENCE_GATE)
            registry.register(GTLiteBlocks.LEMON_WOOD_FENCE_GATE)
            registry.register(GTLiteBlocks.LIME_WOOD_FENCE_GATE)
            registry.register(GTLiteBlocks.OLIVE_WOOD_FENCE_GATE)
            registry.register(GTLiteBlocks.NUTMEG_WOOD_FENCE_GATE)
            registry.register(GTLiteBlocks.COCONUT_WOOD_FENCE_GATE)
            registry.register(GTLiteBlocks.RAINBOW_WOOD_FENCE_GATE)

            registry.register(GTLiteBlocks.DUST_BLOCK)
            registry.register(GTLiteBlocks.DIMENSION_DISPLAY_OVERWORLD)
            registry.register(GTLiteBlocks.DIMENSION_DISPLAY_NETHER)
            registry.register(GTLiteBlocks.DIMENSION_DISPLAY_END)
            registry.register(GTLiteBlocks.BOTTLECRATE)
            registry.register(GTLiteBlocks.NAQUADRIA_CHARGE)
            registry.register(GTLiteBlocks.TARANIUM_CHARGE)
            registry.register(GTLiteBlocks.LEPTONIC_CHARGE)
            registry.register(GTLiteBlocks.QUANTUM_CHROMODYNAMIC_CHARGE)

            GTLiteBlocks.SHEETED_FRAMES.values.distinct().forEach { registry.register(it) }
            GTLiteBlocks.METAL_WALLS.values.distinct().forEach { registry.register(it) }

            registry.register(GTLiteBlocks.MOTOR_CASING)
            registry.register(GTLiteBlocks.PISTON_CASING)
            registry.register(GTLiteBlocks.PUMP_CASING)
            registry.register(GTLiteBlocks.CONVEYOR_CASING)
            registry.register(GTLiteBlocks.ROBOT_ARM_CASING)
            registry.register(GTLiteBlocks.EMITTER_CASING)
            registry.register(GTLiteBlocks.SENSOR_CASING)
            registry.register(GTLiteBlocks.FIELD_GEN_CASING)
            registry.register(GTLiteBlocks.PROCESSOR_CASING)

            registry.register(GTLiteBlocks.PRIMITIVE_CASING)
            registry.register(GTLiteBlocks.METAL_CASING_01)
            registry.register(GTLiteBlocks.METAL_CASING_02)
            registry.register(GTLiteBlocks.METAL_CASING_03)
            registry.register(GTLiteBlocks.BOILER_CASING_01)
            registry.register(GTLiteBlocks.MULTIBLOCK_CASING_01)
            registry.register(GTLiteBlocks.ACTIVE_UNIQUE_CASING_01)
            registry.register(GTLiteBlocks.TURBINE_CASING_01)
            registry.register(GTLiteBlocks.TURBINE_CASING_02)

            registry.register(GTLiteBlocks.FUSION_CASING)
            registry.register(GTLiteBlocks.FUSION_COIL)
            registry.register(GTLiteBlocks.FUSION_CRYOSTAT)
            registry.register(GTLiteBlocks.FUSION_DIVERTOR)
            registry.register(GTLiteBlocks.FUSION_VACUUM)

            registry.register(GTLiteBlocks.SCIENCE_CASING_01)
            registry.register(GTLiteBlocks.SPACETIME_COMPRESSION_FIELD_GENERATOR)
            registry.register(GTLiteBlocks.TIME_ACCELERATION_FIELD_GENERATOR)
            registry.register(GTLiteBlocks.STABILIZATION_FIELD_GENERATOR)

            registry.register(GTLiteBlocks.AEROSPACE_CASING)
            registry.register(GTLiteBlocks.ACCELERATION_TRACK)

            registry.register(GTLiteBlocks.WIRE_COIL)
            registry.register(GTLiteBlocks.CRUCIBLE)
            registry.register(GTLiteBlocks.COMPONENT_ASSEMBLY_CASING)
            registry.register(GTLiteBlocks.NUCLEAR_REACTOR_CORE_01)
            registry.register(GTLiteBlocks.NUCLEAR_REACTOR_CORE_02)
            registry.register(GTLiteBlocks.MANIPULATOR)
            registry.register(GTLiteBlocks.SHIELDING_CORE)

            registry.register(GTLiteBlocks.TRANSPARENT_CASING_01)
            registry.register(GTLiteBlocks.TRANSPARENT_CASING_02)
            registry.register(GTLiteBlocks.TRANSPARENT_CASING_03)
        }

        @SubscribeEvent
        fun registerItems(event: RegistryEvent.Register<Item>)
        {
            val registry = event.registry
            LOGGER.info("Registering Items...")

            GTLiteMetaItems.register()

            GTLiteBlocks.STONES.values.forEach { registry.register(createItemBlock(it, ::VariantItemBlock)) }
            GTLiteBlocks.LEAVES.forEach { registry.register(createItemBlock(it, ::TranslatableVariantItemBlock)) }
            GTLiteBlocks.LOGS.forEach { registry.register(createItemBlock(it, ::TranslatableVariantItemBlock)) }
            GTLiteBlocks.SAPLINGS.forEach { registry.register(createItemBlock(it, ::TranslatableVariantItemBlock)) }
            GTLiteBlocks.PLANKS.forEach { registry.register(createItemBlock(it, ::TranslatableVariantItemBlock)) }

            registry.register(createItemBlock(GTLiteBlocks.WOOD_SLABS) { ItemSlab(it, it, GTLiteBlocks.DOUBLE_WOOD_SLABS) })

            registry.register(createItemBlock(GTLiteBlocks.BANANA_WOOD_STAIR, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.ORANGE_WOOD_STAIR, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.MANGO_WOOD_STAIR, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.APRICOT_WOOD_STAIR, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.LEMON_WOOD_STAIR, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.LIME_WOOD_STAIR, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.OLIVE_WOOD_STAIR, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.NUTMEG_WOOD_STAIR, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.COCONUT_WOOD_STAIR, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.RAINBOW_WOOD_STAIR, ::ItemBlock))

            registry.register(createItemBlock(GTLiteBlocks.BANANA_WOOD_FENCE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.ORANGE_WOOD_FENCE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.MANGO_WOOD_FENCE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.APRICOT_WOOD_FENCE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.LEMON_WOOD_FENCE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.LIME_WOOD_FENCE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.OLIVE_WOOD_FENCE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.NUTMEG_WOOD_FENCE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.COCONUT_WOOD_FENCE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.RAINBOW_WOOD_FENCE, ::ItemBlock))

            registry.register(createItemBlock(GTLiteBlocks.BANANA_WOOD_FENCE_GATE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.ORANGE_WOOD_FENCE_GATE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.MANGO_WOOD_FENCE_GATE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.APRICOT_WOOD_FENCE_GATE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.LEMON_WOOD_FENCE_GATE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.LIME_WOOD_FENCE_GATE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.OLIVE_WOOD_FENCE_GATE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.NUTMEG_WOOD_FENCE_GATE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.COCONUT_WOOD_FENCE_GATE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.RAINBOW_WOOD_FENCE_GATE, ::ItemBlock))

            registry.register(createItemBlock(GTLiteBlocks.DUST_BLOCK, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.DIMENSION_DISPLAY_OVERWORLD, ::DimensionDisplayItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.DIMENSION_DISPLAY_NETHER, ::DimensionDisplayItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.DIMENSION_DISPLAY_END, ::DimensionDisplayItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.BOTTLECRATE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.NAQUADRIA_CHARGE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.TARANIUM_CHARGE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.LEPTONIC_CHARGE, ::ItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.QUANTUM_CHROMODYNAMIC_CHARGE, ::ItemBlock))

            GTLiteBlocks.SHEETED_FRAMES.values.distinct()
                .map { createItemBlock(it, ::SheetedFrameItemBlock) }
                .forEach { registry.register(it) }

            GTLiteBlocks.METAL_WALLS.values.distinct()
                .map { createItemBlock(it) { block -> MaterialItemBlock(block, GTLiteOrePrefix.wallGt) } }
                .forEach { registry.register(it)}

            registry.register(createItemBlock(GTLiteBlocks.MOTOR_CASING, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.PISTON_CASING, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.PUMP_CASING, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.CONVEYOR_CASING, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.ROBOT_ARM_CASING, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.EMITTER_CASING, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.SENSOR_CASING, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.FIELD_GEN_CASING, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.PROCESSOR_CASING, ::VariantItemBlock))

            registry.register(createItemBlock(GTLiteBlocks.PRIMITIVE_CASING, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.METAL_CASING_01, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.METAL_CASING_02, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.METAL_CASING_03, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.BOILER_CASING_01, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.MULTIBLOCK_CASING_01, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.ACTIVE_UNIQUE_CASING_01, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.TURBINE_CASING_01, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.TURBINE_CASING_02, ::VariantItemBlock))

            registry.register(createItemBlock(GTLiteBlocks.FUSION_CASING, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.FUSION_COIL, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.FUSION_CRYOSTAT, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.FUSION_DIVERTOR, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.FUSION_VACUUM, ::VariantItemBlock))

            registry.register(createItemBlock(GTLiteBlocks.SCIENCE_CASING_01, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.SPACETIME_COMPRESSION_FIELD_GENERATOR, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.TIME_ACCELERATION_FIELD_GENERATOR, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.STABILIZATION_FIELD_GENERATOR, ::VariantItemBlock))

            registry.register(createItemBlock(GTLiteBlocks.AEROSPACE_CASING, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.ACCELERATION_TRACK, ::VariantItemBlock))

            registry.register(createItemBlock(GTLiteBlocks.WIRE_COIL, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.CRUCIBLE, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.COMPONENT_ASSEMBLY_CASING, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.NUCLEAR_REACTOR_CORE_01, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.NUCLEAR_REACTOR_CORE_02, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.MANIPULATOR, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.SHIELDING_CORE, ::VariantItemBlock))

            registry.register(createItemBlock(GTLiteBlocks.TRANSPARENT_CASING_01, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.TRANSPARENT_CASING_02, ::VariantItemBlock))
            registry.register(createItemBlock(GTLiteBlocks.TRANSPARENT_CASING_03, ::VariantItemBlock))
        }

        private fun <T : Block> createItemBlock(block: T, producer: (T) -> ItemBlock): ItemBlock
        {
            val itemBlock = producer(block)
            val registryName = block.registryName
            requireNotNull(registryName) { "Block '${block.translationKey}' has no registry name." }
            itemBlock.registryName = registryName
            return itemBlock
        }
    }
}