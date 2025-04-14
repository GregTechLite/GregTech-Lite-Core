package magicbook.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.DilutedSulfuricAcid
import gregtech.api.unification.material.Materials.Indium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Neon
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.Plutonium239
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CHEMICAL_PLANT_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.AdamantiumEnriched
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BedrockGas
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DeepIron
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Vibranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.VibraniumUnstable
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK

@Suppress("MISSING_DEPENDENCY_CLASS")
class VibraniumProcessing
{

    companion object
    {

        fun init()
        {
            // 10Ad -> Vb? + Fe2KeIn + 2Nq + 2Os
            CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .circuitMeta(24)
                .input(dust, Adamantium, 10)
                .fluidInputs(BedrockGas.getFluid(100))
                .fluidInputs(SulfuricAcid.getFluid(100))
                .output(dust, AdamantiumEnriched, 1)
                .output(dust, DeepIron, 5)
                .output(dust, Naquadah, 2)
                .output(dust, Osmium, 2)
                .fluidOutputs(DilutedSulfuricAcid.getFluid(900))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Fe2KeIn -> 2Fe + Ke + In
            ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder()
                .input(dust, DeepIron, 4)
                .output(dust, Iron, 2)
                .output(dust, Trinium)
                .output(dust, Indium)
                .EUt(VA[IV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // 4Vb? + 4Nq+ -> Vb* + 2Nq+ (part cycle) + Pu239
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, AdamantiumEnriched, 4)
                .fluidInputs(NaquadahEnriched.getFluid(L * 4))
                .output(dust, NaquadahEnriched, 2)
                .output(dust, Plutonium239, 1)
                .fluidOutputs(VibraniumUnstable.getFluid(L * 4))
                .EUt(VA[ZPM].toLong())
                .duration(1 * MINUTE + 20 * SECOND)
                .buildAndRegister()

            // Tr + Vb* -> Vb
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(Tritanium.getFluid(L))
                .fluidInputs(VibraniumUnstable.getFluid(L))
                .fluidOutputs(Vibranium.getPlasma(L))
                .EUt(VA[ZPM] * 2L) // ZPM
                .duration(3 * SECOND + 4 * TICK)
                .EUToStart(620_000_000L) // 620M EU, MK3
                .buildAndRegister()

            // Vibranium plasma will direct cooling by fluid solidification to
            // a hot vibranium ingot, vibranium dust cannot blast to ingot yet,
            // just like adamantium.
            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                OreDictUnifier.get(dust, Vibranium),
                IntCircuitIngredient.getIntegratedCircuit(1))

            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Vibranium),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Neon.getFluid(25)))

            FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_INGOT)
                .fluidInputs(Vibranium.getPlasma(L))
                .output(ingotHot, Vibranium)
                .EUt(500_000) // UV
                .duration(20 * SECOND)
                .buildAndRegister()

        }

    }

}