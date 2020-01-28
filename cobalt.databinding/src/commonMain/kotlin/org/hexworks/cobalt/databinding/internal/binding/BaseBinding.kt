package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.core.api.Identifier
import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.Converter
import org.hexworks.cobalt.databinding.api.data.DisposeState
import org.hexworks.cobalt.databinding.api.data.NotDisposed
import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.internal.event.PropertyScope
import org.hexworks.cobalt.databinding.api.extensions.clearSubscriptions
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.property.InternalProperty
import org.hexworks.cobalt.events.api.Subscription
import org.hexworks.cobalt.events.api.subscribeTo
import org.hexworks.cobalt.logging.api.LoggerFactory

abstract class BaseBinding<S : Any, T : Any>(
        internal val source: ObservableValue<S>,
        internal val target: InternalProperty<T>,
        internal val converter: Converter<S, T>,
        internal val subscriptions: MutableList<Subscription>
) : Binding<T> {

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

    internal val logger = LoggerFactory.getLogger(this::class)
    internal val propertyScope = PropertyScope(id)


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