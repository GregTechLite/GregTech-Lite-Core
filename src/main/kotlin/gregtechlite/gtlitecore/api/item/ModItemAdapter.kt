package gregtechlite.gtlitecore.api.item

import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.registry.GameRegistry

object ModItemAdapter
{
    fun get(modid: String, name: String, meta: Int = 0, amount: Int = 1, nbt: String? = null): ItemStack
    {
        if (!Loader.isModLoaded(modid))
            throw IllegalStateException("Cannot get item '$modid:$name', because the mod '$modid' is not active")
        return GameRegistry.makeItemStack("$modid:$name", meta, amount, nbt)
    }

    fun getOrDefault(modid: String, name: String, meta: Int = 0, amount: Int = 1, nbt: String? = null,
                     fallbackStack: ItemStack): ItemStack
        = if (Loader.isModLoaded(modid)) GameRegistry.makeItemStack("$modid:$name", meta, amount, nbt) else fallbackStack
}