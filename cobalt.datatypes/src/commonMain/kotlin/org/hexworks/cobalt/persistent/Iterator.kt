@file:Suppress("UNCHECKED_CAST")

package org.hexworks.cobalt.persistent

import org.hexworks.cobalt.Predicate
import org.hexworks.cobalt.datatypes.Maybe

interface Iterator<T> : kotlin.collections.Iterator<T> {

    /**
     * Returns the first element of this which satisfies the given predicate.
     */
    fun find(predicate: Predicate<in T>): Maybe<out T> {
        for (a in this) {
            if (predicate(a)) {
                return Maybe.of(a)
            }
        }
        return Maybe.empty()
    }

    /**
     * Maps the elements of this Iterator lazily using the given `mapper`.
     */
    fun <U> map(mapper: (T) -> U): Iterator<U> {
        if (!hasNext()) {
            return empty()
        } else {
            val that = this
            return object : Iterator<U> {

                override fun hasNext(): Boolean {
                    return that.hasNext()
                }

                override fun next(): U {
                    if (!hasNext()) {
                        throw NoSuchElementException()
                    }
                    return mapper(that.next())
                }
            }
        }
    }

    companion object {

        /**
         * Returns the singleton instance of the empty [Iterator].
         *
         * A call to [.hasNext] will always return `false`.
         * A call to [.next] will always throw a [NoSuchElementException].
         */
        fun <T> empty(): Iterator<T> {
            return EmptyIterator.INSTANCE as Iterator<T>
        }

        /**
         * Creates an [Iterator] which iterates over the given element.
         */
        fun <T> of(element: T): Iterator<T> {
            return SingletonIterator(element)
        }
    }
}

internal class EmptyIterator private constructor() : Iterator<Any> {

    override fun hasNext(): Boolean {
        return false
    }

    override fun next(): Any {
        throw NoSuchElementException()
    }

    override fun toString(): String {
        return "EmptyIterator"
    }

    companion object {

        val INSTANCE = EmptyIterator()
    }
}

internal class SingletonIterator<T>(private val element: T) : Iterator<T> {
    private var hasNext = true

    override fun hasNext(): Boolean {
        return hasNext
    }

    override fun next(): T {
        if (!hasNext()) {
            throw NoSuchElementException()
        }
        hasNext = false
        return element
    }

    override fun toString(): String {
        return "SingletonIterator"
    }
}
