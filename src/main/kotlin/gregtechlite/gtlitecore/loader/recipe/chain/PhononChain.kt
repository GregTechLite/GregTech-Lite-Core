package gregtechlite.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.L
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.fluids.store.FluidStorageKeys
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Helium
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Krypton
import gregtech.api.unification.material.Materials.NetherStar
import gregtech.api.unification.material.Materials.PolyphenyleneSulfide
import gregtech.api.unification.material.Materials.PolyvinylChloride
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.cableGtDouble
import gregtech.api.unification.ore.OrePrefix.cableGtHex
import gregtech.api.unification.ore.OrePrefix.cableGtOctal
import gregtech.api.unification.ore.OrePrefix.cableGtQuadruple
import gregtech.api.unification.ore.OrePrefix.cableGtSingle
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.frameGt
import gregtech.api.unification.ore.OrePrefix.gear
import gregtech.api.unification.ore.OrePrefix.gearSmall
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.ingotHot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.plateDense
import gregtech.api.unification.ore.OrePrefix.plateDouble
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.rotor
import gregtech.api.unification.ore.OrePrefix.round
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.stick
import gregtech.api.unification.ore.OrePrefix.stickLong
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtech.api.unification.ore.OrePrefix.wireGtHex
import gregtech.api.unification.ore.OrePrefix.wireGtOctal
import gregtech.api.unification.ore.OrePrefix.wireGtQuadruple
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.api.unification.stack.UnificationEntry
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ELECTRIC_IMPLOSION_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LAMINATOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LARGE_MIXER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Antimatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GelidCryotheum
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HarmonicPhononMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HeavyQuarks
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.MagMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Magnetium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Mellion
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.NeutronProtonFermiSuperfluid
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polyetheretherketone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolyphosphonitrileFluoroRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PolytetramethyleneGlycolRubber
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ResonantStrangeMeson
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.StableBaryonicMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Zylon
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.copy
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.TOPOLOGICAL_ORDER_CHANGING_RECIPES
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NAQUADRIA_SUPERSOLID
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PHONONIC_SEED_CRYSTAL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TOPOLOGICAL_INSULATOR_TUBE

internal object PhononChain
{

    // @formatter:off

    fun init()
    {

        // Phononic Seed Crystal
        AUTOCLAVE_RECIPES.recipeBuilder()
            .input(nanite, TranscendentMetal)
            .input(dust, Mellion, 32)
            .fluidInputs(StableBaryonicMatter.getFluid(32000))
            .output(PHONONIC_SEED_CRYSTAL)
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // Advanced recipes for Phononic Seed Crystal when player completed all processing and get MagMatter.
        AUTOCLAVE_RECIPES.recipeBuilder()
            .input(round, MagMatter)
            .fluidInputs(StableBaryonicMatter.getFluid(4000))
            .output(PHONONIC_SEED_CRYSTAL, 5)
            .EUt(VA[UXV])
            .duration(10 * SECOND)
            .cleanroom(CleanroomType.CLEANROOM)
            .buildAndRegister()

        // The stable baryonic matter is cycle when player used advanced recipes.
        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
            OreDictUnifier.get(dust, HarmonicPhononMatter),
            IntCircuitIngredient.getIntegratedCircuit(1))

        GTRecipeHandler.removeRecipesByInputs(BLAST_RECIPES,
            arrayOf(OreDictUnifier.get(dust, HarmonicPhononMatter),
                IntCircuitIngredient.getIntegratedCircuit(2)),
            arrayOf(Krypton.getFluid(10)))

        GTRecipeHandler.removeRecipesByInputs(TOPOLOGICAL_ORDER_CHANGING_RECIPES,
            OreDictUnifier.get(dust, HarmonicPhononMatter),
            IntCircuitIngredient.getIntegratedCircuit(1))

        GTRecipeHandler.removeRecipesByInputs(TOPOLOGICAL_ORDER_CHANGING_RECIPES,
            OreDictUnifier.get(dust, HarmonicPhononMatter),
            IntCircuitIngredient.getIntegratedCircuit(2))

