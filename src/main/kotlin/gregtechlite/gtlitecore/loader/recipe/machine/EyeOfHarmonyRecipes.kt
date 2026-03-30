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
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.inputs
import gregtechlite.gtlitecore.api.extension.outputs
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.DIMENSION_DISPLAY_END
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.DIMENSION_DISPLAY_NETHER
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.DIMENSION_DISPLAY_OVERWORLD
import net.minecraft.init.Blocks.END_STONE
import net.minecraft.init.Blocks.GRASS
import net.minecraft.init.Blocks.MAGMA
import net.minecraft.init.Blocks.NETHERRACK
import net.minecraft.init.Blocks.OBSIDIAN
import net.minecraft.init.Blocks.PURPUR_BLOCK
import net.minecraft.init.Blocks.SAPLING
import net.minecraft.init.Blocks.SOUL_SAND
import net.minecraft.init.Blocks.STONE

internal object EyeOfHarmonyRecipes
{

    // @formatter:off

    fun init()
    {
        // Dimension Display recipes.

        // Overworld
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(17)
            input(block, Bedrockium)
            inputs(GRASS, 64)
            inputs(STONE, 64)
            inputs(SAPLING, 16)
            fluidInputs(Water.getFluid(16000))
            outputs(DIMENSION_DISPLAY_OVERWORLD)
            EUt(VA[LV])
            duration(10 * SECOND)
        }

        // Nether
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(17)
            input(block, Bedrockium)
            inputs(NETHERRACK, 64)
            inputs(SOUL_SAND, 64)
            inputs(MAGMA, 16)
            fluidInputs(Lava.getFluid(16000))
            outputs(DIMENSION_DISPLAY_NETHER)
            EUt(VA[LV])
            duration(10 * SECOND)
        }

        // End
        ASSEMBLER_RECIPES.addRecipe {
            circuitMeta(17)
            input(block, Bedrockium)
            inputs(END_STONE, 64)
            inputs(PURPUR_BLOCK, 64)
            inputs(OBSIDIAN, 16)
            fluidInputs(EnderPearl.getFluid(16000))
            outputs(DIMENSION_DISPLAY_END)
            EUt(VA[LV])
            duration(10 * SECOND)
        }

        // TODO: EoH recipes.
    }

    // @formatter:on

}