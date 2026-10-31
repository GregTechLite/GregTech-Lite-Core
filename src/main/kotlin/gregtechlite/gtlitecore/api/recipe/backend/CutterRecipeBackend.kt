package gregtechlite.gtlitecore.api.recipe.backend

import gregtech.api.GTValues.MODID
import gregtech.api.recipes.RecipeMaps
import gregtech.api.unification.material.Materials
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.extension.duration
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import net.minecraft.util.ResourceLocation
import kotlin.math.max
import kotlin.math.min

object CutterRecipeBackend
{
    fun init()
    {
        RecipeMaps.CUTTER_RECIPES.removeBuildAction(ResourceLocation(MODID, "cutter_fluid"))
        RecipeMaps.CUTTER_RECIPES.onRecipeBuild(GTLiteMod.id("cutter_fluid"))
        {
            if (it.fluidInputs.isEmpty())
            {
                val eut = it.eUt
                val duration = it.duration
                it.copy()
                    .fluidInputs(Materials.Water.getFluid(max(4, min(1000, duration * eut / 320)).toInt()))
                    .duration(duration * 2)
                    .buildAndRegister()

                it.copy()
                    .fluidInputs(Materials.DistilledWater.getFluid(max(3, min(750, duration * eut / 426)).toInt()))
                    .duration(duration * 1.5)
                    .buildAndRegister()

                it.copy()
                    .fluidInputs(Materials.Lubricant.getFluid(max(1, min(250, duration * eut / 1280)).toInt()))
                    .duration(max(1, duration))
                    .buildAndRegister()

                it.fluidInputs(GTLiteMaterials.DimensionallyShiftedSuperfluid.getFluid(max(1, min(20, duration * eut / 2560)).toInt()))
                    .duration(max(1, duration / 2))
            }
        }
    }
}