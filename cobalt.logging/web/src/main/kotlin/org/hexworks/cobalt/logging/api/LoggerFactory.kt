package org.hexworks.cobalt.logging.api

import org.hexworks.cobalt.logging.internal.DefaultLogger

actual object LoggerFactory {

    actual fun getLogger(name: String): Logger {
        return DefaultLogger(name)
    }

}
