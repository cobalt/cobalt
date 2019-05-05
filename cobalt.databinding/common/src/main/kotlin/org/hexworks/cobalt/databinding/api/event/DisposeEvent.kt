package org.hexworks.cobalt.databinding.api.event

import org.hexworks.cobalt.databinding.api.binding.Binding
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Event

/**
 * Contains the information about the event when an [ObservableValue] changes.
 */
data class DisposeEvent<out T : Any>(val observableValue: Binding<T>,
                                     val oldValue: T,
                                     val newValue: T) : Event
