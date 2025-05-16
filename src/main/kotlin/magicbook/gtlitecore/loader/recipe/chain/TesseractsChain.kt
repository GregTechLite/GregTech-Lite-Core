package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.recipes.RecipeMaps.MACERATOR_RECIPES
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stick
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.STELLAR_FORGE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Abyssalloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Antimatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ArceusAlloy2B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GelidCryotheum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyK243
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeliumNeon
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Hypogen
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IncoloyMA813
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pikyonium64B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PrimordialMatter
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ResonantStrangeMeson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SpaceTime
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SpatiallyEnlargedFluid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TachyonRichTemporalFluid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tairitsium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumHafniumSeaborgiumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitanSteel
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TranscendentMetal
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Zeron100
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.LEPTONIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.QUANTUM_CHROMODYNAMIC_CHARGE
import magicbook.gtlitecore.common.block.blocks.BlockManipulator
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ENERGISED_TESSERACT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.QUANTUM_ANOMALY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.RAW_TESSERACT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.TIMEPIECE
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class TesseractsChain
{

    companion object
    {

        fun init()
        {
            tesseractProcess()
            transcendentMetalProcess()
            spaceTimeProcess()
            primordialMatterProcess()
        }

        private fun tesseractProcess()
        {
            // Advanced recipes for Quantum Anomaly.
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(QUANTUM_ANOMALY)
                .inputs(ItemStack(LEPTONIC_CHARGE))
                .fluidInputs(Duranium.getFluid(L))
                .fluidInputs(ResonantStrangeMeson.getFluid(250))
                .output(QUANTUM_ANOMALY, 4)
                .EUt(VA[UIV].toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()

            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(QUANTUM_ANOMALY)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(Tritanium.getFluid(L))
                .fluidInputs(ResonantStrangeMeson.getFluid(500))
                .output(QUANTUM_ANOMALY, 16)
                .EUt(VA[UXV].toLong())
                .duration(20 * SECOND)
                .buildAndRegister()

            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(QUANTUM_ANOMALY)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(Taranium.getFluid(L))
                .fluidInputs(ResonantStrangeMeson.getFluid(1000))
                .output(QUANTUM_ANOMALY, 64)
                .EUt(VA[OpV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Raw Tesseract
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(stick, CosmicNeutronium, 8)
                .input(stick, TantalumHafniumSeaborgiumCarbide, 8)
                .input(stick, Tairitsium, 8)
                .input(stick, ArceusAlloy2B, 8)
                .input(plate, Abyssalloy, 24)
                .input(screw, HastelloyK243, 16)
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .output(RAW_TESSERACT, 4)
                .EUt(VA[UXV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(stick, CosmicNeutronium, 12)
                .input(stick, Tairitsium, 12)
                .input(stick, TranscendentMetal, 8)
                .input(plate, HastelloyK243, 24)
                .input(screw, IncoloyMA813, 16)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .output(RAW_TESSERACT, 8)
                .EUt(VA[OpV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(stick, TranscendentMetal, 32)
                .input(plate, Pikyonium64B, 24)
                .input(screw, Zeron100, 16)
                .input(QUANTUM_ANOMALY)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .output(RAW_TESSERACT, 16)
                .EUt(VA[MAX].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Energised Tesseract
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(QUANTUM_ANOMALY)
                .input(RAW_TESSERACT)
                .output(ENERGISED_TESSERACT)
                .EUt(VA[UXV].toLong())
                .duration(30 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }

        private fun transcendentMetalProcess()
        {
            // dustTranscendentMetal
            MACERATOR_RECIPES.recipeBuilder()
                .input(RAW_TESSERACT)
                .output(dust, TranscendentMetal, 8)
                .EUt(VA[UXV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // ingotHotTranscendentMetal
            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                OreDictUnifier.get(dust, TranscendentMetal),
                IntCircuitIngredient.getIntegratedCircuit(1))

            GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
                arrayOf(OreDictUnifier.get(dust, TranscendentMetal),
                    IntCircuitIngredient.getIntegratedCircuit(2)),
                arrayOf(Krypton.getFluid(10)))

            BLAST_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, TranscendentMetal)
                .fluidInputs(Technetium.getPlasma(L * 4))
                .output(ingotHot, TranscendentMetal)
                .fluidOutputs(Technetium.getFluid(L * 4))
                .EUt(VA[UXV].toLong())
                .duration(3 * MINUTE)
                .blastFurnaceTemp(16101)
                .buildAndRegister()

            // ingotTranscendentMetal
            GTRecipeHandler.removeRecipesByInputs(VACUUM_RECIPES,
                arrayOf(OreDictUnifier.get(ingotHot, TranscendentMetal)),
                arrayOf(Helium.getFluid(FluidStorageKeys.LIQUID, 500)))

            VACUUM_RECIPES.recipeBuilder()
                .input(ingotHot, TranscendentMetal)
                .fluidInputs(TitanSteel.getFluid(L))
                .fluidInputs(GelidCryotheum.getFluid(1000))
                .output(ingot, TranscendentMetal)
                .EUt(VA[UIV].toLong())
                .duration(1 * SECOND)
                .buildAndRegister()

        }

        private fun spaceTimeProcess()
        {
            // Space Time
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(ENERGISED_TESSERACT)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(Hypogen.getFluid(L * 8))
                .fluidInputs(Infinity.getFluid(L * 16))
                .fluidOutputs(SpaceTime.getFluid(L))
                .EUt(VA[UXV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .input(ENERGISED_TESSERACT)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(Infinity.getFluid(L * 8))
                .fluidOutputs(SpaceTime.getFluid(L * 4))
                .EUt(VA[OpV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .input(ENERGISED_TESSERACT, 64)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(Infinity.getFluid(L * 8 * 64))
                .fluidOutputs(SpaceTime.getFluid(L * 4 * 64))
                .EUt(VA[MAX].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }

        private fun primordialMatterProcess()
        {
            // Spatially Enlarged Fluid/Tachyon Rich Temporal Fluid
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .input(RAW_TESSERACT)
                .input(QUANTUM_ANOMALY)
                .fluidInputs(SpaceTime.getFluid(L * 20))
                .fluidOutputs(SpatiallyEnlargedFluid.getFluid(L * 10))
                .fluidOutputs(TachyonRichTemporalFluid.getFluid(L * 10))
                .EUt(VA[UXV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Spatially Enlarged Fluid/Tachyon Rich Temporal Fluid converting.
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .notConsumable(GTLiteMetaBlocks.MANIPULATOR.getItemVariant(BlockManipulator.ManipulatorType.SPACETIME_CONTINUUM_RIPPER))
                .input(ENERGISED_TESSERACT, 2)
                .input(TIMEPIECE, 16)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 4096))
                .fluidInputs(SpaceTime.getFluid(L * 1024))
                .output(RAW_TESSERACT)
                .fluidOutputs(TachyonRichTemporalFluid.getFluid(L * 4096))
                .EUt(VA[MAX].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            STELLAR_FORGE_RECIPES.recipeBuilder()
                .notConsumable(GTLiteMetaBlocks.MANIPULATOR.getItemVariant(BlockManipulator.ManipulatorType.SPACETIME_CONTINUUM_RIPPER))
                .input(ENERGISED_TESSERACT, 2)
                .input(QUANTUM_ANOMALY, 16)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 4096))
                .fluidInputs(SpaceTime.getFluid(L * 1024))
                .output(RAW_TESSERACT)
                .fluidOutputs(SpatiallyEnlargedFluid.getFluid(L * 4096))
                .EUt(VA[MAX].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Primordial Matter
            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(SpatiallyEnlargedFluid.getFluid(1000))
                .fluidInputs(TachyonRichTemporalFluid.getFluid(1000))
                .fluidInputs(Antimatter.getFluid(1000))
                .fluidInputs(ResonantStrangeMeson.getFluid(1000))
                .fluidOutputs(PrimordialMatter.getFluid(1000))
                .EUt(VA[UXV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(SpatiallyEnlargedFluid.getFluid(1000 * 64))
                .fluidInputs(TachyonRichTemporalFluid.getFluid(1000 * 64))
                .fluidInputs(Antimatter.getFluid(1000 * 64))
                .fluidInputs(ResonantStrangeMeson.getFluid(1000 * 64))
                .fluidOutputs(PrimordialMatter.getFluid(1000 * 64))
                .EUt(VA[OpV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            STELLAR_FORGE_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
                .fluidInputs(SpatiallyEnlargedFluid.getFluid(1000 * 256))
                .fluidInputs(TachyonRichTemporalFluid.getFluid(1000 * 256))
                .fluidInputs(Antimatter.getFluid(1000 * 256))
                .fluidInputs(ResonantStrangeMeson.getFluid(1000 * 256))
                .fluidOutputs(PrimordialMatter.getFluid(1000 * 256))
                .EUt(VA[MAX].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

        }

    }

}