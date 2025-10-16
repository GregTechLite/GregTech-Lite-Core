package gregtechlite.gtlitecore.common.metatileentity.multiblock.generator

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.FuelMultiblockController
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH
import gregtech.api.metatileentity.multiblock.MultiblockAbility.OUTPUT_ENERGY
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.GTLiteAPI.NUCLEAR_REACTOR_CORE_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.nuclearReactorCores
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.NUCLEAR_FUELS
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

/**
 * Reactor Core buff factors:
 * - Thorium 1.05x
 * - Protactinium 1.1x
 * - Uranium 1.3x
 * - Neptunium 1.4x
 * - Plutonium 1.5x
 * - Americium 1.8x
 * - Curium 1.85x
 * - Berkelium 1.9x
 * - Californium 2.0x
 * - Einsteinium 2.5x
 * - Fermium 3x
 * - Mendelevium 3.2x
 */
class MultiblockNuclearReactor(id: ResourceLocation)
    : FuelMultiblockController(id, NUCLEAR_FUELS, EV)
{

    private var coreTier = 0

    init
    {
        recipeMapWorkable = NuclearReactorWorkableHandler(this)
        recipeMapWorkable.maximumOverclockVoltage = V[MAX]
    }

    companion object
    {
        private val casingState = MetalCasing.INCONEL_718.state
        private val secondCasingState = ActiveUniqueCasing.TEMPERATURE_CONTROLLER.state
        private val glassState = GlassCasing.LEAD_SILICON.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockNuclearReactor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        coreTier = context.getAttributeOrDefault(NUCLEAR_REACTOR_CORE_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        coreTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "DDD", "CGC", "CGC", "CGC", "CGC", "CGC", "CGC", "CCC")
        .aisle("CCC", "DDD", "GXG", "GXG", "GXG", "GXG", "GXG", "GXG", "COC")
        .aisle("CSC", "DDD", "CGC", "CGC", "CGC", "CGC", "CGC", "CGC", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(30)
            .or(abilities(OUTPUT_ENERGY)
                .setMinGlobalLimited(1)
                .setPreviewCount(1))
            .or(autoAbilities(false, true, true, true, true, true, false)))
        .where('D', states(secondCasingState))
        .where('G', states(glassState))
        .where('O', abilities(MUFFLER_HATCH))
        .where('X', nuclearReactorCores())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.INCONEL_718_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.NUCLEAR_REACTOR_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.nuclear_reactor.tooltip.7"))
    }

    override fun canBeDistinct() = false

    override fun hasMufflerMechanics() = true

    override fun getMufflerParticle(): EnumParticleTypes = EnumParticleTypes.CLOUD

    /**
     * Get boosted factor from [coreTier] to give reactor production a boost.
     */
    fun getBoostedFromCoreTier(tier: Int): Double = when (tier)
    {
        (1) -> 1.05 // Th
        (2) -> 1.1 // Pa
        (3) -> 1.3 // U
        (4) -> 1.4 // Np
        (5) -> 1.5 // Pu
        (6) -> 1.8 // Am
        (7) -> 1.85 // Cm
        (8) -> 1.9 // Bk
        (9) -> 2.0 // Cf
        (10) -> 2.5 // Es
        (11) -> 3.0 // Fm
        (12) -> 3.2 // Md
        else -> 0.0 // Ensure it not cause problem for workableHandler progress max value.
    }

    private inner class NuclearReactorWorkableHandler(mte: RecipeMapMultiblockController) : MultiblockFuelRecipeLogic(mte)
    {

        override fun getMaxVoltage() = max(super.getMaxVoltage().toDouble(), super.getMaxVoltage() * getBoostedFromCoreTier(coreTier)).toLong()

        override fun getParallelLimit() = Int.MAX_VALUE

    }

}