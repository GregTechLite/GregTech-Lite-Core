package gregtechlite.gtlitecore.mixins.gregtech;

import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.widget.Widget;
import com.cleanroommc.modularui.widgets.layout.Flow;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory;
import gregtechlite.gtlitecore.mixins.hooks.Compat;
import gregtechlite.gtlitecore.mixins.hooks.Implemented;
import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@ScheduledForRemoval(inVersion = "Change gregtech to our forked version and update modular ui to latest version")
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2900")
@Compat(modId = { "gregtech", "modularui:3.0.6" })
@Mixin(value = MultiblockUIFactory.class, remap = false)
public abstract class MixinMultiblockUIFactory
{
    @SuppressWarnings("rawtypes")
    @WrapOperation(method = "buildUI",
                   at = @At(value = "INVOKE",
                            target = "Lcom/cleanroommc/modularui/screen/ModularPanel;debugName(Ljava/lang/String;)Lcom/cleanroommc/modularui/widget/Widget;"))
    private Widget gtlitecore$skipRootPanelDebugName(ModularPanel panel, String name,
                                                     Operation<Widget> original)
    {
        return panel;
    }

    @SuppressWarnings("rawtypes")
    @WrapOperation(method = "buildUI",
                   at = @At(value = "INVOKE",
                            target = "Lcom/cleanroommc/modularui/widgets/layout/Flow;debugName(Ljava/lang/String;)Lcom/cleanroommc/modularui/widget/Widget;"))
    private Widget gtlitecore$skipBottomRowDebugName(Flow flow, String name,
                                                     Operation<Widget> original)
    {
        return flow;
    }
}
