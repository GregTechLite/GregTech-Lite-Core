package gregtechlite.gtlitecore.common.cover

import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.V
import gregtech.api.cover.CoverBase
import gregtech.api.cover.CoverDefinition
import gregtech.api.items.behavior.CoverItemBehavior
import gregtech.api.items.metaitem.MetaItem
import gregtech.common.covers.CoverBehaviors
import gregtech.common.covers.CoverConveyor
import gregtech.common.covers.CoverPump
import gregtech.common.covers.CoverRoboticArm
import gregtech.common.covers.CoverSolarPanel
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.common.cover.behavior.CoverAirVent
import gregtechlite.gtlitecore.common.cover.behavior.CoverDrain
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.AIR_VENT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CONVEYOR_MODULE_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DRAIN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELECTRIC_PUMP_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ROBOT_ARM_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOLAR_PANEL_UXV

internal object GTLiteCoverBehaviors
{

    fun init()
    {
        // MAX Conveyor Module
        registerBehavior("conveyor.max", CONVEYOR_MODULE_MAX) { def, tile, side ->
            CoverConveyor(def, tile, side, MAX, 16 * 64)
        }

        // MAX Robot Arm
        registerBehavior("robotic_arm.max", ROBOT_ARM_MAX) { def, tile, side ->
            CoverRoboticArm(def, tile, side, MAX, 16 * 64)
        }

        // MAX Pump
        registerBehavior("pump.max", ELECTRIC_PUMP_MAX) { def, tile, side ->
            CoverPump(def, tile, side, MAX, 1280 * 64 * 64 * 4)
        }

        // Air Vent
        registerBehavior("air_vent", AIR_VENT) { def, tile, side ->
            CoverAirVent(def, tile, side, 100)
        }

        // Drain
        registerBehavior("drain", DRAIN) { def, tile, side ->
            CoverDrain(def, tile, side, 500)
        }

        // UHV Solar Panel
        registerBehavior("solar_panel.uhv", SOLAR_PANEL_UHV) { def, tile, side ->
            CoverSolarPanel(def, tile, side, V[UHV])
        }

        // UEV Solar Panel
        registerBehavior("solar_panel.uev", SOLAR_PANEL_UEV) { def, tile, side ->
            CoverSolarPanel(def, tile, side, V[UEV])
        }

        // UIV Solar Panel
        registerBehavior("solar_panel.uiv", SOLAR_PANEL_UIV) { def, tile, side ->
            CoverSolarPanel(def, tile, side, V[UIV])
        }

        // UXV Solar Panel
        registerBehavior("solar_panel.uxv", SOLAR_PANEL_UXV) { def, tile, side ->
            CoverSolarPanel(def, tile, side, V[UXV])
        }

        // OpV Solar Panel
        registerBehavior("solar_panel.opv", SOLAR_PANEL_OpV) { def, tile, side ->
            CoverSolarPanel(def, tile, side, V[OpV])
        }

        // MAX Solar Panel
        registerBehavior("solar_panel.max", SOLAR_PANEL_MAX) { def, tile, side ->
            CoverSolarPanel(def, tile, side, V[MAX])
        }

    }

    /**
     * Register a cover behavior for an existed meta item.
     *
     * @param name            The name of the cover.
     * @param placerItem      The meta item which representing the cover.
     * @param behaviorCreator The behavior creator callback function.
     */
    private fun registerBehavior(name: String,
                                 placerItem: MetaItem<*>.MetaValueItem,
                                 behaviorCreator: CoverDefinition.CoverCreator)
    {
        placerItem.addComponents(CoverItemBehavior(CoverBehaviors.registerCover(
            GTLiteMod.id(name), placerItem.stackForm, behaviorCreator)))
    }

}