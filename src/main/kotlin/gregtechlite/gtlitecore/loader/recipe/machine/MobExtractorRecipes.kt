package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.recipes.RecipeMaps.SCANNER_RECIPES
import gregtech.api.unification.material.Materials.Emerald
import gregtech.api.unification.material.Materials.Milk
import gregtech.api.unification.ore.OrePrefix.gem
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.getStack
import gregtechlite.gtlitecore.api.extension.outputs
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOB_EXTRACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Blood
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_BASE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_BAT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_CHICKEN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_COW
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_DONKEY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_HORSE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_LLAMA
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_MOOSHROOM
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_MULE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_OCELOT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_PIG
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_RABBIT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_SHEEP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_TRADER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_VILLAGER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MEMORY_CARD_WOLF
import net.minecraft.entity.passive.EntityChicken
import net.minecraft.entity.passive.EntityCow
import net.minecraft.entity.passive.EntityHorse
import net.minecraft.entity.passive.EntityPig
import net.minecraft.entity.passive.EntitySheep
import net.minecraft.entity.passive.EntityVillager
import net.minecraft.init.Blocks
import net.minecraft.init.Blocks.BROWN_MUSHROOM
import net.minecraft.init.Blocks.DEADBUSH
import net.minecraft.init.Blocks.RED_MUSHROOM
import net.minecraft.init.Blocks.WOOL
import net.minecraft.init.Items
import net.minecraft.init.Items.BEETROOT
import net.minecraft.init.Items.CARROT
import net.minecraft.init.Items.DYE
import net.minecraft.init.Items.EGG
import net.minecraft.init.Items.FISH
import net.minecraft.init.Items.IRON_HORSE_ARMOR
import net.minecraft.init.Items.LEAD
import net.minecraft.init.Items.LEATHER
import net.minecraft.init.Items.MILK_BUCKET
import net.minecraft.init.Items.SADDLE
import net.minecraft.init.Items.STRING
import net.minecraft.item.ItemStack

/**
 * @see gregtechlite.gtlitecore.loader.recipe.foodprocessing.AnimalFatProcessing
 */
internal object MobExtractorRecipes
{

    // @formatter:off

    fun init()
    {
        mobExtractionProcess()
        slaughterProcess()
    }

    private fun mobExtractionProcess()
    {
        // Cow
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(1)
            fluidOutputs(Milk.getFluid(10))
            EUt(VA[LV])
            duration(10 * TICK)
            mob(EntityCow::class)
        }

        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(2)
            fluidOutputs(Blood.getFluid(10))
            EUt(VA[LV])
            duration(10 * TICK)
            mob(EntityCow::class)
        }

        // Sheep
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(3)
            outputs(WOOL)
            EUt(VA[MV])
            duration(10 * TICK)
            mob(EntitySheep::class)
        }

        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(4)
            fluidOutputs(Blood.getFluid(5))
            EUt(VA[LV])
            duration(10 * TICK)
            mob(EntitySheep::class)
        }

        // Pig
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(5)
            fluidOutputs(Blood.getFluid(8))
            EUt(VA[LV])
            duration(10 * TICK)
            mob(EntityPig::class)
        }

        // Chicken
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(6)
            outputs(EGG)
            EUt(VA[LV])
            duration(10 * TICK)
            mob(EntityChicken::class)
        }

        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(7)
            fluidOutputs(Blood.getFluid(3))
            EUt(VA[LV])
            duration(10 * TICK)
            mob(EntityChicken::class)
        }

        // Horse
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(8)
            fluidOutputs(Blood.getFluid(80))
            EUt(VA[MV])
            duration(10 * TICK)
            mob(EntityHorse::class)
        }

        // Villager
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(9)
            fluidOutputs(Blood.getFluid(100))
            EUt(VA[HV])
            duration(10 * TICK)
            mob(EntityVillager::class)
        }

        // Player
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(10)
            fluidOutputs(Blood.getFluid(200))
            EUt(VA[HV])
            duration(10 * TICK)
            mob("player")
        }
    }

    private fun slaughterProcess()
    {
        // Chicken
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(EGG)
            output(MEMORY_CARD_CHICKEN)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Cow
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(MILK_BUCKET)
            output(MEMORY_CARD_COW)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Pig
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(CARROT)
            output(MEMORY_CARD_PIG)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Sheep
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(WOOL)
            output(MEMORY_CARD_SHEEP)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Horse
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(IRON_HORSE_ARMOR)
            output(MEMORY_CARD_HORSE)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Donkey
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(LEATHER)
            output(MEMORY_CARD_DONKEY)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Mule
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(SADDLE)
            output(MEMORY_CARD_MULE)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Ocelot
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(FISH)
            output(MEMORY_CARD_OCELOT)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Wolf
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            inputs(DYE.getStack(1, 15))
            output(MEMORY_CARD_WOLF)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Rabbit
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(BEETROOT)
            output(MEMORY_CARD_RABBIT)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Llama
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(STRING)
            output(MEMORY_CARD_LLAMA)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Mooshroom
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(RED_MUSHROOM)
            output(MEMORY_CARD_MOOSHROOM)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Bat
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(DEADBUSH)
            output(MEMORY_CARD_BAT)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Villager
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(gem, Emerald)
            output(MEMORY_CARD_VILLAGER)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // Trader
        SCANNER_RECIPES.addRecipe {
            input(MEMORY_CARD_BASE)
            input(LEAD)
            output(MEMORY_CARD_TRADER)
            EUt(7) // ULV
            duration(1 * SECOND)
        }

        // ---------------------------------------------------------------------------------------------------------
    }

    // @formatter:on

}