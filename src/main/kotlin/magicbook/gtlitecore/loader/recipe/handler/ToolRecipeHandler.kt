package magicbook.gtlitecore.loader.recipe.handler

import gregtech.api.items.toolitem.IGTTool
import gregtech.api.recipes.ModHandler
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.material.properties.ToolProperty
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.ToolItems
import magicbook.gtlitecore.api.unification.material.properties.GTLiteToolPropertyAdder
import magicbook.gtlitecore.common.item.GTLiteToolItems

@Suppress("MISSING_DEPENDENCY_CLASS", "MISSING_DEPENDENCY_SUPERCLASS")
class ToolRecipeHandler
{

    companion object
    {

        fun init()
        {
            OrePrefix.ingot.addProcessingHandler(PropertyKey.TOOL, this::processTool)
        }

        fun registerCustomToolRecipes()
        {
            registerSoftToolRecipes()
        }

        private fun processTool(toolPrefix: OrePrefix, material: Material, property: ToolProperty)
        {
            if (material.hasProperty(PropertyKey.INGOT))
            {
                if (material.hasFlag(MaterialFlags.GENERATE_ROD))
                {
                    // Rolling pin.
                    addToolRecipe(material, GTLiteToolItems.ROLLING_PIN, true,
                        "  R", " I ", "R f",
                        'R', UnificationEntry(OrePrefix.stick, material),
                        'I', UnificationEntry(OrePrefix.ingot, material))
                }
            }
        }

        private fun registerSoftToolRecipes()
        {
            val softMaterials = GTLiteToolPropertyAdder.softMaterials
            val stick = UnificationEntry(OrePrefix.stick, Materials.Wood)

            for (mat in softMaterials)
            {
                val material = mat.first
                addToolRecipe(material, ToolItems.SOFT_MALLET, true,
                    "II ", "IIS", "II ",
                    'I', UnificationEntry(OrePrefix.ingot, material),
                    'S', stick)
                addToolRecipe(material, ToolItems.PLUNGER, true,
                    "xPP", " SP", "S f",
                    'P', UnificationEntry(OrePrefix.plate, material),
                    'S', stick)
            }
        }

        private fun addToolRecipe(material: Material, tool: IGTTool, mirrored: Boolean, vararg recipe: Any)
        {
            val toolId = tool.toolId
            val toolMaterial = tool[material]
            if (mirrored)
                ModHandler.addMirroredShapedRecipe(String.format("%s_%s", toolId, material), toolMaterial, *recipe)
            else
                ModHandler.addShapedRecipe(String.format("%s_%s", toolId, material), toolMaterial, *recipe)
        }

    }

}