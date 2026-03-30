package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.PACKER_RECIPES
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.inputs
import gregtechlite.gtlitecore.api.extension.outputs
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SAND_DUST
import net.minecraft.init.Blocks.SAND

internal object PackerRecipes
{

    // @formatter:off

    fun init()
    {
        // Sand Block -> 4x Sand Dust
        PACKER_RECIPES.addRecipe {
            circuitMeta(4)
            inputs(SAND)
            output(SAND_DUST, 4)
            EUt(VH[ULV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 4x Sand Dust -> Sand Block
        PACKER_RECIPES.addRecipe {
            circuitMeta(4)
            input(SAND_DUST, 4)
            outputs(SAND)
            EUt(VH[ULV])
            duration(2 * SECOND + 10 * TICK)
        }
    }

    // @formatter:on

}