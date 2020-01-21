package org.hexworks.cobalt.events.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import org.hexworks.cobalt.events.internal.ApplicationScope
import org.hexworks.cobalt.events.internal.DefaultEventBus

/**
 * An [EventBus] can be used to broadcast [Event]s to subscribers of that [Event].
 */
interface EventBus {

    /**
     * Returns all subscribers of the event with the given [key] and [eventScope].
     */
    fun fetchSubscribersOf(
            eventScope: EventScope = ApplicationScope,
            key: String
    ): Iterable<Subscription>

    /**
     * Subscribes the callee to [Event]s which have [eventScope] and [key].
     */
    fun <T : Event> subscribeTo(
            eventScope: EventScope = ApplicationScope,
            key: String,
            fn: (T) -> Unit
    ): Subscription

    /**
     * Publishes the given [Event] to all listeners who have the same
     * [eventScope] and [Event.key].
     */
    fun publish(
            event: Event,
            eventScope: EventScope = ApplicationScope)


    /**
     * Cancels all [Subscription]s for the given [scope].
     */
    fun cancelScope(scope: EventScope)

    /**
     * Cancels all subscriptions and closes this [EventBus].
     */
    fun close()

    companion object {

        /**
         * Creates a new [EventBus].
         */
        fun create(): EventBus = DefaultEventBus()
    }

}
