package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
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
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Alumina
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dimethylacetamide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimethylamineHydrochloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dimethylformamide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methylamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MethylamineMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PotassiumFormate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trimethylamine

internal object MethylamineChain
{

    // @formatter:off

    fun init()
    {

        MIXER_RECIPES.recipeBuilder()
            .input(dust, Kyanite)
            .fluidInputs(Methanol.getFluid(2000))
            .fluidInputs(Ammonia.getFluid(1000))
            .fluidOutputs(MethylamineMixture.getFluid(3000))
            .EUt(VA[HV])
            .duration(50 * SECOND)
            .buildAndRegister()

        MIXER_RECIPES.recipeBuilder()
            .input(dust, Alumina)
            .input(dust, SiliconDioxide)
            .fluidInputs(Methanol.getFluid(2000))
            .fluidInputs(Ammonia.getFluid(1000))
            .fluidOutputs(MethylamineMixture.getFluid(3000))
            .EUt(VA[HV])
            .duration(50 * SECOND)
            .buildAndRegister()

        // 6CH3OH + 3NH3 -> CH3NH2 + (CH3)2NH2 + (CH3)3NH2 + 3H2O
        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(MethylamineMixture.getFluid(9000))
            .fluidOutputs(Methylamine.getFluid(1000))
            .fluidOutputs(Dimethylamine.getFluid(1000))
            .fluidOutputs(Trimethylamine.getFluid(1000))
            .fluidOutputs(Water.getFluid(3000))
            .EUt(VA[LuV])
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
            .EUt(VA[HV])
            .duration(12 * SECOND)
            .buildAndRegister()

        // (CH3)2NH + HCl -> C2H8NCl
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Dimethylamine.getFluid(1000))
            .fluidInputs(HydrochloricAcid.getFluid(1000))
            .fluidOutputs(DimethylamineHydrochloride.getFluid(1000))
            .EUt(VA[EV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // CH3OK + C2H8NCl -> KCl + (CH3)2NC(O)H + H + Cl (lost)
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, PotassiumFormate, 6)
            .fluidInputs(DimethylamineHydrochloride.getFluid(1000))
            .output(dust, RockSalt, 2)
            .fluidOutputs(Dimethylformamide.getFluid(1000))
            .fluidOutputs(Hydrogen.getFluid(1000))
            .EUt(VA[EV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Advanced recipe for (CH3)2NC(O)H and (CH3)2NC(O)CH3.

        // C2H7N + CO -> (CH3)2NC(O)H
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(Dimethylamine.getFluid(1000))
            .fluidInputs(CarbonMonoxide.getFluid(1000))
            .fluidOutputs(Dimethylformamide.getFluid(1000))
            .EUt(VA[IV])
            .duration(12 * SECOND)
            .buildAndRegister()

        // C2H7N + C2H4O2 -> (CH3)2NC(O)CH3 + H2O
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .fluidInputs(Dimethylamine.getFluid(1000))
            .fluidInputs(AceticAcid.getFluid(1000))
            .fluidOutputs(Dimethylacetamide.getFluid(1000))
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[LuV])
            .duration(12 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}