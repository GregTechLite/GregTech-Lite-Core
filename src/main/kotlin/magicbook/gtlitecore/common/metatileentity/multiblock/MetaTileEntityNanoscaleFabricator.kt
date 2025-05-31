package magicbook.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues.ULV
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.BlockWorldState
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.recipes.Recipe
import gregtech.api.util.BlockInfo
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.TextComponentUtil.stringWithColor
import gregtech.api.util.TextComponentUtil.translationWithColor
import gregtech.api.util.TextFormattingUtil.formatNumbers
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.utils.TooltipHelper
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityItemBus
import gregtech.core.sound.GTSoundEvents
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.IBlockTier
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MOLECULAR_BEAM_RECIPES
import magicbook.gtlitecore.api.recipe.property.NoCoilTemperatureProperty
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.emitterCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.robotArmCasings
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockCrucible
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import one.util.streamex.StreamEx
import org.jetbrains.annotations.ApiStatus
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer
import java.util.function.Predicate
import kotlin.math.floor
import kotlin.math.pow

class MetaTileEntityNanoscaleFabricator(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, MOLECULAR_BEAM_RECIPES)
{

    private var emitterCasingTier = 0
    private var robotArmCasingTier = 0
    private var temperature = 0

    init
    {
        recipeMapWorkable = NanoscaleFabricatorRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        // Pseudo crucible hash map for decorative JEI page multiblock shape infos.
        // Do not merge it to GTLiteAPI maps because only this mte used this map.
        private val CRUCIBLES = Object2ObjectOpenHashMap<IBlockState, IBlockTier>()

        private var hasRegistered = false
        private lateinit var emitterCasings: List<IBlockState>
        private lateinit var robotArmCasings: List<IBlockState>
        private lateinit var crucibles: List<IBlockState>

        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.TITANIUM_TUNGSTEN_CARBIDE)

