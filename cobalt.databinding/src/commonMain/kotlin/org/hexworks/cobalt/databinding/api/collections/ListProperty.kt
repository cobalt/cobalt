package org.hexworks.cobalt.databinding.api.collections

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.IsomorphicConverter
import org.hexworks.cobalt.databinding.api.property.ListPropertyDelegate
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.property.PropertyDelegate
import org.hexworks.cobalt.databinding.internal.collections.DefaultListPropertyDelegate

/**
 * A [ListProperty] wraps a value which can be *read*, *written* and *observed*
 * and supports **bidirectional** binding using the [bind] function.
 * @see WritableList
 * @see ObservableList
 */
interface ListProperty<T : Any> : MutableList<T>, ObservableList<T>, WritableList<T> {

    /**
     * Creates a bidirectional binding between this [Property] and [other].
     * eg: whenever each one is updated, the other will get updated with
     * the new value.
     */
    infix fun bind(other: ListProperty<T>): Binding<ListProperty<T>>

    /**
     * Creates a bidirectional binding between this [Property] and [other].
     * Uses the given [IsomorphicConverter] to convert the values between the
     * subject properties.
     */
    fun <U : Any> bind(other: ListProperty<U>, converter: IsomorphicConverter<T, U>): Binding<ListProperty<T>>

    /**
     * Creates a [PropertyDelegate] for this [Property].
     */
    fun asDelegate(): ListPropertyDelegate<T> = DefaultListPropertyDelegate(this)

    companion object
}
