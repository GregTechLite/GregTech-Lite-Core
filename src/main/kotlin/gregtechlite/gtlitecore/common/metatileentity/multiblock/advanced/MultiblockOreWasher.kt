package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CRACKING_RECIPES
import gregtech.api.recipes.RecipeMaps.ORE_WASHER_RECIPES
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteLog
import gregtechlite.gtlitecore.api.metatileentity.multiblock.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.metatileentity.multiblock.OverclockMode
import gregtechlite.gtlitecore.api.metatileentity.multiblock.UpgradeMode
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CATALYTIC_REFORMER_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockOreWasher(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(ORE_WASHER_RECIPES, CHEMICAL_BATH_RECIPES, CRACKING_RECIPES,
                                               CATALYTIC_REFORMER_RECIPES))
{

    private var casingTier = 0

    init
    {
        recipeMapWorkable = LargeOreWasherRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.GRISIUM.state
        private val pipeCasingState = GTBoilerCasing.TITANIUM_PIPE.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockOreWasher(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        casingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        casingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start() // TODO Water?
        .aisle("CCCCC", "CCCCC", "CCCCC")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCCCC", "CQ QC", "C   C")
        .aisle("CCPCC", "CCSCC", "CCCCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(9)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('Q', states(pipeCasingState))
        .where('P', pumpCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.GRISIUM_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.LARGE_ORE_WASHER_OVERLAY

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addOverclockInfo(OverclockMode.PERFECT_AFTER)
            addParallelInfo(UpgradeMode.PUMP_CASING, 16)
            addDurationInfo(UpgradeMode.VOLTAGE_TIER, 300)
            addEnergyInfo(20)
        }
    }

    override fun canBeDistinct() = true

    override fun update()
    {
        super.update()
        // We disable rotation of the controller by override allowsExtendedFacing,
        // this is a fallback for some edge case.
        val backFacing = frontFacing.opposite.takeIf { it.axis.isHorizontal } ?: EnumFacing.NORTH

        val offsets = buildList {
            // y = 0 | x = 2, z ∈ [1, 5]
            for (z in 1..5)
            {
                add(BlockPos(0, 0, z))
            }

            // y = 1 | x ∈ [1, 3], z ∈ [1, 5]
            for (z in 1..5)
            {
                for (x in 1..3)
                {
                    add(BlockPos(x - 2, 1, z))
                }
            }
        }

        val forward = backFacing.directionVec
        val rightVec = backFacing.rotateY()

        var waterCount = 0
        val countToFill = offsets.size

        for (relPos in offsets)
        {
            val dx = relPos.x
            val dy = relPos.y
            val dz = relPos.z

            val relX = dx * rightVec.xOffset + dz * forward.x
            val relY = dy
            val relZ = dx * rightVec.zOffset + dz * forward.z

            val checkPos = pos.add(relX, relY, relZ)
            val checkBlock = world.getBlockState(checkPos).block

            val isWater = checkBlock == Blocks.WATER
            val isAirOrFlowingWater = checkBlock == Blocks.AIR || checkBlock == Blocks.FLOWING_WATER

            if (isStructureFormed)
            {
                if (isWater)
                {
                    waterCount++
                    continue
                }

                if (isAirOrFlowingWater)
                {
                    world.setBlockState(checkPos, Blocks.WATER.defaultState)
                    waterCount++
                }
            }
            else
            {
                if (isWater || isAirOrFlowingWater)
                {
                    world.setBlockState(checkPos, Blocks.AIR.defaultState)
                }
            }
        }

        if (waterCount < countToFill)
        {
            GTLiteLog.logger.debug("Actual fill water count: $waterCount, require fill water count: $countToFill")
        }
    }

    override fun allowsExtendedFacing(): Boolean = false

    private inner class LargeOreWasherRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor(): Double
            = if (maxVoltage >= V[UV]) PERFECT_DURATION_FACTOR else STD_DURATION_FACTOR

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -20%
            ocResult.setEut(max(1, (ocResult.eut() * 0.8).toLong()))

            // +300% / voltage tier | D' = D / (1 + 3.0 * (T - 1.0)) = D / (3.0 * T - 2.0), where k = 3.0
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (3.0 * getTierByVoltage(maxVoltage) - 2.0)).toInt()))
        }

        override fun getParallelLimit() = 16 * casingTier

    }

}

