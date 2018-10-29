@file:Suppress("UNCHECKED_CAST")

package org.hexworks.cobalt.events

import org.hexworks.cobalt.events.impl.ApplicationScope

/**
 * An [EventBus] can be used to `broadcast` [Event]s to subscribers of that
 * [Event].
 */
object EventBus {

    val subscribers: List<Pair<EventScope, String>>
        get() = subscriptions.keys.toList()

    private val subscriptions = mutableMapOf<Pair<EventScope, String>, MutableList<EventBusSubscription<*>>>()

    /**
     * Subscribes the callee to [Event]s of type [T].
     */
    inline fun <reified T : Event> subscribe(eventScope: EventScope = ApplicationScope,
                                             noinline callback: (T) -> Unit): Subscription {
        val key = T::class.simpleName ?: throw IllegalArgumentException(
                "Event class doesn't have a name: ${T::class}")
        return subscribe(eventScope = eventScope,
                key = key,
                callback = callback)
    }

    /**
     * Subscribes the callee to [Event]s which have an [Event.key] equal to `key`.
     */
    fun <T : Event> subscribe(eventScope: EventScope = ApplicationScope,
                              eventPrototype: Event,
                              callback: (T) -> Unit): Subscription {
        return subscribe(eventScope = eventScope,
                key = eventPrototype.key,
                callback = callback)
    }

    /**
     * Subscribes the callee to [Event]s which have the same [Event.key]
     * as `eventPrototype`. This is best used with [Event]s which are
     * `object`s.
     */
    fun <T : Event> subscribe(eventScope: EventScope = ApplicationScope,
                              key: String,
                              callback: (T) -> Unit): Subscription {
        val subscription = EventBusSubscription(
                eventScope = eventScope,
                key = key,
                callback = callback)
        subscriptions.getOrPut(eventScope to key) { mutableListOf() }.add(subscription)
        return subscription
    }


    /**
     * Broadcasts [Event] to all listeners of this [Event]'s [Event.key].
     */
    fun broadcast(event: Event,
                  eventScope: EventScope = ApplicationScope) {
        subscriptions[eventScope to event.key]?.let { subscribers ->
            subscribers.forEach { subscription ->
                try {
                    (subscription.callback as (Event) -> Unit).invoke(event)
                } catch (e: Exception) {
                    // TODO: make more robust
                    println("Fail: ${e.message}")
                }
            }
        }
    }

    private class EventBusSubscription<in T : Event>(
            val eventScope: EventScope,
            val key: String,
            val callback: (T) -> Unit) : Subscription {

        override fun cancel() {
            subscriptions.remove(eventScope to key)
        }
    }

}
