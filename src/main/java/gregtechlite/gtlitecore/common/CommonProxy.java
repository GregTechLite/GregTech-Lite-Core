package gregtechlite.gtlitecore.common;

import gregtech.api.block.VariantItemBlock;
import gregtech.common.blocks.MaterialItemBlock;
import gregtechlite.gtlitecore.api.block.TranslatableVariantItemBlock;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeBackends;
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps;
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix;
import gregtechlite.gtlitecore.api.GTLiteLog;
import gregtechlite.gtlitecore.common.block.GTLiteBlocks;
import gregtechlite.gtlitecore.common.block.GTLiteCropBlock;
import gregtechlite.gtlitecore.common.block.GTLiteStoneVariantBlock;
import gregtechlite.gtlitecore.common.item.DimensionDisplayItemBlock;
import gregtechlite.gtlitecore.common.item.GTLiteMetaItem1;
import gregtechlite.gtlitecore.common.item.GTLiteMetaItem2;
import gregtechlite.gtlitecore.common.item.SheetedFrameItemBlock;
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems;
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

import static gregtechlite.gtlitecore.api.GTLiteValues.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class CommonProxy
{

    // region FML Lifecycle Registries

    public void onPreInit()
    {
        GTLiteRecipeMaps.preInit();
        GTLiteMetaOreDictItems.register();
        GTLiteRecipeBackends.init();
    }

    public void onInit()
    {
    }

    public void onPostInit()
    {
        GTLiteRecipeBackends.postInit();
    }

    // endregion

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(MOD_ID))
        {
            ConfigManager.sync(MOD_ID, Config.Type.INSTANCE);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(@NotNull RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();
        GTLiteLog.logger.info("Registering Blocks...");

        // Register all stones in GTLiteStoneVariant.
        for (GTLiteStoneVariantBlock stone : GTLiteBlocks.STONES.values())
            registry.register(stone);

        // Register all tree components.
        GTLiteBlocks.LEAVES.forEach(registry::register);
        GTLiteBlocks.LOGS.forEach(registry::register);
        GTLiteBlocks.PLANKS.forEach(registry::register);
        GTLiteBlocks.SAPLINGS.forEach(registry::register);
        // Register all crops.
        GTLiteCropBlock.CROPS.forEach(registry::register);
        // TODO berries registrate?
        // Wooden slabs.
        registry.register(GTLiteBlocks.WOOD_SLABS);
        registry.register(GTLiteBlocks.DOUBLE_WOOD_SLABS);
        // Wooden stairs.
        registry.register(GTLiteBlocks.BANANA_WOOD_STAIR);
        registry.register(GTLiteBlocks.ORANGE_WOOD_STAIR);
        registry.register(GTLiteBlocks.MANGO_WOOD_STAIR);
        registry.register(GTLiteBlocks.APRICOT_WOOD_STAIR);
        registry.register(GTLiteBlocks.LEMON_WOOD_STAIR);
        registry.register(GTLiteBlocks.LIME_WOOD_STAIR);
        registry.register(GTLiteBlocks.OLIVE_WOOD_STAIR);
        registry.register(GTLiteBlocks.NUTMEG_WOOD_STAIR);
        registry.register(GTLiteBlocks.COCONUT_WOOD_STAIR);
        registry.register(GTLiteBlocks.RAINBOW_WOOD_STAIR);
        // Wooden fences.
        registry.register(GTLiteBlocks.BANANA_WOOD_FENCE);
        registry.register(GTLiteBlocks.ORANGE_WOOD_FENCE);
        registry.register(GTLiteBlocks.MANGO_WOOD_FENCE);
        registry.register(GTLiteBlocks.APRICOT_WOOD_FENCE);
        registry.register(GTLiteBlocks.LEMON_WOOD_FENCE);
        registry.register(GTLiteBlocks.LIME_WOOD_FENCE);
        registry.register(GTLiteBlocks.OLIVE_WOOD_FENCE);
        registry.register(GTLiteBlocks.NUTMEG_WOOD_FENCE);
        registry.register(GTLiteBlocks.COCONUT_WOOD_FENCE);
        registry.register(GTLiteBlocks.RAINBOW_WOOD_FENCE);
        // Wooden fence gates.
        registry.register(GTLiteBlocks.BANANA_WOOD_FENCE_GATE);
        registry.register(GTLiteBlocks.ORANGE_WOOD_FENCE_GATE);
        registry.register(GTLiteBlocks.MANGO_WOOD_FENCE_GATE);
        registry.register(GTLiteBlocks.APRICOT_WOOD_FENCE_GATE);
        registry.register(GTLiteBlocks.LEMON_WOOD_FENCE_GATE);
        registry.register(GTLiteBlocks.LIME_WOOD_FENCE_GATE);
        registry.register(GTLiteBlocks.OLIVE_WOOD_FENCE_GATE);
        registry.register(GTLiteBlocks.NUTMEG_WOOD_FENCE_GATE);
        registry.register(GTLiteBlocks.COCONUT_WOOD_FENCE_GATE);
        registry.register(GTLiteBlocks.RAINBOW_WOOD_FENCE_GATE);

        registry.register(GTLiteBlocks.DUST_BLOCK);
        registry.register(GTLiteBlocks.DIMENSION_DISPLAY_OVERWORLD);
        registry.register(GTLiteBlocks.DIMENSION_DISPLAY_NETHER);
        registry.register(GTLiteBlocks.DIMENSION_DISPLAY_END);
        registry.register(GTLiteBlocks.NAQUADRIA_CHARGE);
        registry.register(GTLiteBlocks.TARANIUM_CHARGE);
        registry.register(GTLiteBlocks.LEPTONIC_CHARGE);
        registry.register(GTLiteBlocks.QUANTUM_CHROMODYNAMIC_CHARGE);

        // TODO Crops?...

        // Sheeted frames.
        GTLiteBlocks.SHEETED_FRAMES.values().stream()
                .distinct()
                .forEach(registry::register);

        // Gregtech Walls
        GTLiteBlocks.METAL_WALLS.values().stream()
                .distinct()
                .forEach(registry::register);

        // Register all common variant blocks.
        registry.register(GTLiteBlocks.MOTOR_CASING);
        registry.register(GTLiteBlocks.PISTON_CASING);
        registry.register(GTLiteBlocks.PUMP_CASING);
        registry.register(GTLiteBlocks.CONVEYOR_CASING);
        registry.register(GTLiteBlocks.ROBOT_ARM_CASING);
        registry.register(GTLiteBlocks.EMITTER_CASING);
        registry.register(GTLiteBlocks.SENSOR_CASING);
        registry.register(GTLiteBlocks.FIELD_GEN_CASING);
        registry.register(GTLiteBlocks.PROCESSOR_CASING);

        registry.register(GTLiteBlocks.PRIMITIVE_CASING);
        registry.register(GTLiteBlocks.METAL_CASING_01);
        registry.register(GTLiteBlocks.METAL_CASING_02);
        registry.register(GTLiteBlocks.METAL_CASING_03);
        registry.register(GTLiteBlocks.BOILER_CASING_01);
        registry.register(GTLiteBlocks.MULTIBLOCK_CASING_01);
        registry.register(GTLiteBlocks.ACTIVE_UNIQUE_CASING_01);
        registry.register(GTLiteBlocks.TURBINE_CASING_01);
        registry.register(GTLiteBlocks.TURBINE_CASING_02);

        registry.register(GTLiteBlocks.FUSION_CASING);
        registry.register(GTLiteBlocks.FUSION_COIL);
        registry.register(GTLiteBlocks.FUSION_CRYOSTAT);
        registry.register(GTLiteBlocks.FUSION_DIVERTOR);
        registry.register(GTLiteBlocks.FUSION_VACUUM);

        registry.register(GTLiteBlocks.SCIENCE_CASING_01);
        registry.register(GTLiteBlocks.SPACETIME_COMPRESSION_FIELD_GENERATOR);
        registry.register(GTLiteBlocks.TIME_ACCELERATION_FIELD_GENERATOR);
        registry.register(GTLiteBlocks.STABILIZATION_FIELD_GENERATOR);

        registry.register(GTLiteBlocks.AEROSPACE_CASING);
        registry.register(GTLiteBlocks.ACCELERATION_TRACK);

        registry.register(GTLiteBlocks.WIRE_COIL);
        registry.register(GTLiteBlocks.CRUCIBLE);
        registry.register(GTLiteBlocks.COMPONENT_ASSEMBLY_CASING);
        registry.register(GTLiteBlocks.NUCLEAR_REACTOR_CORE_01);
        registry.register(GTLiteBlocks.NUCLEAR_REACTOR_CORE_02);
        registry.register(GTLiteBlocks.MANIPULATOR);
        registry.register(GTLiteBlocks.SHIELDING_CORE);

        registry.register(GTLiteBlocks.TRANSPARENT_CASING_01);
        registry.register(GTLiteBlocks.TRANSPARENT_CASING_02);
        registry.register(GTLiteBlocks.TRANSPARENT_CASING_03);
    }

    @SubscribeEvent
    public static void registerItems(@NotNull RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
        GTLiteLog.logger.info("Registering Items...");
        // Register all items.
        GTLiteMetaItem1.register();
        GTLiteMetaItem2.register();
        // Register all item blocks.
        for (GTLiteStoneVariantBlock stone : GTLiteBlocks.STONES.values())
            registry.register(createItemBlock(stone, VariantItemBlock::new));
        GTLiteBlocks.LEAVES.forEach(t ->
                registry.register(createItemBlock(t, TranslatableVariantItemBlock::new)));
        GTLiteBlocks.LOGS.forEach(t ->
                registry.register(createItemBlock(t, TranslatableVariantItemBlock::new)));
        GTLiteBlocks.SAPLINGS.forEach(t ->
                registry.register(createItemBlock(t, TranslatableVariantItemBlock::new)));
        GTLiteBlocks.PLANKS.forEach(t ->
                registry.register(createItemBlock(t, TranslatableVariantItemBlock::new)));

        registry.register(createItemBlock(GTLiteBlocks.WOOD_SLABS,
                t -> new ItemSlab(t, t, GTLiteBlocks.DOUBLE_WOOD_SLABS)));

        registry.register(createItemBlock(GTLiteBlocks.BANANA_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.ORANGE_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.MANGO_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.APRICOT_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.LEMON_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.LIME_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.OLIVE_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.NUTMEG_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.COCONUT_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.RAINBOW_WOOD_STAIR, ItemBlock::new));

        registry.register(createItemBlock(GTLiteBlocks.BANANA_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.ORANGE_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.MANGO_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.APRICOT_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.LEMON_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.LIME_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.OLIVE_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.NUTMEG_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.COCONUT_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.RAINBOW_WOOD_FENCE, ItemBlock::new));

        registry.register(createItemBlock(GTLiteBlocks.BANANA_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.ORANGE_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.MANGO_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.APRICOT_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.LEMON_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.LIME_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.OLIVE_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.NUTMEG_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.COCONUT_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.RAINBOW_WOOD_FENCE_GATE, ItemBlock::new));

        registry.register(createItemBlock(GTLiteBlocks.DUST_BLOCK, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.DIMENSION_DISPLAY_OVERWORLD, DimensionDisplayItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.DIMENSION_DISPLAY_NETHER, DimensionDisplayItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.DIMENSION_DISPLAY_END, DimensionDisplayItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.NAQUADRIA_CHARGE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.TARANIUM_CHARGE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.LEPTONIC_CHARGE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.QUANTUM_CHROMODYNAMIC_CHARGE, ItemBlock::new));

        GTLiteBlocks.SHEETED_FRAMES.values().stream()
                .distinct()
                .map(block -> createItemBlock(block, SheetedFrameItemBlock::new))
                .forEach(registry::register);

        GTLiteBlocks.METAL_WALLS.values().stream()
                .distinct()
                .map(block -> createItemBlock(block, blockWall -> new MaterialItemBlock(blockWall, GTLiteOrePrefix.wallGt)))
                .forEach(registry::register);

        registry.register(createItemBlock(GTLiteBlocks.MOTOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.PISTON_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.PUMP_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.CONVEYOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.ROBOT_ARM_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.EMITTER_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.SENSOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.FIELD_GEN_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.PROCESSOR_CASING, VariantItemBlock::new));

        registry.register(createItemBlock(GTLiteBlocks.PRIMITIVE_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.METAL_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.METAL_CASING_02, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.METAL_CASING_03, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.BOILER_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.MULTIBLOCK_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.ACTIVE_UNIQUE_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.TURBINE_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.TURBINE_CASING_02, VariantItemBlock::new));

        registry.register(createItemBlock(GTLiteBlocks.FUSION_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.FUSION_COIL, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.FUSION_CRYOSTAT, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.FUSION_DIVERTOR, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.FUSION_VACUUM, VariantItemBlock::new));

        registry.register(createItemBlock(GTLiteBlocks.SCIENCE_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.SPACETIME_COMPRESSION_FIELD_GENERATOR, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.TIME_ACCELERATION_FIELD_GENERATOR, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.STABILIZATION_FIELD_GENERATOR, VariantItemBlock::new));

        registry.register(createItemBlock(GTLiteBlocks.AEROSPACE_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.ACCELERATION_TRACK, VariantItemBlock::new));

        registry.register(createItemBlock(GTLiteBlocks.WIRE_COIL, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.CRUCIBLE, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.COMPONENT_ASSEMBLY_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.NUCLEAR_REACTOR_CORE_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.NUCLEAR_REACTOR_CORE_02, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.MANIPULATOR, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.SHIELDING_CORE, VariantItemBlock::new));

        registry.register(createItemBlock(GTLiteBlocks.TRANSPARENT_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.TRANSPARENT_CASING_02, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteBlocks.TRANSPARENT_CASING_03, VariantItemBlock::new));
    }

    private static <T extends Block> ItemBlock createItemBlock(T block,
                                                               Function<T, ItemBlock> producer)
    {
        ItemBlock itemBlock = producer.apply(block);
        ResourceLocation registryName = block.getRegistryName();
        if (registryName == null)
        {
            throw new IllegalArgumentException("Block " + block.getTranslationKey() + " has no registry name.");
        }
        else
        {
            itemBlock.setRegistryName(registryName);
            return itemBlock;
        }
    }

}
