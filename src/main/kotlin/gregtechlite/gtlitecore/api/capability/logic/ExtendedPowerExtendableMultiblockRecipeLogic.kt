package gregtechlite.gtlitecore.api.capability.logic

import gregtech.api.GTValues.VOC
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.util.GTUtility.getFloorTierByVoltage
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalStructureManager
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.RecipeMapExtendableMultiblock
import kotlin.math.max

open class ExtendedPowerExtendableMultiblockRecipeLogic<T : RecipeMapExtendableMultiblock<T>>(
    controller: RecipeMapExtendableMultiblock<T>,
    override val manager: AdditionalStructureManager<T>) : ExtendableMultiblockRecipeLogic<T>(controller, manager)
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