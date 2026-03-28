package gregtechlite.gtlitecore.common.metatileentity.multiblock.mega

import gregtech.api.metatileentity.MetaTileEntity
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity
import gregtech.api.metatileentity.multiblock.IMultiblockPart
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController
import gregtech.api.pattern.BlockPattern
import gregtech.api.pattern.FactoryBlockPattern
import gregtech.client.renderer.ICubeRenderer
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.RECOMBINATION_SUBMODULE_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SOLIDIFICATION_SUBMODULE_RECIPES
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteOverlays
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockMatterReshapingFramework(id: ResourceLocation)
    : MultiMapMultiblockController(id, arrayOf(SOLIDIFICATION_SUBMODULE_RECIPES, RECOMBINATION_SUBMODULE_RECIPES))
{

    companion object
    {
        private val casingState = MetalCasing.NEUTRONIUM.state
    }

    override fun createMetaTileEntity(te: IGregTechTileEntity): MetaTileEntity
        = MultiblockMatterReshapingFramework(metaTileEntityId)

    // TODO: Actual multiblock structure
    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("CCC", "CCC", "CCC")
        .aisle("CCC", "C C", "CCC")
        .aisle("CCC", "CSC", "CCC")
        .where('S', selfPredicate())
        .where('C', states(casingState)
            .or(autoAbilities()))
        .where(' ', any())
        .build()

    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteOverlays.NEUTRONIUM_CASING

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, player: World?, tooltip: MutableList<String>, advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.matter_reshaping_framework.tooltip.1"))
        tooltip.add(I18n.format("gtlitecore.machine.matter_reshaping_framework.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.matter_reshaping_framework.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.matter_reshaping_framework.tooltip.4"))
        tooltip.add(I18n.format("gtlitecore.machine.matter_reshaping_framework.tooltip.5"))
        tooltip.add(I18n.format("gtlitecore.machine.matter_reshaping_framework.tooltip.6"))
        tooltip.add(I18n.format("gtlitecore.machine.matter_reshaping_framework.tooltip.7"))
    }

}