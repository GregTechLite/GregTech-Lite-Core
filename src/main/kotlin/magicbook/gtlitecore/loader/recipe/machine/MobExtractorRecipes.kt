package magicbook.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Milk
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.MOB_EXTRACTOR_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Blood
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import net.minecraft.entity.passive.EntityChicken
import net.minecraft.entity.passive.EntityCow
import net.minecraft.entity.passive.EntityHorse
import net.minecraft.entity.passive.EntityPig
import net.minecraft.entity.passive.EntitySheep
import net.minecraft.entity.passive.EntityVillager
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

@Suppress("MISSING_DEPENDENCY_CLASS")
class MobExtractorRecipes
{

    companion object
    {

        fun init()
        {
            // Cow
            MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidOutputs(Milk.getFluid(10))
                .EUt(VA[LV].toLong())
                .duration(10 * TICK)
                .mob(EntityCow::class.java)
                .buildAndRegister()

            MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidOutputs(Blood.getFluid(10))
                .EUt(VA[LV].toLong())
                .duration(10 * TICK)
                .mob(EntityCow::class.java)
                .buildAndRegister()

            // Sheep
            MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .outputs(ItemStack(Blocks.WOOL))
                .EUt(VA[MV].toLong())
                .duration(10 * TICK)
                .mob(EntitySheep::class.java)
                .buildAndRegister()

            MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .fluidOutputs(Blood.getFluid(5))
                .EUt(VA[LV].toLong())
                .duration(10 * TICK)
                .mob(EntitySheep::class.java)
                .buildAndRegister()

            // Pig
            MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .circuitMeta(5)
                .fluidOutputs(Blood.getFluid(8))
                .EUt(VA[LV].toLong())
                .duration(10 * TICK)
                .mob(EntityPig::class.java)
                .buildAndRegister()

            // Chicken
            MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .outputs(ItemStack(Items.EGG))
                .EUt(VA[LV].toLong())
                .duration(10 * TICK)
                .mob(EntityChicken::class.java)
                .buildAndRegister()

            MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .circuitMeta(7)
                .fluidOutputs(Blood.getFluid(3))
                .EUt(VA[LV].toLong())
                .duration(10 * TICK)
                .mob(EntityChicken::class.java)
                .buildAndRegister()

            // Horse
            MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .fluidOutputs(Blood.getFluid(80))
                .EUt(VA[MV].toLong())
                .duration(10 * TICK)
                .mob(EntityHorse::class.java)
                .buildAndRegister()

            // Villager
            MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .circuitMeta(9)
                .fluidOutputs(Blood.getFluid(100))
                .EUt(VA[HV].toLong())
                .duration(10 * TICK)
                .mob(EntityVillager::class.java)
                .buildAndRegister()

            // Player
            MOB_EXTRACTOR_RECIPES.recipeBuilder()
                .circuitMeta(10)
                .fluidOutputs(Blood.getFluid(200))
                .EUt(VA[EV].toLong())
                .duration(10 * TICK)
                .mob(ResourceLocation("player"))
                .buildAndRegister()

            // Zombie

            // Skeleton

            // ...

            // Mooshroom

        }

    }

}