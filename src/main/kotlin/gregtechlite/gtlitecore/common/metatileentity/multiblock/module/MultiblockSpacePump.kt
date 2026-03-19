package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import com.cleanroommc.modularui.api.drawable.IKey
import com.cleanroommc.modularui.api.widget.IWidget
import com.cleanroommc.modularui.drawable.DynamicDrawable
import com.cleanroommc.modularui.utils.Alignment
import com.cleanroommc.modularui.utils.Color
import com.cleanroommc.modularui.utils.NumberFormat
import com.cleanroommc.modularui.value.sync.IntSyncValue
import com.cleanroommc.modularui.value.sync.StringSyncValue
import com.cleanroommc.modularui.widgets.ListWidget
import com.cleanroommc.modularui.widgets.RichTextWidget
import com.cleanroommc.modularui.widgets.TextWidget
import com.cleanroommc.modularui.widgets.layout.Column
import com.cleanroommc.modularui.widgets.layout.Flow
import com.cleanroommc.modularui.widgets.layout.Row
import com.cleanroommc.modularui.widgets.textfield.TextFieldWidget
import gregtech.api.GTValues
import gregtech.api.capability.IMultipleTankHandler
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.ConfigHolder
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import gregtechlite.gtlitecore.api.gui.widget.FluidSlotDisplayOnly
import gregtechlite.gtlitecore.api.metatileentity.multiblock.ModuleMultiblockBase
import gregtechlite.gtlitecore.api.mui.sync.FluidDisplaySyncHandler
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
import kotlin.math.max

