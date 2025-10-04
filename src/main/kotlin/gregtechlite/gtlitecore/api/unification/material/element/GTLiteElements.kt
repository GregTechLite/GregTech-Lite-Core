package gregtechlite.gtlitecore.api.unification.material.element

import gregtechlite.gtlitecore.api.unification.material.GTMaterialBuilder.addElement

object GTLiteElements
{

    // @formatter:off

    // region Isotope Elements

    @JvmField
    val Pu244 = addElement("Plutonium-244", "Pu-244")
    {
        isotope()
        atomicProp(94, 150)
        decayable("Rn", 2.52288e+15)
    }

    // endregion

    // region Fantastic Elements

    @JvmField
    val Vb = addElement("Vibranium", "Vb")
    {
        atomicProp(152, 226)
    }

    @JvmField
    val M = addElement("Magnetium", "M")
    {
        atomicProp(166, 345)
    }

    @JvmField
    val Mx = addElement("MagMatter", "M⎋")
    {
        atomicProp(166, 690)
    }

    @JvmField
    val Fs = addElement("Rhugnor", "Fs⚶")
    {
        atomicProp(184, 142)
    }

    @JvmField
    val Ad = addElement("Adamantium", "Ad")
    {
        atomicProp(222, 580)
    }

    @JvmField
    val Sh = addElement("Shirabon", "Sh⏧")
    {
        atomicProp(230, 306)
    }

    @JvmField
    val Hy = addElement("Hypogen", "Hy⚶")
    {
        atomicProp(240, 251)
    }

    @JvmField
    val Tn = addElement("Taranium", "Tn")
    {
        atomicProp(321, 478)
    }

    @JvmField
    val Tsx = addElement("TranscendentMetal", "TsЖ")
    {
        atomicProp(360, 360)
    }

    @JvmField
    val SpNt = addElement("CosmicNeutronium", "SpNt")
    {
        atomicProp(1_000, 1_000)
    }

    @JvmField
    val If = addElement("Infinity", "If")
    {
        atomicProp(1_001, 1_001)
    }

    @JvmField
    val Crx = addElement("Creon", "⸎")
    {
        atomicProp(3_650, 3_650)
    }

    @JvmField
    val Spx = addElement("SpaceTime")
    {
        atomicProp(9_999, 9_999)
        description("gtlitecore.material.space_time.formula", "Reality is falling apart")
    }

    @JvmField
    val Ux = addElement("Universium")
    {
        atomicProp(36_524, 36_524)
        description("gtlitecore.material.universium.formula", "Hold the Universe in the Palm of your Hand")
    }

    @JvmField
    val En = addElement("Eternity", "En⦼")
    {
        atomicProp(100_000, 100_000)
    }

    @JvmField
    val Om = addElement("Omnium", "Om⦼")
    {
        atomicProp(2_097_152, 2_097_152)
    }

    // endregion

    // @formatter:on

}