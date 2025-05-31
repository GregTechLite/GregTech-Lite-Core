package magicbook.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.VOC
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MAINTENANCE_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.MultiblockShapeInfo
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.unification.material.Materials.Neutronium
import gregtech.api.util.GTUtility.getFloorTierByVoltage
import gregtech.api.util.RelativeDirection.DOWN
import gregtech.api.util.RelativeDirection.FRONT
import gregtech.api.util.RelativeDirection.LEFT
import gregtech.api.util.TextComponentUtil.translationWithColor
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.blocks.BlockMultiblockCasing
import gregtech.common.blocks.MetaBlocks
import gregtech.common.metatileentities.MetaTileEntities
import magicbook.gtlitecore.api.pattern.GTLiteTraceabilityPredicate.Companion.optionalStates
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.NANO_FORGE_RECIPES
import magicbook.gtlitecore.api.recipe.property.NanoForgeTieredProperty
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HastelloyN
import magicbook.gtlitecore.client.renderer.texture.GTLiteTextures
import magicbook.gtlitecore.common.block.GTLiteMetaBlocks
import magicbook.gtlitecore.common.block.blocks.BlockMetalCasing03
import magicbook.gtlitecore.common.metatileentity.GTLiteMetaTileEntities
import net.minecraft.client.resources.I18n
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextFormatting
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MetaTileEntityNanoForge(metaTileEntityId: ResourceLocation?) : RecipeMapMultiblockController(metaTileEntityId, NANO_FORGE_RECIPES)
{

    private var mainUpgradeNumber = 0

    private var forcePerfectOC = false

    init
    {
        recipeMapWorkable = NanoForgeRecipeLogic(this)
    }

    companion object
    {
        private val casingState
            get() = GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.NAQUADAH_ALLOY)
        private val secondCasingState
            get() = GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.TRINAQUADALLOY)
        private val thirdCasingState
            get() = GTLiteMetaBlocks.METAL_CASING_03.getState(BlockMetalCasing03.MetalCasingType.QUANTUM_ALLOY)
        private val fourthCasingState
            get() = MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_LINE_CASING)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MetaTileEntityNanoForge(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        mainUpgradeNumber += 1 // T1 structure for default.
        if (context.get<String>("MainStructureUpgradeT2") != null)
            mainUpgradeNumber += 1 // T2 structure upgrade
        if (context.get<String>("MainStructureUpgradeT3") != null)
            mainUpgradeNumber += 1 // T3 structure upgrade
    }

    override fun invalidate()
    {
        super.invalidate()
        mainUpgradeNumber = 0
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(SUBSTATION_INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        energyContainer = EnergyContainerList(inputEnergy)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("            CCCCC            ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("           CCCCCCC           ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "            FD#DF            ", "             D#D             ", "            DD#DD            ", "            DD#DD            ", "            DD#DD            ", "            DD#DD            ", "            DD#DD            ", "            DD#DD            ", "            DD#DD            ", "            DD#DD            ", "            FD#DF            ", "            FD#DF            ", "            FD#DF            ", "            FD#DF            ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "              F              ", "              F              ", "              F              ", "              F              ", "              F              ")
        .aisle("          CCCCCCCCC          ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "            FD#DF            ", "            FD#DF            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "           DD###DD           ", "           DD###DD           ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ")
        .aisle("          CCCCCCCCC          ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "            FD#DF            ", "            FD#DF            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "           DD###DD           ", "           DD###DD           ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ")
        .aisle("          CCCCCCCCC          ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "            FD#DF            ", "            FD#DF            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "           DD###DD           ", "           DD###DD           ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ")
        .aisle(" aaaaaa   CCCCCCCCC   cccccc ", "             D#D             ", "    ff       D#D             ", "  ff         D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "    ff      FD#DF            ", "  ff        FD#DF            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "    ff      D###D            ", "  ff       DD###DD           ", "           DD###DD           ", "            D###D            ", "            D###D            ", "            D###D            ", "    ff      D###D            ", "  ff        D###D            ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ")
        .aisle("aaaaaaaa   CCCCCCC   cccccccc", "      f      FDF             ", "             FDF             ", "             FDF             ", " f           FDF             ", "             FDF             ", "             FDF             ", "      f      FDF             ", "             FDF             ", "             FDF             ", " f          FD#DF            ", "             D#D             ", "            DD#DD            ", "      f     DD#DD            ", "            DD#DD            ", "            DD#DD            ", " f          DD#DD            ", "            DD#DD            ", "            DD#DD            ", "      f     DD#DD            ", "            FD#DF            ", "            FD#DF            ", " f          FD#DF            ", "            FD#DF            ", "             FDF             ", "      f      FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "              F              ", "              F              ", "              F              ", "              F              ", "              F              ")
        .aisle("aaaaaaaa    CCSCC    cccccccc", "                             ", "                             ", "                             ", "   bb                        ", "f  yy                   dd   ", "   bb  f                xx   ", "                        dd   ", "                             ", "                             ", "             FDF             ", "f            FDF             ", "       f     FDF        dd   ", "             FDF        xx   ", "   bb        FDF        dd   ", "   bb        FDF             ", "   bb        FDF             ", "f  bb        FDF             ", "       f     FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "f            FDF             ", "   bb  f                     ", "   yyf                       ", "   bb                        ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("aaaaaaaa             cccccccc", "   bb                   dd   ", "   bb                   dd   ", "   bb                   dd   ", "  bbbb                  dd   ", "f ybby                 dddd  ", "  bbbb f               xddx  ", "   bb                  dddd  ", "   bb                   dd   ", "   bb                   dd   ", "   bb                   dd   ", "f  bb                   dd   ", "   bb  f      D        dddd  ", "   bb         D        xddx  ", "  bbbb        D        dddd  ", "  bbbb        D              ", "  bbbb        D              ", "f bbbb        D              ", "   bb  f      D              ", "   bb         D              ", "   bb                        ", "   bb                        ", "   bb                        ", "f  bb                        ", "  bbbb f                     ", "  ybby                       ", "  bbbb                       ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("aaaaaaaa             cccccccc", "   bb                   dd   ", "   bb                   dd   ", "   bb                   dd   ", "  bbbb                  dd   ", "  ybby f               dddd  ", "f bbbb                 xddx  ", "   bb                  dddd  ", "   bb                   dd   ", "   bb                   dd   ", "   bb                   dd   ", "   bb  f                dd   ", "f  bb                  dddd  ", "   bb                  xddx  ", "  bbbb                 dddd  ", "  bbbb                       ", "  bbbb                       ", "  bbbb f                     ", "f  bb                        ", "   bb                        ", "   bb                        ", "   bb                        ", "   bb                        ", "   bb  f                     ", "f bbbb                       ", "  ybby                       ", "  bbbb                       ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("aaaaaaaa             cccccccc", "                             ", "                             ", "                             ", "   bb                        ", "   yy  f                dd   ", "f  bb                   xx   ", "                        dd   ", "                             ", "                             ", "                             ", "       f                     ", "f                       dd   ", "                        xx   ", "   bb                   dd   ", "   bb                        ", "   bb                        ", "   bb  f                     ", "f                            ", "                             ", "                             ", "                             ", "                             ", "       f                     ", "f  bb                        ", "  fyy                        ", "   bb                        ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle("aaaaaaaa             cccccccc", " f                           ", "                             ", "                             ", "      f                      ", "                             ", "                             ", " f                           ", "                             ", "                             ", "      f                      ", "                             ", "                             ", " f                           ", "                             ", "                             ", "      f                      ", "                             ", "                             ", " f                           ", "                             ", "                             ", "      f                      ", "                             ", "                             ", " f                           ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        .aisle(" aaaaaa               cccccc ", "                             ", "  ff                         ", "    ff                       ", "                             ", "                             ", "                             ", "                             ", "  ff                         ", "    ff                       ", "                             ", "                             ", "                             ", "                             ", "  ff                         ", "    ff                       ", "                             ", "                             ", "                             ", "                             ", "  ff                         ", "    ff                       ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
        // Multiblock controller
        .where('S', selfPredicate())
        // T1 Main structure
        .where('C', states(casingState)
            .setMinGlobalLimited(50)
            .or(abilities(MAINTENANCE_HATCH)
                .setExactLimit(1))
            .or(abilities(INPUT_ENERGY)
                .setMaxGlobalLimited(2))
            .or(abilities(INPUT_LASER)
                .setMaxGlobalLimited(1))
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS, IMPORT_FLUIDS)))
        .where('D', states(casingState))
        .where('F', frames(HastelloyN))
        // T2 Main structure
        .where('c', optionalStates("MainStructureUpgradeT2", secondCasingState))
        .where('d', optionalStates("MainStructureUpgradeT2", secondCasingState))
        .where('x', optionalStates("MainStructureUpgradeT2", fourthCasingState))
        // T3 Main structure
        .where('a', optionalStates("MainStructureUpgradeT3", thirdCasingState))
        .where('b', optionalStates("MainStructureUpgradeT3", thirdCasingState))
        .where('y', optionalStates("MainStructureUpgradeT3", fourthCasingState))
        .where('f', optionalStates("MainStructureUpgradeT3", MetaBlocks.FRAMES[Neutronium]!!.getBlock(Neutronium)))
        // Misc contents
        .where('#', air())
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.NAQUADAH_ALLOY_CASING

    @SideOnly(Side.CLIENT)
    override fun getMatchingShapes(): MutableList<MultiblockShapeInfo>
    {
        val shapeInfo: MutableList<MultiblockShapeInfo> = ArrayList()
        val builder = MultiblockShapeInfo.builder(LEFT, DOWN, FRONT)
            .aisle("                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
            .aisle("            CEMEC            ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
            .aisle("           CCCCCCC           ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "            FD#DF            ", "             D#D             ", "            DD#DD            ", "            DD#DD            ", "            DD#DD            ", "            DD#DD            ", "            DD#DD            ", "            DD#DD            ", "            DD#DD            ", "            DD#DD            ", "            FD#DF            ", "            FD#DF            ", "            FD#DF            ", "            FD#DF            ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "              F              ", "              F              ", "              F              ", "              F              ", "              F              ")
            .aisle("          CCCCCCCCC          ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "            FD#DF            ", "            FD#DF            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "           DD###DD           ", "           DD###DD           ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ")
            .aisle("          CCCCCCCCC          ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "            FD#DF            ", "            FD#DF            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "           DD###DD           ", "           DD###DD           ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ")
            .aisle("          CCCCCCCCC          ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "            FD#DF            ", "            FD#DF            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "           DD###DD           ", "           DD###DD           ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ")
            .aisle(" aaaaaa   CCCCCCCCC   cccccc ", "             D#D             ", "    ff       D#D             ", "  ff         D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "    ff      FD#DF            ", "  ff        FD#DF            ", "            D###D            ", "            D###D            ", "            D###D            ", "            D###D            ", "    ff      D###D            ", "  ff       DD###DD           ", "           DD###DD           ", "            D###D            ", "            D###D            ", "            D###D            ", "    ff      D###D            ", "  ff        D###D            ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "             D#D             ", "              D              ", "              D              ", "              D              ", "              D              ", "              D              ")
            .aisle("aaaaaaaa   CCCCCCC   cccccccc", "      f      FDF             ", "             FDF             ", "             FDF             ", " f           FDF             ", "             FDF             ", "             FDF             ", "      f      FDF             ", "             FDF             ", "             FDF             ", " f          FD#DF            ", "             D#D             ", "            DD#DD            ", "      f     DD#DD            ", "            DD#DD            ", "            DD#DD            ", " f          DD#DD            ", "            DD#DD            ", "            DD#DD            ", "      f     DD#DD            ", "            FD#DF            ", "            FD#DF            ", " f          FD#DF            ", "            FD#DF            ", "             FDF             ", "      f      FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "              F              ", "              F              ", "              F              ", "              F              ", "              F              ")
            .aisle("aaaaaaaa    IJSKC    cccccccc", "                             ", "                             ", "                             ", "   bb                        ", "f  yy                   dd   ", "   bb  f                xx   ", "                        dd   ", "                             ", "                             ", "             FDF             ", "f            FDF             ", "       f     FDF        dd   ", "             FDF        xx   ", "   bb        FDF        dd   ", "   bb        FDF             ", "   bb        FDF             ", "f  bb        FDF             ", "       f     FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "             FDF             ", "f            FDF             ", "   bb  f                     ", "   yyf                       ", "   bb                        ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
            .aisle("aaaaaaaa             cccccccc", "   bb                   dd   ", "   bb                   dd   ", "   bb                   dd   ", "  bbbb                  dd   ", "f ybby                 dddd  ", "  bbbb f               xddx  ", "   bb                  dddd  ", "   bb                   dd   ", "   bb                   dd   ", "   bb                   dd   ", "f  bb                   dd   ", "   bb  f      D        dddd  ", "   bb         D        xddx  ", "  bbbb        D        dddd  ", "  bbbb        D              ", "  bbbb        D              ", "f bbbb        D              ", "   bb  f      D              ", "   bb         D              ", "   bb                        ", "   bb                        ", "   bb                        ", "f  bb                        ", "  bbbb f                     ", "  ybby                       ", "  bbbb                       ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
            .aisle("aaaaaaaa             cccccccc", "   bb                   dd   ", "   bb                   dd   ", "   bb                   dd   ", "  bbbb                  dd   ", "  ybby f               dddd  ", "f bbbb                 xddx  ", "   bb                  dddd  ", "   bb                   dd   ", "   bb                   dd   ", "   bb                   dd   ", "   bb  f                dd   ", "f  bb                  dddd  ", "   bb                  xddx  ", "  bbbb                 dddd  ", "  bbbb                       ", "  bbbb                       ", "  bbbb f                     ", "f  bb                        ", "   bb                        ", "   bb                        ", "   bb                        ", "   bb                        ", "   bb  f                     ", "f bbbb                       ", "  ybby                       ", "  bbbb                       ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
            .aisle("aaaaaaaa             cccccccc", "                             ", "                             ", "                             ", "   bb                        ", "   yy  f                dd   ", "f  bb                   xx   ", "                        dd   ", "                             ", "                             ", "                             ", "       f                     ", "f                       dd   ", "                        xx   ", "   bb                   dd   ", "   bb                        ", "   bb                        ", "   bb  f                     ", "f                            ", "                             ", "                             ", "                             ", "                             ", "       f                     ", "f  bb                        ", "  fyy                        ", "   bb                        ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
            .aisle("aaaaaaaa             cccccccc", " f                           ", "                             ", "                             ", "      f                      ", "                             ", "                             ", " f                           ", "                             ", "                             ", "      f                      ", "                             ", "                             ", " f                           ", "                             ", "                             ", "      f                      ", "                             ", "                             ", " f                           ", "                             ", "                             ", "      f                      ", "                             ", "                             ", " f                           ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
            .aisle(" aaaaaa               cccccc ", "                             ", "  ff                         ", "    ff                       ", "                             ", "                             ", "                             ", "                             ", "  ff                         ", "    ff                       ", "                             ", "                             ", "                             ", "                             ", "  ff                         ", "    ff                       ", "                             ", "                             ", "                             ", "                             ", "  ff                         ", "    ff                       ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ", "                             ")
            .where('S', GTLiteMetaTileEntities.NANO_FORGE, EnumFacing.SOUTH)
            .where('C', casingState)
            .where('D', casingState)
            .where('F', MetaBlocks.FRAMES[HastelloyN]!!.getBlock(HastelloyN))
            .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[ULV], EnumFacing.NORTH)
            .where('I', MetaTileEntities.ITEM_IMPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('J', MetaTileEntities.ITEM_EXPORT_BUS[ULV], EnumFacing.SOUTH)
            .where('K', MetaTileEntities.FLUID_IMPORT_HATCH[ULV], EnumFacing.SOUTH)
            .where('M', MetaTileEntities.MAINTENANCE_HATCH, EnumFacing.NORTH)
            .where(' ', Blocks.AIR.defaultState)
            .where('#', Blocks.AIR.defaultState)
        shapeInfo.add(builder.build())
        shapeInfo.add(builder
            .where('c', secondCasingState)
            .where('d', secondCasingState)
            .where('x', fourthCasingState)
            .build())
        shapeInfo.add(builder
            .where('a', thirdCasingState)
            .where('b', thirdCasingState)
            .where('y', fourthCasingState)
            .where('f', MetaBlocks.FRAMES[Neutronium]!!.getBlock(Neutronium))
            .build())
        return shapeInfo
    }

    override fun addInformation(stack: ItemStack?, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.nano_forge.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.nano_forge.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.nano_forge.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.nano_forge.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.nano_forge.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.nano_forge.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.nano_forge.tooltip.7"))
    }

    override fun addDisplayText(textList: MutableList<ITextComponent>?)
    {
        MultiblockDisplayText.builder(textList, isStructureFormed)
            .addCustom { tl ->
                if (isStructureFormed)
                {
                    tl.add(translationWithColor(TextFormatting.GRAY,
                        "gtlitecore.machine.nano_forge.structure_info", mainUpgradeNumber))
                }
            }
            .setWorkingStatus(recipeMapWorkable.isWorkingEnabled, recipeMapWorkable.isActive)
            .addEnergyUsageLine(recipeMapWorkable.energyContainer)
            // .addEnergyTierLine(getTierByVoltage(recipeMapWorkable.maxVoltage).toInt())
            .addParallelsLine(recipeMapWorkable.parallelLimit)
            .addWorkingStatusLine()
            .addProgressLine(recipeMapWorkable.progressPercent)
    }

    override fun canBeDistinct() = true

    override fun shouldShowVoidingModeButton() = true

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        if (recipe.getProperty(NanoForgeTieredProperty.getInstance(), 0)!! < mainUpgradeNumber)
            forcePerfectOC = true // Push it enabled perfect OC to NanoForgeRecipeLogic.
        return super.checkRecipe(recipe, consumeIfSuccess)
                && recipe.getProperty(NanoForgeTieredProperty.getInstance(), 0)!! <= mainUpgradeNumber
    }

    private inner class NanoForgeRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity)
    {

        override fun getOverclockingDurationFactor() = if (forcePerfectOC) 0.25 else 0.5

        /**
         * Ignored maximum overclock voltage of energy hatches limit, let it be the maximum voltage
         * of the MTE because we need to consume huge energies for Nano Forge. This is a revert of
         * GTCEu pull request <a href="https://github.com/GregTechCEu/GregTech/pull/2139">#2139</a>.
         */
        override fun getMaximumOverclockVoltage() = maxVoltage

        /**
         * Ignored maximum overclock voltage of energy hatches limit, let it be the maximum voltage
         * of the MTE because we need to consume huge energies for Nano Forge. This is a revert of
         * GTCEu pull request <a href="https://github.com/GregTechCEu/GregTech/pull/2139">#2139</a>.
         */
        override fun getMaxVoltage(): Long
        {
            val energyContainer = energyContainer
            if (energyContainer is EnergyContainerList)
            {
                val voltage: Long
                val amperage: Long
                if (energyContainer.inputVoltage > energyContainer.outputVoltage)
                {
                    voltage = energyContainer.inputVoltage
                    amperage = energyContainer.inputAmperage
                }
                else
                {
                    voltage = energyContainer.outputVoltage
                    amperage = energyContainer.outputAmperage
                }

                return if (amperage == 1L)
                {
                    // amperage is 1 when the energy is not exactly on a tier
                    // the voltage for recipe search is always on tier, so take the closest lower tier
                    VOC[getFloorTierByVoltage(voltage).toInt()]
                }
                else
                {
                    // amperage != 1 means the voltage is exactly on a tier
                    // ignore amperage, since only the voltage is relevant for recipe search
                    // amps are never > 3 in an EnergyContainerList
                    voltage
                }
            }
            return max(energyContainer.inputVoltage.toDouble(),
                energyContainer.outputVoltage.toDouble()).toLong()
        }

        override fun getParallelLimit() = if (mainUpgradeNumber == 1) 64 else 256

    }

}