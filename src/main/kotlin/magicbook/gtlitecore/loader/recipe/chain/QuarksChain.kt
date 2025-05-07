package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKey
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.FUSION_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.unification.material.Materials.Deuterium
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.nugget
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.common.items.MetaItems.SHAPE_MOLD_BLOCK
import gregtech.common.items.MetaItems.SHAPE_MOLD_GEAR
import gregtech.common.items.MetaItems.SHAPE_MOLD_GEAR_SMALL
import gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT
import gregtech.common.items.MetaItems.SHAPE_MOLD_NUGGET
import gregtech.common.items.MetaItems.SHAPE_MOLD_PLATE
import gregtech.common.items.MetaItems.SHAPE_MOLD_ROTOR
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.LARGE_MIXER_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.STELLAR_FORGE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DegenerateRhenium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DeuteriumSuperheavyMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Gluons
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarkDegenerateMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarkEnrichedMixture
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarks
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HighEnergyQuarkGluonPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightQuarks
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableFlerovium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableHassium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MetastableOganesson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuantumchromodynamicallyConfinedMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuarkGluonPlasma
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.LEPTONIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.QUANTUM_CHROMODYNAMIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.TARANIUM_CHARGE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_BOLT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_RING
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_ROD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_ROD_LONG
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.SHAPE_MOLD_SCREW
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class QuarksChain
{

    companion object
    {

        fun init()
        {

            // Tier 1: 144L Degenerate Rhenium -> 1000L Quark-Gluon Plasma
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(plate, DegenerateRhenium)
                .inputs(ItemStack(TARANIUM_CHARGE))
                .fluidOutputs(QuarkGluonPlasma.getFluid(1000))
                .EUt(VA[UHV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Tier 2: 144L * 64 Degenerate Rhenium -> 1000L * 64 Quark-Gluon Plasma
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(plate, DegenerateRhenium, 64)
                .inputs(ItemStack(LEPTONIC_CHARGE))
                .fluidOutputs(QuarkGluonPlasma.getFluid(64000))
                .EUt(VA[UEV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Tier 3: 144L * 64 * 4 Degenerate Rhenium -> 1000L * 64 * 4 Quark-Gluon Plasma
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(plate, DegenerateRhenium, 64)
                .input(plate, DegenerateRhenium, 64)
                .input(plate, DegenerateRhenium, 64)
                .input(plate, DegenerateRhenium, 64)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidOutputs(QuarkGluonPlasma.getFluid(256000))
                .EUt(VA[UIV].toLong())
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()

            // Quark-Gluon Plasma -> Heavy Quarks, Light Quarks, Gluons
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(QuarkGluonPlasma.getFluid(5000))
                .fluidOutputs(HeavyQuarks.getFluid(3750))
                .fluidOutputs(LightQuarks.getFluid(2500))
                .fluidOutputs(Gluons.getFluid(1250))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Heavy Quarks + Light Quarks -> Heavy Quark Enriched Mixture
            MIXER_RECIPES.recipeBuilder() // 3:1
                .fluidInputs(HeavyQuarks.getFluid(750))
                .fluidInputs(LightQuarks.getFluid(250))
                .fluidOutputs(HeavyQuarkEnrichedMixture.getFluid(1000))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Deuterium + Hs + Fl + Og -> Deuterium-Superheavy Mixture
            LARGE_MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Deuterium.getFluid(2000))
                .fluidInputs(MetastableHassium.getFluid(L))
                .fluidInputs(MetastableFlerovium.getFluid(L))
                .fluidInputs(MetastableOganesson.getFluid(L))
                .fluidOutputs(DeuteriumSuperheavyMixture.getFluid(L * 18))
                .EUt(VA[UHV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Heavy Quark Enriched Mixture + Deuterium-Superheavy Mixture -> Heavy Quark Degenerate Matter (HQDM)
            FUSION_RECIPES.recipeBuilder()
                .fluidInputs(HeavyQuarkEnrichedMixture.getFluid(500))
                .fluidInputs(DeuteriumSuperheavyMixture.getFluid(L * 6))
                .fluidOutputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .EUt(VA[UEV].toLong())
                .duration(8 * SECOND)
                .EUToStart(1_600_000_000L) // 1600M EU, MK5
                .buildAndRegister()

            // HQDM (plasma) -> HQDM (liquid)
            VACUUM_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .fluidOutputs(HeavyQuarkDegenerateMatter.getFluid(L))
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(dust, HeavyQuarkDegenerateMatter)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(dustSmall, HeavyQuarkDegenerateMatter, 4)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(dustTiny, HeavyQuarkDegenerateMatter, 9)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            // HQDM (plasma) -> HQDM components
            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_INGOT)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(ingot, HeavyQuarkDegenerateMatter)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_PLATE)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(plate, HeavyQuarkDegenerateMatter)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_ROD)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(stick, HeavyQuarkDegenerateMatter, 2)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_ROD_LONG)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(stickLong, HeavyQuarkDegenerateMatter)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BOLT)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(bolt, HeavyQuarkDegenerateMatter, 8)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_SCREW)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(screw, HeavyQuarkDegenerateMatter, 8)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_RING)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(ring, HeavyQuarkDegenerateMatter, 4)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_NUGGET)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(nugget, HeavyQuarkDegenerateMatter, 9)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_GEAR)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(4000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 4000))
                .output(gear, HeavyQuarkDegenerateMatter)
                .fluidOutputs(Helium.getFluid(2000))
                .EUt(VA[UEV].toLong())
                .duration(80 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_GEAR_SMALL)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(gearSmall, HeavyQuarkDegenerateMatter)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UEV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_ROTOR)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(4000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 4000))
                .output(rotor, HeavyQuarkDegenerateMatter)
                .fluidOutputs(Helium.getFluid(2000))
                .EUt(VA[UEV].toLong())
                .duration(80 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK)
                .fluidInputs(HeavyQuarkDegenerateMatter.getPlasma(9000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 9000))
                .output(block, HeavyQuarkDegenerateMatter)
                .fluidOutputs(Helium.getFluid(4500))
                .EUt(VA[UEV].toLong())
                .duration(180 * SECOND)
                .buildAndRegister()

            // Tier 1: 144L HQDM -> 1000L HighEnergyQuarkGluonPlasma
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(plate, HeavyQuarkDegenerateMatter)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidOutputs(HighEnergyQuarkGluonPlasma.getFluid(1000))
                .EUt(VA[UEV].toLong())
                .duration(30 * SECOND)
                .buildAndRegister()

            // Tier 2: 144L * 64 HQDM -> 64000L HighEnergyQuarkGluonPlasma
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(plate, HeavyQuarkDegenerateMatter, 64)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidOutputs(HighEnergyQuarkGluonPlasma.getFluid(64000))
                .EUt(VA[UIV].toLong())
                .duration(7 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Tier 3: 144L * 9 * 64 HQDM -> 576000L HighEnergyQuarkGluonPlasma
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .input(block, HeavyQuarkDegenerateMatter, 64)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidOutputs(HighEnergyQuarkGluonPlasma.getFluid(576000))
                .EUt(VA[UXV].toLong())
                .duration(2 * SECOND + 5 * TICK)
                .buildAndRegister()

            // High Energy Q-G plasma -> QCM liquid and components
            VACUUM_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(HighEnergyQuarkGluonPlasma.getFluid(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .fluidOutputs(QuantumchromodynamicallyConfinedMatter.getFluid(L))
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UIV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(HighEnergyQuarkGluonPlasma.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(dust, QuantumchromodynamicallyConfinedMatter)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UIV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .fluidInputs(HighEnergyQuarkGluonPlasma.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(dustSmall, QuantumchromodynamicallyConfinedMatter, 4)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UIV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .fluidInputs(HighEnergyQuarkGluonPlasma.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(dustTiny, QuantumchromodynamicallyConfinedMatter, 9)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UIV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_INGOT)
                .fluidInputs(HighEnergyQuarkGluonPlasma.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(ingot, QuantumchromodynamicallyConfinedMatter)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UIV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_PLATE)
                .fluidInputs(HighEnergyQuarkGluonPlasma.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(plate, QuantumchromodynamicallyConfinedMatter)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UIV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_NUGGET)
                .fluidInputs(HighEnergyQuarkGluonPlasma.getPlasma(1000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .output(nugget, QuantumchromodynamicallyConfinedMatter, 9)
                .fluidOutputs(Helium.getFluid(500))
                .EUt(VA[UIV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            VACUUM_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BLOCK)
                .fluidInputs(HighEnergyQuarkGluonPlasma.getPlasma(9000))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 9000))
                .output(block, QuantumchromodynamicallyConfinedMatter)
                .fluidOutputs(Helium.getFluid(4500))
                .EUt(VA[UIV].toLong())
                .duration(360 * SECOND)
                .buildAndRegister()

        }

    }

}