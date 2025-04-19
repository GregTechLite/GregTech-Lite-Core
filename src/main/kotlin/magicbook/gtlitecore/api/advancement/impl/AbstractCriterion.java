package magicbook.gtlitecore.api.advancement.impl;

import magicbook.gtlitecore.api.advancement.IAdvancementCriterion;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCriterion implements IAdvancementCriterion
{

    private ResourceLocation id = new ResourceLocation("MISSING");

    @NotNull
    @Override
    public ResourceLocation getId()
    {
        return this.id;
    }

    @Override
    public void setId(ResourceLocation id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "AbstractCriterion{id=" + this.id + "}";
    }

}