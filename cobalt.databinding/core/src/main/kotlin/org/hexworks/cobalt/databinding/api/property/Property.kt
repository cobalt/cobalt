package org.hexworks.cobalt.databinding.api.property

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.api.value.WritableValue

interface Property<T : Any> : WritableValue<T>, ObservableValue<T> {

    /**
     * Can be used to check, if a [Property] is bound.
     */
    fun isBound(): Boolean

    /**
     * Create a unidirectional binding for this [Property].
     */
    fun bind(observable: ObservableValue<T>)

    /**
     * Remove the unidirectional binding for this [Property].
     * If the [Property] is not bound, calling this method has no effect.
     */
    fun unbind()

    /**
     * Create a bidirectional binding between this [Property] and another one.
     *
     * It is possible to have multiple bidirectional bindings of one Property.
     *
     */
    fun bindBidirectional(other: Property<T>): Binding<T>

}
