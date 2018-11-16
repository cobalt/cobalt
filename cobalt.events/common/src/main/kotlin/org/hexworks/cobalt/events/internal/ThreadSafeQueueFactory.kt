package org.hexworks.cobalt.events.internal

expect object ThreadSafeQueueFactory {

    fun <E> create(): ThreadSafeQueue<E>
}
