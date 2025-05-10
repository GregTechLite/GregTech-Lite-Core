package magicbook.gtlitecore.loader.recipe

import gregtech.api.recipes.ModHandler
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Paper
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.common.ConfigHolder
import gregtech.common.items.MetaItems.COMPRESSED_CLAY
import gregtech.common.items.MetaItems.WOODEN_FORM_BRICK
import gregtech.common.items.ToolItems
import magicbook.gtlitecore.api.utils.Mods
import magicbook.gtlitecore.common.item.GTLiteToolItems
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class VanillaOverrideRecipeLoader
{

    companion object
    {

        fun init()
        {
            // Allowed player used paper dust though the hard paper config (ConfigHolder.recipes.nerfPaperCrafting).
            if (!ConfigHolder.recipes.nerfPaperCrafting)
            {
                ModHandler.removeRecipeByName("${Mods.Minecraft.getId()}:paper")
                ModHandler.addShapelessRecipe("paper", ItemStack(Items.PAPER),
                    GTLiteToolItems.ROLLING_PIN, ItemStack(Items.REEDS, 1, 0))

                ModHandler.addShapelessRecipe("paper_dust", OreDictUnifier.get(dust, Paper),
                    ToolItems.MORTAR, ItemStack(Items.PAPER, 1))
            }

            // Let sugar used mortar.
            ModHandler.removeRecipeByName("${Mods.Minecraft.getId()}:sugar")
            ModHandler.addShapelessRecipe("sugar", ItemStack(Items.SUGAR, 1),
                ToolItems.MORTAR, ItemStack(Items.REEDS, 1))

            // Modified bricks processing.
            ModHandler.addShapedRecipe(true, "compressed_clay_brick", COMPRESSED_CLAY.getStackForm(8),
                "CCC", "CMC", "CCC",
                'C', ItemStack(Items.CLAY_BALL),
                'M', WOODEN_FORM_BRICK)

            ModHandler.removeFurnaceSmelting(ItemStack(Items.CLAY_BALL))
            ModHandler.addSmeltingRecipe(COMPRESSED_CLAY.stackForm,
                ItemStack(Items.BRICK))

        }

    }

}