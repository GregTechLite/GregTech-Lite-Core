package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.loaders.recipe.CraftingComponent
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.variant.component.RobotArmCasing
import net.minecraft.item.ItemStack

internal object RobotArmCasingRecipes
{

    // @formatter:off

    fun init()
    {
        val robotArmCasings = arrayOf(RobotArmCasing.LV.stack, RobotArmCasing.MV.stack,
                                      RobotArmCasing.HV.stack, RobotArmCasing.EV.stack, RobotArmCasing.IV.stack,
                                      RobotArmCasing.LuV.stack, RobotArmCasing.ZPM.stack, RobotArmCasing.UV.stack,
                                      RobotArmCasing.UHV.stack, RobotArmCasing.UEV.stack, RobotArmCasing.UIV.stack,
                                      RobotArmCasing.UXV.stack, RobotArmCasing.OpV.stack, RobotArmCasing.MAX.stack)

        for (voltage in robotArmCasings.indices)
        {
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(gear, Steel, 2)
                .inputs(CraftingComponent.ROBOT_ARM.getIngredient(voltage + 1) as ItemStack)
                .outputs(robotArmCasings[voltage])
                .EUt(VA[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }
    }

    // @formatter:on

}