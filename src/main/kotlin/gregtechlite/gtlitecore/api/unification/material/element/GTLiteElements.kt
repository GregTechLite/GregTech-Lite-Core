package gregtechlite.gtlitecore.api.unification.material.element

import gregtech.api.unification.Element
import gregtech.api.unification.Elements.add
import gregtechlite.gtlitecore.api.translation.CommonI18n

object GTLiteElements
{

    // @formatter:off

    // region Isotope Elements

    @JvmField
    val Pu244: Element = add(94, 150, 2.52288e+15, null, "Plutonium-244", "Pu-244", true)

    // endregion

    // region Fantastic Elements

    @JvmField
    val Vb: Element  = add(152, 226, "Vibranium", "Vb")

    @JvmField
    val M: Element   = add(166, 345, "Magnetium", "M")

    @JvmField
    val Mx: Element  = add(166, 690, "MagMatter", "M⎋")

    @JvmField
    val Fs: Element  = add(184, 142, "Rhugnor", "Fs⚶")

    @JvmField
    val Ad: Element  = add(222, 580, "Adamantium", "Ad")

    @JvmField
    val Sh: Element  = add(230, 306, "Shirabon", "Sh⏧")

    @JvmField
    val Hy: Element  = add(240, 251, "Hypogen", "Hy⚶")

    @JvmField
    val Tn: Element  = add(321, 478, "Taranium", "Tn")

    @JvmField
    val Tsx: Element = add(360, 360, "TranscendentMetal", "TsЖ")

    @JvmField
    val SpNt: Element = add(1_000,   1_000,   "CosmicNeutronium", "SpNt")

    @JvmField
    val If: Element   = add(1_001,   1_001,   "Infinity", "If")

    @JvmField
    val Crx: Element  = add(3_650,   3_650,   "Creon", "⸎")

    @JvmField
    val Spx: Element  = add(9_999,   9_999,   "SpaceTime",
        CommonI18n.format("gtlitecore.material.space_time.formula", "Reality is falling apart"))

    @JvmField
    val Ux: Element   = add(36_524,  36_524,  "Universium",
        CommonI18n.format("gtlitecore.material.universium.formula", "Hold the Universe in the Palm of your Hand"))

    @JvmField
    val En: Element   = add(100_000, 100_000, "Eternity", "En⦼")

    @JvmField
    val Om: Element   = add(2_097_152, 2_097_152, "Omnium", "Om⦼")

    // endregion

    // @formatter:on

}