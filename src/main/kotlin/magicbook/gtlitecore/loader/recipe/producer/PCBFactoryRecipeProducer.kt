package magicbook.gtlitecore.loader.recipe.producer

import com.google.common.collect.HashBiMap
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.Materials.Aluminium
import gregtech.api.unification.material.Materials.Americium
import gregtech.api.unification.material.Materials.AnnealedCopper
import gregtech.api.unification.material.Materials.Bronze
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Electrum
import gregtech.api.unification.material.Materials.Epoxy
import gregtech.api.unification.material.Materials.Europium
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Iron3Chloride
import gregtech.api.unification.material.Materials.NiobiumTitanium
import gregtech.api.unification.material.Materials.Palladium
import gregtech.api.unification.material.Materials.Platinum
import gregtech.api.unification.material.Materials.Polybenzimidazole
import gregtech.api.unification.material.Materials.Polyethylene
import gregtech.api.unification.material.Materials.Polytetrafluoroethylene
import gregtech.api.unification.material.Materials.PolyvinylChloride
import gregtech.api.unification.material.Materials.ReinforcedEpoxyResin
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.SterileGrowthMedium
import gregtech.api.unification.material.Materials.SulfuricAcid
import gregtech.api.unification.material.Materials.VanadiumGallium
import gregtech.api.unification.material.Materials.YttriumBariumCuprate
import gregtech.api.unification.ore.OrePrefix.foil
import gregtech.api.unification.ore.OrePrefix.plate
import gregtech.common.items.MetaItems.ADVANCED_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.ELITE_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.EXTREME_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.PLASTIC_CIRCUIT_BOARD
import gregtech.common.items.MetaItems.WETWARE_CIRCUIT_BOARD
import magicbook.gtlitecore.api.recipe.GTLiteRecipeMaps.Companion.PCB_FACTORY_RECIPES
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.CarbonNanotube
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.EthylenediaminePyrocatechol
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FluorinatedEthylenePropylene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Fullerene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.FullerenePolymerMatrix
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.KaptonE
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.KaptonK
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Kevlar
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.Phosphorene
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.PolyethyleneTerephthalate
import magicbook.gtlitecore.api.unification.GTLiteMaterials.Companion.ThalliumBariumCalciumCuprate
import magicbook.gtlitecore.api.unification.ore.GTLiteOrePrefix.Companion.nanite
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.INFINITE_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.PERFECT_CIRCUIT_BOARD
import magicbook.gtlitecore.common.item.GTLiteMetaItems.Companion.ULTIMATE_CIRCUIT_BOARD
import net.minecraft.item.ItemStack
import kotlin.math.ceil
import kotlin.math.pow
import kotlin.math.sqrt


@Suppress("MISSING_DEPENDENCY_CLASS")
class PCBFactoryRecipeProducer
{

    companion object
    {

        private val plasticTiers = HashBiMap.create<Material, Int>()
        private var plasticTier = 0

        fun produce()
        {
            addPlasticTier(Polyethylene, 1)
            addPlasticTier(PolyvinylChloride, 2)
            addPlasticTier(Polytetrafluoroethylene, 3)
            addPlasticTier(Epoxy, 4)
            addPlasticTier(ReinforcedEpoxyResin, 5)
            addPlasticTier(Polybenzimidazole, 6)
            addPlasticTier(KaptonK, 7)
            addPlasticTier(KaptonE, 8)
            addPlasticTier(Kevlar, 9)
            addPlasticTier(Fullerene, 10)
            addPlasticTier(CarbonNanotube, 11)
            addPlasticTier(FullerenePolymerMatrix, 12)

            // ---------------------------------------------------------------------------------------------------------
            // T1: Plastic Circuit Board
            for (tier in 1..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 1))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(PLASTIC_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(PLASTIC_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, AnnealedCopper, (16 * (sqrt(tier.toDouble()))).toInt())
                    .input(foil, Copper, (16 * sqrt(tier.toDouble())).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * sqrt(tier.toDouble())).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((250 * sqrt(tier.toDouble())).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier] * 3 / 4L)
                    .duration(ceil(600 / sqrt(1.5.pow(tier - 1.5))).toInt())
                    .tier(1)
                    .buildAndRegister()
            }

