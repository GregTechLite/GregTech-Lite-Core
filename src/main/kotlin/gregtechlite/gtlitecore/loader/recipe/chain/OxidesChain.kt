package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.VHA
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.AntimonyTrioxide
import gregtech.api.unification.material.Materials.Arsenic
import gregtech.api.unification.material.Materials.ArsenicTrioxide
import gregtech.api.unification.material.Materials.Ash
import gregtech.api.unification.material.Materials.BandedIron
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.Beryllium
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonDioxide
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.Cerium
import gregtech.api.unification.material.Materials.Chalcopyrite
import gregtech.api.unification.material.Materials.Charcoal
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.ChromiumTrioxide
import gregtech.api.unification.material.Materials.Coal
import gregtech.api.unification.material.Materials.CobaltOxide
import gregtech.api.unification.material.Materials.Cobaltite
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Dysprosium
import gregtech.api.unification.material.Materials.Erbium
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gadolinium
import gregtech.api.unification.material.Materials.Gallium
import gregtech.api.unification.material.Materials.Garnierite
import gregtech.api.unification.material.Materials.Germanium
import gregtech.api.unification.material.Materials.Hafnia
import gregtech.api.unification.material.Materials.Holmium
import gregtech.api.unification.material.Materials.Lanthanum
import gregtech.api.unification.material.Materials.Lithium
import gregtech.api.unification.material.Materials.Lutetium
import gregtech.api.unification.material.Materials.Magnesia
import gregtech.api.unification.material.Materials.Magnesite
import gregtech.api.unification.material.Materials.Neodymium
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Pentlandite
import gregtech.api.unification.material.Materials.Phosphorus
import gregtech.api.unification.material.Materials.PhosphorusPentoxide
import gregtech.api.unification.material.Materials.Praseodymium
import gregtech.api.unification.material.Materials.Promethium
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.Samarium
import gregtech.api.unification.material.Materials.Scandium
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Sodium
import gregtech.api.unification.material.Materials.Sphalerite
import gregtech.api.unification.material.Materials.Stibnite
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.SulfurDioxide
import gregtech.api.unification.material.Materials.SulfurTrioxide
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Terbium
import gregtech.api.unification.material.Materials.Tetrahedrite
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Thulium
import gregtech.api.unification.material.Materials.Vanadium
import gregtech.api.unification.material.Materials.Ytterbium
import gregtech.api.unification.material.Materials.Yttrium
import gregtech.api.unification.material.Materials.ZincSulfide
import gregtech.api.unification.material.Materials.Zincite
import gregtech.api.unification.material.Materials.Zircon
import gregtech.api.unification.material.Materials.Zirconia
import gregtech.api.unification.material.Materials.ZirconiumTetrachloride
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.inputs
import gregtechlite.gtlitecore.api.extension.removeRecipe
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeHandler
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ROASTER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Baddeleyite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BariumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BerylliumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BismuthTrioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CeriumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Cuprite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.DysprosiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ErbiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EuropiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ferrosilite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GadoliniumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GalliumDioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GermaniumDioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HolmiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Iron3Sulfate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LanthanumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lignite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LithiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.LutetiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NeodymiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NiobiumPentoxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Orpiment
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PraseodymiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PromethiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SamariumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ScandiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SilverOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SodiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StrontiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumPentoxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tenorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TerbiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThoriumDioxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ThuliumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.VanadiumPentoxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.YtterbiumOxide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.YttriumOxide
import net.minecraft.init.Items.COAL

internal object OxidesChain
{

    // @formatter:off

