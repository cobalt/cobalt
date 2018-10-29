package org.hexworks.cobalt.databinding.event

import org.hexworks.cobalt.databinding.value.ObservableValue
import org.hexworks.cobalt.events.Event

data class ChangeEvent<out T>(val observableValue: ObservableValue<T>,
                              val oldValue: T,
                              val newValue: T) : Event
