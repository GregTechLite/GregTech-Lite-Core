package magicbook.gtlitecore.api.utils

import gregtech.api.GTValues
import gregtech.api.unification.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fluids.FluidRegistry
import one.util.streamex.StreamEx
import java.util.function.BooleanSupplier
import java.util.function.Supplier
import kotlin.math.pow

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteUtility
{

    companion object
    {

        /**
         * Tank capabilities for sap collector machines.
         *
         * - For Steam Machines and LV: 16000L
         * - For MV: 24000L
         * - For HV: 32000L
         * - For EV-IV: 64000L
         *
         * @see magicbook.gtlitecore.common.metatileentity.single.MetaTileEntitySapCollector
         * @see magicbook.gtlitecore.common.metatileentity.single.MetaTileEntitySteamSapCollector
         */
        @JvmField
        var collectorTankSizeFunction: java.util.function.Function<Int, Int> = java.util.function.Function { tier ->
            when
            {
                tier <= GTValues.LV -> 16000
                tier == GTValues.MV -> 24000
                tier == GTValues.HV -> 32000
                else -> 64000
            }
        }

        /**
         * Get [ResourceLocation] with [GTLiteValues.MODID].
         */
        @JvmStatic
        fun gtliteId(path: String) = ResourceLocation(GTLiteValues.MODID, path)

        /**
         * Get [ResourceLocation] with [namespace].
         */
        @JvmStatic
        fun getId(namespace: String, path: String) = ResourceLocation(namespace, path)

        /**
         * Copies the [ItemStack].
         *
         * @param stack ItemStack for copying.
         * @return      A copy of ItemStack, or [ItemStack.EMPTY] if the ItemStack is empty.
         */
        @JvmStatic
        fun copy(stack: ItemStack): ItemStack = if (stack.isEmpty) ItemStack.EMPTY else stack.copy()

        /**
         * Copies the [ItemStack] with new stack size.
         *
         * @param stack ItemStack for copying.
         * @param count New stack size of copying ItemStack.
         * @return      A copy of ItemStack or [ItemStack.EMPTY] if the ItemSTack is empty.
         */
        @JvmStatic
        fun copy(stack: ItemStack, count: Int): ItemStack
        {
            if (stack.isEmpty) return ItemStack.EMPTY
            val copyStack = stack.copy()
            copyStack.count = count
            return copyStack
        }

        /**
         * Copies the [ItemStack] with new stack size.
         *
         * @param stack ItemStack for copying.
         * @param meta  Metadata of ItemStack.
         * @param count New stack size of copying ItemStack.
         * @return      A copy of ItemStack or [ItemStack.EMPTY] if the ItemSTack is empty.
         */
        @JvmStatic
        fun copy(stack: ItemStack, meta: Int, count: Int): ItemStack
        {
            if (stack.isEmpty) return ItemStack.EMPTY
            val copyStack = stack.copy()
            copyStack.itemDamage = meta
            copyStack.count = count
            return copyStack
        }

        /**
         * Copies the [BlockPos] to [BlockPos.MutableBlockPos].
         *
         * @param blockPos BlockPos for copying.
         * @return         A MutableBlockPos copy of BlockPos.
         */
        @JvmStatic
        fun copy(blockPos: BlockPos) = BlockPos.MutableBlockPos(blockPos.toImmutable())

        /**
         * Safety copies the [BlockPos] to [BlockPos.MutableBlockPos].
         *
         * @param blockPos        BlockPos for copying.
         * @param defaultBlockPos Default value of BlockPos which be copied.
         * @return                A MutableBlockPos copy of BlockPos.
         */
        @JvmStatic
        fun copyOrDefault(blockPos: BlockPos?, defaultBlockPos: BlockPos)
            = if (blockPos == null) copy(defaultBlockPos) else copy(blockPos)

        /**
         * Get int value from [nbt] data or return [defaultValue].
         */
        @JvmStatic
        fun getOrDefault(nbt: NBTTagCompound, key: String, defaultValue: Int): Int
        {
            if (nbt.hasKey(key)) return nbt.getInteger(key)
            return defaultValue
        }

        /**
         * Universal version of [getOrDefault].
         */
        @JvmStatic
        fun <T> getOrDefault(canGet: BooleanSupplier, getter: Supplier<T>, defaultValue: T): T
            = if (canGet.asBoolean) getter.get() else defaultValue

        /**
         * Transformed a block via [blockState] to [ItemStack].
         */
        @JvmStatic
        fun toItem(blockState: IBlockState) = toItem(blockState, 1)

        /**
         * Transformed a block on ([blockPos], [worldIn]) to [ItemStack].
         */
        @JvmStatic
        fun toItem(blockPos: BlockPos, worldIn: IBlockAccess)
                = toItem(worldIn.getBlockState(blockPos))

        /**
         * Transformed a block on ([blockPos], [worldIn]) to [ItemStack] with [amount].
         */
        @JvmStatic
        fun toItem(blockPos: BlockPos, worldIn: IBlockAccess, amount: Int)
                = toItem(worldIn.getBlockState(blockPos), amount)

        /**
         * Transformed a block via [blockState] to [ItemStack] with [amount].
         */
        @JvmStatic
        fun toItem(blockState: IBlockState, amount: Int)
                = ItemStack(blockState.block, amount, blockState.block.getMetaFromState(blockState))

        /**
         * Get max length of [lists].
         */
        @JvmStatic
        fun <T> maxLength(lists: List<List<T>>): Int = StreamEx.of(lists)
            .mapToInt { obj: List<T> -> obj.size }
            .max()
            .orElse(0)

        /**
         * Consistent a new list with [list] and generate its length with default list.
         */
        @JvmStatic
        fun <T> consistent(list: List<T>, length: Int): List<T>
        {
            if (list.size >= length) return list
            val finalList = ArrayList(list)
            val last = list[list.size - 1]
            for (i in 0 until length - list.size)
                finalList.add(last)
            return finalList
        }

        /**
         * Get average RGB number with original component RGBs and divisor.
         *
         * This method is same as [Material.Builder.colorAverage] calling, but as we have known,
         * there's many unknown composite materials do not have components, so we need these
         * methods to build a color average method for RGB.
         */
        @JvmStatic
        fun averageRGB(divisor: Int, vararg inputs: Int): Int
        {
            var red = 0
            var green = 0
            var blue = 0

            for (input in inputs)
            {
                // Make sure to account for opacity.
                red += (input - (input % (256.0.pow(2).toInt()))) shr (16 % 256)
                // Removes the last chunk, shifts it out, and removes the first chunk.
                green += ((input - (input % 256)) shr 8) % 256
                blue += input % 256
            }

            var result = blue / divisor
            result += (green / divisor) shl 8
            result += (red / divisor) shl 16

            return result
        }

        /**
         * Get fluid from other mods' fluids with a default value.
         */
        @JvmStatic
        fun getModFluid(fluidName: String)
            = getModFluid(fluidName, 1000)

        /**
         * Get fluid from other mods' fluids by FML fluid registrate.
         */
        @JvmStatic
        fun getModFluid(fluidName: String, amount: Int)
            = requireNotNull(FluidRegistry.getFluidStack(fluidName, amount))

    }

}