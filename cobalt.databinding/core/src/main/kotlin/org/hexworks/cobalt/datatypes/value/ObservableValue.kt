package org.hexworks.cobalt.datatypes.value

import org.hexworks.cobalt.datatypes.event.ChangeListener
import org.hexworks.cobalt.events.Subscription

/**
 * An [ObservableValue] is an entity that wraps a value and allows to
 * observe the value for changes.
 */
// TODO: do we need observable?
interface ObservableValue<T> {

    val value: T

    fun onChange(listener: ChangeListener<in T>): Subscription
}
