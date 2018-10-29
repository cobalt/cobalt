package org.hexworks.cobalt.datatypes.binding

import org.hexworks.cobalt.datatypes.event.ChangeListener
import org.hexworks.cobalt.datatypes.expression.BooleanExpression
import org.hexworks.cobalt.events.Subscription

abstract class BooleanBinding : BooleanExpression(), Binding<Boolean> {

    override fun onChange(listener: ChangeListener<in Boolean>): Subscription {
        TODO()
    }

    override fun dispose() {
        // TODO: cancel subscriptions
    }

}
