package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.common.items.MetaItems.GRAVI_STAR
import gregtech.common.items.MetaItems.STEM_CELLS
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GelidCryotheum
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder

internal object MutatedLivingSolderChain
{

    // @formatter:off

    fun init()
    {
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
            .input(STEM_CELLS, 64)
            .input(GRAVI_STAR, 8)
            .input(dust, Neutronium, 2)
            .fluidInputs(Tin.getPlasma(18000))
            .fluidInputs(Bismuth.getPlasma(18000))
            .fluidInputs(GelidCryotheum.getFluid(4000))
            .fluidOutputs(MutatedLivingSolder.getFluid(L * 300))
            .EUt(VA[UEV])
            .duration(1 * MINUTE + 30 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    // @formatter:on

}