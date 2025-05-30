package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ELECTRIC_IMPLOSION_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BlackDwarfMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Eternity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MagnetohydrodynamicallyConstrainedStarMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RawStarMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Universium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WhiteDwarfMatter
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.nanite
import magicbook.gtlitecore.api.utils.GTLiteUtility
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks

@Suppress("MISSING_DEPENDENCY_CLASS")
class MHDCSMChain
{

    companion object
    {

        fun init()
        {
            // Basic recipes for Raw Star Matter.
            ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
                .input(nanite, WhiteDwarfMatter)
                .input(nanite, BlackDwarfMatter)
                .input(nanite, Universium)
                .fluidInputs(RawStarMatter.getFluid(L * 128))
                .fluidOutputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 32))
                .EUt(VA[UXV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Advanced recipes for Raw Star Matter.
            ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
                .input(nanite, Eternity)
                .input(nanite, Universium)
                .fluidInputs(RawStarMatter.getFluid(L * 512))
                .fluidOutputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 128))
                .EUt(VA[OpV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // ingot
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(ingot, Eternity)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(ingot, MagnetohydrodynamicallyConstrainedStarMatter)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // plate
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(plate, Eternity)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(plate, MagnetohydrodynamicallyConstrainedStarMatter)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // plateDouble
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(plateDouble, Eternity)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 2))
                .output(plateDouble, MagnetohydrodynamicallyConstrainedStarMatter)
                .EUt(VA[UXV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // plateDense
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(plateDense, Eternity)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 9))
                .output(plateDense, MagnetohydrodynamicallyConstrainedStarMatter)
                .EUt(VA[UXV].toLong())
                .duration(9 * SECOND)
                .buildAndRegister()

            // foil
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(foil, Eternity, 4)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(foil, MagnetohydrodynamicallyConstrainedStarMatter, 4)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // stick
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(stick, Eternity, 2)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(stick, MagnetohydrodynamicallyConstrainedStarMatter, 2)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // stickLong
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(stickLong, Eternity)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(stickLong, MagnetohydrodynamicallyConstrainedStarMatter)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // bolt
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(bolt, Eternity, 8)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(bolt, MagnetohydrodynamicallyConstrainedStarMatter, 8)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // screw
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(screw, Eternity, 8)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(screw, MagnetohydrodynamicallyConstrainedStarMatter, 8)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // ring
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(ring, Eternity, 4)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(ring, MagnetohydrodynamicallyConstrainedStarMatter, 4)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // round
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(round, Eternity, 8)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(round, MagnetohydrodynamicallyConstrainedStarMatter, 8)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // spring
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(spring, Eternity)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(spring, MagnetohydrodynamicallyConstrainedStarMatter)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // springSmall
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(springSmall, Eternity, 4)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(springSmall, MagnetohydrodynamicallyConstrainedStarMatter, 4)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // gear
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(gear, Eternity)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 4))
                .output(gear, MagnetohydrodynamicallyConstrainedStarMatter)
                .EUt(VA[UXV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // gearSmall
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(gearSmall, Eternity)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(gearSmall, MagnetohydrodynamicallyConstrainedStarMatter)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // wireFine
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(wireFine, Eternity, 8)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
                .output(wireFine, MagnetohydrodynamicallyConstrainedStarMatter, 8)
                .EUt(VA[UXV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

            // rotor
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(rotor, Eternity)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 4))
                .output(rotor, MagnetohydrodynamicallyConstrainedStarMatter)
                .EUt(VA[UXV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // block
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(block, Eternity)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 9))
                .output(block, MagnetohydrodynamicallyConstrainedStarMatter)
                .EUt(VA[UXV].toLong())
                .duration(9 * SECOND)
                .buildAndRegister()

            // frameGt
            AUTOCLAVE_RECIPES.recipeBuilder()
                .input(frameGt, Eternity)
                .fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 2))
                .output(frameGt, MagnetohydrodynamicallyConstrainedStarMatter)
                .EUt(VA[UXV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            // sheetedFrame
            ModHandler.addShapedRecipe(true, "magnetohydrodynamically_constrained_star_matter_sheeted_frame",
                GTLiteUtility.copy(GTLiteMetaBlocks.SHEETED_FRAMES[MagnetohydrodynamicallyConstrainedStarMatter]!!.getItem(MagnetohydrodynamicallyConstrainedStarMatter), 12),
                "PFP", "PhP", "PFP",
                'P', UnificationEntry(plate, MagnetohydrodynamicallyConstrainedStarMatter),
                'F', UnificationEntry(frameGt, MagnetohydrodynamicallyConstrainedStarMatter))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(10)
                .input(plate, MagnetohydrodynamicallyConstrainedStarMatter, 3)
                .input(frameGt, MagnetohydrodynamicallyConstrainedStarMatter)
                .outputs(GTLiteUtility.copy(GTLiteMetaBlocks.SHEETED_FRAMES[MagnetohydrodynamicallyConstrainedStarMatter]!!.getItem(MagnetohydrodynamicallyConstrainedStarMatter), 6))
                .EUt(7) // ULV
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()

            // wallGt
            ModHandler.addShapedRecipe(true, "magnetohydrodynamically_constrained_star_matter_wall_gt",
                GTLiteUtility.copy(GTLiteMetaBlocks.WALLS[MagnetohydrodynamicallyConstrainedStarMatter]!!.getItem(MagnetohydrodynamicallyConstrainedStarMatter), 6),
                "hPS", "P P", "SPd",
                'P', UnificationEntry(plate, MagnetohydrodynamicallyConstrainedStarMatter),
                'S', UnificationEntry(screw, MagnetohydrodynamicallyConstrainedStarMatter))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(11)
                .input(plate, MagnetohydrodynamicallyConstrainedStarMatter, 2)
                .input(screw, MagnetohydrodynamicallyConstrainedStarMatter)
                .outputs(GTLiteUtility.copy(GTLiteMetaBlocks.WALLS[MagnetohydrodynamicallyConstrainedStarMatter]!!.getItem(MagnetohydrodynamicallyConstrainedStarMatter), 3))
                .EUt(7)
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()

        }

    }

}