package org.hexworks.cobalt.databinding.api.property

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.IsomorphicConverter
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.api.value.WritableValue
import org.hexworks.cobalt.databinding.internal.property.DefaultPropertyDelegate

/**
 * A [Property] wraps a value which can be *read*, *written* and *observed*
 * and supports **bidirectional** binding using the [bind] function.
 * @see WritableValue
 * @see ObservableValue
 */
interface Property<T : Any> : WritableValue<T>, ObservableValue<T> {

    /**
     * Creates a bidirectional binding between this [Property] and [other].
     * eg: whenever each one is updated, the other will get updated with
     * the new value. If [updateWhenBound] is `true` then the value of this
     * [Property] will be updated when the binding takes place. Otherwise it will
     * only get updated when [other] is updated.
     */
    fun bind(other: Property<T>, updateWhenBound: Boolean = true): Binding<T>

    /**
     * Creates a bidirectional binding between this [Property] and [other].
     * Uses the given [IsomorphicConverter] to convert the values between the
     * subject properties. If [updateWhenBound] is `true` then the value of this
     * [WritableValue] will be updated when the binding takes place.
     * Otherwise it will only get updated when [other] is updated.
     */
    fun <S : Any> bind(other: Property<S>,
                       updateWhenBound: Boolean = true,
                       converter: IsomorphicConverter<S, T>): Binding<T>

    /**
     * Creates a [PropertyDelegate] for this [Property].
     */
    fun asDelegate(): PropertyDelegate<T> = DefaultPropertyDelegate(this)

    companion object
}
