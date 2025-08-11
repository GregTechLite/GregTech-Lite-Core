package gregtechlite.gtlitecore.mixins.gregtech;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.BlockInfo;
import gregtech.api.util.RelativeDirection;
import gregtechlite.gtlitecore.api.pattern.BlockPatternExtension;
import gregtechlite.gtlitecore.api.pattern.GTLitePredicate;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Mixin(value = BlockPattern.class, remap = false)
public abstract class MixinBlockPattern implements BlockPatternExtension
{

    @Shadow
    static EnumFacing[] FACINGS;

    @Shadow
    @Final
    protected int fingerLength;

    @Shadow
    @Final
    protected int thumbLength;

    @Shadow
    @Final
    protected int palmLength;

    @Shadow
    @Final
    protected TraceabilityPredicate[][][] blockMatches;

    @Shadow
    @Final
    public RelativeDirection[] structureDir;

    @Unique
    private int gtlitecore$previewPages;

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Override
    public int getPreviewPages()
    {
        if (gtlitecore$previewPages <= 0)
        {
            return gtlitecore$previewPages = Arrays.stream(blockMatches)
                    .flatMap(Arrays::stream)
                    .flatMap(Arrays::stream)
                    .flatMap(predicate -> Stream.concat(predicate.common.stream(), predicate.limited.stream()))
                    .filter(GTLitePredicate.class::isInstance)
                    .map(GTLitePredicate.class::cast)
                    .filter(GTLitePredicate::getPreviewCandidates)
                    .mapToInt(predicate -> predicate.candidates.get().length)
                    .max().orElse(1);
        }
        else
        {
            return gtlitecore$previewPages;
        }
    }

