package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.loaders.recipe.CraftingComponent
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.variant.component.SensorCasing
import net.minecraft.item.ItemStack

internal object SensorCasingRecipes
{

    // @formatter:off

    fun init()
    {
        val sensorCasings = arrayOf(SensorCasing.LV.stack, SensorCasing.MV.stack, SensorCasing.HV.stack,
                                    SensorCasing.EV.stack, SensorCasing.IV.stack, SensorCasing.LuV.stack, SensorCasing.ZPM.stack,
                                    SensorCasing.UV.stack, SensorCasing.UHV.stack, SensorCasing.UEV.stack, SensorCasing.UIV.stack,
                                    SensorCasing.UXV.stack, SensorCasing.OpV.stack, SensorCasing.MAX.stack)

        for (voltage in sensorCasings.indices)
        {
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(wireFine, Steel, 2)
                .inputs(CraftingComponent.SENSOR.getIngredient(voltage + 1) as ItemStack)
                .outputs(sensorCasings[voltage])
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }
    }

    // @formatter:on

}