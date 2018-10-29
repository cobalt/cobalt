package org.hexworks.cobalt.datatypes.event

import org.hexworks.cobalt.datatypes.value.ObservableValue

interface ChangeListener<T> {

    fun changed(observable: ObservableValue<out T>, oldValue: T, newValue: T)
}
