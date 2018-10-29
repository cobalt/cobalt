package org.hexworks.cobalt.datatypes.binding

import org.hexworks.cobalt.datatypes.event.ChangeListener
import org.hexworks.cobalt.datatypes.expression.StringExpression
import org.hexworks.cobalt.events.Subscription

abstract class StringBinding : StringExpression(), Binding<String> {

    override fun onChange(listener: ChangeListener<in String>): Subscription {
        TODO()
    }

    override fun dispose() {
        // TODO: cancel subscriptions
    }

}
