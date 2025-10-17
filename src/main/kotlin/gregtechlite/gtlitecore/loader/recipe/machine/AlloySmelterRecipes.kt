package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UHV
import gregtech.api.GTValues.UIV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.UXV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.ZPM
import gregtech.api.recipes.RecipeMaps.ALLOY_SMELTER_RECIPES
import gregtech.api.unification.material.Materials.Erbium
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.api.unification.material.Materials.Praseodymium
import gregtech.api.unification.material.Materials.RhodiumPlatedPalladium
import gregtech.api.unification.material.Materials.Titanium
import gregtech.api.unification.material.Materials.Tritanium
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.CosmicNeutronium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ErbiumDopedZBLANGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PraseodymiumDopedZBLANGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.TranscendentMetal
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZBLANGlass
import gregtechlite.gtlitecore.common.block.variant.GlassCasing

internal object AlloySmelterRecipes
{

    // @formatter:off

    fun init()
    {
        // Er-doped ZBLAN Glass
        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .input(ingot, ZBLANGlass)
            .input(ingot, Erbium)
            .output(ingot, ErbiumDopedZBLANGlass, 2)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .input(ingot, ZBLANGlass)
            .input(dust, Erbium)
            .output(ingot, ErbiumDopedZBLANGlass, 2)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .input(dust, ZBLANGlass)
            .input(ingot, Erbium)
            .output(ingot, ErbiumDopedZBLANGlass, 2)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .input(dust, ZBLANGlass)
            .input(dust, Erbium)
            .output(ingot, ErbiumDopedZBLANGlass, 2)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Pr-doped ZBLAN Glass
        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .input(ingot, ZBLANGlass)
            .input(ingot, Praseodymium)
            .output(ingot, PraseodymiumDopedZBLANGlass, 2)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .input(ingot, ZBLANGlass)
            .input(dust, Praseodymium)
            .output(ingot, PraseodymiumDopedZBLANGlass, 2)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .input(dust, ZBLANGlass)
            .input(ingot, Praseodymium)
            .output(ingot, PraseodymiumDopedZBLANGlass, 2)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .input(dust, ZBLANGlass)
            .input(dust, Praseodymium)
            .output(ingot, PraseodymiumDopedZBLANGlass, 2)
            .EUt(VA[HV])
            .duration(10 * SECOND)
            .buildAndRegister()

        // Titanium reinforced Borosilicate Glass
        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .inputs(GlassCasing.BOROSILICATE.stack)
            .input(plate, Titanium, 4)
            .outputs(GlassCasing.TITANIUM_BOROSILICATE.stack)
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Tungsten Steel reinforced Borosilicate Glass
        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .inputs(GlassCasing.BOROSILICATE.stack)
            .input(plate, TungstenSteel, 4)
            .outputs(GlassCasing.TUNGSTEN_STEEL_BOROSILICATE.stack)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Rhodium Plated Palladium reinforced Borosilicate Glass
        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .inputs(GlassCasing.BOROSILICATE.stack)
            .input(plate, RhodiumPlatedPalladium, 4)
            .outputs(GlassCasing.RHODIUM_PLATED_PALLADIUM_BOROSILICATE.stack)
            .EUt(VA[LuV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Osmiridium reinforced Borosilicate Glass
        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .inputs(GlassCasing.BOROSILICATE.stack)
            .input(plate, Osmiridium, 4)
            .outputs(GlassCasing.OSMIRIDIUM_BOROSILICATE.stack)
            .EUt(VA[ZPM])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Tritanium reinforced Borosilicate Glass
        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .inputs(GlassCasing.BOROSILICATE.stack)
            .input(plate, Tritanium, 4)
            .outputs(GlassCasing.TRITANIUM_BOROSILICATE.stack)
            .EUt(VA[UV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Neutronium reinforced Borosilicate Glass
        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .inputs(GlassCasing.BOROSILICATE.stack)
            .input(plate, Neutronium, 4)
            .outputs(GlassCasing.NEUTRONIUM_BOROSILICATE.stack)
            .EUt(VA[UHV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Cosmic Neutronium reinforced Borosilicate Glass
        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .inputs(GlassCasing.BOROSILICATE.stack)
            .input(plate, CosmicNeutronium, 4)
            .outputs(GlassCasing.COSMIC_NEUTRONIUM_BOROSILICATE.stack)
            .EUt(VA[UEV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Infinity reinforced Borosilicate Glass
        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .inputs(GlassCasing.BOROSILICATE.stack)
            .input(plate, Infinity, 4)
            .outputs(GlassCasing.INFINITY_BOROSILICATE.stack)
            .EUt(VA[UIV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Transcendent Metal reinforced Borosilicate Glass
        ALLOY_SMELTER_RECIPES.recipeBuilder()
            .inputs(GlassCasing.BOROSILICATE.stack)
            .input(plate, TranscendentMetal, 4)
            .outputs(GlassCasing.TRANSCENDENT_METAL_BOROSILICATE.stack)
            .EUt(VA[UXV])
            .duration(5 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}