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
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
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

class MultiblockExtractor(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(EXTRACTOR_RECIPES, CANNER_RECIPES))
{

    private var pumpCasingTier = 0
    private var motorCasingTier = 0
    private var tier = 0

    init
    {
        this.recipeMapWorkable = LargeExtractorRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.TALONITE.state

        private val pipeCasingState
            get() = GTBoilerCasing.STEEL_PIPE.state
    }


    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockExtractor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        this.motorCasingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
        this.tier = minOf(pumpCasingTier, motorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.pumpCasingTier = 0
        this.motorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "C   C", "C   C", "CCCCC")
        .aisle("CCCCC", " CCC ", " CCC ", "CCCCC")
        .aisle("CCCCC", " PQM ", " CQC ", "CCCCC")
        .aisle("CCCCC", " CSC ", " CCC ", "CCCCC")
        .aisle("CCCCC", "C   C", "C   C", "CCCCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(16)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('Q', states(pipeCasingState))
        .where('P', pumpCasings())
        .where('M', motorCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.TALONITE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_extractor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_extractor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_extractor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_extractor.tooltip.4"))
    }

    override fun canBeDistinct(): Boolean = true

    private inner class LargeExtractorRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(motorCasingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * pumpCasingTier

    }

}
