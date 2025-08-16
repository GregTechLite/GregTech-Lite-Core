package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VH
import gregtech.api.GTValues.VHA
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRUDER_RECIPES
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.recipes.RecipeMaps.LASER_ENGRAVER_RECIPES
import gregtech.api.recipes.RecipeMaps.ROCK_BREAKER_RECIPES
import gregtech.api.unification.material.MarkerMaterials.Color
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.craftingLens
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BLOCK
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_INGOT
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import gregtechlite.gtlitecore.common.block.GTLiteStoneVariantBlock

// TODO Rebuild to StoneTypeEntry + StoneRecipeLoader?
internal object StoneVariantRecipeProducer
{

    // @formatter:off

    fun produce()
    {
            
        for (variant in GTLiteStoneVariantBlock.StoneType.entries)
        {
            val stoneSmooth = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.SMOOTH]!!.getItemVariant(variant)
            val stonePolished = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.POLISHED]!!.getItemVariant(variant)
            val stoneChiseled = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.CHISELED]!!.getItemVariant(variant)
            val cobblestone = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.COBBLE]!!.getItemVariant(variant)
            val cobblestoneMossy = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.COBBLE_MOSSY]!!.getItemVariant(variant)
            val bricks = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.BRICKS]!!.getItemVariant(variant)
            val bricksCracked = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.BRICKS_CRACKED]!!.getItemVariant(variant)
            val bricksMossy = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.BRICKS_MOSSY]!!.getItemVariant(variant)
            val bricksSmall = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.BRICKS_SMALL]!!.getItemVariant(variant)
            val bricksSquare = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.BRICKS_SQUARE]!!.getItemVariant(variant)
            val tiles = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.TILED]!!.getItemVariant(variant)
            val tilesSmall = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.TILED_SMALL]!!.getItemVariant(variant)
            val tilesWindmillA = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.WINDMILL_A]!!.getItemVariant(variant)
            val tilesWindmillB = GTLiteBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.WINDMILL_B]!!.getItemVariant(variant)

            // Cobblestone smelting to smooth stone.
            ModHandler.addSmeltingRecipe(cobblestone, stoneSmooth)

            // Cobblestone extruding to smooth stone.
            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_BLOCK)
                .inputs(cobblestone)
                .outputs(stoneSmooth)
                .EUt(V[ULV])
                .duration(1 * SECOND + 4 * TICK)
                .buildAndRegister()

            // Rock Breaker make smooth stone.
            ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(stoneSmooth)
                .outputs(stoneSmooth)
                .EUt(VHA[HV])
                .duration(16 * TICK)
                .buildAndRegister()

            // Smooth stone hamming to cobblestone.
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .inputs(stoneSmooth)
                .outputs(cobblestone)
                .EUt(VH[ULV])
                .duration(12 * TICK)
                .buildAndRegister()

            // Cobblestone -> Mossy Cobblestone
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .inputs(cobblestone)
                .fluidInputs(Water.getFluid(100))
                .outputs(cobblestoneMossy)
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Smooth stone polishing to polished stone, see: PolisherRecipes#init().

            // Smooth stone extruding to bricks.
            EXTRUDER_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_EXTRUDER_INGOT)
                .inputs(stoneSmooth)
                .outputs(bricks)
                .EUt(V[ULV])
                .duration(SECOND + 4 * TICK)
                .buildAndRegister()

            // Polished stone laser engraving to bricks.
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, Color.LightBlue)
                .inputs(stonePolished)
                .outputs(bricks)
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Bricks hamming to cracked bricks.
            FORGE_HAMMER_RECIPES.recipeBuilder()
                .inputs(bricks)
                .outputs(bricksCracked)
                .EUt(VH[ULV])
                .duration(12 * TICK)
                .buildAndRegister()

            // Bricks -> Mossy Bricks
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .inputs(bricks)
                .fluidInputs(Water.getFluid(100))
                .outputs(bricksMossy)
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Polished stone laser engraving to chiseled stone.
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, Color.White)
                .inputs(stonePolished)
                .outputs(stoneChiseled)
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Polished stone laser engraving to tiles.
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, Color.Red)
                .inputs(stonePolished)
                .outputs(tiles)
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Tiles laser engraving to small tiles
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, Color.Red)
                .inputs(tiles)
                .outputs(tilesSmall)
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Polished stone laser engraving to small bricks.
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, Color.Pink)
                .inputs(stonePolished)
                .outputs(bricksSmall)
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Polished stone laser engraving to windmill tiles A.
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, Color.Blue)
                .inputs(stonePolished)
                .outputs(tilesWindmillA)
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Polished stone laser engraving to windmill tiles B.
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, Color.Yellow)
                .inputs(stonePolished)
                .outputs(tilesWindmillB)
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Polished stone laser engraving to square bricks.
            LASER_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, Color.Green)
                .inputs(stonePolished)
                .outputs(bricksSquare)
                .EUt(VH[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()
        }
    }

    // @formatter:on

}