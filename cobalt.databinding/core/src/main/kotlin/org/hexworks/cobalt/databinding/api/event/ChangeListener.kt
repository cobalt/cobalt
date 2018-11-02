package org.hexworks.cobalt.databinding.api.event

/**
 * Listener which gets notified when [ChangeEvent]s
 * happen.
 */
interface ChangeListener<in T : Any> {

    /**
     * Will be called when a [ChangeEvent] happens.
     */
    fun onChange(changeEvent: ChangeEvent<T>)
}
