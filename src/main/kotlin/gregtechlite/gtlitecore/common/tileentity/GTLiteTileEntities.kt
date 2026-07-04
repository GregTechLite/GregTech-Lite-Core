package gregtechlite.gtlitecore.common.tileentity

import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.GTLiteTags.MOD_ID
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.registry.GameRegistry

object GTLiteTileEntities
{
    fun init()
    {
        GameRegistry.registerTileEntity(TileEntityBlockcrate::class.java, GTLiteMod.id("bottlecrate"))
    }
}