package gregtechlite.gtlitecore.api.entity

import net.minecraft.util.DamageSource

object GTLiteDamageSources
{

    lateinit var ORE_CRUSHING: DamageSource

    internal fun init()
    {
        ORE_CRUSHING = DamageSource("ore_crushing")
    }

}