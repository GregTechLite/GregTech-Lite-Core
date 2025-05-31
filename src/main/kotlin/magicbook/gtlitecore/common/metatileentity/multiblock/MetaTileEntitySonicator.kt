package magicbook.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues
import gregtech.api.GTValues.ULV
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SONICATION_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.borosilicateGlasses
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.motorCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.pumpCasings
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03
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

class MetaTileEntitySonicator(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, SONICATION_RECIPES)
{

    private var motorCasingTier = 0
    private var pumpCasingTier = 0
    private var glassTier = 0

    init
    {
        recipeMapWorkable = SonicatorRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var motorCasings: List<IBlockState>
        private lateinit var pumpCasings: List<IBlockState>
        private lateinit var borosilicateGlasses: List<IBlockState>

        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.COBALT_BRASS)

        private val pipeCasingState
            get() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val motorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_MOTOR_CASING)
        val pumpCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PUMP_CASING)
        val borosilicateGlass = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_BOROSILICATE_GLASS)
        val maxLength = maxLength(listOf(motorCasing, pumpCasing, borosilicateGlass))
        motorCasings = consistent(motorCasing, maxLength)
        pumpCasings = consistent(pumpCasing, maxLength)
        borosilicateGlasses = consistent(borosilicateGlass, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntitySonicator(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("MotorCasingTieredStats")
        val type2: Any? = context.get<Any>("PumpCasingTieredStats")
        val type3: Any? = context.get<Any>("BorosilicateGlassTieredStats")
        motorCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        pumpCasingTier = getOrDefault(
            { type2 is WrappedIntTier },
            { (type2 as WrappedIntTier).getIntTier() }, 0)
        glassTier = getOrDefault(
            { type3 is WrappedIntTier },
            { (type3 as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        motorCasingTier = 0
        pumpCasingTier = 0
        glassTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "CCCCC", "CCCCC", "     ")
        .aisle("CCCCC", "C###C", "CGGGC", "     ")
        .aisle("CCCCC", "C#M#C", "CGQGC", "  P  ")
        .aisle("CCCCC", "C###C", "CGGGC", "  P  ")
        .aisle("CCCCC", "CCCCC", "CCCCC", "  P  ")
        .aisle(" CCC ", " CPC ", " CQC ", "  P  ")
        .aisle(" CCC ", " CSC ", " CCC ", "     ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(46)
            .or(autoAbilities()))
        .where('P', states(pipeCasingState))
        .where('M', motorCasings())
        .where('Q', pumpCasings())
        .where('G', borosilicateGlasses())
        .where('#', air())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.COBALT_BRASS_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer =  GTLiteTextures.SONICATOR_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo: MutableList<MultiblockShapeInfo> = ArrayList()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("ECCCC", "CCCCC", "CCCCC", "     ")
            .aisle("CCCCC", "C   C", "CGGGC", "     ")
            .aisle("CCCCC", "C M C", "CGQGC", "  P  ")
            .aisle("CCCCC", "C   C", "CGGGC", "  P  ")
            .aisle("CCCCC", "CCCCC", "CCCCC", "  P  ")
            .aisle(" CCC ", " CPC ", " CQC ", "  P  ")
            .aisle(" KNL ", " ISJ ", " CCC ", "     ")
            .where('S', GTLiteMetaTileEntities.SONICATOR, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('P', pipeCasingState)
            .where('N', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.NORTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[ULV], EnumFacing.SOUTH)
            .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[ULV], EnumFacing.SOUTH)
        val count = AtomicInteger()
        StreamEx.of(motorCasings)
            .map { b ->
                builder.where('M', b)
                builder.where('Q', pumpCasings[count.get()])
                builder.where('G', borosilicateGlasses[count.get()])
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
            if (motorCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (pumpCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (glassTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(motorCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(pumpCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_MINOR_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(glassTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            motorCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            pumpCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_MINOR_TIERED_MACHINE)
            glassTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.sonicator.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.sonicator.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.sonicator.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.sonicator.tooltip.4"))
    }

    override fun canBeDistinct() = true

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    private inner class SonicatorRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor() = if (glassTier >= GTValues.UV - 3) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow(min(motorCasingTier, pumpCasingTier))).toInt())
        }

        override fun getParallelLimit() = 4 * getTierByVoltage(maxVoltage)

    }

}
