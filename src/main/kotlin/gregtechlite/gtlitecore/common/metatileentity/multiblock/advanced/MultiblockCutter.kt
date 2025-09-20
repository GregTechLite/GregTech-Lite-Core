package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

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
import gregtech.api.recipes.RecipeMaps.CUTTER_RECIPES
import gregtech.api.recipes.RecipeMaps.LATHE_RECIPES
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.CONVEYOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.conveyorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.POLISHER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SLICER_RECIPES
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

class MultiblockCutter(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(CUTTER_RECIPES, LATHE_RECIPES,
                                               POLISHER_RECIPES, SLICER_RECIPES))
{

    private var motorCasingTier = 0
    private var conveyorCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = LargeCutterRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.MARAGING_STEEL_250.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockCutter(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        motorCasingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
        conveyorCasingTier = context.getAttributeOrDefault(CONVEYOR_CASING_TIER, 0)
        tier = minOf(motorCasingTier, conveyorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        motorCasingTier = 0
        conveyorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "CCC#C", "  C#C")
        .aisle("CCCCC", "CCOMC", "  C#C")
        .aisle("CCCCC", "CSC#C", "  C#C")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(12)
            .or(autoAbilities(true, true, true, true, true, false, false)))
        .where('M', motorCasings())
        .where('O', conveyorCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.MARAGING_STEEL_250_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.CUTTER_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("LC")
            description(true)
            overclockInfo(UV)
            durationInfo(UpgradeType.MOTOR_CASING, 80)
            parallelInfo(UpgradeType.CONVEYOR_CASING, 16)
        }
    }

    override fun canBeDistinct() = true

    private inner class LargeCutterRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(motorCasingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * conveyorCasingTier

    }

}
