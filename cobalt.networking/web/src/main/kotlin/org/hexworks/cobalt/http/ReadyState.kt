package org.hexworks.cobalt.http

enum class ReadyState(val value: Short) {

    UNSENT(0),
    OPENED(1),
    HEADERS_RECEIVED(2),
    LOADING(3),
    DONE(4);

    companion object {

        fun fetchByReadyStateValue(value: Short): ReadyState {
            return values().firstOrNull { it.value == value } ?: throw IllegalArgumentException(
                    "Can't find ReadyState for value '$value'.")
        }
    }
}
