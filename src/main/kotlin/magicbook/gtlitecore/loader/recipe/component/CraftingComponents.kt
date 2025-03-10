package magicbook.gtlitecore.loader.recipe.component

import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.stack.UnificationEntry
import gregtech.loaders.recipe.CraftingComponent

class CraftingComponents
{

    companion object
    {

        val GEAR = CraftingComponent.Component(sequenceOf(
            0 to UnificationEntry(gear, WroughtIron),
            1 to UnificationEntry(gear, Steel),
            2 to UnificationEntry(gear, Aluminium),
            3 to UnificationEntry(gear, StainlessSteel),
            4 to UnificationEntry(gear, Titanium),
            5 to UnificationEntry(gear, TungstenSteel),
            6 to UnificationEntry(gear, RhodiumPlatedPalladium),
            7 to UnificationEntry(gear, NaquadahAlloy),
            8 to UnificationEntry(gear, Darmstadtium),
            9 to UnificationEntry(gear, Neutronium)
        ).toMap())

        val GEAR_SMALL = CraftingComponent.Component(sequenceOf(
            0 to UnificationEntry(gearSmall, WroughtIron),
            1 to UnificationEntry(gearSmall, Steel),
            2 to UnificationEntry(gearSmall, Aluminium),
            3 to UnificationEntry(gearSmall, StainlessSteel),
            4 to UnificationEntry(gearSmall, Titanium),
            5 to UnificationEntry(gearSmall, TungstenSteel),
            6 to UnificationEntry(gearSmall, RhodiumPlatedPalladium),
            7 to UnificationEntry(gearSmall, NaquadahAlloy),
            8 to UnificationEntry(gearSmall, Darmstadtium),
            9 to UnificationEntry(gearSmall, Neutronium)
        ).toMap())

        val PLATE_DENSE = CraftingComponent.Component(sequenceOf(
            0 to UnificationEntry(plateDense, WroughtIron),
            1 to UnificationEntry(plateDense, Steel),
            2 to UnificationEntry(plateDense, Aluminium),
            3 to UnificationEntry(plateDense, StainlessSteel),
            4 to UnificationEntry(plateDense, Titanium),
            5 to UnificationEntry(plateDense, TungstenSteel),
            6 to UnificationEntry(plateDense, RhodiumPlatedPalladium),
            7 to UnificationEntry(plateDense, NaquadahAlloy),
            8 to UnificationEntry(plateDense, Darmstadtium),
            9 to UnificationEntry(plateDense, Neutronium)
        ).toMap())

        fun setCraftingComponents()
        {
        }

    }

}