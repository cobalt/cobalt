package org.hexworks.cobalt.events.api

import org.hexworks.cobalt.events.internal.ApplicationScope

inline fun <reified T : Event> EventBus.subscribeTo(
        eventScope: EventScope = ApplicationScope,
        noinline callback: (T) -> Unit): Subscription {
    val key = T::class.simpleName ?: throw IllegalArgumentException(
            "Event class doesn't have a name: ${T::class}")
    return subscribeTo(eventScope = eventScope,
            key = key,
            fn = callback)
}