package org.hexworks.cobalt.html

import org.w3c.dom.HTMLFormElement
import org.w3c.dom.events.Event

fun HTMLFormElement.addSubmitListener(listener: (Event) -> Unit) {
    this.onsubmit = { event: Event ->
        event.preventDefault()
        listener.invoke(event)
    }
}
