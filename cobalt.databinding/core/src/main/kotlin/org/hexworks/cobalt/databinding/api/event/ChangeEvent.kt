package org.hexworks.cobalt.databinding.api.event

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.Event

data class ChangeEvent<out T>(val observableValue: ObservableValue<T>,
                              val oldValue: T,
                              val newValue: T) : Event
