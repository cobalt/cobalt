package org.hexworks.cobalt.datatypes.property

import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.cobalt.datatypes.event.ChangeListener
import org.hexworks.cobalt.datatypes.expression.StringExpression
import org.hexworks.cobalt.datatypes.util.Converter
import org.hexworks.cobalt.datatypes.value.ObservableValue
import org.hexworks.cobalt.events.Subscription

class StringProperty(initialValue: String) : StringExpression(), Property<String> {

    private var currentValue = initialValue
    private var observable = Maybe.empty<ObservableValue<String>>()

    override var value: String = currentValue
        get() = currentValue
        set(value) {
            require(isBound().not()) {
                "Can't set the value of a bound property."
            }
            field = value
        }

    override fun onChange(listener: ChangeListener<in String>): Subscription {
        TODO()
    }

    override fun isBound() = observable.isPresent

    override fun bind(observable: ObservableValue<out String>) {
        TODO()
    }

    override fun unbind() {
        TODO()
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

}
