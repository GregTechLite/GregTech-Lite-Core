package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VH
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.loaders.recipe.CraftingComponent
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.variant.component.FieldGenCasing
import net.minecraft.item.ItemStack

internal object FieldGenCasingRecipes
{

    // @formatter:off

    fun init()
    {
        val fieldGenCasings = arrayOf(FieldGenCasing.LV.stack, FieldGenCasing.MV.stack, FieldGenCasing.HV.stack,
                                      FieldGenCasing.EV.stack, FieldGenCasing.IV.stack, FieldGenCasing.LuV.stack, FieldGenCasing.ZPM.stack,
                                      FieldGenCasing.UV.stack, FieldGenCasing.UHV.stack, FieldGenCasing.UEV.stack, FieldGenCasing.UIV.stack,
                                      FieldGenCasing.UXV.stack, FieldGenCasing.OpV.stack, FieldGenCasing.MAX.stack)

        for (voltage in fieldGenCasings.indices)
        {
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(wireGtSingle, Steel, 2)
                .inputs(CraftingComponent.FIELD_GENERATOR.getIngredient(voltage + 1) as ItemStack)
                .outputs(fieldGenCasings[voltage])
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }
    }

    // @formatter:on

}