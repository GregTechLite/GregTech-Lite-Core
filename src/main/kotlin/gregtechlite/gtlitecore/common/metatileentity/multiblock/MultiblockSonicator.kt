package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues
import gregtech.api.GTValues.UV
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.BOROSILICATE_GLASS_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.borosilicateGlasses
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SONICATION_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipDSL.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

class MultiblockSonicator(id: ResourceLocation)
    : RecipeMapMultiblockController(id, SONICATION_RECIPES)
{

    private var motorCasingTier = 0
    private var pumpCasingTier = 0
    private var glassTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = SonicatorRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.COBALT_BRASS.state
        private val pipeCasingState = GTBoilerCasing.TITANIUM_PIPE.state
    }


    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockSonicator(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        motorCasingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
        pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        glassTier = context.getAttributeOrDefault(BOROSILICATE_GLASS_TIER, 0)
        tier = minOf(motorCasingTier, pumpCasingTier, glassTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        motorCasingTier = 0
        pumpCasingTier = 0
        glassTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "CCCCC", "CCCCC", "     ")
        .aisle("CCCCC", "C###C", "CGGGC", "     ")
        .aisle("CCCCC", "C#M#C", "CGQGC", "  P  ")
        .aisle("CCCCC", "C###C", "CGGGC", "  P  ")
        .aisle("CCCCC", "CCCCC", "CCCCC", "  P  ")
        .aisle(" CCC ", " CPC ", " CQC ", "  P  ")
        .aisle(" CCC ", " CSC ", " CCC ", "     ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(46)
            .or(autoAbilities()))
        .where('P', states(pipeCasingState))
        .where('M', motorCasings())
        .where('Q', pumpCasings())
        .where('G', borosilicateGlasses())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.COBALT_BRASS_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer =  GTLiteTextures.SONICATOR_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("Son")
            description(true)
            overclockInfo(UV)
            description(false,
                        "gtlitecore.machine.sonicator.tooltip.1")
            parallelInfo(UpgradeType.VOLTAGE_TIER, 4)
        }
    }

    override fun canBeDistinct() = true

    private inner class SonicatorRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (glassTier >= UV - 3) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow(min(motorCasingTier, pumpCasingTier))).toInt())
        }

        override fun getParallelLimit() = 4 * getTierByVoltage(maxVoltage)

    }

}
