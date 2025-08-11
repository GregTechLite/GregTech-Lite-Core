package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.loaders.recipe.CraftingComponent
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.variant.component.MotorCasing
import net.minecraft.item.ItemStack

internal object MotorCasingRecipes
{

    // @formatter:off

    fun init()
    {
        val motorCasings = arrayOf(MotorCasing.LV.stack, MotorCasing.MV.stack, MotorCasing.HV.stack,
                                   MotorCasing.EV.stack, MotorCasing.IV.stack, MotorCasing.LuV.stack, MotorCasing.ZPM.stack,
                                   MotorCasing.UV.stack, MotorCasing.UHV.stack, MotorCasing.UEV.stack, MotorCasing.UIV.stack,
                                   MotorCasing.UXV.stack, MotorCasing.OpV.stack, MotorCasing.MAX.stack)

        for (voltage in motorCasings.indices)
        {
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(ring, Steel, 2)
                .inputs(CraftingComponent.MOTOR.getIngredient(voltage + 1) as ItemStack)
                .outputs(motorCasings[voltage])
                .EUt(VA[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }
    }

    // @formatter:on

}