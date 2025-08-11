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
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.EMITTER_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PISTON_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.emitterCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pistonCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ELECTRIC_IMPLOSION_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockElectricImplosionCompressor(id: ResourceLocation)
    : RecipeMapMultiblockController(id, ELECTRIC_IMPLOSION_RECIPES)
{

    private var pistonCasingTier = 0
    private var emitterCasingTier = 0
    private var tier = 0

    init
    {
        this.recipeMapWorkable = ElectricImplosionCompressorRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.INCOLOY_MA956.state

        private val pipeCasingState
            get() = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state

        private val glassState
            get() = GlassCasing.SILICON_CARBIDE.state
    }

    override fun createMetaTileEntity(mte: IGregTechTileEntity) = MultiblockElectricImplosionCompressor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.pistonCasingTier = context.getAttributeOrDefault(PISTON_CASING_TIER, 0)
        this.emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        this.tier = minOf(pistonCasingTier, emitterCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.pistonCasingTier = 0
        this.emitterCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "F   F", "F   F", "F   F", "CCCCC")
        .aisle("CCCCC", " QGQ ", " QGQ ", " QGQ ", "CCCCC")
        .aisle("CCPCC", " G#G ", " G#G ", " G#G ", "CCTCC")
        .aisle("CCCCC", " QGQ ", " QGQ ", " QGQ ", "CCCCC")
        .aisle("CCSCC", "F   F", "F   F", "F   F", "CCCCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(20)
            .or(autoAbilities(true, true, true, true, true, false, false)))
        .where('Q', states(pipeCasingState))
        .where('G', states(glassState))
        .where('F', frames(TungstenSteel))
        .where('P', pistonCasings())
        .where('T', emitterCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.INCOLOY_MA956_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.IMPLOSION_COMPRESSOR_OVERLAY

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.electric_implosion_compressor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.electric_implosion_compressor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.electric_implosion_compressor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.electric_implosion_compressor.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.electric_implosion_compressor.tooltip.5"))
    }

    override fun canBeDistinct() = true

    private inner class ElectricImplosionCompressorRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {
        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(emitterCasingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * pistonCasingTier

    }

}
