package gregtechlite.gtlitecore.loader.recipe.chain

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
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ELECTRIC_IMPLOSION_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagnetohydrodynamicallyConstrainedStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RawStarMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Universium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WhiteDwarfMatter
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.block.GTLiteBlocks

internal object MHDCSMChain
{

    // @formatter:off

    fun init()
    {
        // Basic recipes for Raw Star Matter.
        ELECTRIC_IMPLOSION_RECIPES.addRecipe {
            input(nanite, WhiteDwarfMatter)
            input(nanite, BlackDwarfMatter)
            input(nanite, Universium)
            fluidInputs(RawStarMatter.getFluid(L * 128))
            fluidOutputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 32))
            EUt(VA[UXV])
            duration(5 * SECOND)
        }

        // Advanced recipes for Raw Star Matter.
        ELECTRIC_IMPLOSION_RECIPES.addRecipe {
            input(nanite, Eternity)
            input(nanite, Universium)
            fluidInputs(RawStarMatter.getFluid(L * 512))
            fluidOutputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 128))
            EUt(VA[OpV])
            duration(2 * SECOND + 10 * TICK)
        }

        // Ingot
        AUTOCLAVE_RECIPES.addRecipe {
            input(ingot, Eternity)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(ingot, MagnetohydrodynamicallyConstrainedStarMatter)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Plate
        AUTOCLAVE_RECIPES.addRecipe {
            input(plate, Eternity)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(plate, MagnetohydrodynamicallyConstrainedStarMatter)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Double Plate
        AUTOCLAVE_RECIPES.addRecipe {
            input(plateDouble, Eternity)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 2))
            output(plateDouble, MagnetohydrodynamicallyConstrainedStarMatter)
            EUt(VA[UXV])
            duration(2 * SECOND)
        }

        // Dense Plate
        AUTOCLAVE_RECIPES.addRecipe {
            input(plateDense, Eternity)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 9))
            output(plateDense, MagnetohydrodynamicallyConstrainedStarMatter)
            EUt(VA[UXV])
            duration(9 * SECOND)
        }

        // Foil
        AUTOCLAVE_RECIPES.addRecipe {
            input(foil, Eternity, 4)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(foil, MagnetohydrodynamicallyConstrainedStarMatter, 4)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Stick
        AUTOCLAVE_RECIPES.addRecipe {
            input(stick, Eternity, 2)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(stick, MagnetohydrodynamicallyConstrainedStarMatter, 2)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Long Stick
        AUTOCLAVE_RECIPES.addRecipe {
            input(stickLong, Eternity)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(stickLong, MagnetohydrodynamicallyConstrainedStarMatter)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Bolt
        AUTOCLAVE_RECIPES.addRecipe {
            input(bolt, Eternity, 8)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(bolt, MagnetohydrodynamicallyConstrainedStarMatter, 8)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Screw
        AUTOCLAVE_RECIPES.addRecipe {
            input(screw, Eternity, 8)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(screw, MagnetohydrodynamicallyConstrainedStarMatter, 8)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Ring
        AUTOCLAVE_RECIPES.addRecipe {
            input(ring, Eternity, 4)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(ring, MagnetohydrodynamicallyConstrainedStarMatter, 4)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Round
        AUTOCLAVE_RECIPES.addRecipe {
            input(round, Eternity, 8)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(round, MagnetohydrodynamicallyConstrainedStarMatter, 8)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Spring
        AUTOCLAVE_RECIPES.addRecipe {
            input(spring, Eternity)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(spring, MagnetohydrodynamicallyConstrainedStarMatter)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Small Spring
        AUTOCLAVE_RECIPES.addRecipe {
            input(springSmall, Eternity, 4)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(springSmall, MagnetohydrodynamicallyConstrainedStarMatter, 4)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Gear
        AUTOCLAVE_RECIPES.addRecipe {
            input(gear, Eternity)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 4))
            output(gear, MagnetohydrodynamicallyConstrainedStarMatter)
            EUt(VA[UXV])
            duration(4 * SECOND)
        }

        // Small Gear
        AUTOCLAVE_RECIPES.addRecipe {
            input(gearSmall, Eternity)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(gearSmall, MagnetohydrodynamicallyConstrainedStarMatter)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Fine Wire
        AUTOCLAVE_RECIPES.addRecipe {
            input(wireFine, Eternity, 8)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L))
            output(wireFine, MagnetohydrodynamicallyConstrainedStarMatter, 8)
            EUt(VA[UXV])
            duration(1 * SECOND)
        }

        // Rotor
        AUTOCLAVE_RECIPES.addRecipe {
            input(rotor, Eternity)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 4))
            output(rotor, MagnetohydrodynamicallyConstrainedStarMatter)
            EUt(VA[UXV])
            duration(4 * SECOND)
        }

        // Block
        AUTOCLAVE_RECIPES.addRecipe {
            input(block, Eternity)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 9))
            output(block, MagnetohydrodynamicallyConstrainedStarMatter)
            EUt(VA[UXV])
            duration(9 * SECOND)
        }

        // Frame
        AUTOCLAVE_RECIPES.addRecipe {
            input(frameGt, Eternity)
            fluidInputs(MagnetohydrodynamicallyConstrainedStarMatter.getFluid(L * 2))
            output(frameGt, MagnetohydrodynamicallyConstrainedStarMatter)
            EUt(VA[UXV])
            duration(2 * SECOND)
        }

        // sheetedFrame
        ModHandler.addShapedRecipe(true, "magnetohydrodynamically_constrained_star_matter_sheeted_frame", GTLiteBlocks.SHEETED_FRAMES[MagnetohydrodynamicallyConstrainedStarMatter]!!.getItem(MagnetohydrodynamicallyConstrainedStarMatter).copy(12),
            "PFP", "PhP", "PFP",
            'P', UnificationEntry(plate, MagnetohydrodynamicallyConstrainedStarMatter),
            'F', UnificationEntry(frameGt, MagnetohydrodynamicallyConstrainedStarMatter))

        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(10)
            input(plate, MagnetohydrodynamicallyConstrainedStarMatter, 3)
            input(frameGt, MagnetohydrodynamicallyConstrainedStarMatter)
            outputs(GTLiteBlocks.SHEETED_FRAMES[MagnetohydrodynamicallyConstrainedStarMatter]!!.getItem(MagnetohydrodynamicallyConstrainedStarMatter).copy(6))
            EUt(7) // ULV
            duration(2 * SECOND + 5 * TICK)
        }

        // wallGt
        ModHandler.addShapedRecipe(true, "magnetohydrodynamically_constrained_star_matter_wall_gt", GTLiteBlocks.METAL_WALLS[MagnetohydrodynamicallyConstrainedStarMatter]!!.getItem(MagnetohydrodynamicallyConstrainedStarMatter).copy(6),
            "hPS", "P P", "SPd",
            'P', UnificationEntry(plate, MagnetohydrodynamicallyConstrainedStarMatter),
            'S', UnificationEntry(screw, MagnetohydrodynamicallyConstrainedStarMatter))

        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(11)
            input(plate, MagnetohydrodynamicallyConstrainedStarMatter, 2)
            input(screw, MagnetohydrodynamicallyConstrainedStarMatter)
            outputs(GTLiteBlocks.METAL_WALLS[MagnetohydrodynamicallyConstrainedStarMatter]!!.getItem(MagnetohydrodynamicallyConstrainedStarMatter).copy(3))
            EUt(7)
            duration(2 * SECOND + 5 * TICK)
        }
    }

    // @formatter:on

}