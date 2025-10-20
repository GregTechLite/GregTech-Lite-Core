package gregtechlite.gtlitecore.common.metatileentity.multiblock.mega

import gregtech.api.GTValues.VOC
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.ParallelLogicType
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.RecipeMaps.FURNACE_RECIPES
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.GTUtility.getFloorTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.metatileentities.multi.electric.MetaTileEntityMultiSmelter.getDurationForParallel
import gregtech.common.metatileentities.multi.electric.MetaTileEntityMultiSmelter.getEUtForParallel
import gregtechlite.gtlitecore.api.GTLiteAPI.FUSION_COIL_TIER
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.fusionCoils
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTComputerCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockEPCouplingAccelerator(id: ResourceLocation) : RecipeMapMultiblockController(id, FURNACE_RECIPES)
{

    private var tier = 0

    init
    {
        recipeMapWorkable = EPCouplingAcceleratorRecipeLogic(this)
    }

    companion object
    {
        private val casingState = ScienceCasing.MOLECULAR_CASING.state
        private val secondCasingState = ScienceCasing.HOLLOW_CASING.state // TODO Change to Containment Field Casing (UHV) to locked wireless mode?
        private val thirdCasingState = GTComputerCasing.HIGH_POWER_CASING.state
        private val coilState = ScienceCasing.MOLECULAR_COIL.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockEPCouplingAccelerator(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        tier = context.getAttributeOrDefault(FUSION_COIL_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        tier = 0
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        energyContainer = EnergyContainerList(inputEnergy)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("         A A A         ", "         AAAAA         ", "         A A A         ")
        .aisle("        AAAAAAA        ", "       AADDDDDAA       ", "        AAAAAAA        ")
        .aisle("      bbbbIIIbbbb      ", "      cDDeeeeeDDc      ", "      bbbbIIIbbbb      ")
        .aisle("     bAAAAAAAAAAAb     ", "     cDeeDDDDDeeDc     ", "     bAAAAAAAAAAAb     ")
        .aisle("    bAAAA     AAAAb    ", "    cDeDD     DDeDc    ", "    bAAAA     AAAAb    ")
        .aisle("   bAAA         AAAb   ", "   cDeD         DeDc   ", "   bAAA         AAAb   ")
        .aisle("  bAAA           AAAb  ", "  cDeD           DeDc  ", "  bAAA           AAAb  ")
        .aisle("  bAA             AAb  ", " ADeD             DeDA ", "  bAA             AAb  ")
        .aisle(" AbAA             AAbA ", " ADeD             DeDA ", " AbAA             AAbA ")
        .aisle("AAbA               AbAA", "ADeD               DeDA", "AAbA               AbAA")
        .aisle(" AIA               AIA ", "ADeD               DeDA", " AIA               AIA ")
        .aisle("AAIA               AIAA", "ADeD               DeDA", "AAIA               AIAA")
        .aisle(" AIA               AIA ", "ADeD               DeDA", " AIA               AIA ")
        .aisle("AAbA               AbAA", "ADeD               DeDA", "AAbA               AbAA")
        .aisle(" AbAA             AAbA ", " ADeD             DeDA ", " AbAA             AAbA ")
        .aisle("  bAA             AAb  ", " ADeD             DeDA ", "  bAA             AAb  ")
        .aisle("  bAAA           AAAb  ", "  cDeD           DeDc  ", "  bAAA           AAAb  ")
        .aisle("   bAAA         AAAb   ", "   cDeD         DeDc   ", "   bAAA         AAAb   ")
        .aisle("    bAAAAbJJJbAAAAb    ", "    cDeDDbJeJbDDeDc    ", "    bAAAAbJJJbAAAAb    ")
        .aisle("     bAHHbbbbbHHAb     ", "     cDeeDDDDDeeDc     ", "     bAHHbbbbbHHAb     ")
        .aisle("      bbbbGGGbbbb      ", "      cDDee~eeDDc      ", "      bbbbGGGbbbb      ")
        .where('~', selfPredicate())
        .where('A', states(casingState))
        .where('b', states(casingState)
            .setMinGlobalLimited(96)
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS)))
        .where('c', states(secondCasingState))
        .where('D', states(coilState))
        .where('e', states(secondCasingState))
        .where('I', fusionCoils())
        .where('J', states(casingState)
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS)))
        .where('H', states(thirdCasingState))
        .where('G', states(casingState)
            .or(abilities(INPUT_ENERGY)
                    .setPreviewCount(6))
            .or(abilities(INPUT_LASER)
                    .setPreviewCount(0)))

        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.MOLECULAR_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.EP_COUPLING_ACCELERATOR_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gtlitecore.machine.ep_coupling_accelerator.tooltip.1",
                               "gtlitecore.machine.ep_coupling_accelerator.tooltip.2")
            addOverclockInfo(OverclockMode.PERFECT_DOUBLE)
            addParallelInfo(Int.MAX_VALUE)
            addEnergyInfo(UpgradeMode.FUSION_COIL, 10)
            addMaxVoltageInfo()
            addLaserHatchInfo()
        }
    }

    override fun hasMaintenanceMechanics(): Boolean = false

    override fun canBeDistinct(): Boolean = true

    private inner class EPCouplingAcceleratorRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = PERFECT_DURATION_FACTOR / 2

        /**
         * Ignored maximum overclock voltage of energy hatches limit, let it be the maximum voltage
         * of the MTE because we need to consume huge energies for Nano Forge. This is a revert of
         * GTCEu pull request <a href="https://github.com/GregTechCEu/GregTech/pull/2139">#2139</a>.
         */
        override fun getMaximumOverclockVoltage() = maxVoltage

        /**
         * Ignored maximum overclock voltage of energy hatches limit, let it be the maximum voltage
         * of the MTE because we need to consume huge energies for Nano Forge. This is a revert of
         * GTCEu pull request <a href="https://github.com/GregTechCEu/GregTech/pull/2139">#2139</a>.
         */
        override fun getMaxVoltage(): Long
        {
            val energyContainer = energyContainer
            if (energyContainer is EnergyContainerList)
            {
                val voltage: Long
                val amperage: Long
                if (energyContainer.inputVoltage > energyContainer.outputVoltage)
                {
                    voltage = energyContainer.inputVoltage
                    amperage = energyContainer.inputAmperage
                }
                else
                {
                    voltage = energyContainer.outputVoltage
                    amperage = energyContainer.outputAmperage
                }

                return if (amperage == 1L)
                {
                    // amperage is 1 when the energy is not exactly on a tier
                    // the voltage for recipe search is always on tier, so take the closest lower tier
                    VOC[getFloorTierByVoltage(voltage).toInt()]
                }
                else
                {
                    // amperage != 1 means the voltage is exactly on a tier
                    // ignore amperage, since only the voltage is relevant for recipe search
                    // amps are never > 3 in an EnergyContainerList
                    voltage
                }
            }
            return max(energyContainer.inputVoltage.toDouble(),
                energyContainer.outputVoltage.toDouble()).toLong()
        }

        override fun getParallelLogicType() = ParallelLogicType.APPEND_ITEMS

        override fun getParallelLimit(): Int = Int.MAX_VALUE

        override fun applyParallelBonus(builder: RecipeBuilder<*>)
        {
            super.applyParallelBonus(builder)
            builder.EUt(getEUtForParallel(builder.parallel, 64))
                .duration(getDurationForParallel(builder.parallel, parallelLimit))
        }

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)
            if (tier <= 0) return

            // -10% / fusion coil tier
            ocResult.setEut(max(1, (ocResult.eut() * (1.0 - tier * 0.1)).toLong()))
        }


    }

}