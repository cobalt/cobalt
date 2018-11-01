package org.hexworks.cobalt.events

import org.hexworks.cobalt.events.impl.ApplicationScope

/**
 * An [EventBus] can be used to `broadcast` [Event]s to subscribers of that
 * [Event].
 */
class EventBus {

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
    @Suppress("UNCHECKED_CAST")
    fun broadcast(event: Event,
                  eventScope: EventScope = ApplicationScope) {
        subscriptions[eventScope to event.key]?.let { subscribers ->
            subscribers.forEach { subscription ->
                try {
                    (subscription.callback as (Event) -> Unit).invoke(event)
                } catch (e: Exception) {
                    subscription.cancel(CancelledByException(e))
                }
            }
        }
    }

    private inner class EventBusSubscription<in T : Event>(
            val eventScope: EventScope,
            val key: String,
            val callback: (T) -> Unit) : Subscription {

        override var cancelState: CancelState = NotCancelled
            private set


        override fun cancel(cancelState: CancelState) {
            this.cancelState = cancelState
            subscriptions.remove(eventScope to key)
        }
    }

}
