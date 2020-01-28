package org.hexworks.cobalt.events.internal

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.hexworks.cobalt.core.internal.toAtom
import org.hexworks.cobalt.events.api.*
import org.hexworks.cobalt.logging.api.LoggerFactory
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.Volatile

class DefaultEventBus(
        override val coroutineContext: CoroutineContext = Dispatchers.Default + SupervisorJob())
    : EventBus, CoroutineScope {

    @Volatile
    private var closed = false
    @Volatile
    private var subsAtom = persistentMapOf<SubscriberKey, PersistentList<EventBusSubscription<*, Unit>>>().toAtom()
    private val logger = LoggerFactory.getLogger(this::class)

    override fun fetchSubscribersOf(eventScope: EventScope, key: String): Iterable<Subscription> {
        return subsAtom.get().getOrElse(SubscriberKey(eventScope, key)) { listOf() }
    }

    override fun <T : Event> subscribeTo(
            eventScope: EventScope,
            key: String,
            fn: (T) -> Unit
    ): Subscription = whenNotClosed {
        try {
            logger.debug("Subscribing to $key with scope $eventScope.")
            val subscription = EventBusSubscription(
                    eventScope = eventScope,
                    key = key,
                    callback = fn)
            val subKey = SubscriberKey(eventScope, key)
            subsAtom.transform { subscriptions ->
                subscriptions.put(subKey, subscriptions
                        .getOrElse(subKey) { persistentListOf() }
                        .add(subscription))
            }
            subscription
        } catch (e: Exception) {
            logger.warn("Failed to subscribe to event key $key with scope $eventScope", e)
            throw e
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun publish(
            event: Event,
            eventScope: EventScope): Unit = whenNotClosed {
        logger.debug {
            "Publishing event with key ${event.key} and scope $eventScope."
        }
        subsAtom.get()[SubscriberKey(eventScope, event.key)]?.let { subscribers ->
            subscribers.forEach { subscription ->
                try {
                    (subscription.callback as (Event) -> Unit).invoke(event)
                } catch (e: Exception) {
                    logger.warn("Cancelling failed subscription $subscription.", e)
                    try {
                        subscription.cancel(CancelledByException(e))
                    } catch (e: Exception) {
                        logger.warn("Failed to auto-cancel subscription $subscription.", e)
                    }
                }
            }
        }
    }

    override fun cancelScope(scope: EventScope): Unit = whenNotClosed {
        logger.debug("Cancelling scope $scope.")
        subsAtom.get().filter { it.key.scope == scope }
                .flatMap { it.value }
                .forEach {
                    try {
                        it.cancel()
                    } catch (e: Exception) {
                        logger.warn("Cancelling subscription failed while cancelling scope. Reason: ${e.message}")
                    }
                }
    }

    override fun close() {
        closed = true
        return subsAtom.transform { subscriptions ->
            subscriptions.forEach { (_, subs) ->
                subs.forEach { it.cancel() }
            }
            persistentMapOf()
        }
    }

    private fun <T> whenNotClosed(fn: () -> T): T {
        return if (closed) error("This Event Bus is already closed.") else fn()
    }

    private data class SubscriberKey(val scope: EventScope, val key: String)

    private inner class EventBusSubscription<in T : Event, out U : Any>(
            val eventScope: EventScope,
            val key: String,
            val callback: (T) -> U) : Subscription {

        override var cancelState: CancelState = NotCancelled
            private set


        @Suppress("UNCHECKED_CAST")
        override fun cancel(cancelState: CancelState) {
            return try {
                logger.debug {
                    "Cancelling event bus subscription with scope '$eventScope' and key '$key'."
                }
                val key = SubscriberKey(eventScope, key)
                this.cancelState = cancelState
                subsAtom.transform { subscriptions ->
                    var newSubscriptions = subscriptions

                    subscriptions[key]?.let { subs ->
                        val newSubs = subs.remove(this as EventBusSubscription<*, Unit>)
                        newSubscriptions = if (newSubs.isEmpty()) {
                            newSubscriptions.remove(key)
                        } else {
                            newSubscriptions.put(key, newSubs)
                        }
                        newSubscriptions
                    } ?: newSubscriptions
                }
            } catch (e: Exception) {
                logger.warn("Cancelling event bus subscription failed.", e)
                throw e
            }
        }
    }
}
