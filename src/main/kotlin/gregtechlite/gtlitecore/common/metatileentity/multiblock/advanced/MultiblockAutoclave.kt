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
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.PISTON_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pistonCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.metatileentity.multiblock.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.metatileentity.multiblock.OverclockMode
import gregtechlite.gtlitecore.api.metatileentity.multiblock.UpgradeMode
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockAutoclave(id: ResourceLocation) : MultiMapMultiblockController(id, arrayOf(AUTOCLAVE_RECIPES, VACUUM_CHAMBER_RECIPES))
{

    private var pumpCasingTier = 0
    private var pistonCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = LargeAutoclaveRecipeLogic(this)
    }

    companion object
    {
        private val casingState = GTMetalCasing.TUNGSTENSTEEL_ROBUST.state
        private val pipeCasingState = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state
        private val glassState = GTGlassCasing.TEMPERED_GLASS.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockAutoclave(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        pistonCasingTier = context.getAttributeOrDefault(PISTON_CASING_TIER, 0)
        tier = minOf(pumpCasingTier, pistonCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pumpCasingTier = 0
        pistonCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " CGC ", " CGC ", " CGC ", " CCC ")
        .aisle("CCCCC", "C###C", "C###C", "C###C", "CCCCC")
        .aisle("CCQCC", "G#B#G", "G#P#G", "G#B#G", "CCCCC")
        .aisle("CCCCC", "C###C", "C###C", "C###C", "CCCCC")
        .aisle(" CCC ", " CSC ", " CGC ", " CGC ", " CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(12)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('B', states(pipeCasingState))
        .where('G', states(glassState))
        .where('P', pumpCasings())
        .where('Q', pistonCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.ROBUST_TUNGSTENSTEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.LARGE_AUTOCLAVE_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addOverclockInfo(OverclockMode.PERFECT_AFTER)
            addParallelInfo(UpgradeMode.PISTON_CASING, 16)
            addDurationInfo(UpgradeMode.PUMP_CASING, 350)
            addEnergyInfo(30)
        }
    }

    override fun canBeDistinct() = true

    private inner class LargeAutoclaveRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor(): Double
            = if (maxVoltage >= V[UV]) PERFECT_DURATION_FACTOR else STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -30%
            ocResult.setEut(max(1, (ocResult.eut() * 0.7).toLong()))

            // +350% / pump casing tier | D' = D / (1 + 3.5 * (T - 1)) = D / (3.5 * T - 2.5), where k = 3.5
            if (pumpCasingTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (3.5 * pumpCasingTier - 2.5)).toInt()))
        }

        override fun getParallelLimit() = 16 * pistonCasingTier

    }

}
