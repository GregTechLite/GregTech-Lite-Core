package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.block.VariantActiveBlock
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.*
import gregtech.api.recipes.Recipe
import gregtech.api.util.BlockInfo
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.utils.TooltipHelper
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
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

class MultiblockCosmicRayDetector(id: ResourceLocation)
    : RecipeMapMultiblockController(id, COSMIC_RAY_DETECTING_RECIPES)
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
        this.recipeMapWorkable = CosmicRayDetectorWorkableHandler(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.QUANTUM_ALLOY.state

        private val secondCasingState
            get() = MultiblockCasing.REFLECTIVE_SURFACE_CASING.state

        private val coilState
            get() = GTFusionCasing.SUPERCONDUCTOR_COIL.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockCosmicRayDetector(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        this.sensorCasingTier = context.getAttributeOrDefault(SENSOR_CASING_TIER, 0)
        this.fieldGenCasingTier = context.getAttributeOrDefault(FIELD_GEN_CASING_TIER, 0)
        this.processorCasingTier = context.getAttributeOrDefault(PROCESSOR_CASING_TIER, 0)
        this.tier = minOf(emitterCasingTier, sensorCasingTier, fieldGenCasingTier, processorCasingTier)
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
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.QUANTUM_ALLOY_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.COSMIC_RAY_DETECTOR_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.5"))
    }

    override fun canBeDistinct() =  false

    private inner class CosmicRayDetectorWorkableHandler(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte, true)
    {

        override fun checkRecipe(recipe: Recipe): Boolean
            = super.checkRecipe(recipe) && recipe.getProperty(GTLiteRecipeProperties.MINIMUM_HEIGHT, -64)!! <= topBlockPos.y

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.5.pow(min(sensorCasingTier, processorCasingTier))).toInt())
        }

        override fun getParallelLimit() = 32 * min(emitterCasingTier, fieldGenCasingTier)

    }

}
