package magicbook.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CRACKING_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES
import gregtech.api.recipes.RecipeMaps.GAS_TURBINE_FUELS
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Fluorine
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Helium3
import gregtech.api.unification.material.Materials.IndiumGalliumPhosphide
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Xenon
import gregtech.api.unification.ore.OrePrefix.dust
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.DRILLING_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AmmoniumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BedrockGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BedrockSootSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bedrockium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CleanBedrockSootSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CleanEnrichedBedrockSootSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrackedHeavyEnrichedTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrackedHeavyTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrackedLightEnrichedTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrackedLightTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrackedMediumEnrichedTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CrackedMediumTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EnrichedBedrockSootSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyEnrichedBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyEnrichedTaraniumFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyEnrichedTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyNaquadahFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyTaraniumFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightEnrichedBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightEnrichedTaraniumFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightEnrichedTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightNaquadahFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightTaraniumFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumEnrichedBedrockSmoke
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumEnrichedTaraniumFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumEnrichedTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumNaquadahFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumTaraniumFuel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MediumTaraniumGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NaquadriaEnergetic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.UltralightBedrockSmoke
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

/**
 * Produces Taranium, Adamantium and Vibranium from Bedrockium (Bedrock Gas).
 * - Main Products: Taranium, Adamantium, Vibranium and Taranium Fuels.
 * - Side Products: Platinum, Iridium, Osmium and Rhenium.
 */
@Suppress("MISSING_DEPENDENCY_CLASS")
class BedrockiumProcessing
{

