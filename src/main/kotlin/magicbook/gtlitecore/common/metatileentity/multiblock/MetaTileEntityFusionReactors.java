package magicbook.gtlitecore.common.metatileentity.multiblock;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AtomicDouble;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.ImageCycleButtonWidget;
import gregtech.api.gui.widgets.ImageWidget;
import gregtech.api.gui.widgets.IndicatorImageWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.metatileentity.IFastRenderMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.logic.OCParams;
import gregtech.api.recipes.properties.RecipePropertyStorage;
import gregtech.api.recipes.properties.impl.FusionEUToStartProperty;
import gregtech.api.util.RelativeDirection;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.api.util.interpolate.Eases;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.IRenderSetup;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.shader.postprocessing.BloomEffect;
import gregtech.client.shader.postprocessing.BloomType;
import gregtech.client.utils.BloomEffectUtil;
import gregtech.client.utils.EffectRenderContext;
import gregtech.client.utils.IBloomEffect;
import gregtech.client.utils.RenderBufferHelper;
import gregtech.client.utils.RenderUtil;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import lombok.Getter;
import magicbook.gtlitecore.api.gui.GTLiteGuiTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockFusionCasing01;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.util.streamex.StreamEx;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleSupplier;

import static gregtech.api.util.RelativeDirection.DOWN;
import static gregtech.api.util.RelativeDirection.FRONT;
import static gregtech.api.util.RelativeDirection.LEFT;

public class MetaTileEntityFusionReactors extends RecipeMapMultiblockController implements IFastRenderMetaTileEntity, IBloomEffect
{

    // Default color of Fusion Reactor Ring Renderer.
    protected static final int NO_COLOR = 0;
    // Tier of Fusion Reactor, will dependent with Recipe EUt and EUToStart.
    @Getter
    private final int tier;
    // Input Energy Container List.
    private EnergyContainerList energyContainers;
    // Heat of Fusion Reactor, defined in MetaTileEntity class but serialized in Recipe Logic.
    @Getter
    private long heat = 0;
    // Color of Fusion Ring Renderer, has no color by default.
    @Getter
    private int fusionRingColor = NO_COLOR;
    // Used to render Progress Bar in Controller.
    private final FusionProgressBarSupplier progressBarSupplier;
    // Bloom Renderer Ticket register situation optional.
    @SideOnly(Side.CLIENT)
    private boolean registeredBloomRenderTicket;

