package org.hexworks.cobalt.http.builder

import org.hexworks.cobalt.http.HttpStatus
import org.hexworks.cobalt.http.ReadyState
import org.w3c.xhr.XMLHttpRequest
import org.w3c.xhr.XMLHttpRequestResponseType

data class EventContext(val readyState: ReadyState,
                        val response: Any?,
                        val responseText: String,
                        val responseType: XMLHttpRequestResponseType,
                        val responseURL: String,
                        val status: HttpStatus,
                        val statusText: String,
                        val timeout: Int) {

    fun <T> extractData(): T {
        return JSON.parse(responseText)
    }

    companion object {

        fun fromXHR(xhr: XMLHttpRequest): EventContext {
            return EventContext(
                    readyState = ReadyState.fetchByReadyStateValue(xhr.readyState),
                    response = xhr.response,
                    responseText = xhr.responseText,
                    responseType = xhr.responseType,
                    responseURL = xhr.responseURL,
                    status = HttpStatus.findByStatusCode(xhr.status),
                    statusText = xhr.statusText,
                    timeout = xhr.timeout)
        }
    }
}
