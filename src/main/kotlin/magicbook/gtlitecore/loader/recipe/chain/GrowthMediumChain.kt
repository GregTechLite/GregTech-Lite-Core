package magicbook.gtlitecore.loader.recipe.chain

import gregtech.api.GTValues.EV
import gregtech.api.GTValues.HV
import gregtech.api.GTValues.IV
import gregtech.api.GTValues.L
import gregtech.api.GTValues.LV
import gregtech.api.GTValues.LuV
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtech.api.GTValues.V
import gregtech.api.GTValues.VA
import gregtech.api.GTValues.VHA
import gregtech.api.GTValues.ZPM
import gregtech.api.metatileentity.multiblock.CleanroomType
import gregtech.api.recipes.GTRecipeHandler
import gregtech.api.recipes.RecipeMaps.BREWING_RECIPES
import gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES
import gregtech.api.recipes.RecipeMaps.CHEMICAL_BATH_RECIPES
import gregtech.api.recipes.RecipeMaps.DISTILLERY_RECIPES
import gregtech.api.recipes.RecipeMaps.EXTRACTOR_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_HEATER_RECIPES
import gregtech.api.recipes.RecipeMaps.FLUID_SOLIDFICATION_RECIPES
import gregtech.api.recipes.RecipeMaps.MIXER_RECIPES
import gregtech.api.recipes.RecipeMaps.PACKER_RECIPES
import gregtech.api.recipes.ingredients.IntCircuitIngredient
import gregtech.api.unification.OreDictUnifier
import gregtech.api.unification.material.Materials.BacterialSludge
import gregtech.api.unification.material.Materials.Biomass
import gregtech.api.unification.material.Materials.Clay
import gregtech.api.unification.material.Materials.Collagen
import gregtech.api.unification.material.Materials.DistilledWater
import gregtech.api.unification.material.Materials.EnrichedBacterialSludge
import gregtech.api.unification.material.Materials.GelatinMixture
import gregtech.api.unification.material.Materials.Hydrogen
import gregtech.api.unification.material.Materials.Meat
import gregtech.api.unification.material.Materials.Mutagen
import gregtech.api.unification.material.Materials.Naquadria
import gregtech.api.unification.material.Materials.Nitrogen
import gregtech.api.unification.material.Materials.PhosphoricAcid
import gregtech.api.unification.material.Materials.RawGrowthMedium
import gregtech.api.unification.material.Materials.SterileGrowthMedium
import gregtech.api.unification.material.Materials.Sugar
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.Uranium235
import gregtech.api.unification.material.Materials.Uranium238
import gregtech.api.unification.material.Materials.Water
import gregtech.api.unification.material.Materials.Wheat
import gregtech.api.unification.ore.OrePrefix.dust
import gregtech.api.unification.ore.OrePrefix.dustTiny
import gregtech.common.items.MetaItems.PETRI_DISH
import gregtech.common.items.MetaItems.SHAPE_MOLD_BALL
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.BIO_REACTOR_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.LARGE_MIXER_RECIPES
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.SONICATION_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BFGF
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Biotin
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Blood
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BloodCells
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BloodPlasma
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.BrevibacteriumFlavum
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CAT
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Cellulose
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CupriavidusNecator
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EGF
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EscherichiaColi
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Glucose
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Glutamine
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.HydrogenPeroxide
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.LinoleicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PiranhaSolution
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.RedAlgae
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Sorbose
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.StreptococcusPyogenes
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.SuccinicAcid
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Yeast
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.MINUTE
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.SECOND
import magicbook.gtlitecore.api.utils.GTLiteValues.Companion.TICK
import magicbook.gtlitecore.api.utils.GTRecipeUtility
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.BREVIBACTERIUM_FLAVUM_PETRI_DISH
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.CUPRIAVIDUS_NECATOR_PETRI_DISH
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.DIRTY_PETRI_DISH
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ESCHERICHIA_COLI_PETRI_DISH
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.MUD_BALL
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.STREPTOCOCCUS_PYOGENES_PETRI_DISH
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@Suppress("MISSING_DEPENDENCY_CLASS")
class GrowthMediumChain
{

