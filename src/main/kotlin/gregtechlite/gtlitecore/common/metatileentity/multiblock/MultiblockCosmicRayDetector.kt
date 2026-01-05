package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.block.VariantActiveBlock
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.BlockWorldState
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.BlockInfo
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.EMITTER_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.FIELD_GEN_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PROCESSOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.SENSOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.emitterCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.fieldGenCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.processorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.sensorCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.COSMIC_RAY_DETECTING_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import gregtechlite.gtlitecore.api.metatileentity.multiblock.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.metatileentity.multiblock.OverclockMode
import gregtechlite.gtlitecore.api.metatileentity.multiblock.UpgradeMode
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*
import kotlin.math.max
import kotlin.math.min

class MultiblockCosmicRayDetector(id: ResourceLocation) : RecipeMapMultiblockController(id, COSMIC_RAY_DETECTING_RECIPES)
{

    private var emitterCasingTier = 0
    private var sensorCasingTier = 0
    private var fieldGenCasingTier = 0
    private var processorCasingTier = 0
    private var tier = 0

    // Target block at the top block in multiblock structure top.
    private var topBlockPos = BlockPos(0, -64, 0)

    init
    {
        recipeMapWorkable = CosmicRayDetectorWorkableHandler(this)
    }

    companion object
    {
        private val casingState = MetalCasing.QUANTUM_ALLOY.state
        private val secondCasingState = MultiblockCasing.REFLECTIVE_SURFACE_CASING.state
        private val coilState = GTFusionCasing.SUPERCONDUCTOR_COIL.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockCosmicRayDetector(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        sensorCasingTier = context.getAttributeOrDefault(SENSOR_CASING_TIER, 0)
        fieldGenCasingTier = context.getAttributeOrDefault(FIELD_GEN_CASING_TIER, 0)
        processorCasingTier = context.getAttributeOrDefault(PROCESSOR_CASING_TIER, 0)
        tier = minOf(emitterCasingTier, sensorCasingTier, fieldGenCasingTier, processorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        emitterCasingTier = 0
        sensorCasingTier = 0
        fieldGenCasingTier = 0
        processorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "               ")
        .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "    DD   DD    ", "               ")
        .aisle("               ", "               ", "               ", "               ", "               ", "               ", "       D       ", "    DDD DDD    ", "   D       D   ", "               ")
        .aisle("      CCC      ", "      CCC      ", "      CCC      ", "               ", "               ", "       C       ", "     DDDDD     ", "   DD     DD   ", "  D         D  ", "               ")
        .aisle("     CCCCC     ", "     C###C     ", "     C###C     ", "      CCC      ", "      CCC      ", "     CCCCC     ", "    DDDDDDD    ", "  DD       DD  ", " D           D ", "               ")
        .aisle("    CCCCCCC    ", "    C#####C    ", "    C#####C    ", "     C###C     ", "     C###C     ", "    CCDDDCC    ", "   DDD   DDD   ", "  D         D  ", " D           D ", "               ")
        .aisle("   CCCCCCCCC   ", "   C###E###C   ", "   C#######C   ", "    C#####C    ", "    C##F##C    ", "    CDDDDDC    ", "   DD     DD   ", " DD         DD ", "D             D", "       *       ")
        .aisle("   CCCCCCCCC   ", "   C##ExE##C   ", "   C###c###C   ", "    C##c##C    ", "    C#FxF#C    ", "   CCDDEDDCC   ", "  DDD  f  DDD  ", " D     f     D ", "D      f      D", "      *s*      ")
        .aisle("   CCCCCCCCC   ", "   C###E###C   ", "   C#######C   ", "    C#####C    ", "    C##F##C    ", "    CDDDDDC    ", "   DD     DD   ", " DD         DD ", "D             D", "       *       ")
        .aisle("    CCCCCCC    ", "    C#####C    ", "    C#####C    ", "     C###C     ", "     C###C     ", "    CCDDDCC    ", "   DDD   DDD   ", "  D         D  ", " D           D ", "               ")
        .aisle("     CCCCC     ", "     C###C     ", "     C###C     ", "      CCC      ", "      CCC      ", "     CCCCC     ", "    DDDDDDD    ", "  DD       DD  ", " D           D ", "               ")
        .aisle("      CCC      ", "      CSC      ", "      CCC      ", "               ", "               ", "       C       ", "     DDDDD     ", "   DD     DD   ", "  D         D  ", "               ")
        .aisle("               ", "               ", "               ", "               ", "               ", "               ", "       D       ", "    DDD DDD    ", "   D       D   ", "               ")
        .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "    DD   DD    ", "               ")
        .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "               ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(148)
            .or(autoAbilities()))
        .where('D', states(secondCasingState))
        .where('c', states(coilState))
        .where('f', frames(HDCS))
        .where('E', emitterCasings())
        .where('F', fieldGenCasings())
        .where('s', sensorCasings())
        .where('x', processorCasings())
        .where('*', minHeightPredicate(Blocks.AIR.defaultState))
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    private fun minHeightPredicate(vararg allowedStates: IBlockState?) = TraceabilityPredicate({ blockWorldState: BlockWorldState ->
        topBlockPos = blockWorldState.pos
        val state: IBlockState = blockWorldState.blockState
        if (state.block is VariantActiveBlock<*>)
            blockWorldState.matchContext.getOrPut("VABlock", LinkedList<Any>()).add(blockWorldState.pos)
            allowedStates.contains(state)
        }, {
            allowedStates.map { BlockInfo(it, null) }.toTypedArray()
        })

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.QUANTUM_ALLOY_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.COSMIC_RAY_DETECTOR_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gtlitecore.machine.cosmic_ray_detector.tooltip.1",
                               "gtlitecore.machine.cosmic_ray_detector.tooltip.2")
            addOverclockInfo(OverclockMode.PERFECT)
            addMultiParallelInfo(UpgradeMode.EMITTER_CASING, UpgradeMode.FIELD_GEN_CASING, number = 32)
            addMultiDurationInfo(UpgradeMode.SENSOR_CASING, UpgradeMode.PROCESSOR_CASING, percent = 400)
            addEnergyInfo(30)
        }
    }

    override fun canBeDistinct() = false

    private inner class CosmicRayDetectorWorkableHandler(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte, true)
    {

        override fun checkRecipe(recipe: Recipe): Boolean
            = super.checkRecipe(recipe) && recipe.getProperty(GTLiteRecipeProperties.MINIMUM_HEIGHT, -64)!! <= topBlockPos.y

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -30%
            ocResult.setEut(max(1, (ocResult.eut() * 0.7).toLong()))

            // +400% / sensor and processor casing tier | D' = D / (1 + 4.0 * (T - 1.0)) = D / (4.0 * T - 3.0), where k = 4.0
            if (sensorCasingTier <= 0 || processorCasingTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (4.0 * min(sensorCasingTier, processorCasingTier) - 3.0)).toInt()))
        }

        override fun getParallelLimit() = 32 * min(emitterCasingTier, fieldGenCasingTier)

    }

}
