package magicbook.gtlitecore.integration.appliedenergistics2.recipe

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.RedAlloy
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CIRCUIT_ASSEMBLY_LINE_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.Mods
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_BASIC_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_DIAMOND_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_RUBY_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_SAPPHIRE_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOD_CIRCUIT_BOARD

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

            // Logic processor
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


            // Calculation processor
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

            // Engineering processor
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

        }

    }

}