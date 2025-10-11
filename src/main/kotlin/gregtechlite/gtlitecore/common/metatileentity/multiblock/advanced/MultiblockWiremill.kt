package gregtechlite.gtlitecore.common.metatileentity.multiblock.advanced

import gregtech.api.GTValues.UV
import gregtech.api.GTValues.V
import gregtech.api.capability.impl.MultiblockRecipeLogic
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.pattern.PatternMatchContext
import gregtech.api.recipes.RecipeMaps.WIREMILL_RECIPES
import gregtech.api.util.GTUtility.getTierByVoltage
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.renderer.texture.Textures
import gregtechlite.gtlitecore.api.GTLiteAPI.MOTOR_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.motorCasings
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.LOOM_RECIPES
import gregtechlite.gtlitecore.api.translation.MultiblockTooltipBuilder.Companion.addTooltip
import gregtechlite.gtlitecore.api.translation.UpgradeType
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.adapter.GTTurbineCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import kotlin.math.floor
import kotlin.math.pow

class MultiblockWiremill(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(WIREMILL_RECIPES, LOOM_RECIPES))
{

    private var casingTier = 0

    init
    {
        recipeMapWorkable = LargeWiremillRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.BLUE_STEEL.state

        private val gearboxCasingState = GTTurbineCasing.TITANIUM_GEARBOX.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockWiremill(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        casingTier = context.getAttributeOrDefault(MOTOR_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        casingTier = 0
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCCCC", "CCCCC", "CCCCC")
        .aisle("CCCCC", "CMGMC", "CCCCC")
        .aisle("CCCCC", "CMGMC", "CCCCC")
        .aisle("CCC  ", "CSC  ", "CCC  ")
        .where('S', this.selfPredicate())
        .where('C', states(casingState)
            .setMinGlobalLimited(8)
            .or(autoAbilities(true, true, true, true, true, false, false)))
        .where('G', states(gearboxCasingState))
        .where('M', motorCasings())
        .where(' ', any())
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(texture: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.BLUE_STEEL_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = Textures.PROCESSING_ARRAY_OVERLAY

    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        addTooltip(tooltip)
        {
            addMachineTypeLine("LW")
            addDescriptionLine(true)
            overclockInfo(UV)
            durationInfo(UpgradeType.MOTOR_CASING, 80)
            parallelInfo(UpgradeType.VOLTAGE_TIER, 16)
        }
    }

    override fun canBeDistinct() = true

    private inner class LargeWiremillRecipeLogic(mte: RecipeMapMultiblockController?) : MultiblockRecipeLogic(mte)
    {

        override fun getOverclockingDurationFactor() = if (maxVoltage >= V[UV]) 0.25 else 0.5

        override fun setMaxProgress(maxProgress: Int)
        {
            super.setMaxProgress((floor(maxProgress * 0.8.pow(casingTier))).toInt())
        }

        override fun getParallelLimit() = 16 * getTierByVoltage(maxVoltage)

    }

}