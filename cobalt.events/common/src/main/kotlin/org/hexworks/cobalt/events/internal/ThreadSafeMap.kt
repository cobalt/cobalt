package org.hexworks.cobalt.events.internal

interface ThreadSafeMap<K, V> : MutableMap<K, V> {

    fun getOrDefault(key: K, defaultValue: V): V
}
