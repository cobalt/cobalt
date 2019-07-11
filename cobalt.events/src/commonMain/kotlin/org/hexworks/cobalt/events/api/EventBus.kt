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
     * Sends the given [Event] to a listener which has the same
     * [EventScope] and [Event.key] and consumes the result
     * using the given `callback`.
     * @see SubmitResult
     */
    fun <T : Event, U : Any> send(event: T,
                                  eventScope: EventScope = ApplicationScope,
                                  callback: (SubmitResult<U>) -> Unit)

    /**
     * Subscribes the callee to [Event]s which have the same [EventScope] and [Event.key]
     * with the given `processorFn`. When an event of type [T] arrives to this
     * [EventBus] `processorFn` will be called and its result will be routed to the
     * sender of the event.
     */
    fun <T : Event, U : Any> process(eventScope: EventScope = ApplicationScope,
                                     key: String,
                                     processorFn: (T) -> U): Subscription

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
