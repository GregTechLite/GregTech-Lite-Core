package gregtechlite.gtlitecore.api.network.sync

import gregtechlite.gtlitecore.api.LOGGER
import gregtechlite.gtlitecore.api.network.payload.isSupportedType
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.util.concurrent.ConcurrentHashMap

class ManagedFieldHolder private constructor(val ownerClass: Class<*>)
{
    val keys: Array<ManagedFieldKey>
    val syncKeys: Array<ManagedFieldKey>
    val persistKeys: Array<ManagedFieldKey>
    private val byName: Map<String, ManagedFieldKey>

    init
    {
        val found = ownerClass.declaredFields.asSequence()
            .filter { !Modifier.isStatic(it.modifiers) && !it.isSynthetic }
            .filter { it.isAnnotationPresent(Persisted::class.java) || it.isAnnotationPresent(DescSynced::class.java) }
            .filter {
                if (isSupportedType(it.type))
                {
                    return@filter true
                }
                else
                {
                    LOGGER.warn("Managed skipping managed field {} on {}: unsupported type {}.",
                                it.name, ownerClass.simpleName, it.type)
                    return@filter false
                }
            }
            .map { buildKey(it) }
            .toList()

        keys = found.toTypedArray()
        syncKeys = found.filter { it.isSync }.toTypedArray()
        persistKeys = found.filter { it.isPersist }.toTypedArray()
        byName = found.associateBy { it.name }
    }

    companion object
    {
        private val cache = ConcurrentHashMap<Class<*>, ManagedFieldHolder>()

        @JvmStatic
        fun get(clazz: Class<*>): ManagedFieldHolder = cache.computeIfAbsent(clazz) { ManagedFieldHolder(it) }
    }

    fun getKeyByName(name: String): ManagedFieldKey? = byName[name]

    private fun buildKey(field: Field): ManagedFieldKey
    {
        field.isAccessible = true
        val isPersist = field.isAnnotationPresent(Persisted::class.java)
        val isSync = field.isAnnotationPresent(DescSynced::class.java)
        val listener = field.getAnnotation(UpdateListener::class.java)
        return ManagedFieldKey(name = field.name,
                               isPersist = isPersist,
                               isSync = isSync,
                               field = field,
                               persistentKey = field.name,
                               updateListenerMethod = listener?.name)
    }
}