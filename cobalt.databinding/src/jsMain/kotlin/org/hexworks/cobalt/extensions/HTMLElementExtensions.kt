package org.hexworks.cobalt.extensions

import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.w3c.dom.HTMLInputElement

fun Property<String>.bindTo(inputElement: HTMLInputElement) {
    val prop = this
    inputElement.addEventListener(type = "input", callback = {
        prop.value = inputElement.value
    })
}

fun HTMLInputElement.bindTo(observable: ObservableValue<String>) {
    val element = this
    observable.onChange {
        element.value = observable.value
    }
}
