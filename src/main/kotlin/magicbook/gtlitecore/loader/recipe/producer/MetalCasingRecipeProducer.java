package magicbook.gtlitecore.loader.recipe.producer;

import gregtech.api.block.VariantBlock;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing01;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02;
import net.minecraft.util.IStringSerializable;

import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.BlackSteel;
import static gregtech.api.unification.material.Materials.BlueSteel;
import static gregtech.api.unification.material.Materials.Iridium;
import static gregtech.api.unification.material.Materials.Potin;
import static gregtech.api.unification.material.Materials.RedSteel;
import static gregtech.api.unification.ore.OrePrefix.frameGt;
import static gregtech.api.unification.ore.OrePrefix.plate;
import static gregtech.api.unification.ore.OrePrefix.plateDouble;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.BabbitAlloy;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.EglinSteel;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.Grisium;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.HSLASteel;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.IncoloyMA813;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.IncoloyMA956;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.Inconel625;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.Kovar;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.MaragingSteel250;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.Monel500;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.SiliconCarbide;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.Staballoy;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.Stellite;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.Talonite;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.Tumbaga;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.WatertightSteel;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.Zeron100;
import static magicbook.gtlitecore.api.unification.GTLiteMaterials.ZirconiumCarbide;
import static magicbook.gtlitecore.api.utils.GTLiteValues.SECOND;
import static magicbook.gtlitecore.api.utils.GTLiteValues.TICK;

public class MetalCasingRecipeProducer
{

    public static void produce()
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

        // Talonite
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.TALONITE,
                Talonite);

        // Iridium
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.IRIDIUM,
                Iridium);

        // Zeron-100
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.ZERON_100,
                Zeron100);

        // Watertight Steel
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.WATERTIGHT_STEEL,
                WatertightSteel);

        // Stellite
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.STELLITE,
                Stellite);

        // Tumbaga
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.TUMBAGA,
                Tumbaga);

        // Eglin Steel
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.EGLIN_STEEL,
                EglinSteel);

        // Potin
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.POTIN,
                Potin);

        // Grisium
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.GRISIUM,
                Grisium);

        // Babbit Alloy
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.BABBIT_ALLOY,
                BabbitAlloy);

        // Silicon Carbide
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.SILICON_CARBIDE,
                SiliconCarbide);

        // Red Steel
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_01,
                BlockMetalCasing01.MetalCasingType.RED_STEEL,
                RedSteel);

        // HSLA Steel
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_02,
                BlockMetalCasing02.MetalCasingType.HSLA_STEEL,
                HSLASteel);

        // Kovar
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_02,
                BlockMetalCasing02.MetalCasingType.KOVAR,
                Kovar);

        // Black Steel
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_02,
                BlockMetalCasing02.MetalCasingType.BLACK_STEEL,
                BlackSteel);

        // Incoloy-MA813
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_02,
                BlockMetalCasing02.MetalCasingType.INCOLOY_MA813,
                IncoloyMA813);

        // Monel 500
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_02,
                BlockMetalCasing02.MetalCasingType.MONEL_500,
                Monel500);

        // Incoloy-MA956
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_02,
                BlockMetalCasing02.MetalCasingType.INCOLOY_MA956,
                IncoloyMA956);

        // Zirconium Carbide
        addCasingRecipe(GTLiteMetaBlocks.METAL_CASING_02,
                BlockMetalCasing02.MetalCasingType.ZIRCONIUM_CARBIDE,
                ZirconiumCarbide);

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
