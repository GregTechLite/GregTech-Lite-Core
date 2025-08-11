package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plate
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.POLISHER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HydrogenPeroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumHydride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumNiobate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NiobiumPentachloride
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NiobiumPentoxide
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object LithiumNiobateChain
{

    // @formatter:off

    fun init()
    {
        // Nb2O5 + 10HCl -> 2NbCl5 + 3H2O + 4H
        BURNER_REACTOR_RECIPES.recipeBuilder()
            .input(dust, NiobiumPentoxide, 7)
            .fluidInputs(HydrochloricAcid.getFluid(10000))
            .output(dust, NiobiumPentachloride, 12)
            .fluidOutputs(Hydrogen.getFluid(4000))
            .fluidOutputs(Steam.getFluid(3000))
            .EUt(VA[EV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // NbCl5 + LiH + 2H2O2 -> 6LiNbO4 + 5HCl (cycle)
        ARC_FURNACE_RECIPES.recipeBuilder()
            .input(dust, NiobiumPentachloride, 6)
            .input(dust, LithiumHydride, 2)
            .fluidInputs(HydrogenPeroxide.getFluid(2000))
            .output(ingotHot, LithiumNiobate, 6)
            .fluidOutputs(HydrochloricAcid.getFluid(5000))
            .EUt(VA[IV])
            .duration(20 * SECOND)
            .buildAndRegister()

        // LiNbO4 lens polishing.
        POLISHER_RECIPES.recipeBuilder()
            .input(plate, LithiumNiobate)
            .output(lens, LithiumNiobate)
            .output(dustSmall, LithiumNiobate)
            .EUt(VA[MV])
            .duration(1 * MINUTE)
            .buildAndRegister()
    }

    // @formatter:on

}