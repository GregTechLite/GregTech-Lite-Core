package magicbook.gtlitecore.api.metatileentity.multiblock;

import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import magicbook.gtlitecore.api.gui.factory.TemplateBarBuilder;

import java.util.List;
import java.util.function.UnaryOperator;

public interface ProgressBarMultiblock
{

    int getProgressBarCount();

    // the bar only needs three things
    // progress, texture, and tooltip
    void registerBars(List<UnaryOperator<TemplateBarBuilder>> bars, PanelSyncManager syncManager);

}