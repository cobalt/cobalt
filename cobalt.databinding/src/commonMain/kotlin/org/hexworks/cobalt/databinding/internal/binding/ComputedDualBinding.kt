package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.core.api.Identifier
import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.data.DisposeState
import org.hexworks.cobalt.databinding.api.data.NotDisposed
import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.internal.event.PropertyScope
import org.hexworks.cobalt.databinding.api.extensions.clearSubscriptions
import org.hexworks.cobalt.databinding.api.extensions.toInternalProperty
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribeTo

/**
 * A [ComputedDualBinding] creates a [Binding] using **two** [ObservableValue]s which will get
 * updated whenever any of those values get updated using [computerFn] to compute the new value
 * of this [Binding].
 */
class ComputedDualBinding<out S0 : Any, out S1 : Any, T : Any>(
        private val source0: ObservableValue<S0>,
        private val source1: ObservableValue<S1>,
        private val computerFn: (S0, S1) -> T
) : Binding<T> {

    private val target = computerFn(source0.value, source1.value).toInternalProperty()

    override val value: T
        get() {
            require(disposed.not()) {
                "Can't calculate the value of a Binding which is disposed."
            }
            return target.value
        }

    override var disposeState: DisposeState = NotDisposed
        internal set

    private val id = Identifier.randomIdentifier()
    private val propertyScope = PropertyScope(id)
    private val subscriptions = mutableListOf<Subscription>()

    init {
        subscriptions.add(source0.onChange { event ->
            val oldValue = computerFn(event.oldValue, source1.value)
            val newValue = computerFn(event.newValue, source1.value)
            if (oldValue != newValue) {
                target.value = newValue
                Cobalt.eventbus.publish(
                        event = ObservableValueChanged(
                                oldValue = oldValue,
                                newValue = newValue,
                                observableValue = this,
                                emitter = this,
                                trace = listOf(event) + event.trace),
                        eventScope = propertyScope)
            }
        })
        subscriptions.add(source1.onChange { event ->
            val oldValue = computerFn(source0.value, event.oldValue)
            val newValue = computerFn(source0.value, event.newValue)
            if (oldValue != newValue) {
                target.value = newValue
                Cobalt.eventbus.publish(
                        event = ObservableValueChanged(
                                oldValue = oldValue,
                                newValue = newValue,
                                observableValue = this,
                                emitter = this,
                                trace = listOf(event) + event.trace),
                        eventScope = propertyScope)
            }
        })
    }

    override fun dispose(disposeState: DisposeState) {
        this.disposeState = disposeState
        Cobalt.eventbus.cancelScope(propertyScope)
        subscriptions.clearSubscriptions()
    }

    override fun onChange(fn: (ObservableValueChanged<T>) -> Unit): Subscription {
        return Cobalt.eventbus.subscribeTo<ObservableValueChanged<T>>(propertyScope) {
            fn(it)
        }
    }
}
