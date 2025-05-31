package magicbook.gtlitecore.common.metatileentity.multiblock.advanced

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
import gregtech.api.recipes.RecipeMaps.BENDER_RECIPES
import gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.motorCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.pistonCasings
import magicbook.gtlitecore.api.utils.stream.LazyStreams
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

class MetaTileEntityLargeBender(metaTileEntityId: ResourceLocation?) : MultiMapMultiblockController(metaTileEntityId, arrayOf(BENDER_RECIPES, FORMING_PRESS_RECIPES))
{

    private var pistonCasingTier = 0
    private var motorCasingTier = 0

    init
    {
        recipeMapWorkable = LargeBenderRecipeLogic(this)
        registerCasingMaps()
    }

    companion object {
        private var hasRegistered = false
        private lateinit var pistonCasings: List<IBlockState>
        private lateinit var motorCasings: List<IBlockState>

        private val casingState
            get() = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE)
        private val pipeCasingState
            get() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val pistonCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PISTON_CASING)
        val motorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_MOTOR_CASING)
        val maxLength = maxLength(listOf(pistonCasing, motorCasing))
        pistonCasings = consistent(pistonCasing, maxLength)
        motorCasings = consistent(motorCasing, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityLargeBender(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("PistonCasingTieredStats")
        val type2: Any? = context.get<Any>("MotorCasingTieredStats")
        pistonCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        motorCasingTier = getOrDefault(
            { type2 is WrappedIntTier },
            { (type2 as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pistonCasingTier = 0
        motorCasingTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCC", "CCCC", "CCQC")
        .aisle("CCCC", "CPMC", "CCQC")
        .aisle("CCCC", "CPMC", "CCQC")
        .aisle("CCCC", "CSCC", "CCQC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(16)
            .or(autoAbilities(true, true, true, true, false, false, false)))
        .where('Q', states(pipeCasingState))
        .where('P', pistonCasings())
        .where('M', motorCasings())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.STABLE_TITANIUM_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.BENDER_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("ECCC", "CCCC", "CCQC")
            .aisle("ICCC", "CPMC", "CCQJ")
            .aisle("ICCC", "CPMC", "CCQJ")
            .aisle("CCCC", "CSNC", "CCQC")
            .where('S', GTLiteMetaTileEntities.LARGE_BENDER, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('Q', pipeCasingState)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
            .where('N', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.WEST)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.EAST)
        val count = AtomicInteger()
        StreamEx.of(pistonCasings)
            .map { b ->
                builder.where('P', b)
                builder.where('M', motorCasings[count.get()])
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
            if (pistonCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (motorCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(pistonCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(motorCasingTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            pistonCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            motorCasingTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_bender.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_bender.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_bender.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_bender.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class LargeBenderRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(motorCasingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * pistonCasingTier

    }

}
