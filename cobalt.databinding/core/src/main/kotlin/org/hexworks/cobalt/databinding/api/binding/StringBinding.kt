package org.hexworks.cobalt.databinding.api.binding

import org.hexworks.cobalt.databinding.api.event.ChangeListener
import org.hexworks.cobalt.events.Subscription

abstract class StringBinding : Binding<String> {

    override fun onChange(listener: ChangeListener<String>): Subscription {
        TODO()
    }

    override fun dispose() {
        // TODO: cancel subscriptions
    }

}
