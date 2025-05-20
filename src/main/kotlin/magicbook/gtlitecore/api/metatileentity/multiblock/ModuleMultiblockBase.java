package magicbook.gtlitecore.api.metatileentity.multiblock;

import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IWorkable;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import lombok.Getter;
import lombok.Setter;
import magicbook.gtlitecore.api.capability.IModuleProvider;
import magicbook.gtlitecore.api.capability.IModuleReceiver;
import magicbook.gtlitecore.api.gui.GTLiteMuiTextures;
import magicbook.gtlitecore.api.gui.factory.MultiblockUIBuilder;
import magicbook.gtlitecore.api.gui.factory.MultiblockUIFactory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static magicbook.gtlitecore.api.utils.GTLiteValues.SECOND;

public abstract class ModuleMultiblockBase extends MultiblockWithDisplayBase implements IModuleReceiver, IWorkable, IControllable
{

    private final int tier;
    private final int moduleTier;
    private final int minCasingTier;

    @Getter
    @Setter
    protected IModuleProvider moduleProvider;

    protected IEnergyContainer energyContainer;
    protected final long energyConsumed;

    protected boolean isActive;
    @Setter
    @Getter
    protected int maxProgress;
    protected int progressTime = 0;
    protected boolean isWorkingEnabled = false;

