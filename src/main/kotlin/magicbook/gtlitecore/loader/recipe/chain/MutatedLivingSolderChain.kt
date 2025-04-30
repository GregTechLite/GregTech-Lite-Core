package magicbook.gtlitecore.loader.recipe.chain

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
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GelidCryotheum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MutatedLivingSolder
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND

@Suppress("MISSING_DEPENDENCY_CLASS")
class MutatedLivingSolderChain
{

    companion object
    {

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
                .EUt(VA[UEV].toLong())
                .duration(1 * MINUTE + 30 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }

    }

}