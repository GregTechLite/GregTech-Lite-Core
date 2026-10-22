package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.common.items.behaviors.MultiblockBuilderBehavior;
import gregtechlite.gtlitecore.mixins.hooks.MultiblockBuilderHooks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MultiblockBuilderBehavior.class, remap = false)
public abstract class MixinMultiblockBuilderBehavior
{
    @Inject(method = "onItemUseFirst",
            at = @At("HEAD"),
            cancellable = true)
    private void gtlitecore$advancedAutoBuild(EntityPlayer player, World world, BlockPos pos,
                                              EnumFacing side, float hitX, float hitY, float hitZ,
                                              EnumHand hand, CallbackInfoReturnable<EnumActionResult> cir)
    {
        EnumActionResult result = MultiblockBuilderHooks.onItemUseFirst(player, world, pos, side, hand);
        if (result != null)
            cir.setReturnValue(result);
    }
}
