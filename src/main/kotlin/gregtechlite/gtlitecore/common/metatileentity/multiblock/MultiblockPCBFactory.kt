package gregtechlite.gtlitecore.common.metatileentity.multiblock

import com.morphismmc.morphismlib.util.ItemUtil
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.ItemHandlerList
import gregtech.api.gui.Widget
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_INPUT_ENERGY
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Silver
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.capability.logic.ExtendableMultiblockRecipeLogic
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.RecipeMapExtendableMultiblock
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.PCB_FACTORY_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix.nanite
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTCleanroomCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.metatileentity.multiblock.module.MultiblockBioCultivationChamber
import gregtechlite.gtlitecore.common.metatileentity.multiblock.module.MultiblockMicroscaleCircuitDetector
import gregtechlite.gtlitecore.common.metatileentity.multiblock.module.MultiblockNanolithographyArray
import gregtechlite.gtlitecore.common.metatileentity.multiblock.module.MultiblockThermosinkCoolingTower
import gregtechlite.gtlitecore.common.metatileentity.multiblock.module.MultiblockWaterCoolingTower
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.MathHelper.clamp
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max
import kotlin.math.min

/**
 * Additional Structures:
 * - T2: [MultiblockNanolithographyArray]
 * - T3: [MultiblockMicroscaleCircuitDetector]
 * - Cooling Upgrade: [MultiblockWaterCoolingTower]
 * - Advanced Cooling Upgrade: [MultiblockThermosinkCoolingTower]
 * - Bio Chamber Upgrade: [MultiblockBioCultivationChamber]
 */
