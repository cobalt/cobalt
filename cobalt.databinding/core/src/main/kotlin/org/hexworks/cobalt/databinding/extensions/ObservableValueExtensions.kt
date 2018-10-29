package org.hexworks.cobalt.databinding.extensions

import org.hexworks.cobalt.databinding.event.ChangeEvent
import org.hexworks.cobalt.databinding.event.ChangeListener
import org.hexworks.cobalt.databinding.value.ObservableValue
import org.hexworks.cobalt.events.Subscription

inline fun <T> ObservableValue<T>.onChange(crossinline fn: (ChangeEvent<T>) -> Unit): Subscription {
    return onChange(object : ChangeListener<T> {
        override fun changed(changeEvent: ChangeEvent<T>) {
            fn(changeEvent)
        }
    })
}
