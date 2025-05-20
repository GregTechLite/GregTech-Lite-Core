package magicbook.gtlitecore.mixin.modularui;

import com.cleanroommc.modularui.api.drawable.IDrawable;
import com.cleanroommc.modularui.api.drawable.IIcon;
import com.cleanroommc.modularui.drawable.DelegateIcon;
import magicbook.gtlitecore.api.gui.drawable.IconAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = DelegateIcon.class, remap = false)
public abstract class DelegateIconMixin implements IconAccessor
{

    @Override
    public IDrawable gtlitecore$getDrawable()
    {
        if (getDelegate() instanceof IconAccessor)
        {
            IconAccessor accessor = (IconAccessor) getDelegate();
            return accessor.gtlitecore$getDrawable();
        }
        return null;
    }

    @Shadow
    public abstract IIcon getDelegate();
}