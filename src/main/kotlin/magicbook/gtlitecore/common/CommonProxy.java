package magicbook.gtlitecore.common;

import gregtech.api.block.VariantItemBlock;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.utils.GTLiteLog;
import magicbook.gtlitecore.api.utils.GTLiteValues;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.GTLiteStoneVariantBlock;
import magicbook.gtlitecore.common.item.GTLiteMetaItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = GTLiteValues.MODID)
public class CommonProxy
{

    public void preLoad()
    {
        GTLiteRecipeMaps.postRecipeMaps();
    }

    @SubscribeEvent
    public static void registerBlocks(@NotNull RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();
        GTLiteLog.logger.info("Registering Blocks...");
        // Register all stones in GTLiteStoneVariant.
        for (GTLiteStoneVariantBlock stone : GTLiteMetaBlocks.STONES.values())
            registry.register(stone);
        registry.register(GTLiteMetaBlocks.MOTOR_CASING);
        registry.register(GTLiteMetaBlocks.PISTON_CASING);
        registry.register(GTLiteMetaBlocks.PUMP_CASING);
        registry.register(GTLiteMetaBlocks.CONVEYOR_CASING);
        registry.register(GTLiteMetaBlocks.ROBOT_ARM_CASING);
        registry.register(GTLiteMetaBlocks.EMITTER_CASING);
        registry.register(GTLiteMetaBlocks.SENSOR_CASING);
        registry.register(GTLiteMetaBlocks.FIELD_GEN_CASING);
        registry.register(GTLiteMetaBlocks.PRIMITIVE_CASING);
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
        registry.register(createItemBlock(GTLiteMetaBlocks.MOTOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.PISTON_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.PUMP_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.CONVEYOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.ROBOT_ARM_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.EMITTER_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.SENSOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.FIELD_GEN_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GTLiteMetaBlocks.PRIMITIVE_CASING, VariantItemBlock::new));
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
