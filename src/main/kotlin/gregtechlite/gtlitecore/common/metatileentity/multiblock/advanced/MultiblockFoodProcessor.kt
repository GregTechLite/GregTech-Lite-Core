package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.ROBOT_ARM_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.robotArmCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.FOOD_PROCESSOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MULTICOOKER_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
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
import kotlin.math.max

class MultiblockFoodProcessor(id: ResourceLocation) : MultiMapMultiblockController(id, arrayOf(FOOD_PROCESSOR_RECIPES, MULTICOOKER_RECIPES))
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
            addMachineTypeLine()
            addOverclockInfo(OverclockMode.PERFECT)
            addParallelInfo(UpgradeMode.ROBOT_ARM_CASING, 8)
            addDurationInfo(UpgradeMode.PUMP_CASING, 250)
            addEnergyInfo(30)
        }
    }

    override fun canBeDistinct() = true

    override fun hasMufflerMechanics() = true

    private inner class LargeFoodProcessorRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte, true)
    {

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -30%
            ocResult.setEut(max(1, (ocResult.eut() * 0.7).toLong()))

            // +250% / pump casing tier | D' = D / (1 + 2.5 * (T - 1)) = D / (2.5 * T - 1.5), where k = 2.5
            if (pumpCasingTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (2.5 * pumpCasingTier - 1.5)).toInt()))
        }

        override fun getParallelLimit() = 8 * robotArmCasingTier

    }

}
