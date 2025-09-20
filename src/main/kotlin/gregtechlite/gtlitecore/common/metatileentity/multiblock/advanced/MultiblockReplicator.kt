package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.REPLICATOR_RECIPES
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.EMITTER_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.FIELD_GEN_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PROCESSOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.SENSOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.emitterCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.fieldGenCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.processorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.sensorCasings
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipDSL.Companion.addTooltip
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

class MultiblockReplicator(id: ResourceLocation)
    : RecipeMapMultiblockController(id, REPLICATOR_RECIPES)
{

    private var fieldGenCasingTier = 0
    private var emitterCasingTier = 0
    private var sensorCasingTier = 0
    private var processorCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = LargeReplicatorRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.RENE_N5.state
        private val coilState = GTFusionCasing.SUPERCONDUCTOR_COIL.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockReplicator(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        fieldGenCasingTier = context.getAttributeOrDefault(FIELD_GEN_CASING_TIER, 0)
        emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        sensorCasingTier = context.getAttributeOrDefault(SENSOR_CASING_TIER, 0)
        processorCasingTier = context.getAttributeOrDefault(PROCESSOR_CASING_TIER, 0)
        tier = minOf(fieldGenCasingTier, emitterCasingTier, sensorCasingTier, processorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        fieldGenCasingTier = 0
        emitterCasingTier = 0
        sensorCasingTier = 0
        processorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("     XXEXX     ", "     XXEXX     ", "       X       ")
        .aisle("   XXXXXXXXX   ", "   XXCCCCCXX   ", "     XPXPX     ")
        .aisle("  XXXXXEXXXXX  ", "  XCCCXSXCCCX  ", "   XXF s FXX   ")
        .aisle(" XXXXX   XXXXX ", " XCCXX   XXCCX ", "  XF       FX  ")
        .aisle(" XXX       XXX ", " XCX       XCX ", "  X         X  ")
        .aisle("XXXX       XXXX", "XCCX       XCCX", " XF         FX ")
        .aisle("XXX         XXX", "XCX         XCX", " P           P ")
        .aisle("EXE         EXE", "ECE         ECE", "XXs         sXX")
        .aisle("XXX         XXX", "XCX         XCX", " P           P ")
        .aisle("XXXX       XXXX", "XCCX       XCCX", " XF         FX ")
        .aisle(" XXX       XXX ", " XCX       XCX ", "  X         X  ")
        .aisle(" XXXXX   XXXXX ", " XCCXX   XXCCX ", "  XF       FX  ")
        .aisle("  XXXXXEXXXXX  ", "  XCCCXEXCCCX  ", "   XXF s FXX   ")
        .aisle("   XXXXXXXXX   ", "   XXCCCCCXX   ", "     XPXPX     ")
        .aisle("     XXEXX     ", "     XXEXX     ", "       X       ")
        .where('S', selfPredicate())
        .where('X', states(casingState)
            .setMinGlobalLimited(30)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('C', states(coilState))
        .where('F', fieldGenCasings())
        .where('P', processorCasings())
        .where('s', sensorCasings())
        .where('E', emitterCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.RENE_N5_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.REPLICATOR_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("LRep")
            description(true)
            overclockInfo(UV)
            description(false,
                        "gtlitecore.machine.large_replicator.tooltip.1",
                        "gtlitecore.machine.large_replicator.tooltip.2")
        }
    }

    override fun canBeDistinct() = false

    private inner class LargeReplicatorRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(min(emitterCasingTier, sensorCasingTier).toDouble()))).toInt())
        }

        override fun getParallelLimit() = 4 * min(fieldGenCasingTier, processorCasingTier)

    }

}