        TOPOLOGICAL_ORDER_CHANGING_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(PHONONIC_SEED_CRYSTAL)
            .input(nanite, Iron)
            .input(dust, NetherStar, 16)
            .fluidInputs(Mellion.getFluid(L * 16))
            .output(ingot, HarmonicPhononMatter, 4)
            .fluidOutputs(StableBaryonicMatter.getFluid(800))
            .EUt(VA[UXV])
            .duration(20 * SECOND)
            .blastFurnaceTemp(26000)
            .buildAndRegister()

        // MagMatter liquid.
        LARGE_MIXER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .fluidInputs(HarmonicPhononMatter.getFluid(L))
            .fluidInputs(ResonantStrangeMeson.getFluid(8000))
            .fluidInputs(NeutronProtonFermiSuperfluid.getFluid(6000))
            .fluidInputs(Antimatter.getFluid(1000))
            .fluidOutputs(MagMatter.getFluid(16000))
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // ingotMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(ingot, Magnetium)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(ingot, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // blockMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(block, Magnetium)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(block, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // plateMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(plate, Magnetium)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(plate, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // plateDoubleMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(plateDouble, Magnetium)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(plateDouble, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // plateDenseMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(plateDense, Magnetium)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(plateDense, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // foilMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(foil, Magnetium, 4)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(foil, MagMatter, 4)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // stickMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(stick, Magnetium, 2)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(stick, MagMatter, 2)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // stickLongMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(stickLong, Magnetium)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(stickLong, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // boltMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(bolt, Magnetium, 8)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(bolt, MagMatter, 8)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // screwMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(screw, Magnetium, 8)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(screw, MagMatter, 8)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // ringMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(ring, Magnetium, 4)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(ring, MagMatter, 4)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // roundMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(round, Magnetium, 8)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(round, MagMatter, 8)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // gearMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(gear, Magnetium)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(gear, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // gearSmallMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(gearSmall, Magnetium, 2)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(gearSmall, MagMatter, 2)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // rotorMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(rotor, Magnetium)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(rotor, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // wireFineMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(wireFine, Magnetium, 8)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(wireFine, MagMatter, 8)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // wireGtSingleMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(wireFine, Magnetium, 8)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(wireGtSingle, MagMatter, 2)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // wireGtDoubleMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(wireFine, Magnetium, 16)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(wireGtDouble, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // wireGtQuadrupleMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(wireFine, Magnetium, 32)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(wireGtQuadruple, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // wireGtOctalMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .circuitMeta(5)
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(wireFine, Magnetium, 64)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(wireGtOctal, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // wireGtHexMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .circuitMeta(6)
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(wireFine, Magnetium, 64)
            .input(wireFine, Magnetium, 64)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(wireGtHex, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // cableGtSingleMagMatter/cableGtDoubleMagMatter
        for (rubber in arrayOf(PolytetramethyleneGlycolRubber.getFluid(L / 2),
            PolyphosphonitrileFluoroRubber.getFluid(L / 4)))
        {
            LAMINATOR_RECIPES.recipeBuilder()
                .input(wireGtSingle, MagMatter)
                .input(foil, PolyvinylChloride)
                .input(foil, PolyphenyleneSulfide)
                .input(foil, Polyetheretherketone)
                .input(foil, Zylon)
                .fluidInputs(rubber)
                .output(cableGtSingle, MagMatter)
                .EUt(7) // ULV
                .duration(5 * SECOND)
                .buildAndRegister()

            LAMINATOR_RECIPES.recipeBuilder()
                .input(wireGtDouble, MagMatter)
                .input(foil, PolyvinylChloride)
                .input(foil, PolyphenyleneSulfide)
                .input(foil, Polyetheretherketone)
                .input(foil, Zylon)
                .fluidInputs(rubber)
                .output(cableGtDouble, MagMatter)
                .EUt(7) // ULV
                .duration(5 * SECOND)
                .buildAndRegister()
        }

        // cableGtQuadrupleMagMatter
        for (rubber in arrayOf(PolytetramethyleneGlycolRubber.getFluid(L),
            PolyphosphonitrileFluoroRubber.getFluid(L / 2)))
        {
            LAMINATOR_RECIPES.recipeBuilder()
                .input(wireGtQuadruple, MagMatter)
                .input(foil, PolyvinylChloride, 2)
                .input(foil, PolyphenyleneSulfide, 2)
                .input(foil, Polyetheretherketone, 2)
                .input(foil, Zylon, 2)
                .fluidInputs(rubber)
                .output(cableGtQuadruple, MagMatter)
                .EUt(7) // ULV
                .duration(5 * SECOND)
                .buildAndRegister()
        }

        // cableGtOctalMagMatter
        for (rubber in arrayOf(PolytetramethyleneGlycolRubber.getFluid(L + L / 2),
            PolyphosphonitrileFluoroRubber.getFluid(L - L / 4)))
        {
            LAMINATOR_RECIPES.recipeBuilder()
                .input(wireGtOctal, MagMatter)
                .input(foil, PolyvinylChloride, 3)
                .input(foil, PolyphenyleneSulfide, 3)
                .input(foil, Polyetheretherketone, 3)
                .input(foil, Zylon, 3)
                .fluidInputs(rubber)
                .output(cableGtOctal, MagMatter)
                .EUt(7) // ULV
                .duration(5 * SECOND)
                .buildAndRegister()
        }

        // cableGtHexMagMatter
        for (rubber in arrayOf(PolytetramethyleneGlycolRubber.getFluid(L * 2 + L / 2),
            PolyphosphonitrileFluoroRubber.getFluid(L + L / 4)))
        {
            LAMINATOR_RECIPES.recipeBuilder()
                .input(wireGtHex, MagMatter)
                .input(foil, PolyvinylChloride, 5)
                .input(foil, PolyphenyleneSulfide, 5)
                .input(foil, Polyetheretherketone, 5)
                .input(foil, Zylon, 5)
                .fluidInputs(rubber)
                .output(cableGtHex, MagMatter)
                .EUt(7) // ULV
                .duration(5 * SECOND)
                .buildAndRegister()
        }

        // frameGtMagMatter
        ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
            .input(nanite, Copper)
            .input(NAQUADRIA_SUPERSOLID)
            .input(TOPOLOGICAL_INSULATOR_TUBE)
            .input(frameGt, Magnetium)
            .fluidInputs(MagMatter.getFluid(1000))
            .output(frameGt, MagMatter)
            .EUt(VA[UXV])
            .duration(1 * SECOND)
            .buildAndRegister()

        // wallGtMagMatter
        ModHandler.addShapedRecipe(true, "mag_matter_wall_gt", GTLiteMetaBlocks.METAL_WALLS[MagMatter]!!.getItem(MagMatter).copy(6),
            "hPS", "P P", "SPd",
            'P', UnificationEntry(plate, MagMatter),
            'S', UnificationEntry(screw, MagMatter))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(11)
            .input(plate, MagMatter, 2)
            .input(screw, MagMatter)
            .outputs(GTLiteMetaBlocks.METAL_WALLS[MagMatter]!!.getItem(MagMatter).copy(3))
            .EUt(7)
            .duration(2 * SECOND + 5 * TICK)
            .buildAndRegister()

        // Halkonite Steel Sheeted Frame
        ModHandler.addShapedRecipe(true, "mag_matter_sheeted_frame", GTLiteMetaBlocks.SHEETED_FRAMES[MagMatter]!!.getItem(MagMatter).copy(12),
            "PFP", "PhP", "PFP",
            'P', UnificationEntry(plate, MagMatter),
            'F', UnificationEntry(frameGt, MagMatter))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(10)
            .input(plate, MagMatter, 3)
            .input(frameGt, MagMatter)
            .outputs(GTLiteMetaBlocks.SHEETED_FRAMES[MagMatter]!!.getItem(MagMatter).copy(6))
            .EUt(7) // ULV
            .duration(2 * SECOND + 5 * TICK)
            .buildAndRegister()

    }

    // @formatter:on

}