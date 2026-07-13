package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalMultiblockBase
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockNanoForge
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockNaniteReplicationUnrestricor<T : MultiblockNanoForge<T>>(id: ResourceLocation) : AdditionalMultiblockBase<T>(id)
{
    companion object
    {
        private val casingState = MetalCasing.QUANTUM_ALLOY.state
        private val secondCasingState = GTMultiblockCasing.ASSEMBLY_LINE_CASING.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockNaniteReplicationUnrestricor(metaTileEntityId)

    override fun updateFormedValid()
    {
        mainController?.addAdditional(this)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" aaaaaa ", "        ", "    ff  ", "  ff    ", "        ", "        ", "        ", "        ", "    ff  ", "  ff    ", "        ", "        ", "        ", "        ", "    ff  ", "  ff    ", "        ", "        ", "        ", "        ", "    ff  ", "  ff    ", "        ", "        ", "        ", "        ", "        ")
        .aisle("aaaaaaaa", "      f ", "        ", "        ", " f      ", "        ", "        ", "      f ", "        ", "        ", " f      ", "        ", "        ", "      f ", "        ", "        ", " f      ", "        ", "        ", "      f ", "        ", "        ", " f      ", "        ", "        ", "      f ", "        ")
        .aisle("aaaaaaaa", "        ", "        ", "        ", "   bb   ", "f  yy   ", "   bb  f", "        ", "        ", "        ", "        ", "f       ", "       f", "        ", "   bb   ", "   bb   ", "   bb   ", "f  bb   ", "       f", "        ", "        ", "        ", "        ", "f       ", "   bb  f", "   yyf  ", "   bb   ")
        .aisle("aaaaaaaa", "   bb   ", "   bb   ", "   bb   ", "  bbbb  ", "f ybby  ", "  bbbb f", "   bb   ", "   bb   ", "   bb   ", "   bb   ", "f  bb   ", "   bb  f", "   bb   ", "  bbbb  ", "  bbbb  ", "  bbbb  ", "f bbbb  ", "   bb  f", "   bb   ", "   bb   ", "   bb   ", "   bb   ", "f  bb   ", "  bbbb f", "  ybby  ", "  bbbb  ")
        .aisle("aaaaaaaa", "   bb   ", "   bb   ", "   bb   ", "  bbbb  ", "  ybby f", "f bbbb  ", "   bb   ", "   bb   ", "   bb   ", "   bb   ", "   bb  f", "f  bb   ", "   bb   ", "  bbbb  ", "  bbbb  ", "  bbbb  ", "  bbbb f", "f  bb   ", "   bb   ", "   bb   ", "   bb   ", "   bb   ", "   bb  f", "f bbbb  ", "  ybby  ", "  bbbb  ")
        .aisle("aaaaaaaa", "        ", "        ", "        ", "   bb   ", "   yy  f", "f  bb   ", "        ", "        ", "        ", "        ", "       f", "f       ", "        ", "   bb   ", "   bb   ", "   bb   ", "   bb  f", "f       ", "        ", "        ", "        ", "        ", "       f", "f  bb   ", "  fyy   ", "   bb   ")
        .aisle("aaaaaaaa", " f      ", "        ", "        ", "      f ", "        ", "        ", " f      ", "        ", "        ", "      f ", "        ", "        ", " f      ", "        ", "        ", "      f ", "        ", "        ", " f      ", "        ", "        ", "      f ", "        ", "        ", " f      ", "        ")
        .aisle(" aaaaaa ", "     S  ", "  ff    ", "    ff  ", "        ", "        ", "        ", "        ", "  ff    ", "    ff  ", "        ", "        ", "        ", "        ", "  ff    ", "    ff  ", "        ", "        ", "        ", "        ", "  ff    ", "    ff  ", "        ", "        ", "        ", "        ", "        ")
        .where('S', selfPredicate())
        .where('a', states(casingState))
        .where('b', states(casingState))
        .where('y', states(secondCasingState))
        .where('f', frames(Neutronium))
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.QUANTUM_ALLOY_CASING

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.machine_type",
                                I18n.format("gtlitecore.machine.nano_forge.additional_structure_name")))
        tooltip.add(I18n.format("gtlitecore.machine.nanite_replication_unrestricor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.nanite_replication_unrestricor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.nanite_replication_unrestricor.tooltip.3"))
    }
}