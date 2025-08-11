package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.loaders.recipe.CraftingComponent
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.variant.component.PistonCasing
import net.minecraft.item.ItemStack

internal object PistonCasingRecipes
{

    // @formatter:off

    fun init()
    {
        val pistonCasings = arrayOf(PistonCasing.LV.stack, PistonCasing.MV.stack, PistonCasing.HV.stack,
                                    PistonCasing.EV.stack, PistonCasing.IV.stack, PistonCasing.LuV.stack, PistonCasing.ZPM.stack,
                                    PistonCasing.UV.stack, PistonCasing.UHV.stack, PistonCasing.UEV.stack, PistonCasing.UIV.stack,
                                    PistonCasing.UXV.stack, PistonCasing.OpV.stack, PistonCasing.MAX.stack)

        for (voltage in pistonCasings.indices)
        {
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(gearSmall, Steel, 2)
                .inputs(CraftingComponent.PISTON.getIngredient(voltage + 1) as ItemStack)
                .outputs(pistonCasings[voltage])
                .EUt(VA[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }
    }

    // @formatter:on

}