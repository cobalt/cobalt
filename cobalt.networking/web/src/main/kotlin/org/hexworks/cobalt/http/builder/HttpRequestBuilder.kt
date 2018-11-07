package org.hexworks.cobalt.http.builder

import org.hexworks.cobalt.http.HttpMethod
import org.hexworks.cobalt.http.HttpRequest
import org.hexworks.cobalt.http.HttpStatus
import org.hexworks.cobalt.http.ReadyState
import org.w3c.xhr.FormData

class HttpRequestBuilder internal constructor(
        private var method: HttpMethod,
        init: HttpRequestBuilder.() -> Unit) {

    var url: String = ""
    var async: Boolean = true
    var user: String = ""
    var password: String = ""
    var formData: FormData = HttpRequest.NO_FORM_DATA

    // low level listeners
    private var onProgress: (EventContext) -> Unit = {}
    private var onLoad: (EventContext) -> Unit = {}
    private var onError: (EventContext) -> Unit = {}
    private var onAbort: (EventContext) -> Unit = {}

    // high level listeners
    private val httpStatusListeners = mutableMapOf<HttpStatus, (EventContext) -> Unit>()
    private val readyStateListeners = mutableMapOf<ReadyState, (EventContext) -> Unit>()

    init {
        init(this)
    }

    fun onProgress(onProgress: (EventContext) -> Unit) {
        this.onProgress = onProgress
    }

    fun onLoad(onProgress: (EventContext) -> Unit) {
        this.onProgress = onProgress
    }

    fun onError(onError: (EventContext) -> Unit) {
        this.onError = onError
    }
    

    fun onAbort(onAbort: (EventContext) -> Unit) {
        this.onAbort = onAbort
    }

    fun onReadyState(readyState: ReadyState, fn: (EventContext) -> Unit) {
        readyStateListeners[readyState] = fn
    }

    fun onHttpStatus(httpStatus: HttpStatus, fn: (EventContext) -> Unit) {
        httpStatusListeners[httpStatus] = fn
    }

    fun build() = HttpRequest(
            method = method,
            url = url,
            async = async,
            user = user,
            password = password,
            onProgress = onProgress,
            onLoad = onLoad,
            onError = onError,
            onAbort = onAbort,
            httpStatusListeners = httpStatusListeners,
            readyStateListeners = readyStateListeners,
            formData = formData)

}
