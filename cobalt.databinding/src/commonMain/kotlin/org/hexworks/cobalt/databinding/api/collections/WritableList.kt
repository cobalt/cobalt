package org.hexworks.cobalt.databinding.api.collections

import org.hexworks.cobalt.databinding.api.binding.Binding

interface WritableList<T : Any> : MutableList<T> {

    /**
     * Starts updating this [WritableList] from the given [observable].
     * @return a [Binding] which can be disposed to stop the updates
     */
    infix fun updateFrom(observable: ObservableList<T>): Binding<ObservableList<T>>

    /**
     * Starts updating this [WritableList] from the given [observable].
     * Uses the given [converter] to convert the values
     * @return a [Binding] which can be disposed to stop the updates
     */
    fun <U : Any> updateFrom(observable: ObservableList<U>, converter: (U) -> T): Binding<ObservableList<T>>
}
