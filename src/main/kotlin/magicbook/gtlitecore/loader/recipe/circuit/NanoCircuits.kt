package magicbook.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.AceticAcid
import gregtech.api.unification.material.Materials.Air
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.Argon
import gregtech.api.unification.material.Materials.Chlorine
import gregtech.api.unification.material.Materials.DinitrogenTetroxide
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Glowstone
import gregtech.api.unification.material.Materials.Graphene
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.HSSS
import gregtech.api.unification.material.Materials.HydrochloricAcid
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.IndiumGalliumPhosphide
import gregtech.api.unification.material.Materials.Iridium
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.LithiumChloride
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.NaquadahAlloy
import gregtech.api.unification.material.Materials.NiobiumNitride
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.NitricAcid
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Oxygen
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.PalladiumRaw
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.RTMAlloy
import gregtech.api.unification.material.Materials.Ruridit
import gregtech.api.unification.material.Materials.SodaAsh
import gregtech.api.unification.material.Materials.SodiumHydroxide
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.TitaniumTetrachloride
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.Ultimet
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.material.Materials.Xenon
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.common.items.MetaItems.ADVANCED_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.ADVANCED_SMD_CAPACITOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_DIODE
import gregtech.common.items.MetaItems.ADVANCED_SMD_INDUCTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_RESISTOR
import gregtech.common.items.MetaItems.ADVANCED_SMD_TRANSISTOR
import gregtech.common.items.MetaItems.ADVANCED_SYSTEM_ON_CHIP
import gregtech.common.items.MetaItems.CARBON_FIBERS
import gregtech.common.items.MetaItems.CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.CENTRAL_PROCESSING_UNIT_WAFER
import gregtech.common.items.MetaItems.NANO_CENTRAL_PROCESSING_UNIT
import gregtech.common.items.MetaItems.NANO_CENTRAL_PROCESSING_UNIT_WAFER
import gregtech.common.items.MetaItems.NANO_COMPUTER_IV
import gregtech.common.items.MetaItems.NANO_MAINFRAME_LUV
import gregtech.common.items.MetaItems.NANO_PROCESSOR_ASSEMBLY_EV
import gregtech.common.items.MetaItems.NANO_PROCESSOR_HV
import gregtech.common.items.MetaItems.NOR_MEMORY_CHIP
import gregtech.common.items.MetaItems.RANDOM_ACCESS_MEMORY
import gregtech.common.items.MetaItems.SMD_CAPACITOR
import gregtech.common.items.MetaItems.SMD_DIODE
import gregtech.common.items.MetaItems.SMD_INDUCTOR
import gregtech.common.items.MetaItems.SMD_RESISTOR
import gregtech.common.items.MetaItems.SMD_TRANSISTOR
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYOGENIC_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CVD_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.VACUUM_CHAMBER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumCarbonate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LithiumTitanate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumAcetate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumLoadedRutileNanoparticles
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PalladiumNitrate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StrontiumFerrite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuccinicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TitaniumNitrate
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.api.utils.GTRecipeUtility
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_CAPACITOR
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.GOOWARE_SMD_INDUCTOR

@Suppress("MISSING_DEPENDENCY_CLASS")
class NanoCircuits
{

