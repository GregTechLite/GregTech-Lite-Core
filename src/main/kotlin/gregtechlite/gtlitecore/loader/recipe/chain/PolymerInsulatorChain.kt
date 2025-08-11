package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Acetone
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Butene
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.MagnesiumChloride
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Potash
import gregtech.api.unification.material.Materials.Potassium
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Ruthenium
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_FOIL
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AdipicAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Alumina
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AminatedFullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Azafullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DitertbutylDicarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexamethylenediamine
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hexanediol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polyetheretherketone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SacchariaAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumAzide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tertbutanol
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TertbutylAzidoformate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trisaminoethylamine
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PEEK_POLYAMIDE_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.POLYMER_INSULATOR_FOIL

internal object PolymerInsulatorChain
{

    // @formatter:off

    fun init()
    {
        // C6H10O8 + 8H -> C6H10O4 + 4H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, Palladium)
            .input(dust, SacchariaAcid, 24)
            .fluidInputs(Hydrogen.getFluid(8000))
            .output(dust, AdipicAcid, 20)
            .fluidOutputs(Water.getFluid(4000))
            .EUt(VA[EV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C6H14O2 + 2NH3 -> C6H16N2 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .notConsumable(dust, Alumina)
            .notConsumable(dust, Ruthenium)
            .fluidInputs(Hexanediol.getFluid(1000))
            .fluidInputs(Ammonia.getFluid(2000))
            .fluidOutputs(Hexamethylenediamine.getFluid(1000))
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[EV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C3H6O + CH4 -> C4H10O
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .notConsumable(dust, MagnesiumChloride)
            .fluidInputs(Acetone.getFluid(1000))
            .fluidInputs(Methane.getFluid(1000))
            .fluidOutputs(Tertbutanol.getFluid(1000))
            .EUt(VA[MV])
            .duration(4 * SECOND + 5 * TICK)
            .buildAndRegister()

        // 2C4H10O + 2CO2 -> C10H18O5 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
            .fluidInputs(Tertbutanol.getFluid(2000))
            .fluidInputs(CarbonDioxide.getFluid(2000))
            .output(dust, DitertbutylDicarbonate, 33)
            .fluidOutputs(Water.getFluid(1000))
            .EUt(VA[HV])
            .duration(4 * SECOND + 16 * TICK)
            .buildAndRegister()

        // C10H18O5 + 3C + 4NH3 + C4H8 -> (NH2CH2CH2)3N + 2C4H10O + 3CO
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, DitertbutylDicarbonate, 33)
            .input(dust, Carbon, 3)
            .fluidInputs(Ammonia.getFluid(4000))
            .fluidInputs(Butene.getFluid(1000))
            .fluidOutputs(Trisaminoethylamine.getFluid(1000))
            .fluidOutputs(Tertbutanol.getFluid(2000))
            .fluidOutputs(CarbonMonoxide.getFluid(3000))
            .EUt(VA[EV])
            .duration(8 * SECOND + 15 * TICK)
            .buildAndRegister()

        // PEEK-Polyamide Foil
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_EXTRUDER_FOIL)
            .input(dust, AdipicAcid, 20)
            .input(foil, Polyetheretherketone)
            .input(foil, SiliconeRubber)
            .fluidInputs(Hexamethylenediamine.getFluid(1000))
            .fluidInputs(Trisaminoethylamine.getFluid(500))
            .output(PEEK_POLYAMIDE_FOIL, 3)
            .EUt(VA[UV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // C10H18O5 + 2NaN3 + 2K -> 2Na + 2K2O + 2C5H9N3O2
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(dust, DitertbutylDicarbonate, 33)
            .input(dust, SodiumAzide, 8)
            .input(dust, Potassium, 2)
            .output(dust, Sodium, 2)
            .output(dust, Potash, 6)
            .fluidOutputs(TertbutylAzidoformate.getFluid(2000))
            .EUt(VA[EV])
            .duration(8 * SECOND)
            .buildAndRegister()

        // C60 + 4C5H9N3O2 + 8H2O + 4CO -> C60N12H12-NH + 8CO2 + 4C4H10O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Fullerene)
            .fluidInputs(TertbutylAzidoformate.getFluid(4000))
            .fluidInputs(Water.getFluid(8000))
            .fluidInputs(CarbonMonoxide.getFluid(4000))
            .fluidOutputs(AminatedFullerene.getFluid(1000))
            .fluidOutputs(CarbonDioxide.getFluid(8000))
            .fluidOutputs(Tertbutanol.getFluid(4000))
            .EUt(VA[LuV])
            .duration(13 * SECOND + 10 * TICK)
            .buildAndRegister()

        // C60N12H12-NH -> C60N12H12-N
        FLUID_HEATER_RECIPES.recipeBuilder()
            .notConsumable(wireFine, Rhenium)
            .fluidInputs(AminatedFullerene.getFluid(100))
            .fluidOutputs(Azafullerene.getFluid(100))
            .EUt(VA[IV])
            .duration(2 * TICK)
            .buildAndRegister()

        // Polymer Insulator Foil
        CHEMICAL_BATH_RECIPES.recipeBuilder()
            .input(PEEK_POLYAMIDE_FOIL)
            .fluidInputs(Azafullerene.getFluid(10))
            .output(POLYMER_INSULATOR_FOIL, 8)
            .EUt(VA[UV])
            .duration(1 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    // @formatter:on

}