package org.hexworks.cobalt.databinding.binding

import org.hexworks.cobalt.databinding.event.ChangeListener
import org.hexworks.cobalt.databinding.expression.StringExpression
import org.hexworks.cobalt.events.Subscription

abstract class StringBinding : StringExpression(), Binding<String> {

    override fun onChange(listener: ChangeListener<in String>): Subscription {
        TODO()
    }

    override fun dispose() {
        // TODO: cancel subscriptions
    }

}
