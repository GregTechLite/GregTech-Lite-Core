package gregtechlite.gtlitecore.mixins.hooks

import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase
import gregtechlite.gtlitecore.api.pattern.BlockPatternExtension
import gregtechlite.gtlitecore.api.pattern.MultiblockAutoBuilder
import gregtechlite.gtlitecore.api.pattern.MultiblockBuildSetting
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World

@Hook
object MultiblockBuilderHooks
{
    @JvmStatic
    fun onItemUseFirst(player: EntityPlayer, world: World, pos: BlockPos, side: EnumFacing, hand: EnumHand): EnumActionResult?
    {
        if (!player.isSneaking)
            return null

        val tileEntity = world.getTileEntity(pos) as? IGregTechTileEntity ?: return null
        val controller = tileEntity.metaTileEntity as? MultiblockControllerBase ?: return null
        val stack = player.getHeldItem(hand)

        if (!player.canPlayerEdit(pos, side, stack))
            return EnumActionResult.FAIL
        if (world.isRemote)
            return EnumActionResult.SUCCESS

        val setting = MultiblockBuildSetting.read(stack)
        val formed = controller.isStructureFormed

        if (setting.isMirrorMode)
        {
            if (formed)
            {
                player.sendMessage(TextComponentTranslation("item.gtlitecore.multiblock_builder.mirror_formed"))
                return EnumActionResult.SUCCESS
            }
            if (!controller.allowsFlip())
            {
                player.sendMessage(TextComponentTranslation("item.gtlitecore.multiblock_builder.mirror_unsupported"))
                setting.mirrorMode = 0
            }
        }

        if (formed)
            return null

        val pattern = controller.structurePattern ?: return EnumActionResult.SUCCESS
        if (pattern !is BlockPatternExtension)
            return null

        val result = try
        {
            MultiblockAutoBuilder.build(player, controller, pattern, setting, setting.isMirrorMode)
        }
        catch (_: Throwable)
        {
            return EnumActionResult.SUCCESS
        }

        if (result.placed == 0 && result.firstMissing != null)
        {
            player.sendMessage(TextComponentTranslation("item.gtlitecore.multiblock_builder.missing_block",
                                                        result.firstMissing.displayName))
        }
        return EnumActionResult.SUCCESS
    }
}
