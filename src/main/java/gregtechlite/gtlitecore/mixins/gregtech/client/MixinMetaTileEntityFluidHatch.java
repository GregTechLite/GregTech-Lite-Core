package gregtechlite.gtlitecore.mixins.gregtech.client;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityFluidHatch;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockNotifiablePart;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MetaTileEntityFluidHatch.class, remap = false)
public abstract class MixinMetaTileEntityFluidHatch extends MetaTileEntityMultiblockNotifiablePart
{
    public MixinMetaTileEntityFluidHatch(ResourceLocation metaTileEntityId, int tier, boolean isExportHatch)
    {
        super(metaTileEntityId, tier, isExportHatch);
    }

    @Inject(method = "renderMetaTileEntity",
            at = @At("TAIL"))
    private void gtlitecore$renderChannelOverlay(CCRenderState renderState, Matrix4 translation,
                                                 IVertexOperation[] pipeline, CallbackInfo callbackInfo)
    {

        if (!(shouldRenderOverlay() || !isPainted()))
            return;

        boolean isExport = gtlitecore$hasExportAbility();
        SimpleOverlayRenderer renderer = isExport ? Textures.PIPE_OUT_OVERLAY : Textures.PIPE_IN_OVERLAY;
        EnumFacing frontFacing = getFrontFacing();
        IVertexOperation[] tintedPipeline = ArrayUtils.add(pipeline,
                new ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering())));
        renderer.renderSided(frontFacing, renderState, translation, tintedPipeline);
    }

    @Unique
    private boolean gtlitecore$hasExportAbility()
    {
        IMultiblockAbilityPart<?> abilityPart = (IMultiblockAbilityPart<?>) this;
        return abilityPart.getAbilities().contains(MultiblockAbility.EXPORT_FLUIDS)
                || abilityPart.getAbilities().contains(MultiblockAbility.EXPORT_ITEMS);
    }
}
