package gregtechlite.gtlitecore.api.cosmetic

import gregtechlite.gtlitecore.api.collection.openHashMapOf
import java.util.UUID

/**
 * The contributor of GregTech Lite Modpack and all Addon Mods development.
 *
 * @param name        The human-readable name for the contributor.
 * @param uuid        The uuid of the contributor in game.
 * @param isDeveloper If the contributor is a developer, then it will have special cape in game.
 */
enum class GTLiteContributor(name: String, internal val uuid: UUID, internal val isDeveloper: Boolean = true)
{

    // Developers
    MAGIC_SWEEPY(    "Magic_Sweepy",   UUID.fromString("aaed705b-8e08-47fa-b8e0-d0024e3c75bc")),

    // Contributors
    LLAMA_WEI(       "llama_wei",      UUID.fromString("b0fbc8fe-269a-47a8-9105-70246759eeae"), false),
    MINATO_AQUKINNE( "MinatoAqukinne", UUID.fromString("99405735-a590-459f-90bf-8b3e39aee7d0"), false),
    YIYU_QAQ(        "YIYU_QAQ",       UUID.fromString("d5482dd2-8894-4916-b347-50eff773d2f3"), false);

    companion object
    {
        internal val contributors: MutableMap<UUID, String> = openHashMapOf()

        internal fun init()
        {
            for (contributor in entries)
            {
                val key = if (contributor.isDeveloper) "developer" else "contributor"
                contributors.put(contributor.uuid, key)
            }
        }
    }

}