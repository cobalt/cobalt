package org.hexworks.cobalt.events.api

import org.hexworks.cobalt.events.internal.ApplicationScope
import org.hexworks.cobalt.events.internal.DefaultEventBus

/**
 * An [EventBus] can be used to `broadcast` [Event]s to subscribers of that
 * [Event].
 */
interface EventBus {

    fun subscribersFor(eventScope: EventScope, key: String): Collection<Subscription>

    /**
     * Subscribes the callee to [Event]s which have the same [Event.key]
     * as `key`.
     */
    fun <T : Event> subscribe(eventScope: EventScope = ApplicationScope,
                              key: String,
                              callback: (T) -> Unit): Subscription

    /**
     * Publishes the given [Event] to all listeners who have the same
     * [EventScope] and [Event.key].
     */
    fun publish(event: Event,
                eventScope: EventScope = ApplicationScope)

    /**
     * Publishes the given [Event] to all listeners who have the same
     * [EventScope] and [Event.key].
     */
    @Deprecated(
            replaceWith = ReplaceWith("publish(event, eventScope)"),
            message = "Inconsistent with publish/subscribe. Use publish instead.")
    fun broadcast(event: Event,
                  eventScope: EventScope = ApplicationScope)

    /**
     * Cancels all [Subscription]s for a given [EventScope].
     */
    fun cancelScope(scope: EventScope)

    companion object {

        /**
         * Creates a new [EventBus].
         */
        fun create(): EventBus = DefaultEventBus()
    }

}