    // =================================================================================================================
    public MetaTileEntityFusionReactors(ResourceLocation metaTileEntityId, int tier)
    {
        super(metaTileEntityId, RecipeMaps.FUSION_RECIPES);
        this.recipeMapWorkable = new AdvancedFusionRecipeLogic(this);
        this.tier = tier;
        this.energyContainer = new EnergyContainerHandler(this, 0, 0, 0, 0, 0)
        {

            @NotNull
            @Override
            public  String getName()
            {
                return GregtechDataCodes.FUSION_REACTOR_ENERGY_CONTAINER_TRAIT;
            }

        };
        this.progressBarSupplier = new FusionProgressBarSupplier();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityFusionReactors(metaTileEntityId, tier);
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        long energyStored = this.energyContainer.getEnergyStored();
        super.formStructure(context);
        this.initializeAbilities();
        ((EnergyContainerHandler) this.energyContainer).setEnergyStored(energyStored);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.energyContainer = new EnergyContainerHandler(this, 0, 0, 0, 0, 0)
        {

            @NotNull
            @Override
            public String getName()
            {
                return GregtechDataCodes.FUSION_REACTOR_ENERGY_CONTAINER_TRAIT;
            }

        };
        this.energyContainers = new EnergyContainerList(Lists.newArrayList());
        this.heat = 0;
        this.setFusionRingColor(NO_COLOR);
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("               ", "      OGO      ", "               ")
                .aisle("      ICI      ", "    GG###GG    ", "      ICI      ")
                .aisle("    CC   CC    ", "   E##OGO##E   ", "    CC   CC    ")
                .aisle("   C       C   ", "  EKEG   GEKE  ", "   C       C   ")
                .aisle("  C         C  ", " G#E       E#G ", "  C         C  ")
                .aisle("  C         C  ", " G#G       G#G ", "  C         C  ")
                .aisle(" I           I ", "O#O         O#O", " I           I ")
                .aisle(" C           C ", "G#G         G#G", " C           C ")
                .aisle(" I           I ", "O#O         O#O", " I           I ")
                .aisle("  C         C  ", " G#G       G#G ", "  C         C  ")
                .aisle("  C         C  ", " G#E       E#G ", "  C         C  ")
                .aisle("   C       C   ", "  EKEG   GEKE  ", "   C       C   ")
                .aisle("    CC   CC    ", "   E##OGO##E   ", "    CC   CC    ")
                .aisle("      ICI      ", "    GG###GG    ", "      ICI      ")
                .aisle("               ", "      OSO      ", "               ")
                .where('S', selfPredicate())
                .where('G', states(getCasingState(), getGlassState()))
                .where('E', states(getCasingState(), getGlassState())
                        .or(metaTileEntities(StreamEx.of(MetaTileEntities.ENERGY_INPUT_HATCH)
                                .filter(mte -> mte != null && tier <= mte.getTier() && mte.getTier() <= GTValues.UEV)
                                .toArray(MetaTileEntity[]::new))
                                .setMinGlobalLimited(1)
                                .setMaxGlobalLimited(16)))
                .where('C', states(getCasingState()))
                .where('K', states(getCoilState()))
                .where('I', states(getCasingState())
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS)
                                .setMinGlobalLimited(1)))
                .where('O', states(getCasingState(), this.getGlassState())
                        .or(abilities(MultiblockAbility.EXPORT_FLUIDS)
                                .setMinGlobalLimited(1)))
                .where('#', air())
                .where(' ', any())
                .build();
    }

    private IBlockState getCasingState()
    {
        if (this.tier == GTValues.UHV)
            return GTLiteMetaBlocks.FUSION_CASING_01.getState(BlockFusionCasing01.FusionCasingType.FUSION_CASING_MK4);
        return GTLiteMetaBlocks.FUSION_CASING_01.getState(BlockFusionCasing01.FusionCasingType.FUSION_CASING_MK5);
    }

    private IBlockState getGlassState()
    {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.FUSION_GLASS);
    }

    private IBlockState getCoilState()
    {
        if (tier == GTValues.UHV)
            return GTLiteMetaBlocks.FUSION_CASING_01.getState(BlockFusionCasing01.FusionCasingType.ADVANCED_FUSION_COIL);
        return GTLiteMetaBlocks.FUSION_CASING_01.getState(BlockFusionCasing01.FusionCasingType.ULTIMATE_FUSION_COIL);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart texture)
    {
        if (this.recipeMapWorkable.isActive())
            return Textures.ACTIVE_FUSION_TEXTURE;
        else
            return Textures.FUSION_TEXTURE;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return Textures.FUSION_REACTOR_OVERLAY;
    }

    @Override
    protected void initializeAbilities()
    {
        this.inputInventory = new ItemHandlerList(this.getAbilities(MultiblockAbility.IMPORT_ITEMS));
        this.inputFluidInventory = new FluidTankList(true, this.getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.outputInventory = new ItemHandlerList(this.getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.outputFluidInventory = new FluidTankList(true, this.getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        List<IEnergyContainer> energyInputs = this.getAbilities(MultiblockAbility.INPUT_ENERGY);
        this.energyContainers = new EnergyContainerList(energyInputs);
        long euCapacity = this.calculateEnergyStorageFactor(energyInputs.size());
        this.energyContainer = new EnergyContainerHandler(this, euCapacity, GTValues.V[tier], 0, 0, 0)
        {

            @NotNull
            @Override
            public String getName()
            {
                return GregtechDataCodes.FUSION_REACTOR_ENERGY_CONTAINER_TRAIT;
            }

        };
    }

    private long calculateEnergyStorageFactor(int energyInputAmount)
    {
        return energyInputAmount * (long) Math.pow(2, tier - GTValues.LuV) * 10000000L;
    }


    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("               ", "      WGW      ", "               ")
                .aisle("      DCD      ", "    GG   GG    ", "      UCU      ")
                .aisle("    CC   CC    ", "   w  EGE  s   ", "    CC   CC    ")
                .aisle("   C       C   ", "  nKeG   GeKn  ", "   C       C   ")
                .aisle("  C         C  ", " G s       w G ", "  C         C  ")
                .aisle("  C         C  ", " G G       G G ", "  C         C  ")
                .aisle(" D           D ", "N S         N S", " U           U ")
                .aisle(" C           C ", "G G         G G", " C           C ")
                .aisle(" D           D ", "N S         N S", " U           U ")
                .aisle("  C         C  ", " G G       G G ", "  C         C  ")
                .aisle("  C         C  ", " G s       w G ", "  C         C  ")
                .aisle("   C       C   ", "  eKnG   GnKe  ", "   C       C   ")
                .aisle("    CC   CC    ", "   w  WGW  s   ", "    CC   CC    ")
                .aisle("      DCD      ", "    GG   GG    ", "      UCU      ")
                .aisle("               ", "      EME      ", "               ")
                .where('M', tier == GTValues.UHV ? GTLiteMetaTileEntities.FUSION_REACTOR_MK4 : GTLiteMetaTileEntities.FUSION_REACTOR_MK5, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('G', getGlassState())
                .where('K', getCoilState())
                .where('W', MetaTileEntities.FLUID_EXPORT_HATCH[this.tier - 1], EnumFacing.NORTH)
                .where('E', MetaTileEntities.FLUID_EXPORT_HATCH[this.tier - 1], EnumFacing.SOUTH)
                .where('S', MetaTileEntities.FLUID_EXPORT_HATCH[this.tier - 1], EnumFacing.EAST)
                .where('N', MetaTileEntities.FLUID_EXPORT_HATCH[this.tier - 1], EnumFacing.WEST)
                .where('w', MetaTileEntities.ENERGY_INPUT_HATCH[this.tier], EnumFacing.WEST)
                .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[this.tier], EnumFacing.SOUTH)
                .where('s', MetaTileEntities.ENERGY_INPUT_HATCH[this.tier], EnumFacing.EAST)
                .where('n', MetaTileEntities.ENERGY_INPUT_HATCH[this.tier], EnumFacing.NORTH)
                .where('U', MetaTileEntities.FLUID_IMPORT_HATCH[this.tier - 1], EnumFacing.UP)
                .where('D', MetaTileEntities.FLUID_IMPORT_HATCH[this.tier - 1], EnumFacing.DOWN);
        shapeInfo.add(builder.shallowCopy()
                .where('G', this.getCasingState())
                .build());
        shapeInfo.add(builder.build());
        return shapeInfo;
    }

    // =================================================================================================================
    @Override
    protected void updateFormedValid()
    {
        if (this.energyContainers.getEnergyStored() > 0)
        {
            long energyAdded = this.energyContainer.addEnergy(this.energyContainers.getEnergyStored());
            if (energyAdded > 0)
                this.energyContainers.removeEnergy(energyAdded);
        }
        super.updateFormedValid();
        if (this.recipeMapWorkable.isWorking() && this.fusionRingColor == NO_COLOR)
        {
            Recipe previousRecipe = this.recipeMapWorkable.getPreviousRecipe();
            if (previousRecipe != null
                    && !previousRecipe.getFluidOutputs().isEmpty())
            {
                this.setFusionRingColor(0xFF000000 | previousRecipe.getFluidOutputs()
                        .get(0).getFluid().getColor());
            }
        }
        else if (!this.recipeMapWorkable.isWorking() && this.isStructureFormed())
        {
            this.setFusionRingColor(NO_COLOR);
        }
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf)
    {
        super.writeInitialSyncData(buf);
        buf.writeVarInt(this.fusionRingColor);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf)
    {
        super.receiveInitialSyncData(buf);
        this.fusionRingColor = buf.readVarInt();
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        if (dataId == GregtechDataCodes.UPDATE_COLOR)
        {
            this.fusionRingColor = buf.readVarInt();
        }
        else
        {
            super.receiveCustomData(dataId, buf);
        }
    }

    protected boolean hasFusionRingColor()
    {
        return this.fusionRingColor != NO_COLOR;
    }

    protected void setFusionRingColor(int fusionRingColor)
    {
        if (this.fusionRingColor != fusionRingColor)
        {
            this.fusionRingColor = fusionRingColor;
            this.writeCustomData(GregtechDataCodes.UPDATE_COLOR, buf -> buf.writeVarInt(fusionRingColor));
        }
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, world, tooltip, advanced);
        long energyCostEach = this.calculateEnergyStorageFactor(16) / 1000000L;
        tooltip.add(I18n.format("gtlitecore.machine.fusion_reactor.energy_cost",
                GTValues.V[tier] / 16, energyCostEach / 16));
        tooltip.add(I18n.format("gtlitecore.machine.fusion_reactor.recipe_request"));
        tooltip.add(TooltipHelper.RAINBOW_SLOW + I18n.format("gregtech.machine.perfect_oc"));
    }

    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer player)
    {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 198, 236)
                .image(4, 4, 190, 138, GuiTextures.DISPLAY) // Display
                .widget(new ProgressWidget(
                        () -> this.energyContainer.getEnergyCapacity() > 0
                                ? 1.0 * energyContainer.getEnergyStored()
                                    / energyContainer.getEnergyCapacity() : 0,
                        4, 144, 94, 7,
                        GuiTextures.PROGRESS_BAR_FUSION_ENERGY,
                        ProgressWidget.MoveType.HORIZONTAL)
                        .setHoverTextConsumer(this::addEnergyBarHoverText))
                .widget(new ProgressWidget(
                        () -> this.energyContainer.getEnergyCapacity() > 0
                                ? 1.0 * this.heat / this.energyContainer.getEnergyCapacity() : 0,
                        100, 144, 94, 7,
                        GuiTextures.PROGRESS_BAR_FUSION_HEAT,
                        ProgressWidget.MoveType.HORIZONTAL)
                        .setHoverTextConsumer(this::addHeatBarHoverText))
                .widget(new IndicatorImageWidget(174, 122,
                        17, 17, this.getLogo())
                        .setWarningStatus(this.getWarningLogo(), this::addWarningText)
                        .setErrorStatus(this.getErrorLogo(), this::addErrorText));
        // Fusion Reactor Title
        if (this.tier == GTValues.UHV)
        {
            builder.widget(new ImageWidget(66, 9,
                    67, 12, GTLiteGuiTextures.FUSION_REACTOR_MK4_TITLE)
                    .setIgnoreColor(true));
        }
        else
        {
            builder.widget(new ImageWidget(65, 9,
                    69, 12, GTLiteGuiTextures.FUSION_REACTOR_MK5_TITLE)
                    .setIgnoreColor(true));
        }
        // Diagram + Progress Bar
        builder.widget(new ImageWidget(55, 24,
                89, 101, GuiTextures.FUSION_REACTOR_DIAGRAM)
                .setIgnoreColor(true))
                .widget(FusionProgressBarSupplier.Type.BOTTOM_LEFT.getWidget(this))
                .widget(FusionProgressBarSupplier.Type.TOP_LEFT.getWidget(this))
                .widget(FusionProgressBarSupplier.Type.TOP_RIGHT.getWidget(this))
                .widget(FusionProgressBarSupplier.Type.BOTTOM_RIGHT.getWidget(this))
                .widget(new ImageWidget(7, 98, 108, 41,
                        GuiTextures.FUSION_REACTOR_LEGEND)
                        .setIgnoreColor(true))
                .widget(new ImageCycleButtonWidget(173, 211,
                        18, 18, GuiTextures.BUTTON_POWER,
                        this.recipeMapWorkable::isWorkingEnabled,
                        this.recipeMapWorkable::setWorkingEnabled))
                .widget(new ImageWidget(173, 229,
                        18, 6, GuiTextures.BUTTON_POWER_DETAIL))
                .widget(new ImageCycleButtonWidget(173, 189,
                        18, 18, GuiTextures.BUTTON_VOID_MULTIBLOCK,
                        4, this::getVoidingMode, this::setVoidingMode)
                        .setTooltipHoverString(MultiblockWithDisplayBase::getVoidingModeTooltip))
                .widget(new ImageWidget(173, 171,
                        18, 18, GuiTextures.BUTTON_NO_DISTINCT_BUSES)
                        .setTooltip("gregtech.multiblock.universal.distinct_not_supported"))
                .widget(getFlexButton(173, 153, 18, 18))
                .bindPlayerInventory(player.inventory, 153);
        return builder;
    }

    private void addEnergyBarHoverText(List<ITextComponent> hoverList)
    {
        ITextComponent energyInfo = TextComponentUtil.stringWithColor(
                TextFormatting.AQUA,
                TextFormattingUtil.formatNumbers(this.energyContainer.getEnergyStored()) + " / " +
                        TextFormattingUtil.formatNumbers(this.energyContainer.getEnergyCapacity()) + " EU");
        hoverList.add(TextComponentUtil.translationWithColor(
                TextFormatting.GRAY,
                "gregtech.multiblock.energy_stored",
                energyInfo));
    }

    private void addHeatBarHoverText(List<ITextComponent> hoverList)
    {
        ITextComponent heatInfo = TextComponentUtil.stringWithColor(
                TextFormatting.RED,
                TextFormattingUtil.formatNumbers(this.heat) + " / " +
                        TextFormattingUtil.formatNumbers(this.energyContainer.getEnergyCapacity()));
        hoverList.add(TextComponentUtil.translationWithColor(
                TextFormatting.GRAY,
                "gregtech.multiblock.fusion_reactor.heat",
                heatInfo));
    }

    @SideOnly(Side.CLIENT)
    public void renderMetaTileEntity(double x, double y, double z, float partialTicks)
    {
        if (this.hasFusionRingColor() && !this.registeredBloomRenderTicket)
        {
            this.registeredBloomRenderTicket = true;
            BloomEffectUtil.registerBloomRender(MetaTileEntityFusionReactors.FusionBloomSetup.INSTANCE, getBloomType(), this, this);
        }
    }

    @SideOnly(Side.CLIENT)
    public void renderBloomEffect(@NotNull BufferBuilder buffer, @NotNull EffectRenderContext context)
    {
        if (this.hasFusionRingColor())
        {
            int color = RenderUtil.interpolateColor(this.getFusionRingColor(), -1,
                    Eases.QUAD_IN.getInterpolation(Math.abs((float) Math.abs(this.getOffsetTimer() % 50L)
                            + context.partialTicks() - 25.0F) / 25.0F));
            float a = (float) (color >> 24 & 255) / 255.0F;
            float r = (float) (color >> 16 & 255) / 255.0F;
            float g = (float) (color >> 8 & 255) / 255.0F;
            float b = (float) (color & 255) / 255.0F;
            EnumFacing relativeBack = RelativeDirection.BACK.getRelativeFacing(this.getFrontFacing(),
                    this.getUpwardsFacing(), this.isFlipped());
            EnumFacing.Axis axis = RelativeDirection.UP.getRelativeFacing(this.getFrontFacing(),
                    this.getUpwardsFacing(), this.isFlipped()).getAxis();
            buffer.begin(8, DefaultVertexFormats.POSITION_COLOR);
            RenderBufferHelper.renderRing(buffer,
                    (double) this.getPos().getX() - context.cameraX()
                            + (double) (relativeBack.getXOffset() * 7) + 0.5,
                    (double) this.getPos().getY() - context.cameraY()
                            + (double) (relativeBack.getYOffset() * 7) + 0.5,
                    (double) this.getPos().getZ() - context.cameraZ()
                            + (double) (relativeBack.getZOffset() * 7) + 0.5,
                    6.0, 0.2, 10, 20, r, g, b, a, axis);
            Tessellator.getInstance().draw();
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldRenderBloomEffect(@NotNull EffectRenderContext context)
    {
        return this.hasFusionRingColor() && context.camera().isBoundingBoxInFrustum(this.getRenderBoundingBox());
    }

    public AxisAlignedBB getRenderBoundingBox()
    {
        EnumFacing relativeRight = RelativeDirection.RIGHT.getRelativeFacing(this.getFrontFacing(), this.getUpwardsFacing(), this.isFlipped());
        EnumFacing relativeBack = RelativeDirection.BACK.getRelativeFacing(this.getFrontFacing(), this.getUpwardsFacing(), this.isFlipped());
        return new AxisAlignedBB(this.getPos().offset(relativeBack).offset(relativeRight, 6), this.getPos().offset(relativeBack, 13).offset(relativeRight.getOpposite(), 6));
    }

    @Override
    public boolean shouldRenderInPass(int pass)
    {
        return pass == 0;
    }

    @Override
    public boolean isGlobalRenderer()
    {
        return true;
    }

    private static BloomType getBloomType()
    {
        ConfigHolder.FusionBloom fusionBloom = ConfigHolder.client.shader.fusionBloom;
        return BloomType.fromValue(fusionBloom.useShader ? fusionBloom.bloomStyle : -1);
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return false;
    }

    @Override
    public boolean hasMaintenanceMechanics()
    {
        return false;
    }

    protected class AdvancedFusionRecipeLogic extends MultiblockRecipeLogic
    {

        public AdvancedFusionRecipeLogic(MetaTileEntityFusionReactors tileEntity)
        {
            super(tileEntity, true);
        }

        @Override
        public long getMaxVoltage()
        {
            return Math.min(GTValues.V[tier], super.getMaxVoltage());
        }

        @Override
        public void updateWorkable()
        {
            super.updateWorkable();
            // Drain heat when the reactor is not active, is paused via soft mallet, or does not have enough energy and
            // has fully wiped recipe progress
            // Don't drain heat when there is not enough energy and there is still some recipe progress, as that makes
            // it doubly hard to complete the recipe
            // (Will have to recover heat and recipe progress)
            if (heat > 0) {
                if (!isActive || !workingEnabled || (hasNotEnoughEnergy && progressTime == 0)) {
                    heat = heat <= 10000 ? 0 : (heat - 10000);
                }
            }
        }

        @Override
        public boolean checkRecipe(@NotNull Recipe recipe)
        {
            if (!super.checkRecipe(recipe))
                return false;

            // If the reactor is not able to hold enough energy for it, do not run the recipe
            if (recipe.getProperty(FusionEUToStartProperty.getInstance(), 0L)
                    > energyContainer.getEnergyCapacity())
                return false;

            long heatDiff = recipe.getProperty(FusionEUToStartProperty.getInstance(), 0L) - heat;
            // If the stored heat is >= required energy, recipe is okay to run
            if (heatDiff <= 0)
                return true;

            // If the remaining energy needed is more than stored, do not run.
            if (energyContainer.getEnergyStored() < heatDiff)
                return false;

            // Remove the energy needed
            energyContainer.removeEnergy(heatDiff);
            // Increase the stored heat
            heat += heatDiff;
            return true;
        }

        @Override
        protected void modifyOverclockPre(@NotNull OCParams ocParams, @NotNull RecipePropertyStorage storage)
        {
            super.modifyOverclockPre(ocParams, storage);
            long euToStart = storage.get(FusionEUToStartProperty.getInstance(), 0L);
            int fusionTier = FusionEUToStartProperty.getFusionTier(euToStart);
            if (fusionTier != 0)
                fusionTier = MetaTileEntityFusionReactors.this.tier - fusionTier;
            ocParams.setOcAmount(Math.min(fusionTier, ocParams.ocAmount()));
        }

        @NotNull
        @Override
        public NBTTagCompound serializeNBT()
        {
            NBTTagCompound tag = super.serializeNBT();
            tag.setLong("Heat", heat);
            return tag;
        }

        @Override
        public void deserializeNBT(@NotNull NBTTagCompound compound)
        {
            super.deserializeNBT(compound);
            heat = compound.getLong("Heat");
        }

        @Override
        protected void setActive(boolean active)
        {
            if (active != isActive)
                MetaTileEntityFusionReactors.this.progressBarSupplier.resetCountdown();
            super.setActive(active);
        }

    }

    private static class FusionProgressBarSupplier
    {

        private final AtomicDouble tracker = new AtomicDouble(0.0);
        private final ProgressWidget.TimedProgressSupplier bottomLeft;
        private final DoubleSupplier topLeft;
        private final DoubleSupplier topRight;
        private final DoubleSupplier bottomRight;

        public FusionProgressBarSupplier()
        {

            // Bottom Left, fill on [0, 0.25)
            bottomLeft = new ProgressWidget.TimedProgressSupplier(200, 164, false) {

                @Override
                public double getAsDouble()
                {
                    double val = super.getAsDouble();
                    tracker.set(val);
                    if (val >= 0.25) {
                        return 1;
                    }
                    return 4 * val;
                }

                @Override
                public void resetCountdown()
                {
                    super.resetCountdown();
                    tracker.set(0);
                }
            };

            // Top Left, fill on [0.25, 0.5)
            topLeft = () -> {
                double val = tracker.get();
                if (val < 0.25) {
                    return 0;
                } else if (val >= 0.5) {
                    return 1;
                }
                return 4 * (val - 0.25);
            };

            // Top Right, fill on [0.5, 0.75)
            topRight = () -> {
                double val = tracker.get();
                if (val < 0.5) {
                    return 0;
                } else if (val >= 0.75) {
                    return 1;
                }
                return 4 * (val - 0.5);
            };

            // Bottom Right, fill on [0.75, 1.0]
            bottomRight = () -> {
                double val = tracker.get();
                if (val < 0.75) {
                    return 0;
                } else if (val >= 1) {
                    return 1;
                }
                return 4 * (val - 0.75);
            };
        }

        @SuppressWarnings("unused")
        public void resetCountdown()
        {
            bottomLeft.resetCountdown();
        }

        public DoubleSupplier getSupplier(Type type)
        {
            final DoubleSupplier result;
            switch (type)
            {
                case BOTTOM_LEFT:
                    result = bottomLeft;
                    break;
                case TOP_LEFT:
                    result = topLeft;
                    break;
                case TOP_RIGHT:
                    result = topRight;
                    break;
                case BOTTOM_RIGHT:
                    result = bottomRight;
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected type: " + type);
            }
            return result;
        }

        private enum Type
        {

            BOTTOM_LEFT(61, 66, 35, 41,
                    GuiTextures.PROGRESS_BAR_FUSION_REACTOR_DIAGRAM_BL, ProgressWidget.MoveType.VERTICAL),
            TOP_LEFT(61, 30, 41, 35,
                    GuiTextures.PROGRESS_BAR_FUSION_REACTOR_DIAGRAM_TL, ProgressWidget.MoveType.HORIZONTAL),
            TOP_RIGHT(103, 30, 35, 41,
                    GuiTextures.PROGRESS_BAR_FUSION_REACTOR_DIAGRAM_TR, ProgressWidget.MoveType.VERTICAL_DOWNWARDS),
            BOTTOM_RIGHT(97, 72, 41, 35,
                    GuiTextures.PROGRESS_BAR_FUSION_REACTOR_DIAGRAM_BR, ProgressWidget.MoveType.HORIZONTAL_BACKWARDS);

            private final int x;
            private final int y;
            private final int width;
            private final int height;
            private final TextureArea texture;
            private final ProgressWidget.MoveType moveType;

            Type(int x, int y, int width, int height, TextureArea texture, ProgressWidget.MoveType moveType)
            {
                this.x = x;
                this.y = y;
                this.width = width;
                this.height = height;
                this.texture = texture;
                this.moveType = moveType;
            }

            public ProgressWidget getWidget(MetaTileEntityFusionReactors instance)
            {
                return new ProgressWidget(
                        () -> instance.recipeMapWorkable.isActive() ?
                                instance.progressBarSupplier.getSupplier(this).getAsDouble() : 0,
                        x, y, width, height, texture, moveType)
                        .setIgnoreColor(true)
                        .setHoverTextConsumer(
                                tl -> MultiblockDisplayText.builder(tl, instance.isStructureFormed())
                                        .setWorkingStatus(instance.recipeMapWorkable.isWorkingEnabled(),
                                                instance.recipeMapWorkable.isActive())
                                        .addWorkingStatusLine());
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private static final class FusionBloomSetup implements IRenderSetup
    {
        private static final MetaTileEntityFusionReactors.FusionBloomSetup INSTANCE = new MetaTileEntityFusionReactors.FusionBloomSetup();
        float lastBrightnessX;
        float lastBrightnessY;

        private FusionBloomSetup() {}

        public void preDraw(@NotNull BufferBuilder buffer)
        {
            BloomEffect.strength = (float) ConfigHolder.client.shader.fusionBloom.strength;
            BloomEffect.baseBrightness = (float)ConfigHolder.client.shader.fusionBloom.baseBrightness;
            BloomEffect.highBrightnessThreshold = (float)ConfigHolder.client.shader.fusionBloom.highBrightnessThreshold;
            BloomEffect.lowBrightnessThreshold = (float)ConfigHolder.client.shader.fusionBloom.lowBrightnessThreshold;
            BloomEffect.step = 1.0F;
            this.lastBrightnessX = OpenGlHelper.lastBrightnessX;
            this.lastBrightnessY = OpenGlHelper.lastBrightnessY;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
            GlStateManager.disableTexture2D();
        }

        public void postDraw(@NotNull BufferBuilder buffer)
        {
            GlStateManager.enableTexture2D();
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, this.lastBrightnessX, this.lastBrightnessY);
        }

    }

}
