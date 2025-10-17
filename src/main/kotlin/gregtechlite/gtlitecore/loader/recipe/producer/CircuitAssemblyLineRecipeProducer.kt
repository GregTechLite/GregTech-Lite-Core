package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.VHA
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.RecipeMaps.SCANNER_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.BlueAlloy
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.RedAlloy
import gregtech.api.unification.material.Materials.SiliconeRubber
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.SterileGrowthMedium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.pipeTinyFluid
import gregtech.api.unification.ore.OrePrefix.plate
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
import gregtech.common.items.MetaItems.ENERGY_LAPOTRONIC_ORB
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
import gregtech.common.items.MetaItems.NEURO_PROCESSOR
import gregtech.common.items.MetaItems.PROCESSOR_ASSEMBLY_HV
import gregtech.common.items.MetaItems.PROCESSOR_MV
import gregtech.common.items.MetaItems.QUANTUM_ASSEMBLY_IV
import gregtech.common.items.MetaItems.QUANTUM_COMPUTER_LUV
import gregtech.common.items.MetaItems.QUANTUM_MAINFRAME_ZPM
import gregtech.common.items.MetaItems.QUANTUM_PROCESSOR_EV
import gregtech.common.items.MetaItems.STEM_CELLS
import gregtech.common.items.MetaItems.TOOL_DATA_MODULE
import gregtech.common.items.MetaItems.TOOL_DATA_ORB
import gregtech.common.items.MetaItems.TOOL_DATA_STICK
import gregtech.common.items.MetaItems.VACUUM_TUBE
import gregtech.common.items.MetaItems.WETWARE_MAINFRAME_UHV
import gregtech.common.items.MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM
import gregtech.common.items.MetaItems.WETWARE_PROCESSOR_LUV
import gregtech.common.items.MetaItems.WETWARE_SUPER_COMPUTER_UV
import gregtech.common.items.MetaItems.WORKSTATION_EV
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CIRCUIT_ASSEMBLY_LINE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CarbonNanotube
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CIRCUIT_PATTERN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_ASSEMBLY_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_COMPUTER_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_MAINFRAME_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COSMIC_PROCESSOR_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRYSTAL_SOC_SOCKET
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DIAMOND_MODULATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_ASSEMBLY_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_COMPUTER_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_MAINFRAME_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOOWARE_PROCESSOR_ZPM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_ASSEMBLY_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_COMPUTER_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_FIBER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_MAINFRAME_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OPTICAL_PROCESSOR_UV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RUBY_MODULATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SAPPHIRE_MODULATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_ASSEMBLY_UEV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_COMPUTER_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_MAINFRAME_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPINTRONIC_PROCESSOR_UHV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_ASSEMBLY_UXV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_COMPUTER_OpV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_MAINFRAME_MAX
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SUPRACAUSAL_PROCESSOR_UIV
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ADVANCED_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ARAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_BASIC_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_COSMIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CRYSTAL_CPU
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CRYSTAL_INTERFACE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_CRYSTAL_SOC
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ELITE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_DIAMOND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_LAPOTRON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_RUBY_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ENGRAVED_SAPPHIRE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ESR_COMPUTATION_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_EXOTIC_SYSTEM_ON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_EXTREME_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOD_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_GOOWARE_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_HIGHLY_ADVANCED_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ILC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NAND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NANO_CPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NEURO_PROCESSOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NONLINEAR_CHEMICAL_OSCILLATOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_NOR_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_LASER_CONTROL_UNIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTICAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_OPTOELECTRONIC_SYSTEM_ON_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PERFECT_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_PLASTIC_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_QUBIT_CPU_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SIMPLE_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_DIODE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_INDUCTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SPINTRONIC_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_CAPACITOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_RESISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_SUPRACAUSAL_SMD_TRANSISTOR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_UHASOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_ULTIMATE_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WRAP_WETWARE_CIRCUIT_BOARD
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
internal object CircuitAssemblyLineRecipeProducer
{

    // @formatter:off

    const val INFO_NBT_NAME = "CircuitInfo"

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

        addCircuit(ENERGY_LAPOTRONIC_ORB)
        addCircuit(DIAMOND_MODULATOR)
        addCircuit(RUBY_MODULATOR)
        addCircuit(SAPPHIRE_MODULATOR)
        addCircuit(CRYSTAL_SOC_SOCKET)

        /* ---------------------------------------------------------------------------------------------------------- */

        // T1: Electronic

