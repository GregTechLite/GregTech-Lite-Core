package magicbook.gtlitecore.common

import gregtech.api.GregTechAPI
import gregtech.api.metatileentity.registry.MTEManager.MTERegistryEvent
import gregtech.api.unification.material.event.MaterialEvent
import gregtech.api.unification.material.event.MaterialRegistryEvent
import gregtech.api.unification.material.event.PostMaterialEvent
import gregtech.loaders.recipe.CraftingComponent
import magicbook.gtlitecore.api.unification.GTLiteMaterials
import magicbook.gtlitecore.api.unification.material.GTLiteMaterialFlags
import magicbook.gtlitecore.api.unification.material.properties.AlloyBlastProperty
import magicbook.gtlitecore.api.unification.material.properties.AlloyBlastPropertyAdder
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix
import magicbook.gtlitecore.api.utils.GTLiteValues
import magicbook.gtlitecore.common.item.GTLiteToolItems
import magicbook.gtlitecore.loader.MaterialInfoLoader
import magicbook.gtlitecore.loader.OreDictionaryLoader
import magicbook.gtlitecore.loader.recipe.RecipeManager
import magicbook.gtlitecore.loader.recipe.component.CraftingComponents
import magicbook.gtlitecore.loader.recipe.handler.RecipeHandlers
import net.minecraft.item.crafting.IRecipe
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class EventHandlers
{

    @SubscribeEvent
    fun createMaterialRegistry(event: MaterialRegistryEvent)
        = GregTechAPI.materialManager.createRegistry(GTLiteValues.MODID)

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun registerMaterials(event: MaterialEvent)
    {
        GTLiteOrePrefix.addToMetaItem()
        GTLiteOrePrefix.setOrePrefixInfos()
        GTLiteMaterials.setMaterialProperties()
        GTLiteMaterialFlags.setMaterialFlags()
        GTLiteToolItems.registerTools()
        GTLiteToolItems.addToolSymbols()
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
    fun createMetaTileEntityRegistry(event: MTERegistryEvent)
        = GregTechAPI.mteManager.createRegistry(GTLiteValues.MODID)

    @SubscribeEvent(priority = EventPriority.LOW)
    fun registerRecipes(event: RegistryEvent.Register<IRecipe>)
    {
        MaterialInfoLoader.init()
        OreDictionaryLoader.init()
        RecipeManager.init()
        GTLiteOrePrefix.postSetOrePrefixInfos()
    }

    @SubscribeEvent
    fun registerRecipeHandlers(event: RegistryEvent.Register<IRecipe>)
    {
        RecipeHandlers.init()
    }

}