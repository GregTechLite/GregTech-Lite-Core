package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.recipes.logic.OCResult
import gregtech.api.recipes.properties.RecipePropertyStorage
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LAMINATOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.TOOL_CASTER_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.VULCANIZATION_RECIPES
import gregtechlite.gtlitecore.api.metatileentity.multiblock.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.metatileentity.multiblock.OverclockMode
import gregtechlite.gtlitecore.api.metatileentity.multiblock.UpgradeMode
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.max

class MultiblockFluidSolidifier(id: ResourceLocation) : MultiMapMultiblockController(id, arrayOf(FLUID_SOLIDFICATION_RECIPES, TOOL_CASTER_RECIPES,
                                                                                                 LAMINATOR_RECIPES, VULCANIZATION_RECIPES))
{

    private var casingTier = 0

    init
    {
        recipeMapWorkable = LargeFluidSolidifierRecipeLogic(this)
    }

    companion object
    {
        private val casingState = GTMetalCasing.STEEL_SOLID.state
        private val pipeCasingState = GTBoilerCasing.STEEL_PIPE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockFluidSolidifier(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        casingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        casingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC")
        .aisle("CCC", "CPC", "CCC")
        .aisle("CCC", "CPC", "CCC")
        .aisle("QQQ", "QSQ", "QQQ")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(2)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('Q', states(pipeCasingState))
        .where('P', pumpCasings())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = Textures.SOLID_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.FLUID_SOLIDIFIER_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine()
            addOverclockInfo(OverclockMode.PERFECT)
            addParallelInfo(UpgradeMode.VOLTAGE_TIER, 4)
            addDurationInfo(UpgradeMode.PUMP_CASING, 150)
            addEnergyInfo(10)
        }
    }

    override fun canBeDistinct() = true

    private inner class LargeFluidSolidifierRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte, true)
    {

        override fun modifyOverclockPost(ocResult: OCResult, storage: RecipePropertyStorage)
        {
            super.modifyOverclockPost(ocResult, storage)

            // -10% / voltage tier
            ocResult.setEut(max(1, (ocResult.eut() * 0.9).toLong()))

            // +150% / pump casing tier | D' = D / (1 + 1.5 * (T - 1)) = D / (1.5 * T - 0.5), where k = 1.5
            if (casingTier <= 0) return
            ocResult.setDuration(max(1, (ocResult.duration() * 1.0 / (1.5 * casingTier - 0.5)).toInt()))
        }

        override fun getParallelLimit() = 4 * getTierByVoltage(maxVoltage)

    }

}