        // Electronic Circuit
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_BASIC_CIRCUIT_BOARD)
            .input(WRAP_SMD_RESISTOR, 2)
            .input(VACUUM_TUBE, 32)
            .input(wireGtHex, RedAlloy, 2)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(ELECTRONIC_CIRCUIT_LV, 64)
            .EUt(VH[LV])
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
            .EUt(VH[LV])
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
            .EUt(VH[LV])
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
            .EUt(VA[LV])
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
            .EUt(VA[MV])
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
            .EUt(VA[MV])
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
            .EUt(VHA[MV])
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
            .EUt(VHA[MV])
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
            .EUt(VA[MV])
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
            .EUt(VA[HV])
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
            .EUt(VA[EV])
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
            .EUt(VA[EV])
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
            .EUt(VA[EV])
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
            .duration(5 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
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
            .duration(2 * MINUTE + 30 * SECOND) // Original: 10s, Wrapped: 10s * 16 = 160s
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
            .EUt(VA[IV])
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
            .EUt(VA[IV])
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
            .duration(5 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuit(getCircuit(CRYSTAL_COMPUTER_ZPM))
            .buildAndRegister()

        // T7: Wetware

        // Wetware Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_NEURO_PROCESSOR)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_QUBIT_CPU_CHIP)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 8)
            .input(WRAP_ADVANCED_SMD_TRANSISTOR, 8)
            .input(wireGtQuadruple, YttriumBariumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(WETWARE_PROCESSOR_LUV, 64)
            .EUt(38400) // ZPM
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuit(getCircuit(WETWARE_PROCESSOR_LUV))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_NEURO_PROCESSOR)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_QUBIT_CPU_CHIP)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 2)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR, 2)
            .input(wireGtQuadruple, YttriumBariumCuprate, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(WETWARE_PROCESSOR_LUV, 64)
            .EUt(38400) // ZPM
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuit(getCircuit(WETWARE_PROCESSOR_LUV))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_NEURO_PROCESSOR)
            .input(WRAP_HIGHLY_ADVANCED_SOC_CHIP)
            .input(wireGtQuadruple, YttriumBariumCuprate, 8)
            .input(bolt, Naquadah, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(WETWARE_PROCESSOR_LUV, 64)
            .output(WETWARE_PROCESSOR_LUV, 64)
            .EUt(150_000) // UV
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuit(getCircuit(WETWARE_PROCESSOR_LUV))
            .buildAndRegister()

        // Wetware Processor Assembly
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_WETWARE_CIRCUIT_BOARD)
            .input(WETWARE_PROCESSOR_LUV, 64)
            .input(WRAP_ADVANCED_SMD_INDUCTOR, 8)
            .input(WRAP_ADVANCED_SMD_CAPACITOR, 16)
            .input(WRAP_ARAM_CHIP, 24)
            .input(wireGtQuadruple, YttriumBariumCuprate, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(WETWARE_PROCESSOR_ASSEMBLY_ZPM, 48)
            .EUt(38400) // ZPM
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuit(getCircuit(WETWARE_PROCESSOR_ASSEMBLY_ZPM))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_WETWARE_CIRCUIT_BOARD)
            .input(WETWARE_PROCESSOR_LUV, 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 2)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 4)
            .input(WRAP_ARAM_CHIP, 24)
            .input(wireGtQuadruple, YttriumBariumCuprate, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(WETWARE_PROCESSOR_ASSEMBLY_ZPM, 48)
            .EUt(38400) // ZPM
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuit(getCircuit(WETWARE_PROCESSOR_ASSEMBLY_ZPM))
            .buildAndRegister()

        // Wetware Supercomputer
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_WETWARE_CIRCUIT_BOARD)
            .input(WETWARE_PROCESSOR_ASSEMBLY_ZPM, 48)
            .input(WRAP_ARAM_CHIP, 8)
            .input(WRAP_NOR_CHIP, 64)
            .input(WRAP_NAND_CHIP, 64)
            .input(wireGtQuadruple, YttriumBariumCuprate, 32)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(WETWARE_SUPER_COMPUTER_UV, 32)
            .EUt(38400) // ZPM
            .duration(5 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuit(getCircuit(WETWARE_SUPER_COMPUTER_UV))
            .buildAndRegister()

        // Wetware Mainframe

        // T8: Gooware

        // Gooware Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(WRAP_NONLINEAR_CHEMICAL_OSCILLATOR)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 16)
            .input(WRAP_GOOWARE_SMD_TRANSISTOR, 16)
            .input(wireGtQuadruple, Europium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(GOOWARE_PROCESSOR_ZPM, 64)
            .EUt(VHA[UV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuit(getCircuit(GOOWARE_PROCESSOR_ZPM))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(WRAP_NONLINEAR_CHEMICAL_OSCILLATOR)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 4)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 4)
            .input(wireGtQuadruple, Europium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(GOOWARE_PROCESSOR_ZPM, 64)
            .EUt(VHA[UV])
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuit(getCircuit(GOOWARE_PROCESSOR_ZPM))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(WRAP_NONLINEAR_CHEMICAL_OSCILLATOR)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR)
            .input(wireGtQuadruple, Europium, 8)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(GOOWARE_PROCESSOR_ZPM, 64)
            .EUt(VHA[UV])
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuit(getCircuit(GOOWARE_PROCESSOR_ZPM))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(WRAP_NONLINEAR_CHEMICAL_OSCILLATOR)
            .input(WRAP_UHASOC_CHIP)
            .input(wireGtQuadruple, Europium, 8)
            .input(bolt, Neutronium, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(GOOWARE_PROCESSOR_ZPM, 64)
            .output(GOOWARE_PROCESSOR_ZPM, 64)
            .EUt(VHA[UHV])
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuit(getCircuit(GOOWARE_PROCESSOR_ZPM))
            .buildAndRegister()

        // Gooware Processor Assembly
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_PROCESSOR_ZPM, 64)
            .input(WRAP_GOOWARE_SMD_INDUCTOR, 16)
            .input(WRAP_GOOWARE_SMD_CAPACITOR, 32)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtQuadruple, Europium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(GOOWARE_ASSEMBLY_UV, 48)
            .EUt(VHA[UV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuit(getCircuit(GOOWARE_ASSEMBLY_UV))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_PROCESSOR_ZPM, 64)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 4)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 8)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtQuadruple, Europium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(GOOWARE_ASSEMBLY_UV, 48)
            .EUt(VHA[UV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuit(getCircuit(GOOWARE_ASSEMBLY_UV))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ULTIMATE_CIRCUIT_BOARD)
            .input(GOOWARE_PROCESSOR_ZPM, 64)
            .input(WRAP_SPINTRONIC_SMD_INDUCTOR)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 2)
            .input(WRAP_ARAM_CHIP, 32)
            .input(wireGtQuadruple, Europium, 16)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(GOOWARE_ASSEMBLY_UV, 48)
            .EUt(VHA[UV])
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuit(getCircuit(GOOWARE_ASSEMBLY_UV))
            .buildAndRegister()

        // Gooware Supercomputer

        // Gooware Mainframe

        // T9: Optical

        // Optical Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_OPTICAL_LASER_CONTROL_UNIT)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_OPTICAL_SMD_RESISTOR, 16)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 16)
            .input(WRAP_OPTICAL_SMD_TRANSISTOR, 16)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(OPTICAL_PROCESSOR_UV, 64)
            .EUt(VA[UHV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuit(getCircuit(OPTICAL_PROCESSOR_UV))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_OPTICAL_LASER_CONTROL_UNIT)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_SPINTRONIC_SMD_RESISTOR, 4)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 4)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR, 4)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(OPTICAL_PROCESSOR_UV, 64)
            .EUt(VA[UHV])
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuit(getCircuit(OPTICAL_PROCESSOR_UV))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_OPTICAL_LASER_CONTROL_UNIT)
            .input(WRAP_CRYSTAL_CPU)
            .input(WRAP_COSMIC_SMD_RESISTOR)
            .input(WRAP_COSMIC_SMD_CAPACITOR)
            .input(WRAP_COSMIC_SMD_TRANSISTOR)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(OPTICAL_PROCESSOR_UV, 64)
            .EUt(VA[UHV])
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuit(getCircuit(OPTICAL_PROCESSOR_UV))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_OPTICAL_LASER_CONTROL_UNIT)
            .input(WRAP_OPTOELECTRONIC_SYSTEM_ON_CHIP)
            .input(OPTICAL_FIBER, 64)
            .input(bolt, Bedrockium, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(OPTICAL_PROCESSOR_UV, 64)
            .output(OPTICAL_PROCESSOR_UV, 64)
            .EUt(VA[UEV])
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuit(getCircuit(OPTICAL_PROCESSOR_UV))
            .buildAndRegister()

        // Optical Processor Assembly
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_PROCESSOR_UV, 64)
            .input(WRAP_OPTICAL_SMD_INDUCTOR, 16)
            .input(WRAP_OPTICAL_SMD_CAPACITOR, 32)
            .input(WRAP_HIGHLY_ADVANCED_SOC_CHIP, 32)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(OPTICAL_ASSEMBLY_UHV, 48)
            .EUt(VA[UHV])
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .circuit(getCircuit(OPTICAL_ASSEMBLY_UHV))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_PROCESSOR_UV, 64)
            .input(WRAP_SPINTRONIC_SMD_INDUCTOR, 4)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 8)
            .input(WRAP_HIGHLY_ADVANCED_SOC_CHIP, 32)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(OPTICAL_ASSEMBLY_UHV, 48)
            .EUt(VA[UHV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuit(getCircuit(OPTICAL_ASSEMBLY_UHV))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_PERFECT_CIRCUIT_BOARD)
            .input(OPTICAL_PROCESSOR_UV, 64)
            .input(WRAP_COSMIC_SMD_INDUCTOR)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 2)
            .input(WRAP_HIGHLY_ADVANCED_SOC_CHIP, 32)
            .input(OPTICAL_FIBER, 64)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(OPTICAL_ASSEMBLY_UHV, 48)
            .EUt(VA[UHV])
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuit(getCircuit(OPTICAL_ASSEMBLY_UHV))
            .buildAndRegister()

        // Optical Computer

        // Optical Mainframe

        // T10: Spintronic

        // Spintronic Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ESR_COMPUTATION_UNIT)
            .input(WRAP_CRYSTAL_SOC)
            .input(WRAP_SPINTRONIC_SMD_RESISTOR, 16)
            .input(WRAP_SPINTRONIC_SMD_CAPACITOR, 16)
            .input(WRAP_SPINTRONIC_SMD_TRANSISTOR, 16)
            .input(wireFine, CarbonNanotube, 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(SPINTRONIC_PROCESSOR_UHV, 64)
            .EUt(VA[UEV])
            .duration(2 * MINUTE) // Original: 10s, Wrapped: 10s * 16 = 160s
            .circuit(getCircuit(SPINTRONIC_PROCESSOR_UHV))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ESR_COMPUTATION_UNIT)
            .input(WRAP_CRYSTAL_SOC)
            .input(WRAP_COSMIC_SMD_RESISTOR, 4)
            .input(WRAP_COSMIC_SMD_CAPACITOR, 4)
            .input(WRAP_COSMIC_SMD_TRANSISTOR, 4)
            .input(wireFine, CarbonNanotube, 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(SPINTRONIC_PROCESSOR_UHV, 64)
            .EUt(VA[UEV])
            .duration(1 * MINUTE) // Original: 5s, Wrapped: 5s * 16 = 80s
            .circuit(getCircuit(SPINTRONIC_PROCESSOR_UHV))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ESR_COMPUTATION_UNIT)
            .input(WRAP_CRYSTAL_SOC)
            .input(WRAP_SUPRACAUSAL_SMD_RESISTOR)
            .input(WRAP_SUPRACAUSAL_SMD_CAPACITOR)
            .input(WRAP_SUPRACAUSAL_SMD_TRANSISTOR)
            .input(wireFine, CarbonNanotube, 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(SPINTRONIC_PROCESSOR_UHV, 64)
            .EUt(VA[UEV])
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuit(getCircuit(SPINTRONIC_PROCESSOR_UHV))
            .buildAndRegister()

        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ESR_COMPUTATION_UNIT)
            .input(WRAP_EXOTIC_SYSTEM_ON_CHIP)
            .input(wireFine, CarbonNanotube, 64)
            .input(bolt, Vibranium, 64)
            .fluidInputs(MutatedLivingSolder.getFluid(L / 2))
            .output(SPINTRONIC_PROCESSOR_UHV, 64)
            .output(SPINTRONIC_PROCESSOR_UHV, 64)
            .EUt(VA[UIV])
            .duration(30 * SECOND) // Original: 2.5s, Wrapped: 2.5s * 16 = 40s
            .circuit(getCircuit(SPINTRONIC_PROCESSOR_UHV))
            .buildAndRegister()

        // Spintronic Processor Assembly

        // Spintronic Computer

        // Spintronic Mainframe

        // ---------------------------------------------------------------------------------------------------------

        // Lapotronic Orb
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_EXTREME_CIRCUIT_BOARD)
            .input(WRAP_PIC_CHIP, 4)
            .input(WRAP_ENGRAVED_LAPOTRON_CHIP, 24)
            .input(WRAP_NANO_CPU_CHIP, 2)
            .input(wireGtQuadruple, Platinum, 16)
            .input(plate, Platinum, 64)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(ENERGY_LAPOTRONIC_ORB, 16)
            .EUt(VH[EV])
            .duration(6 * MINUTE) // Original: 25.6s, Wrapped: 25.6s * 16 = 409.6s
            .circuit(getCircuit(ENERGY_LAPOTRONIC_ORB))
            .buildAndRegister()

        // Data Stick
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ADVANCED_CIRCUIT_BOARD)
            .input(circuit, Tier.HV, 32)
            .input(WRAP_RAM_CHIP, 4)
            .input(WRAP_NOR_CHIP, 16)
            .input(WRAP_NAND_CHIP, 32)
            .input(wireGtQuadruple, Platinum, 32)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(TOOL_DATA_STICK, 16)
            .EUt(1200) // EV
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        // Data Orb
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_EXTREME_CIRCUIT_BOARD)
            .input(circuit, Tier.IV, 32)
            .input(WRAP_RAM_CHIP, 8)
            .input(WRAP_NOR_CHIP, 32)
            .input(WRAP_NAND_CHIP, 48)
            .input(wireGtQuadruple, NiobiumTitanium, 32)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(TOOL_DATA_ORB, 16)
            .EUt(9600) // LuV
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        // Data Module
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_WETWARE_CIRCUIT_BOARD)
            .input(circuit, Tier.ZPM, 32)
            .input(WRAP_ARAM_CHIP, 32)
            .input(WRAP_NOR_CHIP, 64)
            .input(WRAP_NAND_CHIP, 64)
            .input(wireGtQuadruple, YttriumBariumCuprate, 32)
            .fluidInputs(SolderingAlloy.getFluid(L))
            .output(TOOL_DATA_MODULE, 16)
            .EUt(38400) // ZPM
            .duration(4 * MINUTE) // Original: 20s, Wrapped: 20s * 16 = 320s
            .buildAndRegister()

        // Diamond Modulator
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_ENGRAVED_DIAMOND_CHIP)
            .input(WRAP_PLASTIC_CIRCUIT_BOARD)
            .input(wireFine, Palladium, 64)
            .input(bolt, Platinum, 64)
            .fluidInputs(SolderingAlloy.getFluid(L / 2))
            .output(DIAMOND_MODULATOR, 64)
            .output(DIAMOND_MODULATOR, 64)
            .EUt(VA[IV])
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
            .EUt(VA[IV])
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
                .EUt(VA[IV])
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
            .EUt(VA[LuV])
            .duration(20 * SECOND) // Original: 5s, Wrapped: 5s * 16 = 40s
            .circuit(getCircuit(CRYSTAL_SOC_SOCKET))
            .buildAndRegister()

        // Neuro Processor
        CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(WRAP_WETWARE_CIRCUIT_BOARD)
            .input(STEM_CELLS, 64)
            .input(pipeTinyFluid, Polybenzimidazole, 32)
            .input(plate, Electrum, 64)
            .input(foil, SiliconeRubber, 64)
            .input(bolt, HSSE, 64)
            .fluidInputs(SterileGrowthMedium.getFluid(250))
            .output(NEURO_PROCESSOR, 32)
            .EUt(80000) // ZPM
            .duration(6 * MINUTE) // Original: 30s, Wrapped: 30s * 16 = 480s
            .circuit(getCircuit(NEURO_PROCESSOR))
            .buildAndRegister()

    }

    private fun addCircuit(circuit: MetaItem<*>.MetaValueItem)
    {
        // Circuit Pattern with NBT {"CircuitInfo": "${circuit.unlocalizedName}"}.
        val circuitPattern = CIRCUIT_PATTERN.stackForm
        circuitPattern.setTagInfo(INFO_NBT_NAME, NBTTagString(circuit.unlocalizedName))
        // Add Scanning recipes of Circuit Pattern.
        SCANNER_RECIPES.recipeBuilder()
            .input(CIRCUIT_PATTERN)
            .input(circuit)
            .outputs(circuitPattern)
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    fun getCircuit(circuit: MetaItem<*>.MetaValueItem): ItemStack
    {
        val circuitPattern = CIRCUIT_PATTERN.stackForm
        circuitPattern.setTagInfo(INFO_NBT_NAME, NBTTagString(circuit.unlocalizedName))
        return circuitPattern
    }

    // @formatter:on

}