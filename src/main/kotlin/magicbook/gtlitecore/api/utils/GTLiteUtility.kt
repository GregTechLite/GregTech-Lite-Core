package magicbook.gtlitecore.api.utils

import gregtech.api.GTValues
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials
import gregtech.api.util.RandomPotionEffect
import gregtech.common.items.MetaItems
import magicbook.gtlitecore.common.item.GTLiteMetaItems
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
         * Usage:
         * ```
         * averageRGB(n, mat1.materialRGB, mat2.materialRGB, ..., matn.materialRGB)
         * ```
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
         * @see [gregtech.loaders.recipe.CraftingComponent.MOTOR]
         */
        @JvmStatic
        fun getMotorByTier(tier: Int): MetaItem<*>.MetaValueItem = when (tier)
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
            else -> MetaItems.LOGO // TODO MAX
        }

        @JvmStatic
        fun getMotorStackByTier(tier: Int): ItemStack = getMotorByTier(tier).stackForm

        /**
         * @see [gregtech.loaders.recipe.CraftingComponent.PISTON]
         */
        @JvmStatic
        fun getPistonByTier(tier: Int): MetaItem<*>.MetaValueItem = when (tier)
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
            else -> MetaItems.LOGO // TODO MAX
        }

        @JvmStatic
        fun getPistonStackByTier(tier: Int): ItemStack = getPistonByTier(tier).stackForm

        /**
         * @see [gregtech.loaders.recipe.CraftingComponent.PUMP]
         */
        @JvmStatic
        fun getPumpByTier(tier: Int): MetaItem<*>.MetaValueItem = when (tier)
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
            else -> MetaItems.LOGO // TODO MAX
        }

        @JvmStatic
        fun getPumpStackByTier(tier: Int): ItemStack = getPumpByTier(tier).stackForm

        /**
         * @see [gregtech.loaders.recipe.CraftingComponent.CONVEYOR]
         */
        @JvmStatic
        fun getConveyorByTier(tier: Int): MetaItem<*>.MetaValueItem = when (tier)
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
            else -> MetaItems.LOGO // TODO MAX
        }

        @JvmStatic
        fun getConveyorStackByTier(tier: Int): ItemStack = getConveyorByTier(tier).stackForm

        /**
         * @see [gregtech.loaders.recipe.CraftingComponent.ROBOT_ARM]
         */
        @JvmStatic
        fun getRobotArmByTier(tier: Int): MetaItem<*>.MetaValueItem = when (tier)
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
            else -> MetaItems.LOGO // TODO MAX
        }

        @JvmStatic
        fun getRobotArmStackByTier(tier: Int): ItemStack = getRobotArmByTier(tier).stackForm

        /**
         * @see [gregtech.loaders.recipe.CraftingComponent.EMITTER]
         */
        @JvmStatic
        fun getEmitterByTier(tier: Int): MetaItem<*>.MetaValueItem = when (tier)
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
            else -> MetaItems.LOGO // TODO MAX
        }

        @JvmStatic
        fun getEmitterStackByTier(tier: Int): ItemStack = getEmitterByTier(tier).stackForm

        /**
         * @see [gregtech.loaders.recipe.CraftingComponent.SENSOR]
         */
        @JvmStatic
        fun getSensorByTier(tier: Int): MetaItem<*>.MetaValueItem = when (tier)
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
            else -> MetaItems.LOGO // TODO MAX
        }

        @JvmStatic
        fun getSensorStackByTier(tier: Int): ItemStack = getSensorByTier(tier).stackForm

        /**
         * @see [gregtech.loaders.recipe.CraftingComponent.FIELD_GENERATOR]
         */
        @JvmStatic
        fun getFieldGenByTier(tier: Int): MetaItem<*>.MetaValueItem = when (tier)
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
            else -> MetaItems.LOGO // TODO MAX
        }

        @JvmStatic
        fun getFieldGenStackByTier(tier: Int): ItemStack = getFieldGenByTier(tier).stackForm

        /**
         * Get mining drone by tier like other component tiered getters.
         */
        @JvmStatic
        fun getDroneByTier(tier: Int): MetaItem<*>.MetaValueItem = when (tier)
        {
            GTValues.LV -> GTLiteMetaItems.MINING_DRONE_LV
            GTValues.MV -> GTLiteMetaItems.MINING_DRONE_MV
            GTValues.HV -> GTLiteMetaItems.MINING_DRONE_HV
            GTValues.EV -> GTLiteMetaItems.MINING_DRONE_EV
            GTValues.IV -> GTLiteMetaItems.MINING_DRONE_IV
            GTValues.LuV -> GTLiteMetaItems.MINING_DRONE_LuV
            GTValues.ZPM -> GTLiteMetaItems.MINING_DRONE_ZPM
            GTValues.UV -> GTLiteMetaItems.MINING_DRONE_UV
            GTValues.UHV -> GTLiteMetaItems.MINING_DRONE_UHV
            GTValues.UEV -> GTLiteMetaItems.MINING_DRONE_UEV
            GTValues.UIV -> GTLiteMetaItems.MINING_DRONE_UIV
            GTValues.UXV -> GTLiteMetaItems.MINING_DRONE_UXV
            GTValues.OpV -> GTLiteMetaItems.MINING_DRONE_OpV
            else -> GTLiteMetaItems.MINING_DRONE_MAX
        }

        @JvmStatic
        fun getDroneStackByTier(tier: Int): ItemStack = getDroneByTier(tier).stackForm

        /**
         * @see [gregtech.loaders.recipe.CraftingComponent.CABLE]
         */
        @JvmStatic
        fun getCableByTier(tier: Int): Material = when (tier)
        {
            GTValues.ULV -> Materials.RedAlloy
            GTValues.LV -> Materials.Tin
            GTValues.MV -> Materials.Copper
            GTValues.HV -> Materials.Gold
            GTValues.EV -> Materials.Aluminium
            GTValues.IV -> Materials.Platinum
            GTValues.LuV -> Materials.NiobiumTitanium
            GTValues.ZPM -> Materials.VanadiumGallium
            GTValues.UV -> Materials.YttriumBariumCuprate
            GTValues.UHV -> Materials.Europium
            // TODO UEV-UXV
            else -> Materials.Water // TODO OpV Cable
        }

        /**
         * @see [gregtech.loaders.recipe.CraftingComponent.CABLE]
         */
        @JvmStatic
        fun getComponentCableByTier(tier: Int): Material = when (tier)
        {
            GTValues.ULV -> Materials.RedAlloy
            GTValues.LV -> Materials.Tin
            GTValues.MV -> Materials.Copper
            GTValues.HV -> Materials.Gold
            GTValues.EV -> Materials.Aluminium
            GTValues.IV -> Materials.Tungsten
            GTValues.LuV -> Materials.NiobiumTitanium
            GTValues.ZPM -> Materials.VanadiumGallium
            GTValues.UV -> Materials.YttriumBariumCuprate
            GTValues.UHV -> Materials.Europium
            // TODO UEV-UXV
            else -> Materials.Water // TODO OpV Cable
        }

        /**
         * @see [gregtech.loaders.recipe.CraftingComponent.HULL]
         */
        @JvmStatic
        fun getHullMaterialByTier(tier: Int): Material = when (tier)
        {
            GTValues.ULV -> Materials.Bronze
            GTValues.LV -> Materials.Steel
            GTValues.MV -> Materials.Aluminium
            GTValues.HV -> Materials.StainlessSteel
            GTValues.EV -> Materials.Titanium
            GTValues.IV -> Materials.TungstenSteel
            GTValues.LuV -> Materials.RhodiumPlatedPalladium
            GTValues.ZPM -> Materials.NaquadahAlloy
            GTValues.UV -> Materials.Darmstadtium
            GTValues.UHV -> Materials.Neutronium
            // TODO UEV-UXV
            else -> Materials.Water // TODO OpV
        }

        /**
         * Materials which the tiered components used like [getHullMaterialByTier].
         */
        @JvmStatic
        fun getComponentMaterialByTier(tier: Int): Material = when (tier)
        {
            GTValues.ULV -> Materials.WroughtIron
            GTValues.LV -> Materials.Tin
            GTValues.MV -> Materials.Bronze
            GTValues.HV -> Materials.Steel
            GTValues.EV -> Materials.StainlessSteel
            GTValues.IV -> Materials.TungstenSteel
            GTValues.LuV -> Materials.HSSS
            GTValues.ZPM -> Materials.Osmiridium
            GTValues.UV -> Materials.Tritanium
            // GTValues.UHV -> GTLiteMaterials.Adamantium
            // TODO UEV-UXV
            else -> Materials.Water // TODO OpV
        }

        /**
         * @see [gregtech.loaders.recipe.CraftingComponent.PIPE_NORMAL]
         * @see [gregtech.loaders.recipe.CraftingComponent.PIPE_LARGE]
         */
        @JvmStatic
        fun getPipeByTier(tier: Int): Material = when (tier)
        {
            GTValues.ULV -> Materials.Copper
            GTValues.LV -> Materials.Bronze
            GTValues.MV -> Materials.Steel
            GTValues.HV -> Materials.StainlessSteel
            GTValues.EV -> Materials.Titanium
            GTValues.IV -> Materials.TungstenSteel
            GTValues.LuV -> Materials.NiobiumTitanium
            GTValues.ZPM -> Materials.Iridium
            GTValues.UV -> Materials.Naquadah
            GTValues.UHV -> Materials.Duranium
            GTValues.UEV -> Materials.Neutronium
            // TODO UIV-UXV
            else -> Materials.Water // TODO OpV
        }

        /**
         * Add potion effect tooltips which is client-only.
         * This is useful for [magicbook.gtlitecore.common.item.behavior.FoodBehavior].
         */
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