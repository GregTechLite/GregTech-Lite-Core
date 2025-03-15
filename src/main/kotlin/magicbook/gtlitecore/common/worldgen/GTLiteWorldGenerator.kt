package magicbook.gtlitecore.common.worldgen

import magicbook.gtlitecore.api.worldgen.feature.AbstractFeature
import net.minecraft.world.World
import net.minecraft.world.chunk.IChunkProvider
import net.minecraft.world.gen.ChunkGeneratorFlat
import net.minecraft.world.gen.IChunkGenerator
import net.minecraftforge.fml.common.IWorldGenerator
import java.util.*

class GTLiteWorldGenerator : IWorldGenerator
{

    companion object
    {
        @JvmField
        var INSTANCE = GTLiteWorldGenerator()
    }

    override fun generate(random: Random, chunkX: Int, chunkZ: Int, world: World,
                          chunkGenerator: IChunkGenerator, chunkProvider: IChunkProvider)
    {
        if (chunkGenerator !is ChunkGeneratorFlat)
        {
            for (feature: AbstractFeature in AbstractFeature.features)
            {
                feature.setWorld(world)
                feature.worldGenInstance.generateInChunk(world, random, chunkX, chunkZ)
            }
        }
    }

}