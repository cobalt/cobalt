package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.data.DisposeState
import org.hexworks.cobalt.databinding.api.data.DisposedByHand
import org.hexworks.cobalt.databinding.api.value.ObservableValue

/**
 * A [Binding] calculates a value that depends on one or more sources. The
 * sources are usually called the dependency of a binding. A binding observes
 * its dependencies for changes and updates its value automatically.
 */
interface Binding<out T : Any> : ObservableValue<T> {

    val disposed: Boolean
        get() = disposeState.disposed

    val disposeState: DisposeState

    fun dispose(disposeState: DisposeState = DisposedByHand)

    companion object
}
