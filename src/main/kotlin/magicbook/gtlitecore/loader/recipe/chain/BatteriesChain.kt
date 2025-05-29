package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Seaborgium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.plate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FullerenePolymerMatrix
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kevlar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Periodicium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Shirabon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheavyAlloyA
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuperheavyAlloyB
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Universium
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BATTERY_HULL_LARGE_INFINITY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BATTERY_HULL_LARGE_NEUTRONIUM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BATTERY_HULL_MEDIUM_INFINITY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BATTERY_HULL_MEDIUM_NEUTRONIUM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BATTERY_HULL_SMALL_INFINITY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BATTERY_HULL_SMALL_NEUTRONIUM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BATTERY_MAX_INFINITY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BATTERY_OpV_INFINITY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BATTERY_UEV_NEUTRONIUM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BATTERY_UHV_NEUTRONIUM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BATTERY_UIV_NEUTRONIUM
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BATTERY_UXV_INFINITY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANOSILICON_CATHODE

@Suppress("MISSING_DEPENDENCY_CLASS")
class BatteriesChain
{

    companion object
    {

        fun init()
        {
            // Small Neutronium Battery Hull (UHV)
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(cableGtSingle, Europium, 2)
                .input(plate, Tritanium, 2)
                .fluidInputs(Kevlar.getFluid(L))
                .output(BATTERY_HULL_SMALL_NEUTRONIUM)
                .EUt(VA[UV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Medium Neutronium Battery Hull (UEV)
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(cableGtSingle, Seaborgium, 2)
                .input(plate, Tritanium, 6)
                .fluidInputs(Kevlar.getFluid(L * 2))
                .output(BATTERY_HULL_MEDIUM_NEUTRONIUM)
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Large Neutronium Battery Hull (UIV)
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(cableGtSingle, SuperheavyAlloyA, 2)
                .input(plate, Tritanium, 18)
                .input(NANOSILICON_CATHODE, 4)
                .fluidInputs(Kevlar.getFluid(L * 4))
                .output(BATTERY_HULL_LARGE_NEUTRONIUM)
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // Small Infinity Battery Hull (UXV)
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(cableGtSingle, SuperheavyAlloyB, 2)
                .input(plate, Shirabon, 2)
                .input(NANOSILICON_CATHODE, 8)
                .fluidInputs(FullerenePolymerMatrix.getFluid(L))
                .output(BATTERY_HULL_SMALL_INFINITY)
                .EUt(VA[UIV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Medium Infinity Battery Hull (OpV)
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(cableGtSingle, Periodicium, 2)
                .input(plate, Shirabon, 6)
                .input(NANOSILICON_CATHODE, 16)
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 2))
                .output(BATTERY_HULL_MEDIUM_INFINITY)
                .EUt(VA[UXV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Large Infinity Battery Hull (MAX)
            ASSEMBLER_RECIPES.recipeBuilder()
                .input(cableGtSingle, Universium, 2) // TODO MAX cable.
                .input(plate, Shirabon, 18)
                .input(NANOSILICON_CATHODE, 32)
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
                .output(BATTERY_HULL_LARGE_INFINITY)
                .EUt(VA[OpV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // Small Neutronium Battery
            CANNER_RECIPES.recipeBuilder()
                .input(BATTERY_HULL_SMALL_NEUTRONIUM)
                .input(dust, Neutronium, 2)
                .output(BATTERY_UHV_NEUTRONIUM)
                .EUt(VH[LuV].toLong())
                .duration(17 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Medium Neutronium Battery
            CANNER_RECIPES.recipeBuilder()
                .input(BATTERY_HULL_MEDIUM_NEUTRONIUM)
                .input(dust, Neutronium, 8)
                .output(BATTERY_UEV_NEUTRONIUM)
                .EUt(VA[LuV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // Large Neutronium Battery
            CANNER_RECIPES.recipeBuilder()
                .input(BATTERY_HULL_LARGE_NEUTRONIUM)
                .input(dust, Neutronium, 16)
                .output(BATTERY_UIV_NEUTRONIUM)
                .EUt(VH[ZPM].toLong())
                .duration(22 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Small Infinity Battery
            CANNER_RECIPES.recipeBuilder()
                .input(BATTERY_HULL_SMALL_INFINITY)
                .input(dust, Infinity, 2)
                .output(BATTERY_UXV_INFINITY)
                .EUt(VA[ZPM].toLong())
                .duration(25 * SECOND)
                .buildAndRegister()

            // Medium Infinity Battery
            CANNER_RECIPES.recipeBuilder()
                .input(BATTERY_HULL_MEDIUM_INFINITY)
                .input(dust, Infinity, 8)
                .output(BATTERY_OpV_INFINITY)
                .EUt(VH[UV].toLong())
                .duration(27 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Large Infinity Battery
            CANNER_RECIPES.recipeBuilder()
                .input(BATTERY_HULL_LARGE_INFINITY)
                .input(dust, Infinity, 16)
                .output(BATTERY_MAX_INFINITY)
                .EUt(VA[UV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

        }

    }

}