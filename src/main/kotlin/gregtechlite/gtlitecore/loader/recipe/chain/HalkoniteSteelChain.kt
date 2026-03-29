package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.cableGtDouble
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtOctal
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.spring
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.api.unification.stack.UnificationEntry
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MATTER_RESHAPING_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolyphosphonitrileFluoroRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolytetramethyleneGlycolRubber
import gregtechlite.gtlitecore.common.block.GTLiteBlocks

/**
 * For each material amount with magic number `M`, `1M` material equivalence to 8 second for duration and 144L molten
 * halkonite steel liquid. TODO: Write a convert method to generate these relations automatically?
 */
internal object HalkoniteSteelChain
{

    // @formatter:off

    fun init()
    {
        // Resolve recipe conflicts between bedrockium 3:2 ingot to plate with bedrockium ingot to halkonite steel ingot.
        FORGE_HAMMER_RECIPES.removeRecipe(OreDictUnifier.get(ingot, Bedrockium, 3))
        MATTER_RESHAPING_RECIPES.removeRecipe(OreDictUnifier.get(ingot, Bedrockium, 3))
        FORGE_HAMMER_RECIPES.addRecipe {
            circuitMeta(1)
            input(ingot, Bedrockium, 3)
            output(plate, Bedrockium, 2)
            EUt(VA[LV])
            duration(4 * SECOND + 18 * TICK)
        }

        // Ingot
        MATTER_RESHAPING_RECIPES.addRecipe {
            circuitMeta(2)
            input(ingot, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L))
            output(ingot, HalkoniteSteel)
            EUt(VA[UEV])
            duration(8 * SECOND)
        }

