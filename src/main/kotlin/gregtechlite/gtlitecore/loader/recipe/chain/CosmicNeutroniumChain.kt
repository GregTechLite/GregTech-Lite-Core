package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NeutronProtonFermiSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium

internal object CosmicNeutroniumChain
{

    // @formatter:off

    fun init()
    {
        // Neutron-Proton Fermi Superfluid + Taranium -> Cosmic Neutronium
        FUSION_RECIPES.addRecipe {
            fluidInputs(NeutronProtonFermiSuperfluid.getFluid(1000))
            fluidInputs(Taranium.getFluid(L * 4))
            fluidOutputs(CosmicNeutronium.getFluid(L * 2))
            EUt(VA[UHV])
            duration(10 * SECOND)
            EUToStart(1_270_000_000) // 1,270M EU (MK4)
        }
    }

    // @formatter:on

}