package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.event.ChangeListener
import org.hexworks.cobalt.events.Subscription

abstract class BooleanBinding : Binding<Boolean> {

    override fun onChange(listener: ChangeListener<Boolean>): Subscription {
        TODO()
    }

    override fun dispose() {
        // TODO: cancel subscriptions
    }

}
