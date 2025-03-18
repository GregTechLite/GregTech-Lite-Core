package magicbook.gtlitecore.loader.recipe.producer;

import gregtech.api.block.VariantBlock;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing01;
import net.minecraft.util.IStringSerializable;

import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.BlueSteel;
import static gregtech.api.unification.ore.OrePrefix.frameGt;
import static gregtech.api.unification.ore.OrePrefix.plate;
import static gregtech.api.unification.ore.OrePrefix.plateDouble;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.Inconel625;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.MaragingSteel250;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.Staballoy;
import static magicbook.gtlitecore.api.utils.GTLiteValues.SECOND;
import static magicbook.gtlitecore.api.utils.GTLiteValues.TICK;

public class MetalCasingRecipeProducer
{

    public static void init()
    {
        // Maraging Steel 250
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.MARAGING_STEEL_250,
                MaragingSteel250);

        // Inconel-625
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.INCONEL_625,
                Inconel625);

        // Blue Steel
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.BLUE_STEEL,
                BlueSteel);

        // Staballoy
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.STABALLOY,
                Staballoy);

    }

    private static <T extends Enum<T> & IStringSerializable> void addCasingRecipe(VariantBlock<T> outputCasingType,
                                                                                  T outputCasing,
                                                                                  Material material)
    {
        ModHandler.addShapedRecipe(true, material.getName().toLowerCase() + "_casing", outputCasingType.getItemVariant(outputCasing, ConfigHolder.recipes.casingsPerCraft),
                "PhP", "PFP","PwP",
                'P', new UnificationEntry(plate, material),
                'F', new UnificationEntry(frameGt, material));

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, material, 6)
                .input(frameGt, material)
                .circuitMeta(6)
                .outputs(outputCasingType.getItemVariant(outputCasing, ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV])
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister();
    }

    private static <T extends Enum<T> & IStringSerializable> void addCasingRecipe(String regName,
                                                                                 VariantBlock<T> outputCasingType,
                                                                                 T outputCasing,
                                                                                 Material plateMaterial,
                                                                                 Material frameMaterial)
    {
        ModHandler.addShapedRecipe(true, regName, outputCasingType.getItemVariant(outputCasing, gregtech.common.ConfigHolder.recipes.casingsPerCraft),
                "PhP", "PFP","PwP",
                'P', new UnificationEntry(plate, plateMaterial),
                'F', new UnificationEntry(frameGt, frameMaterial));

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, plateMaterial, 6)
                .input(frameGt, frameMaterial)
                .circuitMeta(6)
                .outputs(outputCasingType.getItemVariant(outputCasing, gregtech.common.ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV])
                .duration(50)
                .buildAndRegister();
    }

    private static <T extends Enum<T> & IStringSerializable> void addCasingRecipe(String regName,
                                                                                 VariantBlock<T> outputCasingType,
                                                                                 T outputCasing,
                                                                                 Material plateDoubleMaterial,
                                                                                 Material plateMaterial,
                                                                                 Material frameMaterial)
    {
        ModHandler.addShapedRecipe(true, regName, outputCasingType.getItemVariant(outputCasing, gregtech.common.ConfigHolder.recipes.casingsPerCraft),
                "PhP", "TFT","PwP",
                'P', new UnificationEntry(plateDouble, plateDoubleMaterial),
                'T', new UnificationEntry(plate, plateMaterial),
                'F', new UnificationEntry(frameGt, frameMaterial));

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plateDouble, plateDoubleMaterial, 4)
                .input(plate, plateMaterial, 2)
                .input(frameGt, frameMaterial)
                .circuitMeta(6)
                .outputs(outputCasingType.getItemVariant(outputCasing, gregtech.common.ConfigHolder.recipes.casingsPerCraft))
                .EUt(VA[LV])
                .duration(50)
                .buildAndRegister();
    }

}
