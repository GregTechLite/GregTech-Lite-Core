package magicbook.gtlitecore.integration.appliedenergistics2.recipe

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.RedAlloy
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CIRCUIT_ASSEMBLY_LINE_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.Mods
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_BASIC_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_COATED_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_DIAMOND_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_RUBY_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_SAPPHIRE_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOD_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_PHENOLIC_BOARD

/**
 * @see [magicbook.gtlitecore.loader.recipe.producer.CircuitAssemblyLineRecipeProducer]
 */
@Suppress("MISSING_DEPENDENCY_CLASS")
class AppEngCALRecipeProducer
{

    companion object
    {

        fun produce()
        {

            // Logic Processor
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ENGRAVED_RUBY_CHIP)
                .input(WRAP_BASIC_CIRCUIT_BOARD)
                .input(wireGtHex, RedAlloy, 2)
                .input(bolt, Gold, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 22, 64)) // Logic processor
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 22, 64)) // Logic processor
                .EUt(VA[LV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ENGRAVED_RUBY_CHIP)
                .input(WRAP_GOOD_CIRCUIT_BOARD)
                .input(wireGtHex, RedAlloy, 2)
                .input(bolt, Electrum, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 22, 64)) // Logic processor
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 22, 64)) // Logic processor
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 22, 64)) // Logic processor
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 22, 64)) // Logic processor
                .EUt(VA[LV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .buildAndRegister()


            // Calculation Processor
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ENGRAVED_SAPPHIRE_CHIP)
                .input(WRAP_BASIC_CIRCUIT_BOARD)
                .input(wireGtHex, RedAlloy, 2)
                .input(bolt, Gold, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 23, 64)) // Calculation processor
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 23, 64)) // Calculation processor
                .EUt(VA[LV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ENGRAVED_SAPPHIRE_CHIP)
                .input(WRAP_GOOD_CIRCUIT_BOARD)
                .input(wireGtHex, RedAlloy, 2)
                .input(bolt, Electrum, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 23, 64)) // Calculation processor
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 23, 64)) // Calculation processor
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 23, 64)) // Calculation processor
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 23, 64)) // Calculation processor
                .EUt(VA[LV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .buildAndRegister()

            // Engineering Processor
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ENGRAVED_DIAMOND_CHIP)
                .input(WRAP_BASIC_CIRCUIT_BOARD)
                .input(wireGtHex, RedAlloy, 2)
                .input(bolt, Gold, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 24, 64)) // Engineering processor
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 24, 64)) // Engineering processor
                .EUt(VA[LV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ENGRAVED_DIAMOND_CHIP)
                .input(WRAP_GOOD_CIRCUIT_BOARD)
                .input(wireGtHex, RedAlloy, 2)
                .input(bolt, Electrum, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 24, 64)) // Engineering processor
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 24, 64)) // Engineering processor
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 24, 64)) // Engineering processor
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 24, 64)) // Engineering processor
                .EUt(VA[LV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .buildAndRegister()

            // 1k ME Storage Cell
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_COATED_BOARD)
                .inputs(Mods.AppliedEnergistics2.getMetaItem("material", 22, 16)) // Logic processor
                .input(foil, Gold, 64)
                .input(bolt, Iron, 32)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 35, 64)) // 1k ME Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 35, 64)) // 1k ME Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 35, 64)) // 1k ME Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 35, 64)) // 1k ME Storage Component
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
                .buildAndRegister()

            // 1k ME Fluid Storage Cell
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_COATED_BOARD)
                .inputs(Mods.AppliedEnergistics2.getMetaItem("material", 23, 16)) // Calculation processor
                .input(foil, Silver, 64)
                .input(bolt, Iron, 32)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 54, 64)) // 1k ME Fluid Storage Cell
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 54, 64)) // 1k ME Fluid Storage Cell
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 54, 64)) // 1k ME Fluid Storage Cell
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 54, 64)) // 1k ME Fluid Storage Cell
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
                .buildAndRegister()

            // 4k ME Storage Cell
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_COATED_BOARD)
                .inputs(Mods.AppliedEnergistics2.getMetaItem("material", 35, 64)) // 1k ME Storage Component
                .inputs(Mods.AppliedEnergistics2.getMetaItem("material", 24, 16)) // Engineering Processor
                .input(wireGtHex, Lead, 2)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 36, 64)) // 4k ME Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 36, 64)) // 4k ME Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 36, 64)) // 4k ME Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 36, 64)) // 4k ME Storage Component
                .EUt(VA[LV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .buildAndRegister()

            // 4k ME Fluid Storage Cell
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_COATED_BOARD)
                .inputs(Mods.AppliedEnergistics2.getMetaItem("material", 54, 64)) // 1k ME Fluid Storage Component
                .inputs(Mods.AppliedEnergistics2.getMetaItem("material", 24, 16)) // Engineering Processor
                .input(wireGtHex, Lead, 2)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 55, 64)) // 4k ME Fluid Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 55, 64)) // 4k ME Fluid Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 55, 64)) // 4k ME Fluid Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 55, 64)) // 4k ME Fluid Storage Component
                .EUt(VA[LV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .buildAndRegister()

            // 16k ME Storage Cell
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_PHENOLIC_BOARD)
                .inputs(Mods.AppliedEnergistics2.getMetaItem("material", 36, 64)) // 4k ME Storage Component
                .input(circuit, MarkerMaterials.Tier.ULV, 16)
                .input(wireGtHex, AnnealedCopper, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 37, 64)) // 16k ME Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 37, 64)) // 16k ME Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 37, 64)) // 16k ME Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 37, 64)) // 16k ME Storage Component
                .EUt(VA[LV].toLong())
                .duration(3 * MINUTE) // Original: 15s, Wrapped: 15s * 16 = 240s
                .buildAndRegister()

            // 16k ME Fluid Storage Cell
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_PHENOLIC_BOARD)
                .inputs(Mods.AppliedEnergistics2.getMetaItem("material", 55, 64)) // 4k ME Fluid Storage Component
                .input(circuit, MarkerMaterials.Tier.ULV, 16)
                .input(wireGtHex, AnnealedCopper, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 56, 64)) // 16k ME Fluid Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 56, 64)) // 16k ME Fluid Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 56, 64)) // 16k ME Fluid Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 56, 64)) // 16k ME Fluid Storage Component
                .EUt(VA[LV].toLong())
                .duration(3 * MINUTE) // Original: 15s, Wrapped: 15s * 16 = 240s
                .buildAndRegister()

            // 64k ME Storage Cell
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_PHENOLIC_BOARD)
                .inputs(Mods.AppliedEnergistics2.getMetaItem("material", 37, 64)) // 16k ME Storage Component
                .input(circuit, MarkerMaterials.Tier.LV, 16)
                .input(wireGtHex, Gold, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 38, 64)) // 64k ME Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 38, 64)) // 64k ME Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 38, 64)) // 64k ME Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 38, 64)) // 64k ME Storage Component
                .EUt(VA[MV].toLong())
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .buildAndRegister()

            // 64k ME Fluid Storage Cell
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_PHENOLIC_BOARD)
                .inputs(Mods.AppliedEnergistics2.getMetaItem("material", 56, 64)) // 16k ME Fluid Storage Component
                .input(circuit, MarkerMaterials.Tier.LV, 16)
                .input(wireGtHex, Gold, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 57, 64)) // 64k ME Fluid Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 57, 64)) // 64k ME Fluid Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 57, 64)) // 64k ME Fluid Storage Component
                .outputs(Mods.AppliedEnergistics2.getMetaItem("material", 57, 64)) // 64k ME Fluid Storage Component
                .EUt(VA[MV].toLong())
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .buildAndRegister()

        }

    }

}