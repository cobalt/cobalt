package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.data.DisposeState
import org.hexworks.cobalt.databinding.api.data.NotDisposed
import org.hexworks.cobalt.databinding.api.event.ChangeListener
import org.hexworks.cobalt.databinding.api.extensions.onChange
import org.hexworks.cobalt.databinding.api.property.Property
import org.hexworks.cobalt.databinding.api.converter.BiConverter
import org.hexworks.cobalt.databinding.internal.extensions.runWithDisposeOnFailure
import org.hexworks.cobalt.events.api.Subscription

class BidirectionalConverterBinding<out S : Any, out T : Any>(
        private val source: Property<S>,
        private val target: Property<T>,
        private val converter: BiConverter<S, T>) : Binding<S> {

    override val value: S
        get() = source.value

    override var disposeState: DisposeState = NotDisposed
        private set

    private val listeners: MutableList<Subscription> = mutableListOf(
            source.onChange {
                runWithDisposeOnFailure { target.value = converter.convertSourceToTarget(source.value) }
            },
            target.onChange {
                runWithDisposeOnFailure { source.value = converter.convertTargetToSource(target.value) }
            })

    override fun dispose(disposeState: DisposeState) {
        this.disposeState = disposeState
        listeners.forEach {
            it.cancel()
        }
        listeners.clear()
    }

    override fun onChange(listener: ChangeListener<S>) = source.onChange(listener)
}
