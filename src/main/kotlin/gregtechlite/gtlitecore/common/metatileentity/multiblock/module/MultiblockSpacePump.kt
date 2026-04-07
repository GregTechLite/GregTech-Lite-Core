package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import com.cleanroommc.modularui.api.IPanelHandler
import com.cleanroommc.modularui.api.drawable.IDrawable
import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.api.widget.IWidget
import com.cleanroommc.modularui.drawable.DynamicDrawable
import com.cleanroommc.modularui.factory.PosGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.utils.Alignment
import com.cleanroommc.modularui.utils.Color
import com.cleanroommc.modularui.utils.NumberFormat
import com.cleanroommc.modularui.value.sync.IntSyncValue
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.value.sync.StringSyncValue
import com.cleanroommc.modularui.widget.ParentWidget
import com.cleanroommc.modularui.widget.Widget
import com.cleanroommc.modularui.widgets.ButtonWidget
import com.cleanroommc.modularui.widgets.ListWidget
import com.cleanroommc.modularui.widgets.TextWidget
import com.cleanroommc.modularui.widgets.layout.Column
import com.cleanroommc.modularui.widgets.layout.Flow
import com.cleanroommc.modularui.widgets.layout.Row
import com.cleanroommc.modularui.widgets.textfield.TextFieldWidget
import gregtech.api.GTValues
import gregtech.api.capability.IMultipleTankHandler
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.mui.GTGuiTextures
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import gregtechlite.gtlitecore.api.gui.sync.FluidDisplaySyncHandler
import gregtechlite.gtlitecore.api.gui.widget.DisplayOnlyFluidSlot
import gregtechlite.gtlitecore.api.metatileentity.multiblock.ModuleMultiblockBase
import gregtechlite.gtlitecore.api.recipe.frontend.SpacePumpRecipeFrontend
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.aerospace.AerospaceCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.common.util.Constants
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.function.Consumer
import kotlin.math.max

