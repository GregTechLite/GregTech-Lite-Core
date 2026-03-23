package gregtechlite.gtlitecore.api.recipe.util

import gregtech.api.GTValues
import gregtech.api.unification.material.Material
import gregtech.api.unification.material.MarkerMaterials.Tier

/**
 * Bridges GregTech voltage tier indexes and tier marker materials.
 *
 * This provides a single ordered, bijective mapping between values such as
 * [GTValues.ULV] / [GTValues.LV] and [Tier.ULV] / [Tier.LV].
 *
 * Intended for recipe generation and other tier-based logic where code needs to
 * traverse voltage tiers while also accessing the corresponding tier [Material].
 */
object TierBridge
{

    /**
     * One bijective tier mapping entry.
     *
     * @property index the integer voltage tier, usually one of the constants from [GTValues]
     * @property material the corresponding tier marker [Material], usually one of the constants
     * from [Tier]
     */
    data class Entry(
        val index: Int,
        val material: Material
    )

    /**
     * Ordered list of all known tier mappings.
     *
     * The list order defines tier progression from the lowest tier to the highest tier.
     * Many traversal helpers such as [fromIndex], [fromMaterial], [next], and [previous]
     * rely on this ordering.
     */
    val entries: List<Entry> = listOf(
            Entry(GTValues.ULV, Tier.ULV),
            Entry(GTValues.LV, Tier.LV),
            Entry(GTValues.MV, Tier.MV),
            Entry(GTValues.HV, Tier.HV),
            Entry(GTValues.EV, Tier.EV),
            Entry(GTValues.IV, Tier.IV),
            Entry(GTValues.LuV, Tier.LuV),
            Entry(GTValues.ZPM, Tier.ZPM),
            Entry(GTValues.UV, Tier.UV),
            Entry(GTValues.UHV, Tier.UHV),
            Entry(GTValues.UEV, Tier.UEV),
            Entry(GTValues.UIV, Tier.UIV),
            Entry(GTValues.UXV, Tier.UXV),
            Entry(GTValues.OpV, Tier.OpV),
            Entry(GTValues.MAX, Tier.MAX)
    )

    private val byIndex: Map<Int, Entry> = entries.associateBy { it.index }
    private val byMaterial: Map<Material, Entry> = entries.associateBy { it.material }

    /**
     * Returns the mapping entry for the given integer voltage tier.
     *
     * @param index a GregTech voltage tier index such as [GTValues.IV] or [GTValues.UEV]
     * @return the matching [Entry]
     * @throws IllegalStateException if the given tier index is not part of this bridge
     */
    @JvmStatic
    fun of(index: Int): Entry
    {
        return byIndex[index] ?: error("Unknown tier index: $index")
    }

    /**
     * Returns the mapping entry for the given tier marker material.
     *
     * @param material a tier marker material such as [Tier.IV] or [Tier.UEV]
     * @return the matching [Entry]
     * @throws IllegalStateException if the given material is not part of this bridge
     */
    @JvmStatic
    fun of(material: Material): Entry
    {
        return byMaterial[material] ?: error("Unknown tier material: $material")
    }

    /**
     * Converts an integer voltage tier into its corresponding tier marker material.
     *
     * Example:
     * `materialOf(GTValues.UEV) == Tier.UEV`
     *
     * @param index a GregTech voltage tier index
     * @return the corresponding tier marker [Material]
     */
    @JvmStatic
    fun materialOf(index: Int): Material
    {
        return of(index).material
    }

    /**
     * Converts a tier marker material into its corresponding integer voltage tier.
     *
     * Example:
     * `indexOf(Tier.UEV) == GTValues.UEV`
     *
     * @param material a tier marker [Material]
     * @return the corresponding GregTech voltage tier index
     */
    @JvmStatic
    fun indexOf(material: Material): Int
    {
        return of(material).index
    }

    /**
     * Checks whether the given integer tier exists in this bridge.
     *
     * This is useful when handling external or computed tier values that may not be part of the
     * supported tier range.
     *
     * @param index a voltage tier index
     * @return `true` if this bridge contains a mapping for [index], otherwise `false`
     */
    @JvmStatic
    fun contains(index: Int): Boolean
    {
        return byIndex.containsKey(index)
    }

    /**
     * Checks whether the given tier material exists in this bridge.
     *
     * @param material a tier marker material
     * @return `true` if this bridge contains a mapping for [material], otherwise `false`
     */
    @JvmStatic
    fun contains(material: Material): Boolean
    {
        return byMaterial.containsKey(material)
    }

