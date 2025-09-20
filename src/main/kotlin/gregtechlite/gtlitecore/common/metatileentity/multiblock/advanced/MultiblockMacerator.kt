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
import gregtech.api.recipes.RecipeMaps.MACERATOR_RECIPES
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PISTON_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
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

class MultiblockMacerator(id: ResourceLocation)
    : RecipeMapMultiblockController(id, MACERATOR_RECIPES)
{

    private var pistonCasingTier = 0
    private var motorCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = LargeMaceratorRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.STELLITE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockMacerator(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        pistonCasingTier = context.getAttributeOrDefault(PISTON_CASING_TIER, 0)
        motorCasingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
        tier = minOf(pistonCasingTier, motorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pistonCasingTier = 0
        motorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC", "CCC", "CCC", "CCC")
        .aisle("CCC", "CMC", "C#C", "CPC", "C#C", "CCC")
        .aisle("CSC", "CCC", "CCC", "CCC", "CCC", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(10)
            .or(autoAbilities(true, true, true, true, false, false, false)))
        .where('P', pistonCasings())
        .where('M', motorCasings())
        .where('#', air())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.STELLITE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.LARGE_MACERATOR_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("LMa")
            description(true)
            overclockInfo(UV)
            durationInfo(UpgradeType.MOTOR_CASING, 50)
            parallelInfo(UpgradeType.PISTON_CASING, 8)
        }
    }

    override fun canBeDistinct() = true

    private inner class LargeMaceratorRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(motorCasingTier))).toInt())
        }

        override fun getParallelLimit() = 8 * pistonCasingTier

    }

}