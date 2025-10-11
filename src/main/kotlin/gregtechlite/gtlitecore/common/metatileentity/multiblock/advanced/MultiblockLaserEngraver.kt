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
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.CONVEYOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.EMITTER_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.conveyorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.emitterCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockLaserEngraver(id: ResourceLocation) : RecipeMapMultiblockController(id, LASER_ENGRAVER_RECIPES)
{

    private var emitterCasingTier = 0
    private var conveyorCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = LargeLaserEngraverRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.ZERON_100.state
        private val glassState = GlassCasing.WOODS.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockLaserEngraver(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        conveyorCasingTier = context.getAttributeOrDefault(CONVEYOR_CASING_TIER, 0)
        tier = minOf(emitterCasingTier, conveyorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        emitterCasingTier = 0
        conveyorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC", " C ")
        .aisle("CCC", "GMG", "CEC", " C ")
        .aisle("CCC", "GMG", "CEC", " C ")
        .aisle("CCC", "GMG", "CEC", " C ")
        .aisle("CCC", "CSC", "CCC", " C ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(4)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('G', states(glassState))
        .where('M', conveyorCasings())
        .where('E', emitterCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.ZERON_100_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.LASER_ENGRAVER_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addOverclockInfo(OverclockMode.PERFECT_AFTER)
            addParallelInfo(UpgradeMode.EMITTER_CASING, 16)
            addDurationInfo(UpgradeMode.CONVEYOR_CASING, 400)
            addEnergyInfo(UpgradeMode.VOLTAGE_TIER, 50)
        }
    }

    override fun canBeDistinct() = true

    private inner class LargeLaserEngraverRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor(): Double
            = if (maxVoltage >= V[UV]) PERFECT_DURATION_FACTOR else STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -50% / voltage tier
            ocResult.setEut(max(1, (ocResult.eut() * (1.0 - getTierByVoltage(maxVoltage) * 0.5)).toLong()))

            // +400% / conveyor casing tier | t = d / (1 + 4.0 * (c - 1)) = d / (4.0 * c - 3.0), where b = 4.0
            if (conveyorCasingTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (4.0 * conveyorCasingTier - 3.0)).toInt()))
        }

        override fun getParallelLimit() = 16 * emitterCasingTier

    }

}