        private val secondCasingState
            get() = GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.HSLA_STEEL)

        private val thirdCasingState
            get() = GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.SUBSTRATE_CASING)

        private val glassState
            get() = MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        // Locally initialized the decorative crucible map, this is not a tiered stats.
        for (tier in BlockCrucible.CrucibleType.entries.toTypedArray())
            CRUCIBLES[GTLiteMetaBlocks.CRUCIBLE.getState(tier)] =
                WrappedIntTier(tier, tier.ordinal + 1)

        val emitterCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_EMITTER_CASING)
        val robotArmCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_ROBOT_ARM_CASING)
        val crucible = LazyStreams.fastSortedByKey(CRUCIBLES)

        val maxLength = maxLength(listOf(emitterCasing, robotArmCasing, crucible))
        emitterCasings = consistent(emitterCasing, maxLength)
        robotArmCasings = consistent(robotArmCasing, maxLength)
        crucibles = consistent(crucible, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityNanoscaleFabricator(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("EmitterCasingTieredStats")
        val type2: Any? = context.get<Any>("RobotArmCasingTieredStats")
        emitterCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        robotArmCasingTier = getOrDefault(
            { type2 is WrappedIntTier },
            { (type2 as WrappedIntTier).getIntTier() }, 0)
        // Initialized crucible temperature and amount info.
        val crucibleAmount = context.getInt("CrucibleAmount")
        temperature = if (crucibleAmount != 0) context.getInt("Temperature") / crucibleAmount else 0
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        emitterCasingTier = 0
        robotArmCasingTier = 0
        temperature = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("   TTT   ", "   TIT   ", "   TCT   ", "         ")
        .aisle("  XXXXX  ", "  XXRXX  ", "  XXXXX  ", "  XXXXX  ")
        .aisle(" XXXXXXX ", " X#####X ", " X#####X ", " XXGGGXX ")
        .aisle("TXXTTTXXT", "TX##Z##XT", "TX#####XT", " XGGGGGX ")
        .aisle("TXXTITXXT", "IR#ZAZ#RI", "CX#####XC", " XGGGGGX ")
        .aisle("TXXTTTXXT", "TX##Z##XT", "TX#####XT", " XGGGGGX ")
        .aisle(" XXXXXXX ", " X#####X ", " X#####X ", " XXGGGXX ")
        .aisle("  XXXXX  ", "  XXRXX  ", "  XXXXX  ", "  XXSXX  ")
        .aisle("   TTT   ", "   TIT   ", "   TCT   ", "         ")
        .where('S', selfPredicate())
        .where('X', states(casingState)
            .setMinGlobalLimited(84)
            .or(autoAbilities(true, true, false, true, true, false, false)))
        .where('T', states(secondCasingState)
            .setMinGlobalLimited(36))
        .where('G', states(glassState))
        // TODO Allowed a Huge Item Import Bus which has Int.MAX_VALUE stack size?
        .where('I', metaTileEntities(MetaTileEntities.ITEM_IMPORT_BUS[ULV])
            .or(states(secondCasingState)))
        .where('C', states(secondCasingState)
            .or(cruciblePredicate()))
        .where('A', states(thirdCasingState))
        .where('Z', emitterCasings())
        .where('R', robotArmCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    @ApiStatus.Internal
    private fun cruciblePredicate() = TraceabilityPredicate(Predicate { blockWorldState: BlockWorldState ->
        val state: IBlockState = blockWorldState.blockState
        val block: Block = state.block
        if (block is BlockCrucible)
        {
            val storedTemperature = blockWorldState.matchContext.getOrPut("Temperature", 0)
            blockWorldState.matchContext["Temperature"] = block.getState(state).temperature + storedTemperature
            val storedCrucibleAmount = blockWorldState.matchContext.getOrPut("CrucibleAmount", 0)
            blockWorldState.matchContext["CrucibleAmount"] = 1 + storedCrucibleAmount
            return@Predicate true
        }
            return@Predicate false
        }) { BlockCrucible.CrucibleType.entries
                .sortedBy { it.temperature }
                .map { BlockInfo(GTLiteMetaBlocks.CRUCIBLE.getState(it), null) }
                .toTypedArray()
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer
    {
        if (sourcePart is MetaTileEntityItemBus && sourcePart.exportItems.getSlots() == 0)
            return GTLiteTextures.HSLA_STEEL_CASING
        return GTLiteTextures.TITANIUM_TUNGSTEN_CARBIDE_CASING
    }

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.NANOSCALE_FABRICATOR_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo: MutableList<MultiblockShapeInfo> = ArrayList()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("   TTT   ", "   T3T   ", "   TCT   ", "         ")
            .aisle("  XXXXX  ", "  XXRXX  ", "  XXXXX  ", "  XENEX  ")
            .aisle(" XXXXXXX ", " X     X ", " X     X ", " XXGGGXX ")
            .aisle("TXXTTTXXT", "TX  Z  XT", "TX     XT", " XGGGGGX ")
            .aisle("TXXTTTXXT", "1R ZAZ R2", "CX     XC", " XGGGGGX ")
            .aisle("TXXTTTXXT", "TX  Z  XT", "TX     XT", " XGGGGGX ")
            .aisle(" XXXXXXX ", " X     X ", " X     X ", " XXGGGXX ")
            .aisle("  XXXXX  ", "  XXRXX  ", "  XXXXX  ", "  XJSKX  ")
            .aisle("   TTT   ", "   T0T   ", "   TCT   ", "         ")
            .where('S', GTLiteMetaTileEntities.NANOSCALE_FABRICATOR, EnumFacing.SOUTH)
            .where('X', casingState)
            .where('T', secondCasingState)
            .where('G', glassState)
            .where('0', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('1', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.WEST)
            .where('2', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.EAST)
            .where('3', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.NORTH)
            .where('A', thirdCasingState)
            .where('N', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.NORTH)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.NORTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[ULV], EnumFacing.SOUTH)
        val count = AtomicInteger()
        StreamEx.of(emitterCasings)
            .map { b  ->
                builder.where('Z', b)
                builder.where('R', robotArmCasings[count.get()])
                builder.where('C', crucibles[count.get()])
                count.getAndIncrement()
                builder
            }
            .nonNull()
            .forEach { b -> shapeInfo.add(b.build()) }
        return shapeInfo
    }

    override fun update()
    {
        super.update()
        if ((world as World).isRemote)
        {
            if (emitterCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (robotArmCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(emitterCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(robotArmCasingTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            emitterCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            robotArmCasingTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.nanoscale_fabricator.tooltip.1"))
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.nanoscale_fabricator.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.nanoscale_fabricator.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.nanoscale_fabricator.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.nanoscale_fabricator.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.nanoscale_fabricator.tooltip.6"))
    }

    override fun addDisplayText(textList: List<ITextComponent>)
    {
        MultiblockDisplayText.builder(textList, isStructureFormed)
            .setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(energyContainer)
            .addEnergyTierLine(getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
            .addCustom { tl ->
                if (isStructureFormed)
                {
                    // Temperature infos.
                    val temperatureInfo = stringWithColor(TextFormatting.RED,
                        formatNumbers(temperature.toLong()) + "K")
                    tl.add(translationWithColor(TextFormatting.GRAY,
                        "gtlitecore.machine.nanoscale_fabricator.average_temperature", temperatureInfo))
                    // Average temperature infos.
                    val temperatureLowerInfo = stringWithColor(TextFormatting.RED,
                        formatNumbers((temperature - 250).toLong()) + "K")
                    val temperatureUpperInfo = stringWithColor(TextFormatting.RED,
                        formatNumbers((temperature + 250).toLong()) + "K")
                    tl.add(translationWithColor(TextFormatting.GRAY,
                        "gtlitecore.machine.nanoscale_fabricator.temperature_range", temperatureLowerInfo, temperatureUpperInfo))
                }
            }
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progressPercent)
    }

    override fun canBeDistinct() = false

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    private inner class NanoscaleFabricatorRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity, true)
    {

        override fun checkRecipe(recipe: Recipe): Boolean
        {
            val delta = temperature - recipe.getProperty(NoCoilTemperatureProperty.INSTANCE, 0)!!
            return (delta in 1..249)
        }

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow(emitterCasingTier)).toInt())
        }

        override fun getParallelLimit() = 4 * robotArmCasingTier

    }

}
