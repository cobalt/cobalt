package org.hexworks.cobalt.databinding.api.event

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Event

/**
 * Fired when an [ObservableValue] **changes** its value.
 */
data class ObservableValueChanged<out T : Any>(
        val oldValue: T,
        val newValue: T,
        val observableValue: ObservableValue<T>,
        override val emitter: Any,
        override val trace: Iterable<Event> = listOf()
): Event {

    companion object
}
