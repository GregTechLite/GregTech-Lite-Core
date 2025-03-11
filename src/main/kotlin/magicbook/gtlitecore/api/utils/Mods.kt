package magicbook.gtlitecore.api.utils

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.FMLLaunchHandler
import net.minecraftforge.fml.relauncher.Side
import java.lang.String.join
import java.util.function.Function

enum class Mods(private val modId: String, private val extraCheck: Function<Mods, Boolean>?)
{

    // Sorted by A to Z.
    AdvancedRocketry("advancedrocketry"),
    Alfheim("alfheim"),
    AppliedEnergistics2("appliedenergistics2"),

    Baubles("baubles"),
    BinnieCore("binniecore"),
    BiomesOPlenty("biomesoplenty"),
    BuildCraftCore("buildcraftcore"),

    Chisel("chisel"),
    CoFHCore("cofhcore"),
    CTM("ctm"),
    CubicChunks("cubicchunks"),
    CraftTweaker2("crafttweaker"),

    EnderCore("endercore"),
    EnderIO("enderio"),
    ExtraBees("extrabees"),
    ExtraTrees("extratrees"),
    ExtraUtilities2("extrautils2"),

    Forestry("forestry"),

    GalacticraftCore("galacticraftcore"),
    Genetics("genetics"),
    GregTech("gregtech"),
    GregTechLiteCore("gtlitecore"),
    GregicAddition("gregicaddition"),
    GroovyScript("groovyscript"),

    HWYLA("hwyla"),

    ImmersiveEngineering("immersiveengineering"),
    IndustrialCraft2("ic2"),
    InventoryTweaks("inventorytweaks"),

    JourneyMap("journeymap"),
    JustEnoughItems("jei"),

    MagicBees("magicbees"),
    Minecraft("minecraft"),

    Nothirium("nothirium"),
    NuclearCraft("nuclearcraft"),

    OpenComputers("opencomputers"),

    ProjectRedCore("projred-core"),

    Railcraft("railcraft"),
    RefinedStorage("refinedstorage"),

    TechReborn("techreborn"),
    TheOneProbe("theoneprobe"),
    TinkersConstruct("tconstruct"),
    TOPAddons("topaddons"),

    Vintagium("vintagium"),
    VoxelMap("voxelmap"),

    XaerosMinimap("xaerominimap");

    private var modLoaded: Boolean = false

    constructor(modId: String) : this(modId, null)

    fun getId(): String = this.modId

    /**
     * This method has same function with [GTLiteUtility.getId].
     */
    fun getLocation(path: String): ResourceLocation = ResourceLocation(this.modId, path)

    fun isModLoaded(): Boolean
    {
        this.modLoaded = Loader.isModLoaded(this.modId)
        if (this.modLoaded)
        {
            if (this.extraCheck != null && !this.extraCheck.apply(this))
                this.modLoaded = false
        }
        return this.modLoaded
    }

    fun getItem(name: String): ItemStack
            = GameRegistry.makeItemStack(this.modId + ":" + name, 0, 1, null)

    fun getItem(name: String, nbt: NBTTagCompound): ItemStack
            = GameRegistry.makeItemStack(this.modId + ":" + name, 0, 1, nbt.toString())

    fun getItem(name: String, amount: Int): ItemStack
            = GameRegistry.makeItemStack(this.modId + ":" + name, 0, amount, null)

    fun getItem(name: String, amount: Int, nbt: NBTTagCompound)
            = GameRegistry.makeItemStack(this.modId + ":" + name, 0, amount, nbt.toString())

    fun getMetaItem(name: String, meta: Int)
            = GameRegistry.makeItemStack(this.modId + ":" + name, meta, 1, null)

    fun getMetaItem(name: String, meta: Int, nbt: NBTTagCompound)
            = GameRegistry.makeItemStack(this.modId + ":" + name, meta, 1, nbt.toString())

    fun getMetaItem(name: String, meta: Int, amount: Int)
            = GameRegistry.makeItemStack(this.modId + ":" + name, meta, amount, null)

    fun getMetaItem(name: String, meta: Int, amount: Int, nbt: NBTTagCompound)
            = GameRegistry.makeItemStack(this.modId + ":" + name, meta, amount, nbt.toString())

    fun throwIncompatibilityIfLoaded(vararg messages: String)
    {
        if (this.isModLoaded())
        {
            val modName: String = "§l${this.modId}§r"
            val msgs: MutableList<String>? = null
            msgs?.add("$modName mod detected, this mod is incompatible with $GregTechLiteCore")
            msgs?.addAll(messages)
            if (FMLLaunchHandler.side() == Side.SERVER)
                throw RuntimeException(join(",", msgs))
            else
                GTLiteLog.logger.error("$modName mod detected on client side, "
                        + "because this mod is incompatible with $GregTechLiteCore,"
                        + " check the mod which loaded on client side!")
        }
    }

    private fun versionContains(versionPart: String): Function<Mods, Boolean>
    {
        return Function<Mods, Boolean> { mod: Mods ->
            if (!mod.isModLoaded())
                return@Function false
            val container = Loader.instance()
                .indexedModList[mod.modId] ?: return@Function false
            container.version.contains(versionPart)
        }
    }

    private fun versionExcludes(versionPart: String): Function<Mods, Boolean>
    {
        return Function<Mods, Boolean> { mod: Mods ->
            if (!mod.isModLoaded())
                return@Function false
            val container = Loader.instance()
                .indexedModList[mod.modId] ?: return@Function false
            !container.version.contains(versionPart)
        }
    }

}