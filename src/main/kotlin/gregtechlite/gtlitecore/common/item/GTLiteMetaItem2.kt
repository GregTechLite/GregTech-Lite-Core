package gregtechlite.gtlitecore.common.item

import gregtech.api.items.metaitem.MetaItem
import gregtech.api.util.RandomPotionEffect
import gregtech.common.items.behaviors.TooltipBehavior
import gregtechlite.gtlitecore.api.MINUTE
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.addOreDicts
import gregtechlite.gtlitecore.api.item.GTLiteMetaItem
import gregtechlite.gtlitecore.client.renderer.texture.GTLiteTextures
import gregtechlite.gtlitecore.common.creativetabs.GTLiteCreativeTabs
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.APRICOT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ARTICHOKE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ARTICHOKE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.AUBERGINE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.AUBERGINE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BANANA
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BASIL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BASIL_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BEAN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BEAN_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BLACKBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BLACK_CURRANT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BLACK_PEPPER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BLACK_PEPPER_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.BLUEBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CERAMIC_BOWL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CERAMIC_CUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CLAY_BOWL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CLAY_CUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COCONUT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COFFEE_CHERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COFFEE_CUP
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COFFEE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CORN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CORN_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COTTON
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.COTTON_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRANBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CRANBERRY_ETIRPS
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CUCUMBER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.CUCUMBER_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.DIRTY_CERAMIC_BOWL
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ELDERBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ETIRPS
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GARLIC_BULB
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GARLIC_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GOLDEN_STRAWBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAHAM_CRACKER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAPE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAPE_JUICE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.GRAPE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HARD_APPLE_CANDY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HORSERADISH
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.HORSERADISH_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LEMON
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LIME
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.LINGONBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MANGO
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MOON_BERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.NUTMEG
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OLIVE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ONION
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ONION_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ORANGE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.ORANGE_JUICE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OREGANO
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.OREGANO_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PAPER_CONE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PEA
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PEA_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.PLASTIC_BOTTLE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.POLENTA
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.POTATO_JUICE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RAINBOW_BERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RASPBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RED_CURRANT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RED_WINE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RICE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.RICE_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SILVER_APPLE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SILVER_STRAWBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOYBEAN
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SOY_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.SPARKLING_WATER
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.STRAWBERRY
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TOMATO
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.TOMATO_SEED
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VINEGAR
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.VODKA
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WHITE_CURRANT
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WHITE_GRAPE
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.WHITE_GRAPE_SEED
import gregtechlite.gtlitecore.common.item.behavior.FoodBehavior
import gregtechlite.gtlitecore.common.item.behavior.HaloRenderItemBehavior
import net.minecraft.client.resources.I18n
import net.minecraft.init.Items
import net.minecraft.init.MobEffects
import net.minecraft.item.ItemStack

object GTLiteMetaItem2
{

    internal lateinit var META_ITEMS_2: MetaItem<*>

    internal fun init()
    {
        META_ITEMS_2 = GTLiteMetaItem()
        META_ITEMS_2.setRegistryName("gtlite_meta_item_2")
        META_ITEMS_2.setCreativeTab(GTLiteCreativeTabs.TAB_MAIN)
    }

