package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.PACKER_RECIPES
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SAND_DUST
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

internal object PackerRecipes
{

    // @formatter:off

    fun init()
    {
        // Sand block -> 4x Sand dust.
        PACKER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .inputs(ItemStack(Blocks.SAND))
            .output(SAND_DUST, 4)
            .EUt(VH[ULV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // 4x Sand dust -> Sand block.
        PACKER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(SAND_DUST, 4)
            .outputs(ItemStack(Blocks.SAND))
            .EUt(VH[ULV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()
    }

    // @formatter:on

}