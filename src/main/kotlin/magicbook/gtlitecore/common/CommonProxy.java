package magicbook.gtlitecore.common;

import gregtech.api.block.VariantItemBlock;
import gregtech.common.blocks.MaterialItemBlock;
import magicbook.gtlitecore.api.block.impl.TranslatableVariantItemBlock;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.GTLiteStoneVariantBlock;
import magicbook.gtlitecore.common.block.itemblocks.SheetedFrameItemBlock;
import magicbook.gtlitecore.common.item.GTLiteMetaItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import one.util.streamex.StreamEx;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = GTLiteValues.MODID)
public class CommonProxy
{

    public void preLoad()
    {
        GTLiteRecipeMaps.preInit();
    }

    @SubscribeEvent
    public static void registerBlocks(@NotNull RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();
        GTLiteLog.logger.info("Registering Blocks...");
        // Register all stones in GTLiteStoneVariant.
        for (GTLiteStoneVariantBlock stone : GTLiteMetaBlocks.STONES.values())
            registry.register(stone);
        // Register all tree components.
        GTLiteMetaBlocks.LEAVES.forEach(registry::register);
        GTLiteMetaBlocks.LOGS.forEach(registry::register);
        GTLiteMetaBlocks.PLANKS.forEach(registry::register);
        GTLiteMetaBlocks.SAPLINGS.forEach(registry::register);
        // Wooden slabs.
        registry.register(GTLiteMetaBlocks.WOOD_SLABS);
        registry.register(GTLiteMetaBlocks.DOUBLE_WOOD_SLABS);
        // Wooden stairs.
        registry.register(GTLiteMetaBlocks.BANANA_WOOD_STAIR);
        registry.register(GTLiteMetaBlocks.ORANGE_WOOD_STAIR);
        registry.register(GTLiteMetaBlocks.MANGO_WOOD_STAIR);
        registry.register(GTLiteMetaBlocks.APRICOT_WOOD_STAIR);
        registry.register(GTLiteMetaBlocks.LEMON_WOOD_STAIR);
        registry.register(GTLiteMetaBlocks.LIME_WOOD_STAIR);
        registry.register(GTLiteMetaBlocks.OLIVE_WOOD_STAIR);
        registry.register(GTLiteMetaBlocks.NUTMEG_WOOD_STAIR);
        registry.register(GTLiteMetaBlocks.COCONUT_WOOD_STAIR);
        registry.register(GTLiteMetaBlocks.RAINBOW_WOOD_STAIR);
        // Wooden fences.
        registry.register(GTLiteMetaBlocks.BANANA_WOOD_FENCE);
        registry.register(GTLiteMetaBlocks.ORANGE_WOOD_FENCE);
        registry.register(GTLiteMetaBlocks.MANGO_WOOD_FENCE);
        registry.register(GTLiteMetaBlocks.APRICOT_WOOD_FENCE);
        registry.register(GTLiteMetaBlocks.LEMON_WOOD_FENCE);
        registry.register(GTLiteMetaBlocks.LIME_WOOD_FENCE);
        registry.register(GTLiteMetaBlocks.OLIVE_WOOD_FENCE);
        registry.register(GTLiteMetaBlocks.NUTMEG_WOOD_FENCE);
        registry.register(GTLiteMetaBlocks.COCONUT_WOOD_FENCE);
        registry.register(GTLiteMetaBlocks.RAINBOW_WOOD_FENCE);
        // Wooden fence gates.
        registry.register(GTLiteMetaBlocks.BANANA_WOOD_FENCE_GATE);
        registry.register(GTLiteMetaBlocks.ORANGE_WOOD_FENCE_GATE);
        registry.register(GTLiteMetaBlocks.MANGO_WOOD_FENCE_GATE);
        registry.register(GTLiteMetaBlocks.APRICOT_WOOD_FENCE_GATE);
        registry.register(GTLiteMetaBlocks.LEMON_WOOD_FENCE_GATE);
        registry.register(GTLiteMetaBlocks.LIME_WOOD_FENCE_GATE);
        registry.register(GTLiteMetaBlocks.OLIVE_WOOD_FENCE_GATE);
        registry.register(GTLiteMetaBlocks.NUTMEG_WOOD_FENCE_GATE);
        registry.register(GTLiteMetaBlocks.COCONUT_WOOD_FENCE_GATE);
        registry.register(GTLiteMetaBlocks.RAINBOW_WOOD_FENCE_GATE);

        // TODO Crops?...

        // Sheeted frames.
        StreamEx.of(GTLiteMetaBlocks.SHEETED_FRAMES.values())
                .distinct()
                .forEach(registry::register);

        // Gregtech Walls
        StreamEx.of(GTLiteMetaBlocks.WALLS.values())
                .distinct()
                .forEach(registry::register);

        // Register all common variant blocks.
        registry.register(GTLiteMetaBlocks.MOTOR_CASING);
        registry.register(GTLiteMetaBlocks.PISTON_CASING);
        registry.register(GTLiteMetaBlocks.PUMP_CASING);
        registry.register(GTLiteMetaBlocks.CONVEYOR_CASING);
        registry.register(GTLiteMetaBlocks.ROBOT_ARM_CASING);
        registry.register(GTLiteMetaBlocks.EMITTER_CASING);
        registry.register(GTLiteMetaBlocks.SENSOR_CASING);
        registry.register(GTLiteMetaBlocks.FIELD_GEN_CASING);
        registry.register(GTLiteMetaBlocks.PROCESSOR_CASING);
        registry.register(GTLiteMetaBlocks.PRIMITIVE_CASING);
        registry.register(GTLiteMetaBlocks.METAL_CASING_01);
        registry.register(GTLiteMetaBlocks.METAL_CASING_02);
        registry.register(GTLiteMetaBlocks.METAL_CASING_03);
        registry.register(GTLiteMetaBlocks.BOILER_CASING_01);
        registry.register(GTLiteMetaBlocks.MULTIBLOCK_CASING_01);
        registry.register(GTLiteMetaBlocks.ACTIVE_UNIQUE_CASING_01);
        registry.register(GTLiteMetaBlocks.FUSION_CASING_01);
        registry.register(GTLiteMetaBlocks.FUSION_CASING_02);
        registry.register(GTLiteMetaBlocks.FUSION_CASING_03);
        registry.register(GTLiteMetaBlocks.WIRE_COIL);
        registry.register(GTLiteMetaBlocks.CRUCIBLE);
        registry.register(GTLiteMetaBlocks.TRANSPARENT_CASING_01);
        registry.register(GTLiteMetaBlocks.TRANSPARENT_CASING_02);
    }

