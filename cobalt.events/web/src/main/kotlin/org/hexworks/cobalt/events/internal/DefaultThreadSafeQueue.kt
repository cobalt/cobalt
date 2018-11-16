package org.hexworks.cobalt.events.internal

import org.hexworks.cobalt.datatypes.Maybe

class DefaultThreadSafeQueue<E>(private val backend: MutableList<E> = mutableListOf())
    : ThreadSafeQueue<E>, MutableCollection<E> by backend {

    override fun offer(e: E): Boolean = backend.add(e)

    override fun drainTo(c: MutableCollection<E>): Int {
        val size = this.size
        c.addAll(this)
        this.clear()
        return size
    }

    override fun drainAll(): Collection<E> {
        return mutableListOf<E>().also {
            drainTo(it)
        }
    }

    override fun remove(element: E): Boolean = backend.remove(element)

    override fun peek(): Maybe<E> = Maybe.ofNullable(backend.first())

    override fun poll(): Maybe<E> {
        return if(this.isEmpty()) {
            Maybe.empty()
        } else {
            val element = first()
            remove(element)
            return Maybe.of(element)
        }
    }

    override fun pollLast(): Maybe<E> {
        return if(this.isEmpty()) {
            Maybe.empty()
        } else {
            val element = last()
            remove(element)
            return Maybe.of(element)
        }
    }
}