    /**
     * @param tier          The voltage tier of this mte.
     * @param moduleTier    The inner tier of the module.
     * @param minCasingTier The minimum casing tier of this module required, this is useful for some
     *                      tiered status predicate.
     */
    public ModuleMultiblockBase(ResourceLocation metaTileEntityId, int tier,
                                int moduleTier, int minCasingTier)
    {
        super(metaTileEntityId);
        this.tier = tier;
        this.moduleTier = moduleTier;
        this.minCasingTier = minCasingTier;
        this.energyConsumed = (long) (Math.pow(4, this.tier + 2) / 2);
        this.energyContainer = new EnergyContainerHandler(this,
                (long) (160_008_000 * Math.pow(4, this.tier - 9)), this.energyConsumed,
                1, 0, 0);
    }

    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        initializeAbilities();
    }

    protected abstract void initializeAbilities();

    @Override
    public void checkStructurePattern()
    {
        super.checkStructurePattern();
        if (getModuleProvider() != null)
        {
            if (getModuleProvider().getCasingTier() >= this.minCasingTier)
                super.checkStructurePattern();
        }
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        setModuleProvider(null);
    }

    @NotNull
    @Override
    protected abstract BlockPattern createStructurePattern();

    @Override
    protected void updateFormedValid()
    {
        if (getOffsetTimer() % SECOND == 0 && getModuleProvider() != null)
        {
            if (energyContainer.getEnergyCapacity() != energyContainer.getEnergyStored()
                    && getModuleProvider().getSubEnergyContainer().getEnergyStored() > energyConsumed * SECOND)
            {
                long simulate = energyContainer.getEnergyCapacity() - energyContainer.getEnergyStored();
                energyContainer.addEnergy(simulate);
            }
        }
        else if (getModuleProvider() == null)
        {
            setWorkingEnabled(false);
        }
    }

    public IEnergyContainer getEnergyContainer()
    {
        if (getModuleProvider() == null || getModuleProvider().getSubEnergyContainer() == null)
        {
            return new EnergyContainerHandler(this, 0, 0, 0, 0, 0);
        }
        else
        {
            return energyContainer;
        }
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side)
    {
        if (capability == GregtechTileCapabilities.CAPABILITY_WORKABLE)
            return GregtechTileCapabilities.CAPABILITY_WORKABLE.cast(this);
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE)
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        return super.getCapability(capability, side);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data)
    {
        super.writeToNBT(data);
        data.setInteger("progressTime", progressTime);
        data.setInteger("maxProgress", maxProgress);
        data.setBoolean("isActive", isActive);
        data.setBoolean("isWorkingEnabled", isWorkingEnabled);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data)
    {
        super.readFromNBT(data);
        progressTime = data.getInteger("progressTime");
        maxProgress = data.getInteger("maxProgress");
        isActive = data.getBoolean("isActive");
        isWorkingEnabled = data.getBoolean("isWorkingEnabled");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf)
    {
        super.writeInitialSyncData(buf);
        buf.writeInt(progressTime);
        buf.writeInt(maxProgress);
        buf.writeBoolean(isActive);
        buf.writeBoolean(isWorkingEnabled);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf)
    {
        super.receiveInitialSyncData(buf);
        progressTime = buf.readInt();
        maxProgress = buf.readInt();
        setActive(buf.readBoolean());
        setWorkingEnabled(buf.readBoolean());
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.WORKABLE_ACTIVE)
        {
            setActive(buf.readBoolean());
            scheduleRenderUpdate();
        }
        else if (dataId == GregtechDataCodes.WORKING_ENABLED)
        {
            setWorkingEnabled(buf.readBoolean());
            scheduleRenderUpdate();
        }
    }

    @Override
    public boolean isActive()
    {
        return isActive && isWorkingEnabled();
    }

    public void setActive(boolean active)
    {
        if (isActive != active)
        {
            isActive = active;
            markDirty();
            if (getWorld() != null && !getWorld().isRemote)
                writeCustomData(GregtechDataCodes.WORKABLE_ACTIVE, buf -> buf.writeBoolean(active));
        }
    }

    @Override
    public int getProgress()
    {
        return progressTime;
    }

    @Override
    public boolean isWorkingEnabled()
    {
        return isWorkingEnabled;
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled)
    {
        initializeAbilities();
        isWorkingEnabled = workingEnabled;
        markDirty();
        if (getWorld() != null && !getWorld().isRemote)
            writeCustomData(GregtechDataCodes.WORKING_ENABLED, buf -> buf.writeBoolean(workingEnabled));
    }

    protected boolean drainEnergy(boolean simulate, long energy)
    {
        long result = energyContainer.getEnergyStored() - energy;
        if (result >= 0L && result <= energyContainer.getEnergyCapacity())
        {
            if (!simulate)
                energyContainer.changeEnergy(-energy);
            return true;
        }
        return false;
    }

    protected boolean drainEnergy(boolean simulate)
    {
        long result = energyContainer.getEnergyStored() - energyContainer.getInputVoltage();
        if (result >= 0L && result <= energyContainer.getEnergyCapacity())
        {
            if (!simulate)
                energyContainer.changeEnergy(-energyContainer.getInputVoltage());
            return true;
        }
        return false;
    }

    @Override
    public String getDisplayCountName()
    {
        return getMetaName() + ".display_count";
    }

    public int getProgressPercent()
    {
        return (int) ((1.0F * getProgress() / getMaxProgress()) * 100);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public boolean usesMui2()
    {
        return true;
    }

    @Override
    public ModularPanel buildUI(PosGuiData guiData, PanelSyncManager guiSyncManager)
    {
        return new MultiblockUIFactory(this)
                .configureDisplayText(this::configureDisplayText)
                .configureWarningText(this::configureWarningText)
                .configureErrorText(this::configureErrorText)
                .buildUI(guiData, guiSyncManager, GTLiteMuiTextures.DISPLAY);
    }

    protected abstract void configureDisplayText(MultiblockUIBuilder builder);

    protected abstract void configureWarningText(MultiblockUIBuilder builder);

    protected abstract void configureErrorText(MultiblockUIBuilder builder);

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected abstract ICubeRenderer getFrontOverlay();

    @SideOnly(Side.CLIENT)
    @Override
    public abstract ICubeRenderer getBaseTexture(IMultiblockPart sourcePart);

    @Override
    public boolean hasMaintenanceMechanics()
    {
        return false;
    }

    @Override
    public void sentWorkingDisabled()
    {
        setWorkingEnabled(false);
    }

    @Override
    public void sentWorkingEnabled()
    {
        setWorkingEnabled(true);
    }

}
