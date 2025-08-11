package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.COMBUSTION_GENERATOR_FUELS
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.AluminiumSulfite
import gregtech.api.unification.material.Materials.Benzene
import gregtech.api.unification.material.Materials.Butane
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Dimethylbenzene
import gregtech.api.unification.material.Materials.Ethane
import gregtech.api.unification.material.Materials.Ethanol
import gregtech.api.unification.material.Materials.Ethylbenzene
import gregtech.api.unification.material.Materials.Ethylene
import gregtech.api.unification.material.Materials.HeavyFuel
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.HydroCrackedButadiene
import gregtech.api.unification.material.Materials.HydroCrackedButane
import gregtech.api.unification.material.Materials.HydroCrackedEthane
import gregtech.api.unification.material.Materials.HydroCrackedEthylene
import gregtech.api.unification.material.Materials.HydroCrackedPropane
import gregtech.api.unification.material.Materials.HydroCrackedPropene
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Isoprene
import gregtech.api.unification.material.Materials.LightFuel
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Naphtha
import gregtech.api.unification.material.Materials.NaturalGas
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Propane
import gregtech.api.unification.material.Materials.Propene
import gregtech.api.unification.material.Materials.RefineryGas
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.SteamCrackedButadiene
import gregtech.api.unification.material.Materials.SteamCrackedButane
import gregtech.api.unification.material.Materials.SteamCrackedEthane
import gregtech.api.unification.material.Materials.SteamCrackedEthylene
import gregtech.api.unification.material.Materials.SteamCrackedPropane
import gregtech.api.unification.material.Materials.SteamCrackedPropene
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Toluene
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.common.items.MetaItems.SHAPE_MOLD_PLATE
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CATALYTIC_REFORMER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AluminiumSulfate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Carbon5Fraction
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dicyclopentadiene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DimerizedCarbon5Fraction
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ParaXylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pentane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZSM5
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler

internal object OilsChain
{

    // @formatter:off

    fun init()
    {
        ethaneCrackProcessing()
        ethyleneCrackProcessing()
        propaneCrackProcessing()
        propeneCrackProcessing()
        butaneCrackProcessing()
        butadieneCrackProcessing()
        carbon5FractionProcessing()
        oilFuelRecipes()
        catalyticReforming()

        // Natural gas distillation addition.
        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(NaturalGas.getFluid(1000))
            .fluidOutputs(Methane.getFluid(650)) // CH4
            .fluidOutputs(Ethane.getFluid(128)) // C2H6
            .fluidOutputs(Propane.getFluid(128)) // C3H8
            .fluidOutputs(Butane.getFluid(64)) // C4H10
            .fluidOutputs(Helium.getFluid(24))
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()
    }

