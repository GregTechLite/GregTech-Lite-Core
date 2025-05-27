package magicbook.gtlitecore.core;

import magicbook.gtlitecore.api.utils.GTLiteValues;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

@Config(modid = GTLiteValues.MODID,
        name = GTLiteValues.MODID + '/' + GTLiteValues.MODID) // Put configurations to the total files with module configurations.
public class GTLiteConfigHolder
{

    @Comment("Config options for Mod Compatibility features")
    @Name("Compatibility Options")
    @RequiresMcRestart
    public static CompatibilityOptions compat = new CompatibilityOptions();

    @Comment("Config options for GregTech Lite Machines, Pipes, Cables, and Electric Items")
    @Name("Machine Options")
    @RequiresMcRestart
    public static MachineOptions machine = new MachineOptions();

    @Comment("Config options for GregTech Lite, GregTech and Vanilla Recipes")
    @Name("Recipe Options")
    @RequiresMcRestart
    public static RecipeOptions recipe = new RecipeOptions();

    @Comment("Config options for World Generation features")
    @Name("Worldgen Options")
    @RequiresMcRestart
    public static WorldGenOptions worldgen = new WorldGenOptions();

    public static class CompatibilityOptions
    {

        @Comment("Config options regarding JustEnoughItems (JEI) mod")
        @Name("JEI Compat Options")
        public JustEnoughItemsCompatOptions jeiCompat = new JustEnoughItemsCompatOptions();

        @Comment("Config options regarding Applied Energistics 2 (AE2) mod")
        @Name("AE2 Compat Options")
        public AppliedEnergisticsCompatOptions ae2Compat = new AppliedEnergisticsCompatOptions();

        public static class JustEnoughItemsCompatOptions
        {

            @Comment({"Make the Font Renderer in all pages be scaleable, it is useful for some large digits.",
                      "Default: true"})
            public boolean scaleableFontRenderer = true;

        }

        public static class AppliedEnergisticsCompatOptions
        {

            @Comment({"Add Circuit Assembly Line (CAL) recipes for all AE2 processors.",
                      "Default: true"})
            public boolean addProcessorCALRecipes = true;

        }

    }

    public static class MachineOptions
    {

        @Comment({"Modifiable settings for Energy Infuser, consists of maximum repaired damage, energy consumed and UU Matter consumed."})
        @Name("Energy Infuser Options")
        public EnergyInfuser energyInfuser = new EnergyInfuser();

        public static class EnergyInfuser
        {

            @Comment({"Maximum repaired durability of Energy Infuser during one working.", "Default: 1000"})
            @Name("Maximum durability repair during one working")
            @RangeInt(min = 1)
            public int maxRepairedDamagePerWorking = 1000;

            @Comment({"Energy consumed (GTEU) per one durability which be repaired in Energy Infuser", "Default: 1000"})
            @Name("Energy consumed per one durability")
            @RangeInt(min = 1)
            public int energyConsumedPerDurability = 1000;

            @Comment({"UU Matter consumed per one durability which be repaired in Energy Infuser", "Default: 1"})
            @Name("UU Matter consumed per one durability")
            @RangeInt(min = 1)
            public int uuMatterConsumedPerDurability = 1;

        }

    }

    public static class RecipeOptions
    {

    }

    public static class WorldGenOptions
    {

        @Comment({"Whether to disable all Addition Trees world generation.",
                  "Default: false"})
        public boolean disableAdditionTreesGeneration = false;

        @Comment({"Allowed to add Additional Items as loot in various structures.",
                  "Default: true"})
        public boolean addLoot = true;

    }

}
