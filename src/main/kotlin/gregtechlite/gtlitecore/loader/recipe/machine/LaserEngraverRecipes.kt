package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.CUTTER_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Color
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.NetherStar
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Ruby
import gregtech.api.unification.material.Materials.Sapphire
import gregtech.api.unification.ore.OrePrefix.craftingLens
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.ADVANCED_SYSTEM_ON_CHIP_WAFER
import gregtech.common.items.MetaItems.CENTRAL_PROCESSING_UNIT_WAFER
import gregtech.common.items.MetaItems.HIGHLY_ADVANCED_SOC_WAFER
import gregtech.common.items.MetaItems.INTEGRATED_LOGIC_CIRCUIT_WAFER
import gregtech.common.items.MetaItems.LOW_POWER_INTEGRATED_CIRCUIT_WAFER
import gregtech.common.items.MetaItems.NAND_MEMORY_CHIP_WAFER
import gregtech.common.items.MetaItems.NAQUADAH_WAFER
import gregtech.common.items.MetaItems.NEUTRONIUM_WAFER
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP_WAFER
import gregtech.common.items.MetaItems.POWER_INTEGRATED_CIRCUIT_WAFER
import gregtech.common.items.MetaItems.RANDOM_ACCESS_MEMORY_WAFER
import gregtech.common.items.MetaItems.SIMPLE_SYSTEM_ON_CHIP_WAFER
import gregtech.common.items.MetaItems.SYSTEM_ON_CHIP_WAFER
import gregtech.common.items.MetaItems.ULTRA_LOW_POWER_INTEGRATED_CIRCUIT_WAFER
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.cleanroom
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicSiliconNitride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicZirconia
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FreeElectronGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumNiobate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LuTmYVO
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetoResonatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NdYAG
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrHoYLF
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ADVANCED_RAM_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ADVANCED_RAM_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ATTO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ATTO_PIC_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENGRAVED_DIAMOND_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENGRAVED_RUBY_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENGRAVED_SAPPHIRE_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FEMTO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FEMTO_PIC_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HASSIUM_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANO_PIC_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PICO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PICO_PIC_WAFER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ULTRA_HIGHLY_ADVANCED_SOC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ULTRA_HIGHLY_ADVANCED_SOC_WAFER
import net.minecraftforge.oredict.OreDictionary

internal object LaserEngraverRecipes
{

    // @formatter:off

    fun init()
    {
        removeColoredLenses()

        // Integrated Logic Circuit (ILC)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(craftingLens, Color.Red)
            input(HASSIUM_WAFER)
            output(INTEGRATED_LOGIC_CIRCUIT_WAFER, 32)
            EUt(VA[LuV])
            duration(10 * TICK)
            cleanroom()
        }