            // T2: Plastic Circuit Board
            for (tier in 1..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 0.5))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(PLASTIC_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(PLASTIC_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .notConsumable(nanite, Silver)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, AnnealedCopper, (16 * (sqrt(tier.toDouble()))).toInt())
                    .input(foil, Copper, (16 * sqrt(tier.toDouble())).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * sqrt(tier.toDouble())).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((250 * sqrt(tier.toDouble())).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(600 / sqrt(1.5.pow(tier - 1.5))).toInt())
                    .tier(2)
                    .buildAndRegister()
            }

            // T3: Plastic Circuit Board
            for (tier in 1..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(PLASTIC_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(PLASTIC_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .notConsumable(nanite, Gold)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, AnnealedCopper, (16 * (sqrt(tier.toDouble()))).toInt())
                    .input(foil, Copper, (16 * sqrt(tier.toDouble())).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * sqrt(tier.toDouble())).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((250 * sqrt(tier.toDouble())).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(600 / sqrt(1.5.pow(tier - 1.5))).toInt())
                    .tier(3)
                    .buildAndRegister()
            }

            // ---------------------------------------------------------------------------------------------------------
            // T1: Advanced Circuit Board
            for (tier in 2..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 2))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(ADVANCED_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(ADVANCED_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Gold, (16 * (sqrt((tier - 1).toDouble()))).toInt())
                    .input(foil, Electrum, (16 * (sqrt((tier - 1).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 1).toDouble()))).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((500 * (sqrt((tier - 1).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier] * 3 / 4L)
                    .duration(ceil(600 / sqrt(1.5.pow(tier - 2.5))).toInt())
                    .tier(1)
                    .buildAndRegister()
            }

            // T2: Advanced Circuit Board
            for (tier in 2..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 1.5))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(ADVANCED_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(ADVANCED_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .notConsumable(nanite, Silver)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Gold, (16 * (sqrt((tier - 1).toDouble()))).toInt())
                    .input(foil, Electrum, (16 * (sqrt((tier - 1).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 1).toDouble()))).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((500 * (sqrt((tier - 1).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(500 / sqrt(1.5.pow(tier - 2.5))).toInt())
                    .tier(2)
                    .buildAndRegister()
            }

            // T3: Advanced Circuit Board
            for (tier in 2..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 1))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(ADVANCED_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(ADVANCED_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .notConsumable(nanite, Gold)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Gold, (16 * (sqrt((tier - 1).toDouble()))).toInt())
                    .input(foil, Electrum, (16 * sqrt((tier - 1).toDouble())).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 1).toDouble()))).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((500 * (sqrt((tier - 1).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(400 / sqrt(1.5.pow(tier - 2.5))).toInt())
                    .tier(3)
                    .buildAndRegister()
            }

            // ---------------------------------------------------------------------------------------------------------
            // T1: Extreme Circuit Board
            for (tier in 3..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 3))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(EXTREME_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(EXTREME_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Aluminium, (16 * (sqrt((tier - 2).toDouble()))).toInt())
                    .input(foil, Bronze, (16 * (sqrt((tier - 2).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 2).toDouble()))).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((1000 * (sqrt((tier - 2).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier] * 3 / 4L)
                    .duration(ceil(600 / sqrt(1.5.pow(tier - 3.5))).toInt())
                    .tier(1)
                    .buildAndRegister()
            }

            // T2: Extreme Circuit Board
            for (tier in 3..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 2.5))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(EXTREME_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(EXTREME_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .notConsumable(nanite, Silver)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Aluminium, (16 * (sqrt((tier - 2).toDouble()))).toInt())
                    .input(foil, Bronze, (16 * (sqrt((tier - 2).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 2).toDouble()))).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((1000 * (sqrt((tier - 2).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(500 / sqrt(1.5.pow(tier - 3.5))).toInt())
                    .tier(2)
                    .buildAndRegister()
            }

            // T3: Extreme Circuit Board
            for (tier in 3..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 2))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(EXTREME_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(EXTREME_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .notConsumable(nanite, Gold)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Aluminium, (16 * (sqrt((tier - 2).toDouble()))).toInt())
                    .input(foil, Bronze, (16 * (sqrt((tier - 2).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 2).toDouble()))).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((1000 * (sqrt((tier - 2).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(400 / sqrt(1.5.pow(tier - 3.5))).toInt())
                    .tier(3)
                    .buildAndRegister()
            }

            // ---------------------------------------------------------------------------------------------------------
            // T1: Elite Circuit Board
            for (tier in 4..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 4))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(ELITE_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(ELITE_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Palladium, (16 * (sqrt((tier - 3).toDouble()))).toInt())
                    .input(foil, Platinum, (16 * (sqrt((tier - 3).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 3).toDouble()))).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((2000 * (sqrt((tier - 3).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier] * 3 / 4L)
                    .duration(ceil(600 / sqrt(1.5.pow(tier - 4.5))).toInt())
                    .tier(1)
                    .buildAndRegister()
            }

            // T2: Elite Circuit Board
            for (tier in 4..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 3.5))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(ELITE_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(ELITE_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .notConsumable(nanite, Silver)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Palladium, (16 * (sqrt((tier - 3).toDouble()))).toInt())
                    .input(foil, Platinum, (16 * (sqrt((tier - 3).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 3).toDouble()))).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((2000 * (sqrt((tier - 3).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(500 / sqrt(1.5.pow(tier - 4.5))).toInt())
                    .tier(2)
                    .buildAndRegister()
            }

            // T3: Elite Circuit Board
            for (tier in 4..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 3))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(ELITE_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(ELITE_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .notConsumable(nanite, Gold)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Palladium, (16 * (sqrt((tier - 3).toDouble()))).toInt())
                    .input(foil, Platinum, (16 * (sqrt((tier - 3).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 3).toDouble()))).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((2000 * (sqrt((tier - 3).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(400 / sqrt(1.5.pow(tier - 4.5))).toInt())
                    .tier(3)
                    .buildAndRegister()
            }

            // ---------------------------------------------------------------------------------------------------------
            // T1: Wetware Circuit Board
            for (tier in 5..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 5))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(WETWARE_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(WETWARE_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, NiobiumTitanium, (16 * sqrt((tier - 4).toDouble())).toInt())
                    .input(foil, VanadiumGallium, (16 * sqrt((tier - 4).toDouble())).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * sqrt((tier - 4).toDouble())).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((4000 * sqrt((tier - 4).toDouble())).toInt()))
                    .fluidInputs(SterileGrowthMedium.getFluid((2000 * sqrt((tier - 4).toDouble())).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier] * 3 / 4L)
                    .duration(ceil(600 / sqrt(1.5.pow(tier - 5.5))).toInt())
                    .tier(1)
                    .upgradeTier(1)
                    .buildAndRegister()
            }

            // T2: Wetware Circuit Board
            for (tier in 5..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 4.5))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(WETWARE_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(WETWARE_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .notConsumable(nanite, Silver)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, NiobiumTitanium, (16 * (sqrt((tier - 4).toDouble()))).toInt())
                    .input(foil, VanadiumGallium, (16 * (sqrt((tier - 4).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 4).toDouble()))).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((4000 * (sqrt((tier - 4).toDouble()))).toInt()))
                    .fluidInputs(SterileGrowthMedium.getFluid((2000 * sqrt((tier - 4).toDouble())).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(500 / sqrt(1.5.pow(tier - 5.5))).toInt())
                    .tier(2)
                    .upgradeTier(1)
                    .buildAndRegister()
            }

            // T3: Wetware Circuit Board
            for (tier in 5..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 4))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64) {
                    boards.add(WETWARE_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(WETWARE_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .notConsumable(nanite, Gold)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, NiobiumTitanium, (16 * (sqrt((tier - 4).toDouble()))).toInt())
                    .input(foil, VanadiumGallium, (16 * (sqrt((tier - 4).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 4).toDouble()))).toInt()))
                    .fluidInputs(Iron3Chloride.getFluid((4000 * (sqrt((tier - 4).toDouble()))).toInt()))
                    .fluidInputs(SterileGrowthMedium.getFluid((2000 * (sqrt((tier - 4).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(400 / sqrt(1.5.pow(tier - 5.5))).toInt())
                    .tier(3)
                    .upgradeTier(1)
                    .buildAndRegister()
            }

            // ---------------------------------------------------------------------------------------------------------
            // T1: Ultimate Circuit Board
            for (tier in 6..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 6))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(ULTIMATE_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(ULTIMATE_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Europium, (16 * (sqrt((tier - 5).toDouble()))).toInt())
                    .input(foil, YttriumBariumCuprate, (16 * (sqrt((tier - 5).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 5).toDouble()))).toInt()))
                    .fluidInputs(EthylenediaminePyrocatechol.getFluid((6000 * (sqrt((tier - 5).toDouble()))).toInt()))
                    .fluidInputs(FluorinatedEthylenePropylene.getFluid((144 * (sqrt((tier - 5).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier] * 3 / 4L)
                    .duration(ceil(600 / sqrt(1.5.pow(tier - 5.5))).toInt())
                    .tier(1)
                    .buildAndRegister()
            }

            // T2: Ultimate Circuit Board
            for (tier in 6..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 5.5))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(ULTIMATE_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(ULTIMATE_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .notConsumable(nanite, Silver)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Europium, (16 * (sqrt((tier - 5).toDouble()))).toInt())
                    .input(foil, YttriumBariumCuprate, (16 * (sqrt((tier - 5).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 5).toDouble()))).toInt()))
                    .fluidInputs(EthylenediaminePyrocatechol.getFluid((6000 * (sqrt((tier - 5).toDouble()))).toInt()))
                    .fluidInputs(FluorinatedEthylenePropylene.getFluid((144 * (sqrt((tier - 5).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(500 / sqrt(1.5.pow(tier - 6.5))).toInt())
                    .tier(2)
                    .buildAndRegister()
            }

            // T3: Ultimate Circuit Board
            for (tier in 6..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 5))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(ULTIMATE_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(ULTIMATE_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .notConsumable(nanite, Gold)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Europium, (16 * (sqrt((tier - 5).toDouble()))).toInt())
                    .input(foil, YttriumBariumCuprate, (16 * (sqrt((tier - 5).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 5).toDouble()))).toInt()))
                    .fluidInputs(EthylenediaminePyrocatechol.getFluid((6000 * (sqrt((tier - 5).toDouble()))).toInt()))
                    .fluidInputs(FluorinatedEthylenePropylene.getFluid((144 * (sqrt((tier - 5).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(400 / sqrt(1.5.pow(tier - 6.5))).toInt())
                    .tier(3)
                    .buildAndRegister()
            }

            // ---------------------------------------------------------------------------------------------------------
            // T1: Perfect Circuit Board
            for (tier in 7..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 7))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(PERFECT_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(PERFECT_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Americium, (16 * (sqrt((tier - 6).toDouble()))).toInt())
                    .input(foil, ThalliumBariumCalciumCuprate, (16 * (sqrt((tier - 6).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 6).toDouble()))).toInt()))
                    .fluidInputs(EthylenediaminePyrocatechol.getFluid((8000 * (sqrt((tier - 6).toDouble()))).toInt()))
                    .fluidInputs(PolyethyleneTerephthalate.getFluid((288 * (sqrt((tier - 6).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier] * 3 / 4L)
                    .duration(ceil(600 / sqrt(1.5.pow(tier - 5.5))).toInt())
                    .tier(1)
                    .buildAndRegister()
            }

            // T2: Perfect Circuit Board
            for (tier in 7..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 6.5))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(PERFECT_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(PERFECT_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .notConsumable(nanite, Silver)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Americium, (16 * (sqrt((tier - 6).toDouble()))).toInt())
                    .input(foil, ThalliumBariumCalciumCuprate, (16 * (sqrt((tier - 6).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * (sqrt((tier - 6).toDouble()))).toInt()))
                    .fluidInputs(EthylenediaminePyrocatechol.getFluid((8000 * (sqrt((tier - 6).toDouble()))).toInt()))
                    .fluidInputs(PolyethyleneTerephthalate.getFluid((288 * (sqrt((tier - 6).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(500 / sqrt(1.5.pow(tier - 6.5))).toInt())
                    .tier(2)
                    .buildAndRegister()
            }

            // T3: Perfect Circuit Board
            for (tier in 7..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 6))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(PERFECT_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(PERFECT_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .notConsumable(nanite, Gold)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Americium, (16 * (sqrt((tier - 6).toDouble()))).toInt())
                    .input(foil, ThalliumBariumCalciumCuprate, (16 * (sqrt((tier - 6).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * sqrt((tier - 6).toDouble())).toInt()))
                    .fluidInputs(EthylenediaminePyrocatechol.getFluid((8000 * (sqrt((tier - 6).toDouble()))).toInt()))
                    .fluidInputs(PolyethyleneTerephthalate.getFluid((288 * (sqrt((tier - 6).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(400 / sqrt(1.5.pow(tier - 6.5))).toInt())
                    .tier(3)
                    .buildAndRegister()
            }

            // ---------------------------------------------------------------------------------------------------------
            // T1: Infinite Circuit Board
            for (tier in 8..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 8))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(INFINITE_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(INFINITE_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(1)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Fullerene, (16 * (sqrt((tier - 7).toDouble()))).toInt())
                    .input(foil, Phosphorene, (16 * (sqrt((tier - 7).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * sqrt((tier - 7).toDouble())).toInt()))
                    .fluidInputs(EthylenediaminePyrocatechol.getFluid((10000 * (sqrt((tier - 7).toDouble()))).toInt()))
                    .fluidInputs(FullerenePolymerMatrix.getFluid((576 * (sqrt((tier - 7).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier] * 3 / 4L)
                    .duration(ceil(600 / sqrt(1.5.pow(tier - 6.5))).toInt())
                    .tier(1)
                    .buildAndRegister()
            }

            // T2: Infinite Circuit Board
            for (tier in 8..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 7.5))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(INFINITE_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(INFINITE_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(2)
                    .notConsumable(nanite, Silver)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Fullerene, (16 * (sqrt((tier - 7).toDouble()))).toInt())
                    .input(foil, Phosphorene, (16 * (sqrt((tier - 7).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * sqrt((tier - 7).toDouble())).toInt()))
                    .fluidInputs(EthylenediaminePyrocatechol.getFluid((10000 * sqrt((tier - 7).toDouble())).toInt()))
                    .fluidInputs(FullerenePolymerMatrix.getFluid((576 * (sqrt((tier - 7).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(500 / sqrt(1.5.pow(tier - 6.5))).toInt())
                    .tier(2)
                    .buildAndRegister()
            }

            // T3: Infinite Circuit Board
            for (tier in 8..plasticTier)
            {
                var boardAmount = ceil(8 * sqrt(2.0.pow(tier - 7))).toInt()
                val boards: MutableList<ItemStack> = ArrayList()
                var i = boardAmount
                while (i > 64)
                {
                    boards.add(INFINITE_CIRCUIT_BOARD.getStackForm(64))
                    boardAmount -= 64
                    i -= 64
                }
                boards.add(INFINITE_CIRCUIT_BOARD.getStackForm(boardAmount))
                PCB_FACTORY_RECIPES.recipeBuilder()
                    .circuitMeta(3)
                    .notConsumable(nanite, Gold)
                    .input(plate, plasticTiers.inverse()[tier])
                    .input(foil, Fullerene, (16 * (sqrt((tier - 7).toDouble()))).toInt())
                    .input(foil, Phosphorene, (16 * (sqrt((tier - 7).toDouble()))).toInt())
                    .fluidInputs(SulfuricAcid.getFluid((500 * sqrt((tier - 7).toDouble())).toInt()))
                    .fluidInputs(EthylenediaminePyrocatechol.getFluid((10000 * sqrt((tier - 7).toDouble())).toInt()))
                    .fluidInputs(FullerenePolymerMatrix.getFluid((576 * (sqrt((tier - 7).toDouble()))).toInt()))
                    .outputs(*boards.toTypedArray())
                    .EUt(VA[tier + 1] * 3 / 4L)
                    .duration(ceil(400 / sqrt(1.5.pow(tier - 6.5))).toInt())
                    .tier(3)
                    .buildAndRegister()
            }

        }

        private fun addPlasticTier(material: Material, tier: Int)
        {
            plasticTiers[material] = tier
            plasticTier++
        }

    }

}