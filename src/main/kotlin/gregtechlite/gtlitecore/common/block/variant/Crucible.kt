package gregtechlite.gtlitecore.common.block.variant

import gregtech.api.block.IStateHarvestLevel
import gregtechlite.gtlitecore.api.block.attribute.StateTier
import gregtechlite.gtlitecore.api.block.variant.BlockVariant
import gregtechlite.gtlitecore.common.block.GTLiteBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.IStringSerializable

enum class Crucible(private val serializedName: String,
                    val temperature: Int = 0,
                    private val harvestLevel: Int = 1) : BlockVariant, IStringSerializable, IStateHarvestLevel, StateTier
{

    BRONZE("bronze", 1696),
    INVAR("invar", 2395),
    QUARTZ("quartz", 2482),
    CHROME("chrome", 2725),
    VANADIUM("vanadium", 2728),
    NIOBIUM_TITANIUM("niobium_titanium", 2931),
    IRIDIUM("iridium", 3398),
    MOLYBDENUM("molybdenum", 3620),
    TUNGSTEN("tungsten", 3695),
    OSMIUM("osmium", 4132),
    GRAPHITE("graphite", 4750),
    BORON_NITRIDE("boron_nitride", 5328);

    override val state: IBlockState
        get() = GTLiteBlocks.CRUCIBLE.getState(this)

    override val tier: Int
        get() = ordinal

    override fun getStack(count: Int): ItemStack = GTLiteBlocks.CRUCIBLE.getItemVariant(this, count)

    override fun getName(): String = serializedName

    override fun getHarvestLevel(state: IBlockState) = harvestLevel

    override fun getHarvestTool(state: IBlockState) = "pickaxe"

}