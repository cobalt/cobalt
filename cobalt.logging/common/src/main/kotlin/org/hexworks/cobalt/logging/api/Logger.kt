package org.hexworks.cobalt.logging.api

import org.hexworks.cobalt.datatypes.sam.Supplier

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
    fun trace(msgFn: Supplier<String>)

    /**
     * Log an exception (throwable) at the TRACE level with an
     * accompanying message.
     */
    fun trace(msg: String, t: Throwable)

    /**
     * Lazily log an exception (throwable) at the TRACE level with an
     * accompanying message.
     */
    fun trace(msgFn: Supplier<String>, t: Throwable)

    /**
     * Log a message at the DEBUG level.
     */
    fun debug(msg: String)

    /**
     * Lazily log a message at the DEBUG level.
     */
    fun debug(msgFn: Supplier<String>)

    /**
     * Log an exception (throwable) at the DEBUG level with an
     * accompanying message.
     */
    fun debug(msg: String, t: Throwable)

    /**
     * Lazily log an exception (throwable) at the DEBUG level with an
     * accompanying message.
     */
    fun debug(msgFn: Supplier<String>, t: Throwable)


    /**
     * Log a message at the INFO level.
     */
    fun info(msg: String)

    /**
     * Lazily log a message at the INFO level.
     */
    fun info(msgFn: Supplier<String>)

    /**
     * Log an exception (throwable) at the INFO level with an
     * accompanying message.
     */
    fun info(msg: String, t: Throwable)

    /**
     * Lazily log an exception (throwable) at the INFO level with an
     * accompanying message.
     */
    fun info(msgFn: Supplier<String>, t: Throwable)

    /**
     * Log a message at the WARN level.
     */
    fun warn(msg: String)

    /**
     * Lazily log a message at the WARN level.
     */
    fun warn(msgFn: Supplier<String>)

    /**
     * Log an exception (throwable) at the WARN level with an
     * accompanying message.
     */
    fun warn(msg: String, t: Throwable)

    /**
     * Lazily log an exception (throwable) at the WARN level with an
     * accompanying message.
     */
    fun warn(msgFn: Supplier<String>, t: Throwable)

    /**
     * Log a message at the ERROR level.
     */
    fun error(msg: String)

    /**
     * Lazily log a message at the ERROR level.
     */
    fun error(msgFn: Supplier<String>)

    /**
     * Log an exception (throwable) at the ERROR level with an
     * accompanying message.
     */
    fun error(msg: String, t: Throwable)

    /**
     * Lazily log an exception (throwable) at the ERROR level with an
     * accompanying message.
     */
    fun error(msgFn: Supplier<String>, t: Throwable)

}
