package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.EXPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.Recipe
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.logic.OverclockingLogic.PERFECT_DURATION_FACTOR
import gregtech.api.recipes.properties.RecipePropertyStorage
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
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.mode.OverclockMode
import gregtechlite.gtlitecore.api.translation.mode.UpgradeMode
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max
import kotlin.math.min

class MultiblockAdvancedFusionReactor(id: ResourceLocation) : RecipeMapMultiblockController(id, ADVANCED_FUSION_RECIPES)
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
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addDescriptionLine("gtlitecore.machine.advanced_fusion_reactor.tooltip.1",
                               "gtlitecore.machine.advanced_fusion_reactor.tooltip.2",
                               "gtlitecore.machine.advanced_fusion_reactor.tooltip.3",
                               "gtlitecore.machine.advanced_fusion_reactor.tooltip.4")
            addOverclockInfo(OverclockMode.PERFECT_DOUBLE)
            addMultiParallelInfo(UpgradeMode.FUSION_COIL, UpgradeMode.FUSION_DIVERTOR, number = 64)
            addMultiDurationInfo(UpgradeMode.FUSION_CASING, UpgradeMode.FUSION_VACUUM, UpgradeMode.FUSION_CRYOSTAT, percent = 400)
            addEnergyInfo(50)
            addLaserHatchInfo()
        }
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

        override fun getOverclockingDurationFactor() = PERFECT_DURATION_FACTOR / 2

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -50%
            ocResult.setEut(max(1, (ocResult.eut() * 0.5).toLong()))

            // +400% / fusion casing, vacuum and cryostat tier | D' = D / (1 + 4.0 * (T - 1.0)) = D / (4.0 * T - 3.0), where k = 4.0
            if (fusionCasingTier <= 0 || vacuumTier <= 0 || cryostatTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (4.0 * min(min(fusionCasingTier, vacuumTier), cryostatTier) - 3.0)).toInt()))
        }

        override fun getParallelLimit() = 64 * min(fusionCoilTier, divertorTier)

    }

}
