package gregtechlite.gtlitecore.client.model

import com.google.common.collect.ImmutableMap
import gregtech.api.block.VariantBlock
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.block.ReTexturableCasingRegistry
import gregtechlite.gtlitecore.api.block.ReTexturableCasing
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import gregtechlite.gtlitecore.client.model.retexture.ReTexturedModelProvider
import gregtechlite.gtlitecore.core.GTLiteConfigHolder
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.TextureStitchEvent
import net.minecraftforge.client.model.IModel
import net.minecraftforge.common.property.IExtendedBlockState
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@EventBusSubscriber(modid = MOD_ID, value = [Side.CLIENT])
@SideOnly(Side.CLIENT)
object ReTexturableCasingRegistration
{
    private val blocks = linkedSetOf<VariantBlock<*>>()
    private val providers = openHashMapOf<VariantBlock<*>, ReTexturedModelProvider>()

    fun register(block: Block, state: IBlockState, location: ModelResourceLocation)
    {
        if (block !is ReTexturableCasing || block !is VariantBlock<*>) return
        blocks.add(block)
        providers.getOrPut(block) { ComponentCasingModel(block) }.register(state, location)
    }

    @SubscribeEvent
    fun onTextureStitch(event: TextureStitchEvent.Pre)
    {
        blocks.forEach { block ->
            block.blockState.validStates.forEach { state ->
                event.map.registerSprite(tierTexture(block.getState(state).name))
            }
        }
    }

    private fun tierTexture(tier: String): ResourceLocation = GTLiteMod.id("blocks/casings/tiered/$tier")

    private class ComponentCasingModel(private val casing: ReTexturableCasing) : ReTexturedModelProvider
    {
        private val glassModel = ResourceLocation(casing.casingModel.namespace, casing.casingModel.path + "_glass")

        override fun getModels(state: IBlockState?): Array<ResourceLocation>
            = arrayOf(casing.casingModel, glassModel)

        override fun loadModel(variant: ResourceLocation, modelResource: ResourceLocation, model: IModel): IModel
        {
            if (modelResource != casing.casingModel)
                return model
            val tier = getTierName(variant) ?: return model
            return model.retexture(ImmutableMap.of("1", tierTexture(tier).toString()))
        }

        override fun reTextured(state: IBlockState?, side: EnumFacing?,
                                model: ResourceLocation): Map<String, String>?
        {
            if (model != casing.casingModel)
                return null
            if (!GTLiteConfigHolder.client.useMultiblockCasingTextures)
                return null
            val texture = (state as? IExtendedBlockState)
                ?.getValue(ReTexturableCasingRegistry.BASE_TEXTURE) ?: return null
            return mapOf("0" to texture)
        }

        override fun shouldRenderInLayer(state: IBlockState?, side: EnumFacing?,
                                         model: ResourceLocation, layer: BlockRenderLayer): Boolean
            = if (model == glassModel) layer == BlockRenderLayer.TRANSLUCENT
              else layer == BlockRenderLayer.CUTOUT

        private fun getTierName(variant: ResourceLocation): String?
            = (variant as? ModelResourceLocation)?.variant?.split(',')
                ?.firstOrNull { it.startsWith("variant=") }?.substringAfter('=')
    }
}
