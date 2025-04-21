package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
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
import gregtech.common.items.MetaItems.HIGHLY_ADVANCED_SOC_WAFER
import gregtech.common.items.MetaItems.NAQUADAH_WAFER
import gregtech.common.items.MetaItems.NEUTRONIUM_WAFER
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CubicZirconia
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumNiobate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnetoResonatic
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NdYAG
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ADVANCED_RAM_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ADVANCED_RAM_WAFER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ENGRAVED_DIAMOND_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ENGRAVED_RUBY_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ENGRAVED_SAPPHIRE_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANO_PIC_WAFER
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
            // Disabled craftingLensWhite ore dict of Nether Star and Cubic Zirconia lens.
            OreDictionary.getOres("craftingLensWhite").removeIf { item ->
                item.isItemEqual(OreDictUnifier.get(lens, NetherStar)) or
                        item.isItemEqual(OreDictUnifier.get(lens, CubicZirconia))
            }

            // Disabled craftingLensPink ore dict of Magneto Resonatic and Nd:YAG.
            OreDictionary.getOres("craftingLensPink").removeIf { item ->
                item.isItemEqual(OreDictUnifier.get(lens, MagnetoResonatic)) or
                        item.isItemEqual(OreDictUnifier.get(lens, NdYAG))
            }

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
                .input(NAQUADAH_WAFER, 1)
                .output(ADVANCED_RAM_WAFER)
                .EUt(VA[ZPM].toLong())
                .duration(30 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, MagnetoResonatic)
                .input(NEUTRONIUM_WAFER, 1)
                .output(ADVANCED_RAM_WAFER, 4)
                .EUt(VA[UV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

            // Advanced Random Access Memory (ARAM) Chip
            CUTTER_RECIPES.recipeBuilder()
                .input(ADVANCED_RAM_WAFER, 1)
                .output(ADVANCED_RAM_CHIP, 16)
                .EUt(VA[ZPM].toLong())
                .duration(20 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Nano Power Integrated Circuit (NPIC) Wafer
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
                .duration(50 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Nano Power Integrated Circuit (NPIC) Chip
            CUTTER_RECIPES.recipeBuilder()
                .input(NANO_PIC_WAFER)
                .output(NANO_PIC_CHIP, 2)
                .EUt(VA[ZPM].toLong())
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

            // Engraved Ruby Chip
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, Ruby)
                .input(gemExquisite, Ruby)
                .output(ENGRAVED_RUBY_CHIP)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            // Engraved Sapphire Chip
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(lens, Sapphire)
                .input(gemExquisite, Sapphire)
                .output(ENGRAVED_SAPPHIRE_CHIP)
                .EUt(VA[LV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

        }

    }

}