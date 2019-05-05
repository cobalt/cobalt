package org.hexworks.cobalt.databinding.api.value

import org.hexworks.cobalt.databinding.api.event.ChangeEvent
import org.hexworks.cobalt.events.api.Subscription

/**
 * An [ObservableValue] wraps a value and allows to
 * observe the value for changes.
 */
interface ObservableValue<out T : Any> : Value<T> {

    /**
     * Starts observing this [ObservableValue] for changes.
     * If any change happens the `listener` is called.
     * **Note that** if the `value` of this [ObservableValue]
     * is **set** to its previous value the callback won't be called.
     */
    fun onChange(fn: (ChangeEvent<T>) -> Unit): Subscription
}
