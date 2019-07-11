package org.hexworks.cobalt.logging.api

import kotlin.reflect.KClass

expect object LoggerFactory {

    fun getLogger(name: String): Logger

    fun getLogger(kClass: KClass<out Any>): Logger
}
