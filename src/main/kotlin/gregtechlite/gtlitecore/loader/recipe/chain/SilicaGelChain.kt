package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.dust
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SilicaGel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SilicaGelBase
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt

internal object SilicaGelChain
{

    // @formatter:off

    fun init()
    {
        // SiO2 + NaOH + HCl + H2O -> (SiNa(OH)O2)(HCl)(H2O)
        MIXER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(dust, SiliconDioxide, 3)
            .input(dust, SodiumHydroxide, 3)
            .fluidInputs(HydrochloricAcid.getFluid(1000))
            .fluidInputs(Steam.getFluid(1000))
            .fluidOutputs(SilicaGelBase.getFluid(1000))
            .EUt(VA[MV])
            .duration(3 * SECOND)
            .buildAndRegister()

        // (SiNa(OH)O2)(HCl)(H2O) -> SiO2 + NaCl + 2H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
            .fluidInputs(SilicaGelBase.getFluid(1000))
            .output(dust, SilicaGel, 3)
            .output(dust, Salt, 2)
            .fluidOutputs(Water.getFluid(2000))
            .EUt(VA[HV])
            .duration(6 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    // @formatter:on

}