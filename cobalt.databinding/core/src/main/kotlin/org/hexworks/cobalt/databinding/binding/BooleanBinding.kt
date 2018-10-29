package org.hexworks.cobalt.databinding.binding

import org.hexworks.cobalt.databinding.event.ChangeListener
import org.hexworks.cobalt.databinding.expression.BooleanExpression
import org.hexworks.cobalt.events.Subscription

abstract class BooleanBinding : BooleanExpression(), Binding<Boolean> {

    override fun onChange(listener: ChangeListener<in Boolean>): Subscription {
        TODO()
    }

    override fun dispose() {
        // TODO: cancel subscriptions
    }

}
