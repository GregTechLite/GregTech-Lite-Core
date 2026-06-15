package gregtechlite.gtlitecore.api.capability

object GTLiteDataCodes
{
    private var nextId = 1000

    val STACK_SIZE_PER_SLOT = assignId()

    private fun assignId(): Int = nextId++
}