        // Random Access Memory (RAM)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(craftingLens, Color.Green)
            input(HASSIUM_WAFER)
            output(RANDOM_ACCESS_MEMORY_WAFER, 32)
            EUt(VA[LuV])
            duration(10 * TICK)
            cleanroom()
        }

        // Central Processing Unit (CPU)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(craftingLens, Color.LightBlue)
            input(HASSIUM_WAFER)
            output(CENTRAL_PROCESSING_UNIT_WAFER, 32)
            EUt(VA[LuV])
            duration(10 * TICK)
            cleanroom()
        }

        // Ultra Low Power Integrated Circuit (ULPIC)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(craftingLens, Color.Blue)
            input(HASSIUM_WAFER)
            output(ULTRA_LOW_POWER_INTEGRATED_CIRCUIT_WAFER, 32)
            EUt(VA[LuV])
            duration(10 * TICK)
            cleanroom()
        }

        // Low Power Integrated Circuit (LPIC)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(craftingLens, Color.Orange)
            input(HASSIUM_WAFER)
            output(LOW_POWER_INTEGRATED_CIRCUIT_WAFER, 32)
            EUt(VA[LuV])
            duration(10 * TICK)
            cleanroom()
        }

        // Simple System on Chip (SSoC)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(craftingLens, Color.Cyan)
            input(HASSIUM_WAFER)
            output(SIMPLE_SYSTEM_ON_CHIP_WAFER, 32)
            EUt(VA[LuV])
            duration(10 * TICK)
            cleanroom()
        }

        // NAND Memory Chip (NAND)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(craftingLens, Color.Gray)
            input(HASSIUM_WAFER)
            output(NAND_MEMORY_CHIP_WAFER, 16)
            EUt(VA[LuV])
            duration(2 * SECOND + 10 * TICK)
            cleanroom()
        }

        // NOR Memory Chip (NOR)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(craftingLens, Color.Pink)
            input(HASSIUM_WAFER)
            output(NOR_MEMORY_CHIP_WAFER, 16)
            EUt(VA[LuV])
            duration(2 * SECOND + 10 * TICK)
            cleanroom()
        }

        // Power Integrated Circuit (PIC)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(craftingLens, Color.Brown)
            input(HASSIUM_WAFER)
            output(POWER_INTEGRATED_CIRCUIT_WAFER, 16)
            EUt(VA[LuV])
            duration(2 * SECOND + 10 * TICK)
            cleanroom()
        }

        // System on Chip (SoC)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(craftingLens, Color.Yellow)
            input(HASSIUM_WAFER)
            output(SYSTEM_ON_CHIP_WAFER, 16)
            EUt(VA[LuV])
            duration(2 * SECOND + 10 * TICK)
            cleanroom()
        }

        // Advanced System on Chip (ASoC)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(craftingLens, Color.Purple)
            input(HASSIUM_WAFER)
            output(ADVANCED_SYSTEM_ON_CHIP_WAFER, 8)
            EUt(VA[LuV])
            duration(10 * SECOND)
            cleanroom()
        }

        // ---------------------------------------------------------------------------------------------------------

        // Highly Advanced System on Chip (HASoC)
        LASER_ENGRAVER_RECIPES.removeRecipe(
            NEUTRONIUM_WAFER.stack(),
            OreDictUnifier.get(craftingLens, Color.Black))

        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, NetherStar)
            notConsumable(craftingLens, Color.Black)
            input(NEUTRONIUM_WAFER)
            output(HIGHLY_ADVANCED_SOC_WAFER)
            EUt(VA[IV])
            duration(45 * SECOND)
            cleanroom()
        }

        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, NetherStar)
            notConsumable(craftingLens, Color.Black)
            input(HASSIUM_WAFER)
            output(HIGHLY_ADVANCED_SOC_WAFER, 4)
            EUt(VA[LuV])
            duration(25 * SECOND)
            cleanroom()
        }

        // Ultra Highly Advanced System on Chip (UHASoC)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, CubicZirconia)
            notConsumable(craftingLens, Color.Magenta)
            notConsumable(craftingLens, Color.LightGray)
            input(NEUTRONIUM_WAFER)
            output(ULTRA_HIGHLY_ADVANCED_SOC_WAFER)
            EUt(VA[LuV])
            duration(45 * SECOND)
            cleanroom()
        }

        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, CubicZirconia)
            notConsumable(craftingLens, Color.Magenta)
            notConsumable(craftingLens, Color.LightGray)
            input(HASSIUM_WAFER)
            output(ULTRA_HIGHLY_ADVANCED_SOC_WAFER, 4)
            EUt(VA[ZPM])
            duration(45 * SECOND)
            cleanroom()
        }

        // Ultra Highly Advanced System on Chip (UHASoC) Chip
        CUTTER_RECIPES.addRecipe {
            input(ULTRA_HIGHLY_ADVANCED_SOC_WAFER)
            output(ULTRA_HIGHLY_ADVANCED_SOC_CHIP, 6)
            EUt(VA[LuV])
            duration(1 * MINUTE + 30 * SECOND)
            cleanroom()
        }

        // Advanced Random Access Memory (ARAM)
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, MagnetoResonatic)
            input(NAQUADAH_WAFER)
            output(ADVANCED_RAM_WAFER)
            EUt(VA[ZPM])
            duration(30 * SECOND)
            cleanroom()
        }

        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, MagnetoResonatic)
            input(NEUTRONIUM_WAFER)
            output(ADVANCED_RAM_WAFER, 4)
            EUt(VA[UV])
            duration(10 * SECOND)
            cleanroom()
        }

        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, MagnetoResonatic)
            input(HASSIUM_WAFER)
            output(ADVANCED_RAM_WAFER, 8)
            EUt(VA[UHV])
            duration(2 * SECOND + 10 * TICK)
            cleanroom()
        }

        // Advanced Random Access Memory (ARAM) Chip
        CUTTER_RECIPES.addRecipe {
            input(ADVANCED_RAM_WAFER, 1)
            output(ADVANCED_RAM_CHIP, 16)
            EUt(VA[ZPM])
            duration(20 * SECOND)
            cleanroom()
        }

        // Nano Power Integrate Circuit (NPIC) Wafer
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, LithiumNiobate)
            input(NAQUADAH_WAFER)
            output(NANO_PIC_WAFER)
            EUt(VA[ZPM])
            duration(1 * MINUTE + 20 * SECOND)
            cleanroom()
        }

        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, LithiumNiobate)
            input(NEUTRONIUM_WAFER)
            output(NANO_PIC_WAFER, 4)
            EUt(VA[UV])
            duration(20 * SECOND)
            cleanroom()
        }

        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, LithiumNiobate)
            input(HASSIUM_WAFER)
            output(NANO_PIC_WAFER, 8)
            EUt(VA[UHV])
            duration(5 * SECOND)
            cleanroom()
        }

        // Nano Power Integrate Circuit (NPIC) Chip
        CUTTER_RECIPES.addRecipe {
            input(NANO_PIC_WAFER)
            output(NANO_PIC_CHIP, 2)
            EUt(VA[ZPM])
            duration(1 * MINUTE + 30 * SECOND)
            cleanroom()
        }

        // Pico Power Integrate Circuit (PPIC) Wafer
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, LuTmYVO)
            input(NAQUADAH_WAFER)
            output(PICO_PIC_WAFER)
            EUt(VA[UV])
            duration(1 * MINUTE + 20 * SECOND)
            cleanroom()
        }

        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, LuTmYVO)
            input(NEUTRONIUM_WAFER)
            output(PICO_PIC_WAFER, 4)
            EUt(VA[UHV])
            duration(20 * SECOND)
            cleanroom()
        }

        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, LuTmYVO)
            input(HASSIUM_WAFER)
            output(PICO_PIC_WAFER, 8)
            EUt(VA[UEV])
            duration(5 * SECOND)
            cleanroom()
        }

        // Pico Power Integrate Circuit (PPIC) Chip
        CUTTER_RECIPES.addRecipe {
            input(PICO_PIC_WAFER)
            output(PICO_PIC_CHIP, 2)
            EUt(VA[UV])
            duration(1 * MINUTE + 30 * SECOND)
            cleanroom()
        }

        // Femto Power Integrate Circuit (FPIC) Wafer
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, PrHoYLF)
            input(NEUTRONIUM_WAFER)
            output(FEMTO_PIC_WAFER)
            EUt(VA[UHV])
            duration(1 * MINUTE + 20 * SECOND)
            cleanroom()
        }

        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, PrHoYLF)
            input(HASSIUM_WAFER)
            output(FEMTO_PIC_WAFER, 4)
            EUt(VA[UEV])
            duration(20 * SECOND)
            cleanroom()
        }

        // Femto Power Integrate Circuit (FPIC) Chip
        CUTTER_RECIPES.addRecipe {
            input(FEMTO_PIC_WAFER)
            output(FEMTO_PIC_CHIP, 2)
            EUt(VA[UHV])
            duration(1 * MINUTE + 30 * SECOND)
            cleanroom()
        }

        // Atto Power Integrate Circuit (APIC) Wafer
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, NdYAG)
            input(FEMTO_PIC_WAFER)
            input(springSmall, Taranium)
            input(wireFine, MetastableOganesson, 4)
            fluidInputs(Neutronium.getFluid(L))
            fluidInputs(FreeElectronGas.getFluid(4000))
            output(ATTO_PIC_WAFER)
            EUt(VA[UEV])
            duration(30 * SECOND)
            cleanroom()
        }

        // Atto Power Integrate Circuit (APIC) Chip
        CUTTER_RECIPES.addRecipe {
            input(ATTO_PIC_WAFER)
            output(ATTO_PIC_CHIP, 2)
            EUt(VA[UEV])
            duration(1 * MINUTE + 30 * SECOND)
            cleanroom()
        }

        // Engraved Diamond Chip
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, Diamond)
            input(plate, Diamond)
            output(ENGRAVED_DIAMOND_CHIP)
            EUt(VA[LV])
            duration(1 * MINUTE)
        }

        // Engraved Ruby Chip
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, Ruby)
            input(plate, Ruby)
            output(ENGRAVED_RUBY_CHIP)
            EUt(VA[LV])
            duration(1 * MINUTE)
        }

        // Engraved Sapphire Chip
        LASER_ENGRAVER_RECIPES.addRecipe {
            notConsumable(lens, Sapphire)
            input(plate, Sapphire)
            output(ENGRAVED_SAPPHIRE_CHIP)
            EUt(VA[LV])
            duration(1 * MINUTE)
        }
    }

    private fun removeColoredLenses()
    {
        // Disabled craftingLensWhite ore dict of Nether Star and Cubic Zirconia lens.
        OreDictionary.getOres("craftingLensWhite").removeIf { item ->
            item.isItemEqual(OreDictUnifier.get(lens, NetherStar)) or
                    item.isItemEqual(OreDictUnifier.get(lens, CubicZirconia))
        }
        // Disabled craftingLensGray ore dict of Cubic Silicon Nitride lens.
        OreDictionary.getOres("craftingLensGray").removeIf { item ->
            item.isItemEqual(OreDictUnifier.get(lens, CubicSiliconNitride))
        }

        // Disabled craftingLensPink ore dict of Magneto Resonatic and Nd:YAG.
        OreDictionary.getOres("craftingLensPink").removeIf { item ->
            item.isItemEqual(OreDictUnifier.get(lens, MagnetoResonatic)) or
                    item.isItemEqual(OreDictUnifier.get(lens, NdYAG))
        }
        // Disabled craftingLensRed ore dict of Lu/Tm:YVO
        OreDictionary.getOres("craftingLensRed").removeIf { item ->
            item.isItemEqual(OreDictUnifier.get(lens, LuTmYVO))
        }
        // Disabled craftingLensLightBlue ore dict of Pr/Ho:YLF
        OreDictionary.getOres("craftingLensLightBlue").removeIf { item ->
            item.isItemEqual(OreDictUnifier.get(lens, PrHoYLF))
        }
    }

    // @formatter:on

}