package gregtechlite.gtlitecore.common.metatileentity.multiblock.module

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.metatileentity.multiblock.extendable.AdditionalMultiblockBase
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.aerospace.AerospaceCasing
import gregtechlite.gtlitecore.common.block.variant.fusion.FusionCoil
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import gregtechlite.gtlitecore.common.metatileentity.multiblock.MultiblockNanoForge
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockVirtualGestaltComputingUplink<T : MultiblockNanoForge<T>>(id: ResourceLocation) : AdditionalMultiblockBase<T>(id)
{
    companion object
    {
        private val casingState = MultiblockCasing.LATTICE_QCD_THERMAL_SHIELDING_CASING.state
        private val secondCasingState = AerospaceCasing.DYSON_SWARM_MODULE_DEPLOYMENT_UNIT_BASE_CASING.state
        private val thirdCasingState = MultiblockCasing.GRAVITY_STABILIZATION_CASING.state
        private val fourthCasingState = ScienceCasing.DIMENSIONAL_BRIDGE_CASING.state
        private val glassState = GlassCasing.NANO_SHIELDING_FRAME.state
        private val coilState = FusionCoil.ULTIMATE.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockVirtualGestaltComputingUplink(metaTileEntityId)

    override fun updateFormedValid()
    {
        mainController?.addAdditional(this)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("         AAAAA         ", "         AAAAA         ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "         AAAAA         ", "         AAAAA         ")
        .aisle("      AAAAAAAAAAA      ", "      AAAAAAAAAAA      ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "      AAAAAAAAAAA      ", "      AAAAAAAAAAA      ")
        .aisle("    AAAAAAAAAAAAAAA    ", "    AAAAACCCCCAAAAA    ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "    AAAAACCCCCAAAAA    ", "    AAAAAAAAAAAAAAA    ")
        .aisle("   AAAAAAAAAAAAAAAAA   ", "   AAACCCEEEEECCCAAA   ", "      DDDFFFFFDDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDDFFFFFDDD      ", "   AAACCCEEEEECCCAAA   ", "   AAAAAAAAAAAAAAAAA   ")
        .aisle("  AAAAAAAAAAAAAAAAAAA  ", "  AACCEEEEEEEEEEECCAA  ", "    DDFFF     FFFDD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DDFFF     FFFDD    ", "  AACCEEEEEEEEEEECCAA  ", "  AAAAAAAAAAAAAAAAAAA  ")
        .aisle("  AAAAAAAAAAAAAAAAAAA  ", "  AACEEEEEEEEEEEEECAA  ", "    DF   GGGGG   FD    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    DF   GGGGG   FD    ", "  AACEEEEEEEEEEEEECAA  ", "  AAAAAAAAAAAAAAAAAAA  ")
        .aisle(" AAAAAAAAAAAAAAAAAAAAA ", " AACEEEEECCCCCEEEEECAA ", "   DF  GG     GG  FD   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   DF  GG     GG  FD   ", " AACEEEEECCCCCEEEEECAA ", " AAAAAAAAAAAAAAAAAAAAA ")
        .aisle(" AAAAAAAAAAAAAAAAAAAAA ", " AACEEECCAAAAACCEEECAA ", "   DF G         G FD   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   DF G         G FD   ", " AACEEECCAAAAACCEEECAA ", " AAAAAAAAAAAAAAAAAAAAA ")
        .aisle(" AAAAAAAAAAAAAAAAAAAAA ", " AACEEECAAAAAAACEEECAA ", "   DF G   CCC   G FD   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   DF G   CCC   G FD   ", " AACEEECAAAAAAACEEECAA ", " AAAAAAAAAAAAAAAAAAAAA ")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAA", "AACEEECAAAAAAAAACEEECAA", "  DF G   CAAAC   G FD  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  DF G   CAAAC   G FD  ", "AACEEECAAAAAAAAACEEECAA", "AAAAAAAAAAAAAAAAAAAAAAA")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAA", "AACEEECAAAAAAAAACEEECAA", "  DF G  CAAAAAC  G FD  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  DF G  CAAAAAC  G FD  ", "AACEEECAAAAAAAAACEEECAA", "AAAAAAAAAAAAAAAAAAAAAAA")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAA", "AACEEECAAAAAAAAACEEECAA", "  DF G  CAAAAAC  G FD  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  DF G  CAAAAAC  G FD  ", "AACEEECAAAAAAAAACEEECAA", "AAAAAAAAAAAAAAAAAAAAAAA")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAA", "AACEEECAAAAAAAAACEEECAA", "  DF G  CAAAAAC  G FD  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  D  G  CAAAAAC  G  D  ", "  DF G  CAAAAAC  G FD  ", "AACEEECAAAAAAAAACEEECAA", "AAAAAAAAAAAAAAAAAAAAAAA")
        .aisle("AAAAAAAAAAAAAAAAAAAAAAA", "AACEEECAAAAAAAAACEEECAA", "  DF G   CAAAC   G FD  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  D  G   CAAAC   G  D  ", "  DF G   CAAAC   G FD  ", "AACEEECAAAAAAAAACEEECAA", "AAAAAAAAAAAAAAAAAAAAAAA")
        .aisle(" AAAAAAAAAAAAAAAAAAAAA ", " AACEEECAAAAAAACEEECAA ", "   DF G   CCC   G FD   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   D  G   CCC   G  D   ", "   DF G   CCC   G FD   ", " AACEEECAAAAAAACEEECAA ", " AAAAAAAAAAAAAAAAAAAAA ")
        .aisle(" AAAAAAAAAAAAAAAAAAAAA ", " AACEEECCAAAAACCEEECAA ", "   DF G         G FD   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   D  G         G  D   ", "   DF G         G FD   ", " AACEEECCAAAAACCEEECAA ", " AAAAAAAAAAAAAAAAAAAAA ")
        .aisle(" AAAAAAAAAAAAAAAAAAAAA ", " AACEEEEECCCCCEEEEECAA ", "   DF  GG     GG  FD   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   D   GG     GG   D   ", "   DF  GG     GG  FD   ", " AACEEEEECCCCCEEEEECAA ", " AAAAAAAAAAAAAAAAAAAAA ")
        .aisle("  AAAAAAAAAAAAAAAAAAA  ", "  AACEEEEEEEEEEEEECAA  ", "    DF   GGGGG   FD    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    D    GGGGG    D    ", "    DF   GGGGG   FD    ", "  AACEEEEEEEEEEEEECAA  ", "  AAAAAAAAAAAAAAAAAAA  ")
        .aisle("  AAAAAAAAAAAAAAAAAAA  ", "  AACCEEEEEEEEEEECCAA  ", "    DDFFF     FFFDD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DD           DD    ", "    DDFFF     FFFDD    ", "  AACCEEEEEEEEEEECCAA  ", "  AAAAAAAAAAAAAAAAAAA  ")
        .aisle("   AAAAAAAAAAAAAAAAA   ", "   AAACCCEEEEECCCAAA   ", "      DDDFFFFFDDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDD     DDD      ", "      DDDFFFFFDDD      ", "   AAACCCEEEEECCCAAA   ", "   AAAAAAAAAAAAAAAAA   ")
        .aisle("    AAAAAAAAAAAAAAA    ", "    AAAAACCCCCAAAAA    ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "         DDDDD         ", "    AAAAACCCCCAAAAA    ", "    AAAAAAAAAAAAAAA    ")
        .aisle("      AAAAAAAAAAA      ", "      AAAAAAAAAAA      ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "      AAAAAAAAAAA      ", "      AAAAAAAAAAA      ")
        .aisle("         AAAAA         ", "         AASAA         ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "                       ", "         AAAAA         ", "         AAAAA         ")
        .where('S', selfPredicate())
        .where('A', states(casingState))
        .where('C', states(secondCasingState))
        .where('E', states(thirdCasingState))
        .where('F', states(fourthCasingState))
        .where('D', states(glassState))
        .where('G', states(coilState))
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.LATTICE_QCD_THERMAL_SHIELDING_CASING

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.machine_type",
                                I18n.format("gtlitecore.machine.nano_forge.additional_structure_name")))
        tooltip.add(I18n.format("gtlitecore.machine.virtual_gestalt_computing_uplink.tooltip.1"))
    }
}