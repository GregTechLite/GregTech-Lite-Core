package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.Invar
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.Vanadium
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.stack.UnificationEntry
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AmorphousBoronNitride
import gregtechlite.gtlitecore.common.block.variant.Crucible

internal object CrucibleRecipes
{

    // @formatter:off

    fun init()
    {
        // Bronze Crucible
        ModHandler.addShapedRecipe(true, "bronze_crucible", Crucible.BRONZE.stack,
            "P P", "PhP", "PPP",
            'P', UnificationEntry(plate, Bronze))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(plate, Bronze, 7)
            .outputs(Crucible.BRONZE.stack)
            .EUt(4) // ULV
            .duration(35 * SECOND)
            .buildAndRegister()

        // Invar Crucible
        ModHandler.addShapedRecipe(true, "invar_crucible", Crucible.INVAR.stack,
            "P P", "PhP", "PPP",
            'P', UnificationEntry(plate, Invar))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(plate, Invar, 7)
            .outputs(Crucible.INVAR.stack)
            .EUt(4) // ULV
            .duration(35 * SECOND)
            .buildAndRegister()

        // Quartzite Crucible
        ModHandler.addShapedRecipe(true, "quartzite_crucible", Crucible.QUARTZ.stack,
            "P P", "PhP", "PPP",
            'P', UnificationEntry(plate, Quartzite))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(plate, Quartzite, 7)
            .outputs(Crucible.QUARTZ.stack)
            .EUt(4) // ULV
            .duration(35 * SECOND)
            .buildAndRegister()

        // Chrome Crucible
        ModHandler.addShapedRecipe(true, "chrome_crucible", Crucible.CHROME.stack,
            "P P", "PhP", "PPP",
            'P', UnificationEntry(plate, Chrome))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(plate, Chrome, 7)
            .outputs(Crucible.CHROME.stack)
            .EUt(4) // ULV
            .duration(35 * SECOND)
            .buildAndRegister()

        // Vanadium Crucible
        ModHandler.addShapedRecipe(true, "vanadium_crucible", Crucible.VANADIUM.stack,
            "P P", "PhP", "PPP",
            'P', UnificationEntry(plate, Vanadium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(plate, Vanadium, 7)
            .outputs(Crucible.VANADIUM.stack)
            .EUt(4) // ULV
            .duration(35 * SECOND)
            .buildAndRegister()

        // Niobium Titanium Crucible
        ModHandler.addShapedRecipe(true, "niobium_titanium_crucible", Crucible.NIOBIUM_TITANIUM.stack,
            "P P", "PhP", "PPP",
            'P', UnificationEntry(plate, NiobiumTitanium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(plate, NiobiumTitanium, 7)
            .outputs(Crucible.NIOBIUM_TITANIUM.stack)
            .EUt(4) // ULV
            .duration(35 * SECOND)
            .buildAndRegister()

        // Iridium Crucible
        ModHandler.addShapedRecipe(true, "iridium_crucible", Crucible.IRIDIUM.stack,
            "P P", "PhP", "PPP",
            'P', UnificationEntry(plate, Iridium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(plate, Iridium, 7)
            .outputs(Crucible.IRIDIUM.stack)
            .EUt(4) // ULV
            .duration(35 * SECOND)
            .buildAndRegister()

        // Molybdenum Crucible
        ModHandler.addShapedRecipe(true, "molybdenum_crucible", Crucible.MOLYBDENUM.stack,
            "P P", "PhP", "PPP",
            'P', UnificationEntry(plate, Molybdenum))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(plate, Molybdenum, 7)
            .outputs(Crucible.MOLYBDENUM.stack)
            .EUt(4) // ULV
            .duration(35 * SECOND)
            .buildAndRegister()

        // Tungsten Crucible
        ModHandler.addShapedRecipe(true, "tungsten_crucible", Crucible.TUNGSTEN.stack,
            "P P", "PhP", "PPP",
            'P', UnificationEntry(plate, Tungsten))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(plate, Tungsten, 7)
            .outputs(Crucible.TUNGSTEN.stack)
            .EUt(4) // ULV
            .duration(35 * SECOND)
            .buildAndRegister()

        // Osmium Crucible
        ModHandler.addShapedRecipe(true, "osmium_crucible", Crucible.OSMIUM.stack,
            "P P", "PhP", "PPP",
            'P', UnificationEntry(plate, Osmium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(plate, Osmium, 7)
            .outputs(Crucible.OSMIUM.stack)
            .EUt(4) // ULV
            .duration(35 * SECOND)
            .buildAndRegister()

        // Graphite Crucible
        ModHandler.addShapedRecipe(true, "graphite_crucible", Crucible.GRAPHITE.stack,
            "P P", "PhP", "PPP",
            'P', UnificationEntry(plate, Graphene))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(plate, Graphene, 7)
            .outputs(Crucible.GRAPHITE.stack)
            .EUt(4) // ULV
            .duration(35 * SECOND)
            .buildAndRegister()

        // Boron Nitride Crucible
        ModHandler.addShapedRecipe(true, "boron_nitride_crucible", Crucible.BORON_NITRIDE.stack,
            "P P", "PhP", "PPP",
            'P', UnificationEntry(plate, AmorphousBoronNitride))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .input(plate, AmorphousBoronNitride, 7)
            .outputs(Crucible.BORON_NITRIDE.stack)
            .EUt(4) // ULV
            .duration(35 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}