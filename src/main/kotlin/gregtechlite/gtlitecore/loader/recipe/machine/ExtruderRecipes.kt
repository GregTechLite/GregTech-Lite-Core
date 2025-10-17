package gregtechlite.gtlitecore.loader.recipe.machine

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
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kevlar
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.KEVLAR_CAN
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.PBI_CAN
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.PE_CAN
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.PTFE_CAN

internal object ExtruderRecipes
{

    // @formatter:off

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
            .EUt(VA[LV])
            .duration(6 * SECOND + 5 * TICK)
            .buildAndRegister()

        EXTRUDER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_EXTRUDER_CELL)
            .input(ingot, StainlessSteel)
            .output(FLUID_CELL, 16)
            .EUt(VA[LV])
            .duration(6 * SECOND + 5 * TICK)
            .buildAndRegister()

        EXTRUDER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_EXTRUDER_CELL)
            .input(ingot, Titanium)
            .output(FLUID_CELL, 32)
            .EUt(VA[LV])
            .duration(6 * SECOND + 5 * TICK)
            .buildAndRegister()

        EXTRUDER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_EXTRUDER_CELL)
            .input(ingot, TungstenSteel)
            .output(FLUID_CELL, 64)
            .EUt(VA[LV])
            .duration(6 * SECOND + 5 * TICK)
            .buildAndRegister()

        // PE Plastic Can
        EXTRUDER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_EXTRUDER_CELL)
            .input(ingot, Polyethylene, 6)
            .output(PE_CAN)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // PTFE Plastic Can
        EXTRUDER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_EXTRUDER_CELL)
            .input(ingot, Polytetrafluoroethylene, 6)
            .output(PTFE_CAN)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // PBI Plastic Can
        EXTRUDER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_EXTRUDER_CELL)
            .input(ingot, Polybenzimidazole, 6)
            .output(PBI_CAN)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Kevlar Plastic Can
        EXTRUDER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_EXTRUDER_CELL)
            .input(ingot, Kevlar, 6)
            .output(KEVLAR_CAN)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}