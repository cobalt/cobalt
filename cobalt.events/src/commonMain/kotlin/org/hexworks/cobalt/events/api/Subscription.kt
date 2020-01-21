package org.hexworks.cobalt.events.api

/**
 * Represents a subscription to an event.
 */
interface Subscription {

    /**
     * Tells whether this [Subscription] was cancelled or not.
     * Shorthand for [CancelState.cancelled].
     */
    val cancelled: Boolean
        get() = cancelState.cancelled

    val cancelState: CancelState

    /**
     * Cancels the [Subscription] to the event.
     */
    fun cancel(cancelState: CancelState = CancelledByHand)

}
