package org.hexworks.cobalt.events.internal

import org.hexworks.cobalt.events.api.*
import org.hexworks.cobalt.logging.api.LoggerFactory
import org.hexworks.cobalt.logging.api.debug

class DefaultEventBus : EventBus {

    private val logger = LoggerFactory.getLogger(this::class)
    private val subscriptions: ThreadSafeMap<SubscriberKey, ThreadSafeQueue<EventBusSubscription<*, Unit>>> =
            ThreadSafeMapFactory.create()

    private val processors: ThreadSafeMap<ProcessorKey, EventBusSubscription<*, *>> =
            ThreadSafeMapFactory.create()

    override fun subscribersFor(eventScope: EventScope, key: String): Collection<Subscription> {
        return subscriptions.getOrDefault(SubscriberKey(eventScope, key), ThreadSafeQueueFactory.create())
    }

    override fun <T : Event> subscribe(eventScope: EventScope,
                                       key: String,
                                       callback: (T) -> Unit): Subscription {
        return try {
            logger.debug {
                "Subscribing to '$key' with scope '$eventScope'."
            }
            val subscription = EventBusSubscription(
                    eventScope = eventScope,
                    key = key,
                    callback = callback)

            subscriptions.getOrPut(SubscriberKey(eventScope, key)) { ThreadSafeQueueFactory.create() }.add(subscription)
            subscription
        } catch (e: Exception) {
            logger.warn("Failed to add new subscription to subscriptions", e)
            throw e
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun publish(event: Event,
                         eventScope: EventScope) {
        logger.debug {
            "Publishing Event with key '${event.key}' and scope '$eventScope'."
        }
        val failedSubscriptions = mutableListOf<Pair<Subscription, Exception>>()
        subscriptions[SubscriberKey(eventScope, event.key)]?.let { subscribers ->
            subscribers.forEach { subscription ->
                try {
                    logger.debug {
                        "Trying to invoke callback for event."
                    }
                    (subscription.callback as (Event) -> Unit).invoke(event)
                } catch (e: Exception) {
                    failedSubscriptions.add(subscription to e)
                }
            }
        }
        failedSubscriptions.forEach { (subscription, e) ->
            logger.warn("Cancelling failed subscription.", e)
            try {
                subscription.cancel(CancelledByException(e))
            } catch (e: Exception) {
                logger.warn("Failed to auto-cancel subscription.", e)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Event, U : Any> send(event: T, eventScope: EventScope, callback: (SubmitResult<U>) -> Unit) {
        processors[ProcessorKey(eventScope, event.key)]?.let { processor ->
            val processorCallback: (T) -> U = processor.callback as (T) -> U
            try {
                callback(SubmitResultValue(processorCallback(event)))
            } catch (e: Exception) {
                callback(SubmitResultError(e))
            }
        } ?: callback(NoProcessorSubmitResult)
    }

    override fun <T : Event, U : Any> process(eventScope: EventScope, key: String, processorFn: (T) -> U): Subscription {
        return try {
            logger.debug {
                "Subscribing to '$key' with scope '$eventScope'."
            }
            val subscription = EventBusSubscription(
                    eventScope = eventScope,
                    key = key,
                    callback = processorFn)

            processors.put(ProcessorKey(eventScope, key), subscription)?.cancel(CancelledByException(
                    exception = IllegalStateException("Processor got overwritten with a new one for the same type.")))
            subscription
        } catch (e: Exception) {
            logger.warn("Failed to add new subscription to subscriptions", e)
            throw e
        }
    }

    override fun cancelScope(scope: EventScope) {
        logger.debug {
            "Cancelling scope '$scope'."
        }
        subscriptions.filterKeys { it.scope == scope }
                .flatMap { it.value }
                .forEach {
                    try {
                        it.cancel()
                    } catch (e: Exception) {
                        logger.warn("Cancelling subscription failed: ${e.message} while cancelling scope.")
                    }
                }
    }

    private data class SubscriberKey(val scope: EventScope, val key: String)
    // TODO: add type later
    private data class ProcessorKey(val scope: EventScope, val key: String)


    private inner class EventBusSubscription<in T : Event, out U : Any>(
            val eventScope: EventScope,
            val key: String,
            val callback: (T) -> U) : Subscription {

        override var cancelState: CancelState = NotCancelled
            private set


        @Suppress("UNCHECKED_CAST")
        override fun cancel(cancelState: CancelState) {
            try {
                logger.debug {
                    "Cancelling event bus subscription with scope '$eventScope' and key '$key'."
                }
                subscriptions.getOrDefault(SubscriberKey(eventScope, key), ThreadSafeQueueFactory.create())
                        .remove(this as EventBusSubscription<*, Unit>)
                this.cancelState = cancelState
            } catch (e: Exception) {
                logger.warn("Cancelling event bus subscription failed.", e)
            }
        }
    }
}
