package gregtechlite.gtlitecore.loader.recipe.circuit

import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Component.Diode
import gregtech.api.unification.material.MarkerMaterials.Component.Resistor
import gregtech.api.unification.material.MarkerMaterials.Component.Transistor
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.Carbon
import gregtech.api.unification.material.Materials.Charcoal
import gregtech.api.unification.material.Materials.Coal
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.GalliumArsenide
import gregtech.api.unification.material.Materials.Glass
import gregtech.api.unification.material.Materials.Glue
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.NickelZincFerrite
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Silicon
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SolderingAlloy
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Tantalum
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.ore.OrePrefix.bolt
import gregtech.api.unification.ore.OrePrefix.component
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustSmall
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.ring
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.ore.OrePrefix.wireGtSingle
import gregtech.api.unification.stack.UnificationEntry
import gregtech.common.items.MetaItems.BASIC_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.CAPACITOR
import gregtech.common.items.MetaItems.DIODE
import gregtech.common.items.MetaItems.GOOD_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.INDUCTOR
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_HV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_LV
import gregtech.common.items.MetaItems.INTEGRATED_CIRCUIT_MV
import gregtech.common.items.MetaItems.INTEGRATED_LOGIC_CIRCUIT
import gregtech.common.items.MetaItems.RANDOM_ACCESS_MEMORY
import gregtech.common.items.MetaItems.RESISTOR
import gregtech.common.items.MetaItems.SILICON_WAFER
import gregtech.common.items.MetaItems.STICKY_RESIN
import gregtech.common.items.MetaItems.TRANSISTOR
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lignite
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

internal object IntegratedCircuits
{

    // @formatter:off

    fun init()
    {
        smdRecipes()
        circuitRecipes()
    }

