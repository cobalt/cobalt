package org.hexworks.cobalt.events

/**
 * Represents a subscription to some kind of event.
 */
interface Subscription {

    /**
     * Cancels the [Subscription] to the event.
     */
    fun cancel()

}
