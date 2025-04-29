package magicbook.gtlitecore.common.metatileentity.multiblock.advanced

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
import gregtech.api.recipes.RecipeMaps.COMPRESSOR_RECIPES
import gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.pistonCasings
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.function.Consumer
import kotlin.math.floor
import kotlin.math.pow

@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityLargeForgeHammer(metaTileEntityId: ResourceLocation?) : MultiMapMultiblockController(metaTileEntityId, arrayOf(FORGE_HAMMER_RECIPES, COMPRESSOR_RECIPES))
{

    private var casingTier = 0

    init
    {
        recipeMapWorkable = LargeForgeHammerRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityLargeForgeHammer(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type: Any? = context.get<Any>("PistonCasingTieredStats")
        casingTier = getOrDefault(
            { type is WrappedIntTier },
            { (type as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        casingTier = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("SCC", "C#C", "CPC", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(2)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('P', pistonCasings())
        .where('#', air())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.SOLID_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.FORGE_HAMMER_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo: MutableList<MultiblockShapeInfo> = ArrayList()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("SME", "I J", "KPL", "CCC")
            .where('S', GTLiteMetaTileEntities.LARGE_FORGE_HAMMER, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('M', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.SOUTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[ULV], EnumFacing.SOUTH)
            .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[ULV], EnumFacing.SOUTH)
        GTLiteAPI.MAP_PISTON_CASING.entries
            .sortedBy { entry -> (entry.value as WrappedIntTier).getIntTier() }
            .forEach(Consumer { entry -> shapeInfo.add(builder.where('P', entry.key).build()) })
        return shapeInfo
    }

    override fun update()
    {
        super.update()
        if ((world as World).isRemote && casingTier == 0)
            writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(casingTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            casingTier = buf.readInt()
    }

    override fun writeInitialSyncData(buf: PacketBuffer)
    {
        super.writeInitialSyncData(buf)
        buf.writeInt(casingTier)
    }

    override fun receiveInitialSyncData(buf: PacketBuffer)
    {
        super.receiveInitialSyncData(buf)
        casingTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack,
                                player: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_forge_hammer.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_forge_hammer.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_forge_hammer.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_forge_hammer.tooltip.4"))
    }

    override fun canBeDistinct() = true

    inner class LargeForgeHammerRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor(): Double = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow(casingTier)).toInt())
        }

        override fun getParallelLimit() = 4 * casingTier

    }

}
