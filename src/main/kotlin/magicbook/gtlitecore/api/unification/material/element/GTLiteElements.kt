package magicbook.gtlitecore.api.unification.material.element

import gregtech.api.unification.Element
import gregtech.api.unification.Elements

class GTLiteElements
{

    companion object
    {

        // Isotope elements
        @JvmStatic
        val Pu244: Element = Elements.add(94, 150, 2.52288e+15, null, "Plutonium-244", "Pu-244", true)

        // Fantastic elements
        @JvmStatic
        val Vb: Element = Elements.add(152, 226, "Vibranium", "Vb")
        @JvmStatic
        val Ad: Element = Elements.add(222, 580, "Adamantium", "Ad")
        @JvmStatic
        val Tn: Element = Elements.add(321, 478, "Taranium", "Tn")
        @JvmStatic
        val SpNt: Element = Elements.add(Elements.Sp.protons + Elements.Nt.protons, Elements.Sp.neutrons + Elements.Nt.neutrons, "CosmicNeutronium", "SpNt")
        @JvmStatic
        val If: Element = Elements.add(1001, 1001, "Infinity", "If")

        @JvmStatic
        val M: Element = Elements.add(166, 345, "Magnetium", "M")

    }

}