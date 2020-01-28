package org.hexworks.cobalt.databinding.api.collections

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.Value

/**
 * A [WritableList] is a [Value] which wraps an underlying [List] and it
 * supports list mutation operations from [MutableList] and can also be
 * bound to an [ObservableList] to track its changes.
 */
interface WritableList<T : Any> : Value<List<T>>, MutableList<T> {

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

    companion object
}
