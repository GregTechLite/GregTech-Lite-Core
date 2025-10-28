package gregtechlite.gtlitecore.api.capability.logic

import gregtech.api.GTValues.VOC
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.util.GTUtility.getFloorTierByVoltage
import kotlin.math.max

/**
 * This recipe logic ignored the maximum overclock voltage of energy hatches limit.
 * It is the revert of GTCEu [PR#2139](https://github.com/GregTechCEu/GregTech/pull/2139).
 *
 * Used for some mega machines or machine which has specific energy requirement.
 */
open class ExtendedPowerMultiblockRecipeLogic(metaTileEntity: RecipeMapMultiblockController) : MultiblockRecipeLogic(metaTileEntity)
{

    override fun getMaximumOverclockVoltage() = maxVoltage

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
                // amp = 1 when the energy is not exactly on a tier, the voltage for recipe search is always on tier,
                // so take the closest lower tier.
                VOC[getFloorTierByVoltage(voltage).toInt()]
            }
            else
            {
                // amp != 1 means the voltage is exactly on a tier, ignore amp, since only the voltage is relevant for
                // recipe search, amps are never > 3 in an EnergyContainerList.
                voltage
            }
        }
        return max(energyContainer.inputVoltage.toDouble(), energyContainer.outputVoltage.toDouble()).toLong()
    }

}