    private fun ethaneCrackProcessing()
    {
        // Deleted vanilla hydrogen cracked ethane recipes.
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(HydroCrackedEthane.getFluid(40)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(HydroCrackedEthane.getFluid(40)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            HydroCrackedEthane.getFluid(1000))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(HydroCrackedEthane.getFluid(1000))
            .fluidOutputs(Carbon5Fraction.getFluid(250))
            .fluidOutputs(Methane.getFluid(1750)) // CH4
            .fluidOutputs(Hydrogen.getFluid(2000)) // H
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Deleted vanilla steam cracked ethane recipes.
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(SteamCrackedEthane.getFluid(100)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(SteamCrackedEthane.getFluid(40)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            SteamCrackedEthane.getFluid(1000))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(SteamCrackedEthane.getFluid(1000))
            .chancedOutput(dust, Carbon, 2500, 0)
            .fluidOutputs(Carbon5Fraction.getFluid(125))
            .fluidOutputs(Ethylene.getFluid(250)) // C2H4
            .fluidOutputs(Methane.getFluid(1125)) // CH4
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()
    }

    private fun ethyleneCrackProcessing()
    {
        // Deleted vanilla hydrogen cracked ethylene recipes.
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(HydroCrackedEthylene.getFluid(40)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            HydroCrackedEthylene.getFluid(1000))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(HydroCrackedEthylene.getFluid(1000))
            .fluidOutputs(Carbon5Fraction.getFluid(200))
            .fluidOutputs(Ethane.getFluid(8000)) // C2H6
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Deleted vanilla steam cracked ethylene recipes.
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(SteamCrackedEthylene.getFluid(1000)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            SteamCrackedEthylene.getFluid(1000))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(SteamCrackedEthylene.getFluid(1000))
            .output(dust, Carbon)
            .fluidOutputs(Carbon5Fraction.getFluid(200))
            .fluidOutputs(Methane.getFluid(800))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

    }

    private fun propaneCrackProcessing()
    {
        // Deleted vanilla hydrogen cracked propane recipes.
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(HydroCrackedPropane.getFluid(40)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(HydroCrackedPropane.getFluid(40)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            HydroCrackedPropane.getFluid(1000))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(HydroCrackedPropane.getFluid(1000))
            .fluidOutputs(Carbon5Fraction.getFluid(250))
            .fluidOutputs(Ethane.getFluid(875)) // C2H6
            .fluidOutputs(Methane.getFluid(875)) // CH4
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Deleted vanilla steam cracked propane recipes.
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(SteamCrackedPropane.getFluid(40)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(SteamCrackedPropane.getFluid(40)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            SteamCrackedPropane.getFluid(1000))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(SteamCrackedPropane.getFluid(1000))
            .chancedOutput(dust, Carbon, 2500, 0)
            .fluidOutputs(Carbon5Fraction.getFluid(250))
            .fluidOutputs(Ethylene.getFluid(750)) // C2H4
            .fluidOutputs(Methane.getFluid(1000)) // CH4
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()
    }

    private fun propeneCrackProcessing()
    {
        // Deleted vanilla hydrogen cracked propene recipes.
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(HydroCrackedPropene.getFluid(100)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(HydroCrackedPropene.getFluid(100)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
            arrayOf(HydroCrackedPropene.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            HydroCrackedPropene.getFluid(1000))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(HydroCrackedPropene.getFluid(1000))
            .fluidOutputs(Carbon5Fraction.getFluid(250))
            .fluidOutputs(Propane.getFluid(250)) // C3H8
            .fluidOutputs(Ethylene.getFluid(250)) // C2H4
            .fluidOutputs(Methane.getFluid(250)) // CH4
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Deleted vanilla steam cracked ethylene recipes.
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(SteamCrackedPropene.getFluid(40)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(SteamCrackedPropene.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            SteamCrackedPropene.getFluid(1000))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(SteamCrackedPropene.getFluid(1000))
            .chancedOutput(dust, Carbon, 5000, 0)
            .fluidOutputs(Carbon5Fraction.getFluid(250))
            .fluidOutputs(Ethylene.getFluid(1000))
            .fluidOutputs(Methane.getFluid(250))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()
    }

    private fun butaneCrackProcessing()
    {
        // Deleted vanilla hydrogen cracked butane recipes.
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(HydroCrackedButane.getFluid(40)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(HydroCrackedButane.getFluid(40)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
            arrayOf(HydroCrackedButane.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            HydroCrackedButane.getFluid(1000))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(HydroCrackedButane.getFluid(1000))
            .fluidOutputs(Carbon5Fraction.getFluid(250))
            .fluidOutputs(Propane.getFluid(750)) // C3H8
            .fluidOutputs(Ethane.getFluid(750)) // C2H6
            .fluidOutputs(Methane.getFluid(250)) // CH4
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Deleted vanilla steam cracked butane recipes.
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(SteamCrackedButane.getFluid(200)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(SteamCrackedButane.getFluid(40)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
            arrayOf(SteamCrackedButane.getFluid(40)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(4)),
            arrayOf(SteamCrackedButane.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            SteamCrackedButane.getFluid(1000))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(SteamCrackedButane.getFluid(1000))
            .chancedOutput(dust, Carbon, 2500, 0)
            .fluidOutputs(Carbon5Fraction.getFluid(125))
            .fluidOutputs(Propane.getFluid(250)) // C3H8
            .fluidOutputs(Ethane.getFluid(750)) // C2H6
            .fluidOutputs(Ethylene.getFluid(750)) // C2H4
            .fluidOutputs(Methane.getFluid(250)) // CH4
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

    }

    private fun butadieneCrackProcessing()
    {
        // Deleted vanilla hydrogen cracked butadiene recipes.
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(HydroCrackedButadiene.getFluid(40)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(HydroCrackedButadiene.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            HydroCrackedButadiene.getFluid(1000))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(HydroCrackedButadiene.getFluid(1000))
            .fluidOutputs(Carbon5Fraction.getFluid(250))
            .fluidOutputs(Butane.getFluid(500)) // C4H8
            .fluidOutputs(Ethylene.getFluid(500)) // C2H4
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Deleted vanilla steam cracked butadiene recipes.
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(SteamCrackedButadiene.getFluid(200)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(SteamCrackedButadiene.getFluid(100)))
        GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
            arrayOf(SteamCrackedButadiene.getFluid(40)))

        GTRecipeHandler.removeRecipesByInputs(DISTILLATION_RECIPES,
            SteamCrackedButadiene.getFluid(1000))

        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(SteamCrackedButadiene.getFluid(1000))
            .chancedOutput(dust, Carbon, 5000, 0)
            .fluidOutputs(Carbon5Fraction.getFluid(125))
            .fluidOutputs(Propene.getFluid(125))
            .fluidOutputs(Ethylene.getFluid(250))
            .fluidOutputs(Methane.getFluid(1000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()
    }

    // C5 Fraction is a petroleum fractionation products in reality world oil
    // cracking processes. We add it to add several recipes for C5H8 recipes.
    private fun carbon5FractionProcessing()
    {
        // Deleted vanilla C5H8 (Isoprene) recipes because we add C5 Fraction recipes
        // for several distillation recipes.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(3)),
            arrayOf(Propene.getFluid(2000)))

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(Ethylene.getFluid(1000),
                    Propene.getFluid(1000)))

        // C5 Fraction -> Dimerized C5 Fraction.
        FLUID_HEATER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .fluidInputs(Carbon5Fraction.getFluid(100))
            .fluidOutputs(DimerizedCarbon5Fraction.getFluid(87))
            .EUt(VA[LV])
            .duration(15 * TICK)
            .buildAndRegister()

        // Dimerized C5 Fraction -> C10H12 + C5H12 + C5H8
        DISTILLATION_RECIPES.recipeBuilder()
            .fluidInputs(DimerizedCarbon5Fraction.getFluid(870))
            .fluidOutputs(Dicyclopentadiene.getFluid(130))
            .fluidOutputs(Pentane.getFluid(380))
            .fluidOutputs(Isoprene.getFluid(360))
            .EUt(VA[LV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // C10H12 -> 2C5H8
        FLUID_HEATER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .fluidInputs(Dicyclopentadiene.getFluid(100))
            .fluidOutputs(Isoprene.getFluid(200))
            .EUt(VA[LV])
            .duration(4 * TICK)
            .buildAndRegister()

    }

    private fun oilFuelRecipes()
    {

        // Pentane
        COMBUSTION_GENERATOR_FUELS.recipeBuilder()
            .fluidInputs(Pentane.getFluid(1))
            .EUt(V[LV])
            .duration(2 * TICK)
            .buildAndRegister()

    }

    private fun catalyticReforming()
    {
        // ZSM5 processing.

        // 2Al + 3H2SO4 -> Al2(SO4)3 + 6H
        ROASTER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Aluminium, 2)
            .fluidInputs(SulfuricAcid.getFluid(3000))
            .output(dust, AluminiumSulfate, 17)
            .fluidOutputs(Hydrogen.getFluid(6000))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // 2Al + 3H2SO4 -> Al2(SO3)3 + 3H2O
        ROASTER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(dust, Aluminium, 2)
            .fluidInputs(SulfuricAcid.getFluid(3000))
            .output(dust, AluminiumSulfite, 14)
            .fluidOutputs(Steam.getFluid(3000))
            .EUt(VA[MV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // NaOH + 3SiO2 + Al2(SO4)3 + H2O -> Na(Al2(SO4)3)(SiO2)2(H2O)2
        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_PLATE)
            .input(dust, SodiumHydroxide, 2)
            .input(dust, AluminiumSulfate, 17)
            .input(dust, SiliconDioxide, 6)
            .fluidInputs(Water.getFluid(1000))
            .notConsumable(Ethanol.getFluid(1))
            .output(plate, ZSM5) // Actually outputs 30, but this is only useful for some recipes.
            .EUt(VA[IV])
            .duration(30 * SECOND)
            .buildAndRegister()

        // ZSM-5 is the highest catalyst of catalytic reforming process, this catalyst
        // can make catalytic reforming product 4x output materials.

        // Light Fuel
        CATALYTIC_REFORMER_RECIPES.recipeBuilder()
            .notConsumable(plate, Platinum)
            .fluidInputs(LightFuel.getFluid(1000))
            .fluidOutputs(Toluene.getFluid(160))
            .fluidOutputs(Benzene.getFluid(200))
            .fluidOutputs(ParaXylene.getFluid(250))
            .fluidOutputs(Dimethylbenzene.getFluid(300))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        CATALYTIC_REFORMER_RECIPES.recipeBuilder()
            .notConsumable(plate, Rhenium)
            .fluidInputs(LightFuel.getFluid(1000))
            .fluidOutputs(Toluene.getFluid(320))
            .fluidOutputs(Benzene.getFluid(400))
            .fluidOutputs(ParaXylene.getFluid(500))
            .fluidOutputs(Dimethylbenzene.getFluid(600))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        CATALYTIC_REFORMER_RECIPES.recipeBuilder()
            .notConsumable(plate, ZSM5)
            .fluidInputs(LightFuel.getFluid(1000))
            .fluidOutputs(Toluene.getFluid(640))
            .fluidOutputs(Benzene.getFluid(800))
            .fluidOutputs(ParaXylene.getFluid(1000))
            .fluidOutputs(Dimethylbenzene.getFluid(1200))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Heavy Fuel
        CATALYTIC_REFORMER_RECIPES.recipeBuilder()
            .notConsumable(plate, Platinum)
            .fluidInputs(HeavyFuel.getFluid(1000))
            .fluidOutputs(Toluene.getFluid(180))
            .fluidOutputs(Benzene.getFluid(100))
            .fluidOutputs(ParaXylene.getFluid(150))
            .fluidOutputs(Dimethylbenzene.getFluid(400))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        CATALYTIC_REFORMER_RECIPES.recipeBuilder()
            .notConsumable(plate, Rhenium)
            .fluidInputs(HeavyFuel.getFluid(1000))
            .fluidOutputs(Toluene.getFluid(360))
            .fluidOutputs(Benzene.getFluid(200))
            .fluidOutputs(ParaXylene.getFluid(300))
            .fluidOutputs(Dimethylbenzene.getFluid(800))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        CATALYTIC_REFORMER_RECIPES.recipeBuilder()
            .notConsumable(plate, ZSM5)
            .fluidInputs(HeavyFuel.getFluid(1000))
            .fluidOutputs(Toluene.getFluid(720))
            .fluidOutputs(Benzene.getFluid(400))
            .fluidOutputs(ParaXylene.getFluid(600))
            .fluidOutputs(Dimethylbenzene.getFluid(1600))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Naphtha
        CATALYTIC_REFORMER_RECIPES.recipeBuilder()
            .notConsumable(plate, Platinum)
            .fluidInputs(Naphtha.getFluid(1000))
            .fluidOutputs(Toluene.getFluid(60))
            .fluidOutputs(Benzene.getFluid(200))
            .fluidOutputs(ParaXylene.getFluid(350))
            .fluidOutputs(Ethylbenzene.getFluid(200))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        CATALYTIC_REFORMER_RECIPES.recipeBuilder()
            .notConsumable(plate, Rhenium)
            .fluidInputs(Naphtha.getFluid(1000))
            .fluidOutputs(Toluene.getFluid(120))
            .fluidOutputs(Benzene.getFluid(400))
            .fluidOutputs(ParaXylene.getFluid(700))
            .fluidOutputs(Ethylbenzene.getFluid(400))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        CATALYTIC_REFORMER_RECIPES.recipeBuilder()
            .notConsumable(plate, ZSM5)
            .fluidInputs(Naphtha.getFluid(1000))
            .fluidOutputs(Toluene.getFluid(240))
            .fluidOutputs(Benzene.getFluid(800))
            .fluidOutputs(ParaXylene.getFluid(1400))
            .fluidOutputs(Ethylbenzene.getFluid(800))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Refinery Gas
        CATALYTIC_REFORMER_RECIPES.recipeBuilder()
            .notConsumable(plate, Platinum)
            .fluidInputs(RefineryGas.getFluid(1000))
            .fluidOutputs(Benzene.getFluid(50))
            .fluidOutputs(Ethylbenzene.getFluid(250))
            .fluidOutputs(ParaXylene.getFluid(250))
            .fluidOutputs(Dimethylbenzene.getFluid(150))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        CATALYTIC_REFORMER_RECIPES.recipeBuilder()
            .notConsumable(plate, Rhenium)
            .fluidInputs(RefineryGas.getFluid(1000))
            .fluidOutputs(Benzene.getFluid(100))
            .fluidOutputs(Ethylbenzene.getFluid(500))
            .fluidOutputs(ParaXylene.getFluid(500))
            .fluidOutputs(Dimethylbenzene.getFluid(300))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        CATALYTIC_REFORMER_RECIPES.recipeBuilder()
            .notConsumable(plate, ZSM5)
            .fluidInputs(RefineryGas.getFluid(1000))
            .fluidOutputs(Benzene.getFluid(200))
            .fluidOutputs(Ethylbenzene.getFluid(1000))
            .fluidOutputs(ParaXylene.getFluid(1000))
            .fluidOutputs(Dimethylbenzene.getFluid(600))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}