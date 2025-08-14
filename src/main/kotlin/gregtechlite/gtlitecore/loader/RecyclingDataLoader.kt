package gregtechlite.gtlitecore.loader

import gregtech.api.GTValues.*
import gregtech.api.GregTechAPI
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.*
import gregtech.api.unification.stack.MaterialStack
import gregtech.api.unification.stack.RecyclingData
import gregtech.common.blocks.BlockWireCoil
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Adamantium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bitumen
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.BlackDwarfMatter
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Creon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Eternity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HalkoniteSteel
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Infinity
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Periodicium
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Shirabon
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SpaceTime
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyA
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.SuperheavyAlloyB
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vibranium
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix
import gregtechlite.gtlitecore.common.block.GTLiteMetaBlocks
import gregtechlite.gtlitecore.common.block.variant.WireCoil
import net.minecraft.item.ItemStack

internal object RecyclingDataLoader
{

    // @formatter:off

    fun init()
    {

        // Wire Coils recycling datas.
        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.CUPRONICKEL),
            RecyclingData(MaterialStack(Cupronickel, M * 8),
                                  MaterialStack(TinAlloy, M)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.KANTHAL),
            RecyclingData(MaterialStack(Kanthal, M * 8),
                                  MaterialStack(Copper, M)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NICHROME),
            RecyclingData(MaterialStack(Nichrome, M * 8),
                                  MaterialStack(Aluminium, M)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.RTM_ALLOY),
            RecyclingData(MaterialStack(RTMAlloy, M * 8),
                                  MaterialStack(Titanium, M)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.HSS_G),
            RecyclingData(MaterialStack(HSSG, M * 8),
                                  MaterialStack(Tungsten, M)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.NAQUADAH),
            RecyclingData(MaterialStack(Naquadah, M * 8),
                                  MaterialStack(TungstenSteel, M)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TRINIUM),
            RecyclingData(MaterialStack(Trinium, M * 8),
                                  MaterialStack(Naquadah, M)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.TRITANIUM),
            RecyclingData(MaterialStack(Tritanium, M * 8),
                                  MaterialStack(NaquadahEnriched, M)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(WireCoil.ADAMANTIUM.stack,
            RecyclingData(MaterialStack(Adamantium, M * 8),
                                  MaterialStack(Naquadria, M)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(WireCoil.INFINITY.stack,
            RecyclingData(MaterialStack(Infinity, M * 8 + M), // 8x wireGtDoubleX + 8x screwX
                                  MaterialStack(Adamantium, M * 4)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(WireCoil.HALKONITE_STEEL.stack,
            RecyclingData(MaterialStack(HalkoniteSteel, M * 8 + M), // 8x wireGtDoubleX + 8x screwX
                                  MaterialStack(Infinity, M * 4)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(WireCoil.SPACE_TIME.stack, // Space Time Wire Coil cannot recycle Halkonite Steel.
            RecyclingData(MaterialStack(SpaceTime, M * 8 + M))) // 8x wireGtDoubleX + 8x screwX

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(WireCoil.ETERNITY.stack,
            RecyclingData(MaterialStack(Eternity, M * 8 + M), // 8x wireGtDoubleX + 8x screwX
                                  MaterialStack(SpaceTime, M * 4)))

        // High-tier Machine Hulls recycling datas.
        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(MetaTileEntities.HULL[UEV].stackForm,
            RecyclingData(MaterialStack(Vibranium, M * 8), // 8x plateX
                          MaterialStack(Seaborgium, M), // 2x cableGtSingle
                          MaterialStack(Rubber, M * 2)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(MetaTileEntities.HULL[UIV].stackForm,
            RecyclingData(MaterialStack(Shirabon, M * 8), // 8x plateX
                          MaterialStack(SuperheavyAlloyA, M), // 2x cableGtSingle
                          MaterialStack(Rubber, M * 2)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(MetaTileEntities.HULL[UXV].stackForm,
            RecyclingData(MaterialStack(Creon, M * 8), // 8x plateX
                          MaterialStack(SuperheavyAlloyB, M), // 2x cableGtSingle
                          MaterialStack(Rubber, M * 2)))

        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(MetaTileEntities.HULL[OpV].stackForm,
            RecyclingData(MaterialStack(BlackDwarfMatter, M * 8), // 8x plateX
                          MaterialStack(Periodicium, M), // 2x cableGtSingle
                          MaterialStack(Rubber, M * 2)))

        // Asphalt recycling data.
        GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(ItemStack(MetaBlocks.ASPHALT),
            RecyclingData(MaterialStack(Bitumen, M * 4)))

        // Sheeted Frames recycling datas.
        GTLiteMetaBlocks.SHEETED_FRAMES.entries.forEach { (material, block) ->
            if (material == NULL) return@forEach
            block.getItem(material).let { stack ->
                OreDictUnifier.registerOre(stack, GTLiteOrePrefix.sheetedFrame, material)
                GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(stack,
                    RecyclingData(MaterialStack(material, (M * 5) / 6)))
            }
        }

        // Metal Walls recycling datas.
        GTLiteMetaBlocks.METAL_WALLS.entries.forEach { (material, block) ->
            if (material == NULL) return@forEach
            block.getItem(material).let { stack ->
                OreDictUnifier.registerOre(stack, GTLiteOrePrefix.wallGt, material)
                GregTechAPI.RECYCLING_MANAGER.registerRecyclingData(stack,
                    RecyclingData(MaterialStack(material, (M * 4 + (M / 9) * 4) / 3)))
            }
        }

    }

    // @formatter:on

}