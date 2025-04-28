package magicbook.gtlitecore.api.unification.material.element

import gregtech.api.unification.Element
import gregtech.api.unification.Elements

class GTLiteElements
{

    companion object
    {

        @JvmStatic
        val Vb: Element = Elements.add(152, 226, "Vibranium", "Vb")
        @JvmStatic
        val Ad: Element = Elements.add(222, 580, "Adamantium", "Ad")
        @JvmStatic
        val Tn: Element = Elements.add(321, 478, "Taranium", "Tn")

        @JvmStatic
        val SpNt: Element = Elements.add(Elements.Sp.protons + Elements.Nt.protons,
            Elements.Sp.neutrons + Elements.Nt.neutrons, "CosmicNeutronium", "SpNt")

        @JvmStatic
        val If: Element = Elements.add(1001, 1001, "Infinity", "If")

    }

}