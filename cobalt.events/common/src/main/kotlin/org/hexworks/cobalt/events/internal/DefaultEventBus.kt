package org.hexworks.cobalt.events.internal

import org.hexworks.cobalt.events.api.*

class DefaultEventBus : EventBus {

    override val subscribers: List<Pair<EventScope, String>>
        get() = subscriptions.keys.toList()

    private val subscriptions = mutableMapOf<Pair<EventScope, String>, MutableList<EventBusSubscription<*>>>()

    override fun <T : Event> subscribe(eventScope: EventScope,
                                       key: String,
                                       callback: (T) -> Unit): Subscription {
        val subscription = EventBusSubscription(
                eventScope = eventScope,
                key = key,
                callback = callback)
        subscriptions.getOrPut(eventScope to key) { mutableListOf() }.add(subscription)
        return subscription
    }

    override fun broadcast(event: Event, eventScope: EventScope) {
        publish(event, eventScope)
    }

    @Suppress("UNCHECKED_CAST")
    override fun publish(event: Event,
                         eventScope: EventScope) {
        val failedSubscriptions = mutableListOf<Pair<Subscription, Exception>>()
        subscriptions[eventScope to event.key]?.let { subscribers ->
            subscribers.forEach { subscription ->
                try {
                    (subscription.callback as (Event) -> Unit).invoke(event)
                } catch (e: Exception) {
                    failedSubscriptions.add(subscription to e)
                }
            }
        }
        failedSubscriptions.forEach { (subscription, e) ->
            subscription.cancel(CancelledByException(e))
        }
    }

    override fun cancelScope(scope: EventScope) {
        subscriptions.filterKeys { it.first == scope }
                .flatMap { it.value }
                .forEach {
                    try {
                        it.cancel()
                    } catch (e: Exception) {
                        println("Cancelling subscription failed: ${e.message}.")
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
            try {
                subscriptions.remove(eventScope to key)
                this.cancelState = cancelState
            } catch (e: Exception) {
                println("Cancelling subscription failed: ${e.message}.")
            }
        }
    }

}
