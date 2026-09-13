package gregtechlite.gtlitecore.api.capability

object GTLiteDataCodes
{
    private var nextId = 1000

    val STACK_SIZE_PER_SLOT = assignId()
    val UPDATE_WIRELESS_CHANNEL = assignId()
    val UPDATE_WIRELESS_PRIORITY = assignId()
    val UPDATE_WIRELESS_AMPERAGE = assignId()
    val UPDATE_WIRELESS_BUFFER_DURATION = assignId()

    private fun assignId(): Int = nextId++
}