package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.RecipeMaps.POLARIZER_RECIPES
import gregtech.api.recipes.category.RecipeCategories.EXTRACTOR_RECYCLING
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Europium
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
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HadronicResonantGas
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.common.block.GTLiteBlocks

internal object MagnetiumChain
{

    // @formatter:off

    fun init()
    {
        // Ingot
        POLARIZER_RECIPES.removeRecipe(OreDictUnifier.get(ingot, Europium))
        POLARIZER_RECIPES.addRecipe {
            input(ingot, Duranium)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(ingot, Magnetium)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(ingot, Magnetium)
            fluidOutputs(Magnetium.getFluid(L))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Plate
        POLARIZER_RECIPES.removeRecipe(OreDictUnifier.get(plate, Europium))
        POLARIZER_RECIPES.addRecipe {
            input(plate, Duranium)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(plate, Magnetium)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(plate, Magnetium)
            fluidOutputs(Magnetium.getFluid(L))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Double Plate
        POLARIZER_RECIPES.addRecipe {
            input(plateDouble, Duranium)
            fluidInputs(HadronicResonantGas.getFluid(500))
            output(plateDouble, Magnetium)
            EUt(VA[UIV])
            duration(20 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(plateDouble, Magnetium)
            fluidOutputs(Magnetium.getFluid(L * 2))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Dense Plate
        POLARIZER_RECIPES.addRecipe {
            input(plateDense, Duranium)
            fluidInputs(HadronicResonantGas.getFluid(2250))
            output(plateDense, Magnetium)
            EUt(VA[UIV])
            duration(1 * MINUTE + 30 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(plateDense, Magnetium)
            fluidOutputs(Magnetium.getFluid(L * 9))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Foil
        POLARIZER_RECIPES.removeRecipe(OreDictUnifier.get(foil, Europium))
        POLARIZER_RECIPES.addRecipe {
            input(foil, Duranium, 4)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(foil, Magnetium, 4)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(foil, Magnetium)
            fluidOutputs(Magnetium.getFluid(L / 4))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Stick
        POLARIZER_RECIPES.removeRecipe(OreDictUnifier.get(stick, Europium))
        POLARIZER_RECIPES.addRecipe {
            input(stick, Duranium, 2)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(stick, Magnetium, 2)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(stick, Magnetium)
            fluidOutputs(Magnetium.getFluid(L / 2))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Long Stick
        POLARIZER_RECIPES.removeRecipe(OreDictUnifier.get(stickLong, Europium))
        POLARIZER_RECIPES.addRecipe {
            input(stickLong, Duranium)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(stickLong, Magnetium)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(stickLong, Magnetium)
            fluidOutputs(Magnetium.getFluid(L))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Bolt
        POLARIZER_RECIPES.addRecipe {
            input(bolt, Duranium, 8)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(bolt, Magnetium, 8)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(bolt, Magnetium)
            fluidOutputs(Magnetium.getFluid(L / 8))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Screw
        POLARIZER_RECIPES.addRecipe {
            input(screw, Duranium, 8)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(screw, Magnetium, 8)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(screw, Magnetium)
            fluidOutputs(Magnetium.getFluid(L / 8))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Ring
        POLARIZER_RECIPES.addRecipe {
            input(ring, Duranium, 4)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(ring, Magnetium, 4)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(ring, Magnetium)
            fluidOutputs(Magnetium.getFluid(L / 4))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Round
        POLARIZER_RECIPES.addRecipe {
            input(round, Duranium, 8)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(round, Magnetium, 8)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(round, Magnetium)
            fluidOutputs(Magnetium.getFluid(L / 8))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Spring
        POLARIZER_RECIPES.addRecipe {
            input(spring, Duranium)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(spring, Magnetium)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(spring, Magnetium)
            fluidOutputs(Magnetium.getFluid(L))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Small Spring
        POLARIZER_RECIPES.addRecipe {
            input(springSmall, Duranium, 4)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(springSmall, Magnetium, 4)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(springSmall, Magnetium)
            fluidOutputs(Magnetium.getFluid(L / 4))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Gear
        POLARIZER_RECIPES.addRecipe {
            input(gear, Duranium)
            fluidInputs(HadronicResonantGas.getFluid(1000))
            output(gear, Magnetium)
            EUt(VA[UIV])
            duration(40 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(gear, Magnetium)
            fluidOutputs(Magnetium.getFluid(L * 4))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Small Gear
        POLARIZER_RECIPES.addRecipe {
            input(gearSmall, Duranium)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(gearSmall, Magnetium)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(gearSmall, Magnetium)
            fluidOutputs(Magnetium.getFluid(L))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Fine Wire
        POLARIZER_RECIPES.removeRecipe(OreDictUnifier.get(wireFine, Europium))
        POLARIZER_RECIPES.addRecipe {
            input(wireFine, Duranium, 8)
            fluidInputs(HadronicResonantGas.getFluid(250))
            output(wireFine, Magnetium, 8)
            EUt(VA[UIV])
            duration(10 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(wireFine, Magnetium)
            fluidOutputs(Magnetium.getFluid(L / 8))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Rotor
        POLARIZER_RECIPES.addRecipe {
            input(rotor, Duranium)
            fluidInputs(HadronicResonantGas.getFluid(1000))
            output(rotor, Magnetium)
            EUt(VA[UIV])
            duration(40 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(rotor, Magnetium)
            fluidOutputs(Magnetium.getFluid(L * 4))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Block
        POLARIZER_RECIPES.removeRecipe(OreDictUnifier.get(block, Europium))
        POLARIZER_RECIPES.addRecipe {
            input(block, Duranium)
            fluidInputs(HadronicResonantGas.getFluid(2250))
            output(block, Magnetium)
            EUt(VA[UIV])
            duration(1 * MINUTE + 30 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(block, Magnetium)
            fluidOutputs(Magnetium.getFluid(L * 9))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Frame
        POLARIZER_RECIPES.addRecipe {
            input(frameGt, Duranium)
            fluidInputs(HadronicResonantGas.getFluid(500))
            output(frameGt, Magnetium)
            EUt(VA[UIV])
            duration(20 * SECOND)
        }

        EXTRACTOR_RECIPES.addRecipe {
            input(frameGt, Magnetium)
            fluidOutputs(Magnetium.getFluid(L * 2))
            EUt(VA[IV])
            duration(10 * SECOND)
            category(EXTRACTOR_RECYCLING)
        }

        // Sheeted Frame
        ModHandler.addShapedRecipe(true, "magnetium_sheeted_frame", GTLiteBlocks.SHEETED_FRAMES[Magnetium]!!.getItem(Magnetium).copy(12),
            "PFP", "PhP", "PFP",
            'P', UnificationEntry(plate, Magnetium),
            'F', UnificationEntry(frameGt, Magnetium))

        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(10)
            input(plate, Magnetium, 3)
            input(frameGt, Magnetium)
            outputs(GTLiteBlocks.SHEETED_FRAMES[Magnetium]!!.getItem(Magnetium).copy(6))
            EUt(7) // ULV
            duration(2 * SECOND + 5 * TICK)
        }

        // Wall
        ModHandler.addShapedRecipe(true, "magnetium_wall_gt", GTLiteBlocks.METAL_WALLS[Magnetium]!!.getItem(Magnetium).copy(6),
            "hPS", "P P", "SPd",
            'P', UnificationEntry(plate, Magnetium),
            'S', UnificationEntry(screw, Magnetium))

        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(11)
            input(plate, Magnetium, 2)
            input(screw, Magnetium)
            outputs(GTLiteBlocks.METAL_WALLS[Magnetium]!!.getItem(Magnetium).copy(3))
            EUt(7)
            duration(2 * SECOND + 5 * TICK)
        }
    }

    // @formatter:on

}