package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.EnderPearl
import gregtech.api.unification.material.Materials.Lava
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.block
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.DIMENSION_DISPLAY_END
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.DIMENSION_DISPLAY_NETHER
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.DIMENSION_DISPLAY_OVERWORLD
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

internal object EyeOfHarmonyRecipes
{

    // @formatter:off

    fun init()
    {
        // Dimension Display recipes.

        // Overworld
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(17)
            .input(block, Bedrockium)
            .inputs(ItemStack(Blocks.GRASS, 64))
            .inputs(ItemStack(Blocks.STONE, 64))
            .inputs(ItemStack(Blocks.SAPLING, 16))
            .fluidInputs(Water.getFluid(16000))
            .outputs(ItemStack(DIMENSION_DISPLAY_OVERWORLD))
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Nether
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(17)
            .input(block, Bedrockium)
            .inputs(ItemStack(Blocks.NETHERRACK, 64))
            .inputs(ItemStack(Blocks.SOUL_SAND, 64))
            .inputs(ItemStack(Blocks.MAGMA, 16))
            .fluidInputs(Lava.getFluid(16000))
            .outputs(ItemStack(DIMENSION_DISPLAY_NETHER))
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // End
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(17)
            .input(block, Bedrockium)
            .inputs(ItemStack(Blocks.END_STONE, 64))
            .inputs(ItemStack(Blocks.PURPUR_BLOCK, 64))
            .inputs(ItemStack(Blocks.OBSIDIAN, 16))
            .fluidInputs(EnderPearl.getFluid(16000))
            .outputs(ItemStack(DIMENSION_DISPLAY_END))
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // EoH recipes.

    }

    // @formatter:on

}