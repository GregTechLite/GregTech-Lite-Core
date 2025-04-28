package magicbook.gtlitecore.common.block.blocks;

import gregtech.api.block.VariantBlock;
import gregtech.api.util.TextFormattingUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BlockCrucible extends VariantBlock<BlockCrucible.CrucibleType>
{

    public BlockCrucible()
    {
        super(Material.IRON);
        this.setTranslationKey("crucible");
        this.setHardness(4.0F);
        this.setResistance(8.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("pickaxe", 1);
        this.setDefaultState(this.getState(CrucibleType.BRONZE_CRUCIBLE));
    }

    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               List<String> tooltip,
                               ITooltipFlag advanced) {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.multiblock.blast_furnace.max_temperature",
                TextFormatting.RED + TextFormattingUtil.formatNumbers(
                        this.getState(stack).getTemperature()) + "K"));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state,
                                    IBlockAccess world,
                                    BlockPos pos,
                                    EntityLiving.SpawnPlacementType type)
    {
        return false;
    }

    @Getter
    @AllArgsConstructor
    public enum CrucibleType implements IStringSerializable
    {
        BRONZE_CRUCIBLE("bronze", 1696),
        INVAR_CRUCIBLE("invar", 2395),
        QUARTZ_CRUCIBLE("quartz", 2482),
        CHROME_CRUCIBLE("chrome", 2725),
        VANADIUM_CRUCIBLE("vanadium", 2728),
        NIOBIUM_TITANIUM_CRUCIBLE("niobium_titanium", 2931),
        IRIDIUM_CRUCIBLE("iridium", 3398),
        MOLYBDENUM_CRUCIBLE("molybdenum", 3620),
        TUNGSTEN_CRUCIBLE("tungsten", 3695),
        OSMIUM_CRUCIBLE("osmium", 4132),
        GRAPHITE_CRUCIBLE("graphite", 4750),
        BORON_NITRIDE_CRUCIBLE("boron_nitride", 5328);

        private final String name;
        public final int temperature;
    }

}
