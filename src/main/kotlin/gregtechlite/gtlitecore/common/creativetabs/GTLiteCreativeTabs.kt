package gregtechlite.gtlitecore.common.creativetabs

import com.morphismmc.morphismlib.creativetab.CustomCreativeTab
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LOGO_CORE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LOGO_DECORATION
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LOGO_FOOD
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LOGO_MACHINE
import net.minecraft.creativetab.CreativeTabs

object GTLiteCreativeTabs
{

    /**
     * The main [CreativeTabs] of the mod, this is the default settings for all contents in the mod.
     * If the items, blocks or other contents satisfied descriptions of the following [CreativeTabs],
     * then should use consistent [CreativeTabs] otherwise this.
     */
    @JvmField
    val TAB_MAIN: CreativeTabs = CustomCreativeTab("gtlite.main") { LOGO_CORE.stackForm }

    /**
     * GT format Machines for the mod, consists of all single machines and multiblock machines.
     * Copied from the machine [CreativeTabs] of GTCEu mod. It is automatically added for machines if the machine
     * registry is well (so if you needed it otherwise not put something in this [CreativeTabs]).
     */
    @JvmField
    val TAB_MACHINE: CreativeTabs = CustomCreativeTab("gtlite.machine") { LOGO_MACHINE.stackForm }

    /**
     * All decorations for the mod, consists of all stones and its variant blocks, planks, logs or other decorative
     * blocks in the game (functional baubles is also used this [CreativeTabs] yet).
     */
    @JvmField
    val TAB_DECORATION: CreativeTabs = CustomCreativeTab("gtlite.decoration") { LOGO_DECORATION.stackForm }

    /**
     * Foods and drinks for the mod, consists of all crops and berries. Tablewares is also in this [CreativeTabs].
     */
    @JvmField
    val TAB_FOOD: CreativeTabs = CustomCreativeTab("gtlite.food") { LOGO_FOOD.stackForm }

}