    internal fun register()
    {
        // region 1-300: Crops & Fruits
        BANANA = item(1, "crop.fruit.banana")
            .addComponents(FoodBehavior(2, 1f)
                .setEatingDuration(3 * SECOND))
            .addOreDicts("cropBanana", "fruitBanana")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ORANGE = item(2, "crop.fruit.orange")
            .addComponents(FoodBehavior(2, 1f)
                .setEatingDuration(2 * SECOND + 10 * TICK))
            .addOreDicts("cropOrange", "fruitOrange")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        MANGO = item(3, "crop.fruit.mango")
            .addComponents(FoodBehavior(2, 1f))
            .addOreDicts("cropMango", "fruitMango")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        APRICOT = item(4, "crop.fruit.apricot")
            .addComponents(FoodBehavior(2, 1f))
            .addOreDicts("cropApricot", "fruitApricot")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        LEMON = item(5, "crop.fruit.lemon")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDicts("cropLemon", "fruitLemon")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        LIME = item(6, "crop.fruit.lime")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDicts("cropLime", "fruitLime")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        OLIVE = item(7, "crop.fruit.olive")
            .addComponents(FoodBehavior(2, 0.5f))
            .addOreDicts("cropOlive", "fruitOlive")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        NUTMEG = item(8, "crop.fruit.nutmeg")
            .addOreDicts("cropNutmeg", "fruitOlive")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        COCONUT = item(9, "crop.fruit.coconut")
            .addOreDicts("cropCoconut", "fruitCoconut")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BLUEBERRY = item(10, "crop.fruit.blueberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDicts("cropBlueberry", "seedBlueberry", "fruitBlueberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BLACKBERRY = item(11, "crop.fruit.blackberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDicts("cropBlackberry", "seedBlackberry", "fruitBlackberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        RASPBERRY = item(12, "crop.fruit.raspberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDicts("cropRaspberry", "seedRaspberry", "fruitRaspberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        STRAWBERRY = item(13, "crop.fruit.strawberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDicts("cropStrawberry", "seedStrawberry", "fruitStrawberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        RED_CURRANT = item(14, "crop.fruit.red_currant")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDicts("cropRedCurrant", "seedRedCurrant", "fruitRedCurrant")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BLACK_CURRANT = item(15, "crop.fruit.black_currant")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDicts("cropBlackCurrant", "seedBlackCurrant", "fruitBlackCurrant")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        WHITE_CURRANT = item(16, "crop.fruit.white_currant")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDicts("cropWhiteCurrant", "seedWhiteCurrant", "fruitWhiteCurrant")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        LINGONBERRY = item(17, "crop.fruit.lingonberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDicts("cropLingonberry", "seedLingonberry", "fruitLingonberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ELDERBERRY = item(18, "crop.fruit.elderberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDicts("cropElderberry", "seedElderberry", "fruitElderberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        CRANBERRY = item(19, "crop.fruit.cranberry")
            .addComponents(FoodBehavior(1, 0.5f))
            .addOreDicts("cropCranberry", "seedCranberry", "fruitCranberry")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        // ...

        COFFEE_SEED = item(101, "crop.seed.coffee")
            .addOreDict("seedCoffee")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        TOMATO_SEED = item(102, "crop.seed.tomato")
            .addOreDict("seedTomato")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ONION_SEED = item(103, "crop.seed.onion")
            .addOreDict("seedOnion")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        CUCUMBER_SEED = item(104, "crop.seed.cucumber")
            .addOreDict("seedCucumber")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        GRAPE_SEED = item(105, "crop.seed.grape")
            .addOreDict("seedGrape")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        SOY_SEED = item(106, "crop.seed.soy")
            .addOreDict("seedSoy")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BEAN_SEED = item(107, "crop.seed.bean")
            .addOreDict("seedBean")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        PEA_SEED = item(108, "crop.seed.pea")
            .addOreDict("seedPea")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        OREGANO_SEED = item(109, "crop.seed.oregano")
            .addOreDict("seedOregano")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        HORSERADISH_SEED = item(110, "crop.seed.horseradish")
            .addOreDict("seedHorseradish")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        GARLIC_SEED = item(111, "crop.seed.garlic")
            .addOreDict("seedGarlic")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BASIL_SEED = item(112, "crop.seed.basil")
            .addOreDict("seedBasil")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        AUBERGINE_SEED = item(113, "crop.seed.aubergine")
            .addOreDict("seedAubergine")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        CORN_SEED = item(114, "crop.seed.corn")
            .addOreDict("seedCorn")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ARTICHOKE_SEED = item(115, "crop.seed.artichoke")
            .addOreDict("seedArtichoke")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BLACK_PEPPER_SEED = item(116, "crop.seed.black_pepper")
            .addOreDict("seedBlackPepper")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        RICE_SEED = item(117, "crop.seed.rice")
            .addOreDict("seedRice")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        WHITE_GRAPE_SEED = item(118, "crop.seed.white_grape")
            .addOreDict("seedWhiteGrape")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        COTTON_SEED = item(119, "crop.seed.cotton")
            .addOreDict("seedCotton")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        // ...

        COFFEE_CHERRY = item(201, "crop.fruit.coffee_cherry")
            .addOreDicts("cropCoffee", "fruitCoffee")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        TOMATO = item(202, "crop.fruit.tomato")
            .addComponents(FoodBehavior(3, 0.5f)
                .setEatingDuration(3 * SECOND + 12 * TICK))
            .addOreDicts("cropTomato", "fruitTomato")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ONION = item(203, "crop.fruit.onion")
            .addComponents(FoodBehavior(3, 0.33f)
                .setEatingDuration(6 * SECOND + 8 * TICK))
            .addOreDicts("cropOnion", "fruitOnion")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        CUCUMBER = item(204, "crop.fruit.cucumber")
            .addComponents(FoodBehavior(2, 0.5f)
                .setEatingDuration(3 * SECOND + 4 * TICK))
            .addOreDicts("cropCucumber", "fruitCucumber")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        GRAPE = item(205, "crop.fruit.grape")
            .addComponents(FoodBehavior(1, 1f))
            .addOreDicts("cropGrape", "fruitGrape")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        SOYBEAN = item(206, "crop.fruit.soybean")
            .addOreDicts("cropSoy", "fruitSoy")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BEAN = item(207, "crop.fruit.bean")
            .addOreDicts("cropBean", "fruitBean")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        PEA = item(208, "crop.fruit.pea")
            .addOreDicts("cropPea", "fruitPea")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        OREGANO = item(209, "crop.fruit.oregano")
            .addOreDicts("cropOregano", "fruitOregano")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        HORSERADISH = item(210, "crop.fruit.horseradish")
            .addOreDicts("cropHorseradish", "fruitHorseradish")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        GARLIC_BULB = item(211, "crop.fruit.garlic_bulb")
            .addOreDicts("cropGarlic", "fruitGarlic")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BASIL = item(212, "crop.fruit.basil")
            .addOreDicts("cropBasil", "fruitBasil")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        AUBERGINE = item(213, "crop.fruit.aubergine")
            .addOreDicts("cropAubergine", "fruitAubergine")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        CORN = item(214, "crop.fruit.corn")
            .addOreDicts("cropCorn", "fruitCorn")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ARTICHOKE = item(215, "crop.fruit.artichoke")
            .addOreDicts("cropArtichoke", "fruitArtichoke")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        BLACK_PEPPER = item(216, "crop.fruit.black_pepper")
            .addOreDicts("cropBlackPepper", "fruitBlackPepper")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        RICE = item(217, "crop.fruit.rice")
            .addOreDicts("cropRice", "fruitRice")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        WHITE_GRAPE = item(218, "crop.fruit.white_grape")
            .addComponents(FoodBehavior(1, 1f))
            .addOreDicts("cropWhiteGrape", "fruitWhiteGrape")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        COTTON = item(219, "crop.fruit.cotton")
            .addOreDicts("cropCotton", "fruitCotton")
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        // endregion

        // region 301-500: Tableware
        CLAY_BOWL = item(301, "food.tableware.component.clay_bowl")
        CERAMIC_BOWL = item(302, "food.tableware.ceramic_bowl")
        DIRTY_CERAMIC_BOWL = item(303, "food.tableware.dirty_ceramic_bowl")
        PAPER_CONE = item(304, "food.tableware.paper_cone")
        CLAY_CUP = item(305, "food.tableware.component.clay_cup")
        CERAMIC_CUP = item(306, "food.tableware.ceramic_cup")
        PLASTIC_BOTTLE = item(307, "food.tableware.plastic_bottle")

        // endregion

        // region 501-700: Drinks
        GRAPE_JUICE = item(501, "food.drink.grape_juice")
            .addComponents(FoodBehavior(3, 0.2F, true, true, ItemStack(Items.GLASS_BOTTLE)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        RED_WINE = item(502, "food.drink.red_wine")
            .addComponents(FoodBehavior(4, 0.7f, true, true, ItemStack(Items.GLASS_BOTTLE),
                RandomPotionEffect(MobEffects.NAUSEA, 30 * SECOND, 0, 100 - 60),
                RandomPotionEffect(MobEffects.RESISTANCE, 20 * SECOND, 0, 100 - 40))
                .setEatingDuration(8 * SECOND + 16 * TICK))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        VINEGAR = item(503, "food.drink.vinegar")
            .addComponents(FoodBehavior(2, 0.5f, true, true, ItemStack(Items.GLASS_BOTTLE),
                RandomPotionEffect(MobEffects.RESISTANCE, 10 * SECOND, 0, 100 - 30)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        POTATO_JUICE = item(504, "food.drink.potato_juice")
            .addComponents(FoodBehavior(4, 0.4F, true, true, ItemStack(Items.GLASS_BOTTLE),
                RandomPotionEffect(MobEffects.NAUSEA, 25 * SECOND, 0, 100 - 80)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        VODKA = item(505, "food.drink.vodka")
            .addComponents(FoodBehavior(4, 0.8F, true, true, ItemStack(Items.GLASS_BOTTLE),
                RandomPotionEffect(MobEffects.NAUSEA, 20 * SECOND, 0, 100 - 80),
                RandomPotionEffect(MobEffects.RESISTANCE, 40 * SECOND, 2, 100 - 80))
                .setEatingDuration(6 * SECOND + 10 * TICK))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        COFFEE_CUP = item(506, "food.drink.coffee_cup")
            .addComponents(FoodBehavior(8, 0.4F, true, true, CERAMIC_CUP.stackForm,
                RandomPotionEffect(MobEffects.REGENERATION, 60, 1, 0),
                RandomPotionEffect(MobEffects.SPEED, 1800, 2, 0)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ORANGE_JUICE = item(507, "food.drink.orange_juice")
            .addComponents(FoodBehavior(3, 0.3F, true, true, ItemStack(Items.GLASS_BOTTLE)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        ETIRPS = item(508, "food.drink.etirps")
            .addComponents(FoodBehavior(2, 0.4f, true, true, PLASTIC_BOTTLE.stackForm,
                RandomPotionEffect(MobEffects.SPEED, 1 * MINUTE, 2, 0)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        SPARKLING_WATER = item(509, "food.drink.sparkling_water")
            .addComponents(FoodBehavior(2, 0.3F, true, true, PLASTIC_BOTTLE.stackForm,
                RandomPotionEffect(MobEffects.SPEED, 30 * SECOND, 1, 0)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        CRANBERRY_ETIRPS = item(510, "food.drink.cranberry_etirps")
            .addComponents(FoodBehavior(3, 0.4f, true, true, PLASTIC_BOTTLE.stackForm,
                RandomPotionEffect(MobEffects.SPEED, 1200, 2, 0),
                RandomPotionEffect(MobEffects.REGENERATION, 200, 1, 100 - 80)))

        // endregion

        // region 701-900: Congees & Soups
        POLENTA = item(701, "food.polenta")
            .addComponents(TooltipBehavior {
                it.add(I18n.format("metaitem.food.polenta.tooltip.1"))
                it.add(I18n.format("metaitem.food.polenta.tooltip.2"))
            })
            .addComponents(FoodBehavior(6, 0.4F)
                               .setReturnStack(DIRTY_CERAMIC_BOWL)
                               .setPotionEffects(RandomPotionEffect(MobEffects.SATURATION, 1 * SECOND, 0, 100 - 50)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        // endregion

        // region 901-1100: Noodles
        GRAHAM_CRACKER = item(901, "food.graham_cracker")
            .addComponents(FoodBehavior(2, 0.6F))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        // endregion

        // region 1101-1300: Breads

        // endregion

        // region 1301-1500: Dairy Products

        // endregion

        // region 1501-1700: Barbecue & Kebab

        // endregion

        // region 1701-1900: Sugar Products

        HARD_APPLE_CANDY = item(1701, "food.hard_apple_candy")
            .addComponents(FoodBehavior(3, 0.5f)
                               .setEatingDuration(1 * SECOND + 4 * TICK)
                               .setPotionEffects(RandomPotionEffect(MobEffects.REGENERATION, 1 * MINUTE, 1, 50)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        // endregion

        // region 1901-2100: Fruit Products
        SILVER_APPLE = item(1901, "fruit.product.silver_apple")
            .addComponents(FoodBehavior(4, 3.5F)
                               .setPotionEffects(RandomPotionEffect(MobEffects.INSTANT_HEALTH, 1 * TICK, 2, 100 - 100),
                                                 RandomPotionEffect(MobEffects.RESISTANCE, 25 * SECOND, 2, 100 - 100)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        SILVER_STRAWBERRY = item(1902, "fruit.product.silver_strawberry")
            .addComponents(FoodBehavior(5, 7.5F)
                               .setPotionEffects(RandomPotionEffect(MobEffects.INSTANT_HEALTH, 1 * TICK, 3, 100 - 100),
                                                 RandomPotionEffect(MobEffects.RESISTANCE, 40 * SECOND, 2, 100 - 100),
                                                 RandomPotionEffect(MobEffects.ABSORPTION, 30 * SECOND, 3, 100 - 75)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        GOLDEN_STRAWBERRY = item(1903, "fruit.product.golden_strawberry")
            .addComponents(FoodBehavior(8, 9.6F)
                               .setEatingDuration(5 * SECOND)
                               .setPotionEffects(RandomPotionEffect(MobEffects.INSTANT_HEALTH, 1 * TICK, 3, 100 - 100),
                                                 RandomPotionEffect(MobEffects.REGENERATION, 20 * SECOND, 10, 100 - 100),
                                                 RandomPotionEffect(MobEffects.RESISTANCE, 2 * MINUTE + 30 * SECOND, 3, 100 - 100),
                                                 RandomPotionEffect(MobEffects.ABSORPTION, 1 * MINUTE, 6, 100 - 100)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        RAINBOW_BERRY = item(1904, "fruit.product.rainbow_berry")
            .addComponents(FoodBehavior(12, 12.8F)
                               .setEatingDuration(5 * SECOND)
                               .setPotionEffects(RandomPotionEffect(MobEffects.INSTANT_HEALTH, 1 * TICK, 10, 100 - 100),
                                                 RandomPotionEffect(MobEffects.REGENERATION, 1 * MINUTE, 10, 100 - 100),
                                                 RandomPotionEffect(MobEffects.RESISTANCE, 2 * MINUTE, 10, 100 - 100),
                                                 RandomPotionEffect(MobEffects.ABSORPTION, 2 * MINUTE, 10, 100 - 100),
                                                 RandomPotionEffect(MobEffects.STRENGTH, 2 * MINUTE, 10, 100 - 100),
                                                 RandomPotionEffect(MobEffects.FIRE_RESISTANCE, 5 * MINUTE, 10, 100 - 100)))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        MOON_BERRY = item(1905, "fruit.product.moon_berry")
            .addComponents(FoodBehavior(16, 19.2F)
                               .setEatingDuration(5 * SECOND)
                               .setPotionEffects(RandomPotionEffect(MobEffects.INSTANT_HEALTH, 1 * TICK, 127, 100 - 100),
                                                 RandomPotionEffect(MobEffects.REGENERATION, 2 * MINUTE, 127, 100 - 100),
                                                 RandomPotionEffect(MobEffects.RESISTANCE, 10 * MINUTE, 127, 100 - 100),
                                                 RandomPotionEffect(MobEffects.ABSORPTION, 5 * MINUTE, 127, 100 - 100),
                                                 RandomPotionEffect(MobEffects.STRENGTH, 8 * MINUTE, 127, 100 - 100),
                                                 RandomPotionEffect(MobEffects.FIRE_RESISTANCE, 20 * MINUTE, 127, 100 - 100),
                                                 RandomPotionEffect(MobEffects.WATER_BREATHING, 20 * MINUTE, 127, 100 - 100),
                                                 RandomPotionEffect(MobEffects.HASTE, 15 * MINUTE, 127, 100 - 100),
                                                 RandomPotionEffect(MobEffects.SATURATION, 8 * MINUTE, 127, 100 - 100),
                                                 RandomPotionEffect(MobEffects.LUCK, 10 * MINUTE, 127, 100 - 100)))
            .addComponents(HaloRenderItemBehavior(10, 0xFFFFFFFF.toInt(), {{ GTLiteTextures.HALO }}, true))
            .setCreativeTabs(GTLiteCreativeTabs.TAB_FOOD)

        // endregion

        // region 2101-2300: Snacks

        // endregion

        // region 2301-2500: Miscellaneous Foods

        // endregion

    }

    private fun item(id: Int, name: String) = META_ITEMS_2.addItem(id, name)

}