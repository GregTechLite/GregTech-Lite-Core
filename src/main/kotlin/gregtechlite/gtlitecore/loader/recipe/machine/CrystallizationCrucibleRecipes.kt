package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.VA
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYSTALLIZATION_RECIPES
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.boule
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.seedCrystal

internal object CrystallizationCrucibleRecipes
{

    fun init()
    {
        // Diamond Boule
        CRYSTALLIZATION_RECIPES.removeRecipe(OreDictUnifier.get(seedCrystal, Diamond), OreDictUnifier.get(dust, Carbon, 4))
        CRYSTALLIZATION_RECIPES.addRecipe {
            input(seedCrystal, Diamond)
            input(dust, Carbon, 64)
            input(dust, Carbon, 64)
            input(dust, Carbon, 64)
            input(dust, Carbon, 64)
            output(boule, Diamond)
            EUt(VA[HV])
            duration(9 * SECOND + 12 * TICK)
            blastFurnaceTemp(1200)
        }
    }

}