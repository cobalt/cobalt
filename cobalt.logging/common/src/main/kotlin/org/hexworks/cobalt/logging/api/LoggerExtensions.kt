package org.hexworks.cobalt.logging.api

import org.hexworks.cobalt.datatypes.sam.Supplier

/**
 * Lazily log a message at the TRACE level.
 */
inline fun Logger.trace(crossinline msgFn: () -> String) {
    trace(object : Supplier<String> {
        override fun get(): String {
            return msgFn()
        }
    })
}

/**
 * Lazily log an exception (throwable) at the TRACE level with an
 * accompanying message.
 */
inline fun Logger.trace(crossinline msgFn: () -> String, t: Throwable) {
    trace(object : Supplier<String> {
        override fun get(): String {
            return msgFn()
        }
    }, t)
}

/**
 * Lazily log a message at the DEBUG level.
 */
inline fun Logger.debug(crossinline msgFn: () -> String) {
    debug(object : Supplier<String> {
        override fun get(): String {
            return msgFn()
        }
    })
}

/**
 * Lazily log an exception (throwable) at the DEBUG level with an
 * accompanying message.
 */
inline fun Logger.debug(crossinline msgFn: () -> String, t: Throwable) {
    debug(object : Supplier<String> {
        override fun get(): String {
            return msgFn()
        }
    }, t)
}

/**
 * Lazily log a message at the INFO level.
 */
inline fun Logger.info(crossinline msgFn: () -> String) {
    info(object : Supplier<String> {
        override fun get(): String {
            return msgFn()
        }
    })
}

/**
 * Lazily log an exception (throwable) at the INFO level with an
 * accompanying message.
 */
inline fun Logger.info(crossinline msgFn: () -> String, t: Throwable) {
    info(object : Supplier<String> {
        override fun get(): String {
            return msgFn()
        }
    }, t)
}

/**
 * Lazily log a message at the WARN level.
 */
inline fun Logger.warn(crossinline msgFn: () -> String) {
    warn(object : Supplier<String> {
        override fun get(): String {
            return msgFn()
        }
    })
}

/**
 * Lazily log an exception (throwable) at the WARN level with an
 * accompanying message.
 */
inline fun Logger.warn(crossinline msgFn: () -> String, t: Throwable) {
    warn(object : Supplier<String> {
        override fun get(): String {
            return msgFn()
        }
    }, t)
}

/**
 * Lazily log a message at the ERROR level.
 */
inline fun Logger.error(crossinline msgFn: () -> String) {
    error(object : Supplier<String> {
        override fun get(): String {
            return msgFn()
        }
    })
}

/**
 * Lazily log an exception (throwable) at the ERROR level with an
 * accompanying message.
 */
inline fun Logger.error(crossinline msgFn: () -> String, t: Throwable) {
    error(object : Supplier<String> {
        override fun get(): String {
            return msgFn()
        }
    }, t)
}
