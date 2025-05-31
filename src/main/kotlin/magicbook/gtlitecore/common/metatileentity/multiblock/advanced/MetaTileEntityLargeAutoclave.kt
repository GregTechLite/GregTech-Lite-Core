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
import gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.VACUUM_CHAMBER_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.pistonCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.pumpCasings
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
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

class MetaTileEntityLargeAutoclave(metaTileEntityId: ResourceLocation?) : MultiMapMultiblockController(metaTileEntityId, arrayOf(AUTOCLAVE_RECIPES, VACUUM_CHAMBER_RECIPES))
{

    private var pumpCasingTier = 0
    private var pistonCasingTier = 0

    init
    {
        recipeMapWorkable = LargeAutoclaveRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var pumpCasings: List<IBlockState>
        private lateinit var pistonCasings: List<IBlockState>

        private val casingState
            get() = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST)

        private val pipeCasingState
            get() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)

        private val glassState
            get() = MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val pumpCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PUMP_CASING)
        val pistonCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PISTON_CASING)
        val maxLength = maxLength(listOf(pumpCasing, pistonCasing))
        pumpCasings = consistent(pumpCasing, maxLength)
        pistonCasings = consistent(pistonCasing, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityLargeAutoclave(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("PumpCasingTieredStats")
        val type2: Any? = context.get<Any>("PistonCasingTieredStats")
        pumpCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        pistonCasingTier = getOrDefault(
            { type2 is WrappedIntTier },
            { (type2 as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pumpCasingTier = 0
        pistonCasingTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " CGC ", " CGC ", " CGC ", " CCC ")
        .aisle("CCCCC", "C###C", "C###C", "C###C", "CCCCC")
        .aisle("CCQCC", "G#B#G", "G#P#G", "G#B#G", "CCCCC")
        .aisle("CCCCC", "C###C", "C###C", "C###C", "CCCCC")
        .aisle(" CCC ", " CSC ", " CGC ", " CGC ", " CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(12)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('B', states(pipeCasingState))
        .where('G', states(glassState))
        .where('P', pumpCasings())
        .where('Q', pistonCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.ROBUST_TUNGSTENSTEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_AUTOCLAVE_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle(" CEC ", " CGC ", " CGC ", " CGC ", " CCC ")
            .aisle("CCCCC", "C   C", "C   C", "C   C", "CCCCC")
            .aisle("CCQCC", "G B G", "G P G", "G B G", "CCCCC")
            .aisle("CCCCC", "C   C", "C   C", "C   C", "CCCCC")
            .aisle(" KNL ", " ISJ ", " CGC ", " CGC ", " CCC ")
            .where('S', GTLiteMetaTileEntities.LARGE_AUTOCLAVE, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('B', pipeCasingState)
            .where('G', glassState)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
            .where('N', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
            .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH)
        val count = AtomicInteger()
        StreamEx.of(pumpCasings)
            .map { b ->
                builder.where('P', b)
                builder.where('Q', pistonCasings[count.get()])
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
            if (pumpCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (pistonCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(pumpCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(pistonCasingTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            pumpCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            pistonCasingTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_autoclave.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_autoclave.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_autoclave.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_autoclave.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class LargeAutoclaveRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(pumpCasingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * pistonCasingTier

    }

}
