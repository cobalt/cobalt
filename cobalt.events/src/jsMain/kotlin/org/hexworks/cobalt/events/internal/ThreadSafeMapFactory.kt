package org.hexworks.cobalt.events.internal

actual object ThreadSafeMapFactory {

    actual fun <K, V> create(): ThreadSafeMap<K, V> {
        return DefaultThreadSafeMap()
    }

}
