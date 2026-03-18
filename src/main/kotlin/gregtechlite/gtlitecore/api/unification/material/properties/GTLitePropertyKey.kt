package gregtechlite.gtlitecore.api.unification.material.properties

import gregtech.api.unification.material.properties.PropertyKey

object GTLitePropertyKey
{

    // This PropertyKey used to add material to automatically ABS recipe generator list,
    // in common situation, all alloys (means component size >2) will auto added this key,
    // and generate correspondence ABS recipe via AlloyBlastPropertyAdder.
    @JvmField
    val ALLOY_BLAST = PropertyKey("blast_alloy", AlloyBlastProperty::class.java)

    // This PropertyKey used to automatically generate Lens recipe for those materials with
    // GENERATE_LENS flag but without GEM property.
    // In common situation, all materials with LENS flag but without GEM property will auto
    // added this key, and generate correspondence Lens recipe via AmorphousLensAdder.
    @JvmField
    val AMORPHOUS_LENS = PropertyKey("lens", AmorphousLensProperty::class.java)

}