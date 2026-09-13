package gregtechlite.gtlitecore.api.metatileentity.multiblock

import gregtech.api.capability.IMultipleRecipeMaps
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import net.minecraft.client.resources.I18n
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockTooltipBuilder(private val metaTileEntity: MultiblockWithDisplayBase)
{
    companion object
    {
        fun MultiblockWithDisplayBase.addTooltip(tooltip: MutableList<String>, builder: MultiblockTooltipBuilder.() -> Unit)
        {
            tooltip += MultiblockTooltipBuilder(this).apply(builder).tooltips
        }
    }

    private val _tooltips = mutableListOf<String>()

    val tooltips: List<String>
        get() = _tooltips.toList()

    // region Tooltip Components

    fun addMachineTypeLine() = apply {
        getRecipeMapName().takeIf { it.isNotEmpty() }?.let { addMachineTypeLine(it) }
    }

    fun addMachineTypeLine(machineType: String) = apply {
        if (machineType.isNotEmpty())
            _tooltips.add(I18n.format(Keys.MACHINE_TYPE, machineType))
    }

    fun addDescriptionLine(vararg descriptions: String) = apply {
        descriptions.forEach { _tooltips.add(I18n.format(it)) }
    }

    fun addOverclockInfo(mode: OverclockMode) = apply {
        _tooltips.add(I18n.format(Keys.OVERCLOCK_MODE) + I18n.format("${Keys.OVERCLOCK_MODE}.${mode.name.lowercase()}"))
    }

    fun addOverclockInfo(conditionInfo: String) = apply {
        _tooltips.add(I18n.format(Keys.OVERCLOCK_MODE) + I18n.format(conditionInfo))
    }

    fun addParallelInfo(number: Int, vararg modes: UpgradeMode) = apply {
        _tooltips.add(when {
            number == Int.MAX_VALUE -> statLine(Keys.PARALLEL_LABEL, "${Keys.PARALLEL_MODE}.unlimited")
            modes.isEmpty()         -> statLine(Keys.PARALLEL_LABEL, "${Keys.PARALLEL_MODE}.flat", number)
            else                    -> statLine(Keys.PARALLEL_LABEL, Keys.PARALLEL_MODE, number, joinModeNames(modes))
        })
    }

    fun addParallelInfo(valueKey: String) = apply {
        _tooltips.add(statLine(Keys.PARALLEL_LABEL, valueKey))
    }

    fun addDurationInfo(percent: Int, vararg modes: UpgradeMode) = apply {
        _tooltips.add(if (modes.isEmpty()) statLine(Keys.DURATION_LABEL, "${Keys.DURATION_MODE}.flat", percent)
                      else statLine(Keys.DURATION_LABEL, Keys.DURATION_MODE, percent, joinModeNames(modes)))
    }

    fun addEnergyInfo(percent: Int, vararg modes: UpgradeMode) = apply {
        _tooltips.add(if (modes.isEmpty()) statLine(Keys.ENERGY_LABEL, "${Keys.ENERGY_MODE}.flat", percent)
                      else statLine(Keys.ENERGY_LABEL, Keys.ENERGY_MODE, percent, joinModeNames(modes)))
    }

    fun addLaserHatchInfo() = apply {
        _tooltips.add(I18n.format(Keys.LASER_HATCH))
    }

    fun addMaxVoltageInfo() = apply {
        _tooltips.add(I18n.format(Keys.MAX_VOLTAGE))
    }

    // endregion

    private fun statLine(labelKey: String, valueKey: String, vararg args: Any): String
        = I18n.format(labelKey) + I18n.format(valueKey, *args)

    private fun joinModeNames(modes: Array<out UpgradeMode>): String
    {
        if (modes.isEmpty()) return ""
        val and = I18n.format(Keys.LIST_AND)
        val comma = I18n.format(Keys.LIST_COMMA)
        val names = modes.map { I18n.format("${Keys.UPGRADE}.${it.name.lowercase()}") }
        return when (names.size)
        {
            1 -> names[0]
            2 -> names[0] + and + names[1]
            else -> names.dropLast(1).joinToString(comma) + and + names.last()
        }
    }

    private object Keys
    {
        // @formatter:off

        const val PREFIX  = "gtlitecore.tooltip.machine."
        const val UPGRADE = "gtlitecore.tooltip.machine.upgrade"

        const val MACHINE_TYPE   = PREFIX + "machine_type"
        const val OVERCLOCK_MODE = PREFIX + "overclock_mode"
        const val PARALLEL_MODE  = PREFIX + "parallel_mode"
        const val DURATION_MODE  = PREFIX + "duration_mode"
        const val ENERGY_MODE    = PREFIX + "energy_mode"

        const val LIST_AND   = PREFIX + "list.and"
        const val LIST_COMMA = PREFIX + "list.comma"

        const val LASER_HATCH = PREFIX + "laser_hatch"
        const val MAX_VOLTAGE = PREFIX + "special_max_voltage"

        const val PARALLEL_LABEL = "$PARALLEL_MODE.label"
        const val DURATION_LABEL = "$DURATION_MODE.label"
        const val ENERGY_LABEL   = "$ENERGY_MODE.label"

        // @formatter:on
    }

    @SideOnly(Side.CLIENT)
    private fun getRecipeMapName() = when (metaTileEntity)
    {
        is IMultipleRecipeMaps -> metaTileEntity.availableRecipeMaps
            .mapNotNull { it.localizedName.takeIf { name -> name.isNotEmpty() } }
            .joinToString(", ")
        is RecipeMapMultiblockController -> metaTileEntity.recipeMap.localizedName
        else -> metaTileEntity.metaFullName
    }
}