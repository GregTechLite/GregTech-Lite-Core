package gregtechlite.gtlitecore.api.pattern

import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.util.BlockInfo
import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.api.block.attribute.BlockAttributeRegistry
import gregtechlite.gtlitecore.api.collection.obj2IntHashMapOf
import it.unimi.dsi.fastutil.objects.Object2IntMap
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import kotlin.math.abs

class CandidateResolver(private val player: EntityPlayer,
                        private val setting: MultiblockBuildSetting,
                        private val optionalGroups: Object2IntMap<String>)
{
    private val tierCache = obj2IntHashMapOf<BlockAttributeRegistry<*>>()

    fun collect(predicate: TraceabilityPredicate,
                cacheGlobal: Object2IntMap<TraceabilityPredicate.SimplePredicate>,
                cacheLayer: Object2IntMap<TraceabilityPredicate.SimplePredicate>): List<BlockInfo>
    {
        for (limit in predicate.limited)
        {
            if (limit.minLayerCount <= 0 || !isEnabled(limit)) continue
            val infos = candidatesOf(limit)
            if (!isPlaceHatch(infos)) continue
            val current = cacheLayer[limit]
            if (current == null)
            {
                cacheLayer[limit] = 1
            }
            else
            {
                if (current >= limit.minLayerCount ||
                    (limit.maxLayerCount != -1 && current >= limit.maxLayerCount)) continue
                cacheLayer[limit] = current + 1
            }
            return select(limit, infos)
        }

        for (limit in predicate.limited)
        {
            if (limit.minGlobalCount <= 0 || !isEnabled(limit)) continue
            val infos = candidatesOf(limit)
            if (!isPlaceHatch(infos)) continue
            val current = cacheGlobal[limit]
            if (current == null)
            {
                cacheGlobal[limit] = 1
                return select(limit, infos)
            }
            if (current < limit.minGlobalCount &&
                (limit.maxGlobalCount == -1 || current < limit.maxGlobalCount))
            {
                cacheGlobal[limit] = current + 1
                return select(limit, infos)
            }
        }

        val result = arrayListOf<BlockInfo>()
        for (limit in predicate.limited)
        {
            if (!isEnabled(limit)) continue
            if (limit.maxLayerCount != -1 &&
                (cacheLayer[limit] ?: Int.MAX_VALUE) == limit.maxLayerCount) continue
            if (limit.maxGlobalCount != -1 &&
                (cacheGlobal[limit] ?: Int.MAX_VALUE) == limit.maxGlobalCount) continue
            val infos = candidatesOf(limit)
            if (!isPlaceHatch(infos)) continue
            result += select(limit, infos)
            cacheLayer[limit] = (cacheLayer[limit] ?: 0) + 1
            cacheGlobal[limit] = (cacheGlobal[limit] ?: 0) + 1
        }
        for (common in predicate.common)
        {
            if (!isEnabled(common)) continue
            val infos = candidatesOf(common)
            if (!isPlaceHatch(infos)) continue
            result += select(common, infos)
        }
        return result
    }

    private fun isEnabled(predicate: TraceabilityPredicate.SimplePredicate): Boolean
    {
        val group = (predicate as? GTLitePredicate)?.optionalGroup ?: return true
        return (optionalGroups[group] ?: Int.MAX_VALUE) < setting.additionalStructureCount
    }

    private fun candidatesOf(predicate: TraceabilityPredicate.SimplePredicate): Array<BlockInfo>
    = predicate.candidates?.get() ?: emptyArray()

    private fun isPlaceHatch(infos: Array<BlockInfo>): Boolean
    {
        if (!setting.isNoHatchMode) return true
        return infos.none { MultiblockAutoBuilder.getMetaTileEntity(it) is IMultiblockPart }
    }

    private fun select(predicate: TraceabilityPredicate.SimplePredicate, infos: Array<BlockInfo>): List<BlockInfo>
    {
        val filtered = infos.toList().filter { it.blockState.block != Blocks.AIR }
        if (filtered.isEmpty())
            return filtered

        val registry = (predicate as? GTLitePredicate)?.registry
            ?: if (filtered.any { GTLiteAPI.COIL_TIER.getAttribute(it.blockState) != null })
                GTLiteAPI.COIL_TIER else null
        return if (registry != null) selectTier(registry, filtered) else filtered
    }

    private fun selectTier(registry: BlockAttributeRegistry<*>, infos: List<BlockInfo>): List<BlockInfo>
    {
        val ordered = registry.ascendingBlocks
        val present = ordered.filter { state -> infos.any { it.blockState == state } }
        if (present.isEmpty())
            return infos

        val requested = tierCache.getOrPut(registry) {
            val configured = setting.tierOf(registry)
            if (configured > 0)
                return@getOrPut configured
            else
                return@getOrPut autoTier(registry, infos, present)
        }
        val target = ordered.getOrNull((requested - 1).coerceAtLeast(0)) ?: ordered.last()
        val chosen = if (target in present)
        {
            target
        }
        else
        {
            val targetIndex = ordered.indexOf(target)
            present.minByOrNull { abs(ordered.indexOf(it) - targetIndex) } ?: present.first()
        }
        return infos.filter { it.blockState == chosen }
    }

    private fun autoTier(registry: BlockAttributeRegistry<*>,
                         infos: List<BlockInfo>,
                         present: List<IBlockState>): Int
    {
        if (player.isCreative) return registry.ascendingBlocks.indexOf(present.last()) + 1

        for (state in present)
        {
            val stack = infos.firstOrNull { it.blockState == state }?.let(MultiblockAutoBuilder::getStackFromBlockInfo) ?: continue
            if (player.inventory.mainInventory.any { !it.isEmpty && ItemStack.areItemsEqual(stack, it) })
            {
                return registry.ascendingBlocks.indexOf(state) + 1
            }
        }
        return registry.ascendingBlocks.indexOf(present.first()) + 1
    }
}