    // TODO When CEu rewrite block pattern checks and merged PR, rework this class.
    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Override
    public @NotNull BlockInfo @NotNull [] @NotNull [] @NotNull [] getPreview(int @NotNull [] repetition, int candidateIndex)
    {
        Map<TraceabilityPredicate.SimplePredicate, BlockInfo[]> cacheInfos = new HashMap<>();
        Map<TraceabilityPredicate.SimplePredicate, Integer> cacheGlobal = new HashMap<>();
        Map<BlockPos, BlockInfo> blocks = new HashMap<>();
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int minZ = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int maxZ = Integer.MIN_VALUE;
        for (int l = 0, x = 0; l < this.fingerLength; l++)
        {
            for (int r = 0; r < repetition[l]; r++)
            {
                // Checking single slice
                Map<TraceabilityPredicate.SimplePredicate, Integer> cacheLayer = new HashMap<>();
                for (int y = 0; y < this.thumbLength; y++)
                {
                    for (int z = 0; z < this.palmLength; z++)
                    {
                        TraceabilityPredicate predicate = this.blockMatches[l][y][z];
                        boolean find = false;
                        // patch
                        boolean previewCandidates = false;
                        // patch end
                        BlockInfo[] infos = null;
                        for (TraceabilityPredicate.SimplePredicate limit : predicate.limited) // check layer and
                        {
                            // previewCount
                            if (limit.minLayerCount > 0)
                            {
                                if (!cacheLayer.containsKey(limit))
                                {
                                    cacheLayer.put(limit, 1);
                                }
                                else if (cacheLayer.get(limit) < limit.minLayerCount)
                                {
                                    cacheLayer.put(limit, cacheLayer.get(limit) + 1);
                                }
                                else
                                {
                                    continue;
                                }
                                if (cacheGlobal.getOrDefault(limit, 0) < limit.previewCount)
                                {
                                    if (!cacheGlobal.containsKey(limit))
                                    {
                                        cacheGlobal.put(limit, 1);
                                    }
                                    else if (cacheGlobal.get(limit) < limit.previewCount)
                                    {
                                        cacheGlobal.put(limit, cacheGlobal.get(limit) + 1);
                                    }
                                    else
                                    {
                                        continue;
                                    }
                                }
                            }
                            else
                            {
                                continue;
                            }
                            if (!cacheInfos.containsKey(limit))
                            {
                                cacheInfos.put(limit, limit.candidates == null ? null : limit.candidates.get());
                            }
                            infos = cacheInfos.get(limit);
                            find = true;
                            // patch
                            previewCandidates = gtlitecore$shouldPreviewCandidates(limit);
                            // patch end
                            break;
                        }
                        if (!find) // check global and previewCount
                        {
                            for (TraceabilityPredicate.SimplePredicate limit : predicate.limited)
                            {
                                if (limit.minGlobalCount == -1 && limit.previewCount == -1) continue;
                                if (cacheGlobal.getOrDefault(limit, 0) < limit.previewCount)
                                {
                                    if (!cacheGlobal.containsKey(limit))
                                    {
                                        cacheGlobal.put(limit, 1);
                                    }
                                    else if (cacheGlobal.get(limit) < limit.previewCount)
                                    {
                                        cacheGlobal.put(limit, cacheGlobal.get(limit) + 1);
                                    }
                                    else
                                    {
                                        continue;
                                    }
                                }
                                else if (limit.minGlobalCount > 0)
                                {
                                    if (!cacheGlobal.containsKey(limit))
                                    {
                                        cacheGlobal.put(limit, 1);
                                    }
                                    else if (cacheGlobal.get(limit) < limit.minGlobalCount)
                                    {
                                        cacheGlobal.put(limit, cacheGlobal.get(limit) + 1);
                                    }
                                    else
                                    {
                                        continue;
                                    }
                                }
                                else
                                {
                                    continue;
                                }
                                if (!cacheInfos.containsKey(limit))
                                {
                                    cacheInfos.put(limit, limit.candidates == null ? null : limit.candidates.get());
                                }
                                infos = cacheInfos.get(limit);
                                find = true;
                                // patch
                                previewCandidates = gtlitecore$shouldPreviewCandidates(limit);
                                // patch end
                                break;
                            }
                        }
                        if (!find) // check common with previewCount
                        {
                            for (TraceabilityPredicate.SimplePredicate common : predicate.common)
                            {
                                if (common.previewCount > 0)
                                {
                                    if (!cacheGlobal.containsKey(common))
                                    {
                                        cacheGlobal.put(common, 1);
                                    }
                                    else if (cacheGlobal.get(common) < common.previewCount)
                                    {
                                        cacheGlobal.put(common, cacheGlobal.get(common) + 1);
                                    }
                                    else {
                                        continue;
                                    }
                                }
                                else
                                {
                                    continue;
                                }
                                if (!cacheInfos.containsKey(common))
                                {
                                    cacheInfos.put(common, common.candidates == null ? null : common.candidates.get());
                                }
                                infos = cacheInfos.get(common);
                                find = true;
                                // patch
                                previewCandidates = gtlitecore$shouldPreviewCandidates(common);
                                // patch end
                                break;
                            }
                        }
                        if (!find) // check without previewCount
                        {
                            for (TraceabilityPredicate.SimplePredicate common : predicate.common)
                            {
                                if (common.previewCount == -1)
                                {
                                    if (!cacheInfos.containsKey(common))
                                    {
                                        cacheInfos.put(common,
                                                common.candidates == null ? null : common.candidates.get());
                                    }
                                    infos = cacheInfos.get(common);
                                    find = true;
                                    // patch
                                    previewCandidates = gtlitecore$shouldPreviewCandidates(common);
                                    // patch end
                                    break;
                                }
                            }
                        }
                        if (!find) // check max
                        {
                            for (TraceabilityPredicate.SimplePredicate limit : predicate.limited)
                            {
                                if (limit.previewCount != -1)
                                {
                                    continue;
                                }
                                else if (limit.maxGlobalCount != -1 || limit.maxLayerCount != -1)
                                {
                                    if (cacheGlobal.getOrDefault(limit, 0) < limit.maxGlobalCount)
                                    {
                                        if (!cacheGlobal.containsKey(limit))
                                        {
                                            cacheGlobal.put(limit, 1);
                                        }
                                        else
                                        {
                                            cacheGlobal.put(limit, cacheGlobal.get(limit) + 1);
                                        }
                                    }
                                    else if (cacheLayer.getOrDefault(limit, 0) < limit.maxLayerCount)
                                    {
                                        if (!cacheLayer.containsKey(limit))
                                        {
                                            cacheLayer.put(limit, 1);
                                        }
                                        else
                                        {
                                            cacheLayer.put(limit, cacheLayer.get(limit) + 1);
                                        }
                                    }
                                    else
                                    {
                                        continue;
                                    }
                                }

                                if (!cacheInfos.containsKey(limit))
                                {
                                    cacheInfos.put(limit, limit.candidates == null ? null : limit.candidates.get());
                                }
                                infos = cacheInfos.get(limit);
                                // patch
                                previewCandidates = gtlitecore$shouldPreviewCandidates(limit);
                                // patch end
                                break;
                            }
                        }
                        // patch
                        int index = previewCandidates ? Math.min(candidateIndex, infos.length - 1) : 0;
                        BlockInfo info = infos == null || infos.length == 0 ? BlockInfo.EMPTY : infos[index];
                        // patch end
                        BlockPos pos = RelativeDirection.setActualRelativeOffset(z, y, x, EnumFacing.NORTH,
                                EnumFacing.UP, false, structureDir);
                        // TODO
                        if (info.getTileEntity() instanceof MetaTileEntityHolder)
                        {
                            MetaTileEntityHolder holder = new MetaTileEntityHolder();
                            holder.setMetaTileEntity(((MetaTileEntityHolder) info.getTileEntity()).getMetaTileEntity());
                            holder.getMetaTileEntity().onPlacement();
                            info = new BlockInfo(holder.getMetaTileEntity().getBlock().getDefaultState(), holder);
                        }
                        blocks.put(pos, info);
                        minX = Math.min(pos.getX(), minX);
                        minY = Math.min(pos.getY(), minY);
                        minZ = Math.min(pos.getZ(), minZ);
                        maxX = Math.max(pos.getX(), maxX);
                        maxY = Math.max(pos.getY(), maxY);
                        maxZ = Math.max(pos.getZ(), maxZ);
                    }
                }
                x++;
            }
        }
        BlockInfo[][][] result = (BlockInfo[][][]) Array.newInstance(BlockInfo.class, maxX - minX + 1, maxY - minY + 1,
                maxZ - minZ + 1);
        int finalMinX = minX;
        int finalMinY = minY;
        int finalMinZ = minZ;
        blocks.forEach((pos, info) -> {
            if (info.getTileEntity() instanceof MetaTileEntityHolder)
            {
                MetaTileEntity metaTileEntity = ((MetaTileEntityHolder) info.getTileEntity()).getMetaTileEntity();
                boolean find = false;
                for (EnumFacing enumFacing : FACINGS)
                {
                    if (metaTileEntity.isValidFrontFacing(enumFacing))
                    {
                        if (!blocks.containsKey(pos.offset(enumFacing)))
                        {
                            metaTileEntity.setFrontFacing(enumFacing);
                            find = true;
                            break;
                        }
                    }
                }
                if (!find)
                {
                    for (EnumFacing enumFacing : FACINGS)
                    {
                        BlockInfo blockInfo = blocks.get(pos.offset(enumFacing));
                        if (blockInfo != null && blockInfo.getBlockState().getBlock() == Blocks.AIR &&
                                metaTileEntity.isValidFrontFacing(enumFacing)) {
                            metaTileEntity.setFrontFacing(enumFacing);
                            break;
                        }
                    }
                }
            }
            result[pos.getX() - finalMinX][pos.getY() - finalMinY][pos.getZ() - finalMinZ] = info;
        });
        return result;
    }

    @Unique
    private boolean gtlitecore$shouldPreviewCandidates(TraceabilityPredicate.SimplePredicate predicate)
    {
        if (predicate instanceof GTLitePredicate)
        {
            return ((GTLitePredicate) predicate).getPreviewCandidates();
        }
        return false;
    }

}
