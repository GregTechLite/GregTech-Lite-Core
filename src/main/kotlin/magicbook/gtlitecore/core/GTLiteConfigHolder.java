package magicbook.gtlitecore.core;

import magicbook.gtlitecore.api.utils.GTLiteValues;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

@Config(modid = GTLiteValues.MODID,
        name = GTLiteValues.MODID + '/' + GTLiteValues.MODID) // Put configurations to the total files with module configurations.
public class GTLiteConfigHolder
{

    @Comment("Config options for Mod Compatibility features")
    @Name("Compatibility Options")
    @RequiresMcRestart
    public static CompatibilityOptions compat = new CompatibilityOptions();

    @Comment("Config options for World Generation features")
    @Name("Worldgen Options")
    @RequiresMcRestart
    public static WorldGenOptions worldgen = new WorldGenOptions();

    public static class CompatibilityOptions
    {
        @Comment("Config options regarding JustEnoughItems (JEI) mod")
        @Name("JEI Compat Options")
        public JustEnoughItemsCompatOptions jeiCompat = new JustEnoughItemsCompatOptions();

        public static class JustEnoughItemsCompatOptions
        {

            @Comment({"Make the Font Renderer in all pages be scaleable, it is useful for some large digits.",
                      "Default: true"})
            public boolean scaleableFontRenderer = true;

        }

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
