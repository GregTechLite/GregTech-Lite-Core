package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Ammonia
import gregtech.api.unification.material.Materials.Barium
import gregtech.api.unification.material.Materials.BariumSulfide
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.CarbonMonoxide
import gregtech.api.unification.material.Materials.CertusQuartz
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.NetherQuartz
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.NitrogenDioxide
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Rhodium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.SiliconDioxide
import gregtech.api.unification.material.Materials.Steam
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Yttrium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingotHot
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BariumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CitricAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CopperNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cuprite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SiliconCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tenorite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YttriumBariumCopperOxidesMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YttriumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.YttriumOxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.api.utils.GTRecipeUtility

@Suppress("MISSING_DEPENDENCY_CLASS")
class AlloysChain
{

    companion object
    {

        fun init()
        {
            siliconCarbideProcess()
            rhodiumPlatedPalladiumProcess()
            yttriumBariumCuprateProcess()
        }

        private fun siliconCarbideProcess()
        {
            // Add a simplified recipes for silicon dust (arc furnace -> dust, EBF -> ingot).
            GTRecipeHandler.removeRecipesByInputs(ELECTROLYZER_RECIPES,
                OreDictUnifier.get(dust, Silicon, 3))

            // SiO2 -> Si + 2O
            ARC_FURNACE_RECIPES.recipeBuilder()
                .circuitMeta(0)
                .input(dust, SiliconDioxide, 3)
                .notConsumable(Air.getFluid(1000))
                .output(dust, Silicon)
                .fluidOutputs(Oxygen.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // Allowed to decompose quartzite, etc.
            ARC_FURNACE_RECIPES.recipeBuilder()
                .circuitMeta(0)
                .input(dust, Quartzite, 3)
                .notConsumable(Air.getFluid(1000))
                .output(dust, Silicon)
                .fluidOutputs(Oxygen.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            ARC_FURNACE_RECIPES.recipeBuilder()
                .circuitMeta(0)
                .input(dust, NetherQuartz, 3)
                .notConsumable(Air.getFluid(1000))
                .output(dust, Silicon)
                .fluidOutputs(Oxygen.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            ARC_FURNACE_RECIPES.recipeBuilder()
                .circuitMeta(0)
                .input(dust, CertusQuartz, 3)
                .notConsumable(Air.getFluid(1000))
                .output(dust, Silicon)
                .fluidOutputs(Oxygen.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(3 * SECOND)
                .buildAndRegister()

            // SiO2 + 2C -> Si + 2CO
            ARC_FURNACE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, SiliconDioxide, 3)
                .input(dust, Carbon, 2)
                .output(dust, Silicon)
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            ARC_FURNACE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Quartzite, 3)
                .input(dust, Carbon, 2)
                .output(dust, Silicon)
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            ARC_FURNACE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, CertusQuartz, 3)
                .input(dust, Carbon, 2)
                .output(dust, Silicon)
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            ARC_FURNACE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, NetherQuartz, 3)
                .input(dust, Carbon, 2)
                .output(dust, Silicon)
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .EUt(VA[LV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // SiO2 + 3C -> SiC + 2CO
            ARC_FURNACE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, SiliconDioxide, 3)
                .input(dust, Carbon, 3)
                .output(ingotHot, SiliconCarbide, 2)
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .EUt(VA[EV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            ARC_FURNACE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, Quartzite, 3)
                .input(dust, Carbon, 3)
                .output(ingotHot, SiliconCarbide, 2)
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .EUt(VA[EV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            ARC_FURNACE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, CertusQuartz, 3)
                .input(dust, Carbon, 3)
                .output(ingotHot, SiliconCarbide, 2)
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .EUt(VA[EV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            ARC_FURNACE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, NetherQuartz, 3)
                .input(dust, Carbon, 3)
                .output(ingotHot, SiliconCarbide, 2)
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .EUt(VA[EV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

        }

        private fun rhodiumPlatedPalladiumProcess()
        {
            // Change rhodium plated palladium smelting recipes to arc furnace but not EBF,
            // player can get liquid by common mixer and ABS.
            GTRecipeUtility.removeMixerRecipes(
                arrayOf(OreDictUnifier.get(dust, Palladium, 3),
                    OreDictUnifier.get(dust, Rhodium),
                    IntCircuitIngredient.getIntegratedCircuit(1)))

            // 3Pd + Rh -> Pd3Rh
            ARC_FURNACE_RECIPES.recipeBuilder()
                .input(dust, Palladium, 3)
                .fluidInputs(Rhodium.getFluid(L))
                .fluidInputs(Oxygen.getFluid(600))
                .output(ingotHot, RhodiumPlatedPalladium, 4)
                .EUt(VA[IV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

        }

        private fun yttriumBariumCuprateProcess()
        {
            // Remove original YBCO dust recipe.
            GTRecipeUtility.removeMixerRecipes(
                arrayOf(OreDictUnifier.get(dust, Yttrium),
                    OreDictUnifier.get(dust, Barium, 2),
                    OreDictUnifier.get(dust, Copper, 3),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Oxygen.getFluid(7000)))

            // Add another choice of Y2O3, do not push player used REE distillation.
            // 2Y + 3O -> Y2O3
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, Yttrium, 2)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, YttriumOxide, 5)
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Y2O3 + 6HNO3 -> 2Y(NO3)3 + 3H2O
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(dust, YttriumOxide, 5)
                .fluidInputs(NitricAcid.getFluid(6000))
                .output(dust, YttriumNitrate, 26)
                .fluidOutputs(Water.getFluid(3000))
                .EUt(VA[EV].toLong())
                .duration(12 * SECOND)
                .buildAndRegister()

            // Add another recipe of BaS because it is the semi-products of Naquadah Processing.
            // Ba + S -> BaS
            ROASTER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, Barium)
                .input(dust, Sulfur)
                .output(dust, BariumSulfide, 2)
                .EUt(VA[LV].toLong())
                .duration(4 * SECOND)
                .buildAndRegister()

            // BaS + 2HNO3 -> Ba(NO3)2 + H2S
            CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, BariumSulfide, 2)
                .fluidInputs(NitricAcid.getFluid(2000))
                .output(dust, BariumNitrate, 9)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Cu + 2HNO3 -> Cu(NO3)2 + 2H
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Copper)
                .fluidInputs(NitricAcid.getFluid(2000))
                .output(dust, CopperNitrate, 9)
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(VA[MV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // CuO + 2HNO3 -> Cu(NO3)2 + H2O
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Tenorite, 2)
                .fluidInputs(NitricAcid.getFluid(2000))
                .output(dust, CopperNitrate, 9)
                .fluidOutputs(Steam.getFluid(1000))
                .EUt(VA[MV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // Cu2O + 4HNO3 -> 2Cu(NO3)2 + H2O + 2H (drop)
            BURNER_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Cuprite, 3)
                .fluidInputs(NitricAcid.getFluid(4000))
                .output(dust, CopperNitrate, 18)
                .EUt(VA[MV].toLong())
                .duration(8 * SECOND)
                .buildAndRegister()

            // Y(NO3)3 + 2Ba(NO3)2 + 3Cu(NO3)2 + 2NH3 + C6H8O7 -> YBa2Cu3O6 + 15NO2 + 6CO + 4H2O + 6H
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, YttriumNitrate, 13)
                .input(dust, BariumNitrate, 18)
                .input(dust, CopperNitrate, 27)
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(CitricAcid.getFluid(1000))
                .output(dust, YttriumBariumCopperOxidesMixture, 12)
                .fluidOutputs(NitrogenDioxide.getFluid(15000))
                .fluidOutputs(CarbonMonoxide.getFluid(6000))
                .fluidOutputs(Water.getFluid(4000))
                .fluidOutputs(Hydrogen.getFluid(6000))
                .EUt(VA[IV].toLong())
                .duration(12 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // YBa2Cu3O6 + O -> YBa2Cu3O7
            ARC_FURNACE_RECIPES.recipeBuilder()
                .input(dust, YttriumBariumCopperOxidesMixture, 12)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(ingotHot, YttriumBariumCuprate, 13)
                .EUt(VA[IV].toLong())
                .duration(2 * MINUTE + 5 * SECOND)
                .buildAndRegister()

        }

    }

}