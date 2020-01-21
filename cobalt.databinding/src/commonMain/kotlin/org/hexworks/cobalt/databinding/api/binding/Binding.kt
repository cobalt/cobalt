package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.data.DisposeState
import org.hexworks.cobalt.databinding.api.data.DisposedByHand
import org.hexworks.cobalt.databinding.api.value.ObservableValue

/**
 * A [Binding] computes its value based on the value of its dependencies.
 * A binding is subscribed to the changes of its dependencies and updates
 * its value whenever any of them changes.
 */
interface Binding<out T : Any> : ObservableValue<T> {

    /**
     * Tells whether this [Binding] is disposed or not.
     */
    val disposed: Boolean
        get() = disposeState.isDisposed

    /**
     * Contains detailed information about the dispose state
     * of this [Binding] like if it is not disposed, or was
     * disposed because of an exception.
     */
    val disposeState: DisposeState

    /**
     * Disposes this [Binding] with the given [DisposeState].
     * Default is [DisposedByHand].
     */
    fun dispose(disposeState: DisposeState = DisposedByHand)

    companion object
}