class MultiblockPCBFactory<T : MultiblockPCBFactory<T>>(id: ResourceLocation)
    : RecipeMapExtendableMultiblock<T>(id, PCB_FACTORY_RECIPES)
{
    // Trace size to modify durations and OC params,  default: 100μm, range: 25~200μm.
    private var traceSize = 100
    private val minTraceSize = 25
    private val maxTraceSize = 200

    init
    {
        recipeMapWorkable = PCBFactoryRecipeLogic(this)
    }

    companion object
    {
        private val firstCasingState = MetalCasing.IRIDIUM.state
        private val secondCasingState = GTCleanroomCasing.PLASCRETE.state
        private val thirdCasingState = GTMultiblockCasing.GRATE_CASING.state
        private val fourthCasingState = MultiblockCasing.SUBSTRATE_CASING.state

        private val glassState= GTGlassCasing.LAMINATED_GLASS.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockPCBFactory(metaTileEntityId)

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(SUBSTATION_INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        energyContainer = EnergyContainerList(inputEnergy)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("FCCCCCF", "FCCCCCF", "FCCCCCF", "FCCCCCF", "F     F", "       ")
        .aisle("CcccccC", "C#####C", "C#####C", "C#####C", "CCCCCCC", "F     F")
        .aisle("CcccccC", "D#XXX#D", "D#####D", "C#####C", "CCCCCCC", "F     F")
        .aisle("CcccccC", "D#XXX#D", "D#####D", "C#####C", "CCCCCCC", "FFFFFFF")
        .aisle("CcccccC", "D#XXX#D", "D#####D", "C#####C", "CGGGGGC", "F     F")
        .aisle("CcccccC", "C#####C", "C#####C", "C#####C", "CGGGGGC", "F     F")
        .aisle("FCCSCCF", "FGGGGGF", "FGGGGGF", "FGGGGGF", "FFFFFFF", "       ")
        .where('S', selfPredicate())
        .where('C', states(firstCasingState)
            .setMinGlobalLimited(40)
            .or(abilities(MAINTENANCE_HATCH)
                    .setExactLimit(1))
            .or(abilities(INPUT_ENERGY)
                    .setMaxGlobalLimited(2))
            .or(abilities(INPUT_LASER)
                    .setMaxGlobalLimited(1))
            .or(abilities(IMPORT_ITEMS)
                    .setPreviewCount(1))
            .or(abilities(EXPORT_ITEMS)
                    .setPreviewCount(1))
            .or(abilities(IMPORT_FLUIDS)
                    .setPreviewCount(1)))
        .where('c', states(secondCasingState))
        .where('D', states(thirdCasingState))
        .where('F', frames(HSLASteel))
        .where('G', states(glassState))
        .where('X', states(fourthCasingState))
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    // TODO
    /*override fun getFlexButton(x: Int, y: Int, width: Int, height: Int): Widget
    {
        val group = WidgetGroup(x, y, width, height)
        group.addWidget(ClickButtonWidget(0, 0, 9, 18, "", this::decrementTraceSize)
            .setButtonTexture(GuiTextures.BUTTON_THROTTLE_MINUS)
            .setTooltipText("gtlitecore.machine.pcb_factory.trace_size.decrement"))
        group.addWidget(ClickButtonWidget(9, 0, 9, 18, "", this::incrementTraceSize)
            .setButtonTexture(GuiTextures.BUTTON_THROTTLE_PLUS)
            .setTooltipText("gtlitecore.machine.pcb_factory.trace_size.increment"))
        return group
    }*/

    // override fun writeToNBT(data: NBTTagCompound): NBTTagCompound
    // {
    //     data.setInteger("TraceSize", traceSize)
    //     return super.writeToNBT(data)
    // }
//
    // override fun readFromNBT(data: NBTTagCompound)
    // {
    //     traceSize = data.getInteger("TraceSize")
    //     super.readFromNBT(data)
    // }
//
    // override fun writeInitialSyncData(buf: PacketBuffer)
    // {
    //     super.writeInitialSyncData(buf)
    //     buf.writeVarInt(traceSize)
    // }
//
    // override fun receiveInitialSyncData(buf: PacketBuffer)
    // {
    //     super.receiveInitialSyncData(buf)
    //     traceSize = buf.readVarInt()
    // }

    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.IRIDIUM_CASING

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.7"))
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.laser_hatch"))
    }

    // TODO
    /*override fun addDisplayText(textList: MutableList<ITextComponent>?)
    {
        MultiblockDisplayText.builder(textList, isStructureFormed)
            .addCustom { tl ->
                if (isStructureFormed)
                {
                    // Main structure tier (trace size)
                    tl.add(translationWithColor(TextFormatting.GRAY,
                        "gtlitecore.machine.pcb_factory.structure_info",
                        mainUpgradeNumber, traceSize) as ITextComponent)
                    // Cooling structure tier
                    if (coolingUpgradeNumber > 0)
                    {
                        tl.add(translationWithColor(TextFormatting.AQUA,
                            "gtlitecore.machine.pcb_factory.structure_cooling"))
                        if (coolingUpgradeNumber == 2)
                            tl.add(translationWithColor(TextFormatting.BLUE,
                                "gtlitecore.machine.pcb_factory.structure_thermosink"))
                    }
                    // Auxiliary structure tier
                    if (auxiliaryUpgradeNumber == 1)
                        tl.add(translationWithColor(TextFormatting.GREEN,
                            "gtlitecore.machine.pcb_factory.structure_bio_chamber"))
                }
            }
            .setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(recipeMapWorkable.energyContainer)
            .addEnergyTierLine(getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progressPercent)
    }*/

    override fun canBeDistinct() = true

    override fun shouldShowVoidingModeButton() = true

    private fun incrementTraceSize(clickData: Widget.ClickData)
    {
        traceSize = clamp(traceSize - 25, minTraceSize, maxTraceSize)
    }

    private fun decrementTraceSize(clickData: Widget.ClickData)
    {
        traceSize = clamp(traceSize + 25, minTraceSize, maxTraceSize)
    }

    private inner class PCBFactoryRecipeLogic(mte: RecipeMapExtendableMultiblock<T>) : ExtendableMultiblockRecipeLogic<T>(mte, additionalStructureManager)
    {
        private var hasWaterCooling: Boolean = false

        override fun getOverclockingDurationFactor(): Double
        {
            if (additionalStructureManager.get(GTLiteMod.id("water_cooling_tower")).isNotEmpty()) // 4/2
            {
                if (additionalStructureManager.get(GTLiteMod.id("thermosink_cooling_tower")).isNotEmpty()) // 4/4
                    return PERFECT_DURATION_FACTOR
                return STD_DURATION_FACTOR
            }
            return 1.0 // 1/1
        }

        override fun getOverclockingVoltageFactor(): Double
        {
            if (additionalStructureManager.get(GTLiteMod.id("water_cooling_tower")).isNotEmpty()
                || additionalStructureManager.get(GTLiteMod.id("thermosink_cooling_tower")).isNotEmpty())
                return super.getOverclockingVoltageFactor()
            return 1.0 // 1/1
        }

        override fun getParallelLimit(): Int
        {
            val targetNanite = OreDictUnifier.get(nanite, Silver)
            val targetAdvancedNanite = OreDictUnifier.get(nanite, Gold)

            var count = 0
            var countAdvanced = 0

            if (additionalStructureManager.get(GTLiteMod.id("nanolithography_array")).isNotEmpty())
            {
                val abilities = additionalStructureManager.get(GTLiteMod.id("nanolithography_array"))[0].getAbilities(IMPORT_ITEMS)
                val itemInputs = ItemHandlerList(abilities)
                for (i in 0 until itemInputs.slots)
                {
                    val currentStack = itemInputs.getStackInSlot(i)
                    if (ItemUtil.areItemTagsEqual(targetNanite, currentStack, false))
                        count = 2 * currentStack.count
                }
            }

            if (additionalStructureManager.get(GTLiteMod.id("microscale_circuit_detector")).isNotEmpty())
            {
                val abilities = additionalStructureManager.get(GTLiteMod.id("microscale_circuit_detector"))[0].getAbilities(IMPORT_ITEMS)
                val itemInputs = ItemHandlerList(abilities)
                for (i in 0 until itemInputs.slots)
                {
                    val currentStack = itemInputs.getStackInSlot(i)
                    if (ItemUtil.areItemTagsEqual(targetAdvancedNanite, currentStack, false))
                        countAdvanced = 4 * currentStack.count
                }
            }
            return min(count + countAdvanced, Int.MAX_VALUE - 1) // I think it's safe... may some edge case will break this?
        }

        override fun updateRecipeProgress()
        {
            if (additionalStructureManager.get(GTLiteMod.id("water_cooling_tower")).isEmpty())
                return super.updateRecipeProgress()

            val abilities = additionalStructureManager.get(GTLiteMod.id("water_cooling_tower"))[0].getAbilities(IMPORT_FLUIDS)
            if (canRecipeProgress && drawEnergy(recipeEUt, true))
            {
                val inputTank = abilities[0]
                val coolant = DistilledWater.getFluid(50)
                // Water cooling
                if (coolant.isFluidStackIdentical(inputTank.fluid))
                {
                    inputTank.drain(coolant.amount, true)
                    hasWaterCooling = true
                }

                // TODO: Trace Size

                if (++progressTime > maxProgressTime)
                    completeRecipe()

                if (hasNotEnoughEnergy && energyInputPerSecond > ((SECOND - 1) * recipeEUt))
                    hasNotEnoughEnergy = false
            }
            else if (recipeEUt > 0)
            {
                hasNotEnoughEnergy = true
                decreaseProgress()
            }
        }

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)
            // +400% | D' = D / (1 + 4.0) = D / 5.0
            if (hasWaterCooling)
            {
                ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / 5.0).toInt()))
            }
        }

        // override fun setMaxProgress(maxProgress: Int)
        // {
        //     maxProgressTime = when (traceSize)
        //     {
        //         25 -> floor(0.4 * maxProgress).toInt()
        //         50 -> floor(0.6 * maxProgress).toInt()
        //         75 -> floor(0.8 * maxProgress).toInt()
        //         125 -> floor(1.2 * maxProgress).toInt()
        //         150 -> floor(1.4 * maxProgress).toInt()
        //         175 -> floor(1.6 * maxProgress).toInt()
        //         200 -> floor(1.8 * maxProgress).toInt()
        //         else -> maxProgress
        //     }
        // }
//
        // override fun updateRecipeProgress()
        // {
        //     val traceSizeFactor = when (traceSize)
        //     {
        //         25 -> 2.5
        //         50 -> 2
        //         75 -> 1.5
        //         125 -> 0.9
        //         150 -> 0.8
        //         175 -> 0.7
        //         200 -> 0.6
        //         else -> 1
        //     }
        //     val actuallyEnergyConsumed: Int = recipeEUt.toInt() * traceSizeFactor.toInt()
        //     if (canRecipeProgress && drawEnergy(actuallyEnergyConsumed.toLong(), true))
        //     {
        //         drawEnergy(actuallyEnergyConsumed.toLong(), false)
        //         if (++progressTime > maxProgressTime)
        //             completeRecipe()
        //         if (hasNotEnoughEnergy && energyInputPerSecond > 19L * actuallyEnergyConsumed.toLong())
        //             hasNotEnoughEnergy = false
        //     }
        //     else if (actuallyEnergyConsumed > 0)
        //     {
        //         hasNotEnoughEnergy = true
        //         decreaseProgress()
        //     }
        // }
    }

}