package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtechlite.gtlitecore.api.pattern.BlockPatterns;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = MultiblockControllerBase.class, remap = false)
public abstract class MixinMultiblockControllerBase
{

    @Shadow
    @Nullable
    public BlockPattern structurePattern;

    @Inject(method = "getMatchingShapes()Ljava/util/List;",
            at = @At(value = "INVOKE",
                     target = "Lgregtech/api/metatileentity/multiblock/MultiblockControllerBase;repetitionDFS(Ljava/util/List;[[ILjava/util/Stack;)Ljava/util/List;"),
            cancellable = true)
    private void injectMatchingShapes(CallbackInfoReturnable<List<MultiblockShapeInfo>> cir)
    {
        // noinspection ConstantConditions
        cir.setReturnValue(BlockPatterns.INSTANCE.getMatchingShapes(structurePattern));
    }

}
