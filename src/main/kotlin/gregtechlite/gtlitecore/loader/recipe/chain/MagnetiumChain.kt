package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.POLARIZER_RECIPES
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.stack.UnificationEntry
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HadronicResonantGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.common.block.GTLiteBlocks

internal object MagnetiumChain
{

    // @formatter:off

    fun init()
    {
        // ingotDuranium -> ingotMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(ingot, Duranium)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(ingot, Magnetium)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // plateDuranium -> plateMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(plate, Duranium)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(plate, Magnetium)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // plateDoubleDuranium -> plateDoubleMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(plateDouble, Duranium)
            .fluidInputs(HadronicResonantGas.getFluid(250 * 2))
            .output(plateDouble, Magnetium)
            .EUt(VA[UIV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // plateDenseDuranium -> plateDenseMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(plateDense, Duranium)
            .fluidInputs(HadronicResonantGas.getFluid(250 * 9))
            .output(plateDense, Magnetium)
            .EUt(VA[UIV])
            .duration(1 * MINUTE + 30 * SECOND)
            .buildAndRegister()

        // foilDuranium -> foilMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(foil, Duranium, 4)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(foil, Magnetium, 4)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // stickDuranium -> stickMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(stick, Duranium, 2)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(stick, Magnetium, 2)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // stickLongDuranium -> stickLongMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(stickLong, Duranium)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(stickLong, Magnetium)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // boltDuranium -> boltMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(bolt, Duranium, 8)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(bolt, Magnetium, 8)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // screwDuranium -> screwMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(screw, Duranium, 8)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(screw, Magnetium, 8)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // ringDuranium -> ringMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(ring, Duranium, 4)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(ring, Magnetium, 4)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // roundDuranium -> roundMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(round, Duranium, 8)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(round, Magnetium, 8)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // springDuranium -> springMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(spring, Duranium)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(spring, Magnetium)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // springSmallDuranium -> springSmallMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(springSmall, Duranium, 4)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(springSmall, Magnetium, 4)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // gearDuranium -> gearMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(gear, Duranium)
            .fluidInputs(HadronicResonantGas.getFluid(250 * 4))
            .output(gear, Magnetium)
            .EUt(VA[UIV])
            .duration(40 * SECOND)
            .buildAndRegister()

        // gearSmallDuranium -> gearSmallMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(gearSmall, Duranium)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(gearSmall, Magnetium)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // wireFineDuranium -> wireFineMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(wireFine, Duranium, 8)
            .fluidInputs(HadronicResonantGas.getFluid(250))
            .output(wireFine, Magnetium, 8)
            .EUt(VA[UIV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // rotorDuranium -> rotorMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(rotor, Duranium)
            .fluidInputs(HadronicResonantGas.getFluid(250 * 4))
            .output(rotor, Magnetium)
            .EUt(VA[UIV])
            .duration(40 * SECOND)
            .buildAndRegister()

        // blockDuranium -> blockMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(block, Duranium)
            .fluidInputs(HadronicResonantGas.getFluid(250 * 9))
            .output(block, Magnetium)
            .EUt(VA[UIV])
            .duration(1 * MINUTE + 30 * SECOND)
            .buildAndRegister()

        // frameGtDuranium -> frameGtMagnetium
        POLARIZER_RECIPES.recipeBuilder()
            .input(frameGt, Duranium)
            .fluidInputs(HadronicResonantGas.getFluid(250 * 2))
            .output(frameGt, Magnetium)
            .EUt(VA[UIV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // sheetedFrameMagnetium
        ModHandler.addShapedRecipe(true, "magnetium_sheeted_frame", GTLiteBlocks.SHEETED_FRAMES[Magnetium]!!.getItem(Magnetium).copy(12),
            "PFP", "PhP", "PFP",
            'P', UnificationEntry(plate, Magnetium),
            'F', UnificationEntry(frameGt, Magnetium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(10)
            .input(plate, Magnetium, 3)
            .input(frameGt, Magnetium)
            .outputs(GTLiteBlocks.SHEETED_FRAMES[Magnetium]!!.getItem(Magnetium).copy(6))
            .EUt(7) // ULV
            .duration(2 * SECOND + 5 * TICK)
            .buildAndRegister()

        // wallGtMagnetium
        ModHandler.addShapedRecipe(true, "magnetium_wall_gt", GTLiteBlocks.METAL_WALLS[Magnetium]!!.getItem(Magnetium).copy(6),
            "hPS", "P P", "SPd",
            'P', UnificationEntry(plate, Magnetium),
            'S', UnificationEntry(screw, Magnetium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(11)
            .input(plate, Magnetium, 2)
            .input(screw, Magnetium)
            .outputs(GTLiteBlocks.METAL_WALLS[Magnetium]!!.getItem(Magnetium).copy(3))
            .EUt(7)
            .duration(2 * SECOND + 5 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}