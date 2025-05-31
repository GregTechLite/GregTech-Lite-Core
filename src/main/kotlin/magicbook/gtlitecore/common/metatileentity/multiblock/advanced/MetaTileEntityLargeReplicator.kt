package magicbook.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.REPLICATOR_RECIPES
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockFusionCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
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
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import one.util.streamex.StreamEx
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

class MetaTileEntityLargeReplicator(metaTileEntityId: ResourceLocation) : RecipeMapMultiblockController(metaTileEntityId, REPLICATOR_RECIPES)
{

    private var fieldGenCasingTier = 0
    private var emitterCasingTier = 0
    private var sensorCasingTier = 0
    private var processorCasingTier = 0

    init
    {
        recipeMapWorkable = LargeReplicatorRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var fieldGenCasings: List<IBlockState>
        private lateinit var emitterCasings: List<IBlockState>
        private lateinit var sensorCasings: List<IBlockState>
        private lateinit var processorCasings: List<IBlockState>

        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.RENE_N5)

        private val coilState
            get() = MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val fieldGenCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_FIELD_GEN_CASING)
        val emitterCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_EMITTER_CASING)
        val sensorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_SENSOR_CASING)
        val processorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PROCESSOR_CASING)
        val maxLength = maxLength(listOf(fieldGenCasing, emitterCasing, sensorCasing, processorCasing))
        fieldGenCasings = consistent(fieldGenCasing, maxLength)
        emitterCasings = consistent(emitterCasing, maxLength)
        sensorCasings = consistent(sensorCasing, maxLength)
        processorCasings = consistent(processorCasing, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityLargeReplicator(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("FieldGenCasingTieredStats")
        val type2: Any? = context.get<Any>("EmitterCasingTieredStats")
        val type3: Any? = context.get<Any>("SensorCasingTieredStats")
        val type4: Any? = context.get<Any>("ProcessorCasingTieredStats")
        fieldGenCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        emitterCasingTier = getOrDefault(
            { type2 is WrappedIntTier },
            { (type2 as WrappedIntTier).getIntTier() }, 0)
        sensorCasingTier = getOrDefault(
            { type3 is WrappedIntTier },
            { (type3 as WrappedIntTier).getIntTier() }, 0)
        processorCasingTier = getOrDefault(
            { type4 is WrappedIntTier },
            { (type4 as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        fieldGenCasingTier = 0
        emitterCasingTier = 0
        sensorCasingTier = 0
        processorCasingTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("     XXEXX     ", "     XXEXX     ", "       X       ")
        .aisle("   XXXXXXXXX   ", "   XXCCCCCXX   ", "     XPXPX     ")
        .aisle("  XXXXXEXXXXX  ", "  XCCCXSXCCCX  ", "   XXF s FXX   ")
        .aisle(" XXXXX   XXXXX ", " XCCXX   XXCCX ", "  XF       FX  ")
        .aisle(" XXX       XXX ", " XCX       XCX ", "  X         X  ")
        .aisle("XXXX       XXXX", "XCCX       XCCX", " XF         FX ")
        .aisle("XXX         XXX", "XCX         XCX", " P           P ")
        .aisle("EXE         EXE", "ECE         ECE", "XXs         sXX")
        .aisle("XXX         XXX", "XCX         XCX", " P           P ")
        .aisle("XXXX       XXXX", "XCCX       XCCX", " XF         FX ")
        .aisle(" XXX       XXX ", " XCX       XCX ", "  X         X  ")
        .aisle(" XXXXX   XXXXX ", " XCCXX   XXCCX ", "  XF       FX  ")
        .aisle("  XXXXXEXXXXX  ", "  XCCCXEXCCCX  ", "   XXF s FXX   ")
        .aisle("   XXXXXXXXX   ", "   XXCCCCCXX   ", "     XPXPX     ")
        .aisle("     XXEXX     ", "     XXEXX     ", "       X       ")
        .where('S', selfPredicate())
        .where('X', states(casingState)
            .setMinGlobalLimited(30)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('C', states(coilState))
        .where('F', fieldGenCasings())
        .where('P', processorCasings())
        .where('s', sensorCasings())
        .where('E', emitterCasings())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.RENE_N5_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.REPLICATOR_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("     XXEXX     ", "     XXEXX     ", "       X       ")
            .aisle("   XXXXXXXXX   ", "   XXCCCCCXX   ", "     XPXPX     ")
            .aisle("  XXXXKELXXXX  ", "  XCCCISJCCCX  ", "   XXF s FXX   ")
            .aisle(" XXXXX   XXXXX ", " XCCXX   XXCCX ", "  XF       FX  ")
            .aisle(" XXX       XXX ", " XCX       XCX ", "  X         X  ")
            .aisle("XXXX       XXXX", "XCCX       XCCX", " XF         FX ")
            .aisle("XXX         XXX", "XCX         XCX", " P           P ")
            .aisle("EXE         EXE", "ECE         ECE", "XXs         sXX")
            .aisle("XXX         XXX", "XCX         XCX", " P           P ")
            .aisle("XXXX       XXXX", "XCCX       XCCX", " XF         FX ")
            .aisle(" XXX       XXX ", " XCX       XCX ", "  X         X  ")
            .aisle(" XXXXX   XXXXX ", " XCCXX   XXCCX ", "  XF       FX  ")
            .aisle("  XXXXXEXXXXX  ", "  XCCCNEHCCCX  ", "   XXF s FXX   ")
            .aisle("   XXXXXXXXX   ", "   XXCCCCCXX   ", "     XPXPX     ")
            .aisle("     XXEXX     ", "     XXEXX     ", "       X       ")
            .where('S', GTLiteMetaTileEntities.LARGE_REPLICATOR, EnumFacing.SOUTH)
            .where('X', casingState)
            .where('C', coilState)
            .where('N', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.NORTH)
            .where('H', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
            .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH)
        val count = AtomicInteger()
        StreamEx.of(fieldGenCasings)
            .map { b ->
                builder.where('F', b)
                builder.where('E', emitterCasings[count.get()])
                builder.where('s', sensorCasings[count.get()])
                builder.where('P', processorCasings[count.get()])
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
        if (world.isRemote)
        {
            if (fieldGenCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (emitterCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (sensorCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (processorCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_LAST_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(fieldGenCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(emitterCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(sensorCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_LAST_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_LAST_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(processorCasingTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            fieldGenCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            emitterCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE)
            sensorCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_LAST_TIERED_MACHINE)
            processorCasingTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_replicator.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_replicator.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_replicator.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_replicator.tooltip.4"))
    }

    override fun canBeDistinct() = false

    private inner class LargeReplicatorRecipeLogic(metaTileEntity: RecipeMapMultiblockController) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(min(emitterCasingTier, sensorCasingTier).toDouble()))).toInt())
        }

        override fun getParallelLimit() = 4 * min(fieldGenCasingTier, processorCasingTier)

    }

}