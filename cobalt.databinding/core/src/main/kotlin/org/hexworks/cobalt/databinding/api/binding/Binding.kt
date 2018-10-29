package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.value.ObservableValue

/**
 * A [Binding] calculates a value that depends on one or more sources. The
 * sources are usually called the dependency of a binding. A binding observes
 * its dependencies for changes and updates its value automatically.
 */
interface Binding<T> : ObservableValue<T> {

    // TODO: do we need these?
//    fun isValid(): Boolean

//    fun getDependencies(): ObservableList<*>

//    fun invalidate()

    fun dispose()

    fun computeValue(): T

}
