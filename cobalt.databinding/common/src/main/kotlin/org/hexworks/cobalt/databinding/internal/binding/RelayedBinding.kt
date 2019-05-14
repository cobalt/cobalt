package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.data.DisposeState
import org.hexworks.cobalt.databinding.api.data.NotDisposed
import org.hexworks.cobalt.databinding.api.event.ChangeEvent
import org.hexworks.cobalt.databinding.api.event.ChangeEventScope
import org.hexworks.cobalt.databinding.api.extensions.clearSubscriptions
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.extensions.runWithDisposeOnFailure
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribe

/**
 * Creates a [Binding] which only relays change events but has
 * no other functionality.
 */
class RelayedBinding<out S : Any>(
        private val source: ObservableValue<S>) : Binding<S> {

    override val value: S
        get() = source.value

    override var disposeState: DisposeState = NotDisposed
        private set

    private val scope = ChangeEventScope()

    private val listeners: MutableList<Subscription> = mutableListOf(
            source.onChange { changeEvent ->
                runWithDisposeOnFailure {
                    Cobalt.eventbus.publish(
                            event = changeEvent,
                            eventScope = scope)
                }
            })

    override fun dispose(disposeState: DisposeState) {
        Cobalt.eventbus.cancelScope(scope)
        this.disposeState = disposeState
        listeners.clearSubscriptions()
    }

    override fun onChange(fn: (ChangeEvent<S>) -> Unit): Subscription {
        return Cobalt.eventbus.subscribe<ChangeEvent<S>>(scope) {
            fn(it)
        }
    }
}
