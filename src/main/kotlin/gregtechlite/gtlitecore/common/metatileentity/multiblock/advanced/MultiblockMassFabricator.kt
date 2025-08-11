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
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.BoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
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
        this.recipeMapWorkable = LargeMassFabricatorRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.HASTELLOY_N.state

        private val secondCasingState
            get() = GTMultiblockCasing.GRATE_CASING.state

        private val pipeCasingState
            get() = BoilerCasing.POLYBENZIMIDAZOLE.state

        private val secondPipeCasingState
            get() = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state

        private val coilState
            get() = GTFusionCasing.SUPERCONDUCTOR_COIL.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MultiblockMassFabricator(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.fieldGenCasingTier = context.getAttributeOrDefault(FIELD_GEN_CASING_TIER, 0)
        this.emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        this.sensorCasingTier = context.getAttributeOrDefault(SENSOR_CASING_TIER, 0)
        this.processorCasingTier = context.getAttributeOrDefault(PROCESSOR_CASING_TIER, 0)
        this.tier = minOf(fieldGenCasingTier, emitterCasingTier, sensorCasingTier, processorCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.fieldGenCasingTier = 0
        this.emitterCasingTier = 0
        this.sensorCasingTier = 0
        this.processorCasingTier = 0
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
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.HASTELLOY_N_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.MASS_FABRICATOR_OVERLAY

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_mass_fabricator.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_mass_fabricator.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_mass_fabricator.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_mass_fabricator.tooltip.4"))
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
