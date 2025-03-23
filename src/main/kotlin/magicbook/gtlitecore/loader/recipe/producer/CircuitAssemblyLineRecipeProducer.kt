package magicbook.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.SCANNER_RECIPES
import gregtech.common.items.MetaItems.CRYSTAL_ASSEMBLY_LUV
import gregtech.common.items.MetaItems.CRYSTAL_COMPUTER_ZPM
import gregtech.common.items.MetaItems.CRYSTAL_MAINFRAME_UV
import gregtech.common.items.MetaItems.CRYSTAL_PROCESSOR_IV
import gregtech.common.items.MetaItems.ELECTRONIC_CIRCUIT_LV
import gregtech.common.items.MetaItems.ELECTRONIC_CIRCUIT_MV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_LV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_MV
import gregtech.common.items.MetaItems.MICROPROCESSOR_LV
import gregtech.common.items.MetaItems.NAND_CHIP_ULV
import gregtech.common.items.MetaItems.VACUUM_TUBE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CIRCUIT_PATTERN
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagString

import gregtech.common.items.MetaItems.PROCESSOR_MV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_HV
import gregtech.common.items.MetaItems.NANO_MAINFRAME_LUV
import gregtech.common.items.MetaItems.NANO_PROCESSOR_ASSEMBLY_EV
import gregtech.common.items.MetaItems.PROCESSOR_ASSEMBLY_HV
import gregtech.common.items.MetaItems.NANO_PROCESSOR_HV
import gregtech.common.items.MetaItems.QUANTUM_COMPUTER_LUV
import gregtech.common.items.MetaItems.QUANTUM_MAINFRAME_ZPM
import gregtech.common.items.MetaItems.WETWARE_PROCESSOR_LUV
import gregtech.common.items.MetaItems.WORKSTATION_EV
import gregtech.common.items.MetaItems.QUANTUM_PROCESSOR_EV
import gregtech.common.items.MetaItems.NANO_COMPUTER_IV
import gregtech.common.items.MetaItems.MAINFRAME_IV
import gregtech.common.items.MetaItems.QUANTUM_ASSEMBLY_IV
import gregtech.common.items.MetaItems.WETWARE_MAINFRAME_UHV
import gregtech.common.items.MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM
import gregtech.common.items.MetaItems.WETWARE_SUPER_COMPUTER_UV
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CIRCUIT_ASSEMBLY_LINE_RECIPES
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
import net.minecraft.init.Items

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
         * - Each 4x SMD -> 1x Advanced SMD, each 4x Advanced SMD -> 1x Gooware SMD, ...
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

            // Testing
            CIRCUIT_ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(ItemStack(Items.APPLE))
                .output(ELECTRONIC_CIRCUIT_LV, 64)
                .circuit(getCircuit(ELECTRONIC_CIRCUIT_LV))
                .EUt(VA[LV].toLong())
                .duration(100000 * SECOND)
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