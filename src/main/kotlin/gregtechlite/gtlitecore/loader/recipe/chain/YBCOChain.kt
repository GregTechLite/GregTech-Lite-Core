package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.BariumSulfide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.NitrogenDioxide
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Yttrium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CitricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CopperNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cuprite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tenorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.YttriumBariumCopperOxidesMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.YttriumNitrate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.YttriumOxide
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.SU
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler

internal object YBCOChain
{

    // @formatter:off

    fun init()
    {
        // Remove original YBCO dust recipe.
        GTLiteRecipeHandler.removeMixerRecipes(
            arrayOf(
                OreDictUnifier.get(dust, Yttrium),
                OreDictUnifier.get(dust, Barium, 2),
                OreDictUnifier.get(dust, Copper, 3),
                IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Oxygen.getFluid(7000)))

        // Y2O3 + 6HNO3 -> 2Y(NO3)3 + 3H2O
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(dust, YttriumOxide, 5)
            .fluidInputs(NitricAcid.getFluid(6000))
            .output(dust, YttriumNitrate, 26)
            .fluidOutputs(Water.getFluid(3000))
            .EUt(VA[EV])
            .duration(12 * SECOND)
            .buildAndRegister()

        // Add another recipe of BaS because it is the semi-products of Naquadah Processing.
        // Ba + S -> BaS
        ROASTER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Barium)
            .input(dust, Sulfur)
            .output(dust, BariumSulfide, 2)
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // BaS + 2HNO3 -> Ba(NO3)2 + H2S
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, BariumSulfide, 2)
            .fluidInputs(NitricAcid.getFluid(2000))
            .output(dust, BariumNitrate, 9)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Cu + 2HNO3 -> Cu(NO3)2 + 2H
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Copper)
            .fluidInputs(NitricAcid.getFluid(2000))
            .output(dust, CopperNitrate, 9)
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[MV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // CuO + 2HNO3 -> Cu(NO3)2 + H2O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Tenorite, 2)
            .fluidInputs(NitricAcid.getFluid(2000))
            .output(dust, CopperNitrate, 9)
            .fluidOutputs(Steam.getFluid(1 * SU))
            .EUt(VA[MV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // Cu2O + 4HNO3 -> 2Cu(NO3)2 + H2O + 2H (drop)
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Cuprite, 3)
            .fluidInputs(NitricAcid.getFluid(4000))
            .output(dust, CopperNitrate, 18)
            .EUt(VA[MV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // Y(NO3)3 + 2Ba(NO3)2 + 3Cu(NO3)2 + 2NH3 + C6H8O7 -> YBa2Cu3O6 + 15NO2 + 6CO + 4H2O + 6H
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, YttriumNitrate, 13)
            .input(dust, BariumNitrate, 18)
            .input(dust, CopperNitrate, 27)
            .fluidInputs(Ammonia.getFluid(2000))
            .fluidInputs(CitricAcid.getFluid(1000))
            .output(dust, YttriumBariumCopperOxidesMixture, 12)
            .fluidOutputs(NitrogenDioxide.getFluid(15000))
            .fluidOutputs(CarbonMonoxide.getFluid(6000))
            .fluidOutputs(Water.getFluid(4000))
            .fluidOutputs(Hydrogen.getFluid(6000))
            .EUt(VA[IV])
            .duration(12 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // YBa2Cu3O6 + O -> YBa2Cu3O7
        ARC_FURNACE_RECIPES.recipeBuilder()
            .input(dust, YttriumBariumCopperOxidesMixture, 12)
            .fluidInputs(Oxygen.getFluid(1000))
            .output(ingotHot, YttriumBariumCuprate, 13)
            .EUt(VA[IV])
            .duration(2 * MINUTE + 5 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}