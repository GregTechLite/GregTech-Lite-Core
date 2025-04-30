package magicbook.gtlitecore.common.entity.explosive;

import gregtech.common.entities.EntityGTExplosive;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityLeptonicCharge extends EntityGTExplosive
{

    @SuppressWarnings("unused")
    public EntityLeptonicCharge(World world)
    {
        super(world);
    }

    public EntityLeptonicCharge(World world, double x, double y, double z, EntityLivingBase exploder)
    {
        super(world, x, y, z, exploder);
    }

    @Override
    protected float getStrength()
    {
        return 800.0F;
    }

    @Override
    public boolean dropsAllBlocks()
    {
        return false;
    }

    @Override
    protected int getRange()
    {
        return 1024;
    }

    @NotNull
    @Override
    public IBlockState getExplosiveState()
    {
        return GTLiteMetaBlocks.LEPTONIC_CHARGE.getDefaultState();
    }

}
