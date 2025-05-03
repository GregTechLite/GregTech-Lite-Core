package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
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
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Bedrockium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HalkoniteSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolyphosphonitrileFluoroRubber
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolytetramethyleneGlycolRubber
import magicbook.gtlitecore.api.utils.GTLiteUtility
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks

@Suppress("MISSING_DEPENDENCY_CLASS")
class HalkoniteSteelChain
{

    companion object
    {

        // Raw Transform Rule:
        // - 1M <-> 8 sec <-> 144L Molten Liquid.
        fun init()
        {

            // Bedrockium Ingot -> Halkonite Steel Ingot
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .circuitMeta(11)
                .input(ingot, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L))
                .output(ingot, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // Bedrockium Block -> Halkonite Steel Block
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(block, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L * 9))
                .output(block, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE + 12 * SECOND)
                .buildAndRegister()

            // Bedrockium Plate -> Halkonite Steel Plate
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(plate, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L))
                .output(plate, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // Bedrockium Stick -> Halkonite Steel Stick
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stick, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L / 2))
                .output(stick, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // Bedrockium Long Stick -> Halkonite Steel Long Stick
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(stickLong, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L))
                .output(stickLong, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // Bedrockium Spring -> Halkonite Steel Spring
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(spring, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L))
                .output(spring, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // Bedrockium Small Spring -> Halkonite Steel Small Spring
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(springSmall, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L / 2))
                .output(springSmall, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // Bedrockium Foil -> Halkonite Steel Foil
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(foil, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L / 4))
                .output(foil, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // Bedrockium Bolt -> Halkonite Steel Bolt
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(bolt, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L / 8))
                .output(bolt, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // Bedrockium Screw -> Halkonite Steel Screw
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(screw, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L / 8))
                .output(screw, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // 1x Bedrockium Wire -> 1x Halkonite Steel Wire
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(wireGtSingle, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L / 2))
                .output(wireGtSingle, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister();

            // 2x Bedrockium Wire -> 2x Halkonite Steel Wire
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(wireGtDouble, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L))
                .output(wireGtDouble, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // 4x Bedrockium Wire -> 4x Halkonite Steel Wire
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(wireGtQuadruple, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L * 2))
                .output(wireGtQuadruple, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister();

            // 8x Bedrockium Wire -> 8x Halkonite Steel Wire
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(wireGtOctal, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L * 4))
                .output(wireGtOctal, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(32 * SECOND)
                .buildAndRegister()

            // 16x Bedrockium Wire -> 16x Halkonite Steel Wire
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .circuitMeta(16)
                .input(wireGtHex, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L * 8))
                .output(wireGtHex, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE + 4 * SECOND)
                .buildAndRegister()

            // 1x Bedrockium Wire -> 1x Halkonite Steel Cable
            for (fluid in arrayOf(PolytetramethyleneGlycolRubber.getFluid(L / 2),
                PolyphosphonitrileFluoroRubber.getFluid(L / 2)))
            {
                FORGE_HAMMER_RECIPES.recipeBuilder()
                    .circuitMeta(11)
                    .input(wireGtSingle, Bedrockium)
                    .fluidInputs(HalkoniteSteel.getFluid(L / 2))
                    .fluidInputs(fluid)
                    .output(cableGtSingle, HalkoniteSteel)
                    .EUt(VA[UEV].toLong())
                    .duration(4 * SECOND)
                    .buildAndRegister()
            }

            // 2x Bedrockium Wire -> 2x Halkonite Steel Cable
            for (fluid in arrayOf(PolytetramethyleneGlycolRubber.getFluid(L),
                PolyphosphonitrileFluoroRubber.getFluid(L)))
            {
                FORGE_HAMMER_RECIPES.recipeBuilder()
                    .circuitMeta(12)
                    .input(wireGtDouble, Bedrockium)
                    .fluidInputs(HalkoniteSteel.getFluid(L))
                    .fluidInputs(fluid)
                    .output(cableGtDouble, HalkoniteSteel)
                    .EUt(VA[UEV].toLong())
                    .duration(8 * SECOND)
                    .buildAndRegister()
            }

            // 4x Bedrockium Wire -> 4x Halkonite Steel Cable
            for (fluid in arrayOf(PolytetramethyleneGlycolRubber.getFluid(L * 2),
                PolyphosphonitrileFluoroRubber.getFluid(L * 2)))
            {
                FORGE_HAMMER_RECIPES.recipeBuilder()
                    .circuitMeta(14)
                    .input(wireGtQuadruple, Bedrockium)
                    .fluidInputs(HalkoniteSteel.getFluid(L * 2))
                    .fluidInputs(fluid)
                    .output(cableGtQuadruple, HalkoniteSteel)
                    .EUt(VA[UEV].toLong())
                    .duration(16 * SECOND)
                    .buildAndRegister()
            }

            // 8x Bedrockium Wire -> 8x Halkonite Steel Cable
            for (fluid in arrayOf(PolytetramethyleneGlycolRubber.getFluid(L * 4),
                PolyphosphonitrileFluoroRubber.getFluid(L * 4)))
            {
                FORGE_HAMMER_RECIPES.recipeBuilder()
                    .circuitMeta(18)
                    .input(wireGtOctal, Bedrockium)
                    .fluidInputs(HalkoniteSteel.getFluid(L * 4))
                    .fluidInputs(fluid)
                    .output(cableGtOctal, HalkoniteSteel)
                    .EUt(VA[UEV].toLong())
                    .duration(32 * SECOND)
                    .buildAndRegister()
            }

            // 16x Bedrockium Wire -> 16x Halkonite Steel Cable
            for (fluid in arrayOf(PolytetramethyleneGlycolRubber.getFluid(L * 8),
                PolyphosphonitrileFluoroRubber.getFluid(L * 8)))
            {
                FORGE_HAMMER_RECIPES.recipeBuilder()
                    .circuitMeta(26)
                    .input(wireGtHex, Bedrockium, 1)
                    .fluidInputs(HalkoniteSteel.getFluid(L * 8))
                    .fluidInputs(fluid)
                    .output(cableGtHex, HalkoniteSteel, 1)
                    .EUt(VA[UEV].toLong())
                    .duration(1 * MINUTE + 4 * SECOND)
                    .buildAndRegister()
            }

            // Bedrockium Frame -> Halkonite Steel Frame
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(frameGt, Bedrockium)
                .fluidInputs(HalkoniteSteel.getFluid(L * 4))
                .output(frameGt, HalkoniteSteel)
                .EUt(VA[UEV].toLong())
                .duration(16 * SECOND)
                .buildAndRegister()

            // Halkonite Steel Wall
            ModHandler.addShapedRecipe(true, "halkonite_steel_wall_gt", GTLiteUtility.copy(GTLiteMetaBlocks.WALLS[HalkoniteSteel]!!.getItem(HalkoniteSteel), 6),
                "hPS", "P P", "SPd",
                'P', UnificationEntry(plate, HalkoniteSteel),
                'S', UnificationEntry(screw, HalkoniteSteel))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(11)
                .input(plate, HalkoniteSteel, 2)
                .input(screw, HalkoniteSteel)
                .outputs(GTLiteUtility.copy(GTLiteMetaBlocks.WALLS[HalkoniteSteel]!!.getItem(HalkoniteSteel), 3))
                .EUt(7)
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()

            // Halkonite Steel Sheeted Frame
            ModHandler.addShapedRecipe(true, "halkonite_steel_sheeted_frame", GTLiteUtility.copy(GTLiteMetaBlocks.SHEETED_FRAMES[HalkoniteSteel]!!.getItem(HalkoniteSteel), 12),
                "PFP", "PhP", "PFP",
                'P', UnificationEntry(plate, HalkoniteSteel),
                'F', UnificationEntry(frameGt, HalkoniteSteel))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(10)
                .input(plate, HalkoniteSteel, 3)
                .input(frameGt, HalkoniteSteel)
                .outputs(GTLiteUtility.copy(GTLiteMetaBlocks.SHEETED_FRAMES[HalkoniteSteel]!!.getItem(HalkoniteSteel), 6))
                .EUt(7) // ULV
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()

        }
    }

}