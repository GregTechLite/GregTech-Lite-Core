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
import gregtech.api.recipes.RecipeMaps.ROCK_BREAKER_RECIPES
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.CONVEYOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PISTON_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.conveyorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pistonCasings
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipDSL.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockRockBreaker(id: ResourceLocation)
    : RecipeMapMultiblockController(id, ROCK_BREAKER_RECIPES)
{

    private var pistonCasingTier = 0
    private var conveyorCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = LargeRockBreakerRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.BLACK_STEEL.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockRockBreaker(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        pistonCasingTier = context.getAttributeOrDefault(PISTON_CASING_TIER, 0)
        conveyorCasingTier = context.getAttributeOrDefault(CONVEYOR_CASING_TIER, 0)
        tier = minOf(pistonCasingTier, conveyorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pistonCasingTier = 0
        conveyorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC", "CCC")
        .aisle("CCC", "CMC", "CPC", "CCC")
        .aisle("CCC", "CSC", "CCC", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(4)
            .or(autoAbilities(true, true, true, true, false, false, false)))
        .where('M', conveyorCasings())
        .where('P', pistonCasings())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.BLACK_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.ROCK_BREAKER_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("LRB")
            description(true)
            overclockInfo(UV)
            durationInfo(UpgradeType.CONVEYOR_CASING, 50)
            parallelInfo(UpgradeType.PISTON_CASING, 16)
        }
    }

    override fun canBeDistinct() = false

    private inner class LargeRockBreakerRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(conveyorCasingTier.toDouble()))).toInt())
        }

        override fun getParallelLimit() = 16 * pistonCasingTier

    }

}
