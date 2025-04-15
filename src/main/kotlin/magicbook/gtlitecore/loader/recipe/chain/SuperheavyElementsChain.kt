package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.Ytterbium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.VACUUM_CHAMBER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FleroviumYtterbiumPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableFlerovium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuasifissioningPlasma
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class SuperheavyElementsChain
{

    companion object
    {

        fun init()
        {
            metastableFleroviumProcess()
        }

        private fun metastableFleroviumProcess()
        {
            // U235 + U238 -> U235U238?
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Uranium235.getFluid(L))
                .fluidInputs(Uranium238.getFluid(L))
                .fluidOutputs(QuasifissioningPlasma.getPlasma(L * 2))
                .EUt(VA[ZPM].toLong())
                .duration(5 * SECOND)
                .EUToStart(325_000_000L) // 325M EU, MK3
                .buildAndRegister()

            // U235U238? -> FlYb?
            VACUUM_CHAMBER_RECIPES.recipeBuilder()
                .fluidInputs(QuasifissioningPlasma.getPlasma(L * 4))
                .fluidOutputs(FleroviumYtterbiumPlasma.getPlasma(L * 4))
                .EUt(VA[ZPM].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // FlYb? -> Fl + Yb
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(FleroviumYtterbiumPlasma.getPlasma(L * 4))
                .output(dust, Ytterbium, 2)
                .fluidOutputs(MetastableFlerovium.getPlasma(L * 2))
                .EUt(VA[UV].toLong())
                .duration(2 * SECOND)
                .buildAndRegister()

            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                OreDictUnifier.get(dust, MetastableFlerovium),
                IntCircuitIngredient.getIntegratedCircuit(1))

            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                arrayOf(OreDictUnifier.get(dust, MetastableFlerovium),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Krypton.getFluid(10)))

            FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_INGOT)
                .fluidInputs(MetastableFlerovium.getPlasma(L))
                .output(ingotHot, MetastableFlerovium)
                .EUt(VA[IV].toLong())
                .duration(7 * SECOND + 15 * TICK)
                .buildAndRegister()

        }

    }

}