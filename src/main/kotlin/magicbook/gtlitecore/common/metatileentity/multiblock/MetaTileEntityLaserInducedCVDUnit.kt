package magicbook.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.ConfigHolder
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CRYSTALLIZATION_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.LASER_CVD_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MOLECULAR_BEAM_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SONICATION_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyX
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.emitterCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.pumpCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.sensorCasings
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing01
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import one.util.streamex.StreamEx
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityLaserInducedCVDUnit(metaTileEntityId: ResourceLocation?) : MultiMapMultiblockController(metaTileEntityId, arrayOf(LASER_CVD_RECIPES, CRYSTALLIZATION_RECIPES, MOLECULAR_BEAM_RECIPES, SONICATION_RECIPES))
{

    private var emitterCasingTier = 0
    private var pumpCasingTier = 0
    private var sensorCasingTier = 0

    init
    {
        recipeMapWorkable = LaserInducedCVDRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var emitterCasings: List<IBlockState>
        private lateinit var pumpCasings: List<IBlockState>
        private lateinit var sensorCasings: List<IBlockState>

        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.HASTELLOY_X)

        private val secondCasingState
            get() = GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.SUBSTRATE_CASING)

        private val glassState
            get() = GTLiteMetaBlocks.TRANSPARENT_CASING_01.getState(BlockGlassCasing01.GlassType.ZBLAN)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val emitterCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_EMITTER_CASING)
        val pumpCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PUMP_CASING)
        val sensorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_SENSOR_CASING)
        val maxLength = maxLength(listOf(emitterCasing, pumpCasing, sensorCasing))
        emitterCasings = consistent(emitterCasing, maxLength)
        pumpCasings = consistent(pumpCasing, maxLength)
        sensorCasings = consistent(sensorCasing, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityLaserInducedCVDUnit(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("EmitterCasingTieredStats")
        val type2: Any? = context.get<Any>("PumpCasingTieredStats")
        val type3: Any? = context.get<Any>("SensorCasingTieredStats")
        emitterCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        pumpCasingTier = getOrDefault(
            { type2 is WrappedIntTier },
            { (type2 as WrappedIntTier).getIntTier() }, 0)
        sensorCasingTier = getOrDefault(
            { type3 is WrappedIntTier },
            { (type3 as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        emitterCasingTier = 0
        pumpCasingTier = 0
        sensorCasingTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("   CCCCC", "   CGGGC", "   CGGGC", "   CGGGC", "    CCC ")
        .aisle("CCCCCCCC", "CCCFDDDF", "CCCF###F", "CCCF###F", "    FFF ")
        .aisle("CCCCCCCC", "CPCFDDDF", "CPRG###T", "CCCF###F", "    FFF ")
        .aisle("CCCCCCCC", "CCCFDDDF", "CSCF###F", "CCCF###F", "    FFF ")
        .aisle("   CCCCC", "   CGGGC", "   CGGGC", "   CGGGC", "    CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(60)
            .or(autoAbilities()))
        .where('D', states(secondCasingState))
        .where('G', states(glassState))
        .where('F', frames(HastelloyX))
        .where('R', emitterCasings())
        .where('P', pumpCasings())
        .where('T', sensorCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.HASTELLOY_X_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.CVD_UNIT_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo: MutableList<MultiblockShapeInfo> = ArrayList()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("   CCCCC", "   CGGGC", "   CGGGC", "   CGGGC", "    CCC ")
            .aisle("CCCCCCCC", "CCCFDDDF", "CCCF   F", "CCCF   F", "    FFF ")
            .aisle("CCCCCCCC", "CPCFDDDF", "CPRG   T", "CCCF   F", "    FFF ")
            .aisle("CECCCCCC", "KNLFDDDF", "ISJF   F", "CCCF   F", "    FFF ")
            .aisle("   CCCCC", "   CGGGC", "   CGGGC", "   CGGGC", "    CCC ")
            .where('S', GTLiteMetaTileEntities.LASER_INDUCED_CVD_UNIT, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('D', secondCasingState)
            .where('F', MetaBlocks.FRAMES[HastelloyX]!!.getBlock(HastelloyX))
            .where('G', glassState)
            .where('N', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.SOUTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[ULV], EnumFacing.SOUTH)
            .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[ULV], EnumFacing.SOUTH)
        val count = AtomicInteger()
        StreamEx.of(emitterCasings)
            .map { b ->
                if (builder != null)
                {
                    builder.where('R', b)
                    builder.where('P', pumpCasings[count.get()])
                    builder.where('T', sensorCasings[count.get()])
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
            if (pumpCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (sensorCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(emitterCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(pumpCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(sensorCasingTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            emitterCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            pumpCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE)
            sensorCasingTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.laser_induced_cvd_unit.tooltip.7"))
    }

    override fun canBeDistinct() = true

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    inner class LaserInducedCVDRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor(): Double = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.5.pow(min(emitterCasingTier, sensorCasingTier))).toInt())
        }

        override fun getParallelLimit() = 8 * pumpCasingTier

    }

}
