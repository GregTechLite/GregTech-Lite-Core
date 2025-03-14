package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Cobalt
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kovar
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

class MixerRecipes
{

    companion object
    {

        fun init()
        {
            // Kovar
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Invar, 15)
                .input(dust, Cobalt, 3)
                .output(dust, Kovar, 18)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Iron, 10)
                .input(dust, Nickel, 5)
                .input(dust, Cobalt, 3)
                .output(dust, Kovar, 18)
                .EUt(VH[LV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

        }

    }

}