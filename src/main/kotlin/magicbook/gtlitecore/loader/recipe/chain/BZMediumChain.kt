package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Bromine
import gregtech.api.unification.material.Materials.Cerium
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.HypochlorousAcid
import gregtech.api.unification.material.Materials.Ice
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYOGENIC_REACTOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BZMedium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ChloroaceticAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Dichloroethane
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrobromicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MalonicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumBromate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PotassiumHydroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Trichloroethylene
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class BZMediumChain
{

    companion object
    {

        fun init()
        {
            // 2Br + SO2 + 2H2O -> H2SO4 + 2HBr
            CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .fluidInputs(Bromine.getFluid(2000))
                .fluidInputs(SulfurDioxide.getFluid(1000))
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(HydrobromicAcid.getFluid(2000))
                .EUt(VA[HV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // 3HBr + 3KOH -> KBrO3 + 3H2O
            CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Bromine.getFluid(3000))
                .fluidInputs(PotassiumHydroxide.getFluid(L * 9))
                .output(dust, PotassiumBromate, 5)
                .fluidOutputs(Ice.getFluid(3000))
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // C2H4Cl2 + Cl -> C2HCl3 + 3H
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Dichloroethane.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(Trichloroethylene.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(3000))
                .EUt(VA[EV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // C2HCl3 + 2H2O -> C2H3ClO2 + 2HCl
            CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Trichloroethylene.getFluid(1000))
                .fluidInputs(Water.getFluid(2000))
                .notConsumable(SulfuricAcid.getFluid(250))
                .output(dust, ChloroaceticAcid, 8)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(VA[EV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // C2H3ClO2 + Na2CO3 + 2H2O -> C3H4O4 + 2NaOH + HClO
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodaAsh, 6)
                .input(dust, ChloroaceticAcid, 8)
                .fluidInputs(Water.getFluid(2000))
                .output(dust, SodiumHydroxide, 6)
                .output(dust, MalonicAcid, 11)
                .fluidOutputs(HypochlorousAcid.getFluid(1000))
                .EUt(VA[IV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // KBrO3 + 0.25 C3H4O4 + Ce -> 1x BZMedium
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, PotassiumBromate, 5)
                .input(dust, MalonicAcid, 3)
                .input(dust, Cerium)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(BZMedium.getFluid(1000))
                .EUt(VA[ZPM].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()
        }

    }

}