package magicbook.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.RecipeMaps.SCANNER_RECIPES
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.RedAlloy
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.common.items.MetaItems.CRYSTAL_ASSEMBLY_LUV
import gregtech.common.items.MetaItems.CRYSTAL_COMPUTER_ZPM
import gregtech.common.items.MetaItems.CRYSTAL_MAINFRAME_UV
import gregtech.common.items.MetaItems.CRYSTAL_PROCESSOR_IV
import gregtech.common.items.MetaItems.ELECTRONIC_CIRCUIT_LV
import gregtech.common.items.MetaItems.ELECTRONIC_CIRCUIT_MV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_HV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_LV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_MV
import gregtech.common.items.MetaItems.MAINFRAME_IV
import gregtech.common.items.MetaItems.MICROPROCESSOR_LV
import gregtech.common.items.MetaItems.NAND_CHIP_ULV
import gregtech.common.items.MetaItems.NANO_COMPUTER_IV
import gregtech.common.items.MetaItems.NANO_MAINFRAME_LUV
import gregtech.common.items.MetaItems.NANO_PROCESSOR_ASSEMBLY_EV
import gregtech.common.items.MetaItems.NANO_PROCESSOR_HV
import gregtech.common.items.MetaItems.PROCESSOR_ASSEMBLY_HV
import gregtech.common.items.MetaItems.PROCESSOR_MV
import gregtech.common.items.MetaItems.QUANTUM_ASSEMBLY_IV
import gregtech.common.items.MetaItems.QUANTUM_COMPUTER_LUV
import gregtech.common.items.MetaItems.QUANTUM_MAINFRAME_ZPM
import gregtech.common.items.MetaItems.QUANTUM_PROCESSOR_EV
import gregtech.common.items.MetaItems.VACUUM_TUBE
import gregtech.common.items.MetaItems.WETWARE_MAINFRAME_UHV
import gregtech.common.items.MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM
import gregtech.common.items.MetaItems.WETWARE_PROCESSOR_LUV
import gregtech.common.items.MetaItems.WETWARE_SUPER_COMPUTER_UV
import gregtech.common.items.MetaItems.WORKSTATION_EV
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CIRCUIT_ASSEMBLY_LINE_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CIRCUIT_PATTERN
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_ASSEMBLY_UIV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_COMPUTER_UXV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_MAINFRAME_OpV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.COSMIC_PROCESSOR_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_ASSEMBLY_UV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_COMPUTER_UHV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_MAINFRAME_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_PROCESSOR_ZPM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_ASSEMBLY_UHV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_COMPUTER_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_MAINFRAME_UIV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_PROCESSOR_UV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_ASSEMBLY_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_COMPUTER_UIV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_MAINFRAME_UXV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_PROCESSOR_UHV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_ASSEMBLY_UXV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_COMPUTER_OpV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_MAINFRAME_MAX
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_PROCESSOR_UIV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_BASIC_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOD_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ILC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_RAM_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_TRANSISTOR
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagString

@Suppress("MISSING_DEPENDENCY_CLASS")
class CircuitAssemblyLineRecipeProducer
{

