package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.recipes.RecipeMaps.MACERATOR_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stick
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.STELLAR_FORGE_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Abyssalloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Antimatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ArceusAlloy2B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CubicZirconia
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyK243
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Hypogen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IncoloyMA813
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PrimordialMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ResonantStrangeMeson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpatiallyEnlargedFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TachyonRichTemporalFluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tairitsium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TantalumHafniumSeaborgiumCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Universium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Zeron100
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.LEPTONIC_CHARGE
import gregtechlite.gtlitecore.common.block.GTLiteBlocks.QUANTUM_CHROMODYNAMIC_CHARGE
import gregtechlite.gtlitecore.common.block.variant.Manipulator
import gregtechlite.gtlitecore.common.block.variant.ShieldingCore
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ENERGISED_TESSERACT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.QUANTUM_ANOMALY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RAW_TESSERACT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TIMEPIECE
import net.minecraft.item.ItemStack

internal object TesseractsChain
{

    // @formatter:off

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
            .EUt(VA[UIV])
            .duration(1 * MINUTE)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(QUANTUM_ANOMALY)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(Tritanium.getFluid(L))
            .fluidInputs(ResonantStrangeMeson.getFluid(500))
            .output(QUANTUM_ANOMALY, 16)
            .EUt(VA[UXV])
            .duration(20 * SECOND)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(QUANTUM_ANOMALY)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(Taranium.getFluid(L))
            .fluidInputs(ResonantStrangeMeson.getFluid(1000))
            .output(QUANTUM_ANOMALY, 64)
            .EUt(VA[OpV])
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
            .EUt(VA[UXV])
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
            .EUt(VA[OpV])
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
            .EUt(VA[MAX])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Energised Tesseract
        LASER_ENGRAVER_RECIPES.recipeBuilder()
            .notConsumable(QUANTUM_ANOMALY)
            .input(RAW_TESSERACT)
            .output(ENERGISED_TESSERACT)
            .EUt(VA[UXV])
            .duration(30 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()
    }

    private fun transcendentMetalProcess()
    {
        // Raw Tesseract -> Transcendent Metal dust
        MACERATOR_RECIPES.recipeBuilder()
            .input(RAW_TESSERACT)
            .output(dust, TranscendentMetal, 8)
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Transcendent Metal dust -> Transcendent Metal ingot
        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
            OreDictUnifier.get(dust, TranscendentMetal),
            IntCircuitIngredient.getIntegratedCircuit(1))

        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
            arrayOf(OreDictUnifier.get(dust, TranscendentMetal),
                IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Krypton.getFluid(10)))

        GTRecipeHandler.removeRecipesByInputs(TOPOLOGICAL_ORDER_CHANGING_RECIPES,
            OreDictUnifier.get(dust, TranscendentMetal),
            IntCircuitIngredient.getIntegratedCircuit(1))

        GTRecipeHandler.removeRecipesByInputs(TOPOLOGICAL_ORDER_CHANGING_RECIPES,
            OreDictUnifier.get(dust, TranscendentMetal),
            IntCircuitIngredient.getIntegratedCircuit(2))

        GTRecipeHandler.removeRecipesByInputs(TOPOLOGICAL_ORDER_CHANGING_RECIPES,
            OreDictUnifier.get(ingot, TranscendentMetal),
            IntCircuitIngredient.getIntegratedCircuit(4))

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, TranscendentMetal)
            .fluidInputs(Technetium.getPlasma(L * 4))
            .output(ingot, TranscendentMetal)
            .fluidOutputs(Technetium.getFluid(L * 4))
            .EUt(VA[UXV])
            .duration(1 * MINUTE)
            .blastFurnaceTemp(23500)
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
            .EUt(VA[UXV])
            .duration(40 * SECOND)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(ENERGISED_TESSERACT)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(Infinity.getFluid(L * 8))
            .fluidOutputs(SpaceTime.getFluid(L * 4))
            .EUt(VA[OpV])
            .duration(10 * SECOND)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(ENERGISED_TESSERACT, 64)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(Infinity.getFluid(L * 8 * 64))
            .fluidOutputs(SpaceTime.getFluid(L * 4 * 64))
            .EUt(VA[MAX])
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
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Spatially Enlarged Fluid/Tachyon Rich Temporal Fluid converting.
        STELLAR_FORGE_RECIPES.recipeBuilder()
            .notConsumable(ShieldingCore.SPACETIME_BENDING_CORE.stack)
            .input(plateDense, Taranium)
            .input(gem, CubicZirconia, 32)
            .input(nanite, Universium)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 1024))
            .output(TIMEPIECE, 3)
            .fluidOutputs(SpatiallyEnlargedFluid.getFluid(L * 1024))
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .notConsumable(Manipulator.SPACETIME_CONTINUUM_RIPPER.stack)
            .input(ENERGISED_TESSERACT, 2)
            .input(TIMEPIECE, 16)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(SpatiallyEnlargedFluid.getFluid(L * 4096))
            .fluidInputs(SpaceTime.getFluid(L * 1024))
            .output(RAW_TESSERACT)
            .fluidOutputs(TachyonRichTemporalFluid.getFluid(L * 4096))
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .buildAndRegister()

        STELLAR_FORGE_RECIPES.recipeBuilder()
            .notConsumable(Manipulator.SPACETIME_CONTINUUM_RIPPER.stack)
            .input(ENERGISED_TESSERACT, 2)
            .input(QUANTUM_ANOMALY, 16)
            .inputs(ItemStack(QUANTUM_CHROMODYNAMIC_CHARGE))
            .fluidInputs(TachyonRichTemporalFluid.getFluid(L * 4096))
            .fluidInputs(SpaceTime.getFluid(L * 1024))
            .output(RAW_TESSERACT)
            .fluidOutputs(SpatiallyEnlargedFluid.getFluid(L * 4096))
            .EUt(VA[UXV])
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
            .EUt(VA[UXV])
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
            .EUt(VA[OpV])
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
            .EUt(VA[MAX])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}