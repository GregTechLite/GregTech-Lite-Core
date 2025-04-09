package magicbook.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.VHA
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.RecipeMaps.SCANNER_RECIPES
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.BlueAlloy
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.RedAlloy
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
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
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CRYSTAL_SOC_SOCKET
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DIAMOND_MODULATOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_ASSEMBLY_UV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_COMPUTER_UHV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_MAINFRAME_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_PROCESSOR_ZPM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_ASSEMBLY_UHV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_COMPUTER_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_MAINFRAME_UIV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.OPTICAL_PROCESSOR_UV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.RUBY_MODULATOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SAPPHIRE_MODULATOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_ASSEMBLY_UEV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_COMPUTER_UIV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_MAINFRAME_UXV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SPINTRONIC_PROCESSOR_UHV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_ASSEMBLY_UXV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_COMPUTER_OpV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_MAINFRAME_MAX
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SUPRACAUSAL_PROCESSOR_UIV
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ADVANCED_SOC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_BASIC_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CPU_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CRYSTAL_CPU
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CRYSTAL_INTERFACE_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_CRYSTAL_SOC
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ELITE_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_DIAMOND_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_RUBY_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ENGRAVED_SAPPHIRE_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_EXTREME_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOD_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOWARE_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOWARE_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_GOOWARE_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_ILC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_NAND_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_NANO_CPU_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_NOR_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_PLASTIC_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_QUBIT_CPU_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_RAM_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SIMPLE_SOC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_DIODE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_RESISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SMD_TRANSISTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.WRAP_SOC_CHIP
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagString

/**
 * CAL recipe producer and its utility methods.
 *
 * This producer add all circuit recipes and its scanning recipes for CAL, the raw materials
 * processing please see [WrapItemRecipeProducer].
 *
 * Here's the rule of CAL recipes:
 * - **Bolt Reduction**: If CAL recipe needs >64x bolt, then regularized it to 64x.
 * - **Same Soldering**: CAL used same amount soldering of Circuit Assembler recipes.
 * - **Advanced Recipes**: In CAL recipe, can use higher SMD to change lower SMD:
 *    - SMD -> Advanced SMD: Amount 4:1, Duration / 4;
 *    - Advanced SMD -> Gooware SMD: Amount 4:1, Duration / 4;
 *    - Gooware SMD -> Optical SMD: Amount 4:1 (16:1), Duration / 4 (/16);
 *    - Optical SMD -> Spintronic SMD: Amount 4:1 (64:1), Duration / 4 (/64);
 *    - Spintronic SMD -> Cosmic SMD: Amount 4:1 (256:1), Duration / 4 (/256);
 *    - Cosmic SMD -> Supracausal SMD: Amount 4:1 (512:1), Duration / 4 (/512);
 * - **Duration Buffing**: For example, if a circuit in Circuit Assembler needs 10s,
 *   we see that 10s * 16 = 160s, but the actual wrapped recipe in CAL needs 120s,
 *   even 60s (lower than original duration); another situation is one and another
 *   circuit has same duration but the first one has lower tier than the second one,
 *   in that time should balance the duration (the lower one should have lower duration
 *   consumed, and the higher one should have higher duration consumed).
 * - **Wire Conversion**: Used [wireGtHex] when used [wireGtSingle] in Circuit Assembly
 *   recipe, used [wireGtQuadruple] when used [wireFine] in Circuit Assembly recipe.
 */
@Suppress("MISSING_DEPENDENCY_CLASS")
class CircuitAssemblyLineRecipeProducer
{

