package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.common.items.MetaItems.PLANT_BALL
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.getStack
import gregtechlite.gtlitecore.api.extension.inputs
import gregtechlite.gtlitecore.api.extension.outputs
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LOOM_RECIPES
import net.minecraft.init.Blocks.CARPET
import net.minecraft.init.Blocks.TALLGRASS
import net.minecraft.init.Blocks.VINE
import net.minecraft.init.Blocks.WATERLILY
import net.minecraft.init.Blocks.WEB
import net.minecraft.init.Blocks.WOOL
import net.minecraft.init.Items.LEATHER
import net.minecraft.init.Items.LEATHER_BOOTS
import net.minecraft.init.Items.LEATHER_CHESTPLATE
import net.minecraft.init.Items.LEATHER_HELMET
import net.minecraft.init.Items.LEATHER_LEGGINGS
import net.minecraft.init.Items.STRING

internal object LoomRecipes
{

    // @formatter:off

    fun init()
    {
        // 4x string -> 1x web
        LOOM_RECIPES.addRecipe {
            circuitMeta(3)
            inputs(STRING, 4)
            outputs(WEB)
            EUt(V[ULV])
            duration(1 * SECOND)
        }

        // 4x string -> 1x wool
        ASSEMBLER_RECIPES.removeRecipe(
            STRING.getStack(4),
            IntCircuitIngredient.getIntegratedCircuit(4))

        LOOM_RECIPES.addRecipe {
            circuitMeta(4)
            inputs(STRING, 4)
            outputs(WOOL)
            EUt(VA[LV])
            duration(2 * SECOND)
        }

        // 8x string -> 3x carpet
        LOOM_RECIPES.addRecipe {
            circuitMeta(8)
            inputs(STRING, 8)
            outputs(CARPET, 3)
            EUt(VA[LV])
            duration(2 * SECOND)
        }

        // Leather armors.
        LOOM_RECIPES.addRecipe {
            circuitMeta(5)
            inputs(LEATHER, 5)
            outputs(LEATHER_HELMET)
            EUt(VA[ULV])
            duration(2 * SECOND + 10 * TICK)
        }

        LOOM_RECIPES.addRecipe {
            circuitMeta(8)
            inputs(LEATHER, 8)
            outputs(LEATHER_CHESTPLATE)
            EUt(VA[ULV])
            duration(2 * SECOND + 10 * TICK)
        }

        LOOM_RECIPES.addRecipe {
            circuitMeta(7)
            inputs(LEATHER, 7)
            outputs(LEATHER_LEGGINGS)
            EUt(VA[ULV])
            duration(2 * SECOND + 10 * TICK)
        }

        LOOM_RECIPES.addRecipe {
            circuitMeta(4)
            inputs(LEATHER, 4)
            outputs(LEATHER_BOOTS)
            EUt(VA[ULV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 1x plant ball -> 2x grass
        LOOM_RECIPES.addRecipe {
            circuitMeta(1)
            input(PLANT_BALL)
            outputs(TALLGRASS, 1, 1)
            EUt(VA[ULV])
            duration(2 * SECOND)
        }

        // 1x plant ball -> 2x tall grass
        LOOM_RECIPES.addRecipe {
            circuitMeta(2)
            input(PLANT_BALL)
            outputs(TALLGRASS, 1, 2)
            EUt(VA[ULV])
            duration(2 * SECOND)
        }

        // 2x plant ball -> 1x vine
        LOOM_RECIPES.addRecipe {
            circuitMeta(3)
            input(PLANT_BALL, 2)
            outputs(VINE)
            EUt(VA[ULV])
            duration(2 * SECOND)
        }

        // 4x plant ball -> 1x waterlily
        LOOM_RECIPES.addRecipe {
            circuitMeta(4)
            input(PLANT_BALL, 4)
            outputs(WATERLILY)
            EUt(VA[ULV])
            duration(2 * SECOND)
        }
    }

    // @formatter:on

}