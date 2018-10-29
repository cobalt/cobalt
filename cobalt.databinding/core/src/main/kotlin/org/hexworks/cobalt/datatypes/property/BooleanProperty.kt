package org.hexworks.cobalt.datatypes.property

import org.hexworks.cobalt.datatypes.event.ChangeListener
import org.hexworks.cobalt.datatypes.expression.BooleanExpression
import org.hexworks.cobalt.datatypes.util.Converter
import org.hexworks.cobalt.datatypes.value.ObservableValue
import org.hexworks.cobalt.events.Subscription

class BooleanProperty(initialValue: Boolean) : BooleanExpression(), Property<Boolean> {

    private var value: Boolean = initialValue

    override fun getValue(): Boolean {
        return value
    }

    override fun setValue(value: Boolean) {
        require(isBound().not()) {
            "Can't set the value of a bound property."
        }
        this.value = value
    }

    override fun onChange(listener: ChangeListener<in Boolean>): Subscription {
        TODO()
    }

    override fun isBound(): Boolean {
        TODO()
    }

    override fun bind(observable: ObservableValue<out Boolean>) {
        TODO()
    }

    override fun unbind() {
        TODO()
    }

    override fun bindBidirectional(other: Property<Boolean>) {
        TODO()
    }

    fun <T> bindBidirectional(other: Property<T>, converter: Converter<Boolean, T>) {
        TODO()
    }

    override fun unbindBidirectional(other: Property<Boolean>) {
        TODO()
    }

}
