package org.hexworks.cobalt.databinding.api.event

import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.api.Event

data class ChangeEvent<out T: Any>(val observableValue: ObservableValue<T>,
                              val oldValue: T,
                              val newValue: T) : Event
