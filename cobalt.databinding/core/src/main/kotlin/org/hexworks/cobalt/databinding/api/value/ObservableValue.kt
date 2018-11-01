package org.hexworks.cobalt.databinding.api.value

import org.hexworks.cobalt.databinding.api.event.ChangeListener
import org.hexworks.cobalt.events.Subscription

/**
 * An [ObservableValue] is an entity that wraps a value and allows to
 * observe the value for changes.
 */
interface ObservableValue<out T : Any> {

    val value: T

    fun onChange(listener: ChangeListener<T>): Subscription
}
