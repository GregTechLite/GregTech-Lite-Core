package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.EXTRUDER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Polytetrafluoroethylene
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.common.items.MetaItems.FLUID_CELL
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_CELL
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.PBI_CAN
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.PE_CAN
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.Companion.PTFE_CAN

@Suppress("MISSING_DEPENDENCY_CLASS")
class ExtruderRecipes
{

    companion object
    {

        fun init()
        {
            // Deleted original cell recipes which used PE, PTFE and PBI.
            GTRecipeHandler.removeRecipesByInputs(EXTRUDER_RECIPES,
                OreDictUnifier.get(ingot, Polytetrafluoroethylene),
                SHAPE_EXTRUDER_CELL.stackForm)

            GTRecipeHandler.removeRecipesByInputs(EXTRUDER_RECIPES,
                OreDictUnifier.get(ingot, Polybenzimidazole),
                SHAPE_EXTRUDER_CELL.stackForm)

            // Add new empty cell buff recipes.
            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_CELL)
                .input(ingot, Aluminium)
                .output(FLUID_CELL, 4)
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND + 5 * TICK)
                .buildAndRegister()

            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_CELL)
                .input(ingot, StainlessSteel)
                .output(FLUID_CELL, 16)
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND + 5 * TICK)
                .buildAndRegister()

            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_CELL)
                .input(ingot, Titanium)
                .output(FLUID_CELL, 32)
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND + 5 * TICK)
                .buildAndRegister()

            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_CELL)
                .input(ingot, TungstenSteel)
                .output(FLUID_CELL, 64)
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND + 5 * TICK)
                .buildAndRegister()

            // PE Plastic Can
            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_CELL)
                .input(ingot, Polyethylene, 6)
                .output(PE_CAN)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // PTFE Plastic Can
            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_CELL)
                .input(ingot, Polytetrafluoroethylene, 6)
                .output(PTFE_CAN)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // PBI Plastic Can
            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_CELL)
                .input(ingot, Polybenzimidazole, 6)
                .output(PBI_CAN)
                .EUt(VA[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

        }

    }

}