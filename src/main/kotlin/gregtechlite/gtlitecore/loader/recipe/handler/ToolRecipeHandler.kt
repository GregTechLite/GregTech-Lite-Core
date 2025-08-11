package gregtechlite.gtlitecore.loader.recipe.handler

import gregtech.api.items.toolitem.IGTTool
import gregtech.api.recipes.ModHandler
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.info.MaterialFlags
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.material.properties.ToolProperty
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.ToolItems
import gregtechlite.gtlitecore.api.unification.material.properties.GTLiteToolPropertyAdder
import gregtechlite.gtlitecore.common.item.GTLiteToolItems
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object ToolRecipeHandler
{

    // @formatter:off

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

            if (material.hasFlags(MaterialFlags.GENERATE_PLATE))
            {
                // Combination Wrench
                addToolRecipe(material, GTLiteToolItems.COMBINATION_WRENCH, true,
                    "PhP", "IPI", "fP ",
                    'I', UnificationEntry(OrePrefix.ingot, material),
                    'P', UnificationEntry(OrePrefix.plate, material))
            }

            if (material.hasFlag(MaterialFlags.GENERATE_ROD))
            {
                // Rolling Pin
                addToolRecipe(material, GTLiteToolItems.ROLLING_PIN, true,
                    "  R", " I ", "R f",
                    'R', UnificationEntry(OrePrefix.stick, material),
                    'I', UnificationEntry(OrePrefix.ingot, material))

                // Club
                addToolRecipe(material, GTLiteToolItems.CLUB, true,
                    "hII", "III", "RIf",
                    'I', UnificationEntry(OrePrefix.ingot, material),
                    'R', UnificationEntry(OrePrefix.stick, material))
            }

            if (material.hasFlags(MaterialFlags.GENERATE_PLATE) && material.hasFlags(MaterialFlags.GENERATE_ROD))
            {
                // Universal Spade
                addToolRecipe(material, GTLiteToolItems.UNIVERSAL_SPADE, true,
                    "hPP", "DRP", "RDf",
                    'P', UnificationEntry(OrePrefix.plate, material),
                    'R', UnificationEntry(OrePrefix.stick, material),
                    'D', UnificationEntry(OrePrefix.dye, MarkerMaterials.Color.Blue))
            }

            if (material.hasFlags(MaterialFlags.GENERATE_SPRING_SMALL) && material.hasFlags(MaterialFlags.GENERATE_SMALL_GEAR)
                && material != Materials.Steel) // Do not generate Steel Flint And Steel because the vanilla Flint And Steel is Steel yet.
            {
                // Flint And Steel
                addToolRecipe(material, GTLiteToolItems.FLINT_AND_STEEL, false,
                    " G ", " F ", " S ",
                    'G', UnificationEntry(OrePrefix.gearSmall, material),
                    'S', UnificationEntry(OrePrefix.springSmall, material),
                    'F', ItemStack(Items.FLINT))
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

    // @formatter:on

}