    companion object
    {

        @JvmField
        val INFO_NBT_NAME = "CircuitInfo"

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

            // T3: Processor

            // NAND Chip
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_GOOD_CIRCUIT_BOARD)
                .input(WRAP_SIMPLE_SOC_CHIP)
                .input(bolt, RedAlloy, 32)
                .input(wireGtQuadruple, Tin, 2)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(NAND_CHIP_ULV, 64)
                .output(NAND_CHIP_ULV, 64)
                .EUt(VA[MV].toLong())
                .duration(3 * MINUTE) // Original: 15s, Wrapped: 15s * 16 = 240s
                .circuit(getCircuit(NAND_CHIP_ULV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_PLASTIC_CIRCUIT_BOARD)
                .input(WRAP_SIMPLE_SOC_CHIP)
                .input(bolt, RedAlloy, 32)
                .input(wireGtQuadruple, Tin, 2)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(NAND_CHIP_ULV, 64)
                .output(NAND_CHIP_ULV, 64)
                .output(NAND_CHIP_ULV, 64)
                .output(NAND_CHIP_ULV, 64)
                .EUt(VA[MV].toLong())
                .duration(3 * MINUTE) // Original: 15s, Wrapped: 15s * 16 = 240s
                .circuit(getCircuit(NAND_CHIP_ULV))
                .buildAndRegister()

            // Microprocessor
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_PLASTIC_CIRCUIT_BOARD)
                .input(WRAP_CPU_CHIP)
                .input(WRAP_SMD_RESISTOR, 2)
                .input(WRAP_SMD_CAPACITOR, 2)
                .input(WRAP_SMD_TRANSISTOR, 2)
                .input(wireGtQuadruple, Copper)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(MICROPROCESSOR_LV, 64)
                .EUt(VHA[MV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(MICROPROCESSOR_LV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_PLASTIC_CIRCUIT_BOARD)
                .input(WRAP_SOC_CHIP)
                .input(wireGtQuadruple, Copper, 2)
                .input(bolt, Tin, 32)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(MICROPROCESSOR_LV, 64)
                .output(MICROPROCESSOR_LV, 64)
                .EUt(600) // EV
                .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
                .circuit(getCircuit(MICROPROCESSOR_LV))
                .buildAndRegister()

            // Processor
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_PLASTIC_CIRCUIT_BOARD)
                .input(WRAP_CPU_CHIP)
                .input(WRAP_SMD_RESISTOR, 4)
                .input(WRAP_SMD_CAPACITOR, 4)
                .input(WRAP_SMD_TRANSISTOR, 4)
                .input(wireGtQuadruple, RedAlloy, 4)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(PROCESSOR_MV, 64)
                .EUt(VHA[MV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(PROCESSOR_MV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_PLASTIC_CIRCUIT_BOARD)
                .input(WRAP_SOC_CHIP)
                .input(wireGtQuadruple, RedAlloy, 4)
                .input(bolt, AnnealedCopper, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(PROCESSOR_MV, 64)
                .output(PROCESSOR_MV, 64)
                .EUt(2400) // IV
                .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
                .circuit(getCircuit(PROCESSOR_MV))
                .buildAndRegister()

            // Processor Assembly
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_PLASTIC_CIRCUIT_BOARD)
                .input(PROCESSOR_MV, 64)
                .input(WRAP_SMD_INDUCTOR, 4)
                .input(WRAP_SMD_CAPACITOR, 8)
                .input(WRAP_RAM_CHIP, 4)
                .input(wireGtQuadruple, RedAlloy, 8)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(PROCESSOR_ASSEMBLY_HV, 48)
                .EUt(90) // MV
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .circuit(getCircuit(PROCESSOR_ASSEMBLY_HV))
                .buildAndRegister()

            // Workstation
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_PLASTIC_CIRCUIT_BOARD)
                .input(PROCESSOR_ASSEMBLY_HV, 48)
                .input(WRAP_SMD_DIODE, 4)
                .input(WRAP_RAM_CHIP, 4)
                .input(wireGtQuadruple, Electrum, 16)
                .input(bolt, BlueAlloy, 64)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(WORKSTATION_EV, 32)
                .EUt(VA[MV].toLong())
                .duration(5 * MINUTE) // Original: 20s, Wrapped: 20ss * 16 = 320s
                .circuit(getCircuit(WORKSTATION_EV))
                .buildAndRegister()

            // Mainframe
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Aluminium, 32)
                .input(WORKSTATION_EV, 32)
                .input(WRAP_SMD_INDUCTOR, 8)
                .input(WRAP_SMD_CAPACITOR, 16)
                .input(WRAP_RAM_CHIP, 16)
                .input(wireGtHex, AnnealedCopper, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .output(MAINFRAME_IV, 16)
                .EUt(VA[HV].toLong())
                .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
                .circuit(getCircuit(MAINFRAME_IV))
                .buildAndRegister()

            // T4: Nano

            // Nano Processor
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ADVANCED_CIRCUIT_BOARD)
                .input(WRAP_NANO_CPU_CHIP)
                .input(WRAP_SMD_RESISTOR, 8)
                .input(WRAP_SMD_CAPACITOR, 8)
                .input(WRAP_SMD_TRANSISTOR, 8)
                .input(wireGtQuadruple, Electrum, 8)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(NANO_PROCESSOR_HV, 64)
                .EUt(600) // EV
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(NANO_PROCESSOR_HV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ADVANCED_CIRCUIT_BOARD)
                .input(WRAP_NANO_CPU_CHIP)
                .input(WRAP_ADVANCED_SMD_RESISTOR, 2)
                .input(WRAP_ADVANCED_SMD_CAPACITOR, 2)
                .input(WRAP_ADVANCED_SMD_TRANSISTOR, 2)
                .input(wireGtQuadruple, Electrum, 8)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(NANO_PROCESSOR_HV, 64)
                .EUt(600) // EV
                .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
                .circuit(getCircuit(NANO_PROCESSOR_HV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ADVANCED_CIRCUIT_BOARD)
                .input(WRAP_ADVANCED_SOC_CHIP)
                .input(wireGtQuadruple, Electrum, 4)
                .input(bolt, Platinum, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(NANO_PROCESSOR_HV, 64)
                .output(NANO_PROCESSOR_HV, 64)
                .EUt(9600) // LuV
                .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
                .circuit(getCircuit(NANO_PROCESSOR_HV))
                .buildAndRegister()

            // Nano Processor Assembly
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ADVANCED_CIRCUIT_BOARD)
                .input(NANO_PROCESSOR_HV, 64)
                .input(WRAP_SMD_INDUCTOR, 4)
                .input(WRAP_SMD_CAPACITOR, 8)
                .input(WRAP_RAM_CHIP, 8)
                .input(wireGtQuadruple, Electrum, 16)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(NANO_PROCESSOR_ASSEMBLY_EV, 48)
                .EUt(600) // EV
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .circuit(getCircuit(NANO_PROCESSOR_ASSEMBLY_EV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ADVANCED_CIRCUIT_BOARD)
                .input(NANO_PROCESSOR_HV, 64)
                .input(WRAP_ADVANCED_SMD_INDUCTOR)
                .input(WRAP_ADVANCED_SMD_CAPACITOR, 2)
                .input(WRAP_RAM_CHIP, 8)
                .input(wireGtQuadruple, Electrum, 16)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(NANO_PROCESSOR_ASSEMBLY_EV, 48)
                .EUt(600) // EV
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(NANO_PROCESSOR_ASSEMBLY_EV))
                .buildAndRegister()

            // Nano Computer
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ADVANCED_CIRCUIT_BOARD)
                .input(NANO_PROCESSOR_ASSEMBLY_EV, 48)
                .input(WRAP_SMD_DIODE, 8)
                .input(WRAP_NOR_CHIP, 4)
                .input(WRAP_RAM_CHIP, 16)
                .input(wireGtQuadruple, Electrum, 16)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(NANO_COMPUTER_IV, 32)
                .EUt(600) // EV
                .duration(5 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .circuit(getCircuit(NANO_COMPUTER_IV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ADVANCED_CIRCUIT_BOARD)
                .input(NANO_PROCESSOR_ASSEMBLY_EV, 48)
                .input(WRAP_ADVANCED_SMD_DIODE, 2)
                .input(WRAP_NOR_CHIP, 4)
                .input(WRAP_RAM_CHIP, 16)
                .input(wireGtQuadruple, Electrum, 16)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(NANO_COMPUTER_IV, 32)
                .EUt(600) // EV
                .duration(2 * MINUTE + 30 * SECOND) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(NANO_COMPUTER_IV))
                .buildAndRegister()

            // Nano Mainframe
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, VanadiumSteel, 32)
                .input(NANO_COMPUTER_IV, 32)
                .input(WRAP_SMD_INDUCTOR, 16)
                .input(WRAP_SMD_CAPACITOR, 32)
                .input(WRAP_RAM_CHIP, 16)
                .input(wireGtHex, AnnealedCopper, 32)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .output(NANO_MAINFRAME_LUV, 16)
                .EUt(VA[EV].toLong())
                .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
                .circuit(getCircuit(NANO_MAINFRAME_LUV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, VanadiumSteel, 32)
                .input(NANO_COMPUTER_IV, 32)
                .input(WRAP_ADVANCED_SMD_INDUCTOR, 4)
                .input(WRAP_ADVANCED_SMD_CAPACITOR, 8)
                .input(WRAP_RAM_CHIP, 16)
                .input(wireGtHex, AnnealedCopper, 32)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .output(NANO_MAINFRAME_LUV, 16)
                .EUt(VA[EV].toLong())
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .circuit(getCircuit(NANO_MAINFRAME_LUV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, VanadiumSteel, 32)
                .input(NANO_COMPUTER_IV, 32)
                .input(WRAP_GOOWARE_SMD_INDUCTOR)
                .input(WRAP_GOOWARE_SMD_CAPACITOR, 2)
                .input(WRAP_RAM_CHIP, 16)
                .input(wireGtHex, AnnealedCopper, 32)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .output(NANO_MAINFRAME_LUV, 16)
                .EUt(VA[EV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(NANO_MAINFRAME_LUV))
                .buildAndRegister()

            // T5: Quantum

            // Quantum Processor
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_EXTREME_CIRCUIT_BOARD)
                .input(WRAP_QUBIT_CPU_CHIP)
                .input(WRAP_NANO_CPU_CHIP)
                .input(WRAP_SMD_CAPACITOR, 12)
                .input(WRAP_SMD_TRANSISTOR, 12)
                .input(wireGtQuadruple, Platinum, 12)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(QUANTUM_PROCESSOR_EV, 64)
                .EUt(2400) // IV
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(QUANTUM_PROCESSOR_EV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_EXTREME_CIRCUIT_BOARD)
                .input(WRAP_QUBIT_CPU_CHIP)
                .input(WRAP_NANO_CPU_CHIP)
                .input(WRAP_ADVANCED_SMD_CAPACITOR, 3)
                .input(WRAP_ADVANCED_SMD_TRANSISTOR, 3)
                .input(wireGtQuadruple, Platinum, 12)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(QUANTUM_PROCESSOR_EV, 64)
                .EUt(2400) // IV
                .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
                .circuit(getCircuit(QUANTUM_PROCESSOR_EV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_EXTREME_CIRCUIT_BOARD)
                .input(WRAP_ADVANCED_SOC_CHIP)
                .input(wireGtQuadruple, Platinum, 12)
                .input(bolt, NiobiumTitanium, 8)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(QUANTUM_PROCESSOR_EV, 64)
                .output(QUANTUM_PROCESSOR_EV, 64)
                .EUt(38400) // ZPM
                .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
                .circuit(getCircuit(QUANTUM_PROCESSOR_EV))
                .buildAndRegister()

            // Quantum Processor Assembly
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_EXTREME_CIRCUIT_BOARD)
                .input(QUANTUM_PROCESSOR_EV, 64)
                .input(WRAP_SMD_INDUCTOR, 8)
                .input(WRAP_SMD_CAPACITOR, 16)
                .input(WRAP_RAM_CHIP, 4)
                .input(wireGtQuadruple, Platinum, 16)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(QUANTUM_ASSEMBLY_IV, 48)
                .EUt(2400) // IV
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .circuit(getCircuit(QUANTUM_ASSEMBLY_IV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_EXTREME_CIRCUIT_BOARD)
                .input(QUANTUM_PROCESSOR_EV, 64)
                .input(WRAP_ADVANCED_SMD_INDUCTOR, 2)
                .input(WRAP_ADVANCED_SMD_CAPACITOR, 4)
                .input(WRAP_RAM_CHIP, 4)
                .input(wireGtQuadruple, Platinum, 16)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(QUANTUM_ASSEMBLY_IV, 48)
                .EUt(2400) // IV
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(QUANTUM_ASSEMBLY_IV))
                .buildAndRegister()

            // Quantum Supercomputer
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_EXTREME_CIRCUIT_BOARD)
                .input(QUANTUM_ASSEMBLY_IV, 48)
                .input(WRAP_SMD_DIODE, 8)
                .input(WRAP_NOR_CHIP, 4)
                .input(WRAP_RAM_CHIP, 16)
                .input(wireGtQuadruple, Platinum, 32)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(QUANTUM_COMPUTER_LUV, 32)
                .EUt(2400) // IV
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .circuit(getCircuit(QUANTUM_COMPUTER_LUV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_EXTREME_CIRCUIT_BOARD)
                .input(QUANTUM_ASSEMBLY_IV, 48)
                .input(WRAP_ADVANCED_SMD_DIODE, 2)
                .input(WRAP_NOR_CHIP, 4)
                .input(WRAP_RAM_CHIP, 16)
                .input(wireGtQuadruple, Platinum, 32)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(QUANTUM_COMPUTER_LUV, 32)
                .EUt(2400) // IV
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(QUANTUM_COMPUTER_LUV))
                .buildAndRegister()

            // Quantum Mainframe
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, HSSG, 32)
                .input(QUANTUM_COMPUTER_LUV, 32)
                .input(WRAP_SMD_INDUCTOR, 24)
                .input(WRAP_SMD_CAPACITOR, 48)
                .input(WRAP_RAM_CHIP, 24)
                .input(wireGtHex, AnnealedCopper, 48)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .output(QUANTUM_MAINFRAME_ZPM, 16)
                .EUt(VA[IV].toLong())
                .duration(8 * MINUTE) // Original: 40s, Wrapped: 40s * 16 = 640s
                .circuit(getCircuit(QUANTUM_MAINFRAME_ZPM))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, HSSG, 32)
                .input(QUANTUM_COMPUTER_LUV, 32)
                .input(WRAP_ADVANCED_SMD_INDUCTOR, 6)
                .input(WRAP_ADVANCED_SMD_CAPACITOR, 12)
                .input(WRAP_RAM_CHIP, 24)
                .input(wireGtHex, AnnealedCopper, 48)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .output(QUANTUM_MAINFRAME_ZPM, 16)
                .EUt(VA[IV].toLong())
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .circuit(getCircuit(QUANTUM_MAINFRAME_ZPM))
                .buildAndRegister()

