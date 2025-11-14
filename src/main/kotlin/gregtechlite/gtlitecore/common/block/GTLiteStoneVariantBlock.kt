package gregtechlite.gtlitecore.common.block

import gregtech.api.block.IStateSpawnControl
import gregtech.api.block.VariantBlock
import gregtech.api.unification.material.Materials
import gregtech.api.unification.ore.OrePrefix
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.util.IStringSerializable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*
import gregtech.api.unification.material.Material as GTMaterial

// todo oreDic generation?
class GTLiteStoneVariantBlock(val stoneVariant: StoneVariant) : VariantBlock<GTLiteStoneVariantBlock.StoneType>(Material.ROCK)
{

    init
    {
        setRegistryName(stoneVariant.id)
        setTranslationKey("$MOD_ID.${stoneVariant.translationKey}")
        setHardness(stoneVariant.hardness)
        setResistance(stoneVariant.resistance)
        setSoundType(SoundType.STONE)
        setHarvestLevel("pickaxe", 0)
        defaultState = getState(StoneType.LIMESTONE)
        setCreativeTab(GTLiteCreativeTabs.TAB_DECORATION)
    }

    @Deprecated("Deprecated in Java")
    override fun canSilkHarvest(): Boolean
        = this.stoneVariant == StoneVariant.SMOOTH

    override fun getItemDropped(state: IBlockState, rand: Random, fortune: Int): Item
    {
        return if (this.stoneVariant == StoneVariant.SMOOTH)
            Item.getItemFromBlock(GTLiteBlocks.STONES[StoneVariant.COBBLE]!!)
        else
            Item.getItemFromBlock(this)
    }

    /**
     * These rock types referenced to GT6, thanks gregorius create these stones. We modified the components with reality
     * world correspondenced rock components, and modified textures with GTCEu style.
     */
    enum class StoneType(private val serialName: String,
                         mapColor: MapColor,
                         val allowSpawn: Boolean = true) : IStringSerializable, IStateSpawnControl
    {
        LIMESTONE("limestone", MapColor.GRAY_STAINED_HARDENED_CLAY),
        KOMATIITE("komatiite", MapColor.YELLOW_STAINED_HARDENED_CLAY),
        GREEN_SCHIST("green_schist", MapColor.GREEN_STAINED_HARDENED_CLAY),
        BLUE_SCHIST("blue_schist", MapColor.BLUE_STAINED_HARDENED_CLAY),
        KIMBERLITE("kimberlite", MapColor.GRAY),
        QUARTZITE("quartzite", MapColor.QUARTZ),
        SLATE("slate", MapColor.RED_STAINED_HARDENED_CLAY),
        SHALE("shale", MapColor.RED_STAINED_HARDENED_CLAY);

        val orePrefix: OrePrefix = OrePrefix.stone

        val material: GTMaterial
            get()
            {
                if (this == LIMESTONE) return GTLiteMaterials.Limestone
                if (this == KOMATIITE) return GTLiteMaterials.Komatiite
                if (this == GREEN_SCHIST) return GTLiteMaterials.GreenSchist
                if (this == BLUE_SCHIST) return GTLiteMaterials.BlueSchist
                if (this == KIMBERLITE) return GTLiteMaterials.Kimberlite
                if (this == QUARTZITE) return Materials.Quartzite
                if (this == SLATE) return GTLiteMaterials.Slate
                if (this == SHALE) return GTLiteMaterials.Shale
                return Materials.Stone
            }

        override fun getName(): String = serialName

        override fun canCreatureSpawn(state: IBlockState, world: IBlockAccess, pos: BlockPos,
                                      type: EntityLiving.SpawnPlacementType): Boolean = allowSpawn

    }

    enum class StoneVariant(
        val id: String,
        val translationKey: String = id,
        val hardness: Float = 1.5f,
        val resistance: Float = 10.0f
    )
    {
        SMOOTH("stone_smooth"),
        COBBLE("stone_cobble", "stone_cobble", 2.0f, 10.0f),
        COBBLE_MOSSY("stone_cobble_mossy", "stone_cobble_mossy", 2.0f, 10.0f),
        POLISHED("stone_polished"),
        BRICKS("stone_bricks"),
        BRICKS_CRACKED("stone_bricks_cracked"),
        BRICKS_MOSSY("stone_bricks_mossy"),
        CHISELED("stone_chiseled"),
        TILED("stone_tiled"),
        TILED_SMALL("stone_tiled_small"),
        BRICKS_SMALL("stone_bricks_small"),
        WINDMILL_A("stone_windmill_a", "stone_bricks_windmill_a"),
        WINDMILL_B("stone_windmill_b", "stone_bricks_windmill_b"),
        BRICKS_SQUARE("stone_bricks_square");
    }
}
