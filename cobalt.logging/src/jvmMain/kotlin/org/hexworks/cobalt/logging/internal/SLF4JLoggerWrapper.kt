package org.hexworks.cobalt.logging.internal

import org.hexworks.cobalt.logging.api.Logger


class SLF4JLoggerWrapper(private val logger: org.slf4j.Logger) : Logger {

    override val name: String = logger.name

    override fun isTraceEnabled() = logger.isTraceEnabled

    override fun isDebugEnabled() = logger.isDebugEnabled

    override fun isInfoEnabled() = logger.isInfoEnabled

    override fun isWarnEnabled() = logger.isWarnEnabled

    override fun isErrorEnabled() = logger.isErrorEnabled

    override fun trace(msg: String) {
        logger.trace(msg)
    }

    override fun trace(msgFn: () -> String) {
        if(isTraceEnabled()) {
            logger.trace(msgFn())
        }
    }

    override fun trace(msg: String, t: Throwable) {
        if(isTraceEnabled()) {
            logger.trace(msg, t)
        }
    }

    override fun trace(msgFn: () -> String, t: Throwable) {
        if(isTraceEnabled()) {
            logger.trace(msgFn(), t)
        }
    }

    override fun debug(msg: String) {
        if(isDebugEnabled()) {
            logger.debug(msg)
        }
    }

    override fun debug(msgFn: () -> String) {
        if(isDebugEnabled()) {
            logger.debug(msgFn())
        }
    }

    override fun debug(msg: String, t: Throwable) {
        if(isDebugEnabled()) {
            logger.debug(msg, t)
        }
    }

    override fun debug(msgFn: () -> String, t: Throwable) {
        if(isDebugEnabled()) {
            logger.debug(msgFn(), t)
        }
    }

    override fun info(msg: String) {
        if(isInfoEnabled()) {
            logger.info(msg)
        }
    }

    override fun info(msgFn: () -> String) {
        if(isInfoEnabled()) {
            logger.info(msgFn())
        }
    }

    override fun info(msg: String, t: Throwable) {
        if(isInfoEnabled()) {
            logger.info(msg, t)
        }
    }

    override fun info(msgFn: () -> String, t: Throwable) {
        if(isInfoEnabled()) {
            logger.info(msgFn(), t)
        }
    }

    override fun warn(msg: String) {
        if(isWarnEnabled()) {
            logger.warn(msg)
        }
    }

    override fun warn(msgFn: () -> String) {
        if(isWarnEnabled()) {
            logger.warn(msgFn())
        }
    }

    override fun warn(msg: String, t: Throwable) {
        if(isWarnEnabled()) {
            logger.warn(msg, t)
        }
    }

    override fun warn(msgFn: () -> String, t: Throwable) {
        if(isWarnEnabled()) {
            logger.warn(msgFn(), t)
        }
    }

    override fun error(msg: String) {
        if(isErrorEnabled()) {
            logger.error(msg)
        }
    }

    override fun error(msgFn: () -> String) {
        if(isErrorEnabled()) {
            logger.error(msgFn())
        }
    }

    override fun error(msg: String, t: Throwable) {
        if(isErrorEnabled()) {
            logger.error(msg, t)
        }
    }

    override fun error(msgFn: () -> String, t: Throwable) {
        if(isErrorEnabled()) {
            logger.error(msgFn(), t)
        }
    }

}
