package org.hexworks.cobalt.events.internal

expect object ThreadSafeMapFactory {

    fun <K, V> create(): ThreadSafeMap<K, V>
}
