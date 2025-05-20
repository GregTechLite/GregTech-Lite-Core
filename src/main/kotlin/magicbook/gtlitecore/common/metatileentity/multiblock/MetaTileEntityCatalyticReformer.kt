package magicbook.gtlitecore.common.metatileentity.multiblock

import com.cleanroommc.modularui.factory.PosGuiData
import com.cleanroommc.modularui.screen.ModularPanel
import com.cleanroommc.modularui.value.sync.PanelSyncManager
import gregtech.api.capability.IControllable
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.unification.material.Materials.StainlessSteel
import gregtech.api.util.GTUtility
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.blocks.BlockBoilerCasing
import gregtech.common.blocks.BlockMetalCasing
import gregtech.common.blocks.MetaBlocks
import magicbook.gtlitecore.api.gui.GTLiteMuiTextures
import magicbook.gtlitecore.api.gui.factory.MultiblockUIBuilder
import magicbook.gtlitecore.api.gui.factory.MultiblockUIFactory
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.CATALYTIC_REFORMER_RECIPES
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly


@Suppress("MISSING_DEPENDENCY_CLASS")
class MetaTileEntityCatalyticReformer(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, CATALYTIC_REFORMER_RECIPES), IControllable
{

    companion object
    {
        private val casingState
            get() = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN)

        private val pipeCasingState
            get() = MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity?) = MetaTileEntityCatalyticReformer(metaTileEntityId)

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("F   F", "CCCPC", "CCCPC", "CCCPC")
        .aisle("     ", "CCCPC", "C###O", "CCCPC")
        .aisle("F   F", "CCCPC", "CSCPC", "CCCPC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(24)
            .or(autoAbilities(true, true, true, false, true, true, false)))
        .where('P', states(pipeCasingState))
        .where('F', frames(StainlessSteel))
        .where('O', abilities(MUFFLER_HATCH))
        .where('#', air())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.CLEAN_STAINLESS_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun hasMufflerMechanics() = true

    @Suppress("UnstableApiUsage")
    override fun usesMui2() = true

    override fun buildUI(guiData: PosGuiData, guiSyncManager: PanelSyncManager): ModularPanel
    {
        return MultiblockUIFactory(this)
            .configureDisplayText(this::configureDisplayText)
            .configureWarningText(this::configureWarningText)
            .configureErrorText(this::configureErrorText)
            .buildUI(guiData, guiSyncManager, GTLiteMuiTextures.DISPLAY)
    }

    private fun configureDisplayText(builder: MultiblockUIBuilder)
    {
        builder.setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(getEnergyContainer())
            .addEnergyTierLine(GTUtility.getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progress, recipeMapWorkable.maxProgress)
    }

    private fun configureWarningText(builder: MultiblockUIBuilder)
    {
        builder.addLowPowerLine(recipeMapWorkable.isHasNotEnoughEnergy)
        builder.addMaintenanceProblemLines(maintenanceProblems, true)
    }

    private fun configureErrorText(builder: MultiblockUIBuilder)
    {
        builder.structureFormed(isStructureFormed)
        if (hasMufflerMechanics())
            builder.addMufflerObstructedLine(!isMufflerFaceFree)
        if (hasMaintenanceMechanics())
            builder.addMaintenanceProblemLines(maintenanceProblems, false)
    }

    override fun isWorkingEnabled(): Boolean = recipeMapWorkable.isWorkingEnabled

    override fun setWorkingEnabled(workingEnabled: Boolean)
    {
        recipeMapWorkable.isWorkingEnabled = workingEnabled
    }

}