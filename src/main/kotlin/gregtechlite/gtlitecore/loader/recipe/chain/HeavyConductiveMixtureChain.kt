package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.Astatine
import gregtech.api.unification.material.Materials.Francium
import gregtech.api.unification.material.Materials.Holmium
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Iodine
import gregtech.api.unification.material.Materials.Methane
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CRYOGENIC_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CVD_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Acetylene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AstatineAzide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BoronTrioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FranciumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyConductiveMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HolmiumIodide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumAzide

internal object HeavyConductiveMixtureChain
{

    // @formatter:off

    fun init()
    {
        // 2Fr + C2H2 -> Fr2C2 + 2H
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Francium, 2)
            .fluidInputs(Acetylene.getFluid(1000))
            .output(dust, FranciumCarbide, 4)
            .fluidOutputs(Hydrogen.getFluid(2000))
            .EUt(VA[EV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // 2B2O3 + CH4 -> B4C + 2H2O + 4O (drop)
        CVD_RECIPES.recipeBuilder()
            .input(dust, BoronTrioxide, 10)
            .fluidInputs(Methane.getFluid(1000))
            .output(gem, BoronCarbide)
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[ZPM])
            .duration(10 * SECOND)
            .temperature(922)
            .buildAndRegister()

        // NaN3 + At + HCl -> AtN3 + NaCl + H
        CRYOGENIC_REACTOR_RECIPES.recipeBuilder()
            .input(dust, SodiumAzide, 4)
            .input(dust, Astatine)
            .fluidInputs(HydrochloricAcid.getFluid(1000))
            .output(dust, AstatineAzide, 4)
            .output(dust, Salt, 2)
            .fluidOutputs(Hydrogen.getFluid(1000))
            .EUt(VA[UV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Ho + 3I -> HoI3
        CHEMICAL_RECIPES.recipeBuilder()
            .input(dust, Holmium)
            .input(dust, Iodine, 3)
            .output(dust, HolmiumIodide, 4)
            .EUt(VA[HV])
            .duration(4 * SECOND + 5 * TICK)
            .buildAndRegister()

        // Fr2C2 + B4C + AtN3 + HoI3 -> (Fr2C2)(B4C)(AtN3)(HoI3)
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(dust, FranciumCarbide, 4)
            .input(dust, BoronCarbide, 5)
            .input(dust, AstatineAzide, 4)
            .input(dust, HolmiumIodide, 4)
            .output(dust, HeavyConductiveMixture, 17)
            .EUt(VA[UHV])
            .duration(25 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}
