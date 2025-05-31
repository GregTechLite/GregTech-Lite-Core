package magicbook.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockFireboxCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BURNER_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ROASTER_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.IncoloyMA813
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.motorCasings
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
import net.minecraftforge.common.property.IExtendedBlockState
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MetaTileEntityLargeBurnerReactor(metaTileEntityId: ResourceLocation?) : MultiMapMultiblockController(metaTileEntityId, arrayOf(BURNER_REACTOR_RECIPES, ROASTER_RECIPES))
{

    private var casingTier = 0


    init
    {
        recipeMapWorkable = LargeBurnerReactorRecipeLogic(this)
    }

    companion object
    {
        private val casingState: IBlockState?
            get() = GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.INCOLOY_MA813)

        private val pipeCasingState: IBlockState?
            get() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE)

        private val fireboxCasingState: IBlockState?
            get() = MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.TITANIUM_FIREBOX)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MetaTileEntityLargeBurnerReactor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type: Any? = context.get<Any>("MotorCasingTieredStats")
        casingTier = getOrDefault(
            { type is WrappedIntTier },
            { (type as WrappedIntTier).getIntTier() }, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        casingTier = 0
        replaceFireboxAsActive(false)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("     ", "     ", " P P ", " P P ", " P P ")
        .aisle("F   F", "FBBBF", "XPXPX", "XXXXX", " P P ")
        .aisle("     ", "XBBBX", "XPNPX", "XPMPX", " P P ")
        .aisle("F   F", "FBBBF", "XXSXX", "XXXXX", "     ")
        .where('S', selfPredicate())
        .where('X', states(casingState)
            .setMinGlobalLimited(14)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('P', states(pipeCasingState))
        .where('B', states(fireboxCasingState))
        .where('F', frames(IncoloyMA813))
        .where('M', abilities(MUFFLER_HATCH))
        .where('N', motorCasings())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.INCOLOY_MA813_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.LARGE_BURNER_REACTOR_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("     ", "     ", " P P ", " P P ", " P P ")
            .aisle("F   F", "FBBBF", "XPEPX", "XXXXX", " P P ")
            .aisle("     ", "XBBBX", "XPNPX", "XPOPX", " P P ")
            .aisle("F   F", "FBBBF", "XISJX", "XKMLX", "     ")
            .where('S', GTLiteMetaTileEntities.LARGE_BURNER_REACTOR, EnumFacing.SOUTH)
            .where('X', casingState)
            .where('P', pipeCasingState)
            .where('B', fireboxCasingState)
            .where('F', MetaBlocks.FRAMES[IncoloyMA813]!!.getBlock(IncoloyMA813))
            .where('O', MetaTileEntities.MUFFLER_HATCH[1], EnumFacing.UP)
            .where('M', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.SOUTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[0], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
            .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH)
        GTLiteAPI.MAP_MOTOR_CASING.entries
            .sortedBy { entry -> (entry.value as WrappedIntTier).getIntTier() }
            .forEach { entry -> shapeInfo.add(builder.where('N', entry.key).build()) }
        return shapeInfo
    }

    override fun update()
    {
        super.update()
        if (world.isRemote && casingTier == 0)
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

    override fun onRemoval()
    {
        super.onRemoval()
        if (!world.isRemote && isStructureFormed)
        {
            // Replaced Firebox Casing textures as active textures.
            replaceFireboxAsActive(false)
        }
    }

    fun replaceFireboxAsActive(isActive: Boolean)
    {
        val centerPos = pos.offset(getFrontFacing().opposite).down()
        for (x in -1..1)
        {
            for (z in -1..1)
            {
                val blockPos = centerPos.add(x, 0, z)
                var blockState = world.getBlockState(blockPos)
                if (blockState.getBlock() is BlockFireboxCasing)
                {
                    blockState = (blockState as IExtendedBlockState).withProperty(BlockFireboxCasing.ACTIVE, isActive)
                    world.setBlockState(blockPos, blockState)
                }
            }
        }
    }

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.large_burner_reactor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.large_burner_reactor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.large_burner_reactor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.large_burner_reactor.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class LargeBurnerReactorRecipeLogic(metaTileEntity: RecipeMapMultiblockController) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(getTierByVoltage(maxVoltage).toDouble()))).toInt())
        }

        override fun getParallelLimit() = 16 * casingTier

    }

}
