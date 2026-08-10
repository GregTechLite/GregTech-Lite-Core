package gregtechlite.gtlitecore.api.network.sync

import java.lang.reflect.Field

data class ManagedFieldKey(val name: String,
                           val isPersist: Boolean,
                           val isSync: Boolean,
                           val field: Field,
                           val persistentKey: String = name,
                           val updateListenerMethod: String? = null)