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
import gregtech.api.recipes.RecipeMaps.ALLOY_SMELTER_RECIPES
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.blocks.BlockWireCoil
import gregtechlite.gtlitecore.api.GTLiteAPI.COIL_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockArcFurnace(id: ResourceLocation) : MultiMapMultiblockController(id, arrayOf(ARC_FURNACE_RECIPES, ALLOY_SMELTER_RECIPES))
{

    private var pumpCasingTier = 0
    private var coilTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = LargeArcFurnaceRecipeLogic(this)
    }

    companion object
    {
        private val casingState = GTMetalCasing.INVAR_HEATPROOF.state
        private val secondCasingState = GTMultiblockCasing.GRATE_CASING.state
        private val pipeCasingState = GTBoilerCasing.STEEL_PIPE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockArcFurnace(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
        coilTier = context.getAttributeOrDefault(COIL_TIER, BlockWireCoil.CoilType.CUPRONICKEL).tier
        tier = minOf(pumpCasingTier, coilTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pumpCasingTier = 0
        coilTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " CCC ", " CCC ")
        .aisle("CCCCC", "CHQHC", " CGC ")
        .aisle("CCCCC", "CHPHC", " CGC ")
        .aisle("CCCCC", "CHQHC", " CGC ")
        .aisle(" CCC ", " CSC ", " CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(10)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('G', states(secondCasingState))
        .where('Q', states(pipeCasingState))
        .where('P', pumpCasings())
        .where('H', heatingCoils())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.HEAT_PROOF_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.LARGE_ARC_FURNACE_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addOverclockInfo(OverclockMode.PERFECT_AFTER)
            addParallelInfo(UpgradeMode.PUMP_CASING, 8)
            addDurationInfo(UpgradeMode.WIRE_COIL, 175)
            addEnergyInfo(UpgradeMode.VOLTAGE_TIER, 25)
        }
    }

    override fun canBeDistinct(): Boolean = true

    private inner class LargeArcFurnaceRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor(): Double
            = if (maxVoltage >= V[UV]) PERFECT_DURATION_FACTOR else STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -25% / voltage tier
            ocResult.setEut(max(1, (ocResult.eut() * (1.0 - getTierByVoltage(maxVoltage) * 0.25)).toLong()))

            // +175% / wire coil tier | D' = D / (1 + 1.75 * (T - 1)) = D / (1.75 * T - 0.75), where k = 1.75
            if (coilTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (1.75 * coilTier - 0.75)).toInt()))
        }

        override fun getParallelLimit(): Int = 8 * pumpCasingTier

    }

}
