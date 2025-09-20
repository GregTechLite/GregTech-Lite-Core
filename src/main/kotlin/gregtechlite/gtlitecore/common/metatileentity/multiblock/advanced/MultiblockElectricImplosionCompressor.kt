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
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipDSL.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
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
        recipeMapWorkable = ElectricImplosionCompressorRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.INCOLOY_MA956.state
        private val pipeCasingState = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state
        private val glassState = GlassCasing.SILICON_CARBIDE.state
    }

    override fun createMetaTileEntity(mte: IGregTechTileEntity) = MultiblockElectricImplosionCompressor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        pistonCasingTier = context.getAttributeOrDefault(PISTON_CASING_TIER, 0)
        emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        tier = minOf(pistonCasingTier, emitterCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pistonCasingTier = 0
        emitterCasingTier = 0
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
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.INCOLOY_MA956_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.IMPLOSION_COMPRESSOR_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("EIC")
            description(true,
                        "gtlitecore.machine.electric_implosion_compressor.tooltip.1")
            overclockInfo(UV)
            durationInfo(UpgradeType.EMITTER_CASING, 50)
            parallelInfo(UpgradeType.PISTON_CASING, 16)
        }
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
