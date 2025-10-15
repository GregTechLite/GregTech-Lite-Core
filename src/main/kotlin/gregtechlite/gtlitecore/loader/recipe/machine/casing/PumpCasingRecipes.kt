package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.loaders.recipe.CraftingComponent
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.variant.component.PumpCasing
import net.minecraft.item.ItemStack

internal object PumpCasingRecipes
{

    // @formatter:off

    fun init()
    {
        val pumpCasings = arrayOf(PumpCasing.LV.stack, PumpCasing.MV.stack, PumpCasing.HV.stack,
                                  PumpCasing.EV.stack, PumpCasing.IV.stack, PumpCasing.LuV.stack, PumpCasing.ZPM.stack,
                                  PumpCasing.UV.stack, PumpCasing.UHV.stack, PumpCasing.UEV.stack, PumpCasing.UIV.stack,
                                  PumpCasing.UXV.stack, PumpCasing.OpV.stack, PumpCasing.MAX.stack)

        for (voltage in pumpCasings.indices)
        {
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(rotor, Steel, 2)
                .inputs(CraftingComponent.PUMP.getIngredient(voltage + 1) as ItemStack)
                .outputs(pumpCasings[voltage])
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }
    }

    // @formatter:on

}