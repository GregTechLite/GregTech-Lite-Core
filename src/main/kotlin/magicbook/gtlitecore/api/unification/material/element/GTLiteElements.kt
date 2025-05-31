package magicbook.gtlitecore.api.unification.material.element

import gregtech.api.unification.Element
import gregtech.api.unification.Elements.add
import magicbook.gtlitecore.api.utils.FormattingUtility.Companion.format

class GTLiteElements
{

    companion object
    {
        // Isotope Elements
        val Pu244: Element = add(94, 150, 2.52288e+15, null, "Plutonium-244", "Pu-244", true)

        // -------------------------------------------------------------------------------------------------------------
        // Fantastic elements

        val Vb: Element  = add(152, 226, "Vibranium", "Vb")
        val M: Element   = add(166, 345, "Magnetium", "M")
        val Mx: Element  = add(166, 690, "MagMatter", "M⎋")
        val Fs: Element  = add(184, 142, "Rhugnor", "Fs⚶")
        val Ad: Element  = add(222, 580, "Adamantium", "Ad")
        val Sh: Element  = add(230, 306, "Shirabon", "Sh⏧")
        val Hy: Element  = add(240, 251, "Hypogen", "Hy⚶")
        val Tn: Element  = add(321, 478, "Taranium", "Tn")
        val Tsx: Element = add(360, 360, "TranscendentMetal", "TsЖ")

        val SpNt: Element = add(1_000,   1_000,   "CosmicNeutronium", "SpNt")
        val If: Element   = add(1_001,   1_001,   "Infinity", "If")
        val Crx: Element  = add(3_650,   3_650,   "Creon", "⸎")
        val Spx: Element  = add(9_999,   9_999,   "SpaceTime", format("gtlitecore.material.space_time.formula", "Reality is falling apart"))
        val Ux: Element   = add(36_524,  36_524,  "Universium", format("gtlitecore.material.universium.formula", "Hold the Universe in the Palm of your Hand"))
        val En: Element   = add(100_000, 100_000, "Eternity", "En⦼")

    }

}