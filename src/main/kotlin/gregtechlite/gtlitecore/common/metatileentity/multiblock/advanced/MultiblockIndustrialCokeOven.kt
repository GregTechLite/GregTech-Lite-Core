package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.block.IHeatingCoilBlockStats
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
import gregtech.client.utils.TooltipHelper
import gregtech.common.blocks.BlockWireCoil
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.HEATING_COIL_STATS
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
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
        this.recipeMapWorkable = IndustrialCokeOvenRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.ALUMINIUM_BRONZE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockIndustrialCokeOven(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)

        val coilType = context.get<Any>(HEATING_COIL_STATS)
        if (coilType is IHeatingCoilBlockStats)
        {
            this.coilTier = coilType.tier
        }
        else
        {
            this.coilTier = BlockWireCoil.CoilType.CUPRONICKEL.tier
        }
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.coilTier = 0
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
        .where('H', heatingCoils())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.ALUMINIUM_BRONZE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.INDUSTRIAL_COKE_OVEN_OVERLAY

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.industrial_coke_oven.tooltip.1"))
        tooltip.add(I18n.format(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc")))
        tooltip.add(I18n.format("gtlitecore.machine.industrial_coke_oven.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.industrial_coke_oven.tooltip.3"))
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
