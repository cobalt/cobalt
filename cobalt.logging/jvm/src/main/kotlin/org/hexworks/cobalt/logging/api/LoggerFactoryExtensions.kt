package org.hexworks.cobalt.logging.api

import org.hexworks.cobalt.logging.internal.SLF4JLoggerWrapper
import kotlin.reflect.KClass

fun LoggerFactory.getLogger(kClass: KClass<out Any>): Logger {
    return SLF4JLoggerWrapper(org.slf4j.LoggerFactory.getLogger(kClass.java))
}
