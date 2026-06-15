package gregtechlite.gtlitecore.api.gui.sync

import com.cleanroommc.modularui.value.sync.IntSyncValue

/**
 * [IntSyncValue] but only do sync when its valid.
 */
class SafeIntSyncValue(getter: () -> Int, setter: (Int) -> Unit) : IntSyncValue(getter, setter) {

    override fun sync()
    {
        if (isValid) super.sync()
    }
}