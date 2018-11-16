package org.hexworks.cobalt.logging.internal

import org.hexworks.cobalt.datatypes.sam.Supplier
import org.hexworks.cobalt.logging.api.Logger
import org.hexworks.cobalt.logging.api.LoggingLevel
import org.hexworks.cobalt.logging.api.LoggingLevel.*

class DefaultLogger(override val name: String) : Logger {

    override fun isTraceEnabled() = TRACE.isEnabled

    override fun isDebugEnabled() = DEBUG.isEnabled

    override fun isInfoEnabled() = INFO.isEnabled

    override fun isWarnEnabled() = WARN.isEnabled

    override fun isErrorEnabled() = ERROR.isEnabled

    override fun trace(msg: String) {
        log(TRACE, msg)
    }

    override fun trace(msgFn: Supplier<String>) {
        log(TRACE, msgFn)
    }

    override fun trace(msg: String, t: Throwable) {
        log(TRACE, msg, t)
    }

    override fun trace(msgFn: Supplier<String>, t: Throwable) {
        log(TRACE, msgFn, t)
    }

    override fun debug(msg: String) {
        log(DEBUG, msg)
    }

    override fun debug(msgFn: Supplier<String>) {
        log(DEBUG, msgFn)
    }

    override fun debug(msg: String, t: Throwable) {
        log(DEBUG, msg, t)
    }

    override fun debug(msgFn: Supplier<String>, t: Throwable) {
        log(DEBUG, msgFn, t)
    }

    override fun info(msg: String) {
        log(INFO, msg)
    }

    override fun info(msgFn: Supplier<String>) {
        log(INFO, msgFn)
    }

    override fun info(msg: String, t: Throwable) {
        log(INFO, msg, t)
    }

    override fun info(msgFn: Supplier<String>, t: Throwable) {
        log(INFO, msgFn, t)
    }

    override fun warn(msg: String) {
        log(WARN, msg)
    }

    override fun warn(msgFn: Supplier<String>) {
        log(WARN, msgFn)
    }

    override fun warn(msg: String, t: Throwable) {
        log(WARN, msg, t)
    }

    override fun warn(msgFn: Supplier<String>, t: Throwable) {
        log(WARN, msgFn, t)
    }

    override fun error(msg: String) {
        log(ERROR, msg)
    }

    override fun error(msgFn: Supplier<String>) {
        log(ERROR, msgFn)
    }

    override fun error(msg: String, t: Throwable) {
        log(ERROR, msg, t)
    }

    override fun error(msgFn: Supplier<String>, t: Throwable) {
        log(ERROR, msgFn, t)
    }

    private fun log(loggingLevel: LoggingLevel, msgFn: Supplier<String>, t: Throwable = NO_THROWABLE) {
        if (loggingLevel.isEnabled) {
            console.log("${loggingLevel.name}: [$name] ${msgFn.get()} ${t.throwableToString()}")
        }
    }

    private fun log(loggingLevel: LoggingLevel, msg: String, t: Throwable = NO_THROWABLE) {
        if (loggingLevel.isEnabled) {
            console.log("${loggingLevel.name}: [$name] $msg ${t.throwableToString()}")
        }
    }

    private fun Throwable.throwableToString(): String {
        var msg = ""
        var current = this
        while (current.cause != null) {
            msg += ", Caused by: '${current.message}'"
            current = current.cause!!
        }
        return msg
    }

    companion object {

        private val NO_THROWABLE = object : Exception() {
            override fun toString() = ""
        }
    }
}
