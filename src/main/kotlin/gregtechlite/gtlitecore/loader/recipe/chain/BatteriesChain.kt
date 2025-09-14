package gregtechlite.gtlitecore.loader.recipe.chain

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
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FullerenePolymerMatrix
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kevlar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RealizedQuantumFoamShard
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_LARGE_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_LARGE_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_MEDIUM_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_MEDIUM_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_SMALL_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_HULL_SMALL_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_MAX_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_OpV_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_UEV_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_UHV_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_UIV_NEUTRONIUM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BATTERY_UXV_INFINITY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANOSILICON_CATHODE

internal object BatteriesChain
{

    // @formatter:off

    fun init()
    {
        // Small Neutronium Battery Hull (UHV)
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(cableGtSingle, Europium, 2)
            .input(plate, Tritanium, 2)
            .fluidInputs(Kevlar.getFluid(L))
            .output(BATTERY_HULL_SMALL_NEUTRONIUM)
            .EUt(VA[UV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Medium Neutronium Battery Hull (UEV)
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(cableGtSingle, Seaborgium, 2)
            .input(plate, Tritanium, 6)
            .fluidInputs(Kevlar.getFluid(L * 2))
            .output(BATTERY_HULL_MEDIUM_NEUTRONIUM)
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Large Neutronium Battery Hull (UIV)
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(cableGtSingle, SuperheavyAlloyA, 2)
            .input(plate, Tritanium, 18)
            .input(NANOSILICON_CATHODE, 4)
            .fluidInputs(Kevlar.getFluid(L * 4))
            .output(BATTERY_HULL_LARGE_NEUTRONIUM)
            .EUt(VA[UEV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // Small Infinity Battery Hull (UXV)
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(cableGtSingle, SuperheavyAlloyB, 2)
            .input(plate, Shirabon, 2)
            .input(NANOSILICON_CATHODE, 8)
            .fluidInputs(FullerenePolymerMatrix.getFluid(L))
            .output(BATTERY_HULL_SMALL_INFINITY)
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Medium Infinity Battery Hull (OpV)
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(cableGtSingle, Periodicium, 2)
            .input(plate, Shirabon, 6)
            .input(NANOSILICON_CATHODE, 16)
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 2))
            .output(BATTERY_HULL_MEDIUM_INFINITY)
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Large Infinity Battery Hull (MAX)
        ASSEMBLER_RECIPES.recipeBuilder()
            .input(cableGtSingle, RealizedQuantumFoamShard, 2)
            .input(plate, Shirabon, 18)
            .input(NANOSILICON_CATHODE, 32)
            .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
            .output(BATTERY_HULL_LARGE_INFINITY)
            .EUt(VA[OpV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // Small Neutronium Battery
        CANNER_RECIPES.recipeBuilder()
            .input(BATTERY_HULL_SMALL_NEUTRONIUM)
            .input(dust, Neutronium, 2)
            .output(BATTERY_UHV_NEUTRONIUM)
            .EUt(VH[LuV])
            .duration(17 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Medium Neutronium Battery
        CANNER_RECIPES.recipeBuilder()
            .input(BATTERY_HULL_MEDIUM_NEUTRONIUM)
            .input(dust, Neutronium, 8)
            .output(BATTERY_UEV_NEUTRONIUM)
            .EUt(VA[LuV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // Large Neutronium Battery
        CANNER_RECIPES.recipeBuilder()
            .input(BATTERY_HULL_LARGE_NEUTRONIUM)
            .input(dust, Neutronium, 16)
            .output(BATTERY_UIV_NEUTRONIUM)
            .EUt(VH[ZPM])
            .duration(22 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Small Infinity Battery
        CANNER_RECIPES.recipeBuilder()
            .input(BATTERY_HULL_SMALL_INFINITY)
            .input(dust, Infinity, 2)
            .output(BATTERY_UXV_INFINITY)
            .EUt(VA[ZPM])
            .duration(25 * SECOND)
            .buildAndRegister()

        // Medium Infinity Battery
        CANNER_RECIPES.recipeBuilder()
            .input(BATTERY_HULL_MEDIUM_INFINITY)
            .input(dust, Infinity, 8)
            .output(BATTERY_OpV_INFINITY)
            .EUt(VH[UV])
            .duration(27 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Large Infinity Battery
        CANNER_RECIPES.recipeBuilder()
            .input(BATTERY_HULL_LARGE_INFINITY)
            .input(dust, Infinity, 16)
            .output(BATTERY_MAX_INFINITY)
            .EUt(VA[UV])
            .duration(30 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}