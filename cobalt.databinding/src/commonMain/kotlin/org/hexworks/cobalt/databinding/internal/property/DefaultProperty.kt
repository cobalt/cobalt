package org.hexworks.cobalt.databinding.internal.property

import org.hexworks.cobalt.core.extensions.Predicate
import org.hexworks.cobalt.core.extensions.abbreviate
import org.hexworks.cobalt.core.internal.toAtom
import org.hexworks.cobalt.core.platform.factory.IdentifierFactory
import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.IdentityConverter
import org.hexworks.cobalt.databinding.api.converter.IsomorphicConverter
import org.hexworks.cobalt.databinding.api.converter.toConverter
import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.internal.event.PropertyScope
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.value.*
import org.hexworks.cobalt.databinding.internal.binding.BidirectionalBinding
import org.hexworks.cobalt.databinding.internal.binding.UnidirectionalBinding
import org.hexworks.cobalt.databinding.internal.exception.CircularBindingException
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribeTo
import org.hexworks.cobalt.logging.api.LoggerFactory
import kotlin.jvm.Synchronized

@Suppress("UNCHECKED_CAST")
class DefaultProperty<T : Any>(
        initialValue: T,
        private val validator: Predicate<T> = { true }
) : InternalProperty<T> {

    override val id = IdentifierFactory.randomIdentifier()
    override val logger = LoggerFactory.getLogger(this::class)
    override val propertyScope = PropertyScope(id)
    private val atom = initialValue.toAtom()
    private val identityConverter = IdentityConverter<T>()

    override var value: T
        get() = atom.get()
        set(value) {
            updateCurrentValue(value)
        }

    override fun toString() = "Property(id=${id.abbreviate()}, value=$value)"

    override fun updateValue(newValue: T): ValueValidationResult {
        logger.debug("Trying to update value $value to new value $newValue.")
        return try {
            updateCurrentValue(newValue)
            ValueValidationSuccessful
        } catch (e: ValueValidationFailedException) {
            ValueValidationFailed(e)
        }
    }

    override fun onChange(fn: (ObservableValueChanged<T>) -> Unit): Subscription {
        logger.debug("Subscribing to changes to property: $this.")
        return Cobalt.eventbus.subscribeTo<ObservableValueChanged<T>>(propertyScope) {
            fn(it)
        }
    }

    override fun bind(other: Property<T>, updateWhenBound: Boolean): Binding<T> {
        return bind(other = other,
                updateWhenBound = updateWhenBound,
                converter = identityConverter)
    }

    override fun <S : Any> bind(
            other: Property<S>,
            updateWhenBound: Boolean,
            converter: IsomorphicConverter<S, T>
    ): Binding<T> {
        logger.debug("Binding property $this to other property $other.")
        checkSelfBinding(other)
        other as? InternalProperty<S> ?: error("Can only bind Properties which implement InternalProperty.")
        if (updateWhenBound) {
            updateCurrentValue(converter.convert(other.value))
        }
        return BidirectionalBinding(
                source = other,
                target = this,
                converter = converter)
    }

    override fun updateFrom(
            observable: ObservableValue<T>,
            updateWhenBound: Boolean
    ): Binding<T> {
        return updateFrom(observable, updateWhenBound) { it }
    }

    override fun <S : Any> updateFrom(
            observable: ObservableValue<S>,
            updateWhenBound: Boolean,
            converter: (S) -> T): Binding<T> {
        logger.debug("Starting to update property $this from $observable.")
        checkSelfBinding(observable)
        if (updateWhenBound) {
            updateCurrentValue(converter(observable.value))
        }
        return UnidirectionalBinding(observable, this, converter.toConverter())
    }

    override fun updateWithEvent(
            oldValue: T,
            newValue: T,
            event: ObservableValueChanged<Any>): Boolean {

        logger.debug("Trying to update $this using event $event with new value $newValue.")
        return try {
            // this trick enables the whole system not to crash if there is a circular dependency
            if (event.trace.any { it.emitter == this }) {
                throw CircularBindingException(
                        "Circular binding detected with trace ${event.trace.joinToString()} for property $this. Loop was prevented.")
            } else {
                var changed = false
                atom.transform {
                    if (validator(newValue).not()) {
                        throw ValueValidationFailedException("The given value '$newValue' is invalid.")
                    }
                    if (oldValue != newValue) {
                        changed = true
                        logger.debug("Old value $oldValue of $this differs from new value $newValue, firing change event.")
                        Cobalt.eventbus.publish(
                                event = ObservableValueChanged(
                                        oldValue = oldValue,
                                        newValue = newValue,
                                        observableValue = this,
                                        emitter = this,
                                        trace = listOf(event) + event.trace),
                                eventScope = propertyScope)
                    }
                    newValue
                }
                changed
            }
        } catch (e: CircularBindingException) {
            logger.warn("Bound Property was not updated due to circular dependency", e)
            false
        }
    }

    private fun checkSelfBinding(other: ObservableValue<Any>) {
        require(this !== other) {
            "Can't bind a property to itself."
        }
    }

    // This won't lead to a deadlock in case we try to set the value twice in case of a
    // circular dependency, because we already have the monitor.
    @Synchronized
    private fun updateCurrentValue(value: T) {
        logger.debug("Updating current value of property $this to $value.")
        atom.transform { oldValue ->
            if (validator(value).not()) {
                throw ValueValidationFailedException("The given value '$value' is invalid.")
            }
            if (oldValue != value) {
                logger.debug("Old value $oldValue of $this differs from new value $value, firing change event.")
                Cobalt.eventbus.publish(
                        event = ObservableValueChanged(
                                oldValue = oldValue,
                                newValue = value,
                                observableValue = this,
                                emitter = this),
                        eventScope = propertyScope)
            }
            value
        }
    }
}


