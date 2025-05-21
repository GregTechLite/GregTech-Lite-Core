package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Nihonium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.ELITE_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.ENERGY_CLUSTER
import gregtech.common.items.MetaItems.ENERGY_LAPOTRONIC_ORB_CLUSTER
import gregtech.common.items.MetaItems.ENERGY_MODULE
import gregtech.common.items.MetaItems.ENGRAVED_LAPOTRON_CHIP
import gregtech.common.items.MetaItems.ULTIMATE_BATTERY
import gregtech.common.items.MetaItems.ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT
import gregtech.common.items.MetaItems.WETWARE_CIRCUIT_BOARD
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SPACE_ASSEMBLER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Adamantium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ArceusAlloy2B
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.MutatedLivingSolder
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PreciousMetalAlloy
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Taranium
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.FEMTO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.NANO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PERFECT_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PICO_PIC_CHIP
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ULTIMATE_CIRCUIT_BOARD

@Suppress("MISSING_DEPENDENCY_CLASS")
class SpaceAssemblerRecipes
{

    companion object
    {

        fun init()
        {
            // Advanced recipes for Lapotronic Energy Cluster.
            SPACE_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD)
                .input(plate, Rutherfordium, 4)
                .input(circuit, MarkerMaterials.Tier.LuV, 2)
                .input(ENGRAVED_LAPOTRON_CHIP, 32)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 16)
                .input(wireFine, Ruridit, 64)
                .input(bolt, NaquadahEnriched, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 5))
                .output(ENERGY_LAPOTRONIC_ORB_CLUSTER, 4)
                .EUt(80_000) // ZPM
                .duration(10 * SECOND)
                .tier(1)
                .buildAndRegister()

            // Advanced recipes for Energy Module.
            SPACE_ASSEMBLER_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD)
                .input(plate, Dubnium, 4)
                .input(circuit, MarkerMaterials.Tier.ZPM, 2)
                .input(ENGRAVED_LAPOTRON_CHIP, 64)
                .input(NANO_PIC_CHIP, 16)
                .input(wireFine, Osmiridium, 64)
                .input(bolt, Naquadria, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 10))
                .output(ENERGY_MODULE, 4)
                .EUt(100_000) // ZPM
                .duration(10 * SECOND)
                .tier(1)
                .buildAndRegister()

            // Advanced recipes for Energy Cluster.
            SPACE_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ULTIMATE_CIRCUIT_BOARD)
                .input(plate, Bohrium, 4)
                .input(circuit, MarkerMaterials.Tier.UV, 2)
                .input(ENGRAVED_LAPOTRON_CHIP, 64)
                .input(ENGRAVED_LAPOTRON_CHIP, 64)
                .input(PICO_PIC_CHIP, 16)
                .input(wireFine, PreciousMetalAlloy, 64)
                .input(bolt, Taranium, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 20))
                .output(ENERGY_CLUSTER, 4)
                .EUt(200_000) // UV
                .duration(10 * SECOND)
                .tier(2)
                .buildAndRegister()

            // Advanced recipes for Ultimate Battery.
            SPACE_ASSEMBLER_RECIPES.recipeBuilder()
                .input(PERFECT_CIRCUIT_BOARD)
                .input(plate, Nihonium, 4)
                .input(circuit, MarkerMaterials.Tier.UHV, 2)
                .input(ENGRAVED_LAPOTRON_CHIP, 64)
                .input(ENGRAVED_LAPOTRON_CHIP, 64)
                .input(ENGRAVED_LAPOTRON_CHIP, 64)
                .input(ENGRAVED_LAPOTRON_CHIP, 64)
                .input(FEMTO_PIC_CHIP, 16)
                .input(wireFine, ArceusAlloy2B, 64)
                .input(bolt, CosmicNeutronium, 8)
                .fluidInputs(MutatedLivingSolder.getFluid(L * 40))
                .output(ULTIMATE_BATTERY, 4)
                .EUt(800_000) // UHV
                .duration(10 * SECOND)
                .tier(3)
                .buildAndRegister()

        }

    }

}