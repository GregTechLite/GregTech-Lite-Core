package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.COMPRESSOR_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Charcoal
import gregtech.api.unification.material.Materials.Coal
import gregtech.api.unification.material.Materials.Concrete
import gregtech.api.unification.material.Materials.FermentedBiomass
import gregtech.api.unification.material.Materials.Oil
import gregtech.api.unification.material.Materials.OilHeavy
import gregtech.api.unification.material.Materials.OilLight
import gregtech.api.unification.material.Materials.RawOil
import gregtech.api.unification.material.Materials.SulfuricGas
import gregtech.api.unification.material.Materials.SulfuricHeavyFuel
import gregtech.api.unification.material.Materials.SulfuricLightFuel
import gregtech.api.unification.material.Materials.SulfuricNaphtha
import gregtech.api.unification.material.Materials.Wood
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.blocks.MetaBlocks
import gregtech.common.blocks.wood.BlockGregPlanks
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BIO_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bitumen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FulvicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kerogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Paraffin
import net.minecraft.item.ItemStack

internal object KerogenChain
{

    // @formatter:off

    fun init()
    {
        // Fermented Biomass -> C14H12O8
        BIO_REACTOR_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(FermentedBiomass.getFluid(1000))
            .fluidOutputs(FulvicAcid.getFluid(1000))
            .EUt(VA[LV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // C14H12O8 -> Kerogen
        FLUID_HEATER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(FulvicAcid.getFluid(100))
            .fluidOutputs(Kerogen.getFluid(100))
            .EUt(VA[LV])
            .duration(4 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Kerogen -> Oil
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, Wood)
            .fluidInputs(Kerogen.getFluid(250))
            .fluidOutputs(Oil.getFluid(500))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Light Oil distillation
        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            OilLight.getFluid(150))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(OilLight.getFluid(150)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(OilLight.getFluid(150)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
            arrayOf(OilLight.getFluid(150)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(4)),
            arrayOf(OilLight.getFluid(30)))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(OilLight.getFluid(150))
            .chancedOutput(dust, Paraffin, 1500, 250)
            .fluidOutputs(SulfuricHeavyFuel.getFluid(10))
            .fluidOutputs(SulfuricLightFuel.getFluid(20))
            .fluidOutputs(SulfuricNaphtha.getFluid(30))
            .fluidOutputs(SulfuricGas.getFluid(240))
            .EUt(96) // MV
            .duration(1 * SECOND)
            .buildAndRegister()

        // Medium Oil
        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            RawOil.getFluid(100))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(RawOil.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(RawOil.getFluid(50)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
            arrayOf(RawOil.getFluid(50)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(4)),
            arrayOf(RawOil.getFluid(50)))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(RawOil.getFluid(100))
            .chancedOutput(dust, Paraffin, 2500, 500)
            .fluidOutputs(SulfuricHeavyFuel.getFluid(10))
            .fluidOutputs(SulfuricLightFuel.getFluid(50))
            .fluidOutputs(SulfuricNaphtha.getFluid(150))
            .fluidOutputs(SulfuricGas.getFluid(60))
            .EUt(96) // MV
            .duration(1 * SECOND)
            .buildAndRegister()

        // Oil
        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            Oil.getFluid(50))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
             arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
             arrayOf(Oil.getFluid(50)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Oil.getFluid(25)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
            arrayOf(Oil.getFluid(50)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(4)),
            arrayOf(Oil.getFluid(25)))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(Oil.getFluid(50))
            .chancedOutput(dust, Bitumen, 3000, 500)
            .fluidOutputs(SulfuricHeavyFuel.getFluid(15))
            .fluidOutputs(SulfuricLightFuel.getFluid(50))
            .fluidOutputs(SulfuricNaphtha.getFluid(20))
            .fluidOutputs(SulfuricGas.getFluid(60))
            .EUt(96) // MV
            .duration(1 * SECOND)
            .buildAndRegister()

        // Heavy Oil
        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
                                              OilHeavy.getFluid(100))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(OilHeavy.getFluid(50)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(OilHeavy.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
            arrayOf(OilHeavy.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(4)),
            arrayOf(OilHeavy.getFluid(50)))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(OilHeavy.getFluid(100))
            .chancedOutput(dust, Bitumen, 5000, 1000)
            .fluidOutputs(SulfuricHeavyFuel.getFluid(250))
            .fluidOutputs(SulfuricLightFuel.getFluid(45))
            .fluidOutputs(SulfuricNaphtha.getFluid(15))
            .fluidOutputs(SulfuricGas.getFluid(60))
            .EUt(96) // MV, original recipe's EUt required is HV (288 EU/t), we down-tier this recipe.
            .duration(1 * SECOND)
            .buildAndRegister()

        GTLiteRecipeHandler.removeMixerRecipes(
            arrayOf(OreDictUnifier.get(dust, Coal)),
            arrayOf(Concrete.getFluid(L)))

        GTLiteRecipeHandler.removeMixerRecipes(
            arrayOf(OreDictUnifier.get(dust, Charcoal)),
            arrayOf(Concrete.getFluid(L)))

        GTLiteRecipeHandler.removeMixerRecipes(
            arrayOf(OreDictUnifier.get(dust, Carbon)),
            arrayOf(Concrete.getFluid(L)))

        COMPRESSOR_RECIPES.recipeBuilder()
            .input(dust, Bitumen, 4)
            .outputs(ItemStack(MetaBlocks.ASPHALT))
            .EUt(VA[ULV])
            .duration(5 * TICK)
            .buildAndRegister()

        // Treated Wood
        ModHandler.addShapedRecipe(false, "treated_wood_planks", MetaBlocks.PLANKS.getItemVariant(BlockGregPlanks.BlockType.TREATED_PLANK, 8),
            "PPP", "PBP", "PPP",
            'P', "plankWood",
            'B', UnificationEntry(dust, Bitumen))

    }

    // @formatter:on

}