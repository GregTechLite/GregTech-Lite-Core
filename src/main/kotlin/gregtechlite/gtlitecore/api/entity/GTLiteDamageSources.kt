package gregtechlite.gtlitecore.api.entity

import net.minecraft.util.DamageSource

object GTLiteDamageSources
{
    lateinit var ORE_CRUSHING: DamageSource
    lateinit var MOB_SLAUGHTERING: DamageSource

    internal fun init()
    {
        ORE_CRUSHING = DamageSource("ore_crushing")
        MOB_SLAUGHTERING = DamageSource("mob_slaughtering").setDamageBypassesArmor().setDamageAllowedInCreativeMode()
    }
}