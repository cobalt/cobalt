package org.hexworks.cobalt.logging.internal

import org.hexworks.cobalt.datatypes.sam.Supplier
import org.hexworks.cobalt.logging.api.Logger


class SLF4JLoggerWrapper(private val logger: org.slf4j.Logger) : Logger {

    override val name: String = logger.name

    override fun isTraceEnabled() = logger.isTraceEnabled

    override fun isDebugEnabled() = logger.isTraceEnabled

    override fun isInfoEnabled() = logger.isInfoEnabled

    override fun isWarnEnabled() = logger.isWarnEnabled

    override fun isErrorEnabled() = logger.isErrorEnabled

    override fun trace(msg: String) {
        logger.trace(msg)
    }

    override fun trace(msgFn: Supplier<String>) {
        if(isTraceEnabled()) {
            logger.trace(msgFn.get())
        }
    }

    override fun trace(msg: String, t: Throwable) {
        if(isTraceEnabled()) {
            logger.trace(msg, t)
        }
    }

    override fun trace(msgFn: Supplier<String>, t: Throwable) {
        if(isTraceEnabled()) {
            logger.trace(msgFn.get(), t)
        }
    }

    override fun debug(msg: String) {
        if(isDebugEnabled()) {
            logger.debug(msg)
        }
    }

    override fun debug(msgFn: Supplier<String>) {
        if(isDebugEnabled()) {
            logger.debug(msgFn.get())
        }
    }

    override fun debug(msg: String, t: Throwable) {
        if(isDebugEnabled()) {
            logger.debug(msg, t)
        }
    }

    override fun debug(msgFn: Supplier<String>, t: Throwable) {
        if(isDebugEnabled()) {
            logger.debug(msgFn.get(), t)
        }
    }

    override fun info(msg: String) {
        if(isInfoEnabled()) {
            logger.info(msg)
        }
    }

    override fun info(msgFn: Supplier<String>) {
        if(isInfoEnabled()) {
            logger.info(msgFn.get())
        }
    }

    override fun info(msg: String, t: Throwable) {
        if(isInfoEnabled()) {
            logger.info(msg, t)
        }
    }

    override fun info(msgFn: Supplier<String>, t: Throwable) {
        if(isInfoEnabled()) {
            logger.info(msgFn.get(), t)
        }
    }

    override fun warn(msg: String) {
        if(isWarnEnabled()) {
            logger.warn(msg)
        }
    }

    override fun warn(msgFn: Supplier<String>) {
        if(isWarnEnabled()) {
            logger.warn(msgFn.get())
        }
    }

    override fun warn(msg: String, t: Throwable) {
        if(isWarnEnabled()) {
            logger.warn(msg, t)
        }
    }

    override fun warn(msgFn: Supplier<String>, t: Throwable) {
        if(isWarnEnabled()) {
            logger.warn(msgFn.get(), t)
        }
    }

    override fun error(msg: String) {
        if(isErrorEnabled()) {
            logger.error(msg)
        }
    }

    override fun error(msgFn: Supplier<String>) {
        if(isErrorEnabled()) {
            logger.error(msgFn.get())
        }
    }

    override fun error(msg: String, t: Throwable) {
        if(isErrorEnabled()) {
            logger.error(msg)
        }
    }

    override fun error(msgFn: Supplier<String>, t: Throwable) {
        if(isErrorEnabled()) {
            logger.error(msgFn.get(), t)
        }
    }

}
