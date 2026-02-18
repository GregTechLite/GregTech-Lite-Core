package gregtechlite.gtlitecore.client.event

import com.mojang.authlib.minecraft.MinecraftProfileTexture
import com.morphismmc.morphismlib.client.Games
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.collection.openHashMapOf
import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.RenderPlayerEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.UUID

object CapesHandler
{

    private val devCapes: MutableMap<UUID, ResourceLocation> = openHashMapOf()

    internal fun register()
    {
        addDevCape("aaed705b-8e08-47fa-b8e0-d0024e3c75bc", capeOf("dev")) // Magic_Sweepy
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    internal fun renderPlayerCape(event: RenderPlayerEvent.Pre)
    {
        val clientPlayer = event.entityPlayer as AbstractClientPlayer
        val uuid = clientPlayer.uniqueID

        var playerInfo = clientPlayer.playerInfo
        if (playerInfo == null)
        {
            playerInfo = Games.mc().connection?.getPlayerInfo(uuid)
        }

        val playerTextures = playerInfo.playerTextures
        if (devCapes.contains(uuid))
        {
            playerTextures[MinecraftProfileTexture.Type.CAPE] = devCapes.getValue(uuid)
        }
    }

    internal fun addDevCape(uuid: UUID, texture: ResourceLocation)
    {
        devCapes.put(uuid, texture)
    }

    internal fun addDevCape(uuid: String, texture: ResourceLocation)
    {
        addDevCape(UUID.fromString(uuid), texture)
    }

    private fun capeOf(texturePath: String) = GTLiteMod.id("textures/capes/$texturePath.png")

}