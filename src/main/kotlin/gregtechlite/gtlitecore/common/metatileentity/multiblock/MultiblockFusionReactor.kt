package gregtechlite.gtlitecore.common.metatileentity.multiblock

import com.cleanroommc.modularui.value.sync.DoubleSyncValue
import com.cleanroommc.modularui.value.sync.LongSyncValue
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import com.cleanroommc.modularui.widgets.ProgressWidget
import com.cleanroommc.modularui.widgets.layout.Column
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VNF
import gregtech.api.capability.GregtechDataCodes.FUSION_REACTOR_ENERGY_CONTAINER_TRAIT
import gregtech.api.capability.GregtechDataCodes.UPDATE_COLOR
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.EnergyContainerHandler
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.IFastRenderMetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.ProgressBarMultiblock
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.metatileentity.multiblock.ui.TemplateBarBuilder
import gregtech.api.mui.GTGuiTextures
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.recipes.logic.OCParams
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_VOLTAGE_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.recipes.properties.impl.FusionEUToStartProperty
import gregtech.api.util.KeyUtil
import gregtech.api.util.RelativeDirection
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.interpolate.Eases
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.client.shader.postprocessing.BloomType
import gregtech.client.utils.BloomEffectUtil
import gregtech.client.utils.EffectRenderContext
import gregtech.client.utils.IBloomEffect
import gregtech.client.utils.RenderBufferHelper
import gregtech.client.utils.RenderUtil
import gregtech.client.utils.TooltipHelper
import gregtech.common.ConfigHolder
import gregtech.common.metatileentities.MetaTileEntities
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import gregtechlite.gtlitecore.client.renderer.handler.FusionBloomSetup
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCasing
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCoil
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.function.UnaryOperator
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow

