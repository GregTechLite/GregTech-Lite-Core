package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.block.IHeatingCoilBlockStats
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
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.blocks.*
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.HEATING_COIL_STATS
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockArcFurnace(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(ARC_FURNACE_RECIPES, ALLOY_SMELTER_RECIPES))
{

    private var pumpCasingTier = 0
    private var coilTier = 0
    private var tier = 0

    init
    {
        this.recipeMapWorkable = LargeArcFurnaceRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = GTMetalCasing.INVAR_HEATPROOF.state

        private val secondCasingState
            get() = GTMultiblockCasing.GRATE_CASING.state

        private val pipeCasingState
            get() = GTBoilerCasing.STEEL_PIPE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockArcFurnace(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)

        val coilType = context.get<Any>(HEATING_COIL_STATS)
        if (coilType is IHeatingCoilBlockStats)
        {
            this.coilTier = coilType.tier
        }
        else
        {
            this.coilTier = BlockWireCoil.CoilType.CUPRONICKEL.tier
        }
        this.tier = minOf(pumpCasingTier, coilTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.pumpCasingTier = 0
        this.coilTier = 0
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
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_ARC_FURNACE_OVERLAY

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_arc_furnace.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_arc_furnace.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_arc_furnace.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_arc_furnace.tooltip.4"))
    }

    override fun canBeDistinct(): Boolean = true

    private inner class LargeArcFurnaceRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(coilTier))).toInt())
        }

        override fun getParallelLimit() = 8 * pumpCasingTier

    }

}
