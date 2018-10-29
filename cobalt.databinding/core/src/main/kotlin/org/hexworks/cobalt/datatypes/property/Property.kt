package org.hexworks.cobalt.datatypes.property

import org.hexworks.cobalt.datatypes.value.ObservableValue
import org.hexworks.cobalt.datatypes.value.WritableValue

interface Property<T> : WritableValue<T> {

    /**
     * Can be used to check, if a [Property] is bound.
     */
    fun isBound(): Boolean

    /**
     * Create a unidirection binding for this [Property].
     */
    fun bind(observable: ObservableValue<out T>)

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
    fun bindBidirectional(other: Property<T>)

    /**
     * Remove a bidirectional binding between this [Property] and `other`.
     *
     * If no bidirectional binding between the properties exists, calling this
     * method has no effect.
     */
    fun unbindBidirectional(other: Property<T>)

}
