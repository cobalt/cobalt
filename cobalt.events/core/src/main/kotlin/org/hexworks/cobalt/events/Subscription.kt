package org.hexworks.cobalt.events

/**
 * Represents a subscription to some kind of event.
 */
interface Subscription {

    val cancelled: Boolean
        get() = cancelState.cancelled

    val cancelState: CancelState

    /**
     * Cancels the [Subscription] to the event.
     */
    fun cancel(cancelState: CancelState = CancelledByHand)

}
