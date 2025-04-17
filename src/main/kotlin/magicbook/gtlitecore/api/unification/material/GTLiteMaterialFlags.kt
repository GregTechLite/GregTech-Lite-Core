package magicbook.gtlitecore.api.unification.material

import gregtech.api.unification.material.Materials.Almandine
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Amethyst
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Apatite
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.BismuthBronze
import gregtech.api.unification.material.Materials.BlueTopaz
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Brass
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.CertusQuartz
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Emerald
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.GarnetRed
import gregtech.api.unification.material.Materials.GarnetYellow
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.Grossular
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.IronMagnetic
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Lapis
import gregtech.api.unification.material.Materials.Lazurite
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Molybdenite
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Monazite
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.NeodymiumMagnetic
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.NiobiumNitride
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Opal
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Polytetrafluoroethylene
import gregtech.api.unification.material.Materials.PolyvinylButyral
import gregtech.api.unification.material.Materials.Potin
import gregtech.api.unification.material.Materials.Powellite
import gregtech.api.unification.material.Materials.Pyrochlore
import gregtech.api.unification.material.Materials.Pyrope
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.RTMAlloy
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.RedSteel
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.RockSalt
import gregtech.api.unification.material.Materials.Ruby
import gregtech.api.unification.material.Materials.Ruthenium
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Sapphire
import gregtech.api.unification.material.Materials.Sodalite
import gregtech.api.unification.material.Materials.Spessartine
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.SteelMagnetic
import gregtech.api.unification.material.Materials.Tantalite
import gregtech.api.unification.material.Materials.Thallium
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Topaz
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.Ultimet
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uvarovite
import gregtech.api.unification.material.Materials.Vanadium
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.Materials.Wulfenite
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.material.Materials.Zircaloy4
import gregtech.api.unification.material.Materials.Zircon
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.material.info.MaterialFlag
import gregtech.api.unification.material.info.MaterialFlags
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
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_RING
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
        val NO_ALLOY_BLAST_RECIPES: MaterialFlag = MaterialFlag.Builder("no_alloy_blast_recipes")
            .requireProps(PropertyKey.BLAST, PropertyKey.FLUID)
            .build()

        // Used to disable everything related to ABS.
        @JvmField
        val DISABLE_ALLOY_PROPERTY: MaterialFlag = MaterialFlag.Builder("disable_alloy_property")
            .requireProps(PropertyKey.BLAST, PropertyKey.FLUID)
            .requireFlags(NO_ALLOY_BLAST_RECIPES)
            .build()

        // Used to disable crystallization crucible recipes of a crystallizable gem,
        // means it only has autoclave crystallization recipes.
        @JvmField
        val DISABLE_CRYSTALLIZATION: MaterialFlag = MaterialFlag.Builder("disable_crystallization")
            .requireProps(PropertyKey.GEM)
            .requireFlags(MaterialFlags.CRYSTALLIZABLE)
            .build()

        // Used to generate boule without crystal seed, or want to generate crystallization
        // crucible recipes only without autoclave recipes.
        @JvmField
        val GENERATE_BOULE: MaterialFlag = MaterialFlag.Builder("generate_boule")
            .requireProps(PropertyKey.GEM)
            .build()


        fun setMaterialFlags()
        {
            // bolt & screw
            Polytetrafluoroethylene.addFlags(GENERATE_BOLT_SCREW)
            Polybenzimidazole.addFlags(GENERATE_BOLT_SCREW)
            Zircaloy4.addFlags(GENERATE_BOLT_SCREW)
            Duranium.addFlags(GENERATE_BOLT_SCREW)

            // boule
            Diamond.addFlags(GENERATE_BOULE)
            Sapphire.addFlags(GENERATE_BOULE)
            GreenSapphire.addFlags(GENERATE_BOULE)
            Ruby.addFlags(GENERATE_BOULE)
            Emerald.addFlags(GENERATE_BOULE)
            Topaz.addFlags(GENERATE_BOULE)
            BlueTopaz.addFlags(GENERATE_BOULE)
            Almandine.addFlags(GENERATE_BOULE)
            Andradite.addFlags(GENERATE_BOULE)
            Zircon.addFlags(GENERATE_BOULE)
            Grossular.addFlags(GENERATE_BOULE)
            Rutile.addFlags(GENERATE_BOULE)
            Pyrope.addFlags(GENERATE_BOULE)
            Spessartine.addFlags(GENERATE_BOULE)
            Uvarovite.addFlags(GENERATE_BOULE)
            Realgar.addFlags(GENERATE_BOULE)
            Malachite.addFlags(GENERATE_BOULE)
            Olivine.addFlags(GENERATE_BOULE)
            Opal.addFlags(GENERATE_BOULE)
            Amethyst.addFlags(GENERATE_BOULE)
            GarnetRed.addFlags(GENERATE_BOULE)
            GarnetYellow.addFlags(GENERATE_BOULE)

            // foil
            Nickel.addFlags(GENERATE_FOIL)
            Niobium.addFlags(GENERATE_FOIL)
            Zirconium.addFlags(GENERATE_FOIL)
            Hafnium.addFlags(GENERATE_FOIL)
            Kanthal.addFlags(GENERATE_FOIL)
            Indium.addFlags(GENERATE_FOIL)
            Ultimet.addFlags(GENERATE_FOIL)
            RTMAlloy.addFlags(GENERATE_FOIL)
            PolyvinylButyral.addFlags(GENERATE_FOIL)
            Germanium.addFlags(GENERATE_FOIL)
            NaquadahEnriched.addFlags(GENERATE_FOIL)
            Thallium.addFlags(GENERATE_FOIL)
            Barium.addFlags(GENERATE_FOIL)
            Calcium.addFlags(GENERATE_FOIL)

            // frameGt
            Potin.addFlags(GENERATE_FRAME)
            RedSteel.addFlags(GENERATE_FRAME)
            Polybenzimidazole.addFlags(GENERATE_FRAME)
            VanadiumSteel.addFlags(GENERATE_FRAME)
            BismuthBronze.addFlags(GENERATE_FRAME)
            RhodiumPlatedPalladium.addFlags(GENERATE_FRAME)
            Darmstadtium.addFlags(GENERATE_FRAME)
            Brass.addFlags(GENERATE_FRAME)
            Cobalt.addFlags(GENERATE_FRAME)
            TungstenCarbide.addFlags(GENERATE_FRAME)

            // gear
            RhodiumPlatedPalladium.addFlags(GENERATE_GEAR)
            Darmstadtium.addFlags(GENERATE_GEAR)
            Brass.addFlags(GENERATE_GEAR)

            // gearSmall
            Neutronium.addFlags(GENERATE_SMALL_GEAR)
            HSSE.addFlags(GENERATE_SMALL_GEAR)

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
            Uranium235.addFlags(GENERATE_PLATE)
            Plutonium239.addFlags(GENERATE_PLATE)
            Indium.addFlags(GENERATE_PLATE)
            Vanadium.addFlags(GENERATE_PLATE)
            Graphene.addFlags(GENERATE_PLATE)
            Germanium.addFlags(GENERATE_PLATE)
            Dubnium.addFlags(GENERATE_PLATE)
            Rutherfordium.addFlags(GENERATE_PLATE)
            Bohrium.addFlags(GENERATE_PLATE)
            Thallium.addFlags(GENERATE_PLATE)
            Barium.addFlags(GENERATE_PLATE)
            Calcium.addFlags(GENERATE_PLATE)
            Curium.addFlags(GENERATE_PLATE)

            // plateDouble
            Inconel718.addFlags(GENERATE_DOUBLE_PLATE)
            Invar.addFlags(GENERATE_DOUBLE_PLATE)
            Uranium235.addFlags(GENERATE_DOUBLE_PLATE)
            Plutonium239.addFlags(GENERATE_DOUBLE_PLATE)
            Dubnium.addFlags(GENERATE_DOUBLE_PLATE)
            Rutherfordium.addFlags(GENERATE_DOUBLE_PLATE)
            HSSS.addFlags(GENERATE_DOUBLE_PLATE)
            Tritanium.addFlags(GENERATE_DOUBLE_PLATE)

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

            // ring
            Duranium.addFlags(GENERATE_RING)

            // rotor
            WroughtIron.addFlags(GENERATE_ROTOR)
            TinAlloy.addFlags(GENERATE_ROTOR)
            Aluminium.addFlags(GENERATE_ROTOR)
            Iridium.addFlags(GENERATE_ROTOR)
            Tritanium.addFlags(GENERATE_ROTOR)
            Neutronium.addFlags(GENERATE_ROTOR)
            Duranium.addFlags(GENERATE_ROTOR)

            // round
            Steel.addFlags(GENERATE_ROUND)

            // stick
            Polybenzimidazole.addFlags(GENERATE_ROD)
            Zircaloy4.addFlags(GENERATE_ROD)
            Rutherfordium.addFlags(GENERATE_ROD)

            // stickLong
            Chrome.addFlags(GENERATE_LONG_ROD)
            Diamond.addFlags(GENERATE_LONG_ROD)
            Trinium.addFlags(GENERATE_LONG_ROD)
            IronMagnetic.addFlags(GENERATE_LONG_ROD)
            SteelMagnetic.addFlags(GENERATE_LONG_ROD)
            NeodymiumMagnetic.addFlags(GENERATE_LONG_ROD)

            // spring
            TinAlloy.addFlags(GENERATE_SPRING)
            Trinium.addFlags(GENERATE_SPRING)
            Tritanium.addFlags(GENERATE_SPRING)

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
            HSSS.addFlags(GENERATE_FINE_WIRE)
            Molybdenum.addFlags(GENERATE_FINE_WIRE)
            Ultimet.addFlags(GENERATE_FINE_WIRE)
            RTMAlloy.addFlags(GENERATE_FINE_WIRE)
            NiobiumNitride.addFlags(GENERATE_FINE_WIRE)
            Trinium.addFlags(GENERATE_FINE_WIRE)
            NaquadahAlloy.addFlags(GENERATE_FINE_WIRE)
            Germanium.addFlags(GENERATE_FINE_WIRE)
            NaquadahEnriched.addFlags(GENERATE_FINE_WIRE)
            Curium.addFlags(GENERATE_FINE_WIRE)

            // Disabled pyrochlore and tantalite ore composition for Niobium-Tantalum chain.
            Pyrochlore.addFlags(DISABLE_DECOMPOSITION)
            Tantalite.addFlags(DISABLE_DECOMPOSITION)
            // Disabled molybdenite, powellite and wulfenite ore composition for Molybdenum-Rhenium chain.
            Molybdenite.addFlags(DISABLE_DECOMPOSITION)
            Powellite.addFlags(DISABLE_DECOMPOSITION)
            Wulfenite.addFlags(DISABLE_DECOMPOSITION)
            // Disabled clay composition.
            Clay.addFlags(DISABLE_DECOMPOSITION)
            // Disabled salt composition.
            Salt.addFlags(DISABLE_DECOMPOSITION)
            RockSalt.addFlags(DISABLE_DECOMPOSITION)

            // Disabled Rhodium Plated Palladium and YBCO ABS recipe.
            RhodiumPlatedPalladium.addFlags(NO_ALLOY_BLAST_RECIPES)
            YttriumBariumCuprate.addFlags(NO_ALLOY_BLAST_RECIPES)
        }

    }

}