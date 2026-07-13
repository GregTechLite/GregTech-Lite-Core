package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.capability.impl.EnergyContainerList
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
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.logic.OverclockingLogic.STD_DURATION_FACTOR
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.GTLiteMod
import gregtechlite.gtlitecore.api.capability.logic.ExtendedPowerExtendableMultiblockRecipeLogic
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalMultiblockBase
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.RecipeMapExtendableMultiblock
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.NANO_FORGE_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HastelloyN
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockNanoForge<T : MultiblockNanoForge<T>>(id: ResourceLocation) : RecipeMapExtendableMultiblock<T>(id, NANO_FORGE_RECIPES)
{
    override val controllerPos: BlockPos
        get() = pos

    init
    {
        recipeMapWorkable = NanoForgeRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.NAQUADAH_ALLOY.state

        private val structT2 = GTLiteMod.id("consciousness_storage_center")
        private val structT3 = GTLiteMod.id("nanite_replication_unrestricor")
        private val structT4 = GTLiteMod.id("virtual_gestalt_computing_uplink")
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockNanoForge(metaTileEntityId)

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
        .aisle("         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "    D    ", "    D    ", "    D    ", "    D    ", "    D    ", "    D    ", "    D    ", "    D    ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ")
        .aisle("  CCCCC  ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ")
        .aisle(" CCCCCCC ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "  FD#DF  ", "   D#D   ", "  DD#DD  ", "  DD#DD  ", "  DD#DD  ", "  DD#DD  ", "  DD#DD  ", "  DD#DD  ", "  DD#DD  ", "  DD#DD  ", "  FD#DF  ", "  FD#DF  ", "  FD#DF  ", "  FD#DF  ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "    F    ", "    F    ", "    F    ", "    F    ", "    F    ")
        .aisle("CCCCCCCCC", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "  FD#DF  ", "  FD#DF  ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", " DD###DD ", " DD###DD ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "    D    ", "    D    ", "    D    ", "    D    ", "    D    ")
        .aisle("CCCCCCCCC", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "  FD#DF  ", "  FD#DF  ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", " DD###DD ", " DD###DD ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "    D    ", "    D    ", "    D    ", "    D    ", "    D    ")
        .aisle("CCCCCCCCC", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "  FD#DF  ", "  FD#DF  ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", " DD###DD ", " DD###DD ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "    D    ", "    D    ", "    D    ", "    D    ", "    D    ")
        .aisle("CCCCCCCCC", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "  FD#DF  ", "  FD#DF  ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", " DD###DD ", " DD###DD ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", "  D###D  ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "   D#D   ", "    D    ", "    D    ", "    D    ", "    D    ", "    D    ")
        .aisle(" CCCCCCC ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "  FD#DF  ", "   D#D   ", "  DD#DD  ", "  DD#DD  ", "  DD#DD  ", "  DD#DD  ", "  DD#DD  ", "  DD#DD  ", "  DD#DD  ", "  DD#DD  ", "  FD#DF  ", "  FD#DF  ", "  FD#DF  ", "  FD#DF  ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "    F    ", "    F    ", "    F    ", "    F    ", "    F    ")
        .aisle("  CCSCC  ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "   FDF   ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ")
        .aisle("         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "    D    ", "    D    ", "    D    ", "    D    ", "    D    ", "    D    ", "    D    ", "    D    ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ", "         ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(50)
            .or(abilities(MAINTENANCE_HATCH)
                .setExactLimit(1))
            .or(abilities(INPUT_ENERGY)
                .setMaxGlobalLimited(2))
            .or(abilities(INPUT_LASER)
                .setMaxGlobalLimited(1))
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS, IMPORT_FLUIDS)))
        .where('D', states(casingState))
        .where('F', frames(HastelloyN))
        .where('#', air())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.NAQUADAH_ALLOY_CASING

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.nano_forge.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.nano_forge.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.special_max_voltage"))
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.laser_hatch"))
    }

    override fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(energyContainer)
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progress, recipeMapWorkable.maxProgress)
            .addCustom { keyManager, syncer ->
                if (isStructureFormed)
                {
                    if (syncer.syncBoolean(structT2.checkStructure()))
                        keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.nano_forge.structure.t2"))
                    if (syncer.syncBoolean(structT3.checkStructure()))
                        keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.nano_forge.structure.t3"))
                    if (syncer.syncBoolean(structT4.checkStructure()))
                        keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gtlitecore.machine.nano_forge.structure.t4"))
                }
            }
            .addRecipeOutputLine(recipeMapWorkable)
    }

    override fun canBeDistinct() = true

    override fun shouldShowVoidingModeButton() = true

    private fun ResourceLocation.checkStructure(): Boolean = additionalStructureManager[this].isNotEmpty()

    private inner class NanoForgeRecipeLogic(mte: RecipeMapExtendableMultiblock<T>) : ExtendedPowerExtendableMultiblockRecipeLogic<T>(mte, additionalStructureManager)
    {
        override fun getOverclockingDurationFactor(): Double
        {
            if (structT3.checkStructure())
            {
                if (structT4.checkStructure()) return PERFECT_DURATION_FACTOR / 2 // 8/4
                return PERFECT_DURATION_FACTOR // 4/4
            }
            return STD_DURATION_FACTOR // 4/2
        }

        override fun getParallelLimit(): Int
        {
            if (structT2.checkStructure())
                return 64
            if (structT3.checkStructure())
                return 256
            if (structT4.checkStructure())
                return Int.MAX_VALUE
            return super.getParallelLimit()
        }
    }

}