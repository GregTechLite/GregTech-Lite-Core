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
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.EMITTER_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PISTON_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.emitterCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pistonCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ELECTRIC_IMPLOSION_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockElectricImplosionCompressor(id: ResourceLocation) : RecipeMapMultiblockController(id, ELECTRIC_IMPLOSION_RECIPES)
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
            addMachineTypeLine()
            addDescriptionLine("gtlitecore.machine.electric_implosion_compressor.tooltip.1")
            addOverclockInfo(OverclockMode.PERFECT_AFTER)
            addParallelInfo(UpgradeMode.PISTON_CASING, 16)
            addDurationInfo(UpgradeMode.EMITTER_CASING, 350)
            addEnergyInfo(UpgradeMode.VOLTAGE_TIER, 35)
        }
    }

    override fun canBeDistinct() = true

    private inner class ElectricImplosionCompressorRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {
        override fun getOverclockingDurationFactor(): Double
            = if (maxVoltage >= V[UV]) PERFECT_DURATION_FACTOR else STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -35% / voltage tier
            ocResult.setEut(max(1, (ocResult.eut() * (1.0 - getTierByVoltage(maxVoltage) * 0.35)).toLong()))

            // +350% / emitter casing tier | D' = D / (1 + 3.5 * (T - 1)) = D / (3.5 * T - 2.5), where k = 3.5
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (3.5 * getTierByVoltage(maxVoltage) - 2.5)).toInt()))
        }

        override fun getParallelLimit() = 16 * pistonCasingTier

    }

}
