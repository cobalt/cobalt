package org.hexworks.cobalt.databinding.internal.property

import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.IsomorphicConverter
import org.hexworks.cobalt.databinding.api.event.ChangeEvent
import org.hexworks.cobalt.databinding.api.event.ChangeEventScope
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.BidirectionalConverterBinding
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribe

@Suppress("UNCHECKED_CAST")
class DefaultProperty<T : Any>(initialValue: T) : Property<T> {

    private val scope = ChangeEventScope()
    private val identityConverter = object : IsomorphicConverter<T, T> {

        override fun convert(source: T) = source

        override fun convertBack(target: T) = target
    }

    private var currentValue = initialValue
    private val subscriptions = mutableListOf<Subscription>()

    override var value: T
        get() = currentValue
        set(value) {
            require(isBound().not()) {
                "Can't set the value of a bound property."
            }
            updateCurrentValue(value)
        }

    override fun onChange(fn: (ChangeEvent<T>) -> Unit): Subscription {
        return Cobalt.eventbus.subscribe<ChangeEvent<T>>(scope) {
            fn(it)
        }
    }

    override fun isBound() = subscriptions.isEmpty().not()

    override fun updateFrom(observable: ObservableValue<T>): Subscription {
        return updateFrom(observable) { it }
    }

    override fun <U : Any> updateFrom(
            observable: ObservableValue<U>,
            converter: (U) -> T): Subscription {
        checkSelfBinding(observable)
        return observable.onChange {
            this.value = converter(it.newValue)
        }.also {
            // TODO: fix subscriptions
            subscriptions.add(it)
        }
    }

    override fun bind(other: Property<T>): Binding<T> {
        return bind(other, identityConverter)
    }

    override fun <U : Any> bind(other: Property<U>, converter: IsomorphicConverter<T, U>): Binding<T> {
        checkSelfBinding(other)
        this.value = converter.convertBack(other.value)
        return BidirectionalConverterBinding(this, other, converter)
    }

    private fun checkSelfBinding(other: ObservableValue<Any>) {
        require(this !== other) {
            "Can't bind a property to itself."
        }
    }

    private fun updateCurrentValue(value: T) {
        if (currentValue != value) {
            val oldValue = currentValue
            currentValue = value
            Cobalt.eventbus.publish(
                    event = ChangeEvent(this, oldValue, currentValue),
                    eventScope = scope)
        }
    }
}


