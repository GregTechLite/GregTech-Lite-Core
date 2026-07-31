package gregtechlite.gtlitecore.client.renderer

import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

interface CosmicRenderBehavior : ItemRendererManager
{
    /**
     * The mask texture where the cosmic overlay will be.
     *
     * @param stack  The stack being rendered with this behavior.
     * @param player The entity holding the item, if null assume either inventory or ground.
     * @return       The masked area where the cosmic overlay will be.
     */
    @SideOnly(Side.CLIENT)
    fun getMaskTexture(stack: ItemStack, player: EntityLivingBase?): TextureAtlasSprite?

    /**
     * The opacity that the mask overlay will be rendered with.
     *
     * @param stack  The stack being rendered with this behavior.
     * @param player The entity holding the item, if null assume either inventory or ground.
     * @return       The opacity that the mask overlay will be rendered with.
     */
    @SideOnly(Side.CLIENT)
    fun getMaskOpacity(stack: ItemStack, player: EntityLivingBase?): Float
}