package gregtechlite.gtlitecore.core

import gregtech.api.GTValues.MAX
import gregtech.api.GTValues.MV
import gregtech.api.GTValues.ULV
import gregtechlite.gtlitecore.api.MOD_ID
import gregtechlite.gtlitecore.api.SECOND
import gregtechlite.gtlitecore.api.TICK
import net.minecraftforge.common.config.Config
import net.minecraftforge.common.config.Config.Comment
import net.minecraftforge.common.config.Config.Name
import net.minecraftforge.common.config.Config.RangeDouble
import net.minecraftforge.common.config.Config.RangeInt
import net.minecraftforge.common.config.Config.RequiresMcRestart

@Config(modid = MOD_ID, name = "$MOD_ID/$MOD_ID")
object GTLiteConfigHolder
{
    @Comment("Config options for Mod Compatibility features")
    @Name("Compatibility Options")
    @RequiresMcRestart
    @JvmField
    val compat = CompatibilityOptions()

    @Comment("Config options for GregTech Lite Machines, Pipes and Cables")
    @Name("Machine Options")
    @RequiresMcRestart
    @JvmField
    val machine = MachineOptions()

    @Comment("Config options for GregTech Lite, GregTech and Vanilla Recipes")
    @Name("Recipe Options")
    @RequiresMcRestart
    @JvmField
    val recipe = RecipeOptions()

    @Comment("Config options for GregTech Lite Tools and Armors")
    @Name("Tool Options")
    @RequiresMcRestart
    @JvmField
    val tool = ToolOptions()

    @Comment("Config options for World Generation features")
    @Name("Worldgen Options")
    @RequiresMcRestart
    @JvmField
    val worldgen = WorldGenOptions()

    class CompatibilityOptions
    {
        @Comment("Config options regarding JustEnoughItems (JEI) mod")
        @Name("JEI Compat Options")
        @JvmField
        val jeiCompat = JEICompatOptions()

        @Comment("Config options regarding Applied Energistics 2 (AE2) mod")
        @Name("AE2 Compat Options")
        @JvmField
        val ae2Compat = AE2CompatOptions()

        class JEICompatOptions
        {

            @Comment("Make the Font Renderer in all pages be scaleable, it is useful for some large digits.",
                     "Default: true")
            @JvmField
            var scaleableFontRenderer: Boolean = true
        }

        class AE2CompatOptions
        {
            @Comment("Add Circuit Assembly Line (CAL) recipes for all AE2 processors.",
                     "Default: true")
            @JvmField
            var addProcessorCALRecipes: Boolean = true
        }
    }

    class MachineOptions
    {
        @Comment("Modifiable settings for Energy Infuser, consists of:",
                 "- Maximum repaired damage,",
                 "- Consumed energy count,",
                 "- Consumed UU Matter amount.")
        @Name("Energy Infuser Options")
        @JvmField
        val energyInfuser = EnergyInfuserOptions()

        @Comment("Modifiable settings for Large Fisher, consists of:",
                 "- Maximum progress time per cycle,",
                 "- Minimum water block fill count in structure.")
        @Name("Large Fisher Options")
        @JvmField
        val largeFisher = LargeFisherOptions()

        class EnergyInfuserOptions
        {
            @Comment("Maximum repaired durability of Energy Infuser per work.",
                     "Default: 1000")
            @Name("Maximum Durability Repair Per work")
            @RangeInt(min = 1)
            @JvmField
            var maxRepairedDamagePerWorking = 1000

            @Comment("Energy consumed (GTEU) per one durability which be repaired in Energy Infuser",
                     "Default: 1000")
            @Name("Energy Consumed Per Durability")
            @RangeInt(min = 1)
            @JvmField
            var energyConsumedPerDurability = 1000

            @Comment("UU Matter consumed per one durability which be repaired in Energy Infuser",
                     "Default: 1")
            @Name("UU Matter Consumed Per Durability")
            @RangeInt(min = 1)
            @JvmField
            var uuMatterConsumedPerDurability = 1
        }

        class LargeFisherOptions
        {
            @Comment("The progress time for Large Fisher each cycle working consumed",
                     "Default: 20 (1s)")
            @Name("Progress Time Per Cycle")
            @RangeInt(min = 1 * TICK)
            @JvmField
            var maxProgressTime = 1 * SECOND

            @Comment("The water fill count in Large Fisher structure for working",
                     "Default: 60")
            @Name("Minimum Water Fill Count")
            @RangeInt(min = 1, max = 98)
            @JvmField
            var waterFillCount = 60
        }
    }

    class RecipeOptions
    {

    }

    class ToolOptions
    {
        @Comment("Modifiable settings of Laser Destroyer tool")
        @Name("Laser Destroyer Options")
        @JvmField
        val laserDestroyer = LaserDestroyerOptions()

        class LaserDestroyerOptions
        {
            @Comment("The default energy tier of Laser Destroyer",
                     "Default: 2 (MV)")
            @Name("Energy Tier")
            @RangeInt(min = ULV, max = MAX)
            @JvmField
            var toolTier: Int = MV

            @Comment("The default capacity of Laser Destroyer",
                     "Default: 1,024,000 EU")
            @Name("Capacity")
            @RangeDouble(min = 0.toDouble(), max = Long.MAX_VALUE.toDouble())
            @JvmField
            var capacity: Double = 1024000.0
        }
    }

    class WorldGenOptions
    {
        @Comment("Whether to disable all Addition Trees world generation.",
                 "Default: false")
        @Name("Mod Tree Generation")
        @JvmField
        var disableAdditionTreesGeneration: Boolean = false

        @Comment("Whether to disable all Berry Bushes world generation.",
                 "Default: false")
        @Name("Mod Berry Bush Generation")
        @JvmField
        var disableAllBerriesGeneration: Boolean = false

        @Comment("Allowed to add Additional Items as loot in various structures.",
                 "Default: true")
        @Name("Mod Loot Table Addition")
        @JvmField
        var addLoot: Boolean = true
    }
}