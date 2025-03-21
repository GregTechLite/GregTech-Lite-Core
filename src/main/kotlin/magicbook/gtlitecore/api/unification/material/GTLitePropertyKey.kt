package magicbook.gtlitecore.api.unification.material

import gregtech.api.unification.material.properties.PropertyKey
import magicbook.gtlitecore.api.unification.material.properties.AlloyBlastProperty

class GTLitePropertyKey
{

    companion object
    {

        // This PropertyKey used to add material to automatically ABS recipe generator list,
        // in common situation, all alloys (means component size >2) will auto added this key,
        // and generate correspondenced ABS recipe via AlloyBlastPropertyAdder.
        @JvmField
        val ALLOY_BLAST = PropertyKey("blast_alloy", AlloyBlastProperty::class.java)

    }

}