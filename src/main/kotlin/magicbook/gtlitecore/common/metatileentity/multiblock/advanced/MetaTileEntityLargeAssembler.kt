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
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.conveyorCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.robotArmCasings
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing01
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
import kotlin.math.pow

class MetaTileEntityLargeAssembler(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, ASSEMBLER_RECIPES)
{

    private var robotArmCasingTier = 0
    private var conveyorCasingTier = 0

    init
    {
        recipeMapWorkable = LargeAssemblerRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var robotArmCasings: List<IBlockState>
        private lateinit var conveyorCasings: List<IBlockState>

        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_01.getState(BlockMetalCasing01.MetalCasingType.IRIDIUM)

        private val pipeCasingState
            get() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)

        private val glassCasingState
            get() = MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val robotArmCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_ROBOT_ARM_CASING)
        val conveyorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_CONVEYOR_CASING)
        val maxLength = maxLength(listOf(robotArmCasing, conveyorCasing))
        robotArmCasings = consistent(robotArmCasing, maxLength)
        conveyorCasings = consistent(conveyorCasing, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityLargeAssembler(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("RobotArmCasingTieredStats")
        val type2: Any? = context.get<Any>("ConveyorCasingTieredStats")
        robotArmCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        conveyorCasingTier = getOrDefault(
            { type2 is WrappedIntTier },
            { (type2 as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        robotArmCasingTier = 0
        conveyorCasingTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCCC", "CCCCCC", "CCCCCC", "CCCCCC")
        .aisle("CCCCCC", "CRPPPC", "CPPPPC", "CCGGGC")
        .aisle("CCCCCC", "CMMMMC", "CP###C", "CCGGGC")
        .aisle("CCCCCC", "CSRRRC", "CCGGGC", "CCGGGC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(12)
            .or(this.autoAbilities(true, true, true, true, true, false, false)))
        .where('P', states(pipeCasingState))
        .where('G', states(glassCasingState))
        .where('R', robotArmCasings())
        .where('M', conveyorCasings())
        .where('#', air())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.IRIDIUM_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("ECCCCC", "CCCCCC", "CCCCCC", "CCCCCC")
            .aisle("CCCCCC", "CRPPPC", "CPPPPC", "CCGGGC")
            .aisle("CCCCCC", "CMMMMC", "CP   C", "CCGGGC")
            .aisle("CNCCCC", "ISRRRC", "JCGGGC", "KCGGGC")
            .where('S', GTLiteMetaTileEntities.LARGE_ASSEMBLER, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('P', pipeCasingState)
            .where('G', glassCasingState)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
            .where('N', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
        val count = AtomicInteger()
        StreamEx.of(robotArmCasings)
            .map { b ->
                builder.where('R', b)
                builder.where('M', conveyorCasings[count.get()])
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
            if (robotArmCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (conveyorCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(robotArmCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(conveyorCasingTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            robotArmCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            conveyorCasingTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_assembler.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_assembler.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_assembler.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_assembler.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class LargeAssemblerRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(conveyorCasingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * robotArmCasingTier

    }

}