            // T6: Crystal

            // Crystal Processor
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ELITE_CIRCUIT_BOARD)
                .input(WRAP_CRYSTAL_CPU)
                .input(WRAP_NANO_CPU_CHIP, 2)
                .input(WRAP_ADVANCED_SMD_CAPACITOR, 4)
                .input(WRAP_ADVANCED_SMD_TRANSISTOR, 4)
                .input(wireGtQuadruple, NiobiumTitanium, 8)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(CRYSTAL_PROCESSOR_IV, 64)
                .EUt(9600) // LuV
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(CRYSTAL_PROCESSOR_IV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ELITE_CIRCUIT_BOARD)
                .input(WRAP_CRYSTAL_CPU)
                .input(WRAP_NANO_CPU_CHIP, 2)
                .input(WRAP_GOOWARE_SMD_CAPACITOR)
                .input(WRAP_GOOWARE_SMD_TRANSISTOR)
                .input(wireGtQuadruple, NiobiumTitanium, 8)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(CRYSTAL_PROCESSOR_IV, 64)
                .EUt(9600) // LuV
                .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
                .circuit(getCircuit(CRYSTAL_PROCESSOR_IV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ELITE_CIRCUIT_BOARD)
                .input(WRAP_CRYSTAL_SOC)
                .input(wireGtQuadruple, NiobiumTitanium, 8)
                .input(bolt, YttriumBariumCuprate, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(CRYSTAL_PROCESSOR_IV, 64)
                .output(CRYSTAL_PROCESSOR_IV, 64)
                .EUt(86000) // ZPM
                .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
                .circuit(getCircuit(CRYSTAL_PROCESSOR_IV))
                .buildAndRegister()

            // Crystal Processor Assembly
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ELITE_CIRCUIT_BOARD)
                .input(CRYSTAL_PROCESSOR_IV, 64)
                .input(WRAP_ADVANCED_SMD_INDUCTOR, 4)
                .input(WRAP_ADVANCED_SMD_CAPACITOR, 8)
                .input(WRAP_RAM_CHIP, 24)
                .input(wireGtQuadruple, NiobiumTitanium, 16)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(CRYSTAL_ASSEMBLY_LUV, 48)
                .EUt(9600) // LuV
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .circuit(getCircuit(CRYSTAL_ASSEMBLY_LUV))
                .buildAndRegister()

            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ELITE_CIRCUIT_BOARD)
                .input(CRYSTAL_PROCESSOR_IV, 64)
                .input(WRAP_GOOWARE_SMD_INDUCTOR)
                .input(WRAP_GOOWARE_SMD_CAPACITOR, 2)
                .input(WRAP_RAM_CHIP, 24)
                .input(wireGtQuadruple, NiobiumTitanium, 16)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(CRYSTAL_ASSEMBLY_LUV, 48)
                .EUt(9600) // LuV
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(CRYSTAL_ASSEMBLY_LUV))
                .buildAndRegister()

            // Crystal Supercomputer
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ELITE_CIRCUIT_BOARD)
                .input(CRYSTAL_ASSEMBLY_LUV, 48)
                .input(WRAP_RAM_CHIP, 4)
                .input(WRAP_NOR_CHIP, 32)
                .input(WRAP_NAND_CHIP, 64)
                .input(wireGtQuadruple, NiobiumTitanium, 32)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .output(CRYSTAL_COMPUTER_ZPM, 32)
                .EUt(9600) // LuV
                .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
                .circuit(getCircuit(CRYSTAL_COMPUTER_ZPM))
                .buildAndRegister()

            // T7: Wetware

            // T8: Gooware

            // T9: Optical

            // ...

            // Diamond Modulator
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ENGRAVED_DIAMOND_CHIP)
                .input(WRAP_PLASTIC_CIRCUIT_BOARD)
                .input(wireFine, Palladium, 64)
                .input(bolt, Platinum, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(DIAMOND_MODULATOR, 64)
                .output(DIAMOND_MODULATOR, 64)
                .EUt(VA[IV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(DIAMOND_MODULATOR))
                .buildAndRegister()

            // Ruby Modulator
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ENGRAVED_RUBY_CHIP)
                .input(WRAP_PLASTIC_CIRCUIT_BOARD)
                .input(wireFine, Palladium, 64)
                .input(bolt, Platinum, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(RUBY_MODULATOR, 64)
                .output(RUBY_MODULATOR, 64)
                .EUt(VA[IV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(RUBY_MODULATOR))
                .buildAndRegister()

            // Sapphire Modulator
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_ENGRAVED_SAPPHIRE_CHIP)
                .input(WRAP_PLASTIC_CIRCUIT_BOARD)
                .input(wireFine, Palladium, 64)
                .input(bolt, Platinum, 64)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(SAPPHIRE_MODULATOR, 64)
                .output(SAPPHIRE_MODULATOR, 64)
                .EUt(VA[IV].toLong())
                .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
                .circuit(getCircuit(SAPPHIRE_MODULATOR))
                .buildAndRegister()

            // Crystal SoC Socket
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(WRAP_CRYSTAL_INTERFACE_CHIP)
                .input(DIAMOND_MODULATOR, 16)
                .input(RUBY_MODULATOR, 16)
                .input(SAPPHIRE_MODULATOR, 16)
                .input(wireGtQuadruple, Europium, 4)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .output(CRYSTAL_SOC_SOCKET, 16)
                .EUt(VA[LuV].toLong())
                .duration(20 * SECOND) // Original: 5s, Wrapped: 5s * 16 = 40s
                .circuit(getCircuit(CRYSTAL_SOC_SOCKET))
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