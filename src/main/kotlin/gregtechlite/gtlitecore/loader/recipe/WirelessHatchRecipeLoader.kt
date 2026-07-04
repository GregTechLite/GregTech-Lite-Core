package gregtechlite.gtlitecore.loader.recipe

import gregtech.api.GTValues.*
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.*
import gregtech.api.unification.ore.OrePrefix.*
import gregtech.loaders.recipe.CraftingComponent
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_INPUT_HATCH
import gregtech.common.metatileentities.MetaTileEntities.ENERGY_OUTPUT_HATCH
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.WIRELESS_DYNAMO_HATCH
import gregtechlite.gtlitecore.common.metatileentity.GTLiteMetaTileEntities.WIRELESS_ENERGY_HATCH
import net.minecraft.item.ItemStack

internal object WirelessHatchRecipeLoader
{

    // @formatter:off

    fun init()
    {
        // Wireless Energy Hatch recipes (IV-OpV tiers)
        // Base recipe: Energy Hatch + Field Generator + Circuit + Antenna components
        for (tier in 0..8)
        {
            val voltageTier = tier + IV
            val tierCircuit = when (voltageTier) {
                IV -> Tier.IV
                LuV -> Tier.LuV
                ZPM -> Tier.ZPM
                UV -> Tier.UV
                UHV -> Tier.UHV
                UEV -> Tier.UEV
                UIV -> Tier.UIV
                UXV -> Tier.UXV
                OpV -> Tier.OpV
                else -> Tier.IV
            }
            val antennaMaterial = when (tier) {
                0 -> Aluminium
                1 -> StainlessSteel
                2 -> Titanium
                3 -> TungstenSteel
                4 -> RhodiumPlatedPalladium
                5 -> NaquadahAlloy
                6 -> Darmstadtium
                7 -> Neutronium
                8 -> Vibranium
                else -> Aluminium
            }

            // Wireless Energy Hatch (Input)
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(ENERGY_INPUT_HATCH[voltageTier])
                .input(plate, antennaMaterial, 2)
                .input(circuit, tierCircuit, 2)
                .inputs(CraftingComponent.FIELD_GENERATOR.getIngredient(voltageTier) as ItemStack)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .output(WIRELESS_ENERGY_HATCH[tier])
                .EUt(VA[voltageTier].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Wireless Dynamo Hatch (Output)
            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(ENERGY_OUTPUT_HATCH[voltageTier])
                .input(plate, antennaMaterial, 2)
                .input(circuit, tierCircuit, 2)
                .inputs(CraftingComponent.FIELD_GENERATOR.getIngredient(voltageTier) as ItemStack)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .output(WIRELESS_DYNAMO_HATCH[tier])
                .EUt(VA[voltageTier].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()
        }
    }

    // @formatter:on

}