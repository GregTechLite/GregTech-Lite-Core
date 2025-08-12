package gregtechlite.gtlitecore.common.cover

import gregtech.api.GTValues
import gregtech.api.cover.CoverDefinition
import gregtech.api.items.behavior.CoverItemBehavior
import gregtech.api.items.metaitem.MetaItem
import gregtech.common.covers.CoverBehaviors
import gregtech.common.covers.CoverConveyor
import gregtech.common.covers.CoverPump
import gregtech.common.covers.CoverRoboticArm
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.common.cover.behavior.CoverAirVent
import gregtechlite.gtlitecore.common.cover.behavior.CoverDrain
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems

internal object GTLiteCoverBehaviors
{

    fun init()
    {
        registerBehavior("conveyor.max", GTLiteMetaItems.CONVEYOR_MODULE_MAX) { def, tile, side ->
            CoverConveyor(def, tile, side, GTValues.MAX, 16 * 64)
        }

        registerBehavior("robotic_arm.max", GTLiteMetaItems.ROBOT_ARM_MAX) { def, tile, side ->
            CoverRoboticArm(def, tile, side, GTValues.MAX, 16 * 64)
        }

        registerBehavior("pump.max", GTLiteMetaItems.ELECTRIC_PUMP_MAX) { def, tile, side ->
            CoverPump(def, tile, side, GTValues.MAX, 1280 * 64 * 64 * 4)
        }

        registerBehavior("air_vent", GTLiteMetaItems.AIR_VENT) { def, tile, side ->
            CoverAirVent(def, tile, side, 100)
        }

        registerBehavior("drain", GTLiteMetaItems.DRAIN) { def, tile, side ->
            CoverDrain(def, tile, side, 500)
        }

    }

    private fun registerBehavior(name: String, placerItem: MetaItem<*>.MetaValueItem,
                                 behaviorCreator: CoverDefinition.CoverCreator)
    {
        placerItem.addComponents(CoverItemBehavior(CoverBehaviors.registerCover(
            GTLiteMod.id(name), placerItem.stackForm, behaviorCreator)))
    }

}