package gregtechlite.gtlitecore.api.capability.logic

import gregtech.api.GTValues
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.recipes.logic.OCParams
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic
import gregtech.api.recipes.properties.RecipePropertyStorage

open class NoEnergyMultiblockRecipeLogic(metaTileEntity: RecipeMapMultiblockController) : MultiblockRecipeLogic(metaTileEntity)
{

    override fun getEnergyInputPerSecond(): Long = Int.MAX_VALUE.toLong()

    override fun getEnergyStored(): Long = Int.MAX_VALUE.toLong()

    override fun getEnergyCapacity(): Long = Int.MAX_VALUE.toLong()

    override fun drawEnergy(recipeEUt: Long, simulate: Boolean): Boolean = true

    override fun getMaxVoltage(): Long = GTValues.LV.toLong()

    override fun getMaximumOverclockVoltage(): Long = GTValues.V[GTValues.LV]

    override fun runOverclockingLogic(ocParams: OCParams, ocResult: OCResult,
                                      propertyStorage: RecipePropertyStorage,
                                      maxVoltage: Long)
    {
        OverclockingLogic.standardOC(ocParams.apply { recipeEUt = 1 }, ocResult,
            getMaxVoltage(), overclockingDurationFactor, overclockingVoltageFactor)
    }

}