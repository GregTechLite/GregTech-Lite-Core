package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockTurbineCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.variant.TurbineCasing

internal object TurbineCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Rhodium Plated Palladium Turbine Casing
        ModHandler.addShapedRecipe(true, "rhodium_plated_palladium_turbine_casing", TurbineCasing.RHODIUM_PLATED_PALLADIUM_TURBINE.getStack(ConfigHolder.recipes.casingsPerCraft),
            "PhP", "PCP", "PwP",
            'C', MetaBlocks.TURBINE_CASING.getItemVariant(BlockTurbineCasing.TurbineCasingType.STEEL_TURBINE_CASING),
            'P', UnificationEntry(plate, RhodiumPlatedPalladium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .inputs(MetaBlocks.TURBINE_CASING.getItemVariant(BlockTurbineCasing.TurbineCasingType.STEEL_TURBINE_CASING))
            .input(plate, RhodiumPlatedPalladium, 6)
            .outputs(TurbineCasing.RHODIUM_PLATED_PALLADIUM_TURBINE.getStack(ConfigHolder.recipes.casingsPerCraft))
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Rhodium Plated Palladium Gearbox Casing
        ModHandler.addShapedRecipe(true, "rhodium_plated_palladium_gearbox_casing", TurbineCasing.RHODIUM_PLATED_PALLADIUM_GEARBOX.getStack(ConfigHolder.recipes.casingsPerCraft),
            "PhP", "GFG", "PwP",
            'P', UnificationEntry(plate, RhodiumPlatedPalladium),
            'G', UnificationEntry(gear, RhodiumPlatedPalladium),
            'F', UnificationEntry(frameGt, RhodiumPlatedPalladium))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(plate, RhodiumPlatedPalladium, 4)
            .input(gear, RhodiumPlatedPalladium, 2)
            .input(frameGt, RhodiumPlatedPalladium)
            .outputs(TurbineCasing.RHODIUM_PLATED_PALLADIUM_GEARBOX.getStack(ConfigHolder.recipes.casingsPerCraft))
            .EUt(VH[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    // @formatter:on

}