class MultiblockFusionReactor(id: ResourceLocation, private val tier: Int)
    : RecipeMapMultiblockController(id, FUSION_RECIPES), IFastRenderMetaTileEntity, IBloomEffect, ProgressBarMultiblock
{

    companion object
    {
        private var emptyColor = 0

        private val glassState = GTGlassCasing.FUSION_GLASS.state
    }

    private lateinit var inputEnergyContainers: EnergyContainerList
    private var heat = 0L
    private var fusionRingColor = emptyColor

    @SideOnly(Side.CLIENT)
    private var registeredBloomRenderTicket = false

    private val casingState = when(tier)
    {
        UHV -> FusionCasing.MK4.state
        UEV -> FusionCasing.MK5.state
        else -> GTFusionCasing.FUSION_CASING.state
    }

    private val coilState = when(tier)
    {
        UHV -> FusionCoil.ADVANCED.state
        UEV -> FusionCoil.ULTIMATE.state
        else -> GTFusionCasing.SUPERCONDUCTOR_COIL.state
    }

    init
    {
        recipeMapWorkable = FusionRecipeLogic(this)
        energyContainer = object : EnergyContainerHandler(this, 0, 0, 0, 0, 0)
        {
            override fun getName(): String = FUSION_REACTOR_ENERGY_CONTAINER_TRAIT
        }
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockFusionReactor(metaTileEntityId, tier)

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("               ", "      OGO      ", "               ")
        .aisle("      ICI      ", "    GG###GG    ", "      ICI      ")
        .aisle("    CC   CC    ", "   E##OGO##E   ", "    CC   CC    ")
        .aisle("   C       C   ", "  EKEG   GEKE  ", "   C       C   ")
        .aisle("  C         C  ", " G#E       E#G ", "  C         C  ")
        .aisle("  C         C  ", " G#G       G#G ", "  C         C  ")
        .aisle(" I           I ", "O#O         O#O", " I           I ")
        .aisle(" C           C ", "G#G         G#G", " C           C ")
        .aisle(" I           I ", "O#O         O#O", " I           I ")
        .aisle("  C         C  ", " G#G       G#G ", "  C         C  ")
        .aisle("  C         C  ", " G#E       E#G ", "  C         C  ")
        .aisle("   C       C   ", "  EKEG   GEKE  ", "   C       C   ")
        .aisle("    CC   CC    ", "   E##OGO##E   ", "    CC   CC    ")
        .aisle("      ICI      ", "    GG###GG    ", "      ICI      ")
        .aisle("               ", "      OSO      ", "               ")
        .where('S', selfPredicate())
        .where('G', states(casingState, glassState))
        .where('E', states(casingState, glassState)
            .or(metaTileEntities(*MetaTileEntities.ENERGY_INPUT_HATCH
                                     .filterNotNull()
                                     .filter { mte -> tier <= mte.tier && mte.tier <= UEV }
                                     .toTypedArray())
                    .setMinGlobalLimited(1)
                    .setMaxGlobalLimited(16)))
        .where('C', states(casingState))
        .where('K', states(coilState))
        .where('I', states(casingState)
            .or(abilities(IMPORT_FLUIDS)
                    .setMinGlobalLimited(1)))
        .where('O', states(casingState, glassState)
            .or(abilities(EXPORT_FLUIDS)
                    .setMinGlobalLimited(1)))
        .where('#', air())
        .where(' ', any())
        .build()

    override fun getMatchingShapes(): MutableList<MultiblockShapeInfo>
    {
        val shapeInfos = arrayListOf<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("               ", "      WGW      ", "               ")
            .aisle("      DCD      ", "    GG   GG    ", "      UCU      ")
            .aisle("    CC   CC    ", "   w  EGE  s   ", "    CC   CC    ")
            .aisle("   C       C   ", "  nKeG   GeKn  ", "   C       C   ")
            .aisle("  C         C  ", " G s       w G ", "  C         C  ")
            .aisle("  C         C  ", " G G       G G ", "  C         C  ")
            .aisle(" D           D ", "N S         N S", " U           U ")
            .aisle(" C           C ", "G G         G G", " C           C ")
            .aisle(" D           D ", "N S         N S", " U           U ")
            .aisle("  C         C  ", " G G       G G ", "  C         C  ")
            .aisle("  C         C  ", " G s       w G ", "  C         C  ")
            .aisle("   C       C   ", "  eKnG   GnKe  ", "   C       C   ")
            .aisle("    CC   CC    ", "   w  WGW  s   ", "    CC   CC    ")
            .aisle("      DCD      ", "    GG   GG    ", "      UCU      ")
            .aisle("               ", "      EME      ", "               ")
            .where('M', if (tier == UHV) GTLiteMetaTileEntities.FUSION_REACTOR_MK4 else GTLiteMetaTileEntities.FUSION_REACTOR_MK5, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('G', glassState)
            .where('K', coilState)
            .where('W', MetaTileEntities.FLUID_EXPORT_HATCH[tier], EnumFacing.NORTH)
            .where('E', MetaTileEntities.FLUID_EXPORT_HATCH[tier], EnumFacing.SOUTH)
            .where('S', MetaTileEntities.FLUID_EXPORT_HATCH[tier], EnumFacing.EAST)
            .where('N', MetaTileEntities.FLUID_EXPORT_HATCH[tier], EnumFacing.WEST)
            .where('w', MetaTileEntities.ENERGY_INPUT_HATCH[tier], EnumFacing.WEST)
            .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[tier], EnumFacing.SOUTH)
            .where('s', MetaTileEntities.ENERGY_INPUT_HATCH[tier], EnumFacing.EAST)
            .where('n', MetaTileEntities.ENERGY_INPUT_HATCH[tier], EnumFacing.NORTH)
            .where('U', MetaTileEntities.FLUID_IMPORT_HATCH[tier], EnumFacing.UP)
            .where('D', MetaTileEntities.FLUID_IMPORT_HATCH[tier], EnumFacing.DOWN)

        shapeInfos.add(builder.shallowCopy().where('G', casingState).build())
        shapeInfos.add(builder.build())
        return shapeInfos
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer
    {
        return if (recipeMapWorkable.isActive) Textures.ACTIVE_FUSION_TEXTURE else Textures.FUSION_TEXTURE
    }

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.FUSION_REACTOR_OVERLAY

    fun hasFusionRingColor(): Boolean = fusionRingColor != emptyColor

    fun setFusionRingColor(fusionRingColor: Int)
    {
        if (this.fusionRingColor != fusionRingColor)
        {
            this.fusionRingColor = fusionRingColor
            writeCustomData(UPDATE_COLOR) { it.writeVarInt(this.fusionRingColor) }
        }
    }

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        initializeAbilities()
        (energyContainer as EnergyContainerHandler).energyStored = energyContainer.energyStored
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        energyContainer = object : EnergyContainerHandler(this, 0, 0, 0, 0, 0)
        {
            override fun getName(): String = FUSION_REACTOR_ENERGY_CONTAINER_TRAIT
        }
        inputEnergyContainers = EnergyContainerList(arrayListOf())
        heat = 0
        setFusionRingColor(emptyColor)
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val energyInputs: MutableList<IEnergyContainer> = getAbilities(INPUT_ENERGY)
        inputEnergyContainers = EnergyContainerList(energyInputs)

        val euCapacity = calculateEnergyStorageFactor(energyInputs.size).toLong()
        energyContainer = object : EnergyContainerHandler(this, euCapacity, V[tier], 0, 0, 0)
        {
            override fun getName(): String = "EnergyContainerInternal"
        }
    }

    private fun calculateEnergyStorageFactor(energyInputAmount: Int) = energyInputAmount * 2.0.pow((tier - 6).toDouble()) * 10_000_000L;

    override fun updateFormedValid()
    {
        val stored = inputEnergyContainers.energyStored
        if (stored > 0L)
        {
            val energyAdded = energyContainer.addEnergy(stored)
            if (energyAdded > 0) inputEnergyContainers.removeEnergy(energyAdded)
        }

        super.updateFormedValid()

        if (recipeMapWorkable.isWorking && fusionRingColor == emptyColor)
        {
            val previousRecipe = recipeMapWorkable.previousRecipe
            if (previousRecipe != null && !previousRecipe.fluidOutputs.isEmpty())
            {
                setFusionRingColor(-0x1000000 or previousRecipe.fluidOutputs[0].fluid.color)
            }
            else if (!recipeMapWorkable.isWorking && isStructureFormed)
            {
                setFusionRingColor(emptyColor)
            }
        }
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeVarInt(fusionRingColor)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        this.fusionRingColor = buf.readVarInt()
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        if (dataId == UPDATE_COLOR)
        {
            this.fusionRingColor = buf.readVarInt()
        }
        else
        {
            super.receiveCustomData(dataId, buf)
        }
    }

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        val energyCost = calculateEnergyStorageFactor(16) / 1000000L
        tooltip.add(I18n.format("gtlitecore.machine.fusion_reactor.energy_cost", V[tier] / 16, energyCost / 16))
        tooltip.add(I18n.format("gtlitecore.machine.fusion_reactor.recipe_request"))
        tooltip.add(I18n.format("gtlitecore.machine.fusion_reactor.tier", VNF[tier]))
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
    }

    @Suppress("UnstableApiUsage")
    override fun createUIFactory(): MultiblockUIFactory
    {
        val titleTexture = when (tier)
        {
            UHV -> GTLiteMuiTextures.FUSION_REACTOR_MK4_TITLE
            UEV -> GTLiteMuiTextures.FUSION_REACTOR_MK5_TITLE
            else -> GTGuiTextures.FUSION_REACTOR_MK1_TITLE
        }

        val progress = DoubleSyncValue(recipeMapWorkable::getProgressPercent)

        return MultiblockUIFactory(this)
            .setScreenHeight(138)
            .disableDisplayText()
            .addScreenChildren { parent, syncManager ->
                val status = MultiblockUIFactory.builder("status", syncManager)
                status.setAction { it.structureFormed(true)
                    .setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
                    .addWorkingStatusLine()
                }

                parent.child(
                    Column()
                        .padding(4)
                        .expanded()
                        .child(titleTexture.asWidget()
                                   .marginBottom(8)
                                   .size(69, 12))
                        .child(ProgressWidget()
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
                                   .size(108, 41)))
            }
    }

    override fun getProgressBarCount(): Int = 2

    @Suppress("UnstableApiUsage")
    override fun registerBars(templateBars: MutableList<UnaryOperator<TemplateBarBuilder>>, syncManager: PanelSyncManager)
    {
        val capacity = LongSyncValue(energyContainer::getEnergyCapacity)
        syncManager.syncValue("capacity", capacity)

        val stored = LongSyncValue(energyContainer::getEnergyStored)
        syncManager.syncValue("stored", stored)

        val heat = LongSyncValue(::heat)
        syncManager.syncValue("heat", heat)

        // Energy Stored / Energy Capacity
        templateBars.add {
            it.progress {
                if (capacity.longValue > 0)
                    return@progress 1.0 * stored.longValue / capacity.longValue
                else
                    return@progress 0.0
            }
                .texture(GTGuiTextures.PROGRESS_BAR_FUSION_ENERGY)
                .tooltipBuilder { tooltip ->
                    tooltip.add(KeyUtil.lang(TextFormatting.GRAY, "gregtech.multiblock.energy_stored",
                        stored.longValue, capacity.longValue))
                }
        }

        // Heat
        templateBars.add {
            it.progress {
                if (capacity.longValue > 0)
                    return@progress 1.0 * heat.longValue / capacity.longValue
                else
                    return@progress 0.0
            }
                .texture(GTGuiTextures.PROGRESS_BAR_FUSION_HEAT)
                .tooltipBuilder { tooltip ->
                    val heatInfo = KeyUtil.string(TextFormatting.AQUA, "%,d / %,d EU",
                                                  heat.longValue, capacity.longValue)
                    tooltip.add(KeyUtil.lang(TextFormatting.GRAY, "gregtech.multiblock.fusion_reactor.heat",
                                             heatInfo))
                }
        }
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(x: Double, y: Double, z: Double, partialTicks: Float)
    {
        if (hasFusionRingColor() && !registeredBloomRenderTicket)
        {
            registeredBloomRenderTicket = true
            BloomEffectUtil.registerBloomRender(FusionBloomSetup.INSTANCE, getBloomType(), this, this)
        }
    }

    @SideOnly(Side.CLIENT)
    override fun renderBloomEffect(buffer: BufferBuilder, context: EffectRenderContext)
    {
        if (hasFusionRingColor())
        {
            val color = RenderUtil.interpolateColor(fusionRingColor, -1,
                    Eases.QUAD_IN.getInterpolation(abs(abs(offsetTimer % 50L).toFloat() + context.partialTicks() - 25.0f) / 25.0f))
            val a = (color shr 24 and 255).toFloat() / 255.0f
            val r = (color shr 16 and 255).toFloat() / 255.0f
            val g = (color shr 8 and 255).toFloat() / 255.0f
            val b = (color and 255).toFloat() / 255.0f
            val relativeBack = RelativeDirection.BACK.getRelativeFacing(getFrontFacing(), getUpwardsFacing(), isFlipped())
            val axis = RelativeDirection.UP.getRelativeFacing(getFrontFacing(), getUpwardsFacing(), isFlipped()).axis
            buffer.begin(8, DefaultVertexFormats.POSITION_COLOR)
            RenderBufferHelper.renderRing(buffer,
                                          pos.x.toDouble() - context.cameraX() + (relativeBack.xOffset * 7).toDouble() + 0.5,
                                          pos.y.toDouble() - context.cameraY() + (relativeBack.yOffset * 7).toDouble() + 0.5,
                                          pos.z.toDouble() - context.cameraZ() + (relativeBack.zOffset * 7).toDouble() + 0.5,
                                          6.0, 0.2, 10, 20, r, g, b, a, axis)
            Tessellator.getInstance().draw()
        }
    }

    @SideOnly(Side.CLIENT)
    override fun shouldRenderBloomEffect(context: EffectRenderContext): Boolean
    {
        return hasFusionRingColor() && context.camera().isBoundingBoxInFrustum(getRenderBoundingBox())
    }

    override fun getRenderBoundingBox(): AxisAlignedBB
    {
        val relativeRight = RelativeDirection.RIGHT.getRelativeFacing(getFrontFacing(), getUpwardsFacing(), isFlipped())
        val relativeBack = RelativeDirection.BACK.getRelativeFacing(getFrontFacing(), getUpwardsFacing(), isFlipped())
        return AxisAlignedBB(pos.offset(relativeBack).offset(relativeRight, 6),
                             pos.offset(relativeBack, 13).offset(relativeRight.opposite, 6))
    }

    private fun getBloomType(): BloomType
    {
        val fusionBloom = ConfigHolder.client.shader.fusionBloom
        return BloomType.fromValue(if (fusionBloom.useShader) fusionBloom.bloomStyle else -1)
    }

    override fun shouldRenderInPass(pass: Int): Boolean = pass == 0

    override fun isGlobalRenderer(): Boolean = true

    override fun canBeDistinct(): Boolean = false

    override fun hasMaintenanceMechanics(): Boolean = false

    private inner class FusionRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor(): Double = PERFECT_DURATION_FACTOR

        override fun getOverclockingVoltageFactor(): Double = STD_VOLTAGE_FACTOR

        override fun getMaxVoltage(): Long = min(V[tier], super.getMaxVoltage())

        override fun updateWorkable()
        {
            super.updateWorkable()
            if (heat > 0)
            {
                if (!isActive || !workingEnabled || (hasNotEnoughEnergy && progressTime == 0))
                {
                    heat = if (heat <= 10000) 0 else heat - 10000
                }
            }
        }

        override fun checkRecipe(recipe: Recipe): Boolean
        {
            if (!super.checkRecipe(recipe)) return false

            // If the reactor is not able to hold enough energy for it, do not run the recipe.
            if (recipe.getProperty(FusionEUToStartProperty.getInstance(), 0)!! > energyContainer.energyCapacity) return false

            val heatDiff = recipe.getProperty(FusionEUToStartProperty.getInstance(), 0L)!! - heat
            // If the stored heat is >= required energy, recipe is okay to run.
            if (heatDiff <= 0) return true

            // If the remaining energy needed is more than stored, do not run.
            if (energyContainer.energyStored < heatDiff) return false

            // Remove the energy needed.
            energyContainer.removeEnergy(heatDiff)

            // Increase the stored heat.
            heat += heatDiff
            return true
        }

        override fun modifyOverclockPre(ocParams: OCParams, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPre(ocParams, storage)
            val euToStart = storage.get(FusionEUToStartProperty.getInstance(), 0)!!
            var fusionTier = FusionEUToStartProperty.getFusionTier(euToStart)
            if (fusionTier != 0) fusionTier = tier - fusionTier
            ocParams.setOcAmount(min(fusionTier, ocParams.ocAmount()))
        }

        override fun serializeNBT(): NBTTagCompound {
            val tag = super.serializeNBT()
            tag.setLong("Heat", heat)
            return tag
        }

        override fun deserializeNBT(compound: NBTTagCompound)
        {
            super.deserializeNBT(compound)
            heat = compound.getLong("Heat")
        }

    }

}