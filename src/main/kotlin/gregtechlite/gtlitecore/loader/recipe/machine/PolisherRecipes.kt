package gregtechlite.gtlitecore.loader.recipe.machine

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
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.POLISHER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlueSchist
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ErbiumDopedZBLANGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GSTGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.GreenSchist
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kimberlite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Komatiite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Limestone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Polymethylmethacrylate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.PraseodymiumDopedZBLANGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shale
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SiliconCarbide
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Slate
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.WoodsGlass
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.ZBLANGlass
import gregtechlite.gtlitecore.common.block.adapter.GTPolishedStoneBlock
import gregtechlite.gtlitecore.common.block.adapter.GTSmoothStoneBlock
import gregtechlite.gtlitecore.common.block.adapter.PolishedStoneBlock
import gregtechlite.gtlitecore.common.block.adapter.SmoothStoneBlock
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

internal object PolisherRecipes
{

    // @formatter:off

    fun init()
    {
        // Change vanilla smooth stone -> polished stone recipes from autoclave to polisher.
        listOf(1, 3, 5).forEach { m -> // (1, 3, 5) => (Granite, Diorite, Andesite).
            listOf(Pair(Water, 200), Pair(DistilledWater, 36)).forEach { (f, c) ->
                GTRecipeHandler.removeRecipesByInputs(AUTOCLAVE_RECIPES,
                    arrayOf(ItemStack(Blocks.STONE, 1, m)),
                    arrayOf(f.getFluid(c)))
            }
        }

        // Add polisher recipes to polished stones.
        POLISHER_RECIPES.recipeBuilder()
            .inputs(ItemStack(Blocks.STONE, 1, 1))
            .outputs(ItemStack(Blocks.STONE, 1, 2))
            .output(dust, Granite, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        POLISHER_RECIPES.recipeBuilder()
            .inputs(ItemStack(Blocks.STONE, 1, 3))
            .outputs(ItemStack(Blocks.STONE, 1, 4))
            .output(dust, Diorite, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        POLISHER_RECIPES.recipeBuilder()
            .inputs(ItemStack(Blocks.STONE, 1, 5))
            .outputs(ItemStack(Blocks.STONE, 1, 6))
            .output(dust, Andesite, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Modified recipes of GregTech StoneVariantBlock conversions.
        GTSmoothStoneBlock.entries.forEach {
            ModHandler.removeFurnaceSmelting(it.stack)
            GTRecipeHandler.removeRecipesByInputs(EXTRUDER_RECIPES, it.stack, SHAPE_EXTRUDER_BLOCK.stackForm)
        }

        // Add polisher recipes to gregtech polished stones.

        // Polished Black Granite
        POLISHER_RECIPES.recipeBuilder()
            .inputs(GTSmoothStoneBlock.BLACK_GRANITE.stack)
            .outputs(GTPolishedStoneBlock.BLACK_GRANITE.stack)
            .output(dust, GraniteBlack, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Red Granite
        POLISHER_RECIPES.recipeBuilder()
            .inputs(GTSmoothStoneBlock.RED_GRANITE.stack)
            .outputs(GTPolishedStoneBlock.RED_GRANITE.stack)
            .output(dust, GraniteRed, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Marble
        POLISHER_RECIPES.recipeBuilder()
            .inputs(GTSmoothStoneBlock.MARBLE.stack)
            .outputs(GTPolishedStoneBlock.MARBLE.stack)
            .output(dust, Marble, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Basalt
        POLISHER_RECIPES.recipeBuilder()
            .inputs(GTSmoothStoneBlock.BASALT.stack)
            .outputs(GTPolishedStoneBlock.BASALT.stack)
            .output(dust, Basalt, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Light Concrete
        POLISHER_RECIPES.recipeBuilder()
            .inputs(GTSmoothStoneBlock.CONCRETE_LIGHT.stack)
            .outputs(GTPolishedStoneBlock.CONCRETE_LIGHT.stack)
            .output(dust, Concrete, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Dark Concrete
        POLISHER_RECIPES.recipeBuilder()
            .inputs(GTSmoothStoneBlock.CONCRETE_DARK.stack)
            .outputs(GTPolishedStoneBlock.CONCRETE_DARK.stack)
            .output(dust, Concrete, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Limestone
        POLISHER_RECIPES.recipeBuilder()
            .inputs(SmoothStoneBlock.LIMESTONE.stack)
            .outputs(PolishedStoneBlock.LIMESTONE.stack)
            .output(dust, Limestone, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Komatiite
        POLISHER_RECIPES.recipeBuilder()
            .inputs(SmoothStoneBlock.KOMATIITE.stack)
            .outputs(PolishedStoneBlock.KOMATIITE.stack)
            .output(dust, Komatiite, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Green Schist
        POLISHER_RECIPES.recipeBuilder()
            .inputs(SmoothStoneBlock.GREEN_SCHIST.stack)
            .outputs(PolishedStoneBlock.GREEN_SCHIST.stack)
            .output(dust, GreenSchist, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Blue Schist
        POLISHER_RECIPES.recipeBuilder()
            .inputs(SmoothStoneBlock.BLUE_SCHIST.stack)
            .outputs(PolishedStoneBlock.BLUE_SCHIST.stack)
            .output(dust, BlueSchist, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Kimberlite
        POLISHER_RECIPES.recipeBuilder()
            .inputs(SmoothStoneBlock.KIMBERLITE.stack)
            .outputs(PolishedStoneBlock.KIMBERLITE.stack)
            .output(dust, Kimberlite, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Quartzite
        POLISHER_RECIPES.recipeBuilder()
            .inputs(SmoothStoneBlock.QUARTZITE.stack)
            .outputs(PolishedStoneBlock.QUARTZITE.stack)
            .output(dust, Quartzite, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Slate
        POLISHER_RECIPES.recipeBuilder()
            .inputs(SmoothStoneBlock.SLATE.stack)
            .outputs(PolishedStoneBlock.SLATE.stack)
            .output(dust, Slate, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Polished Shale
        POLISHER_RECIPES.recipeBuilder()
            .inputs(SmoothStoneBlock.SHALE.stack)
            .outputs(PolishedStoneBlock.SHALE.stack)
            .output(dust, Shale, 2)
            .EUt(V[ULV])
            .duration(1 * SECOND + 4 * TICK)
            .buildAndRegister()

        // Borosilicate Glass
        POLISHER_RECIPES.recipeBuilder()
            .input(block, BorosilicateGlass)
            .outputs(GlassCasing.BOROSILICATE.getStack(2))
            .output(dust, BorosilicateGlass)
            .EUt(VA[HV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Silicon Carbide Glass
        POLISHER_RECIPES.recipeBuilder()
            .input(block, SiliconCarbide)
            .outputs(GlassCasing.SILICON_CARBIDE.getStack(2))
            .output(dust, SiliconCarbide)
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Woods Glass
        POLISHER_RECIPES.recipeBuilder()
            .input(block, WoodsGlass)
            .outputs(GlassCasing.WOODS.getStack(2))
            .output(dust, WoodsGlass)
            .EUt(VA[EV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // ZBLAN Glass
        POLISHER_RECIPES.recipeBuilder()
            .input(block, ZBLANGlass)
            .outputs(GlassCasing.ZBLAN.getStack(2))
            .output(dust, ZBLANGlass)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Er-doped ZBLAN Glass
        POLISHER_RECIPES.recipeBuilder()
            .input(block, ErbiumDopedZBLANGlass)
            .outputs(GlassCasing.ERBIUM_ZBLAN.getStack(2))
            .output(dust, ErbiumDopedZBLANGlass)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // Pr-doped ZBLAN Glass
        POLISHER_RECIPES.recipeBuilder()
            .input(block, PraseodymiumDopedZBLANGlass)
            .outputs(GlassCasing.PRASEODYMIUM_ZBLAN.getStack(2))
            .output(dust, PraseodymiumDopedZBLANGlass)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // GST Glass
        POLISHER_RECIPES.recipeBuilder()
            .input(block, GSTGlass)
            .outputs(GlassCasing.GST.getStack(2))
            .output(dust, GSTGlass)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

        // PMMA Glass
        POLISHER_RECIPES.recipeBuilder()
            .input(block, Polymethylmethacrylate)
            .outputs(GlassCasing.PMMA.getStack(2))
            .output(dust, Polymethylmethacrylate)
            .EUt(VA[IV])
            .duration(5 * SECOND)
            .buildAndRegister()

    }

    // @formatter:on

}