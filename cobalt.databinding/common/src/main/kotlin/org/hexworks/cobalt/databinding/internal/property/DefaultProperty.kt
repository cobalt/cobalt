package org.hexworks.cobalt.databinding.internal.property

import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.BiConverter
import org.hexworks.cobalt.databinding.api.converter.Converter
import org.hexworks.cobalt.databinding.api.converter.IdentityConverter
import org.hexworks.cobalt.databinding.api.event.ChangeEvent
import org.hexworks.cobalt.databinding.api.event.ChangeEventScope
import org.hexworks.cobalt.databinding.api.event.ChangeListener
import org.hexworks.cobalt.databinding.api.extensions.onChange
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.binding.BidirectionalBinding
import org.hexworks.cobalt.databinding.internal.binding.BidirectionalConverterBinding
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.cobalt.datatypes.extensions.fold
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribe

@Suppress("UNCHECKED_CAST")
class DefaultProperty<T : Any>(initialValue: T) : Property<T> {

    private val scope = ChangeEventScope()
    private val identityConverter = IdentityConverter<T>()

    private var currentValue = initialValue
    private var bindingSubscription = Maybe.empty<Subscription>()

    override var value: T
        get() = currentValue
        set(value) {
            require(isBound().not()) {
                "Can't set the value of a bound property."
            }
            updateCurrentValue(value)
        }

    override fun onChange(listener: ChangeListener<T>): Subscription {
        return Cobalt.eventbus.subscribe<ChangeEvent<T>>(scope) {
            listener.onChange(it)
        }
    }

    override fun isBound() = bindingSubscription.isPresent

    override fun bind(observable: ObservableValue<T>) {
        bind(observable, identityConverter)
    }

    override fun <U : Any> bind(observable: ObservableValue<U>, converter: Converter<U, T>) {
        this.bindingSubscription.fold(whenEmpty = {
            if (observable !== this) {
                doBind(observable, converter)
            }
        }, whenPresent = { oldValue ->
            if (oldValue !== observable) {
                doBind(observable, converter)
            }
        })
    }

    override fun unbind() {
        bindingSubscription.map { subscription ->
            subscription.cancel()
            bindingSubscription = Maybe.empty()
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

    override fun <U : Any> bindBidirectional(other: Property<U>, converter: BiConverter<T, U>): Binding<T> {
        require(this !== other) {
            "Can't bind a property to itself."
        }
        // TODO: transaction
        this.value = converter.convertTargetToSource(other.value)
        return BidirectionalConverterBinding(this, other, converter)
    }

    private fun <U : Any> doBind(observable: ObservableValue<U>, converter: Converter<U, T>) {
        val subscription = observable.onChange { changeEvent ->
            updateCurrentValue(converter.convert(changeEvent.newValue))
        }
        updateCurrentValue(converter.convert(observable.value))
        this.bindingSubscription = Maybe.of(subscription)
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


