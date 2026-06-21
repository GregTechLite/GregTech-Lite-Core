package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.unification.material.Materials.Osmiridium
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalMultiblockBase
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockPCBFactory
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World

class MultiblockNanolithographyArray<T : MultiblockPCBFactory<T>>(id: ResourceLocation) : AdditionalMultiblockBase<T>(id)
{
    companion object
    {
        private val casingState = MetalCasing.OSMIRIDIUM.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockNanolithographyArray(metaTileEntityId)

    override fun updateFormedValid()
    {
        mainController?.addAdditional(this)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("FEEF", "FEEF", "FEEF", "    ", "    ", "    ", "    ")
        .aisle("EEEE", "E##E", "E##E", "FEEF", "FEEF", "    ", "    ")
        .aisle("EEEE", "E##E", "E##E", "E##E", "E##E", "FEEF", "    ")
        .aisle("EEEE", "E##E", "E##E", "E##E", "E##E", "EEEE", "FEEF")
        .aisle("EEEE", "E##E", "E##E", "E##E", "E##E", "EEEE", "FEEF")
        .aisle("EEEE", "E##E", "E##E", "E##E", "E##E", "EEEE", "FEEF")
        .aisle("EEEE", "E##E", "E##E", "E##E", "E##E", "EEEE", "FEEF")
        .aisle("EEEE", "E##E", "E##E", "E##E", "E##E", "EEEE", "FEEF")
        .aisle("EEEE", "E##E", "E##E", "E##E", "E##E", "FEEF", "    ")
        .aisle("EEEE", "E##E", "E##E", "FEEF", "FEEF", "    ", "    ")
        .aisle("FEEF", "FSEF", "FEEF", "    ", "    ", "    ", "    ")
        .where('S', selfPredicate())
        .where('E', states(casingState)
            .setMinGlobalLimited(140)
            .or(abilities(IMPORT_ITEMS)
                    .setPreviewCount(1)))
        .where('F', frames(Osmiridium))
        .where('#', air())
        .where(' ', any())
        .build()

    override fun getBaseTexture(source: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.OSMIRIDIUM_CASING

    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.machine_type",
                                I18n.format("gtlitecore.machine.pcb_factory.additional_structure_name")))
        tooltip.add(I18n.format("gtlitecore.machine.nanolithography_array.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.nanolithography_array.tooltip.2"))
    }
}
