package gregtechlite.gtlitecore.api.pattern

import gregtechlite.gtlitecore.api.block.attribute.BlockAttributeRegistry
import gregtechlite.gtlitecore.api.collection.obj2IntHashMapOf
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

class MultiblockBuildSetting(var tier: Int = 0,
                             val tiers: MutableMap<String, Int> = mutableMapOf(),
                             var repeatCount: Int = 0,
                             var noHatchMode: Int = 0,
                             var replaceMode: Int = 0,
                             var appengMode: Int = 0,
                             var additionalStructureCount: Int = DEFAULT_ADDITIONAL_STRUCTURES,
                             var mirrorMode: Int = 0)
{
    val isNoHatchMode: Boolean
        get() = noHatchMode == 1

    val isReplaceMode: Boolean
        get() = replaceMode == 1

    val isAppengMode: Boolean
        get() = appengMode == 1

    val isMirrorMode: Boolean
        get() = mirrorMode == 1

    companion object
    {
        const val NBT_KEY = "gtlitecore:multiblock_builder"

        const val TIER = "Tier"
        const val TIERS = "Tiers"
        const val REPEAT_COUNT = "RepeatCount"

        const val NO_HATCH_MODE = "NoHatchMode"
        const val REPLACE_MODE = "ReplaceMode"
        const val APPENG_MODE = "AppEngMode"

        const val ADDITIONAL_STRUCTURE_COUNT = "AdditionalStructureCount"
        const val MIRROR_MODE = "MirrorMode"

        const val MAX_ADDITIONAL_STRUCTURES = 5
        const val DEFAULT_ADDITIONAL_STRUCTURES = MAX_ADDITIONAL_STRUCTURES
        const val MAX_REPEAT_COUNT = 64

        fun read(stack: ItemStack): MultiblockBuildSetting
        {
            val tag = stack.tagCompound?.getCompoundTag(NBT_KEY) ?: return MultiblockBuildSetting()

            val tiers = obj2IntHashMapOf<String>()
            val tiersTag = tag.getCompoundTag(TIERS)

            val keys = tiersTag.keySet
            for (key in keys)
            {
                val value = tiersTag.getInteger(key)
                if (value > 0)
                    tiers[key] = value
            }

            return MultiblockBuildSetting(tier = readTier(tag), tiers = tiers,
                repeatCount = tag.getInteger(REPEAT_COUNT),
                noHatchMode = tag.getInteger(NO_HATCH_MODE),
                replaceMode = when
                {
                    tag.hasKey(REPLACE_MODE)             -> tag.getInteger(REPLACE_MODE)
                    else                                 -> 0
                },
                appengMode = when
                {
                    tag.hasKey(APPENG_MODE) -> tag.getInteger(APPENG_MODE)
                    else                    -> 0
                },
                additionalStructureCount = when
                {
                    tag.hasKey(ADDITIONAL_STRUCTURE_COUNT) -> tag.getInteger(ADDITIONAL_STRUCTURE_COUNT)
                    else                                   -> DEFAULT_ADDITIONAL_STRUCTURES
                },
                mirrorMode = tag.getInteger(MIRROR_MODE))
        }

        fun get(stack: ItemStack, key: String, defaultValue: Int): Int
        {
            val tag = stack.tagCompound?.getCompoundTag(NBT_KEY) ?: return defaultValue
            if (tag.hasKey(key))
                return tag.getInteger(key)
            if (key == TIER)
                return readTier(tag)
            return defaultValue
        }

        fun set(stack: ItemStack, key: String, value: Int)
        {
            stack.getOrCreateSubCompound(NBT_KEY).setInteger(key, value)
        }

        fun getTier(stack: ItemStack, registryName: String): Int
        {
            val tag = stack.tagCompound?.getCompoundTag(NBT_KEY) ?: return 0
            val tiers = tag.getCompoundTag(TIERS)
            return if (tiers.hasKey(registryName)) tiers.getInteger(registryName) else 0
        }

        fun setTier(stack: ItemStack, registryName: String, value: Int)
        {
            val root = stack.getOrCreateSubCompound(NBT_KEY)
            val tiers = if (root.hasKey(TIERS, 10))
                root.getCompoundTag(TIERS)
            else
                NBTTagCompound().also { root.setTag(TIERS, it) }

            if (value > 0)
                tiers.setInteger(registryName, value)
            else
                tiers.removeTag(registryName)
        }

        private fun readTier(tag: NBTTagCompound): Int = when
        {
            tag.hasKey(TIER) -> tag.getInteger(TIER)
            else             -> 0
        }
    }

    fun tierOf(registry: BlockAttributeRegistry<*>): Int = tiers[registry.name]?.takeIf { it > 0 } ?: tier
}
