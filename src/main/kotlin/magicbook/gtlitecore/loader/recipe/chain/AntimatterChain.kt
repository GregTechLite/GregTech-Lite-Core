package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.COMPRESSOR_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.Bismuth
import gregtech.api.unification.material.Materials.Boron
import gregtech.api.unification.material.Materials.Calcium
import gregtech.api.unification.material.Materials.Fermium
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Neptunium
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Niobium
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Radon
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Thorium
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Zinc
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ANTIMATTER_FORGE_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ANTIMATTER_GENERATOR_FUELS
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Antimatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HighEnergyQuarkGluonPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Protomatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuarkGluonPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SemistableAntimatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Shirabon
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

            // Protomatter -> Semistable Antimatter
            ANTIMATTER_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Protomatter.getFluid(1000)) // 100 : 1
                .fluidInputs(Helium.getPlasma(1000))
                .fluidInputs(Iron.getPlasma(1000))
                .fluidInputs(Calcium.getPlasma(1000))
                .fluidInputs(Niobium.getPlasma(1000)) // 1
                .fluidOutputs(SemistableAntimatter.getFluid(10))
                .EUt(145_149_830) // OpV
                .duration(5 * SECOND)
                .buildAndRegister()

            ANTIMATTER_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(Protomatter.getFluid(10000)) // 10 : 1
                .fluidInputs(Helium.getPlasma(1000))
                .fluidInputs(Iron.getPlasma(1000))
                .fluidInputs(Calcium.getPlasma(1000))
                .fluidInputs(Niobium.getPlasma(1000)) // 1
                .fluidInputs(Radon.getPlasma(1000))
                .fluidInputs(Nickel.getPlasma(1000))
                .fluidInputs(Boron.getPlasma(1000))
                .fluidInputs(Sulfur.getPlasma(1000)) // 2
                .fluidOutputs(SemistableAntimatter.getFluid(1000))
                .EUt(667_684_600) // MAX
                .duration(5 * SECOND)
                .buildAndRegister()

            ANTIMATTER_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .fluidInputs(Protomatter.getFluid(100_000)) // 1 : 1
                .fluidInputs(Helium.getPlasma(1000))
                .fluidInputs(Iron.getPlasma(1000))
                .fluidInputs(Calcium.getPlasma(1000))
                .fluidInputs(Niobium.getPlasma(1000)) // 1
                .fluidInputs(Radon.getPlasma(1000))
                .fluidInputs(Nickel.getPlasma(1000))
                .fluidInputs(Boron.getPlasma(1000))
                .fluidInputs(Sulfur.getPlasma(1000)) // 2
                .fluidInputs(Nitrogen.getPlasma(1000))
                .fluidInputs(Zinc.getPlasma(1000))
                .fluidInputs(Silver.getPlasma(1000))
                .fluidInputs(Titanium.getPlasma(1000)) // 3
                .fluidOutputs(SemistableAntimatter.getFluid(100_000))
                .EUt(2_693_264_510) // MAX+
                .duration(5 * SECOND)
                .buildAndRegister()

            ANTIMATTER_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .fluidInputs(Protomatter.getFluid(1_000_000)) // 1 : 10
                .fluidInputs(Helium.getPlasma(1000))
                .fluidInputs(Iron.getPlasma(1000))
                .fluidInputs(Calcium.getPlasma(1000))
                .fluidInputs(Niobium.getPlasma(1000)) // 1
                .fluidInputs(Radon.getPlasma(1000))
                .fluidInputs(Nickel.getPlasma(1000))
                .fluidInputs(Boron.getPlasma(1000))
                .fluidInputs(Sulfur.getPlasma(1000)) // 2
                .fluidInputs(Nitrogen.getPlasma(1000))
                .fluidInputs(Zinc.getPlasma(1000))
                .fluidInputs(Silver.getPlasma(1000))
                .fluidInputs(Titanium.getPlasma(1000)) // 3
                .fluidInputs(Americium.getPlasma(1000))
                .fluidInputs(Bismuth.getPlasma(1000))
                .fluidInputs(Oxygen.getPlasma(1000))
                .fluidInputs(Tin.getPlasma(1000)) // 4
                .fluidOutputs(SemistableAntimatter.getFluid(10_000_000))
                .EUt(10_730_073_930) // MAX+
                .duration(5 * SECOND)
                .buildAndRegister()

            ANTIMATTER_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .fluidInputs(Protomatter.getFluid(10_000_000)) // 1 : 100
                .fluidInputs(Helium.getPlasma(1000))
                .fluidInputs(Iron.getPlasma(1000))
                .fluidInputs(Calcium.getPlasma(1000))
                .fluidInputs(Niobium.getPlasma(1000)) // 1
                .fluidInputs(Radon.getPlasma(1000))
                .fluidInputs(Nickel.getPlasma(1000))
                .fluidInputs(Boron.getPlasma(1000))
                .fluidInputs(Sulfur.getPlasma(1000)) // 2
                .fluidInputs(Nitrogen.getPlasma(1000))
                .fluidInputs(Zinc.getPlasma(1000))
                .fluidInputs(Silver.getPlasma(1000))
                .fluidInputs(Titanium.getPlasma(1000)) // 3
                .fluidInputs(Americium.getPlasma(1000))
                .fluidInputs(Bismuth.getPlasma(1000))
                .fluidInputs(Oxygen.getPlasma(1000))
                .fluidInputs(Tin.getPlasma(1000)) // 4
                .fluidInputs(Thorium.getPlasma(1000))
                .fluidInputs(Lead.getPlasma(1000))
                .fluidInputs(Neptunium.getPlasma(1000))
                .fluidInputs(Fermium.getPlasma(1000)) // 5
                .fluidOutputs(SemistableAntimatter.getFluid(1_000_000_000))
                .EUt(42_767_675_200) // MAX+
                .duration(5 * SECOND)
                .buildAndRegister()

            // Semistable Antimatter -> Antimatter
            COMPRESSOR_RECIPES.recipeBuilder()
                .fluidInputs(SemistableAntimatter.getFluid(1000))
                .fluidOutputs(Antimatter.getFluid(100))
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Antimatter annihilation.

            // 1.0
            ANTIMATTER_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Antimatter.getFluid(1))
                .fluidInputs(Lead.getPlasma(1000))
                .EUt(1_000_000_000_000)
                .duration(10 * SECOND)
                .buildAndRegister()

            // 1.5
            ANTIMATTER_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Antimatter.getFluid(1))
                .fluidInputs(Infinity.getFluid(L * 4))
                .EUt(1_500_000_000_000)
                .duration(10 * SECOND)
                .buildAndRegister()

            // 2.0
            ANTIMATTER_GENERATOR_FUELS.recipeBuilder()
                .fluidInputs(Antimatter.getFluid(1))
                .fluidInputs(Shirabon.getFluid(L * 4))
                .EUt(2_000_000_000_000)
                .duration(10 * SECOND)
                .buildAndRegister()

            // 4.0 TODO Raw Star Matter?

        }

    }

}