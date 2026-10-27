package gregtechlite.gtlitecore.integration.appeng.recipe

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.MarkerMaterials.Tier
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
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.min
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CIRCUIT_ASSEMBLY_LINE_RECIPES
import gregtechlite.gtlitecore.api.recipe.util.circuitInfo
import gregtechlite.gtlitecore.api.recipe.util.createCircuitPatternRecipeFromItemStack
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_BASIC_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COATED_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_DIAMOND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_RUBY_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_SAPPHIRE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOD_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PHENOLIC_BOARD
import gregtechlite.gtlitecore.integration.appeng.item.AEItems.LOGIC_PROCESSOR
import gregtechlite.gtlitecore.integration.appeng.item.AEItems.CALCULATION_PROCESSOR
import gregtechlite.gtlitecore.integration.appeng.item.AEItems.ENGINEERING_PROCESSOR
import gregtechlite.gtlitecore.integration.appeng.item.AEItems.STORAGE_COMPONENT_1K
import gregtechlite.gtlitecore.integration.appeng.item.AEItems.STORAGE_COMPONENT_4K
import gregtechlite.gtlitecore.integration.appeng.item.AEItems.STORAGE_COMPONENT_16K
import gregtechlite.gtlitecore.integration.appeng.item.AEItems.STORAGE_COMPONENT_64K
import gregtechlite.gtlitecore.integration.appeng.item.AEItems.FLUID_STORAGE_COMPONENT_1K
import gregtechlite.gtlitecore.integration.appeng.item.AEItems.FLUID_STORAGE_COMPONENT_4K
import gregtechlite.gtlitecore.integration.appeng.item.AEItems.FLUID_STORAGE_COMPONENT_16K
import gregtechlite.gtlitecore.integration.appeng.item.AEItems.FLUID_STORAGE_COMPONENT_64K

/**
 * @see [gregtechlite.gtlitecore.loader.recipe.producer.CircuitAssemblyLineRecipeProducer]
 */
object AECircuitRecipeProducer
{
    // @formatter:off

