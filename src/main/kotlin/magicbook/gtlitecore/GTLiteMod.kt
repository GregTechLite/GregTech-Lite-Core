package magicbook.gtlitecore

import magicbook.gtlitecore.api.GTLiteAPI
import magicbook.gtlitecore.api.utils.GTLiteValues
import magicbook.gtlitecore.core.module.GTLiteModules
import magicbook.gtlitecore.core.module.ModuleContainerRegistryEvent
import magicbook.gtlitecore.core.module.ModuleManager
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.*
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent


@Mod(modid = GTLiteValues.MODID,
     name = GTLiteValues.NAME,
     version = GTLiteValues.VERSION,
     acceptedMinecraftVersions = "[1.12.2]",
     dependencies = "required:forge@[14.23.5.2847,);" +
                    "required:forgelin_continuous@[2.1.0.0,);" +
                    "required:modularui@[2.5.0-rc4,);",
     modLanguageAdapter = "io.github.chaosunity.forgelin.KotlinAdapter")
class GTLiteMod
{

     private lateinit var moduleManager: ModuleManager

     init
     {
          GTLiteAPI.instance = this
     }

     @Mod.EventHandler
     fun onConstruction(event: FMLConstructionEvent)
     {
          this.moduleManager = ModuleManager.instance
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