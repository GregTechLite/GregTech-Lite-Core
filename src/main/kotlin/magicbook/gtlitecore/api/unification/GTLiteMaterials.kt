package magicbook.gtlitecore.api.unification

import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Darmstadtium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.WroughtIron
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_DENSE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_GEAR

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