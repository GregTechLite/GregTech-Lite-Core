package magicbook.gtlitecore.api.metatileentity;

import gregtech.api.capability.IGhostSlotConfigurable;
import gregtech.api.capability.impl.FluidHandlerProxy;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.GhostCircuitItemStackHandler;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.capability.impl.ItemHandlerProxy;
import gregtech.api.capability.impl.NotifiableFluidTank;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.capability.impl.RecipeLogicSteam;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.GhostCircuitSlotWidget;
import gregtech.api.gui.widgets.RecipeProgressWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.gui.widgets.TankWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.util.GTTransferUtils;
import gregtech.client.renderer.ICubeRenderer;
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures;
import magicbook.gtlitecore.api.gui.indicator.SteamProgressBarIndicator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class SimpleSteamMachineMetaTileEntity extends SteamMetaTileEntity implements IGhostSlotConfigurable
{

    protected SteamProgressBarIndicator progressBarIndicator;
    protected boolean isBrickedCasing;

    @Nullable
    protected GhostCircuitItemStackHandler circuitInventory;
    protected IItemHandler outputItemInventory;
    protected IFluidHandler outputFluidInventory;
    private IItemHandlerModifiable actualImportItems;

    public SimpleSteamMachineMetaTileEntity(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap,
                                            SteamProgressBarIndicator progressBarIndicator,
                                            ICubeRenderer renderer, boolean isBrickedCasing,
                                            boolean isHighPressure)
    {
        super(metaTileEntityId, recipeMap, renderer, isHighPressure);
        this.progressBarIndicator = progressBarIndicator;
        this.isBrickedCasing = isBrickedCasing;
        // If steam machine has ghost circuit settings, then initialized ghost circuit stack handler.
        if (this.hasGhostCircuitInventory())
        {
            this.circuitInventory = new GhostCircuitItemStackHandler(this);
            this.circuitInventory.addNotifiableMetaTileEntity(this);
        }
        this.initializeInventory();
        this.workableHandler = new RecipeLogicSteam(this, recipeMap, isHighPressure,
                this.steamFluidTank, 1.0);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new SimpleSteamMachineMetaTileEntity(this.metaTileEntityId,
                this.workableHandler.getRecipeMap(), this.progressBarIndicator,
                this.renderer, this.isBrickedCasing, this.isHighPressure);
    }

    @Override
    protected void initializeInventory()
    {
        super.initializeInventory();
        this.outputItemInventory = new ItemHandlerProxy(new ItemStackHandler(0), this.exportItems);
        this.outputFluidInventory = new FluidHandlerProxy(new FluidTankList(false), this.exportFluids);
        this.actualImportItems = null;
    }

    @Override
    public IItemHandlerModifiable getImportItems()
    {
        if (this.actualImportItems == null)
            this.actualImportItems = this.circuitInventory == null ? super.getImportItems()
                    : new ItemHandlerList(Arrays.asList(super.getImportItems(), this.circuitInventory));
        return this.actualImportItems;
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler()
    {
        if (this.workableHandler == null)
            return new ItemStackHandler(0);
        return new NotifiableItemStackHandler(this,
                this.workableHandler.getRecipeMap().getMaxInputs(), this, false);
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler()
    {
        if (this.workableHandler == null)
            return new ItemStackHandler(0);
        return new NotifiableItemStackHandler(this,
                this.workableHandler.getRecipeMap().getMaxOutputs(), this, true);
    }

    @Override
    public FluidTankList createImportFluidHandler()
    {
        super.createImportFluidHandler();
        if (this.workableHandler == null)
            return new FluidTankList(false, this.steamFluidTank);
        IFluidTank[] importFluids = new IFluidTank[this.workableHandler.getRecipeMap()
                .getMaxFluidInputs() + 1];
        importFluids[0] = this.steamFluidTank;
        for (int i = 1; i < importFluids.length; i++)
        {
            importFluids[i] = new NotifiableFluidTank(8000, this, false);
        }
        return new FluidTankList(false, importFluids);
    }

    @Override
    protected FluidTankList createExportFluidHandler()
    {
        if (this.workableHandler == null)
            return new FluidTankList(false);
        FluidTank[] exportFluids = new FluidTank[this.workableHandler.getRecipeMap()
                .getMaxFluidOutputs()];
        for (int i = 0; i < exportFluids.length; i++)
        {
            exportFluids[i] = new NotifiableFluidTank(8000, this, true);
        }
        return new FluidTankList(false, exportFluids);
    }

    @Override
    public boolean isBrickedCasing()
    {
        return this.isBrickedCasing;
    }

    /**
     * TODO redo via ModularUI2.
     */
    @Override
    protected ModularUI createUI(EntityPlayer player)
    {
        return this.createGuiTemplate(player).build(this.getHolder(), player);
    }

    protected ModularUI.Builder createGuiTemplate(EntityPlayer player)
    {
        RecipeMap<?> recipeMap = this.workableHandler.getRecipeMap();
        int offsetY = 0;
        // Internal override SteamMetaTileEntity#createUITemplate().
        ModularUI.Builder builder = super.createUITemplate(player);

        this.addRecipeProgressBar(builder, recipeMap, offsetY);
        this.addInventorySlotGroup(builder, this.importItems, this.importFluids,
                false, offsetY);
        this.addInventorySlotGroup(builder, this.exportItems, this.exportFluids,
                true, offsetY);
        this.addGhostCircuitSlot(builder, offsetY);
        return builder;
    }

    protected void addRecipeProgressBar(ModularUI.Builder builder, RecipeMap<?> recipeMap, int offsetY)
    {
        int x = 89 - this.progressBarIndicator.getWidth() / 2;
        int y = offsetY + 42 - this.progressBarIndicator.getHeight() / 2;
        builder.widget(new RecipeProgressWidget(this.workableHandler::getProgressPercent,
                x, y, this.progressBarIndicator.getWidth(), this.progressBarIndicator.getHeight(),
                this.progressBarIndicator.getProgressBarTexture().get(isHighPressure),
                this.progressBarIndicator.getProgressBarMoveType(), recipeMap));
    }

    protected void addInventorySlotGroup(ModularUI.Builder builder,
                                         IItemHandlerModifiable itemHandler,
                                         FluidTankList fluidHandler,
                                         boolean isOutputs, int offsetY)
    {
        int itemSlotCount = itemHandler.getSlots();
        int fluidSlotCount = fluidHandler.getTanks() - ((isOutputs) ? 0 : 1); // Remove input steam tank.
        // Redundant to store item slots count if you know it's going to be 0.
        boolean invertFluids = false;
        if (itemSlotCount == 0)
        {
            int tmp = itemSlotCount;
            itemSlotCount = fluidSlotCount;
            fluidSlotCount = tmp;
            invertFluids = true;
        }

        int[] inputSlotGrid = determineSlotsGrid(itemSlotCount);
        int itemSlotLeft = inputSlotGrid[0];
        int itemSlotDown = inputSlotGrid[1];

        // If height of item slots > fluid slots AND primary[item] slot (can be item or fluid) don't take full length of 3.
        boolean isVerticalFluid = itemSlotDown >= fluidSlotCount && itemSlotLeft < 3;
        int fluidGridHeight = ((fluidSlotCount / 3 == 0) ? 1 : fluidSlotCount / 3); // Fit into at most 3 wide by x tall.

        int fullGridHeight = itemSlotDown + (isVerticalFluid ? 0 : fluidGridHeight);
        if (fullGridHeight >= 3)
            offsetY += 4;

        int startInputsX = isOutputs ? 89 + this.progressBarIndicator.getWidth() / 2 + 9
                : 89 - (this.progressBarIndicator.getWidth() / 2 + 9 + itemSlotLeft * 18);
        int startInputsY = offsetY + (isVerticalFluid ? 42 - ((itemSlotDown * 18) / 2)
                : 42 - (((fluidSlotCount - 1) / 3 + 1) * 18));

        boolean wasGroup = itemHandler.getSlots() + fluidHandler.getTanks() == 12;
        if (wasGroup)
            startInputsY -= 9;
        else if (itemHandler.getSlots() >= 6
                && fluidHandler.getTanks() >= 2 && !isOutputs)
            startInputsY -= 9;

        for (int i = 0; i < itemSlotDown; i++)
        {
            for (int j = 0; j < itemSlotLeft; j++)
            {
                int slotIndex = i * itemSlotLeft + j;
                if (slotIndex >= itemSlotCount)
                    break;
                int x = startInputsX + 18 * j;
                int y = startInputsY + 18 * i;
                addSlot(builder, x, y, slotIndex, itemHandler, fluidHandler, invertFluids, isOutputs);
            }
        }

        if (wasGroup)
            startInputsY += 2;
        if (fluidSlotCount > 0 || invertFluids)
        {
            if (isVerticalFluid)
            {
                int startSpecX = isOutputs ? startInputsX + itemSlotLeft * 18 : startInputsX - 18;
                for (int i = 0; i < fluidSlotCount; i++)
                {
                    addSlot(builder, startSpecX, startInputsY + 18 * i, i, itemHandler, fluidHandler, !invertFluids, isOutputs);
                }
            }
            else
            {
                int startSpecY = startInputsY + itemSlotDown * 18;
                for (int i = 0; i < fluidSlotCount; i++)
                {
                    int x = isOutputs ? startInputsX + 18 * (i % 3)
                            : startInputsX + itemSlotLeft * 18 - 18 - 18 * (i % 3);
                    int y = startSpecY + (i / 3) * 18;
                    addSlot(builder, x, y, i, itemHandler, fluidHandler, !invertFluids, isOutputs);
                }
            }
        }

    }

    protected void addGhostCircuitSlot(ModularUI.Builder builder, int offsetY)
    {
        if (this.exportItems.getSlots() + this.exportFluids.getTanks() <= 9)
        {
            if (this.circuitInventory != null)
            {
                SlotWidget circuitSlot = new GhostCircuitSlotWidget(circuitInventory, 0, 124, 62 + offsetY)
                        .setBackgroundTexture(GuiTextures.SLOT_STEAM.get(isHighPressure), getCircuitSlotOverlay());
                builder.widget(circuitSlot.setConsumer(this::getCircuitSlotTooltip))
                        .widget(new ClickButtonWidget(115, 62 + offsetY, 9, 9, "",
                                click -> this.circuitInventory.addCircuitValue(click.isShiftClick ? 5 : 1))
                                .setShouldClientCallback(true)
                                .setButtonTexture(GTLiteGuiTextures.BUTTON_INT_CIRCUIT_PLUS_STEAM.get(isHighPressure))
                                .setDisplayFunction(() -> this.circuitInventory.hasCircuitValue()
                                        && this.circuitInventory.getCircuitValue() < IntCircuitIngredient.CIRCUIT_MAX))
                        .widget(new ClickButtonWidget(115, 71 + offsetY, 9, 9, "",
                                click -> this.circuitInventory.addCircuitValue(click.isShiftClick ? -5 : -1))
                                .setShouldClientCallback(true)
                                .setButtonTexture(GTLiteGuiTextures.BUTTON_INT_CIRCUIT_MINUS_STEAM.get(isHighPressure))
                                .setDisplayFunction(() -> this.circuitInventory.hasCircuitValue()
                                        && this.circuitInventory.getCircuitValue() > IntCircuitIngredient.CIRCUIT_MIN));
            }
        }
    }

    protected TextureArea getCircuitSlotOverlay()
    {
        return GTLiteGuiTextures.INT_CIRCUIT_OVERLAY_STEAM.get(isHighPressure);
    }

    protected void getCircuitSlotTooltip(SlotWidget widget)
    {
        String configString;
        if (circuitInventory == null || circuitInventory.getCircuitValue() == GhostCircuitItemStackHandler.NO_CONFIG)
        {
            configString = new TextComponentTranslation("gregtech.gui.configurator_slot.no_value").getFormattedText();
        }
        else
        {
            configString = String.valueOf(circuitInventory.getCircuitValue());
        }
        widget.setTooltipText("gregtech.gui.configurator_slot.tooltip", configString);
    }

    protected void addSlot(ModularUI.Builder builder, int x, int y, int slotIndex,
                           IItemHandlerModifiable itemHandler, FluidTankList fluidHandler,
                           boolean isFluid, boolean isOutputs)
    {
        if (!isOutputs && isFluid)
            slotIndex++; // Skip steam slot.
        if (!isFluid)
        {
            builder.widget(new SlotWidget(itemHandler, slotIndex, x, y, true, !isOutputs)
                    .setBackgroundTexture(getOverlaysForSlot(isOutputs, false)));
        }
        else
        {
            builder.widget(new TankWidget(fluidHandler.getTankAt(slotIndex), x, y, 18, 18)
                    .setAlwaysShowFull(true)
                    .setBackgroundTexture(getOverlaysForSlot(isOutputs, true))
                    .setContainerClicking(true, !isOutputs));
        }
    }

    protected static int[] determineSlotsGrid(int itemInputCount)
    {
        if (itemInputCount == 3)
            return new int[] {3, 1};
        int slotsLeft = (int) Math.ceil(Math.sqrt(itemInputCount));
        int slotsDown = (int) Math.ceil(itemInputCount / (double) slotsLeft);
        return new int[] { slotsLeft, slotsDown };
    }

    // TODO isOutputs predicate?
    protected TextureArea[] getOverlaysForSlot(boolean isOutputs, boolean isFluid)
    {
        return new TextureArea[] {isFluid ? GTLiteGuiTextures.FLUID_SLOT_STEAM.get(isHighPressure)
                : GuiTextures.SLOT_STEAM.get(isHighPressure)};
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data)
    {
        super.writeToNBT(data);
        if (this.circuitInventory != null)
            this.circuitInventory.write(data);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data)
    {
        super.readFromNBT(data);
        if (this.circuitInventory != null)
        {
            if (data.hasKey("CircuitInventory", Constants.NBT.TAG_COMPOUND))
            {
                ItemStackHandler circuitStackHandler = new ItemStackHandler();
                for (int i = 0; i < circuitStackHandler.getSlots(); i++)
                {
                    ItemStack stack = circuitStackHandler.getStackInSlot(i);
                    if (stack.isEmpty())
                        continue;
                    stack = GTTransferUtils.insertItem(this.importItems, stack, false);
                    this.circuitInventory.setCircuitValueFromStack(stack);
                }
            }
            else
            {
                this.circuitInventory.read(data);
            }
        }
    }

    @Override
    public boolean hasGhostCircuitInventory()
    {
        return true;
    }

    @Override
    public void setGhostCircuitConfig(int config)
    {
        if (this.circuitInventory == null || this.circuitInventory.getCircuitValue() == config)
            return;
        this.circuitInventory.setCircuitValue(config);
        if (!this.getWorld().isRemote)
            markDirty();
    }

}
