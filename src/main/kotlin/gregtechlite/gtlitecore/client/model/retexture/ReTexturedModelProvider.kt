package gregtechlite.gtlitecore.client.model.retexture

import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.IModel
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
interface ReTexturedModelProvider
{
    fun register(state: IBlockState?, path: ModelResourceLocation)
        = ReTexturedModelLoader.register(this, state, path)

    fun getModels(state: IBlockState?): Array<ResourceLocation>

    /**
     * Returns the texture replacements of the model [model], where the key is the
     * texture variable declared in the model json (e.g. `0`, `1` or `#all`) and
     * the value is the path of the new texture.
     *
     * @return Returns `null` if no texture should be replaced.
     */
    fun reTextured(state: IBlockState?, side: EnumFacing?, model: ResourceLocation): Map<String, String>? = null

    /**
     * Adjusts the baked quads of the model [model] before they enter the rendering
     * pipeline, e.g. tinting or dynamic UV transformations.
     */
    fun reBakedQuad(state: IBlockState?, side: EnumFacing?, model: ResourceLocation,
                    base: List<BakedQuad>): List<BakedQuad> = base

    /**
     * Whether the model [model] should be rendered in the render [layer], allowing
     * multi-layer rendering.
     */
    fun shouldRenderInLayer(state: IBlockState?, side: EnumFacing?, model: ResourceLocation,
                            layer: BlockRenderLayer): Boolean
        = state != null && state.block.canRenderInLayer(state, layer)

    /**
     * Called when the model resource [modelResource] is loaded for the first time,
     * the [variant] is the model resource location of the registered state,
     * e.g. `gtlitecore:motor_casing#variant=lv`.
     */
    fun loadModel(variant: ResourceLocation, modelResource: ResourceLocation, model: IModel): IModel = model
}
