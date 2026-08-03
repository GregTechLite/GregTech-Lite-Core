package gregtechlite.gtlitecore.client.renderer

import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

interface UniversiumRenderBehavior : ItemRendererManager
{
    @SideOnly(Side.CLIENT)
    fun getCosmicOpacity(): Float

    @SideOnly(Side.CLIENT)
    fun getCosmicOpacity(stack: ItemStack, vararg args: Any): Float = getCosmicOpacity()
}