    private fun smdRecipes()
    {
        // Transistor
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(plate, Silicon),
                OreDictUnifier.get(wireFine, Tin, 6)),
            arrayOf(Polyethylene.getFluid(L)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(plate, Silicon)
            .input(wireFine, Tin, 6)
            .fluidInputs(Polyethylene.getFluid(L))
            .output(TRANSISTOR, 8)
            .EUt(VA[MV])
            .duration(8 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(3)
            .input(plate, Silicon)
            .input(wireFine, Kanthal, 6)
            .fluidInputs(Polyethylene.getFluid(L))
            .output(TRANSISTOR, 16)
            .EUt(VA[MV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // Resistor
        ModHandler.addShapedRecipe(true, "resistor_wire_lignite", RESISTOR.getStackForm(2),
            "RPR", "CDC", " P ",
            'R', STICKY_RESIN,
            'P', ItemStack(Items.PAPER),
            'C', UnificationEntry(wireGtSingle, Copper),
            'D', UnificationEntry(dust, Lignite))

        ModHandler.addShapedRecipe(true, "resistor_wire_fine_lignite", RESISTOR.getStackForm(2),
            "RPR", "CDC", " P ",
            'R', STICKY_RESIN,
            'P', ItemStack(Items.PAPER),
            'C', UnificationEntry(wireFine, Copper),
            'D', UnificationEntry(dust, Lignite))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(dust, Coal),
                OreDictUnifier.get(wireFine, Copper, 4)),
            arrayOf(Glue.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(dust, Charcoal),
                OreDictUnifier.get(wireFine, Copper, 4)),
            arrayOf(Glue.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(dust, Carbon),
                OreDictUnifier.get(wireFine, Copper, 4)),
            arrayOf(Glue.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(dust, Coal),
                OreDictUnifier.get(wireFine, AnnealedCopper, 4)),
            arrayOf(Glue.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(dust, Charcoal),
                OreDictUnifier.get(wireFine, AnnealedCopper, 4)),
            arrayOf(Glue.getFluid(100)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(dust, Carbon),
                OreDictUnifier.get(wireFine, AnnealedCopper, 4)),
            arrayOf(Glue.getFluid(100)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Coal)
            .input(wireFine, Copper, 4)
            .output(RESISTOR, 4)
            .EUt(6) // ULV
            .duration(8 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Charcoal)
            .input(wireFine, Copper, 4)
            .output(RESISTOR, 4)
            .EUt(6) // ULV
            .duration(8 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Lignite)
            .input(wireFine, Copper, 4)
            .output(RESISTOR, 4)
            .EUt(6) // ULV
            .duration(8 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Carbon)
            .input(wireFine, Copper, 4)
            .output(RESISTOR, 4)
            .EUt(6) // ULV
            .duration(8 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Coal)
            .input(wireFine, AnnealedCopper, 4)
            .output(RESISTOR, 8)
            .EUt(6) // ULV
            .duration(4 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Charcoal)
            .input(wireFine, AnnealedCopper, 4)
            .output(RESISTOR, 8)
            .EUt(6) // ULV
            .duration(4 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Lignite)
            .input(wireFine, AnnealedCopper, 4)
            .output(RESISTOR, 8)
            .EUt(6) // ULV
            .duration(4 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(1)
            .input(dust, Carbon)
            .input(wireFine, AnnealedCopper, 4)
            .output(RESISTOR, 8)
            .EUt(6) // ULV
            .duration(4 * SECOND)
            .buildAndRegister()

        // Capacitor
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(foil, Polyethylene),
                OreDictUnifier.get(foil, Aluminium, 2)),
            arrayOf(Polyethylene.getFluid(L)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(foil, Polyethylene)
            .input(foil, Aluminium)
            .fluidInputs(Polyethylene.getFluid(L))
            .output(CAPACITOR, 8)
            .EUt(VA[MV])
            .duration(8 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(foil, Polyethylene)
            .input(foil, Tantalum)
            .fluidInputs(Polyethylene.getFluid(L))
            .output(CAPACITOR, 16)
            .EUt(VA[MV])
            .duration(4 * SECOND)
            .buildAndRegister()

        // Diode
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireFine, Copper, 4),
                OreDictUnifier.get(dustSmall, GalliumArsenide)),
            arrayOf(Glass.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireFine, AnnealedCopper, 4),
                OreDictUnifier.get(dustSmall, GalliumArsenide)),
            arrayOf(Glass.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireFine, Copper, 4),
                OreDictUnifier.get(dustSmall, GalliumArsenide)),
            arrayOf(Polyethylene.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireFine, AnnealedCopper, 4),
                OreDictUnifier.get(dustSmall, GalliumArsenide)),
            arrayOf(Polyethylene.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireFine, Copper, 4),
                SILICON_WAFER.stackForm),
            arrayOf(Polyethylene.getFluid(L)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireFine, AnnealedCopper, 4),
                SILICON_WAFER.stackForm),
            arrayOf(Polyethylene.getFluid(L)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(wireFine, Copper, 4)
            .input(dustSmall, GalliumArsenide)
            .fluidInputs(Glass.getFluid(L))
            .output(DIODE, 2)
            .EUt(VA[LV])
            .duration(8 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(wireFine, Copper, 4)
            .input(dustSmall, GalliumArsenide)
            .fluidInputs(Polyethylene.getFluid(L))
            .output(DIODE, 2)
            .EUt(VA[LV])
            .duration(8 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(wireFine, AnnealedCopper, 4)
            .input(dustSmall, GalliumArsenide)
            .fluidInputs(Glass.getFluid(L))
            .output(DIODE, 4)
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(wireFine, AnnealedCopper, 4)
            .input(dustSmall, GalliumArsenide)
            .fluidInputs(Polyethylene.getFluid(L))
            .output(DIODE, 4)
            .EUt(VA[LV])
            .duration(4 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(wireFine, Tantalum, 4)
            .input(dustSmall, GalliumArsenide)
            .fluidInputs(Glass.getFluid(L))
            .output(DIODE, 8)
            .EUt(VA[LV])
            .duration(2 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(wireFine, Tantalum, 4)
            .input(dustSmall, GalliumArsenide)
            .fluidInputs(Polyethylene.getFluid(L))
            .output(DIODE, 8)
            .EUt(VA[LV])
            .duration(2 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(wireFine, Copper, 4)
            .input(SILICON_WAFER)
            .fluidInputs(Polyethylene.getFluid(L))
            .output(DIODE, 8)
            .EUt(VA[LV])
            .duration(2 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(wireFine, AnnealedCopper, 4)
            .input(SILICON_WAFER)
            .fluidInputs(Polyethylene.getFluid(L))
            .output(DIODE, 8)
            .EUt(VA[LV])
            .duration(2 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(4)
            .input(wireFine, Tantalum, 4)
            .input(SILICON_WAFER)
            .fluidInputs(Polyethylene.getFluid(L))
            .output(DIODE, 16)
            .EUt(VA[LV])
            .duration(2 * SECOND)
            .buildAndRegister()

        // Inductor
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(ring, Steel),
                OreDictUnifier.get(wireFine, Copper, 2)),
            arrayOf(Polyethylene.getFluid(L / 4)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(ring, Steel),
                OreDictUnifier.get(wireFine, AnnealedCopper, 2)),
            arrayOf(Polyethylene.getFluid(L / 4)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(ring, NickelZincFerrite),
                OreDictUnifier.get(wireFine, Copper, 2)),
            arrayOf(Polyethylene.getFluid(L / 4)))

        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(ring, NickelZincFerrite),
                OreDictUnifier.get(wireFine, AnnealedCopper, 2)),
            arrayOf(Polyethylene.getFluid(L / 4)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(ring, Steel)
            .input(wireFine, Copper, 2)
            .fluidInputs(Polyethylene.getFluid(L / 4))
            .output(INDUCTOR, 2)
            .EUt(VA[MV])
            .duration(8 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(ring, Steel)
            .input(wireFine, AnnealedCopper, 2)
            .fluidInputs(Polyethylene.getFluid(L / 4))
            .output(INDUCTOR, 4)
            .EUt(VA[MV])
            .duration(8 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(ring, NickelZincFerrite)
            .input(wireFine, Copper, 2)
            .fluidInputs(Polyethylene.getFluid(L / 4))
            .output(INDUCTOR, 4)
            .EUt(VA[MV])
            .duration(8 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(2)
            .input(ring, NickelZincFerrite)
            .input(wireFine, AnnealedCopper, 2)
            .fluidInputs(Polyethylene.getFluid(L / 4))
            .output(INDUCTOR, 8)
            .EUt(VA[MV])
            .duration(8 * SECOND)
            .buildAndRegister()

    }

    private fun circuitRecipes()
    {
        // LV Integrated Circuit
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(BASIC_CIRCUIT_BOARD.stackForm,
                    INTEGRATED_LOGIC_CIRCUIT.stackForm,
                    OreDictUnifier.get(component, Resistor, 2),
                    OreDictUnifier.get(component, Diode, 2),
                    OreDictUnifier.get(wireFine, Copper, 2),
                    OreDictUnifier.get(bolt, Tin, 2)),
            arrayOf(SolderingAlloy.getFluid(L / 2)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(BASIC_CIRCUIT_BOARD.stackForm,
                INTEGRATED_LOGIC_CIRCUIT.stackForm,
                OreDictUnifier.get(component, Resistor, 2),
                OreDictUnifier.get(component, Diode, 2),
                OreDictUnifier.get(wireFine, Copper, 2),
                OreDictUnifier.get(bolt, Tin, 2)),
            arrayOf(Tin.getFluid(L)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(BASIC_CIRCUIT_BOARD)
            .input(INTEGRATED_LOGIC_CIRCUIT)
            .input(component, Resistor, 2)
            .input(component, Diode, 2)
            .input(wireFine, Copper, 2)
            .input(bolt, Tin, 2)
            .output(INTEGRATED_CIRCUIT_LV, 4)
            .EUt(VH[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // MV Integrated Circuit
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(GOOD_CIRCUIT_BOARD.stackForm,
                INTEGRATED_CIRCUIT_LV.getStackForm(2),
                OreDictUnifier.get(component, Resistor, 2),
                OreDictUnifier.get(component, Diode, 2),
                OreDictUnifier.get(wireFine, Gold, 4),
                OreDictUnifier.get(bolt, Silver, 4)),
            arrayOf(SolderingAlloy.getFluid(L / 2)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(GOOD_CIRCUIT_BOARD.stackForm,
                INTEGRATED_CIRCUIT_LV.getStackForm(2),
                OreDictUnifier.get(component, Resistor, 2),
                OreDictUnifier.get(component, Diode, 2),
                OreDictUnifier.get(wireFine, Gold, 4),
                OreDictUnifier.get(bolt, Silver, 4)),
            arrayOf(Tin.getFluid(L)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(GOOD_CIRCUIT_BOARD)
            .input(INTEGRATED_CIRCUIT_LV, 4)
            .input(component, Resistor, 2)
            .input(component, Diode, 2)
            .input(wireFine, Gold, 4)
            .input(bolt, Silver, 4)
            .output(INTEGRATED_CIRCUIT_MV, 3)
            .EUt(24) // LV
            .duration(20 * SECOND)
            .buildAndRegister()

        // HV Integrated Circuit
        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(INTEGRATED_CIRCUIT_MV.getStackForm(2),
                INTEGRATED_LOGIC_CIRCUIT.getStackForm(2),
                RANDOM_ACCESS_MEMORY.getStackForm(2),
                OreDictUnifier.get(component, Transistor, 4),
                OreDictUnifier.get(wireFine, Electrum, 8),
                OreDictUnifier.get(bolt, AnnealedCopper, 8)),
            arrayOf(SolderingAlloy.getFluid(L / 2)))

        GTRecipeHandler.removeRecipesByInputs(CIRCUIT_ASSEMBLER_RECIPES,
            arrayOf(INTEGRATED_CIRCUIT_MV.getStackForm(2),
                INTEGRATED_LOGIC_CIRCUIT.getStackForm(2),
                RANDOM_ACCESS_MEMORY.getStackForm(2),
                OreDictUnifier.get(component, Transistor, 4),
                OreDictUnifier.get(wireFine, Electrum, 8),
                OreDictUnifier.get(bolt, AnnealedCopper, 8)),
            arrayOf(Tin.getFluid(L)))

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
            .input(INTEGRATED_CIRCUIT_MV, 3)
            .input(INTEGRATED_LOGIC_CIRCUIT, 2)
            .input(RANDOM_ACCESS_MEMORY, 2)
            .input(component, Transistor, 4)
            .input(wireFine, Electrum, 8)
            .input(bolt, AnnealedCopper, 8)
            .output(INTEGRATED_CIRCUIT_HV, 2)
            .EUt(VA[LV])
            .duration(40 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}