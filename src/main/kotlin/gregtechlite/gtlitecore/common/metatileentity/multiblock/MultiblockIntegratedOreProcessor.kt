package gregtechlite.gtlitecore.common.metatileentity.multiblock

import codechicken.lib.raytracer.CuboidRayTraceResult
import codechicken.lib.render.CCRenderState
import codechicken.lib.render.pipeline.IVertexOperation
import codechicken.lib.vec.Matrix4
import gregtech.api.capability.impl.EnergyContainerList
import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.MultiblockAbility.INPUT_LASER
import gregtech.api.metatileentity.multiblock.MultiblockAbility.SUBSTATION_INPUT_ENERGY
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.utils.TooltipHelper
import gregtech.core.sound.GTSoundEvents
import gregtechlite.gtlitecore.api.capability.logic.IntegratedOreProcessorRecipeLogic
import gregtechlite.gtlitecore.api.entity.GTLiteDamageSources
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.INTEGRATED_ORE_PROCESSOR_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.adapter.GTTurbineCasing
import gregtechlite.gtlitecore.common.block.variant.ActiveUniqueCasing
import gregtechlite.gtlitecore.common.block.variant.GlassCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockIntegratedOreProcessor(id: ResourceLocation)
    : RecipeMapMultiblockController(id, INTEGRATED_ORE_PROCESSOR_RECIPES)
{

    init
    {
        this.recipeMapWorkable = IntegratedOreProcessorRecipeLogic(this)
    }

    companion object
    {
        private val casingState = MetalCasing.VANADIUM_GALLIUM.state
        private val secondCasingState = MetalCasing.HASTELLOY_N.state
        private val thirdCasingState = MetalCasing.STELLITE.state
        private val fourthCasingState = GTMultiblockCasing.GRATE_CASING.state
        private val fifthCasingState = GTMultiblockCasing.EXTREME_ENGINE_INTAKE_CASING.state

        private val gearboxCasingState = GTTurbineCasing.TUNGSTENSTEEL_GEARBOX.state
        private val pipeCasingState = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state
        private val uniqueCasingState = ActiveUniqueCasing.HEAT_VENT.state

        private val glassState = GlassCasing.SILICON_CARBIDE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity): MetaTileEntity
        = MultiblockIntegratedOreProcessor(metaTileEntityId)

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(SUBSTATION_INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        energyContainer = EnergyContainerList(inputEnergy)
    }

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle(" JJJG     GJJJ " ,"  J G     G J  " ,"    G     G    " ,"    GG   GG    " ,"     DDDDD     " ,"               " ,"               " ,"               " ,"               ")
        .aisle("JFFFJDDDDDJFFFJ" ," FFFFDDDDDFFFF " ," FFFFDDDDDFFFF " ," FAFFDDDDDFFAF " ," FAFFDDDDDFFAF " ," FAFFFF FFFFAF " ," FAFF     FFAF " ," FAF       FAF " ," FFF       FFF ")
        .aisle("JFFFJDDDDDJFFFJ" ,"JFIFJ      FHFJ" ," FIF       FHF " ," AIF       FHA " ," AIFFDDDDDFFHA " ," AIFFJJGJJFFHA " ," AIFJ     JFHA " ," AIF       FHA " ," FFF       FFF ")
        .aisle("JFFFJDDDDDJFFFJ" ," FFFF     FFFF " ," FFFF     FFFF " ," FAFF     FFAF " ," FAFFDDDDDFFAF " ," FAFFFF FFFFAF " ," FAFF     FFAF " ," FAF       FAF " ," FFF       FFF ")
        .aisle(" JJJDDDDDDDJJJ " ,"  J D     D J  " ,"    D     D    " ,"    D     D    " ,"    DDDDDDD    " ,"               " ,"               " ,"               " ,"               ")
        .aisle("JFFFJDDDDDJFFFJ" ," FFFF     FFFF " ," FFFF     FFFF " ," FAFF     FFAF " ," FAFFDDDDDFFAF " ," FAFFFF FFFFAF " ," FAFF     FFAF " ," FAF       FAF " ," FFF       FFF ")
        .aisle("JFFFJDDDDDJFFFJ" ,"JFCFJ     JF FJ" ," FCF       F F " ," ACF       F A " ," ACFFDDDDDFF A " ," ACFFJJGJJFF A " ," ACFJ     JF A " ," ACF       F A " ," FFF       FFF ")
        .aisle("JFFFJDDDDDJFFFJ" ," FFFF     FFFF " ," FFFF     FFFF " ," FAFF     FFAF " ," FAFFDDDDDFFAF " ," FAFFFF FFFFAF " ," FAFF     FFAF " ," FAF       FAF " ," FFF       FFF ")
        .aisle(" JJJDDDDDDDJJJ " ,"  J D     D J  " ,"    D     D    " ,"    D     D    " ,"    DDDDDDD    " ,"               " ,"               " ,"               " ,"               ")
        .aisle("JFFFJDDDDDJFFFJ" ," FFFF     FFFF " ," FFFF     FFFF " ," FAFF     FFAF " ," FAFFDDDDDFFAF " ," FAFFFF FFFFAF " ," FAFF     FFAF " ," FAF       FAF " ," FFF       FFF ")
        .aisle("JFFFJDDDDDJFFFJ" ,"JFBFJ      FEFJ" ," FBF       FEF " ," ABF       FEA " ," ABFFDDDDDFFEA " ," ABFFJJGJJFFEA " ," ABFJ     JFEA " ," ABF       FEA " ," FFF       FFF ")
        .aisle("JFFFJDDDDDJFFFJ" ," FFFFDDDDDFFFF " ," FFFFDDSDDFFFF " ," FAFFDDDDDFFAF " ," FAFFDDDDDFFAF " ," FAFFFF FFFFAF " ," FAFF     FFAF " ," FAF       FAF " ," FFF       FFF ")
        .aisle(" JJJG     GJJJ " ,"  J G     G J  " ,"    G     G    " ,"    GG   GG    " ,"     DDDDD     " ,"               " ,"               " ,"               " ,"               ")
        .where('S', selfPredicate())
        .where('D', states(casingState)
            .setMinGlobalLimited(140)
            .or(autoAbilities(false, false, true, true, true, false, false))
            .or(abilities(INPUT_ENERGY)
                    .setMaxGlobalLimited(8)
                    .setPreviewCount(1))
            .or(abilities(INPUT_LASER)
                    .setMaxGlobalLimited(1)
                    .setPreviewCount(0)))
        .where('A', states(glassState))
        .where('B', states(gearboxCasingState))
        .where('C', states(fourthCasingState))
        .where('E', states(pipeCasingState))
        .where('F', states(secondCasingState))
        .where('G', frames(VanadiumGallium))
        .where('H', states(uniqueCasingState))
        .where('I', states(fifthCasingState))
        .where('J', states(thirdCasingState))
        .where(' ', any())
        .build()

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.VANADIUM_GALLIUM_CASING

    @SideOnly(Side.CLIENT)
    override fun getFrontOverlay(): ICubeRenderer = GTLiteOverlays.INTEGRATED_ORE_PROCESSOR_OVERLAY

    override fun renderMetaTileEntity(renderState: CCRenderState?, translation: Matrix4, pipeline: Array<IVertexOperation?>?)
    {
        super.renderMetaTileEntity(renderState, translation, pipeline)
        GTLiteTextures.ORE_PROCESSOR_CONTROLLER.renderSided(renderState, translation, pipeline, frontFacing, isStructureFormed, recipeMapWorkable.isActive)
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack?,
                                player: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.1"))
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.7"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.8"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.9"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.10"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.11"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.12"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.13"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.14"))
        tooltip.add(I18n.format("gtlitecore.machine.integrated_ore_processor.tooltip.15"))
        tooltip.add(I18n.format("gtlitecore.tooltip.machine.laser_hatch"))
    }

    override fun hasMaintenanceMechanics(): Boolean = false

    override fun getBreakdownSound(): SoundEvent = GTSoundEvents.BREAKDOWN_ELECTRICAL

    override fun onRightClick(playerIn: EntityPlayer, hand: EnumHand?, facing: EnumFacing?, hitResult: CuboidRayTraceResult?): Boolean
    {
        return onControllerInteract(playerIn) || super.onRightClick(playerIn, hand, facing, hitResult)
    }

    override fun onLeftClick(player: EntityPlayer, facing: EnumFacing?, hitResult: CuboidRayTraceResult?)
    {
        onControllerInteract(player)
    }

    override fun onWrenchClick(playerIn: EntityPlayer, hand: EnumHand?, wrenchSide: EnumFacing?, hitResult: CuboidRayTraceResult?): Boolean
    {
        return onControllerInteract(playerIn) || super.onWrenchClick(playerIn, hand, wrenchSide, hitResult)
    }

    override fun onScrewdriverClick(playerIn: EntityPlayer, hand: EnumHand?, facing: EnumFacing?, hitResult: CuboidRayTraceResult?): Boolean
    {
        return onControllerInteract(playerIn)
    }

    private fun onControllerInteract(player: EntityPlayer): Boolean
    {
        if (player.isCreative)
            return false

        if (!world.isRemote && isActive)
        {
            player.attackEntityFrom(GTLiteDamageSources.ORE_CRUSHING, 7.0F)
            return true
        }
        else
        {
            return false
        }
    }

}
