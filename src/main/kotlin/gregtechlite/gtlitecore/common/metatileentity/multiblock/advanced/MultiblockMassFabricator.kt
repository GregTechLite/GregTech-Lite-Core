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
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.GTUtility.getTierByVoltage
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
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
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
import kotlin.math.max
import kotlin.math.min

class MultiblockMassFabricator(id: ResourceLocation) : RecipeMapMultiblockController(id, MASS_FABRICATOR_RECIPES)
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
            addMachineTypeLine()
            addOverclockInfo(OverclockMode.PERFECT_AFTER)
            addMultiParallelInfo(UpgradeMode.FIELD_GEN_CASING, UpgradeMode.PROCESSOR_CASING, number = 16)
            addMultiDurationInfo(UpgradeMode.EMITTER_CASING, UpgradeMode.SENSOR_CASING, percent = 200)
            addEnergyInfo(UpgradeMode.VOLTAGE_TIER, 25)
        }
    }

    override fun canBeDistinct() = false

    private inner class LargeMassFabricatorRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor(): Double
            = if (maxVoltage >= V[UV]) PERFECT_DURATION_FACTOR else STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -25% / voltage tier
            ocResult.setEut(max(1, (ocResult.eut() * (1.0 - getTierByVoltage(maxVoltage) * 0.25)).toLong()))

            // +200% / emitter and sensor casing tier | t = d / (1 + 2.0 * (c - 1)) = d / (2.0 * c - 1.0), where b = 2.0
            if (emitterCasingTier <= 0 || sensorCasingTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (2.0 * min(emitterCasingTier, sensorCasingTier) - 1.0)).toInt()))
        }

        override fun getParallelLimit() = 16 * min(emitterCasingTier, sensorCasingTier)

    }

}
