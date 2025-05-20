package magicbook.gtlitecore.api.capability;

import javax.annotation.Nullable;

public interface IModuleReceiver
{

    @Nullable
    IModuleProvider getModuleProvider();

    void setModuleProvider(IModuleProvider provider);

    /**
     * Sent invert {@code isWorkingEnabled} to all modules.
     */
    void sentWorkingDisabled();

    /**
     * Sent {@code isWorkingEnabled} to all modules.
     */
    void sentWorkingEnabled();

    String getDisplayCountName();

}
