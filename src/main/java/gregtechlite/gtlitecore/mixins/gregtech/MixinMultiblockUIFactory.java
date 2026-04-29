package gregtechlite.gtlitecore.mixins.gregtech;

import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widget.Widget;
import com.cleanroommc.modularui.widgets.SlotGroupWidget;
import com.cleanroommc.modularui.widgets.layout.Flow;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.metatileentity.multiblock.ProgressBarMultiblock;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory.Bars;
import gregtech.api.mui.GTGuis;
import gregtechlite.gtlitecore.mixins.Compat;
import gregtechlite.gtlitecore.mixins.Implemented;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

/**
 * @deprecated
 */
@Deprecated
@Implemented(at = "https://github.com/GregTechCEu/GregTech/pull/2900")
@Compat(modId = { "gregtech", "modularui" })
@Mixin(value = MultiblockUIFactory.class, remap = false)
public abstract class MixinMultiblockUIFactory
{

    @Shadow
    private int width;

    @Shadow
    private int height;

    @Shadow
    private MultiblockUIFactory.ScreenFunction screenFunction;

    @Shadow
    private boolean disableDisplay;

    @Shadow
    private boolean disableButtons;

    /**
     * @author Magic_Sweepy
     * @reason For Modular UI 3.0.6+ version, the name of a {@link ModularPanel} be final, so it cannot be rewritten by
     *         the legacy {@code debugName} method. We should make compatible with Retro Sophisticated Backpacks mod,
     *         but GTCEu only compatible with Modular UI 3.0.4, so we used mixin to fix this method call in GTCEu.
     */
    @NotNull
    @Overwrite
    public ModularPanel buildUI(PosGuiData guiData, PanelSyncManager syncManager)
    {
        MultiblockWithDisplayBase mte = ((AccessorMultiblockUIFactory) this).mte();

        ModularPanel panel = GTGuis.createPanel(mte, width, height)
                .childIf(!disableDisplay, () -> createScreen(syncManager));

        if (mte instanceof ProgressBarMultiblock && ((ProgressBarMultiblock) mte).hasBars())
        {
            ProgressBarMultiblock progressBarMTE = ((ProgressBarMultiblock) mte);
            panel.height(height + (Bars.HEIGHT * gtlitecore$calculateRows(progressBarMTE.getProgressBarCount())) - 2);
            panel.child(createBars(progressBarMTE, syncManager));
        }

        if (disableDisplay && screenFunction != null)
        {
            screenFunction.addWidgets(panel, syncManager);
        }

        SlotGroupWidget playerInv = SlotGroupWidget.playerInventory(false);
        if (disableButtons)
        {
            playerInv.alignX(0.5f);
        }
        else
        {
            playerInv.left(4);
        }

        return panel.child(Flow.row()
                .bottom(7)
                .coverChildrenHeight()
                .margin(4, 0)
                .crossAxisAlignment(Alignment.CrossAxis.CENTER)
                .child(playerInv)
                .childIf(!disableButtons, () -> createButtons(panel, syncManager, guiData)));
    }

    @NotNull
    @Shadow
    protected abstract Flow createButtons(@NotNull ModularPanel mainPanel, @NotNull PanelSyncManager panelSyncManager, PosGuiData guiData);

    @Nullable
    @Shadow
    protected abstract Flow createBars(@NotNull ProgressBarMultiblock progressMulti, @NotNull PanelSyncManager panelSyncManager);

    @Shadow
    protected abstract Widget<?> createScreen(PanelSyncManager syncManager);

    @Unique
    private static int gtlitecore$calculateRows(int count)
    {
        if (count <= 3)
        {
            return 1;
        }

        if (count <= 8)
        {
            return 2;
        }

        throw new UnsupportedOperationException("Cannot compute progress bar rows for count " + count);
    }

}
