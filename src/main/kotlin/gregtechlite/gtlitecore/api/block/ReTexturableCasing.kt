package gregtechlite.gtlitecore.api.block

import net.minecraft.util.ResourceLocation

/**
 * A casing whose base material is rendered with the `baseTexture` of the multiblock
 * structure they belong to, instead of their own one.
 *
 * @see ReTexturableCasingRegistry
 */
interface ReTexturableCasing
{
    /**
     * The model resource which renders the casing.
     *
     * The `0` texture variable of it will be replaced by the `baseTexture` of the
     * multiblock which contains the casing.
     */
    val casingModel: ResourceLocation
}