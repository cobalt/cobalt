package org.hexworks.cobalt.databinding.api.extensions

import org.hexworks.cobalt.databinding.api.event.ChangeEvent
import org.hexworks.cobalt.databinding.api.event.ChangeListener
import org.hexworks.cobalt.databinding.api.value.ObservableValue
import org.hexworks.cobalt.events.Subscription

inline fun <T> ObservableValue<T>.onChange(crossinline fn: (ChangeEvent<T>) -> Unit): Subscription {
    return onChange(object : ChangeListener<T> {
        override fun changed(changeEvent: ChangeEvent<T>) {
            fn(changeEvent)
        }
    })
}