class MultiblockSpacePump(id: ResourceLocation, tier: Int, moduleTier: Int, minCasingTier: Int)
    : ModuleMultiblockBase(id, tier, moduleTier, minCasingTier)
{

    private var outputFluidInventory: IMultipleTankHandler? = null

    private val planets = intArrayOf(0, 0, 0, 0)
    private val fluids = intArrayOf(0, 0, 0, 0)
    private val parallels = intArrayOf(0, 0, 0, 0)

    init
    {
        maxProgress = if (moduleTier == 3) SECOND else 4 * SECOND
    }

    companion object
    {
        private val casingState = AerospaceCasing.ELEVATOR_BASE_CASING.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity =
        MultiblockSpacePump(metaTileEntityId, tier, moduleTier, minCasingTier)

    override fun initializeAbilities()
    {
        outputFluidInventory = FluidTankList(true, getAbilities(EXPORT_FLUIDS))
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("C", "C", "C", "C", "C")
        .aisle("C", "C", "C", "S", "C")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .or(abilities(EXPORT_FLUIDS)
                .setPreviewCount(0)))
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.SPACE_ELEVATOR_BASE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.SPACE_ELEVATOR_OVERLAY

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        for (renderSide in EnumFacing.HORIZONTALS)
        {
            if (renderSide == getFrontFacing())
            {
                getFrontOverlay().renderOrientedState(
                        renderState,
                        translation,
                        pipeline,
                        getFrontFacing(),
                        isActive,
                        true
                )
            }
            else
            {
                GTLiteOverlays.SPACE_PUMP_OVERLAY.renderSided(renderSide, renderState, translation, pipeline)
            }
        }
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.tooltip.1"))
        when (moduleTier)
        {
            1    -> tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.mk1.tooltip.1"))
            2    -> tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.mk2.tooltip.1"))
            else -> tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.mk3.tooltip.1"))
        }
        tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.tooltip.2"))
        if (moduleTier == 1) tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.tooltip.3"))
        else tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.tooltip.4"))
        if (moduleTier == 3) tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.tooltip.5"))

        tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.max_parallel", GTValues.VNF[tier]))
        tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.track_tier", getTrackTier(moduleTier)))
    }

    private fun getTrackTier(moduleTier: Int) = when (moduleTier)
    {
        1    -> "MK1"
        2    -> "MK2"
        else -> "MK4"
    }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(isWorkingEnabled, isActive)
            .addEnergyTierLine(this.tier)
            .addParallelsLine(this.getParallelLimit())
            .addWorkingStatusLine()
    }

    private fun createFluidRow(index: Int, syncManager: PanelSyncManager): Flow
    {
        val curPlanet = IntSyncValue { this.planets[index] }

        val curFluid = IntSyncValue { this.fluids[index] }

        val curParallel = IntSyncValue { this.parallels[index] }

        syncManager.syncValue("Planet_${index}", curPlanet)
        syncManager.syncValue("Fluid_${index}", curFluid)
        syncManager.syncValue("Parallel_${index}", curParallel)


        val curOutput = FluidDisplaySyncHandler {
            if (SpacePumpRecipeFrontend.RECIPES.containsKey(Pair(planets[index], fluids[index])))
                return@FluidDisplaySyncHandler SpacePumpRecipeFrontend.RECIPES[Pair(planets[index], fluids[index])]
            else return@FluidDisplaySyncHandler null
        }

        syncManager.syncValue("Output_${index}", curOutput)

        return Row()
            .coverChildren()
            .child(
                    TextWidget(IKey.lang("gtlitecore.machine.space_pump_module.fluid", index + 1))
                        .marginRight(5)
                        .color(Color.WHITE.main))
            .child(
                    DisplayOnlyFluidSlot()
                        .syncHandler(curOutput)
                        .tooltipBuilder { t ->
                            val fluidStack: FluidStack? = curOutput.value
                            if (fluidStack == null)
                            {
                                t.clearText().addLine(IKey.lang("gtlitecore.machine.space_pump_module.fluid.empty"))
                            }
                            else
                            {
                                t.clearText()
                                    .addLine(KeyUtil.fluid(fluidStack))
                                    .add(IKey.lang("gtlitecore.machine.space_pump_module.fluid.efficient",
                                                   NumberFormat.format(
                                                           fluidStack.amount.toDouble(),
                                                           NumberFormat.DEFAULT)))
                            }
                        }.tooltipAutoUpdate(true)
            )
            .child(
                    IKey.dynamic {
                        if (curOutput.value == null || curParallel.value == 0)
                            return@dynamic ""
                        val fluidStack: FluidStack = curOutput.value!!
                        return@dynamic NumberFormat.format(fluidStack.amount.toDouble() * curParallel.value,
                                                           NumberFormat.DEFAULT) + "L/s"
                    }.asWidget()
                        .marginLeft(5)
                        .color(Color.ORANGE.main))
            .marginBottom(5)
            .marginLeft(5)
    }

    override fun createUIFactory(): MultiblockUIFactory
    {
        return SpacePumpUIFactory(this)
            .configureWarningText(this::configureWarningText)
            .configureErrorText(this::configureErrorText)
            .configureDisplayText(this::configureDisplayText)
            .addScreenChildren { parent, syncManager ->
                val fluidRows = PumpListWidget()

                // TODO: add background for fluid display?
                parent.childIf(this.isStructureFormed,
                               fluidRows
                                   .widthRel(1F)
                                   .heightRelOffset(1F, -50)
                                   .pos(100, 64)
                                   .margin(4)
                                   .children(getFluidParallels()) {
                                       createFluidRow(it, syncManager)
                                   }
                                   .crossAxisAlignment(Alignment.CrossAxis.START)
                )
            }
    }

    private fun getFluidParallels() = when (moduleTier)
    {
        1    -> 1
        else -> 4
    }

    fun getParallelLimit() = when (moduleTier)
    {
        1    -> 1
        2    -> 4
        else -> 16
    }

    private fun getPlanetValue(index: Int): String = planets[index].toString()

    private fun setPlanetValue(index: Int, value: String)
    {
        if (value.isEmpty()) return
        planets[index] = value.toInt()
    }

    private fun getFluidValue(index: Int): String = fluids[index].toString()

    private fun setFluidValue(index: Int, value: String)
    {
        if (value.isEmpty()) return
        fluids[index] = value.toInt()
    }

    private fun getParallelValue(index: Int): String = parallels[index].toString()

    private fun setParallelValue(index: Int, value: String)
    {
        if (value.isEmpty()) return
        parallels[index] = value.toInt()
    }

    override fun updateFormedValid()
    {
        super.updateFormedValid()
        if (!isWorkingEnabled()) return

        val totalEnergyConsumed = parallels.sum() * energyContainer.inputVoltage

        if (!drainEnergy(true, totalEnergyConsumed))
        {
            if (progress >= 2 * TICK)
            {
                progress = if (ConfigHolder.machines.recipeProgressLowEnergy) TICK else max(TICK, progress - 2 * TICK)
            }
            return
        }

        if (progress == 0 && !checkRecipes())
        {
            setActive(false)
        }
        else
        {
            drainEnergy(false, totalEnergyConsumed)
            setActive(true)

            progress++
            if (progress % maxProgress != 0) return
            progress = 0

            val fluidStacks = ArrayList<FluidStack>()
            for (i in 0..3)
            {
                if (SpacePumpRecipeFrontend.RECIPES.containsKey(Pair(planets[i], fluids[i])))
                {
                    SpacePumpRecipeFrontend.RECIPES[Pair(planets[i], fluids[i])]?.let {
                        val outputFluid = it.copy()
                        outputFluid.amount = outputFluid.amount.times(parallels[i])
                        fluidStacks.add(outputFluid)
                    }
                }
            }
            fluidStacks.forEach { fluidStack ->
                outputFluidInventory?.fill(fluidStack, true)
            }
        }
    }

    private fun checkRecipes(): Boolean
    {
        if (moduleTier > 1)
        {
            for (i in 0..3)
            {
                val fluidStack = SpacePumpRecipeFrontend.RECIPES[Pair(planets[i], fluids[i])]
                if (fluidStack != null)
                    return true
            }
        }
        else
        {
            val fluidStack = SpacePumpRecipeFrontend.RECIPES[Pair(planets[0], fluids[0])]
            return fluidStack != null
        }
        return false
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        super.writeToNBT(data)
        val planetsNBT = NBTTagList()
        val fluidsNBT = NBTTagList()
        val parallelsNBT = NBTTagList()
        for (i in 0..3)
        {
            val planetNBT = NBTTagCompound()
            planetNBT.setInteger("planet", planets[i])
            planetsNBT.appendTag(planetNBT)

            val fluidNBT = NBTTagCompound()
            fluidNBT.setInteger("fluid", fluids[i])
            fluidsNBT.appendTag(fluidNBT)

            val parallelNBT = NBTTagCompound()
            parallelNBT.setInteger("parallel", parallels[i])
            parallelsNBT.appendTag(parallelNBT)
        }
        data.setTag("planets", planetsNBT)
        data.setTag("fluids", fluidsNBT)
        data.setTag("parallels", parallelsNBT)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        val planetsNBT = data.getTagList("planets", Constants.NBT.TAG_COMPOUND)
        val fluidsNBT = data.getTagList("fluids", Constants.NBT.TAG_COMPOUND)
        val parallelsNBT = data.getTagList("parallels", Constants.NBT.TAG_COMPOUND)
        for (i in 0..3)
        {
            val planetNBT = planetsNBT.getCompoundTagAt(i)
            planets[i] = planetNBT.getInteger("planet")

            val fluidNBT = fluidsNBT.getCompoundTagAt(i)
            fluids[i] = fluidNBT.getInteger("fluid")

            val parallelNBT = parallelsNBT.getCompoundTagAt(i)
            parallels[i] = parallelNBT.getInteger("parallel")
        }
    }

    // Custom class for ListWidget since ListWidget<IWidget,*>
    // cannot be directly used due to the generic type parameter W
    private class PumpListWidget : ListWidget<IWidget, PumpListWidget>()
    {
        override fun getThis(): PumpListWidget = this
    }

    private class SpacePumpUIFactory(private val controller: MultiblockSpacePump) : MultiblockUIFactory(controller)
    {

        private val NO_OP: Consumer<String> = Consumer { }

        private val screenHeight = 109

        override fun createScreen(syncManager: PanelSyncManager): Widget<*>
        {
            this.disableIndicator()
            return (super.createScreen(syncManager) as ParentWidget<*>)
                .child(
                        createIndicator(syncManager)
                )
        }

        // TODO: Indicator may not display warning/error text
        //  need to modify MultiblockUIFactory class to access private property?
        /**
         * Code from [gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory.createIndicator]
         */
        fun createIndicator(syncManager: PanelSyncManager): Widget<*>
        {
            if (this.warningText == NO_OP && this.errorText == NO_OP)
            {
                return Widget()
                    .name("indicator_none")
                    .size(18)
                    .pos(174 - 5, screenHeight - 18 - 3)
                    .overlay(GTLiteMuiTextures.SPACE_ELEVATOR_LOGO)
            }
            val error: MultiblockUIBuilder = builder()
            error.sync("error", syncManager)
            error.setAction(this.errorText)

            val warning: MultiblockUIBuilder = builder()
            warning.sync("warning", syncManager)
            warning.setAction(this.warningText)

            // TODO: make updateFormed in MultiblockUIBuilder public?
//            warning.onRebuild(() -> warning.updateFormed(mte.isStructureFormed()))

            val indicator: IDrawable = DynamicDrawable {
                if (!error.isEmpty || !warning.isEmpty)
                {
                    return@DynamicDrawable GTLiteMuiTextures.SPACE_ELEVATOR_LOGO_DARK
                }
                else
                {
                    return@DynamicDrawable GTLiteMuiTextures.SPACE_ELEVATOR_LOGO
                }
            }

            return Widget()
                .name("indicator")
                .size(18)
                .pos(174 - 5, screenHeight - 18 - 3)
                .overlay(indicator)
                .tooltipAutoUpdate(true)
                .tooltipBuilder { t ->
                    if (!error.isEmpty)
                    {
                        error.build(t)
                    }
                    else if (!warning.isEmpty)
                    {
                        warning.build(t)
                    }
                }
        }

        override fun createButtons(mainPanel: ModularPanel,
                                   panelSyncManager: PanelSyncManager,
                                   guiData: PosGuiData?): Flow
        {
            val powerButton = createPowerButton(mainPanel, panelSyncManager)

            return Flow.column()
                .name("button_col")
                .right(4)
                .coverChildren()
                .child(createDistinctButton(mainPanel, panelSyncManager))
                .child(createVoidingButton(mainPanel, panelSyncManager))
                .child(createConfigButton(mainPanel, panelSyncManager))
                .childIf(powerButton != null, powerButton)
        }

        @Suppress("UnstableApiUsage")
        private fun createConfigButton(mainPanel: ModularPanel, panelSyncManager: PanelSyncManager): IWidget
        {
            val configPanel =
                panelSyncManager.syncedPanel("fluid_config_panel", true) { syncManager, syncHandler ->
                    return@syncedPanel createFluidConfigPanel(mainPanel, syncManager, syncHandler)
                }

            return ButtonWidget()
                // TODO: make a proper icon for this/change theme
                .overlay(GTGuiTextures.FILTER_SETTINGS_OVERLAY)
                .disableHoverBackground()
                .onMousePressed {
                    if (!configPanel.isPanelOpen) configPanel.openPanel()
                    else configPanel.closePanel()
                    true
                }.tooltipDynamic { t ->
                    if (configPanel.isPanelOpen)
                    {
                        t.addLine(KeyUtil.lang(
                                "gtlitecore.machine.space_pump_module.config_button.opened"
                        ))
                    }
                    else
                    {
                        t.addLine(KeyUtil.lang(
                                "gtlitecore.machine.space_pump_module.config_button.closed"
                        ))
                    }
                }.tooltipAutoUpdate(true)
        }

        private fun createFluidConfigPanel(parent: ModularPanel,
                                           syncManager: PanelSyncManager,
                                           thisPanel: IPanelHandler): ModularPanel
        {
            val panel = object : ModularPanel("fluid_config_panel")
            {
                override fun isDraggable() = false
            }.size(108, 113)
                .pos(parent.area.x + parent.area.width + 2, parent.area.y)
                .child(
                        Column()
                            .sizeRel(1F)
                            .margin(4)
                            .childPadding(0)
                            .mainAxisAlignment(Alignment.MainAxis.START)
                            .crossAxisAlignment(Alignment.CrossAxis.CENTER)

                            .child(
                                    KeyUtil.lang(
                                            TextFormatting.BLACK,
                                            "gtlitecore.machine.space_pump_module.configuration"
                                    ).asWidget()
                            )
                            .child(
                                    PumpListWidget()
                                        .widthRel(1F)
                                        .coverChildren()
                                        .children(controller.getFluidParallels(), ::createConfigRow)
                            ))

            return panel
        }

        private fun createConfigRow(slotNumber: Int): Flow
        {
            val plantValue = StringSyncValue(
                    { controller.getPlanetValue(slotNumber) },
                    { s -> controller.setPlanetValue(slotNumber, s) })

            val fluidValue = StringSyncValue(
                    { controller.getFluidValue(slotNumber) },
                    { s -> controller.setFluidValue(slotNumber, s) }
            )

            val parallelValue = StringSyncValue(
                    { controller.getParallelValue(slotNumber) },
                    { s -> controller.setParallelValue(slotNumber, s) }
            )

            return Row()
                .mainAxisAlignment(Alignment.MainAxis.SPACE_BETWEEN)
                .size(96, 20)
                .margin(0, 2)
                .child(
                        DynamicDrawable {
                            if (plantValue.value.isEmpty() ||
                                fluidValue.value.isEmpty() ||
                                parallelValue.value.isEmpty() ||
                                !SpacePumpRecipeFrontend.RECIPES.containsKey(Pair(plantValue.value.toInt(),
                                                                                  fluidValue.value.toInt())) ||
                                parallelValue.value.toInt() <= 0
                            )
                                GTLiteMuiTextures.SPACE_ELEVATOR_LOGO_DARK
                            else
                                GTLiteMuiTextures.SPACE_ELEVATOR_LOGO
                        }.asWidget()
                            .size(20, 20)
                            .tooltipBuilder { tooltip ->
                                tooltip.addLine(KeyUtil.lang(
                                        "gtlitecore.machine.space_pump_module.fluid_row.1",
                                        slotNumber + 1
                                ))
                                tooltip.addLine(KeyUtil.lang("gtlitecore.machine.space_pump_module.fluid_row.2"))
                                tooltip.addLine(KeyUtil.lang("gtlitecore.machine.space_pump_module.fluid_row.3"))
                            }.tooltipAutoUpdate(true)
                )
                .child(
                        TextFieldWidget()
                            .value(plantValue)
                            .setNumbers(0, 999)
                            .setMaxLength(3)
                            .size(26, 16)
                            .addTooltipLine(KeyUtil.lang(
                                    "gtlitecore.machine.space_pump_module.planet_setter"
                            ))
                )
                .child(
                        TextFieldWidget()
                            .value(fluidValue)
                            .setNumbers(0, 999)
                            .setMaxLength(3)
                            .size(26, 16)
                            .addTooltipLine(KeyUtil.lang(
                                    "gtlitecore.machine.space_pump_module.fluid_setter"
                            ))
                )
                .child(
                        TextFieldWidget()
                            .value(parallelValue)
                            .setNumbers(0, this.controller.getParallelLimit())
                            .setMaxLength(3)
                            .size(26, 16)
                            .addTooltipLine(KeyUtil.lang(
                                    "gtlitecore.machine.space_pump_module.parallel_setter"
                            ))
                )

        }
    }

}
