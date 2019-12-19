package org.hexworks.cobalt.databinding.internal.property

import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.IsomorphicConverter
import org.hexworks.cobalt.databinding.api.event.ChangeEvent
import org.hexworks.cobalt.databinding.api.event.ChangeEventScope
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.value.*
import org.hexworks.cobalt.databinding.internal.binding.BidirectionalBinding
import org.hexworks.cobalt.databinding.internal.binding.UnidirectionalBinding
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribe

@Suppress("UNCHECKED_CAST")
class DefaultProperty<T : Any>(
        initialValue: T,
        private val isValid: (T) -> Boolean = { true }) : Property<T> {

    private val scope = ChangeEventScope()

    private var currentValue = initialValue
    private val identityConverter = IdentityConverter<T>()

    override var value: T
        get() = currentValue
        set(value) {
            updateCurrentValue(value)
        }

    override fun updateValue(newValue: T): ValueValidationResult {
        return try {
            updateCurrentValue(newValue)
            ValueValidationSuccessful
        } catch (e: ValueValidationFailedException) {
            ValueValidationFailed(e)
        }
    }

    override fun onChange(fn: (ChangeEvent<T>) -> Unit): Subscription {
        return Cobalt.eventbus.subscribe<ChangeEvent<T>>(scope) {
            fn(it)
        }
    }

    override fun bind(other: Property<T>, updateWhenBound: Boolean): Binding<T> {
        return bind(other, updateWhenBound, identityConverter)
    }

    override fun <U : Any> bind(other: Property<U>,
                                updateWhenBound: Boolean,
                                converter: IsomorphicConverter<T, U>): Binding<T> {
        checkSelfBinding(other)
        if (updateWhenBound) {
            updateCurrentValue(converter.convertBack(other.value))
        }
        return BidirectionalBinding(this, other, converter)
    }

    override fun updateFrom(observable: ObservableValue<T>, updateWhenBound: Boolean): Binding<T> {
        return updateFrom(observable, updateWhenBound) { it }
    }

    override fun <U : Any> updateFrom(
            observable: ObservableValue<U>,
            updateWhenBound: Boolean,
            converter: (U) -> T): Binding<T> {
        checkSelfBinding(observable)
        if (updateWhenBound) {
            updateCurrentValue(converter(observable.value))
        }
        return UnidirectionalBinding(observable, this, converter)
    }

    private fun checkSelfBinding(other: ObservableValue<Any>) {
        require(this !== other) {
            "Can't bind a property to itself."
        }
    }

    private fun updateCurrentValue(value: T) {
        if (isValid(value).not()) {
            throw ValueValidationFailedException("The given value '$value' is invalid.")
        }
        // this trick enables the whole system not to crash if there is a circular dependency
        if (currentValue != value) {
            val ce = ChangeEvent(
                    observableValue = this,
                    oldValue = currentValue,
                    newValue = value)
            currentValue = value
            Cobalt.eventbus.publish(
                    event = ce,
                    eventScope = scope)
        }
    }
}


