package magicbook.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
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
import gregtech.api.recipes.RecipeMaps.BLAST_RECIPES
import gregtech.api.recipes.properties.impl.TemperatureProperty
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.TextComponentUtil.stringWithColor
import gregtech.api.util.TextComponentUtil.translationWithColor
import gregtech.api.util.TextFormattingUtil.formatNumbers
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.ConfigHolder
import gregtech.common.blocks.BlockWireCoil
import gregtech.common.metatileentities.MetaTileEntities
import gregtech.core.sound.GTSoundEvents
import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.block.impl.WrappedIntTier
import magicbook.gtlitecore.api.capability.GTLiteDataCodes
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BlazingPyrotheum
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.consistent
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.getOrDefault
import magicbook.gtlitecore.api.utils.GTLiteUtility.Companion.maxLength
import magicbook.gtlitecore.api.utils.StructureUtility.Companion.motorCasings
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
import net.minecraft.util.SoundEvent
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import one.util.streamex.StreamEx
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.pow

class MetaTileEntityVolcanus(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, BLAST_RECIPES), IHeatingCoil
{

    private var motorCasingTier = 0
    private var coilTier = 0
    private var temperature = 0

    init
    {
        recipeMapWorkable = VolcanusRecipeLogic(this)
        registerCasingMaps()
    }

    companion object
    {
        private var hasRegistered = false
        private lateinit var motorCasings: List<IBlockState>
        private lateinit var heatingCoils: List<IBlockState>

        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_02.getState(BlockMetalCasing02.MetalCasingType.HASTELLOY_C276)
    }

