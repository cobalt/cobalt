package org.hexworks.cobalt.events.api

import org.hexworks.cobalt.events.internal.ApplicationScope

/**
 * Subscribes the callee to [Event]s of type [T].
 */
inline fun <reified T : Event> EventBus.subscribe(eventScope: EventScope = ApplicationScope,
                                                  noinline callback: (T) -> Unit): Subscription {
    val key = T::class.simpleName ?: throw IllegalArgumentException(
            "Event class doesn't have a name: ${T::class}")
    return subscribe(eventScope = eventScope,
            key = key,
            callback = callback)
}
