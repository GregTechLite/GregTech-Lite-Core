package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Californium
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Radium
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.Scandium
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.Ytterbium
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VACUUM_CHAMBER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.FleroviumYtterbiumPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableFlerovium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableHassium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.OganessonBreedingBase
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuasifissioningPlasma
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RadiumRadonMixture
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ScandiumTitaniumMixture

internal object SuperheavyElementsChain
{

    // @formatter:off

    fun init()
    {
        hassiumProcess()   // 108 Hassium (Hs)
        fleroviumProcess() // 114 Flerovium (Fl)
        oganessonProcess() // 118 Oganesson (Og)
    }

    private fun hassiumProcess()
    {
        // Sc + Ti -> ScTi
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Scandium.getFluid(L))
            .fluidInputs(Titanium.getFluid(L))
            .fluidOutputs(ScandiumTitaniumMixture.getFluid(L * 2))
            .EUt(VA[ZPM])
            .duration(12 * SECOND)
            .buildAndRegister()

        // Ra + Rn -> RaRn
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Radium.getFluid(L))
            .fluidInputs(Radon.getFluid(125))
            .fluidOutputs(RadiumRadonMixture.getFluid(L * 2))
            .EUt(VA[ZPM])
            .duration(12 * SECOND)
            .buildAndRegister()

        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
            OreDictUnifier.get(dust, MetastableHassium),
            IntCircuitIngredient.getIntegratedCircuit(1))

        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
            arrayOf(OreDictUnifier.get(dust, MetastableHassium),
                IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Krypton.getFluid(10)))

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_INGOT)
            .fluidInputs(MetastableHassium.getPlasma(L))
            .output(ingotHot, MetastableHassium)
            .EUt(VA[UV])
            .duration(14 * SECOND)
            .buildAndRegister()
    }

    private fun fleroviumProcess()
    {
        // U235 + U238 -> U235U238?
        FUSION_RECIPES.recipeBuilder()
            .fluidInputs(Uranium235.getFluid(L))
            .fluidInputs(Uranium238.getFluid(L))
            .fluidOutputs(QuasifissioningPlasma.getPlasma(L * 2))
            .EUt(VA[ZPM])
            .duration(5 * SECOND)
            .EUToStart(325_000_000L) // 325M EU, MK3
            .buildAndRegister()

        // U235U238? -> FlYb?
        VACUUM_CHAMBER_RECIPES.recipeBuilder()
            .fluidInputs(QuasifissioningPlasma.getPlasma(L * 4))
            .fluidOutputs(FleroviumYtterbiumPlasma.getPlasma(L * 4))
            .EUt(VA[ZPM])
            .duration(4 * SECOND)
            .buildAndRegister()

        // FlYb? -> Fl + Yb
        CENTRIFUGE_RECIPES.recipeBuilder()
            .fluidInputs(FleroviumYtterbiumPlasma.getPlasma(L * 4))
            .output(dust, Ytterbium, 2)
            .fluidOutputs(MetastableFlerovium.getPlasma(L * 2))
            .EUt(VA[UV])
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
            .EUt(VA[IV])
            .duration(7 * SECOND + 15 * TICK)
            .buildAndRegister()

    }

    private fun oganessonProcess()
    {
        // 2Ti + 2Cf -> TiCf (Og Breeding Base)
        MIXER_RECIPES.recipeBuilder()
            .fluidInputs(Titanium.getFluid(L * 2))
            .fluidInputs(Californium.getFluid(L * 2))
            .fluidOutputs(OganessonBreedingBase.getFluid(L * 4))
            .EUt(VA[IV])
            .duration(6 * SECOND)
            .buildAndRegister()

        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
            OreDictUnifier.get(dust, MetastableOganesson),
            IntCircuitIngredient.getIntegratedCircuit(1))

        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
            arrayOf(OreDictUnifier.get(dust, MetastableOganesson),
                IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Krypton.getFluid(10)))

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
            .notConsumable(SHAPE_MOLD_INGOT)
            .fluidInputs(MetastableOganesson.getPlasma(L))
            .output(ingotHot, MetastableOganesson)
            .EUt(VA[ZPM])
            .duration(12 * SECOND + 15 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}