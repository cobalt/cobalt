package org.hexworks.cobalt.databinding.internal.property

import org.hexworks.cobalt.core.extensions.Predicate
import org.hexworks.cobalt.core.internal.toAtom
import org.hexworks.cobalt.core.platform.factory.IdentifierFactory
import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.IsomorphicConverter
import org.hexworks.cobalt.databinding.api.event.ChangeEventScope
import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.value.*
import org.hexworks.cobalt.databinding.internal.binding.BidirectionalBinding
import org.hexworks.cobalt.databinding.internal.binding.UnidirectionalBinding
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribeTo
import org.hexworks.cobalt.logging.api.LoggerFactory
import kotlin.jvm.Synchronized

@Suppress("UNCHECKED_CAST")
class DefaultProperty<T : Any>(
        initialValue: T,
        private val validator: Predicate<T> = { true }) : Property<T> {

    private val id = IdentifierFactory.randomIdentifier()
    private val logger = LoggerFactory.getLogger(this::class)
    private val selfScope = ChangeEventScope()
    private val atom = initialValue.toAtom()
    private val identityConverter = IdentityConverter<T>()

    override var value: T
        get() = atom.get()
        set(value) {
            updateCurrentValue(value)
        }

    override fun toString() = "Property(id=${id.toString().substring(0, 4)}, value=$value)"

    override fun updateValue(newValue: T): ValueValidationResult {
        println("Trying to update value $value to new value $newValue.")
        return try {
            updateCurrentValue(newValue)
            ValueValidationSuccessful
        } catch (e: ValueValidationFailedException) {
            ValueValidationFailed(e)
        }
    }

    override fun onChange(fn: (ObservableValueChanged<T>) -> Unit): Subscription {
        println("Subscribing to changes to property: $this.")
        return Cobalt.eventbus.subscribeTo<ObservableValueChanged<T>>(selfScope) {
            fn(it)
        }
    }

    override fun bind(other: Property<T>, updateWhenBound: Boolean): Binding<T> {
        return bind(other = other,
                updateWhenBound = updateWhenBound,
                converter = identityConverter)
    }

    override fun <U : Any> bind(other: Property<U>,
                                updateWhenBound: Boolean,
                                converter: IsomorphicConverter<T, U>): Binding<T> {
        println("Binding property $this to other property $other.")
        checkSelfBinding(other)
        updateCurrentValue(converter.convertBack(other.value))
        return BidirectionalBinding(this, other, converter)
    }

    override fun updateFrom(observable: ObservableValue<T>, updateWhenBound: Boolean): Binding<T> {
        return updateFrom(observable, updateWhenBound) { it }
    }

    override fun <U : Any> updateFrom(
            observable: ObservableValue<U>,
            updateWhenBound: Boolean,
            converter: (U) -> T): Binding<T> {
        println("Starting to update property $this from $observable.")
        checkSelfBinding(observable)
        updateCurrentValue(converter(observable.value))
        return UnidirectionalBinding(observable, this, converter)
    }

    private fun checkSelfBinding(other: ObservableValue<Any>) {
        require(this !== other) {
            "Can't bind a property to itself."
        }
    }

    @Synchronized
    private fun updateCurrentValue(value: T) {
        println("Updating current value of property $this to $value.")
        var oldValue = atom.get()
        atom.transform { currValue ->
            oldValue = currValue
            if (validator(value).not()) {
                throw ValueValidationFailedException("The given value '$value' is invalid.")
            }
            value
        }
        // this trick enables the whole system not to crash if there is a circular dependency
        if (oldValue != value) {
            val event = ObservableValueChanged(
                    observableValue = this,
                    oldValue = oldValue,
                    newValue = value)
            println("Old value $oldValue of $this differs from new value $value, firing change event.")
            Cobalt.eventbus.publish(
                    event = event,
                    eventScope = selfScope)
        }
    }
}


