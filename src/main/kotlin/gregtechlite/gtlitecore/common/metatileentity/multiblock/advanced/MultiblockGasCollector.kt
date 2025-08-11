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
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LARGE_GAS_COLLECTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockGasCollector(id: ResourceLocation)
    : RecipeMapMultiblockController(id, LARGE_GAS_COLLECTOR_RECIPES)
{

    private var casingTier = 0

    init
    {
        this.recipeMapWorkable = LargeGasCollectorRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.KOVAR.state

        private val secondCasingState
            get() = GTMultiblockCasing.GRATE_CASING.state

        private val glassState
            get() = GTGlassCasing.CLEANROOM_GLASS.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockGasCollector(metaTileEntityId)

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

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " CCC ", "     ", "     ")
        .aisle("F   F", "CCCCC", "CGGGC", "CCCCC")
        .aisle("     ", "CDDDC", "D#P#D", "CDDDC")
        .aisle("F   F", "CCCCC", "CGGGC", "CCCCC")
        .aisle(" CCC ", " CSC ", "     ", "     ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(25)
            .or(autoAbilities(true, true, true, false, false, true, false)))
        .where('D', states(secondCasingState))
        .where('G', states(glassState))
        .where('F', frames(HSLASteel))
        .where('P', pumpCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.KOVAR_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_gas_collector.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_gas_collector.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_gas_collector.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_gas_collector.tooltip.4"))
    }

    override fun canBeDistinct() = false

    private inner class LargeGasCollectorRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(getTierByVoltage(maxVoltage).toDouble()))).toInt())
        }

        override fun getParallelLimit() = 16 * casingTier

    }

}
