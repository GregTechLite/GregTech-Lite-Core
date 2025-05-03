package magicbook.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GregTechAPI
import gregtech.api.block.IHeatingCoilBlockStats
import gregtech.api.capability.IHeatingCoil
import gregtech.api.capability.impl.HeatingCoilRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.properties.impl.TemperatureProperty
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.TextComponentUtil.stringWithColor
import gregtech.api.util.TextComponentUtil.translationWithColor
import gregtech.api.util.TextFormattingUtil.formatNumbers
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockWireCoil
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.ALLOY_BLAST_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.pumpCasings
import magicbook.gtlitecore.api.utils.stream.LazyStreams
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockActiveUniqueCasing01
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing02
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.block.state.IBlockState
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import one.util.streamex.StreamEx
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.pow

@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityAlloyBlastSmelter(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, ALLOY_BLAST_RECIPES), IHeatingCoil
{

    private var pumpCasingTier = 0
    private var coilTier = 0
    private var temperature = 0

    init
    {
        recipeMapWorkable = AlloyBlastSmelterRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var pumpCasings: List<IBlockState>
        private lateinit var heatingCoils: List<IBlockState>

        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.ZIRCONIUM_CARBIDE)

        private val pipeCasingState
            get() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)

        private val uniqueCasingState
            get() = GTLiteMetaBlocks.ACTIVE_UNIQUE_CASING_01.getState(BlockActiveUniqueCasing01.UniqueCasingType.HEAT_VENT)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val pumpCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_PUMP_CASING)
        val heatingCoil = LazyStreams.fastSortedByKey(GregTechAPI.HEATING_COILS)
        val maxLength = maxLength(listOf(pumpCasing, heatingCoil))
        pumpCasings = consistent(pumpCasing, maxLength)
        heatingCoils = consistent(heatingCoil, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityAlloyBlastSmelter(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("PumpCasingTieredStats")
        val type2: Any? = context.get<Any>("CoilType")
        pumpCasingTier = getOrDefault(
            { type1 is WrappedIntTier },
            { (type1 as WrappedIntTier).getIntTier() }, 0)
        if (type2 is IHeatingCoilBlockStats)
        {
            temperature = type2.coilTemperature
            coilTier = type2.tier
        }
        else
        {
            temperature = BlockWireCoil.CoilType.CUPRONICKEL.coilTemperature
            coilTier = BlockWireCoil.CoilType.CUPRONICKEL.tier
        }
        temperature += 100 * max(0, getTierByVoltage(getEnergyContainer().inputVoltage) - MV)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        pumpCasingTier = 0
        temperature = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" CCC ", " HHH ", " UUU ", " HHH ", " CCC ")
        .aisle("CCCCC", "H###H", "U###U", "H###H", "CCCCC")
        .aisle("CCCCC", "H#P#H", "U#Q#U", "H#P#H", "CCOCC")
        .aisle("CCCCC", "H###H", "U###U", "H###H", "CCCCC")
        .aisle(" CSC ", " HHH ", " UUU ", " HHH ", " CCC ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(10)
            .or(autoAbilities(true, true, true, false, true, true, false)))
        .where('Q', states(pipeCasingState))
        .where('U', states(uniqueCasingState))
        .where('O', abilities(MUFFLER_HATCH))
        .where('H', heatingCoils())
        .where('P', pumpCasings())
        .where('#', air())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.ZIRCONIUM_CARBIDE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteTextures.ALLOY_BLAST_SMELTER_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo: MutableList<MultiblockShapeInfo> = ArrayList()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
                .aisle(" CEC ", " HHH ", " UUU ", " HHH ", " CCC ")
                .aisle("CCCCC", "H   H", "U   U", "H   H", "CCCCC")
                .aisle("CCCCC", "H P H", "U Q U", "H P H", "CCMCC")
                .aisle("KCCCK", "H   H", "U   U", "H   H", "CCCCC")
                .aisle(" ISL ", " HHH ", " UUU ", " HHH ", " CNC ")
                .where('S', GTLiteMetaTileEntities.ALLOY_BLAST_SMELTER, EnumFacing.SOUTH)
                .where('C', casingState)
                .where('Q', pipeCasingState)
                .where('U', uniqueCasingState)
                .where('M', MetaTileEntities.MUFFLER_HATCH[LV], EnumFacing.UP)
                .where('N', { if (ConfigHolder.machines.enableMaintenance) MetaTileEntities.MAINTENANCE_HATCH else casingState }, EnumFacing.SOUTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[0], EnumFacing.NORTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.SOUTH)
                .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.SOUTH)
                .where('L', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.SOUTH)
        val count = AtomicInteger()
        StreamEx.of(pumpCasings)
            .map { b ->
                if (builder != null)
                {
                    builder.where('P', b)
                    builder.where('H', heatingCoils[count.get()])
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
            if (pumpCasingTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_TIERED_MACHINE) { _: PacketBuffer? -> }
            if (coilTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(pumpCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(coilTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            pumpCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            coilTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.alloy_blast_smelter.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.alloy_blast_smelter.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.alloy_blast_smelter.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.alloy_blast_smelter.tooltip.4"))
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.1"))
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.2"))
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.3"))
    }

    override fun addDisplayText(textList: List<ITextComponent>)
    {
        MultiblockDisplayText.builder(textList, isStructureFormed)
            .setWorkingStatus(recipeMapWorkable.isWorkingEnabled,
                recipeMapWorkable.isActive)
            .addEnergyUsageLine(energyContainer)
            .addEnergyTierLine(getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
            .addCustom { tl ->
                if (isStructureFormed)
                {
                    val temperatureInfo = stringWithColor(TextFormatting.RED,
                        formatNumbers(temperature))
                    tl.add(translationWithColor(TextFormatting.GRAY,
                        "gregtech.multiblock.blast_furnace.max_temperature", temperatureInfo) as ITextComponent)
                }
            }
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progressPercent)
    }

    override fun getDataInfo(): List<ITextComponent>
    {
        val textList = super.getDataInfo()
        val temperatureInfo = translationWithColor(TextFormatting.RED,
            formatNumbers(temperature.toLong()) + " K")
        textList.add(translationWithColor(TextFormatting.GRAY,
            "gregtech.multiblock.blast_furnace.max_temperature", temperatureInfo) as ITextComponent)
        return textList
    }

    override fun canBeDistinct() = true

    override fun hasMufflerMechanics() = true

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
        = temperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0)!!

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    override fun getCurrentTemperature(): Int = temperature

    inner class AlloyBlastSmelterRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : HeatingCoilRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor(): Double = if (maxVoltage >= GTValues.V[GTValues.UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow(coilTier)).toInt())
        }

        override fun getParallelLimit(): Int = 16 * pumpCasingTier

    }

}
