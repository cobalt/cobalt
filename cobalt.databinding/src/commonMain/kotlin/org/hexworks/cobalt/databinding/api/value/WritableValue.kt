package org.hexworks.cobalt.databinding.api.value

import org.hexworks.cobalt.databinding.api.binding.Binding

/**
 * A [WritableValue] wraps a value which can be read and written.
 */
interface WritableValue<T : Any> : Value<T> {

    override var value: T

    /**
     * Tries to update the [value] with the given [newValue].
     * @return [ValueValidationSuccessful] if [newValue] is
     * acceptable, [ValueValidationFailed] if not.
     */
    fun updateValue(newValue: T): ValueValidationResult

    /**
     * Starts updating this [WritableValue] from the given [observable]. If [updateWhenBound]
     * is `true` then the value of this [WritableValue] will be updated when the binding takes place.
     * Otherwise it will only get updated when [observable] is updated.
     * @return a [Binding] which can be disposed to stop the updates
     */
    fun updateFrom(observable: ObservableValue<T>,
                   updateWhenBound: Boolean = true): Binding<T>

    /**
     * Starts updating this [WritableValue] from the given [observable].
     * Uses the given [converter] to convert the values. If [updateWhenBound]
     * is `true` then the value of this [WritableValue] will be updated
     * when the binding takes place. Otherwise it will only get updated when
     * [observable] is updated.
     * @return a [Binding] which can be disposed to stop the updates
     */
    fun <U : Any> updateFrom(observable: ObservableValue<U>,
                             updateWhenBound: Boolean = true,
                             converter: (U) -> T): Binding<T>

}
