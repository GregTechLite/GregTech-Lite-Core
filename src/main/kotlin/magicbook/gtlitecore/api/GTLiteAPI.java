package magicbook.gtlitecore.api;

import gregtech.api.creativetab.BaseCreativeTab;
import gregtech.api.unification.OreDictUnifier;
import magicbook.gtlitecore.api.module.IModuleManager;

import static gregtech.api.unification.material.Materials.Diamond;
import static gregtech.api.unification.ore.OrePrefix.gear;

public class GTLiteAPI
{
    /* -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ API Objects +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- */
    // Will always be available.
    public static Object instance;
    // Will be available at the Construction stage.
    public static IModuleManager moduleManager;

    /* -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- Creative Tabs -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- */
    // Total CreativeTabs of gtlitecore, this is default settings for many contents in
    // the mod, but if item, block or other contents satisfied descriptions of the
    // following CreativeTabs, then should use consistent CreativeTabs.
    public static final BaseCreativeTab TAB_GTLITE = new BaseCreativeTab("gtlite",
            () -> OreDictUnifier.get(gear, Diamond), false);

    /* -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- */




}
