package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.material.Materials.MercuryBariumCalciumCuprate
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.UUMatter
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.pipeLargeFluid
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.items.MetaItems.ELECTRIC_PUMP_UHV
import gregtech.common.items.MetaItems.EMITTER_UHV
import gregtech.common.items.MetaItems.NEUTRON_REFLECTOR
import gregtech.common.items.MetaItems.SENSOR_UHV
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.EnrichedNaquadahAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HDCS
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyX78
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MetastableOganesson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StableBaryonicMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tairitsium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TitanSteel
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VOLTAGE_COIL_UHV

internal object StellarForgeCasingRecipes
{

    // @formatter:off

    fun init()
    {
        // Stellar Containment Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(frameGt, QuantumAlloy, 4)
            .input(frameGt, Tairitsium, 4)
            .input(frameGt, TitanSteel, 4)
            .input(frameGt, EnrichedNaquadahAlloy, 4)
            .input(plateDouble, HDCS, 8)
            .input(plateDouble, Neutronium, 8)
            .input(EMITTER_UHV, 2)
            .input(SENSOR_UHV, 2)
            .input(ELECTRIC_PUMP_UHV, 2)
            .input(NEUTRON_REFLECTOR, 4)
            .input(screw, TitanSteel, 6)
            .fluidInputs(SolderingAlloy.getFluid(L * 32))
            .fluidInputs(UUMatter.getFluid(4000))
            .fluidInputs(Bedrockium.getFluid(L * 16))
            .outputs(MultiblockCasing.STELLAR_CONTAINMENT_CASING.getStack(16))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .stationResearch {
                it.researchStack(MetalCasing.QUANTUM_ALLOY.stack)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Thermal Energy Transmissive Casing
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .inputs(ScienceCasing.HOLLOW_CASING.getStack(4))
            .input(frameGt, Bedrockium, 16)
            .input(plateDouble, MetastableOganesson, 8)
            .input(pipeLargeFluid, HastelloyX78, 2)
            .input(gear, CosmicNeutronium, 3)
            .input(gearSmall, CosmicNeutronium, 6)
            .input(ELECTRIC_PUMP_UHV)
            .input(VOLTAGE_COIL_UHV, 8)
            .input(foil, Tairitsium, 12)
            .input(bolt, HDCS, 4)
            .fluidInputs(SolderingAlloy.getFluid(L * 40))
            .fluidInputs(MercuryBariumCalciumCuprate.getFluid(L * 16))
            .fluidInputs(StableBaryonicMatter.getFluid(4000))
            .outputs(MultiblockCasing.THERMAL_ENERGY_TRANSMISSION_CASING.getStack(32))
            .EUt(VA[UHV])
            .duration(10 * SECOND)
            .stationResearch {
                it.researchStack(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE))
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()
    }

    // @formatter:on

}