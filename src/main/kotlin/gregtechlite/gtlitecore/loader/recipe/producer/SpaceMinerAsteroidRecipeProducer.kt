package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Almandine
import gregtech.api.unification.material.Materials.Amethyst
import gregtech.api.unification.material.Materials.Andradite
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Apatite
import gregtech.api.unification.material.Materials.Asbestos
import gregtech.api.unification.material.Materials.Bauxite
import gregtech.api.unification.material.Materials.Bentonite
import gregtech.api.unification.material.Materials.Biotite
import gregtech.api.unification.material.Materials.BlueTopaz
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.CertusQuartz
import gregtech.api.unification.material.Materials.Charcoal
import gregtech.api.unification.material.Materials.Cinnabar
import gregtech.api.unification.material.Materials.Coal
import gregtech.api.unification.material.Materials.Cobaltite
import gregtech.api.unification.material.Materials.Coke
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Diatomite
import gregtech.api.unification.material.Materials.Emerald
import gregtech.api.unification.material.Materials.GarnetRed
import gregtech.api.unification.material.Materials.GarnetYellow
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.GreenSapphire
import gregtech.api.unification.material.Materials.Grossular
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Kyanite
import gregtech.api.unification.material.Materials.Lapis
import gregtech.api.unification.material.Materials.Lazurite
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Lepidolite
import gregtech.api.unification.material.Materials.Mica
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Oilsands
import gregtech.api.unification.material.Materials.Olivine
import gregtech.api.unification.material.Materials.Opal
import gregtech.api.unification.material.Materials.Pollucite
import gregtech.api.unification.material.Materials.Pyrope
import gregtech.api.unification.material.Materials.Quartzite
import gregtech.api.unification.material.Materials.Realgar
import gregtech.api.unification.material.Materials.RockSalt
import gregtech.api.unification.material.Materials.Ruby
import gregtech.api.unification.material.Materials.Salt
import gregtech.api.unification.material.Materials.Saltpeter
import gregtech.api.unification.material.Materials.Sapphire
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Sodalite
import gregtech.api.unification.material.Materials.Spessartine
import gregtech.api.unification.material.Materials.Sphalerite
import gregtech.api.unification.material.Materials.Spodumene
import gregtech.api.unification.material.Materials.Sulfur
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.material.Materials.Topaz
import gregtech.api.unification.material.Materials.Trona
import gregtech.api.unification.material.Materials.Uvarovite
import gregtech.api.unification.material.Materials.Zircon
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.oreEndstone
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_MINER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Albite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Augite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Bytownite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Canfieldite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Digenite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Dolomite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Ferrosilite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Firestone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Kaolinite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Labradorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lignite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Muscovite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Oligoclase
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Orpiment
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Otavite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Paraffin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Phlogopite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Prasiolite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RichHematite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Skutterudite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Stibiconite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Sylvanite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Tanzanite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trevorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vanadinite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Wollastonite
import gregtechlite.gtlitecore.common.item.GTLiteMetaItems.MINING_DRONE_IV

