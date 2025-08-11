package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.recipes.RecipeBuilder
import gregtech.api.recipes.builders.BlastRecipeBuilder
import gregtech.api.unification.material.Material

class CustomAlloyBlastRecipeProducer(private val circuitNumber: Int,
                                     private val gasCircuitNumber: Int,
                                     private val outputAmount: Int) : AlloyBlastRecipeProducer()
{

    override fun addInputs(material: Material,
                           builder: RecipeBuilder<BlastRecipeBuilder>): Int
    {
        val amount = super.addInputs(material, builder)
        return if (this.outputAmount < 0) amount else this.outputAmount
    }

    override fun getCircuitNumber(componentAmount: Int) = if (this.circuitNumber < 0) super.getCircuitNumber(componentAmount) else this.circuitNumber

    override fun getGasCircuitNumber(componentAmount: Int) = if (this.gasCircuitNumber < 0) super.getGasCircuitNumber(componentAmount) else this.gasCircuitNumber

}