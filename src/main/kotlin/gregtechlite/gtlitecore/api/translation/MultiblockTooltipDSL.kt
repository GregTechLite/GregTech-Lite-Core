package gregtechlite.gtlitecore.api.translation

import gregtech.api.GTValues.FALLBACK
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VNF
import gregtech.api.capability.IMultipleRecipeMaps
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.client.utils.TooltipHelper
import net.minecraft.client.resources.I18n
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockTooltipDSL(private val metaTileEntity: MultiblockWithDisplayBase)
{

    companion object
    {

        fun MultiblockWithDisplayBase.addTooltip(tooltip: MutableList<String>, dsl: MultiblockTooltipDSL.() -> Unit)
        {
            tooltip.addAll(buildTooltip(dsl))
        }

        fun MultiblockWithDisplayBase.buildTooltip(dsl: MultiblockTooltipDSL.() -> Unit): List<String>
        {
            return MultiblockTooltipDSL(this).apply(dsl).tooltips
        }

    }

    /// internal
    private val _tooltips = mutableListOf<String>()

    /// external
    val tooltips: List<String>
        get() = _tooltips.toList()

    // region Tooltip Components

    /**
     * Add a machine type and short name descriptions on the first line.
     *
     * On the top line at first otherwise it has some other function, has some format like MultiMap machine.
     */
    fun machineType(shortName: String? = null)
    {
        getRecipeMapName().takeIf { it.isNotEmpty() }?.let { recipeMapName ->
            _tooltips.add(
                if (shortName.isNullOrEmpty())
                    I18n.format("gtlitecore.tooltip.machine.recipe_map_name", recipeMapName)
                else
                    I18n.format("gtlitecore.tooltip.machine.recipe_map_short_name", recipeMapName, shortName)
            )
        }
    }

    /**
     * Add a common descriptions for the machine without line restricts.
     *
     * @param isLargeMachine If this value is `true`, then add a large machine efficiency description line.
     */
    fun description(isLargeMachine: Boolean = false, vararg descriptions: String)
    {
        if (isLargeMachine)
            _tooltips.add(I18n.format("gtlitecore.tooltip.machine.large_machine_efficiency"))
        descriptions.forEach { _tooltips.add(I18n.format(it)) }
    }

    /**
     * @param unlockedTier When the input voltage of the machine is large than this value, then will enabled Perfect
     *                     Overclocking for the machine. Used [FALLBACK] for default value, means it is always enabled
     *                     the Perfect Overclocking.
     */
    fun overclockInfo(unlockedTier: Int = FALLBACK)
    {
        when
        {
            unlockedTier != FALLBACK ->
            {
                require(unlockedTier in ULV..MAX) { "The Voltage Tier must between ULV and MAX" }
                _tooltips.add(
                    I18n.format("gtlitecore.tooltip.machine.overclocking_tier.0", VNF[unlockedTier])
                            + TooltipHelper.RAINBOW_SLOW + I18n.format("gtlitecore.tooltip.machine.overclocking_tier.1"))
            }
            else ->
            {
                _tooltips.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
            }
        }
    }

    fun durationInfo(upgradeType: UpgradeType = UpgradeType.VOLTAGE_TIER, reductionPercent: Int)
    {
        require(reductionPercent in 0..100) { "Reduction Percent must be between 0 and 100" }
        _tooltips.add(I18n.format("gtlitecore.tooltip.machine.duration_reduction_${upgradeType.name.lowercase()}",
                                  reductionPercent))
    }

    fun parallelInfo(upgradeType: UpgradeType = UpgradeType.VOLTAGE_TIER, parallelNumber: Int)
    {
        require(parallelNumber >= 0) { "Parallel Number must be non-negative" }
        _tooltips.add(I18n.format("gtlitecore.tooltip.machine.parallel_increment_${upgradeType.name.lowercase()}",
                                  parallelNumber))
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