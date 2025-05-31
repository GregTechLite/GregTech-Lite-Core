package magicbook.gtlitecore.common.metatileentity.multiblock.module

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.GTValues
import gregtech.api.capability.GregtechTileCapabilities
import gregtech.api.capability.IMultipleTankHandler
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.gui.GuiTextures
import gregtech.api.gui.ModularUI
import gregtech.api.gui.Widget.ClickData
import gregtech.api.gui.resources.TextureArea
import gregtech.api.gui.widgets.AdvancedTextWidget
import gregtech.api.gui.widgets.ClickButtonWidget
import gregtech.api.gui.widgets.ImageCycleButtonWidget
import gregtech.api.gui.widgets.ImageWidget
import gregtech.api.gui.widgets.IndicatorImageWidget
import gregtech.api.gui.widgets.ServerWidgetGroup
import gregtech.api.gui.widgets.TextFieldWidget2
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.ConfigHolder
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures
import magicbook.gtlitecore.api.metatileentity.multiblock.ModuleMultiblockBase
import magicbook.gtlitecore.api.recipe.frontend.SpacePumpRecipeFrontend
import magicbook.gtlitecore.api.utils.GTLiteValues
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockSpaceElevatorCasing
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.common.util.Constants
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MetaTileEntitySpacePump(metaTileEntityId: ResourceLocation?, tier: Int, moduleTier: Int, minCasingTier: Int) : ModuleMultiblockBase(metaTileEntityId, tier, moduleTier, minCasingTier)
{

    private var outputFluidInventory: IMultipleTankHandler? = null

    private val planets = intArrayOf(0, 0, 0, 0)
    private val fluids = intArrayOf(0, 0, 0, 0)

    init
    {
        setMaxProgress(if (moduleTier == 3) GTLiteValues.Companion.SECOND else 4 * GTLiteValues.Companion.SECOND)
    }

    companion object
    {
        private val casingState
            get() = GTLiteMetaBlocks.SPACE_ELEVATOR_CASING.getState(BlockSpaceElevatorCasing.CasingType.BASE_CASING)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MetaTileEntitySpacePump(metaTileEntityId, tier, moduleTier, minCasingTier)

    override fun initializeAbilities()
    {
        outputFluidInventory = FluidTankList(true, getAbilities(EXPORT_FLUIDS))
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("C", "C", "C", "C", "C")
        .aisle("C", "C", "C", "S", "C")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .or(abilities(EXPORT_FLUIDS)
                .setPreviewCount(0)))
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.SPACE_ELEVATOR_BASE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.SPACE_ELEVATOR_OVERLAY

    override fun getLogo(): TextureArea = GTLiteGuiTextures.SPACE_ELEVATOR_LOGO_DARK

    override fun getWarningLogo(): TextureArea = GTLiteGuiTextures.SPACE_ELEVATOR_LOGO_DARK

    override fun getErrorLogo(): TextureArea = GTLiteGuiTextures.SPACE_ELEVATOR_LOGO_DARK

    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4?, pipeline: Array<IVertexOperation?>?)
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
                GTLiteTextures.SPACE_PUMP_OVERLAY.renderSided(renderSide, renderState, translation, pipeline)
            }
        }
    }

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String?>, advanced: Boolean)
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
        tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.orbit_tier", getOrbitTier(moduleTier)))
    }

    private fun getOrbitTier(moduleTier: Int) = when (moduleTier)
    {
        1 -> "MK1"
        2 -> "MK2"
        else -> "MK4"
    }

    override fun createUITemplate(player: EntityPlayer): ModularUI.Builder
    {
        val builder = ModularUI.builder(GTLiteGuiTextures.BACKGROUND_IRREGULAR, 311, 208)
            .image(4, 4, 190, 117, GuiTextures.DISPLAY)
            .widget(IndicatorImageWidget(174, 101, 17, 17, getLogo())
                .setWarningStatus(getWarningLogo(), { textList -> this.addWarningText(textList) })
                .setErrorStatus(getErrorLogo(), { textList -> this.addErrorText(textList) }))
            .label(9, 9, metaFullName, 0xFFFFFF)
            .widget(AdvancedTextWidget(9, 20, { textList -> this.addDisplayText(textList) }, 0xFFFFFF)
                .setMaxWidthLimit(181)
                .setClickHandler { componentData, clickData ->
                    handleDisplayClick(componentData, clickData)
                })
        // Power Button
        val controllable = getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null)
        if (controllable != null)
        {
            builder.widget(ImageCycleButtonWidget(173, 183, 18, 18,
                GuiTextures.BUTTON_POWER, { controllable.isWorkingEnabled() },
                { b: Boolean -> controllable.setWorkingEnabled(b) }))
                .widget(ImageWidget(173, 201, 18, 6, GuiTextures.BUTTON_POWER_DETAIL))
        }

        // Voiding Mode Button
        builder.widget(ImageCycleButtonWidget(173, 161, 18, 18, GuiTextures.BUTTON_VOID_MULTIBLOCK,
                4, { getVoidingMode() }, { mode: Int -> setVoidingMode(mode) })
            .setTooltipHoverString { mode -> MultiblockWithDisplayBase.getVoidingModeTooltip(mode!!) })

        // Sub Screen for setting (planet, fluid).
        builder.label(198 + 62 - 18 * 2 - 18 - 9, 36 - 12, "gtlitecore.machine.space_pump_module.configuration", 0x1F1E33)
            .widget(ImageWidget(198 + 62 - 18 * 2 - 18 - 9, 36, 20, 20, GTLiteGuiTextures.SPACE_PUMP_MODULE_1))

        if (moduleTier > 1)
        {
            builder.widget(ImageWidget(198 + 62 - 18 * 2 - 18 - 9, 36 + 22, 20, 20,
                GTLiteGuiTextures.SPACE_PUMP_MODULE_2))
                .widget(ImageWidget(198 + 62 - 18 * 2 - 18 - 9, 36 + 22 * 2, 20, 20,
                    GTLiteGuiTextures.SPACE_PUMP_MODULE_3))
                .widget(ImageWidget(198 + 62 - 18 * 2 - 18 - 9, 36 + 22 * 3, 20, 20,
                    GTLiteGuiTextures.SPACE_PUMP_MODULE_4))
        }

        val planetGroup1 = ServerWidgetGroup { true }
        planetGroup1.addWidget(ImageWidget(198 + 62 - 18 * 2 - 9 + 4, 36, 53 / 2, 20, GuiTextures.DISPLAY)
            .setTooltip("gtlitecore.machine.space_pump_module.planet_setter"))

        planetGroup1.addWidget(TextFieldWidget2(198 + 63 - 18 * 2 - 9 + 4, 42, 51 / 2, 20,
                { getPlanetValue(0) }, { s -> setPlanetValue(0, s) })
                .setCentered(true)
                .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                .setMaxLength(3))

        val fluidGroup1 = ServerWidgetGroup { true }
        fluidGroup1.addWidget(ImageWidget(198 + 62 - 18 * 2 + 18 * 2 + 4, 36, 53 / 2, 20, GuiTextures.DISPLAY)
                .setTooltip("gtlitecore.machine.space_pump_module.fluid_setter"))

        fluidGroup1.addWidget(TextFieldWidget2(198 + 63 - 18 * 2 + 18 * 2 + 4, 42, 51 / 2, 20,
            { getFluidValue(0) }, { s -> setFluidValue(0, s) })
                .setCentered(true)
                .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                .setMaxLength(3))

        builder.widget(planetGroup1).widget(fluidGroup1)

        // For MK2/MK3, has more planetGroup to setting 2-4 planets and fluids.
        if (moduleTier > 1)
        {
            val planetGroup2 = ServerWidgetGroup { true }
            planetGroup2.addWidget(ImageWidget(198 + 62 - 18 * 2 - 9 + 4, 36 + 22, 53 / 2, 20, GuiTextures.DISPLAY)
                    .setTooltip("gtlitecore.machine.space_pump_module.planet_setter"))

            planetGroup2.addWidget(TextFieldWidget2(198 + 63 - 18 * 2 - 9 + 4, 42 + 22, 51 / 2, 20,
                { getPlanetValue(1) }, { s -> setPlanetValue(1, s) })
                .setCentered(true)
                .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                .setMaxLength(3))

            val fluidGroup2 = ServerWidgetGroup { true }
            fluidGroup2.addWidget(ImageWidget(198 + 62 - 18 * 2 + 18 * 2 + 4, 36 + 22, 53 / 2, 20, GuiTextures.DISPLAY)
                .setTooltip("gtlitecore.machine.space_pump_module.fluid_setter"))

            fluidGroup2.addWidget(TextFieldWidget2(198 + 63 - 18 * 2 + 18 * 2 + 4, 42 + 22, 51 / 2, 20,
                { getFluidValue(1) }, { s -> setFluidValue(1, s) })
                .setCentered(true)
                .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                .setMaxLength(3))

            val planetGroup3 = ServerWidgetGroup { true }
            planetGroup3.addWidget(ImageWidget(198 + 62 - 18 * 2 - 9 + 4, 36 + 22 * 2, 53 / 2, 20, GuiTextures.DISPLAY)
                .setTooltip("gtlitecore.machine.space_pump_module.planet_setter"))

            planetGroup3.addWidget(TextFieldWidget2(198 + 63 - 18 * 2 - 9 + 4, 42 + 22 * 2, 51 / 2, 20,
                { getPlanetValue(2) }, { s -> setPlanetValue(2, s) })
                .setCentered(true)
                .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                .setMaxLength(3))

            val fluidGroup3 = ServerWidgetGroup { true }
            fluidGroup3.addWidget(ImageWidget(198 + 62 - 18 * 2 + 18 * 2 + 4, 36 + 22 * 2, 53 / 2, 20, GuiTextures.DISPLAY)
                .setTooltip("gtlitecore.machine.space_pump_module.fluid_setter"))

            fluidGroup3.addWidget(TextFieldWidget2(198 + 63 - 18 * 2 + 18 * 2 + 4, 42 + 22 * 2, 51 / 2, 20,
                { getFluidValue(2) }, { s: String? -> setFluidValue(2, s!!) })
                .setCentered(true)
                .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                .setMaxLength(3))

            val planetGroup4 = ServerWidgetGroup { true }
            planetGroup4.addWidget(ImageWidget(198 + 62 - 18 * 2 - 9 + 4, 36 + 22 * 3, 53 / 2, 20, GuiTextures.DISPLAY)
                .setTooltip("gtlitecore.machine.space_pump_module.planet_setter"))

            planetGroup4.addWidget(TextFieldWidget2(198 + 63 - 18 * 2 - 9 + 4, 42 + 22 * 3, 51 / 2, 20,
                { getPlanetValue(3) }, { s -> setPlanetValue(3, s) })
                .setCentered(true)
                .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                .setMaxLength(3))

            val fluidGroup4 = ServerWidgetGroup { true }
            fluidGroup4.addWidget(ImageWidget(198 + 62 - 18 * 2 + 18 * 2 + 4, 36 + 22 * 3, 53 / 2, 20, GuiTextures.DISPLAY)
                .setTooltip("gtlitecore.machine.space_pump_module.fluid_setter"))

            fluidGroup4.addWidget(TextFieldWidget2(
                198 + 63 - 18 * 2 + 18 * 2 + 4, 42 + 22 * 3, 51 / 2, 20,
                { getFluidValue(3) }, { s -> setFluidValue(3, s) })
                .setCentered(true)
                .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                .setMaxLength(3))

            builder.widget(planetGroup2).widget(fluidGroup2)
                .widget(planetGroup3).widget(fluidGroup3)
                .widget(planetGroup4).widget(fluidGroup4)
        }

        builder.widget(ClickButtonWidget(173, 125 + 18, 18, 18, "") { data: ClickData ->
            reinitializeStructurePattern() }
            .setButtonTexture(GTLiteGuiTextures.BUTTON_REFRESH_STRUCTURE_PATTERN)
            .setTooltipText("gtlitecore.machine.space_elevator.refresh_structure_pattern"))

        builder.bindPlayerInventory(player.inventory, 125)
        return builder
    }

    private fun getPlanetValue(index: Int): String = planets[index].toString()


    private fun setPlanetValue(index: Int, value: String)
    {
        planets[index] = value.toInt()
    }

    private fun getFluidValue(index: Int): String = fluids[index].toString()

    private fun setFluidValue(index: Int, value: String)
    {
        fluids[index] = value.toInt()
    }

    override fun updateFormedValid()
    {
        super.updateFormedValid()
        if (!isWorkingEnabled()) return

        if (!drainEnergy(true))
        {
            if (progressTime >= 2 * TICK)
            {
                progressTime = if (ConfigHolder.machines.recipeProgressLowEnergy) TICK else max(TICK, progressTime - 2 * TICK)
            }
            return
        }

        if (progressTime == 0 && !checkRecipes())
        {
            setActive(false)
        }
        else
        {
            drainEnergy(false)
            setActive(true)

            progressTime++
            if (progressTime % getMaxProgress() != 0) return
            progressTime = 0

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
