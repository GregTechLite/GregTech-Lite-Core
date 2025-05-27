package magicbook.gtlitecore.loader.recipe.machine

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
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CosmicNeutronium
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ErbiumDopedZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Infinity
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PraseodymiumDopedZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.TranscendentMetal
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZBLANGlass
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing02

@Suppress("MISSING_DEPENDENCY_CLASS")
class AlloySmelterRecipes
{

    companion object
    {

        fun init()
        {
            // Er-doped ZBLAN Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(ingot, ZBLANGlass)
                .input(ingot, Erbium)
                .output(ingot, ErbiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(ingot, ZBLANGlass)
                .input(dust, Erbium)
                .output(ingot, ErbiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(dust, ZBLANGlass)
                .input(ingot, Erbium)
                .output(ingot, ErbiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(dust, ZBLANGlass)
                .input(dust, Erbium)
                .output(ingot, ErbiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Pr-doped ZBLAN Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(ingot, ZBLANGlass)
                .input(ingot, Praseodymium)
                .output(ingot, PraseodymiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(ingot, ZBLANGlass)
                .input(dust, Praseodymium)
                .output(ingot, PraseodymiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(dust, ZBLANGlass)
                .input(ingot, Praseodymium)
                .output(ingot, PraseodymiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .input(dust, ZBLANGlass)
                .input(dust, Praseodymium)
                .output(ingot, PraseodymiumDopedZBLANGlass, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .buildAndRegister()

            // Titanium reinforced Borosilicate Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.BOROSILICATE))
                .input(plate, Titanium, 4)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.TITANIUM_BOROSILICATE))
                .EUt(VA[EV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Tungsten Steel reinforced Borosilicate Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.BOROSILICATE))
                .input(plate, TungstenSteel, 4)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.TUNGSTEN_STEEL_BOROSILICATE))
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Rhodium Plated Palladium reinforced Borosilicate Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.BOROSILICATE))
                .input(plate, RhodiumPlatedPalladium, 4)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.RHODIUM_PLATED_PALLADIUM_BOROSILICATE))
                .EUt(VA[LuV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Osmiridium reinforced Borosilicate Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.BOROSILICATE))
                .input(plate, Osmiridium, 4)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.OSMIRIDIUM_BOROSILICATE))
                .EUt(VA[ZPM].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Tritanium reinforced Borosilicate Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.BOROSILICATE))
                .input(plate, Tritanium, 4)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.TRITANIUM_BOROSILICATE))
                .EUt(VA[UV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Neutronium reinforced Borosilicate Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.BOROSILICATE))
                .input(plate, Neutronium, 4)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.NEUTRONIUM_BOROSILICATE))
                .EUt(VA[UHV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Cosmic Neutronium reinforced Borosilicate Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.BOROSILICATE))
                .input(plate, CosmicNeutronium, 4)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.COSMIC_NEUTRONIUM_BOROSILICATE))
                .EUt(VA[UEV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Infinity reinforced Borosilicate Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.BOROSILICATE))
                .input(plate, Infinity, 4)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.INFINITY_BOROSILICATE))
                .EUt(VA[UIV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Transcendent Metal reinforced Borosilicate Glass
            ALLOY_SMELTER_RECIPES.recipeBuilder()
                .inputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.BOROSILICATE))
                .input(plate, TranscendentMetal, 4)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.TRANSCENDENT_METAL_BOROSILICATE))
                .EUt(VA[UXV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

        }

    }

}