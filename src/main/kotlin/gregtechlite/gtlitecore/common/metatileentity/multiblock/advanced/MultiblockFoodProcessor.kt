package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.FALLBACK
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.ROBOT_ARM_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.robotArmCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.FOOD_PROCESSOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MULTICOOKER_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTCleanroomCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockFoodProcessor(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(FOOD_PROCESSOR_RECIPES, MULTICOOKER_RECIPES))
{

    private var robotArmCasingTier = 0
    private var pumpCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = LargeFoodProcessorRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.BISMUTH_BRONZE.state
        private val secondCasingState = GTMultiblockCasing.GRATE_CASING.state
        private val thirdCasingState = GTCleanroomCasing.PLASCRETE.state
        private val glassState = GTGlassCasing.CLEANROOM_GLASS.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockFoodProcessor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        robotArmCasingTier = context.getAttributeOrDefault(ROBOT_ARM_CASING_TIER, 0)
        pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        tier = minOf(robotArmCasingTier, pumpCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        robotArmCasingTier = 0
        pumpCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " CDC ", " CCC ", "     ")
        .aisle("CEEEC", "C###C", "C#P#C", " CCC ")
        .aisle("CEEEC", "DR#RD", "C###C", " COC ")
        .aisle("CEEEC", "C###C", "C###C", " CCC ")
        .aisle(" CSC ", " CGC ", " CCC ", "     ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(16)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('D', states(secondCasingState))
        .where('E', states(thirdCasingState))
        .where('G', states(glassState))
        .where('R', robotArmCasings())
        .where('P', pumpCasings())
        .where('O', abilities(MUFFLER_HATCH))
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.BISMUTH_BRONZE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.MULTICOOKER_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine("LFP")
            addDescriptionLine(true)
            overclockInfo(FALLBACK)
            durationInfo(UpgradeType.PUMP_CASING, 80)
            parallelInfo(UpgradeType.ROBOT_ARM_CASING, 8)
        }
    }

    override fun canBeDistinct() = true

    override fun hasMufflerMechanics() = true

    private inner class LargeFoodProcessorRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte, true)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(pumpCasingTier))).toInt())
        }

        override fun getParallelLimit() = 8 * robotArmCasingTier

    }

}