    companion object
    {

        fun init()
        {
            bedrockiumProcess()
            enrichedBedrockiumProcess()

            // Advanced recipes for Taranium Fuels.

            // Light Taranium Fuel.
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Taranium)
                .input(dust, Gallium)
                .fluidInputs(LightNaquadahFuel.getFluid(12000))
                .fluidInputs(Krypton.getFluid(6000))
                .fluidOutputs(LightTaraniumFuel.getFluid(12000))
                .EUt(VA[UHV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Adamantium)
                .input(dust, IndiumGalliumPhosphide)
                .fluidInputs(LightNaquadahFuel.getFluid(24000))
                .fluidInputs(Krypton.getPlasma(8000))
                .fluidOutputs(LightTaraniumFuel.getFluid(24000))
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Medium Taranium Fuel
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Taranium)
                .input(dust, Duranium)
                .fluidInputs(MediumNaquadahFuel.getFluid(12000))
                .fluidInputs(Xenon.getFluid(6000))
                .fluidOutputs(MediumTaraniumFuel.getFluid(12000))
                .EUt(VA[UHV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Adamantium)
                .input(dust, Europium)
                .fluidInputs(MediumNaquadahFuel.getFluid(24000))
                .fluidInputs(Xenon.getPlasma(8000))
                .fluidOutputs(MediumTaraniumFuel.getFluid(24000))
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Heavy Taranium Fuel
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Taranium)
                .input(dust, Tritanium)
                .fluidInputs(HeavyNaquadahFuel.getFluid(12000))
                .fluidInputs(Radon.getFluid(6000))
                .fluidOutputs(HeavyTaraniumFuel.getFluid(12000))
                .EUt(VA[UHV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Adamantium)
                .input(dust, Neutronium)
                .fluidInputs(HeavyNaquadahFuel.getFluid(24000))
                .fluidInputs(Radon.getPlasma(8000))
                .fluidOutputs(HeavyTaraniumFuel.getFluid(24000))
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Enriched Light Taranium Fuel
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Americium)
                .fluidInputs(LightTaraniumFuel.getFluid(6000))
                .fluidInputs(NaquadriaEnergetic.getFluid(1000))
                .fluidOutputs(LightEnrichedTaraniumFuel.getFluid(6000))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Enriched Medium Taranium Fuel
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Dubnium)
                .fluidInputs(MediumTaraniumFuel.getFluid(6000))
                .fluidInputs(NaquadriaEnergetic.getFluid(1000))
                .fluidOutputs(MediumEnrichedTaraniumFuel.getFluid(6000))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Enriched Heavy Taranium Fuel
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Rutherfordium)
                .fluidInputs(HeavyTaraniumFuel.getFluid(6000))
                .fluidInputs(NaquadriaEnergetic.getFluid(1000))
                .fluidOutputs(HeavyEnrichedTaraniumFuel.getFluid(6000))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

        }

        private fun bedrockiumProcess()
        {
            // Drilling bedrock block to extract bedrockium dust and bedrock smoke
            // via a Bedrock Drilling Rig.
            DRILLING_RECIPES.recipeBuilder()
                .notConsumable(ItemStack(Blocks.BEDROCK, 1))
                .chancedOutput(dust, Bedrockium, 100, 0)
                .fluidOutputs(BedrockSmoke.getFluid(1000))
                .EUt(VA[UV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Mixing bedrock smoke and some high energy dust to bedrock soot solution.
            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Naquadah)
                .input(dust, AmmoniumNitrate, 2)
                .fluidInputs(BedrockSmoke.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(BedrockSootSolution.getFluid(1000))
                .EUt(VH[EV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Naquadah)
                .input(dust, AmmoniumNitrate, 2)
                .fluidInputs(BedrockSmoke.getFluid(1000))
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(BedrockSootSolution.getFluid(1000))
                .EUt(VH[EV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Centrifuging bedrock soot solution to get clean bedrock solution.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(BedrockSootSolution.getFluid(2000))
                .chancedOutput(dust, Platinum, 5, 1000, 0)
                .chancedOutput(dust, Iridium, 3, 1000, 0)
                .chancedOutput(dust, Rhenium, 2, 1000, 0)
                .chancedOutput(dust, Naquadah, 1000, 0)
                .fluidOutputs(CleanBedrockSootSolution.getFluid(1000))
                .EUt(VH[IV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Bedrock smoke fractionation.
            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(CleanBedrockSootSolution.getFluid(1000))
                .output(dust, Bedrockium, 3)
                .fluidOutputs(HeavyBedrockSmoke.getFluid(440))
                .fluidOutputs(MediumBedrockSmoke.getFluid(320))
                .fluidOutputs(LightBedrockSmoke.getFluid(180))
                .fluidOutputs(UltralightBedrockSmoke.getFluid(150))
                .EUt(VA[IV].toLong())
                .duration(4 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Centrifuging heavy bedrock smoke to heavy taranium gas.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(HeavyBedrockSmoke.getFluid(2000))
                .output(dust, Iridium)
                .output(dust, Rhenium)
                .output(dust, Bedrockium, 3)
                .fluidOutputs(HeavyTaraniumGas.getFluid(1000))
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Centrifuging medium bedrock smoke to medium taranium gas.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(MediumBedrockSmoke.getFluid(2000))
                .output(dust, Iridium)
                .output(dust, Rhenium)
                .output(dust, Bedrockium, 2)
                .fluidOutputs(MediumTaraniumGas.getFluid(1000))
                .EUt(VA[LuV].toLong())
                .duration(7 * SECOND)
                .buildAndRegister()

            // Centrifuging light bedrock smoke to light taranium gas.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(LightBedrockSmoke.getFluid(2000))
                .output(dust, Iridium)
                .output(dust, Rhenium)
                .output(dust, Bedrockium)
                .fluidOutputs(LightTaraniumGas.getFluid(1000))
                .EUt(VA[LuV].toLong())
                .duration(4 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Centrifuging ultralight bedrock smoke to bedrock gas.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(UltralightBedrockSmoke.getFluid(2000))
                .output(dust, Iridium)
                .output(dust, Rhenium)
                .chancedOutput(dust, Bedrockium, 5000, 0)
                .fluidOutputs(BedrockGas.getFluid(1000))
                .EUt(VA[LuV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Centrifuging bedrock gas can decompose it to He3 and bedrockium dust.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(BedrockGas.getFluid(1000))
                .output(dust, Bedrockium)
                .fluidOutputs(Helium3.getFluid(20))
                .EUt(VH[EV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Another usage of bedrock gas is make it as gas turbine fuels.
            GAS_TURBINE_FUELS.recipeBuilder()
                .fluidInputs(BedrockGas.getFluid(12))
                .EUt(VA[MV].toLong())
                .duration(24 * SECOND)
                .buildAndRegister()

            // Taranium gas cracks and fractions.
            CRACKING_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Fluorine.getFluid(6000))
                .fluidInputs(HeavyTaraniumGas.getFluid(1000))
                .fluidOutputs(CrackedHeavyTaraniumGas.getFluid(2000))
                .EUt(9216) // LuV
                .duration(15 * SECOND)
                .buildAndRegister()

            CRACKING_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Fluorine.getFluid(4000))
                .fluidInputs(MediumTaraniumGas.getFluid(1000))
                .fluidOutputs(CrackedMediumTaraniumGas.getFluid(1600))
                .EUt(9216) // LuV
                .duration(12 * SECOND + 10 * TICK)
                .buildAndRegister()

            CRACKING_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Fluorine.getFluid(2000))
                .fluidInputs(LightTaraniumGas.getFluid(1000))
                .fluidOutputs(CrackedLightTaraniumGas.getFluid(1200))
                .EUt(9216) // LuV
                .duration(10 * SECOND)
                .buildAndRegister()

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(CrackedHeavyTaraniumGas.getFluid(1000))
                .output(dust, Taranium)
                .fluidOutputs(Fluorine.getFluid(250))
                .fluidOutputs(HeavyTaraniumFuel.getFluid(400))
                .fluidOutputs(MediumTaraniumFuel.getFluid(200))
                .fluidOutputs(LightTaraniumFuel.getFluid(100))
                .fluidOutputs(BedrockGas.getFluid(50))
                .EUt(V[LuV] / 2) // LuV
                .duration(8 * SECOND)
                .buildAndRegister()

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(CrackedMediumTaraniumGas.getFluid(1000))
                .output(dust, Taranium)
                .fluidOutputs(Fluorine.getFluid(150))
                .fluidOutputs(HeavyTaraniumFuel.getFluid(100))
                .fluidOutputs(MediumTaraniumFuel.getFluid(400))
                .fluidOutputs(LightTaraniumFuel.getFluid(200))
                .fluidOutputs(BedrockGas.getFluid(150))
                .EUt(V[LuV] / 2) // LuV
                .duration(7 * SECOND)
                .buildAndRegister()

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(CrackedLightTaraniumGas.getFluid(1000))
                .output(dust, Taranium)
                .fluidOutputs(Fluorine.getFluid(50))
                .fluidOutputs(HeavyTaraniumFuel.getFluid(50))
                .fluidOutputs(MediumTaraniumFuel.getFluid(150))
                .fluidOutputs(LightTaraniumFuel.getFluid(400))
                .fluidOutputs(BedrockGas.getFluid(350))
                .EUt(V[LuV] / 2) // LuV
                .duration(6 * SECOND)
                .buildAndRegister()
        }

        private fun enrichedBedrockiumProcess()
        {
            // Mixing bedrock gas from bedrockium process and some other materials to enriched bedrock soot solution.
            MIXER_RECIPES.recipeBuilder()
                .input(dust, NaquadahEnriched)
                .fluidInputs(SulfuricAcid.getFluid(900))
                .fluidInputs(BedrockGas.getFluid(100))
                .fluidOutputs(EnrichedBedrockSootSolution.getFluid(1000))
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Centrifuging enriched bedrock soot solution to clean enriched bedrock solution.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(EnrichedBedrockSootSolution.getFluid(2000))
                .chancedOutput(dust, Platinum, 5, 1000, 0)
                .chancedOutput(dust, Osmium, 3, 1000, 0)
                .chancedOutput(dust, Rhenium, 2, 1000, 0)
                .chancedOutput(dust, NaquadahEnriched, 1000, 0)
                .fluidOutputs(CleanEnrichedBedrockSootSolution.getFluid(1000))
                .EUt(98304) // ZPM
                .duration(15 * SECOND)
                .buildAndRegister()

            // Clean enriched bedrock soot solution fraction.
            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(CleanEnrichedBedrockSootSolution.getFluid(1000))
                .output(dust, Bedrockium, 4)
                .fluidOutputs(HeavyEnrichedBedrockSmoke.getFluid(440))
                .fluidOutputs(MediumEnrichedBedrockSmoke.getFluid(320))
                .fluidOutputs(LightEnrichedBedrockSmoke.getFluid(180))
                .fluidOutputs(UltralightBedrockSmoke.getFluid(150))
                .EUt(40960) // ZPM
                .duration(7 * SECOND)
                .buildAndRegister()

            // Centrifuging heavy enriched bedrock smoke to heavy enriched taranium gas.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(HeavyEnrichedBedrockSmoke.getFluid(8000))
                .output(dust, Bedrockium, 5)
                .output(dust, Naquadria, 1)
                .output(dust, Iridium, 2)
                .output(dust, Osmium, 3)
                .fluidOutputs(HeavyEnrichedTaraniumGas.getFluid(4000))
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // Centrifuging medium enriched bedrock smoke to medium enriched taranium gas.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(MediumEnrichedBedrockSmoke.getFluid(8000))
                .output(dust, Bedrockium, 4)
                .output(dust, Naquadria, 1)
                .output(dust, Iridium, 2)
                .output(dust, Osmium, 3)
                .fluidOutputs(MediumEnrichedTaraniumGas.getFluid(4000))
                .EUt(VA[ZPM].toLong())
                .duration(48 * SECOND)
                .buildAndRegister()

            // Centrifuging light enriched bedrock smoke to light enriched taranium gas.
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(LightEnrichedBedrockSmoke.getFluid(8000))
                .output(dust, Bedrockium, 3)
                .output(dust, Naquadria, 1)
                .output(dust, Iridium, 2)
                .output(dust, Osmium, 3)
                .fluidOutputs(LightEnrichedTaraniumGas.getFluid(4000))
                .EUt(VA[ZPM].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Enriched taranium gas cracks and fractions.
            CRACKING_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Radon.getFluid(6000))
                .fluidInputs(HeavyEnrichedTaraniumGas.getFluid(1000))
                .fluidOutputs(CrackedHeavyEnrichedTaraniumGas.getFluid(2000))
                .EUt(49152) // ZPM
                .duration(15 * SECOND)
                .buildAndRegister()

            CRACKING_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Radon.getFluid(4000))
                .fluidInputs(MediumEnrichedTaraniumGas.getFluid(1000))
                .fluidOutputs(CrackedMediumEnrichedTaraniumGas.getFluid(1600))
                .EUt(49152) // ZPM
                .duration(12 * SECOND + 10 * TICK)
                .buildAndRegister()

            CRACKING_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Radon.getFluid(2000))
                .fluidInputs(LightEnrichedTaraniumGas.getFluid(1000))
                .fluidOutputs(CrackedLightEnrichedTaraniumGas.getFluid(1200))
                .EUt(49152) // ZPM
                .duration(10 * SECOND)
                .buildAndRegister()

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(CrackedHeavyEnrichedTaraniumGas.getFluid(1000))
                .output(dust, Taranium, 2)
                .fluidOutputs(Radon.getFluid(250))
                .fluidOutputs(HeavyEnrichedTaraniumFuel.getFluid(400))
                .fluidOutputs(MediumEnrichedTaraniumFuel.getFluid(200))
                .fluidOutputs(LightEnrichedTaraniumFuel.getFluid(100))
                .fluidOutputs(BedrockGas.getFluid(50))
                .EUt(98304) // ZPM
                .duration(8 * SECOND)
                .buildAndRegister()

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(CrackedMediumEnrichedTaraniumGas.getFluid(1000))
                .output(dust, Taranium, 2)
                .fluidOutputs(Radon.getFluid(150))
                .fluidOutputs(HeavyEnrichedTaraniumFuel.getFluid(100))
                .fluidOutputs(MediumEnrichedTaraniumFuel.getFluid(400))
                .fluidOutputs(LightEnrichedTaraniumFuel.getFluid(200))
                .fluidOutputs(BedrockGas.getFluid(150))
                .EUt(98304) // ZPM
                .duration(7 * SECOND)
                .buildAndRegister()

            DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(CrackedLightEnrichedTaraniumGas.getFluid(1000))
                .output(dust, Taranium, 2)
                .fluidOutputs(Radon.getFluid(50))
                .fluidOutputs(HeavyEnrichedTaraniumFuel.getFluid(50))
                .fluidOutputs(MediumEnrichedTaraniumFuel.getFluid(150))
                .fluidOutputs(LightEnrichedTaraniumFuel.getFluid(400))
                .fluidOutputs(BedrockGas.getFluid(350))
                .EUt(98304) // ZPM
                .duration(6 * SECOND)
                .buildAndRegister()
        }

    }

}