    companion object
    {

        fun init()
        {
            smdRecipes()
            circuitComponentsRecipes()
            circuitRecipes()
        }

        private fun smdRecipes()
        {
            // Advanced SMD Transistor
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(foil, VanadiumGallium),
                    OreDictUnifier.get(wireFine, HSSG, 8)),
                arrayOf(Polybenzimidazole.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(foil, VanadiumGallium)
                .input(wireFine, TungstenSteel, 8)
                .fluidInputs(Polybenzimidazole.getFluid(L))
                .output(ADVANCED_SMD_TRANSISTOR, 16)
                .EUt(VHA[IV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(foil, VanadiumGallium)
                .input(wireFine, HSSG, 8)
                .fluidInputs(Polybenzimidazole.getFluid(L))
                .output(ADVANCED_SMD_TRANSISTOR, 32)
                .EUt(VHA[IV].toLong())
                .duration(4 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(foil, VanadiumGallium)
                .input(wireFine, HSSS, 8)
                .fluidInputs(Polybenzimidazole.getFluid(L))
                .output(ADVANCED_SMD_TRANSISTOR, 64)
                .EUt(VHA[IV].toLong())
                .duration(2 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Advanced SMD Resistor
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Graphene),
                    OreDictUnifier.get(wireFine, Platinum, 4)),
                arrayOf(Polybenzimidazole.getFluid(L * 2)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Graphene)
                .input(wireFine, Molybdenum, 4)
                .fluidInputs(Polybenzimidazole.getFluid(L * 2))
                .output(ADVANCED_SMD_RESISTOR, 16)
                .EUt(VHA[IV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Graphene)
                .input(wireFine, Ultimet, 4)
                .fluidInputs(Polybenzimidazole.getFluid(L * 2))
                .output(ADVANCED_SMD_RESISTOR, 32)
                .EUt(VHA[IV].toLong())
                .duration(4 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Graphene)
                .input(wireFine, RTMAlloy, 4)
                .fluidInputs(Polybenzimidazole.getFluid(L * 2))
                .output(ADVANCED_SMD_RESISTOR, 64)
                .EUt(VHA[IV].toLong())
                .duration(2 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Advanced SMD Capacitor
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(foil, Polybenzimidazole, 2),
                    OreDictUnifier.get(foil, HSSS)),
                arrayOf(Polybenzimidazole.getFluid(L / 4)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(foil, Polybenzimidazole)
                .input(foil, Iridium)
                .fluidInputs(Polybenzimidazole.getFluid(L / 2))
                .output(ADVANCED_SMD_CAPACITOR, 16)
                .EUt(VHA[IV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(foil, Polybenzimidazole)
                .input(foil, Ruridit)
                .fluidInputs(Polybenzimidazole.getFluid(L / 2))
                .output(ADVANCED_SMD_CAPACITOR, 32)
                .EUt(VHA[IV].toLong())
                .duration(4 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(foil, Polybenzimidazole)
                .input(foil, Osmiridium)
                .fluidInputs(Polybenzimidazole.getFluid(L / 2))
                .output(ADVANCED_SMD_CAPACITOR, 64)
                .EUt(VHA[IV].toLong())
                .duration(2 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Advanced SMD Diode
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(dust, IndiumGalliumPhosphide),
                    OreDictUnifier.get(wireFine, NiobiumTitanium, 16)),
                arrayOf(Polybenzimidazole.getFluid(L * 2)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, IndiumGalliumPhosphide)
                .input(wireFine, NiobiumNitride, 8)
                .fluidInputs(Polybenzimidazole.getFluid(L * 2))
                .output(ADVANCED_SMD_DIODE, 64)
                .EUt(VHA[IV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, IndiumGalliumPhosphide)
                .input(wireFine, YttriumBariumCuprate, 4)
                .fluidInputs(Polybenzimidazole.getFluid(L * 2))
                .output(ADVANCED_SMD_DIODE, 64)
                .EUt(VHA[IV].toLong())
                .duration(4 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Advanced SMD Inductor
            GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(ring, HSSE),
                    OreDictUnifier.get(wireFine, Palladium, 4)),
                arrayOf(Polybenzimidazole.getFluid(L)))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(ring, StrontiumFerrite)
                .input(wireFine, Palladium, 4)
                .fluidInputs(Polybenzimidazole.getFluid(L))
                .output(ADVANCED_SMD_INDUCTOR, 16)
                .EUt(VHA[IV].toLong())
                .duration(8 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(ring, StrontiumFerrite)
                .input(wireFine, Trinium, 4)
                .fluidInputs(Polybenzimidazole.getFluid(L))
                .output(ADVANCED_SMD_INDUCTOR, 32)
                .EUt(VHA[IV].toLong())
                .duration(4 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(ring, StrontiumFerrite)
                .input(wireFine, NaquadahAlloy, 4)
                .fluidInputs(Polybenzimidazole.getFluid(L))
                .output(ADVANCED_SMD_INDUCTOR, 64)
                .EUt(VHA[IV].toLong())
                .duration(4 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

        }

        private fun circuitComponentsRecipes()
        {
            // Nano Central Processing Unit (NanoCPU) Wafer
            GTRecipeUtility.removeChemicalRecipes(
                arrayOf(CENTRAL_PROCESSING_UNIT_WAFER.stackForm,
                    CARBON_FIBERS.getStackForm(16)),
                arrayOf(Glowstone.getFluid(L * 4)))

            VACUUM_CHAMBER_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(CENTRAL_PROCESSING_UNIT_WAFER)
                .input(dustTiny, PalladiumLoadedRutileNanoparticles)
                .input(CARBON_FIBERS, 8)
                .fluidInputs(Glowstone.getFluid(L * 4))
                .output(NANO_CENTRAL_PROCESSING_UNIT_WAFER, 4)
                .EUt(VA[EV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            VACUUM_CHAMBER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(CENTRAL_PROCESSING_UNIT_WAFER, 4)
                .input(dustSmall, PalladiumLoadedRutileNanoparticles)
                .input(CARBON_FIBERS, 32)
                .fluidInputs(Glowstone.getFluid(L * 16))
                .output(NANO_CENTRAL_PROCESSING_UNIT_WAFER, 16)
                .EUt(VA[EV].toLong())
                .duration(15 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            VACUUM_CHAMBER_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .input(CENTRAL_PROCESSING_UNIT_WAFER, 9)
                .input(dust, PalladiumLoadedRutileNanoparticles)
                .input(CARBON_FIBERS, 64)
                .fluidInputs(Glowstone.getFluid(L * 64))
                .output(NANO_CENTRAL_PROCESSING_UNIT_WAFER, 36)
                .EUt(VA[EV].toLong())
                .duration(20 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()
        }

        private fun circuitRecipes()
        {
            // HV Nano Processor
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                    SMD_RESISTOR.getStackForm(8),
                    SMD_CAPACITOR.getStackForm(8),
                    SMD_TRANSISTOR.getStackForm(8),
                    OreDictUnifier.get(wireFine, Electrum, 8)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                    SMD_RESISTOR.getStackForm(8),
                    SMD_CAPACITOR.getStackForm(8),
                    SMD_TRANSISTOR.getStackForm(8),
                    OreDictUnifier.get(wireFine, Electrum, 8)),
                arrayOf(Tin.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                    ADVANCED_SMD_RESISTOR.getStackForm(2),
                    ADVANCED_SMD_CAPACITOR.getStackForm(2),
                    ADVANCED_SMD_TRANSISTOR.getStackForm(2),
                    OreDictUnifier.get(wireFine, Electrum, 8)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_CENTRAL_PROCESSING_UNIT.stackForm,
                    ADVANCED_SMD_RESISTOR.getStackForm(2),
                    ADVANCED_SMD_CAPACITOR.getStackForm(2),
                    ADVANCED_SMD_TRANSISTOR.getStackForm(2),
                    OreDictUnifier.get(wireFine, Electrum, 8)),
                arrayOf(Tin.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    ADVANCED_SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(wireFine, Electrum, 4),
                    OreDictUnifier.get(bolt, Platinum, 4)),
                arrayOf(SolderingAlloy.getFluid(L / 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    ADVANCED_SYSTEM_ON_CHIP.stackForm,
                    OreDictUnifier.get(wireFine, Electrum, 4),
                    OreDictUnifier.get(bolt, Platinum, 4)),
                arrayOf(Tin.getFluid(L)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(NANO_CENTRAL_PROCESSING_UNIT)
                .input(SMD_RESISTOR, 8)
                .input(SMD_CAPACITOR, 8)
                .input(SMD_TRANSISTOR, 8)
                .input(wireFine, Electrum, 8)
                .output(NANO_PROCESSOR_HV, 4)
                .EUt(600) // EV
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(NANO_CENTRAL_PROCESSING_UNIT)
                .input(ADVANCED_SMD_RESISTOR, 2)
                .input(ADVANCED_SMD_CAPACITOR, 2)
                .input(ADVANCED_SMD_TRANSISTOR, 2)
                .input(wireFine, Electrum, 8)
                .output(NANO_PROCESSOR_HV, 4)
                .EUt(600) // EV
                .duration(5 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(ADVANCED_SYSTEM_ON_CHIP)
                .input(wireFine, Electrum, 4)
                .input(bolt, Platinum, 4)
                .output(NANO_PROCESSOR_HV, 8)
                .EUt(9600) // LuV
                .duration(2 * SECOND + 10 * TICK)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // EV Nano Assembly
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_HV.getStackForm(2),
                    SMD_INDUCTOR.getStackForm(4),
                    SMD_CAPACITOR.getStackForm(8),
                    RANDOM_ACCESS_MEMORY.getStackForm(8),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(SolderingAlloy.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_HV.getStackForm(2),
                    SMD_INDUCTOR.getStackForm(4),
                    SMD_CAPACITOR.getStackForm(8),
                    RANDOM_ACCESS_MEMORY.getStackForm(8),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(Tin.getFluid(L * 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_HV.getStackForm(2),
                    ADVANCED_SMD_INDUCTOR.stackForm,
                    ADVANCED_SMD_CAPACITOR.getStackForm(2),
                    RANDOM_ACCESS_MEMORY.getStackForm(8),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(SolderingAlloy.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_HV.getStackForm(2),
                    ADVANCED_SMD_INDUCTOR.stackForm,
                    ADVANCED_SMD_CAPACITOR.getStackForm(2),
                    RANDOM_ACCESS_MEMORY.getStackForm(8),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(Tin.getFluid(L * 2)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(NANO_PROCESSOR_HV, 4)
                .input(SMD_INDUCTOR, 4)
                .input(SMD_CAPACITOR, 8)
                .input(RANDOM_ACCESS_MEMORY, 8)
                .input(wireFine, Electrum, 16)
                .output(NANO_PROCESSOR_ASSEMBLY_EV, 3)
                .EUt(600) // EV
                .duration(20 * SECOND)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(NANO_PROCESSOR_HV, 4)
                .input(ADVANCED_SMD_INDUCTOR)
                .input(ADVANCED_SMD_CAPACITOR, 2)
                .input(RANDOM_ACCESS_MEMORY, 8)
                .input(wireFine, Electrum, 16)
                .output(NANO_PROCESSOR_ASSEMBLY_EV, 3)
                .EUt(600) // EV
                .duration(10 * SECOND)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // IV Nano Supercomputer
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(2),
                    SMD_DIODE.getStackForm(8),
                    NOR_MEMORY_CHIP.getStackForm(4),
                    RANDOM_ACCESS_MEMORY.getStackForm(16),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(SolderingAlloy.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(2),
                    SMD_DIODE.getStackForm(8),
                    NOR_MEMORY_CHIP.getStackForm(4),
                    RANDOM_ACCESS_MEMORY.getStackForm(16),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(Tin.getFluid(L * 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(2),
                    ADVANCED_SMD_DIODE.getStackForm(2),
                    NOR_MEMORY_CHIP.getStackForm(4),
                    RANDOM_ACCESS_MEMORY.getStackForm(16),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(SolderingAlloy.getFluid(L)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(ADVANCED_CIRCUIT_BOARD.stackForm,
                    NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(2),
                    ADVANCED_SMD_DIODE.getStackForm(2),
                    NOR_MEMORY_CHIP.getStackForm(4),
                    RANDOM_ACCESS_MEMORY.getStackForm(16),
                    OreDictUnifier.get(wireFine, Electrum, 16)),
                arrayOf(Tin.getFluid(L * 2)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(NANO_PROCESSOR_ASSEMBLY_EV, 3)
                .input(SMD_DIODE, 8)
                .input(NOR_MEMORY_CHIP, 4)
                .input(RANDOM_ACCESS_MEMORY, 16)
                .input(wireFine, Electrum, 16)
                .output(NANO_COMPUTER_IV, 2)
                .EUt(600) // EV
                .duration(20 * SECOND)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(NANO_PROCESSOR_ASSEMBLY_EV, 3)
                .input(ADVANCED_SMD_DIODE, 2)
                .input(NOR_MEMORY_CHIP, 4)
                .input(RANDOM_ACCESS_MEMORY, 16)
                .input(wireFine, Electrum, 16)
                .output(NANO_COMPUTER_IV, 2)
                .EUt(600) // EV
                .duration(10 * SECOND)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // LuV Nano Mainframe
            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, Aluminium, 2),
                    NANO_COMPUTER_IV.getStackForm(2),
                    SMD_INDUCTOR.getStackForm(16),
                    SMD_CAPACITOR.getStackForm(32),
                    RANDOM_ACCESS_MEMORY.getStackForm(16),
                    OreDictUnifier.get(wireGtSingle, AnnealedCopper, 32)),
                arrayOf(SolderingAlloy.getFluid(L * 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, Aluminium, 2),
                    NANO_COMPUTER_IV.getStackForm(2),
                    SMD_INDUCTOR.getStackForm(16),
                    SMD_CAPACITOR.getStackForm(32),
                    RANDOM_ACCESS_MEMORY.getStackForm(16),
                    OreDictUnifier.get(wireGtSingle, AnnealedCopper, 32)),
                arrayOf(Tin.getFluid(L * 4)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, Aluminium, 2),
                    NANO_COMPUTER_IV.getStackForm(2),
                    ADVANCED_SMD_INDUCTOR.getStackForm(4),
                    ADVANCED_SMD_CAPACITOR.getStackForm(8),
                    RANDOM_ACCESS_MEMORY.getStackForm(16),
                    OreDictUnifier.get(wireGtSingle, AnnealedCopper, 32)),
                arrayOf(SolderingAlloy.getFluid(L * 2)))

            GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
                arrayOf(OreDictUnifier.get(frameGt, Aluminium, 2),
                    NANO_COMPUTER_IV.getStackForm(2),
                    ADVANCED_SMD_INDUCTOR.getStackForm(4),
                    ADVANCED_SMD_CAPACITOR.getStackForm(8),
                    RANDOM_ACCESS_MEMORY.getStackForm(16),
                    OreDictUnifier.get(wireGtSingle, AnnealedCopper, 32)),
                arrayOf(Tin.getFluid(L * 4)))

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(frameGt, VanadiumSteel, 2)
                .input(NANO_COMPUTER_IV, 2)
                .input(SMD_INDUCTOR, 16)
                .input(SMD_CAPACITOR, 32)
                .input(RANDOM_ACCESS_MEMORY, 16)
                .input(wireGtSingle, AnnealedCopper, 32)
                .output(NANO_MAINFRAME_LUV)
                .EUt(VA[EV].toLong())
                .duration(40 * SECOND)
                .solderMultiplier(4)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(frameGt, VanadiumSteel, 2)
                .input(NANO_COMPUTER_IV, 2)
                .input(ADVANCED_SMD_INDUCTOR, 4)
                .input(ADVANCED_SMD_CAPACITOR, 8)
                .input(RANDOM_ACCESS_MEMORY, 16)
                .input(wireGtSingle, AnnealedCopper, 32)
                .output(NANO_MAINFRAME_LUV)
                .EUt(VA[EV].toLong())
                .duration(20 * SECOND)
                .solderMultiplier(4)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(frameGt, VanadiumSteel, 2)
                .input(NANO_COMPUTER_IV, 2)
                .input(GOOWARE_SMD_INDUCTOR)
                .input(GOOWARE_SMD_CAPACITOR, 2)
                .input(RANDOM_ACCESS_MEMORY, 16)
                .input(wireGtSingle, AnnealedCopper, 32)
                .output(NANO_MAINFRAME_LUV)
                .EUt(VA[EV].toLong())
                .duration(10 * SECOND)
                .solderMultiplier(4)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

        }

    }

}