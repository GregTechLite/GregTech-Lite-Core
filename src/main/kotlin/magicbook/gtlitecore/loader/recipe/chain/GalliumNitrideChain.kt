package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chloromethane
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Sapphire
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.plate
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_DEHYDRATOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Alumina
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AluminiumTrichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumDioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumTrichloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GalliumTrioxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trimethylaluminium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trimethylgallium
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class GalliumNitrideChain
{

    companion object
    {

        fun init()
        {
            // Ga + 3Cl -> GaCl3
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Gallium)
                .fluidInputs(Chlorine.getFluid(3000))
                .output(dust, GalliumTrichloride, 4)
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // GaO2 + 3Cl -> GaCl3 + 2O
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, GalliumDioxide, 3)
                .fluidInputs(Chlorine.getFluid(3000))
                .output(dust, GalliumTrichloride, 4)
                .fluidOutputs(Oxygen.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Al + 3Na + 3CH3Cl -> 0.5Al2(CH3)6 + 3NaCl
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Aluminium)
                .input(dust, Sodium, 3)
                .fluidInputs(Chloromethane.getFluid(3000))
                .fluidOutputs(Trimethylaluminium.getFluid(500))
                .output(dust, Salt, 6)
                .EUt(VA[EV].toLong())
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

            // GaCl3 + 0.5Al2(CH3)6 -> Ga(CH3)3 + AlCl3
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, GalliumTrichloride, 4)
                .fluidInputs(Trimethylaluminium.getFluid(500))
                .output(dust, AluminiumTrichloride, 4)
                .fluidOutputs(Trimethylgallium.getFluid(1000))
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // AlCl3 + 3H2O -> Al(OH)3 + 3HCl
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, AluminiumTrichloride, 4)
                .fluidInputs(Water.getFluid(3000))
                .output(dust, AluminiumHydroxide, 7)
                .fluidOutputs(HydrochloricAcid.getFluid(3000))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister();

            // 2Al(OH)3 -> Al2O3 + 3H2O
            CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .input(dust, AluminiumHydroxide, 14)
                .output(dust, Alumina, 5)
                .fluidOutputs(Water.getFluid(3000))
                .EUt(VH[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // 2Ga(CH3)3 + 3H2O -> Ga2O3 + 3CH4 + 3H (drop)
            CVD_RECIPES.recipeBuilder()
                .input(plate, Sapphire)
                .fluidInputs(Trimethylgallium.getFluid(2000))
                .fluidInputs(Water.getFluid(3000))
                .output(dust, GalliumTrioxide, 5)
                .fluidOutputs(Methane.getFluid(3000))
                .EUt(VA[HV].toLong())
                .duration(8 * SECOND)
                .temperature(923)
                .buildAndRegister()

            // Ga2O3 + 2NH3 -> 2GaN + 3H2O
            CVD_RECIPES.recipeBuilder()
                .input(dust, GalliumTrioxide, 5)
                .fluidInputs(Ammonia.getFluid(2000))
                .output(dust, GalliumNitride, 4)
                .fluidOutputs(Steam.getFluid(3000))
                .EUt(VA[LuV].toLong())
                .duration(12 * SECOND + 10 * TICK)
                .temperature(1023)
                .buildAndRegister()
        }

    }

}