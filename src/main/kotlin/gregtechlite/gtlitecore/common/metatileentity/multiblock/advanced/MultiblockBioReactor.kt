package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.recipes.properties.impl.CleanroomProperty
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.CLEANROOM_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.SENSOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.cleanroomCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.sensorCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BIO_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.GREENHOUSE_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockBioReactor(id: ResourceLocation) : MultiMapMultiblockController(id, arrayOf(BIO_REACTOR_RECIPES, GREENHOUSE_RECIPES))
{

    private var sensorCasingTier = 0
    private var cleanroomCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = LargeBioReactorRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.RED_STEEL.state
        private val secondCasingState = GTMultiblockCasing.GRATE_CASING.state
        private val glassState = GlassCasing.GREENHOUSE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockBioReactor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        sensorCasingTier = context.getAttributeOrDefault(SENSOR_CASING_TIER, 0)
        cleanroomCasingTier = context.getAttributeOrDefault(CLEANROOM_CASING_TIER, 0)
        tier = minOf(sensorCasingTier, cleanroomCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        sensorCasingTier = 0
        cleanroomCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "CCCCC", "CCCCC", "     ")
        .aisle("CCCCC", "D###D", "D###D", "CCCCC")
        .aisle("CCCCC", "DT#TD", "D###D", "CFFFC")
        .aisle("CCCCC", "D###D", "D###D", "CCCCC")
        .aisle("CCSCC", "CGGGC", "CGGGC", "     ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(10)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('D', states(secondCasingState))
        .where('G', states(glassState))
        .where('T', sensorCasings())
        .where('F', cleanroomCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.RED_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gtlitecore.machine.large_bio_reactor.tooltip.1")
            addOverclockInfo("gtlitecore.machine.large_bio_reactor.tooltip.2")
            addParallelInfo(UpgradeMode.VOLTAGE_TIER, 16)
            addDurationInfo(UpgradeMode.SENSOR_CASING, 375)
            addEnergyInfo(UpgradeMode.VOLTAGE_TIER, 35)
        }
    }

    override fun canBeDistinct() = true

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        if (getRecipeMap() === BIO_REACTOR_RECIPES)
        {
            return if (super.checkRecipe(recipe, consumeIfSuccess)
                && (recipe.getProperty(CleanroomProperty.getInstance(), CleanroomType.CLEANROOM)
                        !== CleanroomType.STERILE_CLEANROOM))
                true
            else if (super.checkRecipe(recipe, consumeIfSuccess)
                && (recipe.getProperty(CleanroomProperty.getInstance(), CleanroomType.CLEANROOM)
                        === CleanroomType.STERILE_CLEANROOM) && cleanroomCasingTier >= 2)
                true
            else
                false
        }
        else
        {
            return super.checkRecipe(recipe, consumeIfSuccess)
        }
    }

    private inner class LargeBioReactorRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor(): Double
            = if (maxVoltage >= V[UV]) PERFECT_DURATION_FACTOR else STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -35% / voltage tier
            ocResult.setEut(max(1, (ocResult.eut() * (1.0 - getTierByVoltage(maxVoltage) * 0.35)).toLong()))

            // +375% / sensor casing tier | t = d / (1 + 3.75 * (c - 1)) = d / (3.75 * c - 2.75), where b = 3.75
            if (sensorCasingTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (3.75 * sensorCasingTier - 2.75)).toInt()))
        }

        override fun getParallelLimit() = 16 * getTierByVoltage(maxVoltage)

    }

}
