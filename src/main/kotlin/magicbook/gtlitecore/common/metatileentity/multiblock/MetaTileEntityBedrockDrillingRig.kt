package magicbook.gtlitecore.common.metatileentity.multiblock

import com.google.common.base.Preconditions
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.ULV
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.capability.impl.NotifiableItemStackHandler
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
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.TooltipHelper
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.BlockMultiblockCasing
import gregtech.common.blocks.BlockTurbineCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.DRILLING_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HSLASteel
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.toItem
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.motorCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.pistonCasings
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03
import magicbook.gtlitecore.common.block.blocks.BlockMultiblockCasing01
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.items.IItemHandlerModifiable
import one.util.streamex.StreamEx
import org.jetbrains.annotations.ApiStatus
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.floor
import kotlin.math.pow

class MetaTileEntityBedrockDrillingRig(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, DRILLING_RECIPES)
{

    private var pistonCasingTier = 0
    private var motorCasingTier = 0

    // Target block at drill head block in multiblock structure bottom.
    private var targetBlock: BlockPos? = null

    init
    {
        recipeMapWorkable = BedrockDrillingRigWorkableHandler(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var pistonCasings: List<IBlockState>
        private lateinit var motorCasings: List<IBlockState>

        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.TRINAQUADALLOY)

        private val secondCasingState
            get() = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)

        private val gearboxCasingState
            get() = MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TUNGSTENSTEEL_GEARBOX)

        private val thirdCasingState
            get() = GTLiteMetaBlocks.MULTIBLOCK_CASING_01.getState(BlockMultiblockCasing01.MultiblockCasingType.DRILL_HEAD)

        private val fourthCasingState
            get() = MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)
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

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityBedrockDrillingRig(metaTileEntityId)

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
        // Transformed targetBlock from blockPos in inputInventory.
        targetBlock?.let {
            (inputInventory as IItemHandlerModifiable).setStackInSlot(0, toItem((world as World).getBlockState(it)))
        }
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pistonCasingTier = 0
        motorCasingTier = 0
        targetBlock = null
        // (inputInventory as IItemHandlerModifiable).setStackInSlot(0, ItemStack.EMPTY)
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        inputInventory = NotifiableItemStackHandler(this, 1, this, false) as IItemHandlerModifiable
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("       ", "XXXXXXX", "X     X", "X     X", "X     X", "X     X", "X     X", "XXXXXXX")
        .aisle("       ", "X     X", "       ", " F   F ", "       ", "       ", "       ", "X  F  X")
        .aisle("       ", "X     X", "   C   ", "  FCF  ", "   C   ", "  CVC  ", "  CVC  ", "X BBB X")
        .aisle("   R   ", "X  D  X", "  COC  ", "  CQC  ", "  CGC  ", "  VGV  ", "  VGV  ", "XFBBBFX")
        .aisle("       ", "X     X", "   C   ", "  FCF  ", "   C   ", "  CSC  ", "  CVC  ", "X BBB X")
        .aisle("       ", "X     X", "       ", " F   F ", "       ", "       ", "       ", "X  F  X")
        .aisle("       ", "XXXXXXX", "X     X", "X     X", "X     X", "X     X", "X     X", "XXXXXXX")
        .where('S', selfPredicate())
        .where('X', states(casingState))
        .where('B', states(secondCasingState)
            .setMinGlobalLimited(4)
            .or(autoAbilities(true, true, false, true, false, true, true)))
        .where('C', states(secondCasingState))
        .where('D', states(thirdCasingState))
        .where('F', frames(HSLASteel))
        .where('G', states(gearboxCasingState))
        .where('V', states(fourthCasingState))
        .where('O', pistonCasings())
        .where('Q', motorCasings())
        .where('R', blockPredicate())
        .where(' ', any())
        .build()

    @ApiStatus.Internal
    private fun blockPredicate() = TraceabilityPredicate { blockWorldState: BlockWorldState ->
        targetBlock = blockWorldState.pos
        if (isStructureFormed)
        {
            (inputInventory as IItemHandlerModifiable).setStackInSlot(0,
                toItem((world as World).getBlockState(targetBlock)))
        }
        return@TraceabilityPredicate true
    }

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.SOLID_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("       ", "XXXXXXX", "X     X", "X     X", "X     X", "X     X", "X     X", "XXXXXXX")
            .aisle("       ", "X     X", "       ", " F   F ", "       ", "       ", "       ", "X  F  X")
            .aisle("       ", "X     X", "   C   ", "  FCF  ", "   C   ", "  CVC  ", "  CVC  ", "X BMB X")
            .aisle("   R   ", "X  D  X", "  COC  ", "  CQC  ", "  CGC  ", "  VGV  ", "  VGV  ", "XFJNLFX")
            .aisle("       ", "X     X", "   C   ", "  FCF  ", "   C   ", "  CSC  ", "  CVC  ", "X BEB X")
            .aisle("       ", "X     X", "       ", " F   F ", "       ", "       ", "       ", "X  F  X")
            .aisle("       ", "XXXXXXX", "X     X", "X     X", "X     X", "X     X", "X     X", "XXXXXXX")
            .where('S', GTLiteMetaTileEntities.BEDROCK_DRILLING_RIG, EnumFacing.SOUTH)
            .where('X', casingState)
            .where('B', secondCasingState)
            .where('C', secondCasingState)
            .where('D', thirdCasingState)
            .where('F', MetaBlocks.FRAMES[HSLASteel]!!.getBlock(HSLASteel))
            .where('G', gearboxCasingState)
            .where('V', fourthCasingState)
            .where('M', MetaTileEntities.MUFFLER_HATCH[LV], EnumFacing.UP)
            .where('N', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.UP)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.UP)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[ULV], EnumFacing.UP)
            .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[ULV], EnumFacing.UP)
        val count = AtomicInteger()
        StreamEx.of(pistonCasings)
            .map { b ->
                builder.where('O', b)
                builder.where('Q', motorCasings[count.get()])
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

    override fun addInformation(stack: ItemStack,
                                world: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.bedrock_drilling_rig.tooltip.6"))
    }

    override fun canBeDistinct() = false

    override fun hasMufflerMechanics() = true

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    private inner class BedrockDrillingRigWorkableHandler(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity, true)
    {

        override fun getMetaTileEntity() = super.getMetaTileEntity() as MetaTileEntityBedrockDrillingRig

        override fun setupAndConsumeRecipeInputs(recipe: Recipe, importInventory: IItemHandlerModifiable): Recipe?
        {
            if (!recipe.inputs[0].isNonConsumable)
            {
                val mte = getMetaTileEntity()
                Preconditions.checkNotNull(mte)
                (mte.world as World).destroyBlock(mte.targetBlock, false)
            }
            return super.setupAndConsumeRecipeInputs(recipe, importInventory)
        }

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow(motorCasingTier)).toInt())
        }

        override fun getParallelLimit(): Int = 16 * pistonCasingTier

    }

}
