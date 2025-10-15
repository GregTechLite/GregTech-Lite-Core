package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Berkelium
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Curium
import gregtech.api.unification.material.Materials.Einsteinium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.Inconel718
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Nichrome
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.Protactinium
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Uranium
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.stack.UnificationEntry
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZirconiumCarbide
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.fuelRodHighDensity
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.NuclearReactorCore

internal object NuclearCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Nuclear Temperature Controller
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .inputs(MetalCasing.INCONEL_718.stack)
            .inputs(GTBoilerCasing.POLYTETRAFLUOROETHYLENE_PIPE.stack)
            .input(wireFine, Nichrome, 4)
            .input(screw, Inconel718, 2)
            .fluidInputs(ZirconiumCarbide.getFluid(L * 4))
            .outputs(ActiveUniqueCasing.TEMPERATURE_CONTROLLER.stack)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Thorium Nuclear Reactor Core
        ModHandler.addShapedRecipe(true, "nuclear_reactor_core.thorium", NuclearReactorCore.THORIUM.stack,
            "PRP", "SDS", "PRP",
            'R', UnificationEntry(fuelRodHighDensity, Graphite),
            'P', UnificationEntry(plateDense, StainlessSteel),
            'S', UnificationEntry(stickLong, StainlessSteel),
            'D', UnificationEntry(plateDense, Thorium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(22)
            .input(fuelRodHighDensity, Graphite, 2)
            .input(plateDense, StainlessSteel, 4)
            .input(plateDense, Thorium)
            .input(stickLong, StainlessSteel, 2)
            .outputs(NuclearReactorCore.THORIUM.stack)
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Protactinium Nuclear Reactor Core
        ModHandler.addShapedRecipe(true, "nuclear_reactor_core.protactinium", NuclearReactorCore.PROTACTINIUM.stack,
            "PRP", "SDS", "PRP",
            'R', UnificationEntry(fuelRodHighDensity, Graphite),
            'P', UnificationEntry(plateDense, StainlessSteel),
            'S', UnificationEntry(stickLong, StainlessSteel),
            'D', UnificationEntry(plateDense, Protactinium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(22)
            .input(fuelRodHighDensity, Graphite, 2)
            .input(plateDense, StainlessSteel, 4)
            .input(plateDense, Protactinium)
            .input(stickLong, StainlessSteel, 2)
            .outputs(NuclearReactorCore.PROTACTINIUM.stack)
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Uranium Nuclear Reactor Core
        ModHandler.addShapedRecipe(true, "nuclear_reactor_core.uranium", NuclearReactorCore.URANIUM.stack,
            "PRP", "SDS", "PRP",
            'R', UnificationEntry(fuelRodHighDensity, Graphite),
            'P', UnificationEntry(plateDense, StainlessSteel),
            'S', UnificationEntry(stickLong, StainlessSteel),
            'D', UnificationEntry(plateDense, Uranium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(22)
            .input(fuelRodHighDensity, Graphite, 2)
            .input(plateDense, StainlessSteel, 4)
            .input(plateDense, Uranium)
            .input(stickLong, StainlessSteel, 2)
            .outputs(NuclearReactorCore.URANIUM.stack)
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Neptunium Nuclear Reactor Core
        ModHandler.addShapedRecipe(true, "nuclear_reactor_core.neptunium", NuclearReactorCore.NEPTUNIUM.stack,
            "PRP", "SDS", "PRP",
            'R', UnificationEntry(fuelRodHighDensity, Graphite),
            'P', UnificationEntry(plateDense, StainlessSteel),
            'S', UnificationEntry(stickLong, StainlessSteel),
            'D', UnificationEntry(plateDense, Neptunium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(22)
            .input(fuelRodHighDensity, Graphite, 2)
            .input(plateDense, StainlessSteel, 4)
            .input(plateDense, Neptunium)
            .input(stickLong, StainlessSteel, 2)
            .outputs(NuclearReactorCore.NEPTUNIUM.stack)
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Plutonium Nuclear Reactor Core
        ModHandler.addShapedRecipe(true, "nuclear_reactor_core.plutonium", NuclearReactorCore.PLUTONIUM.stack,
            "PRP", "SDS", "PRP",
            'R', UnificationEntry(fuelRodHighDensity, Graphite),
            'P', UnificationEntry(plateDense, StainlessSteel),
            'S', UnificationEntry(stickLong, StainlessSteel),
            'D', UnificationEntry(plateDense, Plutonium239))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(22)
            .input(fuelRodHighDensity, Graphite, 2)
            .input(plateDense, StainlessSteel, 4)
            .input(plateDense, Plutonium239)
            .input(stickLong, StainlessSteel, 2)
            .outputs(NuclearReactorCore.PLUTONIUM.stack)
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Americium Nuclear Reactor Core
        ModHandler.addShapedRecipe(true, "nuclear_reactor_core.americium", NuclearReactorCore.AMERICIUM.stack,
            "PRP", "SDS", "PRP",
            'R', UnificationEntry(fuelRodHighDensity, Graphite),
            'P', UnificationEntry(plateDense, StainlessSteel),
            'S', UnificationEntry(stickLong, StainlessSteel),
            'D', UnificationEntry(plateDense, Americium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(22)
            .input(fuelRodHighDensity, Graphite, 2)
            .input(plateDense, StainlessSteel, 4)
            .input(plateDense, Americium)
            .input(stickLong, StainlessSteel, 2)
            .outputs(NuclearReactorCore.AMERICIUM.stack)
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Curium Nuclear Reactor Core
        ModHandler.addShapedRecipe(true, "nuclear_reactor_core.curium", NuclearReactorCore.CURIUM.stack,
            "PRP", "SDS", "PRP",
            'R', UnificationEntry(fuelRodHighDensity, Graphite),
            'P', UnificationEntry(plateDense, StainlessSteel),
            'S', UnificationEntry(stickLong, StainlessSteel),
            'D', UnificationEntry(plateDense, Curium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(22)
            .input(fuelRodHighDensity, Graphite, 2)
            .input(plateDense, StainlessSteel, 4)
            .input(plateDense, Curium)
            .input(stickLong, StainlessSteel, 2)
            .outputs(NuclearReactorCore.CURIUM.stack)
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Berkelium Nuclear Reactor Core
        ModHandler.addShapedRecipe(true, "nuclear_reactor_core.berkelium", NuclearReactorCore.BERKELIUM.stack,
            "PRP", "SDS", "PRP",
            'R', UnificationEntry(fuelRodHighDensity, Graphite),
            'P', UnificationEntry(plateDense, StainlessSteel),
            'S', UnificationEntry(stickLong, StainlessSteel),
            'D', UnificationEntry(plateDense, Berkelium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(22)
            .input(fuelRodHighDensity, Graphite, 2)
            .input(plateDense, StainlessSteel, 4)
            .input(plateDense, Berkelium)
            .input(stickLong, StainlessSteel, 2)
            .outputs(NuclearReactorCore.BERKELIUM.stack)
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Californium Nuclear Reactor Core
        ModHandler.addShapedRecipe(true, "nuclear_reactor_core.californium", NuclearReactorCore.CALIFORNIUM.stack,
            "PRP", "SDS", "PRP",
            'R', UnificationEntry(fuelRodHighDensity, Graphite),
            'P', UnificationEntry(plateDense, StainlessSteel),
            'S', UnificationEntry(stickLong, StainlessSteel),
            'D', UnificationEntry(plateDense, Californium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(22)
            .input(fuelRodHighDensity, Graphite, 2)
            .input(plateDense, StainlessSteel, 4)
            .input(plateDense, Californium)
            .input(stickLong, StainlessSteel, 2)
            .outputs(NuclearReactorCore.CALIFORNIUM.stack)
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Einsteinium Nuclear Reactor Core
        ModHandler.addShapedRecipe(true, "nuclear_reactor_core.einsteinium", NuclearReactorCore.EINSTEINIUM.stack,
            "PRP", "SDS", "PRP",
            'R', UnificationEntry(fuelRodHighDensity, Graphite),
            'P', UnificationEntry(plateDense, StainlessSteel),
            'S', UnificationEntry(stickLong, StainlessSteel),
            'D', UnificationEntry(plateDense, Einsteinium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(22)
            .input(fuelRodHighDensity, Graphite, 2)
            .input(plateDense, StainlessSteel, 4)
            .input(plateDense, Einsteinium)
            .input(stickLong, StainlessSteel, 2)
            .outputs(NuclearReactorCore.EINSTEINIUM.stack)
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Fermium Nuclear Reactor Core
        ModHandler.addShapedRecipe(true, "nuclear_reactor_core.fermium", NuclearReactorCore.FERMIUM.stack,
            "PRP", "SDS", "PRP",
            'R', UnificationEntry(fuelRodHighDensity, Graphite),
            'P', UnificationEntry(plateDense, StainlessSteel),
            'S', UnificationEntry(stickLong, StainlessSteel),
            'D', UnificationEntry(plateDense, Fermium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(22)
            .input(fuelRodHighDensity, Graphite, 2)
            .input(plateDense, StainlessSteel, 4)
            .input(plateDense, Fermium)
            .input(stickLong, StainlessSteel, 2)
            .outputs(NuclearReactorCore.FERMIUM.stack)
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Mendelevium Nuclear Reactor Core
        ModHandler.addShapedRecipe(true, "nuclear_reactor_core.mendelevium", NuclearReactorCore.MENDELEVIUM.stack,
            "PRP", "SDS", "PRP",
            'R', UnificationEntry(fuelRodHighDensity, Graphite),
            'P', UnificationEntry(plateDense, StainlessSteel),
            'S', UnificationEntry(stickLong, StainlessSteel),
            'D', UnificationEntry(plateDense, Mendelevium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(22)
            .input(fuelRodHighDensity, Graphite, 2)
            .input(plateDense, StainlessSteel, 4)
            .input(plateDense, Mendelevium)
            .input(stickLong, StainlessSteel, 2)
            .outputs(NuclearReactorCore.MENDELEVIUM.stack)
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    // @formatter:on

}