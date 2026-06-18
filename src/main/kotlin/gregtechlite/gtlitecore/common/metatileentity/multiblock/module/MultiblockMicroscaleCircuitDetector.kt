package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
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

class MultiblockMicroscaleCircuitDetector<T : MultiblockPCBFactory<T>>(id: ResourceLocation) : AdditionalMultiblockBase<T>(id)
{
    companion object
    {
        private val casingState = MetalCasing.NEUTRONIUM.state
        private val secondCasingState = MetalCasing.NAQUADAH_ALLOY.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockMicroscaleCircuitDetector(metaTileEntityId)

    override fun updateFormedValid()
    {
        mainController?.addAdditional(this)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" KKKKK ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ")
        .aisle("KKKKKKK", "  KKK  ", "  KKK  ", "  KKK  ", "  KKK  ", "  KKK  ", "  KKK  ", "  KKK  ", "  KKK  ", "  KKK  ", "  KKK  ", "  KKK  ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ")
        .aisle("KKKKKKK", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", "  LLL  ", "  LLL  ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ")
        .aisle("KKKKKKK", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", "  L#L  ", " K###K ", "  L#L  ", "  L#L  ", "  LKL  ", "  LKL  ", "   K   ", "   K   ", "   K   ", "   K   ", "   K   ", "       ")
        .aisle("KKKKKKK", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", "  L#L  ", " K###K ", "  L#L  ", "  L#L  ", "  L#L  ", "  L#L  ", "  L#L  ", "  L#L  ", "  L#L  ", "  L#L  ", "   K   ", "   K   ")
        .aisle("KKKKKKK", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", "  L#L  ", "  L#L  ", "  L#L  ", " K###K ", "  L#L  ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", "  L#L  ", "  L#L  ", "   K   ", "   K   ")
        .aisle("KKKKKKK", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", "  L#L  ", "  L#L  ", "  L#L  ", " K###K ", "  L#L  ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", "  L#L  ", "  LKL  ", "   K   ", "       ")
        .aisle("KKKKKKK", " K###K ", " K###K ", " K###K ", " K###K ", " K###K ", "  LLL  ", "  LLL  ", "  LLL  ", " K###K ", " K###K ", " K###K ", " K#L#K ", " K#L#K ", " K#L#K ", " K###K ", " K###K ", " K###K ", "  LLL  ", "  LLL  ", "       ", "       ")
        .aisle("KKKKKKK", "  KKK  ", "  KKK  ", "  KKK  ", "  KKK  ", "  KKK  ", "       ", "       ", "       ", "  KKK  ", "  KKK  ", "  KKK  ", "  K K  ", "  K K  ", "  K K  ", "  KKK  ", "  KKK  ", "  KKK  ", "       ", "       ", "       ", "       ")
        .aisle(" KKSKK ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ", "       ")
        .where('S', selfPredicate())
        .where('K', states(casingState))
        .where('L', states(secondCasingState))
        .where('#', air())
        .where(' ', any())
        .build()

    override fun getBaseTexture(source: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.NEUTRONIUM_CASING

    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.machine_type",
                                I18n.format("gtlitecore.machine.pcb_factory.additional_structure_name")))
    }
}