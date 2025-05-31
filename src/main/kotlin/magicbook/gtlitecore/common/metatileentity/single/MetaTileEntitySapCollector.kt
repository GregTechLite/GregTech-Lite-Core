package magicbook.gtlitecore.common.metatileentity.single;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import magicbook.gtlitecore.api.metatileentity.PseudoMultiMachineMetaTileEntity;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.utils.GTLiteUtility;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetaTileEntitySapCollector extends PseudoMultiMachineMetaTileEntity
{

    public MetaTileEntitySapCollector(ResourceLocation metaTileEntityId, int tier)
    {
        super(metaTileEntityId, GTLiteRecipeMaps.SAP_COLLECTOR_RECIPES(),
                GTLiteTextures.SAP_COLLECTOR_OVERLAY, tier, true,
                GTLiteUtility.collectorTankSizeFunction);
    }

    @NotNull
    @Override
    public MetaTileEntity createMetaTileEntity(@Nullable IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntitySapCollector(this.metaTileEntityId, this.getTier());
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler()
    {
        return new ItemStackHandler(1);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler()
    {
        return new ItemStackHandler(1);
    }

    @Override
    protected FluidTankList createExportFluidHandler()
    {
        return new FluidTankList(false, new FluidTank(16000));
    }

    @Override
    public void addNotifiedInput(Object input)
    {
        super.addNotifiedInput(input);
        this.onNeighborChanged();
    }

    @Override
    public boolean isValidFrontFacing(EnumFacing facing)
    {
        return super.isValidFrontFacing(facing)
                && facing != getOutputFacingFluids().getOpposite()
                && facing != getOutputFacingItems().getOpposite();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void setFrontFacing(EnumFacing frontFacing)
    {
        super.setFrontFacing(frontFacing);
        if (this.getOutputFacingFluids() == frontFacing.getOpposite()
                || this.getOutputFacingItems() == frontFacing.getOpposite())
            this.setOutputFacing(frontFacing.rotateY());
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onWrenchClick(EntityPlayer playerIn,
                                 EnumHand hand, EnumFacing facing,
                                 CuboidRayTraceResult hitResult)
    {
        if (!playerIn.isSneaking())
        {
            if (this.getOutputFacing() == facing)
            {
                return false;
            }
            else if (this.hasFrontFacing() && facing == this.getFrontFacing()
                      || facing == this.getFrontFacing().getOpposite())
            {
                return false;
            }
            else
            {
                if (!this.getWorld().isRemote)
                    this.setOutputFacing(facing);
                return true;
            }
        }
        else
        {
            return super.onWrenchClick(playerIn, hand, facing, hitResult);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderMetaTileEntity(CCRenderState renderState,
                                     Matrix4 translation,
                                     IVertexOperation[] pipeline)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        GTLiteTextures.SAP_COLLECTOR_OVERLAY.renderOrientedState(renderState,
                translation, pipeline, this.getFrontFacing(), this.isActive(), true);
    }

    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World player,
                               List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.set(1, I18n.format("gtlitecore.machine.sap_collector.sap_collection"));
    }

    @Override
    public boolean getIsWeatherOrTerrainResistant()
    {
        return true;
    }

}