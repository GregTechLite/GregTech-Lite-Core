package magicbook.gtlitecore.api.worldgen.feature;

import magicbook.gtlitecore.api.utils.GTLiteLog;
import magicbook.gtlitecore.api.utils.functions.TriConsumer;
import magicbook.gtlitecore.api.worldgen.AbstractWorldGenerator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorSimplex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractFeature
{

    private NoiseGeneratorSimplex generatorSimplex;

    private final int seed;

    public final List<FeatureCondition> featureConditions = new ArrayList<>();
    public static final List<AbstractFeature> features = new ArrayList<>();

    protected AbstractWorldGenerator FEATURE_GROW_INSTANCE;
    protected AbstractWorldGenerator WORLD_GEN_INSTANCE;

    private int totalChunksChecked;
    private int totalChunksPlaced;

    public AbstractFeature(int seed)
    {
        this.seed = seed;
        features.add(this);
    }

    public abstract boolean generate(World worldIn,
                                     BlockPos.MutableBlockPos blockPos,
                                     Random random,
                                     TriConsumer<World, BlockPos, IBlockState> notifier);

    public void setWorld(World worldIn)
    {
        this.generatorSimplex = new NoiseGeneratorSimplex(
                new Random(worldIn.getSeed() + this.seed));
    }

    public double getRandomStrength(int chunkX, int chunkZ)
    {
        return this.generatorSimplex.getValue(
                chunkX * this.getPerlinScale(),
                chunkZ * this.getPerlinScale());
    }

    public double getPerlinScale()
    {
        return 0.04;
    }

    public void updatePlacePercentage(boolean disSucceed)
    {
        this.totalChunksChecked++;
        Double totalChunks = ((double) this.totalChunksPlaced / (this.totalChunksChecked / 100));
        if (disSucceed)
            this.totalChunksPlaced++;

        if (this.totalChunksChecked % 1000 == 0)
        {
            GTLiteLog.logger.info("Feature {} has been placed successfully"
                            + " in chunks {} percent of the time out of {} chunks checked",
                    this, totalChunks, this.totalChunksChecked);
        }
    }

    public AbstractWorldGenerator getWorldGenInstance()
    {
        return this.WORLD_GEN_INSTANCE;
    }

    public AbstractFeature addCondition(FeatureCondition condition)
    {
        this.featureConditions.add(condition);
        return this;
    }

}