    fun init()
    {
        carbonMonoxideProcessing()
        carbonDioxideProcessing()

        // region MgCO3 Roasting

        // Deleted original MgCO3 -> MgO + CO2 recipes in chemical reactor.
        GTLiteRecipeHandler.removeChemicalRecipes(OreDictUnifier.get(dust, Magnesite, 5))

        ROASTER_RECIPES.addRecipe {
            input(dust, Magnesite, 5)
            output(dust, Magnesia, 2)
            fluidOutputs(CarbonDioxide.getFluid(1000))
            EUt(VA[LV])
            duration(12 * SECOND)
        }

        // endregion

        // region Sb2O3 and Sb2S3 Roasting

        ROASTER_RECIPES.addRecipe {
            input(dust, Stibnite)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, AntimonyTrioxide)
            chancedOutput(dust, Ash, 1000, 0)
            fluidOutputs(SulfurDioxide.getFluid(1500))
            EUt(VA[MV])
            duration(6 * SECOND)
        }

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Antimony, 2)),
            arrayOf(Oxygen.getFluid(3000)))

        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Antimony, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, AntimonyTrioxide, 5)
            EUt(VA[ULV])
            duration(3 * SECOND)
        }

        // endregion

        // region Chalcopyrite (CuFeS2) and Tetrahedrite (Cu3FeSbS3) Roasting

        // CuFeS2 + SiO2 + 3O -> CuO + FeSiO3 + 2SO2
        ROASTER_RECIPES.addRecipe {
            input(dust, Chalcopyrite)
            input(dust, SiliconDioxide)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, Tenorite)
            output(dust, Ferrosilite)
            fluidOutputs(SulfurDioxide.getFluid(2000))
            EUt(VA[MV])
            duration(6 * SECOND)
        }

        // Cu3FeSbS3 + 3O -> CuO + 0.75Sb2O3 + 2SO2
        ROASTER_RECIPES.recipeBuilder()
            .input(dust, Tetrahedrite)
            .fluidInputs(Oxygen.getFluid(3000))
            .output(dust, Tenorite)
            .output(dustTiny, AntimonyTrioxide, 3)
            .fluidOutputs(SulfurDioxide.getFluid(2000))
            .EUt(VA[MV])
            .duration(6 * SECOND)
            .buildAndRegister()

        // endregion

        // region Cobaltite (CoAsS) Roasting

        ROASTER_RECIPES.addRecipe {
            input(dust, Cobaltite)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, CobaltOxide)
            output(dust, ArsenicTrioxide)
            fluidOutputs(SulfurDioxide.getFluid(1000))
            EUt(VA[MV])
            duration(6 * SECOND)
        }

        // endregion

        // region As2S3, As4S4 and As2O3 Roasting

        // 2As + 3O -> As2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Arsenic, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, ArsenicTrioxide, 5)
            EUt(VA[LV])
            duration(4 * SECOND + 5 * TICK)
        }

        // As2S3 + 9O -> As2O3 + 3SO2
        ROASTER_RECIPES.addRecipe {
            input(dust, Orpiment, 5)
            fluidInputs(Oxygen.getFluid(6000))
            output(dust, ArsenicTrioxide, 5)
            fluidOutputs(SulfurDioxide.getFluid(3000))
            EUt(VA[LV])
            duration(2 * SECOND + 15 * TICK)
        }

        // As4S4 + 14O -> 2As2O3 + 4SO2
        ROASTER_RECIPES.addRecipe {
            input(dust, Realgar, 8)
            fluidInputs(Oxygen.getFluid(14000))
            output(dust, ArsenicTrioxide, 10)
            fluidOutputs(SulfurDioxide.getFluid(4000))
            EUt(VA[LV])
            duration(4 * SECOND)
        }

        // endregion

        // region Zircon (ZrSiO4) Roasting

        // ZrSiO4 -> 2ZrO2 + HfO2 + SiO2
        BLAST_RECIPES.removeRecipe(OreDictUnifier.get(dust, Zircon, 6))
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Zircon, 6)
            output(dust, Baddeleyite, 3)
            output(dust, SiliconDioxide, 3)
            chancedOutput(dust, Hafnia, 3333, 0)
            EUt(VA[MV])
            duration(10 * SECOND)
        }

        // ZrSiO4 + 2O -> ZrO2 + HfO2 + SiO2
        ROASTER_RECIPES.addRecipe {
            circuitMeta(2)
            input(dust, Zircon, 6)
            fluidInputs(Oxygen.getFluid(2000))
            output(dust, Baddeleyite, 3)
            output(dust, Hafnia, 3)
            output(dust, SiliconDioxide, 3)
            EUt(VA[HV])
            duration(5 * SECOND)
        }

        // ZrO2 + C + 4Cl -> ZrCl4 + CO2
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Zirconia, 3),
                    OreDictUnifier.get(dust, Carbon)),
            arrayOf(Chlorine.getFluid(4000)))

        CHEMICAL_RECIPES.addRecipe {
            input(dust, Baddeleyite, 3)
            input(dust, Carbon)
            fluidInputs(Chlorine.getFluid(4000))
            output(dust, ZirconiumTetrachloride, 5)
            fluidOutputs(CarbonDioxide.getFluid(1000))
            EUt(VA[HV])
            duration(5 * SECOND)
        }

        // ZnS + 3O -> ZnO + SO2
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Sphalerite, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, Zincite, 2)
            chancedOutput(dust, Ash, 1000, 0)
            fluidOutputs(SulfurDioxide.getFluid(1000))
            EUt(VA[MV])
            duration(6 * SECOND)
        }

        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, ZincSulfide, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, Zincite, 2)
            chancedOutput(dust, Ash, 1000, 0)
            fluidOutputs(SulfurDioxide.getFluid(1000))
            EUt(VA[MV])
            duration(6 * SECOND)
        }

        // endregion

        // region Banded Iron (Fe2O3) Roasting

        // Fe2(SO4)3 -> Fe2O3 + 3SO3
        // This is another choice in early game for provide raw material to H2SO4.
        ROASTER_RECIPES.addRecipe {
            input(dust, Iron3Sulfate)
            output(dust, BandedIron)
            fluidOutputs(SulfurTrioxide.getFluid(3000))
            EUt(VA[ULV])
            duration(8 * SECOND)
        }

        // endregion

        // region Pentlandite (Ni9S8) Roasting

        // Ni9S8 + 3O -> NiO + SO2
        ROASTER_RECIPES.addRecipe {
            input(dust, Pentlandite)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, Garnierite)
            chancedOutput(dust, Ash, 1000, 0)
            fluidOutputs(SulfurDioxide.getFluid(1000))
            EUt(VA[MV])
            duration(6 * SECOND)
        }

        // endregion

        // region P4O10 Roasting

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Phosphorus, 4),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(Oxygen.getFluid(10000)))

        ROASTER_RECIPES.addRecipe {
            circuitMeta(5)
            input(dust, Phosphorus, 4)
            fluidInputs(Oxygen.getFluid(10000))
            output(dust, PhosphorusPentoxide, 14)
            EUt(VA[LV])
            duration(2 * SECOND)
        }

        // endregion

        // region CrO3 Roasting

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Chrome, 1)),
            arrayOf(Oxygen.getFluid(3000)))

        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Chrome)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, ChromiumTrioxide, 4)
            EUt(VHA[MV])
            duration(5 * SECOND)
        }

        // endregion

        // region Li2O and Na2O Roasting

        // 2Li + O -> Li2O
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Lithium, 2)
            fluidInputs(Oxygen.getFluid(1000))
            output(dust, LithiumOxide, 3)
            EUt(VH[LV])
            duration(3 * SECOND)
        }

        // 2Na + O -> Na2O
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Sodium, 2)
            fluidInputs(Oxygen.getFluid(1000))
            output(dust, SodiumOxide, 3)
            EUt(VA[LV])
            duration(2 * SECOND)
        }

        // endregion

        // region Nb2O5 and Ta2O5 Roasting

        // 2Nb + 5O -> Nb2O5
        ROASTER_RECIPES.addRecipe {
            circuitMeta(5)
            input(dust, Niobium, 2)
            fluidInputs(Oxygen.getFluid(5000))
            output(dust, NiobiumPentoxide, 7)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Ta + 5O -> Ta2O5
        ROASTER_RECIPES.addRecipe {
            circuitMeta(5)
            input(dust, Tantalum, 2)
            fluidInputs(Oxygen.getFluid(5000))
            output(dust, TantalumPentoxide, 7)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // endregion

        // region BeO and Bi2O3 Roasting

        // Be + O -> BeO
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Beryllium)
            fluidInputs(Oxygen.getFluid(1000))
            output(ingot, BerylliumOxide, 2)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Bi + 3O -> Bi2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Bismuth, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, BismuthTrioxide, 5)
            EUt(VA[LV])
            duration(4 * SECOND + 5 * TICK)
        }

        // endregion

        // region CuO, Cu2O Roasting

        // Cu + O -> CuO
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Copper)
            fluidInputs(Oxygen.getFluid(1000))
            output(dust, Tenorite, 2)
            EUt(VA[LV])
            duration(15 * TICK)
        }

        // 2Cu + O -> Cu2O
        ROASTER_RECIPES.addRecipe {
            circuitMeta(2)
            input(dust, Copper, 2)
            fluidInputs(Oxygen.getFluid(1000))
            output(dust, Cuprite, 3)
            EUt(VA[LV])
            duration(1 * SECOND + 10 * TICK)
        }

        // endregion

        // region REE Oxides Roasting

        // All REE Oxides should has its oxidification recipe, but not push player use REE distillation.

        // 2Sc + 3O -> Sc2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Scandium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, ScandiumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Y + 3O -> Y2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Yttrium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, YttriumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2La + 3O -> La2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Lanthanum, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, LanthanumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // Ce + 2O -> CeO2
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Cerium)
            fluidInputs(Oxygen.getFluid(2000))
            output(dust, CeriumOxide, 3)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Pr + 3O -> Pr2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Praseodymium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, PraseodymiumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Nd + 3O -> Nd2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Neodymium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, NeodymiumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Pm + 3O -> Pm2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Promethium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, PromethiumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Sm + 3O -> Sm2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Samarium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, SamariumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Eu + 3O -> Eu2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Europium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, EuropiumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Gd + 3O -> Gd2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Gadolinium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, GadoliniumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Tb + 3O -> Tb2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Terbium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, TerbiumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Dy + 3O -> Dy2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Dysprosium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, DysprosiumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Ho + 3O -> Ho2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Holmium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, HolmiumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Er + 3O -> Er2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Erbium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, ErbiumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Tm + 3O -> Tm2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Thulium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, ThuliumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Yb + 3O -> Yb2O3
        ROASTER_RECIPES.addRecipe {
            circuitMeta(3)
            input(dust, Ytterbium, 2)
            fluidInputs(Oxygen.getFluid(3000))
            output(dust, YtterbiumOxide, 5)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // 2Lu + 3O -> Lu2O3
        ROASTER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(dust, Lutetium, 2)
            .fluidInputs(Oxygen.getFluid(3000))
            .output(dust, LutetiumOxide, 5)
            .EUt(VA[MV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // endregion

        // region GaO2 Roasting

        // Add another choice of GaO2, do not push player used Naquadah processing.

        // Ga + 2O -> GaO2
        ROASTER_RECIPES.addRecipe {
            circuitMeta(2)
            input(dust, Gallium)
            fluidInputs(Oxygen.getFluid(2000))
            output(dust, GalliumDioxide, 3)
            EUt(VA[MV])
            duration(2 * SECOND + 10 * TICK)
        }

        // endregion

        // region Misc Oxides Roasting

        // Th + 2O -> ThO2
        ROASTER_RECIPES.addRecipe {
            circuitMeta(2)
            input(dust, Thorium)
            fluidInputs(Oxygen.getFluid(2000))
            output(dust, ThoriumDioxide, 3)
            EUt(VA[MV])
            duration(4 * SECOND + 5 * TICK)
        }

        // Ba + O -> BaO
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Barium)
            fluidInputs(Oxygen.getFluid(1000))
            output(dust, BariumOxide, 2)
            EUt(VA[LV])
            duration(2 * SECOND)
        }

        // 2V + 5O -> V2O5
        ROASTER_RECIPES.addRecipe {
            circuitMeta(5)
            input(dust, Vanadium, 2)
            fluidInputs(Oxygen.getFluid(5000))
            output(dust, VanadiumPentoxide, 7)
            EUt(VA[MV])
            duration(3 * SECOND + 10 * TICK)
        }

        // 2Ag + O -> Ag2O
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Silver, 2)
            fluidInputs(Oxygen.getFluid(1000))
            output(dust, SilverOxide, 3)
            EUt(VA[LV])
            duration(4 * SECOND + 5 * TICK)
        }

        // Ge + 2O -> GeO2
        ROASTER_RECIPES.addRecipe {
            circuitMeta(2)
            input(dust, Germanium)
            fluidInputs(Oxygen.getFluid(2000))
            output(dust, GermaniumDioxide, 3)
            EUt(VA[LV])
            duration(2 * SECOND)
        }

        // Sr + O -> SrO
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Strontium)
            fluidInputs(Oxygen.getFluid(1000))
            output(dust, StrontiumOxide, 2)
            EUt(VA[LV])
            duration(2 * SECOND)
        }

        // endregion
    }

    private fun carbonMonoxideProcessing()
    {
        // Remove vanilla C + O -> CO recipes in Chemical Reactor.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Carbon),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(Oxygen.getFluid(1000)))

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(COAL.stack(),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(Oxygen.getFluid(1000)))

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(COAL.stack(1),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(Oxygen.getFluid(1000)))

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Coal),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(Oxygen.getFluid(1000)))

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Charcoal),
                    IntCircuitIngredient.getIntegratedCircuit(1)),
            arrayOf(Oxygen.getFluid(1000)))

        // C + O -> CO
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Carbon, 1)
            fluidInputs(Oxygen.getFluid(1000))
            fluidOutputs(CarbonMonoxide.getFluid(1000))
            EUt(VA[ULV])
            duration(2 * SECOND)
        }

        // Vanilla Coals recipes for C + O -> CO, will output some Ash,
        // in these recipes, chances of Ash output will smaller (just a little)
        // than vanilla GregTech (11.11% -> 10%).
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            inputs(COAL, 1, 0)
            chancedOutput(dust, Ash, 1000, 0)
            fluidInputs(Oxygen.getFluid(1000))
            fluidOutputs(CarbonMonoxide.getFluid(1000))
            EUt(VA[ULV])
            duration(4 * SECOND)
        }

        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            inputs(COAL, 1, 1)
            chancedOutput(dust, Ash, 1000, 0)
            fluidInputs(Oxygen.getFluid(1000))
            fluidOutputs(CarbonMonoxide.getFluid(1000))
            EUt(VA[ULV])
            duration(4 * SECOND)
        }

        // Add compatibility of lignite to these recipes.
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(gem, Lignite)
            chancedOutput(dust, Ash, 1000, 0)
            fluidInputs(Oxygen.getFluid(1000))
            fluidOutputs(CarbonMonoxide.getFluid(1000))
            EUt(VA[ULV])
            duration(4 * SECOND)
        }

        // Dust form of two vanilla coals and Lignite.
        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Coal)
            chancedOutput(dust, Ash, 1000, 0)
            fluidInputs(Oxygen.getFluid(1000))
            fluidOutputs(CarbonMonoxide.getFluid(1000))
            EUt(VA[ULV])
            duration(4 * SECOND)
        }

        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Charcoal)
            chancedOutput(dust, Ash, 1000, 0)
            fluidInputs(Oxygen.getFluid(1000))
            fluidOutputs(CarbonMonoxide.getFluid(1000))
            EUt(VA[ULV])
            duration(4 * SECOND)
        }

        ROASTER_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Lignite)
            chancedOutput(dust, Ash, 1000, 0)
            fluidInputs(Oxygen.getFluid(1000))
            fluidOutputs(CarbonMonoxide.getFluid(1000))
            EUt(VA[ULV])
            duration(4 * SECOND)
        }
    }

    private fun carbonDioxideProcessing()
    {
        // Remove vanilla C + 2O -> CO2 recipes in Chemical Reactor.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Carbon, 1),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Oxygen.getFluid(2000)))

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(COAL.stack(),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Oxygen.getFluid(2000)))

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(COAL.stack(1),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Oxygen.getFluid(2000)))

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Coal),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Oxygen.getFluid(2000)))

        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Charcoal),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Oxygen.getFluid(2000)))

        // C + 2O -> CO2
        ROASTER_RECIPES.addRecipe {
            circuitMeta(2)
            input(dust, Carbon)
            fluidInputs(Oxygen.getFluid(2000))
            fluidOutputs(CarbonDioxide.getFluid(1000))
            EUt(VA[ULV])
            duration(2 * SECOND)
        }

        // Vanilla Coals recipes for C + 2O -> CO2, will output some Ash,
        // in these recipes, chances of Ash output will smaller (just a little)
        // than vanilla GregTech (11.11% -> 10%).
        ROASTER_RECIPES.addRecipe {
            circuitMeta(2)
            inputs(COAL.stack())
            chancedOutput(dust, Ash, 1000, 0)
            fluidInputs(Oxygen.getFluid(2000))
            fluidOutputs(CarbonDioxide.getFluid(1000))
            EUt(VA[ULV])
            duration(4 * SECOND)
        }

        ROASTER_RECIPES.addRecipe {
            circuitMeta(2)
            inputs(COAL.stack(1))
            chancedOutput(dust, Ash, 1000, 0)
            fluidInputs(Oxygen.getFluid(2000))
            fluidOutputs(CarbonDioxide.getFluid(1000))
            EUt(VA[ULV])
            duration(4 * SECOND)
        }

        // Add compatibility of lignite to these recipes.
        ROASTER_RECIPES.addRecipe {
            circuitMeta(2)
            input(gem, Lignite)
            chancedOutput(dust, Ash, 1000, 0)
            fluidInputs(Oxygen.getFluid(2000))
            fluidOutputs(CarbonDioxide.getFluid(1000))
            EUt(VA[ULV])
            duration(4 * SECOND)
        }

        // Dust form of two vanilla coals and Lignite.
        ROASTER_RECIPES.addRecipe {
            circuitMeta(2)
            input(dust, Coal)
            chancedOutput(dust, Ash, 1000, 0)
            fluidInputs(Oxygen.getFluid(2000))
            fluidOutputs(CarbonDioxide.getFluid(1000))
            EUt(VA[ULV])
            duration(4 * SECOND)
        }

        ROASTER_RECIPES.addRecipe {
            circuitMeta(2)
            input(dust, Charcoal)
            chancedOutput(dust, Ash, 1000, 0)
            fluidInputs(Oxygen.getFluid(2000))
            fluidOutputs(CarbonDioxide.getFluid(1000))
            EUt(VA[ULV])
            duration(4 * SECOND)
        }

        ROASTER_RECIPES.addRecipe {
            circuitMeta(2)
            input(dust, Lignite)
            chancedOutput(dust, Ash, 1000, 0)
            fluidInputs(Oxygen.getFluid(2000))
            fluidOutputs(CarbonDioxide.getFluid(1000))
            EUt(VA[ULV])
            duration(4 * SECOND)
        }

        // Delete vanilla CO2 + C -> CO recipe.
        GTLiteRecipeHandler.removeChemicalRecipes(
            arrayOf(OreDictUnifier.get(dust, Carbon)),
            arrayOf(CarbonDioxide.getFluid(1000)))

        CHEMICAL_RECIPES.addRecipe {
            circuitMeta(1)
            input(dust, Carbon)
            fluidInputs(CarbonDioxide.getFluid(1000))
            fluidOutputs(CarbonMonoxide.getFluid(2000))
            EUt(VA[ULV])
            duration(40 * SECOND)
        }
    }

    // @formatter:on

}