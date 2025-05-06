package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Dimethylamine
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Kyanite
import gregtech.api.unification.material.Materials.Methanol
import gregtech.api.unification.material.Materials.RockSalt
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Alumina
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DimethylamineHydrochloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dimethylformamide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Methylamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MethylamineMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumFormate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trimethylamine
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class MethylamineChain
{

    companion object
    {

        fun init()
        {

            MIXER_RECIPES.recipeBuilder()
                .input(dust, Kyanite)
                .fluidInputs(Methanol.getFluid(2000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(MethylamineMixture.getFluid(3000))
                .EUt(VA[HV].toLong())
                .duration(50 * SECOND)
                .buildAndRegister()

            MIXER_RECIPES.recipeBuilder()
                .input(dust, Alumina)
                .input(dust, SiliconDioxide)
                .fluidInputs(Methanol.getFluid(2000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(MethylamineMixture.getFluid(3000))
                .EUt(VA[HV].toLong())
                .duration(50 * SECOND)
                .buildAndRegister()

            // 6CH3OH + 3NH3 -> CH3NH2 + (CH3)2NH2 + (CH3)3NH2 + 3H2O
            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(MethylamineMixture.getFluid(9000))
                .fluidOutputs(Methylamine.getFluid(1000))
                .fluidOutputs(Dimethylamine.getFluid(1000))
                .fluidOutputs(Trimethylamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(VA[LuV].toLong())
                .duration(50 * SECOND)
                .disableDistilleryRecipes()
                .buildAndRegister()

            // DMF process.

            // KCl + CH4O -> CH3OK + HCl
            ROASTER_RECIPES.recipeBuilder()
                .input(dust, RockSalt, 2)
                .fluidInputs(Methanol.getFluid(1000))
                .output(dust, PotassiumFormate, 6)
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            // (CH3)2NH + HCl -> C2H8NCl
            MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Dimethylamine.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(DimethylamineHydrochloride.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // CH3OK + C2H8NCl -> KCl + (CH3)2NC(O)H + H + Cl (lost)
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, PotassiumFormate, 6)
                .fluidInputs(DimethylamineHydrochloride.getFluid(1000))
                .output(dust, RockSalt, 2)
                .fluidOutputs(Dimethylformamide.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // Advanced recipe for (CH3)2NC(O)H.

            // C2H8NCl + CO -> (CH3)2NC(O)H
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(Dimethylamine.getFluid(1000))
                .fluidInputs(CarbonMonoxide.getFluid(1000))
                .fluidOutputs(Dimethylformamide.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

        }

    }

}