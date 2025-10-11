package gregtechlite.gtlitecore.api.translation

import gregtech.api.capability.IMultipleRecipeMaps
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import net.minecraft.client.resources.I18n
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockTooltipBuilder(private val metaTileEntity: MultiblockWithDisplayBase)
{

    companion object
    {

        fun MultiblockWithDisplayBase.addTooltip(tooltip: MutableList<String>, builder: MultiblockTooltipBuilder.() -> Unit)
        {
            tooltip.addAll(MultiblockTooltipBuilder(this).apply(builder).tooltips)
        }

    }

    private val _tooltips = mutableListOf<String>()

    val tooltips: List<String>
        get() = _tooltips.toList()

    // region Tooltip Components

    /**
     * Add machine type for the multiblock, just like multi-map multiblock recipe map line.
     */
    fun addMachineTypeLine(): MultiblockTooltipBuilder
    {
        getRecipeMapName().takeIf { it.isNotEmpty() }?.let {
            _tooltips.add(I18n.format("gtlitecore.tooltip.machine.machine_type", it))
        }
        return this
    }

    /**
     * Add additional descriptions for the multiblock, such as cleanroom environment predicate of Large Bio Reactor.
     */
    fun addDescriptionLine(vararg descriptions: String): MultiblockTooltipBuilder
    {
        descriptions.forEach { _tooltips.add(I18n.format(it)) }
        return this
    }

    fun addOverclockInfo(mode: OverclockMode): MultiblockTooltipBuilder
    {
        val overclockMode = "gtlitecore.tooltip.machine.overclock_mode"
        _tooltips.add(I18n.format(overclockMode) + I18n.format("$overclockMode.${mode.name.lowercase()}"))
        return this
    }

    fun addParallelInfo(mode: UpgradeMode, number: Int): MultiblockTooltipBuilder
    {
        val parallelMode = "gtlitecore.tooltip.machine.parallel_mode"
        _tooltips.add(I18n.format(parallelMode) + I18n.format("$parallelMode.${mode.name.lowercase()}", number))
        return this
    }

    fun addDurationInfo(mode: UpgradeMode, percent: Int): MultiblockTooltipBuilder
    {
        val durationMode = "gtlitecore.tooltip.machine.duration_mode"
        _tooltips.add(I18n.format(durationMode) + I18n.format("$durationMode.${mode.name.lowercase()}", percent))
        return this
    }

    fun addEnergyInfo(mode: UpgradeMode, percent: Int): MultiblockTooltipBuilder
    {
        val energyMode = "gtlitecore.tooltip.machine.energy_mode"
        _tooltips.add(I18n.format(energyMode) + I18n.format("$energyMode.${mode.name.lowercase()}", percent))
        return this
    }

    fun addLaserHatch(): MultiblockTooltipBuilder
    {
        _tooltips.add(I18n.format("gtlitecore.tooltip.machine.laser_hatch"))
        return this
    }

    // endregion

    @SideOnly(Side.CLIENT)
    private fun getRecipeMapName(): String = when (metaTileEntity)
    {
        is IMultipleRecipeMaps -> metaTileEntity.availableRecipeMaps
            .mapNotNull { it.localizedName.takeIf { name -> name.isNotEmpty() } }
            .joinToString(", ")
        is RecipeMapMultiblockController -> metaTileEntity.recipeMap.localizedName
        else -> metaTileEntity.metaFullName
    }

}