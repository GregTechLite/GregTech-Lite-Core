package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing

internal object PCBFactoryCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Infinity Cooling Casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(frameGt, Hypogen)
            .input(rotor, Infinity, 2)
            .input(plate, Thulium, 6)
            .outputs(MultiblockCasing.INFINITY_COOLING_CASING.getStack(2))
            .EUt(VA[UEV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}
