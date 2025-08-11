package gregtechlite.gtlitecore.mixins.gregtech.client;

import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

@Mixin(value = MetaBlocks.class, remap = false)
public interface AccessorMetaBlocks
{

    @Invoker("registerItemModel")
    static void registerItemModel(Block block)
    {
        throw new UnsupportedOperationException();
    }

    @Invoker("registerItemModelWithOverride")
    static void registerItemModelWithOverride(Block block,
                                              Map<IProperty<?>, Comparable<?>> stateOverrides)
    {
        throw new UnsupportedOperationException();
    }

}

