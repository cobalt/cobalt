package org.hexworks.cobalt.databinding.api.property

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.IsomorphicConverter
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.api.value.WritableValue
import org.hexworks.cobalt.databinding.internal.property.DefaultPropertyDelegate
import org.hexworks.cobalt.events.api.Subscription

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
     * Starts updating this [Property] from the given [observable].
     * @return a [Subscription] which can be cancelled to stop the updates
     */
    infix fun updateFrom(observable: ObservableValue<T>): Subscription

    /**
     * Starts updating this [Property] from the given [observable].
     * Uses the given [converter] to convert the values
     * @return a [Subscription] which can be cancelled to stop the updates
     */
    fun <U : Any> updateFrom(observable: ObservableValue<U>, converter: (U) -> T): Subscription

    /**
     * Creates a bidirectional binding between this [Property] and another one.
     */
    infix fun bind(other: Property<T>): Binding<T>

    /**
     * **Binds** the value of this [Property] to the value of the [other] [Property].
     * Uses the given [IsomorphicConverter] to convert the values between the
     * subject properties.
     */
    fun <U : Any> bind(other: Property<U>, converter: IsomorphicConverter<T, U>): Binding<T>

    /**
     * Creates a [PropertyDelegate] for this [Property].
     */
    fun asDelegate(): PropertyDelegate<T> = DefaultPropertyDelegate(this)

}
