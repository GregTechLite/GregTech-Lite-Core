package magicbook.gtlitecore.api.unification.material.properties

import gregtech.api.items.toolitem.ToolClasses
import gregtech.api.unification.material.properties.ExtraToolProperty
import gregtech.api.unification.material.properties.PropertyKey
import magicbook.gtlitecore.api.unification.GTLiteMaterials
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Internal
class GTLiteToolPropertyAdder
{

    companion object
    {

        val softMaterials = listOf(
            GTLiteMaterials.KaptonK to 4096,
            GTLiteMaterials.KaptonE to 8192,
            GTLiteMaterials.Polyetheretherketone to 16384,
            GTLiteMaterials.Kevlar to 32768,
            GTLiteMaterials.Zylon to 65536,
            GTLiteMaterials.Fullerene to 131072,
            GTLiteMaterials.CarbonNanotube to 262144,
            GTLiteMaterials.FullerenePolymerMatrix to 524288)

        fun initSoftToolProperties()
        {
            // Add extraToolProperties to higher polymers.
            for (i in softMaterials.indices)
            {
                val material = softMaterials[i].first
                val durability = softMaterials[i].second
                material.properties.ensureSet(PropertyKey.EXTRATOOL)
                material.getProperty(PropertyKey.EXTRATOOL).setOverrideProperty(ToolClasses.SOFT_MALLET,
                    ExtraToolProperty.Builder.of(4F, 1F, durability, 1).build())
                material.getProperty(PropertyKey.EXTRATOOL).setOverrideProperty(ToolClasses.PLUNGER,
                    ExtraToolProperty.Builder.of(4F, 0F, durability, 1).build())
            }
        }

    }

}