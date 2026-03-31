package gregtechlite.gtlitecore.integration.appliedenergistics2.recipe

import com.morphismmc.morphismlib.integration.Mods
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtechlite.gtlitecore.api.extension.addRecipe
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
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.extension.EUt
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
import net.minecraft.item.ItemStack

/**
 * @see [gregtechlite.gtlitecore.loader.recipe.producer.CircuitAssemblyLineRecipeProducer]
 */
object AppEngCALRecipeProducer
{
    // Enum for AE2 items, to avoid using magic numbers for item meta.
    enum class AE2Item(val itemName: String, val itemMeta: Int) {
        LOGIC_PROCESSOR("material", 22),
        CALCULATION_PROCESSOR("material", 23),
        ENGINEERING_PROCESSOR("material", 24),
        STORAGE_COMPONENT_1K("material", 35),
        STORAGE_COMPONENT_4K("material", 36),
        STORAGE_COMPONENT_16K("material", 37),
        STORAGE_COMPONENT_64K("material", 38),
        FLUID_STORAGE_COMPONENT_1K("material", 54),
        FLUID_STORAGE_COMPONENT_4K("material", 55),
        FLUID_STORAGE_COMPONENT_16K("material", 56),
        FLUID_STORAGE_COMPONENT_64K("material", 57);

        fun getStack(amount: Int = 1): ItemStack {
            return Mods.AppliedEnergistics2.getItem(itemName, itemMeta, amount)
        }
    }

    // @formatter:off

