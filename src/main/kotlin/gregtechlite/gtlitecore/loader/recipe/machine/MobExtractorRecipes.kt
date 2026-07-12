package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.items.metaitem.MetaItem
import gregtech.api.recipes.RecipeMaps.SCANNER_RECIPES
import gregtech.api.unification.material.Materials.Bone
import gregtech.api.unification.material.Materials.Emerald
import gregtech.api.unification.material.Materials.Meat
import gregtech.api.unification.material.Materials.Milk
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.gem
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.getStack
import gregtechlite.gtlitecore.api.extension.outputs
import gregtechlite.gtlitecore.api.extension.stack
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOB_EXTRACTOR_RECIPES
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOB_SLAUGHTERING_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Blood
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Fat
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
import net.minecraft.init.Blocks.DEADBUSH
import net.minecraft.init.Blocks.RED_MUSHROOM
import net.minecraft.init.Blocks.WOOL
import net.minecraft.init.Items.BEETROOT
import net.minecraft.init.Items.CARROT
import net.minecraft.init.Items.CHICKEN
import net.minecraft.init.Items.DYE
import net.minecraft.init.Items.EGG
import net.minecraft.init.Items.FEATHER
import net.minecraft.init.Items.FISH
import net.minecraft.init.Items.IRON_HORSE_ARMOR
import net.minecraft.init.Items.LEAD
import net.minecraft.init.Items.LEATHER
import net.minecraft.init.Items.MILK_BUCKET
import net.minecraft.init.Items.SADDLE
import net.minecraft.init.Items.STRING
import gregtech.api.recipes.builders.SimpleRecipeBuilder
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.Ash
import gregtech.api.unification.material.Materials.DarkAsh
import gregtech.api.unification.material.Materials.Duranium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.HSSE
import gregtech.api.unification.material.Materials.RedSteel
import gregtech.api.unification.ore.OrePrefix.nugget
import gregtech.api.unification.ore.OrePrefix.toolHeadBuzzSaw
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOB_COLLECTING_RECIPES
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HORSE_MEAT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MUD_BALL
import gregtechlite.gtlitecore.common.item.GTLiteMetaOreDictItems.ANIMAL_FAT
import net.minecraft.init.Blocks.BROWN_MUSHROOM
import net.minecraft.init.Items.BEEF
import net.minecraft.init.Items.CLAY_BALL
import net.minecraft.init.Items.FLINT
import net.minecraft.init.Items.MUTTON
import net.minecraft.init.Items.PAPER
import net.minecraft.init.Items.PORKCHOP
import net.minecraft.init.Items.RABBIT_FOOT
import net.minecraft.init.Items.RABBIT_HIDE
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
        // Mob Collecting

        // Chicken
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_CHICKEN)
            output(FEATHER, 2)
            output(EGG)
            fluidOutputs(Blood.getFluid(64))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Cow
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_COW)
            output(LEATHER, 3)
            fluidOutputs(Blood.getFluid(225))
            fluidOutputs(Milk.getFluid(1000))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Pig
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_PIG)
            output(MUD_BALL, 3)
            fluidOutputs(Blood.getFluid(175))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Sheep
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_SHEEP)
            output(WOOL, 2)
            output(STRING, 3)
            fluidOutputs(Blood.getFluid(200))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Horse
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_HORSE)
            output(LEATHER, 2)
            chancedOutput(SADDLE.stack(), 2000, 0)
            fluidOutputs(Blood.getFluid(180))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Donkey
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_DONKEY)
            output(MUD_BALL)
            chancedOutput(SADDLE.stack(), 1500, 0)
            fluidOutputs(Blood.getFluid(175))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Mule
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_MULE)
            chancedOutput(SADDLE.stack(), 3500, 0)
            chancedOutput(LEAD.stack(), 3500, 0)
            fluidOutputs(Blood.getFluid(225))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Ocelot
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_OCELOT)
            fluidOutputs(Blood.getFluid(50))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Wolf
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_WOLF)
            fluidOutputs(Blood.getFluid(80))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Rabbit
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_RABBIT)
            output(RABBIT_HIDE)
            output(RABBIT_FOOT, 2)
            fluidOutputs(Blood.getFluid(65))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Llama
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_LLAMA)
            output(LEATHER)
            fluidOutputs(Blood.getFluid(180))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Mooshroom
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_MOOSHROOM)
            output(BROWN_MUSHROOM, 2)
            output(RED_MUSHROOM)
            fluidOutputs(Blood.getFluid(200))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Bat
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_BAT)
            output(dust, Ash, 2)
            output(dust, DarkAsh)
            fluidOutputs(Blood.getFluid(10))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Villager
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_VILLAGER)
            fluidOutputs(Blood.getFluid(250))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // Wandering Trader
        MOB_COLLECTING_RECIPES.addRecipe {
            notConsumable(MEMORY_CARD_TRADER)
            fluidOutputs(Blood.getFluid(250))
            EUt(VA[HV])
            duration(10 * SECOND)
        }

        // ---------------------------------------------------------------------------------------------------------
        // Mob Slaughtering

        // Chicken
        addSlaughterRecipes(MEMORY_CARD_CHICKEN, 125, 100) {
            output(FEATHER, 4 * it)
            output(CHICKEN, 2 * it)
            output(dust, Meat, 2 * it)
            output(dust, Bone, 1 * it)
        }

        // Cow
        addSlaughterRecipes(MEMORY_CARD_COW, 500, 350) {
            output(LEATHER, 8 * it)
            output(BEEF, 4 * it)
            output(dust, Meat, 6 * it)
            output(dust, Bone, 3 * it)
        }

        // Pig
        addSlaughterRecipes(MEMORY_CARD_PIG, 450, 275) {
            output(PORKCHOP, 6 * it)
            output(dust, Meat, 8 * it)
            output(dust, Bone, 2 * it)
        }

        // Sheep
        addSlaughterRecipes(MEMORY_CARD_SHEEP, 475, 250) {
            output(WOOL, 8 * it)
            output(MUTTON, 5 * it)
            output(dust, Meat, 4 * it)
            output(dust, Bone, 4 * it)
        }

        // Horse
        addSlaughterRecipes(MEMORY_CARD_HORSE, 650, 200) {
            output(LEATHER, 6 * it)
            output(HORSE_MEAT, 3 * it)
            output(dust, Meat, 5 * it)
            output(dust, Bone, 4 * it)
        }

        // Donkey
        addSlaughterRecipes(MEMORY_CARD_DONKEY, 500, 250) {
            output(LEATHER, 4 * it)
            outputs(ANIMAL_FAT.getStack(2 * it))
            output(dust, Meat, 3 * it)
            output(dust, Bone, 2 * it)
        }

        // Mute
        addSlaughterRecipes(MEMORY_CARD_MULE, 525, 180) {
            output(LEATHER, 5 * it)
            outputs(ANIMAL_FAT.getStack(1 * it))
            output(dust, Meat, 6 * it)
            output(dust, Bone, 5 * it)
        }

        // Ocelot
        addSlaughterRecipes(MEMORY_CARD_OCELOT, 75, 30) {
            output(dust, Meat, 2 * it)
            output(dust, Bone, 1 * it)
        }

        // Wolf
        addSlaughterRecipes(MEMORY_CARD_WOLF, 60, 90) {
            output(dust, Meat, 2 * it)
            output(dust, Bone, 3 * it)
        }

        // Rabbit
        addSlaughterRecipes(MEMORY_CARD_RABBIT, 40, 60) {
            output(RABBIT_FOOT, 2 * it)
            output(RABBIT_HIDE, 1 * it)
            output(dust, Meat, 1 * it)
            output(dust, Bone, 1 * it)
        }

        // Llama
        addSlaughterRecipes(MEMORY_CARD_LLAMA, 400, 380) {
            output(LEATHER, 3 * it)
            outputs(ANIMAL_FAT.getStack(4 * it))
            output(dust, Meat, 3 * it)
            output(dust, Bone, 3 * it)
        }

        // Mooshroom
        addSlaughterRecipes(MEMORY_CARD_MOOSHROOM, 480, 325) {
            output(BROWN_MUSHROOM, 4 * it)
            output(RED_MUSHROOM, 4 * it)
            output(dust, Meat, 4 * it)
            output(dust, Bone, 4 * it)
        }

        // Bat
        addSlaughterRecipes(MEMORY_CARD_BAT, 20, 10) {
            output(dust, Meat, 1 * it)
        }

        // Villager
        addSlaughterRecipes(MEMORY_CARD_VILLAGER, 350, 275) {
            output(CLAY_BALL, it * 3)
            output(FLINT, it * 1)
            output(dust, Meat, 6 * it)
            output(dust, Bone, 8 * it)
        }

        // Wandering Trader
        addSlaughterRecipes(MEMORY_CARD_TRADER, 350, 275) {
            output(PAPER, it * 2)
            output(nugget, Gold, it * 2)
            output(dust, Meat, 6 * it)
            output(dust, Bone, 8 * it)
        }
    }

    // @formatter:on

    private fun addSlaughterRecipes(card: MetaItem<*>.MetaValueItem, bloodAmount: Int, fatAmount: Int,
                                    builder: SimpleRecipeBuilder.(a: Int) -> Unit)
    {
        addSlaughterRecipe(card, OreDictUnifier.get(toolHeadBuzzSaw, RedSteel), 1, VA[HV], bloodAmount, fatAmount, builder)
        addSlaughterRecipe(card, OreDictUnifier.get(toolHeadBuzzSaw, HSSE), 4, VA[EV], bloodAmount, fatAmount, builder)
        addSlaughterRecipe(card, OreDictUnifier.get(toolHeadBuzzSaw, Duranium), 16, VA[IV], bloodAmount, fatAmount, builder)
    }

    private fun addSlaughterRecipe(card: MetaItem<*>.MetaValueItem, weapon: ItemStack, factor: Int, eut: Int,
                                   bloodAmount: Int, fatAmount: Int, builder: SimpleRecipeBuilder.(a: Int) -> Unit)
    {
        MOB_SLAUGHTERING_RECIPES.addRecipe {
            notConsumable(card)
            notConsumable(weapon)
            builder(factor)
            fluidOutputs(Blood.getFluid(bloodAmount * factor))
            fluidOutputs(Fat.getFluid(fatAmount * factor))
            EUt(eut)
            duration(40 * SECOND)
        }
    }
}