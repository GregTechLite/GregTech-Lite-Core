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
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.GTUtility.getTierByVoltage
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
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max
import kotlin.math.min

class MultiblockReplicator(id: ResourceLocation) : RecipeMapMultiblockController(id, REPLICATOR_RECIPES)
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
            addMachineTypeLine()
            addOverclockInfo(OverclockMode.PERFECT_AFTER)
            addMultiParallelInfo(UpgradeMode.EMITTER_CASING, UpgradeMode.SENSOR_CASING, number = 16)
            addMultiDurationInfo(UpgradeMode.FIELD_GEN_CASING, UpgradeMode.PROCESSOR_CASING, percent = 200)
            addEnergyInfo(25)
        }
    }

    override fun canBeDistinct() = false

    private inner class LargeReplicatorRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor(): Double
            = if (maxVoltage >= V[UV]) PERFECT_DURATION_FACTOR else STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -25%
            ocResult.setEut(max(1, (ocResult.eut() * 0.75).toLong()))

            // +200% / field gen and processor casing tier | D' = D / (1 + 2.0 * (T - 1.0)) = D / (2.0 * T - 1.0), where k = 2.0
            if (fieldGenCasingTier <= 0 || processorCasingTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (2.0 * min(fieldGenCasingTier, processorCasingTier) - 1.0)).toInt()))
        }

        override fun getParallelLimit() = 16 * min(fieldGenCasingTier, processorCasingTier)

    }

}