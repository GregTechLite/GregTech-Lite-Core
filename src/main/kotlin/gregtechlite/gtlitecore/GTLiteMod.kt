package gregtechlite.gtlitecore

import gregtechlite.gtlitecore.api.GTLiteAPI
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.MOD_NAME
import gregtechlite.gtlitecore.api.MOD_VERSION
import gregtechlite.gtlitecore.core.module.GTLiteModules
import gregtechlite.gtlitecore.core.module.ModuleContainerRegistryEvent
import gregtechlite.gtlitecore.core.module.ModuleManagerImpl
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.ForgeVersion
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLInterModComms
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent
import net.minecraftforge.fml.common.event.FMLServerStartedEvent
import net.minecraftforge.fml.common.event.FMLServerStartingEvent
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent

@Mod(modid = MOD_ID,
     name = MOD_NAME,
     version = MOD_VERSION,
     acceptedMinecraftVersions = "[${ForgeVersion.mcVersion}]",
     dependencies = "required:forge@[14.23.5.2847,);" +
                    "required:forgelin_continuous@[2.1.0.0,);" +
                    "required-after:magicbook@[1.0.0,);" +
                    "required-after:modularui@[2.5.0-rc4,);" +
                    "required-after:gregtech@[2.8.7-beta,);",
     modLanguageAdapter = "io.github.chaosunity.forgelin.KotlinAdapter")
class GTLiteMod
{

     private lateinit var moduleManager: ModuleManagerImpl

     init
     {
          GTLiteAPI.instance = this
     }

     companion object
     {

          /**
           * Get [ResourceLocation] with the unique id [MOD_ID] of the mod.
           */
          @JvmStatic
          fun id(path: String): ResourceLocation = ResourceLocation(MOD_ID, path)

     }

     @Mod.EventHandler
     fun onConstruction(event: FMLConstructionEvent)
     {
          this.moduleManager = ModuleManagerImpl.instance
          GTLiteAPI.moduleManager = this.moduleManager
          this.moduleManager.registerContainer(GTLiteModules())
          MinecraftForge.EVENT_BUS.post(ModuleContainerRegistryEvent())
          this.moduleManager.setup(event.asmHarvestedData, Loader.instance().configDir)
          this.moduleManager.onConstruction(event)
     }

     @Mod.EventHandler
     fun onPreInit(event: FMLPreInitializationEvent)
     {
          this.moduleManager.onPreInit(event)
     }

     @Mod.EventHandler
     fun onInit(event: FMLInitializationEvent)
     {
          this.moduleManager.onInit(event)
     }

     @Mod.EventHandler
     fun onPostInit(event: FMLPostInitializationEvent)
     {
          this.moduleManager.onPostInit(event)
          this.moduleManager.processIMC(FMLInterModComms.fetchRuntimeMessages(GTLiteAPI.instance))
     }

     @Mod.EventHandler
     fun loadComplete(event: FMLLoadCompleteEvent)
     {
          this.moduleManager.onLoadComplete(event)
     }

     @Mod.EventHandler
     fun serverAboutToStart(event: FMLServerAboutToStartEvent)
     {
          this.moduleManager.onServerAboutToStart(event)
     }

     @Mod.EventHandler
     fun serverStarting(event: FMLServerStartingEvent)
     {
          this.moduleManager.onServerStarting(event)
     }

     @Mod.EventHandler
     fun serverStarted(event: FMLServerStartedEvent)
     {
          this.moduleManager.onServerStarted(event)
     }

     @Mod.EventHandler
     fun serverStopping(event: FMLServerStoppingEvent)
     {
          this.moduleManager.onServerStopping(event)
     }

     @Mod.EventHandler
     fun serverStopped(event: FMLServerStoppedEvent)
     {
          this.moduleManager.onServerStopped(event)
     }

     @Mod.EventHandler
     fun respondIMC(event: IMCEvent)
     {
          this.moduleManager.processIMC(event.messages)
     }

}