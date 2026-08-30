package gregtechlite.gtlitecore.common.metatileentity.multiblock.mega

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.util.RelativeDirection
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.standardStabilizationFieldGens
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.BLACKHOLE_FORMING_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Pikyonium64B
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.aerospace.AerospaceCasing
import gregtechlite.gtlitecore.common.block.variant.science.ScienceCasing
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockBlackholeFormer(id: ResourceLocation) : RecipeMapMultiblockController(id, BLACKHOLE_FORMING_RECIPES)
{
    companion object
    {
        private val casingState = AerospaceCasing.ELEVATOR_BASE_CASING.state
        private val secondCasingState = ScienceCasing.ULTIMATE_MOLECULAR_CASING.state
        private val thirdCasingState = MultiblockCasing.ADVANCED_FILTER_CASING.state
        private val uniqueCasingState = MultiblockCasing.HAWKING_RADIATION_ABSORPTION_CASING.state
        private val glassState = GlassCasing.QUANTUM.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockBlackholeFormer(metaTileEntityId)

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.LEFT)
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "     A                 A     ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "    A                   A    ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "   B                     B   ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "            CCCCC            ", "           CCCCCCC           ", "   B       CCCCCCC       B   ", "   D       CCCCCCC       D   ", "   B       CCCCCCC       B   ", "           CCCCCCC           ", "            CCCCC            ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "              C              ", "           CCCCCCC           ", "          CC     CC          ", "          C       C          ", "  B       C       C       B  ", "  D      CC       CC      D  ", "  B       C       C       B  ", "          C       C          ", "          CC     CC          ", "           CCCCCCC           ", "              C              ", "                             ", "                             ")
        .aisle("                             ", "              C              ", "           CCC CCC           ", "          C       C          ", "         C         C         ", "  B      C         C      B  ", " A       C         C       A ", " E      C           C      E ", " A       C         C       A ", "  B      C         C      B  ", "         C         C         ", "          C       C          ", "           CCC CCC           ", "              C              ", "                             ")
        .aisle("                             ", "           CCCCCCC           ", "          C       C          ", "         C         C         ", "        C           C        ", "  A     C           C     A  ", "  B     C           C     B  ", " B      C           C      B ", "  B     C           C     B  ", "  A     C           C     A  ", "        C           C        ", "         C         C         ", "          C       C          ", "           CCCCCCC           ", "                             ")
        .aisle("            CCCCC            ", "          CC     CC          ", "         C         C         ", "        C           C        ", "        C           C        ", "  B    C             C    B  ", " B     C             C     B ", " DF    C             C    FD ", " B     C             C     B ", "  B    C             C    B  ", "        C           C        ", "        C           C        ", "         C         C         ", "          CC     CC          ", "            CCCCC            ")
        .aisle("           CCCCCCC           ", "          C       C          ", "         C         C         ", "        C           C        ", "       C             C       ", " B     C             C     B ", " DF    C             C    FD ", " G     C             C     G ", " DF    C             C    FD ", " B     C             C     B ", "       C             C       ", "        C           C        ", "         C         C         ", "          C       C          ", "           CCCCCCC           ")
        .aisle("           CCCCCCC           ", "          C       C          ", "         C         C         ", "        C           C        ", " B     C             C     B ", " DF    C             C    FD ", "GG     C             C     GG", "GG     C             C     GG", "GG     C             C     GG", " DF    C             C    FD ", " B     C             C     B ", "        C           C        ", "         C         C         ", "          C       C          ", "           CCCCCCC           ")
        .aisle("           CCCCCCC           ", "         CC       CC         ", "        C           C        ", " B      C           C      B ", " DF    C             C    FD ", " G     C             C     G ", "GG     C             C     GG", "S      C             C       ", "GG     C             C     GG", " G     C             C     G ", " DF    C             C    FD ", " B      C           C      B ", "        C           C        ", "         CC       CC         ", "           CCCCCCC           ")
        .aisle("           CCCCCCC           ", "          C       C          ", "         C         C         ", "        C           C        ", " B     C             C     B ", " DF    C             C    FD ", "GG     C             C     GG", "GG     C             C     GG", "GG     C             C     GG", " DF    C             C    FD ", " B     C             C     B ", "        C           C        ", "         C         C         ", "          C       C          ", "           CCCCCCC           ")
        .aisle("           CCCCCCC           ", "          C       C          ", "         C         C         ", "        C           C        ", "       C             C       ", " B     C             C     B ", " DF    C             C    FD ", " G     C             C     G ", " DF    C             C    FD ", " B     C             C     B ", "       C             C       ", "        C           C        ", "         C         C         ", "          C       C          ", "           CCCCCCC           ")
        .aisle("            CCCCC            ", "          CC     CC          ", "         C         C         ", "        C           C        ", "        C           C        ", "  B    C             C    B  ", " B     C             C     B ", " DF    C             C    FD ", " B     C             C     B ", "  B    C             C    B  ", "        C           C        ", "        C           C        ", "         C         C         ", "          CC     CC          ", "            CCCCC            ")
        .aisle("                             ", "           CCCCCCC           ", "          C       C          ", "         C         C         ", "        C           C        ", "  A     C           C     A  ", "  B     C           C     B  ", " B      C           C      B ", "  B     C           C     B  ", "  A     C           C     A  ", "        C           C        ", "         C         C         ", "          C       C          ", "           CCCCCCC           ", "                             ")
        .aisle("                             ", "              C              ", "           CCC CCC           ", "          C       C          ", "         C         C         ", "  B      C         C      B  ", " A       C         C       A ", " E      C           C      E ", " A       C         C       A ", "  B      C         C      B  ", "         C         C         ", "          C       C          ", "           CCC CCC           ", "              C              ", "                             ")
        .aisle("                             ", "                             ", "              C              ", "           CCCCCCC           ", "          CC     CC          ", "          C       C          ", "  B       C       C       B  ", "  D      CC       CC      D  ", "  B       C       C       B  ", "          C       C          ", "          CC     CC          ", "           CCCCCCC           ", "              C              ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "            CCCCC            ", "           CCCCCCC           ", "   B       CCCCCCC       B   ", "   D       CCCCCCC       D   ", "   B       CCCCCCC       B   ", "           CCCCCCC           ", "            CCCCC            ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "   B                     B   ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "    A                   A    ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "     A                 A     ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .where('S', selfPredicate())
        .where('B', states(casingState))
        .where('D', states(glassState))
        .where('F', states(thirdCasingState))
        .where('G', states(secondCasingState))
        .where('C', states(uniqueCasingState))
        .where('A', frames(Pikyonium64B))
        .where('E', standardStabilizationFieldGens())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.SPACE_ELEVATOR_BASE_CASING
}