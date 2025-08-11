package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.MultiblockAbility.*
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.utils.TooltipHelper
import gregtechlite.gtlitecore.api.GTLiteAPI.EMITTER_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.FIELD_GEN_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.SENSOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.emitterCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.fieldGenCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.sensorCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOLECULAR_BEAM_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.PLASMA_CVD_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SONICATION_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
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

class MultiblockPlasmaEnhancedCVDUnit(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(PLASMA_CVD_RECIPES, CRYSTALLIZATION_RECIPES,
                                               MOLECULAR_BEAM_RECIPES, SONICATION_RECIPES))
{

    private var emitterCasingTier = 0
    private var sensorCasingTier = 0
    private var fieldGenCasingTier = 0
    private var pumpCasingTier = 0
    private var tier = 0

    init
    {
        this.recipeMapWorkable = PlasmaEnhancedCVDRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.TRINAQUADALLOY.state

        private val secondCasingState
            get() = MultiblockCasing.ADVANCED_SUBSTRATE_CASING.state

        private val uniqueCasingState
            get() = ActiveUniqueCasing.HEAT_VENT.state

        private val glassState
            get() = GlassCasing.PMMA.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockPlasmaEnhancedCVDUnit(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        this.sensorCasingTier = context.getAttributeOrDefault(SENSOR_CASING_TIER, 0)
        this.fieldGenCasingTier = context.getAttributeOrDefault(FIELD_GEN_CASING_TIER, 0)
        this.pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        this.tier = minOf(emitterCasingTier, sensorCasingTier, fieldGenCasingTier, pumpCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.emitterCasingTier = 0
        this.sensorCasingTier = 0
        this.fieldGenCasingTier = 0
        this.pumpCasingTier = 0
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        inputEnergy.addAll(getAbilities(SUBSTATION_INPUT_ENERGY))
        this.energyContainer = EnergyContainerList(inputEnergy)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCCCC", " CRRRC ", " CTTTC ", "  CCC  ")
        .aisle("CCCCCCC", "CDDDDDC", "C#####C", " CGGGC ")
        .aisle("PCCCCCP", "HDDDDDH", "F#####F", " CGGGC ")
        .aisle("CCCCCCC", "CDDDDDC", "C#####C", " CGGGC ")
        .aisle("CCCCCCC", " SGGGC ", " CGGGC ", "       ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(25)
            .or(autoAbilities(false, true, true, true, true, true, false))
            .or(abilities(INPUT_ENERGY)
                .setMaxGlobalLimited(3))
            .or(abilities(INPUT_LASER)
                .setMaxGlobalLimited(1)))
        .where('D', states(secondCasingState))
        .where('H', states(uniqueCasingState))
        .where('G', states(glassState))
        .where('R', emitterCasings())
        .where('T', sensorCasings())
        .where('F', fieldGenCasings())
        .where('P', pumpCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.TRINAQUADALLOY_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.CVD_UNIT_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.plasma_enhanced_cvd_unit.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.plasma_enhanced_cvd_unit.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.plasma_enhanced_cvd_unit.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.plasma_enhanced_cvd_unit.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.plasma_enhanced_cvd_unit.tooltip.5"))
    }

    override fun canBeDistinct() = true

    private inner class PlasmaEnhancedCVDRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte, true)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.5.pow(min(emitterCasingTier, sensorCasingTier))).toInt())
        }

        override fun getParallelLimit() = 32 * min(pumpCasingTier, fieldGenCasingTier)

    }

}