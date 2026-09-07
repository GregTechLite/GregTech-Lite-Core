package gregtechlite.gtlitecore.mixins.minecraft.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("all")
@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP
{
    @Shadow
    protected Minecraft mc;
    @Shadow
    private BlockPos currentBlock;
    @Shadow
    private boolean isHittingBlock;

    @Unique
    private boolean gtlitecore$needsRelease;

    @Inject(method = "onPlayerDamageBlock",
            at = @At("HEAD"),
            cancellable = true)
    private void gtlitecore$lockAfterBreak(BlockPos posBlock, EnumFacing directionFacing,
                                           CallbackInfoReturnable<Boolean> cir)
    {
        if (!gtlitecore$isLaserDestroyer()) return;

        if (gtlitecore$needsRelease)
        {
            cir.setReturnValue(false);
            return;
        }

        if (posBlock.equals(currentBlock) && isHittingBlock && mc.world.isAirBlock(posBlock))
        {
            gtlitecore$needsRelease = true;
            isHittingBlock = false;
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "resetBlockRemoving",
            at = @At("HEAD"))
    private void gtlitecore$onRelease(CallbackInfo ci)
    {
        gtlitecore$needsRelease = false;
    }

    @Unique
    private boolean gtlitecore$isLaserDestroyer()
    {
        ItemStack item = mc.player.getHeldItemMainhand();
        return !item.isEmpty() && item.getTranslationKey().contains("laser_destroyer");
    }
}
