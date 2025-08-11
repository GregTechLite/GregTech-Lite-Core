package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Calcite
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.DarkAsh
import gregtech.api.unification.material.Materials.Flint
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Magnesite
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Quicklime
import gregtech.api.unification.material.Materials.Saltpeter
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Sodalite
import gregtech.api.unification.material.Materials.Talc
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Albite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Augite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Azurite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlueSchist
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Clinochlore
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dolomite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fluorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Forsterite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GreenSchist
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kimberlite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Komatiite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Limestone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lizardite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Muscovite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shale
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Slate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tanzanite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZephyreanAerotheum
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SAND_DUST

internal object CentrifugeRecipes
{

    // @formatter:off

    fun init()
    {

        // Limestone decomposition.
        CENTRIFUGE_RECIPES.recipeBuilder()
            .input(dust, Limestone, 6)
            .output(dust, Calcite, 4)
            .output(dust, Dolomite, 1)
            .chancedOutput(dustSmall, Quicklime, 2, 2750, 850)
            .EUt(VA[LV])
            .duration(9 * SECOND + 9 * TICK)
            .buildAndRegister()

        // Komatiite decomposition.
        CENTRIFUGE_RECIPES.recipeBuilder()
            .input(dust, Komatiite, 4)
            .output(dust, Olivine, 2)
            .output(dust, Magnesite, 1)
            .output(dustSmall, Flint, 2)
            .output(dustTiny, DarkAsh, 3)
            .EUt(VA[LV])
            .duration(6 * SECOND + 18 * TICK)
            .buildAndRegister()

        // Green Schist decomposition.
        CENTRIFUGE_RECIPES.recipeBuilder()
            .input(dust, GreenSchist, 6)
            .output(dust, Tanzanite, 2)
            .output(dust, Quartzite, 2)
            .output(dust, Talc, 1)
            .chancedOutput(dustSmall, Calcite, 3, 750, 250)
            .EUt(VA[LV])
            .duration(8 * SECOND + 11 * TICK)
            .buildAndRegister()

        // Blue Schist decomposition.
        CENTRIFUGE_RECIPES.recipeBuilder()
            .input(dust, BlueSchist, 6)
            .output(dust, Azurite, 3)
            .output(dust, Sodalite, 2)
            .chancedOutput(dustTiny, Iron, 7, 1650, 350)
            .EUt(VA[LV])
            .duration(12 * SECOND + 12 * TICK)
            .buildAndRegister()

        // Kimberlite decomposition.
        CENTRIFUGE_RECIPES.recipeBuilder()
            .input(dust, Kimberlite, 9)
            .output(dust, Forsterite, 3)
            .output(dust, Augite, 3)
            .output(dust, Andradite, 2)
            .output(dustSmall, Lizardite, 3)
            .EUt(VA[LV])
            .duration(14 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Slate decomposition.
        CENTRIFUGE_RECIPES.recipeBuilder()
            .input(dust, Slate, 10)
            .output(dust, SiliconDioxide, 5)
            .output(dust, Muscovite, 2)
            .output(dust, Clinochlore, 2)
            .output(dustSmall, Albite, 3)
            .EUt(VA[LV])
            .duration(12 * SECOND + 15 * TICK)
            .buildAndRegister()

        // Shale decomposition.
        CENTRIFUGE_RECIPES.recipeBuilder()
            .input(dust, Shale, 10)
            .output(dust, Calcite, 6)
            .output(dust, Clay, 2)
            .output(dust, SiliconDioxide, 1)
            .output(dustSmall, Fluorite, 3)
            .EUt(VA[LV])
            .duration(14 * SECOND + 5 * TICK)
            .buildAndRegister()

        // Zephyrean Aerotheum decomposition.
        CENTRIFUGE_RECIPES.recipeBuilder()
            .input(dust, ZephyreanAerotheum, 4)
            .output(SAND_DUST, 2)
            .output(dust, Saltpeter)
            .fluidOutputs(Air.getFluid(1000))
            .EUt(VA[LV])
            .duration(12 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}