package gregtechlite.gtlitecore.common.metatileentity.multiblock.mega

import com.morphismmc.morphismlib.client.Games
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.IFastRenderMetaTileEntity
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.KeyUtil
import gregtech.api.util.RelativeDirection
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.shader.postprocessing.BloomType
import gregtech.client.utils.BloomEffectUtil
import gregtech.client.utils.EffectRenderContext
import gregtech.client.utils.IBloomEffect
import gregtech.core.sound.GTSoundEvents
import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.manipulators
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.shieldingCores
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.QUANTUM_FORCE_TRANSFORMER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import gregtechlite.gtlitecore.client.renderer.handler.bloom.ForceFieldBloomSetup
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.opengl.GL11
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

class MultiblockQuantumForceTransformer(id: ResourceLocation)
    : RecipeMapMultiblockController(id, QUANTUM_FORCE_TRANSFORMER_RECIPES), IFastRenderMetaTileEntity, IBloomEffect
{

    private var manipulatorTier = 0
    private var shieldingCoreTier = 0
    private var tier = 0

    @SideOnly(Side.CLIENT)
    private var registeredBloomRenderTicket = false

    init
    {
        recipeMapWorkable = QuantumForceTransformerRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MultiblockCasing.PARTICLE_CONTAINMENT_CASING.state
        private val coilState = MultiblockCasing.PARTICLE_EXCITATION_WIRE_COIL.state
        private val glassState = GlassCasing.FORCE_FIELD.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockQuantumForceTransformer(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        manipulatorTier = context.getAttributeOrDefault(GTLiteAPI.MANIPULATOR_TIER, 0)
        shieldingCoreTier = context.getAttributeOrDefault(GTLiteAPI.SHIELDING_CORE_TIER, 0)
        tier = minOf(manipulatorTier, shieldingCoreTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        manipulatorTier = 0
        shieldingCoreTier = 0
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        inputEnergy.addAll(getAbilities(SUBSTATION_INPUT_ENERGY))
        energyContainer = EnergyContainerList(inputEnergy)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("    A     A    ", "    A     A    ", "    A     A    ", "   BA     AB   ", "   BABBABBAB   ", "   BAAAAAAAB   ", "   BBBBABBBB   ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("               ", "               ", "               ", "  A         A  ", "  A         A  ", "  B         B  ", "  BAAAAAAAAAB  ", "   AAABBBAAA   ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("               ", "               ", "               ", " A           A ", " A           A ", " B           B ", " BAA       AAB ", "  AA       AA  ", "    AA   AA    ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("A             A", "A             A", "A             A", "A             A", "A             A", "B             B", "BAA         AAB", " AA         AA ", "   AA     AA   ", "     BAAAB     ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("      HHH      ", "      EEE      ", "      EEE      ", "      EEE      ", "B     DDD     B", "B     EEE     B", "BA    DDD    AB", " A    EEE    A ", "  AA  EEE  AA  ", "    BAEEEAB    ", "      DDD      ", "      EEE      ", "      EEE      ", "      EEE      ", "      DDD      ", "      EEE      ", "      DDD      ", "      EEE      ", "      EEE      ", "      EEE      ", "      FFF      ")
        .aisle("     HHHHH     ", "     ECCCE     ", "     ECCCE     ", "B    ECCCE    B", "B    D###D    B", "B    ECCCE    B", "BA   D###D   AB", " A   ECCCE   A ", "  A  ECCCE  A  ", "   BAECCCEAB   ", "     D###D     ", "     ECCCE     ", "     ECCCE     ", "     ECCCE     ", "     D###D     ", "     ECCCE     ", "     D###D     ", "     ECCCE     ", "     ECCCE     ", "     ECCCE     ", "     FFFFF     ")
        .aisle("    HHHHHHH    ", "    EC###CE    ", "A   EC###CE   A", "A   EC###CE   A", "A   D#####D   A", "A   EC###CE   A", "BA  D#####D  AB", "BB  EC###CE  BB", " B  EC###CE  B ", "  BAEC###CEAB  ", "    D#####D    ", "    EC###CE    ", "    EC###CE    ", "    EC###CE    ", "    D#####D    ", "    EC###CE    ", "    D#####D    ", "    EC###CE    ", "    EC###CE    ", "    ECCCCCE    ", "    FFFFFFF    ")
        .aisle("    HHHHHHH    ", "    EC###CE    ", "    EC###CE    ", "    EC###CE    ", "A   D#####D   A", "A   EC###CE   A", "AA  D#####D  AA", "AB  EC###CE  BA", " A  EC###CE  A ", "  AAEC###CEAA  ", "    D#####D    ", "    EC###CE    ", "    EC###CE    ", "    EC###CE    ", "    D#####D    ", "    EC###CE    ", "    D#####D    ", "    EC###CE    ", "    EC###CE    ", "    ECCCCCE    ", "    FFFFFFF    ")
        .aisle("    HHHHHHH    ", "    EC###CE    ", "    EC###CE    ", "A   EC###CE   A", "A   D#####D   A", "A   EC###CE   A", "BA  D#####D  AB", "BB  EC###CE  BB", " B  EC###CE  B ", "  BAEC###CEAB  ", "    D#####D    ", "    EC###CE    ", "    EC###CE    ", "    EC###CE    ", "    D#####D    ", "    EC###CE    ", "    D#####D    ", "    EC###CE    ", "    EC###CE    ", "    ECCCCCE    ", "    FFFFFFF    ")
        .aisle("     HHHHH     ", "     ECCCE     ", "     ECCCE     ", "B    ECCCE    B", "B    D###D    B", "B    ECCCE    B", "BA   D###D   AB", " A   ECCCE   A ", "  A  ECCCE  A  ", "   BAECCCEAB   ", "     D###D     ", "     ECCCE     ", "     ECCCE     ", "     ECCCE     ", "     D###D     ", "     ECCCE     ", "     D###D     ", "     ECCCE     ", "     ECCCE     ", "     ECCCE     ", "     FFFFF     ")
        .aisle("      HSH      ", "      EEE      ", "      EEE      ", "      EEE      ", "B     DDD     B", "B     EEE     B", "BA    DDD    AB", " A    EEE    A ", "  AA  EEE  AA  ", "    BAEEEAB    ", "      DDD      ", "      EEE      ", "      EEE      ", "      EEE      ", "      DDD      ", "      EEE      ", "      DDD      ", "      EEE      ", "      EEE      ", "      EEE      ", "      FFF      ")
        .aisle("A             A", "A             A", "A             A", "A             A", "A             A", "B             B", "BAA          AB", " AA         AA ", "   AA     AA   ", "     BAAAB     ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("               ", "               ", "               ", " A           A ", " A           A ", " B           B ", " BA         AB ", "  AA       AA  ", "    AA   AA    ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("               ", "               ", "               ", "  A         A  ", "  A         A  ", "  B         B  ", "  BAAAAAAAAAB  ", "   AAABBBAAA   ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .aisle("    A     A    ", "    A     A    ", "    A     A    ", "   BA     AB   ", "   BABBABBAB   ", "   BAAAAAAAB   ", "   BBBBABBBB   ", "      BAB      ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ")
        .where('S', selfPredicate())
        .where('H', states(casingState)
            .setMinGlobalLimited(16)
            .or(abilities(MAINTENANCE_HATCH)
                    .setExactLimit(1))
            .or(abilities(INPUT_ENERGY)
                    .setMaxGlobalLimited(2))
            .or(abilities(INPUT_LASER)
                    .setMaxGlobalLimited(1))
            .or(abilities(IMPORT_ITEMS, IMPORT_FLUIDS)
                    .setPreviewCount(1)))
        .where('F', states(casingState)
            .setMinGlobalLimited(16)
            .or(abilities(EXPORT_ITEMS, EXPORT_FLUIDS)
                    .setPreviewCount(1)))
        .where('A', manipulators())
        .where('B', shieldingCores())
        .where('C', states(coilState))
        .where('D', states(casingState))
        .where('E', states(glassState))
        .where(' ', any())
        .where('#', air())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.PARTICLE_CONTAINMENT_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.QUANTUM_FORCE_TRANSFORMER_OVERLAY

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.quantum_force_transformer.tooltip.7"))
    }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(energyContainer)
            .addEnergyTierLine(getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
            .addCustom { keyManager, syncer ->
                if (isStructureFormed)
                {
                    val manipulatorTierKey = KeyUtil.number(TextFormatting.GREEN,
                                                            syncer.syncInt(manipulatorTier).toLong())
                    val shieldingCoreTierKey = KeyUtil.number(TextFormatting.GREEN,
                                                              syncer.syncInt(shieldingCoreTier).toLong())
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.quantum_force_transformer.manipulator_info",
                                                manipulatorTierKey))
                    keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.quantum_force_transformer.shielding_core_info",
                                                shieldingCoreTierKey))
                }
            }
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progress, recipeMapWorkable.maxProgress)
            .addRecipeOutputLine(recipeMapWorkable)
    }

    override fun canBeDistinct() = true

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        recipe.chancedOutputs.chancedEntries.forEach { min(it.chance * shieldingCoreTier, 10000) }
        recipe.chancedFluidOutputs.chancedEntries.forEach { min(it.chance * shieldingCoreTier, 10000) }
        return super.checkRecipe(recipe, consumeIfSuccess)
                && recipe.getProperty(GTLiteRecipeProperties.QUANTUM_FORCE_TRANSFORMER_TIER, 0)!! <= manipulatorTier
    }

    @SideOnly(Side.CLIENT)
    override fun renderMetaTileEntity(x: Double, y: Double, z: Double, partialTicks: Float)
    {
        if (isActive && !registeredBloomRenderTicket)
        {
            registeredBloomRenderTicket = true
            BloomEffectUtil.registerBloomRender(ForceFieldBloomSetup.INSTANCE, BloomType.UNREAL, this, this)
        }
    }

    override fun getRenderBoundingBox(): AxisAlignedBB
    {
        val relativeBack = RelativeDirection.BACK.getRelativeFacing(getFrontFacing(), getUpwardsFacing(), isFlipped())
        val relativeRight = RelativeDirection.RIGHT.getRelativeFacing(getFrontFacing(), getUpwardsFacing(), isFlipped())
        return AxisAlignedBB(pos.offset(relativeBack, -4).offset(relativeRight, -7),
                             pos.offset(relativeBack, 10).offset(relativeRight, 7).up(4))
    }

    @SideOnly(Side.CLIENT)
    override fun renderBloomEffect(buffer: BufferBuilder, context: EffectRenderContext)
    {
        if (!isActive) return

        val texture = GTLiteTextures.FORCE_FIELD
        val minU = texture.minU.toDouble()
        val maxU = texture.maxU.toDouble()
        val minV = texture.minV.toDouble()
        val maxV = texture.maxV.toDouble()

        val frontFacing = getFrontFacing()
        val forward = frontFacing.opposite
        val right = forward.rotateY()

        // Center
        val cx = pos.x - context.cameraX() + forward.xOffset * 3 + 0.5
        val cy = pos.y - context.cameraY()
        val cz = pos.z - context.cameraZ() + forward.zOffset * 3 + 0.5

        Games.mc().textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE)
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX)

        for (side in 0..7)
        {
            renderForceField(buffer, cx, cy, cz, side, minU, maxU, minV, maxV,
                             forward.xOffset.toDouble(), forward.zOffset.toDouble(),
                             right.xOffset.toDouble(), right.zOffset.toDouble())
        }

        Tessellator.getInstance().draw()
    }

    @SideOnly(Side.CLIENT)
    override fun shouldRenderBloomEffect(context: EffectRenderContext): Boolean
        = isActive && context.camera().isBoundingBoxInFrustum(getRenderBoundingBox())

    @SideOnly(Side.CLIENT)
    private fun renderForceField(buffer: BufferBuilder, cx: Double, cy: Double, cz: Double, side: Int,
                                 minU: Double, maxU: Double, minV: Double, maxV: Double,
                                 fx: Double, fz: Double, rx: Double, rz: Double)
    {
        fun vertex(lx: Double, ly: Double, lz: Double, u: Double, v: Double)
        {
            buffer.pos(cx + lx * rx + lz * fx, cy + ly, cz + lx * rz + lz * fz).tex(u, v).endVertex()
        }

        when (side)
        {
            0 ->
            {
                vertex(3.0, 0.0, 7.0, maxU, maxV)
                vertex(3.0, 4.0, 7.0, maxU, minV)
                vertex(-3.0, 4.0, 7.0, minU, minV)
                vertex(-3.0, 0.0, 7.0, minU, maxV)
            }
            1 ->
            {
                vertex(7.0, 0.0, 4.0, maxU, maxV)
                vertex(7.0, 4.0, 4.0, maxU, minV)
                vertex(7.0, 4.0, -4.0, minU, minV)
                vertex(7.0, 0.0, -4.0, minU, maxV)
            }
            2 ->
            {
                vertex(3.0, 0.0, -7.0, maxU, maxV)
                vertex(3.0, 4.0, -7.0, maxU, minV)
                vertex(-3.0, 4.0, -7.0, minU, minV)
                vertex(-3.0, 0.0, -7.0, minU, maxV)
            }
            3 ->
            {
                vertex(-7.0, 0.0, 4.0, maxU, maxV)
                vertex(-7.0, 4.0, 4.0, maxU, minV)
                vertex(-7.0, 4.0, -4.0, minU, minV)
                vertex(-7.0, 0.0, -4.0, minU, maxV)
            }
            4 ->
            {
                vertex(-3.0, 0.0, 7.0, maxU, maxV)
                vertex(-3.0, 4.0, 7.0, maxU, minV)
                vertex(-7.0, 4.0, 4.0, minU, minV)
                vertex(-7.0, 0.0, 4.0, minU, maxV)
            }
            5 ->
            {
                vertex(-3.0, 0.0, -7.0, maxU, maxV)
                vertex(-3.0, 4.0, -7.0, maxU, minV)
                vertex(-7.0, 4.0, -4.0, minU, minV)
                vertex(-7.0, 0.0, -4.0, minU, maxV)
            }
            6 ->
            {
                vertex(3.0, 0.0, 7.0, maxU, maxV)
                vertex(3.0, 4.0, 7.0, maxU, minV)
                vertex(7.0, 4.0, 4.0, minU, minV)
                vertex(7.0, 0.0, 4.0, minU, maxV)
            }
            7 ->
            {
                vertex(3.0, 0.0, -7.0, maxU, maxV)
                vertex(3.0, 4.0, -7.0, maxU, minV)
                vertex(7.0, 4.0, -4.0, minU, minV)
                vertex(7.0, 0.0, -4.0, minU, maxV)
            }
        }
    }

    @SideOnly(Side.CLIENT)
    override fun shouldRenderInPass(pass: Int): Boolean = pass == 0

    override fun isGlobalRenderer(): Boolean = true

    private inner class QuantumForceTransformerRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.75.pow(manipulatorTier)).toInt())
        }

        override fun getParallelLimit() = 16 * shieldingCoreTier

    }
}