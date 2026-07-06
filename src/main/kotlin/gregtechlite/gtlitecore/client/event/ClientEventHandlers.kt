package gregtechlite.gtlitecore.client.event

import com.mojang.authlib.minecraft.MinecraftProfileTexture
import com.morphismmc.morphismlib.client.Games
import com.morphismmc.morphismlib.util.ItemUtil
import gregtech.api.GTValues.VOCNF
import gregtech.api.unification.OreDictUnifier
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.TooltipHelper
import gregtechlite.gtlitecore.api.cosmetic.GTLiteContributor
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.client.renderer.handler.world.StructureSelectRenderer
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.client.shader.CosmicShaderHelper
import gregtechlite.gtlitecore.common.block.BlockMetalWall
import gregtechlite.gtlitecore.common.block.BlockSheetedFrame
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemBlock
import net.minecraft.util.text.TextFormatting
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.client.event.RenderPlayerEvent
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.lwjgl.BufferUtils
import java.nio.FloatBuffer

@Suppress("unused")
@SideOnly(Side.CLIENT)
object ClientEventHandlers
{

    @JvmField
    var cosmicUVs: FloatBuffer = BufferUtils.createFloatBuffer(4 * 10)

    @SubscribeEvent
    fun addFormulas(event: ItemTooltipEvent)
    {
        val stack = event.itemStack
        val tooltip = event.toolTip

        val item = stack.item
        if (item is ItemBlock)
        {
            // If itemBlock is for sheetedFrame or wallGt, then added formulas for its itemBlocks.
            if (item.block is BlockSheetedFrame || item.block is BlockMetalWall)
            {
                val material = OreDictUnifier.getUnificationEntry(stack)?.material
                if (material?.chemicalFormula != null && material.chemicalFormula!!.isNotEmpty())
                    tooltip.add(TextFormatting.YELLOW.toString() + material.chemicalFormula)
            }
        }

        // Added glass tier tooltips for all mod glasses.
        GlassCasing.Enum01.entries
            .filter { ItemUtil.areItemTypeEqual(it.stack, stack) }
            .forEach { tooltip.add(I18n.format("gtlitecore.tooltip.glass_tier", VOCNF[it.tier])) }

        GlassCasing.Enum02.entries
            .filter { ItemUtil.areItemTypeEqual(it.stack, stack) }
            .forEach { tooltip.add(I18n.format("gtlitecore.tooltip.glass_tier", VOCNF[it.tier])) }

        GlassCasing.Enum03.entries
            .filter { ItemUtil.areItemTypeEqual(it.stack, stack) }
            .forEach { tooltip.add(I18n.format("gtlitecore.tooltip.glass_tier", VOCNF[it.tier])) }

        // Added tooltips for bottlecrate.
        if (ItemUtil.areItemTypeEqual(GTLiteBlocks.BOTTLECRATE.stack(), stack))
        {
            if (TooltipHelper.isShiftDown())
            {
                tooltip.add(I18n.format("gtlitecore.tooltip.contributor_item.owner", GTLiteContributor.LLAMA_WEI.userName))
            }
            else
            {
                tooltip.add(I18n.format("gtlitecore.tooltip.contributor_item"))
            }
            tooltip.add(I18n.format("tile.gtlitecore.bottlecrate.tooltip.1"))
        }
    }

    @SubscribeEvent
    fun onRenderTick(event: TickEvent.RenderTickEvent)
    {
        if (event.phase == TickEvent.Phase.START)
        {
            cosmicUVs = BufferUtils.createFloatBuffer(4 * GTLiteTextures.COSMIC.size)

            var sprite: TextureAtlasSprite
            for (cosmicSprite in GTLiteTextures.COSMIC)
            {
                sprite = cosmicSprite
                cosmicUVs.put(sprite.minU)
                cosmicUVs.put(sprite.minV)
                cosmicUVs.put(sprite.maxU)
                cosmicUVs.put(sprite.maxV)
            }
            cosmicUVs.flip()
        }
    }

    @SubscribeEvent
    fun onRenderWorldLast(event: RenderWorldLastEvent)
    {
        StructureSelectRenderer.render(event)
    }

    @SubscribeEvent
    fun onPreDrawScreen(event: GuiScreenEvent.DrawScreenEvent.Pre)
    {
        CosmicShaderHelper.inventoryRender = true
    }

    @SubscribeEvent
    fun onPostDrawScreen(event: GuiScreenEvent.DrawScreenEvent.Post)
    {
        CosmicShaderHelper.inventoryRender = false
    }

    @SubscribeEvent
    fun renderPlayerCape(event: RenderPlayerEvent.Pre)
    {
        val player = event.entityPlayer as AbstractClientPlayer
        val uuid = player.uniqueID

        var playerInfo = player.playerInfo
        if (playerInfo == null)
        {
            playerInfo = Games.mc().connection?.getPlayerInfo(uuid)
        }

        val playerTextures = playerInfo.playerTextures
        if (GTLiteContributor.contributors.contains(uuid))
        {
            val key = GTLiteContributor.contributors.getValue(uuid)
            if (key != "developer") return
            playerTextures[MinecraftProfileTexture.Type.CAPE] = Textures.GREGTECH_CAPE_TEXTURE
        }
    }

}