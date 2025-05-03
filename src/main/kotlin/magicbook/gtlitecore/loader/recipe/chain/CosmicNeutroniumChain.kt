package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NeutronProtonFermiSuperfluid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class CosmicNeutroniumChain
{

    companion object
    {

        fun init()
        {
            // Cosmic Neutronium
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(NeutronProtonFermiSuperfluid.getFluid(1000))
                .fluidInputs(Taranium.getFluid(L * 4))
                .fluidOutputs(CosmicNeutronium.getFluid(L * 2))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .EUToStart(1_270_000_000) // 1270M EU, MK4
                .buildAndRegister()

        }

    }

}