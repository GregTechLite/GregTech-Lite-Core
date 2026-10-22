package gregtechlite.gtlitecore.api.pattern

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.BlockWorldState
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.util.BlockInfo
import gregtech.api.util.RelativeDirection
import gregtechlite.gtlitecore.api.block.attribute.BlockAttributeRegistry
import gregtechlite.gtlitecore.api.collection.obj2IntHashMapOf
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.integration.appeng.handler.AEItemSource
import it.unimi.dsi.fastutil.objects.Object2IntMap
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object MultiblockAutoBuilder
{
    private val FACINGS = arrayOf(EnumFacing.SOUTH, EnumFacing.NORTH, EnumFacing.WEST,
                                  EnumFacing.EAST, EnumFacing.UP, EnumFacing.DOWN)

    data class BuildResult(val placed: Int, val firstMissing: ItemStack?)

    @JvmStatic
    fun build(player: EntityPlayer, controller: MultiblockControllerBase, pattern: BlockPattern,
              setting: MultiblockBuildSetting, mirrored: Boolean): BuildResult
    {
        val blockPatternExt = pattern as? BlockPatternExtension ?: return BuildResult(0, null)
        val blockMatches = blockPatternExt.blockMatches
        val centerOffset = blockPatternExt.centerOffset
        val aisleRepetitions = pattern.aisleRepetitions

        val world = player.world
        val centerPos = controller.pos
        val facing = controller.frontFacing.opposite
        val upwardsFacing = controller.upwardsFacing
        val isFlipped = mirrored || controller.isFlipped

        val fingerLength = blockMatches.size
        val thumbLength = if (fingerLength > 0) blockMatches[0].size else 0
        val palmLength = if (thumbLength > 0) blockMatches[0][0].size else 0

        if (fingerLength == 0 || thumbLength == 0 || palmLength == 0)
            return BuildResult(0, null)

        val repetitions = IntArray(fingerLength) { idx ->
            val min = aisleRepetitions[idx][0]
            val max = aisleRepetitions[idx][1]
            if (min == max)
                min
            else
                setting.repeatCount.coerceIn(min, max)
        }

        val optionalGroups = collectOptionalGroups(blockMatches)
        val resolver = CandidateResolver(player, setting, optionalGroups)
        val tierStates = if (setting.isReplaceMode) BlockAttributeRegistry.all().flatMapTo(HashSet()) { it.ascendingBlocks } else emptySet()

        val worldState = BlockWorldState()
        val matchContext = PatternMatchContext()

        val globalCount = obj2IntHashMapOf<TraceabilityPredicate.SimplePredicate>()
        val positions = hashSetOf<BlockPos>()
        positions += centerPos

        var z = 0
        for (i in 0 until centerOffset[2].coerceIn(0, fingerLength))
        {
            z -= repetitions[i]
        }

        var placed = 0
        var firstMissing: ItemStack? = null

        for (c in 0 until fingerLength)
        {
            for (slice in 0 until repetitions[c])
            {
                val layerCount = obj2IntHashMapOf<TraceabilityPredicate.SimplePredicate>()
                for (b in 0 until thumbLength)
                {
                    val y = -centerOffset[1] + b
                    for (a in 0 until palmLength)
                    {
                        val x = -centerOffset[0] + a
                        val predicate = blockMatches[c][b][a]
                        val pos = RelativeDirection.setActualRelativeOffset(x, y, z, facing, upwardsFacing,
                            isFlipped, pattern.structureDir).add(centerPos.x, centerPos.y, centerPos.z)
                        worldState.update(world, pos, matchContext, globalCount, layerCount, predicate)

                        val existing = world.getBlockState(pos)
                        var oldBlock: ItemStack? = null
                        if (!existing.material.isReplaceable)
                        {
                            if (!setting.isReplaceMode || existing !in tierStates)
                            {
                                positions += pos
                                for (limit in predicate.limited)
                                    limit.testLimited(worldState)
                                continue
                            }
                            val oldStack = stateStack(existing)
                            if (oldStack.isEmpty)
                            {
                                positions += pos
                                for (limit in predicate.limited)
                                    limit.testLimited(worldState)
                                continue
                            }
                            oldBlock = oldStack
                        }

                        val candidates = resolver.collect(predicate, globalCount, layerCount)
                        val stacks = candidates.map(::getStackFromBlockInfo).filter { !it.isEmpty && it.item is ItemBlock }
                        if (stacks.isEmpty())
                        {
                            if (oldBlock != null)
                                positions += pos
                            continue
                        }

                        if (oldBlock != null && stacks.any { isSameStack(it, oldBlock) })
                        {
                            positions += pos
                            continue
                        }

                        val found = findMaterial(player, stacks, setting)
                        if (found == null)
                        {
                            if (firstMissing == null)
                                firstMissing = stacks.first()
                            if (oldBlock != null)
                                positions += pos
                            continue
                        }

                        if (oldBlock != null)
                        {
                            if (!player.inventory.addItemStackToInventory(oldBlock))
                            {
                                if (!player.isCreative)
                                    player.inventory.addItemStackToInventory(found)
                                if (firstMissing == null)
                                    firstMissing = stacks.first()
                                positions += pos
                                continue
                            }
                            world.setBlockToAir(pos)
                        }

                        if (placeAt(player, world, pos, found))
                        {
                            positions += pos
                            placed++
                        }
                        else
                        {
                            player.inventory.addItemStackToInventory(found)
                            if (oldBlock != null)
                            {
                                player.inventory.addItemStackToInventory(oldBlock)
                                positions += pos
                            }
                        }
                    }
                }
                z++
            }
        }

        fixFrontFacings(world, controller, positions)
        return BuildResult(placed, firstMissing)
    }

    private fun collectOptionalGroups(blockMatches: Array<Array<Array<TraceabilityPredicate>>>): Object2IntMap<String>
    {
        val names = sortedSetOf<String>()
        for (layers in blockMatches)
        {
            for (row in layers)
            {
                for (predicate in row)
                {
                    (predicate.common + predicate.limited).forEach {
                        val group = (it as? GTLitePredicate)?.optionalGroup
                        group?.let { optionalGroup -> names += optionalGroup }
                    }
                }
            }
        }
        return obj2IntHashMapOf(names.withIndex().associate { it.value to it.index })
    }

    private fun findMaterial(player: EntityPlayer, candidates: List<ItemStack>,
                             setting: MultiblockBuildSetting): ItemStack?
    {
        if (player.isCreative)
        {
            for (i in candidates.indices.reversed())
            {
                val candidate = candidates[i]
                if (candidate.item is ItemBlock)
                    return candidate.copy().apply { count = 1 }
            }
            return null
        }

        findInInventory(player, candidates)?.let { return it }
        return if (setting.isAppengMode) AEItemSource.extract(player, candidates) else null
    }

    private fun findInInventory(player: EntityPlayer, candidates: List<ItemStack>): ItemStack?
    {
        for (pass in 0..1)
        {
            for (slot in player.inventory.mainInventory.indices)
            {
                val stack = player.inventory.mainInventory[slot]
                if (stack.isEmpty || stack.item !is ItemBlock) continue
                for (candidate in candidates)
                {
                    val match = ItemStack.areItemsEqual(candidate, stack)
                                && (pass != 0 || ItemStack.areItemStackTagsEqual(candidate, stack))
                    if (!match) continue
                    val result = stack.copy().apply { count = 1 }
                    stack.shrink(1)
                    return result
                }
            }
        }
        return null
    }

    @Suppress("Deprecation")
    private fun placeAt(player: EntityPlayer, world: World, pos: BlockPos, stack: ItemStack): Boolean
    {
        val itemBlock = stack.item as? ItemBlock ?: return false
        stack.count = 1
        val state = itemBlock.block.getStateFromMeta(itemBlock.getMetadata(stack.itemDamage))
        return itemBlock.placeBlockAt(stack, player, world, pos, EnumFacing.UP, 0.5f, 0.5f, 0.5f, state)
    }

    private fun fixFrontFacings(world: World, controller: MultiblockControllerBase, positions: Set<BlockPos>)
    {
        val facings = arrayOf(controller.frontFacing) + FACINGS
        for (pos in positions)
        {
            val metaTileEntity = (world.getTileEntity(pos) as? IGregTechTileEntity)?.metaTileEntity ?: continue
            if (metaTileEntity === controller) continue

            var found = false
            for (facing in facings)
            {
                if (metaTileEntity.isValidFrontFacing(facing) && pos.offset(facing) !in positions)
                {
                    metaTileEntity.frontFacing = facing
                    found = true
                    break
                }
            }
            if (found) continue

            for (facing in FACINGS)
            {
                if (world.isAirBlock(pos.offset(facing)) && metaTileEntity.isValidFrontFacing(facing))
                {
                    metaTileEntity.frontFacing = facing
                    break
                }
            }
        }
    }

    private fun stateStack(state: IBlockState): ItemStack
    {
        val item = Item.getItemFromBlock(state.block) ?: return ItemStack.EMPTY
        return ItemStack(item, 1, state.block.damageDropped(state))
    }

    internal fun getStackFromBlockInfo(info: BlockInfo): ItemStack
    {
        val metaTileEntity = getMetaTileEntity(info)
        if (metaTileEntity != null)
            return metaTileEntity.stack().copy()
        return stateStack(info.blockState)
    }

    internal fun getMetaTileEntity(info: BlockInfo?): MetaTileEntity?
        = (info?.tileEntity as? IGregTechTileEntity)?.metaTileEntity

    // TODO: Change to Morphism Lib ItemUtil usage?
    private fun isSameStack(first: ItemStack, second: ItemStack): Boolean
            = ItemStack.areItemsEqual(first, second) && ItemStack.areItemStackTagsEqual(first, second)
}
