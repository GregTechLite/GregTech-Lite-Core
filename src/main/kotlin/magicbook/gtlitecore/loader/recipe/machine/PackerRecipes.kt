package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.PACKER_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SAND_DUST
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class PackerRecipes
{

    companion object
    {

        fun init()
        {
            // Sand block -> 4x Sand dust.
            PACKER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .inputs(ItemStack(Blocks.SAND))
                .output(SAND_DUST, 4)
                .EUt(VH[ULV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // 4x Sand dust -> Sand block.
            PACKER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(SAND_DUST, 4)
                .outputs(ItemStack(Blocks.SAND))
                .EUt(VH[ULV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

    }

}