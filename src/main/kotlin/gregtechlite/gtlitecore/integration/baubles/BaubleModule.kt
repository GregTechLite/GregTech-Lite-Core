package gregtechlite.gtlitecore.integration.baubles

import baubles.api.BaublesApi
import com.morphismmc.morphismlib.util.SidedLogger
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.module.Module
import gregtechlite.gtlitecore.core.module.GTLiteModules.Companion.MODULE_BAUBLES
import gregtechlite.gtlitecore.integration.IntegrationSubModule
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import org.apache.logging.log4j.Logger

@Suppress("unused")
@Module(moduleId = MODULE_BAUBLES,
        containerId = MOD_ID,
        modDependencies = [ "baubles" ],
        name = "GregTech Lite Baubles Module",
        descriptions = "Baubles Module of GregTech Lite Core Mod.")
class BaubleModule : IntegrationSubModule()
{
    companion object
    {
        @JvmField
        val logger: Logger = SidedLogger("$MOD_ID-baubles-module")

        @Suppress("Deprecation")
        @JvmStatic
        fun getEquippedStacks(player: EntityPlayer): List<ItemStack>
        {
            val inventory = BaublesApi.getBaubles(player) ?: return emptyList()
            return (0 until inventory.sizeInventory).mapNotNull { slot ->
                inventory.getStackInSlot(slot).takeUnless { it.isEmpty }
            }
        }
    }

    override val logger: Logger = Companion.logger
}
