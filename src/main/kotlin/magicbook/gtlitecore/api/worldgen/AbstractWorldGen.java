package magicbook.gtlitecore.api.worldgen;

import gregtech.common.ConfigHolder;
import magicbook.gtlitecore.api.worldgen.feature.AbstractFeature;
import magicbook.gtlitecore.api.worldgen.feature.FeatureCondition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class AbstractWorldGen extends WorldGenerator
{

    public final AbstractFeature feature;

    protected AbstractWorldGen(boolean notify, AbstractFeature feature)
    {
        super(notify);
        this.feature = feature;
    }

    // This method is different with WorldGenerator#generate,
    // we used BlockPos.MutableBlockPos as blockPos in this method, and this
    // method will be overridden to the original method.
    public abstract boolean generateMutable(World worldIn, Random random,
                                            BlockPos.MutableBlockPos blockPos);

    // This method control the WorldGen enabled/disabled by config system,
    // when overridden the method in extends class, then the correspondenced
    // method in ConfigHolder or other Forge @Config API holder can control
    // enabled/disabled of the WorldGen of this extends class.
    public abstract boolean enabledGenerator();

    @Override
    public boolean generate(@NotNull World worldIn,
                            @NotNull Random random,
                            @NotNull BlockPos blockPos)
    {
        return generateMutable(worldIn, random,
                new BlockPos.MutableBlockPos(blockPos));
    }

    // This method is a safety transform of WorldGenerator#setBlockAndNotifyAdequately().
    public void setBlock(World worldIn, BlockPos blockPos, IBlockState blockState)
    {
        if (worldIn.getBlockState(blockPos).getBlock()
                .canBeReplacedByLeaves(worldIn.getBlockState(blockPos),
                        worldIn, blockPos))
        {
            this.setBlockAndNotifyAdequately(worldIn, blockPos, blockState);
        }
    }

    // Get maximum generate amount of blocks which will be generated in a chunk.
    public int getAmountInChunk(List<FeatureCondition> conditions,
                                int chunkX, int chunkZ,
                                World worldIn, BlockPos blockPos)
    {
        Biome biome = worldIn.getBiome(blockPos);
        Optional<FeatureCondition> relevantCondition = conditions.stream()
                .filter(biomeCondition -> biomeCondition.isSatisfied(biome))
                .findFirst();
        double treeStrength = this.feature.getRandomStrength(chunkX, chunkZ);
        if (ConfigHolder.misc.debug) // Debug mode.
        {
            if (relevantCondition.isPresent())
            {
                if (relevantCondition.get().getPerlinCutoff(biome) < treeStrength)
                {
                    this.feature.updatePlacePercentage(true);
                    double perlinCutoff = relevantCondition.get().getPerlinCutoff(biome);
                    double maxFeatures = relevantCondition.get().getMaxFeatures();
                    return (int) (Math.ceil(maxFeatures - perlinCutoff * maxFeatures));
                }
                else
                {
                    this.feature.updatePlacePercentage(false);
                }
            }
        }
        else // Common mode.
        {
            if (relevantCondition.isPresent() && relevantCondition.get()
                    .getPerlinCutoff(biome) < treeStrength)
            {
                double perlinCutoff = relevantCondition.get().getPerlinCutoff(biome);
                double maxFeatures = relevantCondition.get().getMaxFeatures();
                return (int) (Math.ceil(maxFeatures - perlinCutoff * maxFeatures));
            }
        }
        return 0;
    }

    public boolean generateInChunk(@NotNull World worldIn,
                                   @NotNull Random random,
                                   int chunkX, int chunkZ)
    {
        if (!this.enabledGenerator()) // Disabled generator by #EnabledGenerator().
            return false;
        // Common processing of WorldGen in a chunk.
        Chunk chunk = worldIn.getChunk(chunkX, chunkZ);
        int seaLevel = chunk.getWorld().getSeaLevel();
        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos(
                chunk.getPos().getBlock(8, seaLevel, 8));

        int featureCount = this.getAmountInChunk(
                this.feature.featureConditions, chunkX, chunkZ, worldIn, blockPos);
        if (featureCount > 0)
        {
            for (int i = 0; i < featureCount; i++)
            {
                // Set up #blockPos for tree spawn offset by 8 to prevent cascading.
                blockPos.setPos(
                        chunk.x * 16 + random.nextInt(16) + 8,
                        255,
                        chunk.z * 16 + random.nextInt(16) + 8);
                // Air checking and set Y-axis.
                while (worldIn.isAirBlock(blockPos) && blockPos.getY() != 0)
                {
                    blockPos.setY(blockPos.getY() - 1);
                }
                blockPos.setY(blockPos.getY() + 1);

                if (!this.generateMutable(chunk.getWorld(), random, blockPos))
                    featureCount -= 1;
            }
            return true;
        }
        return false;
    }


}
