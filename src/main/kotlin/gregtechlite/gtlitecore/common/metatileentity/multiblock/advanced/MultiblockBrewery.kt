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
import gregtech.api.recipes.RecipeMaps.*
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipDSL.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockBrewery(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(BREWING_RECIPES, FERMENTING_RECIPES,
                                               FLUID_HEATER_RECIPES, CHEMICAL_DEHYDRATOR_RECIPES))
{

    private var pumpCasingTier = 0
    private var motorCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = LargeBreweryRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.WATERTIGHT_STEEL.state
        private val pipeCasingState = GTBoilerCasing.POLYTETRAFLUOROETHYLENE_PIPE.state
        private val glassState = GTGlassCasing.TEMPERED_GLASS.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockBrewery(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        motorCasingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
        tier = minOf(pumpCasingTier, motorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pumpCasingTier = 0
        motorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " CGC ", " CGC ", " CCC ", "     ")
        .aisle("CCCCC", "C###C", "C###C", "C###C", "CCCCC")
        .aisle("CCMCC", "C#Q#C", "P#Q#P", "C#Q#C", "CCCCC")
        .aisle("CCCCC", "C###C", "C###C", "C###C", "CCCCC")
        .aisle(" CCC ", " CSC ", " CGC ", " CCC ", "     ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(12)
            .or(this.autoAbilities(true, true, true, true, true, true, false)))
        .where('Q', states(pipeCasingState))
        .where('G', states(glassState))
        .where('M', motorCasings())
        .where('P', pumpCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.WATERTIGHT_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.LARGE_BREWERY_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("LBr")
            description(true)
            overclockInfo(UV)
            durationInfo(UpgradeType.MOTOR_CASING, 80)
            parallelInfo(UpgradeType.PUMP_CASING, 16)
        }
    }

    override fun canBeDistinct() = true

    private inner class LargeBreweryRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {
        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(motorCasingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * pumpCasingTier

    }

}
