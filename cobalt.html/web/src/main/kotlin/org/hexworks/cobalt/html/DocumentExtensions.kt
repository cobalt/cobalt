package org.hexworks.cobalt.html

import org.hexworks.cobalt.datatypes.Maybe
import org.w3c.dom.Document
import org.w3c.dom.HTMLElement

@Suppress("UNCHECKED_CAST")
fun <T : HTMLElement> Document.find(selector: String): Maybe<T> {
    return Maybe.ofNullable(this.querySelector(selector) as? T)
}
