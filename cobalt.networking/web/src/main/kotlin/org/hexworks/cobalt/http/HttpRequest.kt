package org.hexworks.cobalt.http

import org.hexworks.cobalt.http.builder.EventContext
import org.w3c.xhr.FormData
import org.w3c.xhr.XMLHttpRequest

class HttpRequest(private val method: HttpMethod,
                  private val url: String,
                  private var async: Boolean,
                  private val user: String,
                  private val password: String,
                  private val onProgress: (EventContext) -> Unit,
                  private val onLoad: (EventContext) -> Unit,
                  private val onError: (EventContext) -> Unit,
                  private val onAbort: (EventContext) -> Unit,
                  private val formData: FormData,
                  private val httpStatusListeners: Map<HttpStatus, (EventContext) -> Unit>,
                  private val readyStateListeners: Map<ReadyState, (EventContext) -> Unit>) {

    fun send() {
        val xhr = XMLHttpRequest()
        xhr.open(method.name, url, async)
        xhr.onload = {
            onLoad(EventContext.fromXHR(xhr))
        }
        xhr.onreadystatechange = {
            EventContext.fromXHR(xhr).let { ec ->
                readyStateListeners[ec.readyState]?.invoke(ec)
                if(ec.readyState == ReadyState.DONE) {
                    httpStatusListeners[ec.status]?.invoke(ec)
                }
            }
        }
        xhr.onabort = {
            onAbort(EventContext.fromXHR(xhr))
        }
        xhr.onerror = {
            onError(EventContext.fromXHR(xhr))
        }
        xhr.onprogress = {
            onProgress(EventContext.fromXHR(xhr))
        }
        if(formData === NO_FORM_DATA) {
            xhr.send()
        } else {
            xhr.send(formData)
        }
    }

    companion object {
        val NO_FORM_DATA = FormData()
    }
}
