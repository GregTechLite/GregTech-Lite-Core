package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Butene
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.LithiumChloride
import gregtech.api.unification.material.Materials.Octane
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.wireFine
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Acetaldehyde
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AscorbicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CalciumAlginate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CarbonNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CetaneTrimethylAmmoniumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CitricAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DehydroascorbicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GrapheneOxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Methyltrichlorosilane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Octene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumAcetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumLoadedRutileNanoparticles
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SiliconNanoparticles
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumCarbonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SodiumHydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuccinicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TetraethylammoniumBromide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trimethylamine
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANOSILICON_CATHODE

@Suppress("MISSING_DEPENDENCY_CLASS")
class NanoparticlesChain
{

    companion object
    {

        fun init()
        {
            // Palladium Loaded Rutile Nanoparticles

            // Pd(CH3COOH)2 + 3Li2TiO3 + 2HCl + H2O -> Pd(TiO2) + C4H6O4 + 2LiCl
            CVD_RECIPES.recipeBuilder()
                .input(dust, PalladiumAcetate, 15)
                .input(wireFine, LithiumTitanate, 24)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .output(dust, PalladiumLoadedRutileNanoparticles, 4)
                .output(dust, SuccinicAcid, 14)
                .output(dust, LithiumChloride, 4)
                .EUt(VA[EV].toLong())
                .duration(10 * TICK)
                .temperature(684)
                .buildAndRegister()
            
            // Silicon Nanoparticles

            // Add another recipes for C8H16, the original produce is CPP byproducts.
            // 2C4H8 -> C8H16
            FLUID_HEATER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(Butene.getFluid(200))
                .fluidOutputs(Octene.getFluid(100))
                .EUt(VA[MV].toLong())
                .duration(14 * TICK)
                .buildAndRegister()

            // (CH3)3N + C8H18 + C8H16 + Br -> C19H42BrN + H
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Trimethylamine.getFluid(1000))
                .fluidInputs(Octane.getFluid(1000))
                .fluidInputs(Octene.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .fluidOutputs(CetaneTrimethylAmmoniumBromide.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Si(CH3)Cl3 + C19H42BrN + C6H8O7 + 2Na -> Si + 4C + 7C2H4O + 3HCl + N(CH2CH3)4Br + 2NaH
            CVD_RECIPES.recipeBuilder()
                .input(dust, Sodium, 2)
                .fluidInputs(Methyltrichlorosilane.getFluid(1000))
                .fluidInputs(CetaneTrimethylAmmoniumBromide.getFluid(1000))
                .fluidInputs(CitricAcid.getFluid(1000))
                .output(dust, SiliconNanoparticles)
                .output(dust, SodiumHydride, 4)
                .output(dust, Carbon, 4)
                .fluidOutputs(Acetaldehyde.getFluid(7000))
                .fluidOutputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(TetraethylammoniumBromide.getFluid(1000))
                .EUt(VA[UV].toLong())
                .duration(20 * SECOND)
                .temperature(1342)
                .buildAndRegister()

            // Nanosilicon Cathode
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .input(dust, SiliconNanoparticles)
                .input(dust, GrapheneOxide)
                .input(dust, CalciumAlginate)
                .input(dust, CarbonNanotube)
                .fluidInputs(SodiumCarbonate.getFluid(1000))
                .fluidInputs(AscorbicAcid.getFluid(1000))
                .output(NANOSILICON_CATHODE)
                .fluidOutputs(DehydroascorbicAcid.getFluid(1000))
                .EUt(VA[UHV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

        }

    }

}