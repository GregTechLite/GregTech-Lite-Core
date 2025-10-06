package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.POLARIZER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.AquaRegia
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.NitricOxide
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.nugget
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChloroauricAcid
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.SU
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.getFluid
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOLDEN_STRAWBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MOON_BERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.STRAWBERRY
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object GoldenFruitsProcessing
{

    // @formatter:off

    fun init()
    {
        // Au + (HNO3)(HCl)2 + 2HCl -> HAuCl4 + NO + 2H2O
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Gold)
            .fluidInputs(AquaRegia.getFluid(1000))
            .fluidInputs(HydrochloricAcid.getFluid(2000))
            .fluidOutputs(ChloroauricAcid.getFluid(1000))
            .fluidOutputs(NitricOxide.getFluid(1000))
            .fluidOutputs(Steam.getFluid(2 * SU))
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Golden Carrot
        ModHandler.removeRecipeByOutput(ItemStack(Items.GOLDEN_CARROT))
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(ItemStack(Items.CARROT),
                OreDictUnifier.get(nugget, Gold, 8)))

        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .inputs(ItemStack(Items.CARROT))
            .fluidInputs(ChloroauricAcid.getFluid(250))
            .outputs(ItemStack(Items.GOLDEN_CARROT))
            .EUt(VA[LV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Golden Apple
        ModHandler.removeRecipeByOutput(ItemStack(Items.GOLDEN_APPLE, 1, 0))
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(ItemStack(Items.APPLE),
                OreDictUnifier.get(ingot, Gold, 8)))

        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .inputs(ItemStack(Items.APPLE))
            .fluidInputs(ChloroauricAcid.getFluid(1000))
            .outputs(ItemStack(Items.GOLDEN_APPLE, 1, 0))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Enchanted Golden Apple
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(ItemStack(Items.APPLE),
                OreDictUnifier.get(block, Gold, 8)))

        POLARIZER_RECIPES.recipeBuilder()
            .inputs(ItemStack(Items.GOLDEN_APPLE, 1, 0))
            .fluidInputs(ChloroauricAcid.getFluid(4000))
            .outputs(ItemStack(Items.GOLDEN_APPLE, 1, 1))
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Golden Strawberry
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(STRAWBERRY)
            .fluidInputs(ChloroauricAcid.getFluid(16000))
            .output(GOLDEN_STRAWBERRY)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Moon Berry
        AUTOCLAVE_RECIPES.recipeBuilder()
            .input(GOLDEN_STRAWBERRY)
            .fluidInputs(Eternity.getFluid(L))
            .output(MOON_BERRY)
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}