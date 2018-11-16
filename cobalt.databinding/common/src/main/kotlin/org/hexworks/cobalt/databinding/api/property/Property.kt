package org.hexworks.cobalt.databinding.api.property

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.BiConverter
import org.hexworks.cobalt.databinding.api.converter.Converter
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.api.value.WritableValue

/**
 * A [Property] wraps a value which can be *read*, *written*, *observed*
 * for changes and *bound* to other [Property] and [ObservableValue]
 * objects.
 *
 * **Note that** a [Property] can have only one unidirectional binding
 * (created with [Property.bind] to another one. If a new unidirectional
 * binding is created the old one will be disposed.
 *
 * [Property.value] can only be set **if the [Property] is not bound**.
 *
 * Any number of **bidirectional** bindings can be created however and
 * they don't restrict setting the `value` of the [Property] either.
 */
interface Property<T : Any> : WritableValue<T>, ObservableValue<T> {

    /**
     * Tells whether the value of this [Property] is bound to another one.
     */
    fun isBound(): Boolean

    /**
     * Binds the value of this [Property] to the value of the given [ObservableValue].
     * **Note that** any previous unidirectional bindings created with [Property.bind]
     * will be disposed.
     */
    fun bind(observable: ObservableValue<T>)

    /**
     * Binds the value of this [Property] to the value of the given [ObservableValue]
     * of type [U]. Uses the given [Converter] to convert the value of of the
     * given observable.
     */
    fun <U : Any> bind(observable: ObservableValue<U>, converter: Converter<U, T>)

    /**
     * Removes the unidirectional binding for this [Property] (if any).
     * If the [Property] is not bound, calling this method has no effect.
     */
    fun unbind()

    /**
     * Creates a bidirectional binding between this [Property] and another one.
     */
    fun bindBidirectional(other: Property<T>): Binding<T>

    /**
     * Binds the value of this [Property] to the value of the given [ObservableValue]
     * of type [U] **bidirectionally**. Uses the given [Converter] to convert the value of of the
     * given observable.
     */
    fun <U : Any> bindBidirectional(other: Property<U>, converter: BiConverter<T, U>): Binding<T>

}
