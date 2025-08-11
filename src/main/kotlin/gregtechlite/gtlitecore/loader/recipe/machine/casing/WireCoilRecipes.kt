package gregtechlite.gtlitecore.loader.recipe.machine.casing

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.OpV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials.Tier
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Cupronickel
import gregtech.api.unification.material.Materials.HSSG
import gregtech.api.unification.material.Materials.Kanthal
import gregtech.api.unification.material.Materials.Naquadah
import gregtech.api.unification.material.Materials.NaquadahEnriched
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Nichrome
import gregtech.api.unification.material.Materials.Osmium
import gregtech.api.unification.material.Materials.RTMAlloy
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.unification.material.Materials.TinAlloy
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Trinium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.Tungsten
import gregtech.api.unification.material.Materials.TungstenCarbide
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.material.Materials.VanadiumSteel
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.screw
import gregtech.api.unification.ore.OrePrefix.wireGtDouble
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.common.block.adapter.GTWireCoil
import gregtechlite.gtlitecore.common.block.variant.WireCoil
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MICA_INSULATOR_FOIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.POLYMER_INSULATOR_FOIL

internal object WireCoilRecipes
{

    // @formatter:off

    fun init()
    {
        // Make wire coils easier than original recipes, these new recipes do not
        // need metal foils and instead of mica insulator foils.

        // Cupronickel Wire Coil
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireGtDouble, Cupronickel, 8),
                OreDictUnifier.get(foil, Bronze, 8)),
            arrayOf(TinAlloy.getFluid(L)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Cupronickel, 8)
            .input(MICA_INSULATOR_FOIL, 8)
            .fluidInputs(TinAlloy.getFluid(L))
            .outputs(GTWireCoil.CUPRONICKEL.stack)
            .EUt(VA[LV])
            .duration(10 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Cupronickel, 8)
            .input(POLYMER_INSULATOR_FOIL, 2)
            .fluidInputs(TinAlloy.getFluid(L))
            .outputs(GTWireCoil.CUPRONICKEL.stack)
            .EUt(VA[LV])
            .duration(2 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Kanthal Wire Coil
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireGtDouble, Kanthal, 8),
                OreDictUnifier.get(foil, Aluminium, 8)),
            arrayOf(Copper.getFluid(L)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Kanthal, 8)
            .input(MICA_INSULATOR_FOIL, 12)
            .fluidInputs(Copper.getFluid(L))
            .outputs(GTWireCoil.KANTHAL.stack)
            .EUt(VA[MV])
            .duration(15 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Kanthal, 8)
            .input(POLYMER_INSULATOR_FOIL, 3)
            .fluidInputs(Copper.getFluid(L))
            .outputs(GTWireCoil.KANTHAL.stack)
            .EUt(VA[MV])
            .duration(3 * SECOND + 15 * TICK)
            .buildAndRegister()

        // Nichrome Wire Coil
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireGtDouble, Nichrome, 8),
                OreDictUnifier.get(foil, StainlessSteel, 8)),
            arrayOf(Aluminium.getFluid(L)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Nichrome, 8)
            .input(MICA_INSULATOR_FOIL, 16)
            .fluidInputs(Aluminium.getFluid(L))
            .outputs(GTWireCoil.NICHROME.stack)
            .EUt(VA[HV])
            .duration(20 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Nichrome, 8)
            .input(POLYMER_INSULATOR_FOIL, 4)
            .fluidInputs(Aluminium.getFluid(L))
            .outputs(GTWireCoil.NICHROME.stack)
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // RTM Alloy Wire Coil
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireGtDouble, RTMAlloy, 8),
                OreDictUnifier.get(foil, VanadiumSteel, 8)),
            arrayOf(Nichrome.getFluid(L)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, RTMAlloy, 8)
            .input(MICA_INSULATOR_FOIL, 20)
            .fluidInputs(Titanium.getFluid(L))
            .outputs(GTWireCoil.RTM_ALLOY.stack)
            .EUt(VA[EV])
            .duration(25 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, RTMAlloy, 8)
            .input(POLYMER_INSULATOR_FOIL, 5)
            .fluidInputs(Titanium.getFluid(L))
            .outputs(GTWireCoil.RTM_ALLOY.stack)
            .EUt(VA[EV])
            .duration(6 * SECOND + 5 * TICK)
            .buildAndRegister()

        // HSS-G Wire Coil
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireGtDouble, HSSG, 8),
                OreDictUnifier.get(foil, TungstenCarbide, 8)),
            arrayOf(Tungsten.getFluid(L)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, HSSG, 8)
            .input(MICA_INSULATOR_FOIL, 24)
            .fluidInputs(Tungsten.getFluid(L))
            .outputs(GTWireCoil.HSS_G.stack)
            .EUt(VA[IV])
            .duration(30 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, HSSG, 8)
            .input(POLYMER_INSULATOR_FOIL, 6)
            .fluidInputs(Tungsten.getFluid(L))
            .outputs(GTWireCoil.HSS_G.stack)
            .EUt(VA[IV])
            .duration(7 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Naquadah Wire Coil
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireGtDouble, Naquadah, 8),
                OreDictUnifier.get(foil, Osmium, 8)),
            arrayOf(TungstenSteel.getFluid(L)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Naquadah, 8)
            .input(MICA_INSULATOR_FOIL, 28)
            .fluidInputs(TungstenSteel.getFluid(L))
            .outputs(GTWireCoil.NAQUADAH.stack)
            .EUt(VA[LuV])
            .duration(35 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Naquadah, 8)
            .input(POLYMER_INSULATOR_FOIL, 7)
            .fluidInputs(TungstenSteel.getFluid(L))
            .outputs(GTWireCoil.NAQUADAH.stack)
            .EUt(VA[LuV])
            .duration(8 * SECOND + 15 * TICK)
            .buildAndRegister()

        // Trinium Wire Coil
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireGtDouble, Trinium, 8),
                OreDictUnifier.get(foil, NaquadahEnriched, 8)),
            arrayOf(Naquadah.getFluid(L)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Trinium, 8)
            .input(MICA_INSULATOR_FOIL, 32)
            .fluidInputs(Naquadah.getFluid(L))
            .outputs(GTWireCoil.TRINIUM.stack)
            .EUt(VA[ZPM])
            .duration(40 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Trinium, 8)
            .input(POLYMER_INSULATOR_FOIL, 8)
            .fluidInputs(Naquadah.getFluid(L))
            .outputs(GTWireCoil.TRINIUM.stack)
            .EUt(VA[ZPM])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Tritanium Wire Coil
        GTRecipeHandler.removeRecipesByInputs(ASSEMBLER_RECIPES,
            arrayOf(OreDictUnifier.get(wireGtDouble, Tritanium, 8),
                OreDictUnifier.get(foil, Naquadria, 8)),
            arrayOf(Trinium.getFluid(L)))

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Tritanium, 8)
            .input(MICA_INSULATOR_FOIL, 36)
            .fluidInputs(NaquadahEnriched.getFluid(L))
            .outputs(GTWireCoil.TRITANIUM.stack)
            .EUt(VA[UV])
            .duration(45 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Tritanium, 8)
            .input(POLYMER_INSULATOR_FOIL, 9)
            .fluidInputs(NaquadahEnriched.getFluid(L))
            .outputs(GTWireCoil.TRITANIUM.stack)
            .EUt(VA[UV])
            .duration(11 * SECOND + 5 * TICK)
            .buildAndRegister()

        // Adamantium Wire Coil
        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Adamantium, 8)
            .input(MICA_INSULATOR_FOIL, 48)
            .fluidInputs(Naquadria.getFluid(L))
            .outputs(WireCoil.ADAMANTIUM.stack)
            .EUt(VA[UHV])
            .duration(50 * SECOND)
            .buildAndRegister()

        ASSEMBLER_RECIPES.recipeBuilder()
            .circuitMeta(8)
            .input(wireGtDouble, Adamantium, 8)
            .input(POLYMER_INSULATOR_FOIL, 12)
            .fluidInputs(Naquadria.getFluid(L))
            .outputs(WireCoil.ADAMANTIUM.stack)
            .EUt(VA[UHV])
            .duration(12 * SECOND + 10 * TICK)
            .buildAndRegister()

        // Infinity Wire Coil Block
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(circuit, Tier.UHV)
            .input(wireGtDouble, Infinity, 8)
            .input(screw, Infinity, 8)
            .input(MICA_INSULATOR_FOIL, 64)
            .fluidInputs(Adamantium.getFluid(L * 4))
            .outputs(WireCoil.INFINITY.stack)
            .EUt(VA[UEV])
            .duration(55 * SECOND)
            .stationResearch { r ->
                r.researchStack(WireCoil.ADAMANTIUM.stack)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(circuit, Tier.UHV)
            .input(wireGtDouble, Infinity, 8)
            .input(screw, Infinity, 8)
            .input(POLYMER_INSULATOR_FOIL, 16)
            .fluidInputs(Adamantium.getFluid(L * 4))
            .outputs(WireCoil.INFINITY.stack)
            .EUt(VA[UEV])
            .duration(13 * SECOND + 15 * TICK)
            .stationResearch { r ->
                r.researchStack(WireCoil.ADAMANTIUM.stack)
                    .EUt(VA[UHV])
                    .CWUt(32)
            }
            .buildAndRegister()

        // Halkonite Steel Wire Coil Block
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(circuit, Tier.UEV)
            .input(wireGtDouble, HalkoniteSteel, 8)
            .input(screw, HalkoniteSteel, 8)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .fluidInputs(Infinity.getFluid(L * 4))
            .outputs(WireCoil.HALKONITE_STEEL.stack)
            .EUt(VA[UIV])
            .duration(1 * MINUTE)
            .stationResearch { r ->
                r.researchStack(WireCoil.INFINITY.stack)
                    .EUt(VA[UEV])
                    .CWUt(48)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(circuit, Tier.UEV)
            .input(wireGtDouble, HalkoniteSteel, 8)
            .input(screw, HalkoniteSteel, 8)
            .input(POLYMER_INSULATOR_FOIL, 32)
            .fluidInputs(Infinity.getFluid(L * 4))
            .outputs(WireCoil.HALKONITE_STEEL.stack)
            .EUt(VA[UIV])
            .duration(15 * SECOND)
            .stationResearch { r ->
                r.researchStack(WireCoil.INFINITY.stack)
                    .EUt(VA[UEV])
                    .CWUt(48)
            }
            .buildAndRegister()

        // Space Time Wire Coil Block
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(circuit, Tier.UIV)
            .input(wireGtDouble, SpaceTime, 8)
            .input(screw, SpaceTime, 8)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .fluidInputs(HalkoniteSteel.getFluid(L * 4))
            .outputs(WireCoil.SPACE_TIME.stack)
            .EUt(VA[UXV])
            .duration(1 * MINUTE + 30 * SECOND)
            .stationResearch { r ->
                r.researchStack(WireCoil.HALKONITE_STEEL.stack)
                    .EUt(VA[UIV])
                    .CWUt(64)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(circuit, Tier.UIV)
            .input(wireGtDouble, SpaceTime, 8)
            .input(screw, SpaceTime, 8)
            .input(POLYMER_INSULATOR_FOIL, 64)
            .fluidInputs(HalkoniteSteel.getFluid(L * 4))
            .outputs(WireCoil.SPACE_TIME.stack)
            .EUt(VA[UXV])
            .duration(22 * SECOND + 10 * TICK)
            .stationResearch { r ->
                r.researchStack(WireCoil.HALKONITE_STEEL.stack)
                    .EUt(VA[UIV])
                    .CWUt(64)
            }
            .buildAndRegister()

        // Eternity Wire Coil Block
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(circuit, Tier.UXV)
            .input(wireGtDouble, Eternity, 8)
            .input(screw, Eternity, 8)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .input(MICA_INSULATOR_FOIL, 64)
            .fluidInputs(SpaceTime.getFluid(L * 4))
            .outputs(WireCoil.ETERNITY.stack)
            .EUt(VA[OpV])
            .duration(2 * MINUTE)
            .stationResearch { r ->
                r.researchStack(WireCoil.SPACE_TIME.stack)
                    .EUt(VA[UXV])
                    .CWUt(96)
            }
            .buildAndRegister()

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
            .input(circuit, Tier.UXV)
            .input(wireGtDouble, Eternity, 8)
            .input(screw, Eternity, 8)
            .input(POLYMER_INSULATOR_FOIL, 64)
            .input(POLYMER_INSULATOR_FOIL, 64)
            .fluidInputs(SpaceTime.getFluid(L * 4))
            .outputs(WireCoil.ETERNITY.stack)
            .EUt(VA[OpV])
            .duration(30 * SECOND)
            .stationResearch { r ->
                r.researchStack(WireCoil.SPACE_TIME.stack)
                    .EUt(VA[UXV])
                    .CWUt(96)
            }
            .buildAndRegister()
    }

    // @formatter:on

}