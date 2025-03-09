package magicbook.gtlitecore.api.utils

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class GTLiteLog
{

    companion object
    {

        @JvmField
        val logger: Logger = LogManager.getLogger(GTLiteValues.NAME)

    }

}