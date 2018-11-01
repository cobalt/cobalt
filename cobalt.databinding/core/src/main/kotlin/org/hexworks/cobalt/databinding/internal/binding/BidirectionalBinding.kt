package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.event.ChangeListener
import org.hexworks.cobalt.databinding.api.extensions.onChange
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.events.Subscription

class BidirectionalBinding<out T : Any>(private val source: Property<T>,
                                        private val target: Property<T>) : Binding<T> {

    override val value: T
        get() = source.value

    private val listeners: MutableList<Subscription> = mutableListOf(
            source.onChange { target.value = source.value },
            target.onChange { source.value = target.value })

    override fun dispose() {
        listeners.forEach {
            it.cancel()
        }
        listeners.clear()
    }

    override fun onChange(listener: ChangeListener<T>) = source.onChange(listener)
}
