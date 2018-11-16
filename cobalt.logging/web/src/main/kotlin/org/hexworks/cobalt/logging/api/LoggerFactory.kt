package org.hexworks.cobalt.logging.api

import org.hexworks.cobalt.logging.internal.DefaultLogger
import kotlin.reflect.KClass

actual object LoggerFactory {

    actual fun getLogger(name: String): Logger {
        return DefaultLogger(name)
    }

    actual fun getLogger(kClass: KClass<out Any>): Logger {
        return getLogger(kClass.simpleName ?: throw IllegalArgumentException("The supplied class has no simple name."))
    }

}
