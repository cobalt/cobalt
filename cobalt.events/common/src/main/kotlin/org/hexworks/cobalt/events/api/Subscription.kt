package org.hexworks.cobalt.events.api

import org.hexworks.cobalt.events.api.CancelState
import org.hexworks.cobalt.events.api.CancelledByHand

/**
 * Represents a subscription to some kind of event.
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
