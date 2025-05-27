package magicbook.gtlitecore.common.metatileentity.multiblock.module;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.ConfigHolder;
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures;
import magicbook.gtlitecore.api.metatileentity.multiblock.ModuleMultiblockBase;
import magicbook.gtlitecore.api.recipe.frontend.SpacePumpRecipeFrontend;
import magicbook.gtlitecore.api.utils.tuples.Pair;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockSpaceElevatorCasing;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static gregtech.api.GTValues.VNF;
import static magicbook.gtlitecore.api.utils.GTLiteValues.SECOND;
import static magicbook.gtlitecore.api.utils.GTLiteValues.TICK;

public class MetaTileEntitySpacePump extends ModuleMultiblockBase
{

    private IMultipleTankHandler outputFluidInventory;

    private int[] planets = { 0, 0, 0, 0 };
    private int[] fluids = { 0, 0, 0, 0 };

    public MetaTileEntitySpacePump(ResourceLocation metaTileEntityId, int tier, int moduleTier, int minCasingTier)
    {
        super(metaTileEntityId, tier, moduleTier, minCasingTier);
        setMaxProgress(moduleTier == 3 ? SECOND : 4 * SECOND);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntitySpacePump(metaTileEntityId, tier, moduleTier, minCasingTier);
    }

