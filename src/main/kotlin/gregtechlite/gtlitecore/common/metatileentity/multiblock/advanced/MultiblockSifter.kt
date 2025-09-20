package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.FALLBACK
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.SIFTER_RECIPES
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.CONVEYOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.conveyorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipDSL.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockSifter(id: ResourceLocation)
    : RecipeMapMultiblockController(id, SIFTER_RECIPES)
{

    private var casingTier = 0

    init
    {
        recipeMapWorkable = LargeSifterRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.EGLIN_STEEL.state
        private val secondCasingState = GTMultiblockCasing.GRATE_CASING.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockSifter(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        casingTier = context.getAttributeOrDefault(CONVEYOR_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        casingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "MCCCM", "CCCCC")
        .aisle("CCCCC", "C###C", "CGGGC")
        .aisle("CCCCC", "C###C", "CGGGC")
        .aisle("CCCCC", "C###C", "CGGGC")
        .aisle("CCCCC", "MCSCM", "CCCCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(16)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('G', states(secondCasingState))
        .where('M', conveyorCasings())
        .where('#', air())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.EGLIN_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.LARGE_SIFTER_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("LS")
            description(true)
            overclockInfo(FALLBACK)
            durationInfo(UpgradeType.VOLTAGE_TIER, 50)
            parallelInfo(UpgradeType.CONVEYOR_CASING, 8)
        }
    }

    override fun canBeDistinct() = true

    private inner class LargeSifterRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte, true)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(getTierByVoltage(maxVoltage).toDouble()))).toInt())
        }

        override fun getParallelLimit() = 8 * casingTier

    }

}
