package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.V
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.recipes.properties.impl.FusionEUToStartProperty
import gregtech.api.unification.material.Materials.Steam
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ADVANCED_FUSION_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalLeadBismuthEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalLithiumBerylliumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalLithiumSodiumPotassiumFluorides
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalSodiumPotassiumEutatic
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SupercriticalSteam
import net.minecraftforge.fluids.FluidStack
import kotlin.math.max

object AdvancedFusionRecipeProducer
{

    // @formatter:off

    private var COOLANTS = mutableMapOf<FluidStack, FluidStack>()
        .apply {
            put(Steam.getFluid(570), SupercriticalSteam.getFluid(570))
            put(SodiumPotassiumEutatic.getFluid(120), SupercriticalSodiumPotassiumEutatic.getFluid(120))
            put(LeadBismuthEutatic.getFluid(60), SupercriticalLeadBismuthEutatic.getFluid(60))
            put(LithiumBerylliumFluorides.getFluid(55), SupercriticalLithiumBerylliumFluorides.getFluid(55))
            put(LithiumSodiumPotassiumFluorides.getFluid(50), SupercriticalLithiumSodiumPotassiumFluorides.getFluid(50))
        }

    fun produce()
    {

        FUSION_RECIPES.recipeList
            .filter { it.fluidInputs.size <= 2 && it.fluidOutputs.size <= 1 }
            .forEach { recipe ->
                val inputs = recipe.fluidInputs
                val outputs = recipe.fluidOutputs

                val eut = recipe.eUt
                val duration = recipe.duration
                val euToStart = checkNotNull(recipe.getProperty(FusionEUToStartProperty.getInstance(), 0))

                val euCost= euToStart + eut * duration

                val tier = when (euToStart)
                {
                    in 0L .. 160_000_000L -> 1 // MK1
                    in 160_000_001L .. 320_000_000L -> 2 // MK2
                    in 320_000_001L .. 640_000_000L -> 3 // MK3
                    in 640_000_001L .. 1_280_000_000L -> 4 // MK4
                    in 1_280_000_001L .. 2_560_000_000L -> 5 // MK5
                    else -> 0 // Error
                }

                COOLANTS.forEach { (inputFluid, outputFluid) ->
                    val amount = max((euCost / (V[EV] * 10000)) * inputFluid.amount, 1).toInt()
                    ADVANCED_FUSION_RECIPES.recipeBuilder()
                        .fluidInputs(inputs)
                        .fluidInputs(FluidStack(inputFluid, amount))
                        .fluidOutputs(outputs)
                        .fluidOutputs(FluidStack(outputFluid, amount))
                        .EUt(eut)
                        .tier(tier)
                        .duration(duration)
                        .buildAndRegister()
                }

            }
    }

    // @formatter:on

}