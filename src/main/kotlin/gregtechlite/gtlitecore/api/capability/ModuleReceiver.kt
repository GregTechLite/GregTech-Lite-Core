package gregtechlite.gtlitecore.api.capability

interface ModuleReceiver
{
    var moduleProvider: ModuleProvider?

    val displayCountName: String

    /**
     * Sent invert `isWorkingEnabled` to all modules.
     */
    fun sentWorkingDisabled()

    /**
     * Sent `isWorkingEnabled` to all modules.
     */
    fun sentWorkingEnabled()
}
