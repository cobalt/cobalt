package org.hexworks.cobalt.html

import org.hexworks.cobalt.datatypes.Maybe
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.MouseEvent

fun HTMLElement.addClickListener(listener: (MouseEvent) -> Unit) {
    this.addEventListener("click", { e ->
        listener.invoke(e as MouseEvent)
    }, false)
}

fun HTMLElement.addDoubleClickListener(listener: (MouseEvent) -> Unit) {
    this.addEventListener("dblclick", { e ->
        listener.invoke(e as MouseEvent)
    }, false)
}

fun HTMLElement.toggleVisibility() {
    if (this.style.display == "none") {
        this.style.display = "block"
    } else {
        this.style.display = "none"
    }
}

fun HTMLElement.show() {
    this.style.display = "block"
}

fun HTMLElement.hide() {
    this.style.display = "none"
}

@Suppress("UNCHECKED_CAST")
fun <T : HTMLElement> HTMLElement.findChild(selector: String) = Maybe.ofNullable(this.querySelector(selector) as? T)

fun HTMLElement.getText(): String = this.innerHTML

fun HTMLElement.setText(text: String): String {
    return this.innerHTML.also {
        this.innerHTML = text
    }
}
