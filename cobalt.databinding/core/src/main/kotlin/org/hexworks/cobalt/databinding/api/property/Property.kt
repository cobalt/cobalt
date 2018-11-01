package org.hexworks.cobalt.databinding.api.property

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.BiConverter
import org.hexworks.cobalt.databinding.api.converter.Converter
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
     * Create a unidirectional binding for this [Property] to another one
     * with type [U].
     */
    fun <U : Any> bind(observable: ObservableValue<U>, converter: Converter<U, T>)

    /**
     * Remove the unidirectional binding for this [Property].
     * If the [Property] is not bound, calling this method has no effect.
     */
    fun unbind()

    /**
     * Creates a bidirectional binding between this [Property] and another one.
     */
    fun bindBidirectional(other: Property<T>): Binding<T>

    /**
     * Creates a bidirectional binding between this [Property] and another one
     * with a different type ([U]).
     */
    fun <U : Any> bindBidirectional(other: Property<U>, converter: BiConverter<T, U>): Binding<T>

}
