package magicbook.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.properties.impl.CleanroomProperty
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.TooltipHelper
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockMultiblockCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BIO_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.GREENHOUSE_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.cleanroomCasings
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.sensorCasings
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockGlassCasing01
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

class MetaTileEntityLargeBioReactor(metaTileEntityId: ResourceLocation?) : MultiMapMultiblockController(metaTileEntityId, arrayOf(BIO_REACTOR_RECIPES, GREENHOUSE_RECIPES))
{

    private var sensorCasingTier = 0
    private var cleanroomCasingTier = 0

    init
    {
        recipeMapWorkable = LargeBioReactorRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var sensorCasings: List<IBlockState>
        private lateinit var cleanroomCasings: List<IBlockState>

        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_01.getState(BlockMetalCasing01.MetalCasingType.RED_STEEL)

        private val secondCasingState
            get() = MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)

        private val glassState
            get() = GTLiteMetaBlocks.TRANSPARENT_CASING_01.getState(BlockGlassCasing01.GlassType.GREENHOUSE)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val sensorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_SENSOR_CASING)
        val cleanroomCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_CLEANROOM_CASING)
        val maxLength = maxLength(listOf(sensorCasing, cleanroomCasing))
        sensorCasings = consistent(sensorCasing, maxLength)
        cleanroomCasings = consistent(cleanroomCasing, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MetaTileEntityLargeBioReactor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("SensorCasingTieredStats")
        val type2: Any? = context.get<Any>("CleanroomCasingTieredStats")
        sensorCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        cleanroomCasingTier = getOrDefault(
            { type2 is WrappedIntTier },
            { (type2 as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        sensorCasingTier = 0
        cleanroomCasingTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "CCCCC", "CCCCC", "     ")
        .aisle("CCCCC", "D###D", "D###D", "CCCCC")
        .aisle("CCCCC", "DT#TD", "D###D", "CFFFC")
        .aisle("CCCCC", "D###D", "D###D", "CCCCC")
        .aisle("CCSCC", "CGGGC", "CGGGC", "     ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(10)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('D', states(secondCasingState))
        .where('G', states(glassState))
        .where('T', sensorCasings())
        .where('F', cleanroomCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.RED_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle("ECCCC", "CCCCC", "CCCCC", "     ")
                .aisle("CCCCC", "D   D", "D   D", "CCCCC")
                .aisle("CCCCC", "DT TD", "D   D", "CFFFC")
                .aisle("CCCCC", "D   D", "D   D", "CCNCC")
                .aisle("IJSKL", "CGGGC", "CGGGC", "     ")
                .where('S', GTLiteMetaTileEntities.LARGE_BIO_REACTOR, EnumFacing.SOUTH)
                .where('C', casingState)
                .where('D', secondCasingState)
                .where('G', glassState)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
                .where('N', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH)
        val count = AtomicInteger()
        StreamEx.of(sensorCasings)
            .map { b ->
                builder.where('T', b)
                builder.where('F', cleanroomCasings[count.get()])
                count.getAndIncrement()
                builder
            }
            .nonNull()
            .forEach { b -> shapeInfo.add(b!!.build()) }
        return shapeInfo
    }

    override fun update()
    {
        super.update()
        if (world.isRemote)
        {
            if (sensorCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (cleanroomCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(sensorCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(cleanroomCasingTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            sensorCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            cleanroomCasingTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_bio_reactor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_bio_reactor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_bio_reactor.tooltip.3") + TooltipHelper.RAINBOW_SLOW + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.large_bio_reactor.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.large_bio_reactor.tooltip.5"))
    }

    override fun canBeDistinct() = true

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        if (getRecipeMap() === BIO_REACTOR_RECIPES)
        {
            return if (super.checkRecipe(recipe, consumeIfSuccess)
                && (recipe.getProperty(CleanroomProperty.getInstance(), CleanroomType.CLEANROOM)
                        !== CleanroomType.STERILE_CLEANROOM))
                true
            else if (super.checkRecipe(recipe, consumeIfSuccess)
                && (recipe.getProperty(CleanroomProperty.getInstance(), CleanroomType.CLEANROOM)
                        === CleanroomType.STERILE_CLEANROOM) && cleanroomCasingTier >= 2)
                true
            else
                false
        }
        else
        {
            return super.checkRecipe(recipe, consumeIfSuccess)
        }
    }

    private inner class LargeBioReactorRecipeLogic(metaTileEntity: RecipeMapMultiblockController) : MultiblockRecipeLogic(metaTileEntity)
    {
        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(sensorCasingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * getTierByVoltage(maxVoltage)

    }

}
