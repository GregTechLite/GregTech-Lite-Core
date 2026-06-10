package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.unification.material.Materials.Steel
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.CONVEYOR_CASING_TIER
import gregtechlite.gtlitecore.api.metatileentity.multiblock.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.metatileentity.multiblock.OverclockMode
import gregtechlite.gtlitecore.api.metatileentity.multiblock.UpgradeMode
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.conveyorCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOB_COLLECTING_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOB_SLAUGHTERING_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTGlassCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockSlaughter(id: ResourceLocation) : MultiMapMultiblockController(id, arrayOf(MOB_COLLECTING_RECIPES, MOB_SLAUGHTERING_RECIPES))
{
    private var casingTier = 0

    init
    {
        recipeMapWorkable = LargeSlaughterRecipeLogic(this)
    }

    companion object
    {
        private val casingState = GTMetalCasing.STEEL_SOLID.state
        private val secondCasingState = GTMultiblockCasing.GRATE_CASING.state
        private val uniqueCasingState = ActiveUniqueCasing.CRUSHING_WHEEL.state

        private val glassState = GTGlassCasing.TEMPERED_GLASS.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity = MultiblockSlaughter(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        casingTier = context.getAttributeOrDefault(CONVEYOR_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        casingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("FCCCF", "FDCDF", "FGGGF", "FGGGF", "FDCDF")
        .aisle("CCCCC", "DWWWD", "G###G", "G###G", "DCCCD")
        .aisle("CCCCC", "OWWWO", "G###G", "G###G", "CCCCC")
        .aisle("CCCCC", "DWWWD", "G###G", "G###G", "DCCCD")
        .aisle("FCCCF", "FDSDF", "FGGGF", "FGGGF", "FDCDF")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(9)
            .or(autoAbilities()))
        .where('D', states(secondCasingState))
        .where('W', states(uniqueCasingState))
        .where('G', states(glassState))
        .where('F', frames(Steel))
        .where('O', conveyorCasings())
        .where('#', air())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.SOLID_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.LARGE_SLAUGHTER_OVERLAY

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addOverclockInfo(OverclockMode.PERFECT)
            addParallelInfo(UpgradeMode.CONVEYOR_CASING, 8)
            addDurationInfo(UpgradeMode.CONVEYOR_CASING, 200)
            addEnergyInfo(10)
        }
    }

    override fun canBeDistinct(): Boolean = true

    private inner class LargeSlaughterRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte)
    {

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -10%
            ocResult.setEut(max(1, (ocResult.eut() * 0.9).toLong()))

            // +200% / casing tier | D' = D / (1 + 2.0 * (T - 1.0)), where k = 2.0
            if (casingTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (2.0 * casingTier - 1.0)).toInt()))
        }

        override fun getParallelLimit(): Int = 8 * casingTier
    }

}