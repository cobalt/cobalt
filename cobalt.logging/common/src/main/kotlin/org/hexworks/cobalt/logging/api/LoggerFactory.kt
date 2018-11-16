package org.hexworks.cobalt.logging.api

expect object LoggerFactory {

    fun getLogger(name: String): Logger
}