    fun produce()
    {
        arrayOf(LOGIC_PROCESSOR, CALCULATION_PROCESSOR, ENGINEERING_PROCESSOR, STORAGE_COMPONENT_1K,
                STORAGE_COMPONENT_4K, STORAGE_COMPONENT_16K, STORAGE_COMPONENT_64K, FLUID_STORAGE_COMPONENT_1K,
                FLUID_STORAGE_COMPONENT_4K, FLUID_STORAGE_COMPONENT_16K, FLUID_STORAGE_COMPONENT_64K)
            .forEach { createCircuitPatternRecipeFromItemStack(it.stack) }

        // Logic Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_ENGRAVED_RUBY_CHIP)
            input(WRAP_BASIC_CIRCUIT_BOARD)
            input(wireGtHex, RedAlloy, 2)
            input(bolt, Gold, 64)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(LOGIC_PROCESSOR.getStack(64))
            outputs(LOGIC_PROCESSOR.getStack(64))
            EUt(VA[LV])
            duration(2.min) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(LOGIC_PROCESSOR.stack)
        }

        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_ENGRAVED_RUBY_CHIP)
            input(WRAP_GOOD_CIRCUIT_BOARD)
            input(wireGtHex, RedAlloy, 2)
            input(bolt, Electrum, 64)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(LOGIC_PROCESSOR.getStack(64))
            outputs(LOGIC_PROCESSOR.getStack(64))
            outputs(LOGIC_PROCESSOR.getStack(64))
            outputs(LOGIC_PROCESSOR.getStack(64))
            EUt(VA[LV])
            duration(2.min) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(LOGIC_PROCESSOR.stack)
        }

        // Calculation Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_ENGRAVED_SAPPHIRE_CHIP)
            input(WRAP_BASIC_CIRCUIT_BOARD)
            input(wireGtHex, RedAlloy, 2)
            input(bolt, Gold, 64)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(CALCULATION_PROCESSOR.getStack(64))
            outputs(CALCULATION_PROCESSOR.getStack(64))
            EUt(VA[LV])
            duration(2.min) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(CALCULATION_PROCESSOR.stack)
        }

        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_ENGRAVED_SAPPHIRE_CHIP)
            input(WRAP_GOOD_CIRCUIT_BOARD)
            input(wireGtHex, RedAlloy, 2)
            input(bolt, Electrum, 64)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(CALCULATION_PROCESSOR.getStack(64))
            outputs(CALCULATION_PROCESSOR.getStack(64))
            outputs(CALCULATION_PROCESSOR.getStack(64))
            outputs(CALCULATION_PROCESSOR.getStack(64))
            EUt(VA[LV])
            duration(2.min) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(CALCULATION_PROCESSOR.stack)
        }

        // Engineering Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_ENGRAVED_DIAMOND_CHIP)
            input(WRAP_BASIC_CIRCUIT_BOARD)
            input(wireGtHex, RedAlloy, 2)
            input(bolt, Gold, 64)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(ENGINEERING_PROCESSOR.getStack(64))
            outputs(ENGINEERING_PROCESSOR.getStack(64))
            EUt(VA[LV])
            duration(2.min) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(ENGINEERING_PROCESSOR.stack)
        }

        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_ENGRAVED_DIAMOND_CHIP)
            input(WRAP_GOOD_CIRCUIT_BOARD)
            input(wireGtHex, RedAlloy, 2)
            input(bolt, Electrum, 64)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(ENGINEERING_PROCESSOR.getStack(64))
            outputs(ENGINEERING_PROCESSOR.getStack(64))
            outputs(ENGINEERING_PROCESSOR.getStack(64))
            outputs(ENGINEERING_PROCESSOR.getStack(64))
            EUt(VA[LV])
            duration(2.min) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(ENGINEERING_PROCESSOR.stack)
        }

        // 1k ME Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_COATED_BOARD)
            inputs(LOGIC_PROCESSOR.getStack(16))
            input(foil, Gold, 64)
            input(bolt, Iron, 32)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(STORAGE_COMPONENT_1K.getStack(64))
            outputs(STORAGE_COMPONENT_1K.getStack(64))
            outputs(STORAGE_COMPONENT_1K.getStack(64))
            outputs(STORAGE_COMPONENT_1K.getStack(64))
            EUt(VA[LV])
            duration(1.min) // Original: 5s, Wrapped: 5s * 16 = 80s
            circuitInfo(STORAGE_COMPONENT_1K.stack)
        }

        // 1k ME Fluid Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_COATED_BOARD)
            inputs(CALCULATION_PROCESSOR.getStack(16))
            input(foil, Silver, 64)
            input(bolt, Iron, 32)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(FLUID_STORAGE_COMPONENT_1K.getStack(64))
            outputs(FLUID_STORAGE_COMPONENT_1K.getStack(64))
            outputs(FLUID_STORAGE_COMPONENT_1K.getStack(64))
            outputs(FLUID_STORAGE_COMPONENT_1K.getStack(64))
            EUt(VA[LV])
            duration(1.min) // Original: 5s, Wrapped: 5s * 16 = 80s
            circuitInfo(FLUID_STORAGE_COMPONENT_1K.stack)
        }

        // 4k ME Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_COATED_BOARD)
            inputs(STORAGE_COMPONENT_1K.getStack(16))
            inputs(ENGINEERING_PROCESSOR.getStack(16))
            input(wireGtHex, Lead, 2)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(STORAGE_COMPONENT_4K.getStack(64))
            outputs(STORAGE_COMPONENT_4K.getStack(64))
            outputs(STORAGE_COMPONENT_4K.getStack(64))
            outputs(STORAGE_COMPONENT_4K.getStack(64))
            EUt(VA[LV])
            duration(2.min) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(STORAGE_COMPONENT_4K.stack)
        }

        // 4k ME Fluid Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_COATED_BOARD)
            inputs(FLUID_STORAGE_COMPONENT_1K.getStack(16))
            inputs(ENGINEERING_PROCESSOR.getStack(16))
            input(wireGtHex, Lead, 2)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(FLUID_STORAGE_COMPONENT_4K.getStack(64))
            outputs(FLUID_STORAGE_COMPONENT_4K.getStack(64))
            outputs(FLUID_STORAGE_COMPONENT_4K.getStack(64))
            outputs(FLUID_STORAGE_COMPONENT_4K.getStack(64))
            EUt(VA[LV])
            duration(2.min) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(FLUID_STORAGE_COMPONENT_4K.stack)
        }

        // 16k ME Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_PHENOLIC_BOARD)
            inputs(STORAGE_COMPONENT_4K.getStack(16))
            input(circuit, Tier.ULV, 16)
            input(wireGtHex, AnnealedCopper, 2)
            fluidInputs(SolderingAlloy.getFluid(L))
            outputs(STORAGE_COMPONENT_16K.getStack(64))
            outputs(STORAGE_COMPONENT_16K.getStack(64))
            outputs(STORAGE_COMPONENT_16K.getStack(64))
            outputs(STORAGE_COMPONENT_16K.getStack(64))
            EUt(VA[LV])
            duration(3.min) // Original: 15s, Wrapped: 15s * 16 = 240s
            circuitInfo(STORAGE_COMPONENT_16K.stack)
        }

        // 16k ME Fluid Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_PHENOLIC_BOARD)
            inputs(FLUID_STORAGE_COMPONENT_4K.getStack(16))
            input(circuit, Tier.ULV, 16)
            input(wireGtHex, AnnealedCopper, 2)
            fluidInputs(SolderingAlloy.getFluid(L))
            outputs(FLUID_STORAGE_COMPONENT_16K.getStack(64))
            outputs(FLUID_STORAGE_COMPONENT_16K.getStack(64))
            outputs(FLUID_STORAGE_COMPONENT_16K.getStack(64))
            outputs(FLUID_STORAGE_COMPONENT_16K.getStack(64))
            EUt(VA[LV])
            duration(3.min) // Original: 15s, Wrapped: 15s * 16 = 240s
            circuitInfo(FLUID_STORAGE_COMPONENT_16K.stack)
        }

        // 64k ME Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_PHENOLIC_BOARD)
            inputs(STORAGE_COMPONENT_16K.getStack(16))
            input(circuit, Tier.LV, 16)
            input(wireGtHex, Gold, 2)
            fluidInputs(SolderingAlloy.getFluid(L))
            outputs(STORAGE_COMPONENT_64K.getStack(64))
            outputs(STORAGE_COMPONENT_64K.getStack(64))
            outputs(STORAGE_COMPONENT_64K.getStack(64))
            outputs(STORAGE_COMPONENT_64K.getStack(64))
            EUt(VA[MV])
            duration(4.min) // Original: 20s, Wrapped: 20s * 16 = 320s
            circuitInfo(STORAGE_COMPONENT_64K.stack)
        }

        // 64k ME Fluid Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_PHENOLIC_BOARD)
            inputs(FLUID_STORAGE_COMPONENT_16K.getStack(16))
            input(circuit, Tier.LV, 16)
            input(wireGtHex, Gold, 2)
            fluidInputs(SolderingAlloy.getFluid(L))
            outputs(FLUID_STORAGE_COMPONENT_64K.getStack(64))
            outputs(FLUID_STORAGE_COMPONENT_64K.getStack(64))
            outputs(FLUID_STORAGE_COMPONENT_64K.getStack(64))
            outputs(FLUID_STORAGE_COMPONENT_64K.getStack(64))
            EUt(VA[MV])
            duration(4.min) // Original: 20s, Wrapped: 20s * 16 = 320s
            circuitInfo(FLUID_STORAGE_COMPONENT_64K.stack)
        }
    }

    // @formatter:on
}