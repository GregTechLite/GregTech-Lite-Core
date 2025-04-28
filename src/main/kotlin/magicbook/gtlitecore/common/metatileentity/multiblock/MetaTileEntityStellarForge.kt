package magicbook.gtlitecore.common.metatileentity.multiblock;

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
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.core.sound.GTSoundEvents;
import lombok.Getter;
import magicbook.gtlitecore.api.GTLiteAPI;
import magicbook.gtlitecore.api.block.impl.WrappedIntTier;
import magicbook.gtlitecore.api.capability.GTLiteDataCodes;
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps;
import magicbook.gtlitecore.api.unification.GTLiteMaterials;
import magicbook.gtlitecore.api.utils.stream.LazyStreams;
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures;
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks;
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03;
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01;
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
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
import static magicbook.gtlitecore.api.utils.StructureUtility.emitterCasings;
import static magicbook.gtlitecore.api.utils.StructureUtility.fieldGenCasings;

public class MetaTileEntityStellarForge extends RecipeMapMultiblockController
{

    @Getter
    private int emitterCasingTier;
    @Getter
    private int fieldGenCasingTier;

    private static boolean hasRegistered = false;
    private static List<IBlockState> emitterCasings;
    private static List<IBlockState> fieldGenCasings;

    // =================================================================================================================
    public MetaTileEntityStellarForge(ResourceLocation metaTileEntityId)
    {
        super(metaTileEntityId, GTLiteRecipeMaps.STELLAR_FORGE_RECIPES());
        this.recipeMapWorkable = new StellarForgeRecipeLogic(this);
        this.registerCasingMaps();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity)
    {
        return new MetaTileEntityStellarForge(metaTileEntityId);
    }

