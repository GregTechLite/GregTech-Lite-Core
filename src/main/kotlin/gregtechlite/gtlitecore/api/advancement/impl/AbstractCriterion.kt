package gregtechlite.gtlitecore.api.advancement.impl

import gregtechlite.gtlitecore.api.advancement.AdvancementCriterion
import net.minecraft.util.ResourceLocation

abstract class AbstractCriterion : AdvancementCriterion
{
    private var id: ResourceLocation = ResourceLocation("MISSING")

    override fun getId(): ResourceLocation = id

    override fun setId(id: ResourceLocation)
    {
        this.id = id
    }

    override fun toString(): String = "AbstractCriterion{id=$id}"
}