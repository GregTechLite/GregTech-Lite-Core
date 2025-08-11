package gregtechlite.gtlitecore.api.metatileentity.multiblock

import com.cleanroommc.modularui.widgets.ButtonWidget
import gregtech.api.capability.IEnergyContainer
import gregtech.api.capability.impl.EnergyContainerHandler
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory
import gregtech.api.pattern.BlockPattern
import gregtech.api.recipes.RecipeMap
import gregtech.api.util.KeyUtil
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.capability.ModuleProvider
import gregtechlite.gtlitecore.api.capability.ModuleReceiver
import gregtechlite.gtlitecore.api.gui.GTLiteMuiTextures
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.pow

/**
 * @param tier          The voltage tier of this mte.
 * @param moduleTier    The inner tier of the module.
 * @param minCasingTier The minimum casing tier of this module required, this is useful for some tiered status predicate.
 */
abstract class RecipeMapModuleMultiblockController(metaTileEntityId: ResourceLocation,
                                                   recipeMap: RecipeMap<*>,
                                                   protected val tier: Int,
                                                   protected val moduleTier: Int,
                                                   protected val minCasingTier: Int) : RecipeMapMultiblockController(metaTileEntityId, recipeMap), ModuleReceiver
{

    override var moduleProvider: ModuleProvider? = null
    override val displayCountName: String
        get() = "$metaName.display_count"

    protected val energyConsumed = (4.0.pow((this.tier + 2).toDouble()) / 2).toLong()


    init
    {
        this.energyContainer = EnergyContainerHandler(this,
            (160008000L * 4.0.pow((this.tier - 9).toDouble())).toLong(), this.energyConsumed,
            1, 0, 0)
    }

    override fun checkStructurePattern()
    {
        if (moduleProvider?.casingTier?.let { it >= minCasingTier } == true)
        {
            super.checkStructurePattern()
        }
    }

    abstract override fun initializeAbilities()

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        moduleProvider = null
    }

    abstract override fun createStructurePattern(): BlockPattern

    override fun getEnergyContainer(): IEnergyContainer?
    {
        return if (moduleProvider?.subEnergyContainer == null)
        {
            EnergyContainerHandler(this, 0, 0, 0, 0, 0)
        }
        else
        {
            this.energyContainer
        }
    }

    override fun sentWorkingDisabled()
    {
        this.recipeMapWorkable.isWorkingEnabled = false
    }

    override fun sentWorkingEnabled()
    {
        this.recipeMapWorkable.isWorkingEnabled = true
    }

    override fun updateFormedValid()
    {
        super.updateFormedValid()
        if (offsetTimer % SECOND == 0L)
        {
            moduleProvider?.also {
                if (this.energyContainer.energyCapacity != this.energyContainer.energyStored
                    && it.subEnergyContainer!!.energyStored > this.energyConsumed * SECOND
                )
                {
                    val simulate = this.energyContainer.energyCapacity - this.energyContainer.energyStored
                    it.subEnergyContainer!!.removeEnergy(simulate)
                    this.energyContainer.addEnergy(simulate)
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    abstract override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer?

    @SideOnly(Side.CLIENT)
    abstract override fun getFrontOverlay(): ICubeRenderer

    override fun createUIFactory(): MultiblockUIFactory?
    {
        return super.createUIFactory() // TODO Disabled Indicator and add Space Elevator Logo?
            .createFlexButton { guiData, guiSyncManager ->
                return@createFlexButton ButtonWidget()
                    .background(GTLiteMuiTextures.BUTTON_REFRESH_STRUCTURE_PATTERN)
                    .onMousePressed { i ->
                        reinitializeStructurePattern()
                        return@onMousePressed true
                    }
                    .tooltip { tooltip ->
                        tooltip.addLine(KeyUtil.lang("gtlitecore.machine.space_elevator.refresh_structure_pattern"))
                    }
            }
    }

    override fun hasMaintenanceMechanics() = false

}
