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

    }

}