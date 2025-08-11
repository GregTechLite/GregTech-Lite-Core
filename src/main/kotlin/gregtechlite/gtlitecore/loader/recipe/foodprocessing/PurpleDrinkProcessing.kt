package gregtechlite.gtlitecore.loader.recipe.foodprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.RecipeMaps.MACERATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Dimethylamine
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.HydrogenSulfide
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Propene
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.common.items.MetaItems.BOTTLE_PURPLE_DRINK
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Aniline
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Codeine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CoughSyrup
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Etirps
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IsopropylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phenothiazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhenothiazinePropylChloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Promethazine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PurpleDrink
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.HARD_APPLE_CANDY_DUST
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.POPPY_DUST
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object PurpleDrinkProcessing
{

    // @formatter:off

    fun init()
    {
        // C6H5NH2 + H2S + 6O -> C12H9NS + 6CO
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .fluidInputs(Aniline.getFluid(1000))
            .fluidInputs(HydrogenSulfide.getFluid(1000))
            .fluidInputs(Oxygen.getFluid(6000))
            .output(dust, Phenothiazine, 23)
            .fluidOutputs(CarbonMonoxide.getFluid(6000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // C3H6 + HCl -> (CH3)2CHCl
        CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .fluidInputs(Propene.getFluid(1000))
            .fluidInputs(HydrochloricAcid.getFluid(1000))
            .fluidOutputs(IsopropylChloride.getFluid(1000))
            .EUt(VA[LV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // C12H9NS + (CH3)2CHCl -> C15H14NSCl + 2H
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Phenothiazine, 23)
            .fluidInputs(IsopropylChloride.getFluid(1000))
            .fluidOutputs(PhenothiazinePropylChloride.getFluid(1000))
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VH[HV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // C15H14HSCl + C2H7N -> C17H20N2S + HCl
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, Copper)
            .fluidInputs(PhenothiazinePropylChloride.getFluid(1000))
            .fluidInputs(Dimethylamine.getFluid(1000))
            .output(dust, Promethazine, 40)
            .fluidOutputs(HydrochloricAcid.getFluid(1000))
            .EUt(VA[HV])
            .duration(12 * SECOND)
            .buildAndRegister()

        // Poppy dust.
        MACERATOR_RECIPES.recipeBuilder()
            .inputs(ItemStack(Blocks.RED_FLOWER))
            .outputs(POPPY_DUST.itemStack)
            .EUt(4) // ULV
            .duration(1 * SECOND)
            .buildAndRegister()

        // Poppy dust -> C18H21NO3
        EXTRACTOR_RECIPES.recipeBuilder()
            .inputs(POPPY_DUST.getItemStack(64))
            .output(dust, Codeine, 43)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // C17H20N2S + C18H21NO3 + H2O -> (C17H20N2S)(C18H21NO3)(H2O)
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Promethazine, 40)
            .input(dust, Codeine, 43)
            .fluidInputs(Water.getFluid(1000))
            .fluidOutputs(CoughSyrup.getFluid(1000))
            .EUt(VA[MV])
            .duration(3 * SECOND)
            .buildAndRegister()

        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Promethazine, 40)
            .input(dust, Codeine, 43)
            .fluidInputs(DistilledWater.getFluid(1000))
            .fluidOutputs(CoughSyrup.getFluid(1000))
            .EUt(VA[MV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // Purple Drink
        MIXER_RECIPES.recipeBuilder()
            .inputs(HARD_APPLE_CANDY_DUST.itemStack)
            .fluidInputs(CoughSyrup.getFluid(500))
            .fluidInputs(Etirps.getFluid(1000))
            .fluidOutputs(PurpleDrink.getFluid(1000))
            .EUt(VA[HV])
            .duration(2 * SECOND)
            .buildAndRegister()

        CANNER_RECIPES.recipeBuilder()
            .inputs(ItemStack(Items.GLASS_BOTTLE))
            .fluidInputs(PurpleDrink.getFluid(250))
            .output(BOTTLE_PURPLE_DRINK)
            .EUt(4) // ULV
            .duration(10 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}