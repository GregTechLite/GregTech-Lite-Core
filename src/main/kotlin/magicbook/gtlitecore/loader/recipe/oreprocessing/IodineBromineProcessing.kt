package magicbook.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.SIFTER_RECIPES
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Iodine
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SaltWater
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.nugget
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AcidicSaltWater
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SeaWater
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class IodineBromineProcessing
{

    companion object
    {

        fun init()
        {
            // Dehydring Sea Water to Salt Water.
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(SeaWater.getFluid(1000))
                .chancedOutput(dust, Salt, 1000, 500)
                .fluidOutputs(SaltWater.getFluid(1000))
                .EUt(VA[LV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // Acidification of salt water.
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(SaltWater.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(AcidicSaltWater.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Sifting it to get Bromine and Iodine.
            SIFTER_RECIPES.recipeBuilder()
                .fluidInputs(AcidicSaltWater.getFluid(1000))
                .output(dust, Iodine)
                .chancedOutput(dustSmall, Calcium, 1000, 200)
                .chancedOutput(dustTiny, Sodium, 500, 50)
                .chancedOutput(nugget, Iron, 250, 25)
                .fluidOutputs(Bromine.getFluid(250))
                .fluidOutputs(SulfuricAcid.getFluid(500))
                .EUt(VA[MV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

        }

    }

}