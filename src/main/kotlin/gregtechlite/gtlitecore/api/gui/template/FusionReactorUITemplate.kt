package gregtechlite.gtlitecore.api.gui.template

import com.cleanroommc.modularui.api.drawable.IDrawable
import com.cleanroommc.modularui.value.sync.DoubleSyncValue
import com.cleanroommc.modularui.value.sync.LongSyncValue
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.widgets.ProgressWidget
import com.cleanroommc.modularui.widgets.layout.Column
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.metatileentity.multiblock.ui.TemplateBarBuilder
import gregtech.api.mui.GTGuiTextures
import gregtech.api.util.KeyUtil
import net.minecraft.util.text.TextFormatting
import java.util.function.UnaryOperator

@Suppress("UnstableApiUsage")
object FusionReactorUITemplate
{

    fun crateTemplateUI(titleTexture: IDrawable,
                        mte: MultiblockWithDisplayBase, recipeMapWorkable: MultiblockRecipeLogic): MultiblockUIFactory
    {
        val progress = DoubleSyncValue(recipeMapWorkable::getProgressPercent)
        return MultiblockUIFactory(mte)
            .setScreenHeight(138)
            .disableDisplayText()
            .addScreenChildren { parent, guiSyncManager ->
                val status = MultiblockUIFactory.builder("status", guiSyncManager)
                status.setAction { builder -> builder.structureFormed(true) }
                status.setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
                status.addWorkingStatusLine()

                parent.child(Column())
                    .padding(4)
                    .expanded()
                    .child(titleTexture.asWidget()
                               .marginBottom(8)
                               .size(69, 12))
                    .child(
                        ProgressWidget()
                        .size(77, 77)
                        .tooltipAutoUpdate(true)
                        .tooltipBuilder(status::build)
                        .background(GTGuiTextures.FUSION_DIAGRAM.asIcon()
                                        .size(89, 101)
                                        .marginTop(11))
                        .direction(ProgressWidget.Direction.CIRCULAR_CW)
                        .value(progress)
                        .texture(null, GTGuiTextures.FUSION_PROGRESS, 77))
                    .child(GTGuiTextures.FUSION_LEGEND.asWidget()
                               .left(4)
                               .bottom(4)
                               .size(108, 41))
            }
    }

    fun createTemplateBars(templateBars: MutableList<UnaryOperator<TemplateBarBuilder>>, guiSyncManager: PanelSyncManager,
                           energyContainer: IEnergyContainer, heatGetter: () -> Long)
    {
        val capacity = LongSyncValue(energyContainer::getEnergyCapacity)
        guiSyncManager.syncValue("capacity", capacity)

        val storedEnergy = LongSyncValue(energyContainer::getEnergyStored)
        guiSyncManager.syncValue("stored_energy", storedEnergy)

        val heat = LongSyncValue(heatGetter)
        guiSyncManager.syncValue("heat", heat)

        templateBars.add { templateBar -> templateBar
            .progress {
                if (capacity.longValue > 0L)
                    return@progress 1.0 * storedEnergy.longValue / capacity.longValue
                else return@progress 0.0
            }
            .texture(GTGuiTextures.PROGRESS_BAR_FUSION_ENERGY)
            .tooltipBuilder { tooltip ->
                tooltip.add(KeyUtil.lang(TextFormatting.GRAY,
                                         "gregtech.multiblock.energy_stored", storedEnergy.longValue, capacity.longValue))
            }
        }

        templateBars.add { templateBar -> templateBar
            .progress {
                if (capacity.longValue > 0)
                    return@progress 1.0 * heat.longValue / capacity.longValue
                else
                    return@progress 0.0
            }
            .texture(GTGuiTextures.PROGRESS_BAR_FUSION_HEAT)
            .tooltipBuilder { tooltip ->
                val heatInfo = KeyUtil.string(TextFormatting.AQUA,
                                              "%,d / %,d EU", heat.longValue, capacity.longValue)
                tooltip.add(KeyUtil.lang(TextFormatting.GRAY,
                                         "gregtech.multiblock.fusion_reactor.heat", heatInfo))
            }
        }

    }

}