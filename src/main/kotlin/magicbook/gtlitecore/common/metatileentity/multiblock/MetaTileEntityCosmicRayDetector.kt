package magicbook.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues.ULV
import gregtech.api.block.VariantActiveBlock
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.BlockWorldState
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.recipes.Recipe
import gregtech.api.util.BlockInfo
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.utils.TooltipHelper
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockFusionCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.COSMIC_RAY_DETECTING_RECIPES
import magicbook.gtlitecore.api.recipe.property.MinimumHeightProperty
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HDCS
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.emitterCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.fieldGenCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.processorCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.sensorCasings
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import one.util.streamex.StreamEx
import org.apache.commons.lang3.ArrayUtils
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityCosmicRayDetector(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, COSMIC_RAY_DETECTING_RECIPES)
{

    private var emitterCasingTier = 0
    private var sensorCasingTier = 0
    private var fieldGenCasingTier = 0
    private var processorCasingTier = 0

    // Target block at the top block in multiblock structure top.
    private var topBlockPos = BlockPos(0, -64, 0)

    init
    {
        recipeMapWorkable = CosmicRayDetectorWorkableHandler(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var emitterCasings: List<IBlockState>
        private lateinit var sensorCasings: List<IBlockState>
        private lateinit var fieldGenCasings: List<IBlockState>
        private lateinit var processorCasings: List<IBlockState>

        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.QUANTUM_ALLOY)

        private val secondCasingState
            get() = GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.REFLECTIVE_SURFACE_CASING)

        private val coilState
            get() = MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val emitterCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_EMITTER_CASING)
        val sensorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_SENSOR_CASING)
        val fieldGenCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_FIELD_GEN_CASING)
        val processorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PROCESSOR_CASING)
        val maxLength = maxLength(listOf(emitterCasing, sensorCasing, fieldGenCasing, processorCasing))
        emitterCasings = consistent(emitterCasing, maxLength)
        sensorCasings = consistent(sensorCasing, maxLength)
        fieldGenCasings = consistent(fieldGenCasing, maxLength)
        processorCasings = consistent(processorCasing, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityCosmicRayDetector(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("EmitterCasingTieredStats")
        val type2: Any? = context.get<Any>("SensorCasingTieredStats")
        val type3: Any? = context.get<Any>("FieldGenCasingTieredStats")
        val type4: Any? = context.get<Any>("ProcessorCasingTieredStats")
        emitterCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        sensorCasingTier = getOrDefault(
            { type2 is WrappedIntTier },
            { (type2 as WrappedIntTier).getIntTier() }, 0)
        fieldGenCasingTier = getOrDefault(
            { type3 is WrappedIntTier },
            { (type3 as WrappedIntTier).getIntTier() }, 0)
        processorCasingTier = getOrDefault(
            { type4 is WrappedIntTier },
            { (type4 as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        emitterCasingTier = 0
        sensorCasingTier = 0
        fieldGenCasingTier = 0
        processorCasingTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "               ")
        .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "    DD   DD    ", "               ")
        .aisle("               ", "               ", "               ", "               ", "               ", "               ", "       D       ", "    DDD DDD    ", "   D       D   ", "               ")
        .aisle("      CCC      ", "      CCC      ", "      CCC      ", "               ", "               ", "       C       ", "     DDDDD     ", "   DD     DD   ", "  D         D  ", "               ")
        .aisle("     CCCCC     ", "     C###C     ", "     C###C     ", "      CCC      ", "      CCC      ", "     CCCCC     ", "    DDDDDDD    ", "  DD       DD  ", " D           D ", "               ")
        .aisle("    CCCCCCC    ", "    C#####C    ", "    C#####C    ", "     C###C     ", "     C###C     ", "    CCDDDCC    ", "   DDD   DDD   ", "  D         D  ", " D           D ", "               ")
        .aisle("   CCCCCCCCC   ", "   C###E###C   ", "   C#######C   ", "    C#####C    ", "    C##F##C    ", "    CDDDDDC    ", "   DD     DD   ", " DD         DD ", "D             D", "       *       ")
        .aisle("   CCCCCCCCC   ", "   C##ExE##C   ", "   C###c###C   ", "    C##c##C    ", "    C#FxF#C    ", "   CCDDEDDCC   ", "  DDD  f  DDD  ", " D     f     D ", "D      f      D", "      *s*      ")
        .aisle("   CCCCCCCCC   ", "   C###E###C   ", "   C#######C   ", "    C#####C    ", "    C##F##C    ", "    CDDDDDC    ", "   DD     DD   ", " DD         DD ", "D             D", "       *       ")
        .aisle("    CCCCCCC    ", "    C#####C    ", "    C#####C    ", "     C###C     ", "     C###C     ", "    CCDDDCC    ", "   DDD   DDD   ", "  D         D  ", " D           D ", "               ")
        .aisle("     CCCCC     ", "     C###C     ", "     C###C     ", "      CCC      ", "      CCC      ", "     CCCCC     ", "    DDDDDDD    ", "  DD       DD  ", " D           D ", "               ")
        .aisle("      CCC      ", "      CSC      ", "      CCC      ", "               ", "               ", "       C       ", "     DDDDD     ", "   DD     DD   ", "  D         D  ", "               ")
        .aisle("               ", "               ", "               ", "               ", "               ", "               ", "       D       ", "    DDD DDD    ", "   D       D   ", "               ")
        .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "    DD   DD    ", "               ")
        .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "               ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(148)
            .or(autoAbilities()))
        .where('D', states(secondCasingState))
        .where('c', states(coilState))
        .where('f', frames(HDCS))
        .where('E', emitterCasings())
        .where('F', fieldGenCasings())
        .where('s', sensorCasings())
        .where('x', processorCasings())
        .where('*', minHeightPredicate(Blocks.AIR.defaultState))
        .where('#', air())
        .where(' ', any())
        .build()

    private fun minHeightPredicate(vararg allowedStates: IBlockState?) = TraceabilityPredicate({ blockWorldState: BlockWorldState ->
        topBlockPos = blockWorldState.pos
        val state: IBlockState = blockWorldState.blockState
        if (state.block is VariantActiveBlock<*>)
            blockWorldState.matchContext.getOrPut("VABlock", LinkedList<Any>()).add(blockWorldState.pos)
            ArrayUtils.contains(allowedStates, state)
        }, {
            allowedStates.map { BlockInfo(it, null) }.toTypedArray()
        })

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.QUANTUM_ALLOY_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.COSMIC_RAY_DETECTOR_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo: MutableList<MultiblockShapeInfo> = ArrayList()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "               ")
            .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "    DD   DD    ", "               ")
            .aisle("               ", "               ", "               ", "               ", "               ", "               ", "       D       ", "    DDD DDD    ", "   D       D   ", "               ")
            .aisle("      CeC      ", "      CCC      ", "      CCC      ", "               ", "               ", "       C       ", "     DDDDD     ", "   DD     DD   ", "  D         D  ", "               ")
            .aisle("     CCCCC     ", "     C   C     ", "     C   C     ", "      CCC      ", "      CCC      ", "     CCCCC     ", "    DDDDDDD    ", "  DD       DD  ", " D           D ", "               ")
            .aisle("    CCCCCCC    ", "    C     C    ", "    C     C    ", "     C   C     ", "     C   C     ", "    CCDDDCC    ", "   DDD   DDD   ", "  D         D  ", " D           D ", "               ")
            .aisle("   CCCCCCCCC   ", "   C   E   C   ", "   C       C   ", "    C     C    ", "    C  F  C    ", "    CDDDDDC    ", "   DD     DD   ", " DD         DD ", "D             D", "               ")
            .aisle("   CCCCCCCCC   ", "   C  ExE  C   ", "   C   c   C   ", "    C  c  C    ", "    C FxF C    ", "   CCDDEDDCC   ", "  DDD  f  DDD  ", " D     f     D ", "D      f      D", "       s       ")
            .aisle("   CCCCCCCCC   ", "   C   E   C   ", "   C       C   ", "    C     C    ", "    C  F  C    ", "    CDDDDDC    ", "   DD     DD   ", " DD         DD ", "D             D", "               ")
            .aisle("    CCCCCCC    ", "    C     C    ", "    C     C    ", "     C   C     ", "     C   C     ", "    CCDDDCC    ", "   DDD   DDD   ", "  D         D  ", " D           D ", "               ")
            .aisle("     CCCCC     ", "     C   C     ", "     C   C     ", "      CCC      ", "      CCC      ", "     CCCCC     ", "    DDDDDDD    ", "  DD       DD  ", " D           D ", "               ")
            .aisle("      CMC      ", "      ISJ      ", "      CCC      ", "               ", "               ", "       C       ", "     DDDDD     ", "   DD     DD   ", "  D         D  ", "               ")
            .aisle("               ", "               ", "               ", "               ", "               ", "               ", "       D       ", "    DDD DDD    ", "   D       D   ", "               ")
            .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "    DD   DD    ", "               ")
            .aisle("               ", "               ", "               ", "               ", "               ", "               ", "               ", "               ", "      DDD      ", "               ")
            .where('S', GTLiteMetaTileEntities.COSMIC_RAY_DETECTOR, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('D', secondCasingState)
            .where('c', coilState)
            .where('f', MetaBlocks.FRAMES[HDCS]!!.getBlock(HDCS))
            .where('M', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.FLUID_EXPORT_HATCH[ULV], EnumFacing.SOUTH)
            .where('e', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.NORTH)
        val count = AtomicInteger()
        StreamEx.of(emitterCasings)
            .map { b ->
                if (builder != null)
                {
                    builder.where('E', b)
                    builder.where('F', fieldGenCasings[count.get()])
                    builder.where('s', sensorCasings[count.get()])
                    builder.where('x', processorCasings[count.get()])
                    count.getAndIncrement()
                }
                builder
            }
            .nonNull()
            .forEach(Consumer { b -> shapeInfo.add(b.build()) })
        return shapeInfo
    }

    override fun update()
    {
        super.update()
        if ((world as World).isRemote)
        {
            if (emitterCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (sensorCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (fieldGenCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (processorCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_LAST_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(emitterCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(sensorCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(fieldGenCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_LAST_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_LAST_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(processorCasingTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            emitterCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            sensorCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE)
            fieldGenCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_LAST_TIERED_MACHINE)
            processorCasingTier = buf.readInt()
    }

    override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    {
        data.setIntArray("topBlockPos", intArrayOf(topBlockPos.x, topBlockPos.y, topBlockPos.z))
        return super.writeToNBT(data)
    }

    override fun readFromNBT(data: NBTTagCompound)
    {
        super.readFromNBT(data)
        val pos = data.getIntArray("topBlockPos")
        topBlockPos = BlockPos(pos[0], pos[1], pos[2])
    }

    override fun addInformation(stack: ItemStack,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.cosmic_ray_detector.tooltip.5"))
    }

    override fun canBeDistinct() =  false

    override fun getBreakdownSound(): SoundEvent =  GTSoundEvents.BREAKDOWN_ELECTRICAL

    inner class CosmicRayDetectorWorkableHandler(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity, true)
    {

        override fun checkRecipe(recipe: Recipe): Boolean
            = super.checkRecipe(recipe) && recipe.getProperty(MinimumHeightProperty.getInstance(), -64)!! <= topBlockPos.y

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.5.pow(min(sensorCasingTier, processorCasingTier))).toInt())
        }

        override fun getParallelLimit(): Int = 32 * min(emitterCasingTier, fieldGenCasingTier)

    }

}
