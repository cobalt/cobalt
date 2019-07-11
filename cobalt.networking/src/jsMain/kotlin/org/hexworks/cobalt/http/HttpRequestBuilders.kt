package org.hexworks.cobalt.http

import org.hexworks.cobalt.http.builder.HttpRequestBuilder


fun get(init: HttpRequestBuilder.() -> Unit): HttpRequest {
    return HttpRequestBuilder(HttpMethod.GET, init).build()
}

fun post(init: HttpRequestBuilder.() -> Unit): HttpRequest {
    return HttpRequestBuilder(HttpMethod.POST, init).build()
}

fun put(init: HttpRequestBuilder.() -> Unit): HttpRequest {
    return HttpRequestBuilder(HttpMethod.PUT, init).build()
}

fun delete(init: HttpRequestBuilder.() -> Unit): HttpRequest {
    return HttpRequestBuilder(HttpMethod.DELETE, init).build()
}

