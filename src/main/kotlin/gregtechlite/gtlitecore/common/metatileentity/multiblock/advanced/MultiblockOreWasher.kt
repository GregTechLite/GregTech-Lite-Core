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
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CATALYTIC_REFORMER_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockOreWasher(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(ORE_WASHER_RECIPES, CHEMICAL_BATH_RECIPES,
                                               CRACKING_RECIPES, CATALYTIC_REFORMER_RECIPES))
{

    private var casingTier = 0

    init
    {
        this.recipeMapWorkable = LargeOreWasherRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.GRISIUM.state

        private val pipeCasingState
            get() = GTBoilerCasing.TITANIUM_PIPE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockOreWasher(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.casingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.casingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start() // TODO Water?
        .aisle("CCCCC", "CCCCC", "CCCCC")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCPCC", "CCSCC", "CCCCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(9)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('Q', states(pipeCasingState))
        .where('P', pumpCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.GRISIUM_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_ORE_WASHER_OVERLAY

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_ore_washer.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_ore_washer.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_ore_washer.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_ore_washer.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class LargeOreWasherRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(getTierByVoltage(maxVoltage).toDouble()))).toInt())
        }

        override fun getParallelLimit() = 16 * casingTier

    }

}