    fun produce()
    {

        // Traverse all the AE2 items and create circuit pattern recipes for them.
        AE2Item.entries.forEach {
            createCircuitPatternRecipeFromItemStack(it.getStack())
        }

        // Logic Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_ENGRAVED_RUBY_CHIP)
            input(WRAP_BASIC_CIRCUIT_BOARD)
            input(wireGtHex, RedAlloy, 2)
            input(bolt, Gold, 64)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(AE2Item.LOGIC_PROCESSOR.getStack(64))
            outputs(AE2Item.LOGIC_PROCESSOR.getStack(64))
            EUt(VA[LV])
            duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(AE2Item.LOGIC_PROCESSOR.getStack())
        }

        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_ENGRAVED_RUBY_CHIP)
            input(WRAP_GOOD_CIRCUIT_BOARD)
            input(wireGtHex, RedAlloy, 2)
            input(bolt, Electrum, 64)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(AE2Item.LOGIC_PROCESSOR.getStack(64))
            outputs(AE2Item.LOGIC_PROCESSOR.getStack(64))
            outputs(AE2Item.LOGIC_PROCESSOR.getStack(64))
            outputs(AE2Item.LOGIC_PROCESSOR.getStack(64))
            EUt(VA[LV])
            duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(AE2Item.LOGIC_PROCESSOR.getStack())
        }

        // Calculation Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_ENGRAVED_SAPPHIRE_CHIP)
            input(WRAP_BASIC_CIRCUIT_BOARD)
            input(wireGtHex, RedAlloy, 2)
            input(bolt, Gold, 64)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(AE2Item.CALCULATION_PROCESSOR.getStack(64))
            outputs(AE2Item.CALCULATION_PROCESSOR.getStack(64))
            EUt(VA[LV])
            duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(AE2Item.CALCULATION_PROCESSOR.getStack())
        }

        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_ENGRAVED_SAPPHIRE_CHIP)
            input(WRAP_GOOD_CIRCUIT_BOARD)
            input(wireGtHex, RedAlloy, 2)
            input(bolt, Electrum, 64)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(AE2Item.CALCULATION_PROCESSOR.getStack(64))
            outputs(AE2Item.CALCULATION_PROCESSOR.getStack(64))
            outputs(AE2Item.CALCULATION_PROCESSOR.getStack(64))
            outputs(AE2Item.CALCULATION_PROCESSOR.getStack(64))
            EUt(VA[LV])
            duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(AE2Item.CALCULATION_PROCESSOR.getStack())
        }

        // Engineering Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_ENGRAVED_DIAMOND_CHIP)
            input(WRAP_BASIC_CIRCUIT_BOARD)
            input(wireGtHex, RedAlloy, 2)
            input(bolt, Gold, 64)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(AE2Item.ENGINEERING_PROCESSOR.getStack(64))
            outputs(AE2Item.ENGINEERING_PROCESSOR.getStack(64))
            EUt(VA[LV])
            duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(AE2Item.ENGINEERING_PROCESSOR.getStack())
        }

        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_ENGRAVED_DIAMOND_CHIP)
            input(WRAP_GOOD_CIRCUIT_BOARD)
            input(wireGtHex, RedAlloy, 2)
            input(bolt, Electrum, 64)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(AE2Item.ENGINEERING_PROCESSOR.getStack(64))
            outputs(AE2Item.ENGINEERING_PROCESSOR.getStack(64))
            outputs(AE2Item.ENGINEERING_PROCESSOR.getStack(64))
            outputs(AE2Item.ENGINEERING_PROCESSOR.getStack(64))
            EUt(VA[LV])
            duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(AE2Item.ENGINEERING_PROCESSOR.getStack())
        }

        // 1k ME Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_COATED_BOARD)
            inputs(AE2Item.LOGIC_PROCESSOR.getStack(16))
            input(foil, Gold, 64)
            input(bolt, Iron, 32)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(AE2Item.STORAGE_COMPONENT_1K.getStack(64))
            outputs(AE2Item.STORAGE_COMPONENT_1K.getStack(64))
            outputs(AE2Item.STORAGE_COMPONENT_1K.getStack(64))
            outputs(AE2Item.STORAGE_COMPONENT_1K.getStack(64))
            EUt(VA[LV])
            duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            circuitInfo(AE2Item.STORAGE_COMPONENT_1K.getStack())
        }

        // 1k ME Fluid Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_COATED_BOARD)
            inputs(AE2Item.CALCULATION_PROCESSOR.getStack(16))
            input(foil, Silver, 64)
            input(bolt, Iron, 32)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_1K.getStack(64))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_1K.getStack(64))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_1K.getStack(64))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_1K.getStack(64))
            EUt(VA[LV])
            duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            circuitInfo(AE2Item.FLUID_STORAGE_COMPONENT_1K.getStack())
        }

        // 4k ME Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_COATED_BOARD)
            inputs(AE2Item.STORAGE_COMPONENT_1K.getStack(16))
            inputs(AE2Item.ENGINEERING_PROCESSOR.getStack(16))
            input(wireGtHex, Lead, 2)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(AE2Item.STORAGE_COMPONENT_4K.getStack(64))
            outputs(AE2Item.STORAGE_COMPONENT_4K.getStack(64))
            outputs(AE2Item.STORAGE_COMPONENT_4K.getStack(64))
            outputs(AE2Item.STORAGE_COMPONENT_4K.getStack(64))
            EUt(VA[LV])
            duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(AE2Item.STORAGE_COMPONENT_4K.getStack())
        }

        // 4k ME Fluid Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_COATED_BOARD)
            inputs(AE2Item.FLUID_STORAGE_COMPONENT_1K.getStack(16))
            inputs(AE2Item.ENGINEERING_PROCESSOR.getStack(16))
            input(wireGtHex, Lead, 2)
            fluidInputs(SolderingAlloy.getFluid(L / 2))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_4K.getStack(64))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_4K.getStack(64))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_4K.getStack(64))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_4K.getStack(64))
            EUt(VA[LV])
            duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            circuitInfo(AE2Item.FLUID_STORAGE_COMPONENT_4K.getStack())
        }

        // 16k ME Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_PHENOLIC_BOARD)
            inputs(AE2Item.STORAGE_COMPONENT_4K.getStack(16))
            input(circuit, Tier.ULV, 16)
            input(wireGtHex, AnnealedCopper, 2)
            fluidInputs(SolderingAlloy.getFluid(L))
            outputs(AE2Item.STORAGE_COMPONENT_16K.getStack(64))
            outputs(AE2Item.STORAGE_COMPONENT_16K.getStack(64))
            outputs(AE2Item.STORAGE_COMPONENT_16K.getStack(64))
            outputs(AE2Item.STORAGE_COMPONENT_16K.getStack(64))
            EUt(VA[LV])
            duration(3 * MINUTE) // Original: 15s, Wrapped: 15s * 16 = 240s
            circuitInfo(AE2Item.STORAGE_COMPONENT_16K.getStack())
        }

        // 16k ME Fluid Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_PHENOLIC_BOARD)
            inputs(AE2Item.FLUID_STORAGE_COMPONENT_4K.getStack(16))
            input(circuit, Tier.ULV, 16)
            input(wireGtHex, AnnealedCopper, 2)
            fluidInputs(SolderingAlloy.getFluid(L))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_16K.getStack(64))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_16K.getStack(64))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_16K.getStack(64))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_16K.getStack(64))
            EUt(VA[LV])
            duration(3 * MINUTE) // Original: 15s, Wrapped: 15s * 16 = 240s
            circuitInfo(AE2Item.FLUID_STORAGE_COMPONENT_16K.getStack())
        }

        // 64k ME Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_PHENOLIC_BOARD)
            inputs(AE2Item.STORAGE_COMPONENT_16K.getStack(16))
            input(circuit, Tier.LV, 16)
            input(wireGtHex, Gold, 2)
            fluidInputs(SolderingAlloy.getFluid(L))
            outputs(AE2Item.STORAGE_COMPONENT_64K.getStack(64))
            outputs(AE2Item.STORAGE_COMPONENT_64K.getStack(64))
            outputs(AE2Item.STORAGE_COMPONENT_64K.getStack(64))
            outputs(AE2Item.STORAGE_COMPONENT_64K.getStack(64))
            EUt(VA[MV])
            duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            circuitInfo(AE2Item.STORAGE_COMPONENT_64K.getStack())
        }

        // 64k ME Fluid Storage Cell
        CIRCUIT_ASSEMBLY_LINE_RECIPES.addRecipe {
            input(WRAP_PHENOLIC_BOARD)
            inputs(AE2Item.FLUID_STORAGE_COMPONENT_16K.getStack(16))
            input(circuit, Tier.LV, 16)
            input(wireGtHex, Gold, 2)
            fluidInputs(SolderingAlloy.getFluid(L))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_64K.getStack(64))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_64K.getStack(64))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_64K.getStack(64))
            outputs(AE2Item.FLUID_STORAGE_COMPONENT_64K.getStack(64))
            EUt(VA[MV])
            duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            circuitInfo(AE2Item.FLUID_STORAGE_COMPONENT_64K.getStack())
        }

    }

    // @formatter:on

}