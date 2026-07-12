package gregtechlite.gtlitecore.common.metatileentity.multiblock

import com.morphismmc.morphismlib.util.ItemUtil
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.ItemHandlerList
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
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder
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
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.capability.logic.ExtendableMultiblockRecipeLogic
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalMultiblockBase
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
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextFormatting
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
    override val controllerPos: BlockPos
        get() = pos

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

        private val structT2  = GTLiteMod.id("nanolithography_array")
        private val structT3  = GTLiteMod.id("microscale_circuit_detector")
        private val structOC  = GTLiteMod.id("water_cooling_tower")
        private val structOC2 = GTLiteMod.id("thermosink_cooling_tower")
        private val structBio = GTLiteMod.id("bio_cultivation_chamber")
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

    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.IRIDIUM_CASING

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.pcb_factory.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.laser_hatch"))
    }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(energyContainer)
            .addEnergyTierLine(getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progress, recipeMapWorkable.maxProgress)
            .addCustom { keyManager, syncer ->
                if (isStructureFormed)
                {
                    if (syncer.syncBoolean(structT2.checkStructure()))
                        keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.pcb_factory.structure.t2"))
                    if (syncer.syncBoolean(structT3.checkStructure()))
                        keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.pcb_factory.structure.t3"))
                    if (syncer.syncBoolean(structOC.checkStructure()))
                        keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.pcb_factory.structure.oc"))
                    if (syncer.syncBoolean(structOC2.checkStructure()))
                        keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.pcb_factory.structure.perfect_oc"))
                    if (syncer.syncBoolean(structBio.checkStructure()))
                        keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.pcb_factory.structure.bio"))
                }
            }
            .addRecipeOutputLine(recipeMapWorkable)
    }

    override fun canBeDistinct() = true

    override fun shouldShowVoidingModeButton() = true

    private fun ResourceLocation.checkStructure(): Boolean = additionalStructureManager[this].isNotEmpty()

    private fun ResourceLocation.getStructure(): AdditionalMultiblockBase<T> = additionalStructureManager[this][0]

    private fun ResourceLocation.isStructureEmpty(): Boolean = additionalStructureManager[this].isEmpty()

    private inner class PCBFactoryRecipeLogic(mte: RecipeMapExtendableMultiblock<T>)
        : ExtendableMultiblockRecipeLogic<T>(mte, additionalStructureManager)
    {
        private var hasWaterCooling: Boolean = false

        override fun getOverclockingDurationFactor(): Double
        {
            if (structOC.checkStructure())
            {
                if (structOC2.checkStructure()) return PERFECT_DURATION_FACTOR // 4/4
                return STD_DURATION_FACTOR // 4/2
            }
            return 1.0 // 1/1
        }

        override fun getOverclockingVoltageFactor(): Double
        {
            if (structOC.checkStructure() || structOC2.checkStructure())
                return super.getOverclockingVoltageFactor()
            return 1.0 // 1/1
        }

        override fun getParallelLimit(): Int
        {
            val targetNanite = OreDictUnifier.get(nanite, Silver)
            val targetAdvancedNanite = OreDictUnifier.get(nanite, Gold)

            var count = 0
            var countAdvanced = 0

            if (structT2.checkStructure())
            {
                val abilities = structT2.getStructure().getAbilities(IMPORT_ITEMS)
                val itemInputs = ItemHandlerList(abilities)
                for (i in 0 until itemInputs.slots)
                {
                    val currentStack = itemInputs.getStackInSlot(i)
                    if (ItemUtil.areItemTagsEqual(targetNanite, currentStack, false))
                        count = 2 * currentStack.count
                }
            }

            if (structT3.checkStructure())
            {
                val abilities = structT3.getStructure().getAbilities(IMPORT_ITEMS)
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
            if (structOC.isStructureEmpty()) return super.updateRecipeProgress()

            val abilities = structOC.getStructure().getAbilities(IMPORT_FLUIDS)
            if (canRecipeProgress && drawEnergy(recipeEUt, true))
            {
                val inputTank = abilities[0]
                val coolant = DistilledWater.getFluid(50)
                if (coolant.isFluidStackIdentical(inputTank.fluid))
                {
                    inputTank.drain(coolant.amount, true)
                    hasWaterCooling = true
                }

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
    }
}