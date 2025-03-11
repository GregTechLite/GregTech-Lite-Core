package magicbook.gtlitecore.loader.recipe.handler

import gregtech.api.items.toolitem.IGTTool
import gregtech.api.recipes.ModHandler
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.material.properties.ToolProperty
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.UnificationEntry
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