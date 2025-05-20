package magicbook.gtlitecore.mixin.modularui;

import com.cleanroommc.modularui.api.drawable.IDrawable;
import com.cleanroommc.modularui.drawable.Icon;
import magicbook.gtlitecore.api.gui.drawable.IconAccessor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Icon.class, remap = false)
public class IconMixin implements IconAccessor
{

    @Shadow
    @Final
    private IDrawable drawable;

    @Override
    public IDrawable gtlitecore$getDrawable()
    {
        return drawable;
    }

}
