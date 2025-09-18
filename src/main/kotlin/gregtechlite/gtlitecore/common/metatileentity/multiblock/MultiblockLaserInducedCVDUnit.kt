package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues.FALLBACK
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.EMITTER_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.SENSOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.emitterCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.sensorCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LASER_CVD_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOLECULAR_BEAM_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SONICATION_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipDSL.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyX
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

class MultiblockLaserInducedCVDUnit(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(LASER_CVD_RECIPES, CRYSTALLIZATION_RECIPES,
                                               MOLECULAR_BEAM_RECIPES, SONICATION_RECIPES))
{

    private var emitterCasingTier = 0
    private var pumpCasingTier = 0
    private var sensorCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = LaserInducedCVDRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.HASTELLOY_X.state
        private val secondCasingState = MultiblockCasing.SUBSTRATE_CASING.state
        private val glassState = GlassCasing.ZBLAN.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockLaserInducedCVDUnit(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        sensorCasingTier = context.getAttributeOrDefault(SENSOR_CASING_TIER, 0)
        tier = minOf(emitterCasingTier, pumpCasingTier, sensorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        emitterCasingTier = 0
        pumpCasingTier = 0
        sensorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("   CCCCC", "   CGGGC", "   CGGGC", "   CGGGC", "    CCC ")
        .aisle("CCCCCCCC", "CCCFDDDF", "CCCF###F", "CCCF###F", "    FFF ")
        .aisle("CCCCCCCC", "CPCFDDDF", "CPRG###T", "CCCF###F", "    FFF ")
        .aisle("CCCCCCCC", "CCCFDDDF", "CSCF###F", "CCCF###F", "    FFF ")
        .aisle("   CCCCC", "   CGGGC", "   CGGGC", "   CGGGC", "    CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(60)
            .or(autoAbilities()))
        .where('D', states(secondCasingState))
        .where('G', states(glassState))
        .where('F', frames(HastelloyX))
        .where('R', emitterCasings())
        .where('P', pumpCasings())
        .where('T', sensorCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.HASTELLOY_X_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.CVD_UNIT_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("LICVD")
            description(true,
                        "gtlitecore.machine.laser_induced_cvd_unit.tooltip.1")
            overclockInfo(UV)
            durationInfo(UpgradeType.EMITTER_CASING, 50)
            parallelInfo(UpgradeType.PUMP_CASING, 8)
        }
    }

    override fun canBeDistinct() = true

    private inner class LaserInducedCVDRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.5.pow(min(emitterCasingTier, sensorCasingTier))).toInt())
        }

        override fun getParallelLimit() = 8 * pumpCasingTier

    }

}
