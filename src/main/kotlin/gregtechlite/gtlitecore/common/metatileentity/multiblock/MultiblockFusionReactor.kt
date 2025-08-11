package gregtechlite.gtlitecore.common.metatileentity.multiblock

import com.cleanroommc.modularui.value.sync.PanelSyncManager
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VNF
import gregtech.api.capability.GregtechDataCodes.FUSION_REACTOR_ENERGY_CONTAINER_TRAIT
import gregtech.api.capability.GregtechDataCodes.UPDATE_COLOR
import gregtech.api.capability.impl.EnergyContainerHandler
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.FluidTankList
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.IFastRenderMetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.ProgressBarMultiblock
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.metatileentity.multiblock.ui.TemplateBarBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.recipes.logic.OCParams
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.recipes.properties.impl.FusionEUToStartProperty
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
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import gregtechlite.gtlitecore.api.gui.template.FusionReactorUITemplate
import gregtechlite.gtlitecore.client.renderer.handler.FusionBloomSetup
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCasing
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCoil
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.block.state.IBlockState
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
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.function.UnaryOperator
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow

class MultiblockFusionReactor(id: ResourceLocation, private val tier: Int)
    : RecipeMapMultiblockController(id, FUSION_RECIPES), IFastRenderMetaTileEntity,
                                                                                          IBloomEffect,
                                                                                          ProgressBarMultiblock
{

    /**
     * The default color of [fusionRingColor], means not has any color.
     */
    private val emptyColor = 0

    private var fusionRingColor = emptyColor
        set(value)
        {
            if (fusionRingColor != value)
            {
                fusionRingColor = value
                writeCustomData(UPDATE_COLOR) { it.writeVarInt(fusionRingColor) }
            }
        }

    private var energyContainers: EnergyContainerList? = null
    private var heat: Long = 0L

    @SideOnly(Side.CLIENT)
    private var registeredBloomRenderTicket: Boolean = false

    init
    {
        this.recipeMapWorkable = FusionRecipeLogic(this)
        this.energyContainer = object : EnergyContainerHandler(this, 0, 0, 0, 0, 0)
        {
            override fun getName(): String = FUSION_REACTOR_ENERGY_CONTAINER_TRAIT
        }
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MultiblockFusionReactor(metaTileEntityId, tier)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val storedEnergy = this.energyContainer.energyStored
        this.initializeAbilities()
        (this.energyContainer as EnergyContainerHandler).energyStored = storedEnergy
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.energyContainer = object : EnergyContainerHandler(this, 0, 0, 0, 0, 0)
        {
            override fun getName(): String = FUSION_REACTOR_ENERGY_CONTAINER_TRAIT
        }
        this.energyContainers = EnergyContainerList(arrayListOf())
        this.heat = 0
        this.fusionRingColor = emptyColor
    }

    override fun initializeAbilities()
    {
        this.inputInventory = ItemHandlerList(getAbilities(IMPORT_ITEMS))
        this.inputFluidInventory = FluidTankList(true, getAbilities(IMPORT_FLUIDS))
        this.outputInventory = ItemHandlerList(getAbilities(EXPORT_ITEMS))
        this.outputFluidInventory = FluidTankList(true, getAbilities(EXPORT_FLUIDS))
        val energyInputs = getAbilities(INPUT_ENERGY)
        this.energyContainers = EnergyContainerList(energyInputs)
        val euCapacity = calculateEnergyStorageFactor(energyInputs.size)
        this.energyContainer = object : EnergyContainerHandler(this, euCapacity, V[tier], 0, 0, 0)
        {
            override fun getName(): String = FUSION_REACTOR_ENERGY_CONTAINER_TRAIT
        }
    }

    private fun calculateEnergyStorageFactor(energyInputAmount: Int): Long
    {
        return energyInputAmount * 2.0.pow((tier - LuV).toDouble()).toLong() * 10_000_000L
    }

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
        .where('G', states(getCasingState(), getGlassState()))
        .where('E', states(getCasingState(), getGlassState())
            .or(metaTileEntities(*MetaTileEntities.ENERGY_INPUT_HATCH
                                     .filterNotNull()
                                     .filter { mte -> this.tier <= mte.tier && mte.tier <= UEV }
                                     .toTypedArray())
                    .setMinGlobalLimited(1)
                    .setMaxGlobalLimited(16)))
        .where('C', states(getCasingState()))
        .where('K', states(getCoilState()))
        .where('I', states(getCasingState())
            .or(abilities(IMPORT_FLUIDS)
                    .setMinGlobalLimited(1)))
        .where('O', states(getCasingState(), getGlassState())
            .or(abilities(EXPORT_FLUIDS)
                    .setMinGlobalLimited(1)))
        .where('#', air())
        .where(' ', any())
        .build()

    private fun getCasingState(): IBlockState = when (tier)
    {
        UHV -> FusionCasing.MK4.state
        else -> FusionCasing.MK5.state
    }

    private fun getGlassState(): IBlockState = MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.FUSION_GLASS)

    private fun getCoilState(): IBlockState = when (tier)
    {
        UHV -> FusionCoil.ADVANCED.state
        else -> FusionCoil.ULTIMATE.state
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer
    {
        return if (this.recipeMapWorkable.isActive) Textures.ACTIVE_FUSION_TEXTURE else Textures.FUSION_TEXTURE
    }

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.FUSION_REACTOR_OVERLAY

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
            .where('C', getCasingState())
            .where('G', getGlassState())
            .where('K', getCoilState())
            .where('W', MetaTileEntities.FLUID_EXPORT_HATCH[tier - 1], EnumFacing.NORTH)
            .where('E', MetaTileEntities.FLUID_EXPORT_HATCH[tier - 1], EnumFacing.SOUTH)
            .where('S', MetaTileEntities.FLUID_EXPORT_HATCH[tier - 1], EnumFacing.EAST)
            .where('N', MetaTileEntities.FLUID_EXPORT_HATCH[tier - 1], EnumFacing.WEST)
            .where('w', MetaTileEntities.ENERGY_INPUT_HATCH[tier], EnumFacing.WEST)
            .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[tier], EnumFacing.SOUTH)
            .where('s', MetaTileEntities.ENERGY_INPUT_HATCH[tier], EnumFacing.EAST)
            .where('n', MetaTileEntities.ENERGY_INPUT_HATCH[tier], EnumFacing.NORTH)
            .where('U', MetaTileEntities.FLUID_IMPORT_HATCH[tier - 1], EnumFacing.UP)
            .where('D', MetaTileEntities.FLUID_IMPORT_HATCH[tier - 1], EnumFacing.DOWN)
        shapeInfos.add(builder.shallowCopy()
                           .where('G', getCasingState())
                           .build())
        shapeInfos.add(builder.build())
        return shapeInfos
    }

    override fun updateFormedValid()
    {
        if (this.energyContainers!!.energyStored > 0)
        {
            val energyAdded = this.energyContainer.addEnergy(this.energyContainers!!.energyStored)
            if (energyAdded > 0)
                this.energyContainers!!.removeEnergy(energyAdded)
        }
        super.updateFormedValid()
        if (this.recipeMapWorkable.isWorking && this.fusionRingColor == emptyColor)
        {
            val previousRecipe = this.recipeMapWorkable.getPreviousRecipe()
            if (previousRecipe != null && !previousRecipe.fluidOutputs.isEmpty())
            {
                this.fusionRingColor = -0x1000000 or previousRecipe.fluidOutputs[0].fluid.getColor()
            }
        }
        else if (!this.recipeMapWorkable.isWorking && this.isStructureFormed)
        {
            this.fusionRingColor = emptyColor
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

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        val energyCostEach = calculateEnergyStorageFactor(16) / 1000000L
        tooltip.add(
            I18n.format("gtlitecore.machine.fusion_reactor.energy_cost", V[tier] / 16, energyCostEach / 16))
        tooltip.add(I18n.format("gtlitecore.machine.fusion_reactor.recipe_request"))
        tooltip.add(I18n.format("gtlitecore.machine.fusion_reactor.tier", VNF[tier]))
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
    }

    override fun createUIFactory(): MultiblockUIFactory?
    {
        val titleTexture = when (tier)
        {
            UHV -> GTLiteMuiTextures.FUSION_REACTOR_MK4_TITLE
            else -> GTLiteMuiTextures.FUSION_REACTOR_MK5_TITLE
        }
        return FusionReactorUITemplate.crateTemplateUI(titleTexture, this, recipeMapWorkable)
    }

    override fun getProgressBarCount(): Int = 2

    override fun registerBars(templateBars: MutableList<UnaryOperator<TemplateBarBuilder>>, guiSyncManager: PanelSyncManager)
    {
        FusionReactorUITemplate.createTemplateBars(templateBars, guiSyncManager, energyContainer, this::heat)
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(x: Double, y: Double, z: Double, partialTicks: Float)
    {
        if (fusionRingColor != emptyColor && !registeredBloomRenderTicket)
        {
            registeredBloomRenderTicket = true
            BloomEffectUtil.registerBloomRender(FusionBloomSetup.INSTANCE, getBloomType(), this, this)
        }
    }

    private fun getBloomType(): BloomType
    {
        val fusionBloom = ConfigHolder.client.shader.fusionBloom
        return BloomType.fromValue(if (fusionBloom.useShader) fusionBloom.bloomStyle else -1)
    }

    @SideOnly(Side.CLIENT)
    override fun renderBloomEffect(buffer: BufferBuilder, context: EffectRenderContext)
    {
        if (fusionRingColor != emptyColor)
        {
            val color = RenderUtil.interpolateColor(this.fusionRingColor, -1,
                Eases.QUAD_IN.getInterpolation(abs(abs(this.offsetTimer % 50L).toFloat()
                                                           + context.partialTicks() - 25.0f) / 25.0f))
            val a = (color shr 24 and 255).toFloat() / 255.0f
            val r = (color shr 16 and 255).toFloat() / 255.0f
            val g = (color shr 8 and 255).toFloat() / 255.0f
            val b = (color and 255).toFloat() / 255.0f
            val relativeBack = RelativeDirection.BACK.getRelativeFacing(
                this.getFrontFacing(), this.getUpwardsFacing(), this.isFlipped())
            val axis = RelativeDirection.UP.getRelativeFacing(
                this.getFrontFacing(), this.getUpwardsFacing(), this.isFlipped()).axis
            buffer.begin(8, DefaultVertexFormats.POSITION_COLOR)
            RenderBufferHelper.renderRing(buffer,
                this.pos.x.toDouble() - context.cameraX() + (relativeBack.xOffset * 7).toDouble() + 0.5,
                this.pos.y.toDouble() - context.cameraY() + (relativeBack.yOffset * 7).toDouble() + 0.5,
                this.pos.z.toDouble() - context.cameraZ() + (relativeBack.zOffset * 7).toDouble() + 0.5,
                6.0, 0.2, 10, 20, r, g, b, a, axis)
            Tessellator.getInstance().draw()
        }
    }

    @SideOnly(Side.CLIENT)
    override fun shouldRenderBloomEffect(context: EffectRenderContext): Boolean
    {
        return fusionRingColor != emptyColor && context.camera().isBoundingBoxInFrustum(getRenderBoundingBox())
    }

    override fun getRenderBoundingBox(): AxisAlignedBB
    {
        val relativeRight = RelativeDirection.RIGHT
            .getRelativeFacing(this.getFrontFacing(), this.getUpwardsFacing(), this.isFlipped())
        val relativeBack = RelativeDirection.BACK
            .getRelativeFacing(this.getFrontFacing(), this.getUpwardsFacing(), this.isFlipped())
        return AxisAlignedBB(
            this.pos.offset(relativeBack).offset(relativeRight, 6),
            this.pos.offset(relativeBack, 13).offset(relativeRight.opposite, 6))
    }

    override fun shouldRenderInPass(pass: Int): Boolean = pass == 0

    override fun isGlobalRenderer(): Boolean = true

    override fun canBeDistinct(): Boolean = false

    override fun hasMaintenanceMechanics(): Boolean = false

    private inner class FusionRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte, true)
    {

        override fun getMaxVoltage(): Long = min(V[tier], super.getMaxVoltage())

        /**
         * Drain heat when the reactor is not active, is paused via soft mallet, or does not have enough energy and has
         * fully wiped recipe progress. Don't drain heat when there is not enough energy and there is still some recipe
         * progress, as that makes it doubly hard to complete the recipe. Will have to recover heat and recipe progress.
         */
        override fun updateWorkable()
        {
            super.updateWorkable()
            if (heat > 0)
            {
                if (!isActive || !workingEnabled || (hasNotEnoughEnergy && progressTime == 0))
                {
                    heat = if (heat <= 10000) 0 else (heat - 10000)
                }
            }
        }

        override fun checkRecipe(recipe: Recipe): Boolean
        {
            if (!super.checkRecipe(recipe)) return false
            // If the reactor is not able to hold enough energy for it, do not run the recipe
            if (recipe.getProperty(FusionEUToStartProperty.getInstance(), 0L)!!
                > energyContainer.energyCapacity) return false

            // If the stored heat is >= required energy, recipe is okay to run
            val heatDiff = recipe.getProperty(FusionEUToStartProperty.getInstance(), 0L)!! - heat
            if (heatDiff <= 0) return true

            // If the remaining energy needed is more than stored, do not run.
            if (energyContainer.energyStored < heatDiff) return false

            // Remove the energy needed
            energyContainer.removeEnergy(heatDiff)
            // Increase the stored heat
            heat += heatDiff
            return true
        }

        override fun modifyOverclockPre(ocParams: OCParams, storage: RecipePropertyStorage)
        {
           super.modifyOverclockPre(ocParams, storage)
           val euToStart: Long = storage.get(FusionEUToStartProperty.getInstance(), 0L)!!
           var fusionTier = FusionEUToStartProperty.getFusionTier(euToStart)
           if (fusionTier != 0) fusionTier = this@MultiblockFusionReactor .tier - fusionTier
           ocParams.setOcAmount(min(fusionTier, ocParams.ocAmount()))
        }

        override fun serializeNBT(): NBTTagCompound
        {
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