package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalMultiblockBase
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.QuantumAlloy
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTFusionCasing
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
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
        private val thirdCasingState = MultiblockCasing.STELLAR_CONTAINMENT_CASING.state
        private val uniqueCasingState = ActiveUniqueCasing.HEAT_VENT.state
        private val secondUniqueCasingState = ScienceCasing.CONTAINMENT_FIELD_GENERATOR.state
        private val pipeCasingState = ScienceCasing.HOLLOW_CASING.state
        private val glassState = GTGlassCasing.FUSION_GLASS.state
        private val secondGlassState = GlassCasing.FORCE_FIELD.state
        private val coilState = GTFusionCasing.FUSION_COIL.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockNaniteReplicationUnrestricor(metaTileEntityId)

    override fun updateFormedValid()
    {
        mainController?.addAdditional(this)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("             ", "             ", "             ", "             ", "             ", "             ", "     FFF     ", "     F F     ", "     CCC     ", "    CUCUC    ", "    CUCUC    ", "    CUCUC    ", "     CCC     ", "     F F     ", "     FFF     ", "             ")
        .aisle("  I       I  ", "  I       I  ", "  F       F  ", "  F       F  ", "             ", "             ", "    F   F    ", "    CCCCC    ", "   CC###CC   ", "   E#####E   ", "   U#####U   ", "   E#####E   ", "   CC###CC   ", "    CCCCC    ", "    F   F    ", "     FFF     ")
        .aisle("   I     I   ", "   I     I   ", "   F     F   ", "   F     F   ", "   F     F   ", "   F FFF F   ", "   FCCCCCF   ", "   C#####C   ", "  C#######C  ", "  E#######E  ", "  U#######U  ", "  E#######E  ", "  C#######C  ", "   C#####C   ", "   FCCCCCF   ", "    F   F    ")
        .aisle("             ", "             ", "             ", "             ", "    F   F    ", "    FCCCF    ", "  FCCJPJCCF  ", "  C##J#J##C  ", "  C##J#J##C  ", "IC###J#J###CI", "IC###JHJ###CI", "IC###J#J###CI", "  C##J#J##C  ", "  C##J#J##C  ", "  FCCJPJCCF  ", "   F CCC F   ")
        .aisle("             ", "             ", "             ", "             ", "             ", "   FCCCCCF   ", " F CJXDXJC F ", " IC#JX#XJ#CI ", "IC##J###J##CI", " G##J#H#J##G ", " G##JHHHJ##G ", " G##J#H#J##G ", "IC##J###J##CI", " IC#JX#XJ#CI ", " F CJXDXJC F ", "  F CCCCC F  ")
        .aisle("             ", "             ", "             ", "             ", "             ", "   FCCCCCF   ", " F CPDDDPC F ", "  C###D###C  ", " C####H####C ", " G###HHH###G ", " G##HHHHH##G ", " G###HHH###G ", " C####H####C ", "  C###D###C  ", " F CPDDDPC F ", "  F CCCCC F  ")
        .aisle("             ", "             ", "             ", "             ", "             ", "   FCCCCCF   ", " F CJXDXJC F ", " IC#JX#XJ#CI ", "IC##J###J##CI", " G##J#H#J##G ", " G##JHHHJ##G ", " G##J#H#J##G ", "IC##J###J##CI", " IC#JX#XJ#CI ", " F CJXDXJC F ", "  F CCCCC F  ")
        .aisle("             ", "             ", "             ", "             ", "    F   F    ", "    FCCCF    ", "  FCCJPJCCF  ", "  C##J#J##C  ", "  C##J#J##C  ", "IC###J#J###CI", "IC###JHJ###CI", "IC###J#J###CI", "  C##J#J##C  ", "  C##J#J##C  ", "  FCCJPJCCF  ", "   F CCC F   ")
        .aisle("   I     I   ", "   I     I   ", "   F     F   ", "   F     F   ", "   F     F   ", "   F FFF F   ", "   FCCCCCF   ", "   C#####C   ", "  C#######C  ", "  E#######E  ", "  U#######U  ", "  E#######E  ", "  C#######C  ", "   C#####C   ", "   FCCCCCF   ", "    F   F    ")
        .aisle("  I       I  ", "  I       I  ", "  F       F  ", "  F       F  ", "             ", "             ", "    F   F    ", "    CCCCC    ", "   CC###CC   ", "   E#####E   ", "   U#####U   ", "   E#####E   ", "   CC###CC   ", "    CCCCC    ", "    F   F    ", "     FFF     ")
        .aisle("             ", "             ", "             ", "             ", "             ", "             ", "     FFF     ", "     F F     ", "     CCC     ", "    CCCCC    ", "    CCSCC    ", "    CCCCC    ", "     CCC     ", "     F F     ", "     FFF     ", "             ")
        .where('S', selfPredicate())
        .where('C', states(casingState))
        .where('D', states(secondCasingState))
        .where('E', states(thirdCasingState))
        .where('U', states(uniqueCasingState))
        .where('P', states(pipeCasingState))
        .where('X', states(secondUniqueCasingState))
        .where('G', states(glassState))
        .where('J', states(secondGlassState))
        .where('H', states(coilState))
        .where('F', frames(Neutronium))
        .where('I', frames(QuantumAlloy))
        .where('#', air())
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