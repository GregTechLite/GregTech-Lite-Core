package gregtechlite.gtlitecore.common.metatileentity.multiblock

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
import gregtech.api.unification.material.Materials.TungstenSteel
import gregtech.client.renderer.ICubeRenderer
import gregtech.client.utils.TooltipHelper
import gregtechlite.gtlitecore.api.GTLiteAPI.COMPONENT_CASING_TIER
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.borosilicateGlasses
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.componentCasings
import gregtechlite.gtlitecore.api.pattern.TraceabilityPredicates.getAttributeOrDefault
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.COMPONENT_ASSEMBLY_LINE_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.HSLASteel
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.block.adapter.GTBoilerCasing
import gregtechlite.gtlitecore.common.block.adapter.GTMultiblockCasing
import gregtechlite.gtlitecore.common.block.variant.MetalCasing
import gregtechlite.gtlitecore.common.block.variant.MultiblockCasing
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MultiblockComponentAssemblyLine(id: ResourceLocation)
    : RecipeMapMultiblockController(id, COMPONENT_ASSEMBLY_LINE_RECIPES)
{

    private var casingTier = 0

    init
    {
        this.recipeMapWorkable = MultiblockRecipeLogic(this, true)
    }

    companion object
    {
        private val casingState
            get() = MetalCasing.IRIDIUM.state

        private val secondCasingState
            get() = GTMultiblockCasing.ASSEMBLY_LINE_CASING.state

        private val thirdCasingState
            get() = MultiblockCasing.ADVANCED_FILTER_CASING.state

        private val pipeCasingState
            get() = GTBoilerCasing.TUNGSTENSTEEL_PIPE.state
    }

    override fun createMetaTileEntity(tileEntity: IGregTechTileEntity) = MultiblockComponentAssemblyLine(metaTileEntityId)

    override fun formStructure(context: PatternMatchContext)
    {
        super.formStructure(context)
        this.casingTier = context.getAttributeOrDefault(COMPONENT_CASING_TIER, 0)
    }

    override fun invalidateStructure()
    {
        super.invalidateStructure()
        this.casingTier = 0
    }

    override fun initializeAbilities()
    {
        super.initializeAbilities()
        val inputEnergy = ArrayList(getAbilities(INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(SUBSTATION_INPUT_ENERGY))
        inputEnergy.addAll(getAbilities(INPUT_LASER))
        this.energyContainer = EnergyContainerList(inputEnergy)
    }

    // @formatter:off

    override fun createStructurePattern(): BlockPattern = FactoryBlockPattern.start()
        .aisle("HHHHHHHHH", "H  KKK  H", "H       H", "H       H", "H       H", "H       H", "HH     HH", " HHHHHHH ", "         ", "         ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " ELHHHLE ", "         ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A D   D A", "AC     CA", "AC     CA", "HC     CH", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A  n n  A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "AG     GA", "AG HHH GA", "AG     GA", "AG     GA", "AG  C  GA", "HGG D GGH", "E GGDGG E", " EL   LE ", "   BBB   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A D   D A", "AC     CA", "AC     CA", "HC     CH", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "HG     GH", "HG HHH GH", "HG     GH", "HG     GH", "HG  C  GH", "HGG D GGH", "E GGDGG E", " EL   LE ", "   BBB   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A D   D A", "AC     CA", "AC     CA", "HC     CH", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "AG     GA", "AG HHH GA", "AG     GA", "AG     GA", "AG  C  GA", "HGG D GGH", "E GGDGG E", " EL   LE ", "   BBB   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A D   D A", "AC     CA", "AC     CA", "HC     CH", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "HG     GH", "HG HHH GH", "HG     GH", "HG     GH", "HG  C  GH", "HGG D GGH", "E GGDGG E", " EL   LE ", "   BBB   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A D   D A", "AC     CA", "AC     CA", "HC     CH", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "AG     GA", "AG HHH GA", "AG     GA", "AG     GA", "AG  C  GA", "HGG D GGH", "E GGDGG E", " EL   LE ", "   BBB   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A D   D A", "AC     CA", "AC     CA", "HC     CH", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "HG     GH", "HG HHH GH", "HG     GH", "HG     GH", "HG  C  GH", "HGG D GGH", "E GGDGG E", " EL   LE ", "   BBB   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A D   D A", "AC     CA", "AC     CA", "HC     CH", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "AG     GA", "AG HHH GA", "AG     GA", "AG     GA", "AG  C  GA", "HGG D GGH", "E GGDGG E", " EL   LE ", "   BBB   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A D   D A", "AC     CA", "AC     CA", "HC     CH", "E       E", " EL   LE ", "   HBH   ")
        .aisle("MHHHHHHHM", "A       A", "A  HHH  A", "A       A", "A       A", "A       A", "H       H", "E       E", " ELHHHLE ", "         ")
        .aisle("HHHHHHHHH", "H  N N  H", "H  JJJ  H", "H  JJJ  H", "H       H", "H       H", "HH III HH", " HHISIHH ", "   III   ", "         ")
        .where('S', selfPredicate())
        .where('A', borosilicateGlasses())
        .where('B', componentCasings())
        .where('C', states(secondCasingState))
        .where('D', frames(HSLASteel))
        .where('E', states(thirdCasingState))
        .where('G', states(pipeCasingState))
        .where('H', states(casingState))
        .where('I', states(casingState)
            .or(abilities(MAINTENANCE_HATCH)
                .setExactLimit(1)
                .setPreviewCount(1)))
        .where('J', states(casingState)
            .or(abilities(IMPORT_ITEMS)
                .setMaxGlobalLimited(6)
                .setPreviewCount(3))
            .or(abilities(IMPORT_FLUIDS)
                .setMaxGlobalLimited(6)
                .setPreviewCount(3)))
        .where('K', states(casingState)
            .or(abilities(EXPORT_ITEMS)
                .setMaxGlobalLimited(3)
                .setPreviewCount(1)))
        .where('L', states(casingState)
            .or(abilities(INPUT_ENERGY)
                .setPreviewCount(1))
            .or(abilities(INPUT_LASER)
                .setMaxGlobalLimited(1)
                .setPreviewCount(0)))
        .where('M', states(casingState)
            .or(abilities(IMPORT_ITEMS)
                .setMaxGlobalLimited(6)
                .setPreviewCount(0))
            .or(abilities(IMPORT_FLUIDS)
                .setMaxGlobalLimited(6)
                .setPreviewCount(0)))
        .where('N', frames(TungstenSteel)
            .or(abilities(IMPORT_ITEMS)
                .setMaxGlobalLimited(2)
                .setPreviewCount(0))
            .or(abilities(IMPORT_FLUIDS)
                .setMaxGlobalLimited(2)
                .setPreviewCount(0)))
        .where('n', frames(TungstenSteel))
        .build()

    // @formatter:on

    @SideOnly(Side.CLIENT)
    override fun getBaseTexture(sourcePart: IMultiblockPart?): ICubeRenderer = GTLiteTextures.IRIDIUM_CASING

    override fun addInformation(stack: ItemStack,
                                player: World?,
                                tooltip: MutableList<String>,
                                advanced: Boolean)
    {
        super.addInformation(stack, player, tooltip, advanced)
        tooltip.add(I18n.format("gtlitecore.machine.component_assembly_line.tooltip.1"))
        tooltip.add(TooltipHelper.RAINBOW_SLOW.toString() + I18n.format("gregtech.machine.perfect_oc"))
        tooltip.add(I18n.format("gtlitecore.machine.component_assembly_line.tooltip.2"))
        tooltip.add(I18n.format("gtlitecore.machine.component_assembly_line.tooltip.3"))
        tooltip.add(I18n.format("gtlitecore.machine.component_assembly_line.tooltip.4"))
    }

    override fun canBeDistinct() = true

    override fun checkRecipe(recipe: Recipe,
                             consumeIfSuccess: Boolean): Boolean
    {
        return (super.checkRecipe(recipe, consumeIfSuccess)
                && recipe.getProperty(GTLiteRecipeProperties.COMPONENT_ASSEMBLY_LINE_TIER, 0)!! <= casingTier)
    }

}
