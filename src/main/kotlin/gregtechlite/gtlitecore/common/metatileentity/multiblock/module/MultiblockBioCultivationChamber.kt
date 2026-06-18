package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalMultiblockBase
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockPCBFactory
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World

class MultiblockBioCultivationChamber<T : MultiblockPCBFactory<T>>(id: ResourceLocation) : AdditionalMultiblockBase<T>(id)
{
    companion object
    {
        private val casingState = GTMetalCasing.STAINLESS_CLEAN.state
        private val glassState = GlassCasing.GREENHOUSE.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockBioCultivationChamber(metaTileEntityId)

    override fun updateFormedValid()
    {
        mainController?.addAdditional(this)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("hMMMh  hMMMh", "hNNNh  hNNNh", "hNNNh  hNNNh", "hNNNh  hNNNh", "h   h  h   h", "            ", "            ")
        .aisle("MMMMM  MMMMM", "N###N  N###N", "N###N  N###N", "N###N  N###N", " MMM    MMM ", "            ", "            ")
        .aisle("MMMMM  MMMMM", "N###N  N###N", "N###N  N###N", "N###N  N###N", " MMM    MMM ", "  M      M  ", "   MMMMMM   ")
        .aisle("MMMMM  MMMMM", "N###N  N###N", "N###N  N###N", "N###N  N###N", " MMM    MMM ", "            ", "            ")
        .aisle("hMSMh  hMMMh", "hNNNh  hNNNh", "hNNNh  hNNNh", "hNNNh  hNNNh", "h   h  h   h", "            ", "            ")
        .where('S', selfPredicate())
        .where('M', states(casingState))
        .where('h', frames(HSLASteel))
        .where('N', states(glassState))
        .where('#', air())
        .where(' ', any())
        .build()

    override fun getBaseTexture(source: IMultiblockPart?): ICubeRenderer = Textures.CLEAN_STAINLESS_STEEL_CASING

    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.machine_type",
                                I18n.format("gtlitecore.machine.pcb_factory.additional_structure_name")))
    }
}