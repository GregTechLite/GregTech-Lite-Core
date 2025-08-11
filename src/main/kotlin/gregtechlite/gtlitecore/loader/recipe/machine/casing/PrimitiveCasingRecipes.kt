package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.TreatedWood
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.stack.UnificationEntry
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.variant.PrimitiveCasing

internal object PrimitiveCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Reinforced Treated Wood Wall
        ModHandler.addShapedRecipe(true, "reinforced_treated_wood_wall", PrimitiveCasing.REINFORCED_TREATED_WOOD_WALL.getStack(2),
            "PhP", "QFQ", "PwP",
            'P', UnificationEntry(plate, TreatedWood),
            'Q', UnificationEntry(plate, Steel),
            'F', UnificationEntry(frameGt, TreatedWood))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(plate, TreatedWood, 4)
            .input(plate, Steel, 2)
            .input(frameGt, TreatedWood)
            .outputs(PrimitiveCasing.REINFORCED_TREATED_WOOD_WALL.getStack(2))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    // @formatter:on

}