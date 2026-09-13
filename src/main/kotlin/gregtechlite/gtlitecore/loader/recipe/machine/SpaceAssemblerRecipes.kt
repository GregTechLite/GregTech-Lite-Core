package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Bohrium
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Dubnium
import gregtech.api.unification.material.Materials.IndiumGalliumPhosphide
import gregtech.api.unification.material.Materials.IndiumTinBariumTitaniumCuprate
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Mendelevium
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Nihonium
import gregtech.api.unification.material.Materials.NiobiumNitride
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Polonium
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Promethium
import gregtech.api.unification.material.Materials.Rubidium
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.Rutherfordium
import gregtech.api.unification.material.Materials.Selenium
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Strontium
import gregtech.api.unification.material.Materials.Technetium
import gregtech.api.unification.material.Materials.Tellurium
import gregtech.api.unification.material.Materials.Thulium
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
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_ASSEMBLER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ArceusAlloy2B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fullerene
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyX78
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.IncoloyMA956
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kevlar
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Monel500
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MutatedLivingSolder
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PreciousMetalAlloy
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Taranium
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.FEMTO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LOW_DENSITY_STRUCTURE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NANO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PERFECT_CIRCUIT_BOARD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PICO_PIC_CHIP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ULTIMATE_CIRCUIT_BOARD

internal object SpaceAssemblerRecipes
{

    // @formatter:off

    fun init()
    {
        // Advanced recipes for Lapotronic Energy Cluster.
        SPACE_ASSEMBLER_RECIPES.addRecipe {
            input(ELITE_CIRCUIT_BOARD)
            input(plate, Rutherfordium, 4)
            input(circuit, Tier.LuV, 2)
            input(ENGRAVED_LAPOTRON_CHIP, 32)
            input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 16)
            input(wireFine, Ruridit, 64)
            input(bolt, NaquadahEnriched, 8)
            fluidInputs(SolderingAlloy.getFluid(L * 5))
            output(ENERGY_LAPOTRONIC_ORB_CLUSTER, 4)
            EUt(80_000) // ZPM
            duration(10 * SECOND)
            tier(1)
        }

        // Advanced recipes for Energy Module.
        SPACE_ASSEMBLER_RECIPES.addRecipe {
            input(WETWARE_CIRCUIT_BOARD)
            input(plate, Dubnium, 4)
            input(circuit, Tier.ZPM, 2)
            input(ENGRAVED_LAPOTRON_CHIP, 64)
            input(NANO_PIC_CHIP, 16)
            input(wireFine, Osmiridium, 64)
            input(bolt, Naquadria, 8)
            fluidInputs(SolderingAlloy.getFluid(L * 10))
            output(ENERGY_MODULE, 4)
            EUt(100_000) // ZPM
            duration(10 * SECOND)
            tier(1)
        }

        // Advanced recipes for Energy Cluster.
        SPACE_ASSEMBLER_RECIPES.addRecipe {
            input(ULTIMATE_CIRCUIT_BOARD)
            input(plate, Bohrium, 4)
            input(circuit, Tier.UV, 2)
            input(ENGRAVED_LAPOTRON_CHIP, 64)
            input(ENGRAVED_LAPOTRON_CHIP, 64)
            input(PICO_PIC_CHIP, 16)
            input(wireFine, PreciousMetalAlloy, 64)
            input(bolt, Taranium, 8)
            fluidInputs(SolderingAlloy.getFluid(L * 20))
            output(ENERGY_CLUSTER, 4)
            EUt(200_000) // UV
            duration(10 * SECOND)
            tier(2)
        }

        // Advanced recipes for Ultimate Battery.
        SPACE_ASSEMBLER_RECIPES.addRecipe {
            input(PERFECT_CIRCUIT_BOARD)
            input(plate, Nihonium, 4)
            input(circuit, Tier.UHV, 2)
            input(ENGRAVED_LAPOTRON_CHIP, 64)
            input(ENGRAVED_LAPOTRON_CHIP, 64)
            input(ENGRAVED_LAPOTRON_CHIP, 64)
            input(ENGRAVED_LAPOTRON_CHIP, 64)
            input(FEMTO_PIC_CHIP, 16)
            input(wireFine, ArceusAlloy2B, 64)
            input(bolt, CosmicNeutronium, 8)
            fluidInputs(MutatedLivingSolder.getFluid(L * 40))
            output(ULTIMATE_BATTERY, 4)
            EUt(800_000) // UHV
            duration(10 * SECOND)
            tier(3)
        }

        // Low Density Structure Plate
        SPACE_ASSEMBLER_RECIPES.addRecipe {
            input(plate, Polybenzimidazole)
            input(plate, Selenium)
            input(plate, Strontium)
            input(plate, IndiumGalliumPhosphide)
            input(plate, NiobiumNitride)
            input(wireFine, Rubidium, 4)
            fluidInputs(Iron.getPlasma(L * 40))
            fluidInputs(Monel500.getFluid(L * 20))
            fluidInputs(IncoloyMA956.getFluid(L * 20))
            output(LOW_DENSITY_STRUCTURE, 2)
            EUt(VA[UHV])
            duration(10 * SECOND)
            tier(1)
        }

        SPACE_ASSEMBLER_RECIPES.addRecipe {
            input(plate, Kevlar, 2)
            input(plate, Polonium, 2)
            input(plate, Promethium, 2)
            input(plate, IndiumTinBariumTitaniumCuprate, 2)
            input(plate, NiobiumTitanium, 2)
            input(wireFine, Technetium, 4)
            fluidInputs(Iron.getPlasma(L * 80))
            fluidInputs(Monel500.getFluid(L * 40))
            fluidInputs(IncoloyMA956.getFluid(L * 40))
            output(LOW_DENSITY_STRUCTURE, 16)
            EUt(VA[UEV])
            duration(2 * SECOND + 10 * TICK)
            tier(2)
        }

        SPACE_ASSEMBLER_RECIPES.addRecipe {
            input(plate, Fullerene, 4)
            input(plate, Tellurium, 4)
            input(plate, Thulium, 4)
            input(plate, HastelloyX78, 4)
            input(plate, Pikyonium64B, 4)
            input(wireFine, Mendelevium, 4)
            fluidInputs(Iron.getPlasma(L * 160))
            fluidInputs(Monel500.getFluid(L * 80))
            fluidInputs(IncoloyMA956.getFluid(L * 80))
            output(LOW_DENSITY_STRUCTURE, 64)
            EUt(VA[UIV])
            duration(1 * SECOND)
            tier(3)
        }
    }

    // @formatter:on

}