    /**
     * Returns all mapping entries from the specified integer tier up to the highest supported tier.
     *
     * The returned list includes the starting tier itself.
     *
     * This is particularly useful for recipe generation where recipes should be created for all
     * tiers greater than or equal to a starting voltage tier.
     *
     * Example:
     * `fromIndex(GTValues.UEV)` returns entries for UEV, UIV, UXV, OpV, and MAX.
     *
     * @param startIndex the first tier to include
     * @return an ordered list of [Entry] values from [startIndex] upward
     * @throws IllegalArgumentException if [startIndex] is not part of this bridge
     */
    @JvmStatic
    fun fromIndex(startIndex: Int): List<Entry>
    {
        val start = entries.indexOfFirst { it.index == startIndex }
        require(start >= 0) { "Unknown tier index: $startIndex" }
        return entries.drop(start)
    }

    /**
     * Returns all mapping entries from the specified tier material up to the highest supported tier.
     *
     * The returned list includes the starting material itself.
     *
     * Example:
     * `fromMaterial(Tier.UEV)` returns entries for UEV, UIV, UXV, OpV, and MAX.
     *
     * @param startMaterial the first tier material to include
     * @return an ordered list of [Entry] values from [startMaterial] upward
     * @throws IllegalArgumentException if [startMaterial] is not part of this bridge
     */
    @JvmStatic
    fun fromMaterial(startMaterial: Material): List<Entry>
    {
        val start = entries.indexOfFirst { it.material == startMaterial }
        require(start >= 0) { "Unknown tier material: $startMaterial" }
        return entries.drop(start)
    }

    /**
     * Returns the next higher tier after the given integer voltage tier.
     *
     * Example:
     * `next(GTValues.IV)` returns the LuV entry.
     *
     * @param index the current tier index
     * @return the next higher [Entry], or `null` if [index] is already the highest tier
     * @throws IllegalArgumentException if [index] is not part of this bridge
     */
    @JvmStatic
    fun next(index: Int): Entry?
    {
        val i = entries.indexOfFirst { it.index == index }
        require(i >= 0) { "Unknown tier index: $index" }
        return entries.getOrNull(i + 1)
    }

    /**
     * Returns the next higher tier after the given tier material.
     *
     * Example:
     * `next(Tier.IV)` returns the LuV entry.
     *
     * @param material the current tier material
     * @return the next higher [Entry], or `null` if [material] is already the highest tier
     * @throws IllegalArgumentException if [material] is not part of this bridge
     */
    @JvmStatic
    fun next(material: Material): Entry?
    {
        val i = entries.indexOfFirst { it.material == material }
        require(i >= 0) { "Unknown tier material: $material" }
        return entries.getOrNull(i + 1)
    }

    /**
     * Returns the previous lower tier before the given integer voltage tier.
     *
     * Example:
     * `previous(GTValues.UEV)` returns the UHV entry.
     *
     * @param index the current tier index
     * @return the previous lower [Entry], or `null` if [index] is already the lowest tier
     * @throws IllegalArgumentException if [index] is not part of this bridge
     */
    @JvmStatic
    fun previous(index: Int): Entry?
    {
        val i = entries.indexOfFirst { it.index == index }
        require(i >= 0) { "Unknown tier index: $index" }
        return entries.getOrNull(i - 1)
    }

    /**
     * Returns the previous lower tier before the given tier material.
     *
     * Example:
     * `previous(Tier.UEV)` returns the UHV entry.
     *
     * @param material the current tier material
     * @return the previous lower [Entry], or `null` if [material] is already the lowest tier
     * @throws IllegalArgumentException if [material] is not part of this bridge
     */
    @JvmStatic
    fun previous(material: Material): Entry?
    {
        val i = entries.indexOfFirst { it.material == material }
        require(i >= 0) { "Unknown tier material: $material" }
        return entries.getOrNull(i - 1)
    }

    /**
     * Executes [action] for each entry from [startIndex] up to the highest tier.
     */
    @JvmStatic
    fun forEachFrom(startIndex: Int, action: (Entry) -> Unit)
    {
        fromIndex(startIndex).forEach(action)
    }

    /**
     * Executes [action] for each entry from [startMaterial] up to the highest tier.
     */
    @JvmStatic
    fun forEachFrom(startMaterial: Material, action: (Entry) -> Unit)
    {
        fromMaterial(startMaterial).forEach(action)
    }
}

/*
Usage examples:

for (entry in TierBridge.fromIndex(GTValues.UEV)) {
    val tier = entry.index
    val tierMaterial = entry.material

    MY_RECIPES.recipeBuilder()
        .input(...)
        .recipeProperty(SomeTierProperty.getInstance(), tierMaterial)
        .EUt(GTValues.VA[tier])
        .duration(200)
        .buildAndRegister()
}

val material = TierBridge.materialOf(GTValues.UHV)
val tier = TierBridge.indexOf(Tier.UEV)
val next = TierBridge.next(GTValues.IV)
*/
