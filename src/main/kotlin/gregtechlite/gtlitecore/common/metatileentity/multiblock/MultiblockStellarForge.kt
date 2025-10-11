package gregtechlite.gtlitecore.common.metatileentity.multiblock

import gregtech.api.GTValues.FALLBACK
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.*
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.client.renderer.ICubeRenderer
import gregtech.common.blocks.BlockGlassCasing
import gregtech.common.blocks.MetaBlocks
import gregtechlite.gtlitecore.api.GTLiteAPI.EMITTER_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.FIELD_GEN_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.emitterCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.fieldGenCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.STELLAR_FORGE_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bedrockium
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import kotlin.math.floor
import kotlin.math.pow

class MultiblockStellarForge(id: ResourceLocation)
    : RecipeMapMultiblockController(id, STELLAR_FORGE_RECIPES)
{

    private var emitterCasingTier = 0
    private var fieldGenCasingTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = StellarForgeRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.QUANTUM_ALLOY.state
        private val secondCasingState = MultiblockCasing.STELLAR_CONTAINMENT_CASING.state
        private val glassState = MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.FUSION_GLASS)
        private val coilState = MultiblockCasing.THERMAL_ENERGY_TRANSMISSION_CASING.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockStellarForge(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        emitterCasingTier = context.getAttributeOrDefault(EMITTER_CASING_TIER, 0)
        fieldGenCasingTier = context.getAttributeOrDefault(FIELD_GEN_CASING_TIER, 0)
        tier = minOf(emitterCasingTier, fieldGenCasingTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        emitterCasingTier = 0
        fieldGenCasingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ")
        .aisle("        A A        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        AAA        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        AAA        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        A A        ")
        .aisle("        A A        ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "        A A        ")
        .aisle("        A A        ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "        A A        ")
        .aisle("        A A        ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "        A A        ")
        .aisle("        A A        ", " A A           A A ", "  C C   EEE   C C  ", " A A    EFE    A A ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", " A A    EFE    A A ", "  C C   EEE   C C  ", " A A           A A ", "        A A        ")
        .aisle("        A A        ", " A A    EEE    A A ", "  C C  E   E  C C  ", " A A   E   E   A A ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", " A A   E   E   A A ", "  C C  E   E  C C  ", " A A    EEE    A A ", "        A A        ")
        .aisle("        A A        ", "A A    EEEEE    A A", " C C  E     E  C C ", "A A   E     E   A A", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "A A   E     E   A A", " C C  E     E  C C ", "A A    EEEEE    A A", "        A A        ")
        .aisle(" AAAAAAA   AAAAAAA ", "Q A   EEEEEEE   A Q", "QC C E       E C CQ", "Q A  E       E  A Q", " A   E       E   A ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " A   E       E   A ", "Q A  E       E  A Q", "QC C E       E C CQ", "Q A   EEEEEEE   A Q", " AAAAAAAA AAAAAAAA ")
        .aisle("         G         ", "Q A   EEEHEEE   A Q", " C C E       E C C ", "Q A  F       F  A Q", " A   F       F   A ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", "     F       F     ", " A   F       F   A ", "Q A  F       F  A Q", " C C E       E C C ", "Q A   EEEHEEE   A Q", "         G         ")
        .aisle(" AAAAAAA   AAAAAAA ", "Q A   EEEEEEE   A Q", "QC C E       E C CQ", "Q A  E       E  A Q", " A   E       E   A ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " D   E       E   D ", " A   E       E   A ", "Q A  E       E  A Q", "QC C E       E C CQ", "Q A   EEEEEEE   A Q", " AAAAAAAA AAAAAAAA ")
        .aisle("        A A        ", "A A    EEEEE    A A", " C C  E     E  C C ", "A A   E     E   A A", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "      E     E      ", "A A   E     E   A A", " C C  E     E  C C ", "A A    EEEEE    A A", "        A A        ")
        .aisle("        A A        ", " A A    EEE    A A ", "  C C  E   E  C C  ", " A A   E   E   A A ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", "       E   E       ", " A A   E   E   A A ", "  C C  E   E  C C  ", " A A    EEE    A A ", "        A A        ")
        .aisle("        A A        ", " A A           A A ", "  C C   EEE   C C  ", " A A    EFE    A A ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", "        EFE        ", " A A    EFE    A A ", "  C C   EEE   C C  ", " A A           A A ", "        A A        ")
        .aisle("        A A        ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "  A A         A A  ", "   C CC     CC C   ", "  A A         A A  ", "        A A        ")
        .aisle("        A A        ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "   A AA     AA A   ", "    C  CCCCC  C    ", "   A AA     AA A   ", "        A A        ")
        .aisle("        A A        ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "    A  AAAAA  A    ", "     CC     CC     ", "    A  AAAAA  A    ", "        A A        ")
        .aisle("        A A        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        AAA        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        D D        ", "        AAA        ", "     AA     AA     ", "       CCCCC       ", "     AA     AA     ", "        A A        ")
        .aisle("                   ", "       AQQQA       ", "        QSQ        ", "       AQQQA       ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "                   ", "       AQQQA       ", "        Q Q        ", "       AQQQA       ", "                   ")
        .where('S', selfPredicate())
        // Both `A` and `Q` can put hatches but Energy Hatches can only replace `Q`,
        // because then we can see that one structure can hold almost 8 controller.
        .where('A', states(casingState)
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS, IMPORT_FLUIDS, EXPORT_FLUIDS)))
        .where('Q', states(casingState)
            .or(abilities(INPUT_ENERGY)
                .setMinGlobalLimited(1)
                .setMaxGlobalLimited(3)
                .setPreviewCount(1))
            .or(abilities(IMPORT_ITEMS, EXPORT_ITEMS, IMPORT_FLUIDS, EXPORT_FLUIDS)))
        .where('D', frames(Bedrockium))
        .where('E', states(secondCasingState))
        .where('F', states(glassState))
        .where('C', states(coilState))
        .where('G', fieldGenCasings())
        .where('H', emitterCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    override fun getBaseTexture(texture: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.QUANTUM_ALLOY_CASING

    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.STELLAR_FORGE_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine("SF")
            addDescriptionLine(true,
                               "gtlitecore.machine.stellar_forge.tooltip.1",
                               "gtlitecore.machine.stellar_forge.tooltip.2")
            overclockInfo(FALLBACK)
            durationInfo(UpgradeType.EMITTER_CASING, 50)
            parallelInfo(UpgradeType.FIELD_GEN_CASING, 32)
        }
    }

    override fun canBeDistinct() = true

    override fun hasMaintenanceMechanics() = false

    private inner class StellarForgeRecipeLogic(metaTileEntity: RecipeMapMultiblockController?) : MultiblockRecipeLogic(metaTileEntity, true)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.5.pow(emitterCasingTier)).toInt())
        }

        override fun getParallelLimit() =  32 * fieldGenCasingTier

    }

}
