package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.CUTTER_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.NetherStar
import gregtech.api.unification.material.Materials.Ruby
import gregtech.api.unification.material.Materials.Sapphire
import gregtech.api.unification.ore.OrePrefix.craftingLens
import gregtech.api.unification.ore.OrePrefix.gemExquisite
import gregtech.api.unification.ore.OrePrefix.lens
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
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicSiliconNitride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicZirconia
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumNiobate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LuTmYVO
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnetoResonatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NdYAG
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.gemSolitary
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ADVANCED_RAM_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ADVANCED_RAM_WAFER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ENGRAVED_DIAMOND_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ENGRAVED_RUBY_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ENGRAVED_SAPPHIRE_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.HASSIUM_WAFER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANO_PIC_WAFER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PICO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PICO_PIC_WAFER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ULTRA_HIGHLY_ADVANCED_SOC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ULTRA_HIGHLY_ADVANCED_SOC_WAFER
import net.minecraftforge.oredict.OreDictionary

@Suppress("MISSING_DEPENDENCY_CLASS")
class LaserEngraverRecipes
{

    companion object
    {

        fun init() // TODO Higher wafers for laser engravings beyond Neutronium (MK3 material).
        {
            removeColoredLenses()

            // Integrated Logic Circuit (ILC)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, MarkerMaterials.Color.Red)
                .input(HASSIUM_WAFER)
                .output(INTEGRATED_LOGIC_CIRCUIT_WAFER, 32)
                .EUt(VA[LuV].toLong())
                .duration(10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Random Access Memory (RAM)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, MarkerMaterials.Color.Green)
                .input(HASSIUM_WAFER)
                .output(RANDOM_ACCESS_MEMORY_WAFER, 32)
                .EUt(VA[LuV].toLong())
                .duration(10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Central Processing Unit (CPU)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, MarkerMaterials.Color.LightBlue)
                .input(HASSIUM_WAFER)
                .output(CENTRAL_PROCESSING_UNIT_WAFER, 32)
                .EUt(VA[LuV].toLong())
                .duration(10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Ultra Low Power Integrated Circuit (ULPIC)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, MarkerMaterials.Color.Blue)
                .input(HASSIUM_WAFER)
                .output(ULTRA_LOW_POWER_INTEGRATED_CIRCUIT_WAFER, 32)
                .EUt(VA[LuV].toLong())
                .duration(10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Low Power Integrated Circuit (LPIC)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, MarkerMaterials.Color.Orange)
                .input(HASSIUM_WAFER)
                .output(LOW_POWER_INTEGRATED_CIRCUIT_WAFER, 32)
                .EUt(VA[LuV].toLong())
                .duration(10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Simple System on Chip (SSoC)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, MarkerMaterials.Color.Cyan)
                .input(HASSIUM_WAFER)
                .output(SIMPLE_SYSTEM_ON_CHIP_WAFER, 32)
                .EUt(VA[LuV].toLong())
                .duration(10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // NAND Memory Chip (NAND)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, MarkerMaterials.Color.Gray)
                .input(HASSIUM_WAFER)
                .output(NAND_MEMORY_CHIP_WAFER, 16)
                .EUt(VA[LuV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // NOR Memory Chip (NOR)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, MarkerMaterials.Color.Pink)
                .input(HASSIUM_WAFER)
                .output(NOR_MEMORY_CHIP_WAFER, 16)
                .EUt(VA[LuV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Power Integrated Circuit (PIC)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, MarkerMaterials.Color.Brown)
                .input(HASSIUM_WAFER)
                .output(POWER_INTEGRATED_CIRCUIT_WAFER, 16)
                .EUt(VA[LuV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // System on Chip (SoC)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, MarkerMaterials.Color.Yellow)
                .input(HASSIUM_WAFER)
                .output(SYSTEM_ON_CHIP_WAFER, 16)
                .EUt(VA[LuV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Advanced System on Chip (ASoC)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, MarkerMaterials.Color.Purple)
                .input(HASSIUM_WAFER)
                .output(ADVANCED_SYSTEM_ON_CHIP_WAFER, 8)
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // ---------------------------------------------------------------------------------------------------------

            // Highly Advanced System on Chip (HASoC)
            GTRecipeHandler.removeRecipesByInputs(LASER_ENGRAVER_RECIPES,
                NEUTRONIUM_WAFER.stackForm,
                OreDictUnifier.get(craftingLens, MarkerMaterials.Color.Black))

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, NetherStar)
                .notConsumable(craftingLens, MarkerMaterials.Color.Black)
                .input(NEUTRONIUM_WAFER)
                .output(HIGHLY_ADVANCED_SOC_WAFER)
                .EUt(VA[IV].toLong())
                .duration(45 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, NetherStar)
                .notConsumable(craftingLens, MarkerMaterials.Color.Black)
                .input(HASSIUM_WAFER)
                .output(HIGHLY_ADVANCED_SOC_WAFER, 4)
                .EUt(VA[LuV].toLong())
                .duration(25 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Ultra Highly Advanced System on Chip (UHASoC)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, CubicZirconia)
                .notConsumable(craftingLens, MarkerMaterials.Color.Magenta)
                .notConsumable(craftingLens, MarkerMaterials.Color.LightGray)
                .input(NEUTRONIUM_WAFER)
                .output(ULTRA_HIGHLY_ADVANCED_SOC_WAFER)
                .EUt(VA[LuV].toLong())
                .duration(45 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, CubicZirconia)
                .notConsumable(craftingLens, MarkerMaterials.Color.Magenta)
                .notConsumable(craftingLens, MarkerMaterials.Color.LightGray)
                .input(HASSIUM_WAFER)
                .output(ULTRA_HIGHLY_ADVANCED_SOC_WAFER, 4)
                .EUt(VA[ZPM].toLong())
                .duration(45 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Ultra Highly Advanced System on Chip (UHASoC) Chip
            CUTTER_RECIPES.recipeBuilder()
                .input(ULTRA_HIGHLY_ADVANCED_SOC_WAFER)
                .output(ULTRA_HIGHLY_ADVANCED_SOC_CHIP, 6)
                .EUt(VA[LuV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Advanced Random Access Memory (ARAM)
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, MagnetoResonatic)
                .input(NAQUADAH_WAFER)
                .output(ADVANCED_RAM_WAFER)
                .EUt(VA[ZPM].toLong())
                .duration(30 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, MagnetoResonatic)
                .input(NEUTRONIUM_WAFER)
                .output(ADVANCED_RAM_WAFER, 4)
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, MagnetoResonatic)
                .input(HASSIUM_WAFER)
                .output(ADVANCED_RAM_WAFER, 8)
                .EUt(VA[UHV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Advanced Random Access Memory (ARAM) Chip
            CUTTER_RECIPES.recipeBuilder()
                .input(ADVANCED_RAM_WAFER, 1)
                .output(ADVANCED_RAM_CHIP, 16)
                .EUt(VA[ZPM].toLong())
                .duration(20 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Nano Power Integrate Circuit (NPIC) Wafer
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, LithiumNiobate)
                .input(NAQUADAH_WAFER)
                .output(NANO_PIC_WAFER)
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE + 20 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, LithiumNiobate)
                .input(NEUTRONIUM_WAFER)
                .output(NANO_PIC_WAFER, 4)
                .EUt(VA[UV].toLong())
                .duration(20 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, LithiumNiobate)
                .input(HASSIUM_WAFER)
                .output(NANO_PIC_WAFER, 8)
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Nano Power Integrate Circuit (NPIC) Chip
            CUTTER_RECIPES.recipeBuilder()
                .input(NANO_PIC_WAFER)
                .output(NANO_PIC_CHIP, 2)
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Pico Power Integrate Circuit (PPIC) Wafer
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, LuTmYVO)
                .input(NAQUADAH_WAFER)
                .output(PICO_PIC_WAFER)
                .EUt(VA[UV].toLong())
                .duration(1 * MINUTE + 20 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, LuTmYVO)
                .input(NEUTRONIUM_WAFER)
                .output(PICO_PIC_WAFER, 4)
                .EUt(VA[UHV].toLong())
                .duration(20 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, LuTmYVO)
                .input(HASSIUM_WAFER)
                .output(PICO_PIC_WAFER, 8)
                .EUt(VA[UEV].toLong())
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Pico Power Integrate Circuit (PPIC) Chip
            CUTTER_RECIPES.recipeBuilder()
                .input(PICO_PIC_WAFER)
                .output(PICO_PIC_CHIP, 2)
                .EUt(VA[UV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Engraved Diamond Chip
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, Diamond)
                .input(gemExquisite, Diamond)
                .output(ENGRAVED_DIAMOND_CHIP)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, Diamond)
                .input(gemSolitary, Diamond)
                .output(ENGRAVED_DIAMOND_CHIP, 2)
                .EUt(VA[LV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Engraved Ruby Chip
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, Ruby)
                .input(gemExquisite, Ruby)
                .output(ENGRAVED_RUBY_CHIP)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, Ruby)
                .input(gemSolitary, Ruby)
                .output(ENGRAVED_RUBY_CHIP, 2)
                .EUt(VA[LV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Engraved Sapphire Chip
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, Sapphire)
                .input(gemExquisite, Sapphire)
                .output(ENGRAVED_SAPPHIRE_CHIP)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, Sapphire)
                .input(gemSolitary, Sapphire)
                .output(ENGRAVED_SAPPHIRE_CHIP, 2)
                .EUt(VA[LV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

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

        }

    }

}