    @SubscribeEvent
    public static void registerItems(@NotNull RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
        GTLiteLog.logger.info("Registering Items...");
        // Register all items.
        GTLiteMetaItems.register();
        // Register all item blocks.
        for (GTLiteStoneVariantBlock stone : GTLiteMetaBlocks.STONES.values())
            registry.register(createItemBlock(stone, VariantItemBlock::new));
        GTLiteMetaBlocks.LEAVES.forEach(t ->
                registry.register(createItemBlock(t, TranslatableVariantItemBlock::new)));
        GTLiteMetaBlocks.LOGS.forEach(t ->
                registry.register(createItemBlock(t, TranslatableVariantItemBlock::new)));
        GTLiteMetaBlocks.SAPLINGS.forEach(t ->
                registry.register(createItemBlock(t, TranslatableVariantItemBlock::new)));
        GTLiteMetaBlocks.PLANKS.forEach(t ->
                registry.register(createItemBlock(t, TranslatableVariantItemBlock::new)));

        registry.register(createItemBlock(GTLiteMetaBlocks.WOOD_SLABS,
                t -> new ItemSlab(t, t, GTLiteMetaBlocks.DOUBLE_WOOD_SLABS)));

        registry.register(createItemBlock(GTLiteMetaBlocks.BANANA_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.ORANGE_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.MANGO_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.APRICOT_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.LEMON_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.LIME_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.OLIVE_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.NUTMEG_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.COCONUT_WOOD_STAIR, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.RAINBOW_WOOD_STAIR, ItemBlock::new));

        registry.register(createItemBlock(GTLiteMetaBlocks.BANANA_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.ORANGE_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.MANGO_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.APRICOT_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.LEMON_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.LIME_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.OLIVE_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.NUTMEG_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.COCONUT_WOOD_FENCE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.RAINBOW_WOOD_FENCE, ItemBlock::new));

        registry.register(createItemBlock(GTLiteMetaBlocks.BANANA_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.ORANGE_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.MANGO_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.APRICOT_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.LEMON_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.LIME_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.OLIVE_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.NUTMEG_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.COCONUT_WOOD_FENCE_GATE, ItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.RAINBOW_WOOD_FENCE_GATE, ItemBlock::new));

        StreamEx.of(GTLiteMetaBlocks.SHEETED_FRAMES.values())
                .distinct()
                .map(b -> createItemBlock(b, SheetedFrameItemBlock::new))
                .forEach(registry::register);

        StreamEx.of(GTLiteMetaBlocks.WALLS.values())
                .distinct()
                .map(b -> createItemBlock(b, d -> new MaterialItemBlock(d, GTLiteOrePrefix.wallGt)))
                .forEach(registry::register);

        registry.register(createItemBlock(GTLiteMetaBlocks.MOTOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.PISTON_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.PUMP_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.CONVEYOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.ROBOT_ARM_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.EMITTER_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.SENSOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.FIELD_GEN_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.PROCESSOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.PRIMITIVE_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.METAL_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.METAL_CASING_02, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.METAL_CASING_03, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.BOILER_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.MULTIBLOCK_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.ACTIVE_UNIQUE_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.FUSION_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.FUSION_CASING_02, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.FUSION_CASING_03, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.WIRE_COIL, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.CRUCIBLE, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.TRANSPARENT_CASING_01, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.TRANSPARENT_CASING_02, VariantItemBlock::new));
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
