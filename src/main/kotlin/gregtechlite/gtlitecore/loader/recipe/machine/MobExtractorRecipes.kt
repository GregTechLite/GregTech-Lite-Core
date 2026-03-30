package gregtechlite.gtlitecore.loader.recipe.machine

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Milk
import gregtechlite.gtlitecore.api.TICK
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.extension.outputs
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.MOB_EXTRACTOR_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Blood
import net.minecraft.entity.passive.EntityChicken
import net.minecraft.entity.passive.EntityCow
import net.minecraft.entity.passive.EntityHorse
import net.minecraft.entity.passive.EntityPig
import net.minecraft.entity.passive.EntitySheep
import net.minecraft.entity.passive.EntityVillager
import net.minecraft.init.Blocks.WOOL
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

internal object MobExtractorRecipes
{

    // @formatter:off

    fun init()
    {
        // Cow
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(1)
            fluidOutputs(Milk.getFluid(10))
            EUt(VA[LV])
            duration(10 * TICK)
            mob(EntityCow::class.java)
        }

        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(2)
            fluidOutputs(Blood.getFluid(10))
            EUt(VA[LV])
            duration(10 * TICK)
            mob(EntityCow::class.java)
        }

        // Sheep
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(3)
            outputs(WOOL)
            EUt(VA[MV])
            duration(10 * TICK)
            mob(EntitySheep::class.java)
        }

        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(4)
            fluidOutputs(Blood.getFluid(5))
            EUt(VA[LV])
            duration(10 * TICK)
            mob(EntitySheep::class.java)
        }

        // Pig
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(5)
            fluidOutputs(Blood.getFluid(8))
            EUt(VA[LV])
            duration(10 * TICK)
            mob(EntityPig::class.java)
        }

        // Chicken
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(6)
            outputs(ItemStack(Items.EGG))
            EUt(VA[LV])
            duration(10 * TICK)
            mob(EntityChicken::class.java)
        }

        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(7)
            fluidOutputs(Blood.getFluid(3))
            EUt(VA[LV])
            duration(10 * TICK)
            mob(EntityChicken::class.java)
        }

        // Horse
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(8)
            fluidOutputs(Blood.getFluid(80))
            EUt(VA[MV])
            duration(10 * TICK)
            mob(EntityHorse::class.java)
        }

        // Villager
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(9)
            fluidOutputs(Blood.getFluid(100))
            EUt(VA[HV])
            duration(10 * TICK)
            mob(EntityVillager::class.java)
        }

        // Player
        MOB_EXTRACTOR_RECIPES.addRecipe {
            circuitMeta(10)
            fluidOutputs(Blood.getFluid(200))
            EUt(VA[EV])
            duration(10 * TICK)
            mob(ResourceLocation("player"))
        }

        // TODO: Zombie, Skeleton, Mooshroom, e.t.c.?
    }

    // @formatter:on

}