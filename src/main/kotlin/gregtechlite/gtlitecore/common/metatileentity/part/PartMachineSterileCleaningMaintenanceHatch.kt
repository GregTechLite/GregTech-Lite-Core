package gregtechlite.gtlitecore.common.metatileentity.part

import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.ColourMultiplier
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.GTValues
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.metatileentity.multiblock.DummyCleanroom
import gregtech.api.metatileentity.multiblock.ICleanroomReceiver
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase
import gregtech.api.util.GTUtility
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityAutoMaintenanceHatch
import gregtechlite.gtlitecore.api.extension.add
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World

class PartMachineSterileCleaningMaintenanceHatch(id: ResourceLocation) : MetaTileEntityAutoMaintenanceHatch(id)
{

    companion object
    {

        private val cleanedTypes = hashSetOf<CleanroomType>().apply {
            add(CleanroomType.CLEANROOM)
            add(CleanroomType.STERILE_CLEANROOM)
        }

        private val dummyCleanroom = DummyCleanroom.createForTypes(cleanedTypes)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = PartMachineSterileCleaningMaintenanceHatch(metaTileEntityId)

    override fun addToMultiBlock(controllerBase: MultiblockControllerBase)
    {
        super.addToMultiBlock(controllerBase)
        if (controllerBase is ICleanroomReceiver)
        {
            controllerBase.setCleanroom(dummyCleanroom)
        }
    }

    override fun getTier(): Int = GTValues.UV

    override fun getBaseTexture(): ICubeRenderer?
    {
        if (controller != null)
        {
            return controller.getBaseTexture(this).also { this.hatchTexture = it }
        }
        else if (this.hatchTexture != null)
        {
            if (hatchTexture !== Textures.getInactiveTexture(hatchTexture))
            {
                return Textures.getInactiveTexture(hatchTexture).also { this.hatchTexture = it }
            }
            return this.hatchTexture
        }
        else
        {
            return Textures.VOLTAGE_CASINGS[getTier()]
        }
    }

    override fun renderMetaTileEntity(renderState: CCRenderState?,
                                      translation: Matrix4?,
                                      pipeline: Array<IVertexOperation?>?)
    {
        baseTexture?.render(renderState, translation, pipeline?.add(ColourMultiplier(
            GTUtility.convertRGBtoOpaqueRGBA_CL(paintingColorForRendering))))
        if (shouldRenderOverlay())
            GTLiteTextures.STERILE_CLEANING_MAINTENANCE_OVERLAY.renderSided(getFrontFacing(), renderState, translation, pipeline)
    }

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.maintenance_hatch_sterile_cleanroom_auto.tooltip.1"))
        tooltip.add(I18n.format("gregtech.machine.maintenance_hatch.cleanroom_auto.tooltip.2"))
        for (type in cleanedTypes)
        {
            tooltip.add(String.format("  %s%s", TextFormatting.GREEN, I18n.format(type.translationKey)))
        }
    }

}