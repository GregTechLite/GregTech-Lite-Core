package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Polytetrafluoroethylene
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.pipeNormalFluid
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.variant.BoilerCasing

internal object BoilerCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Bronze pipe casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(plate, Bronze, 4)
            .input(pipeNormalFluid, Bronze, 4)
            .input(frameGt, Bronze)
            .outputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.BRONZE_PIPE, ConfigHolder.recipes.casingsPerCraft))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Steel pipe casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(plate, Steel, 4)
            .input(pipeNormalFluid, Steel, 4)
            .input(frameGt, Steel)
            .outputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE, ConfigHolder.recipes.casingsPerCraft))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Titanium pipe casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(plate, Titanium, 4)
            .input(pipeNormalFluid, Titanium, 4)
            .input(frameGt, Titanium)
            .outputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE, ConfigHolder.recipes.casingsPerCraft))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Tungsten Steel pipe casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(plate, TungstenSteel, 4)
            .input(pipeNormalFluid, TungstenSteel, 4)
            .input(frameGt, TungstenSteel)
            .outputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE, ConfigHolder.recipes.casingsPerCraft))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Polytetrafluoroethylene pipe casing
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(plate, Polytetrafluoroethylene, 4)
            .input(pipeNormalFluid, Polytetrafluoroethylene, 4)
            .input(frameGt, Polytetrafluoroethylene)
            .outputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE, ConfigHolder.recipes.casingsPerCraft))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Polybenzimidazole Pipe Casing
        ModHandler.addShapedRecipe(true, "polybenzimidazole_pipe_casing", BoilerCasing.POLYBENZIMIDAZOLE.getStack(ConfigHolder.recipes.casingsPerCraft),
            "PQP", "QFQ", "PQP",
            'P', UnificationEntry(plate, Polybenzimidazole),
            'Q', UnificationEntry(pipeNormalFluid, Polybenzimidazole),
            'F', UnificationEntry(frameGt, Polybenzimidazole))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(plate, Polybenzimidazole, 4)
            .input(pipeNormalFluid, Polybenzimidazole, 4)
            .input(frameGt, Polybenzimidazole)
            .outputs(BoilerCasing.POLYBENZIMIDAZOLE.getStack(ConfigHolder.recipes.casingsPerCraft))
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}