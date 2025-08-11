package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.loaders.recipe.CraftingComponent
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.variant.component.EmitterCasing
import net.minecraft.item.ItemStack

internal object EmitterCasingRecipes
{

    // @formatter:off

    fun init()
    {
        val emitterCasings = arrayOf(EmitterCasing.LV.stack, EmitterCasing.MV.stack, EmitterCasing.HV.stack,
                                     EmitterCasing.EV.stack, EmitterCasing.IV.stack, EmitterCasing.LuV.stack, EmitterCasing.ZPM.stack,
                                     EmitterCasing.UV.stack, EmitterCasing.UHV.stack, EmitterCasing.UEV.stack, EmitterCasing.UIV.stack,
                                     EmitterCasing.UXV.stack, EmitterCasing.OpV.stack, EmitterCasing.MAX.stack)

        for (voltage in emitterCasings.indices)
        {
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(frameGt, Steel)
                .input(plate, Steel, 4)
                .input(foil, Steel, 2)
                .inputs(CraftingComponent.EMITTER.getIngredient(voltage + 1) as ItemStack)
                .outputs(emitterCasings[voltage])
                .EUt(VA[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }
    }

    // @formatter:on

}
