package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.data.DisposeState
import org.hexworks.cobalt.databinding.api.data.NotDisposed
import org.hexworks.cobalt.databinding.api.event.ChangeEventScope
import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.api.extensions.clearSubscriptions
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.api.value.WritableValue
import org.hexworks.cobalt.databinding.internal.extensions.runWithDisposeOnFailure
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribeTo
import org.hexworks.cobalt.logging.api.LoggerFactory

/**
 * Creates a **unidirectional** [Binding] between [source] and [target] which means that
 * whenever [source] gets updated [target] will be updated as well with the new value
 * but not the other way around.
 * [converter] will be used to convert the values between [source] and [target].
 */
class UnidirectionalBinding<out S : Any, out T : Any>(
        private val source: ObservableValue<S>,
        private val target: WritableValue<T>,
        private val converter: (S) -> T) : Binding<T> {

    override val value: T
        get() = target.value

    override var disposeState: DisposeState = NotDisposed
        private set

    private val logger = LoggerFactory.getLogger(this::class)
    private val selfScope = ChangeEventScope()

    override fun toString() = "UnidirectionalBinding(source=$source, target=$target)"

    private val listeners: MutableList<Subscription> = mutableListOf(
            source.onChange { changeEvent ->
                println("Receiving change event: $changeEvent in $this")
                runWithDisposeOnFailure {
                    val newValue = converter(changeEvent.newValue)
                    println("Updating bound $target from ${changeEvent.observableValue} with new value $newValue.")
                    target.value = newValue
                    Cobalt.eventbus.publish(
                            event = changeEvent,
                            eventScope = selfScope)
                }
            })

    override fun dispose(disposeState: DisposeState) {
        Cobalt.eventbus.cancelScope(selfScope)
        this.disposeState = disposeState
        listeners.clearSubscriptions()
    }

    override fun onChange(fn: (ObservableValueChanged<T>) -> Unit): Subscription {
        return Cobalt.eventbus.subscribeTo<ObservableValueChanged<T>>(selfScope) {
            fn(it)
        }
    }
}
