package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UIV
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
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.nugget
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.SU
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.inputs
import gregtechlite.gtlitecore.api.extension.outputs
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.STELLAR_FORGE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ActiniumGroupAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AlkaliEarthGroupAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ChloroauricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyX78
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IncoloyMA813
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumNiobate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitanSteel
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOLDEN_STRAWBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MOON_BERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RAINBOW_BERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SILVER_APPLE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SILVER_STRAWBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.STRAWBERRY
import net.minecraft.init.Items
import net.minecraft.init.Items.APPLE
import net.minecraft.init.Items.CARROT
import net.minecraft.init.Items.GOLDEN_APPLE
import net.minecraft.init.Items.GOLDEN_CARROT
import net.minecraft.item.ItemStack

internal object GoldenFruitsProcessing
{

    // @formatter:off

    fun init()
    {
        // Au + (HNO3)(HCl)2 + 2HCl -> HAuCl4 + NO + 2H2O
        BURNER_REACTOR_RECIPES.addRecipe {
            input(dust, Gold)
            fluidInputs(AquaRegia.getFluid(1000))
            fluidInputs(HydrochloricAcid.getFluid(2000))
            fluidOutputs(ChloroauricAcid.getFluid(1000))
            fluidOutputs(NitricOxide.getFluid(1000))
            fluidOutputs(Steam.getFluid(2 * SU))
            EUt(VA[LV])
            duration(10 * SECOND)
        }

        // Golden Carrot
        ModHandler.removeRecipeByOutput(ItemStack(GOLDEN_CARROT))
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(ItemStack(CARROT),
                    OreDictUnifier.get(nugget, Gold, 8)))

        CHEMICAL_BATH_RECIPES.addRecipe {
            inputs(CARROT)
            fluidInputs(ChloroauricAcid.getFluid(250))
            outputs(GOLDEN_CARROT)
            EUt(VA[LV])
            duration(5 * SECOND)
        }

        // Silver Apple
        CHEMICAL_BATH_RECIPES.addRecipe {
            inputs(APPLE)
            fluidInputs(Silver.getFluid(L))
            output(SILVER_APPLE)
            EUt(VA[LV])
            duration(5 * SECOND)
        }

        // Golden Apple
        ModHandler.removeRecipeByOutput(ItemStack(GOLDEN_APPLE, 1, 0))
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(ItemStack(APPLE),
                    OreDictUnifier.get(ingot, Gold, 8)))

        CHEMICAL_BATH_RECIPES.addRecipe {
            inputs(APPLE)
            fluidInputs(ChloroauricAcid.getFluid(1000))
            outputs(GOLDEN_APPLE)
            EUt(VA[MV])
            duration(5 * SECOND)
        }

        // Enchanted Golden Apple
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(ItemStack(APPLE),
                    OreDictUnifier.get(block, Gold, 8)))

        POLARIZER_RECIPES.addRecipe {
            inputs(GOLDEN_APPLE)
            fluidInputs(ChloroauricAcid.getFluid(4000))
            outputs(GOLDEN_APPLE, metadata = 1)
            EUt(VA[HV])
            duration(5 * SECOND)
        }

        // Silver Strawberry
        CHEMICAL_BATH_RECIPES.addRecipe {
            input(STRAWBERRY)
            fluidInputs(Silver.getFluid(L))
            output(SILVER_STRAWBERRY)
            EUt(VA[EV])
            duration(5 * SECOND)
        }

        // Golden Strawberry
        CHEMICAL_BATH_RECIPES.addRecipe {
            input(STRAWBERRY)
            fluidInputs(ChloroauricAcid.getFluid(16000))
            output(GOLDEN_STRAWBERRY)
            EUt(VA[IV])
            duration(5 * SECOND)
        }

        // Rainbow Berry
        STELLAR_FORGE_RECIPES.addRecipe {
            circuitMeta(1)
            input(GOLDEN_STRAWBERRY)
            input(springSmall, Infinity, 2)
            fluidInputs(TitanSteel.getFluid(L))
            fluidInputs(LithiumNiobate.getFluid(L))
            fluidInputs(HastelloyX78.getFluid(L))
            fluidInputs(IncoloyMA813.getFluid(L))
            fluidInputs(ActiniumGroupAlloyB.getFluid(L))
            fluidInputs(Pikyonium64B.getFluid(L))
            fluidInputs(AlkaliEarthGroupAlloy.getFluid(L))
            output(RAINBOW_BERRY)
            EUt(VA[UEV])
            duration(10 * SECOND)
        }

        STELLAR_FORGE_RECIPES.addRecipe {
            circuitMeta(2)
            input(GOLDEN_STRAWBERRY, 16)
            input(springSmall, Infinity, 32)
            fluidInputs(TitanSteel.getFluid(L * 16))
            fluidInputs(LithiumNiobate.getFluid(L * 16))
            fluidInputs(HastelloyX78.getFluid(L * 16))
            fluidInputs(IncoloyMA813.getFluid(L * 16))
            fluidInputs(ActiniumGroupAlloyB.getFluid(L * 16))
            fluidInputs(Pikyonium64B.getFluid(L * 16))
            fluidInputs(AlkaliEarthGroupAlloy.getFluid(L * 16))
            output(RAINBOW_BERRY, 16)
            EUt(VA[UIV])
            duration(5 * SECOND)
        }

        STELLAR_FORGE_RECIPES.addRecipe {
            circuitMeta(3)
            input(GOLDEN_STRAWBERRY, 64)
            input(springSmall, Infinity, 64)
            fluidInputs(TitanSteel.getFluid(L * 64))
            fluidInputs(LithiumNiobate.getFluid(L * 64))
            fluidInputs(HastelloyX78.getFluid(L * 64))
            fluidInputs(IncoloyMA813.getFluid(L * 64))
            fluidInputs(ActiniumGroupAlloyB.getFluid(L * 64))
            fluidInputs(Pikyonium64B.getFluid(L * 64))
            fluidInputs(AlkaliEarthGroupAlloy.getFluid(L * 64))
            output(RAINBOW_BERRY, 64)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Moon Berry
        AUTOCLAVE_RECIPES.addRecipe {
            input(RAINBOW_BERRY)
            fluidInputs(Eternity.getFluid(L))
            output(MOON_BERRY)
            EUt(VA[UXV])
            duration(10 * SECOND)
        }
    }

    // @formatter:on

}