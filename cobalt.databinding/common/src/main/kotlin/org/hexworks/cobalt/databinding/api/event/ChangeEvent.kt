package org.hexworks.cobalt.databinding.api.event

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Event

/**
 * Contains the information about the event when an [ObservableValue] changes.
 * **Note that** if no **change** happens ([oldValue] == [newValue]) no
 * [ChangeEvent] is fired.
 */
data class ChangeEvent<out T : Any>(val observableValue: ObservableValue<T>,
                                    val oldValue: T,
                                    val newValue: T) : Event
