package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.unification.ore.OrePrefix.plate
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.STELLAR_FORGE_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.DegenerateRhenium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Gluons
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HeavyQuarks
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LightQuarks
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.QuarkGluonPlasma
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.LEPTONIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.QUANTUM_CHROMODYNAMIC_CHARGE
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks.Companion.TARANIUM_CHARGE
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

        }

    }

}