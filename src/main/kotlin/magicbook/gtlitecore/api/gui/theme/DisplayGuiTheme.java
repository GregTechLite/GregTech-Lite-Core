package magicbook.gtlitecore.api.gui.theme;

import com.cleanroommc.modularui.drawable.DrawableSerialization;
import com.cleanroommc.modularui.drawable.UITexture;
import gregtech.api.GTValues;
import gregtech.api.mui.GTGuiTheme;

import javax.annotation.Nullable;

public class DisplayGuiTheme
{

    @Nullable
    public static UITexture getDisplayBackground(GTGuiTheme theme)
    {
        if (theme == GTGuiTheme.BRONZE)
            return getDisplayBackground(ThemeIDs.DISPLAY_BRONZE);
        else if (theme == GTGuiTheme.STEEL)
            return getDisplayBackground(ThemeIDs.DISPLAY_STEEL);
        else
            return null;
    }

    @Nullable
    public static UITexture getDisplayBackground(String displayBackground)
    {
        return DrawableSerialization.getTexture(displayBackground);
    }

    public static class ThemeIDs
    {

        public static final String DISPLAY = id("display");
        public static final String DISPLAY_BRONZE = id("display_bronze");
        public static final String DISPLAY_STEEL = id("display_steel");

        private static String id(String path)
        {
            return GTValues.MODID + ":" + path;
        }

    }

}
