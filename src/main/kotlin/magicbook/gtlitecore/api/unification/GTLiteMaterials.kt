package magicbook.gtlitecore.api.unification

import gregtech.api.unification.material.Materials.Almandine
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Amethyst
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Apatite
import gregtech.api.unification.material.Materials.BlueTopaz
import gregtech.api.unification.material.Materials.CertusQuartz
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.GarnetRed
import gregtech.api.unification.material.Materials.GarnetYellow
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.Grossular
import gregtech.api.unification.material.Materials.Lapis
import gregtech.api.unification.material.Materials.Lazurite
import gregtech.api.unification.material.Materials.Malachite
import gregtech.api.unification.material.Materials.Monazite
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Opal
import gregtech.api.unification.material.Materials.Pyrope
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Rutile
import gregtech.api.unification.material.Materials.Sodalite
import gregtech.api.unification.material.Materials.Spessartine
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Topaz
import gregtech.api.unification.material.Materials.Uvarovite
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DENSE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_LENS

class GTLiteMaterials
{

    companion object
    {

        fun setMaterialProperties()
        {

        }

        fun setMaterialFlags()
        {
            // gear
            RhodiumPlatedPalladium.addFlags(GENERATE_GEAR)
            Darmstadtium.addFlags(GENERATE_GEAR)

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

            // plateDense
            WroughtIron.addFlags(GENERATE_DENSE)
            Steel.addFlags(GENERATE_DENSE)
            Aluminium.addFlags(GENERATE_DENSE)
            StainlessSteel.addFlags(GENERATE_DENSE)
            Titanium.addFlags(GENERATE_DENSE)
            Neutronium.addFlags(GENERATE_DENSE)
        }

    }

}