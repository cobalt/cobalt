package org.hexworks.cobalt.events.internal

actual object ThreadSafeQueueFactory {

    actual fun <E> create(): ThreadSafeQueue<E> {
        return DefaultThreadSafeQueue()
    }

}
