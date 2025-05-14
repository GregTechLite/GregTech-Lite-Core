package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.recipes.RecipeMaps.MACERATOR_RECIPES
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stick
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.STELLAR_FORGE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Abyssalloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ArceusAlloy2B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyK243
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IncoloyMA813
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Pikyonium64B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ResonantStrangeMeson
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Tairitsium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumHafniumSeaborgiumCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TranscendentMetal
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Zeron100
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.LEPTONIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.QUANTUM_CHROMODYNAMIC_CHARGE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ENERGISED_TESSERACT
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.QUANTUM_ANOMALY
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.RAW_TESSERACT
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
            MACERATOR_RECIPES.recipeBuilder()
                .input(RAW_TESSERACT)
                .output(dust, TranscendentMetal, 8)
                .EUt(VA[UXV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()
        }

    }

}