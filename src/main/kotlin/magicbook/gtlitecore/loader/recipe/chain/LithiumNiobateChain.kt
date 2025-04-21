package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plate
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.POLISHER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenPeroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumHydride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumNiobate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NiobiumPentachloride
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.NiobiumPentoxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class LithiumNiobateChain
{

    companion object
    {

        fun init()
        {
            // Nb2O5 + 10HCl -> 2NbCl5 + 3H2O + 4H
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, NiobiumPentoxide, 7)
                .fluidInputs(HydrochloricAcid.getFluid(10000))
                .output(dust, NiobiumPentachloride, 12)
                .fluidOutputs(Hydrogen.getFluid(4000))
                .fluidOutputs(Steam.getFluid(3000))
                .EUt(VA[EV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // NbCl5 + LiH + 2H2O2 -> 6LiNbO4 + 5HCl (cycle)
            ARC_FURNACE_RECIPES.recipeBuilder()
                .input(dust, NiobiumPentachloride, 6)
                .input(dust, LithiumHydride, 2)
                .fluidInputs(HydrogenPeroxide.getFluid(2000))
                .output(ingotHot, LithiumNiobate, 6)
                .fluidOutputs(HydrochloricAcid.getFluid(5000))
                .EUt(VA[IV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // LiNbO4 lens polishing.
            POLISHER_RECIPES.recipeBuilder()
                .input(plate, LithiumNiobate)
                .output(lens, LithiumNiobate)
                .output(dustSmall, LithiumNiobate)
                .EUt(VA[MV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()
        }

    }

}