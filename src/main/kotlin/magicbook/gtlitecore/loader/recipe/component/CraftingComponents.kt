package magicbook.gtlitecore.loader.recipe.component

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
import gregtech.api.GTValues.ZPM
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.BismuthBronze
import gregtech.api.unification.material.Materials.BlackBronze
import gregtech.api.unification.material.Materials.Brass
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.stack.UnificationEntry
import gregtech.loaders.recipe.CraftingComponent
import magicbook.gtlitecore.common.item.GTLiteMetaItems

@Suppress("MISSING_DEPENDENCY_CLASS")
class CraftingComponents
{

    companion object
    {

        // Gears.
        val GEAR = CraftingComponent.Component(sequenceOf(
            ULV to UnificationEntry(gear, WroughtIron),
            LV  to UnificationEntry(gear, Steel),
            MV  to UnificationEntry(gear, Aluminium),
            HV  to UnificationEntry(gear, StainlessSteel),
            EV  to UnificationEntry(gear, Titanium),
            IV  to UnificationEntry(gear, TungstenSteel),
            LuV to UnificationEntry(gear, RhodiumPlatedPalladium),
            ZPM to UnificationEntry(gear, NaquadahAlloy),
            UV  to UnificationEntry(gear, Darmstadtium),
            UHV to UnificationEntry(gear, Neutronium)
        ).toMap())

        // Small Gears.
        val GEAR_SMALL = CraftingComponent.Component(sequenceOf(
            ULV to UnificationEntry(gearSmall, WroughtIron),
            LV  to UnificationEntry(gearSmall, Steel),
            MV  to UnificationEntry(gearSmall, Aluminium),
            HV  to UnificationEntry(gearSmall, StainlessSteel),
            EV  to UnificationEntry(gearSmall, Titanium),
            IV  to UnificationEntry(gearSmall, TungstenSteel),
            LuV to UnificationEntry(gearSmall, RhodiumPlatedPalladium),
            ZPM to UnificationEntry(gearSmall, NaquadahAlloy),
            UV  to UnificationEntry(gearSmall, Darmstadtium),
            UHV to UnificationEntry(gearSmall, Neutronium)
        ).toMap())

        // Dense Plates.
        val PLATE_DENSE = CraftingComponent.Component(sequenceOf(
            ULV to UnificationEntry(plateDense, WroughtIron),
            LV  to UnificationEntry(plateDense, Steel),
            MV  to UnificationEntry(plateDense, Aluminium),
            HV  to UnificationEntry(plateDense, StainlessSteel),
            EV  to UnificationEntry(plateDense, Titanium),
            IV  to UnificationEntry(plateDense, TungstenSteel),
            LuV to UnificationEntry(plateDense, RhodiumPlatedPalladium),
            ZPM to UnificationEntry(plateDense, NaquadahAlloy),
            UV  to UnificationEntry(plateDense, Darmstadtium),
            UHV to UnificationEntry(plateDense, Neutronium)
        ).toMap())

        // Sticks.
        val STICK = CraftingComponent.Component(sequenceOf(
            ULV to UnificationEntry(stick, WroughtIron),
            LV  to UnificationEntry(stick, Steel),
            MV  to UnificationEntry(stick, Aluminium),
            HV  to UnificationEntry(stick, StainlessSteel),
            EV  to UnificationEntry(stick, Titanium),
            IV  to UnificationEntry(stick, TungstenSteel),
            LuV to UnificationEntry(stick, RhodiumPlatedPalladium),
            ZPM to UnificationEntry(stick, NaquadahAlloy),
            UV  to UnificationEntry(stick, Darmstadtium),
            UHV to UnificationEntry(stick, Neutronium)
        ).toMap())

        // Long Sticks.
        val STICK_LONG = CraftingComponent.Component(sequenceOf(
            ULV to UnificationEntry(stickLong, WroughtIron),
            LV  to UnificationEntry(stickLong, Steel),
            MV  to UnificationEntry(stickLong, Aluminium),
            HV  to UnificationEntry(stickLong, StainlessSteel),
            EV  to UnificationEntry(stickLong, Titanium),
            IV  to UnificationEntry(stickLong, TungstenSteel),
            LuV to UnificationEntry(stickLong, RhodiumPlatedPalladium),
            ZPM to UnificationEntry(stickLong, NaquadahAlloy),
            UV  to UnificationEntry(stickLong, Darmstadtium),
            UHV to UnificationEntry(stickLong, Neutronium)
        ).toMap())

        // Small Springs.
        val SPRING_SMALL = CraftingComponent.Component(sequenceOf(
            ULV to UnificationEntry(springSmall, Lead),
            LV  to UnificationEntry(springSmall, Tin),
            MV  to UnificationEntry(springSmall, Copper),
            HV  to UnificationEntry(springSmall, Gold),
            EV  to UnificationEntry(springSmall, Aluminium),
            IV  to UnificationEntry(springSmall, Tungsten),
            LuV to UnificationEntry(springSmall, NiobiumTitanium),
            ZPM to UnificationEntry(springSmall, VanadiumGallium),
            UV  to UnificationEntry(springSmall, YttriumBariumCuprate),
            UHV to UnificationEntry(springSmall, Europium)
        ).toMap())

        fun setCraftingComponents()
        {

            // Voltage Coils.
            CraftingComponent.VOLTAGE_COIL.appendIngredients(sequenceOf(
                UHV to GTLiteMetaItems.VOLTAGE_COIL_UHV.stackForm,
                UEV to GTLiteMetaItems.VOLTAGE_COIL_UEV.stackForm,
                UIV to GTLiteMetaItems.VOLTAGE_COIL_UIV.stackForm,
                UXV to GTLiteMetaItems.VOLTAGE_COIL_UXV.stackForm,
                OpV to GTLiteMetaItems.VOLTAGE_COIL_OpV.stackForm,
                MAX to GTLiteMetaItems.VOLTAGE_COIL_MAX.stackForm
            ).toMap())

        }

    }

}