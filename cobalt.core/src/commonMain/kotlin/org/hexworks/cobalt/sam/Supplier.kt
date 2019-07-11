package org.hexworks.cobalt.sam

interface Supplier<out T> {

    fun get(): T
}
