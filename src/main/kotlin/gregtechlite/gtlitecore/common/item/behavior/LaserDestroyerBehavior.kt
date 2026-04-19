package gregtechlite.gtlitecore.common.item.behavior

import gregtech.api.capability.GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM
import gregtech.api.items.metaitem.stats.IItemBehaviour
import gregtech.api.util.GTUtility
import net.minecraft.block.state.IBlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.init.Enchantments
import net.minecraft.item.ItemStack
import net.minecraft.network.play.server.SPacketBlockChange
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World

class LaserDestroyerBehavior : IItemBehaviour
{
    companion object
    {
        private const val NBT_SILK_MODE = "silk_mode"

        fun toggleSilkMode(item: ItemStack): Boolean
        {
            val nbt = item.getOrCreateSubCompound(NBT_SILK_MODE)
            val newMode = !nbt.getBoolean(NBT_SILK_MODE)
            nbt.setBoolean(NBT_SILK_MODE, newMode)
            return newMode
        }

        fun isSilkMode(item: ItemStack): Boolean
                = item.tagCompound?.getBoolean(NBT_SILK_MODE) ?: false

        @Suppress("Deprecation")
        fun breakBlock(item: ItemStack, player: EntityPlayer, world: World, pos: BlockPos,
                       isSilkMode: Boolean, energyCost: Long): Boolean
        {
            if (world.isRemote) return true

            val state = world.getBlockState(pos)
            val block = state.block
            val mte = GTUtility.getMetaTileEntity(world, pos)

            if (block === Blocks.AIR) return false

            // When the tool in silk mode, it will have Silk Touch enchantment.
            val silkLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.heldItemMainhand)

            var drops: List<ItemStack>
            if (silkLevel != 0)
            {
                drops = if (mte != null) listOf(mte.stackForm) else listOf(getSilkDrops(state))
            }
            else
            {
                drops = if (mte != null) listOf(mte.stackForm) else block.getDrops(world, pos, state, 0)
            }

            val soundType = block.getSoundType(state, world, pos, player)
            world.playSound(player, pos, soundType.breakSound, SoundCategory.BLOCKS, 1.0f, 1.0f)

            if (player is EntityPlayerMP)
            {
                player.connection.sendPacket(SPacketBlockChange(world, pos))
            }

            val removed = block.removedByPlayer(state, world, pos, player, !isSilkMode)

            if (removed)
            {
                block.onPlayerDestroy(world, pos, state)
            }
            else
            {
                block.onPlayerDestroy(world, pos, state)
                world.setBlockState(pos, Blocks.AIR.defaultState, 3)
            }

            for (drop in drops)
            {
                if (player.isCreative) continue
                val f = 0.7f
                val dx = world.rand.nextFloat() * f + (1.0f - f) * 0.5
                val dy = world.rand.nextFloat() * f + (1.0f - f) * 0.5
                val dz = world.rand.nextFloat() * f + (1.0f - f) * 0.5
                val entityItem = EntityItem(world, pos.x.toDouble() + dx, pos.y.toDouble() + dy, pos.z.toDouble() + dz, drop)
                entityItem.setDefaultPickupDelay()
                world.spawnEntity(entityItem)
            }

            if (player.isCreative || drainEnergy(item, energyCost, true))
            {
                if (!player.isCreative)
                {
                    drainEnergy(item, energyCost, false)
                }
            }

            return true
        }

        private fun getSilkDrops(state: IBlockState): ItemStack = runCatching {
            val method = state.block.javaClass.getMethod("getSilkTouchDrop", IBlockState::class.java)
            method.isAccessible = true
            method.invoke(state.block, state) as ItemStack
        }.getOrDefault(ItemStack(state.block, 1, state.block.getMetaFromState(state)))

        private fun drainEnergy(item: ItemStack, amount: Long, simulate: Boolean): Boolean
        {
            val electricItem = item.getCapability(CAPABILITY_ELECTRIC_ITEM, null)
            if (electricItem == null)
                return false
            return electricItem.discharge(amount, Int.MAX_VALUE, true, false, simulate) >= amount
        }
    }

    override fun onItemRightClick(world: World, player: EntityPlayer, hand: EnumHand): ActionResult<ItemStack>
    {
        val item = player.getHeldItem(hand)
        if (!world.isRemote && player.isSneaking)
        {
            toggleSilkMode(item)
            if (!isSilkMode(item))
            {
                item.addEnchantment(Enchantments.SILK_TOUCH, 1)
                player.sendMessage(TextComponentTranslation("metaitem.tool.laser_destroyer.silk_mode.enabled"))
            }
            else
            {
                // Try to remove tag from addEnchantment.
                val enchantments = item.tagCompound?.getTagList("ench", 10)
                repeat (enchantments?.tagList!!.size) {
                    item.tagCompound?.getTagList("ench", 10)?.removeTag(it)
                }

                player.sendMessage(TextComponentTranslation("metaitem.tool.laser_destroyer.silk_mode.disabled"))
            }
            return ActionResult.newResult(EnumActionResult.SUCCESS, item)
        }
        return ActionResult.newResult(EnumActionResult.PASS, item)
    }

}