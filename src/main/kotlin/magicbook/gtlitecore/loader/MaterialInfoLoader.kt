package magicbook.gtlitecore.loader

import gregtech.api.GTValues.M
import gregtech.api.GTValues.UEV
import gregtech.api.GTValues.UIV
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials
import gregtech.api.unification.stack.ItemMaterialInfo
import gregtech.api.unification.stack.MaterialStack
import gregtech.common.blocks.BlockWireCoil
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.unification.GTLiteMaterials
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockWireCoils
import magicbook.gtlitecore.loader.recipe.WoodRecipeLoader

@Suppress("MISSING_DEPENDENCY_CLASS")
class MaterialInfoLoader
{

    companion object
    {

        fun init()
        {
            // Add material info to sheetedFrameX ore prefix blocks.
            GTLiteMetaBlocks.SHEETED_FRAMES.entries.forEach { (m, b) ->
                if (m == Materials.NULL)
                    return@forEach
                b.getItem(m).let { stack ->
                    OreDictUnifier.registerOre(stack, GTLiteOrePrefix.sheetedFrame, m)
                    OreDictUnifier.registerOre(stack, ItemMaterialInfo(MaterialStack(m, 1)))
                }
            }

            // Add material info to wallGtX ore prefix blocks.
            GTLiteMetaBlocks.WALLS.entries.forEach { (m, b) ->
                if (m == Materials.NULL)
                    return@forEach
                b.getItem(m).let { stack ->
                    OreDictUnifier.registerOre(stack, GTLiteOrePrefix.wallGt, m)
                    OreDictUnifier.registerOre(stack, ItemMaterialInfo(MaterialStack(m, 1)))
                }
            }

            // Override all wire coil blocks material infos.
            OreDictUnifier.registerOre(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL),
                ItemMaterialInfo(MaterialStack(Materials.Cupronickel, M * 8),
                    MaterialStack(Materials.TinAlloy, M)))

            OreDictUnifier.registerOre(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.KANTHAL),
                ItemMaterialInfo(MaterialStack(Materials.Kanthal, M * 8),
                    MaterialStack(Materials.Copper, M)))

            OreDictUnifier.registerOre(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NICHROME),
                ItemMaterialInfo(MaterialStack(Materials.Nichrome, M * 8),
                    MaterialStack(Materials.Aluminium, M)))

            OreDictUnifier.registerOre(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.RTM_ALLOY),
                ItemMaterialInfo(MaterialStack(Materials.RTMAlloy, M * 8),
                    MaterialStack(Materials.Titanium, M)))

            OreDictUnifier.registerOre(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.HSS_G),
                ItemMaterialInfo(MaterialStack(Materials.HSSG, M * 8),
                    MaterialStack(Materials.Tungsten, M)))

            OreDictUnifier.registerOre(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NAQUADAH),
                ItemMaterialInfo(MaterialStack(Materials.Naquadah, M * 8),
                    MaterialStack(Materials.TungstenSteel, M)))

            OreDictUnifier.registerOre(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TRINIUM),
                ItemMaterialInfo(MaterialStack(Materials.Trinium, M * 8),
                    MaterialStack(Materials.Naquadah, M)))

            OreDictUnifier.registerOre(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TRITANIUM),
                ItemMaterialInfo(MaterialStack(Materials.Tritanium, M * 8),
                    MaterialStack(Materials.NaquadahEnriched, M)))

            OreDictUnifier.registerOre(GTLiteMetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoils.WireCoilType.ADAMANTIUM),
                ItemMaterialInfo(MaterialStack(GTLiteMaterials.Adamantium, M * 8),
                    MaterialStack(Materials.Naquadria, M)))

            OreDictUnifier.registerOre(GTLiteMetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoils.WireCoilType.INFINITY),
                ItemMaterialInfo(MaterialStack(GTLiteMaterials.Infinity, M * 8 + M), // 8x wireGtDoubleX + 8x screwX
                    MaterialStack(GTLiteMaterials.Adamantium, M * 4)))

            OreDictUnifier.registerOre(GTLiteMetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoils.WireCoilType.HALKONITE_STEEL),
                ItemMaterialInfo(MaterialStack(GTLiteMaterials.HalkoniteSteel, M * 8 + M), // 8x wireGtDoubleX + 8x screwX
                    MaterialStack(GTLiteMaterials.Infinity, M * 4)))

            // Add material infos for all machine hulls.
            OreDictUnifier.registerOre(MetaTileEntities.HULL[UEV].stackForm,
                ItemMaterialInfo(MaterialStack(GTLiteMaterials.Vibranium, M * 8), // 8x plateX
                    MaterialStack(Materials.Seaborgium, M), // 2x cableGtSingle
                    MaterialStack(Materials.Rubber, M * 2)))

            OreDictUnifier.registerOre(MetaTileEntities.HULL[UIV].stackForm,
                ItemMaterialInfo(MaterialStack(GTLiteMaterials.Shirabon, M * 8), // 8x plateX
                    MaterialStack(GTLiteMaterials.SuperheavyAlloyA, M), // 2x cableGtSingle
                    MaterialStack(Materials.Rubber, M * 2)))

            // Loading all wood related material infos.
            WoodRecipeLoader.initUnificationInfos()
        }

    }

}