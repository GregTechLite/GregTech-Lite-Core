package gregtechlite.gtlitecore.common.item.behavior

import gregtech.api.capability.GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM
import gregtech.api.items.metaitem.stats.IItemBehaviour
import gregtech.api.pipenet.tile.IPipeTile
import gregtech.api.util.GTUtility
import gregtech.client.utils.TooltipHelper
import gregtechlite.gtlitecore.api.cosmetic.GTLiteContributor
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.core.GTLiteConfigHolder
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.init.Enchantments
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
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
        private const val NBT_MINING_TIER = "mining_tier"

        fun setSilkMode(item: ItemStack, silkMode: Boolean)
        {
            var tagCompound = item.tagCompound
            if (tagCompound == null)
            {
                tagCompound = NBTTagCompound()
                item.tagCompound = tagCompound
            }
            tagCompound.setBoolean(NBT_SILK_MODE, silkMode)
        }

        fun isSilkMode(item: ItemStack): Boolean
        {
            val tagCompound = item.tagCompound
            return tagCompound != null && tagCompound.getBoolean(NBT_SILK_MODE)
        }

        fun getMiningTier(item: ItemStack): Int = item.tagCompound?.getInteger(NBT_MINING_TIER) ?: 0

        fun setMiningTier(item: ItemStack, index: Int)
        {
            var tagCompound = item.tagCompound
            if (tagCompound == null)
            {
                tagCompound = NBTTagCompound()
                item.tagCompound = tagCompound
            }
            tagCompound.setInteger(NBT_MINING_TIER, index)
        }

        fun getMiningTicks(item: ItemStack): Int
        {
            val tiers = GTLiteConfigHolder.tool.laserDestroyer.miningTiers
            if (tiers.isEmpty()) return 0
            return tiers[getMiningTier(item).coerceIn(0, tiers.size - 1)]
        }

        fun cycleMiningTier(item: ItemStack): Int
        {
            val tiers = GTLiteConfigHolder.tool.laserDestroyer.miningTiers
            if (tiers.isEmpty())
            {
                setMiningTier(item, 0)
                return 0
            }
            val next = (getMiningTier(item) + 1) % tiers.size
            setMiningTier(item, next)
            return next
        }

        private fun speedTierNameKey(index: Int): String =
            if (index in 0..3) "metaitem.tool.laser_destroyer.speed.name.$index"
            else "metaitem.tool.laser_destroyer.speed.name.generic"

        @Suppress("Deprecation")
        fun breakBlock(item: ItemStack, player: EntityPlayer, world: World, pos: BlockPos,
                       isSilkMode: Boolean, energyCost: Long): Boolean
        {
            if (world.isRemote) return true

            val state = world.getBlockState(pos)
            val block = state.block
            val mte = GTUtility.getMetaTileEntity(world, pos)

            if (block === Blocks.AIR) return false

            val silkLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.heldItemMainhand)
            val drops: List<ItemStack> = if (silkLevel != 0)
            {
                mte?.let { listOf(it.stack()) } ?: listOf(getSilkDrops(world, pos, state))
            }
            else
            {
                mte?.let { listOf(it.stack()) } ?: getNormalDrops(world, pos, state)
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

        @Suppress("Deprecation")
        private fun getNormalDrops(world: World, pos: BlockPos, state: IBlockState): List<ItemStack>
        {
            if (world.getTileEntity(pos) is IPipeTile<*, *>)
            {
                val item = state.block.getItem(world, pos, state)
                return if (item.isEmpty) emptyList() else listOf(item)
            }
            return state.block.getDrops(world, pos, state, 0)
        }

        @Suppress("Deprecation")
        private fun getSilkDrops(world: World, pos: BlockPos, state: IBlockState): ItemStack
        {
            if (world.getTileEntity(pos) is IPipeTile<*, *>)
            {
                val item = state.block.getItem(world, pos, state)
                if (!item.isEmpty) return item
            }

            return runCatching {
                var block: Class<*>? = state.block.javaClass
                while (block != null)
                {
                    try
                    {
                        val silkTouchDrop = block.getDeclaredMethod("getSilkTouchDrop", IBlockState::class.java)
                        silkTouchDrop.isAccessible = true
                        return@runCatching silkTouchDrop.invoke(state.block, state) as ItemStack
                    }
                    catch (_: NoSuchMethodException)
                    {
                        block = block.superclass
                    }
                }
                throw NoSuchMethodException()
            }.getOrDefault(ItemStack(state.block, 1, state.block.getMetaFromState(state)))
        }

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
        if (player.isSneaking)
        {
            if (isSilkMode(item))
            {
                setSilkMode(item, false)
                item.tagCompound?.removeTag("ench")
                if (!world.isRemote)
                {
                    player.sendMessage(TextComponentTranslation("metaitem.tool.laser_destroyer.silk_mode.disabled"))
                }
            }
            else
            {
                setSilkMode(item, true)
                item.addEnchantment(Enchantments.SILK_TOUCH, 1)
                if (!world.isRemote)
                {
                    player.sendMessage(TextComponentTranslation("metaitem.tool.laser_destroyer.silk_mode.enabled"))
                }
            }
        }
        else if (!world.isRemote)
        {
            val tierIndex = cycleMiningTier(item)
            val seconds = "%.2f".format(getMiningTicks(item) / 20.0)
            player.sendMessage(TextComponentTranslation("metaitem.tool.laser_destroyer.speed.changed",
                TextComponentTranslation(speedTierNameKey(tierIndex), tierIndex + 1), seconds))
        }
        return ActionResult.newResult(EnumActionResult.PASS, item)
    }

    override fun addInformation(itemStack: ItemStack, lines: MutableList<String>)
    {
        super.addInformation(itemStack, lines)
        if (TooltipHelper.isShiftDown())
            lines.add(I18n.format("gtlitecore.tooltip.contributor_item.owner", GTLiteContributor.YIYU_QAQ.userName))
        else
            lines.add(I18n.format("gtlitecore.tooltip.contributor_item"))
        lines.add(I18n.format("metaitem.tool.laser_destroyer.mode"))
        val ticks = getMiningTicks(itemStack)
        val seconds = if (ticks <= 0) "0.00" else "%.2f".format(ticks / 20.0)
        val tier = getMiningTier(itemStack)
        lines.add(I18n.format("metaitem.tool.laser_destroyer.speed",
            I18n.format(speedTierNameKey(tier), tier + 1), seconds))
        lines.add(I18n.format("metaitem.tool.laser_destroyer.energy_cost", 4))
    }
}