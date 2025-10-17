package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.common.items.MetaItems.PLANT_BALL
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LOOM_RECIPES
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object LoomRecipes
{

    // @formatter:off

    fun init()
    {
        // 4x string -> 1x web
        LOOM_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .inputs(ItemStack(Items.STRING, 4))
            .outputs(ItemStack(Blocks.WEB))
            .EUt(V[ULV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // 4x string -> 1x wool
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            ItemStack(Items.STRING, 4),
            IntCircuitIngredient.getIntegratedCircuit(4))

        LOOM_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .inputs(ItemStack(Items.STRING, 4))
            .outputs(ItemStack(Blocks.WOOL))
            .EUt(VA[LV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // 8x string -> 3x carpet
        LOOM_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .inputs(ItemStack(Items.STRING, 8))
            .outputs(ItemStack(Blocks.CARPET, 3))
            .EUt(VA[LV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // Leather armors.
        LOOM_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .inputs(ItemStack(Items.LEATHER, 5))
            .outputs(ItemStack(Items.LEATHER_HELMET))
            .EUt(VA[ULV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        LOOM_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .inputs(ItemStack(Items.LEATHER, 8))
            .outputs(ItemStack(Items.LEATHER_CHESTPLATE))
            .EUt(VA[ULV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        LOOM_RECIPES.recipeBuilder()
            .circuitMeta(7)
            .inputs(ItemStack(Items.LEATHER, 7))
            .outputs(ItemStack(Items.LEATHER_LEGGINGS))
            .EUt(VA[ULV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        LOOM_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .inputs(ItemStack(Items.LEATHER, 4))
            .outputs(ItemStack(Items.LEATHER_BOOTS))
            .EUt(VA[ULV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // 1x plant ball -> 2x grass
        LOOM_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(PLANT_BALL, 1)
            .outputs(ItemStack(Blocks.TALLGRASS, 1, 1))
            .EUt(VA[ULV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // 1x plant ball -> 2x tall grass
        LOOM_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(PLANT_BALL)
            .outputs(ItemStack(Blocks.TALLGRASS, 1, 2))
            .EUt(VA[ULV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // 2x plant ball -> 1x vine
        LOOM_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(PLANT_BALL, 2)
            .outputs(ItemStack(Blocks.VINE))
            .EUt(VA[ULV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // 4x plant ball -> 1x waterlily
        LOOM_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(PLANT_BALL, 4)
            .outputs(ItemStack(Blocks.WATERLILY))
            .EUt(VA[ULV])
            .duration(2 * SECOND)
            .buildAndRegister()
    }

    // @formatter:on

}