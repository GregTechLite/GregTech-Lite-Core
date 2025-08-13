package gregtechlite.gtlitecore.api.recipe.backend

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.V
import gregtech.api.recipes.RecipeMaps
import gregtech.api.unification.material.Materials
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ADVANCED_FUSION_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import net.minecraftforge.fluids.FluidStack
import kotlin.math.max

object AdvancedFusionRecipeBackend
{

    private var COOLANTS = mutableMapOf<FluidStack, FluidStack>().apply {
        put(Materials.Steam.getFluid(570), GTLiteMaterials.SupercriticalSteam.getFluid(570))
        put(GTLiteMaterials.SodiumPotassiumEutatic.getFluid(120), GTLiteMaterials.SupercriticalSodiumPotassiumEutatic.getFluid(120))
        put(GTLiteMaterials.LeadBismuthEutatic.getFluid(60), GTLiteMaterials.SupercriticalLeadBismuthEutatic.getFluid(60))
        put(GTLiteMaterials.LithiumBerylliumFluorides.getFluid(55), GTLiteMaterials.SupercriticalLithiumBerylliumFluorides.getFluid(55))
        put(GTLiteMaterials.LithiumSodiumPotassiumFluorides.getFluid(50), GTLiteMaterials.SupercriticalLithiumSodiumPotassiumFluorides.getFluid(50))
    }

    fun init()
    {
        RecipeMaps.FUSION_RECIPES.onRecipeBuild(GTLiteMod.id("adv_fusion_moderator")) { recipeBuilder ->
            // (euToStart + EUt * duration) * euReturn / 100
            val euStart = recipeBuilder.euToStart + recipeBuilder.eUt * recipeBuilder.duration
            val tier = when (recipeBuilder.euToStart)
            {
                in 0L .. 160_000_000L -> 1 // MK1
                in 160_000_001L .. 320_000_000L -> 2 // MK2
                in 320_000_001L .. 640_000_000L -> 3 // MK3
                in 640_000_001L .. 1_280_000_000L -> 4 // MK4
                in 1_280_000_001L .. 2_560_000_000L -> 5 // MK5
                else -> 0 // Error
            }

            COOLANTS.forEach { (inputFluid, outputFluid) ->
                val amount = max((euStart / (V[EV] * 10000)) * inputFluid.amount, 1).toInt()
                ADVANCED_FUSION_RECIPES.recipeBuilder()
                    .inputs(*recipeBuilder.inputs.toTypedArray())
                    .fluidInputs(recipeBuilder.fluidInputs)
                    .fluidInputs(FluidStack(inputFluid, amount))
                    .outputs(recipeBuilder.outputs)
                    .chancedOutputs(recipeBuilder.chancedOutputs)
                    .fluidOutputs(recipeBuilder.fluidOutputs)
                    .fluidOutputs(FluidStack(outputFluid, amount))
                    .chancedFluidOutputs(recipeBuilder.chancedFluidOutputs)
                    .cleanroom(recipeBuilder.cleanroom)
                    .duration(recipeBuilder.duration)
                    .EUt(recipeBuilder.eUt)
                    .tier(tier)
                    .buildAndRegister()
            }
        }

    }

}