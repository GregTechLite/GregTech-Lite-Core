package magicbook.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Bauxite
import gregtech.api.unification.material.Materials.Beryllium
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.HydrofluoricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Magnesium
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Ruby
import gregtech.api.unification.material.Materials.Sapphire
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.Vanadium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.crushed
import gregtech.api.unification.ore.OrePrefix.crushedPurified
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustImpure
import gregtech.api.unification.ore.OrePrefix.dustPure
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.ingot
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Alumina
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cryolite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GreenSapphireJuice
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RubyJuice
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SapphireJuice
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumAluminate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumCarbonate
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class AluminiumSodiumProcessing
{

    companion object
    {

        fun init()
        {
            // Centrifuging gem juices to get Al(OH)3.
            for (inputPrefix in arrayOf(crushed, crushedPurified, dustImpure, dustPure))
            {
                // Green Sapphire Juice
                MIXER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(inputPrefix, GreenSapphire)
                    .input(dustTiny, SodiumHydroxide)
                    .fluidInputs(HydrochloricAcid.getFluid(1000))
                    .fluidOutputs(GreenSapphireJuice.getFluid(1000))
                    .EUt(VA[MV].toLong())
                    .duration(2 * SECOND)
                    .buildAndRegister()

                // Sapphire Juice
                MIXER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(inputPrefix, Sapphire)
                    .input(dustTiny, SodiumHydroxide)
                    .fluidInputs(HydrochloricAcid.getFluid(1000))
                    .fluidOutputs(SapphireJuice.getFluid(1000))
                    .EUt(VA[MV].toLong())
                    .duration(2 * SECOND)
                    .buildAndRegister();

                // Ruby Juice
                MIXER_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(inputPrefix, Ruby)
                    .input(dustTiny, SodiumHydroxide)
                    .fluidInputs(HydrochloricAcid.getFluid(1000))
                    .fluidOutputs(RubyJuice.getFluid(1000))
                    .EUt(VA[MV].toLong())
                    .duration(2 * SECOND)
                    .buildAndRegister()
            }

            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(GreenSapphireJuice.getFluid(1000))
                .output(dust, AluminiumHydroxide, 3)
                .chancedOutput(dust, Iron, 1000, 500)
                .chancedOutput(dust, Vanadium, 200, 100)
                .chancedOutput(dust, Manganese, 200, 100)
                .chancedOutput(dust, Beryllium, 200, 100)
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()

            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(SapphireJuice.getFluid(1000))
                .output(dust, AluminiumHydroxide, 3)
                .chancedOutput(dust, Iron, 3000, 1000)
                .chancedOutput(dust, Vanadium, 200, 100)
                .chancedOutput(dust, Magnesium, 200, 100)
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()

            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(RubyJuice.getFluid(1000))
                .output(dust, AluminiumHydroxide, 3)
                .chancedOutput(dust, Chrome, 5000, 1000)
                .chancedOutput(dust, Iron, 300, 150)
                .chancedOutput(dust, Vanadium, 200, 100)
                .chancedOutput(dust, Magnesium, 200, 100)
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()

            // Chemistry Al(OH)3 processing.
            // (Al2O3)3(TiO2)2(H2O)2? (bauxite) + 4NaOH + H2O -> 4NaAlO2
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Bauxite, 4)
                .input(dust, SodiumHydroxide, 12)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, SodiumAluminate, 16)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // NaAlO2 + 2H2O -> Al(OH)3 + NaOH
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, SodiumAluminate, 4)
                .fluidInputs(Water.getFluid(2000))
                .output(dust, AluminiumHydroxide, 4)
                .output(dust, SodiumHydroxide, 3)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // 2NaAlO2 + CO2 -> Al2O3 + Na2CO3
            BLAST_RECIPES.recipeBuilder()
                .input(dust, SodiumAluminate, 8)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .output(dust, Alumina, 5)
                .output(dust, SodiumCarbonate, 6)
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .blastFurnaceTemp(1200) // Cupronickel
                .buildAndRegister()

            // Common Na2CO3 decomposition.
            ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, SodiumCarbonate, 6)
                .output(dust, Sodium, 2)
                .output(dust, Carbon, 1)
                .fluidOutputs(Oxygen.getFluid(3000))
                .EUt(VHA[MV].toLong())
                .duration(4 * SECOND + 16 * TICK)
                .buildAndRegister()

            // Another Na2CO3 recipes.
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, SodaAsh)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, SodiumCarbonate, 6)
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Chemistry cryolite processing.
            // 3NaOH + Al(OH)3 + 6HF -> Na3AlF6 + 6H2O
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 9)
                .input(dust, AluminiumHydroxide, 4)
                .fluidInputs(HydrofluoricAcid.getFluid(6000))
                .output(dust, Cryolite, 10)
                .fluidOutputs(Water.getFluid(6000))
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, SodiumHydroxide, 63)
                .input(dust, AluminiumHydroxide, 28)
                .fluidInputs(HydrofluoricAcid.getFluid(42000))
                .output(dust, Cryolite, 64)
                .output(dust, Cryolite, 6)
                .fluidOutputs(Water.getFluid(42000))
                .EUt(VA[HV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Add Alumina (Al2O3) to Aluminium recipes.

            // Al2O3 + 3H -> 2Al + 3H2O
            BLAST_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Alumina, 5)
                .fluidInputs(Hydrogen.getFluid(3000))
                .output(ingot, Aluminium)
                .fluidOutputs(Steam.getFluid(3000))
                .EUt(VA[MV].toLong())
                .duration(1 * MINUTE + 20 * SECOND)
                .blastFurnaceTemp(963) // Cupronickel
                .buildAndRegister()

            // 2Al2O3 + 0.5Na3AlF6 -> 4Al
            BLAST_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Alumina, 10)
                .input(dust, Cryolite, 5)
                .output(ingot, Aluminium, 4)
                .EUt(VA[MV].toLong())
                .duration(80 * SECOND)
                .blastFurnaceTemp(963) // Cupronickel
                .buildAndRegister()

            // 2Al2O3 + 3C -> 4Al + 3CO2
            BLAST_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Alumina, 10)
                .input(dust, Carbon, 3)
                .output(ingot, Aluminium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(VA[MV].toLong())
                .duration(1 * MINUTE)
                .blastFurnaceTemp(2054) // Kanthal
                .buildAndRegister()

        }

    }

}