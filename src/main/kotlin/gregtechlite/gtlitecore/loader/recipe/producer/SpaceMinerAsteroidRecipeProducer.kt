package gregtechlite.gtlitecore.loader.recipe.producer

import gregtech.api.GTValues.IV
import gregtech.api.GTValues.VA
import gregtech.api.unification.material.Materials.Antimony
import gregtech.api.unification.material.Materials.Cadmium
import gregtech.api.unification.material.Materials.Charcoal
import gregtech.api.unification.material.Materials.Coal
import gregtech.api.unification.material.Materials.Cobaltite
import gregtech.api.unification.material.Materials.Coke
import gregtech.api.unification.material.Materials.Copper
import gregtech.api.unification.material.Materials.Diamond
import gregtech.api.unification.material.Materials.Gold
import gregtech.api.unification.material.Materials.Graphite
import gregtech.api.unification.material.Materials.Iron
import gregtech.api.unification.material.Materials.Lead
import gregtech.api.unification.material.Materials.Nickel
import gregtech.api.unification.material.Materials.Silver
import gregtech.api.unification.material.Materials.Tin
import gregtech.api.unification.ore.OrePrefix.block
import gregtech.api.unification.ore.OrePrefix.oreEndstone
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.extension.EUt
import gregtechlite.gtlitecore.api.extension.addRecipe
import gregtechlite.gtlitecore.api.recipe.GTLiteRecipeMaps.SPACE_MINER_RECIPES
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Canfieldite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Digenite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Firestone
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Lignite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Otavite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Paraffin
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.RichHematite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Skutterudite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Stibiconite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Sylvanite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Trevorite
import gregtechlite.gtlitecore.api.unification.GTLiteMaterials.Vanadinite
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

        // endregion

        // region Legendary Asteroid (LuV)

        // endregion

        // region Exotic Asteroid (ZPM)

        // endregion

        // region Perfect Asteroid (UV)

        // endregion

        // region Tipler Asteroid (UHV)

        // endregion

        // region Gallifreyan Asteroid (UEV)

        // endregion

        // region Zenith Asteroid (UIV)

        // endregion
    }

}