package magicbook.gtlitecore.common.metatileentity.multiblock;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import gregtech.api.capability.FeCompat;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.IElectricItem;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockComputerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import magicbook.gtlitecore.api.gui.GTLiteMuiTextures;
import magicbook.gtlitecore.api.gui.factory.MultiblockUIBuilder;
import magicbook.gtlitecore.api.gui.factory.MultiblockUIFactory;
import magicbook.gtlitecore.api.item.GTLiteToolHelper;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockComputerCasing01;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import magicbook.gtlitecore.core.GTLiteConfigHolder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.GTValues.ULV;
import static gregtech.api.util.RelativeDirection.DOWN;
import static gregtech.api.util.RelativeDirection.FRONT;
import static gregtech.api.util.RelativeDirection.LEFT;


public class MetaTileEntityEnergyInfuser extends MultiblockWithDisplayBase implements IControllable
{

    private IItemHandlerModifiable inputInventory;
    private IItemHandlerModifiable outputInventory;
    private IMultipleTankHandler inputFluidInventory;

    private IEnergyContainer energyContainer;

    private boolean isActive = false;
    private boolean isWorkingEnabled = false;

    public MetaTileEntityEnergyInfuser(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityEnergyInfuser(metaTileEntityId);
    }

    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        initializeAbilities();
    }

    private void initializeAbilities()
    {
        this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.inputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));

        List<IEnergyContainer> inputEnergy = getAbilities(MultiblockAbility.INPUT_ENERGY);
        // inputEnergy.addAll(getAbilities(MultiblockAbility.SUBSTATION_INPUT_ENERGY));
        // inputEnergy.addAll(getAbilities(MultiblockAbility.INPUT_LASER));
        this.energyContainer = new EnergyContainerList(inputEnergy);
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("CCC", "MMM", "DDD", "MMM", "CCC")
                .aisle("CCC", "MDM", "DDD", "MDM", "CCC")
                .aisle("CCC", "MMM", "DSD", "MMM", "CCC")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(9)
                        .or(abilities(MultiblockAbility.INPUT_ENERGY)
                                .setMaxGlobalLimited(4)
                                .setPreviewCount(1))
                        .or(abilities(MultiblockAbility.INPUT_LASER)
                                .setMaxGlobalLimited(1)
                                .setPreviewCount(0))
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS)
                                .setPreviewCount(1))
                        .or(abilities(MultiblockAbility.EXPORT_ITEMS)
                                .setPreviewCount(1))
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS)
                                .setPreviewCount(0)))
                .where('D', states(getSecondCasingState()))
                .where('M', states(getCoilState()))
                .build();
    }

    private static IBlockState getCasingState()
    {
        return MetaBlocks.COMPUTER_CASING.getState(BlockComputerCasing.CasingType.HIGH_POWER_CASING);
    }

    private static IBlockState getSecondCasingState()
    {
        return GTLiteMetaBlocks.COMPUTER_CASING_01.getState(BlockComputerCasing01.ComputerCasingType.MOLECULAR_CASING);
    }

    private static IBlockState getCoilState()
    {
        return GTLiteMetaBlocks.COMPUTER_CASING_01.getState(BlockComputerCasing01.ComputerCasingType.MOLECULAR_COIL);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        if (sourcePart == null)
            return GTLiteTextures.MOLECULAR_CASING;
        else
            return Textures.HIGH_POWER_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return Textures.DATA_BANK_OVERLAY;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState,
                                     Matrix4 translation,
                                     IVertexOperation[] pipeline)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline,
                getFrontFacing(), isActive(), isWorkingEnabled());
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfos = new ArrayList<>();
        MultiblockShapeInfo builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("CEC", "MMM", "DDD", "MMM", "CCC")
                .aisle("CCC", "MDM", "DDD", "MDM", "CCC")
                .aisle("ICJ", "MMM", "DSD", "MMM", "CCC")
                .where('S', GTLiteMetaTileEntities.ENERGY_INFUSER, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('D', getSecondCasingState())
                .where('M', getCoilState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.NORTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[ULV], EnumFacing.SOUTH)
                .build();
        shapeInfos.add(builder);
        return shapeInfos;
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
                .configureErrorText(this::configureErrorText)
                .buildUI(guiData, guiSyncManager, GTLiteMuiTextures.DISPLAY);
    }

    private void configureDisplayText(MultiblockUIBuilder builder)
    {
        builder.setWorkingStatus(isWorkingEnabled(), isActive())
                .addEnergyUsageLine(energyContainer)
                .addWorkingStatusLine();
    }

    private void configureErrorText(MultiblockUIBuilder builder)
    {
        builder.structureFormed(isStructureFormed());
    }

    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.energy_infuser.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.energy_infuser.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.energy_infuser.tooltip.3"));
    }

    @Override
    protected void updateFormedValid()
    {
        if (!isWorkingEnabled() || getWorld() == null || getWorld().isRemote)
            return;

        boolean itemProcessed = false;
        for (int i = 0; i < inputInventory.getSlots(); i++)
        {
            long energyAvailable = energyContainer.getEnergyStored();
            if (energyAvailable < 1)
                break;

            ItemStack stackInSlot = inputInventory.getStackInSlot(i);
            if (stackInSlot.isEmpty())
                continue;

            boolean isCharged = isItemFullyCharged(stackInSlot);
            boolean isRepaired = GTLiteToolHelper.isItemHasFullDurability(stackInSlot);

            if (isCharged && isRepaired)
            {
                stackInSlot = inputInventory.extractItem(i, 1, true);
                if (outputInventory.getSlots() > 0
                        && GTTransferUtils.insertItem(outputInventory, stackInSlot, true).isEmpty())
                {
                    stackInSlot = inputInventory.extractItem(i, 1, false);
                    GTTransferUtils.insertItem(outputInventory, stackInSlot, false);
                }
                else if (getVoidingMode() == 1 || getVoidingMode() == 3)
                {
                    inputInventory.extractItem(i, 1, false);
                }
            }

            if (!isCharged)
            {
                long energyUsed = chargeItem(stackInSlot, energyAvailable);
                if (!itemProcessed)
                    itemProcessed = energyUsed > 0;
            }

            if (!isRepaired && inputFluidInventory.getTanks() > 0)
            {
                energyAvailable = energyContainer.getEnergyStored();
                int toRepair = Math.min(stackInSlot.getItemDamage(), GTLiteConfigHolder.machine.energyInfuser.maxRepairedDamagePerWorking);
                long powerCost = (long) toRepair * GTLiteConfigHolder.machine.energyInfuser.energyConsumedPerDurability;
                FluidStack toDrain = new FluidStack(Materials.UUMatter.getFluid(), toRepair * GTLiteConfigHolder.machine.energyInfuser.uuMatterConsumedPerDurability);
                if (energyAvailable > powerCost && inputFluidInventory.drain(toDrain, false) != null)
                {
                    stackInSlot.setItemDamage(Math.max(stackInSlot.getItemDamage() - toRepair, 0));
                    inputFluidInventory.drain(toDrain, true);
                    energyContainer.removeEnergy(powerCost);
                    itemProcessed = true;
                }
            }
        }

        if (itemProcessed != isActive())
        {
            setActive(itemProcessed);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data)
    {
        super.writeToNBT(data);
        data.setBoolean("isWorkingEnabled", this.isWorkingEnabled);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data)
    {
        super.readFromNBT(data);
        this.isWorkingEnabled = data.getBoolean("isWorkingEnabled");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf)
    {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(this.isWorkingEnabled);
        buf.writeBoolean(this.isActive);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf)
    {
        super.receiveInitialSyncData(buf);
        this.isWorkingEnabled = buf.readBoolean();
        this.isActive = buf.readBoolean();
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.WORKABLE_ACTIVE)
        {
            this.isActive = buf.readBoolean();
            scheduleRenderUpdate();
        }
        else if (dataId == GregtechDataCodes.WORKING_ENABLED)
        {
            this.isWorkingEnabled = buf.readBoolean();
            scheduleRenderUpdate();
        }
    }

    @Override
    public boolean isActive()
    {
        return super.isActive() && isActive;
    }

    public void setActive(boolean active)
    {
        this.isActive = active;
        markDirty();
        writeCustomData(GregtechDataCodes.WORKABLE_ACTIVE, buf -> buf.writeBoolean(this.isActive));
    }

    @Override
    public boolean isWorkingEnabled()
    {
        return this.isWorkingEnabled;
    }

    @Override
    public void setWorkingEnabled(boolean workingEnabled)
    {
        this.isWorkingEnabled = workingEnabled;
        markDirty();
        writeCustomData(GregtechDataCodes.WORKING_ENABLED, buf -> buf.writeBoolean(this.isWorkingEnabled));
    }

    @Override
    public boolean hasMaintenanceMechanics()
    {
        return false;
    }

    private long chargeItem(@NotNull ItemStack stack, long energy)
    {
        long energyAvailable = Math.min(energy, this.energyContainer.getInputVoltage() * this.energyContainer.getInputAmperage());
        if (stack.hasCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null)) // GTEU
        {
            IElectricItem item = stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
            if (item == null)
                return 0;
            return item.charge(energyAvailable, GTUtility.getFloorTierByVoltage(this.energyContainer.getInputVoltage()), true, false);
        }
        else if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) // FE/EU/RF
        {
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (storage == null)
                return 0;
            return FeCompat.insertEu(storage, energyAvailable);
        }
        return 0;
    }

    private boolean isItemFullyCharged(@NotNull ItemStack stack)
    {
        if (stack.hasCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null)) // GTEU
        {
            IElectricItem item = stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
            if (item == null || !item.chargeable())
                return true;
            return item.getCharge() >= item.getMaxCharge();
        }
        else if (stack.hasCapability(CapabilityEnergy.ENERGY, null)) // FE/EU/RF
        {
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY, null);
            if (storage == null || !storage.canReceive())
                return true;
            return storage.getEnergyStored() >= storage.getMaxEnergyStored();
        }
        return true;
    }

}