    private void registerCasingMaps()
    {
        if (hasRegistered) return;
        List<IBlockState> emitterCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_EMITTER_CASING);
        List<IBlockState> fieldGenCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_FIELD_GEN_CASING);
        int maxLength = maxLength(new ArrayList<List<IBlockState>>() {{
            add(emitterCasing);
            add(fieldGenCasing);
        }});
        emitterCasings = consistent(emitterCasing, maxLength);
        fieldGenCasings = consistent(fieldGenCasing, maxLength);
        hasRegistered = true;
    }

    // =================================================================================================================
    @Override
    protected void formStructure(PatternMatchContext context)
    {
        super.formStructure(context);
        Object type1 = context.get("EmitterCasingTieredStats");
        Object type2 = context.get("FieldGenCasingTieredStats");
        this.emitterCasingTier = getOrDefault(
                () -> type1 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type1).getIntTier(), 0);
        this.fieldGenCasingTier = getOrDefault(
                () -> type2 instanceof WrappedIntTier,
                () -> ((WrappedIntTier) type2).getIntTier(), 0);
    }

    @Override
    public void invalidateStructure()
    {
        super.invalidateStructure();
        this.emitterCasingTier = 0;
        this.fieldGenCasingTier = 0;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern()
    {
        return FactoryBlockPattern.start()
                .aisle("                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ")
                .aisle("        A A        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        AAA        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        AAA        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        A A        ")
                .aisle("        A A        ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "        A A        ")
                .aisle("        A A        ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "        A A        ")
                .aisle("        A A        ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "        A A        ")
                .aisle("        A A        ", " A A           A A ", "  C C   EEE   C C  ", " A A    EFE    A A ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", " A A    EFE    A A ", "  C C   EEE   C C  ", " A A           A A ", "        A A        ")
                .aisle("        A A        ", " A A    EEE    A A ", "  C C  E   E  C C  ", " A A   E   E   A A ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", " A A   E   E   A A ", "  C C  E   E  C C  ", " A A    EEE    A A ", "        A A        ")
                .aisle("        A A        ", "A A    EEEEE    A A", " C C  E     E  C C ", "A A   E     E   A A", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "A A   E     E   A A", " C C  E     E  C C ", "A A    EEEEE    A A", "        A A        ")
                .aisle(" AAAAAAA   AAAAAAA ", "Q A   EEEEEEE   A Q", "QC C E       E C CQ", "Q A  E       E  A Q", " A   E       E   A ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " A   E       E   A ", "Q A  E       E  A Q", "QC C E       E C CQ", "Q A   EEEEEEE   A Q", " AAAAAAAA AAAAAAAA ")
                .aisle("         G         ", "Q A   EEEHEEE   A Q", " C C E       E C C ", "Q A  F       F  A Q", " A   F       F   A ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", " A   F       F   A ", "Q A  F       F  A Q", " C C E       E C C ", "Q A   EEEHEEE   A Q", "         G         ")
                .aisle(" AAAAAAA   AAAAAAA ", "Q A   EEEEEEE   A Q", "QC C E       E C CQ", "Q A  E       E  A Q", " A   E       E   A ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " A   E       E   A ", "Q A  E       E  A Q", "QC C E       E C CQ", "Q A   EEEEEEE   A Q", " AAAAAAAA AAAAAAAA ")
                .aisle("        A A        ", "A A    EEEEE    A A", " C C  E     E  C C ", "A A   E     E   A A", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "A A   E     E   A A", " C C  E     E  C C ", "A A    EEEEE    A A", "        A A        ")
                .aisle("        A A        ", " A A    EEE    A A ", "  C C  E   E  C C  ", " A A   E   E   A A ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", " A A   E   E   A A ", "  C C  E   E  C C  ", " A A    EEE    A A ", "        A A        ")
                .aisle("        A A        ", " A A           A A ", "  C C   EEE   C C  ", " A A    EFE    A A ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", " A A    EFE    A A ", "  C C   EEE   C C  ", " A A           A A ", "        A A        ")
                .aisle("        A A        ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "        A A        ")
                .aisle("        A A        ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "        A A        ")
                .aisle("        A A        ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "        A A        ")
                .aisle("        A A        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        AAA        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        AAA        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        A A        ")
                .aisle("                   ", "       AQQQA       ", "        QSQ        ", "       AQQQA       ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ")
                .where('S', this.selfPredicate())
                // Both `A` and `Q` can put hatches but Energy Hatches can only replace `Q`,
                // because then we can see that one structure can hold almost 8 controller.
                .where('A', states(getCasingState())
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
                                MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS)))
                .where('Q', states(getCasingState())
                        .or(abilities(MultiblockAbility.INPUT_ENERGY)
                                .setMinGlobalLimited(1)
                                .setMaxGlobalLimited(3)
                                .setPreviewCount(1))
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
                                MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS)))
                .where('D', frames(GTLiteMaterials.Bedrockium))
                .where('E', states(getSecondCasingState()))
                .where('F', states(getGlassState()))
                .where('C', states(getCoilState()))
                .where('G', fieldGenCasings())
                .where('H', emitterCasings())
                .where(' ', any())
                .build();
    }

    private static IBlockState getCasingState()
    {
        return GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.QUANTUM_ALLOY);
    }

    private static IBlockState getSecondCasingState()
    {
        return GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.STELLAR_CONTAINMENT_CASING);
    }

    private static IBlockState getGlassState()
    {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.FUSION_GLASS);
    }

    private static IBlockState getCoilState()
    {
        return GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.HARMONIC_PHONON_TRANSMISSION_CASING);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart texture)
    {
        return GTLiteTextures.QUANTUM_ALLOY_CASING;
    }

    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay()
    {
        return GTLiteTextures.STELLAR_FORGE_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ")
                .aisle("        A A        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        AAA        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        AAA        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        A A        ")
                .aisle("        A A        ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "        A A        ")
                .aisle("        A A        ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "        A A        ")
                .aisle("        A A        ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "        A A        ")
                .aisle("        A A        ", " A A           A A ", "  C C   EEE   C C  ", " A A    EFE    A A ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", " A A    EFE    A A ", "  C C   EEE   C C  ", " A A           A A ", "        A A        ")
                .aisle("        A A        ", " A A    EEE    A A ", "  C C  E   E  C C  ", " A A   E   E   A A ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", " A A   E   E   A A ", "  C C  E   E  C C  ", " A A    EEE    A A ", "        A A        ")
                .aisle("        A A        ", "A A    EEEEE    A A", " C C  E     E  C C ", "A A   E     E   A A", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "A A   E     E   A A", " C C  E     E  C C ", "A A    EEEEE    A A", "        A A        ")
                .aisle(" AAAAAAA   AAAAAAA ", "Q A   EEEEEEE   A Q", "QC C E       E C CQ", "Q A  E       E  A Q", " A   E       E   A ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " A   E       E   A ", "Q A  E       E  A Q", "QC C E       E C CQ", "Q A   EEEEEEE   A Q", " AAAAAAAA AAAAAAAA ")
                .aisle("         G         ", "Q A   EEEHEEE   A Q", " C C E       E C C ", "Q A  F       F  A Q", " A   F       F   A ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", " A   F       F   A ", "Q A  F       F  A Q", " C C E       E C C ", "Q A   EEEHEEE   A Q", "         G         ")
                .aisle(" AAAAAAA   AAAAAAA ", "Q A   EEEEEEE   A Q", "QC C E       E C CQ", "Q A  E       E  A Q", " A   E       E   A ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " A   E       E   A ", "Q A  E       E  A Q", "QC C E       E C CQ", "Q A   EEEEEEE   A Q", " AAAAAAAA AAAAAAAA ")
                .aisle("        A A        ", "A A    EEEEE    A A", " C C  E     E  C C ", "A A   E     E   A A", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "A A   E     E   A A", " C C  E     E  C C ", "A A    EEEEE    A A", "        A A        ")
                .aisle("        A A        ", " A A    EEE    A A ", "  C C  E   E  C C  ", " A A   E   E   A A ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", " A A   E   E   A A ", "  C C  E   E  C C  ", " A A    EEE    A A ", "        A A        ")
                .aisle("        A A        ", " A A           A A ", "  C C   EEE   C C  ", " A A    EFE    A A ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", " A A    EFE    A A ", "  C C   EEE   C C  ", " A A           A A ", "        A A        ")
                .aisle("        A A        ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "        A A        ")
                .aisle("        A A        ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "        A A        ")
                .aisle("        A A        ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "        A A        ")
                .aisle("        A A        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        AAA        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        AAA        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        A A        ")
                .aisle("                   ", "       AKPLA       ", "        ISJ        ", "       AQQQA       ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ")
                .where('S', GTLiteMetaTileEntities.STELLAR_FORGE, EnumFacing.SOUTH)
                .where('A', getCasingState())
                .where('Q', getCasingState())
                .where('D', MetaBlocks.FRAMES.get(GTLiteMaterials.Bedrockium).getBlock(GTLiteMaterials.Bedrockium))
                .where('E', getSecondCasingState())
                .where('F', getGlassState())
                .where('C', getCoilState())
                .where('P', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH);
        AtomicInteger count = new AtomicInteger();
        StreamEx.of(emitterCasings)
                .map(b -> {
                    if (builder != null)
                    {
                        builder.where('H', b);
                        builder.where('G', fieldGenCasings.get(count.get()));
                    }
                    count.getAndIncrement();
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
            if (this.emitterCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE, buf -> {});
            if (this.fieldGenCasingTier == 0)
                this.writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE, buf -> {});
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf)
    {
        super.receiveCustomData(dataId, buf);
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE, b -> b.writeInt(this.emitterCasingTier));
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            this.writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE, b -> b.writeInt(this.fieldGenCasingTier));
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            this.emitterCasingTier = buf.readInt();
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            this.fieldGenCasingTier = buf.readInt();
    }

    // =================================================================================================================
    @Override
    public void addInformation(ItemStack stack,
                               @Nullable World world,
                               @NotNull List<String> tooltip,
                               boolean advanced)
    {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(TooltipHelper.RAINBOW_SLOW + I18n.format("gregtech.machine.perfect_oc"));
        tooltip.add(I18n.format("gtlitecore.machine.stellar_forge.tooltip.1"));
        tooltip.add(I18n.format("gtlitecore.machine.stellar_forge.tooltip.2"));
        tooltip.add(I18n.format("gtlitecore.machine.stellar_forge.tooltip.3"));
        tooltip.add(I18n.format("gtlitecore.machine.stellar_forge.tooltip.4"));
        tooltip.add(I18n.format("gtlitecore.machine.stellar_forge.tooltip.5"));
        tooltip.add(I18n.format("gtlitecore.machine.stellar_forge.tooltip.6"));
    }

    // =================================================================================================================
    @Override
    public boolean canBeDistinct()
    {
        return true;
    }

    @Override
    public boolean hasMaintenanceMechanics()
    {
        return false;
    }

    @Override
    public SoundEvent getBreakdownSound()
    {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    protected class StellarForgeRecipeLogic extends MultiblockRecipeLogic
    {

        public StellarForgeRecipeLogic(RecipeMapMultiblockController tileEntity)
        {
            super(tileEntity, true);
        }

        @Override
        public void setMaxProgress(int maxProgress)
        {
            super.setMaxProgress((int) (Math.floor(maxProgress * Math.pow(0.5, getEmitterCasingTier()))));
        }

        @Override
        public int getParallelLimit()
        {
            return 32 * getFieldGenCasingTier();
        }

    }

}