    @Override
    protected void initializeAbilities()
    {
        this.outputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("C","C","C","C","C")
                .aisle("C","C","C","S","C")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .or(abilities(MultiblockAbility.EXPORT_FLUIDS)
                                .setPreviewCount(0)))
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.SPACE_ELEVATOR_CASING.getState(BlockSpaceElevatorCasing.CasingType.BASE_CASING);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.SPACE_ELEVATOR_BASE_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.SPACE_ELEVATOR_OVERLAY;
    }

    @NotNull
    @Override
    protected TextureArea getLogo()
    {
        return GTLiteGuiTextures.SPACE_ELEVATOR_LOGO_DARK;
    }

    @NotNull
    @Override
    protected TextureArea getWarningLogo()
    {
        return GTLiteGuiTextures.SPACE_ELEVATOR_LOGO_DARK;
    }

    @NotNull
    @Override
    protected TextureArea getErrorLogo()
    {
        return GTLiteGuiTextures.SPACE_ELEVATOR_LOGO_DARK;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState,
                                     Matrix4 translation,
                                     IVertexOperation[] pipeline)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        for (EnumFacing renderSide : EnumFacing.HORIZONTALS)
        {
            if (renderSide == getFrontFacing())
            {
                getFrontOverlay().renderOrientedState(renderState, translation, pipeline,
                        getFrontFacing(), isActive(), true);
            }
            else
            {
                GTLiteTextures.SPACE_PUMP_OVERLAY.renderSided(renderSide, renderState, translation, pipeline);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.tooltip.1"));
        if (moduleTier == 1)
            tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.mk1.tooltip.1"));
        else if (moduleTier == 2)
            tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.mk2.tooltip.1"));
        else
            tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.mk3.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.tooltip.2"));
        if (moduleTier == 1)
            tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.tooltip.3"));
        else
            tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.tooltip.4"));
        if (moduleTier == 3)
            tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.tooltip.5"));

        tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.max_parallel", VNF[tier]));
        tooltip.add(I18n.format("gtlitecore.machine.space_pump_module.orbit_tier", getOrbitTier(moduleTier)));
    }

    private String getOrbitTier(int moduleTier)
    {
        if (moduleTier == 1) return "MK1";
        else if (moduleTier == 2) return "MK2";
        else return "MK4";
    }

    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer entityPlayer)
    {
        ModularUI.Builder builder = ModularUI.builder(GTLiteGuiTextures.BACKGROUND_IRREGULAR, 311, 208)
                .image(4, 4, 190, 117, GuiTextures.DISPLAY)
                .widget(new IndicatorImageWidget(174, 101, 17, 17, getLogo())
                        .setWarningStatus(getWarningLogo(), this::addWarningText)
                        .setErrorStatus(getErrorLogo(), this::addErrorText))

                .label(9, 9, getMetaFullName(), 0xFFFFFF)
                .widget(new AdvancedTextWidget(9, 20, this::addDisplayText, 0xFFFFFF)
                        .setMaxWidthLimit(181)
                        .setClickHandler(this::handleDisplayClick));

        // Power Button
        IControllable controllable = getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null);
        if (controllable != null)
        {
            builder.widget(new ImageCycleButtonWidget(173, 183, 18, 18, GuiTextures.BUTTON_POWER,
                    controllable::isWorkingEnabled, controllable::setWorkingEnabled))
                    .widget(new ImageWidget(173, 201, 18, 6, GuiTextures.BUTTON_POWER_DETAIL));
        }

        // Voiding Mode Button
        builder.widget(new ImageCycleButtonWidget(173, 161, 18, 18, GuiTextures.BUTTON_VOID_MULTIBLOCK,
                4, this::getVoidingMode, this::setVoidingMode)
                        .setTooltipHoverString(MultiblockWithDisplayBase::getVoidingModeTooltip));

        // Sub Screen for setting (planet, fluid).
        builder.label(198 + 62 - 18 * 2 - 18 - 9, 36 - 12, "gtlitecore.machine.space_pump_module.configuration", 0x1F1E33)
                .widget(new ImageWidget(198 + 62 - 18 * 2 - 18 - 9, 36, 20, 20, GTLiteGuiTextures.SPACE_PUMP_MODULE_1))
                .widget(new ImageWidget(198 + 62 - 18 * 2 - 18 - 9, 36 + 22, 20, 20, GTLiteGuiTextures.SPACE_PUMP_MODULE_2))
                .widget(new ImageWidget(198 + 62 - 18 * 2 - 18 - 9, 36 + 22 * 2, 20, 20, GTLiteGuiTextures.SPACE_PUMP_MODULE_3))
                .widget(new ImageWidget(198 + 62 - 18 * 2 - 18 - 9, 36 + 22 * 3, 20, 20, GTLiteGuiTextures.SPACE_PUMP_MODULE_4));

        ServerWidgetGroup planetGroup1 = new ServerWidgetGroup(() -> true);
        planetGroup1.addWidget(new ImageWidget(198 + 62 - 18 * 2 - 9 + 4, 36, 53 / 2, 20, GuiTextures.DISPLAY)
                .setTooltip("gtlitecore.machine.space_pump_module.planet_setter"));

        planetGroup1.addWidget(new TextFieldWidget2(198 + 63 - 18 * 2 - 9 + 4, 42, 51 / 2, 20,
                () -> getPlanetValue(0), s -> setPlanetValue(0, s))
                .setCentered(true)
                .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                .setMaxLength(3));

        ServerWidgetGroup fluidGroup1 = new ServerWidgetGroup(() -> true);
        fluidGroup1.addWidget(new ImageWidget(198 + 62 - 18 * 2 + 18 * 2 + 4, 36, 53 / 2, 20, GuiTextures.DISPLAY)
                .setTooltip("gtlitecore.machine.space_pump_module.fluid_setter"));

        fluidGroup1.addWidget(new TextFieldWidget2(198 + 63 - 18 * 2 + 18 * 2 + 4, 42, 51 / 2, 20,
                () -> getFluidValue(0), s -> setFluidValue(0, s))
                .setCentered(true)
                .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                .setMaxLength(3));

        builder.widget(planetGroup1).widget(fluidGroup1);

        // For MK2/MK3, has more planetGroup to setting 2-4 planets and fluids.
        if (moduleTier > 1)
        {
            ServerWidgetGroup planetGroup2 = new ServerWidgetGroup(() -> true);
            planetGroup2.addWidget(new ImageWidget(198 + 62 - 18 * 2 - 9 + 4, 36 + 22, 53 / 2, 20, GuiTextures.DISPLAY)
                    .setTooltip("gtlitecore.machine.space_pump_module.planet_setter"));

            planetGroup2.addWidget(new TextFieldWidget2(198 + 63 - 18 * 2 - 9 + 4, 42 + 22, 51 / 2, 20,
                    () -> getPlanetValue(1), s -> setPlanetValue(1, s))
                    .setCentered(true)
                    .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                    .setMaxLength(3));

            ServerWidgetGroup fluidGroup2 = new ServerWidgetGroup(() -> true);
            fluidGroup2.addWidget(new ImageWidget(198 + 62 - 18 * 2 + 18 * 2 + 4, 36 + 22, 53 / 2, 20, GuiTextures.DISPLAY)
                    .setTooltip("gtlitecore.machine.space_pump_module.fluid_setter"));

            fluidGroup2.addWidget(new TextFieldWidget2(198 + 63 - 18 * 2 + 18 * 2 + 4, 42 + 22, 51 / 2, 20,
                    () -> getFluidValue(1), s -> setFluidValue(1, s))
                    .setCentered(true)
                    .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                    .setMaxLength(3));

            ServerWidgetGroup planetGroup3 = new ServerWidgetGroup(() -> true);
            planetGroup3.addWidget(new ImageWidget(198 + 62 - 18 * 2 - 9 + 4, 36 + 22 * 2, 53 / 2, 20, GuiTextures.DISPLAY)
                    .setTooltip("gtlitecore.machine.space_pump_module.planet_setter"));

            planetGroup3.addWidget(new TextFieldWidget2(198 + 63 - 18 * 2 - 9 + 4, 42 + 22 * 2, 51 / 2, 20,
                    () -> getPlanetValue(2), s -> setPlanetValue(2, s))
                    .setCentered(true)
                    .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                    .setMaxLength(3));

            ServerWidgetGroup fluidGroup3 = new ServerWidgetGroup(() -> true);
            fluidGroup3.addWidget(new ImageWidget(198 + 62 - 18 * 2 + 18 * 2 + 4, 36 + 22 * 2, 53 / 2, 20, GuiTextures.DISPLAY)
                    .setTooltip("gtlitecore.machine.space_pump_module.fluid_setter"));

            fluidGroup3.addWidget(new TextFieldWidget2(198 + 63 - 18 * 2 + 18 * 2 + 4, 42 + 22 * 2, 51 / 2, 20,
                    () -> getFluidValue(2), s -> setFluidValue(2, s))
                    .setCentered(true)
                    .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                    .setMaxLength(3));

            ServerWidgetGroup planetGroup4 = new ServerWidgetGroup(() -> true);
            planetGroup4.addWidget(new ImageWidget(198 + 62 - 18 * 2 - 9 + 4, 36 + 22 * 3, 53 / 2, 20, GuiTextures.DISPLAY)
                    .setTooltip("gtlitecore.machine.space_pump_module.planet_setter"));

            planetGroup4.addWidget(new TextFieldWidget2(198 + 63 - 18 * 2 - 9 + 4, 42 + 22 * 3, 51 / 2, 20,
                    () -> getPlanetValue(3), s -> setPlanetValue(3, s))
                    .setCentered(true)
                    .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                    .setMaxLength(3));

            ServerWidgetGroup fluidGroup4 = new ServerWidgetGroup(() -> true);
            fluidGroup4.addWidget(new ImageWidget(198 + 62 - 18 * 2 + 18 * 2 + 4, 36 + 22 * 3, 53 / 2, 20, GuiTextures.DISPLAY)
                    .setTooltip("gtlitecore.machine.space_pump_module.fluid_setter"));

            fluidGroup4.addWidget(new TextFieldWidget2(198 + 63 - 18 * 2 + 18 * 2 + 4, 42 + 22 * 3, 51 / 2, 20,
                    () -> getFluidValue(3), s -> setFluidValue(3, s))
                    .setCentered(true)
                    .setAllowedChars(TextFieldWidget2.WHOLE_NUMS)
                    .setMaxLength(3));

            builder.widget(planetGroup2).widget(fluidGroup2)
                    .widget(planetGroup3).widget(fluidGroup3)
                    .widget(planetGroup4).widget(fluidGroup4);
        }

        builder.widget(new ClickButtonWidget(173, 125 + 18, 18, 18, "", data -> reinitializeStructurePattern())
                .setButtonTexture(GTLiteGuiTextures.BUTTON_REFRESH_STRUCTURE_PATTERN)
                .setTooltipText("gtlitecore.machine.space_elevator.refresh_structure_pattern"));

        builder.bindPlayerInventory(entityPlayer.inventory, 125);

        return builder;

        // planetGroup1.addWidget(new IncrementButtonWidget(198 + 118 - 18 * 2, 36, 30, 20,
        //         1, 4, 16, 64,
        //         t -> setPlanetValue(0, String.valueOf(t)))
        //         .setDefaultTooltip()
        //         .setShouldClientCallback(false));
        // planetGroup1.addWidget(new IncrementButtonWidget(198 + 29 - 18 * 2, 36, 30, 20,
        //         -1, -4, -16, -64,
        //         t -> setPlanetValue(0, String.valueOf(t)))
        //         .setDefaultTooltip()
        //         .setShouldClientCallback(false));
    }

    private String getPlanetValue(int index)
    {
        return String.valueOf(planets[index]);
    }

    private void setPlanetValue(int index, String value)
    {
        planets[index] = Integer.parseInt(value);
    }

    private String getFluidValue(int index)
    {
        return String.valueOf(fluids[index]);
    }

    private void setFluidValue(int index, String value)
    {
        fluids[index] = Integer.parseInt(value);
    }

    @Override
    protected void updateFormedValid()
    {
        super.updateFormedValid();
        if (!isWorkingEnabled())
            return;

        if (!drainEnergy(true))
        {
            if (progressTime >= 2 * TICK)
            {
                if (ConfigHolder.machines.recipeProgressLowEnergy)
                    progressTime = TICK;
                else
                    progressTime = Math.max(TICK, progressTime - 2 * TICK);
            }
            return;
        }

        if (progressTime == 0 && !checkRecipes())
        {
            setActive(false);
        }
        else
        {
            drainEnergy(false);
            setActive(true);

            progressTime++;
            if (progressTime % getMaxProgress() != 0)
                return;
            progressTime = 0;

            List<FluidStack> fluidStacks = new ArrayList<>();
            for (int i = 0; i < 4; i++)
            {
                if (SpacePumpRecipeFrontend.RECIPES.containsKey(Pair.of(planets[i], fluids[i])))
                {
                    fluidStacks.add(SpacePumpRecipeFrontend.RECIPES.get(Pair.of(planets[i], fluids[i])));
                }
            }
            fluidStacks.forEach(fluidStack -> outputFluidInventory.fill(fluidStack, true));
        }
    }

    protected boolean checkRecipes()
    {
        if (moduleTier > 1)
        {
            for (int i = 0; i < 4; i++)
            {
                FluidStack fluidStack = SpacePumpRecipeFrontend.RECIPES.get(Pair.of(planets[i], fluids[i]));
                if (fluidStack != null)
                    return true;
            }
        }
        else
        {
            FluidStack fluidStack = SpacePumpRecipeFrontend.RECIPES.get(Pair.of(planets[0], fluids[0]));
            return fluidStack != null;
        }
        return false;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data)
    {
        super.writeToNBT(data);
        NBTTagList planetsNBT = new NBTTagList();
        NBTTagList fluidsNBT = new NBTTagList();
        for (int i = 0; i < 4; i++)
        {
            NBTTagCompound planetNBT = new NBTTagCompound();
            planetNBT.setInteger("planet", planets[i]);
            planetsNBT.appendTag(planetNBT);

            NBTTagCompound fluidNBT = new NBTTagCompound();
            fluidNBT.setInteger("fluid", fluids[i]);
            fluidsNBT.appendTag(fluidNBT);
        }
        data.setTag("planets", planetsNBT);
        data.setTag("fluids", fluidsNBT);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data)
    {
        super.readFromNBT(data);
        NBTTagList planetsNBT = data.getTagList("planets", Constants.NBT.TAG_COMPOUND);
        NBTTagList fluidsNBT = data.getTagList("fluids", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < 4; i++)
        {
            NBTTagCompound planetNBT = planetsNBT.getCompoundTagAt(i);
            planets[i] = planetNBT.getInteger("planet");

            NBTTagCompound fluidNBT = fluidsNBT.getCompoundTagAt(i);
            fluids[i] = fluidNBT.getInteger("fluid");
        }
    }

}
