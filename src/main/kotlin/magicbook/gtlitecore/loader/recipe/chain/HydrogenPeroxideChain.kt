package magicbook.gtlitecore.loader.recipe.chain

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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ethylanthrahydroquinone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ethylanthraquinone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenPeroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Ozone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PhthalicAnhydride
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class HydrogenPeroxideChain
{

    companion object
    {

        fun init()
        {
            // C6H4(CO2H)2 -> C6H4(CO)2O + H2O
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(PhthalicAcid.getFluid(1000))
                .output(dust, PhthalicAnhydride, 13)
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[HV].toLong())
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
                .EUt(VA[LuV].toLong())
                .duration(6 * SECOND)
                .buildAndRegister()

            // 6H + 2O3 -> 3H2O2
            CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Platinum)
                .fluidInputs(Hydrogen.getFluid(6000))
                .fluidInputs(Ozone.getFluid(2000))
                .fluidOutputs(HydrogenPeroxide.getFluid(3000))
                .EUt(VA[LuV].toLong())
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
                .EUt(VA[UV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()
        }

        private fun anthraquinoneProcess()
        {
            // C6H4(CO)2O + C6H5Et -> C6H4(CO)2C6H3Et + H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PhthalicAnhydride, 13)
                .fluidInputs(Ethylbenzene.getFluid(1000))
                .fluidOutputs(Ethylanthraquinone.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // C6H4(CO)2C6H3Et + 6H -> C6H4(CH2OH)2C6H3Et
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dustTiny, Palladium)
                .fluidInputs(Ethylanthraquinone.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(6000))
                .fluidOutputs(Ethylanthrahydroquinone.getFluid(1000))
                .EUt(VA[IV].toLong()) // Used LCR with two EV Energy Hatches.
                .duration(10 * SECOND)
                .buildAndRegister()

            // C6H4(CH2OH)2C6H3Et + 6O -> C6H4(CO)2C6H3Et + 3H2O2
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ethylanthrahydroquinone.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(6000))
                .fluidOutputs(Ethylanthraquinone.getFluid(1000))
                .fluidOutputs(HydrogenPeroxide.getFluid(3000))
                .EUt(VA[EV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // C6H4(CH2OH)2C6H3Et + 2O3 -> C6H4(CO)2C6H3Et + 3H2O2
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ethylanthrahydroquinone.getFluid(1000))
                .fluidInputs(Ozone.getFluid(2000))
                .fluidOutputs(Ethylanthraquinone.getFluid(1000))
                .fluidOutputs(HydrogenPeroxide.getFluid(3000))
                .EUt(VA[EV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

    }

}