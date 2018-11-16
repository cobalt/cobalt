package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.data.DisposeState
import org.hexworks.cobalt.databinding.api.data.DisposedByException
import org.hexworks.cobalt.databinding.api.data.NotDisposed
import org.hexworks.cobalt.databinding.api.event.ChangeEvent
import org.hexworks.cobalt.databinding.api.event.ChangeEventScope
import org.hexworks.cobalt.databinding.api.event.ChangeListener
import org.hexworks.cobalt.databinding.api.extensions.clearSubscriptions
import org.hexworks.cobalt.databinding.api.extensions.onChange
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribe

class ComputedBiBinding<out T : Any, out U : Any, V : Any>(private val value0: ObservableValue<T>,
                                                           private val value1: ObservableValue<U>,
                                                           private val computerFn: (T, U) -> V) : Binding<V> {

    private val scope = ChangeEventScope()

    override val value: V
        get() {
            require(disposed.not()) {
                "Can't calculate the value of a Binding which is disposed"
            }
            return currentValue
        }

    private var currentValue: V = try {
        computerFn(value0.value, value1.value)
    } catch (e: Exception) {
        throw IllegalArgumentException("Can't calculate initial value for binding", e)
    }

    override var disposeState: DisposeState = NotDisposed
        private set

    private val listeners: MutableList<Subscription> = mutableListOf(
            value0.onChange { doCalculate() },
            value1.onChange { doCalculate() })

    override fun dispose(disposeState: DisposeState) {
        this.disposeState = disposeState
        Cobalt.eventbus.cancelScope(scope)
        listeners.clearSubscriptions()
    }

    override fun onChange(listener: ChangeListener<V>): Subscription {
        return Cobalt.eventbus.subscribe<ChangeEvent<V>>(scope) {
            listener.onChange(it)
        }
    }

    private fun doCalculate(): V {
        return try {
            val oldValue = currentValue
            val newValue = computerFn(value0.value, value1.value)
            if (oldValue == newValue) {
                oldValue
            } else {
                this.currentValue = newValue
                val event = ChangeEvent(this, oldValue, newValue)
                Cobalt.eventbus.publish(event, scope)
                newValue
            }
        } catch (e: Exception) {
            dispose(DisposedByException(e))
            currentValue
        }
    }
}
