package org.hexworks.cobalt.databinding.api.event

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Event

/**
 * Fired when an [ObservableValue] changes.
 * **Note that** if no **change** happens no [ChangeEvent] is fired.
 */
data class ChangeEvent<out T : Any>(val observableValue: ObservableValue<T>) : Event