    companion object
    {

        fun init()
        {
            bloodProcess()
            bacterialGrowthMediumProcess() // RawGrowthMedium processing, used to all bacteria.
            bacteriasProcess()
            sterilizedGrowthMediumProcess()
        }

        private fun bloodProcess()
        {
            // Blood -> Blood Cells + Blood Plasma
            SONICATION_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Blood.getFluid(1000))
                .fluidOutputs(BloodCells.getFluid(500))
                .fluidOutputs(BloodPlasma.getFluid(500))
                .EUt(VA[HV].toLong())
                .duration(40 * SECOND)
                .buildAndRegister()

            // Blood Plasma -> bFGF, EGF, CAT
            CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(BloodPlasma.getFluid(1000))
                .fluidOutputs(BFGF.getFluid(200))
                .fluidOutputs(EGF.getFluid(200))
                .fluidOutputs(CAT.getFluid(200))
                .EUt(VA[HV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Blood and Meat convert.
            FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(SHAPE_MOLD_BALL)
                .fluidInputs(Blood.getFluid(L))
                .output(dust, Meat)
                .EUt(VA[ULV].toLong())
                .duration(4 * TICK)
                .buildAndRegister()

            EXTRACTOR_RECIPES.recipeBuilder()
                .input(dust, Meat)
                .fluidOutputs(Blood.getFluid(L))
                .EUt(V[ULV])
                .duration(8 * TICK)
                .buildAndRegister()

        }

        private fun bacterialGrowthMediumProcess()
        {
            // Deleted original BacterialSludge -> EnrichedBacterialSludge brewing recipe.
            GTRecipeHandler.removeRecipesByInputs(BREWING_RECIPES,
                arrayOf(OreDictUnifier.get(dust, Uranium238)),
                arrayOf(BacterialSludge.getFluid(1000)))

            GTRecipeHandler.removeRecipesByInputs(BREWING_RECIPES,
                arrayOf(OreDictUnifier.get(dustTiny, Uranium235)),
                arrayOf(BacterialSludge.getFluid(1000)))

            GTRecipeHandler.removeRecipesByInputs(BREWING_RECIPES,
                arrayOf(OreDictUnifier.get(dustTiny, Naquadria)),
                arrayOf(BacterialSludge.getFluid(1000)))

            // 2x BacterialSludge -> 1x EnrichedBacterialSludge
            FLUID_HEATER_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(BacterialSludge.getFluid(200))
                .fluidOutputs(EnrichedBacterialSludge.getFluid(100))
                .EUt(VA[EV].toLong())
                .duration(6 * SECOND + 8 * TICK)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            // Deleted original EnrichedBacterialSludge -> Mutagen distillation recipe.
            GTRecipeHandler.removeRecipesByInputs(DISTILLERY_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(EnrichedBacterialSludge.getFluid(1000)))

            // 1x EnrichedBacterialSludge -> 1x Mutagen
            BREWING_RECIPES.recipeBuilder()
                    .input("dustTinyRadioactive")
                    .fluidInputs(EnrichedBacterialSludge.getFluid(1000))
                    .fluidOutputs(Mutagen.getFluid(1000))
                    .EUt(VHA[HV].toLong())
                    .duration(20 * SECOND)
                    .cleanroom(CleanroomType.STERILE_CLEANROOM)
                    .buildAndRegister()

            // Gelatin Mixture
            GTRecipeUtility.removeMixerRecipes(
                arrayOf(OreDictUnifier.get(dust, Collagen, 4)),
                arrayOf(PhosphoricAcid.getFluid(1000),
                    Water.getFluid(3000)))

            MIXER_RECIPES.recipeBuilder()
                .circuitMeta(4)
                .input(dust, Collagen, 4)
                .input(dust, RedAlgae, 2)
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .fluidInputs(DistilledWater.getFluid(2000))
                .fluidOutputs(GelatinMixture.getFluid(6000))
                .EUt(VA[HV].toLong())
                .duration(1 * MINUTE + 20 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

        }

        private fun bacteriasProcess()
        {
            // Mud Ball
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .inputs(ItemStack(Blocks.DIRT))
                .fluidInputs(Water.getFluid(1000))
                .output(MUD_BALL, 4)
                .EUt(VA[ULV].toLong())
                .duration(10 * TICK)
                .buildAndRegister()

            // Yeast
            BREWING_RECIPES.recipeBuilder()
                .input(dust, Wheat)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, Yeast, 2)
                .EUt(VA[LV].toLong())
                .duration(4 * TICK)
                .buildAndRegister()

            // H2SO4 + H2O2 -> (H2SO4)(H2O2)
            MIXER_RECIPES.recipeBuilder()
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(PiranhaSolution.getFluid(2000))
                .EUt(VA[MV].toLong())
                .duration(2 * SECOND + 10 * TICK)
                .buildAndRegister()

            // Dirty Petri Dish -> Petri Dish convert
            CHEMICAL_BATH_RECIPES.recipeBuilder()
                .input(DIRTY_PETRI_DISH)
                .fluidInputs(PiranhaSolution.getFluid(100))
                .output(PETRI_DISH)
                .EUt(VA[LV].toLong())
                .duration(1 * SECOND + 5 * TICK)
                .buildAndRegister()

            // Brevibacterium Flavum Petri Dish
            BIO_REACTOR_RECIPES.recipeBuilder()
                .input(PETRI_DISH)
                .input(MUD_BALL)
                .fluidInputs(RawGrowthMedium.getFluid(100))
                .output(BREVIBACTERIUM_FLAVUM_PETRI_DISH)
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            PACKER_RECIPES.recipeBuilder()
                .input(BREVIBACTERIUM_FLAVUM_PETRI_DISH)
                .output(dust, BrevibacteriumFlavum)
                .output(DIRTY_PETRI_DISH)
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Cupriavidus Necator Petri Dish
            BIO_REACTOR_RECIPES.recipeBuilder()
                .input(PETRI_DISH)
                .input(dust, Clay)
                .fluidInputs(RawGrowthMedium.getFluid(100))
                .output(CUPRIAVIDUS_NECATOR_PETRI_DISH)
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            PACKER_RECIPES.recipeBuilder()
                .input(CUPRIAVIDUS_NECATOR_PETRI_DISH)
                .output(dust, CupriavidusNecator)
                .output(DIRTY_PETRI_DISH)
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Streptococcus Pyogenes Petri Dish (for Sugar processing, it is not required in this chain).
            BIO_REACTOR_RECIPES.recipeBuilder()
                .input(PETRI_DISH)
                .inputs(ItemStack(Items.ROTTEN_FLESH))
                .fluidInputs(RawGrowthMedium.getFluid(100))
                .output(STREPTOCOCCUS_PYOGENES_PETRI_DISH)
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            PACKER_RECIPES.recipeBuilder()
                .input(STREPTOCOCCUS_PYOGENES_PETRI_DISH)
                .output(dust, StreptococcusPyogenes)
                .output(DIRTY_PETRI_DISH)
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

            // Escherichia Coli Petri Dish (for Succinic Acid processing, it is not required in this chain).
            BIO_REACTOR_RECIPES.recipeBuilder()
                .input(PETRI_DISH)
                .input(dust, Meat)
                .fluidInputs(RawGrowthMedium.getFluid(100))
                .output(ESCHERICHIA_COLI_PETRI_DISH)
                .EUt(VA[LuV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            PACKER_RECIPES.recipeBuilder()
                .input(ESCHERICHIA_COLI_PETRI_DISH)
                .output(dust, EscherichiaColi)
                .output(DIRTY_PETRI_DISH)
                .EUt(VA[HV].toLong())
                .duration(15 * SECOND)
                .buildAndRegister()

        }

        private fun sterilizedGrowthMediumProcess()
        {

            // Brevibacterium Flavum + Sugar -> 2C5H10N2O3
            BIO_REACTOR_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, BrevibacteriumFlavum)
                .input(dust, Sugar)
                .fluidInputs(DistilledWater.getFluid(1000))
                .output(dust, Glutamine, 40)
                .EUt(VA[IV].toLong())
                .duration(25 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            // Shallow copying of Brevibacterium Flavum
            BIO_REACTOR_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, BrevibacteriumFlavum)
                .fluidInputs(RawGrowthMedium.getFluid(100))
                .fluidInputs(DistilledWater.getFluid(1000))
                .output(dust, BrevibacteriumFlavum, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            // Linoleic Acid
            BREWING_RECIPES.recipeBuilder()
                .input(dust, Yeast)
                .fluidInputs(Biomass.getFluid(1000))
                .fluidOutputs(LinoleicAcid.getFluid(1000))
                .EUt(VA[EV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister()

            // Vitamin-H
            BIO_REACTOR_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, CupriavidusNecator)
                .input(dust, Sugar)
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .output(dust, Biotin, 64) // 2x
                .EUt(VA[IV].toLong())
                .duration(25 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            // Shallow copying of Cupriavidus Necator
            BIO_REACTOR_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, CupriavidusNecator)
                .fluidInputs(RawGrowthMedium.getFluid(100))
                .fluidInputs(DistilledWater.getFluid(1000))
                .output(dust, CupriavidusNecator, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            // Deleted original RawGrowthMedium -> SterileGrowthMedium fluid heating recipes.
            GTRecipeHandler.removeRecipesByInputs(FLUID_HEATER_RECIPES,
                arrayOf(IntCircuitIngredient.getIntegratedCircuit(1)),
                arrayOf(RawGrowthMedium.getFluid(100)))

            LARGE_MIXER_RECIPES.recipeBuilder()
                .circuitMeta(8)
                .input(dust, Glutamine, 20)
                .input(dust, Biotin, 32)
                .fluidInputs(Biomass.getFluid(16000))
                .fluidInputs(BloodCells.getFluid(4000))
                .fluidInputs(LinoleicAcid.getFluid(2000))
                .fluidInputs(BFGF.getFluid(500))
                .fluidInputs(EGF.getFluid(250))
                .fluidInputs(CAT.getFluid(100))
                .fluidOutputs(SterileGrowthMedium.getFluid(64000))
                .EUt(VA[ZPM].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            // Sorbose
            BIO_REACTOR_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, StreptococcusPyogenes)
                .input(dust, Glucose, 24)
                .output(dust, Sorbose, 24)
                .EUt(VA[IV].toLong())
                .duration(25 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            // Shallow copying of Streptococcus Pyogenes.
            BIO_REACTOR_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, StreptococcusPyogenes)
                .fluidInputs(RawGrowthMedium.getFluid(100))
                .fluidInputs(DistilledWater.getFluid(1000))
                .output(dust, StreptococcusPyogenes, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            // Succinic Acid
            BIO_REACTOR_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, EscherichiaColi)
                .input(dust, Sugar)
                .output(dust, SuccinicAcid, 14)
                .EUt(VA[IV].toLong())
                .duration(25 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            // Shallow copying of Escherichia Coli.
            BIO_REACTOR_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .input(dust, EscherichiaColi)
                .fluidInputs(RawGrowthMedium.getFluid(100))
                .fluidInputs(DistilledWater.getFluid(1000))
                .output(dust, EscherichiaColi, 2)
                .EUt(VA[HV].toLong())
                .duration(10 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

            // Glucose
            BIO_REACTOR_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .input(dust, EscherichiaColi)
                .input(dust, Cellulose, 21)
                .output(dust, Glucose, 24)
                .EUt(VA[IV].toLong())
                .duration(25 * SECOND)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister()

        }

    }

}