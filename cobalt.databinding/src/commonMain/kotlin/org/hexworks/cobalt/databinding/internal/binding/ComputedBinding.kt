package org.hexworks.cobalt.databinding.internal.binding

import org.hexworks.cobalt.databinding.api.Cobalt
import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.converter.toConverter
import org.hexworks.cobalt.databinding.api.event.ObservableValueChanged
import org.hexworks.cobalt.databinding.api.extensions.toProperty
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.databinding.internal.property.InternalProperty

/**
 * A [ComputedBinding] creates a [Binding] using an [ObservableValue]. The [Binding] will get
 * updated whenever the [ObservableValue] changes using [converter] to compute the new value of this [Binding].
 */
@Suppress("UNCHECKED_CAST")
class ComputedBinding<S : Any, T : Any>(
        source: ObservableValue<S>,
        converter: (S) -> T
) : BaseBinding<S, T>(
        source = source,
        target = converter(source.value).toProperty() as InternalProperty<T>,
        converter = converter.toConverter(),
        subscriptions = mutableListOf()) {

    init {
        subscriptions.add(source.onChange { event ->
            val oldValue = converter(event.oldValue)
            val newValue = converter(event.newValue)
            if(target.updateWithEvent(
                    oldValue = oldValue,
                    newValue = newValue,
                    event = event)) {
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
}
