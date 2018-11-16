package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.data.DisposeState
import org.hexworks.cobalt.databinding.api.data.NotDisposed
import org.hexworks.cobalt.databinding.api.event.ChangeListener
import org.hexworks.cobalt.databinding.api.extensions.clearSubscriptions
import org.hexworks.cobalt.databinding.api.extensions.onChange
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.internal.extensions.runWithDisposeOnFailure
import org.hexworks.cobalt.events.api.Subscription

class BidirectionalBinding<out T : Any>(private val source: Property<T>,
                                        private val target: Property<T>) : Binding<T> {

    override val value: T
        get() = source.value

    override var disposeState: DisposeState = NotDisposed
        private set

    private val listeners: MutableList<Subscription> = mutableListOf(
            source.onChange { runWithDisposeOnFailure { target.value = source.value } },
            target.onChange { runWithDisposeOnFailure { source.value = target.value } })

    override fun dispose(disposeState: DisposeState) {
        this.disposeState = disposeState
        listeners.clearSubscriptions()
    }

    override fun onChange(listener: ChangeListener<T>) = source.onChange(listener)
}
