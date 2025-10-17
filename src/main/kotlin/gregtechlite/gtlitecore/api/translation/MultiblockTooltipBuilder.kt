package gregtechlite.gtlitecore.api.translation

import gregtech.api.capability.IMultipleRecipeMaps
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import net.minecraft.client.resources.I18n
import net.minecraft.util.text.TextFormatting
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

    /**
     * Add machine type for the multiblock, just like multi-map multiblock recipe map line.
     */
    fun addMachineTypeLine() = apply {
        getRecipeMapName().takeIf { it.isNotEmpty() }?.let {
            _tooltips.add(I18n.format("gtlitecore.tooltip.machine.machine_type", it))
        }
    }

    /**
     * Add machine type for the multiblock with custom machine type string.
     */
    fun addMachineTypeLine(machineType: String) = apply {
        if (machineType.isNotEmpty())
            _tooltips.add(I18n.format("gtlitecore.tooltip.machine.machine_type", machineType))
    }

    /**
     * Add additional descriptions for the multiblock, such as cleanroom environment predicate of Large Bio Reactor.
     */
    fun addDescriptionLine(vararg descriptions: String) = apply {
        descriptions.forEach { _tooltips.add(I18n.format(it)) }
    }

    fun addOverclockInfo(mode: OverclockMode) = apply {
        val overclockMode = "gtlitecore.tooltip.machine.overclock_mode"
        _tooltips.add(I18n.format(overclockMode) + I18n.format("$overclockMode.${mode.name.lowercase()}"))
    }

    fun addOverclockInfo(conditionInfo: String) = apply {
        _tooltips.add(I18n.format("gtlitecore.tooltip.machine.overclock_mode") + I18n.format(conditionInfo))
    }

    fun addParallelInfo(mode: UpgradeMode, number: Int) = apply {
        val parallelMode = "gtlitecore.tooltip.machine.parallel_mode"
        _tooltips.add(I18n.format(parallelMode) + I18n.format("$parallelMode.${mode.name.lowercase()}", number))
    }

    fun addMultiParallelInfo(vararg modes: UpgradeMode, number: Int) = apply {
        val parallelMode = "gtlitecore.tooltip.machine.parallel_mode"
        val modeKey = modes.joinToString("_") { it.name.lowercase() }
        _tooltips.add(I18n.format(parallelMode) + I18n.format("$parallelMode.$modeKey", number))
    }

    fun addDurationInfo(mode: UpgradeMode, percent: Int) = apply {
        val durationMode = "gtlitecore.tooltip.machine.duration_mode"
        _tooltips.add(I18n.format(durationMode) + I18n.format("$durationMode.${mode.name.lowercase()}", percent))
    }

    fun addMultiDurationInfo(vararg modes: UpgradeMode, percent: Int) = apply {
        val durationMode = "gtlitecore.tooltip.machine.duration_mode"
        val modeKey = modes.joinToString("_") { it.name.lowercase() }
        _tooltips.add(I18n.format(durationMode) + I18n.format("$durationMode.$modeKey", percent))
    }

    fun addEnergyInfo(mode: UpgradeMode, percent: Int) = apply {
        val energyMode = "gtlitecore.tooltip.machine.energy_mode"
        _tooltips.add(I18n.format(energyMode) + I18n.format("$energyMode.${mode.name.lowercase()}", percent))
    }

    fun addEnergyInfo(percent: Int) = apply {
        _tooltips.add(I18n.format("gtlitecore.tooltip.machine.energy_mode") + TextFormatting.GOLD + "-$percent%")
    }

    fun addLaserHatchInfo() = apply {
        _tooltips.add(I18n.format("gtlitecore.tooltip.machine.laser_hatch"))
    }

    fun addMaxVoltageInfo() = apply {
        _tooltips.add(I18n.format("gtlitecore.tooltip.machine.special_max_voltage"))
    }

    // endregion

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