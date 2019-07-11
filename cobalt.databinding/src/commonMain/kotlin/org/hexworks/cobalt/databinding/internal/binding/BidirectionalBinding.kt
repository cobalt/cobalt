package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.IsomorphicConverter
import org.hexworks.cobalt.databinding.api.data.DisposeState
import org.hexworks.cobalt.databinding.api.data.NotDisposed
import org.hexworks.cobalt.databinding.api.event.ChangeEvent
import org.hexworks.cobalt.databinding.api.event.ChangeEventScope
import org.hexworks.cobalt.databinding.api.extensions.clearSubscriptions
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.internal.extensions.runWithDisposeOnFailure
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribe

/**
 * Creates a **bidirectional** [Binding] between [source] and [target] which means that
 * whenever [source] gets updated [target] will be updated as well with the new value
 * and vica versa.
 * [converter] will be used to convert the values between [source] and [target].
 */
class BidirectionalBinding<out S : Any, out T : Any>(
        private val source: Property<S>,
        private val target: Property<T>,
        private val converter: IsomorphicConverter<S, T>) : Binding<S> {

    override val value: S
        get() = source.value

    override var disposeState: DisposeState = NotDisposed
        private set

    private val scope = ChangeEventScope()

    private val listeners: MutableList<Subscription> = mutableListOf(
            source.onChange { changeEvent ->
                runWithDisposeOnFailure {
                    target.value = converter.convert(source.value)
                    Cobalt.eventbus.publish(
                            event = changeEvent,
                            eventScope = scope)
                }
            },
            target.onChange { changeEvent ->
                runWithDisposeOnFailure {
                    source.value = converter.convertBack(target.value)
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
