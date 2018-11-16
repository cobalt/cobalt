package org.hexworks.cobalt.logging.api

import org.hexworks.cobalt.logging.internal.SLF4JLoggerWrapper
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

actual object LoggerFactory {

    actual fun getLogger(name: String): Logger {
        return SLF4JLoggerWrapper(LoggerFactory.getLogger(name))
    }

    actual fun getLogger(kClass: KClass<out Any>): Logger {
        return SLF4JLoggerWrapper(org.slf4j.LoggerFactory.getLogger(kClass.java))
    }

    fun getLogger(klass: Class<out Any>): Logger {
        return SLF4JLoggerWrapper(LoggerFactory.getLogger(klass))
    }

}
