package gregtechlite.gtlitecore.client.event

import com.morphismmc.morphismlib.util.ItemUtil
import gregtech.api.GTValues.VOCNF
import gregtech.api.unification.OreDictUnifier
import gregtechlite.gtlitecore.client.renderer.handler.StructureSelectRenderer
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.client.shader.CosmicShaderHelper
import gregtechlite.gtlitecore.common.block.BlockMetalWall
import gregtechlite.gtlitecore.common.block.BlockSheetedFrame
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemBlock
import net.minecraft.util.text.TextFormatting
import net.minecraftforge.client.event.GuiScreenEvent
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
            .forEach { glass ->
                tooltip.add(I18n.format("gtlitecore.tooltip.glass_tier", VOCNF[glass.tier]))
            }

        GlassCasing.Enum02.entries
            .filter { ItemUtil.areItemTypeEqual(it.stack, stack) }
            .forEach { glass ->
                tooltip.add(I18n.format("gtlitecore.tooltip.glass_tier", VOCNF[glass.tier]))
            }

        GlassCasing.Enum03.entries
            .filter { ItemUtil.areItemTypeEqual(it.stack, stack) }
            .forEach { glass ->
                tooltip.add(I18n.format("gtlitecore.tooltip.glass_tier", VOCNF[glass.tier]))
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

}