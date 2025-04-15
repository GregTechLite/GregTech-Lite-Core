package magicbook.gtlitecore.common.metatileentity.multiblock.advanced;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.core.sound.GTSoundEvents;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.recipe.property.AdvancedFusionTieredProperty;
import magicbook.gtlitecore.api.utils.stream.LazyStreams;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.util.streamex.StreamEx;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static gregtech.api.util.RelativeDirection.DOWN;
import static gregtech.api.util.RelativeDirection.FRONT;
import static gregtech.api.util.RelativeDirection.LEFT;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.consistent;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.getOrDefault;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.maxLength;
import static magicbook.gtlitecore.api.utils.StructureUtility.cryostats;
import static magicbook.gtlitecore.api.utils.StructureUtility.divertors;
import static magicbook.gtlitecore.api.utils.StructureUtility.fusionCasings;
import static magicbook.gtlitecore.api.utils.StructureUtility.fusionCoils;
import static magicbook.gtlitecore.api.utils.StructureUtility.vacuums;

public class MetaTileEntityAdvancedFusionReactor extends RecipeMapMultiblockController
{

    @Getter
    private int fusionCasingTier;
    @Getter
    private int fusionCoilTier;
    @Getter
    private int cryostatTier;
    @Getter
    private int divertorTier;
    @Getter
    private int vacuumTier;

    private static boolean hasRegistered = false;
    private static List<IBlockState> fusionCasings;
    private static List<IBlockState> fusionCoils;
    private static List<IBlockState> cryostats;
    private static List<IBlockState> divertors;
    private static List<IBlockState> vacuums;

