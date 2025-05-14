package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HighEnergyQuarkGluonPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Protomatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuarkGluonPlasma
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.QUANTUM_ANOMALY

@Suppress("MISSING_DEPENDENCY_CLASS")
class AntimatterChain
{

    companion object
    {

        fun init()
        {
            // Protomatter
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(QUANTUM_ANOMALY)
                .fluidInputs(QuarkGluonPlasma.getFluid(1000))
                .fluidOutputs(Protomatter.getFluid(1000))
                .EUt(VA[UEV].toLong())
                .duration(30 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(QUANTUM_ANOMALY)
                .fluidInputs(HighEnergyQuarkGluonPlasma.getFluid(1000))
                .fluidOutputs(Protomatter.getFluid(10000))
                .EUt(VA[UIV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Protomatter -> Semistable Antimatter TODO

            // Semistable Antimatter -> Antimatter TODO

        }

    }

}