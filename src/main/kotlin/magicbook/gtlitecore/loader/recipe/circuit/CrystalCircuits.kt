package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.NetherStar
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.PolyvinylButyral
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.craftingLens
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.lens
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.common.items.MetaItems.ADVANCED_SMD_CAPACITOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_INDUCTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_TRANSISTOR
import gregtech.common.items.MetaItems.CRYSTAL_ASSEMBLY_LUV
import gregtech.common.items.MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.CRYSTAL_PROCESSOR_IV
import gregtech.common.items.MetaItems.CRYSTAL_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.ELITE_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.ENGRAVED_CRYSTAL_CHIP
import gregtech.common.items.MetaItems.NANO_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.RANDOM_ACCESS_MEMORY
import gregtech.common.items.MetaItems.RAW_CRYSTAL_CHIP
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MOLECULAR_BEAM_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Aegirine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ErbiumDopedZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Forsterite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Jade
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PraseodymiumDopedZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Prasiolite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TantalumPentoxide
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DIELECTRIC_MIRROR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_INDUCTOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_TRANSISTOR

@Suppress("MISSING_DEPENDENCY_CLASS")
class CrystalCircuits
{

    companion object
    {

        fun init()
        {
            circuitComponentsRecipes()
            circuitRecipes()
        }

        private fun circuitComponentsRecipes()
        {
            // Gem addition of engraved crystal chip.
            BLAST_RECIPES.recipeBuilder()
                .input(plate, Forsterite)
                .input(RAW_CRYSTAL_CHIP)
                .fluidInputs(Helium.getFluid(1000))
                .output(ENGRAVED_CRYSTAL_CHIP)
                .EUt(VA[HV].toLong())
                .duration(45 * SECOND)
                .blastFurnaceTemp(5000) // HSSG
                .buildAndRegister()

            BLAST_RECIPES.recipeBuilder()
                .input(plate, Aegirine)
                .input(RAW_CRYSTAL_CHIP)
                .fluidInputs(Helium.getFluid(1000))
                .output(ENGRAVED_CRYSTAL_CHIP)
                .EUt(VA[HV].toLong())
                .duration(45 * SECOND)
                .blastFurnaceTemp(5000) // HSSG
                .buildAndRegister()

            BLAST_RECIPES.recipeBuilder()
                .input(plate, Jade)
                .input(RAW_CRYSTAL_CHIP)
                .fluidInputs(Helium.getFluid(1000))
                .output(ENGRAVED_CRYSTAL_CHIP)
                .EUt(VA[HV].toLong())
                .duration(45 * SECOND)
                .blastFurnaceTemp(5000) // HSSG
                .buildAndRegister()

            BLAST_RECIPES.recipeBuilder()
                .input(plate, Prasiolite)
                .input(RAW_CRYSTAL_CHIP)
                .fluidInputs(Helium.getFluid(1000))
                .output(ENGRAVED_CRYSTAL_CHIP)
                .EUt(VA[HV].toLong())
                .duration(45 * SECOND)
                .blastFurnaceTemp(5000) // HSSG
                .buildAndRegister()

            // Dielectric Mirror
            MOLECULAR_BEAM_RECIPES.recipeBuilder()
                .input(foil, PolyvinylButyral)
                .input(dust, ErbiumDopedZBLANGlass, 2)
                .input(dust, PraseodymiumDopedZBLANGlass, 2)
                .input(dust, TantalumPentoxide, 7)
                .output(DIELECTRIC_MIRROR)
                .EUt(VA[LuV].toLong())
                .duration(30 * SECOND)
                .temperature(2820)
                .buildAndRegister()

            // Crystal Central Processing Unit (Crystal CPU)
            GTRecipeHandler.removeRecipesByInputs(LASER_ENGRAVER_RECIPES,
                OreDictUnifier.get(craftingLens, MarkerMaterials.Color.Lime),
                ENGRAVED_CRYSTAL_CHIP.stackForm)

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(DIELECTRIC_MIRROR)
                .notConsumable(craftingLens, MarkerMaterials.Color.Lime)
                .input(ENGRAVED_CRYSTAL_CHIP)
                .output(CRYSTAL_CENTRAL_PROCESSING_UNIT)
                .EUt(10000) // LuV
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }

        private fun circuitRecipes()
        {

            // IV Crystal Processor
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                    CRYSTAL_CENTRAL_PROCESSING_UNIT.stackForm,
                    NANO_CENTRAL_PROCESSING_UNIT.getStackForm(2),
                    ADVANCED_SMD_CAPACITOR.getStackForm(6),
                    ADVANCED_SMD_TRANSISTOR.getStackForm(6),
                    OreDictUnifier.get(wireFine, NiobiumTitanium, 8)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                    CRYSTAL_CENTRAL_PROCESSING_UNIT.stackForm,
                    NANO_CENTRAL_PROCESSING_UNIT.getStackForm(2),
                    ADVANCED_SMD_CAPACITOR.getStackForm(6),
                    ADVANCED_SMD_TRANSISTOR.getStackForm(6),
                    OreDictUnifier.get(wireFine, NiobiumTitanium, 8)),
                arrayOf(Tin.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                    CRYSTAL_SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(wireFine, NiobiumTitanium, 8),
                    OreDictUnifier.get(bolt, YttriumBariumCuprate, 8)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                    CRYSTAL_SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(wireFine, NiobiumTitanium, 8),
                    OreDictUnifier.get(bolt, YttriumBariumCuprate, 8)),
                arrayOf(Tin.getFluid(L)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD)
                .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
                .input(NANO_CENTRAL_PROCESSING_UNIT, 2)
                .input(ADVANCED_SMD_CAPACITOR, 4)
                .input(ADVANCED_SMD_TRANSISTOR, 4)
                .input(wireFine, NiobiumTitanium, 8)
                .output(CRYSTAL_PROCESSOR_IV, 4)
                .EUt(9600) // LuV
                .duration(10 * SECOND)
                .solderMultiplier(1)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD)
                .input(CRYSTAL_CENTRAL_PROCESSING_UNIT)
                .input(NANO_CENTRAL_PROCESSING_UNIT, 2)
                .input(GOOWARE_SMD_CAPACITOR)
                .input(GOOWARE_SMD_TRANSISTOR)
                .input(wireFine, NiobiumTitanium, 8)
                .output(CRYSTAL_PROCESSOR_IV, 4)
                .EUt(9600) // LuV
                .duration(5 * SECOND)
                .solderMultiplier(1)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD)
                .input(CRYSTAL_SYSTEM_ON_CHIP)
                .input(wireFine, NiobiumTitanium, 8)
                .input(bolt, YttriumBariumCuprate, 8)
                .output(CRYSTAL_PROCESSOR_IV, 8)
                .EUt(86000) // ZPM
                .duration(2 * SECOND + 10 * TICK)
                .solderMultiplier(1)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // LuV Crystal Processor Assembly
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                    CRYSTAL_PROCESSOR_IV.getStackForm(2),
                    ADVANCED_SMD_INDUCTOR.getStackForm(4),
                    ADVANCED_SMD_CAPACITOR.getStackForm(8),
                    RANDOM_ACCESS_MEMORY.getStackForm(24),
                    OreDictUnifier.get(wireFine, NiobiumTitanium, 16)),
                arrayOf(SolderingAlloy.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ELITE_CIRCUIT_BOARD.stackForm,
                    CRYSTAL_PROCESSOR_IV.getStackForm(2),
                    ADVANCED_SMD_INDUCTOR.getStackForm(4),
                    ADVANCED_SMD_CAPACITOR.getStackForm(8),
                    RANDOM_ACCESS_MEMORY.getStackForm(24),
                    OreDictUnifier.get(wireFine, NiobiumTitanium, 16)),
                arrayOf(Tin.getFluid(L * 2)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD)
                .input(CRYSTAL_PROCESSOR_IV, 4)
                .input(ADVANCED_SMD_INDUCTOR, 4)
                .input(ADVANCED_SMD_CAPACITOR, 8)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, NiobiumTitanium, 16)
                .output(CRYSTAL_ASSEMBLY_LUV, 3)
                .EUt(9600) // LuV
                .duration(20 * SECOND)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ELITE_CIRCUIT_BOARD)
                .input(CRYSTAL_PROCESSOR_IV, 4)
                .input(GOOWARE_SMD_INDUCTOR)
                .input(GOOWARE_SMD_CAPACITOR, 2)
                .input(RANDOM_ACCESS_MEMORY, 24)
                .input(wireFine, NiobiumTitanium, 16)
                .output(CRYSTAL_ASSEMBLY_LUV, 3)
                .EUt(9600) // LuV
                .duration(10 * SECOND)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // ZPM Crystal Supercomputer

            // UV Crystal Mainframe

        }

    }

}