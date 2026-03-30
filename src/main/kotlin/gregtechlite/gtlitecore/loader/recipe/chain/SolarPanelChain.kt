package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Glue
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Polytetrafluoroethylene
import gregtech.api.unification.material.Materials.PolyvinylAcetate
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.common.items.MetaItems.COVER_SOLAR_PANEL
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.cleanroom
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BURNER_REACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EthyleneVinylAcetatePolymer
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Methyltrichlorosilane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Silane
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SilicaGel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumPeroxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SolarGradeSilicon
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICA_INSULATOR_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.POLYMER_INSULATOR_FOIL

object SolarPanelChain
{

    // @formatter:off

    fun init()
    {
        // Si(CH3)Cl3 + 4H -> SiH4 + 3HCl + C
        BURNER_REACTOR_RECIPES.addRecipe {
            fluidInputs(Methyltrichlorosilane.getFluid(1000))
            fluidInputs(Hydrogen.getFluid(4000))
            output(dust, Carbon)
            fluidOutputs(Silane.getFluid(1000))
            fluidOutputs(HydrochloricAcid.getFluid(3000))
            EUt(VA[MV])
            duration(6 * SECOND)
        }

        // SiH4 + Na2O2 + 2Cl -> Si + 2NaCl + 2H2O
        CHEMICAL_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, SodiumPeroxide, 4)
            fluidInputs(Silane.getFluid(1000))
            fluidInputs(Chlorine.getFluid(2000))
            output(dust, SolarGradeSilicon)
            output(dust, Salt, 4)
            fluidOutputs(Water.getFluid(2000))
            EUt(VA[HV])
            duration(8 * SECOND)
        }

        // 3C2H4 + 2C4H6O2 -> (C2H4)3(C4H6O2)2
        AUTOCLAVE_RECIPES.addRecipe {
            input(round, Polyethylene, 27)
            fluidInputs(PolyvinylAcetate.getFluid(2000))
            fluidOutputs(EthyleneVinylAcetatePolymer.getFluid(1000))
            EUt(VA[MV])
            duration(10 * SECOND)
            cleanroom()
        }

        // Deleted Solar-Grade Silicon EBF gas protective recipe.
        BLAST_RECIPES.removeRecipe(
            arrayOf(IntCircuitIngredient.getIntegratedCircuit(2),
                    OreDictUnifier.get(dust, SolarGradeSilicon)),
            arrayOf(Nitrogen.getFluid(1000)))

        // Solar Panel
        ModHandler.removeRecipeByName("gregtech:solar_panel_basic")
        CHEMICAL_PLANT_RECIPES.addRecipe {
            circuitMeta(1)
            input(plate, SolarGradeSilicon, 4)
            input(circuit, Tier.ULV, 2)
            input(foil, Aluminium, 24)
            input(MICA_INSULATOR_FOIL, 32)
            input(dust, SilicaGel, 6)
            fluidInputs(Glue.getFluid(1000))
            fluidInputs(AnnealedCopper.getFluid(L * 4))
            fluidInputs(Polytetrafluoroethylene.getFluid(1000))
            fluidInputs(EthyleneVinylAcetatePolymer.getFluid(500))
            output(COVER_SOLAR_PANEL)
            EUt(VA[IV])
            duration(10 * SECOND)
            cleanroom()
        }

        CHEMICAL_PLANT_RECIPES.addRecipe {
            circuitMeta(2)
            input(plate, SolarGradeSilicon, 4)
            input(circuit, Tier.ULV, 2)
            input(foil, Aluminium, 24)
            input(POLYMER_INSULATOR_FOIL, 8)
            input(dust, SilicaGel, 6)
            fluidInputs(Glue.getFluid(1000))
            fluidInputs(AnnealedCopper.getFluid(L * 4))
            fluidInputs(Polytetrafluoroethylene.getFluid(1000))
            fluidInputs(EthyleneVinylAcetatePolymer.getFluid(500))
            output(COVER_SOLAR_PANEL)
            EUt(VA[IV])
            duration(2 * SECOND + 10 * TICK)
            cleanroom()
        }

        // Remove all existed solar panel recipes yet.
        ModHandler.removeRecipeByName("gregtech:solar_panel_ulv")
        ModHandler.removeRecipeByName("gregtech:solar_panel_lv")
    }

    // @formatter:on

}