    companion object
    {

        @JvmField
        val INFO_NBT_NAME = "CircuitInfo"

        /**
         * Raw rules:
         *
         * - If recipe needs bolt number >64, then regularized it to 64.
         * - Used same soldering consumed of Circuit Assembler recipes.
         * - Each 4x SMD -> 1x Advanced SMD, each 4x Advanced SMD -> 1x Gooware SMD, ...
         * - Has duration buffing, e.g. original duration 10s, we see that 10s * 16 = 160s,
         *   but the actual wrapped duration should be 60s (160s - 100s).
         */
        fun produce()
        {
            addCircuit(VACUUM_TUBE)
            addCircuit(NAND_CHIP_ULV)

            addCircuit(ELECTRONIC_CIRCUIT_LV)
            addCircuit(INTEGRATED_CIRCUIT_LV)
            addCircuit(MICROPROCESSOR_LV)

            addCircuit(ELECTRONIC_CIRCUIT_MV)
            addCircuit(INTEGRATED_CIRCUIT_MV)
            addCircuit(PROCESSOR_MV)

            addCircuit(INTEGRATED_CIRCUIT_HV)
            addCircuit(PROCESSOR_ASSEMBLY_HV)
            addCircuit(NANO_PROCESSOR_HV)

            addCircuit(WORKSTATION_EV)
            addCircuit(NANO_PROCESSOR_ASSEMBLY_EV)
            addCircuit(QUANTUM_PROCESSOR_EV)

            addCircuit(MAINFRAME_IV)
            addCircuit(NANO_COMPUTER_IV)
            addCircuit(QUANTUM_ASSEMBLY_IV)
            addCircuit(CRYSTAL_PROCESSOR_IV)

            addCircuit(NANO_MAINFRAME_LUV)
            addCircuit(QUANTUM_COMPUTER_LUV)
            addCircuit(CRYSTAL_ASSEMBLY_LUV)
            addCircuit(WETWARE_PROCESSOR_LUV)

            addCircuit(QUANTUM_MAINFRAME_ZPM)
            addCircuit(CRYSTAL_COMPUTER_ZPM)
            addCircuit(WETWARE_PROCESSOR_ASSEMBLY_ZPM)
            addCircuit(GOOWARE_PROCESSOR_ZPM)

            addCircuit(CRYSTAL_MAINFRAME_UV)
            addCircuit(WETWARE_SUPER_COMPUTER_UV)
            addCircuit(GOOWARE_ASSEMBLY_UV)
            addCircuit(OPTICAL_PROCESSOR_UV)

            addCircuit(WETWARE_MAINFRAME_UHV)
            addCircuit(GOOWARE_COMPUTER_UHV)
            addCircuit(OPTICAL_ASSEMBLY_UHV)
            addCircuit(SPINTRONIC_PROCESSOR_UHV)

            addCircuit(GOOWARE_MAINFRAME_UEV)
            addCircuit(OPTICAL_COMPUTER_UEV)
            addCircuit(SPINTRONIC_ASSEMBLY_UEV)
            addCircuit(COSMIC_PROCESSOR_UEV)

            addCircuit(OPTICAL_MAINFRAME_UIV)
            addCircuit(SPINTRONIC_COMPUTER_UIV)
            addCircuit(COSMIC_ASSEMBLY_UIV)
            addCircuit(SUPRACAUSAL_PROCESSOR_UIV)

            addCircuit(SPINTRONIC_MAINFRAME_UXV)
            addCircuit(COSMIC_COMPUTER_UXV)
            addCircuit(SUPRACAUSAL_ASSEMBLY_UXV)

            addCircuit(COSMIC_MAINFRAME_OpV)
            addCircuit(SUPRACAUSAL_COMPUTER_OpV)

            addCircuit(SUPRACAUSAL_MAINFRAME_MAX)

            // =========================================================================================================

            // T1: Electronic

            // Electronic Circuit
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_BASIC_CIRCUIT_BOARD)
                .input(WRAP_SMD_RESISTOR, 2)
                .input(VACUUM_TUBE, 32)
                .input(wireGtHex, RedAlloy, 2)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(ELECTRONIC_CIRCUIT_LV, 64)
                .EUt(VH[LV].toLong())
                .duration(1 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(ELECTRONIC_CIRCUIT_LV))
                .buildAndRegister()

            // Good Electronic Circuit
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_GOOD_CIRCUIT_BOARD)
                .input(ELECTRONIC_CIRCUIT_LV, 32)
                .input(WRAP_SMD_DIODE, 2)
                .input(wireGtHex, Copper, 2)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(ELECTRONIC_CIRCUIT_MV, 32)
                .EUt(VH[LV].toLong())
                .duration(1 * MINUTE + 20 * SECOND) // Original: 15s, Wrapped: 15s * 16 = 240s
                .circuit(getCircuit(ELECTRONIC_CIRCUIT_MV))
                .buildAndRegister()

            // T2: Integrated

            // Integrated Circuit
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_BASIC_CIRCUIT_BOARD)
                .input(WRAP_ILC_CHIP)
                .input(WRAP_SMD_RESISTOR, 2)
                .input(WRAP_SMD_RESISTOR, 2)
                .input(wireGtQuadruple, Copper, 2)
                .input(bolt, Tin, 32)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(INTEGRATED_CIRCUIT_LV, 64)
                .EUt(VH[LV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(INTEGRATED_CIRCUIT_LV))
                .buildAndRegister()

            // Good Integrated Circuit
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_GOOD_CIRCUIT_BOARD)
                .input(INTEGRATED_CIRCUIT_LV, 64)
                .input(WRAP_SMD_RESISTOR, 2)
                .input(WRAP_SMD_DIODE, 2)
                .input(wireGtQuadruple, Gold, 4)
                .input(bolt, Silver, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(INTEGRATED_CIRCUIT_MV, 48)
                .EUt(24) // LV
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .circuit(getCircuit(INTEGRATED_CIRCUIT_MV))
                .buildAndRegister()

            // Advanced Integrated Circuit
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(INTEGRATED_CIRCUIT_MV, 48)
                .input(WRAP_ILC_CHIP, 2)
                .input(WRAP_RAM_CHIP, 2)
                .input(WRAP_SMD_TRANSISTOR, 4)
                .input(wireGtQuadruple, Electrum, 8)
                .input(bolt, AnnealedCopper, 64)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(INTEGRATED_CIRCUIT_HV, 32)
                .EUt(VA[LV].toLong())
                .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
                .circuit(getCircuit(INTEGRATED_CIRCUIT_HV))
                .buildAndRegister()
        }

        private fun addCircuit(circuit: MetaItem<*>.MetaValueItem)
        {
            // Circuit Pattern with NBT {"CircuitInfo": "${circuit.unlocalizedName}"}.
            val circuitPattern: ItemStack = CIRCUIT_PATTERN.stackForm
            circuitPattern.setTagInfo(INFO_NBT_NAME, NBTTagString(circuit.unlocalizedName))
            // Add Scanning recipes of Circuit Pattern.
            SCANNER_RECIPES.recipeBuilder()
                .input(CIRCUIT_PATTERN)
                .input(circuit)
                .outputs(circuitPattern)
                .EUt(VA[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

        fun getCircuit(circuit: MetaItem<*>.MetaValueItem): ItemStack
        {
            val circuitPattern: ItemStack = CIRCUIT_PATTERN.stackForm
            circuitPattern.setTagInfo(INFO_NBT_NAME, NBTTagString(circuit.unlocalizedName))
            return circuitPattern
        }

    }

}