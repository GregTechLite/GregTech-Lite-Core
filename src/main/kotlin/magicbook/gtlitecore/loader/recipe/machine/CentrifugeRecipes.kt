package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.DarkAsh
import gregtech.api.unification.material.Materials.Flint
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Magnesite
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Quicklime
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Sodalite
import gregtech.api.unification.material.Materials.Talc
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.dustTiny
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Albite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Augite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Azurite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BlueSchist
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Clinochlore
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dolomite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Forsterite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GreenSchist
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kimberlite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Komatiite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Limestone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Lizardite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Muscovite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Slate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tanzanite
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

class CentrifugeRecipes
{

    companion object
    {

        fun init()
        {

            // Limestone decomposition.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Limestone, 6)
                .output(dust, Calcite, 4)
                .output(dust, Dolomite, 1)
                .chancedOutput(dustSmall, Quicklime, 2, 2750, 850)
                .EUt(VA[LV].toLong())
                .duration(9 * SECOND + 9 * TICK)
                .buildAndRegister()

            // Komatiite decomposition.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Komatiite, 4)
                .output(dust, Olivine, 2)
                .output(dust, Magnesite, 1)
                .output(dustSmall, Flint, 2)
                .output(dustTiny, DarkAsh, 3)
                .EUt(VA[LV].toLong())
                .duration(6 * SECOND + 18 * TICK)
                .buildAndRegister()

            // Green Schist decomposition.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, GreenSchist, 6)
                .output(dust, Tanzanite, 2)
                .output(dust, Quartzite, 2)
                .output(dust, Talc, 1)
                .chancedOutput(dustSmall, Calcite, 3, 750, 250)
                .EUt(VA[LV].toLong())
                .duration(8 * SECOND + 11 * TICK)
                .buildAndRegister()

            // Blue Schist decomposition.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, BlueSchist, 6)
                .output(dust, Azurite, 3)
                .output(dust, Sodalite, 2)
                .chancedOutput(dustTiny, Iron, 7, 1650, 350)
                .EUt(VA[LV].toLong())
                .duration(12 * SECOND + 12 * TICK)
                .buildAndRegister()

            // Kimberlite decomposition.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Kimberlite, 9)
                .output(dust, Forsterite, 3)
                .output(dust, Augite, 3)
                .output(dust, Andradite, 2)
                .output(dustSmall, Lizardite, 3)
                .EUt(VA[LV].toLong())
                .duration(14 * SECOND + 4 * TICK)
                .buildAndRegister()

            // Slate decomposition.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Slate, 10)
                .output(dust, SiliconDioxide, 5)
                .output(dust, Muscovite, 2)
                .output(dust, Clinochlore, 2)
                .output(dustSmall, Albite, 3)
                .EUt(VA[LV].toLong())
                .duration(12 * SECOND + 15 * TICK)
                .buildAndRegister()

        }

    }

}