package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.*
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.core.sound.GTSoundEvents
import gregtechlite.gtlitecore.api.GTLiteAPI.CRYOSTAT_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.DIVERTOR_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.FUSION_CASING_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.FUSION_COIL_TIER
import gregtechlite.gtlitecore.api.GTLiteAPI.VACUUM_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.cryostats
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.divertors
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.fusionCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.fusionCoils
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.fusionVacuums
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.ADVANCED_FUSION_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.pow

class MultiblockAdvancedFusionReactor(id: ResourceLocation) :
    RecipeMapMultiblockController(id, ADVANCED_FUSION_RECIPES)
{

    private var fusionCasingTier = 0
    private var fusionCoilTier = 0
    private var cryostatTier = 0
    private var divertorTier = 0
    private var vacuumTier = 0
    private var tier = 0

    init
    {
        recipeMapWorkable = AdvancedFusionRecipeLogic(this)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockAdvancedFusionReactor(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        fusionCasingTier = context.getAttributeOrDefault(FUSION_CASING_TIER, 0)
        fusionCoilTier = context.getAttributeOrDefault(FUSION_COIL_TIER, 0)
        cryostatTier = context.getAttributeOrDefault(CRYOSTAT_TIER, 0)
        divertorTier = context.getAttributeOrDefault(DIVERTOR_TIER, 0)
        vacuumTier = context.getAttributeOrDefault(VACUUM_TIER, 0)
        tier = minOf(fusionCasingTier, fusionCoilTier, cryostatTier, divertorTier, vacuumTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        fusionCasingTier = 0
        fusionCoilTier = 0
        cryostatTier = 0
        divertorTier = 0
        vacuumTier = 0
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        energyContainer = EnergyContainerList(inputEnergy)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("               ", "               ", "     cECEc     ", "     cECEc     ", "               ", "               ")
        .aisle("               ", "       C       ", "   icvvvvvci   ", "   icvvvvvci   ", "       C       ", "               ")
        .aisle("       C       ", "  C  ddddd  C  ", "  Cvv#####vvC  ", "  Cvv#####vvC  ", "  C  bbbbb  C  ", "       C       ")
        .aisle("   C   C   C   ", "   ddddddddd   ", " Iv#########vI ", " Iv#########vI ", "   bbbbbbbbb   ", "   C   C   C   ")
        .aisle("    C     C    ", "   ddd#C#ddd   ", " cv###vvv###vc ", " cv###vvv###vc ", "   bbb#C#bbb   ", "    C     C    ")
        .aisle("               ", "  dddC###Cddd  ", "cv###v#C#v###vc", "cv###v#C#v###vc", "  bbbC###Cbbb  ", "               ")
        .aisle("               ", "  dd##CCC##dd  ", "Ev##v#CXC#v##vE", "Ev##v#CXC#v##vE", "  bb##CCC##bb  ", "               ")
        .aisle("  CC       CC  ", " CddC#CCC#CddC ", "Cv##vCXXXCv##vC", "Cv##vCXXXCv##vC", " CbbC#CCC#CbbC ", "  CC       CC  ")
        .aisle("               ", "  dd##CCC##dd  ", "Ev##v#CXC#v##vE", "Ev##v#CXC#v##vE", "  bb##CCC##bb  ", "               ")
        .aisle("               ", "  dddC###Cddd  ", "cv###v#C#v###vc", "cv###v#C#v###vc", "  bbbC###Cbbb  ", "               ")
        .aisle("    C     C    ", "   ddd#C#ddd   ", " cv###vvv###vc ", " cv###vvv###vc ", "   bbb#C#bbb   ", "    C     C    ")
        .aisle("   C   C   C   ", "   ddddddddd   ", " Iv#########vI ", " Iv#########vI ", "   bbbbbbbbb   ", "   C   C   C   ")
        .aisle("       C       ", "  C  ddddd  C  ", "  Cvv#####vvC  ", "  Cvv#####vvC  ", "  C  bbbbb  C  ", "       C       ")
        .aisle("               ", "       C       ", "   icvvvvvci   ", "   icvvvvvci   ", "       S       ", "               ")
        .aisle("               ", "               ", "     cECEc     ", "     cECEc     ", "               ", "               ")
        .where('S', selfPredicate())
        .where('c', fusionCasings())
        .where('E', fusionCasings()
            .or(abilities(INPUT_ENERGY)
                    .setMaxGlobalLimited(16))
            .or(abilities(INPUT_LASER)
                    .setMaxGlobalLimited(16)))
        .where('I', fusionCasings()
            .or(abilities(IMPORT_FLUIDS)
                    .setMinGlobalLimited(1)
                    .setPreviewCount(8)))
        .where('i', fusionCasings()
            .or(abilities(EXPORT_FLUIDS)
                    .setMinGlobalLimited(2)
                    .setPreviewCount(8)))
        .where('b', fusionCasings())
        .where('X', fusionCoils())
        .where('C', cryostats())
        .where('d', divertors())
        .where('v', fusionVacuums())
        .where('#', air())
        .where(' ', any())

        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer
        = if (recipeMapWorkable.isActive) Textures.ACTIVE_FUSION_TEXTURE else Textures.FUSION_TEXTURE

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.ADVANCED_FUSION_REACTOR_OVERLAY

    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, world, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.7"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.8"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.9"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.10"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.11"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.12"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.13"))
        tooltip.add(I18n.format("gtlitecore.machine.advanced_fusion_reactor.tooltip.14"))
    }

    override fun canBeDistinct() = false

    override fun hasMaintenanceMechanics() = false

    override fun checkRecipe(recipe: Recipe, consumeIfSuccess: Boolean): Boolean
    {
        val recipeTier = recipe.getProperty(GTLiteRecipeProperties.ADVANCED_FUSION_TIER, 0)!!
        return super.checkRecipe(recipe, consumeIfSuccess)
                && fusionCasingTier >= recipeTier
                && fusionCoilTier >= recipeTier
                && cryostatTier >= recipeTier
                && divertorTier >= recipeTier
                && vacuumTier >= recipeTier
    }

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    private inner class AdvancedFusionRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = 0.125

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress(floor(maxProgress * 0.5.pow(min(fusionCasingTier,
                min(vacuumTier, cryostatTier)))).toInt())
        }

        override fun getParallelLimit() = 64 * min(fusionCoilTier, divertorTier)

    }

}
