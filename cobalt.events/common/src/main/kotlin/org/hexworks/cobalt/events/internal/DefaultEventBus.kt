package org.hexworks.cobalt.events.internal

import org.hexworks.cobalt.events.api.*
import org.hexworks.cobalt.logging.api.LoggerFactory
import org.hexworks.cobalt.logging.api.warn

class DefaultEventBus : EventBus {

    override val subscribers: List<Pair<EventScope, String>>
        get() = subscriptions.keys.toList()

    private val logger = LoggerFactory.getLogger(this::class)
    private val subscriptions: ThreadSafeMap<Pair<EventScope, String>, ThreadSafeQueue<EventBusSubscription<*>>> =
            ThreadSafeMapFactory.create()

    override fun <T : Event> subscribe(eventScope: EventScope,
                                       key: String,
                                       callback: (T) -> Unit): Subscription {
        return try {
            logger.warn {
                "Subscribing to '$key' with scope '$eventScope'."
            }
            val subscription = EventBusSubscription(
                    eventScope = eventScope,
                    key = key,
                    callback = callback)

            subscriptions.getOrPut(eventScope to key) { ThreadSafeQueueFactory.create() }.add(subscription)
            subscription
        } catch (e: Exception) {
            logger.warn("Failed to add new subscription to subscriptions", e)
            throw e
        }
    }

    override fun broadcast(event: Event, eventScope: EventScope) {
        publish(event, eventScope)
    }

    @Suppress("UNCHECKED_CAST")
    override fun publish(event: Event,
                         eventScope: EventScope) {
        logger.warn {
            "Publishing Event with key '${event.key}' and scope '$eventScope'."
        }
        val failedSubscriptions = mutableListOf<Pair<Subscription, Exception>>()
        subscriptions[eventScope to event.key]?.let { subscribers ->
            subscribers.forEach { subscription ->
                try {
                    logger.warn {
                        "Trying to invoke callback for event."
                    }
                    (subscription.callback as (Event) -> Unit).invoke(event)
                } catch (e: Exception) {
                    logger.warn {
                        "Failed to invoke callback. Adding it to failed subscriptions."
                    }
                    failedSubscriptions.add(subscription to e)
                }
            }
        }
        if (failedSubscriptions.size > 0) {
            logger.warn("Cancelling failed subscriptions.")
        }
        failedSubscriptions.forEach { (subscription, e) ->
            try {
                subscription.cancel(CancelledByException(e))
            } catch (e: Exception) {
                logger.warn("Failed to auto-cancel subscription.", e)
            }
        }
    }

    override fun cancelScope(scope: EventScope) {
        logger.warn {
            "Cancelling scope '$scope'."
        }
        subscriptions.filterKeys { it.first == scope }
                .flatMap { it.value }
                .forEach {
                    try {
                        it.cancel()
                    } catch (e: Exception) {
                        logger.warn("Cancelling subscription failed: ${e.message} while cancelling scope.")
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
                logger.warn {
                    "Cancelling event bus subscription."
                }
                subscriptions.remove(eventScope to key)
                this.cancelState = cancelState
            } catch (e: Exception) {
                logger.warn("Cancelling event bus subscription failed.", e)
            }
        }
    }

}