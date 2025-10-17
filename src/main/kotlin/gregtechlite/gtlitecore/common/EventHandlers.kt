package gregtechlite.gtlitecore.common

import gregtech.api.GregTechAPI
import gregtech.api.metatileentity.registry.MTEManager.MTERegistryEvent
import gregtech.api.unification.material.event.MaterialEvent
import gregtech.api.unification.material.event.MaterialRegistryEvent
import gregtech.api.unification.material.event.PostMaterialEvent
import gregtech.loaders.recipe.CraftingComponent
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeProperties
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialFlags
import gregtechlite.gtlitecore.api.unification.material.info.GTLiteMaterialIconSet
import gregtechlite.gtlitecore.api.unification.material.info.MaterialInfoCache
import gregtechlite.gtlitecore.api.unification.material.properties.AlloyBlastPropertyAdder
import gregtechlite.gtlitecore.api.unification.material.properties.GTLiteMaterialProperties
import gregtechlite.gtlitecore.api.unification.material.properties.GTLiteToolPropertyAdder
import gregtechlite.gtlitecore.api.unification.ore.GTLiteOrePrefix
import gregtechlite.gtlitecore.common.item.GTLiteToolItems
import gregtechlite.gtlitecore.loader.RecyclingDataLoader
import gregtechlite.gtlitecore.loader.WoodTypeEntryLoader
import gregtechlite.gtlitecore.loader.ore.OreDictionaryLoader
import gregtechlite.gtlitecore.loader.recipe.RecipeManager
import gregtechlite.gtlitecore.loader.recipe.component.CraftingComponents
import gregtechlite.gtlitecore.loader.recipe.handler.RecipeHandlers
import net.minecraft.item.crafting.IRecipe
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object EventHandlers
{

    @SubscribeEvent
    fun createMaterialRegistry(event: MaterialRegistryEvent) = GregTechAPI.materialManager.createRegistry(MOD_ID)

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun registerMaterials(event: MaterialEvent)
    {
        GTLiteOrePrefix.addToMetaItem()
        GTLiteMaterials.init()
        GTLiteOrePrefix.setOrePrefixInfos()
        GTLiteMaterialProperties.init()
        GTLiteToolPropertyAdder.initSoftToolProperties()
        GTLiteMaterialFlags.setMaterialFlags()
        GTLiteMaterialIconSet.setMaterialIconSets()
        GTLiteToolItems.registerTools()
        GTLiteToolItems.addToolSymbols()
        MaterialInfoCache.build()
    }

    @SubscribeEvent
    fun registerPostMaterials(event: PostMaterialEvent)
    {
        AlloyBlastPropertyAdder.preInitABSProperties()
        AlloyBlastPropertyAdder.initABSProperties()
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun registerMaterialComponents(event: GregTechAPI.RegisterEvent<CraftingComponent>)
    {
        CraftingComponents.setCraftingComponents()
    }

    @SubscribeEvent
    fun createMetaTileEntityRegistry(event: MTERegistryEvent) = GregTechAPI.mteManager.createRegistry(MOD_ID)

    @SubscribeEvent(priority = EventPriority.LOW)
    fun registerRecipes(event: RegistryEvent.Register<IRecipe>)
    {
        GTLiteRecipeProperties.init()
        RecyclingDataLoader.init()
        OreDictionaryLoader.init()
        WoodTypeEntryLoader.init()
        RecipeManager.init()
    }

    @SubscribeEvent
    fun registerRecipeHandlers(event: RegistryEvent.Register<IRecipe>)
    {
        RecipeHandlers.init()
    }

}