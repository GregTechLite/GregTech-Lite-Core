package magicbook.gtlitecore.common.block.blocks;

import gregtech.api.items.toolitem.ToolClasses;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Material;
import gregtech.client.model.MaterialStateMapper;
import gregtech.client.model.modelfactories.MaterialBlockModelLoader;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockMaterialBase;
import gregtech.common.blocks.properties.PropertyMaterial;
import gregtech.common.creativetab.GTCreativeTabs;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialIconType;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class BlockGregtechWall extends BlockMaterialBase
{

    private BlockGregtechWall()
    {
        super(net.minecraft.block.material.Material.IRON);
        this.setTranslationKey("wall_gt");
        this.setHardness(4.0F);
        this.setResistance(8.0F);
        this.setCreativeTab(GTCreativeTabs.TAB_GREGTECH_MATERIALS);
        this.setCreativeTab(GTLiteAPI.TAB_GTLITE_DECORATION);
    }

    public static BlockGregtechWall create(Material[] materials)
    {
        PropertyMaterial property = PropertyMaterial.create("variant", materials);
        return new BlockGregtechWall()
        {

            @Override
            public PropertyMaterial getVariantProperty()
            {
                return property;
            }

        };
    }

    @Override
    public String getHarvestTool(IBlockState state)
    {
        Material material = getGtMaterial(state);
        if (ModHandler.isMaterialWood(material))
            return ToolClasses.AXE;
        return ToolClasses.WRENCH;
    }

    @Override
    public SoundType getSoundType(IBlockState state,
                                  World world,
                                  BlockPos pos,
                                  @Nullable Entity entity)
    {
        Material material = getGtMaterial(state);
        if (ModHandler.isMaterialWood(material))
            return SoundType.WOOD;
        return SoundType.METAL;
    }

    public SoundType getSoundType(ItemStack stack)
    {
        Material material = getGtMaterial(stack);
        if (ModHandler.isMaterialWood(material))
            return SoundType.WOOD;
        return SoundType.METAL;
    }

    @Override
    public int getHarvestLevel(IBlockState state)
    {
        return 1;
    }

    @SuppressWarnings("deprecation")
    @Override
    public net.minecraft.block.material.Material getMaterial(IBlockState state)
    {
        Material material = getGtMaterial(state);
        if (ModHandler.isMaterialWood(material)) {
            return net.minecraft.block.material.Material.WOOD;
        }
        return super.getMaterial(state);
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
    public void addInformation(ItemStack stack,
                               @Nullable World worldIn,
                               List<String> tooltip,
                               ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);;
        if (ConfigHolder.misc.debug)
            tooltip.add("MetaItem Id: wallGt" + this.getGtMaterial(stack).toCamelCaseString());
    }

    @SideOnly(Side.CLIENT)
    public void onModelRegister()
    {
        ModelLoader.setCustomStateMapper(this, new MaterialStateMapper(
                GTLiteMaterialIconType.wallGt, s -> this.getGtMaterial(s).getMaterialIconSet()));
        for (IBlockState state : this.getBlockState().getValidStates())
        {
            ModelLoader.setCustomModelResourceLocation(
                    Item.getItemFromBlock(this), this.getMetaFromState(state),
                    MaterialBlockModelLoader.registerItemModel(GTLiteMaterialIconType.wallGt,
                            this.getGtMaterial(state).getMaterialIconSet()));
        }
    }

}
