package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VH
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.ModHandler
import gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES
import gregtech.api.recipes.RecipeMaps.CANNER_RECIPES
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.MarkerMaterials
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Chrome
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Cupronickel
import gregtech.api.unification.material.Materials.Glowstone
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Manganese
import gregtech.api.unification.material.Materials.Molybdenum
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Redstone
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Steel
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.ore.OrePrefix.circuit
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.ingot
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.api.unification.ore.OrePrefix.springSmall
import gregtech.api.unification.ore.OrePrefix.wireFine
import gregtech.api.unification.stack.UnificationEntry
import gregtech.api.util.GTUtility.getTierByVoltage
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BIO_SIMULATOR_RECIPES
import magicbook.gtlitecore.api.utils.GTLiteUtility
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.api.utils.Mods
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_BASE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_BLAZE
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_CREEPER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_ENDERMAN
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_ENDER_DRAGON
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_GHAST
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_GUARDIAN
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_SHULKER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_SKELETON
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_SLIME
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_SPIDER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_WITCH
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_WITHER
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_WITHER_SKELETON
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MEMORY_CARD_ZOMBIE
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class BioSimulatorRecipes
{

    companion object
    {


        fun init()
        {
            // Base
            ModHandler.addShapedRecipe(true, "memory_card_base", MEMORY_CARD_BASE.stackForm,
                "wXd", "APA", "WSW",
                'X', UnificationEntry(circuit, MarkerMaterials.Tier.LV),
                'P', UnificationEntry(plate, Steel),
                'A', UnificationEntry(plate, Lead),
                'S', UnificationEntry(springSmall, Cupronickel),
                'W', UnificationEntry(wireFine, Silver))

            ASSEMBLER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(plate, Steel)
                .input(plate, Lead, 2)
                .input(circuit, MarkerMaterials.Tier.LV)
                .input(springSmall, Cupronickel)
                .input(wireFine, Silver, 2)
                .outputs(MEMORY_CARD_BASE.getStackForm(4))
                .EUt(VH[LV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Zombie
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.ROTTEN_FLESH)
                .output(MEMORY_CARD_ZOMBIE)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Skeleton
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.BONE)
                .output(MEMORY_CARD_SKELETON)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Creeper
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.GUNPOWDER)
                .output(MEMORY_CARD_CREEPER)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Slime
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.SLIME_BALL)
                .output(MEMORY_CARD_SLIME)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Spider
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.SPIDER_EYE)
                .output(MEMORY_CARD_SPIDER)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Blaze
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.BLAZE_POWDER)
                .output(MEMORY_CARD_BLAZE)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Ghast
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.GHAST_TEAR)
                .output(MEMORY_CARD_GHAST)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Guardian
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.PRISMARINE_SHARD)
                .output(MEMORY_CARD_GUARDIAN)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Wither Skeleton
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.SKULL, 1, 1)
                .output(MEMORY_CARD_WITHER_SKELETON)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Witch
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.GLASS_BOTTLE)
                .output(MEMORY_CARD_WITCH)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Enderman
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.ENDER_EYE)
                .output(MEMORY_CARD_ENDERMAN)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Shulker
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.SHULKER_SHELL)
                .output(MEMORY_CARD_SHULKER)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Wither
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .input(Items.NETHER_STAR)
                .output(MEMORY_CARD_WITHER)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // Ender Dragon
            CANNER_RECIPES.recipeBuilder()
                .input(MEMORY_CARD_BASE)
                .inputs(ItemStack(Blocks.DRAGON_EGG))
                .output(MEMORY_CARD_ENDER_DRAGON)
                .EUt(7) // ULV
                .duration(1 * SECOND)
                .buildAndRegister()

            // ---------------------------------------------------------------------------------------------------------
            // LV Simulations.

            // Zombie
            addRecipe(MEMORY_CARD_ZOMBIE, 1,
                ItemStack(Items.ROTTEN_FLESH, 64), VA[LV])
            addRecipe(MEMORY_CARD_ZOMBIE, 2,
                ItemStack(Items.IRON_INGOT, 16), VA[LV])
            addRecipe(MEMORY_CARD_ZOMBIE, 3,
                ItemStack(Items.CARROT, 32), VA[LV])
            addRecipe(MEMORY_CARD_ZOMBIE, 4,
                ItemStack(Items.POTATO, 32), VA[LV])
            addRecipe(MEMORY_CARD_ZOMBIE, 5,
                ItemStack(Items.SKULL, 16, 2), VA[LV])

            // Skeleton
            addRecipe(MEMORY_CARD_SKELETON, 1,
                ItemStack(Items.BONE, 32), VA[LV])
            addRecipe(MEMORY_CARD_SKELETON, 2,
                ItemStack(Items.ARROW, 64), VA[LV])
            addRecipe(MEMORY_CARD_SKELETON, 3,
                ItemStack(Items.SKULL, 16, 0), VA[LV])
            addRecipe(MEMORY_CARD_SKELETON, 4,
                OreDictUnifier.get(ingot, Tin, 16), VA[LV])

            // Creeper
            addRecipe(MEMORY_CARD_CREEPER, 1,
                ItemStack(Items.GUNPOWDER, 32), VA[LV])
            addRecipe(MEMORY_CARD_CREEPER, 2,
                ItemStack(Items.COAL, 32), VA[LV])
            addRecipe(MEMORY_CARD_CREEPER, 3,
                ItemStack(Items.SKULL, 16, 4), VA[LV])

            // Slime
            addRecipe(MEMORY_CARD_SLIME, 1,
                ItemStack(Items.SLIME_BALL, 32), VA[LV])
            addRecipe(MEMORY_CARD_SLIME, 2,
                OreDictUnifier.get(ingot, Nickel, 16), VA[LV])

            // Spider
            addRecipe(MEMORY_CARD_SPIDER, 1,
                ItemStack(Items.SPIDER_EYE, 64), VA[LV])
            addRecipe(MEMORY_CARD_SPIDER, 2,
                ItemStack(Items.STRING, 32), VA[LV])
            addRecipe(MEMORY_CARD_SPIDER, 3,
                OreDictUnifier.get(ingot, Copper, 16), VA[LV])

            // ---------------------------------------------------------------------------------------------------------
            // MV Simulations.

            // Blaze
            addRecipe(MEMORY_CARD_BLAZE, 1,
                ItemStack(Items.BLAZE_ROD, 16), VA[MV])
            addRecipe(MEMORY_CARD_BLAZE, 2,
                OreDictUnifier.get(dust, Sulfur, 32), VA[MV])
            addRecipe(MEMORY_CARD_BLAZE, 3,
                ItemStack(Items.MAGMA_CREAM, 64), VA[MV])

            // Ghast
            addRecipe(MEMORY_CARD_GHAST, 1,
                ItemStack(Items.GHAST_TEAR, 32), VA[MV])
            addRecipe(MEMORY_CARD_GHAST, 2,
                OreDictUnifier.get(ingot, Silver, 16), VA[MV])

            // Guardian
            addRecipe(MEMORY_CARD_GUARDIAN, 1,
                ItemStack(Items.PRISMARINE_SHARD, 64), VA[MV])
            addRecipe(MEMORY_CARD_GUARDIAN, 2,
                ItemStack(Items.PRISMARINE_CRYSTALS, 64), VA[MV])
            addRecipe(MEMORY_CARD_GUARDIAN, 3,
                ItemStack(Items.FISH, 64), VA[MV])
            addRecipe(MEMORY_CARD_GUARDIAN, 4,
                OreDictUnifier.get(ingot, Gold, 16), VA[MV])
            addRecipe(MEMORY_CARD_GUARDIAN, 5,
                OreDictUnifier.get(dust, Aluminium, 16), VA[MV])

            // Wither Skeleton
            addRecipe(MEMORY_CARD_WITHER_SKELETON, 1,
                ItemStack(Items.SKULL, 16, 1), VA[MV])
            addRecipe(MEMORY_CARD_WITHER_SKELETON, 2,
                OreDictUnifier.get(ingot, Lead, 16), VA[MV])
            addRecipe(MEMORY_CARD_WITHER_SKELETON, 3,
                ItemStack(Blocks.SOUL_SAND, 64), VA[MV])

            // Witch
            addRecipe(MEMORY_CARD_WITCH, 1,
                OreDictUnifier.get(dust, Redstone, 32), VA[MV])
            addRecipe(MEMORY_CARD_WITCH, 2,
                OreDictUnifier.get(dust, Glowstone, 32), VA[MV])
            addRecipe(MEMORY_CARD_WITCH, 3,
                ItemStack(Items.SUGAR, 64), VA[MV])
            addRecipe(MEMORY_CARD_WITCH, 4,
                ItemStack(Items.GLASS_BOTTLE, 16), VA[MV])

            // ---------------------------------------------------------------------------------------------------------
            // HV Simulations.

            // Enderman
            addRecipe(MEMORY_CARD_ENDERMAN, 1,
                ItemStack(Items.ENDER_PEARL, 16), VA[HV])
            addRecipe(MEMORY_CARD_ENDERMAN, 2,
                ItemStack(Items.EMERALD, 16), VA[HV])
            addRecipe(MEMORY_CARD_ENDERMAN, 3,
                ItemStack(Blocks.END_STONE, 32), VA[HV])
            addRecipe(MEMORY_CARD_ENDERMAN, 4,
                OreDictUnifier.get(dust, Manganese, 16), VA[HV])

            if (Mods.EnderIO.isModLoaded())
            {
                addRecipe(MEMORY_CARD_ENDERMAN, 4,
                    Mods.EnderIO.getItem("block_enderman_skull", 16), VA[HV])
            }

            // Shulker
            addRecipe(MEMORY_CARD_SHULKER, 1,
                ItemStack(Items.SHULKER_SHELL, 16), VA[HV])
            addRecipe(MEMORY_CARD_SHULKER, 2,
                ItemStack(Items.DIAMOND, 16), VA[HV])
            addRecipe(MEMORY_CARD_SHULKER, 3,
                OreDictUnifier.get(dust, Chrome, 16), VA[HV])

            // ---------------------------------------------------------------------------------------------------------
            // EV Simulations.

            // Wither
            addRecipe(MEMORY_CARD_WITHER, 1,
                ItemStack(Items.NETHER_STAR, 4), VA[EV])
            addRecipe(MEMORY_CARD_WITHER, 2,
                OreDictUnifier.get(ingot, Antimony, 16), VA[EV])
            addRecipe(MEMORY_CARD_WITHER, 3,
                OreDictUnifier.get(dust, Molybdenum, 16), VA[EV])

            // ---------------------------------------------------------------------------------------------------------
            // IV Simulations.

            // Ender Dragon
            addRecipe(MEMORY_CARD_ENDER_DRAGON, 1,
                ItemStack(Items.DRAGON_BREATH, 32), VA[IV])
            addRecipe(MEMORY_CARD_ENDER_DRAGON, 2,
                ItemStack(Blocks.DRAGON_EGG), VA[IV])
            addRecipe(MEMORY_CARD_ENDER_DRAGON, 3,
                ItemStack(Blocks.END_ROD, 16), VA[IV])
            addRecipe(MEMORY_CARD_ENDER_DRAGON, 4,
                ItemStack(Items.ENDER_EYE, 64), VA[IV])

        }

        private fun addRecipe(memoryCard: MetaItem<*>.MetaValueItem,
                              circuitMeta: Int,
                              outputs: MetaItem<*>.MetaValueItem,
                              eut: Int)
            = addRecipe(memoryCard.stackForm, circuitMeta, outputs.stackForm, eut)

        private fun addRecipe(memoryCard: MetaItem<*>.MetaValueItem,
                              circuitMeta: Int,
                              outputs: ItemStack,
                              eut: Int)
            = addRecipe(memoryCard.stackForm, circuitMeta, outputs, eut)

        private fun addRecipe(memoryCard: ItemStack,
                              circuitMeta: Int,
                              outputs: ItemStack,
                              eut: Int)
        {
            val builder = BIO_SIMULATOR_RECIPES.recipeBuilder()
                .circuitMeta(circuitMeta)
                .notConsumable(memoryCard)
                .chancedOutput(outputs, 1000, 0)
            if (Mods.EnderIO.isModLoaded())
                builder.fluidOutputs(GTLiteUtility.getModFluid("xpjuice", 250 * getTierByVoltage(eut.toLong())))
            builder.EUt(eut.toLong())
                .duration(1 * MINUTE)
                .buildAndRegister()
        }

    }

}