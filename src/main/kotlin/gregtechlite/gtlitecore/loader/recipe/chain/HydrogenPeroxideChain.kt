package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Ethylbenzene
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.PhthalicAcid
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.common.items.MetaItems.BLACKLIGHT
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylanthrahydroquinone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ethylanthraquinone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenPeroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ozone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PhthalicAnhydride

internal object HydrogenPeroxideChain
{

    // @formatter:off

    fun init()
    {
        // C6H4(CO2H)2 -> C6H4(CO)2O + H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .fluidInputs(PhthalicAcid.getFluid(1000))
            .output(dust, PhthalicAnhydride, 15)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        anthraquinoneProcess() // Basic processing of H2O2 at EV-IV stage.

        // Directed reaction with H and O for IV/LuV stage with platinum catalyst.
        // 2H + 2O -> H2O2
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, Platinum)
            .fluidInputs(Hydrogen.getFluid(2000))
            .fluidInputs(Oxygen.getFluid(2000))
            .fluidOutputs(HydrogenPeroxide.getFluid(1000))
            .EUt(VA[LuV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // 6H + 2O3 -> 3H2O2
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, Platinum)
            .fluidInputs(Hydrogen.getFluid(6000))
            .fluidInputs(Ozone.getFluid(2000))
            .fluidOutputs(HydrogenPeroxide.getFluid(3000))
            .EUt(VA[LuV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // Water decompose reaction for ZPM/UV stage.
        // 16H2O -> 8H2O2 + 8H
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .circuitMeta(24)
            .notConsumable(BLACKLIGHT)
            .notConsumable(dust, YttriumBariumCuprate)
            .fluidInputs(Water.getFluid(16000))
            .fluidOutputs(HydrogenPeroxide.getFluid(8000))
            .fluidOutputs(Hydrogen.getFluid(8000))
            .EUt(VA[UV])
            .duration(12 * SECOND)
            .buildAndRegister()
    }

    private fun anthraquinoneProcess()
    {
        // C6H4(CO)2O + C6H5Et -> C6H4(CO)2C6H3Et + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, PhthalicAnhydride, 15)
            .fluidInputs(Ethylbenzene.getFluid(1000))
            .fluidOutputs(Ethylanthraquinone.getFluid(1000))
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[EV])
            .duration(30 * SECOND)
            .buildAndRegister()

        // C6H4(CO)2C6H3Et + 6H -> C6H4(CH2OH)2C6H3Et
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dustTiny, Palladium)
            .fluidInputs(Ethylanthraquinone.getFluid(1000))
            .fluidInputs(Hydrogen.getFluid(6000))
            .fluidOutputs(Ethylanthrahydroquinone.getFluid(1000))
            .EUt(VA[IV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // C6H4(CH2OH)2C6H3Et + 6O -> C6H4(CO)2C6H3Et + 3H2O2
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Ethylanthrahydroquinone.getFluid(1000))
            .fluidInputs(Oxygen.getFluid(6000))
            .fluidOutputs(Ethylanthraquinone.getFluid(1000))
            .fluidOutputs(HydrogenPeroxide.getFluid(3000))
            .EUt(VA[EV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // C6H4(CH2OH)2C6H3Et + 2O3 -> C6H4(CO)2C6H3Et + 3H2O2
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Ethylanthrahydroquinone.getFluid(1000))
            .fluidInputs(Ozone.getFluid(2000))
            .fluidOutputs(Ethylanthraquinone.getFluid(1000))
            .fluidOutputs(HydrogenPeroxide.getFluid(3000))
            .EUt(VA[EV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    // @formatter:on

}