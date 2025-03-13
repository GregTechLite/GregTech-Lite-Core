package magicbook.gtlitecore.common.metatileentity.single;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import magicbook.gtlitecore.api.gui.SteamProgressBarIndicators;
import magicbook.gtlitecore.api.metatileentity.PseudoMultiSteamMachineMetaTileEntity;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetaTileEntitySteamSapCollector extends PseudoMultiSteamMachineMetaTileEntity
{

    private final long sapCollectionAmount;

    public MetaTileEntitySteamSapCollector(ResourceLocation metaTileEntityId, boolean isHighPressure)
    {
        super(metaTileEntityId, GTLiteRecipeMaps.SAP_COLLECTOR_RECIPES(),
                SteamProgressBarIndicators.EXTRACTION, GTLiteTextures.SAP_COLLECTOR_OVERLAY, false, isHighPressure);
        this.sapCollectionAmount = isHighPressure ? 6L : 3L;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntitySteamSapCollector(this.metaTileEntityId, this.isHighPressure);
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler()
    {
        return new NotifiableItemStackHandler(this, 1, this, false);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler()
    {
        return new NotifiableItemStackHandler(this, 1, this, true);
    }

    @Override
    protected FluidTankList createExportFluidHandler()
    {
        return new FluidTankList(false, new FluidTank(16000));
    }

    @Override
    public void addNotifiedInput(Object input) {
        super.addNotifiedInput(input);
        this.onNeighborChanged();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderMetaTileEntity(CCRenderState renderState,
                                     Matrix4 translation,
                                     IVertexOperation[] pipeline)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        GTLiteTextures.SAP_COLLECTOR_OVERLAY.renderOrientedState(renderState, translation,
                pipeline, this.getFrontFacing(), this.isActive(), true);
    }

    @Override
    public boolean isValidFrontFacing(EnumFacing facing)
    {
        return super.isValidFrontFacing(facing)
                && facing != this.workableHandler.getVentingSide()
                && facing != this.workableHandler.getVentingSide().getOpposite();
    }

    @Override
    public void setFrontFacing(EnumFacing frontFacing)
    {
        super.setFrontFacing(frontFacing);
        if (this.workableHandler.getVentingSide() == frontFacing
                || this.workableHandler.getVentingSide() == frontFacing.getOpposite())
        {
            this.workableHandler.setVentingSide(frontFacing.rotateY());
        }
    }

    @Override
    public boolean onWrenchClick(EntityPlayer playerIn,
                                 EnumHand hand, EnumFacing facing,
                                 CuboidRayTraceResult hitResult)
    {
        if (!playerIn.isSneaking())
        {
            if (this.workableHandler.getVentingSide() == facing)
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
                    this.workableHandler.setVentingSide(facing);
                return true;
            }

        }
        else
        {
            return super.onWrenchClick(playerIn, hand, facing, hitResult);
        }

    }

    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.sap_collector.sap_collection"));
        tooltip.add(I18n.format("gtlitecore.machine.sap_collector.sap_collection_amount", this.sapCollectionAmount));
    }

}