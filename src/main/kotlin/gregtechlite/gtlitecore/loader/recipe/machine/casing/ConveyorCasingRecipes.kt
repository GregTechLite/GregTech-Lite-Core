package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.loaders.recipe.CraftingComponent
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.variant.component.ConveyorCasing
import net.minecraft.item.ItemStack

internal object ConveyorCasingRecipes
{

    // @formatter:off

    fun init()
    {
        val conveyorCasings = arrayOf(ConveyorCasing.LV.stack, ConveyorCasing.MV.stack,
                                      ConveyorCasing.HV.stack, ConveyorCasing.EV.stack, ConveyorCasing.IV.stack,
                                      ConveyorCasing.LuV.stack, ConveyorCasing.ZPM.stack, ConveyorCasing.UV.stack,
                                      ConveyorCasing.UHV.stack, ConveyorCasing.UEV.stack, ConveyorCasing.UIV.stack,
                                      ConveyorCasing.UXV.stack, ConveyorCasing.OpV.stack, ConveyorCasing.MAX.stack)

        for (voltage in conveyorCasings.indices)
        {
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(round, Steel, 2)
                .inputs(CraftingComponent.CONVEYOR.getIngredient(voltage + 1) as ItemStack)
                .outputs(conveyorCasings[voltage])
                .EUt(VA[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }
    }

    // @formatter:on

}