package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.FALLBACK
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.PYROLYSE_RECIPES
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.blocks.BlockWireCoil
import gregtechlite.gtlitecore.api.GTLiteAPI.COIL_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.coils
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
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

class MultiblockIndustrialCokeOven(id: ResourceLocation)
    : RecipeMapMultiblockController(id, PYROLYSE_RECIPES)
{

    private var coilTier = 0

    init
    {
        recipeMapWorkable = IndustrialCokeOvenRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.ALUMINIUM_BRONZE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockIndustrialCokeOven(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        coilTier = context.getAttributeOrDefault(COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).tier
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        coilTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "CCCCC")
        .aisle("CCCCC", "CHCHC")
        .aisle("CCCCC", "CHCHC")
        .aisle("CCSCC", "CCCCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(12)
            .or(autoAbilities(true, true, true, true, true, true, true)))
        .where('H', coils())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.ALUMINIUM_BRONZE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.INDUSTRIAL_COKE_OVEN_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("ICO")
            description(true)
            overclockInfo(FALLBACK)
            durationInfo(UpgradeType.WIRE_COIL, 80)
            parallelInfo(UpgradeType.VOLTAGE_TIER, 8)
        }
    }

    override fun canBeDistinct() = true

    override fun hasMufflerMechanics() = true

    private inner class IndustrialCokeOvenRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte, true)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(coilTier))).toInt())
        }

        override fun getParallelLimit() = 8 * getTierByVoltage(maxVoltage)

    }

}