    private fun registerCasingMaps()
    {
        if (hasRegistered) return
        val motorCasing = LazyStreams.fastSortedByKey(GTLiteAPI.MAP_MOTOR_CASING)
        val heatingCoil = LazyStreams.fastSortedByKey(GregTechAPI.HEATING_COILS)
        val maxLength = maxLength(listOf(motorCasing, heatingCoil))
        motorCasings = consistent(motorCasing, maxLength)
        heatingCoils = consistent(heatingCoil, maxLength)
        hasRegistered = true
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityVolcanus(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        val type1: Any? = context.get<Any>("MotorCasingTieredStats")
        val type2: Any? = context.get<Any>("CoilType")
        motorCasingTier = getOrDefault(
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
        motorCasingTier = 0
        coilTier = 0
        temperature = 0
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("DDD", "CCC", "CCC", "DDD")
        .aisle("DDD", "CMC", "CMC", "DOD")
        .aisle("DSD", "CCC", "CCC", "DDD")
        .where('S', selfPredicate())
        .where('D', states(casingState)
            .setMinGlobalLimited(9)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('O', abilities(MUFFLER_HATCH))
        .where('M', motorCasings())
        .where('C', heatingCoils())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.HASTELLOY_C276_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.BLAST_FURNACE_OVERLAY

    override fun getMatchingShapes(): List<MultiblockShapeInfo>
    {
        val shapeInfo = ArrayList<MultiblockShapeInfo>()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("DED", "CCC", "CCC", "DDD")
            .aisle("DDD", "CMC", "CMC", "DOD")
            .aisle("ISJ", "CCC", "CCC", "KNL")
            .where('S', GTLiteMetaTileEntities.VOLCANUS, EnumFacing.SOUTH)
            .where('D', casingState)
            .where('O', MetaTileEntities.MUFFLER_HATCH[LV], EnumFacing.UP)
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
                builder.where('C', heatingCoils[count.get()])
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
            if (coilTier == 0)
                writeCustomData(GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE) { _: PacketBuffer? -> }
        }
    }

    override fun receiveCustomData(dataId: Int, buf: PacketBuffer)
    {
        super.receiveCustomData(dataId, buf)
        if (dataId == GTLiteDataCodes.INITIALIZE_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(motorCasingTier) }
        if (dataId == GTLiteDataCodes.INITIALIZE_SUB_TIERED_MACHINE)
            writeCustomData(GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE) { b: PacketBuffer -> b.writeInt(coilTier) }
        if (dataId == GTLiteDataCodes.UPDATE_TIERED_MACHINE)
            motorCasingTier = buf.readInt()
        if (dataId == GTLiteDataCodes.UPDATE_SUB_TIERED_MACHINE)
            coilTier = buf.readInt()
    }

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.volcanus.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.volcanus.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.volcanus.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.volcanus.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.volcanus.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.volcanus.tooltip.6"))
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.1"))
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.2"))
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.3"))
    }

    override fun addDisplayText(textList: List<ITextComponent>)
    {
        MultiblockDisplayText.builder(textList, isStructureFormed)
            .setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(energyContainer)
            .addEnergyTierLine(getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
            .addCustom { tl ->
                if (isStructureFormed)
                {
                    val temperatureInfo = stringWithColor(TextFormatting.RED,
                        formatNumbers(temperature))
                    tl.add(translationWithColor(TextFormatting.GRAY,
                        "gregtech.multiblock.blast_furnace.max_temperature", temperatureInfo))

                    // TODO Impl of IProgressBarMultiblock to replaced it.
                    if (getInputFluidInventory() != null)
                    {
                        val pyrotheumStack: FluidStack? = getInputFluidInventory().drain(BlazingPyrotheum.getFluid(Int.MAX_VALUE), false)
                        val pyrotheumAmount = pyrotheumStack?.amount ?: 0
                        val amountInfo = stringWithColor(TextFormatting.GREEN,
                            formatNumbers(pyrotheumAmount) + " L")

                        tl.add(translationWithColor(TextFormatting.GRAY,
                            "gtlitecore.machine.volcanus.pyrotheum_amount", amountInfo))
                    }
                }
            }
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progressPercent)
    }

    override fun addWarningText(textList: List<ITextComponent>)
    {
        MultiblockDisplayText.builder(textList, isStructureFormed, false)
            .addCustom { tl ->
                if (isStructureFormed)
                {
                    val pyrotheumStack: FluidStack? = getInputFluidInventory().drain(BlazingPyrotheum.getFluid(Int.MAX_VALUE), false)
                    if (pyrotheumStack == null || pyrotheumStack.amount == 0)
                    {
                        val warnInfo = translationWithColor(TextFormatting.YELLOW,
                            "gtlitecore.machine.volcanus.pyrotheum_warning")
                        tl.add(warnInfo)
                    }
                }
            }
    }

    override fun getDataInfo(): List<ITextComponent>
    {
        val textList = super.getDataInfo()
        val temperatureInfo = translationWithColor(TextFormatting.RED,
            formatNumbers(temperature) + " K")

        textList.add(translationWithColor(TextFormatting.GRAY,
            "gregtech.multiblock.blast_furnace.max_temperature", temperatureInfo))
        return textList
    }

    override fun canBeDistinct() = true

    override fun hasMufflerMechanics() = true

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    override fun getCurrentTemperature() = temperature

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
        = temperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0)!!

    private inner class VolcanusRecipeLogic(metaTileEntity: RecipeMapMultiblockController) : HeatingCoilRecipeLogic(metaTileEntity)
    {

        private val mte = metaTileEntity as MetaTileEntityVolcanus

        override fun updateRecipeProgress()
        {
            if (canRecipeProgress && drawEnergy(recipeEUt, true))
            {
                val inputTank = mte.getInputFluidInventory()
                val pyrotheumStack = BlazingPyrotheum.getFluid(2)
                if (pyrotheumStack.isFluidStackIdentical(inputTank.drain(pyrotheumStack, false)))
                {
                    inputTank.drain(pyrotheumStack, true)
                    if (++progressTime > maxProgressTime)
                        completeRecipe()
                }
                else
                {
                    return
                }
                drawEnergy(recipeEUt, false)
            }
        }

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.8.pow(motorCasingTier)).toInt())
        }

        override fun getParallelLimit() =  16 * coilTier

    }

}
