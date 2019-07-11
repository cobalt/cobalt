package org.hexworks.cobalt.logging.api

enum class LoggingLevel {
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR;

    var isEnabled = this.ordinal >= LoggingSettings.loggingLevel.ordinal
}
