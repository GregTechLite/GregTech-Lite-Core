package gregtechlite.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.ZPM
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.recipe.util.TierBridge
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_EV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_HV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_IV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_LV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_LuV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_MV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_ULV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GENERIC_CIRCUIT_ZPM

internal object GenericCircuits
{
    // @formatter:off

    fun init()
    {
        for (tier in ULV..MAX)
        {
            VACUUM_CHAMBER_RECIPES.addRecipe {
                input(circuit, TierBridge.materialOf(tier))
                output(genericByTier(tier)!!)
                EUt(VH[ULV])
                duration(5 * TICK)
            }
        }
    }

    private fun genericByTier(tier: Int) = when (tier)
    {
        ULV  -> GENERIC_CIRCUIT_ULV
        LV   -> GENERIC_CIRCUIT_LV
        MV   -> GENERIC_CIRCUIT_MV
        HV   -> GENERIC_CIRCUIT_HV
        EV   -> GENERIC_CIRCUIT_EV
        IV   -> GENERIC_CIRCUIT_IV
        LuV  -> GENERIC_CIRCUIT_LuV
        ZPM  -> GENERIC_CIRCUIT_ZPM
        UV   -> GENERIC_CIRCUIT_UV
        UHV  -> GENERIC_CIRCUIT_UHV
        UEV  -> GENERIC_CIRCUIT_UEV
        UIV  -> GENERIC_CIRCUIT_UIV
        UXV  -> GENERIC_CIRCUIT_UXV
        OpV  -> GENERIC_CIRCUIT_OpV
        MAX  -> GENERIC_CIRCUIT_MAX
        else -> null
    }

    // @formatter:on
}