        // Block
        MATTER_RESHAPING_RECIPES.addRecipe {
            input(block, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L * 9))
            output(block, HalkoniteSteel)
            EUt(VA[UEV])
            duration(1 * MINUTE + 12 * SECOND)
        }

        // Plate
        MATTER_RESHAPING_RECIPES.addRecipe {
            input(plate, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L))
            output(plate, HalkoniteSteel)
            EUt(VA[UEV])
            duration(8 * SECOND)
        }

        // Double Plate
        MATTER_RESHAPING_RECIPES.addRecipe {
            input(plateDouble, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L * 2))
            output(plateDouble, HalkoniteSteel)
            EUt(VA[UEV])
            duration(16 * SECOND)
        }

        // Dense Plate
        MATTER_RESHAPING_RECIPES.addRecipe {
            input(plateDense, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L * 9))
            output(plateDense, HalkoniteSteel)
            EUt(VA[UEV])
            duration(1 * MINUTE + 12 * SECOND)
        }

        // Resolve recipe conflicts between bedrockium stick to long stick with bedrockium stick to halkonite steel stick.
        FORGE_HAMMER_RECIPES.removeRecipe(OreDictUnifier.get(stick, Bedrockium, 2))
        MATTER_RESHAPING_RECIPES.removeRecipe(OreDictUnifier.get(stick, Bedrockium, 2))
        FORGE_HAMMER_RECIPES.addRecipe {
            circuitMeta(1)
            input(stick, Bedrockium, 2)
            output(stickLong, Bedrockium)
            EUt(VA[LV])
            duration(4 * SECOND + 18 * TICK)
        }

        MATTER_RESHAPING_RECIPES.addRecipe {
            circuitMeta(2)
            input(stick, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L / 2))
            output(stick, HalkoniteSteel)
            EUt(VA[UEV])
            duration(4 * SECOND)
        }

        // Long Stick
        MATTER_RESHAPING_RECIPES.addRecipe {
            input(stickLong, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L))
            output(stickLong, HalkoniteSteel)
            EUt(VA[UEV])
            duration(8 * SECOND)
        }

        // Spring
        MATTER_RESHAPING_RECIPES.addRecipe {
            input(spring, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L))
            output(spring, HalkoniteSteel)
            EUt(VA[UEV])
            duration(8 * SECOND)
        }

        // Small Spring
        MATTER_RESHAPING_RECIPES.addRecipe {
            input(springSmall, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L / 2))
            output(springSmall, HalkoniteSteel)
            EUt(VA[UEV])
            duration(4 * SECOND)
        }

        // Foil
        MATTER_RESHAPING_RECIPES.addRecipe {
            input(foil, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L / 4))
            output(foil, HalkoniteSteel)
            EUt(VA[UEV])
            duration(2 * SECOND)
        }

        // Bolt
        MATTER_RESHAPING_RECIPES.addRecipe {
            input(bolt, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L / 8))
            output(bolt, HalkoniteSteel)
            EUt(VA[UEV])
            duration(1 * SECOND)
        }

        // Screw
        MATTER_RESHAPING_RECIPES.addRecipe {
            input(screw, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L / 8))
            output(screw, HalkoniteSteel)
            EUt(VA[UEV])
            duration(1 * SECOND)
        }

        // Wire
        MATTER_RESHAPING_RECIPES.addRecipe {
            circuitMeta(1)
            input(wireGtSingle, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L / 2))
            output(wireGtSingle, HalkoniteSteel)
            EUt(VA[UEV])
            duration(4 * SECOND)
        }

        // 2x Wire
        MATTER_RESHAPING_RECIPES.addRecipe {
            circuitMeta(1)
            input(wireGtDouble, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L))
            output(wireGtDouble, HalkoniteSteel)
            EUt(VA[UEV])
            duration(8 * SECOND)
        }

        // 4x Wire
        MATTER_RESHAPING_RECIPES.addRecipe {
            circuitMeta(1)
            input(wireGtQuadruple, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L * 2))
            output(wireGtQuadruple, HalkoniteSteel)
            EUt(VA[UEV])
            duration(16 * SECOND)
        }

        // 8x Wire
        MATTER_RESHAPING_RECIPES.addRecipe {
            circuitMeta(1)
            input(wireGtOctal, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L * 4))
            output(wireGtOctal, HalkoniteSteel)
            EUt(VA[UEV])
            duration(32 * SECOND)
        }

        // 16x Wire
        MATTER_RESHAPING_RECIPES.addRecipe {
            circuitMeta(1)
            input(wireGtHex, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L * 8))
            output(wireGtHex, HalkoniteSteel)
            EUt(VA[UEV])
            duration(1 * MINUTE + 4 * SECOND)
        }

        // Cable
        for (fluid in arrayOf(
            PolytetramethyleneGlycolRubber.getFluid(L / 2),
            PolyphosphonitrileFluoroRubber.getFluid(L / 2)))
        {
            MATTER_RESHAPING_RECIPES.addRecipe {
                circuitMeta(2)
                input(wireGtSingle, Bedrockium)
                fluidInputs(HalkoniteSteel.getFluid(L / 2))
                fluidInputs(fluid)
                output(cableGtSingle, HalkoniteSteel)
                EUt(VA[UEV])
                duration(4 * SECOND)
            }
        }

        // 2x Cable
        for (fluid in arrayOf(
            PolytetramethyleneGlycolRubber.getFluid(L),
            PolyphosphonitrileFluoroRubber.getFluid(L)))
        {
            MATTER_RESHAPING_RECIPES.addRecipe {
                circuitMeta(2)
                input(wireGtDouble, Bedrockium)
                fluidInputs(HalkoniteSteel.getFluid(L))
                fluidInputs(fluid)
                output(cableGtDouble, HalkoniteSteel)
                EUt(VA[UEV])
                duration(8 * SECOND)
            }
        }

        // 4x Cable
        for (fluid in arrayOf(
            PolytetramethyleneGlycolRubber.getFluid(L * 2),
            PolyphosphonitrileFluoroRubber.getFluid(L * 2)))
        {
            MATTER_RESHAPING_RECIPES.addRecipe {
                circuitMeta(2)
                input(wireGtQuadruple, Bedrockium)
                fluidInputs(HalkoniteSteel.getFluid(L * 2))
                fluidInputs(fluid)
                output(cableGtQuadruple, HalkoniteSteel)
                EUt(VA[UEV])
                duration(16 * SECOND)
            }
        }

        // 8x Cable
        for (fluid in arrayOf(
            PolytetramethyleneGlycolRubber.getFluid(L * 4),
            PolyphosphonitrileFluoroRubber.getFluid(L * 4)))
        {
            MATTER_RESHAPING_RECIPES.addRecipe {
                circuitMeta(2)
                input(wireGtOctal, Bedrockium)
                fluidInputs(HalkoniteSteel.getFluid(L * 4))
                fluidInputs(fluid)
                output(cableGtOctal, HalkoniteSteel)
                EUt(VA[UEV])
                duration(32 * SECOND)
            }
        }

        // 16x Cable
        for (fluid in arrayOf(
            PolytetramethyleneGlycolRubber.getFluid(L * 8),
            PolyphosphonitrileFluoroRubber.getFluid(L * 8)))
        {
            MATTER_RESHAPING_RECIPES.addRecipe {
                circuitMeta(2)
                input(wireGtHex, Bedrockium)
                fluidInputs(HalkoniteSteel.getFluid(L * 8))
                fluidInputs(fluid)
                output(cableGtHex, HalkoniteSteel)
                EUt(VA[UEV])
                duration(1 * MINUTE + 4 * SECOND)
            }
        }

        // Frame
        MATTER_RESHAPING_RECIPES.addRecipe {
            input(frameGt, Bedrockium)
            fluidInputs(HalkoniteSteel.getFluid(L * 4))
            output(frameGt, HalkoniteSteel)
            EUt(VA[UEV])
            duration(16 * SECOND)
        }

        // Wall
        ModHandler.addShapedRecipe(true, "halkonite_steel_wall_gt", GTLiteBlocks.METAL_WALLS[HalkoniteSteel]!!.getItem(HalkoniteSteel).copy(6),
            "hPS", "P P", "SPd",
            'P', UnificationEntry(plate, HalkoniteSteel),
            'S', UnificationEntry(screw, HalkoniteSteel))

        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(11)
            input(plate, HalkoniteSteel, 2)
            input(screw, HalkoniteSteel)
            outputs(GTLiteBlocks.METAL_WALLS[HalkoniteSteel]!!.getItem(HalkoniteSteel).copy(3))
            EUt(7) // ULV
            duration(2 * SECOND + 5 * TICK)
        }

        // Sheeted Frame
        ModHandler.addShapedRecipe(true, "halkonite_steel_sheeted_frame", GTLiteBlocks.SHEETED_FRAMES[HalkoniteSteel]!!.getItem(HalkoniteSteel).copy(12),
            "PFP", "PhP", "PFP",
            'P', UnificationEntry(plate, HalkoniteSteel),
            'F', UnificationEntry(frameGt, HalkoniteSteel))

        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(10)
            input(plate, HalkoniteSteel, 3)
            input(frameGt, HalkoniteSteel)
            outputs(GTLiteBlocks.SHEETED_FRAMES[HalkoniteSteel]!!.getItem(HalkoniteSteel).copy(6))
            EUt(7) // ULV
            duration(2 * SECOND + 5 * TICK)
        }
    }

    // @formatter:on

}