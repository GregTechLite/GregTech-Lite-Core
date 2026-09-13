package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.PYROLYSE_RECIPES
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.Butyraldehyde
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Phenol
import gregtech.api.unification.material.Materials.Rhenium
import gregtech.api.unification.material.Materials.Yttrium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.stick
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AceticAnhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CBDOPolycarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dimethylketene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DiphenylCarbonate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IsobutyricAcid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IsobutyricAnhydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tetramethylcyclobutanediol

internal object CBDOPolycarbonateChain
{
    // @formatter:off

    fun init()
    {
        // C4H8O + O -> C4H8O2
        CHEMICAL_RECIPES.addRecipe {
            fluidInputs(Butyraldehyde.getFluid(1000))
            fluidInputs(Oxygen.getFluid(1000))
            fluidOutputs(IsobutyricAcid.getFluid(1000))
            EUt(VA[HV])
            duration(9 * SECOND)
        }

        // 2C4H8O2 + C4H6O3 -> C8H14O3 + 2C2H4O2
        CHEMICAL_RECIPES.addRecipe {
            fluidInputs(IsobutyricAcid.getFluid(2000))
            fluidInputs(AceticAnhydride.getFluid(1000))
            fluidOutputs(IsobutyricAnhydride.getFluid(1000))
            fluidOutputs(AceticAcid.getFluid(2000))
            EUt(VA[EV])
            duration(3 * SECOND)
        }

        // C8H14O3 -> 2C4H6O2 + 2H2O (lost)
        PYROLYSE_RECIPES.addRecipe {
            notConsumable(stick, Yttrium)
            fluidInputs(IsobutyricAnhydride.getFluid(1000))
            fluidOutputs(Dimethylketene.getFluid(2000))
            EUt(VA[IV])
            duration(12 * SECOND)
        }

        // 2C4H6O2 + 4H -> C8H16O2
        CHEMICAL_RECIPES.addRecipe {
            notConsumable(dust, Rhenium)
            fluidInputs(Dimethylketene.getFluid(2000))
            fluidInputs(Hydrogen.getFluid(4000))
            fluidOutputs(Tetramethylcyclobutanediol.getFluid(1000))
            EUt(VA[UV])
            duration(6 * SECOND)
        }

        // C8H16O2 + C13H10O3 -> C9H14O3 + 2C6H6O
        CHEMICAL_RECIPES.addRecipe {
            fluidInputs(Tetramethylcyclobutanediol.getFluid(1000))
            fluidInputs(DiphenylCarbonate.getFluid(1000))
            fluidOutputs(CBDOPolycarbonate.getFluid(L))
            fluidOutputs(Phenol.getFluid(2000))
            EUt(VA[MV])
            duration(8 * SECOND)
        }
    }

    // @formatter:on
}