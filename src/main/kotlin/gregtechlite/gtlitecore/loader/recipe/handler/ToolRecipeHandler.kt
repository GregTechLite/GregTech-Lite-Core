package gregtechlite.gtlitecore.loader.recipe.handler

import gregtech.api.items.toolitem.IGTTool
import gregtech.api.recipes.ModHandler
import gregtech.api.unification.material.MarkerMaterials.Color
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Wood
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_ROD
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SMALL_GEAR
import gregtech.api.unification.material.info.MaterialFlags.GENERATE_SPRING_SMALL
import gregtech.api.unification.material.properties.PropertyKey
import gregtech.api.unification.material.properties.ToolProperty
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.unification.ore.OrePrefix.dye
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.ToolItems.PLUNGER
import gregtech.common.items.ToolItems.SOFT_MALLET
import gregtechlite.gtlitecore.api.unification.material.properties.GTLiteToolPropertyAdder
import gregtechlite.gtlitecore.common.item.GTLiteToolItems.CLUB
import gregtechlite.gtlitecore.common.item.GTLiteToolItems.COMBINATION_WRENCH
import gregtechlite.gtlitecore.common.item.GTLiteToolItems.FLINT_AND_STEEL
import gregtechlite.gtlitecore.common.item.GTLiteToolItems.ROLLING_PIN
import gregtechlite.gtlitecore.common.item.GTLiteToolItems.UNIVERSAL_SPADE
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("unused")
object ToolRecipeHandler
{

    // @formatter:off

    fun init()
    {
        ingot.addProcessingHandler(PropertyKey.TOOL, ::processTool)
    }

    fun registerCustomToolRecipes()
    {
        registerSoftToolRecipes()
    }

    private fun processTool(toolPrefix: OrePrefix, material: Material, property: ToolProperty)
    {
        if (material.hasProperty(PropertyKey.INGOT))
        {

            if (material.hasFlag(GENERATE_PLATE))
            {
                // Combination Wrench
                addToolRecipe(material, COMBINATION_WRENCH, true,
                    "PhP", "IPI", "fP ",
                    'I', UnificationEntry(ingot, material),
                    'P', UnificationEntry(plate, material))
            }

            if (material.hasFlag(GENERATE_ROD))
            {
                // Rolling Pin
                addToolRecipe(material, ROLLING_PIN, true,
                    "  R", " I ", "R f",
                    'R', UnificationEntry(stick, material),
                    'I', UnificationEntry(ingot, material))

                // Club
                addToolRecipe(material, CLUB, true,
                    "hII", "III", "RIf",
                    'I', UnificationEntry(ingot, material),
                    'R', UnificationEntry(stick, material))
            }

            if (material.hasFlags(GENERATE_PLATE, GENERATE_ROD))
            {
                // Universal Spade
                addToolRecipe(material, UNIVERSAL_SPADE, true,
                    "hPP", "DRP", "RDf",
                    'P', UnificationEntry(plate, material),
                    'R', UnificationEntry(stick, material),
                    'D', UnificationEntry(dye, Color.Blue))
            }

            // Do not generate Steel Flint And Steel because the vanilla Flint And Steel is Steel yet.
            if (material.hasFlags(GENERATE_SPRING_SMALL, GENERATE_SMALL_GEAR) && material != Steel)
            {
                // Flint And Steel
                addToolRecipe(material, FLINT_AND_STEEL, false,
                    " G ", " F ", " S ",
                    'G', UnificationEntry(gearSmall, material),
                    'S', UnificationEntry(springSmall, material),
                    'F', ItemStack(Items.FLINT))
            }

        }
    }

    private fun registerSoftToolRecipes()
    {
        val softMaterials = GTLiteToolPropertyAdder.softMaterials
        val stick = UnificationEntry(stick, Wood)

        for (mat in softMaterials)
        {
            val material = mat.first
            addToolRecipe(material, SOFT_MALLET, true,
                "II ", "IIS", "II ",
                'I', UnificationEntry(ingot, material),
                'S', stick)
            addToolRecipe(material, PLUNGER, true,
                "xPP", " SP", "S f",
                'P', UnificationEntry(plate, material),
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