package magicbook.gtlitecore.loader.recipe.producer

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
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.ore.OrePrefix.craftingLens
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_BLOCK
import gregtech.common.items.MetaItems.SHAPE_EXTRUDER_INGOT
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.GTLiteStoneVariantBlock
import net.minecraft.item.ItemStack

// TODO Rebuild to StoneTypeEntry + StoneRecipeLoader?
@Suppress("MISSING_DEPENDENCY_CLASS")
class StoneVariantRecipeProducer
{

    companion object
    {

        fun init()
        {
            
            for (variant: GTLiteStoneVariantBlock.StoneType in GTLiteStoneVariantBlock.StoneType.entries)
            {
                val stoneSmooth: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.SMOOTH]!!.getItemVariant(variant)
                val stonePolished: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.POLISHED]!!.getItemVariant(variant)
                val stoneChiseled: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.CHISELED]!!.getItemVariant(variant)
                val cobblestone: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.COBBLE]!!.getItemVariant(variant)
                val cobblestoneMossy: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.COBBLE_MOSSY]!!.getItemVariant(variant)
                val bricks: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.BRICKS]!!.getItemVariant(variant)
                val bricksCracked: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.BRICKS_CRACKED]!!.getItemVariant(
                        variant
                    )
                val bricksMossy: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.BRICKS_MOSSY]!!.getItemVariant(variant)
                val bricksSmall: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.BRICKS_SMALL]!!.getItemVariant(variant)
                val bricksSquare: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.BRICKS_SQUARE]!!.getItemVariant(variant)
                val tiles: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.TILED]!!.getItemVariant(variant)
                val tilesSmall: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.TILED_SMALL]!!.getItemVariant(variant)
                val tilesWindmillA: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.WINDMILL_A]!!.getItemVariant(variant)
                val tilesWindmillB: ItemStack =
                    GTLiteMetaBlocks.STONES[GTLiteStoneVariantBlock.StoneVariant.WINDMILL_B]!!.getItemVariant(variant)

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
                    .EUt(VHA[HV].toLong())
                    .duration(16 * TICK)
                    .buildAndRegister()

                // Smooth stone hamming to cobblestone.
                FORGE_HAMMER_RECIPES.recipeBuilder()
                    .inputs(stoneSmooth)
                    .outputs(cobblestone)
                    .EUt(VH[ULV].toLong())
                    .duration(12 * TICK)
                    .buildAndRegister()

                // Cobblestone -> Mossy Cobblestone
                CHEMICAL_BATH_RECIPES.recipeBuilder()
                    .inputs(cobblestone)
                    .fluidInputs(Water.getFluid(100))
                    .outputs(cobblestoneMossy)
                    .EUt(VH[LV].toLong())
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
                    .notConsumable(craftingLens, MarkerMaterials.Color.LightBlue)
                    .inputs(stonePolished)
                    .outputs(bricks)
                    .EUt(VH[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()

                // Bricks hamming to cracked bricks.
                FORGE_HAMMER_RECIPES.recipeBuilder()
                    .inputs(bricks)
                    .outputs(bricksCracked)
                    .EUt(VH[ULV].toLong())
                    .duration(12 * TICK)
                    .buildAndRegister()

                // Bricks -> Mossy Bricks
                CHEMICAL_BATH_RECIPES.recipeBuilder()
                    .inputs(bricks)
                    .fluidInputs(Water.getFluid(100))
                    .outputs(bricksMossy)
                    .EUt(VH[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()

                // Polished stone laser engraving to chiseled stone.
                LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(craftingLens, MarkerMaterials.Color.White)
                    .inputs(stonePolished)
                    .outputs(stoneChiseled)
                    .EUt(VH[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()

                // Polished stone laser engraving to tiles.
                LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(craftingLens, MarkerMaterials.Color.Red)
                    .inputs(stonePolished)
                    .outputs(tiles)
                    .EUt(VH[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()

                // Tiles laser engraving to small tiles
                LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(craftingLens, MarkerMaterials.Color.Red)
                    .inputs(tiles)
                    .outputs(tilesSmall)
                    .EUt(VH[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()

                // Polished stone laser engraving to small bricks.
                LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(craftingLens, MarkerMaterials.Color.Pink)
                    .inputs(stonePolished)
                    .outputs(bricksSmall)
                    .EUt(VH[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()

                // Polished stone laser engraving to windmill tiles A.
                LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(craftingLens, MarkerMaterials.Color.Blue)
                    .inputs(stonePolished)
                    .outputs(tilesWindmillA)
                    .EUt(VH[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()

                // Polished stone laser engraving to windmill tiles B.
                LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(craftingLens, MarkerMaterials.Color.Yellow)
                    .inputs(stonePolished)
                    .outputs(tilesWindmillB)
                    .EUt(VH[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()

                // Polished stone laser engraving to square bricks.
                LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .notConsumable(craftingLens, MarkerMaterials.Color.Green)
                    .inputs(stonePolished)
                    .outputs(bricksSquare)
                    .EUt(VH[LV].toLong())
                    .duration(2 * SECOND + 10 * TICK)
                    .buildAndRegister()
            }
        }
    }

}