package magicbook.gtlitecore.api.unification.material

import gregtech.api.unification.material.Materials.Almandine
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Amethyst
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Apatite
import gregtech.api.unification.material.Materials.BlueTopaz
import gregtech.api.unification.material.Materials.CertusQuartz
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.GarnetRed
import gregtech.api.unification.material.Materials.GarnetYellow
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.Grossular
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Lapis
import gregtech.api.unification.material.Materials.Lazurite
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Molybdenite
import gregtech.api.unification.material.Materials.Monazite
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Opal
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Polytetrafluoroethylene
import gregtech.api.unification.material.Materials.Potin
import gregtech.api.unification.material.Materials.Powellite
import gregtech.api.unification.material.Materials.Pyrochlore
import gregtech.api.unification.material.Materials.Pyrope
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.RedSteel
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Ruthenium
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.Sodalite
import gregtech.api.unification.material.Materials.Spessartine
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Tantalite
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Topaz
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.UraniumHexafluoride
import gregtech.api.unification.material.Materials.Uvarovite
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.Materials.Wulfenite
import gregtech.api.unification.material.Materials.Zircon
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.material.info.MaterialFlag
import gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_BOLT_SCREW
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DENSE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DOUBLE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FINE_WIRE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FOIL
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_FRAME
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_LENS
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_LONG_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROTOR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROUND
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SMALL_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING_SMALL
import gregtech.api.unification.material.properties.PropertyKey

class GTLiteMaterialFlags
{

    companion object
    {

        // Used to disabled ABS recipes for automatically generate handler.
        @JvmField
        val NO_ALLOY_BLAST_RECIPES = MaterialFlag.Builder("no_alloy_blast_recipes")
            .requireProps(PropertyKey.BLAST, PropertyKey.FLUID)
            .build()

        // Used to disable everything related to ABS.
        @JvmField
        val DISABLE_ALLOY_PROPERTY = MaterialFlag.Builder("disable_alloy_property")
            .requireProps(PropertyKey.BLAST, PropertyKey.FLUID)
            .requireFlags(NO_ALLOY_BLAST_RECIPES)
            .build()

        fun setMaterialFlags()
        {
            // bolt & screw
            Polytetrafluoroethylene.addFlags(GENERATE_BOLT_SCREW)
            Polybenzimidazole.addFlags(GENERATE_BOLT_SCREW)

            // foil
            Nickel.addFlags(GENERATE_FOIL)
            Niobium.addFlags(GENERATE_FOIL)
            Zirconium.addFlags(GENERATE_FOIL)
            Hafnium.addFlags(GENERATE_FOIL)
            Kanthal.addFlags(GENERATE_FOIL)

            // frameGt
            Potin.addFlags(GENERATE_FRAME)
            RedSteel.addFlags(GENERATE_FRAME)
            Polybenzimidazole.addFlags(GENERATE_FRAME)

            // gear
            RhodiumPlatedPalladium.addFlags(GENERATE_GEAR)
            Darmstadtium.addFlags(GENERATE_GEAR)

            // gearSmall
            Neutronium.addFlags(GENERATE_SMALL_GEAR)

            // lens/craftingLens
            CertusQuartz.addFlags(GENERATE_LENS)
            Almandine.addFlags(GENERATE_LENS)
            Andradite.addFlags(GENERATE_LENS)
            BlueTopaz.addFlags(GENERATE_LENS)
            GreenSapphire.addFlags(GENERATE_LENS)
            Grossular.addFlags(GENERATE_LENS)
            Rutile.addFlags(GENERATE_LENS)
            Lazurite.addFlags(GENERATE_LENS)
            Lapis.addFlags(GENERATE_LENS)
            Pyrope.addFlags(GENERATE_LENS)
            Sodalite.addFlags(GENERATE_LENS)
            Spessartine.addFlags(GENERATE_LENS)
            Topaz.addFlags(GENERATE_LENS)
            Uvarovite.addFlags(GENERATE_LENS)
            Quartzite.addFlags(GENERATE_LENS)
            Realgar.addFlags(GENERATE_LENS)
            Malachite.addFlags(GENERATE_LENS)
            Olivine.addFlags(GENERATE_LENS)
            Opal.addFlags(GENERATE_LENS)
            Amethyst.addFlags(GENERATE_LENS)
            Apatite.addFlags(GENERATE_LENS)
            GarnetRed.addFlags(GENERATE_LENS)
            GarnetYellow.addFlags(GENERATE_LENS)
            Monazite.addFlags(GENERATE_LENS)
            Zircon.addFlags(GENERATE_LENS)

            // plate
            Clay.addFlags(GENERATE_PLATE)
            Inconel718.addFlags(GENERATE_PLATE)
            Niobium.addFlags(GENERATE_PLATE)
            Zirconium.addFlags(GENERATE_PLATE)
            Hafnium.addFlags(GENERATE_PLATE)
            Graphite.addFlags(GENERATE_PLATE)
            Rhenium.addFlags(GENERATE_PLATE)

            // plateDouble
            Inconel718.addFlags(GENERATE_DOUBLE_PLATE)
            Invar.addFlags(GENERATE_DOUBLE_PLATE)

            // plateDense
            WroughtIron.addFlags(GENERATE_DENSE)
            Steel.addFlags(GENERATE_DENSE)
            Aluminium.addFlags(GENERATE_DENSE)
            StainlessSteel.addFlags(GENERATE_DENSE)
            Titanium.addFlags(GENERATE_DENSE)
            Neutronium.addFlags(GENERATE_DENSE)
            Invar.addFlags(GENERATE_DENSE)
            Potin.addFlags(GENERATE_DENSE)
            Osmium.addFlags(GENERATE_DENSE)

            // rotor
            WroughtIron.addFlags(GENERATE_ROTOR)
            TinAlloy.addFlags(GENERATE_ROTOR)
            Aluminium.addFlags(GENERATE_ROTOR)

            // round
            Steel.addFlags(GENERATE_ROUND)

            // stick
            Polybenzimidazole.addFlags(GENERATE_ROD)

            // stickLong
            Chrome.addFlags(GENERATE_LONG_ROD)

            // spring
            TinAlloy.addFlags(GENERATE_SPRING)

            // springSmall
            WroughtIron.addFlags(GENERATE_SPRING_SMALL)
            Europium.addFlags(GENERATE_SPRING_SMALL)

            // wireFine
            Titanium.addFlags(GENERATE_FINE_WIRE)
            Niobium.addFlags(GENERATE_FINE_WIRE)
            Tungsten.addFlags(GENERATE_FINE_WIRE)
            Ruthenium.addFlags(GENERATE_FINE_WIRE)
            Hafnium.addFlags(GENERATE_FINE_WIRE)
            Kanthal.addFlags(GENERATE_FINE_WIRE)

            // Disabled pyrochlore and tantalite ore composition for Niobium-Tantalum chain.
            Pyrochlore.addFlags(DISABLE_DECOMPOSITION)
            Tantalite.addFlags(DISABLE_DECOMPOSITION)
            // Disabled molybdenite, powellite and wulfenite ore composition for Molybdenum-Rhenium chain.
            Molybdenite.addFlags(DISABLE_DECOMPOSITION)
            Powellite.addFlags(DISABLE_DECOMPOSITION)
            Wulfenite.addFlags(DISABLE_DECOMPOSITION)
            // Disabled uranium hexafluoride composition.
            UraniumHexafluoride.addFlags(DISABLE_DECOMPOSITION)
        }

    }

}