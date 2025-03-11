package magicbook.gtlitecore.common.block.blocks;

import gregtech.api.block.VariantBlock;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import lombok.AllArgsConstructor;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.unification.GTLiteMaterials;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GTLiteStoneVariantBlock extends VariantBlock<GTLiteStoneVariantBlock.StoneType>
{

    private static final PropertyEnum<StoneType> PROPERTY = PropertyEnum.create("variant", StoneType.class);

    private final StoneVariant stoneVariant;

    public GTLiteStoneVariantBlock(StoneVariant stoneVariant)
    {
        super(Material.ROCK);
        this.stoneVariant = stoneVariant;
        this.setRegistryName(stoneVariant.id);
        this.setTranslationKey(stoneVariant.translationKey);
        this.setHardness(stoneVariant.hardness);
        this.setResistance(stoneVariant.resistance);
        this.setSoundType(SoundType.STONE);
        this.setHarvestLevel("pickaxe", 0);
        this.setDefaultState(this.getState(StoneType.LIMESTONE));
        this.setCreativeTab(GTLiteAPI.TAB_GTLITE);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        this.VARIANT = PROPERTY;
        this.VALUES = StoneType.values();
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state,
                                    IBlockAccess world,
                                    BlockPos pos,
                                    EntityLiving.SpawnPlacementType type)
    {
        return false;
    }

    @Override
    public boolean canSilkHarvest(World world,
                                  BlockPos pos,
                                  IBlockState state,
                                  EntityPlayer player)
    {
        return this.stoneVariant == StoneVariant.SMOOTH;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this.stoneVariant == StoneVariant.SMOOTH
                    ? GTLiteMetaBlocks.STONES.get(StoneVariant.COBBLE) : this);
    }

    @Getter
    @AllArgsConstructor
    public enum StoneType implements IStringSerializable
    {
        // Rock type referenced to GregTech6, thanks gregorius create these stones,
        // we modified the components with reality world correspondenced rock components,
        // and modified textures with GregTechCEu style.
        LIMESTONE("limestone", MapColor.GRAY_STAINED_HARDENED_CLAY),
        KOMATIITE("komatiite", MapColor.YELLOW_STAINED_HARDENED_CLAY),
        GREEN_SCHIST("green_schist", MapColor.GREEN_STAINED_HARDENED_CLAY),
        BLUE_SCHIST("blue_schist", MapColor.BLUE_STAINED_HARDENED_CLAY),
        KIMBERLITE("kimberlite", MapColor.GRAY),
        QUARTZITE("quartzite", MapColor.QUARTZ),
        SLATE("slate", MapColor.RED_STAINED_HARDENED_CLAY),
        SHALE("shale", MapColor.RED_STAINED_HARDENED_CLAY);

        private final String name;
        private final MapColor mapColor;

        public OrePrefix getOrePrefix()
        {
            return OrePrefix.stone;
        }

        public gregtech.api.unification.material.Material getMaterial()
        {
            if (this == LIMESTONE)
                return GTLiteMaterials.Limestone;
            if (this == KOMATIITE)
                return GTLiteMaterials.Komatiite;
            if (this == GREEN_SCHIST)
                return GTLiteMaterials.GreenSchist;
            if (this == BLUE_SCHIST)
                return GTLiteMaterials.BlueSchist;
            if (this == KIMBERLITE)
                return GTLiteMaterials.Kimberlite;
            if (this == QUARTZITE)
                return Materials.Quartzite;
            if (this == SLATE)
                return GTLiteMaterials.Slate;
            if (this == SHALE)
                return GTLiteMaterials.Shale;
            return Materials.Stone;
        }

    }

    @Getter
    @AllArgsConstructor
    public enum StoneVariant
    {

        SMOOTH("stone_smooth"),
        COBBLE("stone_cobble", 2.0f, 10.0f),
        COBBLE_MOSSY("stone_cobble_mossy", 2.0f, 10.0f),
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

        public final String id;
        public final String translationKey;
        public final float hardness;
        public final float resistance;

        StoneVariant(String id)
        {
            this(id, id);
        }

        StoneVariant(String id, String translationKey)
        {
            this(id, translationKey, 1.5f, 10.0f);
        }

        StoneVariant(String id, float hardness, float resistance)
        {
            this(id, id, hardness, resistance);
        }

    }

}