    // =================================================================================================================
    public MetaTileEntityAdvancedFusionReactor(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, GTLiteRecipeMaps.ADVANCED_FUSION_RECIPES());
        this.recipeMapWorkable = new AdvancedFusionRecipeLogic(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityAdvancedFusionReactor(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        List<IBlockState> fusionCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_FUSION_CASING);
        List<IBlockState> fusionCoil = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_FUSION_COIL);
        List<IBlockState> cryostat = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_CRYOSTAT);
        List<IBlockState> divertor = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_DIVERTOR);
        List<IBlockState> vacuum = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_VACUUM);
        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(fusionCasing);
            add(fusionCoil);
            add(cryostat);
            add(divertor);
            add(vacuum);
        }});
        fusionCasings = consistent(fusionCasing, maxLength);
        fusionCoils = consistent(fusionCoil, maxLength);
        cryostats = consistent(cryostat, maxLength);
        divertors = consistent(divertor, maxLength);
        vacuums = consistent(vacuum, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("FusionCasingTieredStats");
        Object type2 = context.get("FusionCoilTieredStats");
        Object type3 = context.get("CryostatTieredStats");
        Object type4 = context.get("DivertorTieredStats");
        Object type5 = context.get("VacuumTieredStats");
        this.fusionCasingTier = getOrDefault(
                () -> type1 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type1).getIntTier(), 0);
        this.fusionCoilTier = getOrDefault(
                () -> type2 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type2).getIntTier(), 0);
        this.cryostatTier = getOrDefault(
                () -> type3 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type3).getIntTier(), 0);
        this.divertorTier = getOrDefault(
                () -> type4 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type4).getIntTier(), 0);
        this.vacuumTier = getOrDefault(
                () -> type5 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type5).getIntTier(), 0);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.fusionCasingTier = 0;
        this.fusionCoilTier = 0;
        this.cryostatTier = 0;
        this.divertorTier = 0;
        this.vacuumTier = 0;
    }

    @Override
    protected void initializeAbilities()
    {
        super.initializeAbilities();
        List<IEnergyContainer> inputEnergy = new ArrayList<>(getAbilities(MultiblockAbility.INPUT_ENERGY));
        inputEnergy.addAll(getAbilities(MultiblockAbility.INPUT_LASER));
        this.energyContainer = new EnergyContainerList(inputEnergy);
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("               ", "               ", "     cECEc     ", "     cECEc     ", "               ", "               ")
                .aisle("               ", "       C       ", "   icvvvvvci   ", "   icvvvvvci   ", "       C       ", "               ")
                .aisle("       C       ", "  C  ddddd  C  ", "  Cvv#####vvC  ", "  Cvv#####vvC  ", "  C  bbbbb  C  ", "       C       ")
                .aisle("   C   C   C   ", "   ddddddddd   ", " Iv#########vI ", " Iv#########vI ", "   bbbbbbbbb   ", "   C   C   C   ")
                .aisle("    C     C    ", "   ddd#C#ddd   ", " cv###vvv###vc ", " cv###vvv###vc ", "   bbb#C#bbb   ", "    C     C    ")
                .aisle("               ", "  dddC###Cddd  ", "cv###v#C#v###vc", "cv###v#C#v###vc", "  bbbC###Cbbb  ", "               ")
                .aisle("               ", "  dd##CCC##dd  ", "Ev##v#CXC#v##vE", "Ev##v#CXC#v##vE", "  bb##CCC##bb  ", "               ")
                .aisle("  CC       CC  ", " CddC#CCC#CddC ", "Cv##vCXXXCv##vC", "Cv##vCXXXCv##vC", " CbbC#CCC#CbbC ", "  CC       CC  ")
                .aisle("               ", "  dd##CCC##dd  ", "Ev##v#CXC#v##vE", "Ev##v#CXC#v##vE", "  bb##CCC##bb  ", "               ")
                .aisle("               ", "  dddC###Cddd  ", "cv###v#C#v###vc", "cv###v#C#v###vc", "  bbbC###Cbbb  ", "               ")
                .aisle("    C     C    ", "   ddd#C#ddd   ", " cv###vvv###vc ", " cv###vvv###vc ", "   bbb#C#bbb   ", "    C     C    ")
                .aisle("   C   C   C   ", "   ddddddddd   ", " Iv#########vI ", " Iv#########vI ", "   bbbbbbbbb   ", "   C   C   C   ")
                .aisle("       C       ", "  C  ddddd  C  ", "  Cvv#####vvC  ", "  Cvv#####vvC  ", "  C  bbbbb  C  ", "       C       ")
                .aisle("               ", "       C       ", "   icvvvvvci   ", "   icvvvvvci   ", "       S       ", "               ")
                .aisle("               ", "               ", "     cECEc     ", "     cECEc     ", "               ", "               ")
                .where('S', selfPredicate())
                .where('c', fusionCasings())
                .where('E', fusionCasings()
                        .or(abilities(MultiblockAbility.INPUT_ENERGY)
                                .setMaxGlobalLimited(16))
                        .or(abilities(MultiblockAbility.INPUT_LASER)
                                .setMaxGlobalLimited(16)))
                .where('I', fusionCasings()
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS)
                                .setMinGlobalLimited(1)
                                .setPreviewCount(8)))
                .where('i', fusionCasings()
                        .or(abilities(MultiblockAbility.EXPORT_FLUIDS)
                                .setMinGlobalLimited(2)
                                .setPreviewCount(8)))
                .where('b', fusionCasings())
                .where('X', fusionCoils())
                .where('C', cryostats())
                .where('d', divertors())
                .where('v', vacuums())
                .where('#', air())
                .where(' ', any())
                .build();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
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
        return GTLiteTextures.ADVANCED_FUSION_REACTOR_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("               ", "               ", "     c2C2c     ", "     c2C2c     ", "               ", "               ")
                .aisle("               ", "       C       ", "   8cvvvvvc8   ", "   8cvvvvvc8   ", "       C       ", "               ")
                .aisle("       C       ", "  C  ddddd  C  ", "  Cvv     vvC  ", "  Cvv     vvC  ", "  C  bbbbb  C  ", "       C       ")
                .aisle("   C   C   C   ", "   ddddddddd   ", " 6v         v6 ", " 6v         v6 ", "   bbbbbbbbb   ", "   C   C   C   ")
                .aisle("    C     C    ", "   ddd C ddd   ", " cv   vvv   vc ", " cv   vvv   vc ", "   bbb C bbb   ", "    C     C    ")
                .aisle("               ", "  dddC   Cddd  ", "cv   v C v   vc", "cv   v C v   vc", "  bbbC   Cbbb  ", "               ")
                .aisle("               ", "  dd  CCC  dd  ", "3v  v CXC v  v4", "3v  v CXC v  v4", "  bb  CCC  bb  ", "               ")
                .aisle("  CC       CC  ", " CddC CCC CddC ", "Cv  vCXXXCv  vC", "Cv  vCXXXCv  vC", " CbbC CCC CbbC ", "  CC       CC  ")
                .aisle("               ", "  dd  CCC  dd  ", "3v  v CXC v  v4", "3v  v CXC v  v4", "  bb  CCC  bb  ", "               ")
                .aisle("               ", "  dddC   Cddd  ", "cv   v C v   vc", "cv   v C v   vc", "  bbbC   Cbbb  ", "               ")
                .aisle("    C     C    ", "   ddd C ddd   ", " cv   vvv   vc ", " cv   vvv   vc ", "   bbb C bbb   ", "    C     C    ")
                .aisle("   C   C   C   ", "   ddddddddd   ", " 5v         v5 ", " 5v         v5 ", "   bbbbbbbbb   ", "   C   C   C   ")
                .aisle("       C       ", "  C  ddddd  C  ", "  Cvv     vvC  ", "  Cvv     vvC  ", "  C  bbbbb  C  ", "       C       ")
                .aisle("               ", "       C       ", "   7cvvvvvc7   ", "   7cvvvvvc7   ", "       S       ", "               ")
                .aisle("               ", "               ", "     c1C1c     ", "     c1C1c     ", "               ", "               ")
                .where('S', GTLiteMetaTileEntities.ADVANCED_FUSION_REACTOR, EnumFacing.SOUTH)
                .where('1', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.SOUTH)
                .where('2', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
                .where('3', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.WEST)
                .where('4', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.EAST)
                .where('5', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('6', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.NORTH)
                .where('7', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH)
                .where('8', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.NORTH);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(fusionCasings)
                .map(b -> {
                    if (builder != null)
                    {
                        builder.where('c', b);
                        builder.where('b', b);
                        builder.where('X', fusionCoils.get(count.get()));
                        builder.where('C', cryostats.get(count.get()));
                        builder.where('d', divertors.get(count.get()));
                        builder.where('v', vacuums.get(count.get()));
                        count.getAndIncrement();
                    }
                    return builder;
                })
                .nonNull()
                .forEach(b -> shapeInfo.add(b.build()));
        return shapeInfo;
    }

    // =================================================================================================================
    @Override
    public void update()
    {
        super.update();
        if (this.getWorld().isRemote)
        {
            if (this.fusionCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE, buf -> {});
            if (this.fusionCoilTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE, buf -> {});
            if (this.cryostatTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE, buf -> {});
            if (this.divertorTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_LAST_TIERED_MACHINE, buf -> {});
            if (this.vacuumTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_EXTRA_TIERED_MACHINE, buf -> {});
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        super.receiveCustomData(dataId, buf);
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.fusionCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.fusionCoilTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE, b -> b.writeInt(this.cryostatTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_LAST_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_LAST_TIERED_MACHINE, b -> b.writeInt(this.divertorTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_EXTRA_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_EXTRA_TIERED_MACHINE, b -> b.writeInt(this.vacuumTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.fusionCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            this.fusionCoilTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE)
            this.cryostatTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_LAST_TIERED_MACHINE)
            this.divertorTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_EXTRA_TIERED_MACHINE)
            this.vacuumTier = buf.readInt();
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.4"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.5"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.6"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.7"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.8"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.9"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.10"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.11"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.12"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.13"));
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.14"));
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

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe, boolean consumeIfSuccess)
    {
        return super.checkRecipe(recipe, consumeIfSuccess)
                && this.fusionCasingTier >= recipe.getProperty(AdvancedFusionTieredProperty.getInstance(), 0)
                && this.fusionCoilTier >= recipe.getProperty(AdvancedFusionTieredProperty.getInstance(), 0)
                && this.cryostatTier >= recipe.getProperty(AdvancedFusionTieredProperty.getInstance(), 0)
                && this.divertorTier >= recipe.getProperty(AdvancedFusionTieredProperty.getInstance(), 0)
                && this.vacuumTier >= recipe.getProperty(AdvancedFusionTieredProperty.getInstance(), 0);
    }

    @Override
    public SoundEvent getBreakdownSound()
    {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    protected class AdvancedFusionRecipeLogic extends MultiblockRecipeLogic
    {

        public AdvancedFusionRecipeLogic(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity);
        }

        @Override
        protected double getOverclockingDurationFactor()
        {
            return 0.125;
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.5, Math.min(getFusionCasingTier(), Math.min(getVacuumTier(),  getCryostatTier()))))));
        }

        @Override
        public int getParallelLimit()
        {
            return 64 * Math.min(getFusionCoilTier(), getDivertorTier());
        }

    }

}
