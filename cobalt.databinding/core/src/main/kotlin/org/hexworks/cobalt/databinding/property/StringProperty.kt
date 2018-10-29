package org.hexworks.cobalt.databinding.property

import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.cobalt.databinding.event.ChangeEvent
import org.hexworks.cobalt.databinding.event.ChangeEventScope
import org.hexworks.cobalt.databinding.event.ChangeListener
import org.hexworks.cobalt.databinding.expression.StringExpression
import org.hexworks.cobalt.datatypes.extensions.fold
import org.hexworks.cobalt.datatypes.extensions.map
import org.hexworks.cobalt.databinding.extensions.onChange
import org.hexworks.cobalt.datatypes.factory.IdentifierFactory
import org.hexworks.cobalt.databinding.util.Converter
import org.hexworks.cobalt.databinding.value.ObservableValue
import org.hexworks.cobalt.events.EventBus
import org.hexworks.cobalt.events.Subscription

class StringProperty(initialValue: String) : StringExpression(), Property<String> {

    private val scope = ChangeEventScope(IdentifierFactory.randomIdentifier())

    private var currentValue = initialValue
    private var observable = Maybe.empty<Pair<ObservableValue<String>, Subscription>>()

    override var value: String = currentValue
        get() = currentValue
        set(value) {
            require(isBound().not()) {
                "Can't set the value of a bound property."
            }
            val oldValue = value
            field = value
            EventBus.broadcast(
                    event = ChangeEvent(this, oldValue, value),
                    eventScope = scope)
        }

    override fun onChange(listener: ChangeListener<String>): Subscription {
        return EventBus.subscribe<ChangeEvent<String>>(scope) {
            listener.changed(it)
        }
    }

    override fun isBound() = observable.isPresent

    override fun bind(observable: ObservableValue<String>) {
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

    override fun bindBidirectional(other: Property<String>) {
        TODO()
    }

    fun <T> bindBidirectional(other: Property<T>, converter: Converter<String, T>) {
        TODO()
    }

    override fun unbindBidirectional(other: Property<String>) {
        TODO()
    }

    private fun doBind(observable: ObservableValue<String>) {
        val subscription = observable.onChange { changeEvent ->
            currentValue = changeEvent.newValue
        }
        this.observable = Maybe.of(observable to subscription)
    }

}