class MultiblockSpacePump(id: ResourceLocation, tier: Int, moduleTier: Int, minCasingTier: Int)
    : ModuleMultiblockBase(id, tier, moduleTier, minCasingTier)
{

    private var outputFluidInventory: IMultipleTankHandler? = null

    private val planets = intArrayOf(0, 0, 0, 0)
    private val fluids = intArrayOf(0, 0, 0, 0)

    init
    {
        maxProgress = if (moduleTier == 3) SECOND else 4 * SECOND
    }

    companion object
    {
        private val casingState = AerospaceCasing.ELEVATOR_BASE_CASING.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) =
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

    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        for (renderSide in EnumFacing.HORIZONTALS)
        {
            if (renderSide == getFrontFacing())
            {
                getFrontOverlay().renderOrientedState(renderState, translation, pipeline,
                    getFrontFacing(), isActive(), true)
            } else
            {
                GTLiteOverlays.SPACE_PUMP_OVERLAY.renderSided(renderSide, renderState, translation, pipeline)
            }
        }
    }

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.tooltip.1"))
        when (moduleTier)
        {
            1 -> tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.mk1.tooltip.1"))
            2 -> tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.mk2.tooltip.1"))
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
        1 -> "MK1"
        2 -> "MK2"
        else -> "MK4"
    }

    private fun getFluidParallels() = when (moduleTier)
    {
        1 -> 1
        else -> 4
    }

    private fun createFluidRow(slotNumber: Int): Flow
    {
        val plantValue = StringSyncValue(
            { this.getPlanetValue(slotNumber) },
            { s -> setPlanetValue(slotNumber, s) })

        val fluidValue = StringSyncValue(
            { this.getFluidValue(slotNumber) },
            { s -> setFluidValue(slotNumber, s) }
        )

        return Row()
                .expanded()
                .mainAxisAlignment(Alignment.MainAxis.SPACE_BETWEEN)
                .size(76, 20)
                .child(
                    DynamicDrawable {
                        if (plantValue.value.isEmpty() ||
                            fluidValue.value.isEmpty() ||
                            !SpacePumpRecipeFrontend.RECIPES.containsKey(Pair(plantValue.value.toInt(),
                                fluidValue.value.toInt()))
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

    }


    override fun createUIFactory(): MultiblockUIFactory
    {
        return super.createUIFactory()
                .setSize(284, 208)
                .disableDisplayText()
                .addScreenChildren { parent, syncManager ->

                    val moduleTier = IntSyncValue(::moduleTier)

                    // Custom class for ListWidget since ListWidget<IWidget,*>
                    // cannot be directly used due to the generic type parameter W
                    class PumpScreenList : ListWidget<IWidget, PumpScreenList>()
                    {
                        override fun getThis(): PumpScreenList = this
                    }

                    val screenView: ListWidget<IWidget, PumpScreenList> = PumpScreenList()

                    // Add machine information display
                    val uiBuilder = MultiblockUIBuilder()
                    uiBuilder.setAction { builder ->
                        builder.title(this.metaFullName).structureFormed(this.isStructureFormed)
                    }

                    uiBuilder.sync("display", syncManager)

                    screenView.addChild(
                        RichTextWidget()
                                .fullWidth()
                                .alignment(Alignment.TopLeft)
                                .autoUpdate(true)
                                .textBuilder(uiBuilder::build), 0
                    )

                    // add fluid rows

                    for (index in 0 until getFluidParallels())
                    {
                        val curPlanet = IntSyncValue(
                            { this.planets[index] },
                            { s -> this.planets[index] = s })

                        val curFluid = IntSyncValue(
                            { this.fluids[index] },
                            { s -> this.fluids[index] = s })

                        syncManager.syncValue("Planet_${index}", curPlanet)
                        syncManager.syncValue("Fluid_${index}", curFluid)


                        val curOutput = FluidDisplaySyncHandler {
                            if (SpacePumpRecipeFrontend.RECIPES.containsKey(Pair(planets[index], fluids[index])))
                                return@FluidDisplaySyncHandler SpacePumpRecipeFrontend.RECIPES[Pair(planets[index],
                                    fluids[index])]
                            else return@FluidDisplaySyncHandler null
                        }

                        syncManager.syncValue("Output_${index}", curOutput)

                        screenView.addChild(
                            Row()
                                    .coverChildren()
                                    .child(
                                        TextWidget(IKey.lang("gtlitecore.machine.space_pump_module.fluid", index + 1))
                                                .marginRight(5)
                                                .color(Color.WHITE.main))
                                    .child(
                                        FluidSlotDisplayOnly()
                                                .syncHandler(curOutput)
                                                .tooltipBuilder { t ->
                                                    val fluidStack: FluidStack? = curOutput.value
                                                    if (fluidStack == null)
                                                    {
                                                        t.clearText()
                                                                .addLine(IKey.lang("gtlitecore.machine.space_pump_module.noFluidTooltip"))
                                                    } else
                                                    {
                                                        t.clearText()
                                                                .addLine(KeyUtil.fluid(fluidStack))
                                                                .add(
                                                                    IKey.str(
                                                                        "§a"
                                                                                + NumberFormat.format(fluidStack.amount.toDouble(),
                                                                            NumberFormat.DEFAULT)
                                                                                + "L/s "
                                                                                + "§7"))
                                                    }
                                                }.tooltipAutoUpdate(true)
                                    )
                                    .child(
                                        IKey.dynamic {
                                            val fluidStack: FluidStack = curOutput.value ?: return@dynamic ""
                                            return@dynamic NumberFormat.format(fluidStack.amount.toDouble(),
                                                NumberFormat.DEFAULT) + "L/s"
                                        }.asWidget()
                                                .marginLeft(5)
                                                .color(Color.ORANGE.main))
                                    .marginBottom(5)
                                    .marginLeft(5), index + 1)
                    }

                    parent.child(
                        Column()
                                .name("Configure Column")
                                .size(80, 100)
                                .top(4).left(194)
                                .mainAxisAlignment(Alignment.MainAxis.START)
                                .childPadding(3)
                                .child(
                                    KeyUtil.lang(
                                        TextFormatting.BLACK,
                                        "gtlitecore.machine.space_pump_module.configuration"
                                    ).asWidget()
                                )
                                .child(
                                    createFluidRow(0)
                                )
                                .childIf(moduleTier.value > 1) {
                                    createFluidRow(1)
                                }
                                .childIf(moduleTier.value > 1) {
                                    createFluidRow(2)
                                }
                                .childIf(moduleTier.value > 1) {
                                    createFluidRow(3)
                                }
                    )
                    parent.addChild(
                        screenView
                                .sizeRel(1F)
                                .margin(4)
                                .crossAxisAlignment(Alignment.CrossAxis.START)
                        , 1)
                }
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

    override fun updateFormedValid()
    {
        super.updateFormedValid()
        if (!isWorkingEnabled()) return

        if (!drainEnergy(true))
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
        } else
        {
            drainEnergy(false)
            setActive(true)

            progress++
            if (progress % maxProgress != 0) return
            progress = 0

            val fluidStacks = ArrayList<FluidStack>()
            for (i in 0..3)
            {
                if (SpacePumpRecipeFrontend.RECIPES.containsKey(Pair(planets[i], fluids[i])))
                {
                    SpacePumpRecipeFrontend.RECIPES[Pair(planets[i], fluids[i])]?.let { fluidStacks.add(it) }
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
        } else
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
        for (i in 0..3)
        {
            val planetNBT = NBTTagCompound()
            planetNBT.setInteger("planet", planets[i])
            planetsNBT.appendTag(planetNBT)

            val fluidNBT = NBTTagCompound()
            fluidNBT.setInteger("fluid", fluids[i])
            fluidsNBT.appendTag(fluidNBT)
        }
        data.setTag("planets", planetsNBT)
        data.setTag("fluids", fluidsNBT)
        return data
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        val planetsNBT = data.getTagList("planets", Constants.NBT.TAG_COMPOUND)
        val fluidsNBT = data.getTagList("fluids", Constants.NBT.TAG_COMPOUND)
        for (i in 0..3)
        {
            val planetNBT = planetsNBT.getCompoundTagAt(i)
            planets[i] = planetNBT.getInteger("planet")

            val fluidNBT = fluidsNBT.getCompoundTagAt(i)
            fluids[i] = fluidNBT.getInteger("fluid")
        }
    }

}
