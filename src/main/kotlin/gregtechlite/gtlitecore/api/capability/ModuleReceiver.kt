package gregtechlite.gtlitecore.api.capability

interface ModuleReceiver
{
    var moduleProvider: ModuleProvider?

    val displayCountName: String

    fun sentWorkingDisabled()

    fun sentWorkingEnabled()
}
