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

class DefaultEventBus(
        override val coroutineContext: CoroutineContext = Dispatchers.Default + SupervisorJob())
    : EventBus, CoroutineScope {

    private var closed = false
    private val logger = LoggerFactory.getLogger(this::class)
    private var subsRef = persistentMapOf<SubscriberKey, PersistentList<EventBusSubscription<*, Unit>>>().toAtom()

    override fun fetchSubscribersOf(eventScope: EventScope, key: String): Iterable<Subscription> {
        checkClosed()
        return subsRef.get().getOrElse(SubscriberKey(eventScope, key)) { listOf() }
    }

    override fun <T : Event> subscribeTo(
            eventScope: EventScope,
            key: String,
            fn: (T) -> Unit
    ): Subscription {
        checkClosed()
        return try {
            logger.debug {
                "Subscribing to '$key' with scope '$eventScope'."
            }
            val subscription = EventBusSubscription(
                    eventScope = eventScope,
                    key = key,
                    callback = fn)
            val subKey = SubscriberKey(eventScope, key)
            subsRef.transform { subscriptions ->
                subscriptions.put(subKey, subscriptions
                        .getOrElse(subKey) { persistentListOf() }
                        .add(subscription))
            }
            subscription
        } catch (e: Exception) {
            logger.warn("Failed to add new subscription to subscriptions", e)
            throw e
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun publish(
            event: Event,
            eventScope: EventScope) {
        checkClosed()
        logger.debug {
            "Publishing Event with key '${event.key}' and scope '$eventScope'."
        }
        val subKey = SubscriberKey(eventScope, event.key)
        subsRef.get()[subKey]?.let { subscribers ->
            subscribers.forEach { subscription ->
                try {
                    logger.debug {
                        "Trying to invoke callback for event."
                    }
                    (subscription.callback as (Event) -> Unit).invoke(event)
                } catch (e: Exception) {
                    logger.warn("Cancelling failed subscription '$subscription'.", e)
                    try {
                        subscription.cancel(CancelledByException(e))
                    } catch (e: Exception) {
                        logger.warn("Failed to auto-cancel subscription.", e)
                    }
                }
            }
        }
    }

    override fun cancelScope(scope: EventScope) {
        checkClosed()
        logger.debug {
            "Cancelling scope '$scope'."
        }
        subsRef.get().filter { it.key.scope == scope }
                .flatMap { it.value }
                .forEach {
                    try {
                        it.cancel()
                    } catch (e: Exception) {
                        logger.warn("Cancelling subscription failed: ${e.message} while cancelling scope.")
                    }
                }
    }

    override fun close() {
        closed = true
        return subsRef.transform { subscriptions ->
            subscriptions.forEach { (_, subs) ->
                subs.forEach { it.cancel() }
            }
            persistentMapOf()
        }
    }

    private fun checkClosed() {
        if (closed) {
            throw IllegalStateException("This EventBus is already closed.")
        }
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
                subsRef.transform { subscriptions ->
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
