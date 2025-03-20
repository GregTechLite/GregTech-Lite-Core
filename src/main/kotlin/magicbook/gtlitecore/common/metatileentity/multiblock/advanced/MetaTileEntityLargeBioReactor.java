package magicbook.gtlitecore.common.metatileentity.multiblock.advanced;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.properties.impl.CleanroomProperty;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing01;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.util.streamex.StreamEx;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static gregtech.api.util.RelativeDirection.DOWN;
import static gregtech.api.util.RelativeDirection.FRONT;
import static gregtech.api.util.RelativeDirection.LEFT;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.consistent;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.getOrDefault;
import static magicbook.gtlitecore.api.utils.GTLiteUtility.maxLength;
import static magicbook.gtlitecore.api.utils.StructureUtility.cleanroomCasings;
import static magicbook.gtlitecore.api.utils.StructureUtility.sensorCasings;

public class MetaTileEntityLargeBioReactor extends MultiMapMultiblockController
{

    @Getter
    private int sensorCasingTier;
    @Getter
    private int cleanroomCasingTier;

    private static boolean hasRegistered = false;
    private static List<IBlockState> sensorCasings;
    private static List<IBlockState> cleanroomCasings;

    // =================================================================================================================
    public MetaTileEntityLargeBioReactor(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, new RecipeMap[] {
                GTLiteRecipeMaps.BIO_REACTOR_RECIPES(),
                GTLiteRecipeMaps.GREENHOUSE_RECIPES()
        });
        this.recipeMapWorkable = new LargeBioReactorRecipeLogic(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityLargeBioReactor(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        List<IBlockState> sensorCasing = StreamEx.of(GTLiteAPI.MAP_SENSOR_CASING.entrySet())
                .sortedByInt(entry -> ((WrappedIntTier) entry.getValue()).getIntTier())
                .map(Map.Entry::getKey)
                .toList();
        List<IBlockState> cleanroomCasing = StreamEx.of(GTLiteAPI.MAP_CLEANROOM_CASING.entrySet())
                .sortedByInt(entry -> ((WrappedIntTier) entry.getValue()).getIntTier())
                .map(Map.Entry::getKey)
                .toList();
        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(sensorCasing);
            add(cleanroomCasing);
        }});
        sensorCasings = consistent(sensorCasing, maxLength);
        cleanroomCasings = consistent(cleanroomCasing, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("SensorCasingTieredStats");
        Object type2 = context.get("CleanroomCasingTieredStats");
        this.sensorCasingTier = getOrDefault(
                () -> type1 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type1).getIntTier(), 0);
        this.cleanroomCasingTier = getOrDefault(
                () -> type2 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type2).getIntTier(), 0);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.sensorCasingTier = 0;
        this.cleanroomCasingTier = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("CCCCC", "CCCCC", "CCCCC", "     ")
                .aisle("CCCCC", "D###D", "D###D", "CCCCC")
                .aisle("CCCCC", "DT#TD", "D###D", "CFFFC")
                .aisle("CCCCC", "D###D", "D###D", "CCCCC")
                .aisle("CCSCC", "CGGGC", "CGGGC", "     ")
                .where('S', selfPredicate())
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(10)
                        .or(autoAbilities(true, true, true, true, true, true, false)))
                .where('D', states(getSecondCasingState()))
                .where('G', states(getGlassState()))
                .where('T', sensorCasings())
                .where('F', cleanroomCasings())
                .where('#', air())
                .where(' ', any())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_01.getState(BlockMetalCasing01.MetalCasingType.RED_STEEL);
    }

    private static IBlockState getSecondCasingState()
    {
        return MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING);
    }

    private static IBlockState getGlassState()
    {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart)
    {
        return GTLiteTextures.RED_STEEL_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return Textures.PROCESSING_ARRAY_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes()
    {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("ECCCC", "CCCCC", "CCCCC", "     ")
                .aisle("CCCCC", "D   D", "D   D", "CCCCC")
                .aisle("CCCCC", "DT TD", "D   D", "CFFFC")
                .aisle("CCCCC", "D   D", "D   D", "CCNCC")
                .aisle("IJSKL", "CGGGC", "CGGGC", "     ")
                .where('S', GTLiteMetaTileEntities.LARGE_BIO_REACTOR, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('D', getSecondCasingState())
                .where('G', getGlassState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
                .where('N', () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH : getCasingState(), EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(sensorCasings)
                .map(b -> {
                    if (builder != null)
                    {
                        builder.where('T', b);
                        builder.where('F', cleanroomCasings.get(count.get()));
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
            if (this.sensorCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE, buf -> {});
            if (this.cleanroomCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE, buf -> {});
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        super.receiveCustomData(dataId, buf);
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.sensorCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.cleanroomCasingTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.sensorCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            this.cleanroomCasingTier = buf.readInt();
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World player,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtlitecore.machine.large_bio_reactor.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.large_bio_reactor.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.large_bio_reactor.tooltip.3") + TooltipHelper.RAINBOW_SLOW + I18n.format("gregtech.machine.perfect_oc"));
        tooltip.add(I18n.format("gtlitecore.machine.large_bio_reactor.tooltip.4"));
        tooltip.add(I18n.format("gtlitecore.machine.large_bio_reactor.tooltip.5"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct() {
        return true;
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe, boolean consumeIfSuccess)
    {
        if (this.getRecipeMap() == GTLiteRecipeMaps.BIO_REACTOR_RECIPES())
        {
            if (super.checkRecipe(recipe, consumeIfSuccess) &&
                    recipe.getProperty(CleanroomProperty.getInstance(), CleanroomType.CLEANROOM)
                            != CleanroomType.STERILE_CLEANROOM)
            {
                return true;
            }
            else if (super.checkRecipe(recipe, consumeIfSuccess)
                    && recipe.getProperty(CleanroomProperty.getInstance(), CleanroomType.CLEANROOM)
                        == CleanroomType.STERILE_CLEANROOM && this.cleanroomCasingTier >= 2) {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return super.checkRecipe(recipe, consumeIfSuccess);
        }
    }

    protected class LargeBioReactorRecipeLogic extends MultiblockRecipeLogic
    {

        public LargeBioReactorRecipeLogic(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity);
        }

        @Override
        protected double getOverclockingDurationFactor()
        {
            return getMaxVoltage() >= GTValues.V[GTValues.UV] ? 0.25 : 0.5;
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.8, getSensorCasingTier()))));
        }

        @Override
        public int getParallelLimit()
        {
            return 16 * GTUtility.getTierByVoltage(this.getMaxVoltage());
        }

    }

}
