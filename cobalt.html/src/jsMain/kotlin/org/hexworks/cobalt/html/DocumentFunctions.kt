package org.hexworks.cobalt.html

import org.hexworks.cobalt.datatypes.Maybe
import org.w3c.dom.HTMLElement
import kotlin.browser.document

fun <T : HTMLElement> find(selector: String): Maybe<T> {
    return document.find(selector)
}
