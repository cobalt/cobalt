package org.hexworks.cobalt.databinding.internal.property

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.event.ChangeEvent
import org.hexworks.cobalt.databinding.api.event.ChangeEventScope
import org.hexworks.cobalt.databinding.api.event.ChangeListener
import org.hexworks.cobalt.databinding.api.extensions.onChange
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.util.Converter
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.BidirectionalBinding
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.cobalt.datatypes.extensions.fold
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.cobalt.datatypes.factory.IdentifierFactory
import org.hexworks.cobalt.events.EventBus
import org.hexworks.cobalt.events.Subscription

class DefaultProperty<T : Any>(initialValue: T) : Property<T> {
    private val scope = ChangeEventScope(IdentifierFactory.randomIdentifier())

    private var currentValue = initialValue
    private var observable = Maybe.empty<Pair<ObservableValue<T>, Subscription>>()

    override var value: T
        get() = currentValue
        set(value) {
            require(isBound().not()) {
                "Can't set the value of a bound property."
            }
            updateCurrentValue(value)
        }

    override fun onChange(listener: ChangeListener<T>): Subscription {
        return EventBus.subscribe<ChangeEvent<T>>(scope) {
            listener.changed(it)
        }
    }

    override fun isBound() = observable.isPresent

    override fun bind(observable: ObservableValue<T>) {
        this.observable.fold(whenEmpty = {
            if (observable !== this) {
                doBind(observable)
            }
        }, whenPresent = { oldValue ->
            if (oldValue !== observable) {
                doBind(observable)
            }
        })
    }

    override fun unbind() {
        observable.map { (oldObservable, subscription) ->
            currentValue = oldObservable.value
            subscription.cancel()
            observable = Maybe.empty()
        }
    }

    override fun bindBidirectional(other: Property<T>): Binding<T> {
        require(this !== other) {
            "Can't bind a property to itself."
        }
        // TODO: transaction
        this.value = other.value
        return BidirectionalBinding(this, other)
    }

    fun <U : Any> bindBidirectional(other: Property<U>, converter: Converter<T, U>) {
        TODO()
    }

    private fun doBind(observable: ObservableValue<T>) {
        val subscription = observable.onChange { changeEvent ->
            updateCurrentValue(changeEvent.newValue)
        }
        updateCurrentValue(observable.value)
        this.observable = Maybe.of(observable to subscription)
    }

    private fun updateCurrentValue(value: T) {
        if (currentValue != value) {
            val oldValue = currentValue
            currentValue = value
            EventBus.broadcast(
                    event = ChangeEvent(this, oldValue, currentValue),
                    eventScope = scope)
        }
    }
}
