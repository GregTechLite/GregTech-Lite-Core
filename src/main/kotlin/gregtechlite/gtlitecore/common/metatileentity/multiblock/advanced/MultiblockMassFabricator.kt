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
import gregtech.api.recipes.RecipeMaps.MASS_FABRICATOR_RECIPES
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.EMITTER_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.FIELD_GEN_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PROCESSOR_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.SENSOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.emitterCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.fieldGenCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.processorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.sensorCasings
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipDSL.Companion.addTooltip
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.BoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

class MultiblockMassFabricator(id: ResourceLocation)
    : RecipeMapMultiblockController(id, MASS_FABRICATOR_RECIPES)
{

    private var fieldGenCasingTier = 0
    private var emitterCasingTier = 0
    private var sensorCasingTier = 0
    private var processorCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = LargeMassFabricatorRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.HASTELLOY_N.state
        private val secondCasingState = GTMultiblockCasing.GRATE_CASING.state
        private val pipeCasingState = BoilerCasing.POLYBENZIMIDAZOLE.state
        private val secondPipeCasingState = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state
        private val coilState = GTFusionCasing.SUPERCONDUCTOR_COIL.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockMassFabricator(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        fieldGenCasingTier = context.getAttributeOrDefault(FIELD_GEN_CASING_TIER, 0)
        emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        sensorCasingTier = context.getAttributeOrDefault(SENSOR_CASING_TIER, 0)
        processorCasingTier = context.getAttributeOrDefault(PROCESSOR_CASING_TIER, 0)
        tier = minOf(fieldGenCasingTier, emitterCasingTier, sensorCasingTier, processorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        fieldGenCasingTier = 0
        emitterCasingTier = 0
        sensorCasingTier = 0
        processorCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", " P P ", " P P ", " P P ", "CCCCC")
        .aisle("CCCCC", "PCDCP", "PCDCP", "PCDCP", "CCCCC")
        .aisle("CCCCC", " QUQ ", " QOQ ", " QUQ ", "CDDDC")
        .aisle("CCCCC", " QRQ ", " FEF ", " QRQ ", "CDDDC")
        .aisle("CCCCC", " QRQ ", " FEF ", " QRQ ", "CDDDC")
        .aisle("CCCCC", " QUQ ", " QOQ ", " QUQ ", "CDDDC")
        .aisle("CCCCC", "PCDCP", "PCDCP", "PCDCP", "CCCCC")
        .aisle("CCSCC", " P P ", " P P ", " P P ", "CCCCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(20)
            .or(autoAbilities(true, true, true, false, true, true, false)))
        .where('D', states(secondCasingState))
        .where('P', states(pipeCasingState))
        .where('Q', states(secondPipeCasingState))
        .where('R', states(coilState))
        .where('F', fieldGenCasings())
        .where('E', emitterCasings())
        .where('O', sensorCasings())
        .where('U', processorCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.HASTELLOY_N_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.MASS_FABRICATOR_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            machineType("LMasFab")
            description(true)
            overclockInfo(UV)
            description(false,
                        "gtlitecore.machine.large_mass_fabricator.tooltip.1",
                        "gtlitecore.machine.large_mass_fabricator.tooltip.2")
        }
    }

    override fun canBeDistinct() = false

    private inner class LargeMassFabricatorRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(min(fieldGenCasingTier, processorCasingTier).toDouble()))).toInt())
        }

        override fun getParallelLimit() = 4 * min(emitterCasingTier, sensorCasingTier)

    }

}
