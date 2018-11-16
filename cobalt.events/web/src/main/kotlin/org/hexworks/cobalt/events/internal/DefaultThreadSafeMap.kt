package org.hexworks.cobalt.events.internal

class DefaultThreadSafeMap<K, V>(private val backend: MutableMap<K, V> = mutableMapOf())
    : ThreadSafeMap<K, V>, MutableMap<K, V> by backend {

    override fun getOrDefault(key: K, defaultValue: V): V {
        return backend.getOrElse(key) { defaultValue }
    }
}
