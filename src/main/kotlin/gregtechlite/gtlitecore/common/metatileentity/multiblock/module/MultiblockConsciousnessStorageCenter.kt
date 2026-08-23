package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.unification.material.Materials.Francium
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalMultiblockBase
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.adapter.GTTurbineCasing
import gregtechlite.gtlitecore.common.block.variant.BoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockNanoForge
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockConsciousnessStorageCenter<T : MultiblockNanoForge<T>>(id: ResourceLocation) : AdditionalMultiblockBase<T>(id)
{
    companion object
    {
        private val casingState = MetalCasing.TRINAQUADALLOY.state
        private val secondCasingState = GTMultiblockCasing.ASSEMBLY_LINE_CASING.state
        private val gearboxCasingState = GTTurbineCasing.TUNGSTENSTEEL_GEARBOX.state
        private val pipeCasingState = BoilerCasing.POLYBENZIMIDAZOLE.state
        private val coilState = GTFusionCasing.SUPERCONDUCTOR_COIL.state
        private val glassState = GTGlassCasing.FUSION_GLASS.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockConsciousnessStorageCenter(metaTileEntityId)

    override fun updateFormedValid()
    {
        mainController?.addAdditional(this)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("      FCF      ", "     CGCGC     ", "      FCF      ")
        .aisle("    CCCCCCC    ", "   CC XDX CC   ", "    CCCGCCC    ")
        .aisle("   CCCCCCCCC   ", "  C  CGCGC  C  ", "   CCCCCCCCC   ")
        .aisle("  CCC F F CCC  ", " C  C FPF C  C ", "  CCC F F CCC  ")
        .aisle(" CCC       CCC ", " C C   P   C C ", " CCC       CCC ")
        .aisle(" CC  F   F  CC ", "C C   FQF   C C", " CC  F   F  CC ")
        .aisle("FCCF   Q   FCCF", "GXGF FQQQF FGXG", "FCCF   Q   FCCF")
        .aisle("CCC   QQQ   CCC", "CDCPPQQQQQPPCDC", "CGC   QQQ   CGC")
        .aisle("FCCF   Q   FCCF", "GXGF FQQQF FGXG", "FCCF   Q   FCCF")
        .aisle(" CC  F   F  CC ", "C C   FQF   C C", " CC  F   F  CC ")
        .aisle(" CCC       CCC ", " C C   P   C C ", " CCC       CCC ")
        .aisle("  CCC F F CCC  ", " C  C FPF C  C ", "  CCC F F CCC  ")
        .aisle("   CCCCCCCCC   ", "  C  CGCGC  C  ", "   CCCCCCCCC   ")
        .aisle("    CCCCCCC    ", "   CC XDX CC   ", "    CCCGCCC    ")
        .aisle("      FCF      ", "     CGSGC     ", "      FCF      ")
        .where('S', selfPredicate())
        .where('C', states(casingState))
        .where('X', states(secondCasingState))
        .where('D', states(gearboxCasingState))
        .where('P', states(pipeCasingState))
        .where('Q', states(coilState))
        .where('G', states(glassState))
        .where('F', frames(Francium))
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.TRINAQUADALLOY_CASING

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.machine_type",
                                I18n.format("gtlitecore.machine.nano_forge.additional_structure_name")))
        tooltip.add(I18n.format("gtlitecore.machine.consciousness_storage_center.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.consciousness_storage_center.tooltip.2"))
    }
}