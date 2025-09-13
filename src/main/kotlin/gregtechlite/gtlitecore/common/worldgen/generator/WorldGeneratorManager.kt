package gregtechlite.gtlitecore.common.worldgen.generator

import gregtechlite.gtlitecore.api.worldgen.generator.AbstractWorldGenerator
import net.minecraft.world.World
import net.minecraft.world.chunk.IChunkProvider
import net.minecraft.world.gen.ChunkGeneratorFlat
import net.minecraft.world.gen.IChunkGenerator
import net.minecraftforge.fml.common.IWorldGenerator
import net.minecraftforge.fml.common.registry.GameRegistry
import java.util.Random

class WorldGeneratorManager : IWorldGenerator
{

    companion object
    {

        internal fun init()
        {
            GameRegistry.registerWorldGenerator(WorldGeneratorManager(), 1)
        }

    }

    override fun generate(random: Random,
                          chunkX: Int,
                          chunkZ: Int,
                          world: World?,
                          chunkGenerator: IChunkGenerator?,
                          chunkProvider: IChunkProvider?)
    {
        if (chunkGenerator !is ChunkGeneratorFlat)
        {
            WorldGeneratorRegistry.generators.forEach { generator ->
                if (generator is AbstractWorldGenerator)
                {
                    world?.let {
                        generator.setWorld(it)
                        generator.innerGenerator?.generateInChunk(world, random, chunkX, chunkZ)
                    }
                }
            }
        }
    }

}