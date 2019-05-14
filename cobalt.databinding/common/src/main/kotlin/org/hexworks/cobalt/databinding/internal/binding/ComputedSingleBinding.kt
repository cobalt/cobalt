package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.data.DisposeState
import org.hexworks.cobalt.databinding.api.data.DisposedByException
import org.hexworks.cobalt.databinding.api.data.NotDisposed
import org.hexworks.cobalt.databinding.api.event.ChangeEvent
import org.hexworks.cobalt.databinding.api.event.ChangeEventScope
import org.hexworks.cobalt.databinding.api.extensions.clearSubscriptions
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribe

/**
 * A [ComputedSingleBinding] creates a [Binding] using **one** [ObservableValue] which will get
 * updated whenever the [ObservableValue] gets updated using [computerFn] to compute the new value
 * of this [Binding].
 */
class ComputedSingleBinding<out T : Any, V : Any>(private val value0: ObservableValue<T>,
                                                  private val computerFn: (T) -> V) : Binding<V> {

    private val scope = ChangeEventScope()

    override val value: V
        get() {
            require(disposed.not()) {
                "Can't calculate the value of a Binding which is disposed"
            }
            return currentValue
        }

    private var currentValue: V = try {
        computerFn(value0.value)
    } catch (e: Exception) {
        throw IllegalArgumentException("Can't calculate initial value for binding", e)
    }

    override var disposeState: DisposeState = NotDisposed
        private set

    private val listeners: MutableList<Subscription> = mutableListOf(
            value0.onChange { doCalculate() })

    override fun dispose(disposeState: DisposeState) {
        this.disposeState = disposeState
        Cobalt.eventbus.cancelScope(scope)
        listeners.clearSubscriptions()
    }

    override fun onChange(fn: (ChangeEvent<V>) -> Unit): Subscription {
        return Cobalt.eventbus.subscribe<ChangeEvent<V>>(scope) {
            fn(it)
        }
    }

    private fun doCalculate(): V {
        return try {
            val oldValue = currentValue
            val newValue = computerFn(value0.value)
            if (oldValue == newValue) {
                oldValue
            } else {
                this.currentValue = newValue
                val event = ChangeEvent(this)
                Cobalt.eventbus.publish(event, scope)
                newValue
            }
        } catch (e: Exception) {
            dispose(DisposedByException(e))
            currentValue
        }
    }
}
