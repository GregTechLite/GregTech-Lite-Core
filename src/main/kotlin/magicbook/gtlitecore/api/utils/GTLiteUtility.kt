package magicbook.gtlitecore.api.utils

import gregtech.api.GTValues
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.ore.OrePrefix
import gregtech.api.util.RandomPotionEffect
import gregtech.common.items.MetaItems
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.potion.PotionEffect
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import one.util.streamex.StreamEx
import java.util.function.BooleanSupplier
import java.util.function.Consumer
import java.util.function.Supplier

@Suppress("MISSING_DEPENDENCY_CLASS")
class GTLiteUtility
{

    companion object
    {

        @JvmField
        var collectorTankSizeFunction: java.util.function.Function<Int, Int> = java.util.function.Function<Int, Int> { tier ->
            if (tier <= GTValues.LV)
                return@Function 16000
            if (tier === GTValues.MV)
                return@Function 24000
            if (tier === GTValues.HV)
                return@Function 32000
            64000
        }

        @JvmStatic
        fun gtliteId(path: String) = ResourceLocation(GTLiteValues.MODID, path)

        @JvmStatic
        fun getId(namespace: String, path: String) = ResourceLocation(namespace, path)

        /**
         * Copies the [ItemStack].
         *
         * @param stack ItemStack for copying.
         * @return      A copy of ItemStack, or [ItemStack.EMPTY] if the ItemStack is empty.
         */
        @JvmStatic
        fun copy(stack: ItemStack) = if (stack.isEmpty) ItemStack.EMPTY else stack.copy()

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
         *
         */
        @JvmStatic
        fun toItem(blockState: IBlockState) = toItem(blockState, 1)

        /**
         *
         */
        @JvmStatic
        fun toItem(blockState: IBlockState, amount: Int)
                = ItemStack(blockState.block, amount, blockState.block.getMetaFromState(blockState))

        /**
         *
         */
        @JvmStatic
        fun toItem(blockPos: BlockPos, worldIn: IBlockAccess)
                = toItem(worldIn.getBlockState(blockPos))

        /**
         *
         */
        @JvmStatic
        fun toItem(blockPos: BlockPos, worldIn: IBlockAccess, amount: Int)
                = toItem(worldIn.getBlockState(blockPos), amount)

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

        @JvmStatic
        fun getMotorByTier(tier: Int): MetaItem<*>.MetaValueItem
        {
            return when (tier)
            {
                GTValues.LV -> MetaItems.ELECTRIC_MOTOR_LV
                GTValues.MV -> MetaItems.ELECTRIC_MOTOR_MV
                GTValues.HV -> MetaItems.ELECTRIC_MOTOR_HV
                GTValues.EV -> MetaItems.ELECTRIC_MOTOR_EV
                GTValues.IV -> MetaItems.ELECTRIC_MOTOR_IV
                GTValues.LuV -> MetaItems.ELECTRIC_MOTOR_LuV
                GTValues.ZPM -> MetaItems.ELECTRIC_MOTOR_ZPM
                GTValues.UV -> MetaItems.ELECTRIC_MOTOR_UV
                GTValues.UHV -> MetaItems.ELECTRIC_MOTOR_UHV
                GTValues.UEV -> MetaItems.ELECTRIC_MOTOR_UEV
                GTValues.UIV -> MetaItems.ELECTRIC_MOTOR_UIV
                GTValues.UXV -> MetaItems.ELECTRIC_MOTOR_UXV
                GTValues.OpV -> MetaItems.ELECTRIC_MOTOR_OpV
                else -> MetaItems.LOGO // TODO MAX Motor
            }
        }

        @JvmStatic
        fun getMotorStackByTier(tier: Int) = getMotorByTier(tier).stackForm

        @JvmStatic
        fun getPistonByTier(tier: Int): MetaItem<*>.MetaValueItem
        {
            return when (tier)
            {
                GTValues.LV -> MetaItems.ELECTRIC_PISTON_LV
                GTValues.MV -> MetaItems.ELECTRIC_PISTON_MV
                GTValues.HV -> MetaItems.ELECTRIC_PISTON_HV
                GTValues.EV -> MetaItems.ELECTRIC_PISTON_EV
                GTValues.IV -> MetaItems.ELECTRIC_PISTON_IV
                GTValues.LuV -> MetaItems.ELECTRIC_PISTON_LUV
                GTValues.ZPM -> MetaItems.ELECTRIC_PISTON_ZPM
                GTValues.UV -> MetaItems.ELECTRIC_PISTON_UV
                GTValues.UHV -> MetaItems.ELECTRIC_PISTON_UHV
                GTValues.UEV -> MetaItems.ELECTRIC_PISTON_UEV
                GTValues.UIV -> MetaItems.ELECTRIC_PISTON_UIV
                GTValues.UXV -> MetaItems.ELECTRIC_PISTON_UXV
                GTValues.OpV -> MetaItems.ELECTRIC_PISTON_OpV
                else -> MetaItems.LOGO // TODO MAX Piston
            }
        }

        @JvmStatic
        fun getPistonStackByTier(tier: Int) = getPistonByTier(tier).stackForm

        @JvmStatic
        fun getPumpByTier(tier: Int): MetaItem<*>.MetaValueItem
        {
            return when (tier)
            {
                GTValues.LV -> MetaItems.ELECTRIC_PUMP_LV
                GTValues.MV -> MetaItems.ELECTRIC_PUMP_MV
                GTValues.HV -> MetaItems.ELECTRIC_PUMP_HV
                GTValues.EV -> MetaItems.ELECTRIC_PUMP_EV
                GTValues.IV -> MetaItems.ELECTRIC_PUMP_IV
                GTValues.LuV -> MetaItems.ELECTRIC_PUMP_LuV
                GTValues.ZPM -> MetaItems.ELECTRIC_PUMP_ZPM
                GTValues.UV -> MetaItems.ELECTRIC_PUMP_UV
                GTValues.UHV -> MetaItems.ELECTRIC_PUMP_UHV
                GTValues.UEV -> MetaItems.ELECTRIC_PUMP_UEV
                GTValues.UIV -> MetaItems.ELECTRIC_PUMP_UIV
                GTValues.UXV -> MetaItems.ELECTRIC_PUMP_UXV
                GTValues.OpV -> MetaItems.ELECTRIC_PUMP_OpV
                else -> MetaItems.LOGO // TODO MAX Pump
            }
        }

        @JvmStatic
        fun getPumpStackByTier(tier: Int) = getPumpByTier(tier).stackForm

        @JvmStatic
        fun getConveyorByTier(tier: Int): MetaItem<*>.MetaValueItem
        {
            return when (tier)
            {
                GTValues.LV -> MetaItems.CONVEYOR_MODULE_LV
                GTValues.MV -> MetaItems.CONVEYOR_MODULE_MV
                GTValues.HV -> MetaItems.CONVEYOR_MODULE_HV
                GTValues.EV -> MetaItems.CONVEYOR_MODULE_EV
                GTValues.IV -> MetaItems.CONVEYOR_MODULE_IV
                GTValues.LuV -> MetaItems.CONVEYOR_MODULE_LuV
                GTValues.ZPM -> MetaItems.CONVEYOR_MODULE_ZPM
                GTValues.UV -> MetaItems.CONVEYOR_MODULE_UV
                GTValues.UHV -> MetaItems.CONVEYOR_MODULE_UHV
                GTValues.UEV -> MetaItems.CONVEYOR_MODULE_UEV
                GTValues.UIV -> MetaItems.CONVEYOR_MODULE_UIV
                GTValues.UXV -> MetaItems.CONVEYOR_MODULE_UXV
                GTValues.OpV -> MetaItems.CONVEYOR_MODULE_OpV
                else -> MetaItems.LOGO // TODO MAX Conveyor
            }
        }

        @JvmStatic
        fun getConveyorStackByTier(tier: Int) = getConveyorByTier(tier).stackForm

        @JvmStatic
        fun getRobotArmByTier(tier: Int): MetaItem<*>.MetaValueItem
        {
            return when (tier)
            {
                GTValues.LV -> MetaItems.ROBOT_ARM_LV
                GTValues.MV -> MetaItems.ROBOT_ARM_MV
                GTValues.HV -> MetaItems.ROBOT_ARM_HV
                GTValues.EV -> MetaItems.ROBOT_ARM_EV
                GTValues.IV -> MetaItems.ROBOT_ARM_IV
                GTValues.LuV -> MetaItems.ROBOT_ARM_LuV
                GTValues.ZPM -> MetaItems.ROBOT_ARM_ZPM
                GTValues.UV -> MetaItems.ROBOT_ARM_UV
                GTValues.UHV -> MetaItems.ROBOT_ARM_UHV
                GTValues.UEV -> MetaItems.ROBOT_ARM_UEV
                GTValues.UIV -> MetaItems.ROBOT_ARM_UIV
                GTValues.UXV -> MetaItems.ROBOT_ARM_UXV
                GTValues.OpV -> MetaItems.ROBOT_ARM_OpV
                else -> MetaItems.LOGO // TODO MAX Robot Arm
            }
        }

        @JvmStatic
        fun getRobotArmStackByTier(tier: Int) = getRobotArmByTier(tier).stackForm

        @JvmStatic
        fun getEmitterByTier(tier: Int): MetaItem<*>.MetaValueItem
        {
            return when (tier)
            {
                GTValues.LV -> MetaItems.EMITTER_LV
                GTValues.MV -> MetaItems.EMITTER_MV
                GTValues.HV -> MetaItems.EMITTER_HV
                GTValues.EV -> MetaItems.EMITTER_EV
                GTValues.IV -> MetaItems.EMITTER_IV
                GTValues.LuV -> MetaItems.EMITTER_LuV
                GTValues.ZPM -> MetaItems.EMITTER_ZPM
                GTValues.UV -> MetaItems.EMITTER_UV
                GTValues.UHV -> MetaItems.EMITTER_UHV
                GTValues.UEV -> MetaItems.EMITTER_UEV
                GTValues.UIV -> MetaItems.EMITTER_UIV
                GTValues.UXV -> MetaItems.EMITTER_UXV
                GTValues.OpV -> MetaItems.EMITTER_OpV
                else -> MetaItems.LOGO // TODO MAX Emitter
            }
        }

        @JvmStatic
        fun getEmitterStackByTier(tier: Int) = getEmitterByTier(tier).stackForm

        @JvmStatic
        fun getSensorByTier(tier: Int): MetaItem<*>.MetaValueItem
        {
            return when (tier)
            {
                GTValues.LV -> MetaItems.SENSOR_LV
                GTValues.MV -> MetaItems.SENSOR_MV
                GTValues.HV -> MetaItems.SENSOR_HV
                GTValues.EV -> MetaItems.SENSOR_EV
                GTValues.IV -> MetaItems.SENSOR_IV
                GTValues.LuV -> MetaItems.SENSOR_LuV
                GTValues.ZPM -> MetaItems.SENSOR_ZPM
                GTValues.UV -> MetaItems.SENSOR_UV
                GTValues.UHV -> MetaItems.SENSOR_UHV
                GTValues.UEV -> MetaItems.SENSOR_UEV
                GTValues.UIV -> MetaItems.SENSOR_UIV
                GTValues.UXV -> MetaItems.SENSOR_UXV
                GTValues.OpV -> MetaItems.SENSOR_OpV
                else -> MetaItems.LOGO // TODO MAX Sensor
            }
        }

        @JvmStatic
        fun getSensorStackByTier(tier: Int) = getSensorByTier(tier).stackForm

        @JvmStatic
        fun getFieldGenByTier(tier: Int): MetaItem<*>.MetaValueItem
        {
            return when (tier)
            {
                GTValues.LV -> MetaItems.FIELD_GENERATOR_LV
                GTValues.MV -> MetaItems.FIELD_GENERATOR_MV
                GTValues.HV -> MetaItems.FIELD_GENERATOR_HV
                GTValues.EV -> MetaItems.FIELD_GENERATOR_EV
                GTValues.IV -> MetaItems.FIELD_GENERATOR_IV
                GTValues.LuV -> MetaItems.FIELD_GENERATOR_LuV
                GTValues.ZPM -> MetaItems.FIELD_GENERATOR_ZPM
                GTValues.UV -> MetaItems.FIELD_GENERATOR_UV
                GTValues.UHV -> MetaItems.FIELD_GENERATOR_UHV
                GTValues.UEV -> MetaItems.FIELD_GENERATOR_UEV
                GTValues.UIV -> MetaItems.FIELD_GENERATOR_UIV
                GTValues.UXV -> MetaItems.FIELD_GENERATOR_UXV
                GTValues.OpV -> MetaItems.FIELD_GENERATOR_OpV
                else -> MetaItems.LOGO // TODO MAX Field Gen
            }
        }

        @JvmStatic
        fun getFieldGenStackByTier(tier: Int) = getFieldGenByTier(tier).stackForm

        @JvmStatic
        fun getCircuitByTier(tier: Int): ItemStack
        {
            return when (tier)
            {
                GTValues.LV -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.LV)
                GTValues.MV -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.MV)
                GTValues.HV -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.HV)
                GTValues.EV -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.EV)
                GTValues.IV -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.IV)
                GTValues.LuV -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.LuV)
                GTValues.ZPM -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.ZPM)
                GTValues.UV -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.UV)
                GTValues.UHV -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.UHV)
                GTValues.UEV -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.UEV)
                GTValues.UIV -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.UIV)
                GTValues.UXV -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.UXV)
                GTValues.OpV -> OreDictUnifier.get(OrePrefix.circuit, MarkerMaterials.Tier.OpV)
                else -> MetaItems.LOGO.stackForm // TODO MAX Field Gen
            }
        }

        @SideOnly(Side.CLIENT)
        @JvmStatic
        fun addPotionEffectTooltip(effects: List<RandomPotionEffect>,
                                   lines: MutableList<String>?)
        {
            lines!!.add(TextComponentTranslation("${GTLiteValues.MODID}.tooltip.potion.header").formattedText)
            effects.forEach(Consumer { effect: RandomPotionEffect ->
                lines.add(TextComponentTranslation("${GTLiteValues.MODID}.tooltip.potion.each",
                    TextComponentTranslation((effect.effect as PotionEffect).effectName).formattedText,
                    TextComponentTranslation("enchantment.level." + (effect.effect.getAmplifier() + 1)),
                    effect.effect.getDuration(), 100 - effect.chance).formattedText) })
        }

    }

}