object SpaceMinerAsteroidRecipeProducer
{
    fun produce()
    {
        // region Epic Asteroid (IV)

        // Coal Asteroid
        SPACE_MINER_RECIPES.addRecipe {
            circuitMeta(1)
            notConsumable(MINING_DRONE_IV)
            output(oreEndstone, Coal, 2048)
            output(oreEndstone, Lignite, 2048)
            output(oreEndstone, Graphite, 2048)
            output(oreEndstone, Diamond, 1024)
            output(oreEndstone, Firestone, 1024)
            output(block, Charcoal, 512)
            output(block, Coke, 512)
            output(block, Paraffin, 512)
            EUt(VA[IV])
            duration(20 * SECOND)
            CWUt(4)
            tier(1)
        }

        // Iron-Gold-Silver Asteroid
        SPACE_MINER_RECIPES.addRecipe {
            circuitMeta(2)
            notConsumable(MINING_DRONE_IV)
            output(oreEndstone, Iron, 1024)
            output(oreEndstone, Gold, 1024)
            output(oreEndstone, Silver, 1024)
            output(oreEndstone, RichHematite, 256)
            output(oreEndstone, RichHematite, 256)
            output(oreEndstone, RichHematite, 256)
            output(oreEndstone, RichHematite, 256)
            output(oreEndstone, Sylvanite, 256)
            output(oreEndstone, Sylvanite, 256)
            output(oreEndstone, Sylvanite, 256)
            output(oreEndstone, Sylvanite, 256)
            EUt(VA[IV])
            duration(20 * SECOND)
            CWUt(8)
            tier(1)
        }

        // Copper-Tin Asteroid
        SPACE_MINER_RECIPES.addRecipe {
            circuitMeta(3)
            notConsumable(MINING_DRONE_IV)
            output(oreEndstone, Copper, 1024)
            output(oreEndstone, Tin, 1024)
            output(oreEndstone, Digenite, 256)
            output(oreEndstone, Digenite, 256)
            output(oreEndstone, Digenite, 256)
            output(oreEndstone, Digenite, 256)
            output(oreEndstone, Canfieldite, 256)
            output(oreEndstone, Canfieldite, 256)
            output(oreEndstone, Canfieldite, 256)
            output(oreEndstone, Canfieldite, 256)
            EUt(VA[IV])
            duration(20 * SECOND)
            CWUt(6)
            tier(1)
        }

        // Nickel-Cobalt Asteroid
        SPACE_MINER_RECIPES.addRecipe {
            circuitMeta(4)
            notConsumable(MINING_DRONE_IV)
            output(oreEndstone, Nickel, 1024)
            output(oreEndstone, Cobaltite, 1024)
            output(oreEndstone, Trevorite, 256)
            output(oreEndstone, Trevorite, 256)
            output(oreEndstone, Trevorite, 256)
            output(oreEndstone, Trevorite, 256)
            output(oreEndstone, Skutterudite, 256)
            output(oreEndstone, Skutterudite, 256)
            output(oreEndstone, Skutterudite, 256)
            output(oreEndstone, Skutterudite, 256)
            EUt(VA[IV])
            duration(20 * SECOND)
            CWUt(6)
            tier(1)
        }

        // Lead-Antimony-Cadmium Asteroid
        SPACE_MINER_RECIPES.addRecipe {
            circuitMeta(5)
            notConsumable(MINING_DRONE_IV)
            output(oreEndstone, Lead, 1024)
            output(oreEndstone, Antimony, 1024)
            output(oreEndstone, Cadmium, 1024)
            output(oreEndstone, Vanadinite, 256)
            output(oreEndstone, Vanadinite, 256)
            output(oreEndstone, Vanadinite, 256)
            output(oreEndstone, Vanadinite, 256)
            output(oreEndstone, Stibiconite, 256)
            output(oreEndstone, Stibiconite, 256)
            output(oreEndstone, Stibiconite, 256)
            output(oreEndstone, Stibiconite, 256)
            output(oreEndstone, Otavite, 256)
            output(oreEndstone, Otavite, 256)
            output(oreEndstone, Otavite, 256)
            output(oreEndstone, Otavite, 256)
            EUt(VA[IV])
            duration(20 * SECOND)
            CWUt(10)
            tier(1)
        }

        // Salt-Sulfur Asteroid
        SPACE_MINER_RECIPES.addRecipe {
            circuitMeta(6)
            notConsumable(MINING_DRONE_IV)
            output(oreEndstone, Salt, 1024)
            output(oreEndstone, RockSalt, 1024)
            output(oreEndstone, Saltpeter, 1024)
            output(oreEndstone, Sulfur, 1024)
            output(oreEndstone, Sphalerite, 1024)
            output(oreEndstone, Lepidolite, 1024)
            output(oreEndstone, Spodumene, 1024)
            output(oreEndstone, Asbestos, 1024)
            output(oreEndstone, Diatomite, 1024)
            output(oreEndstone, Oilsands, 1024)
            EUt(VA[IV])
            duration(20 * SECOND)
            CWUt(4)
            tier(1)
        }

        // Misc Gems I Asteroid
        SPACE_MINER_RECIPES.addRecipe {
            circuitMeta(7)
            notConsumable(MINING_DRONE_IV)
            output(oreEndstone, Albite, 1024)
            output(oreEndstone, Oligoclase, 1024)
            output(oreEndstone, Prasiolite, 1024)
            output(oreEndstone, Labradorite, 1024)
            output(oreEndstone, Bytownite, 1024)
            output(oreEndstone, Cinnabar, 1024)
            output(oreEndstone, Ruby, 1024)
            output(oreEndstone, Emerald, 1024)
            output(oreEndstone, Olivine, 1024)
            output(oreEndstone, Sapphire, 1024)
            output(oreEndstone, GreenSapphire, 1024)
            output(oreEndstone, Lapis, 1024)
            output(oreEndstone, Lazurite, 1024)
            output(oreEndstone, Sodalite, 1024)
            output(oreEndstone, CertusQuartz, 1024)
            output(oreEndstone, Topaz, 1024)
            EUt(VA[IV])
            duration(20 * SECOND)
            CWUt(5)
            tier(1)
        }

        // Misc Gems II Asteroid
        SPACE_MINER_RECIPES.addRecipe {
            circuitMeta(8)
            notConsumable(MINING_DRONE_IV)
            output(oreEndstone, BlueTopaz, 1024)
            output(oreEndstone, Amethyst, 1024)
            output(oreEndstone, Opal, 1024)
            output(oreEndstone, Almandine, 1024)
            output(oreEndstone, Andradite, 1024)
            output(oreEndstone, Grossular, 1024)
            output(oreEndstone, Pyrope, 1024)
            output(oreEndstone, Spessartine, 1024)
            output(oreEndstone, Uvarovite, 1024)
            output(oreEndstone, Tanzanite, 1024)
            output(oreEndstone, Quartzite, 1024)
            output(oreEndstone, Realgar, 1024)
            output(oreEndstone, Orpiment, 1024)
            output(oreEndstone, Apatite, 1024)
            output(oreEndstone, GarnetRed, 1024)
            output(oreEndstone, GarnetYellow, 1024)
            EUt(VA[IV])
            duration(20 * SECOND)
            CWUt(5)
            tier(1)
        }

        // Mica-Kaolinite-Dolomite Asteroid
        SPACE_MINER_RECIPES.addRecipe {
            circuitMeta(9)
            notConsumable(MINING_DRONE_IV)
            output(oreEndstone, Mica, 1024)
            output(oreEndstone, Kyanite, 1024)
            output(oreEndstone, Bauxite, 1024)
            output(oreEndstone, Pollucite, 1024)
            output(oreEndstone, Muscovite, 1024)
            output(oreEndstone, Phlogopite, 1024)
            output(oreEndstone, Biotite, 1024)
            output(oreEndstone, Lepidolite, 1024)
            output(oreEndstone, Kaolinite, 1024)
            output(oreEndstone, Bentonite, 1024)
            output(oreEndstone, Augite, 1024)
            output(oreEndstone, Ferrosilite, 1024)
            output(oreEndstone, Spodumene, 1024)
            output(oreEndstone, Dolomite, 1024)
            output(oreEndstone, Wollastonite, 1024)
            output(oreEndstone, Trona, 1024)
            EUt(VA[IV])
            duration(20 * SECOND)
            CWUt(6)
            tier(1)
        }

        // endregion

        // region Legendary Asteroid (LuV)

        // Aluminium, Manganese, Chrome

        // endregion

        // region Exotic Asteroid (ZPM)

        // TODO Zirc output(oreEndstone, Zircon, 1024)
        // Radioactive
        // enriched naquadah, trinium
        // plat group

        // endregion

        // region Perfect Asteroid (UV)

        // rare earth
        // bedrockium

        // naquadria

        // endregion

        // region Tipler Asteroid (UHV)

        // adamantium

        // endregion

        // region Gallifreyan Asteroid (UEV)

        // vibranium

        // endregion

        // region Zenith Asteroid (UIV)

        // endregion
    }

}