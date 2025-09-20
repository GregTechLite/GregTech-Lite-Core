package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.*
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtech.core.sound.GTSoundEvents
import gregtechlite.gtlitecore.api.GTLiteAPI.MANIPULATOR_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.SHIELDING_CORE_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.manipulators
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.shieldingCores
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.QUANTUM_FORCE_TRANSFORMER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

class MultiblockQuantumForceTransformer(id: ResourceLocation)
    : RecipeMapMultiblockController(id, QUANTUM_FORCE_TRANSFORMER_RECIPES) // TODO IFastRenderMetaTileEntity, IBloomEffect
{

    private var manipulatorTier = 0
    private var shieldingCoreTier = 0
    private var tier = 0

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

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockQuantumForceTransformer(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        manipulatorTier = context.getAttributeOrDefault(MANIPULATOR_TIER, 0)
        shieldingCoreTier = context.getAttributeOrDefault(SHIELDING_CORE_TIER, 0)
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

    override fun addInformation(stack: ItemStack,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
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
        recipe.chancedOutputs.chancedEntries.forEach { chanceEntry ->
            min(chanceEntry.chance * shieldingCoreTier, 10000)
        }
        recipe.chancedFluidOutputs.chancedEntries.forEach { chanceEntry ->
            min(chanceEntry.chance * shieldingCoreTier, 10000)
        }
        return super.checkRecipe(recipe, consumeIfSuccess)
                && recipe.getProperty(GTLiteRecipeProperties.QUANTUM_FORCE_TRANSFORMER_TIER, 0)!! <= manipulatorTier
    }

    // @SideOnly(Side.CLIENT)
    // override fun renderMetaTileEntity(x: Double, y: Double, z: Double, partialTicks: Float)
    // {
    //     val forceField = GTLiteTextures.FORCE_FIELD
    //     if (isActive && MinecraftForgeClient.getRenderPass() == 0) {
    //         BloomEffectUtil.registerBloomRender(ForceFieldRenderer.INSTANCE, BloomType.UNREAL, this, this) { buffer ->
    //             val entity: Entity? = Minecraft.getMinecraft().renderViewEntity
    //             if (entity != null) {
    //                 val minU = forceField.minU.toDouble()
    //                 val maxU = forceField.maxU.toDouble()
    //                 val minV = forceField.minV.toDouble()
    //                 val maxV = forceField.maxV.toDouble()
    //                 val xBaseOffset: Double = (3 * getFrontFacing().getOpposite().getXOffset()).toDouble()
    //                 val zBaseOffset: Double = (3 * getFrontFacing().getOpposite().getZOffset()).toDouble()
    //                 GlStateManager.pushMatrix()
    //                 GlStateManager.disableCull()
    //                 GlStateManager.disableAlpha()
    //                 GlStateManager.enableBlend()
    //                 Minecraft.getMinecraft().textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE)
    //                 //Center O:  0,  0         1 ------- 8
    //                 //Corner 1:  7, -2        /           \
    //                 //Corner 2:  3, -6     2 /             \ 7
    //                 //Corner 3: -2, -6      |               |
    //                 //Corner 4: -6, -2      |       O       |
    //                 //Corner 5: -6,  3      |               |
    //                 //Corner 6: -2,  7     3 \             / 6
    //                 //Corner 7:  3,  7        \           /
    //                 //Corner 8:  7,  3         4 ------- 5
    //                 buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX)
    //                 GlStateManager.translate(
    //                     x + xBaseOffset + 0.5,
    //                     0,
    //                     MathUtils.z + zBaseOffset + 0.5
    //                 )
    //                 if (zBaseOffset == 0.0) {
    //                     GlStateManager.rotate(90f, 0f, 1f, 0f)
    //                 }
    //                 for (i in 0..7) {
    //                     renderForceField(buffer, 0, MathUtils.y, 0, i, minU, maxU, minV, maxV)
    //                 }
    //                 Tessellator.getInstance().draw()
    //                 GlStateManager.disableBlend()
    //                 GlStateManager.enableAlpha()
    //                 GlStateManager.enableCull()
    //                 GlStateManager.popMatrix()
    //             }
    //         }
    //     }
    // }

    // @SideOnly(Side.CLIENT)
    // private fun renderForceField(buffer: BufferBuilder, x: Double, y: Double, z: Double, side: Int,
    //                              minU: Double, maxU: Double, minV: Double, maxV: Double)
    // {
    //     when (side)
    //     {
    //         0 -> {
    //             buffer.pos(x + 3, y, z + 7).tex(maxU, maxV).endVertex()
    //             buffer.pos(x + 3, y + 4, z + 7).tex(maxU, minV).endVertex()
    //             buffer.pos(x - 3, y + 4, z + 7).tex(minU, minV).endVertex()
    //             buffer.pos(x - 3, y, z + 7).tex(minU, maxV).endVertex()
    //         }
    //         1 -> {
    //             buffer.pos(x + 7, y, z + 4).tex(maxU, maxV).endVertex()
    //             buffer.pos(x + 7, y + 4, z + 4).tex(maxU, minV).endVertex()
    //             buffer.pos(x + 7, y + 4, z - 4).tex(minU, minV).endVertex()
    //             buffer.pos(x + 7, y, z - 4).tex(minU, maxV).endVertex()
    //         }
    //         2 -> {
    //             buffer.pos(x + 3, y, z - 7).tex(maxU, maxV).endVertex()
    //             buffer.pos(x + 3, y + 4, z - 7).tex(maxU, minV).endVertex()
    //             buffer.pos(x - 3, y + 4, z - 7).tex(minU, minV).endVertex()
    //             buffer.pos(x - 3, y, z - 7).tex(minU, maxV).endVertex()
    //         }
    //         3 -> {
    //             buffer.pos(x - 7, y, z + 4).tex(maxU, maxV).endVertex()
    //             buffer.pos(x - 7, y + 4, z + 4).tex(maxU, minV).endVertex()
    //             buffer.pos(x - 7, y + 4, z - 4).tex(minU, minV).endVertex()
    //             buffer.pos(x - 7, y, z - 4).tex(minU, maxV).endVertex()
    //         }
    //         4 -> {
    //             buffer.pos(x - 3, y, z + 7).tex(maxU, maxV).endVertex()
    //             buffer.pos(x - 3, y + 4, z + 7).tex(maxU, minV).endVertex()
    //             buffer.pos(x - 7, y + 4, z + 4).tex(minU, minV).endVertex()
    //             buffer.pos(x - 7, y, z + 4).tex(minU, maxV).endVertex()
    //         }
    //         5 -> {
    //             buffer.pos(x - 3, y, z - 7).tex(maxU, maxV).endVertex()
    //             buffer.pos(x - 3, y + 4, z - 7).tex(maxU, minV).endVertex()
    //             buffer.pos(x - 7, y + 4, z - 4).tex(minU, minV).endVertex()
    //             buffer.pos(x - 7, y, z - 4).tex(minU, maxV).endVertex()
    //         }
    //         6 -> {
    //             buffer.pos(x + 3, y, z + 7).tex(maxU, maxV).endVertex()
    //             buffer.pos(x + 3, y + 4, z + 7).tex(maxU, minV).endVertex()
    //             buffer.pos(x + 7, y + 4, z + 4).tex(minU, minV).endVertex()
    //             buffer.pos(x + 7, y, z + 4).tex(minU, maxV).endVertex()
    //         }
    //         7 -> {
    //             buffer.pos(x + 3, y, z - 7).tex(maxU, maxV).endVertex()
    //             buffer.pos(x + 3, y + 4, z - 7).tex(maxU, minV).endVertex()
    //             buffer.pos(x + 7, y + 4, z - 4).tex(minU, minV).endVertex()
    //             buffer.pos(x + 7, y, z - 4).tex(minU, maxV).endVertex()
    //         }
    //     }
    // }

    private inner class QuantumForceTransformerRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.75.pow(manipulatorTier)).toInt())
        }

        override fun getParallelLimit() = 16 * shieldingCoreTier

    }

}