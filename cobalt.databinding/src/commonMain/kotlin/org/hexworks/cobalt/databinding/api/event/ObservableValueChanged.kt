package org.hexworks.cobalt.databinding.api.event

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Event

/**
 * Fired when an [ObservableValue] **changes** its value.
 */
data class ObservableValueChanged<out T : Any>(
        val observableValue: ObservableValue<T>,
        val oldValue: T,
        val newValue: T) : Event
