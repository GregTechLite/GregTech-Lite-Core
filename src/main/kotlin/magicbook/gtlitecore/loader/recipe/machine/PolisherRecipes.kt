package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRUDER_RECIPES
import gregtech.api.unification.material.Materials.Andesite
import gregtech.api.unification.material.Materials.Basalt
import gregtech.api.unification.material.Materials.BorosilicateGlass
import gregtech.api.unification.material.Materials.Concrete
import gregtech.api.unification.material.Materials.Diorite
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Granite
import gregtech.api.unification.material.Materials.GraniteBlack
import gregtech.api.unification.material.Materials.GraniteRed
import gregtech.api.unification.material.Materials.Marble
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BLOCK
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.POLISHER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BlueSchist
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ErbiumDopedZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GSTGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.GreenSchist
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kimberlite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Komatiite
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Limestone
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Polymethylmethacrylate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PraseodymiumDopedZBLANGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Shale
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SiliconCarbide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Slate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.WoodsGlass
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ZBLANGlass
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.api.utils.Mods
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing01
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing02

@Suppress("MISSING_DEPENDENCY_CLASS")
class PolisherRecipes
{

    companion object
    {

        fun init()
        {
            // Change vanilla smooth stone -> polished stone recipes from autoclave to polisher.
            listOf(1, 3, 5).forEach { m -> // (1, 3, 5) => (Granite, Diorite, Andesite).
                listOf(Pair(Water, 200), Pair(DistilledWater, 36)).forEach { (f, c) ->
                    GTRecipeHandler.removeRecipesByInputs(AUTOCLAVE_RECIPES,
                        arrayOf(Mods.Minecraft.getMetaItem("stone", m)),
                        arrayOf(f.getFluid(c)))
                }
            }
            // Add polisher recipes to polished stones.
            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.Minecraft.getMetaItem("stone", 1))
                .outputs(Mods.Minecraft.getMetaItem("stone", 2))
                .output(dust, Granite, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.Minecraft.getMetaItem("stone", 3))
                .outputs(Mods.Minecraft.getMetaItem("stone", 4))
                .output(dust, Diorite, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.Minecraft.getMetaItem("stone", 4))
                .outputs(Mods.Minecraft.getMetaItem("stone", 5))
                .output(dust, Andesite, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            // Modified recipes of GregTech StoneVariantBlock conversions.
            for (m in 0 .. 5)
            {
                ModHandler.removeFurnaceSmelting(Mods.GregTech.getMetaItem("stone_smooth", m))
                GTRecipeHandler.removeRecipesByInputs(EXTRUDER_RECIPES,
                    Mods.GregTech.getMetaItem("stone_smooth", m),
                    SHAPE_EXTRUDER_BLOCK.stackForm)
            }
            // Add polisher recipes to gregtech polished stones.
            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTech.getMetaItem("stone_smooth", 0))
                .outputs(Mods.GregTech.getMetaItem("stone_polished", 0))
                .output(dust, GraniteBlack, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTech.getMetaItem("stone_smooth", 1))
                .outputs(Mods.GregTech.getMetaItem("stone_polished", 1))
                .output(dust, GraniteRed, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTech.getMetaItem("stone_smooth", 2))
                .outputs(Mods.GregTech.getMetaItem("stone_polished", 2))
                .output(dust, Marble, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTech.getMetaItem("stone_smooth", 3))
                .outputs(Mods.GregTech.getMetaItem("stone_polished", 3))
                .output(dust, Basalt, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTech.getMetaItem("stone_smooth", 4))
                .outputs(Mods.GregTech.getMetaItem("stone_polished", 4))
                .output(dust, Concrete, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTech.getMetaItem("stone_smooth", 5))
                .outputs(Mods.GregTech.getMetaItem("stone_polished", 5))
                .output(dust, Concrete, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            // Polished Limestone.
            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTechLiteCore.getMetaItem("stone_smooth", 0, 1))
                .outputs(Mods.GregTechLiteCore.getMetaItem("stone_polished", 0, 1))
                .output(dust, Limestone, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            // Polished Komatiite.
            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTechLiteCore.getMetaItem("stone_smooth", 1, 1))
                .outputs(Mods.GregTechLiteCore.getMetaItem("stone_polished", 1, 1))
                .output(dust, Komatiite, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            // Polished Green Schist.
            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTechLiteCore.getMetaItem("stone_smooth", 2, 1))
                .outputs(Mods.GregTechLiteCore.getMetaItem("stone_polished", 2, 1))
                .output(dust, GreenSchist, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            // Polished Blue Schist.
            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTechLiteCore.getMetaItem("stone_smooth", 3, 1))
                .outputs(Mods.GregTechLiteCore.getMetaItem("stone_polished", 3, 1))
                .output(dust, BlueSchist, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            // Polished Kimberlite.
            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTechLiteCore.getMetaItem("stone_smooth", 4, 1))
                .outputs(Mods.GregTechLiteCore.getMetaItem("stone_polished", 4, 1))
                .output(dust, Kimberlite, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            // Polished Quartzite.
            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTechLiteCore.getMetaItem("stone_smooth", 5, 1))
                .outputs(Mods.GregTechLiteCore.getMetaItem("stone_polished", 5, 1))
                .output(dust, Quartzite, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            // Polished Slate.
            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTechLiteCore.getMetaItem("stone_smooth", 6, 1))
                .outputs(Mods.GregTechLiteCore.getMetaItem("stone_polished", 6, 1))
                .output(dust, Slate, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            // Polished Shale.
            POLISHER_RECIPES.recipeBuilder()
                .inputs(Mods.GregTechLiteCore.getMetaItem("stone_smooth", 7, 1))
                .outputs(Mods.GregTechLiteCore.getMetaItem("stone_polished", 7, 1))
                .output(dust, Shale, 2)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            // Borosilicate Glass
            POLISHER_RECIPES.recipeBuilder()
                .input(block, BorosilicateGlass)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_02.getItemVariant(BlockGlassCasing02.GlassType.BOROSILICATE, 2))
                .output(dust, BorosilicateGlass)
                .EUt(VA[HV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Silicon Carbide Glass
            POLISHER_RECIPES.recipeBuilder()
                .input(block, SiliconCarbide)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.SILICON_CARBIDE, 2))
                .output(dust, SiliconCarbide)
                .EUt(VA[EV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Woods Glass
            POLISHER_RECIPES.recipeBuilder()
                .input(block, WoodsGlass)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.WOODS, 2))
                .output(dust, WoodsGlass)
                .EUt(VA[EV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // ZBLAN Glass
            POLISHER_RECIPES.recipeBuilder()
                .input(block, ZBLANGlass)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.ZBLAN, 2))
                .output(dust, ZBLANGlass)
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Er-doped ZBLAN Glass
            POLISHER_RECIPES.recipeBuilder()
                .input(block, ErbiumDopedZBLANGlass)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.ERBIUM_ZBLAN, 2))
                .output(dust, ErbiumDopedZBLANGlass)
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // Pr-doped ZBLAN Glass
            POLISHER_RECIPES.recipeBuilder()
                .input(block, PraseodymiumDopedZBLANGlass)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.PRASEODYMIUM_ZBLAN, 2))
                .output(dust, PraseodymiumDopedZBLANGlass)
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // GST Glass
            POLISHER_RECIPES.recipeBuilder()
                .input(block, GSTGlass)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.GST, 2))
                .output(dust, GSTGlass)
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

            // PMMA Glass
            POLISHER_RECIPES.recipeBuilder()
                .input(block, Polymethylmethacrylate)
                .outputs(GTLiteMetaBlocks.TRANSPARENT_CASING_01.getItemVariant(BlockGlassCasing01.GlassType.PMMA, 2))
                .output(dust, Polymethylmethacrylate)
                .EUt(VA[IV].toLong())
                .duration(5 * SECOND)
                .buildAndRegister()

        }

    }

}