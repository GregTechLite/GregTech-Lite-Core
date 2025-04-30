package magicbook.gtlitecore.common.entity.explosive;

import gregtech.common.entities.EntityGTExplosive;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityNaquadriaCharge extends EntityGTExplosive
{

    @SuppressWarnings("unused")
    public EntityNaquadriaCharge(World world)
    {
        super(world);
    }

    public EntityNaquadriaCharge(World world, double x, double y, double z, EntityLivingBase exploder)
    {
        super(world, x, y, z, exploder);
    }

    @Override
    protected float getStrength()
    {
        return 200.0F;
    }

    @Override
    public boolean dropsAllBlocks()
    {
        return false;
    }

    @Override
    protected int getRange()
    {
        return 64;
    }

    @NotNull
    @Override
    public IBlockState getExplosiveState()
    {
        return GTLiteMetaBlocks.NAQUADRIA_CHARGE.getDefaultState();
    }

}

