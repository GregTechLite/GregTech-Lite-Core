package gregtechlite.gtlitecore.api.unification.material.properties

import gregtech.api.GregTechAPI
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.stack.MaterialStack
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags
import gregtechlite.gtlitecore.loader.recipe.producer.CustomAlloyBlastRecipeProducer

object AlloyBlastPropertyAdder
{

    // The first start point of ABS property adder.
    fun preInitABSProperties()
    {
        GregTechAPI.materialManager.registeredMaterials
            .filter { !it.hasFlag(GTLiteMaterialFlags.DISABLE_ALLOY_PROPERTY) }
            .forEach(::addAlloyBlastProperty)
    }

    // Used to process special recipes to resolve conflicts of ABS recipes.
    fun initABSProperties()
    {
        // NbN
        var property = Materials.NiobiumNitride.getProperty(GTLitePropertyKey.ALLOY_BLAST)
        property.recipeProducer = CustomAlloyBlastRecipeProducer(1, 11, -1)

        // In4Sn2Ba2TiCu7O14
        property = Materials.IndiumTinBariumTitaniumCuprate.getProperty(GTLitePropertyKey.ALLOY_BLAST)
        property.recipeProducer = CustomAlloyBlastRecipeProducer(-1, -1, 16)
    }

    private fun addAlloyBlastProperty(material: Material)
    {
        val components = material.materialComponents
        if (components.size < 2) return // Ignore Materials which are not alloys.

        if (!material.hasProperty(PropertyKey.BLAST) || !material.hasProperty(PropertyKey.FLUID)) return

        // If there are more than 2 fluid-only components in the material, do not generate fluid.
        if (components.filter(this::isMaterialStackFluidOnly).take(3).count() > 2)
            return

        material.setProperty(GTLitePropertyKey.ALLOY_BLAST, AlloyBlastProperty(material.blastTemperature))
    }

    private fun isMaterialStackFluidOnly(materialStack: MaterialStack): Boolean
    {
        return (!materialStack.material.hasProperty(PropertyKey.DUST)
                && materialStack.material.hasProperty(PropertyKey.FLUID))
    }

}