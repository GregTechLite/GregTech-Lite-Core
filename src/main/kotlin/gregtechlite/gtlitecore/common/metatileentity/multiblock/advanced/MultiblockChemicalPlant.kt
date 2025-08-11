package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.block.IHeatingCoilBlockStats
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.LARGE_CHEMICAL_RECIPES
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtech.client.utils.TooltipHelper
import gregtech.common.blocks.BlockWireCoil
import gregtechlite.gtlitecore.api.GTLiteAPI.PUMP_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.HEATING_COIL_STATS
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.pumpCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.CHEMICAL_PLANT_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.variant.BoilerCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockChemicalPlant(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(LARGE_CHEMICAL_RECIPES, CHEMICAL_PLANT_RECIPES))
{

    private var pumpCasingTier = 0
    private var coilTier = 0
    private var tier = 0

    companion object
    {
        private val casingState
            get() = MetalCasing.POLYBENZIMIDAZOLE.state

        private val pipeCasingState
            get() = BoilerCasing.POLYBENZIMIDAZOLE.state
    }

    init
    {
        this.recipeMapWorkable = ChemicalPlantRecipeLogic(this)
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockChemicalPlant(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.pumpCasingTier = context.getAttributeOrDefault(PUMP_CASING_TIER, 0)

        val coilType = context.get<Any>(HEATING_COIL_STATS)
        if (coilType is IHeatingCoilBlockStats)
        {
            this.coilTier = coilType.tier
        }
        else
        {
            this.coilTier = BlockWireCoil.CoilType.CUPRONICKEL.tier
        }

        this.tier = minOf(pumpCasingTier, coilTier)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.pumpCasingTier = 0
        this.coilTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("C   C", "CCCCC", "C   C", "CCCCC", "C   C")
        .aisle("CCCCC", "CHHHC", "CQQQC", "CHHHC", "CCCCC")
        .aisle("C   C", "CQQQC", "CPQPC", "CQQQC", "C   C")
        .aisle("CCCCC", "CHHHC", "CQQQC", "CHHHC", "CCCCC")
        .aisle("C   C", "SCCCC", "C   C", "CCCCC", "C   C")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(20)
            .or(autoAbilities(true, true, true, true, true, true, false)))
        .where('Q', states(pipeCasingState))
        .where('H', heatingCoils())
        .where('P', pumpCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.POLYBENZIMIDAZOLE_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.CHEMICAL_REACTOR_OVERLAY

    override fun addInformation(stack: ItemStack?, player: World?, tooltip: MutableList<String?>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.chemical_plant.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.chemical_plant.tooltip.2"))
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.chemical_plant.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.chemical_plant.tooltip.4"))
    }

    override fun canBeDistinct() = true

    private inner class ChemicalPlantRecipeLogic(mte: RecipeMapMultiblockController) : MultiblockRecipeLogic(mte, true)
    {

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.5.pow(coilTier))).toInt())
        }

        override fun getParallelLimit() = 16 * pumpCasingTier

    }

}
