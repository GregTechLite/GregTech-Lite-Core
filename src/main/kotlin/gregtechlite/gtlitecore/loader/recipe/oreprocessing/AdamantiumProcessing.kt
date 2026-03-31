package gregtechlite.gtlitecore.loader.recipe.oreprocessing

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Hafnium
import gregtech.api.unification.material.Materials.Monazite
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.Zirconium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.AdamantiumUnstable
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium

/**
 * Produces Adamantium from Bedrockium.
 * - Main Products: Adamantium.
 * - Side Products: Hafnium, Zirconium.
 */
internal object AdamantiumProcessing
{

    // @formatter:off

    fun init()
    {
        // Separated the bedrockium to get some special materials.
        ELECTROMAGNETIC_SEPARATOR_RECIPES.addRecipe {
            input(dust, Bedrockium)
            chancedOutput(dust, Adamantite, 3000, 500)
            chancedOutput(dust, Monazite, 2, 3000, 0)
            chancedOutput(dust, Hafnium, 3, 6000, 0)
            chancedOutput(dust, Zirconium, 4, 4500, 0)
            EUt(VA[IV])
            duration(6 * SECOND)
        }

        // 4Ad + 4Nq -> Ad* + Nq (part cycle) + U238
        CHEMICAL_BATH_RECIPES.addRecipe {
            input(dust, Adamantium, 4)
            fluidInputs(Naquadah.getFluid(L * 4))
            output(dust, Naquadah)
            output(dust, Uranium238)
            fluidOutputs(AdamantiumUnstable.getFluid(L * 4))
            EUt(VA[LuV])
            duration(40 * SECOND)
        }

        // Dr + Ad* -> Ad
        FUSION_RECIPES.addRecipe {
            fluidInputs(Duranium.getFluid(16))
            fluidInputs(AdamantiumUnstable.getFluid(16))
            fluidOutputs(Adamantium.getPlasma(16))
            EUt(VA[LuV])
            duration(1 * SECOND + 12 * TICK)
            EUToStart(300_000_000L) // 300M EU （MK1）
        }

        // Adamantium plasma will direct cooling by fluid solidification to
        // a hot adamantium ingot, adamantium dust cannot blast to ingot yet.
        BLAST_RECIPES.removeRecipe(
            OreDictUnifier.get(dust, Adamantium),
            IntCircuitIngredient.getIntegratedCircuit(1))

        BLAST_RECIPES.removeRecipe(
            arrayOf(OreDictUnifier.get(dust, Adamantium),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Argon.getFluid(50)))

        FLUID_SOLIDFICATION_RECIPES.addRecipe {
            notConsumable(SHAPE_MOLD_INGOT)
            fluidInputs(Adamantium.getPlasma(L))
            output(ingotHot, Adamantium)
            EUt(VA[IV])
            duration(10 * SECOND)
        }
    }

    // @formatter:on

}