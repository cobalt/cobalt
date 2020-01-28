package org.hexworks.cobalt.events.api

/**
 * Common interface for all [Event]s which can be sent using the [EventBus]. Each event
 * must have a [key] which can be used to group events from the same origin / cause together.
 * [trace] can be used to check the chain of events which caused this [Event]. Each [Event]
 * must also has an [emitter] which is the object responsible for emitting this [Event].
 */
interface Event {

    /**
     * An unique key for this [Event].
     * If not supplied, [kotlin.reflect.KClass.simpleName] will be used.
     */
    val key: String
        get() = this::class.simpleName
                ?: throw IllegalArgumentException("Event class doesn't have a name: ${this::class}")

    /**
     * The object which emitted *this* [Event].
     */
    val emitter: Any

    /**
     * Contains a (possibly empty) sequence of [Event]s which lead up to *this* [Event] in reverse
     * chronological order (most recent is first, oldest is last).
     */
    val trace: Iterable<Event>
        get() = listOf()
}
