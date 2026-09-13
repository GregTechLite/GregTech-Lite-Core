package gregtechlite.gtlitecore.client.renderer.handler.world

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.ColourMultiplier
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Cuboid6
import codechicken.lib.vec.Matrix4
import com.morphismmc.morphismlib.client.Games
import gregtech.api.util.GTUtility.convertRGBtoOpaqueRGBA_CL
import gregtech.client.renderer.texture.Textures
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.biome.BiomeColorHelper
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
object OreWasherWaterRenderer
{
    private const val SURFACE_HEIGHT = 8.0 / 9.0

    @JvmStatic
    fun render(renderState: CCRenderState, translation: Matrix4, world: World, origin: BlockPos,
               offsets: List<BlockPos>)
    {
        val sprite = Games.mc().textureMapBlocks.getAtlasSprite(FluidRegistry.WATER.still.toString())
        val pipeline = arrayOf<IVertexOperation?>(ColourMultiplier(
            convertRGBtoOpaqueRGBA_CL(BiomeColorHelper.getWaterColorAtPos(world, origin))))
        val cells = offsets.toHashSet()

        for (offset in offsets)
        {
            renderState.setBrightness(world, origin.add(offset))
            val cellTranslation = translation.copy()
                .translate(offset.x.toDouble(), offset.y.toDouble(), offset.z.toDouble())
            val maxY = if (offset.up() in cells) 1.0 else SURFACE_HEIGHT
            val box = Cuboid6(0.0, 0.0, 0.0, 1.0, maxY, 1.0)

            for (face in EnumFacing.VALUES)
            {
                val neighbor = offset.offset(face)
                if (neighbor in cells) continue
                if (world.getBlockState(origin.add(neighbor)).isOpaqueCube()) continue
                Textures.renderFace(renderState, cellTranslation, pipeline, face, box, sprite,
                                    BlockRenderLayer.CUTOUT_MIPPED)
            }
        }
    }
}
