package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.EMITTER_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.emitterCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CVD_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockCVDUnit(id: ResourceLocation)
    : RecipeMapMultiblockController(id, CVD_RECIPES)
{

    private var emitterCasingTier = 0
    private var pumpCasingTier = 0
    private var tier = 0

    init
    {
        this.recipeMapWorkable = CVDUnitRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.HSLA_STEEL.state

        private val secondCasingState
            get() = MultiblockCasing.SUBSTRATE_CASING.state

        private val glassState
            get() = GTGlassCasing.TEMPERED_GLASS.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockCVDUnit(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        this.pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        this.tier = minOf(emitterCasingTier, pumpCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.emitterCasingTier = 0
        this.pumpCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "CCCCC", "CCCCC")
        .aisle("CCCCC", "EDDDE", "CGGGC")
        .aisle("CCCCC", "PDDDP", "CGGGC")
        .aisle("CCCCC", "SGGGC", "CGGGC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(20)
            .or(autoAbilities()))
        .where('D', states(secondCasingState))
        .where('G', states(glassState))
        .where('E', emitterCasings())
        .where('P', pumpCasings())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer =  GTLiteTextures.HSLA_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.CVD_UNIT_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.cvd_unit.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.cvd_unit.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.cvd_unit.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.cvd_unit.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.cvd_unit.tooltip.5"))
    }

    override fun canBeDistinct() = true

    private inner class CVDUnitRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor(): Double = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow(emitterCasingTier)).toInt())
        }

        override fun getParallelLimit() =  4 * pumpCasingTier

    }

}
