package org.hexworks.cobalt.logging.api

/**
 * The [Logger] interface contains all possible logging operations.
 */
interface Logger {

    /**
     * The name of this logger instance
     */
    val name: String

    /**
     * Is the logger instance enabled for the TRACE level?
     */
    fun isTraceEnabled(): Boolean

    /**
     * Is the logger instance enabled for the DEBUG level?
     */
    fun isDebugEnabled(): Boolean

    /**
     * Is the logger instance enabled for the INFO level?
     */
    fun isInfoEnabled(): Boolean

    /**
     * Is the logger instance enabled for the WARN level?
     */
    fun isWarnEnabled(): Boolean

    /**
     * Is the logger instance enabled for the ERROR level?
     */
    fun isErrorEnabled(): Boolean

    /**
     * Log a message at the TRACE level.
     */
    fun trace(msg: String)

    /**
     * Lazily log a message at the TRACE level.
     */
    fun trace(msgFn: () -> String)

    /**
     * Log an exception (throwable) at the TRACE level with an
     * accompanying message.
     */
    fun trace(msg: String, t: Throwable)

    /**
     * Lazily log an exception (throwable) at the TRACE level with an
     * accompanying message.
     */
    fun trace(msgFn: () -> String, t: Throwable)

    /**
     * Log a message at the DEBUG level.
     */
    fun debug(msg: String)

    /**
     * Lazily log a message at the DEBUG level.
     */
    fun debug(msgFn: () -> String)

    /**
     * Log an exception (throwable) at the DEBUG level with an
     * accompanying message.
     */
    fun debug(msg: String, t: Throwable)

    /**
     * Lazily log an exception (throwable) at the DEBUG level with an
     * accompanying message.
     */
    fun debug(msgFn: () -> String, t: Throwable)


    /**
     * Log a message at the INFO level.
     */
    fun info(msg: String)

    /**
     * Lazily log a message at the INFO level.
     */
    fun info(msgFn: () -> String)

    /**
     * Log an exception (throwable) at the INFO level with an
     * accompanying message.
     */
    fun info(msg: String, t: Throwable)

    /**
     * Lazily log an exception (throwable) at the INFO level with an
     * accompanying message.
     */
    fun info(msgFn: () -> String, t: Throwable)

    /**
     * Log a message at the WARN level.
     */
    fun warn(msg: String)

    /**
     * Lazily log a message at the WARN level.
     */
    fun warn(msgFn: () -> String)

    /**
     * Log an exception (throwable) at the WARN level with an
     * accompanying message.
     */
    fun warn(msg: String, t: Throwable)

    /**
     * Lazily log an exception (throwable) at the WARN level with an
     * accompanying message.
     */
    fun warn(msgFn: () -> String, t: Throwable)

    /**
     * Log a message at the ERROR level.
     */
    fun error(msg: String)

    /**
     * Lazily log a message at the ERROR level.
     */
    fun error(msgFn: () -> String)

    /**
     * Log an exception (throwable) at the ERROR level with an
     * accompanying message.
     */
    fun error(msg: String, t: Throwable)

    /**
     * Lazily log an exception (throwable) at the ERROR level with an
     * accompanying message.
     */
    fun error(msgFn: () -> String, t: Throwable)

}
