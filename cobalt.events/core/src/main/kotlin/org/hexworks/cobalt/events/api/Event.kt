package org.hexworks.cobalt.events.api

interface Event {

    /**
     * An unique key for this [Event].
     * If not supplied, [kotlin.reflect.KClass.simpleName] will be used.
     */
    val key: String
        get() = this::class.simpleName
                ?: throw IllegalArgumentException("Event class doesn't have a name: ${this::class}")
}
