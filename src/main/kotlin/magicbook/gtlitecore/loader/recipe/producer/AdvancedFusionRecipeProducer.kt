package magicbook.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.V
import gregtech.api.unification.material.Materials
import gregtech.api.unification.material.Materials.Lava
import gregtech.api.unification.material.Materials.Water
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ADVANCED_FUSION_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials
import net.minecraftforge.fluids.FluidStack
import kotlin.math.max

@Suppress("MISSING_DEPENDENCY_CLASS")
class AdvancedFusionRecipeProducer
{

    companion object
    {
        private var COOLANTS = mutableMapOf<FluidStack, FluidStack>().apply {
            put(Materials.Steam.getFluid(570), GTLiteMaterials.SupercriticalSteam.getFluid(570))
            put(GTLiteMaterials.SodiumPotassium.getFluid(120), GTLiteMaterials.SupercriticalSodiumPotassium.getFluid(120))
            put(GTLiteMaterials.LeadBismuthEutatic.getFluid(60), GTLiteMaterials.SupercriticalLeadBismuthEutatic.getFluid(60))
            put(GTLiteMaterials.LithiumBerylliumFluorides.getFluid(55), GTLiteMaterials.SupercriticalLithiumBerylliumFluorides.getFluid(55))
            put(GTLiteMaterials.LithiumSodiumPotassiumFluorides.getFluid(50), GTLiteMaterials.SupercriticalLithiumSodiumPotassiumFluorides.getFluid(50))
        }

        fun produce()
        {
            ADVANCED_FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Water.getFluid(1))
                .fluidOutputs(Lava.getFluid(1))
                .EUt(1)
                .duration(1)
                .tier(1)
                .buildAndRegister()
        }


        // Coping fusion reactor recipes to advanced fusion reactor.
        // RecipeMaps.FUSION_RECIPES.onRecipeBuild(GTLiteUtility.gtliteId("reactor_moderator")) { recipeBuilder ->
        //
        // val euStart = max(recipeBuilder.euToStart + recipeBuilder.eUt * recipeBuilder.duration, 1) // (euToStart + EUt * duration) * euReturn / 100
        // COOLANTS.forEach { (inputFluid, outputFluid) ->
        //     val amount = max((euStart / (V[EV] * 10000)) * inputFluid.amount, 1).toInt()
        //     ADVANCED_FUSION_RECIPES.recipeBuilder()
        //         .inputs(*recipeBuilder.inputs.toTypedArray())
        //         .fluidInputs(recipeBuilder.fluidInputs)
        //         .fluidInputs(FluidStack(inputFluid, amount))
        //         .outputs(recipeBuilder.outputs)
        //         .chancedOutputs(recipeBuilder.chancedOutputs)
        //         .fluidOutputs(recipeBuilder.fluidOutputs)
        //         .fluidOutputs(FluidStack(outputFluid, amount))
        //         .chancedFluidOutputs(recipeBuilder.chancedFluidOutputs)
        //         .cleanroom(recipeBuilder.cleanroom)
        //         .duration(recipeBuilder.duration)
        //         .EUt(recipeBuilder.eUt)
        //         .tier(tier)
        //         .buildAndRegister()
        // }

    }

}