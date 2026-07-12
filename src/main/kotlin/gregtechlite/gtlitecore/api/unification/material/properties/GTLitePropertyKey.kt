package gregtechlite.gtlitecore.api.unification.material.properties

import gregtech.api.unification.material.properties.PropertyKey

object GTLitePropertyKey
{
    // Add material to automatically ABS recipe generator list,  all alloys (component size > 2) will auto added
    // this key, and generate corresponding ABS recipe via AlloyBlastPropertyAdder.
    @JvmField
    val ALLOY_BLAST = PropertyKey("blast_alloy", AlloyBlastProperty::class.java)

    // Automatically generate lens recipe for those materials with lens flag but without gem property. All materials
    // with lens flag but without gem property will auto added this key, and generate corresponding lens recipe
    // via AmorphousLensAdder.
    @JvmField
    val AMORPHOUS_LENS = PropertyKey("amorphous_lens", AmorphousLensProperty::class.java)
}